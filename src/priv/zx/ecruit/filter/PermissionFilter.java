package priv.zx.ecruit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PermissionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
 
    }
 
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //���Ƚ������е�ServletRequest��ServletResponseǿתΪHttp...
        HttpServletRequest hreq= (HttpServletRequest) req;
        HttpServletResponse hresp= (HttpServletResponse) resp;
 
        //��ȡ�����е�Servletpath
        String servletpath=hreq.getServletPath();
        //��ȡsession����
        HttpSession hsess=hreq.getSession();
        //��ȡsession������flag��ֵ��ǿתΪString����
        String flag= (String) hsess.getAttribute("flag");
 
        //����û���¼������ҳ������login������ִ�е�¼�����Ļ�
        // ������ֱ��ת������һ��������д���
        //�������������򶼽���Ȩ��У��
        if (servletpath!=null&&(
                (servletpath.equals("/servlet/stuLoginServlet"))||
                (servletpath.equals("/servlet/EPLoginServlet"))||
                (servletpath.equals("/servlet/AdminLoginServlet"))||
                (servletpath.equals("/servlet/MainPageServlet_new"))||
                (servletpath.equals("/servlet/RegisterServlet"))||
                (servletpath.equals("/servlet/EPRegisterServlet"))||
                (servletpath.equals("/servlet/RegisterServlet")) ||
                (servletpath.equals("/servlet/BrowseLoginServlet"))
                )){
 
            chain.doFilter(req, resp);
        }else{
            //�û����ڵ�¼״̬�����ֱ�ӽ��з���
            if (flag!=null&&flag.equals("login_success")){
                chain.doFilter(req, resp);
              
                return;
                //��¼ʧ��,����ת����¼����
            }else {
                chain.doFilter(req, resp);
                //RequestDispatcher rd=hreq.getRequestDispatcher("/index.jsp");
                //rd.forward(hreq,hresp);
            }
        }
    }
 
    public void destroy() {
    }
 
}
