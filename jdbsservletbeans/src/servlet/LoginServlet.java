package servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDAO;
import dao.UserDAO;

public class LoginServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String identity = request.getParameter("identity");
		// String id = request.getParameter("id");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String path = request.getContextPath();
		UserDAO userdao = new UserDAO();

		if (identity.equals("user")) {
			// 用户名和密码是否存在并且匹配，返回flag
			// System.out.println("---------");
			String username = name;
			boolean flag = userdao.findUser(username, password);
			ResultSet rs = userdao.getUserByusername(username);
			if (flag) {
				request.getSession().setAttribute("rs", rs);
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("identity", identity);
				response.sendRedirect(path + "/welcomeuser.jsp");
			} else {
				request.getSession().setAttribute("err", "用户名或密码不正确！ ");
				response.sendRedirect(path + "/userlogin.jsp");
			}
		}

		else if (identity.equals("admin")) {// 管理员身份
			String path1 = request.getContextPath();
			String adminname = name;
			AdminDAO admindao = new AdminDAO();
			// 用户名和密码是否存在并且匹配，返回flag
			boolean flag = admindao.findadmin(adminname, password);
			ResultSet rs = userdao.getUserByadminname(adminname);
			request.getSession().setAttribute("identity", identity);
			if (flag) {
				request.getSession().setAttribute("rs", rs);
				request.getSession().setAttribute("adminname", adminname);
				// request.getRequestDispatcher("OprationServlet").forward(request,
				// response);
				response.sendRedirect(path1 + "/welcomeadmin.jsp");
			} else {
				request.getSession().setAttribute("err", "用户名或密码不正确！ ");
				response.sendRedirect(path1 + "/adminlogin.jsp");
			}
		}
	}
}