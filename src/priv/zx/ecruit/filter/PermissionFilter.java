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
        //首先将参数中的ServletRequest和ServletResponse强转为Http...
        HttpServletRequest hreq= (HttpServletRequest) req;
        HttpServletResponse hresp= (HttpServletResponse) resp;
 
        //获取请求中的Servletpath
        String servletpath=hreq.getServletPath();
        //获取session对象
        HttpSession hsess=hreq.getSession();
        //获取session对象中flag的值，强转为String类型
        String flag= (String) hsess.getAttribute("flag");
 
        //如果用户登录的是首页或者是login或者是执行登录操作的话
        // 将请求直接转发给下一个组件进行处理
        //对于其他请求则都进行权限校验
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
            //用户处于登录状态则可以直接进行访问
            if (flag!=null&&flag.equals("login_success")){
                chain.doFilter(req, resp);
              
                return;
                //登录失败,则跳转到登录界面
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
