package com.sinosoft.lis.admin;

import com.sinosoft.lis.db.SDUserDB;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDUserSchema;
import com.sinosoft.service.BusinessService;
import com.sinosoft.service.BusinessServiceParent;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;



public class UserMgtBL extends BusinessServiceParent implements BusinessService
{   
    public UserMgtBL()
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
        	SDUserSchema sdu = new SDUserDB().executeQuery("select * from sduser where userid='"+request.getParameter("UserID")+"'").get(1);
        	String[] pa = {"Password","UserName","NickName","Email","Phone","AuthFlag","TNo","IDNo","Address","Special"};
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
