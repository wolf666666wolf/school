package com.sinosoft.service.handle;

import com.sinosoft.service.BusinessService;
import com.sinosoft.service.Handle;
import com.sinosoft.service.IMessage;
import com.sinosoft.service.Service;
import com.sinosoft.service.exception.ServiceException;
import com.sinosoft.utility.TransferData;
import com.sinosoft.utility.VData;

/**
 * ����ҵ���߼�����
 * ���,����ʵ��ҵ���� ���,���ش�����
 *  
 * <p>Description: ת�����</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: sinosoft</p>
 *
 * @author litao
 * @version 1.0
 * */
public class BusinessHandle implements Handle {
	private Service service;
	public BusinessHandle(Service service)
	{
		this.service = service;
	}
	public void init(TransferData transfer) {
	}
	
	public void invoke(IMessage message) {
		/**��ȡǰ̨�������*/
		VData tData = message.getVData();
		String tOperate = message.getOperate();
		BusinessService tBusinessService;
		try {
			/**ʵ������Ӧҵ������*/
			tBusinessService = (BusinessService) Class.forName(service.getClassName()).newInstance();
			
			/**�ύҵ����*/
			if(!tBusinessService.submitData(tData, tOperate))
			{
				/**������ش���*/
				message.setSuccess(false);
				message.setCErrors(tBusinessService.getErrors());
				message.setResult(tBusinessService.getResult());
			}
			else
			{
				/**������ش�����*/
				message.setSuccess(true);
				message.setCErrors(tBusinessService.getErrors());
				message.setResult(tBusinessService.getResult());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			throw new ServiceException("ʵ����ҵ���Ӧ��ʧ��!�쳣��Ϣ:"+ex.toString());
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
			throw new ServiceException("ʵ����ҵ���Ӧ��ʧ��!�쳣��Ϣ:"+ex.toString());
		} catch (InstantiationException ex) {
			ex.printStackTrace();
			throw new ServiceException("ʵ����ҵ���Ӧ��ʧ��!�쳣��Ϣ:"+ex.toString());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new ServiceException("ҵ����ʧ��!�쳣��Ϣ"+ex.toString());
		}
	}

}
