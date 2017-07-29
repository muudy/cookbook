<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="vo.User"%>
<%@ page import="vo.Admin"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>登录成功</title>

</head>

<style> 
body{text-align:center} 
</style> 

<%
    Admin u = (Admin)session.getAttribute("admin");
    ResultSet rs = (ResultSet) session.getAttribute("rs");
	String adminname = (String) session.getAttribute("adminname");
if (u != null && session != null) {
%>
你已经登录，欢迎<%=u.getAdminname()%>
<%
	} else {
		out.print("你还没有登录， 1秒后自动跳往登录页面");
		response.setHeader("refresh", "1;url=" + path + "/adminlogin.jsp");
	}
%><br/>
<%
 if(session.getAttribute("adminname")!=null){
%>

<a href ="showallusers.action" style="font-size:30px">管理用户</a><br/>
<a href = "test2.action" style="font-size:30px">注销</a><br/>
<a href = "index.jsp" style="font-size:30px">返回首页</a>
<%
}
else{
%>
<h3>请先进行系统的<s:a href="adminlogin.jsp">登录</s:a></h3>
<%
 }
%>
</html>
