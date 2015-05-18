package com.sinosoft.lis.pubfun;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sinosoft.lis.db.SDChannelDB;
import com.sinosoft.lis.db.SDContentDB;
import com.sinosoft.lis.db.SDVoteItemDB;
import com.sinosoft.lis.db.SDVoteMainDB;
import com.sinosoft.lis.schema.SDContentSchema;
import com.sinosoft.lis.schema.SDVoteMainSchema;
import com.sinosoft.lis.vschema.SDChannelSet;
import com.sinosoft.lis.vschema.SDContentSet;
import com.sinosoft.lis.vschema.SDVoteItemSet;
import com.sinosoft.lis.vschema.SDVoteMainSet;
import com.sinosoft.utility.ExeSQL;
import com.sinosoft.utility.SSRS;


public class CacheInfo
{
	String path;
    public CacheInfo()
    {
        path=this.getClass().getClassLoader().getResource("/").getPath();
        path=path.split("WEB-INF")[0]+"static/";
    }
    public void genVoteList()
    {
    	String varName = "votelist";
    	String jsName ="vote.js";
    	
    	SDVoteMainSet sdvms = new SDVoteMainDB().executeQuery("select * from sdvotemain where DisplayFlag='Y' ");
    	JSONArray ja = PubFun.set2JsonArr(sdvms);
    	PubFun.genJs(varName,path+jsName,ja.toString());
    }
    public void genVoteInfo(String voteNo)
    {
    	String varName = "voteinfo";
    	String jsName ="vote_"+voteNo+".js";
    	
    	SDVoteMainSchema sdvm = new SDVoteMainDB().executeQuery("select * from sdvotemain where voteno='"+voteNo+"'").get(1);
		SDVoteItemSet sdvis = new SDVoteItemDB().executeQuery("select * from sdvoteitem where voteno='"+voteNo+"'");
		JSONObject jv = PubFun.schema2JsonObj(sdvm);
		jv.accumulate("itemlist", PubFun.set2JsonArr(sdvis));
    	PubFun.genJs(varName,path+jsName,jv.toString());
    }
    public void genChnlList()
    {
    	String varName = "chnllist";
    	String jsName ="chnl.js";
    	
    	SDChannelSet sdcs = new SDChannelDB().executeQuery("select * from sdchannel where 1=1 order by cast (R1 as number) asc ");
    	JSONArray ja = PubFun.set2JsonArr(sdcs);
    	PubFun.genJs(varName,path+jsName,ja.toString());
    }
    public void genContentList()
    {
    	String SQL = "select distinct ChannelCode from sdcontent where r1='Y' ";
    	SSRS sr = new ExeSQL().execSQL(SQL);
    	for(int k=0;k<sr.getMaxRow();k++)
    	{
    		String varName = "contentlist";
        	String jsName ="class_"+sr.GetText(k+1, 1)+".js";
        	
        	SDContentSet sdcs = new SDContentDB().executeQuery("select * from sdcontent where r1='Y' and ChannelCode='"+sr.GetText(k+1, 1)+"' order by serialno desc");
        	for(int i=0;i<sdcs.size();i++)
        	{
        		sdcs.get(i+1).setContentHtml("");
        		sdcs.get(i+1).setContentTxt("");    		
        	}
        	JSONArray ja = PubFun.set2JsonArr(sdcs);
        	PubFun.genJs(varName,path+jsName,ja.toString());
    	}    	
    }
    public void genContentInfo(String contentNo)
    {
    	String varName = "contentinfo";
    	String jsName ="content_"+contentNo+".js";
    	
    	SDContentSchema sdvm = new SDContentDB().executeQuery("select * from sdcontent where serialno='"+contentNo+"'").get(1);
		JSONObject jv = PubFun.schema2JsonObj(sdvm);
    	PubFun.genJs(varName,path+jsName,jv.toString());
    }
    
    
}
