package priv.zx.ecruit.controller;

/**
 * �޸ļ����Ĺ���Ȩ�޵�servlet
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import priv.zx.ecruit.dao.JobIntentionDao;

public class StuModifyStatusServlet extends HttpServlet {

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
		//��ȡjson�������ļ�����ǰȨ��
		int status = Integer.parseInt(request.getParameter("status"));
//		System.out.println(status);
		//��ȡsession�е�stuUser
		HttpSession session = request.getSession();
		String stuUsername = (String) session.getAttribute("stuUser");
		//����JobintentionDao����
		JobIntentionDao jid = new JobIntentionDao();
		if(status == 0){
			try {
				jid.modifyAuthority(stuUsername, 1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JSONObject json = new JSONObject();
			json.put("callback", "����Ȩ���Ѿ���Ϊ����");
			PrintWriter pw = response.getWriter();
			pw.println(json.toString());
		}else{
			try {
				jid.modifyAuthority(stuUsername, 0);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JSONObject json = new JSONObject();
			json.put("callback", "����Ȩ���Ѿ���Ϊ������");
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
