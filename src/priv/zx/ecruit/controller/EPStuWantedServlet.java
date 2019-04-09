package priv.zx.ecruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import priv.zx.ecruit.dao.StuWantedDao;
import priv.zx.ecruit.model.StuWanted;

public class EPStuWantedServlet extends HttpServlet {

	/**
	 *  ��ҵ����˷�������
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

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//��json�л�ȡstuUsername
		String stuUsername = request.getParameter("stuUsername");
		System.out.println(stuUsername);
		//��Session�л�ȡEPUser
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		//����StuWanted����
		StuWanted sw = new StuWanted();
		sw.setEpUsername(epUsername);
		sw.setStuUsername(stuUsername);
		//�ж��Ƿ񷢳�������,����ǰ�˷���json����
		StuWantedDao swd = new StuWantedDao();
		JSONObject json = new JSONObject();
		try {
			if(swd.isExist(sw)){
				json.put("callback", "���Ѿ���ñ�ҵ������������");
			}else{
				//�����ݿ������������
				swd.addStuWanted(sw);
				json.put("callback", "�ɹ���ñ�ҵ����������");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter pw = response.getWriter();
		pw.print(json.toString());
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
