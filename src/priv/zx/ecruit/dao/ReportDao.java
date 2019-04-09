package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.Report;

/**
 * 举报信息表Dao
 * @author zx
 *
 */
public class ReportDao {

	public void addReport(Report r) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_report " +
				"(stuUsername,epUsername) " +
				"values(?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, r.getStuUsername());
		ptmt.setString(2, r.getEpUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public boolean isExist(Report r) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_report " +
				"where stuUsername = ? and epUsername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, r.getStuUsername());
		ptmt.setString(2, r.getEpUsername());
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			rs.close();
			ptmt.close();
			return true;
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return false;
	}
	
	public void delReport(Report r) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_report " +
				"where stuUsername = ? and epUsername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, r.getStuUsername());
		ptmt.setString(2, r.getEpUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public ArrayList<Report> queryAll() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_report ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Report> arrReport = new ArrayList<Report>();
		Report r = null;
		while(rs.next()){
			r = new Report();
			r.setStuUsername(rs.getString("stuUsername"));
			r.setEpUsername(rs.getString("epUsername"));
			arrReport.add(r);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrReport;
	}
}
