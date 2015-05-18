package com.sinosoft.service;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;

import com.sinosoft.service.exception.ServiceException;
import com.sinosoft.service.handle.BusinessHandle;
import com.sinosoft.service.handle.BusinessServicesHandle;
import com.sinosoft.service.handle.ServiceHandle;
import com.sinosoft.service.handle.SortHandle;
import com.sinosoft.utility.TransferData;

/**
 * �����ĵ�����,��ʼ������service,�������
 *  * <p>Description: ת�����</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: sinosoft</p>
 *
 * @author litao
 * @version 1.0
 * */
public class ServiceParse {
	/**�����ļ���*/
	public static final String CONFILE = "Engine.xml";
	/**LocalService�ڵ�*/
	public static final String GLOBALHANDLE = "GlobalHandle";
	/**Service�ڵ�*/
	public static final String SERVICE = "Service";
	/**ǰ�ô���*/
	public static final String REQUESTHANDLE = "RequestHandle";
	/**���ô���*/
	public static final String RESPONSEHANDLE = "ResponseHandle";
	/**��������*/
	public static final String HANDLE = "Handle";
	/**��ӦService����*/
	public static final String NAME = "name";
	/**������*/
	public static final String SCOPE = "scope";
	/**��ӦClass*/
	public static final String CLASSNAME = "type";
	/**��������*/
	public static final String INITPARAM = "init-param";
	/**��������*/
	public static final String PARAMNAME = "param-name";
	/**����ֵ*/
	public static final String PARAMVALUE = "param-value";
	
	private DOMBuilder dBuilder = new DOMBuilder();
	private Document doc ;
	private LocalService localService ;
	
	public static String className = "com.sinosoft.service.ServiceParse";
	/**log��־*/
	public static Log log = LogFactory.getLog(className);
	
	public ServiceParse(LocalService tLocalService)
	{
		this.localService = tLocalService;
	}
	/**����XML,����LocalService,Service*/
	public void parses()
	{
		/**��ȡ�����ļ�*/
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(CONFILE); 
		if(is==null)
		{
		String msg = "�����ļ�" + CONFILE + "������,���ʵ";
		//log.fatal(msg);
		//modify by cuimq
		log.error(msg);
		System.out.println(msg);
		throw new ServiceException(msg);
		}
		
		org.w3c.dom.Document document = null; 
		 try 
	       { 
	            DocumentBuilderFactory   factory = DocumentBuilderFactory.newInstance();    
	            DocumentBuilder builder=factory.newDocumentBuilder();    
	            document=builder.parse(is);    
	            document.normalize(); 
	       } 
	      catch (Exception ex){ 
	           ex.printStackTrace(); 
	       }  
	      doc=dBuilder.build(document);

//		try
//		{
//			doc = dBuilder.build(is);
//		}
//		catch(JDOMException ex)
//		{
//			String msg = "JDOM����ʧ��";
//			log.fatal(msg);
//			ex.printStackTrace();
//			throw new ServiceException(msg,ex);
//		}
		Element root = doc.getRootElement();
		// ��ʼ��
		Element tEle = root.getChild(GLOBALHANDLE);		
		Handle tGlobalHandle = getServiceHandle(tEle);
		localService.setGlobalHandle(tGlobalHandle);
		// ��ʼ��Service
		List tServiceList = root.getChildren(SERVICE);
		parseServices(tServiceList);		
	}
	
