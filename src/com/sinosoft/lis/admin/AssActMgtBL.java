package com.sinosoft.lis.admin;

import com.sinosoft.lis.db.SDAssActivityDB;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDAssActivitySchema;
import com.sinosoft.service.BusinessService;
import com.sinosoft.service.BusinessServiceParent;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;



public class AssActMgtBL extends BusinessServiceParent implements BusinessService
{   
    public AssActMgtBL()
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
        	SDAssActivitySchema sdaa = new SDAssActivityDB().executeQuery("select * from sdassactivity where actid='"+request.getParameter("ActID")+"'").get(1);
        	String[] pa = {"AppStatus","AppRemark"};
        	for(int i=0;i<pa.length;i++)
        	{
        		sdaa.setV(pa[i], request.getParameter(pa[i]));
        	}
        	sdaa.setAppDate(cd);
        	sdaa.setAppTime(ct);
        	sdaa.setApper(mGI.Operator);
        	m.put(sdaa, "UPDATE");
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
