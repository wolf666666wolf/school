package com.sinosoft.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;

public interface BusinessService {
	
	public String cd = PubFun.getCurrentDate();
	public String ct = PubFun.getCurrentTime();

	/**�ύҵ����*/
    public boolean submitData(VData vData,String Operater);
    /**��ȡ������*/
    public VData getResult();
    /**��ȡ������Ϣ*/
    public CErrors getErrors();
}
