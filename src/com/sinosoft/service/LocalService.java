package com.sinosoft.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sinosoft.service.exception.ServiceException;
import com.sinosoft.utility.CErrors;

/**
 * <p>Title: ����ת������</p>
 *
 * <p>Description: ת�����</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: sinosoft</p>
 *
 * @author litao
 * @version 1.0
 */
public class LocalService implements LService{
	private static LocalService localService;
    private Handle handle ;
    /**�Ƿ��ʼ���ɹ���־*/
    private static boolean initflag = false;
    private String info;
    /**service����*/
    private BusinessFactory businessFactory = BusinessFactory.getFactory();
    public static String classinfo = "com.sinosoft.service.LocalService";
    public static Log log = LogFactory.getLog(classinfo);
    
    /**��Ӻ���ȫ�ִ�����*/
    public void setGlobalHandle(Handle handle)
    {
    	this.handle = handle;
    }
    /**���Service����*/
    public void addService(Service service)
    {
    	this.businessFactory.addService(service);
    }
    /**�Ƴ�service����*/
    public void removeService(Service service)
    {
    	this.businessFactory.removeService(service);
    }
    /**��ȡ��������Ӧ�ķ�������*/
    public Service getService(String serviceName)
    {
    	return this.businessFactory.getService(serviceName);
    }
    /**���캯��*/
    private LocalService() {
    	init();
    }
    
    /**��ʼ��  ͨ��XML���� ��ʼ���������Handle*/
    private void init()
    {
    	try
    	{
			ServiceParse tServiceParse = new ServiceParse(this);
			tServiceParse.parses();
			initflag = true;
			log.debug("��ʼ���ɹ�!");
		}
    	catch(ServiceException ex)
    	{
    		initflag = false;
    		info = ex.getMessage();
    		log.error(info);
    	}
    	catch(Exception ex)
    	{
    		initflag = false;
    		info = ex.getMessage();
    		log.error(info);
    	}
    }
    
    /**��ȡת��������ʵ��*/
    public static synchronized LService getLocalService()
    {
    	if(!initflag)
    	{
    		localService = new LocalService();
    	}
    	return localService;
    }
    
    /**ʵ�ʴ������*/
    public void doService(IMessage message)
    {
    	try
    	{
    		if(!initflag)
    		{
    			log.error("������δ�ɹ���ʼ��");
    			throw new ServiceException(info);   			
    		}
    		handle.invoke(message);
    	}
    	catch(ServiceException ex)
    	{
    		
    		message.setSuccess(false);
    		CErrors tCError = message.getCErrors();
    		if(tCError==null)
    		{
    			tCError = new CErrors();
    		}
    		tCError.addOneError(ex.getMessage());
    		message.setCErrors(tCError);
    	}
    	catch(Exception ex)
    	{
    		message.setSuccess(false);
    		CErrors tCError = message.getCErrors();
    		if(tCError==null)
    		{
    			tCError = new CErrors();
    		}
    		tCError.addOneError(ex.getMessage());
    		message.setCErrors(tCError);
    	}
    }
}
