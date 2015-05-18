<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>清华大学后勤综合服务平台</title>		
	</head>
	<body class="articlePage">
	<%@include file="./plugin_header.jsp"%>
		<script type="text/javascript">
		var voteno = '<%=request.getParameter("id")%>';

		$(function(){
			loadContent();	
			loadSide();
		});
		function loadSide()
		{
			po('content','getsidelist',{});

		}
		function loadContent()
		{
			var data = 
		   	{
					VoteNo:voteno
		   			};
		   	po('vote','getvote',data);
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
			if(d.action=='vote'&&d.operate=='getvote')
			{
				if(d.rd.succ)
				{
					//alert();
					var vo = d.rd.data.sdvm;
					$("#Title").html(vo.VoteTitle);
					var ht="";
					if(vo.AnonyFlag!='Y'&&userjson.UserID==undefined)
					{
						ht="此调查非匿名，请先登录！";
						$("#Button1").css("display","none");
					}
					$(".article-infor").html(ht);
					
					loadVoteInfo(d.rd.data);
				}
			}
			if(d.action=='vote'&&d.operate=='sendvote')
			{
				alert(d.rd.msg);
			}
		}
var vm,vt,vi;
function loadVoteInfo(d)
{
	vm=d.sdvm;
	vi=d.sdvi;
	vt=d.sdvt;
	var h = "";
	//循环问题
	for(var i=0;i<vt.length;i++)
	{
		if(vt[i].DisplayFlag!='Y')
			continue;
		h+="<div class=\"form-box\"> ";	
		h+="<ul class=\"form-titlevote\">";
		h+="<li class=\"form-item clearfix\"><span>"+(i+1)+"."+vt[i].TopicTitle+"</span></li>";
		h+="</ul> ";
		//选项
		for(var j=0;j<vi.length;j++)
		{
			if(vi[j].TopicNo==vt[i].TopicNo)
			{
				var ha="";
				if(vt[i].VoteType=='C')
				{
					ha+="<input type=\"checkbox\" id=\"check_"+vt[i].TopicNo+"_"+vi[j].ItemNo+"\" value=\""+vt[i].TopicNo+"_"+vi[j].ItemNo+"\"/>";
				}
				else
				{
					ha+="<input type=\"radio\" name=\"radio_"+vt[i].TopicNo+"\" value=\""+vt[i].TopicNo+"_"+vi[j].ItemNo+"\"/>";
				}
				h+=ha+"<span class=\"form-vote\">"+vi[j].VoteSeq+"."+vi[j].VoteItem+"</span>";
				if(j<vi.length-1)
					h+="<br />";
			}
		}
		h+="</div> ";

	}
	$("#votemain_div").html(h);
}
function votesubmit()
{
	//校验及收集
	var rl=new Array();
	for(var i=0;i<vt.length;i++)
	{
		if(vt[i].DisplayFlag!='Y')
			continue;
		if(vt[i].VoteType=='C')
		{
			var cf = false;
			for(var j=0;j<vi.length;j++)
			{
				if(vi[j].TopicNo==vt[i].TopicNo)
				{
					if($("#check_"+vt[i].TopicNo+"_"+vi[j].ItemNo).is(':checked'))	
					{
						cf=true;	
						rl[rl.length]={TopicNo:vt[i].TopicNo,ItemNo:vi[j].ItemNo};
					}
				}
			}
			if(!cf)
			{
				alert("请完成第"+(i+1)+"题");
				return;
			}
		}
		else
		{
			if($("input[name='radio_"+vt[i].TopicNo+"']:checked").val()==undefined)
			{
				alert("请完成第"+(i+1)+"题");
				return;
			}
			rl[rl.length]={TopicNo:vt[i].TopicNo,ItemNo:$("input[name='radio_"+vt[i].TopicNo+"']:checked").val().split("_")[1]};
		}
	}
	var data = 
   	{
			itemlist:rl,
			AnonyFlag:vm.AnonyFlag,
			UserID:userjson.UserID,
			VoteNo:vm.VoteNo
   			};
   	po('vote','sendvote',data);
}
	</script>
		<div class="wrapper clearfix"> 
			<div class="main">
				 <div class="article-box">
				 	<div class="article-header">
				 		<h1 class="article-title" id="Title"></h1>
				 		<div class="article-infor"></div>
				 	</div>
				 	<!--form-box-->
				 	<div id="votemain_div">
				 	<!-- 
						<div class="form-box"> 
							<ul class="form-text">
								<li class="form-item clearfix"><span>1.调查问题1</span></li>
							</ul> 
							<input type="radio" name="11" id="r2"/><span class="item-title">1.选项1</span><br />
							<input type="radio" name="11" id="r2"/><span class="item-title">2.选项2</span>
						</div>
						<div class="form-box"> 
							<ul class="form-text">
								<li class="form-item clearfix"><span>2.调查问题2</span>
								</li>
							</ul> 
							<input type="checkbox" group="11" id="r2"/><span class="item-title">1.选项1</span><br />
							<input type="checkbox" group="11" id="r2"/><span class="item-title">2.选项2</span>
						</div>
						 -->
					</div>
				 </div>
				 
			<div class="button-box">  
				<input id="Button1" type="button" value="提交" onclick="votesubmit();" class="submit-button">
			</div>
			</div>
			
			<div class="aside"><!--section-->
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
