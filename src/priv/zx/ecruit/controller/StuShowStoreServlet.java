package priv.zx.ecruit.controller;

/**
 * 查询该毕业生收藏公司的servlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
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

public class StuShowStoreServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//创建公司用户名list
		ArrayList<EPStore> arrEPStore = new ArrayList<EPStore>();
		//创建StuStoreDao数据库操作对象
		StuStoreDao ssd = new StuStoreDao();
		//从session中获取毕业生用户名
		HttpSession session = request.getSession();
		String stuUsername = (String) session.getAttribute("stuUser");
		try {
			//从数据库中查询此毕业生用户所收藏的公司账户名
			arrEPStore = ssd.queryStuStore(stuUsername);
			session.setAttribute("arrEPStore", arrEPStore);
			//如果arrEpUsername为空
			if(arrEPStore.size() == 0){
				//传递信息到前端页面
				request.setAttribute("info", "您暂时无收藏的公司");
			}else{
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
				//遍历arrEpUsername
				for (EPStore es : arrEPStore) {
					ssr = new StuSelectResult();
					ssr.setEPusername(es.getEpUsername());
					//根据epUsername查询出EPData
					epd = epdd.getEPData(es.getEpUsername());
					ssr.setEPname(epd.getEPname());
					ssr.setJobname(es.getJobname());
					//根据epUsername查询出EPPostJob
					eppj = eppjd.getEPPostJob(es.getEpUsername(),ssr.getJobname());
					ssr.setJobaddr(eppj.getJobaddr());
					ssr.setSalary(eppj.getJobsalary());
					//将ssr添入arrSsr
					arrSsr.add(ssr);
				}
				//将arrSsr放入request
				request.setAttribute("epUsernames", arrSsr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//查询推荐的职位功能
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
				StuSelectResult ssr = null;
				//创建EPDataDao和EPPostJobDao对象
				EPDataDao epdd = new EPDataDao();
				EPPostJobDao eppjd = new EPPostJobDao();
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
		request.getRequestDispatcher("../stuShowStore.jsp").forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
