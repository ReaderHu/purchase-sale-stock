<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/10 0010
  Time: 20:48
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
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="${pageContext.request.contextPath}/user/updateuser" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label>用户名称：</label>
                    <span >${user.userName}</span>
                    <input type="hidden" name="uuId" value="${user.uuId}">
                </div>
                <div>
                    <label for="telPhone">用户电话：</label>
                    <input type="text" name="telPhone" id="telPhone" placeholder="${user.telPhone}" />
                    <span >*</span>
                </div>
                <div>
                    <label for="department">用户部门：</label>
                    <input type="text" name="department" id="department" placeholder="${user.department}"/>
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存"/>
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<jsp:include page="/view/common/footer.jsp"/>
</div>
</body>
</html>
