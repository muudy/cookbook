package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.UserDAO;

public class delectServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setContentType("text/html");
		String id = request.getParameter("id");

		String path = request.getContextPath(); // 获取请求路径
		User user = new User();
		user.setId(id);
		// System.out.println("path"+path);
		UserDAO userdao = new UserDAO();
		boolean flag = userdao.delectUser(id);
		if (flag) {
			// request.getSession().setAttribute("username", username);
			response.sendRedirect(path + "/index.jsp");
			// return;
			// System.out.println("path1"+path);
		} else {
			request.getSession().setAttribute("err", "用户名或密码不正确！ ");
			System.out.println("path2" + path);
			response.sendRedirect(path + "/index.jsp");
		}

	}

}// end class delectServlet
