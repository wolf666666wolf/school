package com.sinosoft.service.exception;

import java.io.PrintStream;
/**
 * service��Ӧ�쳣����
 *  * <p>Description: ת�����</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: sinosoft</p>
 *
 * @author litao
 * @version 1.0
 * */
public class ServiceException extends RuntimeException {
	public ServiceException(String msg)
	{
		super(msg);
	}
	public ServiceException(String msg,Throwable tExcep)
	{
		super(msg,tExcep);
	}
}
