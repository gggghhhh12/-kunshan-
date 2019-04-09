package priv.zx.ecruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.StuSelectResult;

public class stuSelectServlet2 extends HttpServlet {

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
	@SuppressWarnings({ "unchecked", "unused" })
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		//取出session中的EPusernames
		HttpSession session = request.getSession();
		ArrayList<StuSelectResult> arrSsrs=(ArrayList<StuSelectResult>) session.getAttribute("arrSsr");
		ArrayList<StuSelectResult> arrSsr=(ArrayList<StuSelectResult>) arrSsrs.clone();
		//得到依靠点击的条件
		String salary = URLDecoder.decode(request.getParameter("salary"),"utf-8");
		String nature = URLDecoder.decode(request.getParameter("nature"),"utf-8");
		String diploma = URLDecoder.decode(request.getParameter("diploma"),"utf-8");
		String scale = URLDecoder.decode(request.getParameter("scale"),"utf-8");
		System.out.println(salary + " " + nature + " " + diploma + " " + scale);
		//如果点击条件搜索
		if(salary != null && nature != null && diploma !=null && scale != null){
			//只有列表中存在数据才执行筛选操作
			if(arrSsr.size() > 0){
				//创建数据库操作对象
				EPDataDao epdd = new EPDataDao();
				EPPostJobDao eppjd = new EPPostJobDao();
				//遍历usernames,找出符合条件的username
				int len=arrSsr.size();
				System.out.println(len);
				for(int j=0;j<len;j++){
					try {
						if((!"不限".equals(salary) && !arrSsr.get(j).getSalary().equals(salary)) || 
								(!"不限".equals(nature) && !epdd.getEPData(arrSsr.get(j).getEPusername()).getEPnature().equals(nature)) ||
								(!"不限".equals(diploma) && !eppjd.getEPPostJob(arrSsr.get(j).getEPusername(), arrSsr.get(j).getJobname()).getJobdiploma().equals(diploma)) ||
								(!"不限".equals(scale) && !epdd.getEPData(arrSsr.get(j).getEPusername()).getEPscale().equals(scale))){
							arrSsr.remove(j);
							j--;
							len--;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				System.out.println(false);
				request.setAttribute("error", "请先进行搜索框搜索");
				return;
			}
			
			//使用符合条件的username查询出EPData和EPPostJob
			//将ArrayList epds转化为json
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			JSONObject member = null;
			System.out.println(arrSsr.size());
			if(arrSsr != null){
				
				for(int i=0;i<arrSsr.size();i++){
					member = new JSONObject();
					member.put("epUsername", arrSsr.get(i).getEPusername());
					member.put("epName", arrSsr.get(i).getEPname());
					member.put("epAddr", arrSsr.get(i).getJobaddr());
					member.put("jobName", arrSsr.get(i).getJobname());
					member.put("salary", arrSsr.get(i).getSalary());
					array.add(member);
				}
					
				json.put("result", array);
				PrintWriter pw = response.getWriter();
				pw.print(json.toString());
			}else{
				System.out.println(false);
				request.setAttribute("error", "未找到结果");
			}
		}
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
