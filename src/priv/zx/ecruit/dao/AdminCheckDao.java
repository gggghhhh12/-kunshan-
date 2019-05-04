package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.AdminPerssion;
import priv.zx.ecruit.model.AdminResume;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.User;
/*
 * 管理员 审核dao
 * @author Administrator
 *
 */
public class AdminCheckDao {
	
	//管理员通过学生审核
	public void updateUserCheck(String username) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_users " +
				"set usercheck=? " +
				"where username=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, "1");
		ptmt.setString(2, username);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	//找到所有未通过的
	public ArrayList<User> userUnCheck() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_users " +
				"where usercheck='0'" ;
		Statement stmt = conn.createStatement();
		ArrayList<User> userUncheckName = new ArrayList<User>();
		User bi = null;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			bi= new User();
			//System.out.println(rs.getString("username"));
			bi.setUsername(rs.getString("username"));
			userUncheckName.add(bi);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return userUncheckName;
	}

	//管理员 通过企业审核
	public void updateEPUserCheck(String epusername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_epuser " +
				"set EPcheck=? " +
				"where EPusername=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, "1");
		ptmt.setString(2, epusername);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	//管理员 通过简历审核
		public void updateepBCheck(String username) throws SQLException{
			Connection conn = DBUtil.getConnection();
			String sql = "" +
					"update tb_basicinfo " +
					"set checked=? " +
					"where username=? ";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, "1");
			ptmt.setString(2, username);
			ptmt.execute();
			ptmt.close();
			DBUtil.close(conn);
		}
		//管理员 通过职位审核
		public void updateJobCheck(String EPusername,String jobname) throws SQLException{
			Connection conn = DBUtil.getConnection();
			String sql = "" +
					"update tb_eppostjob " +
					"set jobcheck=? " +
					"where EPusername=? and jobname = ?";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, "1");
			ptmt.setString(2, EPusername);
			ptmt.setString(3, jobname);
			ptmt.execute();
			ptmt.close();
			DBUtil.close(conn);
		}
		//开通权限管理
		public void addPerssionTime(AdminPerssion perssion) throws SQLException{
			Connection conn = DBUtil.getConnection();
			String sql = "" +
					"insert into tb_checktime" +
					"(tb_StartTime,tb_endtime,tb_time) " +
					"values(?,?,?) ";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setDate(1,new Date(perssion.getStartTime().getTime()));
			ptmt.setDate(2, new Date(perssion.getEndTime().getTime()));
		    ptmt.setDate(3, new Date(perssion.getdaytime().getTime()));
			ptmt.execute();
			ptmt.close();
			DBUtil.close(conn);
		}
		//找到最大的最新的记录
		public ArrayList<AdminPerssion> searchPerssionTime() throws SQLException{
			Connection conn = DBUtil.getConnection();
			String sql = "" +
					"select  tb_StartTime,tb_endtime from tb_checktime " +
					"where tb_timeid in (select max(tb_timeid) from tb_checktime) ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<AdminPerssion> checkResult = new ArrayList<AdminPerssion>();
			AdminPerssion ar = null;
			while(rs.next()){
				ar = new AdminPerssion();
				//System.out.println("111111111");
				//System.out.println(rs.getDate("tb_StartTime"));
				ar.setStartTime(rs.getDate("tb_StartTime"));;
				ar.setEndTime(rs.getDate("tb_endtime"));
				checkResult.add(ar);
			}
			rs.close();
			stmt.close();
			DBUtil.close(conn);
			return checkResult;
		}
}
