<%@page contentType="text/html;charset=UTF-8" %>
<%
//程序名称：
//程序功能：
//创建日期：2007-7-25 11:10:36
//创建人  ：杨亚峰
//更新记录：  更新人    更新日期     更新原因/内容
%>
<!--用户校验类-->
<%@include file="../common/jsp/UsrCheck.jsp"%>

  <%@page import="com.sinosoft.utility.*"%>
  <%@page import="com.sinosoft.lis.schema.*"%>
  <%@page import="com.sinosoft.lis.vschema.*"%>
  <%@page import="com.sinosoft.lis.pubfun.*"%>
  <%@page import="com.sinosoft.lis.vdb.*"%>
    <%@page import="com.sinosoft.lis.db.*"%> 
  <%@page import="com.sinosoft.lis.encrypt.*"%>
<%
String Content = "";
String FlagStr = "";

GlobalInput tGlobalInput = new GlobalInput(); 
  tGlobalInput = (GlobalInput)session.getValue("GI");
 String tType = request.getParameter("checkCart");
 System.out.println("::::::tType:::::::::"+tType);
 String tUserCode = request.getParameter("tempUserCode");
  System.out.println("::::::tUserCode:::::::::"+tUserCode);

   StringBuffer t1SBql = new StringBuffer(256);
   t1SBql.append("select * from lduser where usercode = '");
   t1SBql.append(tUserCode);
   t1SBql.append("'");
   String SuperPopedomFlag = "0";
   LDUserDB t1LDUserDB = new LDUserDB();
   LDUserSet m1LDUserSet = new LDUserSet();
   m1LDUserSet = t1LDUserDB.executeQuery(t1SBql.toString());
   if (m1LDUserSet == null || m1LDUserSet.size() == 0)
   {
            
        if(m1LDUserSet.get(1).getLockState() != null && !m1LDUserSet.get(1).getLockState().equals("null")&&
                        !m1LDUserSet.get(1).getLockState().equals("") )
        {
            SuperPopedomFlag = m1LDUserSet.get(1).getSuperPopedomFlag();
        }
   }
        
    
    MMap tMMap = new MMap();
   String tSql = "";
   
  if(tType.equals("1")) //用户密码锁定
  {
  	tSql = "update lduser set lockstate = '0'  where usercode = '"+tUserCode+"'"; 
  }
   
  if(tType.equals("2")) //已建帐户确认
  {
  	 tSql = "update lduser set UseFlag = '2' where usercode = '"+tUserCode+"'";  
  }  
  
  
  if(tType.equals("3")) //过期密码重置
  {
    String currentDate = PubFun.getCurrentDate();
    String endDate = "";
    if(SuperPopedomFlag.equals("1"))
    {
    	endDate = PubFun.calOFDate(currentDate, 30, "D", "");
    }
  	else
  	{
  		endDate = PubFun.calOFDate(currentDate, 60, "D", "");
    }
  	tSql = "update lduser set pwdstartdata  = '"+currentDate+"' , pwdenddata = '"+endDate+"' where  usercode = '"+tUserCode+"' "; 
  }  
        
        tMMap.put(tSql,"UPDATE");

      
        if(!tType.equals(""))
        {
        System.out.println("dafdkad");
        VData tVData = new VData();
        tVData.add(tMMap);
        PubSubmit tPubSubmit  = new PubSubmit();
        if(!tPubSubmit.submitData(tVData,""))
        {
        Content = " 处理失败，没有成功提交";
	  	  FlagStr = "Fail";
        
        }
        else
        {
        	Content = " 处理成功! ";
	  	    FlagStr = "Success";
        }
      }
      else
      {
       Content = " 处理失败，提交信息不全";
	  	  FlagStr = "Fail";
      
      }
        
   System.out.println(Content + "\n" + FlagStr + "\n---WriteToFileSave End---\n\n");


%>                      
<html>
<script language="javascript">
	
		parent.fraInterface.afterSubmit('<%=FlagStr%>', '<%=Content%>');
		parent.fraInterface.initForm();
</script>
</html>
