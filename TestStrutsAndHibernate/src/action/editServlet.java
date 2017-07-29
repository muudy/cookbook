package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.User;

import com.opensymphony.xwork2.ActionSupport;

import dao.UserDAO;

public class editServlet extends ActionSupport {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String id = request.getParameter("id");
		UserDAO userdao = new UserDAO();
		// ResultSet rs = userdao.getUserById(id);// 得到id
		ArrayList<User> userList = new ArrayList<User>();
		/*
		 * if (rs != null) { while(rs.next()){ User user = new User();
		 * user.setId(rs.getString("id"));
		 * 
		 * //String username = user.setUsername(rs.getString("username"));
		 * 
		 * user.setUsername(rs.getString("username"));
		 * user.setPassword(rs.getString("password")); userList.add(user); }
		 */

		// request.getSession().setAttribute("rs", rs);
		// request.getSession().setAttribute("username", userList.get(1));
		// request.getSession().setAttribute("password", userList.get(1));
		response.sendRedirect(path + "/modify.jsp");
		// } else {
		// request.getSession().setAttribute("err", "用户名或密码不正确！ ");
		// response.sendRedirect(path + "/login.jsp");
		// }
	}
}
