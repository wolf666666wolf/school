package com.sinosoft.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;

public interface BusinessService {
	
	public String cd = PubFun.getCurrentDate();
	public String ct = PubFun.getCurrentTime();

	/**提交业务处理*/
    public boolean submitData(VData vData,String Operater);
    /**获取处理结果*/
    public VData getResult();
    /**获取错误信息*/
    public CErrors getErrors();
}
