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

<title>栏目信息</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table>
	<tr>
		<td class=common></td>
		<td class=titleImg>栏目信息</td>
	</tr>
</table>
<table class=common border=0>
	<TR class=common>
		<td class=title>名称</td>
		<td class=input><Input class="common" name=ChannelName>
		<Input class="common" name=ParentChannel value="<%=request.getParameter("parent")%>" type=hidden>
		<Input class="common" name=SerialNo value="<%=request.getParameter("sno")%>" type=hidden>
		<Input class="common" name=ChannelLevel value="<%=request.getParameter("level")%>" type=hidden>
		</td>
	</TR>
	<TR class=common>
		<td class=title>顺序</td>
		<td class=input><Input class="common" name=R1></td>
	</TR>
	<TR class=common>
        <TD class= title>是否显示</TD>
        <td class= input><Input class=codeno name=R2 ondblclick="showCodeList('yesorno',[this,R2Name],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,R2Name],[0,1],null,null,null,true);"><input class=codename name=R2Name readonly=true></td>
	</TR>
</table>
<table class=common border=0 id="subTable1">
	<TR class=common>
        <TD class= title>展示类型</TD>
        <td class= input><Input class=codeno name=R3 ondblclick="showCodeList('chnltype',[this,R3Name],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('chnltype',[this,R3Name],[0,1],null,null,null,true);"><input class=codename name=R3Name readonly=true></td>
	</TR>
</table>
<table class=common border=0 id="subTable2">
	<TR class=common>
        <TD class= title>发布权限</TD>
        <td class= input></td>
	</TR>
	<TR class=common>
        <TD class= title>管理员</TD>
        <td class= input><Input value=N class=codeno name=R4 ondblclick="showCodeList('yesorno',[this,R4Name],[0,1],null,null,null,true);" 
        onkeyup="return showCodeListKey('yesorno',[this,R4Name],[0,1],null,null,null,true);"><input class=codename name=R4Name readonly=true></td>
	</TR>
	<TR class=common>
        <TD class= title>认证商家</TD>
        <td class= input><Input value=N class=codeno name=R5 ondblclick="showCodeList('yesorno',[this,R5Name],[0,1],null,null,null,true);" 
        onkeyup="return showCodeListKey('yesorno',[this,R4Name],[0,1],null,null,null,true);"><input class=codename name=R5Name readonly=true></td>
	</TR>
	<TR class=common>
        <TD class= title>非认证商家</TD>
        <td class= input><Input value=N class=codeno name=R6 ondblclick="showCodeList('yesorno',[this,R6Name],[0,1],null,null,null,true);" 
        onkeyup="return showCodeListKey('yesorno',[this,R4Name],[0,1],null,null,null,true);"><input class=codename name=R6Name readonly=true></td>
	</TR>
	<TR class=common>
        <TD class= title>社团用户</TD>
        <td class= input><Input value=N class=codeno name=R7 ondblclick="showCodeList('yesorno',[this,R7Name],[0,1],null,null,null,true);" 
        onkeyup="return showCodeListKey('yesorno',[this,R4Name],[0,1],null,null,null,true);"><input class=codename name=R7Name readonly=true></td>
	</TR>
	<TR class=common>
        <TD class= title>个人用户</TD>
        <td class= input><Input value=N class=codeno name=R8 ondblclick="showCodeList('yesorno',[this,R8Name],[0,1],null,null,null,true);" 
        onkeyup="return showCodeListKey('yesorno',[this,R4Name],[0,1],null,null,null,true);"><input class=codename name=R8Name readonly=true></td>
	</TR>
</table>
<table class=common border=0 id="subTable3">
	<TR class=common>
		<td class=title>链接</td>
		<td class=input><Input class="common" name=URL></td>
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
	if(fm.ParentChannel.value=='0')
	{
		subTable1.style.display="none";
		subTable2.style.display="none";
		subTable3.style.display="none";
	}
	else
	{
		subTable2.style.display="none";
		subTable3.style.display="none";
	}
	queryData();
}
function afterCodeSelect(a,b)
{
	if(a=='chnltype')
	{
		if(b.value!='3')
		{
			subTable2.style.display="none";
			subTable3.style.display="";
		}
		else
		{
			subTable2.style.display="";
			subTable3.style.display="none";
		}
	}	
}
function queryData()
{
	var sno = fm.SerialNo.value;
	if(sno!=''&&sno!=null&&sno!='null')
	{
		var SQL = "select * from sdchannel where serialno='"+sno+"'";
		var arr = easyExecSql(SQL, 1, 0);
		if(arr)
		{
			fm.SerialNo.value=arr[0][0];
			fm.ChannelName.value=arr[0][1];
			fm.ParentChannel.value=arr[0][2];
			fm.ChannelLevel.value=arr[0][3];
			fm.URL.value=arr[0][4];
			fm.R1.value=arr[0][5];
			fm.R2.value=arr[0][6];
			fm.R3.value=arr[0][7];
			fm.R4.value=arr[0][8];
			fm.R5.value=arr[0][9];
			fm.R6.value=arr[0][10];
			fm.R7.value=arr[0][11];
			fm.R8.value=arr[0][12];
			showOneCodeName('yesorno',fm.R2,fm.R2Name);
			showOneCodeName('chnltype',fm.R3,fm.R3Name);
			showOneCodeName('yesorno',fm.R4,fm.R4Name);
			showOneCodeName('yesorno',fm.R5,fm.R5Name);
			showOneCodeName('yesorno',fm.R6,fm.R6Name);
			showOneCodeName('yesorno',fm.R7,fm.R7Name);
			showOneCodeName('yesorno',fm.R8,fm.R8Name);
			if(fm.ParentChannel.value!='0')
			{
				afterCodeSelect('chnltype',fm.R3);
			}
		}
	}
}
function save()
{
	if(fm.ChannelName.value=='')
	{
		alert('请录入名称');	
		return;
	}
	if(fm.R1.value=='')
	{
		alert('请录入顺序');	
		return;
	}
	if(fm.R2.value=='')
	{
		alert('请选择是否显示');	
		return;
	}
	if(fm.R2.value=='')
	{
		alert('请选择信息发布权限');	
		return;
	}
	fm.action="./pa.jsp?a=chnlmgt&o=save";
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