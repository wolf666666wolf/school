<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的评价</title>
	</head>
	<body>	
	<%@include file="./plugin_header.jsp"%>
	<%@include file="./userchkS.jsp"%>	
		
		<script type="text/javascript">
		$(function(){
			loadList();		
		});
		function loadList()
		{
			var SellerID=userjson.SellerID;
			var data = 
		   	{
		   			SellerID:SellerID
		   			};
		   	po('seller','getasslist',data);
		}

		function afterPost(d)
		{
			if(d.action=='seller'&&d.operate=='getasslist')
			{
				if(d.rd.succ)
				{					 
				    // 附上模板  
				    $("#result1").setTemplateElement("template");  
				    // 给模板加载数据  
				    $("#result1").processTemplate(d.rd.data.asslist);
				}
				else
				{
			   		alert(d.rd.msg);		
				}
			}
		} 
		</script> 
		<!--wrapper-->
		<div class="wrapper clearfix">
			 <!--section-->
			<textarea id="template" style="display:none">  			 
			<div class="section">
				<div class="section-header">
					<h3 class="section-title">评价列表</h3>
				</div>
				<div class="section-content">
					<div class="table-wrapper my-follow-table"> 
						<table width="100%" border="0">
						  <tr>
						    <th scope="col" class="cell-1">产品名称</th>
						    <th scope="col" class="cell-2">订单日期</th>
						    <th scope="col" class="cell-3">评价等级</th>
						    <th scope="col" class="cell-4">评价内容</th>
						    <th scope="col" class="cell-5">评价时间</th>
						    <th scope="col" class="cell-6">用户</th>
						    <th scope="col" class="cell-7">手机</th>
						  </tr>
						  
					{#foreach $T as record} 
						  <tr>
						    <td class="cell-1"><span>{$T.record.PrdName}</span></td>
						    <td class="cell-2"><span>{$T.record.OrderDate}</span></td>
						    <td class="cell-3"><span>{$T.record.Level}</span></td>
						    <td class="cell-4"><span>{$T.record.Content}</span></td>
						    <td class="cell-5"><span>{$T.record.AssEssDate}</span></td>
						    <td class="cell-5"><span>{$T.record.NickName}</span></td>
						    <td class="cell-5"><span>{$T.record.Phone}</span></td>
						  </tr> 
						{#/for} 
						</table>
					</div>
				</div>			 
			</div> 					
			</textarea> 	
						
			<!-- 输出元素 -->  
			<div id="result1" ></div>
			
			<div class="button-box">  
				<input id="Button1" type="button" value="返回" onclick="window.location.href='seller_center.html';" class="submit-button">
			</div>		
		</div>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
