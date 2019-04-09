package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.model.EPData;

public class EPHandleModifyData extends HttpServlet {

	/**
	 *  企业修改信息 
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
		//取得更改后表单中的公司资料信息
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
//		System.out.println(EPname + " " + EPnature + " " + EPcode + " " + EPtrade + " " +
//					EPscale + " " + EPaddr + " " + EPcontact + " " + EPemail + " " +
//					EPtel + " " + EPmobile + " " + EPpostalcode);
		//从session中取得公司用户名
		HttpSession session = request.getSession();
		String EPusername = (String) session.getAttribute("EPUser");
		//创建公司资料对象
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
		//将epd对象添加进数据库中
		try {
			EPDataDao epdd = new EPDataDao();
			epdd.updateEPData(epd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//转向EPHome.jsp页面
		request.getRequestDispatcher("EPHomeServlet").forward(request, response);
	}

}
