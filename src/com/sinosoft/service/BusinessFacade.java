/**
 * Copyright (c) 2008 sinosoft  Co. Ltd.
 * All right reserved.
 */
package com.sinosoft.service;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import com.sinosoft.utility.*;
/**
 * <p>Title: Webҵ��ϵͳ</p>
 * <p>Description:����Ϊweb server ��App server֮���ṩͨ��</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: Sinosoft</p>
 * @author lk
 * @version 1.0
 * @date: 2008-05-20
 */
public class BusinessFacade implements SessionBean 
{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	private IMessage mMessage=null;
    /**
     * Ϊweb server ��������ṩ�ӿ�
     * @param Message aMessage
     */	
	
	public IMessage request(IMessage aMessage)
	{
		this.mMessage=aMessage;
		LocalService.getLocalService().doService(mMessage);
		mMessage.setVData(null);
		mMessage.setOperate(null);
		mMessage.setBusiName(null);
		return this.mMessage;
	}

	
	public void ejbCreate(){}		
	
	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
		// TODO Auto-generated method stub

	}

}
