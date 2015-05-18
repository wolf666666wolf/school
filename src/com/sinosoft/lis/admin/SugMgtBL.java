package com.sinosoft.lis.admin;

import com.sinosoft.lis.db.SDSuggestDB;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDSuggestSchema;
import com.sinosoft.service.BusinessService;
import com.sinosoft.service.BusinessServiceParent;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;



public class SugMgtBL extends BusinessServiceParent implements BusinessService
{   
    public SugMgtBL()
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
        	SDSuggestSchema sdsu = new SDSuggestDB().executeQuery("select * from sdsuggest where serialno='"+request.getParameter("SerialNo")+"'").get(1);
        	String[] pa = {"DealFlag","Remark"};
        	for(int i=0;i<pa.length;i++)
        	{
        		sdsu.setV(pa[i], request.getParameter(pa[i]));
        	}
        	sdsu.setR1(cd);
        	sdsu.setR2(ct);
        	m.put(sdsu, "UPDATE");
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