	/**����Services�����ɹ���*/
	private void parseServices(List tServiceList)
	{		
		Iterator tServiceIter = tServiceList.iterator();
		while(tServiceIter.hasNext())
		{
			Element tEle = (Element)tServiceIter.next();	
			Service service = new Service();
			Handle serviceHandle = new ServiceHandle();								
			String name = tEle.getAttributeValue(NAME);
			String classname = tEle.getAttributeValue(CLASSNAME);
			String scope =  tEle.getAttributeValue(SCOPE);	
			service.setClassName(classname);
			service.setServiceName(name);
			service.setScope(scope);
			//service.setRoles(roles);
			/**��ʼ��Serviceǰ�ô���*/
			SortHandle tPreHandle = getSortHandle(tEle,REQUESTHANDLE);
			/**��ʼ��Service���ô���*/
			SortHandle tAftHandle =  getSortHandle(tEle,RESPONSEHANDLE);					
			/**���service���Ĵ���*/
			Handle tHandle = new BusinessHandle(service);			
			/**��ʼ��service*/
			TransferData tTransferData = new TransferData();
			tTransferData.setNameAndValue(ServiceHandle.PREHANDLE, tPreHandle);
			tTransferData.setNameAndValue(ServiceHandle.AFTHANDLE, tAftHandle);
			tTransferData.setNameAndValue(ServiceHandle.HANDLE, tHandle);
			serviceHandle.init(tTransferData);	
			service.setHandle(serviceHandle);
			localService.addService(service);
		}
	}
		
	/**��ȡlcoalservice�Ĵ�����*/
	private Handle getServiceHandle(Element tEle)
	{	
		Handle globalHandle = new ServiceHandle();			
		/**��ȡGlobalǰ�ô�������*/
		SortHandle tPreHandle = getSortHandle(tEle,REQUESTHANDLE);				
		/**��ȡGlobal���ô�������*/
		SortHandle tAftHandle = getSortHandle(tEle,RESPONSEHANDLE);							
		//���global���Ĵ�����
		Handle tHandle = new BusinessServicesHandle(localService);		
		//��ʼ��global������
		TransferData tTransferData = new TransferData();
		tTransferData.setNameAndValue(ServiceHandle.PREHANDLE, tPreHandle);
		tTransferData.setNameAndValue(ServiceHandle.AFTHANDLE, tAftHandle);
		tTransferData.setNameAndValue(ServiceHandle.HANDLE, tHandle);
		globalHandle.init(tTransferData);		
		return globalHandle;
	}
	
	/**��ȡSortHandle*/
	private SortHandle getSortHandle(Element tEle,String type)
	{
		SortHandle tSortHandle = new SortHandle();
		List tList = tEle.getChildren(type);
		Iterator tIterator = tList.iterator();
		while (tIterator.hasNext()) {
			Element tResponseEle = (Element) tIterator.next();
			List tHandleList = tResponseEle.getChildren(HANDLE);
			Iterator tHandleIter = tHandleList.iterator();
			while (tHandleIter.hasNext()) {
				Element tHandleEle = (Element) tHandleIter.next();
				Handle tHandle = getHandle(tHandleEle);
				if(tHandle!=null)
					tSortHandle.add(tHandle);
			}
		}
		return tSortHandle;
	}
	/**��ȡ������handle������ʼ������*/
	private Handle getHandle(Element tEle)
	{
		Element tHandleEle = tEle;
		Handle tHandle;
		TransferData tTransfer = new TransferData();
		String className = tHandleEle.getAttributeValue(CLASSNAME);
		try {
			/**ֱ��ʵ����*/
			tHandle = (Handle) Class.forName(className).newInstance();
			/**��ȡ����*/
			List paraList = tEle.getChildren(INITPARAM);
			Iterator paraIter = paraList.iterator();
			while (paraIter.hasNext()) {
				Element paraEle = (Element) paraIter.next();
				Element nameEle = paraEle.getChild(PARAMNAME);
				Element valueEle = paraEle.getChild(PARAMVALUE);
				String name = "";
				String value = "";
				if (nameEle != null) {
					name = nameEle.getTextTrim();
				}
				if (valueEle != null) {
					value = valueEle.getTextTrim();
				}
				tTransfer.setNameAndValue(name, value);
			}
			/**��������ʼ��*/
			tHandle.init(tTransfer);
		} catch (Exception ex) {
			String msg = "���" + className + "��ʧ��!\n�쳣:" + ex.getMessage();
			ex.printStackTrace();
			log.error(msg);
			return null;
		}
		return tHandle;
	}
}
