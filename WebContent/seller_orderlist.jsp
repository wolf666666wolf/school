<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>订单信息</title>
	</head>
	<body>
<%@include file="./plugin_header.jsp"%>
<%@include file="./userchkS.jsp"%>
<script>

$(function(){
	loadOrderList();		
});
function conf(id)
{
	var data = 
   	{
			OrderID:id
   			};
   	po('seller','confirmorder',data);
}
function canc(id)
{
	var data = 
   	{
			OrderID:id
   			};
   	po('seller','cancelorder',data);
}
function loadOrderList(pno)
{
	if(pno==null||pno==undefined||pno==""||pno=="null")pno=1;
	var SellerID = userjson.SellerID;
	var data = 
   	{
			SellerID:SellerID,pno:pno
   			};
   	po('seller','getorderlist',data);
}

function afterPost(d)
{
	if(d.action=='seller'&&d.operate=='getorderlist')
	{
		if(d.rd.succ)
		{
			 // 附上模板  
		    $("#result1").setTemplateElement("template");  
		    // 给模板加载数据  
		    $("#result1").processTemplate(d.rd.data.orderlist);
		    
		    //页码处理
		    var pno = d.data.pno;
		    var tno = d.rd.data.totalNo;
		    var page=Math.ceil(tno/10);
		    var ph = "";
		    for(var i=0;i<page;i++)
		    {
		   		 if(i+1==pno)
		   			 ph+="<span class=\"bigNo\"><a href=\"javascript:void(0);\">"+(i+1)+"</a></span>&nbsp;&nbsp;";
		   		 else
		   			 ph+="<span class=\"smallNo\"><a href=\"javascript:loadOrderList("+(i+1)+");\">"+(i+1)+"</a></span>&nbsp;&nbsp;";
		   	}
		    $("#pageNav").html(ph);
		    $("#pageNav2").html(ph);
		    //alert(page);
		}
	}
	if(d.action=='seller'&&d.operate=='confirmorder')
	{
		alert(d.rd.msg);
		loadOrderList();
	}
	if(d.action=='seller'&&d.operate=='cancelorder')
	{
		alert(d.rd.msg);
		loadOrderList();
	}
}
</script>	
		
		<div class="wrapper page-header">
			<h2 class="pape-title">订单管理</h2>
		</div>
		
		<div class="wrapper order-modify-wrapper clearfix"> 
			<!--table-wrapper-->
			<div class="table-wrapper order-wrapper"> 
			<style>
			.pageNav span
			{
				width:30px;
			}
			.pageNav .bigNo
			{
				font-size: 16px;
				font-weight: bold;
			}
			.pageNav .smallNo
			{
				font-size: 14px;
			}
			</style>
			<div id="pageNav" class="pageNav"></div>
			<!-- 输出元素 -->  
			<div id="result1" ></div>		
			
			<textarea id="template" style="display:none">  
			{#foreach $T as record}  		
				<table width="100%" border="0">
				  <tr>
				    <th scope="col" class="cell-1"><div class="th-inner">订单编号：{$T.record.OrderID}</div></th>
				    <th scope="col" class="cell-2"><div class="th-inner">时间/地址</div></th>
				    <th scope="col" class="cell-3"><div class="th-inner">订单联系人/联系电话</div></th>
				    <th scope="col" class="cell-4"><div class="th-inner">类别/内容（数量）</div></th>
				    <th scope="col" class="cell-5"><div class="th-inner">详细要求</div></th>
				    <th scope="col" class="cell-6 last-cell"><div class="th-inner">订单状态</div></th>
				  </tr>
				  <tr>
				    <td class="cell-1"><span>商品/服务名称：{$T.record.PrdName}</span></td>
				    <td class="cell-2"><span>{$T.record.ArriveDate}</span><span>{$T.record.R1} {$T.record.Address}</span></td>
				    <td class="cell-3"><span>{$T.record.Owner}</span><span>{$T.record.Phone} {$T.record.Tel}</span></td>
				    <td class="cell-4"><span>{$T.record.OCount}</span></td>
				    <td class="cell-5"><span>{$T.record.Requirement}</span></td>
				    <td class="cell-6 last-cell"><span>{$T.record.R2}</span><div class="modify-buttons" style="display:{$T.record.Display};"><a href="javascript:conf('{$T.record.OrderID}');">确认</a><a href="javascript:canc('{$T.record.OrderID}');">取消</a></div></td>
				  </tr> 
				</table>
				{#/for} 
				</textarea>
				<div id="pageNav2" class="pageNav"></div>
				
			</div>
			
			<div class="button-box">  
				<input id="Button1" type="button" value="返回" onclick="window.location.href='seller_center.html';" class="submit-button">
			</div>	
		</div>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
