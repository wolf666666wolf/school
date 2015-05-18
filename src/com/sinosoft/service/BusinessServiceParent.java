package com.sinosoft.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinosoft.lis.pubfun.GlobalInput;
import com.sinosoft.utility.VData;

public class BusinessServiceParent {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected GlobalInput mGI = new GlobalInput();
	public BusinessServiceParent()
	{
		
	}
    public boolean submitData(VData cInputData, String cOperate)
    {
    	request = (HttpServletRequest) cInputData.getObjectByObjectName("ORG.APACHE.CATALINA.CONNECTOR.REQUESTFACADE", 0);
    	response = (HttpServletResponse) cInputData.getObjectByObjectName("ORG.APACHE.CATALINA.CONNECTOR.RESPONSEFACADE", 0);
    	mGI = (GlobalInput)cInputData.getObjectByObjectName("COM.SINOSOFT.LIS.PUBFUN.GLOBALINPUT", 0);
    	return true;
    }

	
}
