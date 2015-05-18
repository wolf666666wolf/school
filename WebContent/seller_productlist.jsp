<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商家中心</title>
	</head>
	<body>	
	<%@include file="./plugin_header.jsp"%>
	<%@include file="./userchkS.jsp"%>	
		
		<script type="text/javascript">
		$(function(){
			loadPrdList();		
		});
		function delprd(prdid)
		{
			var data = 
		   	{
		   			PrdID:prdid
		   			};
		   	po('seller','delprd',data);
		}
		function loadPrdList()
		{
			var SellerID=userjson.SellerID;
			var data = 
		   	{
		   			SellerID:SellerID
		   			};
		   	po('seller','queryprdlist',data);
		}

		function afterPost(d)
		{
			//alert(t(d.rd.data.sdps));
			
			if(d.action=='seller'&&d.operate=='delprd')
			{
		   		alert(d.rd.msg);	
				loadPrdList();		
			}
			if(d.action=='seller'&&d.operate=='queryprdlist')
			{
				if(d.rd.succ)
				{					 
				    // 附上模板  
				    $("#result1").setTemplateElement("template");  
				    // 给模板加载数据  
				    $("#result1").processTemplate(d.rd.data.sdps);
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
			
			<div class="button-box registration-cover-buttons"> 
				<a href="seller_productdetail.html" class="submit-button">新增项目</a>
			</div> 
			 <!--section-->
			<textarea id="template" style="display:none">  			 
			<div class="section">
				<div class="section-header">
					<h3 class="section-title">产品列表</h3>
				</div>
				<div class="section-content">
					<div class="table-wrapper my-follow-table"> 
					{#foreach $T as record} 
						<table width="100%" border="0">
						  <tr>
						    <th scope="col" class="cell-1">名称</th>
						    <th scope="col" class="cell-3">价格</th>
						    <th scope="col" class="cell-4">有效期</th>
						    <th scope="col" class="cell-5">审核</th>
						    <th scope="col" class="cell-6">下架</th>
						    <th rowspan="2" scope="col" class="cell-8">
						    <a href="./seller_productdetail_{$T.record.PrdID}.html" class="join-link">编辑</a><br />
						    <a href="javascript:delprd('{$T.record.PrdID}');" class="join-link">下架</a>
						    </th>
						  </tr>
						  <tr>
						    <td class="cell-1"><span>{$T.record.PrdName}</span></td>
						    <td class="cell-3"><span>{$T.record.PrdPrice}</span></td>
						    <td class="cell-4"><span>{$T.record.EffEndDate}</span></td>
						    <td class="cell-5"><span>{$T.record.R1}</span></td>
						    <td class="cell-5"><span>{$T.record.R2}</span></td>
						  </tr> 
						</table>
						{#/for} 
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
