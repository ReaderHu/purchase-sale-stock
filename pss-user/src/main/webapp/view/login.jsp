<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/4 0004
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>登录页面</title>
    <%-- 引入jquery--%>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/static/js/jquery-1.12.4.min.js"></script>
    <%--  引入样式--%>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/static/css/style.css"/>
</head>
<body class="login_bg">
<section class="loginBox">
    <header class="loginHeader">
        <h1>工厂管理系统用户总计${userCount}人</h1>
    </header>
    <section class="loginCont">
        <form class="loginForm" action="${pageContext.servletContext.contextPath}/user/login" method="post">
            <div class="inputbox">
                <label for="user">手机号：</label>
                <input id="user" type="text" name="telPhone" placeholder="请输入登录手机号" required/>
            </div>
            <div class="inputbox">
                <label for="mima">密码：</label>
                <input id="mima" type="password" name="password" placeholder="请输入密码" required/>
            </div>
            <div class="subBtn">
                <input type="submit" value="登录" />
                <input type="reset" value="重置"/>
            </div>

        </form>
    </section>
</section>

</body>
</html>
