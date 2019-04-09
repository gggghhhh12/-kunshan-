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

import priv.zx.ecruit.dao.JobWantedDao;
import priv.zx.ecruit.model.JobWanted;

public class StuJobWantedServlet extends HttpServlet {

	/**
	 *  ѧ��������ְ����
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
		//��ǰ�˻�ȡ��Ҫ��ְ�Ĺ�˾�û���
		String epUsername = request.getParameter("epUsername");
		String epJobname=request.getParameter("epJobname");
//		System.out.println(epUsername);
		//��session�л�ȡstuUser
		HttpSession session = request.getSession();
		String stuUsername = (String) session.getAttribute("stuUser");
		//����ְ�Ĺ�˾��ӽ����ݿ�
		JobWantedDao jwd = new JobWantedDao();
		JobWanted jw = new JobWanted();
		jw.setStuUsername(stuUsername);
		jw.setEpUsername(epUsername);
		jw.setEpJobname(epJobname);
		try {
			if(jwd.isExist(jw)){
				JSONObject json = new JSONObject();
				json.put("info", "���Ѿ���˹�˾����������");
				PrintWriter pw = response.getWriter();
				pw.print(json.toString());
			}else{
				jwd.addJobWanted(jw);
				//��callbackת��Ϊjson����ǰ��
				JSONObject json = new JSONObject();
				json.put("info", "�ɹ���˹�˾������ְ����");
				PrintWriter pw = response.getWriter();
				pw.print(json.toString());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
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
