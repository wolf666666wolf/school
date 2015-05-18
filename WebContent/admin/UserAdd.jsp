<html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="../common/jsp/UsrCheck.jsp"%>
<script>
    var operator = "<%=tGI.Operator%>";   //记录操作员
    var manageCom = "<%=tGI.ManageCom%>"; //记录登陆机构
    var comcode = "<%=tGI.ComCode%>";//记录登陆机构
</script>
<head>
<SCRIPT src="../common/javascript/Common.js"></SCRIPT>
<SCRIPT src="../common/cvar/CCodeOperate.js"></SCRIPT>
<SCRIPT src="../common/javascript/MulLine.js"></SCRIPT>
<SCRIPT src="../common/easyQueryVer3/EasyQueryVer3.js"></SCRIPT>
<SCRIPT src="../common/easyQueryVer3/EasyQueryCache.js"></SCRIPT>
<SCRIPT src="../common/Calendar/Calendar.js"></SCRIPT>
<LINK href="../common/css/Project.css" rel=stylesheet type=text/css>
<LINK href="../common/css/mulLine.css" rel=stylesheet type=text/css>

<title>用户信息</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table class=common border=0>
	<TR class=common>
		<td class=title>用户编码</td>
		<td class=input><Input class="common" name=UserID value="<%=request.getParameter("uno")%>" readonly></td>
		<td class=title>用户名</td>
		<td class=input><Input class="common" name=UserName></td>
		<td class=title>姓名或昵称</td>
		<td class=input><Input class="common" name=NickName></td>
	</TR>
	<TR class=common>
		<td class=title>地址</td>
		<td class=input><Input class="common" name=Address></td>
		<td class=title>电子邮件</td>
		<td class=input><Input class="common" name=Email></td>
		<td class=title>手机</td>
		<td class=input><Input class="common" name=Phone readonly></td>
	</TR>
	<TR class=common>
		<td class=title>教工号</td>
		<td class=input><Input class="common" name=TNo></td>
		<td class=title>证件号</td>
		<td class=input><Input class="common" name=IDNo></td>
		<td class=title>家中特殊人群</td>
		<td class=input><Input class="common" name=Special></td>
	</TR>
	<TR class=common>
		<td class=title>注册日期</td>
		<td class=input><Input class="common" name=RegDate readonly></td>
		<td class=title>注册时间</td>
		<td class=input><Input class="common" name=RegTime readonly></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
	<TR class=common>
		<td class=title>最后登陆日期</td>
		<td class=input><Input class="common" name=LastLoginDate readonly></td>
		<td class=title>最后登录时间</td>
		<td class=input><Input class="common" name=LastLoginTime readonly></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
	<TR class=common>
		<td class=title></td>
		<td class=input></td>
		<td class=title></td>
		<td class=input></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
	<TR class=common>
		<td class=title style="display:none;">是否认证</td>
		<td class=input style="display:none;"><Input class=codeno name=AuthFlag ondblclick="showCodeList('yesorno',[this,AuthFlagName],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,AuthFlagName],[0,1],null,null,null,true);"><input class=codename name=AuthFlagName readonly=true></td>
		<td class=title>密码</td>
		<td class=input><Input type=password class="common" name=Password></td>
		<td class=title>确认密码</td>
		<td class=input><Input type=password class="common" name=pass2></td>
	</TR>
</table>
<INPUT class=cssButton TYPE=button VALUE=" 保 存 "
	onclick="save();"> <INPUT class=cssButton
	TYPE=button VALUE=" 返 回 " onclick="top.close();">

	<span id="spanCode" style="display: none; position: absolute;"></span>
</form>

</body>
</html>
<script>
var turnPage = new turnPageClass();  
function initForm()
{
	queryData();
}
function queryData()
{
	var vno = fm.UserID.value;
	if(vno!=''&&vno!=null&&vno!='null')
	{
		var SQL = "select * from sduser where userid='"+vno+"'";
		var arr = easyExecSql(SQL, 1, 0);
		if(arr)
		{
			fm.UserID.value=arr[0][0];
			fm.UserName.value=arr[0][2];
			fm.NickName.value=arr[0][3];
			fm.Email.value=arr[0][4];
			fm.Phone.value=arr[0][5];
			fm.AuthFlag.value=arr[0][6];
			fm.TNo.value=arr[0][7];
			fm.IDNo.value=arr[0][8];
			fm.Address.value=arr[0][9];
			fm.Special.value=arr[0][10];
			fm.RegDate.value=arr[0][11];
			fm.RegTime.value=arr[0][12];
			fm.LastLoginDate.value=arr[0][13];
			fm.LastLoginTime.value=arr[0][14];
			fm.Password.value=arr[0][1];
			fm.pass2.value=arr[0][1];
		}
	}
}
function save()
{
	if(fm.Password.value=='')
	{
		alert('请录入密码');	
	}
	if(fm.pass2.value=='')
	{
		alert('请录入确认密码');	
	}
	if(fm.Password.value!=fm.pass2.value)
	{
		alert('两次输入密码不一致');	
	}
	fm.action="./pa.jsp?a=usermgt&o=save";
	fm.submit();
}
function afterSubmit(a,b)
{
	alert(b);
	if(a=="Succ")
	{
		top.close();
	}
}

</script>