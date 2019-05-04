package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.dao.NewsDao;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.News;

public class MainPageServlet_new extends HttpServlet {

    /**
     *  ��վ��ҳ
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
        System.out.println("success");
        request.getSession().invalidate();
        //��վչʾ�������б�
        ArrayList<News> arrNews = new ArrayList<News>();

        NewsDao nd = new NewsDao();
        //�����ݿ��в�ѯ�����б�
        try {
            arrNews = nd.queryNews();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("arrNews", arrNews);
        
        ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
        //����EPPostJobDaoְλҪ�����ݿ��������
        EPPostJobDao eppjd = new EPPostJobDao();
        try {
            System.out.println("shabi");
            eppjs=eppjd.getLatestEPPostJob();
            for(EPPostJob i:eppjs) {
                System.out.println(i.getEPname());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        //��ְλҪ��������request��
        request.setAttribute("EPPostJobs", eppjs);
        request.getRequestDispatcher("../mainPage_new.jsp").forward(request, response);
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
