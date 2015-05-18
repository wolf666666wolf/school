package com.sinosoft.lis.admin;

import com.sinosoft.lis.db.SDContentDB;
import com.sinosoft.lis.pubfun.CacheInfo;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDContentSchema;
import com.sinosoft.lis.schema.SDSellerClassSchema;
import com.sinosoft.service.BusinessService;
import com.sinosoft.service.BusinessServiceParent;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;



public class SellerClassMgtBL extends BusinessServiceParent implements BusinessService
{   
    public SellerClassMgtBL()
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
    	String mSNO = "";
        if (mOperate.equals("add"))
        {
        	String cno = request.getParameter("SerialNo");
        	String name = request.getParameter("ClassName");
        	SDSellerClassSchema sdsc = new SDSellerClassSchema();
        	sdsc.setChannelCode(cno);
        	sdsc.setClassName(name);
        	sdsc.setSerialNo(PubFun.CreateMaxNo("SER", "SER", 15));
        	m.put(sdsc, "INSERT");
        	
        }

        if (mOperate.equals("mod"))
        {
        	String cno = request.getParameter("SerialNo");
        	String name = request.getParameter("ClassName");
        	m.put("update sdsellerclass set classname='"+name+"' where serialno = '"+cno+"'", "UPDATE");
        }
        if (mOperate.equals("del"))
        {
        	String SerialNo = request.getParameter("SerialNo");
        	m.put("delete from sdsellerclass where serialno = '"+SerialNo+"'", "DELETE");
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
