package filters;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class loginFilters implements Filter{
	private FilterConfig config;
	
	
	public void init(FilterConfig config) {
		this.config=config;
	}
	
	public void doFilter(ServletRequest request,ServletResponse response,
			FilterChain chain)throws ServletException,IOException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession session=req.getSession();
		
		Object account = session.getAttribute("login");
		if(account == null){
			session.setAttribute("location",req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/sign/signin.jsp");
			return;
		}else{
			chain.doFilter(request, response);
		}
	}
	
	public void destroy() {
		config=null;
	}
}
