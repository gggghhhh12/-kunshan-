package priv.zx.ecruit.dao;

/**
 * 求职表Dao
 * @author zx
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.JobWanted;

public class JobWantedDao {

	public void addJobWanted(JobWanted jw) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_jobwanted " +
				"(stuUsername,epUsername,epJobname) " +
				"values(?,?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, jw.getStuUsername());
		ptmt.setString(2, jw.getEpUsername());
		ptmt.setString(3, jw.getEpJobname());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public boolean isExist(JobWanted jw) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_jobwanted " +
				"where stuUsername = ? and epUsername = ? and epJobname=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, jw.getStuUsername());
		ptmt.setString(2, jw.getEpUsername());
		ptmt.setString(3, jw.getEpJobname());
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			rs.close();
			ptmt.close();
			DBUtil.close(conn);
			return true;
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return false;
	}
	
	//查询向该公司投递简历的毕业生用户名
	public ArrayList<String> queryInvite(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select stuUsername from tb_jobwanted " +
				"where epUsername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, epUsername);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<String> arrStuUsername = new ArrayList<String>();
		while(rs.next()){
			arrStuUsername.add(rs.getString("stuUsername"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrStuUsername;
	}
	public ArrayList<String> queryInvite1(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select tb_jobwanted.stuUsername,tb_jobwanted.epJobname,tb_jobwanted.flag from tb_jobwanted,tb_basicinfo " +
				"where tb_jobwanted.epUsername = ? and tb_jobwanted.stuUsername=tb_basicinfo.username and tb_basicinfo.checked='1'";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, epUsername);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<String> arrStuUsername = new ArrayList<String>();
		while(rs.next()){
			arrStuUsername.add(rs.getString("stuUsername")+ '_' + rs.getString("epJobname")+ '_' + rs.getString("flag"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrStuUsername;
	}
	
	//删除该毕业生向公司发出申请的所有记录
	public void delJobWantedOfStu(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_jobwanted where stuUsername = \'" + stuUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
	//删除毕业生向该公司发出申请的所有记录
	public void delJobWantedOfEP(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_jobwanted where epUsername = \'" + epUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
	//删除指定毕业生向指定公司发出申请的记录
	public void delJobWanted(JobWanted jw) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_jobwanted " +
				"where epUsername = ? and stuUsername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, jw.getEpUsername());
		ptmt.setString(2, jw.getStuUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	//标志信息为已读
	public void ChangeMessageStutes(String stuUsername,String epUsername,String epJobname) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_jobwanted " +
				"set flag = ?" +
				"where stuUsername = ? and epUsername = ? and epJobname = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, "1");
		ptmt.setString(2, stuUsername);
		ptmt.setString(3, epUsername);
		ptmt.setString(4, epJobname);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	//转为收藏
	public void InsertToStore(String epUsername,String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_epstore " +
				"(epUsername,stuUsername) " +
				"values(?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, epUsername);
		ptmt.setString(2, stuUsername);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
}
