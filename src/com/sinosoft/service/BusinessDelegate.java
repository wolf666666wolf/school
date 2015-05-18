/**
 * Copyright (c) 2008 sinosoft  Co. Ltd.
 * All right reserved.
 */
package com.sinosoft.service;

import javax.naming.*;
import java.util.Properties;
import java.util.Hashtable;
import java.util.ResourceBundle;
import javax.rmi.PortableRemoteObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sinosoft.service.exception.ServiceException;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;

/**
 * <p>Title: Webҵ��ϵͳ</p>
 * <p>Description:����Ϊweb server��������ͳһ�ӿ�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: Sinosoft</p>
 * @author lk
 * @version 1.0
 * @date: 2008-05-20
 */
public class BusinessDelegate
{
	private int i = 0;
	/**
	 * ҵ�����ݣ���������
	 */
	private IMessage mMessage = null;
	/**
	 * Ĭ�ϲ����ļ�
	 */
//	private static ResourceBundle config = ResourceBundle.getBundle("config");
	/**
	 * EJB�����ṩ��
	 */
	private String server = "websphere";
	private String url = "";
	private String port = "";
	private String EJBName =  "";
	private String authUser ="";
	private String authPwd ="";
//	private String server = config.getString("server");
	/**
	 * EJB�����ṩ��url
	 */
//	private String url = config.getString("url");
	/**
	 * EJB�����ṩ��port
	 */
//	private String port = config.getString("port");
	/**
	 * EJBName
	 */
//	private String EJBName = config.getString("EJBName");
	/**��֤�û�*/
//	private String authUser = config.getString("user");

	/**��֤����*/
//	private String authPwd = config.getString("pwd");
	/**
	 * ������
	 */
	private static InitialContext initialContext = null;
	/**
	 * ������
	 */
	private static BusinessHome businessHome = null;

	/**
	 * ��־
	 */
	public static String classinfo = "com.sinosoft.service.BusinessDelegate";
	/**
	 * ��־
	 */
	public static Log log = LogFactory.getLog(classinfo);
	/**
	 * ��ʼ��������Ϣ
	 */
	private String info = null;
	/**
	 * �Ƿ��ʼ���ɹ���־
	 */
	private static boolean initflag = false;
	/**
	 * �Ƿ�ֲ㲿��
	 */
	private static boolean mode = false;

	/**
	 * ���캯��
	 */
	private BusinessDelegate()
	{
		if (mode)
		{
			init();
		}
	}

	/**
	 * ��ʼ��
	 */
	private void init()
	{
		try
		{
			getContext();
			getHome();
			initflag = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			i++;
			initflag = false;
			info = e.toString();
			log.error("��ʼ��ʧ��:" + info);
		}
	}

	/**
	 * ��ȡʵ��
	 */
	public static BusinessDelegate getBusinessDelegate()
	{
		return new BusinessDelegate();
	}

	/**
	 * web�������ӿ�
	 * @param VData aVData,String aOperate,String aBusiName
	 */
	public boolean submitData(VData aVData, String aOperate, String aBusiName)
	{

		boolean aSuccess = false;
		mMessage = new Message();
		mMessage.setVData(aVData);
		mMessage.setOperate(aOperate);
		mMessage.setBusiName(aBusiName);
		if (mode)
		{
			if (initflag)
			{
				aSuccess = doPost(mMessage);
			}
			else
			{
				aSuccess = false;
				mMessage.setSuccess(false);
				if (mMessage.getCErrors() != null)
				{
					mMessage.getCErrors().addOneError(info);
				}
				else
				{
					CErrors aCErrors = new CErrors();
					aCErrors.addOneError(info);
					mMessage.setCErrors(aCErrors);
				}
			}
		}
		else
		{
			aSuccess = doLocal(mMessage);
		}
		if (aSuccess)
		{
			return mMessage.getSuccess();
		}
		else
		{
			return false;
		}
	}

	/**
	 * web������񷵻ش���
	 * @return CErrors
	 */
	public CErrors getCErrors()
	{
		return mMessage.getCErrors();
	}

	/**
	 * web������񷵻ؽ��
	 * @return CErrors
	 */
	public VData getResult()
	{
		return mMessage.getResult();
	}

	/**
	 * �������
	 * @param Message aMessage
	 */
	private boolean doPost(IMessage aMessage)
	{
		try
		{
			Business mBusiness = businessHome.create();
			mMessage = mBusiness.request(mMessage);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			// ���³�ʼ��
			initflag = false;
			log.info(e.toString());
			if (mMessage.getCErrors() != null)
			{
				mMessage.getCErrors().addOneError(e.toString());
			}
			else
			{
				CErrors aCErrors = new CErrors();
				aCErrors.addOneError(e.toString());
				mMessage.setCErrors(aCErrors);
			}
			return false;
		}
	}

	/**
	 * �������
	 * @param Message aMessage
	 */
	private boolean doLocal(IMessage aMessage)
	{
		try
		{
			LocalService.getLocalService().doService(mMessage);
			return true;
		}
		catch (Exception e)
		{
			log.info(e.toString());
			if (mMessage.getCErrors() != null)
			{
				mMessage.getCErrors().addOneError(e.toString());
			}
			else
			{
				CErrors aCErrors = new CErrors();
				aCErrors.addOneError(e.toString());
				mMessage.setCErrors(aCErrors);
			}
			return false;
		}
	}

	/**
	 * ��λԶ�̷�����
	 * @return BusinessHome
	 */
	private void getHome() throws NamingException
	{
		//businessHome = (BusinessHome) initialContext.lookup(EJBName);
    	Object obj = initialContext.lookup(EJBName);
    	businessHome = (BusinessHome) PortableRemoteObject.narrow(obj,BusinessHome.class);

	}

	/**
	 * ��ʼ��������  ����Ҫ�ػ�
	 * @return InitialContext
	 */
	private void getContext() throws NamingException
	{
		if ("weblogic".equals(server))
		{
			Hashtable props = new Hashtable();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			props.put(Context.PROVIDER_URL, "t3://" + url + ":" + port);
			if (authUser != null && !authUser.equals(""))
			{
				props.put(Context.SECURITY_PRINCIPAL, authUser);
				props.put(Context.SECURITY_CREDENTIALS, authPwd);
			}

			initialContext = new InitialContext(props);
		}
		else if ("jboss".equals(server))
		{
			Hashtable props = new Hashtable();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL, "jnp://" + url + ":" + port);
			if (authUser != null && !authUser.equals(""))
			{
				props.put(Context.SECURITY_PRINCIPAL, authUser);
				props.put(Context.SECURITY_CREDENTIALS, authPwd);
			}
			initialContext = new InitialContext(props);
		}
//		else if ("websphere".equals(server))
//		{
//
//			try
//			{
//				Hashtable props = new Hashtable();
//				props.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
//				props.put(Context.PROVIDER_URL, "iiop://" + url + ":" + port);
//				System.out.println("iiop://" + url + ":" + port);
//				if (authUser != null && !authUser.equals(""))
//				{
//					props.put(Context.SECURITY_PRINCIPAL, authUser);
//					props.put(Context.SECURITY_CREDENTIALS, authPwd);
//				}
//				initialContext = new InitialContext(props);
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//
//		}
		else
		{
			log.error("��ʼ��ʧ��:" + "��֧�ִ������͵�������");
		}
	}
}
