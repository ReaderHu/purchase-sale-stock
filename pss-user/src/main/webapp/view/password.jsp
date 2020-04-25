
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/11 0011
  Time: 22:43
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
            <span>密码修改页面</span>
        </div>
        <div class="providerAdd">
            <form id="from" action="" method="post">

                <%--管理员才有的权限去修改所有用户的密码--%>
                <c:if test="${sessionUser.adminFlag == 0}" >
                    <div>
                        <label>选择员工：</label>
                        <select id="selectUser" name="uuId">
                            <OPTION value="" selected>==请选择==</OPTION>
                            <c:forEach items="${upUserList}" var="user">
                                <OPTION value="${user.uuId}">${user.userName}</OPTION>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>
                <!--div的class 为error是验证错误，ok是验证成功-->
                <c:if test="${sessionUser.adminFlag == 1}" >
                    <div>
                        <label>旧密码：</label>
                        <input type="password" name="oldPwd" id="oldPwd" required/>
                        <span id="pwdOk"></span>
                    </div>
                </c:if>
                <div>
                    <label>新密码：</label>
                    <input type="password" name="updPwd" id="updPwd" required/>
                </div>
                <div>
                    <label>确认新密码：</label>
                    <input type="password" name="updConfirmPwd" id="updConfirmPwd" required/>
                    <span id="updpwdOk"></span>
                </div>

                <div aria-busy="width: 300px;">
                    <c:if test="${not empty msg}" >
                        <span  style="float: left;text-align: right;padding-left: 220px">${msg}</span>
                    </c:if>
                </div>

                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <button class="btn btn-default btn-lg" id="saveBtn" onclick="save()"  disabled >保 存</button>
                </div>
            </form>
        </div>
    </div>
</section>
<jsp:include page="/view/common/footer.jsp"/>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.md5.js"></script>
<script type="text/javascript">

    $(document).ready(function(){
        var oldFlg = false;

        $("#oldPwd").blur(function () {
            $.ajax({
                type:"post",
                contentType:"application/x-www-form-urlencoded",
                url:  "${pageContext.request.contextPath}/user/getuserpwd",
                data:{
                    "oldPwd":$("#oldPwd").val()
                },
                success:function(data){
                    if(data.success == true){
                        $("#pwdOk").html("<img src=\"${pageContext.request.contextPath}/static/img/OK.jpg\" width=\"15px\" height=\"15px\">");
                        oldFlg = true;
                    }else {
                        $("#pwdOk").html(data.message);
                    }
                },
                error:function(data){
                    alert("登陆失败,原因为" + data.responseText);
                }
            });
            return false;
        });


        $("#updPwd").blur(function(){
            if($("#updPwd").val() != "" && $("#updConfirmPwd").val() != "") {
                if ($("#updPwd").val() != $("#updConfirmPwd").val()) {
                    $("#updpwdOk").html("两次密码不一样，请重新输入");
                    $("#saveBtn").attr("disable",true);
                } else {
                    $("#updpwdOk").html("");
                    if (oldFlg || ${sessionUser.adminFlag} == '0' ) {
                        $("#saveBtn").attr("disabled",false);
                        $("#saveBtn").attr("class","btn btn-primary btn-lg ");
                    }
                }
            }
            return false;
        });

        $("#updConfirmPwd").blur(function(){
            if($("#updPwd").val() != "" && $("#updConfirmPwd").val() != "") {
                if ($("#updPwd").val() != $("#updConfirmPwd").val()) {
                    $("#updpwdOk").html("两次密码不一样，请重新输入");
                    $("#saveBtn").attr("disable",true);
                }else {
                    $("#updpwdOk").html("");
                    if (oldFlg || ${sessionUser.adminFlag} == '0') {
                        $("#saveBtn").attr("disabled",false);
                        $("#saveBtn").attr("class","btn btn-primary btn-lg ");
                    }
                }
            }
            return false;
        });



    });

    function save() {
        $("#selectUser").val($('#selectUser option:selected') .val());
        $("#from").attr("action","${pageContext.request.contextPath}/user/updateuserpwd").submit();
        $("#saveBtn").attr("disable",true);
        $("#saveBtn").attr("class","btn btn-default btn-lg");
    };

</script>
</html>
