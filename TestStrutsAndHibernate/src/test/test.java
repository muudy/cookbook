package test;

import java.util.List;

import vo.User;
import dao.UserService;

public class test {
	public static void main(String[] args) throws Exception {
		UserService userserive = new UserService(); // 使用Service完成业务功能
		User u = new User();
		u.setUsername("2");
		u.setPassword("2");
		boolean flag = userserive.register(u);
		if (flag) {
			System.out.println("注册成功");
		} else {
			System.out.println("注册失败：用户名已存在");
		}
		System.out.println("所有注册用户信息： ");
		List<User> list = userserive.findAllUser();
		for (User x : list) {
			System.out.println("编号=" + x.getId() + "，用户名=" + x.getUsername()
					+ "，密码=" + x.getPassword());
		}

		flag = userserive.changePassword("2", "2");
		if (flag) {
			System.out.println("用户密码已修改");
		} else {
			System.out.println("密码修改错误");
		}
		User u2 = userserive.findUserByName("2");
		if (u2 != null) {
			System.out.print("该用户密码=" + u2.getPassword());
		}
	} // end main
} // end class