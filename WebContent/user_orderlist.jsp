<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>订单信息</title>
	</head>
	<body>
<%@include file="./plugin_header.jsp"%>
<%@include file="./userchkU.jsp"%>
<script>

$(function(){
	loadOrderList();		
});
function loadOrderList()
{
	var UserID = userjson.UserID;
	var data = 
   	{
			UserID:UserID
   			};
   	po('user','getorderlist',data);
}

function leam(id)
{
	window.location.href="orderassess_"+id+".html";
}
function canc(id)
{
	var data = 
   	{
			OrderID:id
   			};
   	po('user','cancelorder',data);
}
function afterPost(d)
{
	if(d.action=='user'&&d.operate=='getorderlist')
	{
		if(d.rd.succ)
		{
			 // 附上模板  
		    $("#result1").setTemplateElement("template");  
		    // 给模板加载数据  
		    $("#result1").processTemplate(d.rd.data.orderlist);
		}
	}
	if(d.action=='user'&&d.operate=='cancelorder')
	{
		alert(d.rd.msg);
		if(d.rd.succ)
		{
			loadOrderList();	
		}
	}
}
</script>	
		
		<div class="wrapper page-header">
			<h2 class="pape-title">订单信息</h2>
		</div>
		
		<div class="wrapper order-modify-wrapper clearfix"> 
			<!--table-wrapper-->
			<div class="table-wrapper order-wrapper"> 
			<!-- 输出元素 -->  
			<div id="result1" ></div>		
			</div>
			<textarea id="template" style="display:none">  			 
			{#foreach $T as record} 
				<table width="100%" border="0">
				  <tr>
				    <th scope="col" class="cell-1"><div class="th-inner">订单编号：{$T.record.OrderID}</div></th>
				    <th scope="col" class="cell-2"><div class="th-inner">星期/日期/时间</div></th>
				    <th scope="col" class="cell-3"><div class="th-inner">商户联系人/联系电话</div></th>
				    <th scope="col" class="cell-4"><div class="th-inner">内容（数量）</div></th>
				    <th scope="col" class="cell-5"><div class="th-inner">详细要求</div></th>
				    <th scope="col" class="cell-6 last-cell"><div class="th-inner">订单状态</div></th>
				  </tr>
				  <tr>
				    <td class="cell-1"><span>商户名称：{$T.record.ShotOrgName}</span><span>商品/服务名称：{$T.record.PrdName}</span></td>
				    <td class="cell-2"><span>{$T.record.Week}</span><span>{$T.record.MakeDate}  {$T.record.MakeTime}</span></td>
				    <td class="cell-3"><span>{$T.record.LinkPerson}</span><span>{$T.record.Phone}</span></td>
				    <td class="cell-4"><span>{$T.record.OCount}</span></td>
				    <td class="cell-5"><span>{$T.record.Requirement}</span></td>
				    <td class="cell-6 last-cell"><span><span>{$T.record.R2}</span><div class="modify-buttons"><a href="javascript:leam('{$T.record.OrderID}');" style="display:{$T.record.Display1};">评价</a><a href="javascript:canc('{$T.record.OrderID}');"  style="display:{$T.record.Display2};">取消</a></div></td>
				  </tr> 
				</table>
				{#/for} 
				</textarea>
			</div>
			
			<div class="button-box">  
				<input id="Button1" type="button" value="返回" onclick="window.location.href='user_center.html';" class="submit-button">
			</div>
		</div>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
