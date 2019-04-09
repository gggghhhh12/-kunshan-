package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
//管理员修改企业信息
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.model.EPData;
public class adminModifyEPDataServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1L;

		
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			//创建EPData公司资料对象
			EPData epd = new EPData();
			//创建EPDataDao公司资料数据库对象
			EPDataDao epdd = new EPDataDao();
			//从session中获取企业用户名
			
			String EPusername = (String) request.getParameter("epusername");
		
			System.out.println(EPusername);
			//从数据库中查找出企业资料信息
			try {
				epd = epdd.getEPData(EPusername);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//将企业资料对象epd放入request中
			request.setAttribute("EPData", epd);
			
			//转向EPModifyData.jsp页面
			request.getRequestDispatcher("../adminModifyEPData.jsp").forward(request, response);
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
