<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<title>用户登录界面</title>

<style> 
body{text-align:center} 
</style> 

<%
	String errMsg = (String) session.getAttribute("err");
if (errMsg != null) {
%>
<div style="color:red;"><%=errMsg%></div>
<%
	session.removeAttribute("err");
	}
%>

<s:actionerror cssStyle="color:red;"/>
<s:form action="userlogin.action" method="post" >
<s:textfield name="user.username" label="用户名" >用户名</s:textfield><br/>
<s:password name="user.password" label="密码" >密码</s:password><br/>
<s:submit value="登录"></s:submit>
</s:form>
<s:a href="register.jsp">注册</s:a>

</html>
