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
		initContentList();	
	});
	function initContentList()
	{
		var data = 
		{
				id:'<%=request.getParameter("id")%>'
				};
		po('content','getlist',data);
	}

	function afterPost(d)
	{
		if(d.action=='content'&&d.operate=='getlist')
		{
			loadContentList(d.rd.data.contentlist);
		}
	}
	function loadContentList(contentlist)
	{
		var clh = "";
		for(var i=0;i<contentlist.length;i++)
		{
			var addh = "";
			if(i<5)
			{
				addh="<span class=\"tag new-tag\">[新]</span>";
			}
			clh+="<li class=\"news-item\"><a href=\"./content_"+contentlist[i].SerialNo+".html\" class=\"more\">查看详细&gt;&gt;</a>"+
			"<a href=\"./content_"+contentlist[i].SerialNo+".html\" class=\"news-title\">"+contentlist[i].ContentTitle+" ["+contentlist[i].IssueDate+"] "+addh+"</a></li>";
		}
		$(".news-list").html(clh);
	}
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
