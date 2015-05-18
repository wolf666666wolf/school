<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>内容发布</title>
	</head>
	<body>	
	<%@include file="./plugin_header.jsp"%>
	<%@include file="./userchkS.jsp"%>	
		
		<script type="text/javascript">
		$(function(){
			loadList();		
		});
		function delcontent(cid)
		{

			if(!confirm("确定删除?"))
			{
				return;		
			}
			var data = 
		   	{
		   			SerialNo:cid
		   			};
		   	po('seller','deletecontent',data);
		}
		function loadList()
		{
			var SellerID=userjson.SellerID;
			var data = 
		   	{
		   			SellerID:SellerID
		   			};
		   	po('seller','querycontentlist',data);
		}

		function afterPost(d)
		{
			//alert(t(d.rd.data.sdps));
			
			if(d.action=='seller'&&d.operate=='deletecontent')
			{
		   		alert(d.rd.msg);		
				loadList();		
			}
			if(d.action=='seller'&&d.operate=='querycontentlist')
			{
				if(d.rd.succ)
				{					 
				    // 附上模板  
				    $("#result1").setTemplateElement("template");  
				    // 给模板加载数据  
				    $("#result1").processTemplate(d.rd.data.contentlist);
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
				<a href="seller_contentdetail.html" class="submit-button">新增内容</a>
			</div> 
			 <!--section-->
			<textarea id="template" style="display:none">  			 
			<div class="section">
				<div class="section-header">
					<h3 class="section-title">内容列表</h3>
				</div>
				<div class="section-content">
					<div class="table-wrapper my-follow-table"> 
					{#foreach $T as record} 
						<table width="100%" border="0">
						  <tr>
						    <th scope="col" class="cell-1">标题</th>
						    <th scope="col" class="cell-2">栏目</th>
						    <th scope="col" class="cell-3">发布时间</th>
						    <th scope="col" class="cell-4">审核</th>
						    <th rowspan="2" scope="col" class="cell-8">
						    <a href="./seller_contentdetail_{$T.record.SerialNo}.html" class="join-link">编辑</a><br />
						    <a href="javascript:delcontent('{$T.record.SerialNo}');" class="join-link">删除</a>
						    </th>
						  </tr>
						  <tr>
						    <td class="cell-1"><span>{$T.record.ContentTitle}</span></td>
						    <td class="cell-2"><span>{$T.record.ChannelName}</span></td>
						    <td class="cell-3"><span>{$T.record.IssueDateTime}</span></td>
						    <td class="cell-4"><span>{$T.record.R1}</span></td>
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
