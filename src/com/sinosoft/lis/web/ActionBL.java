package com.sinosoft.lis.web;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sinosoft.lis.db.SDAssActAtnDB;
import com.sinosoft.lis.db.SDAssActivityDB;
import com.sinosoft.lis.db.SDAssDB;
import com.sinosoft.lis.db.SDContentDB;
import com.sinosoft.lis.db.SDCrawlerContentDB;
import com.sinosoft.lis.db.SDOrderDB;
import com.sinosoft.lis.db.SDProductAssessDB;
import com.sinosoft.lis.db.SDProductDB;
import com.sinosoft.lis.db.SDProductFavoriteDB;
import com.sinosoft.lis.db.SDSellerClassDB;
import com.sinosoft.lis.db.SDSellerDB;
import com.sinosoft.lis.db.SDSellerFollowDB;
import com.sinosoft.lis.db.SDUserDB;
import com.sinosoft.lis.db.SDVoteItemDB;
import com.sinosoft.lis.db.SDVoteMainDB;
import com.sinosoft.lis.db.SDVoteTopicDB;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDAssActAtnSchema;
import com.sinosoft.lis.schema.SDAssActivitySchema;
import com.sinosoft.lis.schema.SDAssSchema;
import com.sinosoft.lis.schema.SDContentSchema;
import com.sinosoft.lis.schema.SDOrderSchema;
import com.sinosoft.lis.schema.SDProductAssessSchema;
import com.sinosoft.lis.schema.SDProductFavoriteSchema;
import com.sinosoft.lis.schema.SDProductSchema;
import com.sinosoft.lis.schema.SDSellerFollowSchema;
import com.sinosoft.lis.schema.SDSellerSchema;
import com.sinosoft.lis.schema.SDSendCodeTraceSchema;
import com.sinosoft.lis.schema.SDSuggestSchema;
import com.sinosoft.lis.schema.SDUserSchema;
import com.sinosoft.lis.schema.SDVoteMainSchema;
import com.sinosoft.lis.schema.SDVoteTraceSchema;
import com.sinosoft.lis.vschema.SDAssActAtnSet;
import com.sinosoft.lis.vschema.SDAssActivitySet;
import com.sinosoft.lis.vschema.SDAssSet;
import com.sinosoft.lis.vschema.SDContentSet;
import com.sinosoft.lis.vschema.SDCrawlerContentSet;
import com.sinosoft.lis.vschema.SDOrderSet;
import com.sinosoft.lis.vschema.SDProductAssessSet;
import com.sinosoft.lis.vschema.SDProductFavoriteSet;
import com.sinosoft.lis.vschema.SDProductSet;
import com.sinosoft.lis.vschema.SDSellerClassSet;
import com.sinosoft.lis.vschema.SDSellerFollowSet;
import com.sinosoft.lis.vschema.SDSellerSet;
import com.sinosoft.lis.vschema.SDUserSet;
import com.sinosoft.lis.vschema.SDVoteItemSet;
import com.sinosoft.lis.vschema.SDVoteMainSet;
import com.sinosoft.lis.vschema.SDVoteTopicSet;
import com.sinosoft.utility.CErrors;
import com.sinosoft.utility.ExeSQL;
import com.sinosoft.utility.SSRS;
import com.sinosoft.utility.VData;


