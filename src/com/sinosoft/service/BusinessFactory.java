package com.sinosoft.service;

import java.util.Hashtable;
import java.util.Map;

import com.sinosoft.service.exception.ServiceException;

/**
 * ҵ���߼����� ͨ��XML��Ӧ���� ������Ӧ��ҵ���߼�
 *  * <p>Description: ת�����</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: sinosoft</p>
 *
 * @author litao
 * @version 1.0
 * */
public class BusinessFactory {
	private static BusinessFactory businessFactory;
	/**Service����*/
	private Map services = new Hashtable();
	private BusinessFactory()
	{
	}
	/**���Service*/
	public void addService(Service service)
	{
		services.put(service.getServiceName(), service);
	}
	/**�Ƴ�Service*/
	public void removeService(Service service)
	{
		services.remove(service.getServiceName());
	}
	/**��̬*/
	public static synchronized BusinessFactory getFactory()
	{
		if(businessFactory==null)
		{
			businessFactory = new BusinessFactory();
		}
		return businessFactory;
	}
	/**��������,��ȡ��Ӧ������Ϣ*/
	public Service getService(String serviceName)
	{
		Service tService;
		String des = serviceName;
		if(des==null || des.equals(""))
		{
			throw new ServiceException("û�д�����÷���������Ϣ");
		}		
		tService = (Service)services.get(des);
		if(tService == null)
		{
			throw new ServiceException("û�ж�Ӧ"+des+"�ķ�����Ϣ");
		}
		return tService;		
	}
}
