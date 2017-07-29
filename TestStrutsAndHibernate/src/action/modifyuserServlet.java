package action;

import vo.Admin;
import vo.User;

import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;

public class modifyuserServlet extends ActionSupport {
	private User user;
	private Admin admin;
	private String username;
	private String password;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	UserService userservice = new UserService(); // 使用Sevice完成业务功能

	public String modifyuser() {
		System.out.println(username);
		if (userservice.changeusernameandpassword(username, password)) {
			// ActionContext.getContext().getSession().put("user", user);
			return SUCCESS;
		} else {
			addActionError("用户名已存在，请重新输入");
			return INPUT;
		}
	}
}