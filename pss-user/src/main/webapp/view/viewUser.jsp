<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/10 0010
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>工厂管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css"/>
</head>
<body>
<!--头部-->
<jsp:include page="/view/common/header.jsp" flush="true"/>
<!--主体内容-->
<section class="publicMian">
    <jsp:include page="/view/common/menu.jsp" />
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户信息查看页面</span>
        </div>
        <div class="providerView">
            <p><strong>用户编号：</strong><span>${user.uuId}</span></p>
            <p><strong>用户名称：</strong><span>${user.userName}</span></p>
            <p><strong>用户性别：</strong><span>${user.userAge}</span></p>
            <p><strong>出生年龄：</strong><span>${user.userAge}</span></p>
            <p><strong>用户电话：</strong><span>${user.telPhone}</span></p>
            <p><strong>用户部门：</strong><span>${user.department}</span></p>

            <a href="${pageContext.request.contextPath}/user/getList">返回</a>
        </div>
    </div>
</section>
<jsp:include page="/view/common/footer.jsp"/>
</div>
</body>
</html>
