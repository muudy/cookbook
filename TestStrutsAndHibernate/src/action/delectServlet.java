package action;

import java.util.List;

import vo.Admin;
import vo.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserDAO;
import dao.UserService;

public class delectServlet extends ActionSupport {
	private int id;
	private User user;
	private Admin admin;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	UserService userservice = new UserService(); // 使用Sevice完成业务功能

	public String delect() {
		System.out.println(id);
		if (userservice.delect(id)) {
			UserDAO userdao = new UserDAO();
			List<User> userlist = userservice.findAllUser();
			ActionContext.getContext().getSession().put("userlist", userlist);
			return SUCCESS;
		} else {
			addActionError("用户名已存在，请重新输入");
			return INPUT;
		}
	}

}// end class delectServlet