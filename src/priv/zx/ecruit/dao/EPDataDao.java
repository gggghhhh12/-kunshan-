package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.EPData;

/**
 * 公司资料数据操作Dao
 */
public class EPDataDao {
	
	public void addEPDataDao(EPData epd) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_epdata " +
				"(EPusername,EPname,EPnature,EPcode,EPtrade,EPscale,EPaddr,EPcontact,EPemail,EPtel,EPmobile,EPpostalcode,EPintroduction) " +
				"values( " +
				"?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, epd.getEPusername());
		ptmt.setString(2, epd.getEPname());
		ptmt.setString(3, epd.getEPnature());
		ptmt.setString(4, epd.getEPcode());
		ptmt.setString(5, epd.getEPtrade());
		ptmt.setString(6, epd.getEPscale());
		ptmt.setString(7, epd.getEPaddr());
		ptmt.setString(8, epd.getEPcontact());
		ptmt.setString(9, epd.getEPemail());
		ptmt.setString(10, epd.getEPtel());
		ptmt.setString(11, epd.getEPmobile());
		ptmt.setString(12, epd.getEPpostalcode());
		ptmt.setString(13, epd.getEPintroduction());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public EPData getEPData(String EPusername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_epdata " +
				"where EPusername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, EPusername);
		ResultSet rs = ptmt.executeQuery();
		EPData epd = null;
		while(rs.next()){
			epd = new EPData();
			epd.setEPusername(EPusername);
			epd.setEPname(rs.getString("EPname"));
			epd.setEPnature(rs.getString("EPnature"));
			epd.setEPcode(rs.getString("EPcode"));
			epd.setEPtrade(rs.getString("EPtrade"));
			epd.setEPscale(rs.getString("EPscale"));
			epd.setEPaddr(rs.getString("EPaddr"));
			epd.setEPcontact(rs.getString("EPcontact"));
			epd.setEPemail(rs.getString("EPemail"));
			epd.setEPtel(rs.getString("EPtel"));
			epd.setEPmobile(rs.getString("EPmobile"));
			epd.setEPpostalcode(rs.getString("EPpostalcode"));
			epd.setEPintroduction(rs.getString("EPintroduction"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return epd;
	}
	
	public void updateEPData(EPData epd) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_epdata " +
				"set EPname = ?,EPnature = ?,EPcode = ?, EPtrade = ?, " +
				"EPscale = ?,EPaddr = ?,EPcontact = ?,EPemail = ?,EPtel = ?,EPmobile = ?, EPpostalcode = ?,EPintroduction = ? " +
				"where EPusername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, epd.getEPname());
		ptmt.setString(2, epd.getEPnature());
		ptmt.setString(3, epd.getEPcode());
		ptmt.setString(4, epd.getEPtrade());
		ptmt.setString(5, epd.getEPscale());
		ptmt.setString(6, epd.getEPaddr());
		ptmt.setString(7, epd.getEPcontact());
		ptmt.setString(8, epd.getEPemail());
		ptmt.setString(9, epd.getEPtel());
		ptmt.setString(10, epd.getEPmobile());
		ptmt.setString(11, epd.getEPpostalcode());
		ptmt.setString(12, epd.getEPintroduction());
		ptmt.setString(13, epd.getEPusername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	//根据关键字搜索公司
	public ArrayList<String> queryEPusesnames(String keyword) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select EPusername from tb_epdata " +
				"where EPname like \'%" + keyword + "%\' ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<String> arrEPusername = new ArrayList<String>();
		while(rs.next()){
			arrEPusername.add(rs.getString("EPusername"));
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrEPusername;
	}
	
	//查找出所有的公司用户
	public ArrayList<EPData> queryAll() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_epuser left join tb_epdata " +
				"on tb_epuser.EPusername = tb_epdata.EPusername ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<EPData> arrEPData = new ArrayList<EPData>();
		EPData epd = null;
		while(rs.next()){
			epd = new EPData();
			epd.setEPusername(rs.getString("EPusername"));
			epd.setEPname(rs.getString("EPname"));
			epd.setEPnature(rs.getString("EPnature"));
			epd.setEPcode(rs.getString("EPcode"));
			epd.setEPtrade(rs.getString("EPtrade"));
			epd.setEPscale(rs.getString("EPscale"));
			epd.setEPaddr(rs.getString("EPaddr"));
			epd.setEPcontact(rs.getString("EPcontact"));
			epd.setEPemail(rs.getString("EPemail"));
			epd.setEPtel(rs.getString("EPtel"));
			epd.setEPmobile(rs.getString("EPmobile"));
			epd.setEPpostalcode(rs.getString("EPpostalcode"));
			epd.setEPintroduction(rs.getString("EPintroduction"));
			arrEPData.add(epd);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrEPData;
	}
	
	//管理员删除公司信息
	public void delEPData(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_epdata where EPusername = \'" + epUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
	//查找出所有的公司信息
	public ArrayList<EPData> queryEPData() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_epdata,tb_epuser " +
				"where tb_epdata.EPusername = tb_epuser.EPusername ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<EPData> arrEPData = new ArrayList<EPData>();
		EPData epd = null;
		while(rs.next()){
			epd = new EPData();
			epd.setEPusername(rs.getString("EPusername"));
			epd.setEPname(rs.getString("EPname"));
			epd.setEPnature(rs.getString("EPnature"));
			epd.setEPcode(rs.getString("EPcode"));
			epd.setEPtrade(rs.getString("EPtrade"));
			epd.setEPscale(rs.getString("EPscale"));
			epd.setEPaddr(rs.getString("EPaddr"));
			epd.setEPcontact(rs.getString("EPcontact"));
			epd.setEPemail(rs.getString("EPemail"));
			epd.setEPtel(rs.getString("EPtel"));
			epd.setEPmobile(rs.getString("EPmobile"));
			epd.setEPpostalcode(rs.getString("EPpostalcode"));
			epd.setEPintroduction(rs.getString("EPintroduction"));
			arrEPData.add(epd);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrEPData;
	}
	//	改变公司等级以决定是否显示联系方式及公司名
	public void setEPlevel(String epusername, String eplevel) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		String sql = "update tb_epuser " +
				"set EPlevel = ?" +
				"where EPusername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, eplevel);
		ptmt.setString(2, epusername);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	//获取所有公司权限，返回<公司账号，公司等级>的集合
	public HashMap<String, String> queryAllEPlevel() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tb_epuser ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		HashMap<String, String> mapEPlevel = new HashMap<String, String>();
		while(rs.next()){
			mapEPlevel.put(rs.getString("EPusername"), rs.getString("EPlevel"));
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return mapEPlevel;
	}

	public ArrayList<EPData> queryLatestEPData(String times) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_epdata,tb_epuser " +
				"where tb_epdata.EPusername = tb_epuser.EPusername and to_days(now())-to_days(tb_epdata.EPpostdate)<? order by tb_epdata.EPpostdate desc";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, times);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<EPData> arrEPData = new ArrayList<EPData>();
		EPData epd = null;
		while(rs.next()){
			epd = new EPData();
			epd.setEPusername(rs.getString("EPusername"));
			epd.setEPname(rs.getString("EPname"));
			epd.setEPnature(rs.getString("EPnature"));
			epd.setEPcode(rs.getString("EPcode"));
			epd.setEPtrade(rs.getString("EPtrade"));
			epd.setEPscale(rs.getString("EPscale"));
			epd.setEPaddr(rs.getString("EPaddr"));
			epd.setEPcontact(rs.getString("EPcontact"));
			epd.setEPemail(rs.getString("EPemail"));
			epd.setEPtel(rs.getString("EPtel"));
			epd.setEPmobile(rs.getString("EPmobile"));
			epd.setEPpostalcode(rs.getString("EPpostalcode"));
			epd.setEPintroduction(rs.getString("EPintroduction"));
			arrEPData.add(epd);
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrEPData;
	}

	public ArrayList<EPData> queryUncheckEPData() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_epuser left join tb_epdata " +
				"on tb_epuser.EPusername = tb_epdata.EPusername where EPcheck='0'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<EPData> arrEPData = new ArrayList<EPData>();
		EPData epd = null;
		while(rs.next()){
			epd = new EPData();
			epd.setEPusername(rs.getString("EPusername"));
			epd.setEPname(rs.getString("EPname"));
			epd.setEPnature(rs.getString("EPnature"));
			epd.setEPcode(rs.getString("EPcode"));
			epd.setEPtrade(rs.getString("EPtrade"));
			epd.setEPscale(rs.getString("EPscale"));
			epd.setEPaddr(rs.getString("EPaddr"));
			epd.setEPcontact(rs.getString("EPcontact"));
			epd.setEPemail(rs.getString("EPemail"));
			epd.setEPtel(rs.getString("EPtel"));
			epd.setEPmobile(rs.getString("EPmobile"));
			epd.setEPpostalcode(rs.getString("EPpostalcode"));
			epd.setEPintroduction(rs.getString("EPintroduction"));
			arrEPData.add(epd);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrEPData;
	}
}
