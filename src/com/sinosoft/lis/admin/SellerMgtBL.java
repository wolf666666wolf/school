package com.sinosoft.lis.admin;

import com.sinosoft.lis.db.SDSellerDB;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDSellerSchema;
import com.sinosoft.service.BusinessService;
import com.sinosoft.service.BusinessServiceParent;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;



public class SellerMgtBL extends BusinessServiceParent implements BusinessService
{   
    public SellerMgtBL()
    {
    	
    }    
    private String mOperate = "";
    private MMap m = new MMap();
    private VData mResult = new VData();
   

    public boolean submitData(VData cInputData, String cOperate)
    {
    	super.submitData(cInputData, cOperate);
    	String tOperate=request.getParameter("o");

        this.mOperate = tOperate;
        if (mOperate.equals("save"))
        {
        	SDSellerSchema sdu = new SDSellerDB().executeQuery("select * from sdseller where sellerid='"+request.getParameter("SellerID")+"'").get(1);
        	String[] pa = {"Password","AuthFlag","R9"};
        	for(int i=0;i<pa.length;i++)
        	{
        		sdu.setV(pa[i], request.getParameter(pa[i]));
        	}
        	if("Y".equals(sdu.getAuthFlag()))
        	{
        		sdu.setR2("N");
        	}
        	m.put(sdu, "UPDATE");
        }
        PubSubmit ps = new PubSubmit();
        if(!ps.submitData(m))
        {
        	return false;
        }
        return true;
    }


	public VData getResult() {
		// TODO Auto-generated method stub
		return mResult;
	}


	public CErrors getErrors() {
		// TODO Auto-generated method stub
		return null;
	}
}
