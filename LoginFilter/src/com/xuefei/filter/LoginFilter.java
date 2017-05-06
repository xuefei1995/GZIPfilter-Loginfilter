package com.xuefei.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest myrequest=(HttpServletRequest)request;
		HttpServletResponse myreponse=(HttpServletResponse)response;
		HttpSession session = myrequest.getSession();
		if(session==null){
			myreponse.sendRedirect(myrequest.getContextPath()+"/login.jsp");
			return;
		}else {
			String name =(String)session.getAttribute("user");
			if(name==null || "".equals(name)){
				myreponse.sendRedirect(myrequest.getContextPath()+"/login.jsp");
				return;
			}
		}
		chain.doFilter(myrequest, myreponse);
	}
}
