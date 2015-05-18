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
		loadSideNavSelf(ncode);		
		initContentList();	
	});
	function loadSideNavSelf(ncode)
	{
		var ha="";
		var hb="";
		if(ncode=='news')
			ha=" class=\"current\"";;
		if(ncode=='notice')
			hb=" class=\"current\"";;
		var h="";
		h+="<a href=\"./class_out_news.html\" "+ha+">新闻播报</a>";
		h+="<a href=\"./class_out_notice.html\" "+hb+">通知公告</a>";
		$(".side-nav").html(h);
	}
	function initContentList()
	{
		var data = 
		{
				id:'<%=request.getParameter("id")%>'
				};
		po('content','getcrawlerlist',data);
	}

	function afterPost(d)
	{
		if(d.action=='content'&&d.operate=='getcrawlerlist')
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
			clh+="<li class=\"news-item\"><a href=\""+contentlist[i].R3+"\" target=\"_blank\" class=\"more\">查看详细&gt;&gt;</a>"+
			"<a href=\""+contentlist[i].R3+"\" target=\"_blank\" class=\"news-title\">["+contentlist[i].ContentFrom+"] "+contentlist[i].ContentTitle+addh+"&nbsp;"+contentlist[i].IssueDate+"</a></li>";
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
