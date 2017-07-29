<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	//action="login.action" method="post"
    //action="servlet/LoginServlet"
	-->
  </head> 
  <style> 
body{text-align:center} 
</style> 
  <body>
    <%
String errMsg=(String)session.getAttribute("err");
if( errMsg!=null ) { %>
	<div style="color:red;"><%=errMsg %></div>
	<% session.removeAttribute("err");
} %>
	<form action="servlet/LoginServlet">
		用户名<input type="text" name="username"><br /> 
		密码<input type="password" name="password"><br /> 
		<input type="hidden" name="identity" value="admin">
		<input type="submit" value="登录"> 
		<input type="reset" value="重填">
	</form>
  </body>
</html>
