package priv.zx.ecruit.test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
 

import priv.zx.ecruit.dao.AdminUserDao;
import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.CommentDao;
import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.dao.EPStoreDao;
import priv.zx.ecruit.dao.EPUserDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.dao.JobWantedDao;
import priv.zx.ecruit.dao.NewsDao;
import priv.zx.ecruit.dao.ReportDao;
import priv.zx.ecruit.dao.StuStoreDao;
import priv.zx.ecruit.dao.StuUserDao;
import priv.zx.ecruit.dao.StuWantedDao;
import priv.zx.ecruit.model.AdminResume;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.Comment;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.EPStore;
import priv.zx.ecruit.model.EPUser;
import priv.zx.ecruit.model.Education;
import priv.zx.ecruit.model.JobIntention;
import priv.zx.ecruit.model.JobWanted;
import priv.zx.ecruit.model.News;
import priv.zx.ecruit.model.Report;
import priv.zx.ecruit.model.StuStore;
import priv.zx.ecruit.model.StuWanted;
import priv.zx.ecruit.model.TradeAndCount;
import priv.zx.ecruit.model.User;

public class Test {
	public static void main(String[] args) throws SQLException {
		
//		StuUserDao ud = new StuUserDao();
//		User u = new User();
//		u.setUsername("123");
//		u.setPassword("234");
//		ud.addUser(u);
		
//		User u = new User();
//		u.setUsername("zhengxiang");
//		u.setPassword("122");
//		ud.updateUser(u);
		
//		String password = ud.getPassword("zhengxiang");
//		System.out.println(password);
		
//		System.out.println(ud.isExist("zhengxiang"));
		
//		BasicInfoDao bid = new BasicInfoDao();
//		BasicInfo bi = new BasicInfo();
//		bi.setUsername("zhengxiang");
//		bi.setName("郑翔");
//		bi.setSex("男");
//		bi.setNation("汉族");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String date = "1994-6-25";
//		try {
//			bi.setBirthday(sdf.parse(date));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		bi.setTel("15295501121");
//		bi.setEmail("123@qq.com");
//		bi.setLiveaddr("江苏南京");
//		bi.setResidence("浙江金华");
//		bid.updateBasicInfo(bi);
		
//		EducationDao ed = new EducationDao();
//		Education edu = new Education();
//		edu.setUsername("zhengxiang");
//		String enterTime = "2012-9";
//		String gradTime = "2016-6";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//		try {
//			edu.setEnterTime(sdf.parse(enterTime));
//			edu.setGradTime(sdf.parse(gradTime));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		edu.setEduschool("南京大学");
//		edu.setEdumajor("计算机科学与技术");
//		edu.setEdudiploma("本科");
//		edu.setEnglevel("英语六级");
//		edu.setEduduty("2012年9月-2013年6月 团支书");
//		edu.setEduaward("2012年9月-2013年月 一等奖学金");
//		edu.setEduprictise("2012年9月-2013年月 xx实习");
//		edu.setAbroad("否");
//		ed.updateEducation(edu);
		
//		JobIntentionDao jid = new JobIntentionDao();
//		JobIntention ji = new JobIntention();
//		ji.setUsername("zhengxiang");
//		ji.setKeyword("计算机 机智");
//		ji.setEvaluation("我是最棒滴");
//		ji.setPlace("南京");
//		ji.setTrade("IT");
//		ji.setSalary("15000以上");
//		jid.updateJobIntention(ji);
		
//		BasicInfoDao bid = new BasicInfoDao();
//		BasicInfo bi = new BasicInfo();
//		bi = bid.getBasicInfo("zhengxiang");
//		System.out.println(bi.getName() + " " + bi.getSex() + " " + bi.getNation() + " " + bi.getBirthday() + " " +
//					bi.getTel() + " " + bi.getEmail() + " " + bi.getLiveaddr() + " " + bi.getResidence());
		
//		EducationDao ed = new EducationDao();
//		Education edu = new Education();
//		edu = ed.getEducation("zhengxiang");
//		System.out.println(edu.getEnterTime() + " " + edu.getGradTime() + " " + edu.getEduschool() + " " + 
//					edu.getEdumajor() + " " +edu.getEdudiploma() + " "+ edu.getEnglevel() + " " + 
//					edu.getEduduty() + " " + edu.getEduaward() + " " + edu.getEduprictise() + " " + edu.getAbroad());
		
//		JobIntentionDao jid = new JobIntentionDao();
//		JobIntention ji = new JobIntention();
//		ji = jid.getJobIntention("zhengxiang");
//		System.out.println(ji.getKeyword() + " " + ji.getEvaluation() + " " + ji.getPlace() + " " +
//					ji.getTrade() + " " +ji.getSalary());
		
//		EPUserDao epud = new EPUserDao();
//		EPUser epu = new EPUser();
//		epu.setEPname("haha");
//		epu.setEPpassword("12345");
//		epud.addEPUser(epu);
		
//		EPUserDao epud = new EPUserDao();
//		EPUser epu = new EPUser();
//		epu.setEPname("haha");
//		epu.setEPpassword("123456");
//		epud.updateEPUser(epu);
		
//		EPUserDao epu = new EPUserDao();
//		String EPpassword = epu.getEPpassword("haha");
//		System.out.println(EPpassword);
		
//		EPUserDao epu = new EPUserDao();
//		System.out.println(epu.isExist("haha"));
		
//		EPDataDao epdd = new EPDataDao();
//		EPData epd = new EPData();
//		epd.setEPusername("haha");
//		epd.setEPname("国际有限公司");
//		epd.setEPnature("外资");
//		epd.setEPcode("123456789");
//		epd.setEPtrade("TI");
//		epd.setEPscale("大型");
//		epd.setEPaddr("南京");
//		epd.setEPcontact("李四");
//		epd.setEPemail("123456@gj.com");
//		epd.setEPtel("12345678");
//		epd.setEPmobile("12345678901");
//		epd.setEPpostalcode("123456");
//		epd.setEPintroduction("接发垃圾佛奥及佛教哦胶卷分行好好公安共啊哦哦啊好工行和哦啊后宫好和公安和都好怪哦啊后宫Oahu讴歌红");
//		epdd.addEPDataDao(epd);
		
//		EPDataDao epdd = new EPDataDao();
//		EPData epd = new EPData();
//		epd = epdd.getEPData("haha");
//		System.out.println(epd.getEPname() + " " + epd.getEPnature() + " " + epd.getEPcode());
		
//		EPPostJobDao eppjd = new EPPostJobDao();
//		EPPostJob eppj = new EPPostJob();
//		eppj.setEPusername("haha");
//		eppj.setJobname("高级软件工程师");
//		eppj.setJobsalary("12000");
//		eppj.setJobdiploma("本科以上");
//		eppj.setEngrequest("四级");
//		eppj.setReqnum(6);
//		eppj.setPostdate(new Date());
//		eppj.setBenefits("五险一金 包食宿");
//		eppj.setJobdescribe("很牛逼的职位");
//		eppj.setJobduty("项目开发");
//		eppj.setTechrequest("精通java,C++");
//		eppj.setJobkind("软件工程师");
//		eppj.setJobaddr("江苏南京玄武区卫岗一号");
//		eppjd.updateEPPostJob(eppj);
		
//		EPPostJobDao eppjd = new EPPostJobDao();
//		EPPostJob eppj = new EPPostJob();
//		eppj = eppjd.getEPPostJob("haha");
//		System.out.println(eppj.getJobname() + " " + eppj.getBenefits());
		
//		EPPostJobDao eppjd = new EPPostJobDao();
//		ArrayList<String> usernames = new ArrayList<String>();
//		usernames =eppjd.queryEPPostJob("江苏南京", "软件工程师", "IT");
//		System.out.println(usernames.size());
		
//		StuStore ss = new StuStore();
//		StuStoreDao ssd = new StuStoreDao();
//		ss.setStuUsername("zhengxiang");
//		ss.setEpUsername("haha");
//		System.out.println(ssd.isExist(ss));
		
//		StuStoreDao ssd = new StuStoreDao();
//		ArrayList<String> arr = ssd.queryStuStore("zhengxiang");
//		for (String string : arr) {
//			System.out.println(string);
//		}
		
//		StuStoreDao ssd = new StuStoreDao();
//		StuStore ss = new StuStore();
//		ss.setStuUsername("zhengxiang");
//		ss.setEpUsername("haha");
//		ssd.delStuStore(ss);
		
//		EPPostJobDao eppjd = new EPPostJobDao();
//		ArrayList<String> arrName = new ArrayList<String>();
//		arrName = eppjd.queryRecommend("", "IT", "");
//		for (String name : arrName) {
//			System.out.println(name);
//		}
		
//		StuStoreDao ssd = new StuStoreDao();
//		ArrayList<String> arrAddr = new ArrayList<String>();
//		arrAddr = ssd.queryAddr("zhengxiang");
//		for (String string : arrAddr) {
//			System.out.println(string);
//		}

		
//		StuStoreDao ssd = new StuStoreDao();
//		ArrayList<String> arrAddr = new ArrayList<String>();
//		ArrayList<String> arrJobkind = new ArrayList<String>();
//		arrAddr = ssd.queryAddr("zhengxiang");
//		arrJobkind = ssd.queryJobkind("zhengxiang");
//		ArrayList<String> arrUsername = new ArrayList<String>();
//		arrUsername = ssd.queryRecommend(arrAddr, arrJobkind);
//		for (String string : arrUsername) {
//			System.out.println(string);
//		}
		
//		JobWantedDao jwd = new JobWantedDao();
//		JobWanted jw = new JobWanted();
//		jw.setStuUsername("zhengxiang");
//		jw.setEpUsername("haha");
//		System.out.println(jwd.isExist(jw));
		
//		JobIntentionDao jid = new JobIntentionDao();
//		ArrayList<String> arrUsername = jid.queryStuUsername("南京", "15000以上", "IT", "本科");
//		for (String string : arrUsername) {
//			System.out.println(string);
//		}
		
//		JobIntentionDao jid = new JobIntentionDao();
//		ArrayList<String> arrUsername = new ArrayList<String>();
//		arrUsername = jid.queryRecommend("江苏南京", "IT");
//		for (String string : arrUsername) {
//			System.out.println(string);
//		}
		
//		EPStoreDao epsd = new EPStoreDao();
//		EPStore eps = new EPStore();
//		eps.setEpUsername("haha");
//		eps.setStuUsername("zhengxiang");
//		epsd.delEPStore(eps);
		
//		EPStoreDao epsd = new EPStoreDao();
//		EPStore eps = new EPStore();
//		eps.setEpUsername("haha");
//		eps.setStuUsername("zhengxiang");
//		System.out.println(epsd.isExist(eps));
		
//		EPStoreDao epsd = new EPStoreDao();
//		ArrayList<String> arrStuUsername = new ArrayList<String>();
//		arrStuUsername = epsd.queryEPStore("haha");
//		for (String string : arrStuUsername) {
//			System.out.println(string);
//		}
		
//		EPStoreDao epsd = new EPStoreDao();
//		ArrayList<String> arrPlace = new ArrayList<String>();
//		arrPlace = epsd.queryPlace("haha");
//		ArrayList<String> arrTrade = new ArrayList<String>();
//		arrTrade = epsd.queryTrade("haha");
//		ArrayList<String> arrStuUsername = new ArrayList<String>();
//		arrStuUsername = epsd.queryRecommend(arrPlace, arrTrade);
//		for (String string : arrStuUsername) {
//			System.out.println(string);
//		}
		
//		StuWantedDao swd = new StuWantedDao();
//		StuWanted sw = new StuWanted();
//		sw.setEpUsername("haha");
//		sw.setStuUsername("zhengxiang");
//		System.out.println(swd.isExist(sw));
		
//		StuWantedDao swd = new StuWantedDao();
//		ArrayList<String> arrEPusername = new ArrayList<String>();
//		arrEPusername = swd.queryInvite("zhengxiang");
//		for (String string : arrEPusername) {
//			System.out.println(string);
//		}
		
//		JobWantedDao jwd = new JobWantedDao();
//		ArrayList<String> arrStuUsername = new ArrayList<String>();
//		arrStuUsername = jwd.queryInvite("haha");
//		for (String string : arrStuUsername) {
//			System.out.println(string);
//		}
		
//		EPDataDao epdd = new EPDataDao();
//		ArrayList<String> arrEPusername = new ArrayList<String>();
//		arrEPusername = epdd.queryEPusesnames("公司");
//		for (String string : arrEPusername) {
//			System.out.println(string);
//		}
		
//		AdminUserDao aud = new AdminUserDao();
//		System.out.println(aud.getPassword("admin"));
		
//		NewsDao nd = new NewsDao();
//		News n = new News();
//		n.setNews_id("12138");
//		n.setNews_title("haha");
//		n.setNews_content("boomshakalaka");
//		n.setNews_time(new Date());
//		nd.modifyNews(n);
		
//		NewsDao nd = new NewsDao();
//		ArrayList<News> arrNews = new ArrayList<News>();
//		arrNews = nd.queryAll();
//		for (News news : arrNews) {
//			System.out.println(news.getNews_id());
//		}
		
//		NewsDao nd = new NewsDao();
//		nd.delNews("12138");
		
//		BasicInfoDao bid = new BasicInfoDao();
//		ArrayList<BasicInfo> arrBasicInfo = new ArrayList<BasicInfo>();
//		arrBasicInfo = bid.queryAll();
//		for (BasicInfo basicInfo : arrBasicInfo) {
//			System.out.println(basicInfo.getUsername());
//		}
		
//		BasicInfoDao bid = new BasicInfoDao();
//		ArrayList<AdminResume> arrResume = new ArrayList<AdminResume>();
//		arrResume = bid.queryResumes();
//		for (AdminResume adminResume : arrResume) {
//			System.out.println(adminResume.getStuName());
//		}
		
//		EPDataDao epdd = new EPDataDao();
//		ArrayList<EPData> arrEPData = new ArrayList<EPData>();
//		arrEPData = epdd.queryAll();
//		for (EPData epData : arrEPData) {
//			System.out.println(epData.getEPusername());
//		}
		
//		CommentDao cd = new CommentDao();
//		Comment c = new Comment();
//		c.setStuUsername("zhengxiang");
//		c.setEpUsername("haha");
//		c.setRelation("在该公司工作过");
//		c.setContent("很厉害哦哈哈哈哈");
//		c.setDate(new Date());
//		cd.addComment(c);
		
//		CommentDao cd = new CommentDao();
//		ArrayList<Comment> arrComm = new ArrayList<Comment>();
//		arrComm = cd.queryAll();
//		for (Comment comment : arrComm) {
//			System.out.println(comment.getStuUsername());
//		}
		
//		EPPostJobDao eppjd = new EPPostJobDao();
//		ArrayList<TradeAndCount> arrCount = new ArrayList<TradeAndCount>();
//		arrCount = eppjd.queryCount();
//		for (TradeAndCount tradeAndCount : arrCount) {
//			System.out.println(tradeAndCount.getTrade() + " " + tradeAndCount.getCount());
//		}
		
//		EPPostJobDao eppjd = new EPPostJobDao();
//		System.out.println(eppjd.getCount());
		
		ReportDao rd =  new ReportDao();
		Report r = new Report();
		r.setStuUsername("zhengxiang");
		r.setEpUsername("haha");
		rd.delReport(r);
		
	}

}
