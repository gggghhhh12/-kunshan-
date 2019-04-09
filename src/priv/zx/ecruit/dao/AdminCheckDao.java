package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import priv.zx.ecruit.db.DBUtil;

/**
 * ����Ա ���dao
 * @author Administrator
 *
 */
public class AdminCheckDao {
	
	//����Աͨ��ѧ�����
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

	//����Ա ͨ����ҵ���
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
	//����Ա ͨ���������
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
		//����Ա ͨ��ְλ���
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
}
