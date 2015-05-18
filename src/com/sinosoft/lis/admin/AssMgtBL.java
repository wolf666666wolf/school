package com.sinosoft.lis.admin;

import com.sinosoft.lis.db.SDAssDB;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDAssSchema;
import com.sinosoft.service.BusinessService;
import com.sinosoft.service.BusinessServiceParent;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;



public class AssMgtBL extends BusinessServiceParent implements BusinessService
{   
    public AssMgtBL()
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
        	SDAssSchema sda = new SDAssDB().executeQuery("select * from sdass where assid='"+request.getParameter("AssID")+"'").get(1);
        	String[] pa = {"OrgName","ShotOrgName","Address","Email","Phone","LinkPerson","AssPeople","AssFeature","WebPage","AssType"};
        	for(int i=0;i<pa.length;i++)
        	{
        		sda.setV(pa[i], request.getParameter(pa[i]));
        	}
        	m.put(sda, "UPDATE");
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
