package com.sinosoft.service.handle;

import com.sinosoft.service.Handle;
import com.sinosoft.service.IMessage;
import com.sinosoft.utility.TransferData;

/**
 * <p>Title: ���ϴ�����,��Ӧǰ�ô���,���Ĵ���,���ô���</p>
 *
 * <p>Description: ejbת�����</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: sinosoft</p>
 *
 * @author litao
 * @version 1.0
 */
public class ServiceHandle implements Handle{
	public static final String PREHANDLE = "PreHandle";
	public static final String AFTHANDLE = "AftHandle";
	public static final String HANDLE = "Handle";
    private SortHandle preHandle;
    private SortHandle aftHandle;
    private Handle handle;
    public ServiceHandle() {
    }
	public void init(TransferData transfer) {
		preHandle = (SortHandle)transfer.getValueByName(PREHANDLE);
		aftHandle = (SortHandle)transfer.getValueByName(AFTHANDLE);
		handle = (Handle)transfer.getValueByName(HANDLE);
	}

	public void invoke(IMessage message) {
		/**ִ��ǰ��*/
		preHandle.invoke(message);
		/**ִ�к��Ĵ���*/
		handle.invoke(message);
		/**ִ�к���*/
		aftHandle.invoke(message);
	}
}
