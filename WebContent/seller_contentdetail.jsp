<!DOCTYPE html>
<html>
	<head>
		<title>内容发布</title>
	    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	    <script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="ueditor.all.min.js"> </script>
	</head>
	<body>	
<%@include file="./plugin_header.jsp"%>
<%@include file="./userchkS.jsp"%>
<script type="text/javascript">
var ID='<%=request.getParameter("id")%>';
$(function(){
	loadSelect();
	if(ID!=null&&ID!=''&&ID!='null')
	loadInfo();		
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
		if(chnllist[i].R3=='3')
		{
			if((classtype=='1'&&chnllist[i].R5=='Y')||(classtype=='2'&&chnllist[i].R6=='Y'))
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
				$("#ChannelCode").append("<option value='"+selectArr[i].SerialNo+"'>"+selectArr[i].ChannelName+"</option>");
			}
		}
	}
}
function loadInfo()
{
	var data = 
   	{
   			ID:ID
   			};
   	po('seller','querycontent',data);
}
function save()
{
	var SellerID=userjson.SellerID;
	var ContentTitle = $("#ContentTitle").val();
	var ChannelCode = $("#ChannelCode").find("option:selected").val();
	if(ContentTitle=='')
	{
		alert("标题不能为空");
		return;
	}
	if(ChannelCode=='')
	{
		alert("栏目不能为空");
		return;
	}
	fm.ContentHtml.value=ue.getContent();
	fm.ContentTxt.value=ue.getContentTxt();
	var ContentHtml=ue.getContent();
	var ContentTxt=ue.getContentTxt();
	if(ContentTxt=='')
	{
		alert("内容不能为空");
		return;
	}

   	var data = 
   	{
   			ContentTitle:ContentTitle,
   			ChannelCode:ChannelCode,
   			ContentHtml:fm.ContentHtml.value.replace("\n","<br/>"),
   			ContentTxt:fm.ContentTxt.value.replace("\n","<br/>"),
   			ContentFrom:SellerID,
   			SerialNo:ID
   			};
   	po('seller','addcontent',data);

}

function afterPost(d)
{
	if(d.action=='seller'&&d.operate=='addcontent')
	{
   		alert(d.rd.msg);	

		if(d.rd.succ)
		{
			window.location.href="./seller_content.html";
		}
	}
	if(d.action=='seller'&&d.operate=='querycontent')
	{
		if(d.rd.succ)
		{		
			var sdc = d.rd.data.sdc;
			
		    $("#ContentTitle").val(sdc.ContentTitle);
		    $("#ChannelCode").val(sdc.ChannelCode);
			setTimeout(function(){ue.setContent(sdc.ContentHtml, false);},1000);
		}
		else
		{
	   		alert(d.rd.msg);		
		}
	}
}
</script>
<form method=post name=fm target="fraSubmit">
<input name="ContentHtml" type="hidden"/>
<input name="ContentTxt" type="hidden"/>
</form>
		<!--wrapper-->
		<div class="wrapper clearfix"> 
			<div class="form-box"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">标题<i class="icon">*</i></span>
						<input type="text" class="form-input text-w210" id="ContentTitle" style="width:70%;"><span class="tip"></span>
					</li>
					<li class="form-item clearfix"><span class="item-title">栏目<i class="icon">*</i></span>
						<select class="text-w210" id="ChannelCode">
						</select> 
					</li>
				</ul>
				
<!-- 加载编辑器的容器 -->
    <script id="container" name="content" type="text/plain">

    </script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>
				<div class="button-box"> 
					<input id="Button1" type="button" value=" 保 存 " onclick="save();" class="submit-button"><br />
					<input id="Button1" type="button" value=" 返 回 " onclick="window.location.href='seller_content.html';" class="submit-button">
				</div>
			</div>
		</div>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
