package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
//����Ա�޸���ҵ��Ϣ
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.model.EPData;
public class adminModifyEPDataServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1L;

		
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			//����EPData��˾���϶���
			EPData epd = new EPData();
			//����EPDataDao��˾�������ݿ����
			EPDataDao epdd = new EPDataDao();
			//��session�л�ȡ��ҵ�û���
			
			String EPusername = (String) request.getParameter("epusername");
		
			System.out.println(EPusername);
			//�����ݿ��в��ҳ���ҵ������Ϣ
			try {
				epd = epdd.getEPData(EPusername);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//����ҵ���϶���epd����request��
			request.setAttribute("EPData", epd);
			
			//ת��EPModifyData.jspҳ��
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
