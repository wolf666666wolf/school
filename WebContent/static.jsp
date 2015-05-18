<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>清华大学后勤综合服务平台</title>		
		<script src="./static/content_<%=request.getParameter("id")%>.js?t=<%=System.currentTimeMillis()%>"></script>		

	</head>
	<body class="articlePage">
	<%@include file="./plugin_header.jsp"%>
		<script type="text/javascript">
		$(function(){
			loadContent();	
		});
		function loadContent()
		{
			//$(".article-title").html(contentinfo.ContentTitle);
			//if(contentinfo.ContentFrom==''||contentinfo.ContentFrom==null||contentinfo.ContentFrom=='null')contentinfo.ContentFrom='后勤服务平台';
			//$(".article-infor").html(contentinfo.IssueDate+" "+contentinfo.IssueTime+" 来源："+contentinfo.ContentFrom);
			$(".article-content").html(contentinfo.ContentHtml);			
		}
	</script>
		<div class="wrapper clearfix"> 
			<div class="main">
				 <div class="article-box">				 	
				 	<div class="article-content">
					</div>
				 </div>
			</div>
		</div>
		 
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
