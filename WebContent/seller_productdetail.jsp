<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商家中心</title>
	    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	    <script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="ueditor.all.min.js"> </script>
	</head>
	<body>	
<%@include file="./plugin_header.jsp"%>
<%@include file="./userchkS.jsp"%>	
<script type="text/javascript">
var PrdID='<%=request.getParameter("id")%>';
$(function(){
	loadSelect();
	if(PrdID!=null&&PrdID!=''&&PrdID!='null')
	loadPrdInfo();		
	$("#EffEndDate").datepicker({dateFormat:'yy-mm-dd',changeYear: true,changeMonth: true});

});
function loadSelect()
{
	var authflag = userjson.AuthFlag;
	var classtype = '1';
	if(authflag!='Y')
		classtype='2';
	var selectArr=new Array();
	for(var i=0;i<chnllist.length;i++)
	{
		if(chnllist[i].R3==classtype)
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
				selectArr[i].ChannelName=chnllist[j].ChannelName+"-"+selectArr[i].ChannelName;
				$("#PrdType").append("<option value='"+selectArr[i].SerialNo+"'>"+selectArr[i].ChannelName+"</option>");
			}
		}
	}
}

//图片上传
var imgurl="";
function submits2()
{
	form2.action="upload.jsp";
	form2.submit();
}
function afterUpload(a,b,c)
{
	alert(b);
	if(a=='Succ')
	{
		$("#prd_img").attr("src","./sellerimg/temp/"+c+".jpg?"+new Date().getMilliseconds());
		imgurl=c;
	}
		//alert(a+"|"+b+"|"+c);
}
function loadPrdInfo()
{
	var data = 
   	{
   			PrdID:PrdID
   			};
   	po('seller','queryprd',data);
}
function save()
{
	var SellerID=userjson.SellerID;
	var PrdName = $("#PrdName").val();
	var PrdPrice = $("#PrdPrice").val();
	var Discount = $("#Discount").val();
	var EffEndDate = $("#EffEndDate").val();
	var PrdNum = $("#PrdNum").val();
	var Remark = $("#Remark").val();		   	
	var PrdType = $("#PrdType").find("option:selected").val();
	if(PrdName=='')
	{
		alert("名称不能为空");
		return;
	}
	if(PrdPrice=='')
	{
		alert("价格不能为空");
		return;
	}
	if(PrdNum=='')
	{
		alert("商品数量不能为空");
		return;
	}	
	if(Discount=='')
	{
		alert("折扣不能为空");
		return;
	}
    var reg = new RegExp("^[0-9]*$");  
	if(!Discount.match(reg)){

		alert("折扣请输入1-100之间的数字");
		return;
	}
	if(Discount*1<1||Discount*1>100){

		alert("折扣请输入1-100之间的数字");
		return;
	}
	if(Remark=='')
	{
		alert("简介不能为空");
		return;
	}
	fm.R4.value=ue.getContent();
	fm.R5.value=ue2.getContent();
//fm.R5.value.replace("\n","<br/>")
   	var data = 
   	{
   			PrdID:PrdID,
   			SellerID:SellerID,
   			PrdName:PrdName,
   			PrdPrice:PrdPrice,
   			Discount:Discount,
   			R15:PrdNum,
   			R14:imgurl,
   			EffEndDate:EffEndDate,
   			Remark:Remark,
   			PrdType:PrdType,
   			R4:escape(fm.R4.value),
   			R5:escape(fm.R5.value)
   			};
//   	alert(escape(fm.R4.value));
//   	alert(fm.R5.value);
   	po('seller','addprd',data);

}

function afterPost(d)
{
	if(d.action=='seller'&&d.operate=='addprd')
	{
   		alert(d.rd.msg);	

		if(d.rd.succ)
		{
			window.location.href="./seller_productlist.html";
		}
	}
	if(d.action=='seller'&&d.operate=='queryprd')
	{
		if(d.rd.succ)
		{		
			var prd = d.rd.data.sdp;
		    $("#PrdName").val(prd.PrdName);
		    $("#PrdType").val(prd.PrdType);
		    $("#PrdPrice").val(prd.PrdPrice);
		    $("#Discount").val(prd.Discount);
		    $("#EffEndDate").val(prd.EffEndDate);
		    $("#PrdNum").val(prd.R15);
		    $("#Remark").val(prd.Remark);
		    imgurl=prd.R14;
		    $("#prd_img").attr("src","./sellerimg/temp/"+prd.R14+".jpg?"+new Date().getMilliseconds());
			
		    setTimeout(function(){ue.setContent(prd.R4, false);},1000);
			setTimeout(function(){ue2.setContent(prd.R5, false);},1000);

		}
		else
		{
	   		alert(d.rd.msg);		
		}
	}
}



