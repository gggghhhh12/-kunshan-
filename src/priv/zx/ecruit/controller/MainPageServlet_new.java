package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.dao.NewsDao;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.News;

public class MainPageServlet_new extends HttpServlet {

    /**
     *  网站首页
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
    	request.setCharacterEncoding("utf-8");
        System.out.println("success");
        request.getSession().invalidate();
       ArrayList<EPPostJob> newjob=new ArrayList<EPPostJob>();
       ArrayList<EPPostJob> hotjob=new ArrayList<EPPostJob>();
       ArrayList<EPPostJob> highSalary=new ArrayList<EPPostJob>();
       ArrayList<EPData> vipEP=new ArrayList<EPData>();
        //创建EPPostJobDao职位要求数据库操作对象
        EPPostJobDao eppjd = new EPPostJobDao();
        EPDataDao eppv=new EPDataDao();
        try {
            //System.out.println("shabi");

          
            
            newjob=eppjd.getLatestEPPostJob();
            hotjob=eppjd.getHotEPPostJob();
            highSalary=eppjd.getHighSalaryJob();
            vipEP=eppv.getVipEPData();
            
           
                
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        //将职位要求对象放入request中
        request.setAttribute("newJobs", newjob);
        request.setAttribute("hotJobs", hotjob);
        request.setAttribute("highSalarys", highSalary);
        request.setAttribute("vipEps", vipEP);
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
