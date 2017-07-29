package action;

import beans.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String logout() {    // 注销功能
		ActionContext.getContext().getSession().remove("user");
		return SUCCESS;
	}

	public String execute() throws Exception {
		// 这里简单操作一下，直接将用户信息保存到session中
		ActionContext.getContext().getSession().put("user", user);
		return SUCCESS;
	}
}