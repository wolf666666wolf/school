<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>投票调查</title>
    <script src="js/jquery-1.10.1.min.js"></script>
    <script src="js/common.js"></script>
    <script src="static/vote.js?t=<%=System.currentTimeMillis()%>"></script>
</head>
<body>
<input type='button' value='提交' onclick='sug()'/>
</body>
</html>
<script>
alert(t(votelist));
function sug()
{
	var data = 
	{
			UserType:'UserType',
			NickName:'NickName',
			Title:'Title',
			Phone:'Phone',
			Com:'Com',
			Content:'Content'
			};
	po('com','sug',data);

}
function afterPost(d)
{
	if(d.action=='com'&&d.operate=='sug')
	{
		alert(d.rd.msg);
	}
}
</script>
