<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="dao.AdminDAO"%>
<%@ page import="dao.UserDAO"%>
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

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%-- 注释语句 --%>
</head>
<%--
	String url = "jdbc:mysql://localhost:3306/testdb"; //数据库连接字符串
	Class.forName("org.gjt.mm.mysql.Driver").newInstance(); //加载驱动程序
	Connection conn = DriverManager.getConnection(url, "root",
			"15927557003"); //建立连接

	String sql = "select * from users where id=?";
	PreparedStatement pStmt = conn.prepareStatement(sql);

	String id = request.getParameter("id");
	pStmt.setString(1, id);

	ResultSet rs = pStmt.executeQuery();
	rs.next();
	request.getSession().getAttribute(rs.getString("id"));
--%>
<%
UserDAO userdao = new UserDAO();
String id = request.getParameter("id");
ResultSet rs = userdao.getUserById(id);
%>
<form action="servlet/modifyuserServlet" method="post">
	id<input type="text" name="id" readonly="readonly"
		value='<%=rs.getString("id") %>'       ><br /> 
	用户名<input type="text" name="username" 
	    value='<%=rs.getString("username")%>' ><br />
	密码 <input type="text" name="password"
		value='<%=rs.getString("password")%>'    ><br /> 
		
		<input type="submit" value="保存">
</form>

</html>
