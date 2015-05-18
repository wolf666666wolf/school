<!-- 登陆页面 -->
<%@page contentType='text/html;charset=UTF-8' %>
<%@page import="com.sinosoft.lis.pubfun.*"%>
<%@page import="com.sinosoft.lis.admin.*"%>
<%@page import="com.sinosoft.lis.web.*"%>
<%@page import="com.sinosoft.utility.*"%>
<%
//new CrawlerBL().crawler();
//DC dc = new DC();
//dc.tran();
%>
<html>
<head>
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../common/javascript/Common.js"></script>
<SCRIPT src="../common/cvar/CCodeOperate.js"></SCRIPT>
<link rel='stylesheet' type='text/css' href='../common/css/other.css'>
<script language=javascript>
function submitForm(){
if (!achieveInit()) return false;
	if(fm.UserCode.value.length == 0){
		alert("请输入用户名.");
		return false;
	}
	if(fm.PWD.value.length == 0){
		alert("请输入密码.");
		return false;
	}
	fm.ClientURL.value = document.location;
	fm.submit();
	return true;
}

function achieveInit() {
	try {
		var tVD = top.achieveVD;
		var tEX = top.achieveEX;

		if (!(tVD && tEX) || typeof(mCs) == "undefined") {

			//alert("tVD:" + tVD + "\ntEX:" + tEX + "\nmCs:" + typeof(mCs));
			top.window.location = "../index.jsp";
			alert("页面初始化未完成，请等待！");
			return false;
		}
	}
	catch(ex) {
		alert("页面初始化未错误!\ntop.window.location = '../index.jsp'");
		return false;
	}
	return true;
}

function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}

MM_reloadPage(true);

function exprint()
{
var keycode = event.keyCode;
if (keycode == "13") 
{
 submitForm();
 }
}
  
</script>
<style type="text/css">
#uc{
    padding-top:290px;
    padding-left:392px;    
    }
#up{
    padding-top:10px;
    padding-left:392px;    
    }
#ub
{
position:absolute;
cursor:hand;
width:103px;
height:38px;
    top:393px;
    left:562px;    
}
#main
{
background: url(../common/images/logon.jpg) no-repeat;
position:absolute;
width:1000px;
height:656px;
}
</style>
</head>
<HTML>
<HEAD>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<bodyleftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onkeypress = "exprint();">
<!-- ImageReady Slices (Login_I.jpeg) -->
<form name="fm" action="./LogonSubmit.jsp" method="post">
<div id="main" style="color:black;">
本系统需要使用IE浏览器打开，如使用IE8及以上版本，请打开浏览器的兼容性视图模式。具体操作方法请参考<a target="_blank" href="http://baike.baidu.com/link?url=9QEIs3iPvlyBbbob0FQmhsri7c5_NA1rDFAuos02t0Ih_ihMw_xYUWK5x1wYD33lRWYU3nGkQn9UHma4k4OTBq#1">《兼容性视图操作方法》</a>
<div id="uc">
<input name=UserCode class="common2" type="text" id="UserCode" size="18" maxlength="18" value="">                         
</div>
<div id="up">
<input name=PWD class="common2" type="Password" id="PWD2" size="18" maxlength="18" value="">
</div>     
<div id="ub"onClick="return submitForm();">
</div>  
</div>
                    
<input TYPE="hidden" name="ClientURL" value="">

		<span id="spanCode"  style="display: none; position:absolute; slategray; left: 736px; top: 264px; width: 229px; height: 44px;">
        </span>
</form>
</body>
</html>

<!-- xxx added on 2006-05-13 : 自动设置用户名为焦点 -->
<script language="JavaScript">
    try
	{
		document.getElementsByName("UserCode")[0].focus();
    }
    catch (ex) {}
</script>