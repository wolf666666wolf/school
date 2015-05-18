package com.sinosoft.lis.h5;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sinosoft.lis.db.SDAssActAtnDB;
import com.sinosoft.lis.db.SDAssActivityDB;
import com.sinosoft.lis.db.SDAssDB;
import com.sinosoft.lis.db.SDChannelDB;
import com.sinosoft.lis.db.SDContentDB;
import com.sinosoft.lis.db.SDOrderDB;
import com.sinosoft.lis.db.SDProductDB;
import com.sinosoft.lis.db.SDProductFavoriteDB;
import com.sinosoft.lis.db.SDSellerDB;
import com.sinosoft.lis.db.SDSellerFollowDB;
import com.sinosoft.lis.db.SDSendCodeTraceDB;
import com.sinosoft.lis.db.SDUserDB;
import com.sinosoft.lis.pubfun.MMap;
import com.sinosoft.lis.pubfun.PubFun;
import com.sinosoft.lis.pubfun.PubSubmit;
import com.sinosoft.lis.schema.SDAssActAtnSchema;
import com.sinosoft.lis.schema.SDContentSchema;
import com.sinosoft.lis.schema.SDOrderSchema;
import com.sinosoft.lis.schema.SDProductAssessSchema;
import com.sinosoft.lis.schema.SDProductFavoriteSchema;
import com.sinosoft.lis.schema.SDProductSchema;
import com.sinosoft.lis.schema.SDSellerFollowSchema;
import com.sinosoft.lis.schema.SDSellerMessageSchema;
import com.sinosoft.lis.schema.SDSellerSchema;
import com.sinosoft.lis.schema.SDSendCodeTraceSchema;
import com.sinosoft.lis.schema.SDUserSchema;
import com.sinosoft.lis.vschema.SDAssActAtnSet;
import com.sinosoft.lis.vschema.SDAssActivitySet;
import com.sinosoft.lis.vschema.SDAssSet;
import com.sinosoft.lis.vschema.SDChannelSet;
import com.sinosoft.lis.vschema.SDContentSet;
import com.sinosoft.lis.vschema.SDOrderSet;
import com.sinosoft.lis.vschema.SDProductFavoriteSet;
import com.sinosoft.lis.vschema.SDProductSet;
import com.sinosoft.lis.vschema.SDSellerFollowSet;
import com.sinosoft.lis.vschema.SDSellerSet;
import com.sinosoft.lis.vschema.SDSendCodeTraceSet;
import com.sinosoft.lis.vschema.SDUserSet;
import com.sinosoft.lis.wx.WXBL;
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
    public PubSubmit ps = new PubSubmit();
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

        try {
        	this.getClass().getMethod(mOperate,new Class[]{}).invoke(this, new Object[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			succ=false;
        	msg="��֧�ֵĲ���";
		} 
        
        String result = "{succ:"+succ+",msg:'"+msg+"',data:"+rd.toString()+"}";
        JSONObject rjo = JSONObject.fromObject(result);
        
        mResult.add(rjo.toString());
        return true;
    }
    public long getCT(String s1,String s2)
    {
    	long r = 0;
    	try {
			Date d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s1);
	    	Date d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s2);
	    	r=d1.getTime()-d2.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
    }
  public void weixin_getcofig()
  {
    String url = this.data.get("url").toString();
    String r = WXBL.getInstance().get_signature(url);
    this.succ = true;
    this.rd = JSONObject.fromObject(r);
  }
    public void seller_setcounter()
    {
    	String SellerID = data.get("SellerID").toString();
    	m.put("update sdseller set r8=ifnull(r8,0)*1+1 where sellerid='"+SellerID+"'", "UPDATE");
    	succ=ps.submitData(m);
    }
    //���޸�
    public void user_userbind()
    {
    	//У����֤��
    	String Phone = data.get("Phone").toString();
    	String Code = data.get("VerifyCode").toString();
    	SDSendCodeTraceSet s = new SDSendCodeTraceDB().executeQuery("select * from sdsendcodetrace where phone='"+Phone+"' and code='"+Code+"'");
    	if(s.size()==0)
    	{
    		succ=false;
    		msg="��֤�����";
    		return;
    	}
    	long x = getCT(cd+" "+ct,s.get(1).getSendDate()+" "+s.get(1).getSendTime());
    	if(x>14400000)
    	{
    		succ=false;
    		msg="��֤���Ѿ�ʧЧ";
    		return;
    	}
    	SDUserSchema sdu = new SDUserSchema();
    	SDUserSet  sduser = new SDUserDB().executeQuery("select * from sduser where phone='"+Phone+"'");
    	if(sduser.size()>0)
    	{
    		sdu=sduser.get(1);
    	}	
    	else
    	{
    		//ע������û�

        	sdu.setRegDate(cd);
        	sdu.setRegTime(ct);
        	sdu.setLastLoginDate(cd);
        	sdu.setLastLoginTime(ct);
        	sdu.setPassModDate(cd);
        	sdu.setPassModTime(ct);
        	String uid = PubFun.CreateMaxNo("U", "U", 8);
        	System.out.println("____----____"+uid);
        	sdu.setUserID(uid);
        	sdu.setUserName(Phone);
        	sdu.setNickName(Phone);
        	sdu.setPhone(Phone);
        	//������8λ����
        	String pass = UUID.randomUUID().toString();
        	pass=pass.substring(0, 8);

        	sdu.setPassword(pass);
        	sdu.setLastPass(sdu.getPassword());
        	m.put(sdu, "INSERT");   	
        	
        	succ=ps.submitData(m);
        	//�����Ÿ�֪�û�

        	String content = "��Ϊ��ɹ�ע����վ�û����û���"+Phone+"������Ϊ"+pass+"��������¼��վ�޸����롣���廪԰����";
        	PubFun.sendMessage(Phone, content);
        	//�洢���͹켣
        	SDSendCodeTraceSchema sdsct = new SDSendCodeTraceSchema();
        	sdsct.setSerialNo(UUID.randomUUID().toString());
        	sdsct.setPhone(Phone);
        	sdsct.setCode("");
        	sdsct.setSendDate(cd);
        	sdsct.setSendTime(ct);
        	sdsct.setR1("H5");
        	sdsct.setR2(content);
        	m=new MMap();
        	m.put(sdsct, "INSERT");
        	ps.submitData(m);
    	}
    	
    	succ=true;
    	msg="�󶨳ɹ�";
    	rd.accumulate("userinfo", PubFun.schema2JsonObj(sdu));
    }
    public void seller_getsellerlist()
    {
    	String KeyWords = data.get("KeyWords").toString();
    	String SortType = data.get("SortType").toString();
    	String PrdType = data.get("PrdType").toString();
    	String SQL = "select * from sdseller where 1=1 and r9<>'Y' ";
    	if(!PubFun.isNull(KeyWords))
    	{
    		SQL+=" and (sellername like '%"+KeyWords+"%' or OrgName like '%"+KeyWords+"%' or ShotOrgName like '%"+KeyWords+"%' or Address like '%"+KeyWords+"%')";
    	}
    	if(!PubFun.isNull(PrdType))
    	{
    		SQL+=" and ServiceType='"+PrdType+"'";
    	}
    	if(SortType==null||"".equals(SortType)||"p".equals(SortType))
    	{
        	SQL+=" order by sellerid asc";
    	}
    	
    	succ=true;
    	msg="��ѯ�ɹ�";
    	SDSellerSet  sdseller = new SDSellerDB().executeQuery(SQL);
    	JSONArray ja = PubFun.set2JsonArr(sdseller);
    	for(int i=0;i<ja.size();i++)
    	{
    		double dis = 1;
    		try {
    			String xy = ja.getJSONObject(i).get("XY").toString();
    			String[] xya = xy.split("\\|");
    			String uxy = data.get("UserPosition").toString();
    			String[] uxya = uxy.split("\\|");
    			System.out.println("___XY___"+xy+","+uxy);
				dis = PubFun.getDistatce(Double.parseDouble(xya[0]), Double.parseDouble(uxya[0]), Double.parseDouble(xya[1]), Double.parseDouble(uxya[1]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			//������λС��
			DecimalFormat df = new DecimalFormat("#.00");
    		ja.getJSONObject(i).accumulate("SellerDistance", df.format(dis)+"����");
    		ja.getJSONObject(i).accumulate("SellerDistanceNum", dis);
    	}
    	if("d".equals(SortType))
    	{
        	ja=sortJSONArray(ja);
        	for(int i=0;i<ja.size();i++)
        	{
            	ja=sortJSONArray(ja);
        	}
    	}

    	rd.accumulate("sellerlist", ja);
    }
    public JSONArray sortJSONArray(JSONArray jsonArr){  
        JSONObject jObject = null;  
     for(int i = 0;i<jsonArr.size();i++){  
         double l = jsonArr.getJSONObject(i).getDouble("SellerDistanceNum");  
         for(int j = i+1; j<jsonArr.size();j++){  
        	 double nl = jsonArr.getJSONObject(j).getDouble("SellerDistanceNum");  
                 if(l>nl){  
                     jObject = jsonArr.getJSONObject(j);  
                     jsonArr.set(j, jsonArr.getJSONObject(i));  
                     jsonArr.set(i, jObject);  
                 }  
         }  

     }  
     for(int i = 0;i<jsonArr.size();i++){
         //System.out.println(jsonArr.getJSONObject(i).get("SellerDistance").toString());
     }
     return jsonArr;
}
    public void content_getcampaignlist()
    {
    	String SQL = "select * from sdcontent a,sdchannel b where a.channelcode=b.serialno and b.ChannelName='�������' order by a.serialno desc";
    	SDContentSet sdcs = new SDContentDB().executeQuery(SQL);
    	succ=true;
    	msg="��ѯ�ɹ�";
    	rd.accumulate("list", PubFun.set2JsonArr(sdcs));
    }

    public void content_getcampaign()
    {
    	String SerialNo = data.get("SerialNo").toString();
    	SDContentSchema sdc = new SDContentDB().executeQuery("select * from sdcontent where SerialNo='"+SerialNo+"'").get(1);
    	succ=true;
    	msg="��ѯ�ɹ�";
    	rd=PubFun.schema2JsonObj(sdc);
    }
    public void seller_gethotsellerlist()
    {
    	String SQL = "select * from (" +
    			"select a.sellerid,a.shotorgname,a.AuthFlag,a.XY,a.R3,count(*),a.r6,a.r5,a.r7 from sdseller a,sdorder b,sdproduct c where a.sellerid=c.sellerid and b.prdid=c.prdid and b.r2 not like'%ȡ��%'" +
    			" group by a.sellerid,a.shotorgname,a.AuthFlag,a.xy,a.r3,a.r6,a.r5,a.r7 order by count(*) desc) as tmp limit 10";

    	
    	succ=true;
    	msg="��ѯ�ɹ�";
    	SSRS sr = new ExeSQL().execSQL(SQL);
    	JSONArray ja = new JSONArray();
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
    		ja.add(ji);
    	}
//    	SDSellerSet  sdseller = new SDSellerDB().executeQuery(SQL);
//    	JSONArray ja = PubFun.set2JsonArr(sdseller);
    	for(int i=0;i<ja.size();i++)
    	{
    		double dis = 1;
    		try {
    			String xy = ja.getJSONObject(i).get("XY").toString();
    			String[] xya = xy.split("\\|");
    			String uxy = data.get("UserPosition").toString();
    			String[] uxya = uxy.split("\\|");
    			System.out.println("___XY___"+xy+","+uxy);
				dis = PubFun.getDistatce(Double.parseDouble(xya[0]), Double.parseDouble(uxya[0]), Double.parseDouble(xya[1]), Double.parseDouble(uxya[1]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			//������λС��
			DecimalFormat df = new DecimalFormat("#.00");
    		ja.getJSONObject(i).accumulate("SellerDistance", df.format(dis)+"����");
    	}
    	rd.accumulate("sellerlist", ja);
    }

    public void seller_getseller()
    {
    	seller_setcounter();
    	String SellerID = data.get("SellerID").toString();
    	String UserID = data.get("UserID").toString();
    	SDSellerSet sdss = new SDSellerDB().executeQuery("select * from sdseller where sellerid='"+SellerID+"' and r9<>'Y'");
    	if(sdss.size()==0)
    	{
    		succ=false;
    		msg="δ��ѯ����Ϣ";
    	}
    	else
    	{
    		succ=true;
    		msg="��ѯ�ɹ�";
    		rd=PubFun.schema2JsonObj(sdss.get(1));
    		String ConnStatus = "Y";
    		if(PubFun.checkZero("select count(*) from sdsellerfollow where sellerid='"+SellerID+"' and userid='"+UserID+"'"))
    		{
    			ConnStatus="N";
    		}
    		rd.accumulate("ConnStatus", ConnStatus);
    		rd.accumulate("PicUrl",  "../sellerimg/"+sdss.get(1).getSellerID()+".jpg");
    	}
    }

    public void seller_getchnllist()
    {
    	SDChannelSet sdcs = new SDChannelDB().executeQuery("select * from sdchannel where r2='Y' and r3='2' union select * from sdchannel a where " +
    			"exists (select 1 from sdchannel b where a.SerialNo=b.parentchannel and b.r3='2' and a.r2='Y')");
    	succ=true;
    	msg="��ѯ�ɹ�";
    	rd.accumulate("channellist", PubFun.set2JsonArr(sdcs));
    	
    }
    public void seller_follow()
    {
    	String SellerID = data.get("SellerID").toString();
    	String UserID = data.get("UserID").toString();
    	String Act = data.get("Act").toString();
    	if("add".equals(Act))
    	{
    		SDSellerFollowSet sdsfs = new SDSellerFollowDB().executeQuery("select * from sdsellerfollow where sellerid='"+SellerID+"' and userid='"+UserID+"'");
        	if(sdsfs.size()>0)
        	{
        		succ=true;
        		msg="��ע�ɹ�";
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
        		msg="��ע�ɹ�";
        	}
    	}
    	else if("cancel".equals(Act))
    	{
    		m.put("delete from sdsellerfollow where sellerid='"+SellerID+"' and userid='"+UserID+"'", "DELETE");
    		succ=ps.submitData(m);
    		msg="ȡ���ע�ɹ�";
    	}
    	else
    	{
    		succ=false;
    		msg="Act����ݴ���";
    	}
    	
    }

    public void product_getprd()
    {
    	String PrdID = data.get("PrdID").toString();
    	String UserID = data.get("UserID").toString();
    	SDProductSet sdps = new SDProductDB().executeQuery("select * from sdproduct where prdid='"+PrdID+"' and r1='Y'");
    	if(sdps.size()==0)
    	{
    		succ=false;
    		msg="δ��ѯ����Ϣ";
    	}
    	else
    	{
    		succ=true;
    		msg="��ѯ�ɹ�";
    		sdps.get(1).setR4(new ExeSQL().getOneValue("select count(*) from sdorder where prdid='"+PrdID+"'"));
    		rd=PubFun.schema2JsonObj(sdps.get(1));
    		String ConnStatus = "Y";
    		if(PubFun.checkZero("select count(*) from SDProductFavorite where prdid='"+PrdID+"' and userid='"+UserID+"'"))
    		{
    			ConnStatus="N";
    		}
    		rd.accumulate("ConnStatus", ConnStatus);
//    		rd.accumulate("PicUrl", "./static/images/"+sdps.get(1).getPrdID()+".jpg");
    		
    	}
    }
    public void product_getprdlist()
    {
    	String SellerID = data.get("SellerID").toString();
    	String KeyWords = data.get("KeyWords").toString();
    	SDProductSet sdps = new SDProductDB().executeQuery("select * from sdproduct where sellerid='"+SellerID+"' and R1='Y' and prdname like '%"+KeyWords+"%'");
    	SDSellerSchema sds = new SDSellerDB().executeQuery("select * from sdseller where sellerid = '"+SellerID+"'").get(1);
    	succ=true;
    	msg="��ѯ�ɹ�";
    	SSRS r4 = new ExeSQL().execSQL("select count(*),a.prdid from sdorder a,sdproduct b where a.prdid=b.prdid and b.sellerid='"+SellerID+"' group by a.prdid");
    	for(int i=0;i<sdps.size();i++)
    	{
    		sdps.get(i+1).setR4("0");
    		for(int j=0;j<r4.getMaxRow();j++)
    		{
    			if(r4.GetText(j+1, 2).equals(sdps.get(i+1).getPrdID()))
    				sdps.get(i+1).setR4(r4.GetText(j+1, 1));
    		}
    	}
    	rd.accumulate("productlist", PubFun.set2JsonArr(sdps));
    	rd.accumulate("ShotOrgName", sds.getShotOrgName());
    	rd.accumulate("AuthFlag", sds.getAuthFlag());
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
    		msg="ȡ���ղسɹ�";
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
    		msg="�ղسɹ�";
    	}

    }

    public void product_assess()
    {
    	SDProductAssessSchema sdpa = new SDProductAssessSchema();
    	
    	String[] pa = {"OrderID","UserID","Title","Level","Community","Content","Phone"};
    	for(int i=0;i<pa.length;i++)
    	{
    		sdpa.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sdpa.setAssEssDate(cd);
    	sdpa.setAssEssTime(ct);
    	sdpa.setSerialNo(PubFun.CreateMaxNo("SA", "SA", 20));
    	m.put(sdpa, "INSERT");
    	m.put("update sdorder set r2='�����(������)' where orderid='"+data.get("OrderID")+"'", "UPDATE");

    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="���۳ɹ�";
    	}
    	else
    	{
    		msg="����ʧ��";
    	}
    	
    }

    public void seller_message()
    {
    	SDSellerMessageSchema sdsm = new SDSellerMessageSchema();
    	
    	String[] pa = {"SellerID","UserID","Level","Context","Phone","OrderID"};
    	for(int i=0;i<pa.length;i++)
    	{
    		sdsm.setV(pa[i], data.get(pa[i]).toString());
    	}
    	sdsm.setMsgDate(cd);
    	sdsm.setMsgTime(ct);
    	sdsm.setSerialNo(PubFun.CreateMaxNo("SM", "SM", 20));
    	m.put(sdsm, "INSERT");
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="���Գɹ�";
    	}
    	else
    	{
    		msg="����ʧ��";
    	}
    	
    }

    public void activity_getactlist()
    {
    	String KeyWrods = data.get("KeyWrods").toString();
    	String SQL = "select * from SDAssActivity where 1=1 ";
    	String SQL2 = "select a.* from sdass a,SDAssActivity b where a.assid=b.assid ";
    	if(!PubFun.isNull(KeyWrods))
    	{
    		SQL+=" and (Title like '%"+KeyWrods+"%' or ActRule like '%"+KeyWrods+"%' or ActContent like '%"+KeyWrods+"%' or ActGift like '%"+KeyWrods+"%')";
    		SQL2+=" and (Title like '%"+KeyWrods+"%' or ActRule like '%"+KeyWrods+"%' or ActContent like '%"+KeyWrods+"%' or ActGift like '%"+KeyWrods+"%')";
    	}
    	SQL +=" and AppStatus='Y'";
    	SQL2 +=" and AppStatus='Y'";
    	SQL+=" order by actid desc";
    	SDAssSet sdas = new SDAssDB().executeQuery(SQL2);
    	SDAssActivitySet sdaas = new SDAssActivityDB().executeQuery(SQL);
    	JSONArray ja = new JSONArray();
    	for(int i=0;i<sdaas.size();i++)
    	{
    		JSONObject ji = PubFun.schema2JsonObj(sdaas.get(i+1));
    		for(int j=0;j<sdas.size();j++)
    		{
    			if(sdaas.get(i+1).getAssID().equals(sdas.get(j+1).getAssID()))
    			{
    				ji.accumulate("ShotOrgName", sdas.get(j+1).getShotOrgName());
    				break;
    			}
    		}
    		ja.add(ji);
    	}
    	succ=true;
    	msg="��ѯ�ɹ�";
    	rd.accumulate("actlist", ja);
    }

    public void activity_getact()
    {
    	String ActID = data.get("ActID").toString();
    	String UserID = data.get("UserID").toString();
    	SDAssActivitySet sdaas = new SDAssActivityDB().executeQuery("select * from SDAssActivity where ActID = '"+ActID+"'");
    	if(sdaas.size()==0)
    	{
    		succ=false;
    		msg="δ��ѯ���";
    	}
    	else
    	{
    		succ=true;
    		msg="��ѯ�ɹ�";
    		rd=PubFun.schema2JsonObj(sdaas.get(1));
    		String Sataus = "y";
    		try{
    		String nowDate = PubFun.getCurrentDate();

    		if(new SimpleDateFormat("yyyy-MM-dd").parse(nowDate).after(new SimpleDateFormat("yyyy-MM-dd").parse(sdaas.get(1).getRegEndDate())))
    		{
    			Sataus="n";
    		}
    		else
    		{
    			Sataus="y";
    		}
    		}
    		catch(Exception e){}
    		rd.accumulate("Sataus", Sataus);
    		rd.accumulate("PtcNum", new ExeSQL().getOneValue("SELECT COUNT(*) from SDAssActAtn where actid='"+ActID+"'"));
    		rd.accumulate("ConnPsn", new ExeSQL().getOneValue("select assname from sdass where assid='"+sdaas.get(1).getAssID()+"'"));
    			String ConnStatus ="y";
    		if(PubFun.checkZero("select count(*) from sdassactatn where actid='"+ActID+"' and userid='"+UserID+"'"))
    		{
    			ConnStatus="n";
    		}
    		rd.accumulate("ConnStatus", ConnStatus);
    		
    	}

    }
    public void order_sendorder()
    {
    	SDOrderSchema sdo = new SDOrderSchema();
    	
    	String[] pa = {"PrdID","UserID","OrderDate","ArriveDate","Owner","OCount","ComeFlag","R1","Address","Requirement","Phone","Tel"};
    	for(int i=0;i<pa.length;i++)
    	{
    		sdo.setV(pa[i], data.get(pa[i]).toString());
    	}
		//    	sdo.setOrderDate(cd);
    	sdo.setMakeDate(cd);
    	sdo.setMakeTime(ct);
    	sdo.setModifyDate(cd);
    	sdo.setModifyTime(ct);
    	String OrderID = PubFun.CreateMaxNo("O", "O", 10);
    	sdo.setOrderID(OrderID);
    	sdo.setR2("���ύ");
    	m.put(sdo, "INSERT");
    	SDProductSchema sdp = new SDProductDB().executeQuery("select * from sdproduct where prdid='"+data.get("PrdID").toString()+"'").get(1);
    	m.put("update sdseller set r3=(select count(*) from sdorder b,sdproduct c where b.prdid=c.prdid and c.sellerid=sdseller.sellerid) where sellerid='"+sdp.getSellerID()+"'", "UPDATE");
    	succ=ps.submitData(m);
    	if(succ)
    	{
    		msg="�µ��ɹ�";
    		rd.accumulate("OrderID", OrderID);
     SDSellerSchema se = new SDSellerDB().executeQuery("select * from a.* from sdseller a,sdproduct b where a.sellerid=b.sellerid and b.prdid='" + sdo.getPrdID() + "'").get(1);
      String content = "���û����¶���(" + sdo.getOrderID() + ")����ϵ�绰Ϊ(" + sdo.getPhone() + ")�����¼�鿴�����廪԰����";
      PubFun.sendMessage(se.getPhone(), content);

      SDSendCodeTraceSchema sdsct = new SDSendCodeTraceSchema();
      sdsct.setSerialNo(UUID.randomUUID().toString());
      sdsct.setPhone(se.getPhone());
      sdsct.setCode("");
      sdsct.setSendDate(this.cd);
      sdsct.setSendTime(this.ct);
      sdsct.setR1("WEB");
      sdsct.setR2(content);
      this.m.put(sdsct, "INSERT");
      this.ps.submitData(this.m);
    	}
    	else
    	{
    		msg="�µ�ʧ��";
    	}

    }

    public void activity_register()
    {
    	String ActID = data.get("ActID").toString();
    	String UserID = data.get("UserID").toString();
    	SDAssActAtnSet sdaaas = new SDAssActAtnDB().executeQuery("select * from SDAssActAtn where actid='"+ActID+"' and userid = '"+UserID+"'");
    	if(sdaaas.size()>0)
    	{
    		m.put(sdaaas, "DELETE");
    		m.put("update sdassactivity set r2=(select count(*) from SDAssActAtn a where a.actid='"+ActID+"') where actid='"+ActID+"'", "UPDATE");

    		succ=ps.submitData(m);
    		msg="ȡ��μӳɹ�";
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
    		msg="�μӳɹ�";
    	}    	
    }

    public void user_getcenter()
    {
    	String UserID = data.get("UserID").toString();
    	SDUserSet sdus = new SDUserDB().executeQuery("select * from sduser where userid = '"+UserID+"'");
    	if(sdus.size()==0)
    	{
    		succ=false;
    		msg="δ��ѯ���û���Ϣ";
    	}
    	else
    	{
    		succ=true;
    		msg="��ѯ�ɹ�";
    		rd=PubFun.schema2JsonObj(sdus.get(1));
    		rd.accumulate("NowDate", PubFun.getCurrentDate());
    		rd.accumulate("NowDay", PubFun.getWeekOfDate(new Date()));
        	String c1 = new ExeSQL().getOneValue("select count(*) from 	sdorder where userid='"+UserID+"'");
        	String c2 = new ExeSQL().getOneValue("select count(*) from 	sdorder where userid='"+UserID+"' and r2 like '�����%'");
        	String c3 = new ExeSQL().getOneValue("select count(*) from 	sdorder where userid='"+UserID+"' and r2 not like '�����%'");
        	String c4 = new ExeSQL().getOneValue("select count(*) from 	sdorder where userid='"+UserID+"' and r2 like '%ȡ��%'");
        	String c5 = new ExeSQL().getOneValue("select count(*) from 	SDProductAssess where userid='"+UserID+"'");
        	String c6 = new ExeSQL().getOneValue("select count(*) from 	SDAssActAtn where userid='"+UserID+"'");
        	rd.accumulate("c1", c1);
        	rd.accumulate("c2", c2);
        	rd.accumulate("c3", c3);
        	rd.accumulate("c4", c4);
        	rd.accumulate("c5", c5);
        	rd.accumulate("c6", c6);
    		
    	}

    }
    public void user_getorderlist()
    {
    	String UserID = data.get("UserID").toString();
    	String OrderID = data.get("OrderID").toString();
    	String SellerID = data.get("SellerID").toString();
    	String SQL = "select * from sdorder a where userid='"+UserID+"' ";
    	String SQL2 = "select a.prdid,a.prdname from sdproduct a,sdorder b where a.prdid=b.prdid and b.userid='"+UserID+"' ";
    	if(!PubFun.isNull(OrderID))
    	{
    		SQL+=" and orderid='"+OrderID+"'";
    		SQL2+=" and orderid='"+OrderID+"'";
    		
    	}
    	if(!PubFun.isNull(SellerID))
    	{
    		SQL+=" and exists (select 1 from sdproduct b where a.prdid=b.prdid and b.sellerid='"+SellerID+"')";
    		SQL2+=" and a.sellerid='"+SellerID+"'";
    	}
    	SQL+=" order by orderid desc";
    	
    	SSRS sr = new ExeSQL().execSQL(SQL2);
    	
    	SDOrderSet sdos = new SDOrderDB().executeQuery(SQL);
    	JSONArray ja = PubFun.set2JsonArr(sdos);
    	for(int i=0;i<ja.size();i++)
    	{
    		for(int j=0;j<sr.getMaxRow();j++)
    		{
    			if(ja.getJSONObject(i).get("PrdID").equals(sr.GetText(j+1, 1)))
    			{
    				ja.getJSONObject(i).accumulate("PrdName", sr.GetText(j+1, 2));
    				break;
    			}
    		}
    	}
    	succ=true;
    	msg="��ѯ�ɹ�";
    	rd.accumulate("orderlist", ja);
    }

    public void user_getactlist()
    {
    	String UserID = data.get("UserID").toString();
    	String ActID = data.get("ActID").toString();
    	String SQL = "select b.* from sdassactatn a,sdassactivity b where a.actid=b.actid and a.userid='"+UserID+"' ";
    	if(!PubFun.isNull(ActID))
    	{
    		SQL+=" and a.ActID='"+ActID+"'";
    	}
    	SQL+=" order by a.actid desc";
    	SDAssActivitySet sdaas = new SDAssActivityDB().executeQuery(SQL);
    	succ=true;
    	msg="��ѯ�ɹ�";
    	rd.accumulate("actlist", PubFun.set2JsonArr(sdaas));
    }
    public void user_getfavoritelist()
    {
    	String UserID = data.get("UserID").toString();
    	String PrdID = data.get("PrdID").toString();
    	String SQL = "select b.* from sdproductfavorite a,sdproduct b where a.prdid=b.prdid and a.userid='"+UserID+"' order by a.serialno desc";
    	if(!PubFun.isNull(PrdID))
    	{
    		SQL+=" and a.prdID='"+PrdID+"'";
    	}
    	SDProductSet sdps = new SDProductDB().executeQuery(SQL);
    	succ=true;
    	msg="��ѯ�ɹ�";
    	rd.accumulate("favoritelist", PubFun.set2JsonArr(sdps));
    }
    public void user_getfollowlist()
    {
    	String UserID = data.get("UserID").toString();
    	String SellerID = data.get("SellerID").toString();
    	String SQL = "select b.* from sdsellerfollow a,sdseller b where a.sellerid=b.sellerid and a.userid='"+UserID+"' order by a.serialno desc";
    	if(!PubFun.isNull(SellerID))
    	{
    		SQL+=" and a.SellerID='"+SellerID+"'";
    	}
    	SDSellerSet sdss = new SDSellerDB().executeQuery(SQL);
    	succ=true;
    	msg="��ѯ�ɹ�";
    	JSONArray ja =  PubFun.set2JsonArr(sdss);
    	for(int i=0;i<ja.size();i++)
    	{
    		double dis = 1;
    		try {
    			String xy = ja.getJSONObject(i).get("XY").toString();
    			String[] xya = xy.split("\\|");
    			String uxy = data.get("UserPosition").toString();
    			String[] uxya = uxy.split("\\|");
    			System.out.println("___XY___"+xy+","+uxy);
				dis = PubFun.getDistatce(Double.parseDouble(xya[0]), Double.parseDouble(uxya[0]), Double.parseDouble(xya[1]), Double.parseDouble(uxya[1]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			//������λС��
			DecimalFormat df = new DecimalFormat("#.00");
    		ja.getJSONObject(i).accumulate("SellerDistance", df.format(dis)+"����");
    	}
    	rd.accumulate("followlist", PubFun.set2JsonArr(sdss));
    }

    public void user_sendcode()
    {
    	String Phone = data.get("Phone").toString();
    	//�������ĸ�����
    	String code = String.valueOf(System.currentTimeMillis());
    	code=code.substring(code.length()-6, code.length());
    	//�ԽӶ���ƽ̨���Ͷ���
    	String content = "�����֤����"+code+"�����廪԰����";
    	PubFun.sendMessage(Phone, content);
    	//�洢���͹켣
    	SDSendCodeTraceSchema sdsct = new SDSendCodeTraceSchema();
    	sdsct.setSerialNo(UUID.randomUUID().toString());
    	sdsct.setPhone(Phone);
    	sdsct.setCode(code);
    	sdsct.setSendDate(cd);
    	sdsct.setSendTime(ct);
    	sdsct.setR1("H5");
    	sdsct.setR2(content);
    	m.put(sdsct, "INSERT");
    	ps.submitData(m);
    	succ=true;
    	msg="���ͳɹ�";

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
	public static void main(String[] args) {
		//System.out.println(PubFun.schema2JsonObj(new SDUserDB().executeQuery("select * from sduser").get(1)));
//		VData vd = new VData();
//		vd.add("{UserPosition:'',SellerID:'111',UserID:'222',Level:'3',Context:'3',Phone:'3',OrderID:'3'}");
//		new ActionBL().submitData(vd,"user_getorderlist");
//		JSONArray ja = JSONArray.fromObject("[{\"Phone\":\"34\",\"OrgPeople\":\"\",\"SellerDistance\":\"10.84����\",\"PassModDate\":\"2014-11-11\",\"Password\":\"12\",\"R15\":\"\",\"ServiceType\":\"C0020\",\"RegTime\":\"09:36:31\",\"R13\":\"\",\"PassModTime\":\"09:36:31\",\"R14\":\"\",\"R11\":\"\",\"R12\":\"\",\"R10\":\"\",\"R8\":\"\",\"R9\":\"\",\"R6\":\"1\",\"LastLoginDate\":\"2014-11-12\",\"R7\":\"0\",\"LastLoginTime\":\"09:36:31\",\"SellerDistanceNum\":10.837932096166556,\"R4\":\"0\",\"R5\":\"�����\",\"R2\":\"\",\"LinkPerson\":\"\",\"R3\":\"0\",\"ShotOrgName\":\"\",\"ServiceFeature\":\"\",\"RidingRoute\":\"\",\"SellerID\":\"S00000007\",\"SellerName\":\"����\",\"XY\":\"116.332585|40.00871\",\"RegDate\":\"2014-11-11\",\"R1\":\"\",\"OrgName\":\"\",\"FaxNo\":\"\",\"TelPhone\":\"\",\"OrgBrief\":\"\",\"LastPass\":\"12\",\"Email\":\"12\",\"Address\":\"\",\"FoundDate\":\"2010-10-10\",\"WebPage\":\"\",\"AuthFlag\":\"Y\",\"Remark\":\"\"},{\"Phone\":\"1553\",\"OrgPeople\":\"\",\"SellerDistance\":\"10.37����\",\"PassModDate\":\"2014-11-11\",\"Password\":\"12\",\"R15\":\"\",\"ServiceType\":\"C0020\",\"RegTime\":\"10:02:40\",\"R13\":\"\",\"PassModTime\":\"10:02:40\",\"R14\":\"\",\"R11\":\"\",\"R12\":\"\",\"R10\":\"\",\"R8\":\"\",\"R9\":\"\",\"R6\":\"1\",\"LastLoginDate\":\"2014-11-14\",\"R7\":\"0\",\"LastLoginTime\":\"10:02:40\",\"SellerDistanceNum\":10.373209862740024,\"R4\":\"0\",\"R5\":\"����\",\"R2\":\"\",\"LinkPerson\":\"\",\"R3\":\"0\",\"ShotOrgName\":\"\",\"ServiceFeature\":\"\",\"RidingRoute\":\"\",\"SellerID\":\"S00000011\",\"SellerName\":\"��8\",\"XY\":\"116.337149|40.008185\",\"RegDate\":\"2014-11-11\",\"R1\":\"\",\"OrgName\":\"\",\"FaxNo\":\"\",\"TelPhone\":\"159\",\"OrgBrief\":\"\",\"LastPass\":\"12\",\"Email\":\"12\",\"Address\":\"\",\"FoundDate\":\"2010-10-10\",\"WebPage\":\"\",\"AuthFlag\":\"N\",\"Remark\":\"\"},{\"Phone\":\"1370\",\"OrgPeople\":\"\",\"SellerDistance\":\"10.36����\",\"PassModDate\":\"2014-11-11\",\"Password\":\"12\",\"R15\":\"\",\"ServiceType\":\"C0020\",\"RegTime\":\"10:05:08\",\"R13\":\"\",\"PassModTime\":\"10:05:08\",\"R14\":\"\",\"R11\":\"\",\"R12\":\"\",\"R10\":\"\",\"R8\":\"\",\"R9\":\"\",\"R6\":\"1\",\"LastLoginDate\":\"2014-11-11\",\"R7\":\"0\",\"LastLoginTime\":\"10:05:08\",\"SellerDistanceNum\":10.360482729069927,\"R4\":\"0\",\"R5\":\"����\",\"R2\":\"\",\"LinkPerson\":\"\",\"R3\":\"0\",\"ShotOrgName\":\"����֮��\",\"ServiceFeature\":\"\",\"RidingRoute\":\"\",\"SellerID\":\"S00000012\",\"SellerName\":\"����\",\"XY\":\"116.337508|40.00918\",\"RegDate\":\"2014-11-11\",\"R1\":\"\",\"OrgName\":\"����֮��\",\"FaxNo\":\"\",\"TelPhone\":\"1590\",\"OrgBrief\":\"\",\"LastPass\":\"12\",\"Email\":\"12\",\"Address\":\"\",\"FoundDate\":\"2010-10-10\",\"WebPage\":\"\",\"AuthFlag\":\"N\",\"Remark\":\"\"},{\"Phone\":\"1371\",\"OrgPeople\":\"\",\"SellerDistance\":\"10.26����\",\"PassModDate\":\"2014-11-11\",\"Password\":\"12\",\"R15\":\"\",\"ServiceType\":\"C0020\",\"RegTime\":\"10:08:44\",\"R13\":\"\",\"PassModTime\":\"10:08:44\",\"R14\":\"\",\"R11\":\"\",\"R12\":\"\",\"R10\":\"\",\"R8\":\"\",\"R9\":\"\",\"R6\":\"1\",\"LastLoginDate\":\"2014-11-11\",\"R7\":\"0\",\"LastLoginTime\":\"10:08:44\",\"SellerDistanceNum\":10.257169689785313,\"R4\":\"0\",\"R5\":\"����\",\"R2\":\"\",\"LinkPerson\":\"\",\"R3\":\"0\",\"ShotOrgName\":\"�ֹ��̳�\",\"ServiceFeature\":\"\",\"RidingRoute\":\"\",\"SellerID\":\"S00000013\",\"SellerName\":\"�ֹ�\",\"XY\":\"116.337688|40.005394\",\"RegDate\":\"2014-11-11\",\"R1\":\"\",\"OrgName\":\"�ֹ��̳�\",\"FaxNo\":\"\",\"TelPhone\":\"1591\",\"OrgBrief\":\"\",\"LastPass\":\"12\",\"Email\":\"12\",\"Address\":\"\",\"FoundDate\":\"2010-10-10\",\"WebPage\":\"\",\"AuthFlag\":\"N\",\"Remark\":\"\"},{\"Phone\":\"1372\",\"OrgPeople\":\"\",\"SellerDistance\":\"10.46����\",\"PassModDate\":\"2014-11-11\",\"Password\":\"12\",\"R15\":\"\",\"ServiceType\":\"C0020\",\"RegTime\":\"10:23:51\",\"R13\":\"\",\"PassModTime\":\"10:23:51\",\"R14\":\"\",\"R11\":\"\",\"R12\":\"\",\"R10\":\"\",\"R8\":\"\",\"R9\":\"\",\"R6\":\"1\",\"LastLoginDate\":\"2014-11-11\",\"R7\":\"0\",\"LastLoginTime\":\"10:23:51\",\"SellerDistanceNum\":10.463790628931546,\"R4\":\"0\",\"R5\":\"����\",\"R2\":\"\",\"LinkPerson\":\"\",\"R3\":\"0\",\"ShotOrgName\":\"\",\"ServiceFeature\":\"\",\"RidingRoute\":\"\",\"SellerID\":\"S00000014\",\"SellerName\":\"�ݳ�\",\"XY\":\"116.337041|40.011666\",\"RegDate\":\"2014-11-11\",\"R1\":\"\",\"OrgName\":\"�ݳ�֮��\",\"FaxNo\":\"\",\"TelPhone\":\"1592\",\"OrgBrief\":\"\",\"LastPass\":\"12\",\"Email\":\"12\",\"Address\":\"\",\"FoundDate\":\"2010-10-10\",\"WebPage\":\"\",\"AuthFlag\":\"N\",\"Remark\":\"\"},{\"Phone\":\"1373\",\"OrgPeople\":\"\",\"SellerDistance\":\"10.95����\",\"PassModDate\":\"2014-11-11\",\"Password\":\"12\",\"R15\":\"\",\"ServiceType\":\"C0020\",\"RegTime\":\"10:27:41\",\"R13\":\"\",\"PassModTime\":\"10:27:41\",\"R14\":\"\",\"R11\":\"\",\"R12\":\"\",\"R10\":\"\",\"R8\":\"\",\"R9\":\"\",\"R6\":\"1\",\"LastLoginDate\":\"2014-11-13\",\"R7\":\"0\",\"LastLoginTime\":\"10:27:41\",\"SellerDistanceNum\":10.945126065285425,\"R4\":\"0\",\"R5\":\"����\",\"R2\":\"\",\"LinkPerson\":\"\",\"R3\":\"0\",\"ShotOrgName\":\"\",\"ServiceFeature\":\"\",\"RidingRoute\":\"\",\"SellerID\":\"S00000015\",\"SellerName\":\"�˼�\",\"XY\":\"116.330321|40.00299\",\"RegDate\":\"2014-11-11\",\"R1\":\"\",\"OrgName\":\"�˼�\",\"FaxNo\":\"\",\"TelPhone\":\"1593\",\"OrgBrief\":\"\",\"LastPass\":\"12\",\"Email\":\"12\",\"Address\":\"\",\"FoundDate\":\"2010-10-10\",\"WebPage\":\"\",\"AuthFlag\":\"N\",\"Remark\":\"\"},{\"Phone\":\"1374\",\"OrgPeople\":\"\",\"SellerDistance\":\"11.84����\",\"PassModDate\":\"2014-11-14\",\"Password\":\"12\",\"R15\":\"\",\"ServiceType\":\"C0020\",\"RegTime\":\"10:30:34\",\"R13\":\"\",\"PassModTime\":\"14:34:19\",\"R14\":\"\",\"R11\":\"\",\"R12\":\"\",\"R10\":\"\",\"R8\":\"\",\"R9\":\"\",\"R6\":\"5\",\"LastLoginDate\":\"2014-11-17\",\"R7\":\"3\",\"LastLoginTime\":\"10:30:34\",\"SellerDistanceNum\":11.83765063034689,\"R4\":\"4\",\"R5\":\"����\",\"R2\":\"\",\"LinkPerson\":\"\",\"R3\":\"4\",\"ShotOrgName\":\"\",\"ServiceFeature\":\"\",\"RidingRoute\":\"\",\"SellerID\":\"S00000016\",\"SellerName\":\"����\",\"XY\":\"116.322093|40.005753\",\"RegDate\":\"2014-11-11\",\"R1\":\"\",\"OrgName\":\"��������\",\"FaxNo\":\"\",\"TelPhone\":\"1594\",\"OrgBrief\":\"\",\"LastPass\":\"12\",\"Email\":\"12\",\"Address\":\"\",\"FoundDate\":\"2010-10-10\",\"WebPage\":\"\",\"AuthFlag\":\"Y\",\"Remark\":\"\"},{\"Phone\":\"1378\",\"OrgPeople\":\"1000��\",\"SellerDistance\":\"10.99����\",\"PassModDate\":\"2014-11-12\",\"Password\":\"12\",\"R15\":\"\",\"ServiceType\":\"C0020\",\"RegTime\":\"10:57:40\",\"R13\":\"\",\"PassModTime\":\"10:57:40\",\"R14\":\"\",\"R11\":\"\",\"R12\":\"\",\"R10\":\"\",\"R8\":\"\",\"R9\":\"\",\"R6\":\"3\",\"LastLoginDate\":\"2014-11-16\",\"R7\":\"1\",\"LastLoginTime\":\"10:57:40\",\"SellerDistanceNum\":10.988378966053586,\"R4\":\"3\",\"R5\":\"����\",\"R2\":\"\",\"LinkPerson\":\"СǮ\",\"R3\":\"4\",\"ShotOrgName\":\"�ֶ���\",\"ServiceFeature\":\"Ϊ����߽�Լÿһ��Ǯ��\",\"RidingRoute\":\"\",\"SellerID\":\"S00000019\",\"SellerName\":\"�ֶ���\",\"XY\":\"116.330896|40.007853\",\"RegDate\":\"2014-11-12\",\"R1\":\"\",\"OrgName\":\"����������ֶ���\",\"FaxNo\":\"\",\"TelPhone\":\"1598\",\"OrgBrief\":\"�ٻ����С�\",\"LastPass\":\"12\",\"Email\":\"12\",\"Address\":\"�������������\",\"FoundDate\":\"2010-10-10\",\"WebPage\":\"www.baid.com\",\"AuthFlag\":\"Y\",\"Remark\":\"134·\"}]");
//
//	     for(int i = 0;i<ja.size();i++){
//	         System.out.println(ja.getJSONObject(i).get("SellerDistance").toString());
//	     }
//		ja=new ActionBL().sortJSONArray(ja);
//        System.out.println("----------");
//	     for(int i = 0;i<ja.size();i++){
//	         System.out.println(ja.getJSONObject(i).get("SellerDistance").toString());
//	     }
//	     ja=new ActionBL().sortJSONArray(ja);
//	     System.out.println("----------");
//	     for(int i = 0;i<ja.size();i++){
//	    	 System.out.println(ja.getJSONObject(i).get("SellerDistance").toString());
//	     }
//	     ja=new ActionBL().sortJSONArray(ja);
//	     System.out.println("----------");
//	     for(int i = 0;i<ja.size();i++){
//	    	 System.out.println(ja.getJSONObject(i).get("SellerDistance").toString());
//	     }
//	     ja=new ActionBL().sortJSONArray(ja);
//	     System.out.println("----------");
//	     for(int i = 0;i<ja.size();i++){
//	    	 System.out.println(ja.getJSONObject(i).get("SellerDistance").toString());
//	     }
//	     ja=new ActionBL().sortJSONArray(ja);
//	     System.out.println("----------");
//	     for(int i = 0;i<ja.size();i++){
//	    	 System.out.println(ja.getJSONObject(i).get("SellerDistance").toString());
//	     }
//	     ja=new ActionBL().sortJSONArray(ja);
//	     System.out.println("----------");
//	     for(int i = 0;i<ja.size();i++){
//	    	 System.out.println(ja.getJSONObject(i).get("SellerDistance").toString());
//	     }
//	     ja=new ActionBL().sortJSONArray(ja);
//	     System.out.println("----------");
//	     for(int i = 0;i<ja.size();i++){
//	    	 System.out.println(ja.getJSONObject(i).get("SellerDistance").toString());
//	     }
//	     ja=new ActionBL().sortJSONArray(ja);
//	     System.out.println("----------");
//	     for(int i = 0;i<ja.size();i++){
//	    	 System.out.println(ja.getJSONObject(i).get("SellerDistance").toString());
//	     }
//	     ja=new ActionBL().sortJSONArray(ja);
//	     System.out.println("----------");
//	     for(int i = 0;i<ja.size();i++){
//	    	 System.out.println(ja.getJSONObject(i).get("SellerDistance").toString());
//	     }
//		ja=new ActionBL().sortJSONArray(ja);
//        System.out.println("----------");
//	     for(int i = 0;i<ja.size();i++){
//	         System.out.println(ja.getJSONObject(i).get("SellerDistance").toString());
//	     }
		
		long x=new ActionBL().getCT("2014-01-01 00:04:00", "2014-01-01 00:03:09");
		System.out.println(x);
	}
}
