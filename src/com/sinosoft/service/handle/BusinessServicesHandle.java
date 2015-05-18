package com.sinosoft.service.handle;

import com.sinosoft.service.Handle;
import com.sinosoft.service.IMessage;
import com.sinosoft.service.LocalService;
import com.sinosoft.service.Service;
import com.sinosoft.utility.TransferData;
/**
 * ȫ��ҵ������ʵ��ҵ��Service�ĶԽ�,ͨ����������������Ӧservice�����ô���
 *  * <p>Description: ת�����</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: sinosoft</p>
 *
 * @author litao
 * @version 1.0
 * */
public class BusinessServicesHandle implements Handle{
	private LocalService localService;
	
	public BusinessServicesHandle(LocalService localService)
	{
		this.localService = localService;
	}
	public void init(TransferData transfer) {		
	}

	public void invoke(IMessage message) {
		Handle tHandle;
		Service tService;
		/**���ݰ������ȡ��Ӧ����*/
		tService = localService.getService(message.getBusiName());
		/**����service��������*/
		tHandle = tService.getHandle();
		tHandle.invoke(message);
	}	
}
