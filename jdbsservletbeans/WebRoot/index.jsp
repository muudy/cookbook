<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<html>
<head>
<base href="<%=basePath%>">

<title>首页</title>
<style> 
body{text-align:center} 
</style> 


<body>

	<a href="userlogin.jsp" style="font-size:30px">用户登录</a><br/>
	<a href="adminlogin.jsp" style="font-size:30px">管理员登录</a><br/>
	<a href="register.jsp" style="font-size:30px">注册一个用户</a>
</body>
</html>
