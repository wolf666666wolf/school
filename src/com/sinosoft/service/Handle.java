package com.sinosoft.service;

import com.sinosoft.service.IMessage;
import com.sinosoft.utility.TransferData;

/**
 * <p>Title: �������ӿ�,���д���ʵ�ָýӿ�</p>
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
public interface Handle {
	/**���ڳ�ʼ��,��ʼ��ʱ���Զ�����*/
	public void init(TransferData tTransfer);
	/**�������*/
    public void invoke(IMessage message);
}
