package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.JobIntention;
import priv.zx.ecruit.model.TradeAndCount;

public class JobIntentionDao {
	
	public void addJobIntention(JobIntention ji) throws SQLException{
		
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_jobintention " +
				"(username,keyword,evaluation,place,trade,salary,date) " +
				"values(?,?,?,?,?,?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ji.getUsername());
		ptmt.setString(2, ji.getKeyword());
		ptmt.setString(3, ji.getEvaluation());
		ptmt.setString(4, ji.getPlace());
		ptmt.setString(5, ji.getTrade());
		ptmt.setString(6, ji.getSalary());
		ptmt.setDate(7, new Date(ji.getDate().getTime()));
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public JobIntention getJobIntention(String username) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_jobintention " +
				"where username = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, username);
		ResultSet rs = ptmt.executeQuery();
		JobIntention ji = null;
		while(rs.next()){
			ji = new JobIntention();
			ji.setUsername(username);
			ji.setKeyword(rs.getString("keyword"));
			ji.setEvaluation(rs.getString("evaluation"));
			ji.setPlace(rs.getString("place"));
			ji.setTrade(rs.getString("trade"));
			ji.setSalary(rs.getString("salary"));
			ji.setDate(rs.getDate("date"));
			ji.setStatus(rs.getInt("status"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return ji;
	}
	
	public void updateJobIntention(JobIntention ji) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_jobintention " +
				"set keyword = ?,evaluation = ?,place = ?,trade = ?,salary = ?,date = ? " +
				"where username = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ji.getKeyword());
		ptmt.setString(2, ji.getEvaluation());
		ptmt.setString(3, ji.getPlace());
		ptmt.setString(4, ji.getTrade());
		ptmt.setString(5, ji.getSalary());
		ptmt.setDate(6, new Date(ji.getDate().getTime()));
		ptmt.setString(7, ji.getUsername());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	//删选符合条件的毕业生，筛选条件是求职地点、期望月薪、求职行业、学历
	public ArrayList<String> queryStuUsername(String address,String salary,String trade,String diploma) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String condition = "";
		//拼接条件语句
		if(!"".equals(address)){
			condition += "and tb_jobintention.place = \'" + address + "\'";
		}
		if(!"不限".equals(salary)){
			condition += "and tb_jobintention.salary = \'" + salary + "\'";
		}
		if(!"".equals(trade)){
			condition += "and tb_jobintention.trade = \'" + trade + "\'";
		}
		if(!"不限".equals(diploma)){
			condition += "and tb_education.edudiploma = \'" + diploma + "\'";
		}
		String sql = "select tb_basicinfo.username from tb_basicinfo where tb_basicinfo.checked='1' and tb_basicinfo.username in (" +
				"select tb_jobintention.username from tb_jobintention,tb_education where " +
				"tb_basicinfo.username=tb_jobintention.username and "+
				"tb_jobintention.status = 1 " + condition+")";
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
	
	//推荐功能，根据求职地点，求职行业推荐该公司可能需要的人
	public ArrayList<String> queryRecommend(String place,String trade) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "select tb_basicinfo.username from tb_basicinfo where tb_basicinfo.checked='1' and tb_basicinfo.username in (" +
				"select username from tb_jobintention " +
				"where place = ? and trade = ? and status = 1 )";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, place);
		ptmt.setString(2, trade);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<String> arrStuUsername = new ArrayList<String>();
		while(rs.next()){
			arrStuUsername.add(rs.getString("username"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrStuUsername;
	}
	
	//管理员删除求职意向
	public void delJobIntention(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_jobintention where username = \'" + stuUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
	//更改简历公开权限(1为公开，0位不公开)
	public void modifyAuthority(String stuUsername,int status) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_jobintention " +
				"set status = ? " +
				"where username = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, status);
		ptmt.setString(2, stuUsername);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	//查询该月各行业投简历的人数
	public ArrayList<TradeAndCount> queryCount() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select trade,count(trade) from tb_jobintention " +
				"where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m') " +
				"group by trade " +
				"order by count(trade) desc ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<TradeAndCount> arrCount = new ArrayList<TradeAndCount>();
		TradeAndCount tac = null;
		while(rs.next()){
			tac = new TradeAndCount();
			tac.setTrade(rs.getString("trade"));
			tac.setCount(rs.getInt("count(trade)"));
			arrCount.add(tac);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrCount;
	}
	
	//查询该月找工作的人数
	public int getCount() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select count(trade) from tb_jobintention " +
				"where date_format(date,'%Y-%m')=date_format(now(),'%Y-%m') ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		int count = 0;
		while(rs.next()){
			count = rs.getInt("count(trade)");
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return count;
	}
	
}
