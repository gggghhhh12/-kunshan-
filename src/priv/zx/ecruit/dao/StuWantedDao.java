package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.StuWanted;

/**
 * 公司向毕业生发出邀请Dao
 * @author zx
 *
 */
public class StuWantedDao {
	
	public void addStuWanted(StuWanted sw) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_stuwanted " +
				"(epUsername,stuUsername) " +
				"values(?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, sw.getEpUsername());
		ptmt.setString(2, sw.getStuUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public boolean isExist(StuWanted sw) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_stuwanted " +
				"where epUsername = ? and stuUsername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, sw.getEpUsername());
		ptmt.setString(2, sw.getStuUsername());
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
	
	//通过毕业生用户名查找出向该毕业生发出邀请的公司用户名
	public ArrayList<String> queryInvite(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select epUsername from tb_stuwanted " +
				"where stuUsername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, stuUsername);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<String> arrEPname = new ArrayList<String>();
		while(rs.next()){
			arrEPname.add(rs.getString("epUsername"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrEPname; 
	}
	
	//删除公司向该毕业生发出邀请的记录
	public void delStuWantedOfStu(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_stuwanted where stuUsername = \'" + stuUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
	//删除该公司向毕业生发出邀请的记录
	public void delStuWantedOfEP(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_stuwanted where epUsername = \'" + epUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
	//删除指定公司向该毕业生发出邀请的记录
	public void delStuWanted(StuWanted sw) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_stuwanted " +
				"where epUsername = ? and stuUsername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, sw.getEpUsername());
		ptmt.setString(2, sw.getStuUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
}
