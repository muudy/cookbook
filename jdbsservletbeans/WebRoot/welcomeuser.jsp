<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcomeuser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script language="javascript">
       function exit(){
       window.open('../login/login.jsp','_top')
         } 
      </script>

<input type="button" value="退出" onclick="exit();" />
	-->
  </head>
  <style> 
body{text-align:center} 
</style> 
  
  <%
    ResultSet rs = (ResultSet) session.getAttribute("rs");
	String username = (String) session.getAttribute("username");
    if (username != null && !username.isEmpty()&&session != null) {
    	//ResultSet rs = (ResultSet) session.getAttribute("rs");   	
 %>
你已经登录，欢迎<%=session.getAttribute("username")%>
 <%
	} else {
		out.print("你还没有登录， 1秒后自动跳往登录页面");
		response.setHeader("refresh", "1;url=" + path + "/userlogin.jsp");
	}

 if(session.getAttribute("username")!=null)
 {
%>
<table border="1">
		<tr>
			<th>编号</th>
			<th>用户名</th>
			<th>密码</th>
			<th>操作</th>
			
		</tr>

		<tr>
			<td><%=rs.getString("id")%></td>
			<td><%=rs.getString("username")%></td>
			<td><%=rs.getString("password")%></td>
			
			<td><a href="modifyuser.jsp?id=<%=rs.getString("id")%>">修改</a></td>
		</tr>
		
	</table>
	<%
	}
	else{
%>
<h3>请先进行系统的<a href="userlogin.jsp">登录</a></h3>
<%
 }
%><br/>
<a href = "servlet/usercancel" style="font-size:30px">注销</a><br/>
<a href = "index.jsp">返回首页</a>
</html>


