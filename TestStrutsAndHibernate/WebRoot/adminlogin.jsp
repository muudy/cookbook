<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登录</title>
    
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
	<s:form action="adminlogin.action" method="post" >
		<s:textfield name="admin.adminname" label="用户名" >用户名</s:textfield><br/>
        <s:password name="admin.password" label="密码" >密码</s:password> <br/>
		<input type="hidden" name="identity" value="admin">
		<s:submit value="登录"></s:submit>  
		<input type="reset" value="重填">
		
	</s:form>
  </body>
</html>
