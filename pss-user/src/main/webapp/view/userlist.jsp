<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/6 0006
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>工厂管理系统</title>
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
            <span>用户管理页面</span>
        </div>
        <div class="search">
            <span>用户名：</span>
            <input type="text" placeholder="请输入用户名"/>
            <input type="button" value="查询"/>
            <a href="userAdd.html">添加用户</a>
        </div>
        <!--用户-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="15%">用户编码</th>
                <th width="20%">用户名称</th>
                <th width="10%">性别</th>
                <th width="10%">年龄</th>
                <th width="10%">电话</th>
                <th width="10%">用户类型</th>
                <th width="25%">操作</th>
            </tr>
            <c:forEach items="${userList}" var="user" varStatus="index">
                <tr>
                    <td>${user.uuId}</td>
                    <td>${user.userName}</td>
                    <td><c:if test="${user.userSex == 1}">男</c:if><c:if test="${user.userSex ==0}">女</c:if></td>
                    <td>${user.userAge}</td>
                    <td>${user.telPhone}</td>
                    <td>${user.department}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/user/getUserbyId?uuId=${user.uuId}&view=viewUser"><img src="${pageContext.request.contextPath}/static/img/read.png" alt="查看" title="查看"/></a>
                        <a href="${pageContext.request.contextPath}/user/getUserbyId?uuId=${user.uuId}&view=userUpdate"><img src="${pageContext.request.contextPath}/static/img/xiugai.png" alt="修改" title="修改"/></a>
                        <a href="#" class="removeUser" onclick="deleteUser(${user.uuId})"><img src="${pageContext.request.contextPath}/static/img/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<jsp:include page="/view/common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/js.js"></script>
<script src="${pageContext.request.contextPath}/static/js/time.js"></script>
<script type="text/javascript">

    function deleteUser(uuid) {
          $("#yes").attr("href","${pageContext.request.contextPath}/user/deluset?uuId=" + uuid);
    }

</script>
</body>
</html>
