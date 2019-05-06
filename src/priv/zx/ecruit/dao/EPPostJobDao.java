package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.StuSelectResult;
import priv.zx.ecruit.model.TradeAndCount;

/**
 * 企业发布职位要求数据库操作Dao
 */
public class EPPostJobDao {
	
	public void addEPPostJob(EPPostJob eppj) throws SQLException{
		
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_eppostjob " +
				"(EPusername,jobname,jobsalary,jobdiploma,engrequest,reqnum,postdate, " +
				"benefits,jobdescribe,jobduty,techrequest,jobkind,jobaddr) " +
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, eppj.getEPusername());
		ptmt.setString(2, eppj.getJobname());
		ptmt.setString(3, eppj.getJobsalary());
		ptmt.setString(4, eppj.getJobdiploma());
		ptmt.setString(5, eppj.getEngrequest());
		ptmt.setInt(6, eppj.getReqnum());
		ptmt.setDate(7, new Date(eppj.getPostdate().getTime()));
		ptmt.setString(8, eppj.getBenefits());
		ptmt.setString(9, eppj.getJobdescribe());
		ptmt.setString(10, eppj.getJobduty());
		ptmt.setString(11, eppj.getTechrequest());
		ptmt.setString(12, eppj.getJobkind());
		ptmt.setString(13, eppj.getJobaddr());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	//获取职位信息
	public ArrayList<EPPostJob> getLatestEPPostJob()throws SQLException{
	    Connection conn=DBUtil.getConnection();
	    String sql="select tb_eppostjob.EPusername,EPname,jobname,jobsalary,jobdiploma from tb_eppostjob ,tb_epdata where jobcheck='1'and tb_eppostjob.EPusername=tb_epdata.EPusername order by postdate desc";
	    PreparedStatement ptmt=conn.prepareStatement(sql);
	    ResultSet rs=ptmt.executeQuery();
	    ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
	    while(rs.next()) {
	        EPPostJob eppj=new EPPostJob();
	        System.out.println(rs.getString("EPusername")+" "+rs.getString("EPname")+" "
	                +rs.getString("jobname")+" "+rs.getString("jobsalary")+" "
	                +rs.getString("jobdiploma"));
	        eppj.setEPusername(rs.getString("EPusername"));
	        eppj.setEPname(rs.getString("EPname"));
	        eppj.setJobname(rs.getString("jobname"));
	        eppj.setJobsalary(rs.getString("jobsalary"));
	        eppj.setJobdiploma(rs.getString("jobdiploma"));
	        eppjs.add(eppj);
	    }
	    
	    return eppjs;
	}
	//获取职位信息
		public ArrayList<EPPostJob> getHotEPPostJob()throws SQLException
		{
		    Connection conn=DBUtil.getConnection();
		    String sql="select tb_eppostjob.EPusername,EPname,jobname,jobsalary,jobdiploma from tb_eppostjob ,tb_epdata where jobcheck='1'and tb_eppostjob.EPusername=tb_epdata.EPusername order by hotCount desc";
		    PreparedStatement ptmt=conn.prepareStatement(sql);
		    ResultSet rs=ptmt.executeQuery();
		    ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
		    while(rs.next()) {
		        EPPostJob eppj=new EPPostJob();
		        System.out.println(rs.getString("EPusername")+" "+rs.getString("EPname")+" "
		                +rs.getString("jobname")+" "+rs.getString("jobsalary")+" "
		                +rs.getString("jobdiploma"));
		        eppj.setEPusername(rs.getString("EPusername"));
		        eppj.setEPname(rs.getString("EPname"));
		        eppj.setJobname(rs.getString("jobname"));
		        eppj.setJobsalary(rs.getString("jobsalary"));
		        eppj.setJobdiploma(rs.getString("jobdiploma"));
		        eppjs.add(eppj);
		    }
		    
		    return eppjs;
		}
		//getHitcount
		public int getHitCount(String EPusername,String jobname)throws SQLException
		{
		    Connection conn=DBUtil.getConnection();
		    int eppjs = 0;
		    String sql="select hitCount from tb_eppostjob where tb_eppostjob.jobcheck='1' "
		    		+ "and tb_eppostjob.EPusername='"+EPusername+"' and tb_eppostjob.jobname='"+jobname+"'";
		   
		    
		    PreparedStatement ptmt=conn.prepareStatement(sql);
		    ResultSet rs=ptmt.executeQuery(sql);
		    while(rs.next()){
		    	int eppj;
		    	eppj=rs.getInt(1);
		    	eppjs=eppj;
		    }
		   
		    System.out.println(eppjs+"6666666");
		    ptmt.execute();
			ptmt.close();
		    rs.close();
			DBUtil.close(conn);
			 return eppjs;
		}
		//upateCount
		public void updateHitCount(String EPusername,String jobname)throws SQLException
		{
		    Connection conn=DBUtil.getConnection();
		    int count=getHitCount(EPusername, jobname);
		    count=count+1;
		    String sql="update tb_eppostjob set  hitCount= ? "
		    		+ "where tb_eppostjob.EPusername= ?  and tb_eppostjob.jobname=?";
		   
		    PreparedStatement ptmt=conn.prepareStatement(sql);
		   ptmt.setInt(1, count);
		   ptmt.setString(2, EPusername);
		   ptmt.setString(3, jobname);
		    System.out.println(count);		   
		    ptmt.execute();
			ptmt.close();
			DBUtil.close(conn);
		}
		//huoqupostjob
	public ArrayList<EPPostJob> getEPPostJob(String EPusername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_eppostjob " +
				"where EPusername = ? and jobcheck='1' order by postdate desc";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, EPusername);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
		while(rs.next()){
			EPPostJob eppj = null;
			eppj = new EPPostJob();
			eppj.setEPusername(rs.getString("EPusername"));
			eppj.setJobname(rs.getString("jobname"));
			eppj.setJobsalary(rs.getString("jobsalary"));
			eppj.setJobdiploma(rs.getString("jobdiploma"));
			eppj.setEngrequest(rs.getString("engrequest"));
			eppj.setReqnum(rs.getInt("reqnum"));
			eppj.setPostdate(rs.getDate("postdate"));
			eppj.setBenefits(rs.getString("benefits"));
			eppj.setJobdescribe(rs.getString("jobdescribe"));
			eppj.setJobduty(rs.getString("jobduty"));
			eppj.setTechrequest(rs.getString("techrequest"));
			eppj.setJobkind(rs.getString("jobkind"));
			eppj.setJobaddr(rs.getString("jobaddr"));
			eppj.setStatus(rs.getInt("status"));
			eppjs.add(eppj);
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return eppjs;
	}
	
