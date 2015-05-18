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

<title>社团活动信息</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table class=common border=0>
	<TR class=common>
		<td class=title>活动编码</td>
		<td class=input><Input class="common" name=ActID value="<%=request.getParameter("ano")%>" readonly>
		<Input class="common" name=AssID type=hidden></td>
		<td class=title>活动标题</td>
		<td class=input><Input class="common" name=Title readonly></td>
		<td class=title>社团名称</td>
		<td class=input><Input class="common" name=OrgName readonly></td>
	</TR>
	<TR class=common>
		<td class=title>联系人手机</td>
		<td class=input><Input class="common" name=Phone readonly></td>
		<td class=title>缴纳费用</td>
		<td class=input><Input class="common" name=ActFee readonly></td>
		<td class=title>参加人数</td>
		<td class=input><Input class="common" name=AcPeople readonly></td>
	</TR>
	<TR class=common>
		<td class=title>活动规则</td>
		<td class=input colspan="5">
        				<textarea name=ActRule  cols="100%" rows="5" witdh=100% class="common" readonly>
        				</textarea>
		</td>
	</TR>
	<TR class=common>
		<td class=title>活动内容</td>
		<td class=input colspan="5">
        				<textarea name=ActContent  cols="100%" rows="5" witdh=100% class="common" readonly>
        				</textarea>
		</td>
	</TR>
	<TR class=common>
		<td class=title>奖品赠品</td>
		<td class=input colspan="5">
        				<textarea name=ActGift  cols="100%" rows="5" witdh=100% class="common" readonly>
        				</textarea>
		</td>
	</TR>
	<TR class=common>
		<td class=title>开始日期</td>
		<td class=input><Input class="common" name=StartDate></td>
		<td class=title>开始时间</td>
		<td class=input><Input class="common" name=StartTime readonly></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
	<TR class=common>
		<td class=title>结束日期</td>
		<td class=input><Input class="common" name=EndDate readonly></td>
		<td class=title>结束时间</td>
		<td class=input><Input class="common" name=EndTime readonly></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
	<TR class=common>
		<td class=title>报名截止日期</td>
		<td class=input><Input class="common" name=RegEndDate readonly></td>
		<td class=title>报名截止时间</td>
		<td class=input><Input class="common" name=RegEndTime readonly></td>
		<td class=title>活动地点</td>
		<td class=input><Input class="common" name=R3 readonly></td>
	</TR>
	<TR class=common>
		<td class=title>审核状态(是否通过)</td>
		<td class=input><Input class=codeno name=AppStatus ondblclick="showCodeList('yesorno',[this,AppStatusName],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,AppStatusName],[0,1],null,null,null,true);"><input class=codename name=AppStatusName readonly=true></td>
		<td class=title>审核日期</td>
		<td class=input><Input class="common" name=AppDate readonly></td>
		<td class=title>审核时间</td>
		<td class=input><Input class="common" name=AppTime readonly></td>
	</TR>
	<TR class=common>
		<td class=title>审核意见</td>
		<td class=input colspan="5">
        				<textarea name=AppRemark  cols="100%" rows="5" witdh=100% class="common">
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
	var vno = fm.ActID.value;
	if(vno!=''&&vno!=null&&vno!='null')
	{
		var SQL = "select * from sdassactivity where actid='"+vno+"'";
		var arr = easyExecSql(SQL, 1, 0);
		if(arr)
		{
			fm.ActID.value=arr[0][0];
			fm.AssID.value=arr[0][1];
			fm.OrgName.value=easyExecSql("select orgname from sdass where assid='"+fm.AssID.value+"'");
			fm.Phone.value=arr[0][2];
			fm.Title.value=arr[0][3];
			fm.ActFee.value=arr[0][4];
			fm.ActRule.value=arr[0][5];
			fm.ActContent.value=arr[0][6];
			fm.ActGift.value=arr[0][7];
			fm.AcPeople.value=arr[0][8];
			fm.StartDate.value=arr[0][9];
			fm.StartTime.value=arr[0][10];
			fm.EndDate.value=arr[0][11];
			fm.EndTime.value=arr[0][12];
			fm.RegEndDate.value=arr[0][13];
			fm.RegEndTime.value=arr[0][14];
			fm.AppStatus.value=arr[0][15];
			fm.AppDate.value=arr[0][17];
			fm.AppTime.value=arr[0][18];
			fm.AppRemark.value=arr[0][19];
			fm.R3.value=arr[0][22];
		}
	}
}
function save()
{
	fm.action="./pa.jsp?a=assactmgt&o=save";
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