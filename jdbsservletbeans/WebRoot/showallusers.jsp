<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>管理用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <style> 
body{text-align:center} 
</style>
 
<%
ResultSet rs = (ResultSet) session.getAttribute("rs");
String username = (String) session.getAttribute("username");
String identity = (String) session.getAttribute("identity");
	if(session.getAttribute("rs")!=null&&identity.equals("admin"))
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
			while (rs.next()) {
		%>
		<tr>
			<td><%=rs.getString("id")%></td>
			<td><%=rs.getString("username")%></td>
			<td><%=rs.getString("password")%></td>
			<td><a href="delect.jsp?id=<%=rs.getString("id")%>">删除</a></td>
			<td><a href="modify.jsp?id=<%=rs.getString("id")%>">修改</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href = "servlet/admincancel" style="font-size:30px">注销</a><br/>
	<a href="register.jsp">添加新纪录</a>
	<%
	}
	else{
%><br/>

<h3>你已注销或你不是管理员，请先进行系统的登录<br/></h3>
<h3>普通用户<a href="userlogin.jsp">登录</a></h3>
<h3>管理员<a href="adminlogin.jsp">登录</a></h3>

<%
 }
%>
	

</html>
