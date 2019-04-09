package priv.zx.ecruit.controller;

/**
 * 显示公司收藏的人servlet
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
import priv.zx.ecruit.dao.EPStoreDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.EPSelectResult;
import priv.zx.ecruit.model.Education;

public class EPShowStoreServlet extends HttpServlet {

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

		//创建收藏的毕业生用户名list
		ArrayList<String> arrStuUsername = new ArrayList<String>();
		//创建EPStoreDao数据库操作对象
		EPStoreDao epsd = new EPStoreDao();
		//从session中获取该公司的EPUser
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		//查询出该公司收藏的公司
		try {
			arrStuUsername = epsd.queryEPStore(epUsername);
			session.setAttribute("storeStuUsernames", arrStuUsername);
			//判断是否为空
			if(arrStuUsername.size() == 0){
				//若为空
				request.setAttribute("info", "您暂时无收藏的毕业生");
			}else{
				//创建EPSelectResult的list
				ArrayList<EPSelectResult> arrEPsr = new ArrayList<EPSelectResult>();
				//创建EPSelectResult对象
				EPSelectResult epsr = null;
				//创建BasicInfo和Education对象
				BasicInfo bi = new BasicInfo();
				Education e = new Education();
				//创建BasicInfoDao和EducationDao对象
				BasicInfoDao bid = new BasicInfoDao();
				EducationDao ed = new EducationDao();
				//查询出要显示的信息
				for (String stuUsername : arrStuUsername) {
					epsr = new EPSelectResult();
					epsr.setStuUsername(stuUsername);
					bi = bid.getBasicInfo(stuUsername);
					epsr.setStuName(bi.getName());
					epsr.setSex(bi.getSex());
					e = ed.getEducation(stuUsername);
					epsr.setSchool(e.getEduschool());
					epsr.setMajor(e.getEdumajor());
					arrEPsr.add(epsr);
				}
				//将arrEPsr放入request
				request.setAttribute("stuUsers", arrEPsr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//推荐功能
		//若该公司收藏过毕业生
		if(arrStuUsername.size() > 0){
			//查询出收藏的毕业生的求职地点和求职类别
			ArrayList<String> arrPlace = new ArrayList<String>();
			ArrayList<String> arrTrade = new ArrayList<String>();
			ArrayList<String> arrUsername = new ArrayList<String>();
			try {
				arrPlace = epsd.queryPlace(epUsername);
				arrTrade = epsd.queryTrade(epUsername);
				//查询出要推荐的毕业生
				arrUsername = epsd.queryRecommend(arrPlace, arrTrade);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//创建EPSelectResult对象的list
			if(arrUsername.size() > 0){
				ArrayList<EPSelectResult> arrRecommend = new ArrayList<EPSelectResult>();
				EPSelectResult epsr = null;
				//创建学生信息对象
				BasicInfo bi = new BasicInfo();
				Education e = new Education();
				//创建学生信息的数据库操作对象
				BasicInfoDao bid = new BasicInfoDao();
				EducationDao ed = new EducationDao();
				for (String username : arrUsername) {
					epsr = new EPSelectResult();
					epsr.setStuUsername(username);
					//查询出BasicInfo
					try {
						bi = bid.getBasicInfo(username);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					epsr.setStuName(bi.getName());
					epsr.setSex(bi.getSex());
					//查询出Education
					try {
						e = ed.getEducation(username);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					epsr.setSchool(e.getEduschool());
					epsr.setMajor(e.getEdumajor());
					arrRecommend.add(epsr);
				}
				request.setAttribute("recommends1", arrRecommend);
				request.setAttribute("strRecommends1", arrRecommend.toString());
			}
		}
		//转向epShowStore.jsp页面
		request.getRequestDispatcher("../epShowStore.jsp").forward(request, response);
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
