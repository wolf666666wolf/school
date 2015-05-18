<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>清华大学后勤综合服务平台</title>
		<script src="./static/class_<%=request.getParameter("id")%>.js?t=<%=System.currentTimeMillis()%>"></script>		
	</head>
	<body>
	<%@include file="./plugin_header.jsp"%>
	<script type="text/javascript">
	var ncode = '<%=request.getParameter("id")%>';
	$(function(){
		loadSideNav(ncode);		
	});
	
	</script>			
		<!--wrapper-->
		<div class="wrapper clearfix">
			<div class="side-bar">
				<div class="side-nav">
				</div>
			</div>
			<div class="main">
				<ul class="news-list">
				</ul> 
			</div>
		</div>
		<!--//wrapper-->
		
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
