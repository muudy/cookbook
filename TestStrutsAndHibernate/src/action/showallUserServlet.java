package action;

import java.util.List;

import vo.Admin;
import vo.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserDAO;
import dao.UserService;

public class showallUserServlet extends ActionSupport {
	private User user;
	private Admin admin;
	private String identity;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	UserService userservice = new UserService(); // 使用Sevice完成业务功能

	public String showallusers() {

		UserDAO userdao = new UserDAO();

		List<User> userlist = userservice.findAllUser();
		// String identity = request.getParameter("identity");

		if (userlist == null) {
			addActionError("用户名或密码错，请重新输入");
			System.out.println("1");
			return "input";
		} else {
			ActionContext.getContext().getSession().put("userlist", userlist);
			ActionContext.getContext().getSession().put("identity", identity);
			// System.out.println("2");
			// System.out.println(userlist.size());
			return "showallusers";
		}
	}
}