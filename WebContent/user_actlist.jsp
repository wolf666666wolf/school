<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的活动</title>
	</head>
	<body>
<%@include file="./plugin_header.jsp"%>
<%@include file="./userchkU.jsp"%>
<script>
$(function(){
	loadList();		
});
function can(id)
{
	var data = 
   	{
   			ActID:id,
   			UserID:userjson.UserID
   			};
   	po('act','regact',data);
}
function loadList()
{
	var UserID = userjson.UserID;
	var data = 
   	{
			UserID:UserID
   			};
   	po('user','getactlist',data);
}

function afterPost(d)
{
	if(d.action=='user'&&d.operate=='getactlist')
	{
		if(d.rd.succ)
		{
			 // 附上模板  
		    $("#result1").setTemplateElement("template");  
		    // 给模板加载数据  
		    $("#result1").processTemplate(d.rd.data.actlist);
		}
	}

	if(d.action=='act'&&d.operate=='regact')
	{
		alert(d.rd.msg);
		loadList();
	}
}
</script>
		<div class="wrapper clearfix">
			<!--section-->
			<div class="section">
				<div class="section-header">
					<h3 class="section-title">活动信息</h3>
				</div>
				<div class="section-content">
					<div class="table-wrapper my-follow-table"> 
					<!-- 输出元素 -->  
					<div id="result1" ></div>		
					</div>
					<textarea id="template" style="display:none">  			 
					{#foreach $T as record} 
						<table width="100%" border="0">
						  <tr>
						    <th scope="col" class="cell-1">活动名称</th>
						    <th scope="col" class="cell-2">发起人</th>
						    <th scope="col" class="cell-3">联系人手机</th>
						    <th scope="col" class="cell-4">开始时间</th>
						    <th scope="col" class="cell-5">结束时间</th>
						    <th scope="col" class="cell-6">费用</th>
						    <th rowspan="2" scope="col" class="cell-8"><a href="javascript:can('{$T.record.ActID}');" class="join-link">取消参加</a></th>
						  </tr>
						  <tr>
						    <td class="cell-1"><span><a href="./actmain_{$T.record.ActID}.html">{$T.record.Title}</a></span></td>
						    <td class="cell-2"><span>{$T.record.ShotOrgName}</span></td>
						    <td class="cell-3"><span>{$T.record.Phone}</span></td>
						    <td class="cell-4"><span>{$T.record.StartDate}</span></td>
						    <td class="cell-5"><span>{$T.record.EndDate}</span></td>
						    <td class="cell-6"><span>{$T.record.ActFee}</span></td>
						  </tr> 
						</table> 
						{#/for} 
						</textarea> 
					</div>
				</div>
			</div>
			<!--//section-->
			
			<div class="button-box">  
				<input id="Button1" type="button" value="返回" onclick="window.location.href='user_center.html';" class="submit-button">
			</div>
		</div>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
