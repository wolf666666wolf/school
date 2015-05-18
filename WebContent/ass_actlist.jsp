<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>活动列表</title>
	</head>
	<body>	
	<%@include file="./plugin_header.jsp"%>
	<%@include file="./userchkA.jsp"%>	
		
		<script type="text/javascript">
		$(function(){
			loadList();		
		});
		function loadList()
		{
			var AssID=userjson.AssID;
			var data = 
		   	{
		   			AssID:AssID
		   			};
		   	po('ass','queryactlist',data);
		}

		function afterPost(d)
		{
			//alert(t(d.rd.data.sdps));
			if(d.action=='ass'&&d.operate=='queryactlist')
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
			
			<div class="button-box registration-cover-buttons"> 
				<a href="ass_actdetail.html" class="submit-button">新增活动</a>
			</div> 
			 <!--section-->
			<textarea id="template" style="display:none">  			 
			<div class="section">
				<div class="section-header">
					<h3 class="section-title">活动列表</h3>
				</div>
				<div class="section-content">
					<div class="table-wrapper my-follow-table"> 
					{#foreach $T as record} 
						<table width="100%" border="0">
						  <tr>
						    <th scope="col" class="cell-1">活动标题</th>
						    <th scope="col" class="cell-2">开始时间</th>
						    <th scope="col" class="cell-3">结束时间</th>
						    <th scope="col" class="cell-4">报名人数</th>
						    <th scope="col" class="cell-5">审核状态</th>
						    <th rowspan="2" scope="col" class="cell-8">
						    <a href="./ass_actdetail_{$T.record.ActID}.html" class="join-link">编辑</a>
						    </th>
						  </tr>
						  <tr>
						    <td class="cell-1"><span>{$T.record.Title}</span></td>
						    <td class="cell-2"><span>{$T.record.StartDate} {$T.record.StartTime}</span></td>
						    <td class="cell-3"><span>{$T.record.EndDate} {$T.record.EndTime}</span></td>
						    <td class="cell-4"><a title="点击查看参加人员" href="actatn_{$T.record.ActID}.html"><span>{$T.record.R2}</span></a></td>
						    <td class="cell-5"><span>{$T.record.AppStatus}</span></td>
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
				<input id="Button1" type="button" value="返回" onclick="window.location.href='ass_center.html';" class="submit-button">
			</div>		
		</div>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
