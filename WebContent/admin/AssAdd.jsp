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

<title>社团信息</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table class=common border=0>
	<TR class=common>
		<td class=title>社团编码</td>
		<td class=input><Input class="common" name=AssID value="<%=request.getParameter("ano")%>" readonly></td>
		<td class=title>社团名称</td>
		<td class=input><Input class="common" name=OrgName></td>
		<td class=title>社团简称</td>
		<td class=input><Input class="common" name=ShotOrgName></td>
	</TR>
	<TR class=common>
		<td class=title>地址</td>
		<td class=input><Input class="common" name=Address></td>
		<td class=title>电子邮件</td>
		<td class=input><Input class="common" name=Email></td>
		<td class=title>手机</td>
		<td class=input><Input class="common" name=Phone></td>
	</TR>
	<TR class=common>
		<td class=title>联系人</td>
		<td class=input><Input class="common" name=LinkPerson></td>
		<td class=title>社团人数</td>
		<td class=input><Input class="common" name=AssPeople></td>
		<td class=title>社团特色</td>
		<td class=input><Input class="common" name=AssFeature></td>
	</TR>
	<TR class=common>
		<td class=title>网址</td>
		<td class=input><Input class="common" name=WebPage></td>
		<td class=title>注册日期</td>
		<td class=input><Input class="common" name=RegDate readonly></td>
		<td class=title>注册时间</td>
		<td class=input><Input class="common" name=RegTime readonly></td>
	</TR>
	<TR class=common>
		<td class=title>社团类型</td>
		<td class=input><Input class="common" name=AssType></td>
		<td class=title>最后登陆日期</td>
		<td class=input><Input class="common" name=LastLoginDate readonly></td>
		<td class=title>最后登录时间</td>
		<td class=input><Input class="common" name=LastLoginTime readonly></td>
	</TR>
	<TR class=common>
		<td class=title></td>
		<td class=input></td>
		<td class=title></td>
		<td class=input></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
	<TR class=common><td class=title>密码</td>
		<td class=input><Input type=password class="common" name=Password></td>
		<td class=title>确认密码</td>
		<td class=input><Input type=password class="common" name=pass2></td>		
		<td class=title></td>
		<td class=input></td>
		
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
	var vno = fm.AssID.value;
	if(vno!=''&&vno!=null&&vno!='null')
	{
		var SQL = "select * from sdass where assid='"+vno+"'";
		var arr = easyExecSql(SQL, 1, 0);
		if(arr)
		{
			fm.AssID.value=arr[0][0];
			fm.Password.value=arr[0][1];
			fm.pass2.value=arr[0][1];
			fm.ShotOrgName.value=arr[0][3];
			fm.OrgName.value=arr[0][4];
			fm.Email.value=arr[0][5];
			fm.Phone.value=arr[0][6];
			fm.AssPeople.value=arr[0][7];
			fm.AssFeature.value=arr[0][8];
			fm.AssType.value=arr[0][9];
			fm.WebPage.value=arr[0][10];
			fm.Address.value=arr[0][11];
			fm.LinkPerson.value=arr[0][12];
			fm.RegDate.value=arr[0][13];
			fm.RegTime.value=arr[0][14];
			fm.LastLoginDate.value=arr[0][15];
			fm.LastLoginTime.value=arr[0][16];
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
	fm.action="./pa.jsp?a=assmgt&o=save";
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