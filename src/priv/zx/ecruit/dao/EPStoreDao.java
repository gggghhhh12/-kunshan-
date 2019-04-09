package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.EPStore;

/**
 * 公司收藏毕业生数据库操作Dao
 * @author zx
 *
 */
public class EPStoreDao {

	public void addEPStore(EPStore eps) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_epstore " +
				"(epUsername,stuUsername) " +
				"values(?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, eps.getEpUsername());
		ptmt.setString(2, eps.getStuUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public boolean isExist(EPStore eps) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_epstore " +
				"where epUsername = ? and stuUsername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, eps.getEpUsername());
		ptmt.setString(2, eps.getStuUsername());
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
	
	public ArrayList<String> queryEPStore(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "select tb_basicinfo.username as stuUsername from tb_basicinfo where tb_basicinfo.checked='1' and tb_basicinfo.username in (" +
				"select stuUsername from tb_epstore " +
				"where epUsername = ? )";
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
	
	public void delEPStore(EPStore eps) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_epstore " +
				"where epUsername = ? and stuUsername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, eps.getEpUsername());
		ptmt.setString(2, eps.getStuUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	//查询出公司收藏的人的求职地点
	public ArrayList<String> queryPlace(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select tb_jobintention.place from tb_jobintention,tb_epstore " +
				"where tb_jobintention.username = tb_epstore.stuUsername and tb_epstore.epUsername = ? " +
				"group by tb_jobintention.place " +
				"order by count(tb_jobintention.place) desc ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, epUsername);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<String> arrPlace = new ArrayList<String>();
		while(rs.next()){
			arrPlace.add(rs.getString("place"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrPlace;
	}
	
	//查询出公司收藏的人的求职类别
	public ArrayList<String> queryTrade(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select tb_jobintention.trade from tb_jobintention,tb_epstore " +
				"where tb_jobintention.username = tb_epstore.stuUsername and tb_epstore.epUsername = ? " +
				"group by tb_jobintention.trade " +
				"order by count(tb_jobintention.trade) desc ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, epUsername);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<String> arrTrade = new ArrayList<String>();
		while(rs.next()){
			arrTrade.add(rs.getString("trade"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrTrade;
	}
	
	//通过收藏的人的求职地点和求职类别，推荐相似的毕业生,返回毕业生用户名的ArrayList
	public ArrayList<String> queryRecommend(ArrayList<String> arrPlace,ArrayList<String> arrTrade) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String condition1 = "";//求职地点条件
		String condition2 = "";//求职类别条件
		//拼接求职地点条件
		for (String place : arrPlace) {
			condition1 += "place = \'" + place + "\' or ";
		}
		//拼接求职类别条件
		for (String trade : arrTrade) {
			condition2 += "trade = \'" + trade + "\' or ";
		}
		String sql = "" +
				"select username from tb_jobintention " +
				"where (" + condition1 + "1=0) and (" + condition2 + "1=0) ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<String> arrStuUsername = new ArrayList<String>();
		while(rs.next()){
			arrStuUsername.add(rs.getString("username"));
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrStuUsername;
	}
	
	//删除该公司收藏的毕业生
	public void delEPStore(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_epstore where epUsername = \'" + epUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
	//删除所有公司收藏某毕业生的记录
	public void delEPStoreOfStu(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_epstore where stuUsername = \'" + stuUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
}
