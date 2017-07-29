package servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

public class showallUserServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		UserDAO userdao = new UserDAO();
		// 用户名和密码是否存在并且匹配，返回flag
		ResultSet rs = userdao.getAllUser();

		// System.out.println("userList大小：" + userList.size());
		// request.setAttribute("userList", userList);
		request.getSession().setAttribute("rs", rs);
		response.sendRedirect(path + "/showallusers.jsp");
	}
}