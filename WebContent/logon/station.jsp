<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sinosoft.lis.logon.*"%>
<%@page import="com.sinosoft.lis.pubfun.*"%>
<%@page import="com.sinosoft.utility.*"%>
<%@page import="com.sinosoft.lis.log.*"%>
<%
String nodecode = request.getParameter("nodecode");
String Ip = request.getParameter("Ip");
String ls="";
String strFlag = "00";

GlobalInput tG = (GlobalInput)session.getValue("GI");
String ManageCom = tG.ManageCom;

String uname = new ExeSQL().getOneValue("select username from lduser where usercode = '"+tG.Operator+"'");
if ((nodecode==null) || (nodecode.trim().equals("")))
{
    ls = "现在的位置：首页";
}
else
{
    if (nodecode.equals("60001"))
    {
        ls = "现在的位置：密码修改";
    }
    else
    {
        MenuShow menuShow = new MenuShow();
		ls = menuShow.getStation(nodecode);
        ls = ls.substring(0,ls.length()-3);
		System.out.println(">>"+ls);
    }
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript">
function showmenu()
{
    if(parent.fraSet.cols=="0%,*,0%")
    {
        document.all("menushow").style.display = "none";
        parent.fraSet.cols = "180,*,0%";
    }
}

if ( "01" == "<%= strFlag %>" )
{
  alert("发现错误的菜单节点，请与系统维护人员联系。");
}

</script>
<link rel='stylesheet' type='text/css' href='../common/css/other.css'>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginhigh="0">
    <table width=100% height="25" cellspacing="0">
        <tr>
            <th width="10%" align="left" id=menushow style="display:none" name="menushow">
                &nbsp;&nbsp;
                <a href="#" onclick="showmenu()"><img src="../common/images/t_open.gif" width="70" height="13" border="0" title="显示菜单栏"></a>
            </th>
            <th width="40%" align="left">&nbsp;&nbsp;<font style="font-size:9pt" color="#FFFFFF">登录用户：<%=uname+"("+tG.Operator+")"%></font></th>
            <th width="50%" align="right"><font style="font-size:9pt" color="#FFFFFF"><%=ls%>&nbsp;</th>
        </tr>
    </table>
</body>
</html>
