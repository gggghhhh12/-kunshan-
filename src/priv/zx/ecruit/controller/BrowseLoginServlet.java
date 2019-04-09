package priv.zx.ecruit.controller;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import priv.zx.ecruit.dao.BrowseDao;
public class BrowseLoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		//System.out.print("   66666");
		//��ȡ���е��û���������
		String browseUsername = request.getParameter("browsename");
		String browsePassword = request.getParameter("browsepassword");
		//System.out.print(browseUsername+"   66666");
		//��ȡ���ݿ��е�����
		BrowseDao aud1 = new BrowseDao();
		String DbPassword = "";
		try {
			DbPassword = aud1.getPassword(browseUsername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(DbPassword.equals(browsePassword)){
			//���û�������session
			HttpSession session = request.getSession();
			session.setAttribute("browseUsername",browseUsername);
			session.setAttribute("flag", "login_success");
			request.getRequestDispatcher("../browseHome.jsp").forward(request, response);
		}else{
			request.setAttribute("error", "��¼ʧ��,�����û���������");
			request.getRequestDispatcher("../BrowseLogin.jsp").forward(request, response);
		}
	}

}
