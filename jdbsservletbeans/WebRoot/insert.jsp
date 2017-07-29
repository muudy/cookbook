<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<%
//添加一个新用户
String url ="jdbc:mysql://localhost:3306/testdb"; //数据库连接字符串
Class.forName("org.gjt.mm.mysql.Driver").newInstance(); //加载驱动程序
Connection conn= DriverManager.getConnection(url,"root","15927557003"); //建立连接

String sql="insert into users values(null,?,?)";
PreparedStatement pStmt = conn.prepareStatement(sql);
//此处省略用户名检测
String username=request.getParameter("username");
String password=request.getParameter("password");
pStmt.setString(1,username);
pStmt.setString(2,password);
int cnt=pStmt.executeUpdate();
if(cnt>0){
      response.sendRedirect("index.jsp");
}
else{
      out.print("用户已存在， <a href='insert.jsp'>重新注册</a>");
}
//关闭
pStmt.close(); 
conn.close(); %>
