package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.AdminUser;

/**
 * 管理员表操作对象Dao
 *
 */
public class AdminUserDao {

	public String getPassword(String adminUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select admin_password from tb_adminuser " +
				"where admin_username = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, adminUsername);
		ResultSet rs = ptmt.executeQuery();
		String password = "";
		while(rs.next()){
			password = rs.getString("admin_password");
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return password;
	}
	
	public boolean isExist(String username) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select admin_username from tb_adminuser " +
				"where tb_admin_username = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, username);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			return true;
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return false;
	}
	
	public void updateAdminUser(AdminUser au) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_adminuser " +
				"set admin_password = ? " +
				"where admin_username = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, au.getAdminPassword());
		ptmt.setString(2, au.getAdminUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	} 
}
