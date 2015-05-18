<%@page import="com.sinosoft.lis.schema.*"%>
<%@page import="com.sinosoft.lis.db.*"%>
<%@page import="com.sinosoft.lis.pubfun.*"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no,target-densitydpi=device-densitydpi" />
    <title>关注</title>
    <link rel="stylesheet" href="css/common.css">

</head>
<style>


</style>
<%
	String SellerID = request.getParameter("id");
	SDSellerSchema s = new SDSellerDB().executeQuery("select * from sdseller where sellerid = '"+SellerID+"'").get(1);
%>
<body>

<header id="title">
    <div class="select-back">
    </div>
    <span id="SellerName"><%=s.getShotOrgName()%></span>
    <div class="speak">
    </div>
</header>
<main class="business" style="padding-bottom: 2em;padding-top: /*13.2em*/132px;font-size:2em;">
点击  <span onclick="window.location.href='./view-goods.html?id=<%=request.getParameter("id")%>'"><a href="weixin://addfriend/wuzhou360">这里</a></span>  关注微信号
</main>
</body>
</html>