package priv.zx.ecruit.controller;

/**
 * 查看公司资料servlet
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

public class EPShowDataServlet extends HttpServlet {

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

		//创建EPData公司资料对象
		EPData epd = new EPData();
		//创建EPDataDao公司资料数据库对象
		EPDataDao epdd = new EPDataDao();
		//从session中获取企业用户名
		HttpSession session = request.getSession();
		String EPusername = (String) session.getAttribute("EPUser");
		//从数据库中查找出企业资料信息
		try {
			epd = epdd.getEPData(EPusername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//将企业资料对象epd放入request中
		request.setAttribute("EPData", epd);
		//将EPintroduction处理成有格式的字符串，方便显示
		
		if(epd != null){
			//得到原先字符串
			String introduction = epd.getEPintroduction();
			//将原先字符串按段落分开
			String[] introParas = introduction.split("\r\n");
			//将每段落字符添加格式,存入sbIntroduction中
			StringBuilder sbIntroduction = new StringBuilder();
			for(int i = 0;i < introParas.length;i++){
				char[] introductions = introParas[i].toCharArray();
				for(int j = 0;j < introductions.length;j++){
					if(introductions[j] == ' '){
						sbIntroduction.append("&nbsp;".toCharArray());
					}else{
						sbIntroduction.append(introductions[j]);
					}
					if((j+1)%30 == 0){
						sbIntroduction.append("<br>".toCharArray());
					}
				}
				sbIntroduction.append("<br>".toCharArray());
			}
			//将添加格式后的字符串sbIntroduction放入request中
			request.setAttribute("introduction", sbIntroduction);
		}
		//转向EPShowData.jsp页面
		request.getRequestDispatcher("../EPShowData.jsp").forward(request, response);
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
