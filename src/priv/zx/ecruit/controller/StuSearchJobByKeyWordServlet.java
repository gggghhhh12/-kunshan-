package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.StuSelectResult;

/**
 * Servlet implementation class StuSearchJobByKeyWord
 */
public class StuSearchJobByKeyWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuSearchJobByKeyWordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取表单中的keyword
		String keyword = request.getParameter("keyword");

		//创建EPPostJob对象，查询出有此关键字的职位
		
		EPDataDao epdatadao = new EPDataDao();
		EPPostJobDao epdd = new EPPostJobDao();
		HashMap<String,String> res = new HashMap<String,String>();
		try {
			//查询出相关职位名称和公司名
			res = epdd.queryJobnames(keyword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//将职位信息封装成StuSelectResult对象
		//存放结果的列表
		ArrayList<StuSelectResult> arrSsr = new ArrayList<StuSelectResult>();
		StuSelectResult ssr = null;
		if(res.size() > 0){
			EPPostJobDao eppostjobdao = new EPPostJobDao();
			EPData epdata = new EPData();
			ArrayList<EPPostJob> epppstjobs = new ArrayList<EPPostJob>();

			//存放公司名
			Set<String> epusernames = res.keySet();
			Collection<String> values= res.values();
			Iterator<String> iterator1 = epusernames.iterator();
			Iterator<String> iterator2=values.iterator();
			//当两个都不空时
			while(iterator1.hasNext()&&iterator2.hasNext())
			{
				try{
					//获取企业名和工作名
					String epUsername = iterator1.next();
					String jobname  = iterator2.next();
					epdata = epdatadao.getEPData(epUsername);
					EPPostJob eppostjob = eppostjobdao.getEPPostJob(epUsername, jobname);
					
					ssr = new StuSelectResult();
					ssr.setEPusername(epUsername);
					ssr.setEPname(epdata.getEPname());
					ssr.setJobaddr(epdata.getEPaddr());
					ssr.setJobname(jobname);
					
					ssr.setSalary(eppostjob.getJobsalary());
					arrSsr.add(ssr);
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
			
			//根据公司名搜索并加入结果集中
			
			EPDataDao epdd1 = new EPDataDao();
			ArrayList<String> arrEPusernames = new ArrayList<String>();
			try {
				arrEPusernames = epdd1.queryEPusesnames(keyword);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//将公司信息封装成StuSelectResult对象
			if(arrEPusernames.size() > 0){
				EPPostJobDao eppjd = new EPPostJobDao();
				EPData epd2 = new EPData();
				ArrayList<EPPostJob> eppjs = new ArrayList<EPPostJob>();
				
				
				for (String epUsername : arrEPusernames) {
					try {
						epd2 = epdd1.getEPData(epUsername);
						eppjs = eppjd.getEPPostJob(epUsername);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					for(int i=0;i<eppjs.size();i++){
						if(epd2 !=null && eppjs.get(i) != null){
							ssr = new StuSelectResult();
							ssr.setEPusername(epUsername);
							ssr.setEPname(epd2.getEPname());
							ssr.setJobaddr(epd2.getEPaddr());
							ssr.setJobname(eppjs.get(i).getJobname());
							ssr.setSalary(eppjs.get(i).getJobsalary());
							arrSsr.add(ssr);
					}
					}
				}
			}
			

			if(arrSsr.isEmpty())
			{
				request.setAttribute("error", "暂无查询结果");
			}
			else
			{
				request.setAttribute("arrSsr", arrSsr);
			}
		
		request.getRequestDispatcher("StuHomeServlet").forward(request, response);	
		
	}
}
