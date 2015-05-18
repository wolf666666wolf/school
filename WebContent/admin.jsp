<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
    <script language="JavaScript">
    <!--
        //判断页面是否初始化完成
        var achieveEX = false;
        var achieveVD = false;
        //设置主窗口属性, 用于代码选择功能查找主窗口, 缓存数据
        window.name = "Lis";
        try
        {
            //window.resizeTo(screen.availWidth, screen.availHeight);
        }
        catch (ex) {}
        window.focus();
    -->
    </script>
</head>
<frameset name="fraMain" framespacing="0" frameborder="no" rows="0,0,0,0,*" cols="*" border="1">
    <!-- 保存客户端变量的区域 -->
    <frame name="VD" src="common/cvar/CVarData.html" noresize />
    <!-- 实现服务器交户的区域 -->
    <frame name="EX" src="common/cvar/CExec.jsp" noresize />
    <!-- 提交客户端查询的区域 -->
    <frame name="fraSubmit" src="about:blank" scrolling="no" noresize />
    <!-- 顶部显示标题栏的区域 -->
    <frame name="fraTitle" src="logon/Title.jsp" scrolling="no" noresize />
    <!-- 显示菜单和交互的区域 -->
    <frameset name="fraSet" framespacing="0" frameborder="no" rows="*" cols="0%,*,0%" border="1">
        <!-- 显示菜单的区域 -->
        <frameset name="fraMenuMain" framespacing="0" frameborder="no" rows="25,*" border="1">
            <frame name="fraMenuTop" src="logon/menutop.jsp" scrolling="no" noresize />
            <frame name="fraMenu" src="about:blank" scrolling="auto" noresize />
        </frameset>
        <!-- 实现交互的区域 -->
        <frameset name="fraTalk" framespacing="0" frameborder="no" rows="0,*" border="1">
            <frame name="fraQuick" src="logon/quick.jsp" scrolling="no" noresize />
            <frameset name="fraTalkCol" framespacing="0" frameborder="no" cols="0,*" border="1">
                <frame name="fraPic" src="about:blank" scrolling="auto" noresize />
                <frame name="fraInterface" src="logon/main.jsp" scrolling="auto" noresize />
            </frameset>
        </frameset>
        <!-- 下一步操作区域 -->
        <frame name="fraNext" src="about:blank" scrolling="auto" noresize />
    </frameset>
</frameset>
<noframes>
    <body bgcolor="#FFFFFF">
        <br><br><br><br><br>
        <center>
            <font size="2">对不起，您的浏览器不支持框架网页。</font>
            <br><br>
            <font size="2">请使用 IE 5.0 或其以上浏览本系统。</font>
        </center>
    </body>
</noframes>
</html>
