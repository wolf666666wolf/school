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
<SCRIPT src="../common/easyQueryVer3/EasyQueryVer3.js"></SCRIPT>
<SCRIPT src="../common/easyQueryVer3/EasyQueryCache.js"></SCRIPT>
<SCRIPT src="../common/Calendar/Calendar.js"></SCRIPT>
<LINK href="../common/css/Project.css" rel=stylesheet type=text/css>
<LINK href="../common/css/mulLine.css" rel=stylesheet type=text/css>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor.all.min.js"> </script>

<title>内容信息</title>
</head>
<body onload="initForm();">
<form method=post name=fm target="fraSubmit">
<table class=common border=0>
	<TR class=common>
		<td class=title>标题</td>
		<td class=input colspan="5"><Input class="common3" name=ContentTitle>
		<Input class="common" name=SerialNo value="<%=request.getParameter("cno")%>" type=hidden>
		<Input class="common" name=ContentTxt type=hidden>
		<Input class="common" name=ContentHtml type=hidden>
		</td>
	</TR>
	<TR class=common>
		<td class=title></td>
		<td class=input></td>
		<td class=title></td>
		<td class=input></td>
		<td class=title></td>
		<td class=input></td>
	</TR>
</table>


<!-- 加载编辑器的容器 -->
    <script id="container" name="content" type="text/plain">

    </script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>
    
    <br>
<table class=common border=0>
	<TR class=common>
		<td class=title>是否显示</td>
		<td class=input><Input class=codeno name=R1 ondblclick="showCodeList('yesorno',[this,R1Name],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('yesorno',[this,R1Name],[0,1],null,null,null,true);"><input class=codename name=R1Name readonly=true></td>
		<td class=title>发布日期</td>
		<td class=input><Input class="common" name=IssueDate readonly></td>
		<td class=title>发布时间</td>
		<td class=input><Input class="common" name=IssueTime readonly></td>
	</TR>
	<TR class=common>
		<td class=title>来源</td>
		<td class=input><Input class="common" name=ContentFrom></td>
		<td class=title>栏目</td>
		<td class=input><Input class=codeno name=ChannelCode ondblclick="showCodeList('chnlcode',[this,ChannelCodeName],[0,1],null,null,null,true);" onkeyup="return showCodeListKey('chnlcode',[this,ChannelCodeName],[0,1],null,null,null,true);"><input class=codename name=ChannelCodeName readonly=true></td>
		<td class=title>页面标识</td>
		<td class=input><Input class="common" name=SpecFlag></td>
	</TR>
</table>
    
    <br>
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
	var sno = fm.SerialNo.value;
	if(sno!=''&&sno!=null&&sno!='null')
	{
		var SQL = "select * from sdcontent where serialno='"+sno+"'";
		var arr = easyExecSql(SQL, 1, 0);
		if(arr)
		{
			fm.SerialNo.value=arr[0][0];
			fm.ChannelCode.value=arr[0][1];
			if(fm.ChannelCode.value=='C0000')
			{
				fm.SpecFlag.value=arr[0][0];
			}
			fm.ContentTitle.value=arr[0][2];
			fm.ContentTxt.value=arr[0][3];
			fm.ContentHtml.value=arr[0][4];
			fm.IssueDate.value=arr[0][5];
			fm.IssueTime.value=arr[0][6];
			fm.ContentFrom.value=arr[0][7];
			fm.R1.value=arr[0][8];
			showOneCodeName('yesorno',fm.R1,fm.R1Name);
			if(fm.ChannelCode.value=='C0000')
			fm.ChannelCodeName.value='静态页面';
			else
				fm.ChannelCodeName.value=easyExecSql("select b.channelname||'>'||a.channelname from sdchannel a,sdchannel b where a.parentchannel=b.serialno and a.R3='3'  and a.serialno='"+fm.ChannelCode.value+"'", 1, 0);
			setTimeout(function(){ue.setContent(fm.ContentHtml.value, false);},1000);
			
		}
	}
}
function save()
{
	if(fm.ContentTitle.value=='')
	{
		alert('请录入标题');	
		return;
	}
	if(fm.R1.value=='')
	{
		alert('请录入是否显示');	
		return;
	}
	fm.ContentHtml.value=ue.getContent();
	fm.ContentTxt.value=ue.getContentTxt();
	if(fm.ContentTxt.value=='')
	{
		alert('请录入内容');	
		return;
	}
	if(fm.ChannelCode.value=='')
	{
		alert('请选择栏目');	
		return;
	}

	if(fm.ChannelCode.value=='C0000'&&fm.SpecFlag.value=='')
	{
		alert('请录入页面标示');	
		return;
	}
	fm.action="./pa.jsp?a=contentmgt&o=save";
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