<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>活动参加人员列表</title>
	</head>
	<body>	
	<%@include file="./plugin_header.jsp"%>
	<%@include file="./userchkA.jsp"%>	
		
		<script type="text/javascript">
		var ActID='<%=request.getParameter("id")%>';
		$(function(){
			loadList();		
		});
		function loadList()
		{
			var data = 
		   	{
					ActID:ActID
		   			};
		   	po('ass','queryactatnlist',data);
		}

		function afterPost(d)
		{
			//alert(t(d.rd.data.sdps));
			if(d.action=='ass'&&d.operate=='queryactatnlist')
			{
				if(d.rd.succ)
				{					 
				    // 附上模板  
				    $("#result1").setTemplateElement("template");  
				    // 给模板加载数据  
				    $("#result1").processTemplate(d.rd.data.sdaas);
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
					<h3 class="section-title">参加人员列表</h3>
				</div>
				<div class="section-content">
					<div class="table-wrapper my-follow-table"> 
					{#foreach $T as record} 
						<table width="100%" border="0">
						  <tr>
						    <th scope="col" class="cell-1">活动标题</th>
						    <th scope="col" class="cell-2">参加人</th>
						    <th scope="col" class="cell-3">报名日期</th>
						    <th scope="col" class="cell-4">报名时间</th>
						    <th scope="col" class="cell-5">联系方式</th>
						  </tr>
						  <tr>
						    <td class="cell-1"><span>{$T.record.Title}</span></td>
						    <td class="cell-2"><span>{$T.record.NickName}</span></td>
						    <td class="cell-3"><span>{$T.record.AtnDate}</span></td>
						    <td class="cell-4"><span>{$T.record.AtnTime}</span></td>
						    <td class="cell-5"><span>{$T.record.Phone}</span></td>
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
				<input id="Button1" type="button" value="返回" onclick="window.location.href='ass_actlist.html';" class="submit-button">
			</div>		
		</div>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
