<style type="text/css">
<!--
.style4 {color: #FF0000}
.style5 {
    font-weight: bold;
    font-size: 36px;
    color: #CC3333;
    font-family: "方正舒体", "华文隶书", "隶书";
}
-->
</style>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sinosoft.lis.db.*"%>
<%@page import="com.sinosoft.lis.encrypt.*"%>
<%@page import="com.sinosoft.lis.logon.*"%>
<%@page import="com.sinosoft.lis.menumang.*"%>
<%@page import="com.sinosoft.lis.pubfun.*"%>
<%@page import="com.sinosoft.lis.schema.*"%>
<%@page import="com.sinosoft.utility.*"%>
<%@page import="com.sinosoft.lis.log.*"%>

<%
boolean bSuccess = false;
String sErrorMsg = new String("");
//用户名称和密码
String UserCode = request.getParameter("UserCode");

if( UserCode != null && !UserCode.equals("") ) {
  UserCode = UserCode.trim();
}
String Password = request.getParameter("PWD");
LisIDEA tIdea = new LisIDEA();
Password = tIdea.encryptString(Password);

//用用户最高机构登陆
String sSQL = "select comcode from lduser where usercode = '"+UserCode+"'";
ExeSQL tExeSQL = new ExeSQL();
String StationCode = tExeSQL.getOneValue(sSQL);
//String StationCode = request.getParameter("StationCode");
String ClientURL = request.getParameter("ClientURL");  

//用户IP
String Ip = request.getRemoteAddr();
String ls;  //返回的字符串
if (Password.length() == 0 || UserCode.length() == 0)
{
log.addlog("bSuccess失败了！");
    bSuccess = false;
}
else
{
    VData tVData = new VData();
    LDUserSchema tLDUserSchema = new LDUserSchema();
    tLDUserSchema.setUserCode(UserCode);
    tLDUserSchema.setPassword(Password);
    tLDUserSchema.setComCode(StationCode);
    tVData.add(tLDUserSchema);
    LDUserUI tLDUserUI = new LDUserUI();
    log.addlog("提交后台");
    bSuccess=tLDUserUI.submitData(tVData,"query");
     log.addlog("提交后台返回");
    //xxx added on 2006-09-04
    if (!bSuccess)
    {
        sErrorMsg = tLDUserUI.mErrors.getFirstError();
    }
}

String SQL1 = "select username from lduser where usercode = '"+UserCode+"'";
ExeSQL es = new ExeSQL();
String Operator = es.getOneValue(SQL1);
System.out.println(UserCode+"|"+Operator);

if("".equals(Operator)||"null".equals(Operator)||Operator==null)
{
	Operator=UserCode;
	UserCode = es.getOneValue("select usercode from lduser where username = '"+Operator+"'");
}

String title = Operator + "您好，欢迎登录本系统！";
GlobalInput tG = new GlobalInput();
tG.Operator = UserCode;
tG.ComCode  = StationCode;
tG.ManageCom = StationCode;
session.putValue("GI",tG);
session.putValue("ClientURL",ClientURL);    //LQ 2004-04-19
GlobalInput tG1 = new GlobalInput();
tG1=(GlobalInput)session.getValue("GI");
if(bSuccess == true)
{
System.out.println("---"+UserCode+"|"+Ip);
%>
<script language=javascript>
if(parent.fraMain.rows == "0,0,0,0,*")
    parent.document.frames('fraTitle').showTitle();
if(parent.fraSet.cols== "0%,*,0%")
    parent.document.frames('fraTitle').showHideFrame();
parent.fraMenu.window.location="./menu2.jsp?userCode=<%=UserCode%>&Ip=<%=Ip%>";

parent.fraInterface.window.location.href='../whatsnew/whatsnew.jsp';

</script>
<%
}
else
{
    session.putValue("GI",null);
%>
<script language=javascript>
alert("用户名/密码不正确！\n\n可能的原因是：" + "<%=sErrorMsg%>");
parent.window.location ="../index.jsp";
</script>
<%
}
%>

<script src="../common/javascript/Common.js"></script>
<script src="../common/cvar/CCodeOperate.js"></script>
<script src="../common/javascript/EasyQuery.js"></script>
<script src="../common/easyQueryVer3/EasyQueryVer3.js"></script>
<body>


<form name="fm">
    <input type="hidden" name="VirtualCode" value="0" />
    <input type="hidden" name="VirtualName" value="" />

</form>
</body>
