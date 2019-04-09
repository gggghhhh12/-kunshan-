package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.AdminResume;
import priv.zx.ecruit.model.BasicInfo;

/**
 * 简历基本信息dao
 */
public class BasicInfoDao {
	
	public void addBasicInfo(BasicInfo bi) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_basicinfo " +
				"(username,name,sex,nation,birthday,tel,email,liveaddr,residence) " +
				"values( " +
				"?,?,?,?,?,?,?,?,?) ";
		
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, bi.getUsername());
		ptmt.setString(2, bi.getName());
		ptmt.setString(3, bi.getSex());
		ptmt.setString(4,bi.getNation());
		ptmt.setDate(5, new Date(bi.getBirthday().getTime()));
		ptmt.setString(6, bi.getTel());
		ptmt.setString(7, bi.getEmail());
		ptmt.setString(8, bi.getLiveaddr());
		ptmt.setString(9, bi.getResidence());
		ptmt.execute();	
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public BasicInfo getBasicInfo(String username) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_basicinfo " +
				"where username = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, username);
		ResultSet rs = ptmt.executeQuery();
		BasicInfo bi = null;
		while(rs.next()){
			bi = new BasicInfo();
			bi.setUsername(username);
			bi.setName(rs.getString("name"));
			bi.setSex(rs.getString("sex"));
			bi.setNation(rs.getString("nation"));
			bi.setBirthday(rs.getDate("birthday"));
			bi.setTel(rs.getString("tel"));
			bi.setEmail(rs.getString("email"));
			bi.setLiveaddr(rs.getString("liveaddr"));
			bi.setResidence(rs.getString("residence"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return bi;
	}
	
	public void updateBasicInfo(BasicInfo bi) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_basicinfo " +
				"set name = ?,sex = ?,nation = ?,birthday = ?,tel = ?,email = ?,liveaddr = ?,residence = ?,checked='0' "+
				"where username = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, bi.getName());
		ptmt.setString(2, bi.getSex());
		ptmt.setString(3, bi.getNation());
		ptmt.setDate(4, new Date(bi.getBirthday().getTime()));
		//System.out.println(new Date(bi.getBirthday().getTime()));
		ptmt.setString(5, bi.getTel());
		ptmt.setString(6, bi.getEmail());
		ptmt.setString(7, bi.getLiveaddr());
		ptmt.setString(8, bi.getResidence());
		ptmt.setString(9, bi.getUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public boolean isExist(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_basicinfo " +
				"where username = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, stuUsername);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			rs.close();
			ptmt.close();
			return true;
		}
		rs.close();
		ptmt.close();
		return false;
	}
	
	//查找出所有的用户
	public ArrayList<BasicInfo> queryAll() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_users left join tb_basicinfo " +
				"on tb_basicinfo.username = tb_users.username ";
		Statement stmt = conn.createStatement();
		ArrayList<BasicInfo> arrBasicInfo = new ArrayList<BasicInfo>();
		BasicInfo bi = null;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			bi = new BasicInfo();
			bi.setUsername(rs.getString("username"));
			bi.setName(rs.getString("name"));
			bi.setSex(rs.getString("sex"));
			bi.setNation(rs.getString("nation"));
			bi.setBirthday(rs.getDate("birthday"));
			bi.setTel(rs.getString("tel"));
			bi.setEmail(rs.getString("email"));
			bi.setLiveaddr(rs.getString("liveaddr"));
			bi.setResidence(rs.getString("residence"));
			arrBasicInfo.add(bi);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrBasicInfo;
	}
	
	//查找出所有用户的简历信息
	public ArrayList<AdminResume> queryResumes() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select tb_basicinfo.username,tb_basicinfo.name,tb_basicinfo.sex,tb_basicinfo.sex,tb_education.eduschool, " +
				"tb_education.edumajor,tb_basicinfo.tel,tb_basicinfo.email from tb_basicinfo,tb_education " +
				"where tb_basicinfo.username = tb_education.username ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<AdminResume> arrResume = new ArrayList<AdminResume>();
		AdminResume ar = null;
		while(rs.next()){
			ar = new AdminResume();
			ar.setStuUsername(rs.getString("username"));
			ar.setStuName(rs.getString("name"));
			ar.setSex(rs.getString("sex"));
			ar.setSchool(rs.getString("eduschool"));
			ar.setMajor(rs.getString("edumajor"));
			ar.setTel(rs.getString("tel"));
			ar.setEmail(rs.getString("email"));
			arrResume.add(ar);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrResume;
	}
	
	//管理员删除用户基本信息
	public void delBasicInfo(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_basicinfo where username = \'" + stuUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	//查询所有求职者权限，返回<用户名,等级>的map集合
	public HashMap<String, String> queryAllUserlevel() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tb_users";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		HashMap<String, String> mapUserlevel = new HashMap<String, String>();
		while(rs.next()){
			mapUserlevel.put(rs.getString("username"), rs.getString("userlevel"));
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return mapUserlevel;
	}
	//设置求职者等级
	public void setUserlevel(String username, String userlevel) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		String sql = "update tb_users set userlevel=? where username=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, userlevel);
		ptmt.setString(2, username);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}

	public ArrayList<BasicInfo> queryUncheckAll() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_users left join tb_basicinfo " +
				"on tb_basicinfo.username = tb_users.username where tb_users.usercheck='0'";
		Statement stmt = conn.createStatement();
		ArrayList<BasicInfo> arrBasicInfo = new ArrayList<BasicInfo>();
		BasicInfo bi = null;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			bi = new BasicInfo();
			bi.setUsername(rs.getString("username"));
			bi.setName(rs.getString("name"));
			bi.setSex(rs.getString("sex"));
			bi.setNation(rs.getString("nation"));
			bi.setBirthday(rs.getDate("birthday"));
			bi.setTel(rs.getString("tel"));
			bi.setEmail(rs.getString("email"));
			bi.setLiveaddr(rs.getString("liveaddr"));
			bi.setResidence(rs.getString("residence"));
			arrBasicInfo.add(bi);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrBasicInfo;
	}

	public ArrayList<AdminResume> queryUncheckResumes() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select tb_basicinfo.username,tb_basicinfo.name,tb_basicinfo.sex,tb_basicinfo.sex,tb_education.eduschool, " +
				"tb_education.edumajor,tb_basicinfo.tel,tb_basicinfo.email from tb_basicinfo,tb_education " +
				"where tb_basicinfo.username = tb_education.username and tb_basicinfo.checked='0'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<AdminResume> arrResume = new ArrayList<AdminResume>();
		AdminResume ar = null;
		while(rs.next()){
			ar = new AdminResume();
			ar.setStuUsername(rs.getString("username"));
			ar.setStuName(rs.getString("name"));
			ar.setSex(rs.getString("sex"));
			ar.setSchool(rs.getString("eduschool"));
			ar.setMajor(rs.getString("edumajor"));
			ar.setTel(rs.getString("tel"));
			ar.setEmail(rs.getString("email"));
			arrResume.add(ar);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrResume;
	}

}
