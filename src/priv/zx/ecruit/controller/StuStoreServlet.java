package priv.zx.ecruit.controller;

/**
 * ��ҵ���ղع�˾servlet
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

import priv.zx.ecruit.dao.StuStoreDao;
import priv.zx.ecruit.model.StuStore;

public class StuStoreServlet extends HttpServlet {

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

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		//��ȡjson�е�epUsername
		String epUsername = request.getParameter("epUsername");
		String jobname=request.getParameter("jobname");
		//��session�л�ȡ��ҵ���û���
		HttpSession session = request.getSession();
		String stuUsername = (String) session.getAttribute("stuUser");
		//����StuStore����
		StuStore ss = new StuStore();
		ss.setStuUsername(stuUsername);
		ss.setEpUsername(epUsername);
		ss.setJobname(jobname);
		//����StuStoreDao���ݿ��������
		StuStoreDao ssd = new StuStoreDao();
		try {
			if(ssd.isExist(ss)){
				//����json����
				JSONObject json = new JSONObject();
				json.put("info", "���Ѿ��ղع��˹�˾");
				PrintWriter pw = response.getWriter();
				pw.print(json.toString());
			}else{
				ssd.addStuStore(ss);
				//����json����
				JSONObject json = new JSONObject();
				json.put("info", "�ɹ��ղش˹�˾");
				PrintWriter pw = response.getWriter();
				pw.print(json.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
