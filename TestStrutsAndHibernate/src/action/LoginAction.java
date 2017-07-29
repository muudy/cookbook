package action;

import vo.Admin;
import vo.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
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

	public String back1() {
		return SUCCESS;
	}

	public String back2() {
		return SUCCESS;
	}

	public String back3() {
		return SUCCESS;
	}

	public String userlogin() {
		// System.out.println("name=" + user.getUsername());
		// System.out.println("password=" + user.getUsername());
		User u = userservice.userlogin(user);

		if (u == null) {
			addActionError("用户名或密码错，请重新输入");
			return "input";
		} else {
			ActionContext.getContext().getSession().put("user", u);
			ActionContext.getContext().getSession()
					.put("username", u.getUsername());
			ActionContext.getContext().getSession()
					.put("password", u.getPassword());
			return "welcomeuser";
		}

	}// end userlogin

	public String adminlogin() {
		// String identity = request.getParameter("identity");
		Admin u = userservice.adminlogin(admin);
		if (u == null) {
			addActionError("用户名或密码错，请重新输入");
			// System.out.println("1");
			return "input";
		} else {
			ActionContext.getContext().getSession().put("admin", u);
			ActionContext.getContext().getSession()
					.put("adminname", u.getAdminname());
			return "welcomeadmin";
		}

	}// end adminlogin

	public String register() {
		if (userservice.register(user)) {
			ActionContext.getContext().getSession().put("user", user);
			return SUCCESS;
		} else {
			addActionError("用户名已存在，请重新输入");
			return INPUT;
		}
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

} // end action