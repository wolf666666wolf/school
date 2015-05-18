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
	var ncode = window.location.href;
	$(function(){
		var ncode = window.location.href;
		ncode=ncode.split("?")[ncode.split("?").length-1];
		loadSideNav(ncode);		
		initList();	
	});
	function initList()
	{
		var data = 
		{
				};
		po('content','getasslist',data);
	}

	function afterPost(d)
	{
		if(d.action=='content'&&d.operate=='getasslist')
		{
			loadList(d.rd.data.asslist);
		}
	}
	function loadList(list)
	{
		var clh = "";
		for(var i=0;i<list.length;i++)
		{
			clh+="<li class=\"news-item\"><a href=\"./ass_"+list[i].AssID+".html\" class=\"more\">查看详细&gt;&gt;</a>"+
			"<a href=\"./ass_"+list[i].AssID+".html\" class=\"news-title\">"+list[i].ShotOrgName+"</a></li>";
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
