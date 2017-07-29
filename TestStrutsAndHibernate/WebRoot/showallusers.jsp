<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="vo.User"%>
<%@ page import="vo.Admin"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>  
    <title>管理用户</title>
</head>
  
  <style> 
body{text-align:center} 
</style>
<%
//List<User> userList =(List<User>)session.getAttribute("userlist");
//List<User> userlist=(List<User>)session.getAttribute(userlist);
ArrayList userList = (ArrayList)session.getAttribute("userlist");
if(session.getAttribute("adminname") != null)
	 {
%> 
  
	<table border="1">
		<tr>
			<th>编号</th>
			<th>用户名</th>
			<th>密码</th>
			<th>操作</th>
			<th>操作</th>
		</tr>

		<%
		for(int i=0;i<userList.size();i++) 
		{ 
		User user = (User)userList.get(i);
		%>
		<tr>
			<td><%=user.getId() %></td>
			<td><%=user.getUsername()%></td>
			<td><%=user.getPassword()%></td>
			<td><a href="delect.action?id=<%=user.getId()%>">删除</a></td>
			<td><s:a href="modifyuser.jsp?id=<%=user.getId()%>">修改</s:a></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href = "test2.action" style="font-size:30px">注销</a><br/>
	<a href="register.jsp" style="font-size:30px">添加新纪录</a><br/>
	<a href = "index.jsp" style="font-size:30px">返回首页</a><br/>
	<a href="typeAction.doname=laowai250&password=250&...">测试</a>
	<%
	}
	else{
%><br/>

<h3>你已注销或你不是管理员，请先进行系统的登录<br/></h3>
<h3>普通用户<s:a href="userlogin.jsp">登录</s:a></h3>
<h3>管理员<s:a href="adminlogin.jsp">登录</s:a></h3>

<%
 }
%>
	

</html>
