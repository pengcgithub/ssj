package com.ssj.manage.modules.filter;

import com.ssj.manage.modules.system.SystemPropertyHandler;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainFilter implements Filter {

	@Override
	public void destroy() {}
 
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request;
		HttpServletResponse response;
		request = (HttpServletRequest)arg0;
		response = (HttpServletResponse)arg1;
		
		String params = request.getQueryString();//请求参数
		String filter = SystemPropertyHandler.getProperty("fbs.nouser.remotePageFilter");//白名单
		if(StringUtils.isNotBlank(params) && params.contains(filter)){
			arg2.doFilter(arg0, arg1);
			return;
		}
	    String url = request.getServletPath(); 
		String strlocal = request.getRequestURL().toString();
		strlocal = strlocal.replace(request.getServletPath(),"" );
		String strLogin = strlocal+"/business/login.html";

		if ((request.getServletPath().equals( "/business/login.html")) && request.getSession().getAttribute("USERID")!=null){ //Cast.toInt(request.getSession().getAttribute("USERID") )>0
			String strMain =strlocal+ "/index.html";
			String script;
			script=String.format("<script>if(window.parent == undefined) window.location.href='%s';else window.parent.location.href='%s';</script>",strMain,strMain);
			response.getWriter().write(script);
			return ;
		}

		if ((request.getServletPath().equals("/business/login.html"))
				&& request.getSession().getAttribute("USERID") == null ) {
			arg2.doFilter(arg0, arg1);
			return;
		} 

		if (request.getServletPath().equals("/keyLogin.jsp")
				|| (request.getServletPath().indexOf("/jseq/")==0)) {
			arg2.doFilter(arg0, arg1);
			return;
		}

		if (!(request.getServletPath().equals("/business/login.html"))
				&& request.getSession().getAttribute("USERID") == null ) {
			String script;
			script = String.format("<script>if(window.parent == undefined) window.location.href='%s';else window.parent.location.href='%s';</script>",strLogin,strLogin);
			response.getWriter().write(script);
			return;
		} 
		
		if(request.getSession().getAttribute("USERID") == null) {
			String script;
			script = String.format("<script>if(window.parent == undefined) window.location.href='%s';else window.parent.location.href='%s';</script>",strLogin,strLogin);
			response.getWriter().write(script);
			return;
		}else {
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	 
}
