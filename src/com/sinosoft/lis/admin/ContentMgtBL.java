package com.sinosoft.lis.admin;

import com.sinosoft.lis.db.SDContentDB;
import com.sinosoft.lis.pubfun.CacheInfo;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDContentSchema;
import com.sinosoft.service.BusinessService;
import com.sinosoft.service.BusinessServiceParent;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.VData;



public class ContentMgtBL extends BusinessServiceParent implements BusinessService
{   
    public ContentMgtBL()
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
        if (mOperate.equals("save"))
        {
        	String cno = request.getParameter("SerialNo");
        	SDContentSchema sdaa = new SDContentSchema();
        	if(!"".equals(cno)&&cno!=null&&!"null".equals(cno))
        	{

            	if("C0000".equals(request.getParameter("ChannelCode")))
            	{
            		cno=request.getParameter("SpecFlag");
            	}
            	sdaa = new SDContentDB().executeQuery("select * from SDContent where serialno='"+request.getParameter("SerialNo")+"'").get(1);
        		sdaa.setSerialNo(cno);
        		mResult.add(cno);
        		mSNO=cno;
        	}
        	else
        	{
        		String serialno="";

            	if("C0000".equals(request.getParameter("ChannelCode")))
            	{
            		serialno=request.getParameter("SpecFlag");
            	}
            	else
            	{
            		serialno=PubFun.CreateMaxNo("CT", "CT", 10);
            	}
        		sdaa.setSerialNo(serialno);
        		mResult.add(serialno);
        		mSNO=serialno;

        	}

        	String[] pa = {"ContentTitle","ContentTxt","ContentHtml","R1","IssueDate","IssueTime","ContentFrom","ChannelCode"};
        	for(int i=0;i<pa.length;i++)
        	{
        		sdaa.setV(pa[i], request.getParameter(pa[i]));
        	}
        	if("".equals(sdaa.getIssueDate()))
            	sdaa.setIssueDate(cd);
        	if("".equals(sdaa.getIssueTime()))
            	sdaa.setIssueTime(ct);

        	m.put(sdaa, "DELETE&INSERT");
        }
        if (mOperate.equals("del"))
        {
        	String SerialNo = request.getParameter("SerialNo");
        	m.put("delete from sdcontent where serialno = '"+SerialNo+"'", "DELETE");
        }
        PubSubmit ps = new PubSubmit();
        if(!ps.submitData(m))
        {
        	return false;
        }

        if (!mOperate.equals("del"))
        {
            new CacheInfo().genContentList();
            new CacheInfo().genContentInfo(mSNO);
        	
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
