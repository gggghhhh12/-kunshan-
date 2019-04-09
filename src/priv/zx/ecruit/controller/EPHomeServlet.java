package priv.zx.ecruit.controller;

/**
 * EPHome.jsp页面的人才推荐功能servlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPSelectResult;
import priv.zx.ecruit.model.Education;
import priv.zx.ecruit.model.TradeAndCount;

public class EPHomeServlet extends HttpServlet {

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

		request.setCharacterEncoding("utf-8");
		//从session中获取公司用户名
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		//创建EPDataDao对象
		EPDataDao epdd = new EPDataDao();
		//创建EPData对象
		EPData epd = new EPData();
		//查找出该公司的信息
		try {
			epd = epdd.getEPData(epUsername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JobIntentionDao jid = new JobIntentionDao();
		if(epd != null){
			//查找出符合该公司的人才（根据求职地点和行业）
			ArrayList<String> arrStuUsername = new ArrayList<String>();
			try {
				arrStuUsername = jid.queryRecommend(epd.getEPaddr(), epd.getEPtrade());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//将推荐的人的信息转化为EPSelectResult对象
			if(arrStuUsername.size() > 0){
				ArrayList<EPSelectResult> arrEPSr = new ArrayList<EPSelectResult>();
				EPSelectResult epsr = null;
				//创建BasicInfoDao和EducationDao对象
				BasicInfoDao bid = new BasicInfoDao();
				EducationDao ed = new EducationDao();
				//创建BasicInfo和Education对象
				BasicInfo bi = new BasicInfo();
				Education e = new Education();
				for (String stuUsername : arrStuUsername) {
					epsr = new EPSelectResult();
					epsr.setStuUsername(stuUsername);
					try {
						bi = bid.getBasicInfo(stuUsername);
						e = ed.getEducation(stuUsername);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(bi != null && e!= null){
						epsr.setStuName(bi.getName());
						epsr.setSex(bi.getSex());
						epsr.setSchool(e.getEduschool());
						epsr.setMajor(e.getEdumajor());
						arrEPSr.add(epsr);
					}
				}
				//将数列存入request
				request.setAttribute("recommends", arrEPSr);
				request.setAttribute("strRecommends", arrEPSr.toString());
			}else{
				request.setAttribute("error", "暂无推荐");
			}
		}else{
			request.setAttribute("error", "请先完善公司信息");
		}
		
		//发布各行业求职人数
		try {
			int count = jid.getCount();
			if(count > 0){
				ArrayList<TradeAndCount> arrCount = new ArrayList<TradeAndCount>();
				arrCount = jid.queryCount();
				request.setAttribute("counts", arrCount);
				request.setAttribute("sum", count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//转向EPHome.jsp页面
		request.getRequestDispatcher("../EPHome.jsp").forward(request, response);
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

		doGet(request,response);
	}

}
