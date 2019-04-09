package priv.zx.ecruit.controller;

/**
 * ��˾��д����servlet
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.model.EPData;

public class EPDataServlet extends HttpServlet {

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
		//�ӹ�˾���ϱ��л�ȡ������Ϣ
		String EPname = request.getParameter("EPname");
		String EPnature = request.getParameter("EPnature");
		String EPcode = request.getParameter("EPcode");
		String EPtrade = request.getParameter("EPtrade");
		String EPscale = request.getParameter("EPscale");
		String EPaddr = request.getParameter("EPaddr");
		String EPcontact = request.getParameter("EPcontact");
		String EPemail = request.getParameter("EPemail");
		String EPtel = request.getParameter("EPtel");
		String EPmobile = request.getParameter("EPmobile");
		String EPpostalcode = request.getParameter("EPpostalcode");
		String EPintroduction = request.getParameter("EPintroduction");

		//��session��ȡ�ù�˾�û���
		HttpSession session = request.getSession();
		String EPusername = (String) session.getAttribute("EPUser");
		//������˾���϶���
		EPData epd = new EPData();
		epd.setEPusername(EPusername);
		epd.setEPname(EPname);
		epd.setEPnature(EPnature);
		epd.setEPcode(EPcode);
		epd.setEPtrade(EPtrade);
		epd.setEPscale(EPscale);
		epd.setEPaddr(EPaddr);
		epd.setEPcontact(EPcontact);
		epd.setEPemail(EPemail);
		epd.setEPtel(EPtel);
		epd.setEPmobile(EPmobile);
		epd.setEPpostalcode(EPpostalcode);
		epd.setEPintroduction(EPintroduction);
		//��epd������ӽ����ݿ���
		try {
			EPDataDao epdd = new EPDataDao();
			epdd.addEPDataDao(epd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ת��EPHome.jspҳ��
		request.getRequestDispatcher("EPHomeServlet").forward(request, response);
		
		
	}

}
