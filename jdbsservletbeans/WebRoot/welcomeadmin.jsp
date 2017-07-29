<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>登录成功</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<style> 
body{text-align:center} 
</style> 

<%
    ResultSet rs = (ResultSet) session.getAttribute("rs");
	String adminname = (String) session.getAttribute("adminname");
if (adminname != null && !adminname.isEmpty()) {
%>
你已经登录，欢迎<%=session.getAttribute("adminname")%>
<%
	} else {
		out.print("你还没有登录， 1秒后自动跳往登录页面");
		response.setHeader("refresh", "1;url=" + path + "/adminlogin.jsp");
	}
%><br/>
<%
 if(session.getAttribute("adminname")!=null){
%>

<a href="servlet/showallUserServlet" style="font-size:30px">管理用户</a><br/>
<a href = "servlet/admincancel" style="font-size:30px">注销</a><br/>
<a href = "index.jsp" style="font-size:30px">返回首页</a>
<%
}
else{
%>
<h3>请先进行系统的<a href="adminlogin.jsp">登录</a></h3>
<%
 }
%>
</html>
