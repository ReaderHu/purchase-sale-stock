<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/6 0006
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
</head>
<body>
<div class="left">
<h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
    <nav>
        <ul class="list">
            <li ><a href="billList.html">账单管理</a></li>
            <li><a href="providerList.html">供应商管理</a></li>
            <c:if test="${sessionUser.adminFlag == 0 }">
            <li><a href="${pageContext.request.contextPath}/user/getList">用户管理</a></li>
            </c:if>
            <li><a href="${pageContext.request.contextPath}/user/toPassword">密码修改</a></li>
            <li><a href="${pageContext.request.contextPath}/login/loginout">退出系统</a></li>
        </ul>
    </nav>
</div>
</body>
</html>
