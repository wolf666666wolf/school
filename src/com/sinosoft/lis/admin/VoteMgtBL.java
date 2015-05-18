package com.sinosoft.lis.admin;

import com.sinosoft.lis.db.SDVoteItemDB;
import com.sinosoft.lis.db.SDVoteTopicDB;
import com.sinosoft.lis.pubfun.CacheInfo;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDVoteItemSchema;
import com.sinosoft.lis.schema.SDVoteMainSchema;
import com.sinosoft.lis.schema.SDVoteTopicSchema;
import com.sinosoft.service.BusinessService;
import com.sinosoft.service.BusinessServiceParent;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.ExeSQL;
import com.sinosoft.utility.VData;



public class VoteMgtBL extends BusinessServiceParent implements BusinessService
{   
    public VoteMgtBL()
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
        String mVoteNo = "";
        if (mOperate.equals("save"))
        {
        	SDVoteMainSchema sdvm = new SDVoteMainSchema();
        	String[] pa = {"VoteTitle","VoteType","AnonyFlag","StartDate","EndDate","DisplayFlag","R1"};
        	for(int i=0;i<pa.length;i++)
        	{
        		sdvm.setV(pa[i], request.getParameter(pa[i]));
        	}
        	String vno = request.getParameter("VoteNo");
        	if(!"".equals(vno)&&vno!=null&&!"null".equals(vno))
        	{
        		sdvm.setVoteNo(vno);
            	m.put(sdvm, "UPDATE");
            	mVoteNo=vno;
        	}
        	else
        	{
        		sdvm.setVoteNo(PubFun.CreateMaxNo("V", "V", 6));
            	m.put(sdvm, "INSERT");
            	mResult.add(sdvm.getVoteNo());
            	mVoteNo=sdvm.getVoteNo();
        	}        	
        }

        if (mOperate.equals("addtopic"))
        {
        	String TopicTitle = request.getParameter("TopicTitle");
        	String VoteType = request.getParameter("VoteType");
        	String VoteNo = request.getParameter("VoteNo");
        	SDVoteTopicSchema sdvt = new SDVoteTopicSchema();
        	sdvt.setTopicTitle(TopicTitle);
        	sdvt.setVoteType(VoteType);
        	sdvt.setVoteNo(VoteNo);
        	sdvt.setR1(request.getParameter("R1"));
        	sdvt.setDisplayFlag(request.getParameter("DisplayFlagT"));
        	sdvt.setTopicNo(PubFun.CreateMaxNo("VT", "VT", 8));
        	m.put(sdvt, "INSERT");
        	
        }
        
        if (mOperate.equals("modtopic"))
        {
        	String TopicNo = request.getParameter("topicno");
        	SDVoteTopicSchema sdvt = new SDVoteTopicDB().executeQuery("select * from SDVoteTopic where topicno='"+TopicNo+"'").get(1);
        	String TopicTitle = request.getParameter("TopicTitle");
        	String VoteType = request.getParameter("VoteType");
        	String VoteNo = request.getParameter("VoteNo");
        	sdvt.setTopicTitle(TopicTitle);
        	sdvt.setVoteType(VoteType);
        	sdvt.setVoteNo(VoteNo);
        	sdvt.setR1(request.getParameter("R1"));
        	sdvt.setDisplayFlag(request.getParameter("DisplayFlagT"));
        	m.put(sdvt, "UPDATE");
        	
        }
        if (mOperate.equals("deltopic"))
        {
        	String TopicNo = request.getParameter("topicno");
        	m.put("delete from sdvotetopic where topicno='"+TopicNo+"'", "DELETE");
        	m.put("delete from sdvoteitem where topicno='"+TopicNo+"'", "DELETE");
        	m.put("delete from sdvotetrace where topicno='"+TopicNo+"'", "DELETE");
        }
        if (mOperate.equals("additem"))
        {
        	SDVoteItemSchema sdvi = new SDVoteItemSchema();
        	sdvi.setTopicNo(request.getParameter("TopicNo"));
        	sdvi.setVoteItem(request.getParameter("ItemTitle"));
        	sdvi.setVoteSeq(request.getParameter("VoteSeq"));
        	sdvi.setVoteNo(new ExeSQL().getOneValue("select voteno from sdvotetopic where topicno='"+request.getParameter("TopicNo")+"'"));
        	sdvi.setVoteCount("0");
        	sdvi.setItemNo(PubFun.CreateMaxNo("I", "I", 6));
        	m.put(sdvi, "INSERT");
        }
        if (mOperate.equals("moditem"))
        {
        	SDVoteItemSchema sdvi = new SDVoteItemDB().executeQuery("select * from sdvoteitem where itemno='"+request.getParameter("itemno")+"'").get(1);
        	sdvi.setTopicNo(request.getParameter("TopicNo"));
        	sdvi.setVoteItem(request.getParameter("ItemTitle"));
        	sdvi.setVoteSeq(request.getParameter("VoteSeq"));
        	m.put(sdvi, "UPDATE");
        }

        if (mOperate.equals("delitem"))
        {

        	String ItemNo = request.getParameter("itemno");
        	m.put("delete from sdvoteitem where ItemNo='"+ItemNo+"'", "DELETE");
        	m.put("delete from sdvotetrace where ItemNo='"+ItemNo+"'", "DELETE");
        }
        if (mOperate.equals("del"))
        {
        	String VoteNo = request.getParameter("VoteNo");
        	m.put("delete from sdvotemain where voteno='"+VoteNo+"'", "DELETE");
        	m.put("delete from sdvoteitem where voteno='"+VoteNo+"'", "DELETE");
        	m.put("delete from sdvotetrace where voteno='"+VoteNo+"'", "DELETE");
        	m.put("delete from sdvotetopic where voteno='"+VoteNo+"'", "DELETE");
        }
        PubSubmit ps = new PubSubmit();
        if(!ps.submitData(m))
        {
        	return false;
        }
        genCache(mVoteNo);
        return true;
    }

    private void genCache(String voteNo)
    {
    	//先生成list
    	new CacheInfo().genVoteList();
    	
    	//生成单个投票
    	if(!"".equals(voteNo))
    	{
    		new CacheInfo().genVoteInfo(voteNo);
    	}
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
