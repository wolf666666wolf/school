package com.sinosoft.lis.admin;

import net.sf.json.JSONArray;

import com.sinosoft.lis.db.SDChannelDB;
import com.sinosoft.lis.pubfun.CacheInfo;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDChannelSchema;
import com.sinosoft.lis.vschema.SDChannelSet;
import com.sinosoft.service.BusinessService;
import com.sinosoft.service.BusinessServiceParent;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;



public class ChnlMgtBL extends BusinessServiceParent implements BusinessService
{   
    public ChnlMgtBL()
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
        	SDChannelSchema sdc = new SDChannelSchema();
        	sdc.setChannelName(request.getParameter("ChannelName"));
        	sdc.setParentChannel(request.getParameter("ParentChannel"));
        	sdc.setChannelLevel(request.getParameter("ChannelLevel"));
        	sdc.setURL(request.getParameter("URL"));
        	sdc.setR1(request.getParameter("R1"));
        	sdc.setR2(request.getParameter("R2"));
        	sdc.setR3(request.getParameter("R3"));
        	if("3".equals(sdc.getR3()))
        	{
        		sdc.setR4(request.getParameter("R4"));
            	sdc.setR5(request.getParameter("R5"));
            	sdc.setR6(request.getParameter("R6"));
            	sdc.setR7(request.getParameter("R7"));
            	sdc.setR8(request.getParameter("R8"));
        	}
        	
        	String sno = request.getParameter("SerialNo");
        	if(!"".equals(sno)&&sno!=null&&!"null".equals(sno))
        	{
        		sdc.setSerialNo(sno);
            	m.put(sdc, "UPDATE");
        	}
        	else
        	{
        		sdc.setSerialNo(PubFun.CreateMaxNo("C", "C", 4));
            	m.put(sdc, "INSERT");
        	}
        	
        }
        if (mOperate.equals("del"))
        {
        	m.put("delete from sdchannel where parentchannel='"+request.getParameter("SerialNo")+"'", "DELETE");
        	m.put("delete from sdchannel where serialno='"+request.getParameter("SerialNo")+"'", "DELETE");
        }
        PubSubmit ps = new PubSubmit();
        if(!ps.submitData(m))
        {
        	return false;
        }
        genCache();
        return true;
    }

    private void genCache()
    {
    	new CacheInfo().genChnlList();
    }
	public VData getResult() {
		// TODO Auto-generated method stub
		return null;
	}


	public CErrors getErrors() {
		// TODO Auto-generated method stub
		return null;
	}
}
