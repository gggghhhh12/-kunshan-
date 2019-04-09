package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.Education;

public class EducationDao {
	
	public void addEducation(Education edu) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_education " +
				"(username,enterTime,gradTime,eduschool,edumajor,edudiploma,englevel,eduduty, " +
				"eduaward,eduprictise,abroad) " +
				"values( " +
				"?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		Date entertime =new Date(edu.getEnterTime().getTime());
		Date gradtime =new Date(edu.getGradTime().getTime());
		//System.out.println(new Date(edu.getEnterTime().getTime()));
		//System.out.println(new Date(edu.getGradTime().getTime()));
		ptmt.setString(1, edu.getUsername());
		ptmt.setDate(2, entertime);
		ptmt.setDate(3, gradtime);
		ptmt.setString(4, edu.getEduschool());
		ptmt.setString(5, edu.getEdumajor());
		ptmt.setString(6, edu.getEdudiploma());
		ptmt.setString(7, edu.getEnglevel());
		ptmt.setString(8, edu.getEduduty());
		ptmt.setString(9, edu.getEduaward());
		ptmt.setString(10, edu.getEduprictise());
		ptmt.setString(11, edu.getAbroad());
		ptmt.execute();	
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public Education getEducation(String username) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_education " +
				"where username = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, username);
		ResultSet rs = ptmt.executeQuery();
		Education edu = null;
		while(rs.next()){
			edu = new Education();
			edu.setUsername(username);
			edu.setEnterTime(rs.getDate("enterTime"));
			edu.setGradTime(rs.getDate("gradTime"));
			edu.setEduschool(rs.getString("eduschool"));
			edu.setEdumajor(rs.getString("edumajor"));
			edu.setEdudiploma(rs.getString("edudiploma"));
			edu.setEnglevel(rs.getString("englevel"));
			edu.setEduduty(rs.getString("eduduty"));
			edu.setEduaward(rs.getString("eduaward"));
			edu.setEduprictise(rs.getString("eduprictise"));
			edu.setAbroad(rs.getString("abroad"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return edu;
	}
	
	public void updateEducation(Education edu) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_education " +
				"set enterTime = ?,gradTime = ?,eduschool = ?,edumajor = ?,edudiploma = ?,englevel = ?, " +
				"eduduty = ?,eduaward = ?,eduprictise = ?,abroad = ? " +
				"where username = ? ";
		
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setDate(1, new Date(edu.getEnterTime().getTime()));
		ptmt.setDate(2, new Date(edu.getGradTime().getTime()));
		ptmt.setString(3, edu.getEduschool());
		ptmt.setString(4, edu.getEdumajor());
		ptmt.setString(5, edu.getEdudiploma());
		ptmt.setString(6, edu.getEnglevel());
		ptmt.setString(7, edu.getEduduty());
		ptmt.setString(8, edu.getEduaward());
		ptmt.setString(9, edu.getEduprictise());
		ptmt.setString(10, edu.getAbroad());
		ptmt.setString(11, edu.getUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	//管理员删除教育信息
	public void delEducation(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_education where username = \'" + stuUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
}
