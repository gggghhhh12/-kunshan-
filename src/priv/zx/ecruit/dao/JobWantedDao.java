package priv.zx.ecruit.dao;

/**
 * ��ְ��Dao
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
	
	//��ѯ��ù�˾Ͷ�ݼ����ı�ҵ���û���
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
	
	//ɾ���ñ�ҵ����˾������������м�¼
	public void delJobWantedOfStu(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_jobwanted where stuUsername = \'" + stuUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
	//ɾ����ҵ����ù�˾������������м�¼
	public void delJobWantedOfEP(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_jobwanted where epUsername = \'" + epUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
	//ɾ��ָ����ҵ����ָ����˾��������ļ�¼
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
	//��־��ϢΪ�Ѷ�
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
	//תΪ�ղ�
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
