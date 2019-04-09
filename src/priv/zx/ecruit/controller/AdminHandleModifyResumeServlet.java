package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.Education;
import priv.zx.ecruit.model.JobIntention;

/**
 * Servlet implementation class AdminHandleModifyResumeServlet
 */
@WebServlet("/AdminHandleModifyResumeServlet")
public class AdminHandleModifyResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHandleModifyResumeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//�õ��޸ĺ�ĸ������������Ϣ
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String nation = request.getParameter("nation");
		String biryear = request.getParameter("biryear");
		String birmonth = request.getParameter("birmonth");
		String birday = request.getParameter("birday");
		String birthday = biryear + "-" +birmonth + "-" + birday;
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String liveaddr = request.getParameter("liveaddr");
		String residence = request.getParameter("residence");
		//�õ��޸ĺ�ĸ������������Ϣ
		String eduyear = request.getParameter("eduyear");
		String edumonth = request.getParameter("edumonth");
		String eduyear1 = request.getParameter("eduyear1");
		String edumonth1 = request.getParameter("edumonth1");
		String eduschool = request.getParameter("eduschool");
		String edumajor = request.getParameter("edumajor");
		String edudiploma = request.getParameter("edudiploma");
		String englevel = request.getParameter("englevel");
		String eduduty = request.getParameter("eduduty");
		String eduaward = request.getParameter("eduaward");
		String eduprictise = request.getParameter("eduprictise");
		String abroad = request.getParameter("abroad");
		String enterTime = eduyear + "-" + edumonth;
		String gradTime = eduyear1 + "-" + edumonth1;
		//�õ��޸ĺ�ĸ��������ְ������Ϣ
		String keyword = request.getParameter("keyword");
		String evaluation = request.getParameter("evaluation");
		String place = request.getParameter("place");
		String trade = request.getParameter("trade");
		String salary = request.getParameter("salary");
		//��session�л��username

		String username = request.getParameter("username");
		//����BasicInfo����,�������Ը����ö���
		BasicInfo bi = new BasicInfo();
		bi.setUsername(username);
		bi.setName(name);
		bi.setSex(sex);
		bi.setNation(nation);
		//���ַ���������תΪDate��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bi.setBirthday(sdf.parse(birthday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bi.setTel(tel);
		bi.setEmail(email);
		bi.setLiveaddr(liveaddr);
		bi.setResidence(residence);
		//����Education���󣬽������Ը����ö���
		Education edu = new Education();
		sdf = new SimpleDateFormat("yyyy-MM");
		edu.setUsername(username);
		try {
			edu.setEnterTime(sdf.parse(enterTime));
			edu.setGradTime(sdf.parse(gradTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		edu.setEduschool(eduschool);
		edu.setEdumajor(edumajor);
		edu.setEdudiploma(edudiploma);
		edu.setEnglevel(englevel);
		edu.setEduduty(eduduty);
		edu.setEduaward(eduaward);
		edu.setEduprictise(eduprictise);
		edu.setAbroad(abroad);
		//����JobIntention���󣬽������Ը����ö���
		JobIntention ji= new JobIntention();
		ji.setUsername(username);
		ji.setKeyword(keyword);
		ji.setEvaluation(evaluation);
		ji.setPlace(place);
		ji.setTrade(trade);
		ji.setSalary(salary);
		ji.setDate(new Date());
		//����Dao����
		BasicInfoDao bid = new BasicInfoDao();
		EducationDao ed = new EducationDao();
		JobIntentionDao jid = new JobIntentionDao();
		//���±�
		try {
			bid.updateBasicInfo(bi);//���±�tb_basicinfo
			ed.updateEducation(edu);//���±�tb_education
			jid.updateJobIntention(ji);//���±�tb_jobintention
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ת��stuHome.jspҳ��
		request.getRequestDispatcher("AdminShowResumeServlet").forward(request, response);
	}

}
