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
			po('content','setcounter',{SerialNo:'<%=request.getParameter("id")%>'});

			loadContent();	
			loadPath();
			loadSide();
		});
		function loadSide()
		{
			po('content','getsidelist',{});

		}

		function afterPost(d)
		{
			if(d.action=='content'&&d.operate=='getsidelist')
			{
				 $("#result1").setTemplateElement("template1");
				    $("#result1").processTemplate(d.rd.data.result1);
				    $("#result2").setTemplateElement("template2");
				    $("#result2").processTemplate(d.rd.data.result2);
			}
			if(d.action=='content'&&d.operate=='setcounter')
			{
				$(".article-infor").append(" 浏览:"+d.rd.data.R8+"次");
			}

			if(d.action=='content'&&d.operate=='getSellerName')
			{
				$(".article-infor").html(contentinfo.IssueDate+" "+contentinfo.IssueTime+" 来源(商家)：<a href='./seller_"+contentinfo.ContentFrom+".html'>"+d.rd.data.sds.SellerName+"</a>");
			}
		   
		}
		function loadContent()
		{
			$(".article-title").html(contentinfo.ContentTitle);
			if(contentinfo.ContentFrom==''||contentinfo.ContentFrom==null||contentinfo.ContentFrom=='null')contentinfo.ContentFrom='后勤服务平台';
			$(".article-infor").html(contentinfo.IssueDate+" "+contentinfo.IssueTime+" 来源："+contentinfo.ContentFrom);
			if(contentinfo.ContentFrom.substring(0,1)=="S")
			{
				loadSName();		
			}
			$(".article-content").html(contentinfo.ContentHtml);			
		}
		function loadSName()
		{
			po('content','getSellerName',{sid:contentinfo.ContentFrom});
		}
		function loadPath()
		{	
			var pccode="";		
			var pcname="";
			var sccode=contentinfo.ChannelCode;
			var scname="";
			for(var i=0;i<chnllist.length;i++)
			{
				if(sccode==chnllist[i].SerialNo)
				{
					pccode=chnllist[i].ParentChannel;
					scname=chnllist[i].ChannelName;
				}
			}
			for(var i=0;i<chnllist.length;i++)
			{
				if(pccode==chnllist[i].SerialNo)
				{
					pcname=chnllist[i].ChannelName;
				}
			}
			var ph = "<a href=\"index.html\">首  页</a><i>></i><a>"+pcname+"</a><i>></i><a href=\"./class_3_"+sccode+".html\">"+scname+"</a><i>></i><a>正文</a>";
			$("#path").html(ph);
		}
	</script>
		<div class="wrapper location" id="path"><a href="index.html">首  页</a><i>></i> <a href="">新闻播报</a><i>></i><a href="">正文</a></div>		
		<div class="wrapper clearfix"> 
			<div class="main">
				 <div class="article-box">
				 	<div class="article-header">
				 		<h1 class="article-title"></h1>
				 		<div class="article-infor"></div>
				 	</div>
				 	
				 	<div class="article-content">
					</div>
				 </div>
			</div>
			
			<div class="aside">
				<!--section-->
				<div class="section news-broadcast-section">
					<div class="section-header">
						<a href="class_3_C0041.html" class="pic more"><img src="./images/more.png"/></a>
						<h3 class="section-title">其他新闻</h3>
					</div>
					<div class="section-content">
						<div id="result1"></div>
						<textarea id="template1" style="display:none"> 
						<ul class="news-list">
						{#foreach $T as record} 
							<li><span class="date">{$T.record.IssueDate}</span><a href="./content_{$T.record.SerialNo}.html" target="_blank">{$T.record.ContentTitle}</a></li>
						{#/for} 
						</ul>
						</textarea>
					</div>
				</div>
				<!--//section--> 
				
				<!--section-->
				<div class="section hot-services-section">
					<div class="section-header">
						<a href="class_3_C0042.html" class="pic more"><img src="./images/more.png"/></a>
						<h3 class="section-title">通告通知</h3>
					</div>
					<div class="section-content"> 
						<div id="result2"></div>
						<textarea id="template2" style="display:none"> 
						<ul class="news-list">
						{#foreach $T as record} 
							<li> 
								<span class="date">{$T.record.IssueDate}</span><a href="./content_{$T.record.SerialNo}.html"  target="_blank" class="news-title">{$T.record.ContentTitle}</a>
							</li>
						{#/for} 
						</ul>
						</textarea>
					</div>
				</div>
				<!--//section-->  
			</div>
		</div>
		 
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
