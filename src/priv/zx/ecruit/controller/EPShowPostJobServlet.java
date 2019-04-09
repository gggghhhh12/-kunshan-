package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPPostJob;

public class EPShowPostJobServlet extends HttpServlet {

	/**
	 *  企业展示发布的职位
	 */
	private static final long serialVersionUID = 1L;
	
	//将带段落的字符串s转换成有格式的字符串，方便在页面显示
	private StringBuilder getFormatString(String s){
		//按回车符将原字符串分成段落字符串
		String[] params = s.split("\r\n");
		//将每段字符串格式化输出，每行30字
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < params.length;i++){
			char[] letters = params[i].toCharArray();
			for(int j = 0;j < letters.length; j++){
				if(letters[j] == ' '){
					sb.append("&nbsp;".toCharArray());
				}else{
					sb.append(letters[j]);
				}
				if((j+1)%30 == 0){
					sb.append("<br>".toCharArray());
				}
			}
			sb.append("<br>".toCharArray());
		}		
		return sb;
	}

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

		//创建EPPostJob职位对象列表
		ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
		//创建EPPostJobDao职位要求数据库操作对象
		EPPostJobDao eppjd = new EPPostJobDao();
		//从session中获取企业用户名
		HttpSession session = request.getSession();
		String EPusername = (String) session.getAttribute("EPUser");
		String res=request.getParameter("res");
		//从数据库中查找出该公司发布的职位要求
		try {
			if(res.equals("all")){
				eppjs = eppjd.getEPPostJobAll(EPusername);
			}
			else{
				eppjs = eppjd.getEPPostJob(EPusername);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//将职位要求对象放入request中
		request.setAttribute("EPPostJobs", eppjs);
//		if(eppj != null){
//			//将职位描述格式化字符串
//			StringBuilder sbJobdescribe = new StringBuilder();
//			sbJobdescribe = getFormatString(eppj.getJobdescribe());
//			request.setAttribute("jobdescribe", sbJobdescribe);
//			//将岗位职责格式化字符串
//			StringBuilder sbJobduty = new StringBuilder();
//			sbJobduty = getFormatString(eppj.getJobdescribe());
//			request.setAttribute("jobduty", sbJobduty);
//			//将技术要求格式化字符串
//			StringBuilder sbTechrequest = new StringBuilder();
//			sbTechrequest = getFormatString(eppj.getJobdescribe());
//			request.setAttribute("techrequest", sbTechrequest);
//			//处理公开权限状态
//			int status = eppj.getStatus();
//			if(status == 0){
//				request.setAttribute("auth", "(下架状态)");
//			}else{
//				request.setAttribute("auth", "(上架状态)");
//			}
//		}
		//转向EPShowPostJob.jsp页面
		request.getRequestDispatcher("../EPShowPostJob.jsp").forward(request, response);
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
