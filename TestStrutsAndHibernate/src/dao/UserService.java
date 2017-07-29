package dao;

import java.util.List;

import org.hibernate.Query;

import vo.Admin;
import vo.User;

public class UserService {
	UserDAO userdao = new UserDAO();

	public User userlogin(User user) {
		// System.out.println("name=" + user.getUsername());
		// System.out.println("password=" + user.getUsername());
		User u = findUserByNameAndPassword(user.getUsername(),
				user.getPassword());
		if (u != null) {
			return u;
		}
		return null;
	}

	public Admin adminlogin(Admin admin) {
		Admin u = findAdminByNameAndPassword(admin.getAdminname(),
				admin.getPassword());
		if (u != null) {
			return u;
		}
		return null;
	}

	public boolean register(User user) {
		User u = findUserByName(user.getUsername());
		if (u == null) {
			userdao.add(user); // 调用DAO
			return true;
		} else {
			return false;
		}
	}

	public boolean delect(int id) {
		User u = findUserById(id);
		// System.out.println("2");
		// System.out.println(id);
		if (u != null) {
			userdao.delect(u); // 调用DAO
			return true;
		} else {
			return false;
		}
	}

	public User findUserByNameAndPassword(String username, String password) {
		String queryString = "from User as u where u.username=? and u.password=?";
		Query queryObject = userdao.getSession().createQuery(queryString);
		queryObject.setParameter(0, username);
		queryObject.setParameter(1, password);
		List<User> list = queryObject.list();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Admin findAdminByNameAndPassword(String adminname, String password) {
		String queryString = "from Admin as u where u.adminname=? and u.password=?";
		Query queryObject = userdao.getSession().createQuery(queryString);
		queryObject.setParameter(0, adminname);
		queryObject.setParameter(1, password);
		List<Admin> list = queryObject.list();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public User findUserById(int id) {
		User user = (User) userdao.findById("vo.User", id);
		return user;
	}

	public User findUserByName(String username) {
		List<User> list = findUserByProperty("username", username);
		if (!list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	public List<User> findUserByProperty(String property, Object value) {
		try {
			String queryString = "from User as user where user." + property
					+ "= ?";
			Query query = userdao.getSession().createQuery(queryString);
			query.setParameter(0, value);
			return query.list();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean changePassword(String username, String password) {
		User user = findUserByName(username);
		if (user != null) {
			user.setUsername(username);
			user.setPassword(password);
			userdao.update(user);
			return true;
		} else {
			return false;
		}
	}

	public boolean changeusernameandpassword(String username, String password) {
		User user = findUserByName(username);
		if (user != null) {
			user.setUsername(username);
			user.setPassword(password);
			userdao.update(user);
			return true;
		} else {
			return false;
		}
	}

	public List<User> findAllUser() {
		try {
			String queryString = "from User";
			Query queryObject = userdao.getSession().createQuery(queryString);
			return queryObject.list();
		} catch (Exception e) {
			return null;
		}
	}
} // end class