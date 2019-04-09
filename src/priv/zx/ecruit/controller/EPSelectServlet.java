package priv.zx.ecruit.controller;

/**
 * ��ҵ�����ѯ����Ҫ��ı�ҵ����Ϣservlet
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.EPSelectResult;
import priv.zx.ecruit.model.Education;

public class EPSelectServlet extends HttpServlet {

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
		//ȡ��json�е�ɸѡ����
		String address = request.getParameter("address");
		String trade = request.getParameter("trade");
		String salary = request.getParameter("salary");
		String diploma = request.getParameter("diploma");
		System.out.println(address + " " + trade + " " + salary + " " + diploma);
		//����JobIntentionDao���ݿ��������
		JobIntentionDao jid = new JobIntentionDao();
		//��ѯ����Ҫ��ı�ҵ���û���
		ArrayList<String> arrStuUsername = new ArrayList<String>();
		try {
			arrStuUsername = jid.queryStuUsername(address, salary, trade, diploma);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//���з��������ı�ҵ��
		if(arrStuUsername.size() > 0){
			ArrayList<EPSelectResult> arrEPsr = new ArrayList<EPSelectResult>();
			EPSelectResult epsr = null;
			//����BasicInfoDao��EducationDao�������ݿ��������
			BasicInfoDao bid = new BasicInfoDao();
			EducationDao ed = new EducationDao();
			//����BasicInfo��Education��������
			BasicInfo bi = new BasicInfo();
			Education e = new Education();
			//��Ҫ��ʾ����Ϣת��ΪEPSelectResult����
			for (String stuUsername : arrStuUsername) {
				epsr = new EPSelectResult();
				epsr.setStuUsername(stuUsername);
				try {
					bi = bid.getBasicInfo(stuUsername);
					e = ed.getEducation(stuUsername);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(bi != null && e != null){
					epsr.setStuName(bi.getName());
					epsr.setSex(bi.getSex());
					epsr.setSchool(e.getEduschool());
					epsr.setMajor(e.getEdumajor());
					//��EPSelectResult������ӽ�arrEPsr�б�
					arrEPsr.add(epsr);
				}
			}
			//��arrEPsrת��Ϊjson
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			JSONObject member = null;
			if(arrStuUsername.size() > 0){
				for (EPSelectResult epsr1 : arrEPsr) {
					member = new JSONObject();
					member.put("stuUsername", epsr1.getStuUsername());
					member.put("stuName", epsr1.getStuName());
					member.put("sex", epsr1.getSex());
					member.put("school", epsr1.getSchool());
					member.put("major", epsr1.getMajor());
					array.add(member);
				}
			}
			json.put("result", array);
			PrintWriter pw = response.getWriter();
			pw.print(json.toString());
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
