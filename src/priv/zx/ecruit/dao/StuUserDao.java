package priv.zx.ecruit.dao;

//毕业生账户数据库操作
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.User;

public class StuUserDao {
	//毕业生注册
	public void addUser(User u) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_users " +
				"(username,password) " +
				"values(?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, u.getUsername());
		ptmt.setString(2, u.getPassword());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	//更改密码
	public void updateUser(User u) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_users " +
				"set password=? " +
				"where username=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, u.getPassword());
		ptmt.setString(2, u.getUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public String getPassword(String username) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select password from tb_users " +
				"where username=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, username);
		ResultSet rs = ptmt.executeQuery();
		String password = null;
		while(rs.next()){
			password = rs.getString("password");
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return password;
	}
	//获取学生权限标志位
	public String getuserlevel(String username) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select userlevel from tb_users " +
				"where username=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, username);
		ResultSet rs = ptmt.executeQuery();
		String userlevel = "0";
		while(rs.next()){
			userlevel = rs.getString("userlevel");
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		
		return userlevel;
		
		
	}
	//获取学生是否通过审核标志
	public Boolean getChecked(String username) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select usercheck from tb_users " +
				"where username=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, username);
		ResultSet rs = ptmt.executeQuery();
		String userlevel = "0";
		while(rs.next()){
			userlevel = rs.getString("usercheck");
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		
		if(userlevel.equals("0"))
		{
			return false;
		}
		return true;
	}
	
	public boolean isExist(String username) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select username from tb_users " +
				"where username = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, username);
		ResultSet rs = ptmt.executeQuery();
		if(rs.next()){
			rs.close();
			ptmt.close();
			return true;
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return false;
	}
	
	//管理员删除用户
	public void delStuUser(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_users where username = \'" + stuUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	

	
}
