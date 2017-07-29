package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class admincancel extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getContextPath();
		HttpSession session = request.getSession(false);

		// session.removeAttribute("id");
		// session.removeAttribute("adminname");
		session.removeAttribute("userlist");
		response.sendRedirect(path + "/index.jsp");

		// 这里简单操作一下，直接将用户信息保存到session中
		// ActionContext.getContext().getSession().put("admin", admin);

	}
}