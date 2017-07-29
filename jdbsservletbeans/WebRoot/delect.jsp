<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>

<%
//删除一个用户
String url ="jdbc:mysql://localhost:3306/testdb"; //数据库连接串
Class.forName("org.gjt.mm.mysql.Driver").newInstance(); //加载驱动程序
Connection conn= DriverManager.getConnection(url,"root","15927557003"); //建立连接
String sql="delete from users where id=?";
PreparedStatement pStmt = conn.prepareStatement(sql);

String id=request.getParameter("id");
//System.out.print("********"+id);
pStmt.setString(1, id);
int cnt=pStmt.executeUpdate();

response.sendRedirect("showallusers.jsp");
 pStmt.close();
 conn.close();
 %>