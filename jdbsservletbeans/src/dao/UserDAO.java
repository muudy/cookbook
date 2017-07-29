package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.User;

public class UserDAO {
	public static final String DRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/testdb";
	public static final String DBUSER = "root";
	public static final String DBPASS = "15927557003";
	private Connection conn = null;
	private PreparedStatement pStmt = null;
	private ResultSet rs = null;

	public Connection getConnectionn() {
		try {
			Class.forName(DRIVER).newInstance();
			return DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (Exception e) {
			return null;
		}
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pStmt != null)
				pStmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end close

	public ResultSet getAllUser() {
		conn = getConnectionn();
		try {
			pStmt = conn.prepareStatement("select * from users ");
			rs = pStmt.executeQuery();
			if (rs.next())
				return rs;
			else
				return null;
		} catch (Exception e) {
			return null;
		} finally {
			;
		}
	}// end get

	public boolean isUsernameExists(String username) {
		conn = getConnectionn();
		try {
			pStmt = conn.prepareStatement("select * from users where id=?");
			pStmt.setString(1, username);
			rs = pStmt.executeQuery();
			pStmt = conn
					.prepareStatement("select * from users where username=?");
			pStmt.setString(1, username);
			rs = pStmt.executeQuery();
			if (rs.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	} // end isUsernameExists

	public boolean addUser(User user) {
		conn = getConnectionn();
		try {
			pStmt = conn.prepareStatement("insert into users values(null,?,?)");
			pStmt.setString(1, user.getUsername());
			pStmt.setString(2, user.getPassword());
			int cnt = pStmt.executeUpdate();
			if (cnt > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	} // end add

	public boolean findUser(String username, String password) {
		conn = getConnectionn();
		try {
			pStmt = conn
					.prepareStatement("select * from users where username=? and password=?");
			pStmt.setString(1, username);
			pStmt.setString(2, password);
			rs = pStmt.executeQuery();

			if (rs.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	} // end findUser

	public boolean findadmin(String adminname, String password) {
		conn = getConnectionn();
		try {
			pStmt = conn
					.prepareStatement("select * from admins where adminname=? and password=?");
			pStmt.setString(1, adminname);
			pStmt.setString(2, password);
			rs = pStmt.executeQuery();
			if (rs.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	} // end findadmin

	public boolean delectUser(String id) {
		conn = getConnectionn();
		try {
			pStmt = conn.prepareStatement("delete from users where id=?");
			// System.out.print("********"+id);
			pStmt.setString(1, id);
			int cnt = pStmt.executeUpdate();
			if (cnt > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	} // end delectUser

	public boolean modifyUser(String id, String username, String password) {
		conn = getConnectionn();
		try {
			pStmt = conn
					.prepareStatement("update users set username=?, password=? where id=?");
			pStmt.setString(1, username);
			pStmt.setString(2, password);
			pStmt.setString(3, id);
			int cnt = pStmt.executeUpdate();
			if (cnt > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	} // end delectUser

	public ResultSet getUserById(String id) {
		conn = getConnectionn();
		try {
			pStmt = conn.prepareStatement("select * from users where id=?");
			pStmt.setString(1, id);
			rs = pStmt.executeQuery();
			if (rs.next())
				return rs;
			else
				return null;
		} catch (Exception e) {
			return null;
		} finally {
			// close();
		}
	}// end getUserById

	public ResultSet getUserByusername(String username) {
		conn = getConnectionn();
		try {
			pStmt = conn
					.prepareStatement("select * from users where username=?");
			pStmt.setString(1, username);
			rs = pStmt.executeQuery();
			if (rs.next())
				return rs;
			else
				return null;
		} catch (Exception e) {
			return null;
		} finally {
			// close();
		}
	}// end getUserById

	public ResultSet getadminById(String id) {
		conn = getConnectionn();
		try {
			pStmt = conn.prepareStatement("select * from admins where id=?");
			pStmt.setString(1, id);
			rs = pStmt.executeQuery();
			if (rs.next())
				return rs;
			else
				return null;
		} catch (Exception e) {
			return null;
		} finally {
			// close();
		}
	}// end getUserById

	public ResultSet getUserByadminname(String adminname) {
		conn = getConnectionn();
		try {
			pStmt = conn
					.prepareStatement("select * from admins where adminname=?");
			pStmt.setString(1, adminname);
			rs = pStmt.executeQuery();
			if (rs.next())
				return rs;
			else
				return null;
		} catch (Exception e) {
			return null;
		} finally {
			// close();
		}
	}// end getUserById

}// end class
