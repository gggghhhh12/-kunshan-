package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.EPUser;

//��ҵ�˻����ݿ����
public class EPUserDao {
	
	public void addEPUser(EPUser epu) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_epuser " +
				"(EPusername,EPpassword) " +
				"values(?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, epu.getEPusername());
		ptmt.setString(2, epu.getEPpassword());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public void updateEPUser(EPUser epu) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_epuser " +
				"set EPpassword = ? " +
				"where EPusername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, epu.getEPpassword());
		ptmt.setString(2, epu.getEPusername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public String getEPpassword(String EPusername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select EPpassword from tb_epuser " +
				"where EPusername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, EPusername);
		ResultSet rs = ptmt.executeQuery();
		String EPpassword = null;
		while(rs.next()){
			EPpassword = rs.getString("EPpassword");
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return EPpassword;
	}
	//��ȡ��ҵ�ȼ�
	public String getEplevel(String EPusername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select EPlevel from tb_epuser " +
				"where EPusername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, EPusername);
		ResultSet rs = ptmt.executeQuery();
		String EPlevel = "0";
		while(rs.next()){
			EPlevel = rs.getString("EPlevel");
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return EPlevel;
		
		
	}
	
	public boolean isExist(String EPusername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select EPusername from tb_epuser " +
				"where EPusername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, EPusername);
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
	
	
	//����Աɾ����ҵ�û���
	public void delEPUser(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_epuser where EPusername = \'" + epUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
//��ȡ�Ƿ�ͨ�����
	public boolean getIsChecked(String ePname)throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select EPcheck from tb_epuser " +
				"where EPusername=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ePname);
		ResultSet rs = ptmt.executeQuery();
		String userlevel = "0";
		while(rs.next()){
			userlevel = rs.getString("EPcheck");
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
}
