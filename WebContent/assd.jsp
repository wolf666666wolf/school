<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>社团信息</title>
		<link rel="stylesheet" href="../css/public.css" />
	</head>
	<body>
<%@include file="./plugin_header.jsp"%>	
<script>
var assid = '<%=request.getParameter("id")%>';
$(function(){
	loadInfo();
});	 
function loadInfo()
{
	var data = 
   	{
   			AssID:assid
   			};
   	po('content','getass',data);
}

function afterPost(d)
{
	//alert(t(d.rd.data.sdps));
	if(d.action=='content'&&d.operate=='getass')
	{
		if(d.rd.succ)
		{		
			//商家信息
			var s = d.rd.data.ass;
			$("#AssID").html(s.AssID);
			$("#Address").html(s.Address);
			$("#AssName").html(s.AssName);
			$("#OrgShotName").html(s.AssName+"详细信息");
			$("#AssFeature").html(s.AssFeature);	    
			$("#AssPeople").html(s.AssPeople);	    
			$("#R1").html(s.R1);	    
		}
		else
		{
	   		alert(d.rd.msg);		
		}
	}
} 
</script>

		<div class="wrapper page-header">
			<h2 class="pape-title" id="OrgShotName"></h2>
		</div>
		
		<div class="wrapper clearfix">
			<div class="merchant-summary clearfix">
				<!--form-box-->
				<div class="form-box"> 
					<ul class="form-text">
						<li class="form-item clearfix"><span class="item-title">社团编号</span>
							<span id="AssID"></span>
						</li>
						<li class="form-item clearfix"><span class="item-title">社团名称</span>
							<span id="AssName"></span>
						</li> 
						<li class="form-item clearfix"><span class="item-title">社团特色</span>
							<span id="AssFeature"></span>
						</li>
						<li class="form-item clearfix"><span class="item-title">社团人数</span>
							<span id="AssPeople"></span>
						</li>
						<li class="form-item clearfix"><span class="item-title">社团咨询电话</span>
							<span id="R1"></span>
						</li>
						<li class="form-item clearfix"><span class="item-title">办公地址</span>
							<span id="Address"></span>
						</li>
					</ul> 
				</div>
				<!--//form-box-->
			</div>
		</div>
		
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
