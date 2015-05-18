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

<title>商家信息</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table class=common border=0>
	<TR class=common>
		<td class=title>商户编码</td>
		<td class=input><Input class="common" name=SellerID value="<%=request.getParameter("uno")%>" readonly></td>
		<td class=title>用户名</td>
		<td class=input><Input class="common" name=SellerName readonly></td>
		<td class=title>机构全称</td>
		<td class=input><Input class="common" name=OrgName readonly></td>
	</TR>
	<TR class=common>
		<td class=title>地址</td>
		<td class=input><Input class="common" name=Address readonly></td>
		<td class=title>电子邮件</td>
		<td class=input><Input class="common" name=Email readonly></td>
		<td class=title>手机</td>
		<td class=input><Input class="common" name=Phone readonly></td>
	</TR>
	<TR class=common>
		<td class=title>服务特色</td>
		<td class=input><Input class="common" name=ServiceFeature readonly></td>
		<td class=title>机构简称</td>
		<td class=input><Input class="common" name=ShotOrgName readonly></td>
		<td class=title>成立日期</td>
		<td class=input><Input class="common" name=FoundDate readonly></td>
	</TR>
	<TR class=common>
		<td class=title>服务类别</td>
		<td class=input><Input class="common" name=ServiceType readonly></td>
		<td class=title>机构人数</td>
		<td class=input><Input class="common" name=OrgPeople readonly></td>
		<td class=title>咨询电话</td>
		<td class=input><Input class="common" name=TelPhone readonly></td>
	</TR>
	<TR class=common>
		<td class=title>网址</td>
		<td class=input><Input class="common" name=WebPage readonly></td>
		<td class=title>传真</td>
		<td class=input><Input class="common" name=FaxNo readonly></td>
		<td class=title>乘车路线</td>
		<td class=input><Input class="common" name=RidingRoute readonly></td>
	</TR>
	<TR class=common>
		<td class=title>注册日期</td>
		<td class=input><Input class="common" name=RegDate readonly></td>
		<td class=title>注册时间</td>
		<td class=input><Input class="common" name=RegTime readonly></td>
		<td class=title>联系人</td>
		<td class=input><Input class="common" name=LinkPerson readonly></td>
	</TR>
	<TR class=common>
		<td class=title>最后登陆日期</td>
		<td class=input><Input class="common" name=LastLoginDate readonly></td>
		<td class=title>最后登录时间</td>
		<td class=input><Input class="common" name=LastLoginTime readonly></td>
		<td class=title>是否暂停</td>
		<td class=input><Input class=codeno name=R9 ondblclick="showCodeList('yesorno',[this,R9Name],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,R9Name],[0,1],null,null,null,true);"><input class=codename name=R9Name readonly=true></td>
	</TR>
	<TR class=common>
		<td class=title>服务机构简介</td>
		<td class=input colspan="5">
        				<textarea name=OrgBrief  cols="100%" rows="5" witdh=100% class="common" readonly>
        				</textarea>
		</td>
	</TR>
	<TR class=common style="display:none">
		<td class=title>其他说明</td>
		<td class=input colspan="5">
        				<textarea name=Remark  cols="100%" rows="5" witdh=100% class="common" readonly>
        				</textarea>
		</td>
	</TR>
	<TR class=common>
		<td class=title>是否认证</td>
		<td class=input><Input class=codeno name=AuthFlag ondblclick="showCodeList('yesorno',[this,AuthFlagName],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,AuthFlagName],[0,1],null,null,null,true);"><input class=codename name=AuthFlagName readonly=true></td>
		<td class=title>密码</td>
		<td class=input><Input type=password class="common" name=Password></td>
		<td class=title>确认密码</td>
		<td class=input><Input type=password class="common" name=pass2></td>
	</TR>
</table>
<INPUT class=cssButton TYPE=button VALUE=" 保 存 "
	onclick="save();"> <INPUT class=cssButton
	TYPE=button VALUE=" 返 回 " onclick="top.close();">
<br><br>
<INPUT class=cssButton TYPE=button VALUE="查看评价"
	onclick="viewassess();">
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
	var vno = fm.SellerID.value;
	if(vno!=''&&vno!=null&&vno!='null')
	{
		var SQL = "select * from sdseller where sellerid='"+vno+"'";
		var arr = easyExecSql(SQL, 1, 0);
		if(arr)
		{
			fm.SellerID.value=arr[0][0];
			fm.Password.value=arr[0][1];
			fm.pass2.value=arr[0][1];
			fm.SellerName.value=arr[0][2];
			fm.OrgName.value=arr[0][3];
			fm.Email.value=arr[0][4];
			fm.Phone.value=arr[0][5];
			fm.AuthFlag.value=arr[0][6];
			fm.ShotOrgName.value=arr[0][8];
			fm.FoundDate.value=arr[0][9];
			fm.ServiceType.value=arr[0][10];
			fm.Address.value=arr[0][11];
			fm.ServiceFeature.value=arr[0][12];
			fm.OrgPeople.value=arr[0][13];
			fm.TelPhone.value=arr[0][14];
			fm.WebPage.value=arr[0][15];
			fm.LinkPerson.value=arr[0][16];
			fm.FaxNo.value=arr[0][17];
			fm.RidingRoute.value=arr[0][18];
			fm.Remark.value=arr[0][19];
			fm.OrgBrief.value=arr[0][20];
			fm.RegDate.value=arr[0][21];
			fm.RegTime.value=arr[0][22];
			fm.LastLoginDate.value=arr[0][23];
			fm.LastLoginTime.value=arr[0][24];
			fm.R9.value=arr[0][36];
			//服务类别
			fm.ServiceType.value=easyExecSql("select a.channelname||'>'||b.channelname from sdchannel a,sdchannel b where a.serialno=b.parentchannel and b.serialno='"+fm.ServiceType.value+"'", 1, 0);
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
	fm.action="./pa.jsp?a=sellermgt&o=save";
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