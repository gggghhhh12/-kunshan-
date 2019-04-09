package priv.zx.ecruit.controller;

/**
 * ����ҵ����ӽ���˾�ղ�servlet
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

import priv.zx.ecruit.dao.EPStoreDao;
import priv.zx.ecruit.model.EPStore;

public class EPStoreServlet extends HttpServlet {

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
		//�õ�json�е�stuUsername
		String stuUsername = request.getParameter("stuUsername");
//		System.out.println(stuUsername);
		//���session�е�EPUser
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		//����EPStore����
		EPStore eps = new EPStore();
		eps.setEpUsername(epUsername);
		eps.setStuUsername(stuUsername);
		//�ж��Ƿ��Ѿ��ղع�
		//��callbackת��Ϊjson������ǰ��
		EPStoreDao epsd = new EPStoreDao();
		JSONObject json = new JSONObject();
		try {
			if(epsd.isExist(eps)){
				json.put("callback", "���Ѿ��ղع�����");
			}else{
				epsd.addEPStore(eps);
				json.put("callback", "�ɹ��ղش���");
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
