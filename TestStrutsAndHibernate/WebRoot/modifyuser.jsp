<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="vo.User"%>
<%@ page import="vo.Admin"%>
<%@ page import="dao.UserService"%>
<%@ page import="java.math.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'modifyhtml.jsp' starting page</title>
<style> 
body{text-align:center} 
</style> 
</head>

<%
UserService userservice = new UserService();
//int i=Integer.parseInt((String)session.getAttribute("id"));
//String id = request.getParameter("id");
//int i=Integer.parseInt(session.getAttribute("id").toString());
//int i=Integer.parseInt((String)session.getAttribute("id"));
//int i = Integer.parseInt(id);
//User user = userservice.findUserById(i);
//User user = (User)session.getAttribute("user");
////String username=user.getUsername();
//String password=user.getPassword();
//System.out.println(username);
//System.out.println(password);
%>

<s:form action="modifyuser.action" method="post">
<s:textfield name="username" value="%{#session.user.username}"  label="用户名" >用户名</s:textfield><br/>
<s:textfield name="password"  value="%{#session.user.password}" label="密码" >密码</s:textfield><br/>
<s:submit value="确定"></s:submit><br/>
</s:form>

</html>
<%-- 
value="<%=request.getAttribute("username")%>" 
value="<%=session.getAttribute("username")%>"
id：<%=user.getUsername()%><br/>
<%=user.getPassword()%><br/>
<%=session.getAttribute("username") %><br/>
<%=request.getAttribute("username")%>" 

--%>



