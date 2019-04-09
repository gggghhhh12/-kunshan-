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
		//获取表单中的用户名和密码
		String browseUsername = request.getParameter("browsename");
		String browsePassword = request.getParameter("browsepassword");
		//System.out.print(browseUsername+"   66666");
		//获取数据库中的密码
		BrowseDao aud1 = new BrowseDao();
		String DbPassword = "";
		try {
			DbPassword = aud1.getPassword(browseUsername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(DbPassword.equals(browsePassword)){
			//将用户名存入session
			HttpSession session = request.getSession();
			session.setAttribute("browseUsername",browseUsername);
			session.setAttribute("flag", "login_success");
			request.getRequestDispatcher("../browseHome.jsp").forward(request, response);
		}else{
			request.setAttribute("error", "登录失败,请检查用户名和密码");
			request.getRequestDispatcher("../BrowseLogin.jsp").forward(request, response);
		}
	}

}
