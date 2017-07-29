<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="vo.User"%>
<%@ page import="vo.Admin"%>
<%@ page import="dao.UserService"%>

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

<title>My JSP 'modifyhtml.jsp' starting page</title>

</head>

<%
UserService userservice = new UserService();
String id = request.getParameter("id");
int i=Integer.parseInt(id);
User user = userservice.findUserById(i);

%>
<s:form action="adminmodifusers.action" method="post">
<s:textfield name="user.username" label="用户名" ></s:textfield><br/>
<s:password name="user.password" label="密码" ></s:password><br/>
<s:submit value="确认"></s:submit><br/>
</s:form>

</html>



