<html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="../common/jsp/UsrCheck.jsp"%>
<%@page import="com.sinosoft.lis.pubfun.*"%>
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

<title>建议信息</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table class=common border=0>
	<TR class=common>
		<td class=title>姓名或昵称</td>
		<td class=input><Input class="common" name=NickName readonly>
		<Input class="common" name=SerialNo value="<%=request.getParameter("sno")%>" type=hidden></td>
		<td class=title>手机</td>
		<td class=input><Input class="common" name=Phone readonly></td>
		<td class=title>社区</td>
		<td class=input><Input class="common" name=Com readonly></td>
	</TR>
	<TR class=common>
		<td class=title>标题</td>
		<td class=input><Input class="common" name=Title readonly></td>
		<td class=title>建议日期</td>
		<td class=input><Input class="common" name=SuggestDate readonly></td>
		<td class=title>建议时间</td>
		<td class=input><Input class="common" name=SuggestTime readonly></td>
	</TR>
	<TR class=common>
		<td class=title>用户类型</td>
		<td class=input><Input class="common" name=UserType readonly></td>
		<td class=title></td>
		<td class=input></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
	<TR class=common>
		<td class=title>建议内容</td>
		<td class=input colspan="5">
        				<textarea name=Content  cols="100%" rows="5" witdh=100% class="common">
        				</textarea>
		</td>
	</TR>
	<TR class=common>
		<td class=title>处理标记</td>
		<td class=input><Input class=codeno name=DealFlag ondblclick="showCodeList('yesorno',[this,DealFlagName],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,DealFlagName],[0,1],null,null,null,true);"><input class=codename name=DealFlagName readonly=true></td>
		<td class=title>处理日期</td>
		<td class=input><Input class="common" name=R1 readonly value="<%=PubFun.getCurrentDate()%>"></td>
		<td class=title>处理时间</td>
		<td class=input><Input class="common" name=R2 readonly value="<%=PubFun.getCurrentTime()%>"></td>
	</TR>
	<TR class=common>
		<td class=title>备注</td>
		<td class=input colspan="5">
        				<textarea name=Remark  cols="100%" rows="5" witdh=100% class="common">
        				</textarea>
		</td>
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
	var vno = fm.SerialNo.value;
	if(vno!=''&&vno!=null&&vno!='null')
	{
		var SQL = "select * from sdsuggest where SerialNo='"+vno+"'";
		var arr = easyExecSql(SQL, 1, 0);
		if(arr)
		{
			fm.SerialNo.value=arr[0][0];
			fm.UserType.value=arr[0][1];
			fm.NickName.value=arr[0][2];
			fm.Title.value=arr[0][3];
			fm.Phone.value=arr[0][4];
			fm.Com.value=arr[0][5];
			fm.Content.value=arr[0][6];
			fm.SuggestDate.value=arr[0][7];
			fm.SuggestTime.value=arr[0][8];
			fm.DealFlag.value=arr[0][9];
			fm.Remark.value=arr[0][10];
			fm.R1.value=arr[0][11];
			fm.R2.value=arr[0][12];
			
		}
	}
}
function save()
{

	fm.action="./pa.jsp?a=sugmgt&o=save";
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