	public ArrayList<EPPostJob> getEPPostJobAll(String EPusername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_eppostjob " +
				"where EPusername = ? order by postdate desc";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, EPusername);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
		while(rs.next()){
			EPPostJob eppj = null;
			eppj = new EPPostJob();
			eppj.setEPusername(rs.getString("EPusername"));
			eppj.setJobname(rs.getString("jobname"));
			eppj.setJobsalary(rs.getString("jobsalary"));
			eppj.setJobdiploma(rs.getString("jobdiploma"));
			eppj.setEngrequest(rs.getString("engrequest"));
			eppj.setReqnum(rs.getInt("reqnum"));
			eppj.setPostdate(rs.getDate("postdate"));
			eppj.setBenefits(rs.getString("benefits"));
			eppj.setJobdescribe(rs.getString("jobdescribe"));
			eppj.setJobduty(rs.getString("jobduty"));
			eppj.setTechrequest(rs.getString("techrequest"));
			eppj.setJobkind(rs.getString("jobkind"));
			eppj.setJobaddr(rs.getString("jobaddr"));
			eppj.setStatus(rs.getInt("status"));
			eppjs.add(eppj);
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return eppjs;
	}
	public ArrayList<EPPostJob> getEPPostJobAllup(String browseusername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_eppostjob " +
				"where status=1 order by postdate desc";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
		while(rs.next()){
			EPPostJob eppj = null;
			eppj = new EPPostJob();
			eppj.setEPusername(rs.getString("EPusername"));
			eppj.setJobname(rs.getString("jobname"));
			eppj.setJobsalary(rs.getString("jobsalary"));
			eppj.setJobdiploma(rs.getString("jobdiploma"));
			eppj.setEngrequest(rs.getString("engrequest"));
			eppj.setReqnum(rs.getInt("reqnum"));
			eppj.setPostdate(rs.getDate("postdate"));
			eppj.setBenefits(rs.getString("benefits"));
			eppj.setJobdescribe(rs.getString("jobdescribe"));
			eppj.setJobduty(rs.getString("jobduty"));
			eppj.setTechrequest(rs.getString("techrequest"));
			eppj.setJobkind(rs.getString("jobkind"));
			eppj.setJobaddr(rs.getString("jobaddr"));
			eppj.setStatus(rs.getInt("status"));
			eppjs.add(eppj);
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return eppjs;
	}
	public ArrayList<EPPostJob> getEPPostJobAlldown(String browseusername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_eppostjob " +
				"where status=0 order by postdate desc";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
		while(rs.next()){
			EPPostJob eppj = null;
			eppj = new EPPostJob();
			eppj.setEPusername(rs.getString("EPusername"));
			eppj.setJobname(rs.getString("jobname"));
			eppj.setJobsalary(rs.getString("jobsalary"));
			eppj.setJobdiploma(rs.getString("jobdiploma"));
			eppj.setEngrequest(rs.getString("engrequest"));
			eppj.setReqnum(rs.getInt("reqnum"));
			eppj.setPostdate(rs.getDate("postdate"));
			eppj.setBenefits(rs.getString("benefits"));
			eppj.setJobdescribe(rs.getString("jobdescribe"));
			eppj.setJobduty(rs.getString("jobduty"));
			eppj.setTechrequest(rs.getString("techrequest"));
			eppj.setJobkind(rs.getString("jobkind"));
			eppj.setJobaddr(rs.getString("jobaddr"));
			eppj.setStatus(rs.getInt("status"));
			eppjs.add(eppj);
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return eppjs;
	}
	
	public void updateEPPostJob(EPPostJob eppj) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_eppostjob " +
				"set jobname = ?,jobsalary = ?,jobdiploma = ?,engrequest = ?,reqnum = ?,postdate = ?,benefits = ?, " +
				"jobdescribe = ?,jobduty = ?,techrequest = ?,jobkind = ?,jobaddr = ?,jobcheck='0' " +
				"where EPusername = ? and jobname=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, eppj.getJobname());
		ptmt.setString(2, eppj.getJobsalary());
		ptmt.setString(3, eppj.getJobdiploma());
		ptmt.setString(4, eppj.getEngrequest());
		ptmt.setInt(5, eppj.getReqnum());
		ptmt.setDate(6, new Date(eppj.getPostdate().getTime()));
		ptmt.setString(7, eppj.getBenefits());
		ptmt.setString(8, eppj.getJobdescribe());
		ptmt.setString(9, eppj.getJobduty());
		ptmt.setString(10, eppj.getTechrequest());
		ptmt.setString(11, eppj.getJobkind());
		ptmt.setString(12, eppj.getJobaddr());
		ptmt.setString(13, eppj.getEPusername());
		ptmt.setString(14, eppj.getJobname());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	public ArrayList<StuSelectResult> queryEPPostJob(String jobaddr,String jobkind,String trade) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String condition = "";
		//拼接条件语句
		if(!"".equals(jobaddr)){
			condition += "and tb_epdata.EPaddr = \'" + jobaddr + "\' ";
		}
		if(!"".equals(trade)){
			condition += "and tb_epdata.EPtrade = \'" + trade + "\' ";
		}
		if(!"".equals(jobkind)){
			condition += "and tb_eppostjob.jobkind = \'" + jobkind + "\' ";
		}
		String sql = "" +
				"select tb_epdata.EPusername,tb_epdata.EPname, tb_eppostjob.jobname,tb_eppostjob.jobaddr,tb_eppostjob.jobsalary from tb_epdata,tb_eppostjob " +
				"where tb_epdata.EPusername = tb_eppostjob.EPusername and tb_eppostjob.jobcheck='1' and  tb_eppostjob.status = 1 " + condition;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<StuSelectResult> arrUsername = new ArrayList<StuSelectResult>();
		while(rs.next()){
			StuSelectResult res=new StuSelectResult();
			res.setEPname(rs.getString("EPname"));
			res.setJobname(rs.getString("jobname"));
			res.setEPusername(rs.getString("EPusername"));
			res.setJobaddr(rs.getString("jobaddr"));
			res.setSalary(rs.getString("jobsalary"));
			arrUsername.add(res);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrUsername;
	}
	
	//按时间倒序搜索最新发布的5个职位
	public ArrayList<String> queryLastestPost() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select distinct EPusername from tb_eppostjob " +
				"where status = 1 and tb_eppostjob.jobcheck='1' " +
				"order by postdate desc ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<String> arrName = new ArrayList<String>();
		int i = 0;
		while(rs.next() && i<5){
			arrName.add(rs.getString("EPusername"));
			i++;
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrName;
	}
	
	//职位细节页面的职位推荐，参照物为地址、行业、职能类别
	public ArrayList<String> queryRecommend(String addr,String trade,String jobkind) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select tb_epdata.EPusername from tb_epdata,tb_eppostjob " +
				"where tb_epdata.EPusername = tb_eppostjob.EPusername and tb_epdata.EPaddr = ? and tb_epdata.EPtrade = ? and tb_eppostjob.jobkind = ? and tb_eppostjob.status = 1 ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, addr);
		ptmt.setString(2, trade);
		ptmt.setString(3, jobkind);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<String> arrUsername = new ArrayList<String>();
		while(rs.next()){
			arrUsername.add(rs.getString("EPusername"));
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrUsername;
	}
	
	//管理员删除该公司发布的职位
	public void delEPPostJob(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_eppostjob where EPusername = \'" + epUsername + "\'";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
		DBUtil.close(conn);
	}
	
	//查询出所有的职位
	public ArrayList<EPPostJob> queryAll() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_epuser,tb_eppostjob " +
				"where tb_epuser.EPusername = tb_eppostjob.EPusername ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<EPPostJob> arrEPPostJob = new ArrayList<EPPostJob>();
		EPPostJob eppj = null;
		while(rs.next()){
			eppj = new EPPostJob();
			eppj.setEPusername(rs.getString("EPusername"));
			eppj.setJobname(rs.getString("jobname"));
			eppj.setJobsalary(rs.getString("jobsalary"));
			eppj.setJobdiploma(rs.getString("jobdiploma"));
			eppj.setEngrequest(rs.getString("engrequest"));
			eppj.setReqnum(rs.getInt("reqnum"));
			eppj.setPostdate(rs.getDate("postdate"));
			eppj.setBenefits(rs.getString("benefits"));
			eppj.setJobdescribe(rs.getString("jobdescribe"));
			eppj.setJobduty(rs.getString("jobduty"));
			eppj.setTechrequest(rs.getString("techrequest"));
			eppj.setJobkind(rs.getString("jobkind"));
			eppj.setJobaddr(rs.getString("jobaddr"));
			arrEPPostJob.add(eppj);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrEPPostJob;
	}
	
	//修改职位的公开状态
	public void modifyJobAuthority(String epUsername,int status,String jobname) throws SQLException{
		Connection conn = DBUtil.getConnection();
//		System.out.println(jobname);
		String sql = "" +
				"update tb_eppostjob " +
				"set status = ? " +
				"where EPusername = ? and jobname=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, status);
		ptmt.setString(2, epUsername);
		ptmt.setString(3, jobname);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	//查询出该月各行业发布的职位数量
	public ArrayList<TradeAndCount> queryCount() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select tb_epdata.EPtrade,count(tb_epdata.EPtrade) from tb_epdata,tb_eppostjob " +
				"where tb_epdata.EPusername = tb_eppostjob.EPusername " +
				"and date_format(tb_eppostjob.postdate,'%Y-%m')=date_format(now(),'%Y-%m') " +
				"group by tb_epdata.EPtrade " +
				"order by count(tb_epdata.EPtrade) desc ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<TradeAndCount> arrCount = new ArrayList<TradeAndCount>();
		TradeAndCount tac = null;
		while(rs.next()){
			tac = new TradeAndCount();
			tac.setTrade(rs.getString("tb_epdata.EPtrade"));
			tac.setCount(rs.getInt("count(tb_epdata.EPtrade)"));
			arrCount.add(tac);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrCount;
	}
	
	//查询出该月各行业发布职位的总数量
	public int getCount() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select count(tb_epdata.EPtrade) from tb_epdata,tb_eppostjob " +
				"where tb_epdata.EPusername = tb_eppostjob.EPusername " +
				"and date_format(tb_eppostjob.postdate,'%Y-%m')=date_format(now(),'%Y-%m') ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		int count = 0;
		while(rs.next()){
			count = rs.getInt("count(tb_epdata.EPtrade)");
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return count;
	}

	public EPPostJob getEPPostJob(String EPusername, String jobName) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_eppostjob " +
				"where EPusername=? and jobname=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, EPusername);
		ptmt.setString(2, jobName);
		ResultSet rs = ptmt.executeQuery();
		EPPostJob eppj=new EPPostJob();
		while(rs.next()){
			eppj.setEPusername(rs.getString("EPusername"));
			eppj.setJobname(rs.getString("jobname"));
			eppj.setJobsalary(rs.getString("jobsalary"));
			eppj.setJobdiploma(rs.getString("jobdiploma"));
			eppj.setEngrequest(rs.getString("engrequest"));
			eppj.setReqnum(rs.getInt("reqnum"));
			eppj.setPostdate(rs.getDate("postdate"));
			eppj.setBenefits(rs.getString("benefits"));
			eppj.setJobdescribe(rs.getString("jobdescribe"));
			eppj.setJobduty(rs.getString("jobduty"));
			eppj.setTechrequest(rs.getString("techrequest"));
			eppj.setJobkind(rs.getString("jobkind"));
			eppj.setJobaddr(rs.getString("jobaddr"));
			eppj.setStatus(rs.getInt("status"));
		}
//		System.out.println(eppj.getBenefits());
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return eppj;
	}
	//根据关键字搜索职位 返回职位名和EPusername
		public HashMap<String,String> queryJobnames(String keyword) throws SQLException{
			Connection conn = DBUtil.getConnection();
			String sql = "" +
					"select EPusername,jobname from tb_eppostjob " +
					"where jobcheck='1' and jobname like \'%" + keyword + "%\' ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			HashMap<String,String> resmap = new HashMap<String,String>();
			while(rs.next()){
				resmap.put(rs.getString("EPusername"), rs.getString("jobname"));
				
			}
			rs.close();
			stmt.close();
			DBUtil.close(conn);
			return resmap;
		}
		//根据公司名和工作名获取工资
		public String getSalary(String epUsername, String jobname) {
			
			return null;
		}

		public ArrayList<EPPostJob> queryLatestAll(String times) throws SQLException {
			// TODO Auto-generated method stub
			Connection conn = DBUtil.getConnection();
			String sql = "" +
					"select * from tb_epuser,tb_eppostjob " +
					"where tb_epuser.EPusername = tb_eppostjob.EPusername and to_days(now())-to_days(tb_eppostjob.postdate)<? order by tb_eppostjob.postdate desc";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, times);
			ResultSet rs = ptmt.executeQuery();
			ArrayList<EPPostJob> arrEPPostJob = new ArrayList<EPPostJob>();
			EPPostJob eppj = null;
			while(rs.next()){
				eppj = new EPPostJob();
				eppj.setEPusername(rs.getString("EPusername"));
				eppj.setJobname(rs.getString("jobname"));
				eppj.setJobsalary(rs.getString("jobsalary"));
				eppj.setJobdiploma(rs.getString("jobdiploma"));
				eppj.setEngrequest(rs.getString("engrequest"));
				eppj.setReqnum(rs.getInt("reqnum"));
				eppj.setPostdate(rs.getDate("postdate"));
				eppj.setBenefits(rs.getString("benefits"));
				eppj.setJobdescribe(rs.getString("jobdescribe"));
				eppj.setJobduty(rs.getString("jobduty"));
				eppj.setTechrequest(rs.getString("techrequest"));
				eppj.setJobkind(rs.getString("jobkind"));
				eppj.setJobaddr(rs.getString("jobaddr"));
				arrEPPostJob.add(eppj);
			}
			rs.close();
			ptmt.close();
			DBUtil.close(conn);
			return arrEPPostJob;
		}

		public ArrayList<EPPostJob> queryUnchecktAll() throws SQLException {
			// TODO Auto-generated method stub
			Connection conn = DBUtil.getConnection();
			String sql = "" +
					"select * from tb_epuser,tb_eppostjob " +
					"where tb_epuser.EPusername = tb_eppostjob.EPusername and tb_eppostjob.jobcheck='0'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<EPPostJob> arrEPPostJob = new ArrayList<EPPostJob>();
			EPPostJob eppj = null;
			while(rs.next()){
				eppj = new EPPostJob();
				eppj.setEPusername(rs.getString("EPusername"));
				eppj.setJobname(rs.getString("jobname"));
				eppj.setJobsalary(rs.getString("jobsalary"));
				eppj.setJobdiploma(rs.getString("jobdiploma"));
				eppj.setEngrequest(rs.getString("engrequest"));
				eppj.setReqnum(rs.getInt("reqnum"));
				eppj.setPostdate(rs.getDate("postdate"));
				eppj.setBenefits(rs.getString("benefits"));
				eppj.setJobdescribe(rs.getString("jobdescribe"));
				eppj.setJobduty(rs.getString("jobduty"));
				eppj.setTechrequest(rs.getString("techrequest"));
				eppj.setJobkind(rs.getString("jobkind"));
				eppj.setJobaddr(rs.getString("jobaddr"));
				arrEPPostJob.add(eppj);
			}
			rs.close();
			stmt.close();
			DBUtil.close(conn);
			return arrEPPostJob;
		}
	
}
