package com.sinosoft.service.handle;

import com.sinosoft.service.Handle;
import com.sinosoft.service.IMessage;
import com.sinosoft.service.LocalService;
import com.sinosoft.service.Service;
import com.sinosoft.utility.TransferData;
/**
 * 全局业务处理与实际业务Service的对接,通过工厂方法查找相应service并调用处理
 *  * <p>Description: 转发设计</p>
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
		/**根据包请求获取对应服务*/
		tService = localService.getService(message.getBusiName());
		/**传给service容器处理*/
		tHandle = tService.getHandle();
		tHandle.invoke(message);
	}	
}
