package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.EPStore;
import priv.zx.ecruit.model.StuStore;

/**
 * tb_stuStore表操作类
 * @author zx
 *
 */
public class StuStoreDao {
	
	public void addStuStore(StuStore ss) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_stuStore " +
				"(stuUsername,epUsername,jobname) " +
				"values(?,?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ss.getStuUsername());
		ptmt.setString(2, ss.getEpUsername());
		ptmt.setString(3, ss.getJobname());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public boolean isExist(StuStore ss) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_stuStore " +
				"where stuUsername = ? and epUsername = ? and jobname=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ss.getStuUsername());
		ptmt.setString(2, ss.getEpUsername());
		ptmt.setString(3, ss.getJobname());
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
	
	public ArrayList<EPStore> queryStuStore(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_stuStore " +
				"where stuUsername = ? and epUsername in (select EPusername from tb_eppostjob where jobcheck='1')";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, stuUsername);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<EPStore> arrEPStore = new ArrayList<EPStore>();
		while(rs.next()){
			EPStore es=new EPStore();
			es.setEpUsername(rs.getString("epUsername"));
			es.setJobname(rs.getString("jobname"));
			es.setStuUsername(rs.getString("stuUsername"));
			arrEPStore.add(es);
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrEPStore;
	}
	
	public void delStuStore(StuStore ss) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_stuStore " +
				"where stuUsername = ? and epUsername = ? and jobname=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ss.getStuUsername());
		ptmt.setString(2, ss.getEpUsername());
		ptmt.setString(3, ss.getJobname());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	//查找出该生所收藏的公司中的地址
	public ArrayList<String> queryAddr(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select tb_epdata.EPaddr from tb_stustore,tb_epdata " +
				"where tb_stustore.epUsername = tb_epdata.EPusername and tb_stustore.stuUsername = ? " +
				"group by tb_epdata.EPaddr " +
				"order by count(tb_epdata.EPaddr) desc ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, stuUsername);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<String> arrAddr = new ArrayList<String>();
		while(rs.next()){
			arrAddr.add(rs.getString("EPaddr"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrAddr;
	}
	
	//查找出该生所收藏的职位的职能类别
	public ArrayList<String> queryJobkind(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select tb_eppostjob.jobkind from tb_stustore,tb_eppostjob " +
				"where tb_stustore.epUsername = tb_eppostjob.EPusername and tb_stustore.stuUsername = ? " +
				"group by tb_eppostjob.jobkind " +
				"order by count(tb_eppostjob.jobkind) desc ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, stuUsername);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<String> arrJobkind = new ArrayList<String>();
		while(rs.next()){
			arrJobkind.add(rs.getString("jobkind"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrJobkind;
	}
	
	//根据地址和职能类别推荐职位,返回值为公司用户名的ArrayList
	public ArrayList<String> queryRecommend(ArrayList<String> arrAddr,ArrayList<String> arrJobkind) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String condition1 = "";//地址条件
		String condition2 = "";//职能类别条件
		//拼接地址条件
		for (String addr : arrAddr) {
			condition1 += "tb_epdata.EPaddr =\'" + addr + "\' or ";
		}
		//拼接职能类别条件
		for (String jobkind : arrJobkind) {
			condition2 += "tb_eppostjob.jobkind =\'" + jobkind + "\' or ";
		}
		String sql = "select tb_epdata.EPusername from tb_epdata,tb_eppostjob " +
				"where tb_eppostjob.jobcheck='1' and tb_epdata.EPusername = tb_eppostjob.EPusername and ( " +
				condition1 + "1=0) " + "and ( " + condition2 + "1=0) ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<String> arrUsername = new ArrayList<String>();
		while(rs.next()){
			arrUsername.add(rs.getString("EPusername"));
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrUsername;
	}
	
	//管理员删除该用户的收藏职位
	public void delStuStore(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_stustore where stuUsername = \'" + stuUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
	//管理员删除所有用户收藏某公司的职位
	public void delStuStoreOfEP(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_stustore where epUsername = \'" + epUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
 	
}
