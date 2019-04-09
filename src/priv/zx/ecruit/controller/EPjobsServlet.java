package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.dao.StuStoreDao;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.EPStore;
import priv.zx.ecruit.model.StuSelectResult;

/**
 * Servlet implementation class EPjobsServlet
 * 企业职位操作
 */
@WebServlet("/EPjobsServlet")
public class EPjobsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EPjobsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//取得url中的stuUsername
		String EPusername = request.getParameter("EPusername");
		//从session中获取毕业生用户名
		HttpSession session = request.getSession();
		String stuUsername = (String) session.getAttribute("stuUser");
		//创建查询结果的List
		ArrayList<StuSelectResult> arrSsr = new ArrayList<StuSelectResult>();
		//创建StuSelectResult对象
		StuSelectResult ssr = null;
		//创建EPData对象
		EPData epd = new EPData();
		//创建EPPostJob对象
		EPPostJob eppj = new EPPostJob();
		//创建EPDataDao和EPPostJobDao对象
		EPDataDao epdd = new EPDataDao();
		EPPostJobDao eppjd = new EPPostJobDao();
		ArrayList<EPPostJob> arrEPStore = null;
		try {
			arrEPStore = eppjd.getEPPostJob(EPusername);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//遍历arrEpUsername
		for (EPPostJob es : arrEPStore) {
			ssr = new StuSelectResult();
			ssr.setEPusername(es.getEPusername());
			//根据epUsername查询出EPData
			try {
				epd = epdd.getEPData(es.getEPusername());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ssr.setEPname(epd.getEPname());
			ssr.setJobaddr(es.getJobaddr());
			ssr.setJobname(es.getJobname());	
			ssr.setSalary(es.getJobsalary());
			//将ssr添入arrSsr
			arrSsr.add(ssr);
		}
				//将arrSsr放入request
		request.setAttribute("epUsernames", arrSsr);	
		//查询推荐的职位功能
		//创建StuStoreDao数据库操作对象
		StuStoreDao ssd = new StuStoreDao();
		if(arrEPStore.size() > 0){
			//若该毕业生有收藏的公司
			//查询收藏的公司中出现过的地址和职能类别
			ArrayList<String> arrAddr = new ArrayList<String>();
			ArrayList<String> arrJobkind = new ArrayList<String>();
			//查询推荐的公司用户名
			ArrayList<String> arrUsername = new ArrayList<String>();
			try {
				arrAddr = ssd.queryAddr(stuUsername);
				arrJobkind = ssd.queryJobkind(stuUsername);
				arrUsername = ssd.queryRecommend(arrAddr, arrJobkind);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(arrUsername != null){
				ArrayList<StuSelectResult> arrRecommend = new ArrayList<StuSelectResult>();
				//创建EPDataDao和EPPostJobDao对象
				//创建EPData和EPPostJob对象
				EPData epd1 = new EPData();
				EPPostJob eppj1 = new EPPostJob();
				for (String username : arrUsername) {
					ssr = new StuSelectResult();
					ssr.setEPusername(username);
					try {
						epd1 = epdd.getEPData(username);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					ssr.setJobaddr(epd1.getEPaddr());
					ssr.setEPname(epd1.getEPname());
					try {
						eppj1 = eppjd.getEPPostJob(username).get(0);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					ssr.setJobname(eppj1.getJobname());
					ssr.setSalary(eppj1.getJobsalary());
					arrRecommend.add(ssr);
				}
				request.setAttribute("recommends1", arrRecommend);
				request.setAttribute("strRecommends1", arrRecommend.toString());
			}
		}
		
		//转到stuShowStore.jsp页面
		request.getRequestDispatcher("../EPjobs.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
