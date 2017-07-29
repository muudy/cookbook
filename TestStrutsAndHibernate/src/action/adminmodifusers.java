package action;

import vo.Admin;
import vo.User;

import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;

public class adminmodifusers extends ActionSupport {
	private User user;
	private Admin admin;
	private String username;
	private String password;
	private int id;

	UserService userservice = new UserService(); // 使用Sevice完成业务功能

	public String modifyuser() {
		if (userservice.changeusernameandpassword(username, password)) {
			// ActionContext.getContext().getSession().put("user", user);
			return SUCCESS;
		} else {
			addActionError("用户名已存在，请重新输入");
			return INPUT;
		}
	}
}