</script>

<form method=post name=fm target="fraSubmit">
<input name="R4" type="hidden"/>
<input name="R5" type="hidden"/>
</form>
		<!--wrapper-->
		<div class="wrapper clearfix"> 
			<div class="form-box"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">名称<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="PrdName"><span class="tip"></span>
					</li>
					<!-- 
					<li class="form-item clearfix"><span class="item-title">产品类型<i class="icon">*</i></span>
						<select class="text-w210" id="PrdType">
						</select> 
					</li> 
					 -->
					<li class="form-item clearfix"><span class="item-title">价格<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="PrdPrice"><span class="tip">单位：元</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">折扣<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="Discount" value='100'><span class="tip">折扣（%），请录入1-100的数字</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">有效日期(截止)<i class="icon"></i></span>
						<input type="text" class="form-input text-w210" id="EffEndDate"><span class="tip">不录入则为长期</span>
					</li>
					<li class="form-item clearfix"><span class="item-title">商品数量<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="PrdNum" value='100'><span class="tip">商品数量为0，则不能下单;如商品数量为9999（或-1）就表示无限制</span>
					</li>					
					<li class="form-item clearfix"><span class="item-title">简介<i class="icon">*</i></span>
						<textarea id="Remark" class="text-w210" style="height:180px;"></textarea>
						 <span class="tip">限600字</span>
					</li>
					<li class="form-item clearfix">
						<span class="item-title">图片</span>						
					 	<form action='' name="form2" method='post'	enctype='multipart/form-data' target="img_frame">
						<input type='file' name='upfile' id='upfile' >
						
						<button class="button button_80x30" onclick="submits2()">上传图片</button>
						</form>
						<iframe style="display: none;" name="img_frame" id="productpic"></iframe>
						<span class="tip">形象图片，大门、门面、前台（Logo标志为佳，480*315像素）</span><br />
						<img id="prd_img" src="" alt="" width="480" height="315">
					</li>					
					<li class="form-item clearfix"><span class="item-title">详细信息</span>
					</li>
					<li class="form-item clearfix">
						<!-- 加载编辑器的容器 -->
				   	 <script id="container" name="content" type="text/plain">

    					</script>
					    <!-- 实例化编辑器 -->
					    <script type="text/javascript">
					        var ue = UE.getEditor('container');
					    </script>
					</li>
					<li class="form-item clearfix"><span class="item-title">服务保障</span>
					</li>
					<li class="form-item clearfix">
						<!-- 加载编辑器的容器 -->
				   	 <script id="container2" name="content" type="text/plain">

    					</script>
					    <!-- 实例化编辑器 -->
					    <script type="text/javascript">
					        var ue2 = UE.getEditor('container2');
					    </script>
					</li>					
					<li style="display:none;" class="form-item clearfix"><span class="item-title">图片(最多5张)</span>
					</li>
					<li style="display:none;" class="form-item clearfix">
						<table border=1px>
							<tr>
								<td id="tdimg1"></td>
								<td id="tdimg2"></td>
								<td id="tdimg3"></td>
								<td id="tdimg4"></td>
								<td id="tdimg5"></td>
							</tr>
							<tr>
								<td id="tdbtn1"><input type="button" value="删除" /></td>
								<td id="tdbtn2"><input type="button" value="删除" /></td>
								<td id="tdbtn3"><input type="button" value="删除" /></td>
								<td id="tdbtn4"><input type="button" value="删除" /></td>
								<td id="tdbtn5"><input type="button" value="删除" /></td>
							</tr>
						</table> 
					</li>
				</ul>
				<div class="button-box">
					<input id="Button1" type="button" value=" 保 存 " onclick="save();" class="submit-button"><br />
					<input id="Button1" type="button" value=" 返 回 " onclick="window.location.href='seller_productlist.html';" class="submit-button">
				</div>
			</div>
		</div>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
