<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的收藏</title>
	</head>
	<body>
<%@include file="./plugin_header.jsp"%>
<%@include file="./userchkU.jsp"%>
<script>
$(function(){
	loadList();		
});
function fav(id)
{
	var data = 
   	{
   			PrdID:id,
   			UserID:userjson.UserID
   			};
   	po('product','favorite',data);
}
function loadList()
{
	var UserID = userjson.UserID;
	var data = 
   	{
			UserID:UserID
   			};
   	po('user','getfavoritelist',data);
}

function afterPost(d)
{
	if(d.action=='user'&&d.operate=='getfavoritelist')
	{
		if(d.rd.succ)
		{
			 // 附上模板  
		    $("#result1").setTemplateElement("template");  
		    // 给模板加载数据  
		    $("#result1").processTemplate(d.rd.data.favoritelist);
		}
	}
	if(d.action=='product'&&d.operate=='favorite')
	{
		alert(d.rd.msg);
		loadList();
	}
}
</script>
		<div class="wrapper clearfix">
			<!--section-->
			<div class="section">
				<div class="section-header">
					<h3 class="section-title">商品与服务项目信息</h3>
				</div>
				<div class="section-content">
					<div class="table-wrapper my-follow-table"> 
					<!-- 输出元素 -->  
					<div id="result1" ></div>		
					</div>
					<textarea id="template" style="display:none">  			 
					{#foreach $T as record} 
						<table width="100%" border="0">
						  <tr>
						    <th scope="col" class="cell-1">商户名称</th>
						    <th scope="col" class="cell-2">商品/服务名称</th>
						    <!-- 
						    <th scope="col" class="cell-3">类型</th>
						     -->
						    <th scope="col" class="cell-4">价格</th>
						    <th scope="col" class="cell-5">折扣</th>
						    <th scope="col" class="cell-6">有效期</th>
						    <th scope="col" class="cell-7">备注</th> 
						    <th rowspan="2" scope="col" class="cell-8">
						    {#if $T.AuthFlag=='Y'}
						    <a href="order_{$T.record.PrdID}.html" class="join-link">下订单</a>
						     {#/if}
						    <a href="javascript:fav('{$T.record.PrdID}');" class="join-link">取消收藏</a></th>
						  </tr>
						  <tr>
						    <td class="cell-1"><a href="seller_{$T.record.SellerID}.html"><span>{$T.record.ShotOrgName}</span></a></td>
						    <td class="cell-2"><span>{$T.record.PrdName}</span></td>
						    <!-- 
						    <td class="cell-3"><span></span></td>
						     -->
						    <td class="cell-4"><span>{$T.record.PrdPrice}</span></td>
						    <td class="cell-5"><span>{$T.record.Discount}</span></td>
						    <td class="cell-6"><span>{$T.record.EffEndDate}</span></td>
						    <td class="cell-7"><span>{$T.record.Remark}</span></td>  
						  </tr> 
						</table> 
						{#/for} 
						</textarea> 
					</div>
				</div>
			</div>
			<!--//section-->
			
			<div class="button-box">  
				<input id="Button1" type="button" value="返回" onclick="window.location.href='user_center.html';" class="submit-button">
			</div>
		</div>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
