<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>博客系统登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="css/main.css" media="all" />
<!--[if IE 6]><link type="text/css" rel="stylesheet" href="css/ie6.css" media="all" /><![endif]-->
<script type="text/javascript" src="js/mootools.js"></script>
<script type="text/javascript" src="js/site.js"></script>
 <%-- 引入jquery--%>
  <script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>
<%--  引入样式--%>
  <link href="static/bootstrap/css/bootstrap.css" rel="stylesheet">
  <script src="static/bootstrap/js/bootstrap.js"></script>
</head>
<body class="text-center">
<form class="form-signin" action="dashboard.html" th:action="@{/user/login}" method="post">
  <img class="mb-4" th:src="@{/asserts/img/bootstrap-solid.svg}" src="asserts/img/bootstrap-solid.svg" alt="" width="72" height="72">
  <h1 class="h3 mb-3 font-weight-normal" th:text="">Please sign in</h1>
  <!--判断-->
  <label class="sr-only" th:text="#{login.username}">Username</label>
  <input type="text"  name="username" class="form-control" placeholder="Username" th:placeholder="#{login.username}" required="" autofocus="">
  <label class="sr-only" th:text="#{login.password}">Password</label>
  <input type="password" name="password" class="form-control" placeholder="Password" th:placeholder="#{login.password}" required="">
  <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="remember-me"/> [[#{login.remember}]]
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit" th:text="#{login.btn}">Sign in</button>
</form>
</body>
</html>
