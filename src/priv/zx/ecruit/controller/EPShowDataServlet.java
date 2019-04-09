package priv.zx.ecruit.controller;

/**
 * �鿴��˾����servlet
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

		//����EPData��˾���϶���
		EPData epd = new EPData();
		//����EPDataDao��˾�������ݿ����
		EPDataDao epdd = new EPDataDao();
		//��session�л�ȡ��ҵ�û���
		HttpSession session = request.getSession();
		String EPusername = (String) session.getAttribute("EPUser");
		//�����ݿ��в��ҳ���ҵ������Ϣ
		try {
			epd = epdd.getEPData(EPusername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//����ҵ���϶���epd����request��
		request.setAttribute("EPData", epd);
		//��EPintroduction������и�ʽ���ַ�����������ʾ
		
		if(epd != null){
			//�õ�ԭ���ַ���
			String introduction = epd.getEPintroduction();
			//��ԭ���ַ���������ֿ�
			String[] introParas = introduction.split("\r\n");
			//��ÿ�����ַ���Ӹ�ʽ,����sbIntroduction��
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
			//����Ӹ�ʽ����ַ���sbIntroduction����request��
			request.setAttribute("introduction", sbIntroduction);
		}
		//ת��EPShowData.jspҳ��
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
