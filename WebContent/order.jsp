<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>下订单</title>
	</head>
	<body>
<%@include file="./plugin_header.jsp"%>
<%@include file="./userchkU.jsp"%>		 
<script>
var PrdID='<%=request.getParameter("pid")%>';

$(function(){
	$("#ArriveDate").datetimepicker({dateFormat:'yy-mm-dd',changeYear: true,changeMonth: true});
	loadPrdList();
});
function loadPrdList()
{
	var data = 
   	{
   			PrdID:PrdID
   			};
   	po('order','prdlist',data);
}
function comeChange()
{
	var ComeFlag = $("#ComeFlag").find("option:selected").val();
	if(ComeFlag=='否')
	{
		$("#comedis1").css('display','none');
		$("#comedis2").css('display','none');
	}
	else
	{
		$("#comedis1").css('display','');
		$("#comedis2").css('display','');		
	}
}
function sub()
{
	var Owner=$("#Owner").val();
	var ArriveDate=$("#ArriveDate").val();
	//var OCount=$("#OCount").val();
	var ComeFlag = $("#ComeFlag").find("option:selected").val();
	var R1 = $("#R1").find("option:selected").val();
	var Requirement=$("#Requirement").val();
	var Address=$("#Address").val();
	var Phone=$("#Phone").val();
	var Tel=$("#Tel").val();

	if(Owner=='')
	{
		alert("请输入订单人姓名");
		return;
	}
	if(Phone==''&&Tel=='')
	{
		alert("至少填写一个联系电话");
		return;
	}
	if(ComeFlag!='是')
	{
		R1='';
		Address='';
	}
	if(Phone!='')
	{
		if(!emailPhone(Phone))
		  {
			alert('请输入正确的手机号码');
			return;
		   
		  }		
	}

   	var data = 
   	{
   			PrdID:PrdID,
   			Owner:Owner,
   			ArriveDate:ArriveDate,
   			//OCount:OCount,
   			ComeFlag:ComeFlag,
   			Requirement:Requirement,
   			Address:Address,
   			Phone:Phone,
   			Tel:Tel,
   			R1:R1,
   			UserID:userjson.UserID
   			};
   	po('order','sendorder',data);
}

function afterPost(d)
{
	if(d.action=='order'&&d.operate=='sendorder')
	{
   		alert(d.rd.msg);	

		if(d.rd.succ)
		{
			window.location.href="./user_orderlist.html";
		}
	}
	if(d.action=='order'&&d.operate=='prdlist')
	{
		// 附上模板  
	    $("#result1").setTemplateElement("template");  
	    // 给模板加载数据  
	    $("#result1").processTemplate(d.rd.data.sdps);
	}
}
</script>
		<div class="wrapper page-header">
			<h2 class="pape-title">下订单服务</h2>
		</div>
		<!--section-->
		
		<div class="wrapper clearfix">
			<div class="section">
				<div class="section-header">
					<h3 class="section-title">订单信息</h3>
				</div>
				<div class="section-content">
					<div class="table-wrapper merchant-detail-table"> 
					<!-- 输出元素 -->  
					<div id="result1" ></div>
					<textarea id="template" style="display:none">  				
						<table width="100%" border="0">
						  <tr>
						    <th scope="col" class="cell-2" style="width:50px;">项目编号</th>
						    <th scope="col" class="cell-2" style="width:10px;">商品/服务名称</th>
						    <th scope="col" class="cell-3" style="width:40px;">价格</th>
						    <th scope="col" class="cell-4" style="width:40px;">折扣</th>
						    <th scope="col" class="cell-5" style="width:50px;">有效期</th>
						    <th scope="col" class="cell-6" style="width:100px;">备注</th>
						    <th scope="col" class="cell-6" style="width:40px;">数量</th>
						  </tr>			 
					{#foreach $T as record} 
						  <tr>
						    <td class="cell-2"><span>{$T.record.PrdID}</span></td>
						    <td class="cell-2"><span><a href="./detail_{$T.record.PrdID}.html">{$T.record.PrdName}</a></span></td>
						    <td class="cell-3"><span>{$T.record.PrdPrice}</span></td>
						    <td class="cell-4"><span>{$T.record.Discount}</span></td>
						    <td class="cell-5"><span>{$T.record.EffEndDate}</span></td>
						    <td class="cell-6" style="width:300px;"><span>{$T.record.Remark}</span></td>  
						    <td class="cell-6"><span>{$T.record.R9}</td>
						  </tr> 
					{#/for}	
						</table>  		
					</textarea> 	
					</div>
				</div>
			</div>
		</div>
			<!--//section-->
		<!--wrapper-->
		<div class="wrapper clearfix">  
			<!--form-box-->
			<div class="form-box"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">订单人姓名<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Owner"> 
					</li>
					<li class="form-item clearfix"><span class="item-title">预计到达时间</span>
						<input type="text" class="form-input text-w210" id="ArriveDate" readonly>
					</li>
					<li style="display:none" class="form-item clearfix"><span class="item-title">内容（数量）<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="OCount"> 
					</li>
					<li class="form-item clearfix"><span class="item-title">是否上门服务</span>
						<select  class="text-w100" id="ComeFlag" onchange="comeChange()">
							<option value="是">是</option>
							<option value="否">否</option>
						</select> 
					</li> 
					<li class="form-item clearfix" id="comedis1">
						<span class="item-title">地址区域</span>
						<select  class="text-w210" id="R1">
							<option value="管理办公区">管理办公区</option> 
							<option value="公共教学区">公共教学区</option> 
							<option value="各院系教学区">各院系教学区</option> 
							<option value="生活服务区">生活服务区</option> 
							<option value="校企产业">校企产业</option> 
							<option value="清华科技园">清华科技园 </option> 
							<option value="体育场馆">体育场馆</option> 
							<option value="主楼">主楼</option> 
							<option value="大礼堂">大礼堂</option> 
							<option value="老图书馆">老图书馆</option> 
							<option value="新图书馆">新图书馆</option> 
							<option value="蓝旗营住宅区">蓝旗营住宅区</option> 
							<option value="东小区">东小区</option> 
							<option value="西小区">西小区</option> 
							<option value="中区">中区</option> 
							<option value="东南小区">东南小区</option> 
							<option value="西北小区">西北小区</option> 
							<option value="西南小区">西南小区</option> 
							<option value="老年公寓">老年公寓</option> 
							<option value="荷清苑小区">荷清苑小区</option> 
							<option value="青年公寓">青年公寓</option> 
							<option value="其他">其他</option> 
						</select> 
					</li>
					<li class="form-item clearfix" id="comedis2">
						<span class="item-title">详细地址</span>
						<textarea  id="Address" class="text-w210"></textarea>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">详细要求</span>
						<textarea  id="Requirement" class="text-w210"></textarea>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">联系电话(手机)</span>
						<input type="text" class="form-input text-w210" style="display:none" id="Tel"><input type="text" id="Phone" class="form-input text-w210">
					</li>
				</ul>  
				
				<div class="button-box">   
					<input type="button" onclick="sub();" value="提交" class="submit-button">
				</div>
			</div>
			<!--//form-box--> 
		</div>
		<!--//wrapper-->
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
