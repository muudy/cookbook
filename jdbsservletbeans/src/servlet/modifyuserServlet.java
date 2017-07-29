package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.UserDAO;

public class modifyuserServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String path = request.getContextPath();
		User user = new User();
		user.setUsername(id);
		user.setUsername(username);
		user.setPassword(password);
		UserDAO userdao = new UserDAO();

		boolean flag = userdao.modifyUser(id, username, password);
		if (flag) {
			request.getSession().setAttribute("username", username);
			response.sendRedirect(path + "/welcomeuser.jsp");
		} else {
			PrintWriter out = response.getWriter();
			out.print("报错了");
			request.getSession().setAttribute("err", "用户名或密码不正确！ ");
			response.sendRedirect(path + "/login.jsp");
		}
	}
}