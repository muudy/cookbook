<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <title>注册界面</title>
    <style> 
body{text-align:center} 
</style> 
  </head>
  
 <s:form action="register.action" method="post">
<s:textfield name="user.username" label="用户名" >用户名</s:textfield><br/>
<s:password name="user.password" label="密码" >密码</s:password><br/>
<s:submit value="注册"></s:submit><br/>
<input type="reset" value="重填">
</s:form>
</html>
