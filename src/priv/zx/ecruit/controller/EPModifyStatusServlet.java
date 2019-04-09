package priv.zx.ecruit.controller;
/*
 * �޸�ְλ����Ȩ�޵�servlet
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import priv.zx.ecruit.dao.EPPostJobDao;

public class EPModifyStatusServlet extends HttpServlet {

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
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//��ȡjson�д�����status
		int status = Integer.parseInt(request.getParameter("status"));
//		String jobname=new String(request.getParameter("jobname").getBytes("iso8859-1"), "utf-8");
		String jobname=URLDecoder.decode(request.getParameter("jobname"),"utf-8");
		System.out.println(jobname);
		System.out.println(status);
		//��ȡsession�е�EPUser
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		//�޸�ְλ����Ȩ��
		EPPostJobDao eppjd = new EPPostJobDao();
		if(status == 0){
			try {
				eppjd.modifyJobAuthority(epUsername, 1,jobname);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JSONObject json = new JSONObject();
			json.put("callback", "��ְλ�ѳɹ��ϼ�");
			PrintWriter pw = response.getWriter();
			pw.println(json.toString());
		}else{
			try {
				eppjd.modifyJobAuthority(epUsername, 0,jobname);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JSONObject json = new JSONObject();
			json.put("callback", "��ְλ�ѳɹ��¼�");
			PrintWriter pw = response.getWriter();
			pw.println(json.toString());
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
