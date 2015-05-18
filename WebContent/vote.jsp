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
	var ncode = window.location.href.split("?")[1];
	$(function(){
		loadSideNav(ncode);		
		initList();	
	});
	function initList()
	{
		var data = 
		{
				};
		po('vote','getlist',data);
	}

	function afterPost(d)
	{
		if(d.action=='vote'&&d.operate=='getlist')
		{
			loadList(d.rd.data.vote);
		}
	}
	function loadList(list)
	{
		var clh = "";
		for(var i=0;i<list.length;i++)
		{
			var addh = "";
			if(i<5)
			{
				addh="<span class=\"tag new-tag\">[新]</span>";
			}
			clh+="<li class=\"news-item\"><a href=\"./votemain_"+list[i].VoteNo+".html\" class=\"more\">查看详细&gt;&gt;</a>"+
			"<a href=\"./votemain_"+list[i].VoteNo+".html\" class=\"news-title\">"+list[i].VoteTitle+addh+"</a></li>";
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
