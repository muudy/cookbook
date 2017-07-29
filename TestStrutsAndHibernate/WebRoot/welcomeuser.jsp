<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="vo.User"%>
<%@ page import="vo.Admin"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcomeuser.jsp' starting page</title>
    
  </head>
  <style> 
body{text-align:center} 
</style> 
  
  <%
  //ArrayList userList = (ArrayList)request.getAttribute("userList");
  String username=(String)session.getAttribute("username");
  User u = (User)session.getAttribute("user");
    if (session.getAttribute("username")!=null) {
    	//ResultSet rs = (ResultSet) session.getAttribute("rs");

 %>
你已经登录，欢迎<%=u.getUsername()%>
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
			<td><%=u.getId()%></td>
			<td><%=u.getUsername()%></td>
			<td><%=u.getPassword()%></td>
			<td><s:a href="modifyuser.jsp?id=<%=u.getId()%>">修改</s:a></td>
		</tr>
		
	</table>
	<%
		System.out.println("u.getId()  =="+u.getId());
	   session.setAttribute("qqq",u.getId());
	}
	else{
%>
<h3>请先进行系统的<a href="userlogin.jsp" style="font-size:30px">登录</a></h3>
<%
 }
%><br/>
<a href = "test1.action" style="font-size:30px">注销</a><br/>
<a href = "index.jsp" style="font-size:30px">返回首页</a>
</html>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script language="javascript">
       function exit(){
       window.open('../login/login.jsp','_top')
         } 
      </script>

<input type="button" value="退出" onclick="exit();" />

<servlet>
    <description>This is the description of my J2EE component</description>
    <display-name>This is the display name of my J2EE component</display-name>
    <servlet-name>usercancel</servlet-name>
    <servlet-class>servlet.usercancel</servlet-class>
  </servlet>
  <servlet>
    <description>This is the description of my J2EE component</description>
    <display-name>This is the display name of my J2EE component</display-name>
    <servlet-name>admincancel</servlet-name>
    <servlet-class>servlet.admincancel</servlet-class>
  </servlet>
  
  <servlet-mapping>
    <servlet-name>usercancel</servlet-name>
    <url-pattern>/servlet/usercancel</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
    <servlet-name>admincancel</servlet-name>
    <url-pattern>/servlet/admincancel</url-pattern>
  </servlet-mapping>
	-->


