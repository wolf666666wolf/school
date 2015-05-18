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
	var kw = getParameter("kw");
	$(function(){
		loadClassName();
	});
	function loadClassName()
	{
	   	po('seller','loadclassname',{});
	}
	var classlist;
	function loadSubClass()
	{
		var cno = $("#ServiceType").find("option:selected").val();
		$("#R5").html("");
		$("#R5").append("<option value='ALL'>全部</option>");

		for(var i=0;i<classlist.length;i++)
		{
			if(classlist[i].ChannelCode==cno)
			{
				$("#R5").append("<option value='"+classlist[i].ClassName+"'>"+classlist[i].ClassName+"</option>");
			}
		}
	}
	function loadSelect()
	{
		$("#ServiceType").append("<option value='ALL'>全部</option>");

		var selectArr=new Array();
		for(var i=0;i<chnllist.length;i++)
		{
			if(true)
			{
				selectArr[selectArr.length]=chnllist[i];			
			}
		}
		for(var i=0;i<selectArr.length;i++)
		{
			for(var j=0;j<chnllist.length;j++)
			{
				if(chnllist[j].SerialNo==selectArr[i].ParentChannel)
				{
					$("#ServiceType").append("<option value='"+selectArr[i].SerialNo+"'>"+chnllist[j].ChannelName+"-"+selectArr[i].ChannelName+"</option>");
				}
			}
		}
		loadSubClass();	
		if(kw!=null&&kw!='null'&&kw!='')
		{
			$("#kw").val(kw);
			search();
		}
	}
	   function afterPost(d)
	   {
		   if(d.action=='seller'&&d.operate=='loadclassname')
			{
			   classlist=d.rd.data.classname;
				loadSelect();	
			}
		   if(d.action=='common'&&d.operate=='search')
			{
			   //alert(t(d));
			   $(".section").css('display','');
			   // 附上模板  
			    $("#result1").setTemplateElement("template");  
			    // 给模板加载数据  
			    $("#result1").processTemplate(d.rd.data.contentlist);
			    if(d.rd.data.contentlist.length==0)
			    {
			   		$("#result1").html("未搜到任何结果"); 	
			   	}
			   // 附上模板  
			    $("#result2").setTemplateElement("template2");  
			    // 给模板加载数据  
			    $("#result2").processTemplate(d.rd.data.prdlist);
			    if(d.rd.data.prdlist.length==0)
			    {
			   		$("#result2").html("未搜到任何结果"); 	
			   	}
			}
	   }
function search()
{
	var kw = $("#kw").val();
	if(kw=='')
	{
		alert("请输入关键字");
		return;
	}
	var st = $("#st").find("option:selected").val();
	var ServiceType = $("#ServiceType").find("option:selected").val();
	var R5 = $("#R5").find("option:selected").val();

   	var data = 
   	{
   			kw:kw,
   			st:st,
   			ServiceType:ServiceType,
   			R5:R5
   			};
   	po('common','search',data);
}
	</script>	
		<div class="wrapper page-header">
			<h2 class="pape-title">高级搜索</h2>
		</div>
		
		<div class="wrapper clearfix">
			<div class="form-box advanced-search-form"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">输入查找内容<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="kw">
						<!-- 
						<select name="" id="" class="text-w120">
							<option value="包含全部字词">包含全部字词</option>
							<option value="包含全部字词">包含全部字词</option>
							<option value="包含全部字词">包含全部字词</option>
						</select>
						<select name="" id="" class="text-w120">
							<option value="每页显示15项">每页显示15项</option>
							<option value="每页显示15项">每页显示15项</option>
							<option value="每页显示15项">每页显示15项</option>
						</select>
						 -->
					</li>
					<li class="form-item clearfix"><span class="item-title">查询字词</span>
						<select id="st" class="text-w210">
							<option value="A">全部范围</option>
							<option value="T">位于标题</option>
							<option value="C">位于正文</option>
						</select>
					</li> 
					<li class="form-item clearfix"><span class="item-title">服务类别</span>
						<select id="ServiceType" class="text-w210" onchange="loadSubClass();";>
						</select>
					</li>
					<li class="form-item clearfix"><span class="item-title">二级分类</span>
						<select id="R5" class="text-w210">
						</select>
					</li>
				</ul> 
				<div class="button-box">
					<input value="查    找" onclick="search();" class="submit-button">
				</div>
			</div>
			
			<!--section-->
			<div class="section" style="display:none">
				<div class="section-header">
					<h3 class="section-title">内容搜索结果</h3>
				</div>
				<div class="section-content">
					<div class="table-wrapper advanced-search-table"> 					
					<!-- 输出元素 -->  
					<div id="result1" ></div>
					<textarea id="template" style="display:none">
						<table width="100%" border="0">
							<tr>
								<th scope="col" class="cell-1">网页标题</th>
								<th scope="col" class="cell-3">日期</th>
							</tr>
					{#foreach $T as record} 
							<tr>
								<td class="cell-1"><a href="./content_{$T.record.SerialNo}.html"><span>{$T.record.ContentTitle}</span></a>
								</td>
								<td class="cell-3"><span>{$T.record.IssueDate}</span>
								</td>
							</tr>
						{#/for} 
						</table>
					</textarea> 	
						
					</div>
				</div>
			</div>
			<!--//section-->
			
			<!--section-->
			<div class="section" style="display:none">
				<div class="section-header">
					<h3 class="section-title">产品服务搜索结果</h3>
				</div>
				<div class="section-content">
					<div class="table-wrapper advanced-search-table"> 					
					<!-- 输出元素 -->  
					<div id="result2" ></div>
					<textarea id="template2" style="display:none">
						<table width="100%" border="0">
							<tr>
								<th scope="col" class="cell-2">商户名称</th>
								<th scope="col" class="cell-3">产品/服务名称</th>
								<th scope="col" class="cell-4">价格</th>
								<th scope="col" class="cell-5">折扣</th>
								<th scope="col" class="cell-6">有效期</th>
								<th scope="col" class="cell-7">备注</th>
							</tr>
					{#foreach $T as record} 
							<tr>
								<td class="cell-2"><a href="seller_{$T.record.SellerID}.html"><span>{$T.record.ShotOrgName}</span></a>
								</td>
								<td class="cell-3"><span>{$T.record.PrdName}</span>
								</td>
								<td class="cell-4"><span>{$T.record.PrdPrice}</span>
								</td>
								<td class="cell-5"><span>{$T.record.Discount}</span>
								</td>
								<td class="cell-6"><span>{$T.record.EffEndDate}</span>
								</td>
								<td class="cell-7"><span>{$T.record.Remark}</span>
								</td>
							</tr>
						{#/for} 
						</table>
					</textarea> 
					</div>
				</div>
			</div>
			<!--//section-->
		</div>
		
		
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
