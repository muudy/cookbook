package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.UserDAO;

public class RegServlet extends HttpServlet {

	public RegServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String path = request.getContextPath(); // 获取请求路径
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		UserDAO userdao = new UserDAO();
		if (userdao.isUsernameExists(username)) {
			request.getSession().setAttribute("err", "用户名已存在");
			response.sendRedirect(path + "/register.jsp");
		} else {
			boolean flag = userdao.addUser(user);
			ResultSet rs = userdao.getUserByusername(username);
			if (flag) {
				request.getSession().setAttribute("rs", rs);
				request.getSession().setAttribute("username", username);
				response.sendRedirect(path + "/welcomeuser.jsp");
				// return;
			} else {
				request.getSession().setAttribute("err", "注册失败");
				response.sendRedirect(path + "/index.jsp");
			}
		}

	}
}