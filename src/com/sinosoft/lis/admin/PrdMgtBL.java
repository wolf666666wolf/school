package com.sinosoft.lis.admin;

import com.sinosoft.lis.db.SDProductDB;
import com.sinosoft.lis.db.SDSellerDB;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDProductSchema;
import com.sinosoft.lis.schema.SDSellerSchema;
import com.sinosoft.service.BusinessService;
import com.sinosoft.service.BusinessServiceParent;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;



public class PrdMgtBL extends BusinessServiceParent implements BusinessService
{   
    public PrdMgtBL()
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
        	SDProductSchema sdu = new SDProductDB().executeQuery("select * from sdproduct where prdid='"+request.getParameter("PrdID")+"'").get(1);
        	String[] pa = {"R1"};
        	for(int i=0;i<pa.length;i++)
        	{
        		sdu.setV(pa[i], request.getParameter(pa[i]));
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
