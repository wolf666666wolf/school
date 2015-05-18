<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>活动详情</title>
	</head>
	<body>	
<%@include file="./plugin_header.jsp"%>
<%@include file="./userchkA.jsp"%>	
<script type="text/javascript">
var ActID='<%=request.getParameter("id")%>';
if(ActID==null||ActID=='null')ActID='';
$(function(){
	if(ActID!=null&&ActID!=''&&ActID!='null')
	loadInfo();	
	$("#StartDate").datetimepicker({dateFormat:'yy-mm-dd',changeYear: true,changeMonth: true});
	$("#EndDate").datetimepicker({dateFormat:'yy-mm-dd',changeYear: true,changeMonth: true});
	$("#RegEndDate").datepicker({dateFormat:'yy-mm-dd',changeYear: true,changeMonth: true});

});
function loadInfo()
{
	var data = 
   	{
   			ActID:ActID
   			};
   	po('ass','queryact',data);
}
function save()
{
	var AssID=userjson.AssID;
	var Title = $("#Title").val();
	var StartDate = $("#StartDate").val();
	var EndDate = $("#EndDate").val();
	var ActFee = $("#ActFee").val();
	var RegEndDate = $("#RegEndDate").val();		   	
	var ActRule = $("#ActRule").val();		   	
	var ActContent = $("#ActContent").val();		   	
	var ActGift = $("#ActGift").val();		   	
	var Phone = $("#Phone").val();
	var R3 = $("#R3").val();
	if(Phone=='')
	{
		alert('请填写联系人电话');
		return;
	}

	   if(!emailPhone(Phone))
		  {
			alert('请输入正确的联系人电话');
			return;
		   
		  }
	var AcPeople = $("#AcPeople").val();	
	var StartTime="";
	var EndTime="";
	if(StartDate!='')
		{
			StartTime=StartDate.split(" ")[1];
			StartDate=StartDate.split(" ")[0];
		
		}
	if(EndDate!='')
		{
			EndTime=EndDate.split(" ")[1];
			EndDate=EndDate.split(" ")[0];

		}
	if(Title=='')
	{
		alert("标题不能为空");
		return;
	}

   	var data = 
   	{
   			ActID:ActID,
   			AssID:AssID,
   			Title:Title,
   			StartDate:StartDate,
   			StartTime:StartTime,
   			EndDate:EndDate,
   			EndTime:EndTime,
   			ActFee:ActFee,
   			RegEndDate:RegEndDate,
   			RegEndTime:RegEndDate,
   			ActRule:ActRule,
   			ActContent:ActContent,
   			ActGift:ActGift,
   			Phone:Phone,
   			AcPeople:AcPeople,
   			R3:R3
   			};
   	po('ass','addact',data);

}

function afterPost(d)
{
	if(d.action=='ass'&&d.operate=='addact')
	{
   		alert(d.rd.msg);	

		if(d.rd.succ)
		{
			window.location.href="./ass_actlist.html";
		}
	}
	if(d.action=='ass'&&d.operate=='queryact')
	{
		if(d.rd.succ)
		{		
			var act = d.rd.data.sdaa;

		    $("#Title").val(act.Title);
		    $("#StartDate").val(act.StartDate+" "+act.StartTime);
		    $("#EndDate").val(act.EndDate+" "+act.EndTime);
		    $("#ActFee").val(act.ActFee);
		    $("#RegEndDate").val(act.RegEndDate);
		    $("#ActRule").val(act.ActRule);
		    $("#ActContent").val(act.ActContent);
		    $("#ActGift").val(act.ActGift);
		    $("#Phone").val(act.Phone);
		    $("#AcPeople").val(act.AcPeople);
		}
		else
		{
	   		alert(d.rd.msg);		
		}
	}
}
</script>
		<!--wrapper-->
		<div class="wrapper clearfix"> 
			<div class="form-box"> 
				<ul class="form-text">
					<li class="form-item clearfix"><span class="item-title">活动主题<i class="icon">*</i ></span><input type="text" id="Title" class="form-input text-w210"/></li>
					<li class="form-item clearfix"><span class="item-title">创建人<i class="icon">*</i ></span><span><%=sda.getShotOrgName() %></span></li>
					<li class="form-item clearfix"><span class="item-title">开始时间</span><input readonly type="text" id="StartDate" class="form-input text-w210"/></li>
					<li class="form-item clearfix"><span class="item-title">结束时间</span><input readonly type="text" id="EndDate" class="form-input text-w210"/></li>
					<li class="form-item clearfix"><span class="item-title">活动地点</span><input type="text" id="R3" class="form-input text-w210" value=""/></li>
					<li class="form-item clearfix"><span class="item-title">交纳费用</span><input type="text" id="ActFee" class="form-input text-w40" value="0"/></li>
					<li class="form-item clearfix"><span class="item-title">报名截止日期</span><input readonly type="text" id="RegEndDate" class="form-input text-w210"/></li>
					<li class="form-item clearfix"><span class="item-title">活动规则</span><textarea id="ActRule" class="text-w210"></textarea><span class="tip">限150字</span></li>
					<li class="form-item clearfix"><span class="item-title">活动内容<i class="icon">*</i ></span></span><textarea id="ActContent" class="text-w210"></textarea><span class="tip">限150字</span></li>
					<li class="form-item clearfix"><span class="item-title">奖品赠品</span></span><textarea id="ActGift" class="text-w210"></textarea><span class="tip">限150字</span></li>
					<li class="form-item clearfix"><span class="item-title">联系人电话<i class="icon">*</i ></span><input type="text" id="Phone" class="form-input text-w210"/></li>
					<li class="form-item clearfix"><span class="item-title">活动计划人数</span><input type="text" id="AcPeople" class="form-input text-w40"/></li>
				</ul>
				
				<div class="button-box"> 
					<input id="Button1" type="button" value=" 保 存 " onclick="save();" class="submit-button"><br />
					<input id="Button1" type="button" value=" 返 回 " onclick="window.location.href='ass_actlist.html';" class="submit-button">
				</div>
			</div>
		</div>
<%@include file="./plugin_links.jsp"%>
<%@include file="./plugin_footer.jsp"%>
	</body>
</html>