public class ActionBL
{
    public CErrors mErrors = new CErrors();
    public VData mResult = new VData();
    HttpSession session;
    JSONObject data ;
    JSONObject rd =new JSONObject();;
    public String mOperate;
    public boolean succ = true;
    public String msg = "";
    public String cd = PubFun.getCurrentDate();
    public String ct = PubFun.getCurrentTime();
    public MMap m = new MMap();
    public VData vd = new VData();
    public PubSubmit ps = new PubSubmit();
    public String[] action_cache={"recommend_querylist","brp_querylist","brp_querydetail","msg_querylist","help_querylist"};
    public ActionBL()
    {
    	
    }
    public boolean submitData(VData cInputData, String cOperate)
    {
    	mOperate = cOperate;
        if (!getInputData(cInputData))
        {
            return false;
        }
        
        //判断是否走缓存
        String r="";
        ActionCached ac = ActionCached.getInstance();
//        for(int i=0;i<action_cache.length;i++)
//        {
//        	if(action_cache[i].equals(mOperate))
//        	{
//        		//走缓存
//        		r = ac.findCache(mOperate, data.toString());
//        		if(r!=null)
//        		{
//        			mResult.add(r);
//        			return true;
//        		}
//        		else
//        		{
//        			break;
//        		}
//        	}
//        }   
        try {
        	this.getClass().getMethod(mOperate,new Class[]{}).invoke(this, new Object[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			succ=false;
        	msg="不支持的操作";
		} 
        String result = "{succ:"+succ+",msg:'"+msg+"',data:"+rd.toString()+"}";
        JSONObject rjo = JSONObject.fromObject(result);
        
        mResult.add(rjo.toString());
        if(r==null)
        {
        	ac.putCache(mOperate, data.toString(), result);
        }

        return true;
    }

    public void receive_receivemessage()
    {
    	JSONArray rml = data.getJSONArray("data");
    	for(int i=0;i<rml.size();i++)
    	{
    		String Phone = rml.getJSONObject(i).get("mobile").toString();
    		String content = rml.getJSONObject(i).get("content").toString();
    		String receivetime = rml.getJSONObject(i).get("receivetime").toString();
    		String taskid = rml.getJSONObject(i).get("taskid").toString();
    		String extno = rml.getJSONObject(i).get("extno").toString();
    		System.out.println(">>>>>>>"+Phone+"|"+content+"|"+receivetime+"|"+taskid+"|"+extno);
    		//先找订单
    		SDOrderSet os = new SDOrderDB().executeQuery("select * from sdorder where orderid like '%"+extno+"' order by orderid desc");
    		if(os.size()>0)
    		{

    			//校验是否评价
    			if(!PubFun.checkZero("select count(*) from SDProductAssess where orderid='"+os.get(1).getOrderID()+"' and userid='"+os.get(1).getUserID()+"'"))
    			{
    				//存储发送轨迹
    				String c = "您的订单["+os.get(1).getOrderID()+"]已经评价。【清华园社区】";
    	        	SDSendCodeTraceSchema sdsct = new SDSendCodeTraceSchema();
    	        	sdsct.setSerialNo(UUID.randomUUID().toString());
    	        	sdsct.setPhone(Phone);
    	        	sdsct.setCode("");
    	        	sdsct.setSendDate(cd);
    	        	sdsct.setSendTime(ct);
    	        	sdsct.setR1("WEB");
    	        	sdsct.setR2(c);
    	        	m.put(sdsct, "INSERT");
    	        	ps.submitData(m);
    				PubFun.sendMessage(Phone, c, extno);
    				return;
    			}
    			String Level="";
    			String Content="";
    			String[] lc = content.split(",");
    			if(lc.length<2)lc=content.split("，");
    			if(lc.length>1)
    			{
    				Level=lc[0];
    				if(content.length()>=2)
    					Content=content.substring(2, content.length());
    				else
    					Content=content;
    				try {
						int lf = Integer.parseInt(Level);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						Level="5";
						Content=content;
					}
    			}
    			
    			data=new JSONObject();
    			data.accumulate("OrderID", os.get(1).getOrderID());
    			data.accumulate("UserID", os.get(1).getUserID());
    			data.accumulate("Level", Level);
    			data.accumulate("Content", Content);
    			data.accumulate("Phone", Phone);
    			product_assess();
    		}
    	}
    }
    public void seller_setcounter()
    {
    	String SellerID = data.get("SellerID").toString();
    	m.put("update sdseller set r8=ifnull(r8,0)*1+1 where sellerid='"+SellerID+"'", "UPDATE");
    	succ=ps.submitData(m);
    }
    public void content_setcounter()
    {
    	String SerialNo = data.get("SerialNo").toString();
    	m.put("update sdcontent set r8=ifnull(r8,0)*1+1 where serialno='"+SerialNo+"'", "UPDATE");
    	succ=ps.submitData(m);
    	rd.accumulate("R8", new ExeSQL().getOneValue("select r8 from sdcontent where serialno='"+SerialNo+"'"));
    }
    public void seller_getorderlist ()
    {
    	String SellerID = data.get("SellerID").toString();
    	String SQL = "select a.OrderID,b.prdname,a.ArriveDate,a.r1||a.address,a.Owner,a.phone,b.PrdType,a.OCount,a.Requirement,a.r2 from sdorder a,sdproduct b where a.prdid=b.prdid and b.sellerid='"+SellerID+"' order by a.makedate desc,a.maketime desc";

    	SSRS sr = new ExeSQL().execSQL(SQL);
    	JSONArray ja = new JSONArray();
    	for(int i=0;i<sr.getMaxRow();i++)
    	{
    		JSONObject ji = new JSONObject();
    		ji.accumulate("OrderID", sr.GetText(i+1, 1));
    		ji.accumulate("PrdName", sr.GetText(i+1, 2));
    		ji.accumulate("ArriveDate", sr.GetText(i+1, 3));
    		ji.accumulate("Address", sr.GetText(i+1, 4));
    		ji.accumulate("Owner", sr.GetText(i+1, 5));
    		ji.accumulate("Phone", sr.GetText(i+1, 6));
    		ji.accumulate("PrdType", sr.GetText(i+1, 7));
    		ji.accumulate("OCount", sr.GetText(i+1, 8));
    		ji.accumulate("Requirement", sr.GetText(i+1, 9));
    		ji.accumulate("R2", sr.GetText(i+1, 10));
    		if("已提交".equals(sr.GetText(i+1, 10)))
    			ji.accumulate("Display", "");
    		else
        		ji.accumulate("Display", "none");
    		
    		ja.add(ji);

    	}
    	rd.accumulate("orderlist", ja);
    	succ=true;
    	msg="查询成功";
    }
    public void seller_getasslist ()
    {
    	String SellerID = data.get("SellerID").toString();
    	String SQL = "select c.prdname,b.orderdate,a.level,a.content,a.assessdate,d.nickname,d.phone from SDProductAssess a,sdorder b,sdproduct c,sduser d where b.userid=d.userid and a.orderid=b.orderid and b.prdid=c.prdid and c.sellerid='"+SellerID+"' order by a.serialno desc ";
    	
    	SSRS sr = new ExeSQL().execSQL(SQL);
    	JSONArray ja = new JSONArray();
    	for(int i=0;i<sr.getMaxRow();i++)
    	{
    		JSONObject ji = new JSONObject();
    		ji.accumulate("PrdName", sr.GetText(i+1, 1));
    		ji.accumulate("OrderDate", sr.GetText(i+1, 2));
    		ji.accumulate("Level", sr.GetText(i+1, 3));
    		ji.accumulate("Content", sr.GetText(i+1, 4));
    		ji.accumulate("AssEssDate", sr.GetText(i+1, 5));
    		ji.accumulate("NickName", sr.GetText(i+1, 6));
    		ji.accumulate("Phone", sr.GetText(i+1, 7));
    		ja.add(ji);
    		
    	}
    	rd.accumulate("asslist", ja);
    	succ=true;
    	msg="查询成功";
    }

    public void user_getfavoritelist()
    {
    	String UserID = data.get("UserID").toString();
    	String SQL = "select c.shotorgname,c.sellerid,b.prdname,b.prdid,b.PrdPrice,b.discount,b.EffEndDate,b.remark,c.authflag from sdproductfavorite a,sdproduct b,sdseller c where a.prdid=b.prdid and b.sellerid=c.sellerid and a.userid='"+UserID+"' ";
    	SSRS sr = new ExeSQL().execSQL(SQL);
    	JSONArray ja = new JSONArray();
    	for(int i=0;i<sr.getMaxRow();i++)
    	{
    		JSONObject ji = new JSONObject();
    		ji.accumulate("ShotOrgName", sr.GetText(i+1, 1));
    		ji.accumulate("SellerID", sr.GetText(i+1, 2));
    		ji.accumulate("PrdName", sr.GetText(i+1, 3));
    		ji.accumulate("PrdID", sr.GetText(i+1, 4));
    		ji.accumulate("PrdPrice", sr.GetText(i+1, 5));
    		ji.accumulate("Discount", sr.GetText(i+1, 6));
    		ji.accumulate("EffEndDate", sr.GetText(i+1, 7));
    		ji.accumulate("Remark", sr.GetText(i+1, 8));
    		ji.accumulate("AuthFlag", sr.GetText(i+1, 9));
    		ja.add(ji);
    	}
    	succ=true;
    	msg="查询成功";
    	rd.accumulate("favoritelist", ja);
    }

    public void vote_getvote()
    {
    	String VoteNo = data.get("VoteNo").toString();
    	SDVoteMainSchema sdvm = new SDVoteMainDB().executeQuery("select * from sdvotemain where voteno='"+VoteNo+"'").get(1);
    	SDVoteItemSet sdvi = new SDVoteItemDB().executeQuery("select * from sdvoteitem where voteno='"+VoteNo+"' order by VoteSeq asc");
    	SDVoteTopicSet sdvt = new SDVoteTopicDB().executeQuery("select * from sdvotetopic where voteno='"+VoteNo+"'");
    	succ=true;
    	msg="查询成功";
    	rd.accumulate("sdvm", PubFun.schema2JsonObj(sdvm));
    	rd.accumulate("sdvi", PubFun.set2JsonArr(sdvi));
    	rd.accumulate("sdvt", PubFun.set2JsonArr(sdvt));
    }
    public void vote_sendvote()
    {
    	String AnonyFlag = data.get("AnonyFlag").toString();
    	String VoteNo = data.get("VoteNo").toString();
    	String UserID="";
		if(!"Y".equals(AnonyFlag))
		{
			UserID = data.get("UserID").toString();
	    	if(!PubFun.checkZero("select count(*) from sdvotetrace where voteno='"+VoteNo+"' and VotePerson='"+UserID+"'"))
	    	{
	    		succ=false;
	    		msg="请勿重复投票";
	    		return;
	    	}
		}
    	JSONArray ja = data.getJSONArray("itemlist");
    	for(int i=0;i<ja.size();i++)
    	{
    		JSONObject ji = ja.getJSONObject(i);
    		String ItemNo=ji.get("ItemNo").toString();
        	String TopicNo = ji.get("TopicNo").toString();

    		m.put("update sdvoteitem set votecount=VoteCount*1+1 where itemno='"+ItemNo+"'", "UPDATE");
    		if(!"Y".equals(AnonyFlag))
    		{
    			SDVoteTraceSchema sdvt = new SDVoteTraceSchema();
    			sdvt.setItemNo(ItemNo);
    			sdvt.setVotePerson(UserID);
    			sdvt.setVoteDate(cd);
    			sdvt.setVoteTime(ct);
    			sdvt.setVoteNo(VoteNo);
    			sdvt.setTopicNo(TopicNo);
    			sdvt.setSerialNo(UUID.randomUUID().toString());
    			m.put(sdvt, "INSERT");
    		}
    	}
    	succ=ps.submitData(m);
    	msg="投票成功";
    }
    public void user_getactlist()
    {
    	String UserID = data.get("UserID").toString();
    	String SQL = "select a.actid,c.title,b.shotorgname,c.phone,c.startdate,c.enddate,c.actfee from sdassactatn a,sdass b,sdassactivity c where a.actid=c.actid and c.assid=b.assid and a.userid='"+UserID+"' order by a.serialno desc";
    	SSRS sr = new ExeSQL().execSQL(SQL);
    	JSONArray ja = new JSONArray();
    	for(int i=0;i<sr.getMaxRow();i++)
    	{
    		JSONObject ji = new JSONObject();
    		ji.accumulate("ActID", sr.GetText(i+1, 1));
    		ji.accumulate("Title", sr.GetText(i+1, 2));
    		ji.accumulate("ShotOrgName", sr.GetText(i+1, 3));
    		ji.accumulate("Phone", sr.GetText(i+1, 4));
    		ji.accumulate("StartDate", sr.GetText(i+1, 5));
    		ji.accumulate("EndDate", sr.GetText(i+1, 6));
    		ji.accumulate("ActFee", sr.GetText(i+1, 7));
    		ja.add(ji);
    	}
    	succ=true;
    	msg="查询成功";
    	rd.accumulate("actlist", ja);
    }
    public void user_getorderlist()
    {
    	String UserID = data.get("UserID").toString();
//    	String SQL = "select * from sdorder where userid='"+UserID+"' ";
    	String SQL = "select a.orderid,c.shotorgname,b.prdname,a.makedate,a.maketime,c.LinkPerson,c.phone,b.prdtype,a.OCount,a.Requirement,a.R2 from sdorder a,sdproduct b,sdseller c where a.prdid=b.prdid and b.sellerid=c.sellerid and a.userid='"+UserID+"' order by a.orderid desc";
    	SSRS sr = new ExeSQL().execSQL(SQL);
    	JSONArray ja = new JSONArray();
    	for(int i=0;i<sr.getMaxRow();i++)
    	{
    		JSONObject ji = new JSONObject();
    		ji.accumulate("OrderID", sr.GetText(i+1, 1));
    		ji.accumulate("ShotOrgName", sr.GetText(i+1, 2));
    		ji.accumulate("PrdName", sr.GetText(i+1, 3));
    		ji.accumulate("MakeDate", sr.GetText(i+1, 4));
    		ji.accumulate("MakeTime", sr.GetText(i+1, 5));
    		ji.accumulate("LinkPerson", sr.GetText(i+1, 6));
    		ji.accumulate("Phone", sr.GetText(i+1, 7));
    		ji.accumulate("PrdType", sr.GetText(i+1, 8));
    		ji.accumulate("OCount", sr.GetText(i+1, 9));
    		ji.accumulate("Requirement", sr.GetText(i+1, 10));
    		ji.accumulate("R2", sr.GetText(i+1, 11));
    		ji.accumulate("Week", PubFun.getWeek(sr.GetText(i+1, 4)));
    		if("已提交".equals(sr.GetText(i+1, 11)))
    		{
        		ji.accumulate("Display2", "");
    		}
    		else
    		{
        		ji.accumulate("Display2", "none");
    		}
    		if("已完成".equals(sr.GetText(i+1, 11)))
    		{
    			ji.accumulate("Display1", "");
    		}
    		else
    		{
    			ji.accumulate("Display1", "none");
    		}
    		ja.add(ji);
    	}
    	succ=true;
    	msg="查询成功";
    	rd.accumulate("orderlist", ja);
    }
  public void order_prdlist()
  {
    String pids = this.data.get("PrdID").toString();
    String[] pa = pids.split("\\.");
    String SQL = "select * from sdproduct where prdid in (";
    for (int i = 0; i < pa.length; i++)
    {
      String[] paa = pa[i].split("\\,");
      String pid = paa[0];
      SQL = SQL + "'" + pid + "'";
      if (i >= pa.length - 1)
        continue;
      SQL = SQL + ",";
    }

    SQL = SQL + ")";
    SDProductSet sdps = new SDProductDB().executeQuery(SQL);
    for (int i = 0; i < sdps.size(); i++)
    {
      String pido = sdps.get(i + 1).getPrdID();
      for (int j = 0; j < pa.length; j++)
      {
        String[] paa = pa[j].split(",");
        String pid = paa[0];
        String count = paa[1];
        if (pido.equals(pid))
          sdps.get(i + 1).setR9(count);
      }
    }
    this.succ = true;
    this.rd.accumulate("sdps", PubFun.set2JsonArr(sdps));
  }
    public void order_getorderinfo()
    {
    	String OrderID = data.get("OrderID").toString();
    	SDOrderSet sdos = new SDOrderDB().executeQuery("select * from sdorder where orderid='"+OrderID+"'");
    	if(sdos.size()==0)
    	{
    		succ=false;
    		msg="查询失败";
    	}
    	else
    	{
    		succ=true;
    		msg="查询成功";
    		rd.accumulate("orderinfo", PubFun.schema2JsonObj(sdos.get(1)));
    		SDSellerSchema sdss = new SDSellerDB().executeQuery("select a.* from sdseller a,sdproduct b,sdorder c where a.sellerid=b.sellerid and b.prdid=c.prdid and c.orderid='"+OrderID+"'").get(1);
    		rd.accumulate("sellerinfo", PubFun.schema2JsonObj(sdss));
    	}
    }

    public void product_assess()
    {
    	SDProductAssessSchema sdpa = new SDProductAssessSchema();
    	
    	String[] pa = {"OrderID","UserID","Level","Content","Phone"};
    	for(int i=0;i<pa.length;i++)
    	{
    		sdpa.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sdpa.setAssEssDate(cd);
    	sdpa.setAssEssTime(ct);
    	sdpa.setSerialNo(PubFun.CreateMaxNo("SA", "SA", 20));
    	m.put(sdpa, "INSERT");
    	m.put("update sdorder set r2='已完成(已评价)' where orderid='"+data.get("OrderID")+"'", "UPDATE");
    	//先提交一次
    	ps.submitData(m);
    	m=new MMap();
    	String SellerID = new ExeSQL().getOneValue("select b.sellerid from sdproduct b,sdorder c where b.prdid=c.prdid and c.orderid='"+data.get("OrderID")+"'");
    	String lSQL = "select round(avg(a.Level),0) from SDProductAssess a,sdorder b,sdproduct c where a.orderid=b.orderid and b.prdid=c.prdid and sellerid='"+SellerID+"'";
    	String ls = new ExeSQL().getOneValue(lSQL);
    	ls=ls.substring(0, 1);
    	m.put("update sdseller set r6='"+ls+"' where sellerid='"+SellerID+"'", "UPDATE");
    	m.put("update sdseller set r7=(select count(*) from SDProductAssess a,sdorder b,sdproduct c where a.orderid=b.orderid and b.prdid=c.prdid and c.sellerid=sdseller.sellerid) where sellerid='"+SellerID+"'", "UPDATE");
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="评价成功";
    	}
    	else
    	{
    		msg="评价失败";
    	}
    	
    }
    public void seller_follow()
    {
    	String SellerID = data.get("SellerID").toString();
    	String UserID = data.get("UserID").toString();
    	SDSellerFollowSet sdsfs = new SDSellerFollowDB().executeQuery("select * from sdsellerfollow where sellerid='"+SellerID+"' and userid='"+UserID+"'");
    	if(sdsfs.size()>0)
    	{
    		m.put(sdsfs, "DELETE");
    		succ=ps.submitData(m);
    		msg="取消关注成功";
    	}
    	else
    	{
    		SDSellerFollowSchema sdsf = new SDSellerFollowSchema();
    		sdsf.setUserID(UserID);
    		sdsf.setSellerID(SellerID);
    		sdsf.setFollowDate(cd);
    		sdsf.setFollowTime(ct);
    		sdsf.setSerialNo(PubFun.CreateMaxNo("SF", "SF", 20));
    		m.put(sdsf, "INSERT");
    		succ=ps.submitData(m);
    		msg="关注成功";
    	}
    }
   public void order_sendorder() {
    String pids = this.data.get("PrdID").toString();
    String[] pa1 = pids.split("\\.");
    SDOrderSet sdos = new SDOrderSet();
    String OrderMainID = PubFun.CreateMaxNo("OM", "OM", 9);
    String pidOne = "";
    for (int x = 0; x < pa1.length; )
    {
      String[] paa = pa1[x].split("\\,");
      String pid = paa[0];
      pidOne = pid;
      String count = paa[1];
      SDOrderSchema sdo = new SDOrderSchema();

      String[] pa = { "UserID", "ArriveDate", "Owner", "ComeFlag", "R1", "Address", "Requirement", "Phone", "Tel" };
      for (int i = 0; i < pa.length; i++)
      {
        sdo.setV(pa[i], this.data.get(pa[i]).toString());
      }
      try{
    	DateFormat df =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	
        if (df.parse(this.cd + " " + this.ct).after(df.parse(sdo.getArriveDate() + ":00"))){
          this.succ = false;
          this.msg = "预订到达时间不能早于当前时间";
          return;
        }
      } catch (ParseException e) {
        e.printStackTrace();

  
      }
      sdo.setPrdID(pid);
        sdo.setOCount(count);
        sdo.setOrderDate(this.cd);
        sdo.setMakeDate(this.cd);
        sdo.setMakeTime(this.ct);
        sdo.setModifyDate(this.cd);
        sdo.setModifyTime(this.ct);
        sdo.setR2("已提交");
        sdo.setR9(OrderMainID);
        String OrderID = PubFun.CreateMaxNo("O", "O", 10);
        sdo.setOrderID(OrderID);
        sdos.add(sdo);

        x++;
    }

    this.m.put(sdos, "INSERT");
    SDProductSchema sdp = new SDProductDB().executeQuery("select * from sdproduct where prdid='" + pidOne + "'").get(1);

    this.m.put("update sdseller set r3=(select count(*) from sdorder b,sdproduct c where b.prdid=c.prdid and c.sellerid=sdseller.sellerid) where sellerid='" + sdp.getSellerID() + "'", "UPDATE");

    this.succ = this.ps.submitData(this.m);
    if (this.succ)
    {
      this.msg = "下单成功";
      this.rd.accumulate("OrderID", OrderMainID);

      SDSellerSchema se = new SDSellerDB().executeQuery("select a.* from sdseller a,sdproduct b where a.sellerid=b.sellerid and b.prdid='" + pidOne + "'").get(1);
      String content = "有用户下新订单(" + OrderMainID + ")，联系电话为(" + this.data.get("Phone").toString() + ")，请登录查看。【清华园社区】";
      PubFun.sendMessage(se.getPhone(), content);

      SDSendCodeTraceSchema sdsct = new SDSendCodeTraceSchema();
      sdsct.setSerialNo(UUID.randomUUID().toString());
      sdsct.setPhone(se.getPhone());
      sdsct.setCode("");
      sdsct.setSendDate(this.cd);
      sdsct.setSendTime(this.ct);
      sdsct.setR1("WEB");
      sdsct.setR2(content);
      this.m = new MMap();
      this.m.put(sdsct, "INSERT");
      this.ps.submitData(this.m);
    }
    else
    {
      this.msg = "下单失败";
    }
  }
    public void seller_confirmorder()
    {
    	String OrderID = data.get("OrderID").toString();
    	m.put("update sdorder set r2='已完成' where orderid='"+OrderID+"'", "UPDATE");
    	//变更商家订单数
    	String SQL = "update sdseller set r4=(select count(*) from sdorder a,sdproduct b where a.prdid=b.prdid and a.r2 not like '%取消%' and b.sellerid=sdseller.sellerid)";
    	m.put(SQL, "UPDATE");
    	succ=ps.submitData(m);
    	msg="确认成功";
    	//发送短信
    	SDOrderSchema o = new SDOrderDB().executeQuery("select * from sdorder where orderid = '"+OrderID+"'").get(1);
    	if(o.getPhone()!=null&&!"".equals(o.getPhone()))
    	{
    		String content = "您的订单["+OrderID+"]已经完成，请回复1-5对商家进行评分。如想留下评价信息，请加在评分后，用逗号分隔。【清华园社区】";
    		PubFun.sendMessage(o.getPhone(), content,OrderID.substring(OrderID.length()-5, OrderID.length()));

        	//存储发送轨迹
        	SDSendCodeTraceSchema sdsct = new SDSendCodeTraceSchema();
        	sdsct.setSerialNo(UUID.randomUUID().toString());
        	sdsct.setPhone(o.getPhone());
        	sdsct.setCode("");
        	sdsct.setSendDate(cd);
        	sdsct.setSendTime(ct);
        	sdsct.setR1("WEB");
        	sdsct.setR2(content);
        	m.put(sdsct, "INSERT");
        	ps.submitData(m);
    	}
    	
    }
    public void act_regact()
    {
    	String ActID = data.get("ActID").toString();
    	String UserID = data.get("UserID").toString();
    	SDAssActAtnSet sdaaas = new SDAssActAtnDB().executeQuery("select * from SDAssActAtn where actid='"+ActID+"' and userid = '"+UserID+"'");
    	//String AssID = new ExeSQL().getOneValue("select assid from sdassactivity where actid='"+ActID+"'");
    	if(sdaaas.size()>0)
    	{
    		m.put(sdaaas, "DELETE");
    		m.put("update sdassactivity set r2=(select count(*) from SDAssActAtn a where a.actid='"+ActID+"') where actid='"+ActID+"'", "UPDATE");
    		succ=ps.submitData(m);
    		msg="取消参加成功";
    	}
    	else
    	{
    		SDAssActAtnSchema sdaaa = new SDAssActAtnSchema();
    		sdaaa.setActID(ActID);
    		sdaaa.setUserID(UserID);
    		sdaaa.setAtnDate(cd);
    		sdaaa.setAtnTime(ct);
    		sdaaa.setSerialNo(PubFun.CreateMaxNo("SR", "SR", 20));
    		m.put(sdaaa, "INSERT");
    		m.put("update sdassactivity set r2=(select count(*) from SDAssActAtn a where a.actid='"+ActID+"') where actid='"+ActID+"'", "UPDATE");

    		succ=ps.submitData(m);
    		msg="参加成功";
    	}    	
    }
    public void seller_cancelorder()
    {
    	String OrderID = data.get("OrderID").toString();
    	m.put("update sdorder set r2='已取消(商家)' where orderid='"+OrderID+"'", "UPDATE");
    	SDProductSchema sdp = new SDProductDB().executeQuery("select a.* from sdproduct a,sdorder b where a.prdid=b.prdid and  b.orderid='"+data.get("OrderID").toString()+"'").get(1);

    	m.put("update sdseller set r3=(select count(*) from sdorder b,sdproduct c where b.prdid=c.prdid and c.sellerid=sdseller.sellerid) where sellerid='"+sdp.getSellerID()+"'", "UPDATE");

    	
    	succ=ps.submitData(m);
    	msg="取消成功";
    }
    public void user_cancelorder()
    {
    	String OrderID = data.get("OrderID").toString();
    	m.put("update sdorder set r2='已取消(个人)' where orderid='"+OrderID+"'", "UPDATE");
    	SDProductSchema sdp = new SDProductDB().executeQuery("select * from sdproduct a,sdorder b where a.prdid=b.prdid and b.orderid='"+OrderID+"'").get(1);

    	m.put("update sdseller set r3=(select count(*) from sdorder b,sdproduct c where b.prdid=c.prdid and c.sellerid=sdseller.sellerid) where sellerid='"+sdp.getSellerID()+"'", "UPDATE");

    	
    	succ=ps.submitData(m);
    	msg="取消成功";
    }
    public void content_getlist()
    {
    	SDContentSet sdcs = new SDContentDB().executeQuery("select * from sdcontent where ChannelCode='"+data.get("id").toString()+"' and r1='Y' order by serialno desc");
    	rd.accumulate("contentlist", PubFun.set2JsonArr(sdcs));
    	succ=true;
    	msg="查询成功";
    }
    public void content_getcrawlerlist()
    {
    	SDCrawlerContentSet sdcs = new SDCrawlerContentDB().executeQuery("select * from SDCrawlerContent where ChannelCode='"+data.get("id").toString()+"' order by serialno asc");
    	rd.accumulate("contentlist", PubFun.set2JsonArr(sdcs));
    	succ=true;
    	msg="查询成功";
    }
    public void act_getlist()
    {
    	SDAssActivitySet sdaas = new SDAssActivityDB().executeQuery("select * from sdassactivity where AppStatus='Y' order by actid desc");
    	rd.accumulate("act", PubFun.set2JsonArr(sdaas));
    	succ=true;
    	msg="查询成功";
    }
    public void act_getact()
    {
    	String ActID = data.get("ActID").toString();
    	String UserID = data.get("UserID").toString();
    	SDAssActivitySet sdaas = new SDAssActivityDB().executeQuery("select * from sdassactivity where ActID='"+ActID+"'");
    	JSONObject jo = PubFun.schema2JsonObj(sdaas.get(1));
    	jo.accumulate("ShotOrgName", new ExeSQL().getOneValue("select shotorgname from sdass a,sdassactivity b where a.assid=b.assid and b.actid='"+ActID+"'"));
    	jo.accumulate("AtnFlag", new ExeSQL().getOneValue("select count(*) from sdassactatn where actid='"+ActID+"' and userid='"+UserID+"'"));
    	jo.accumulate("AtnPeople", new ExeSQL().getOneValue("select count(*) from sdassactatn where actid='"+ActID+"'"));
    	
    	String ActStatus="";
    	String EndDate = sdaas.get(1).getEndDate();
    	String RegEndDate = sdaas.get(1).getRegEndDate();
    	String nowDate = PubFun.getCurrentDate();
    	try {
//    		if( new SimpleDateFormat("yyyy-MM-ddO").parse(nowDate))
			if(new SimpleDateFormat("yyyy-MM-dd").parse(nowDate).after(new SimpleDateFormat("yyyy-MM-dd").parse(RegEndDate)))
			{
				ActStatus="报名结束";
			}
			else
			{
				ActStatus="报名中";
			}
			if(new SimpleDateFormat("yyyy-MM-dd").parse(nowDate).after(new SimpleDateFormat("yyyy-MM-dd").parse(EndDate)))
			{
				ActStatus="活动结束";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	jo.accumulate("ActStatus",ActStatus );
    	rd.accumulate("act", jo);
    	succ=true;
    	msg="查询成功";
    }
    public void vote_getlist()
    {
    	SDVoteMainSet sdvm = new SDVoteMainDB().executeQuery("select * from sdvotemain where DisplayFlag='Y' order by VoteNo desc");
    	rd.accumulate("vote", PubFun.set2JsonArr(sdvm));
    	succ=true;
    	msg="查询成功";
    }
    public void seller_loadclassname()
    {
    	SDSellerClassSet sdscs = new SDSellerClassDB().executeQuery("select * from sdsellerclass");
    	rd.accumulate("classname", PubFun.set2JsonArr(sdscs));
    	succ=true;
    	msg="查询成功";
    }
    public void content_getsellerlist()
    {
    	SDSellerSet sdss = new SDSellerDB().executeQuery("select * from sdseller where ServiceType='"+data.get("id").toString()+"' and r9<>'Y' order by r4 desc");
    	rd.accumulate("sellerlist", PubFun.set2JsonArr(sdss));
    	succ=true;
    	msg="查询成功";
    }
    public void content_gethotsellerlist()
    { 
    	//2最热服务
    	String SQL = "select * from (" +
		"select a.sellerid,a.shotorgname,a.AuthFlag,a.XY,a.R3,count(*),a.r6,a.r5,a.r7 from sdseller a,sdorder b,sdproduct c where a.sellerid=c.sellerid and b.prdid=c.prdid and b.r2 not like'%取消%'" +
		" group by a.sellerid,a.shotorgname,a.AuthFlag,a.xy,a.r3,a.r6,a.r7 order by count(*) desc) as tmp limit 16";

		SSRS sr = new ExeSQL().execSQL(SQL);
		JSONArray ja2 = new JSONArray();
		for(int i=0;i<sr.getMaxRow();i++)
		{
			JSONObject ji = new JSONObject();
			ji.accumulate("SellerID", sr.GetText(i+1, 1));
			ji.accumulate("ShotOrgName", sr.GetText(i+1, 2));
			ji.accumulate("AuthFlag", sr.GetText(i+1, 3));
			ji.accumulate("XY", sr.GetText(i+1, 4));
			ji.accumulate("R3", sr.GetText(i+1, 5));
			ji.accumulate("R4", sr.GetText(i+1, 6));
			ji.accumulate("R6", sr.GetText(i+1, 7));
			ji.accumulate("R5", sr.GetText(i+1, 8));
			ji.accumulate("R7", sr.GetText(i+1, 9));
			ja2.add(ji);
		}    	
    	rd.accumulate("sellerlist", ja2);
    	succ=true;
    	msg="查询成功";
    }
    public void content_getasslist()
    {
    	SDAssSet sdas = new SDAssDB().executeQuery("select * from sdass order by assid desc");
    	rd.accumulate("asslist", PubFun.set2JsonArr(sdas));
    	succ=true;
    	msg="查询成功";
    }
    public void user_getfollowlist()
    {
    	String UserID = data.get("UserID").toString();
    	String SQL = "select b.* from sdsellerfollow a,sdseller b where a.sellerid=b.sellerid and a.userid='"+UserID+"' ";
    	SDSellerSet sdss = new SDSellerDB().executeQuery(SQL);
    	succ=true;
    	msg="查询成功";
    	rd.accumulate("followlist", PubFun.set2JsonArr(sdss));
    }
  public void seller_getprddetail() {
    String PrdID = this.data.get("PrdID").toString();
    SDProductSchema sdps = new SDProductDB().executeQuery("select * from sdproduct where prdid='" + PrdID + "'").get(1);
    if (sdps.getR3().equals(""))
    {
      sdps.setR3("1");
    }
    else
    {
      sdps.setR3(String.valueOf(Integer.parseInt(sdps.getR3()) + 1));
    }
    this.m.put(sdps, "UPDATE");
    this.ps.submitData(this.m);
    SDProductAssessSet sdpaes = new SDProductAssessDB().executeQuery("select a.* from SDProductAssess a,sdorder b where a.orderid=b.orderid and b.prdid='" + PrdID + "' order by a.serialno desc");
    SDSellerSchema sds = new SDSellerDB().executeQuery("select * from sdseller where sellerid='" + sdps.getSellerID() + "'").get(1);
    this.rd.accumulate("sdp", PubFun.schema2JsonObj(sdps));
    this.rd.accumulate("sds", PubFun.schema2JsonObj(sds));
    this.rd.accumulate("sdpaes", PubFun.set2JsonArr(sdpaes));
    this.rd.accumulate("OrderCount", new ExeSQL().getOneValue("select count(*) from sdorder a where a.prdid='" + PrdID + "'"));
    this.succ = true;
  }
    public void content_getseller()
    {
    	seller_setcounter();
    	SDSellerSet sdss = new SDSellerDB().executeQuery("select * from sdseller where sellerid='"+data.get("SellerID").toString()+"' and r9<>'Y'");
    	if(sdss.size()==0)
    	{
    		msg="商家不存在";
    		succ=false;
    		return;
    	}
    	rd.accumulate("seller", PubFun.schema2JsonObj(sdss.get(1)));
    	SDProductSet sdps = new SDProductDB().executeQuery("select * from sdproduct where sellerid='"+data.get("SellerID").toString()+"' and R1='Y' and R2<>'Y'");
    	SDProductFavoriteSet sdpfs = new SDProductFavoriteDB().executeQuery("select a.* from sdproductfavorite a,sdproduct b where a.prdid=b.prdid and b.sellerid='"+data.get("SellerID").toString()+"' and userid='"+data.get("UserID").toString()+"' ");
    	JSONArray ja =  PubFun.set2JsonArr(sdps);
    	for(int i=0;i<ja.size();i++)
    	{
    		JSONObject ji =ja.getJSONObject(i);
    		boolean exisflag = false;
    		for(int j=0;j<sdpfs.size();j++)
    		{
    			SDProductFavoriteSchema sdpf = sdpfs.get(j+1);
        		if(ji.get("PrdID").equals(sdpf.getPrdID()))
        		{
        			exisflag=true;
        			break;
        		}
    		}
    		if(exisflag)
    		{
    			ji.accumulate("favbtntext", "取消收藏");
    		}
    		else
    		{
    			ji.accumulate("favbtntext", "收藏");
    		}
    	}
    	rd.accumulate("prdlist",ja);
//    	SDProductAssessSet sdpas = new SDProductAssessDB().executeQuery("select a.* from sdproductassess a,sdorder b,sdproduct c where a.orderid=b.orderid and b.prdid=c.prdid and c.sellerid='"+data.get("SellerID").toString()+"' and a.userid='"+data.get("UserID").toString()+"'");
//    	rd.accumulate("asslist", PubFun.set2JsonArr(sdpas));
    	
    	SDSellerFollowSet sdsfs = new SDSellerFollowDB().executeQuery("select * from SDSellerFollow where sellerid='"+data.get("SellerID").toString()+"' and userid='"+data.get("UserID").toString()+"'");
    	rd.accumulate("followlist", PubFun.set2JsonArr(sdsfs));
    	String SellerID = data.get("SellerID").toString();
    	String c2 = new ExeSQL().getOneValue("select count(*) from 	sdorder a,sdproduct b where a.prdid=b.prdid and b.sellerid='"+SellerID+"'");
    	String c6 = new ExeSQL().getOneValue("select count(*) from 	SDProductAssess a,sdproduct b,sdorder c where a.orderid=c.orderid and c.prdid=b.prdid and sellerid='"+SellerID+"'");
    	rd.accumulate("c2", c2);
    	rd.accumulate("c6", c6);
    	
    	
    	succ=true;
    	msg="查询成功";
    }
    public void product_favorite()
    {
    	String PrdID = data.get("PrdID").toString();
    	String UserID = data.get("UserID").toString();
    	SDProductFavoriteSet sdpfs = new SDProductFavoriteDB().executeQuery("select * from sdproductfavorite where userid='"+UserID+"' and prdid='"+PrdID+"'");
    	if(sdpfs.size()>0)
    	{
    		m.put(sdpfs, "DELETE");
    		succ=ps.submitData(m);
    		msg="取消收藏成功";
    	}
    	else
    	{
    		SDProductFavoriteSchema sdpf = new SDProductFavoriteSchema();
    		sdpf.setUserID(UserID);
    		sdpf.setPrdID(PrdID);
    		sdpf.setFavDate(cd);
    		sdpf.setFavTime(ct);
    		sdpf.setSerialNo(PubFun.CreateMaxNo("SF", "SF", 20));
    		m.put(sdpf, "INSERT");
    		succ=ps.submitData(m);
    		msg="收藏成功";
    	}

    }
    public void content_getass()
    {
    	SDAssSet sdas = new SDAssDB().executeQuery("select * from sdass where assid='"+data.get("AssID").toString()+"'");
    	rd.accumulate("ass", PubFun.schema2JsonObj(sdas.get(1)));
    	succ=true;
    	msg="查询成功";
    }
    public void ass_queryact()
    {
    	SDAssActivitySchema sdaa = new SDAssActivityDB().executeQuery("select * from SDAssActivity where actid='"+data.get("ActID").toString()+"'").get(1);
    	rd.accumulate("sdaa", PubFun.schema2JsonObj(sdaa));
    	succ=true;
    	msg="查询成功";
    }
    public void ass_queryactlist()
    {
    	String assID = data.get("AssID").toString();
    	SDAssActivitySet sdaas = new SDAssActivityDB().executeQuery("select * from sdassactivity where assid='"+assID+"' order by actid desc");
    	rd.accumulate("sdaas", PubFun.set2JsonArr(sdaas));
    	succ=true;
    	msg="查询成功";
    }
    public void ass_queryactatnlist()
    {
    	String ActID = data.get("ActID").toString();
    	String SQL = "select b.title,c.nickname,a.AtnDate,a.AtnTime,c.phone from sdassactatn a,sdassactivity b,sduser c where a.actid=b.actid and a.userid=c.userid and a.actid='"+ActID+"'";
    	SSRS sr = new ExeSQL().execSQL(SQL);
    	JSONArray ja = new JSONArray();
    	for(int i=0;i<sr.getMaxRow();i++)
    	{
    		JSONObject ji = new JSONObject();
    		ji.accumulate("Title", sr.GetText(i+1, 1));
    		ji.accumulate("NickName", sr.GetText(i+1, 2));
    		ji.accumulate("AtnDate", sr.GetText(i+1, 3));
    		ji.accumulate("AtnTime", sr.GetText(i+1, 4));
    		ji.accumulate("Phone", sr.GetText(i+1, 5));
    		ja.add(ji);
    	}
    	rd.accumulate("sdaas", ja);
    	succ=true;
    	msg="查询成功";
    }
    public void ass_addact()
    {
    	SDAssActivitySchema sdaa = new SDAssActivitySchema();

    	if(!PubFun.isNull(data.get("ActID").toString()))
    	{
    		sdaa = new SDAssActivityDB().executeQuery("select * from SDAssActivity where actid='"+data.get("ActID").toString()+"'").get(1);
    	}
    	String[] pa = {"AssID","Phone","Title","ActFee","ActRule","ActContent","ActGift","AcPeople",
    			"StartDate","StartTime","EndDate","EndTime","RegEndDate","RegEndTime","R3"};
    	for(int i=0;i<pa.length;i++)
    	{
    		System.out.println(pa[i]);
    		sdaa.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sdaa.setAppStatus("N");
    	System.out.println(data.get("ActID").toString());
    	if(PubFun.isNull(data.get("ActID").toString()))
    	{
        	String SerialNo = PubFun.CreateMaxNo("AAC", "AAC", 12);
        	sdaa.setActID(SerialNo);
        	sdaa.setR2("0");
        	sdaa.setR5(cd);
        	m.put(sdaa, "INSERT");    		
    	}
    	else
    	{
        	sdaa.setActID(data.get("ActID").toString());
        	m.put(sdaa, "UPDATE");    		
    	}
    	//校验有效期和当前时间
		try {
			if(new SimpleDateFormat("yyyy-MM-dd").parse(cd).after(new SimpleDateFormat("yyyy-MM-dd").parse(data.get("RegEndDate").toString())))
			{
				succ=false;
				msg="报名截止日早于当前时间不能提交";
				return;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="提交成功";
    	}
    }
    public void com_sug()
    {
    	if(!PubFun.checkZero("select count(*) from sdsuggest where phone='"+data.get("Phone").toString()+"' and title='"+data.get("Title").toString()+"'"))
    	{
    		succ=false;
    		msg="请勿提交重复内容";
    		return;
    	}
    	SDSuggestSchema sdsu = new SDSuggestSchema();
    	
    	String[] pa = {"UserType","NickName","Title","Phone","Com","Content","R3"};
    	for(int i=0;i<pa.length;i++)
    	{
    		sdsu.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sdsu.setSuggestDate(cd);
    	sdsu.setSuggestTime(ct);
    	String SerialNo = PubFun.CreateMaxNo("SUG", "SUG", 12);
    	sdsu.setSerialNo(SerialNo);
    	sdsu.setDealFlag("N");
    	m.put(sdsu, "INSERT");
    	
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="提交成功";
    	}
    }

    public void seller_queryprdlist()
    {
    	String sellerid = data.get("SellerID").toString();
    	SDProductSet sdps = new SDProductDB().executeQuery("select * from sdproduct where sellerid='"+sellerid+"' Order by prdid desc");
    	rd.accumulate("sdps", PubFun.set2JsonArr(sdps));
    	succ=true;
    	msg="查询成功";

    }
    public void seller_querycontentlist()
    {
    	String sellerid = data.get("SellerID").toString();
    	String SQL = "select serialno,ContentTitle,IssueDate||' '||IssueTime,R1,(select b.channelname||'>'||a.channelname from sdchannel a,sdchannel b where a.parentchannel=b.serialno and a.serialno=x.channelcode) from sdcontent x where r5='"+sellerid+"' order by x.serialno desc";
    	JSONArray ja = new JSONArray();
    	SSRS sr = new ExeSQL().execSQL(SQL);
    	for(int i=0;i<sr.getMaxRow();i++)
    	{
    		JSONObject ji = new JSONObject();
    		ji.accumulate("SerialNo", sr.GetText(i+1, 1));
    		ji.accumulate("ContentTitle", sr.GetText(i+1, 2));
    		ji.accumulate("IssueDateTime", sr.GetText(i+1, 3));
    		ji.accumulate("R1", sr.GetText(i+1, 4));
    		ji.accumulate("ChannelName", sr.GetText(i+1, 5));
    		ja.add(ji);
    	}
    	rd.accumulate("contentlist", ja);
    	succ=true;
    	msg="查询成功";
    	
    }
    public void user_querycontentlist()
    {
    	String userid = data.get("UserID").toString();
    	String SQL = "select serialno,ContentTitle,IssueDate||' '||IssueTime,R1,(select b.channelname||'>'||a.channelname from sdchannel a,sdchannel b where a.parentchannel=b.serialno and a.serialno=x.channelcode) from sdcontent x where r5='"+userid+"' order by x.serialno desc";
    	JSONArray ja = new JSONArray();
    	SSRS sr = new ExeSQL().execSQL(SQL);
    	for(int i=0;i<sr.getMaxRow();i++)
    	{
    		JSONObject ji = new JSONObject();
    		ji.accumulate("SerialNo", sr.GetText(i+1, 1));
    		ji.accumulate("ContentTitle", sr.GetText(i+1, 2));
    		ji.accumulate("IssueDateTime", sr.GetText(i+1, 3));
    		ji.accumulate("R1", sr.GetText(i+1, 4));
    		ji.accumulate("ChannelName", sr.GetText(i+1, 5));
    		ja.add(ji);
    	}
    	rd.accumulate("contentlist", ja);
    	succ=true;
    	msg="查询成功";
    	
    }
    public void ass_querycontentlist()
    {
    	String assid = data.get("AssID").toString();
    	String SQL = "select serialno,ContentTitle,IssueDate||' '||IssueTime,R1,(select b.channelname||'>'||a.channelname from sdchannel a,sdchannel b where a.parentchannel=b.serialno and a.serialno=x.channelcode) from sdcontent x where r5='"+assid+"' order by x.serialno desc";
    	JSONArray ja = new JSONArray();
    	SSRS sr = new ExeSQL().execSQL(SQL);
    	for(int i=0;i<sr.getMaxRow();i++)
    	{
    		JSONObject ji = new JSONObject();
    		ji.accumulate("SerialNo", sr.GetText(i+1, 1));
    		ji.accumulate("ContentTitle", sr.GetText(i+1, 2));
    		ji.accumulate("IssueDateTime", sr.GetText(i+1, 3));
    		ji.accumulate("R1", sr.GetText(i+1, 4));
    		ji.accumulate("ChannelName", sr.GetText(i+1, 5));
    		ja.add(ji);
    	}
    	rd.accumulate("contentlist", ja);
    	succ=true;
    	msg="查询成功";
    	
    }
    public void seller_queryprd()
    {
    	String PrdID = data.get("PrdID").toString();
    	SDProductSet sdps = new SDProductDB().executeQuery("select * from sdproduct where prdid='"+PrdID+"'");
    	rd.accumulate("sdp", PubFun.schema2JsonObj(sdps.get(1)));
    	succ=true;
    	msg="查询成功";
    	
    }
    public void seller_querycontent()
    {
    	String serialno = data.get("ID").toString();
    	SDContentSchema sdc = new SDContentDB().executeQuery("select * from sdcontent where serialno='"+serialno+"'").get(1);
    	rd.accumulate("sdc", PubFun.schema2JsonObj(sdc));
    	succ=true;
    	msg="查询成功";    	
    }

    public void seller_delprd()
    {
    	String prdid = data.get("PrdID").toString();
    	SDProductSchema sdp = new SDProductDB().executeQuery("select * from sdproduct where prdid = '"+prdid+"'").get(1);
    	if("Y".equals(sdp.getR2()))
    	{
    		succ=false;
    		msg="该商品已经下架，请勿重复操作";
    		return;
    	}
    	m.put("update sdproduct set r2='Y' where prdid='"+prdid+"'", "UPDATE");
    	succ=ps.submitData(m);
    	msg="下架成功";
    }
    public void seller_addprd()
    {
    	String prdid = data.get("PrdID").toString();
    	if("null".equals(prdid))
    	{

        	if(!PubFun.checkZero("select count(*) from sdproduct where PrdName='"+data.get("PrdName").toString()+"' and SellerID='"+data.get("SellerID").toString()+"'"))
        	{
        		succ=false;
        		msg="请勿提交重复产品";
        		return;
        	}
    	}
    	SDProductSet sdps = new SDProductDB().executeQuery("select * from sdproduct where prdid='"+prdid+"'");
    	
    	SDProductSchema sdp = new SDProductSchema();
    	if(sdps.size()>0)
    	{
    		sdp=sdps.get(1);
    	}
    	else
    	{
        	String SerialNo = PubFun.CreateMaxNo("PID", "PID", 8);
        	sdp.setPrdID(SerialNo);
        	sdp.setMakeDate(cd);
        	sdp.setMakeTime(ct);
        	sdp.setR2("N");
    	}
    	sdp.setModifyDate(cd);
    	sdp.setModifyTime(ct);
    	String[] pa = {"SellerID","PrdName","PrdPrice","Discount","Remark","R15","R14","R4","R5"};
    	for(int i=0;i<pa.length;i++)
    	{
    		sdp.setV(pa[i], data.get(pa[i]).toString());
    	}
    	String date =  data.get("EffEndDate").toString();
    	if(PubFun.isNull(date))
    	{
    		sdp.setEffEndDate("长期");
    	}
    	else
    	{
    		sdp.setEffEndDate(date);
    	}
    	//校验有效期和当前时间
		try {
			if(!"长期".equals(date)){
			if(new SimpleDateFormat("yyyy-MM-dd").parse(cd).after(new SimpleDateFormat("yyyy-MM-dd").parse(date)))
			{
				succ=false;
				msg="有效期截止日早于当前时间不能提交";
				return;
			}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	sdp.setR1("N");
    	m.put(sdp, "DELETE&INSERT");
    	
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="提交成功";
    	}
    }

    public void seller_deletecontent()
    {
    	String serialno = data.get("SerialNo").toString();
    	m.put("delete from sdcontent where serialno='"+serialno+"'", "DELETE");
    	succ=ps.submitData(m);
    	msg="删除成功";
    }
    public void seller_addcontent()
    {
    	String serialno = data.get("SerialNo").toString();
    	if("null".equals(serialno))
    	{
    		
    		if(!PubFun.checkZero("select count(*) from sdcontent where contenttitle='"+data.get("ContentTitle").toString()+"' and ContentFrom='"+data.get("ContentFrom").toString()+"'"))
    		{
    			succ=false;
    			msg="请勿提交相同标题";
    			return;
    		}
    	}
    	SDContentSet sdcs = new SDContentDB().executeQuery("select * from sdcontent where serialno='"+serialno+"'");
    	
    	SDContentSchema sdc = new SDContentSchema();
    	if(sdcs.size()>0)
    	{
    		sdc=sdcs.get(1);
    	}
    	else
    	{
    		String SerialNo = PubFun.CreateMaxNo("CT", "CT", 10);
    		sdc.setSerialNo(SerialNo);
    		sdc.setIssueDate(cd);
    		sdc.setIssueTime(ct);
    		
    	}
    	String[] pa = {"ContentTitle","ChannelCode","ContentHtml","ContentTxt","ContentFrom"};
    	for(int i=0;i<pa.length;i++)
    	{
    		sdc.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sdc.setR1("N");
    	sdc.setR8("0");
    	if(sdc.getContentFrom().indexOf("U")!=-1)
    	{
        	SDUserSchema sdu = new SDUserDB().executeQuery("select * from sduser where userid = '"+sdc.getContentFrom()+"'").get(1);
        	sdc.setR5(sdc.getContentFrom());
        	sdc.setContentFrom(sdu.getNickName()+"(个人)");
    	}
    	else if(sdc.getContentFrom().indexOf("S")!=-1)
    	{
        	SDSellerSchema sdu = new SDSellerDB().executeQuery("select * from sdseller where sellerid = '"+sdc.getContentFrom()+"'").get(1);
        	sdc.setR5(sdc.getContentFrom());
        	sdc.setContentFrom(sdu.getShotOrgName()+"(商家)");
    	}
    	else if(sdc.getContentFrom().indexOf("A")!=-1)
    	{
        	SDAssSchema sdu = new SDAssDB().executeQuery("select * from sdass where assid = '"+sdc.getContentFrom()+"'").get(1);
        	sdc.setR5(sdc.getContentFrom());
        	sdc.setContentFrom(sdu.getShotOrgName()+"(社团)");
    	}
    	m.put(sdc, "DELETE&INSERT");
    	
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="提交成功";
    	}
    }

    public void user_logout()
    {
		session.putValue("ut", null);
		session.putValue("sdu", null);
		session.putValue("sds", null);
		session.putValue("sda", null);

		succ=true;
		msg="退出成功";
    }
    public void user_usercenter()
    {
    	String UserID = data.get("UserID").toString();
    	String c1 = new ExeSQL().getOneValue("select count(*) from 	sdorder where userid='"+UserID+"'");
    	String c2 = new ExeSQL().getOneValue("select count(*) from 	sdorder where userid='"+UserID+"' and r2 like '已完成%'");
    	String c3 = new ExeSQL().getOneValue("select count(*) from 	sdorder where userid='"+UserID+"' and r2 not like '已完成%'");
    	String c4 = new ExeSQL().getOneValue("select count(*) from 	sdorder where userid='"+UserID+"' and r2 like '%取消%'");
    	String c5 = new ExeSQL().getOneValue("select count(*) from 	SDProductAssess where userid='"+UserID+"'");
    	String c6 = new ExeSQL().getOneValue("select count(*) from 	SDAssActAtn where userid='"+UserID+"'");
    	rd.accumulate("c1", c1);
    	rd.accumulate("c2", c2);
    	rd.accumulate("c3", c3);
    	rd.accumulate("c4", c4);
    	rd.accumulate("c5", c5);
    	rd.accumulate("c6", c6);
    	succ=true;
    	msg="查询成功";
    }
    public void seller_sellercenter()
    {
    	String SellerID = data.get("SellerID").toString();
    	String c1 = new ExeSQL().getOneValue("select a.channelname||'>'||b.channelname from sdchannel a,sdchannel b,sdseller c where a.serialno=b.parentchannel and b.serialno=c.servicetype and c.sellerid='"+SellerID+"'");
    	String c2 = new ExeSQL().getOneValue("select count(*) from 	sdorder a,sdproduct b where a.prdid=b.prdid and b.sellerid='"+SellerID+"'");
    	String c3 = new ExeSQL().getOneValue("select count(*) from 	sdorder a,sdproduct b where a.prdid=b.prdid and b.sellerid='"+SellerID+"' and a.r2 like '%完成%'");
    	String c4 = new ExeSQL().getOneValue("select count(*) from 	sdorder a,sdproduct b where a.prdid=b.prdid and b.sellerid='"+SellerID+"' and a.r2 like '%提交%'");
    	String c5 = new ExeSQL().getOneValue("select count(*) from 	sdcontent where ContentFrom='"+SellerID+"'");
    	String c6 = new ExeSQL().getOneValue("select count(*) from 	SDProductAssess a,sdproduct b,sdorder c where a.orderid=c.orderid and c.prdid=b.prdid and sellerid='"+SellerID+"'");
    	rd.accumulate("c1", c1);
    	rd.accumulate("c2", c2);
    	rd.accumulate("c3", c3);
    	rd.accumulate("c4", c4);
    	rd.accumulate("c5", c5);
    	rd.accumulate("c6", c6);
    	succ=true;
    	msg="查询成功";
    }

    public void user_findpass()
    {
    	String userType = data.get("UserType").toString();
    	if("U".equals(userType))
    	{
        	SDUserSet sdus = new SDUserDB().executeQuery("select * from sduser where  username='"+data.get("UserName").toString()+"'");
        	if(sdus.size()>0)
        	{
        		String content = "您的密码是"+sdus.get(1).getPassword()+"，请妥善保管【清华园社区】";
        		PubFun.sendMessage(sdus.get(1).getPhone(), content);

            	//存储发送轨迹
            	SDSendCodeTraceSchema sdsct = new SDSendCodeTraceSchema();
            	sdsct.setSerialNo(UUID.randomUUID().toString());
            	sdsct.setPhone(sdus.get(1).getPhone());
            	sdsct.setCode("");
            	sdsct.setSendDate(cd);
            	sdsct.setSendTime(ct);
            	sdsct.setR1("WEB");
            	sdsct.setR2(content);
            	m.put(sdsct, "INSERT");
            	ps.submitData(m);
        		succ=true;
        		msg="密码已经发送到您注册时所填写的手机上，请查收";
        	}
        	else
        	{
        		succ=false;
        		msg="用户名未找到，请确认";
        	}
    	}

    	else if("S".equals(userType))
    	{
    		SDSellerSet sdss = new SDSellerDB().executeQuery("select * from sdseller where SellerName='"+data.get("UserName").toString()+"' ");
    		if(sdss.size()>0)
        	{
    			String content = "您的密码是"+sdss.get(1).getPassword()+"，请妥善保管【清华园社区】";
    			PubFun.sendMessage(sdss.get(1).getPhone(), content);
    			

            	//存储发送轨迹
            	SDSendCodeTraceSchema sdsct = new SDSendCodeTraceSchema();
            	sdsct.setSerialNo(UUID.randomUUID().toString());
            	sdsct.setPhone(sdss.get(1).getPhone());
            	sdsct.setCode("");
            	sdsct.setSendDate(cd);
            	sdsct.setSendTime(ct);
            	sdsct.setR1("WEB");
            	sdsct.setR2(content);
            	m.put(sdsct, "INSERT");
            	ps.submitData(m);
    			
        		succ=true;
        		msg="密码已经发送到您注册时所填写的手机上，请查收";
        	}
        	else
        	{
        		succ=false;
        		msg="用户名未找到，请确认";
        	}
    	}
    	else if("A".equals(userType))
    	{
    		SDAssSet sdas = new SDAssDB().executeQuery("select * from sdass where AssName='"+data.get("UserName").toString()+"'");
    		if(sdas.size()>0)
        	{
    			String content = "您的密码是"+sdas.get(1).getPassword()+"，请妥善保管【清华园社区】";
    			PubFun.sendMessage(sdas.get(1).getPhone(), content);

            	//存储发送轨迹
            	SDSendCodeTraceSchema sdsct = new SDSendCodeTraceSchema();
            	sdsct.setSerialNo(UUID.randomUUID().toString());
            	sdsct.setPhone(sdas.get(1).getPhone());
            	sdsct.setCode("");
            	sdsct.setSendDate(cd);
            	sdsct.setSendTime(ct);
            	sdsct.setR1("WEB");
            	sdsct.setR2(content);
            	m.put(sdsct, "INSERT");
            	ps.submitData(m);
        		succ=true;
        		msg="密码已经发送到您注册时所填写的手机上，请查收";
        	}
        	else
        	{
        		succ=false;
        		msg="用户名未找到，请确认";
        	}
    	}
    }
    public void user_login()
    {
    	String userType = data.get("UserType").toString();
    	if("U".equals(userType))
    	{
        	SDUserSet sdus = new SDUserDB().executeQuery("select * from sduser where password='"+data.get("Password").toString()+"' and (phone='"+data.get("UserName").toString()+"' or username='"+data.get("UserName").toString()+"')");
        	if(sdus.size()>0)
        	{
        		succ=true;
        		msg="登陆成功";
        		SDUserSchema sdu = sdus.get(1);
        		//更新上次登录日期
        		sdu.setLastLoginDate(cd);
        		m.put(sdu, "UPDATE");
        		ps.submitData(m);
        		session.putValue("ut", userType);
        		session.putValue("sdu", sdu);
        	}
        	else
        	{
        		succ=false;
        		msg="用户名或密码错误";    		
        	}    		
    	}
    	else if("S".equals(userType))
    	{
    		SDSellerSet sdss = new SDSellerDB().executeQuery("select * from sdseller where password='"+data.get("Password").toString()+"' and (SellerName='"+data.get("UserName").toString()+"' or Phone='"+data.get("UserName").toString()+"' )");
    		if(sdss.size()>0)
        	{
        		succ=true;
        		msg="登陆成功";
        		SDSellerSchema sds = sdss.get(1);
        		sds.setLastLoginDate(cd);
        		m.put(sds, "UPDATE");
        		ps.submitData(m);
        		session.putValue("ut", userType);
        		session.putValue("sds", sds);
        	}
        	else
        	{
        		succ=false;
        		msg="用户名或密码错误";    		
        	}
    	}
    	else if("A".equals(userType))
    	{
    		SDAssSet sdas = new SDAssDB().executeQuery("select * from sdass where password='"+data.get("Password").toString()+"' and AssName='"+data.get("UserName").toString()+"'");
    		if(sdas.size()>0)
        	{
        		succ=true;
        		msg="登陆成功";
        		SDAssSchema sda = sdas.get(1);
        		sda.setLastLoginDate(cd);
        		m.put(sda, "UPDATE");
        		ps.submitData(m);
        		session.putValue("ut", userType);
        		session.putValue("sda", sda);
        	}
        	else
        	{
        		succ=false;
        		msg="用户名或密码错误";    		
        	}
    	}
    }

    public void content_getsidelist()
    {
    	SDCrawlerContentSet sdccs1 = new SDCrawlerContentDB().executeQuery("select * from SDContent where r4='news' order by serialno asc limit 5");
    	rd.accumulate("result1", PubFun.set2JsonArr(sdccs1));
    	SDCrawlerContentSet sdccs2 = new SDCrawlerContentDB().executeQuery("select * from SDContent where r4='notice' order by serialno asc limit 5");
    	rd.accumulate("result2", PubFun.set2JsonArr(sdccs2));
    }
    public void common_search()
    {
    	String kw = data.get("kw").toString();
    	String st = data.get("st").toString();
    	String ServiceType = data.get("ServiceType").toString();
    	String R5 = data.get("R5").toString();
    	//搜文章
    	String SQL = "select * from sdcontent where 1=1 and R1='Y' ";
    	if("T".equals(st))
    	{
    		SQL +=" and (contenttitle like '%"+kw+"%')";
    	}
    	else if("C".equals(st))
    	{
    		SQL +=" and (contenthtml like '%"+kw+"%')";
    	}
    	else if("A".equals(st))
    	{
    		SQL +=" and (contenthtml like '%"+kw+"%' or contenttitle like '%"+kw+"%')";
    	}
    	if(!"ALL".equals(ServiceType))
    	{
    		SQL+=" and ChannelCode='"+st+"'";
    	}
    	SQL+=" order by IssueDate desc";
    	SDContentSet sdcs = new SDContentDB().executeQuery(SQL);
    	
    	rd.accumulate("contentlist", PubFun.set2JsonArr(sdcs));
    	
    	//搜商品
    	String SQL2="select a.prdid,a.prdname,b.sellerid,b.sellername,a.prdprice,a.discount,a.effenddate,a.remark from sdproduct a,sdseller b where a.sellerid=b.sellerid and a.R1='Y' ";

    	if("T".equals(st))
    	{
    		SQL2 +=" and (a.PrdName like '%"+kw+"%' or b.sellername like '%"+kw+"%')";
    	}

    	else if("C".equals(st))
    	{
    		SQL2 +=" and (a.remark like '%"+kw+"%')";
    	}
    	else if("A".equals(st))
    	{
    		SQL2 +=" and (a.PrdName like '%"+kw+"%' or a.remark like '%"+kw+"%' or b.sellername like '%"+kw+"%')";
    	}
    	if(!"ALL".equals(ServiceType))
    	{
    		SQL2+=" and b.ServiceType = '"+ServiceType+"'";
    	}
    	if(!"ALL".equals(R5))
    	{
    		SQL2+=" and b.r5 = '"+R5+"'";
    	}
    	SSRS sr = new ExeSQL().execSQL(SQL2);
    	JSONArray ja2 = new JSONArray();
    	for(int i=0;i<sr.getMaxRow();i++)
    	{
    		JSONObject ji = new JSONObject();
    		ji.accumulate("PrdID", sr.GetText(i+1, 1));
    		ji.accumulate("PrdName", sr.GetText(i+1, 2));
    		ji.accumulate("SellerID", sr.GetText(i+1, 3));
    		ji.accumulate("SellerName", sr.GetText(i+1, 4));
    		ji.accumulate("PrdPrice", sr.GetText(i+1, 5));
    		ji.accumulate("Discount", sr.GetText(i+1, 6));
    		ji.accumulate("EffEndDate", sr.GetText(i+1, 7));
    		ji.accumulate("Remark", sr.GetText(i+1, 8));
    		ja2.add(ji);
    	}
    	rd.accumulate("prdlist", ja2);
    }
    public void index_getindex()
    {
    	//1.新闻
    	String SQL1 = "select a.* from sdcontent a,sdchannel b where a.channelcode=b.serialNo and b.channelname like '%新闻%' and a.r1='Y' order by a.SerialNo desc limit 5";
    	SDContentSet sdcs1 = new SDContentDB().executeQuery(SQL1);
    	rd.accumulate("result1", PubFun.set2JsonArr(sdcs1));
    	
    	
    	//2最热服务
    	String SQL = "select * from (" +
		"select a.sellerid,a.shotorgname,a.AuthFlag,a.XY,a.R3,count(*),a.r6,a.r5 from sdseller a,sdorder b,sdproduct c where a.sellerid=c.sellerid and b.prdid=c.prdid and b.r2 not like'%取消%'" +
		" group by a.sellerid,a.sellername,a.AuthFlag,a.xy,a.r3,a.r6 order by count(*) desc) as tmp limit 6";

		SSRS sr = new ExeSQL().execSQL(SQL);
		JSONArray ja2 = new JSONArray();
		for(int i=0;i<sr.getMaxRow();i++)
		{
			JSONObject ji = new JSONObject();
			ji.accumulate("url", "./seller_"+sr.GetText(i+1, 1)+".html");
			ji.accumulate("title", sr.GetText(i+1, 2));
			ji.accumulate("AuthFlag", sr.GetText(i+1, 3));
			ji.accumulate("XY", sr.GetText(i+1, 4));
			ji.accumulate("R3", sr.GetText(i+1, 5));
			ji.accumulate("R4", sr.GetText(i+1, 6));
			ji.accumulate("R6", sr.GetText(i+1, 7));
			ji.accumulate("R5", sr.GetText(i+1, 8));
			ja2.add(ji);
		}
		rd.accumulate("result2", ja2);
		
		//4公告
    	String SQL4 = "select a.* from sdcontent a,sdchannel b where a.channelcode=b.serialNo and b.channelname like '%通知%' and a.r1='Y' order by a.SerialNo desc limit 5";
    	SDContentSet sdcs4 = new SDContentDB().executeQuery(SQL4);
    	rd.accumulate("result4", PubFun.set2JsonArr(sdcs4));
    	
    	String SQL7 = "select a.* from sdcontent a,sdchannel b where a.channelcode=b.serialNo and b.channelname like '%促销%' and a.r1='Y' order by a.SerialNo desc limit 5";
    	SDContentSet sdcs7 = new SDContentDB().executeQuery(SQL7);
    	rd.accumulate("result7", PubFun.set2JsonArr(sdcs7));
    	String SQL8 = "select a.* from sdcontent a,sdchannel b where a.channelcode=b.serialNo and b.channelname like '%邻里推荐%' and a.r1='Y' order by a.SerialNo desc limit 5";
    	SDContentSet sdcs8 = new SDContentDB().executeQuery(SQL8);
    	rd.accumulate("result8", PubFun.set2JsonArr(sdcs8));
    	//9最新活动
    	String SQL9 = "select * from sdassactivity where AppStatus='Y' order by actid desc limit 5";
    	SDAssActivitySet sdaas = new SDAssActivityDB().executeQuery(SQL9);
    	rd.accumulate("result9", PubFun.set2JsonArr(sdaas));

    }
    public void user_regsendcode()
    {
    	String code = data.get("code").toString();
    	String Phone = data.get("Phone").toString();
    	String content = "您的认证码是"+code+"。【清华园社区】";
    	PubFun.sendMessage(Phone, content);

    	//存储发送轨迹
    	SDSendCodeTraceSchema sdsct = new SDSendCodeTraceSchema();
    	sdsct.setSerialNo(UUID.randomUUID().toString());
    	sdsct.setPhone(Phone);
    	sdsct.setCode(code);
    	sdsct.setSendDate(cd);
    	sdsct.setSendTime(ct);
    	sdsct.setR1("WEB");
    	sdsct.setR2(content);
    	m.put(sdsct, "INSERT");
    	ps.submitData(m);
    	succ=true;
    	msg="发送成功";
    }
    public void user_regmodify()
    {
    	if(!PubFun.checkZero("select count(*) from sduser where phone='"+data.get("Phone").toString()+"' and userid <> '"+data.get("UserID").toString()+"'"))
    	{
    		succ=false;
    		msg="该手机号已经被使用";
    		return;
    	}
    	SDUserSchema sdu = new SDUserDB().executeQuery("select * from sduser where userid = '"+data.get("UserID").toString()+"'").get(1);
    	String[] pa = {"Password","UserName","NickName","Email","Phone","TNo","IDNo","Address","Special","R1"};
    	for(int i=0;i<pa.length;i++)
    	{
    		System.out.println(pa[i]);
    		sdu.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sdu.setPassModDate(cd);
    	sdu.setPassModTime(ct);
    	sdu.setLastPass(sdu.getPassword());
    	
    	m.put(sdu, "UPDATE");   	

    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="修改成功";
    		session.putValue("sdu", sdu);  
    		session.putValue("ut", "U");
    	}
    }
    public void user_reg()
    {
    	if(!PubFun.checkZero("select count(*) from sduser where phone='"+data.get("Phone").toString()+"'"))
    	{
    		succ=false;
    		msg="手机号码已经注册";
    		return;
    	}
    	if(!PubFun.checkZero("select count(*) from sduser where UserName='"+data.get("UserName").toString()+"'"))
    	{
    		succ=false;
    		msg="用户名已经注册";
    		return;
    	}
    	SDUserSchema sdu = new SDUserSchema();
    	String[] pa = {"Password","UserName","NickName","Email","Phone","AuthFlag","TNo","IDNo","Address","Special","R1"};
    	for(int i=0;i<pa.length;i++)
    	{
    		System.out.println(pa[i]);
    		sdu.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sdu.setRegDate(cd);
    	sdu.setRegTime(ct);
    	sdu.setLastLoginDate(cd);
    	sdu.setLastLoginTime(ct);
    	sdu.setPassModDate(cd);
    	sdu.setPassModTime(ct);
    	sdu.setLastPass(sdu.getPassword());
    	String uid = PubFun.CreateMaxNo("U", "U", 8);
    	sdu.setUserID(uid);
    	
    	m.put(sdu, "INSERT");   	
    	
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="注册成功";
    		session.putValue("sdu", sdu);
    		
    	}
    }
    public void seller_reg()
    {
    	if(!PubFun.checkZero("select count(*) from sdseller where SellerName='"+data.get("SellerName").toString()+"'"))
    	{
    		succ=false;
    		msg="用户名已经注册";
    		return;
    	}if(!PubFun.checkZero("select count(*) from sdseller where phone='"+data.get("Phone").toString()+"'"))
    	{
    		succ=false;
    		msg="手机号已经注册";
    		return;
    	}
    	String OrgName = data.get("OrgName").toString();
    	if(!"".equals(OrgName)&&OrgName!=null)
    	{
        	if(!PubFun.checkZero("select count(*) from sdseller where OrgName='"+data.get("OrgName").toString()+"'"))
        	{
        		succ=false;
        		msg="机构名称已经注册";
        		return;
        	}    		
    	}
    	SDSellerSchema sds = new SDSellerSchema();
    	String[] pa = {"Password","SellerName","OrgName","Email","Phone","AuthFlag","XY","ShotOrgName","FoundDate","ServiceType",
    			"Address","ServiceFeature","OrgPeople","TelPhone","WebPage","LinkPerson","FaxNo","RidingRoute","Remark","OrgBrief","R5","R2"};
    	for(int i=0;i<pa.length;i++)
    	{
    		sds.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sds.setRegDate(cd);
    	sds.setRegTime(ct);
    	sds.setLastLoginDate(cd);
    	sds.setLastLoginTime(ct);
    	sds.setPassModDate(cd);
    	sds.setPassModTime(ct);
    	sds.setLastPass(sds.getPassword());
    	sds.setR3("0");
    	sds.setR4("0");
    	sds.setR6("1");
    	sds.setR8("0");
    	sds.setR9("N");
    	String sid = PubFun.CreateMaxNo("S", "S", 8);
    	sds.setSellerID(sid);
    	try {
			if(new SimpleDateFormat("yyyy-MM-dd").parse(sds.getFoundDate()).after(new SimpleDateFormat("yyyy-MM-dd").parse(cd)))
			{
				succ=false;
				msg="成立日期晚于当前时间，不能注册";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	m.put(sds, "INSERT");   	
    	
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		//转移图片
    		//根目录
    		String path =this.getClass().getClassLoader().getResource("/").getPath();
            path=path.split("WEB-INF")[0]+"sellerimg/";
            File fo = new File(path+"temp/"+data.get("imgurl").toString()+".jpg");
            // Destination directory
            File dir = new File(path);
           
            // Move file to new directory
            succ = fo.renameTo(new File(dir, sid+".jpg"));
    		msg="注册成功";
    		session.putValue("sds", sds);  
    		session.putValue("ut", "S");
    	}
    }
    public void seller_regmodify()
    {
    	SDSellerSchema sds = new SDSellerDB().executeQuery("select * from sdseller where sellerid='"+data.get("SellerID").toString()+"'").get(1);
    	String[] pa = {"Password","SellerName","OrgName","Email","Phone","XY","ShotOrgName","FoundDate",
    			"Address","ServiceFeature","OrgPeople","TelPhone","WebPage","LinkPerson","FaxNo","RidingRoute","Remark","OrgBrief"};
    	for(int i=0;i<pa.length;i++)
    	{
    		sds.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sds.setPassModDate(cd);
    	sds.setPassModTime(ct);
    	sds.setLastPass(sds.getPassword());
    	
    	if("N".equals(sds.getAuthFlag())&&"Y".equals(data.get("R2").toString()))
    	{
    		sds.setR2("Y");
    	}
    	
    	m.put(sds, "UPDATE");   	
    	
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		if(!"".equals(data.get("imgurl").toString())&&data.get("imgurl").toString()!=null)
    		{
    			//转移图片
        		//根目录
        		String path =this.getClass().getClassLoader().getResource("/").getPath();
        		path=path.split("WEB-INF")[0]+"sellerimg/";
        		File fo = new File(path+"temp/"+data.get("imgurl").toString()+".jpg");
        		// Destination directory
        		File dir = new File(path);
        		new File(path+sds.getSellerID()+".jpg").delete();
        		// Move file to new directory
        		succ = fo.renameTo(new File(dir, sds.getSellerID()+".jpg"));
    		}
    		
    		msg="修改成功";
    		session.putValue("sds", sds);  
    		session.putValue("ut", "S");
    	}
    }
    public void ass_reg()
    {
    	if(!PubFun.checkZero("select count(*) from sdass where phone='"+data.get("Phone").toString()+"'"))
    	{
    		succ=false;
    		msg="联系人手机号码已经注册";
    		return;
    	}
    	if(!PubFun.checkZero("select count(*) from sdass where AssName='"+data.get("AssName").toString()+"'"))
    	{
    		succ=false;
    		msg="用户名已经注册";
    		return;
    	}
    	if(!PubFun.checkZero("select count(*) from sdass where LinkPerson='"+data.get("LinkPerson").toString()+"' and LinkPerson<>'' and LinkPerson is not null"))
    	{
    		succ=false;
    		msg="注册联系人已经被使用";
    		return;
    	}
    	SDAssSchema sda = new SDAssSchema();
    	String[] pa = {"Password","AssName","ShotOrgName","OrgName","Email","Phone","AssPeople","AssFeature","AssType","WebPage","Address","LinkPerson","R1","R2"};
    	for(int i=0;i<pa.length;i++)
    	{
    		System.out.println("--"+pa[i]);
    		sda.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sda.setRegDate(cd);
    	sda.setRegTime(ct);
    	sda.setLastLoginDate(cd);
    	sda.setLastLoginTime(ct);
    	sda.setPassModDate(cd);
    	sda.setPassModTime(ct);
    	sda.setLastPass(sda.getPassword());
    	String uid = PubFun.CreateMaxNo("A", "A", 8);
    	sda.setAssID(uid);
    	
    	m.put(sda, "INSERT");   	
    	
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="注册成功";
    		session.putValue("sda", sda);  
    		session.putValue("ut", "A");
    	}
    }
    public void ass_regmodify()
    {
    	SDAssSchema sda = new SDAssDB().executeQuery("select * from sdass where assid = '"+data.get("AssID").toString()+"'").get(1);
    	String[] pa = {"Password","AssName","ShotOrgName","OrgName","Email","Phone","AssPeople","AssFeature","AssType","WebPage","Address","LinkPerson","R1","R2"};
    	for(int i=0;i<pa.length;i++)
    	{
    		sda.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sda.setPassModDate(cd);
    	sda.setPassModTime(ct);
    	sda.setLastPass(sda.getPassword());
    	
    	m.put(sda, "UPDATE");   	
    	
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="修改成功";

    		session.putValue("ut", "A");
    		session.putValue("sda", sda);
    	}
    }
    public boolean getInputData(VData cInputData)
    {
    	String ds = (String) cInputData.getObjectByObjectName("String", 0);
    	session = (HttpSession) cInputData.getObjectByObjectName("ORG.APACHE.CATALINA.SESSION.STANDARDSESSIONFACADE", 0);

    	data = JSONObject.fromObject(ds);
        return true;
    }
    public VData getResult()
    {
        return mResult;
    }
}
