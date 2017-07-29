package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Admin;

public class AdminDAO {
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

	public boolean isAdminnameExists(String adminname) {
		conn = getConnectionn();
		try {
			pStmt = conn
					.prepareStatement("select * from admins where adminname=?");
			pStmt.setString(1, adminname);
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
	} // end isadminnameExists

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

	public boolean addadmin(Admin admin) {
		conn = getConnectionn();
		try {
			pStmt = conn
					.prepareStatement("insert into admins values(null,?,?)");
			pStmt.setString(1, admin.getAdminname());
			pStmt.setString(2, admin.getPassword());
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
} // end class
