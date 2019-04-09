package priv.zx.ecruit.controller;

/**
 * 个人简历基本信息servlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.model.BasicInfo;

public class BasicinfoServlet extends HttpServlet {

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

		doPost(request,response);
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
		request.setCharacterEncoding("utf-8");
		//得到表单中的基本信息
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String nation = request.getParameter("nation");
//		String biryear = request.getParameter("biryear");
//		String birmonth = request.getParameter("birmonth");
		String birthday = request.getParameter("birthday");
		//String birthday = /*biryear + "-" +birmonth + "-" +*/ birday;
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String liveaddr = request.getParameter("liveaddr");
		String residence = request.getParameter("residence");

		//从session中得到登录名
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("stuUser");
		//将各信息加入到BasicInfo对象中
		BasicInfo bi = new BasicInfo();
		bi.setUsername(username);
		bi.setName(name);
		bi.setSex(sex);
		bi.setNation(nation);
		//将字符串型生日转为Date型
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bi.setBirthday(sdf.parse(birthday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bi.setTel(tel);
		bi.setEmail(email);
		bi.setLiveaddr(liveaddr);
		bi.setResidence(residence);
		//将bi对象添加到数据库中
		
		try {
			BasicInfoDao bid = new BasicInfoDao();
			//System.out.println(bid.getBasicInfo(username));
			if(bid.getBasicInfo(username)!=null)
			{
				bid.updateBasicInfo(bi);
		    }
			else{
				bid.addBasicInfo(bi);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("../education.jsp").forward(request, response);
	}

}
