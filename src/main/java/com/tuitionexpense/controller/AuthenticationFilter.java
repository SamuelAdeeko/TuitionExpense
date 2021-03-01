package com.tuitionexpense.controller;

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

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/pages/*")
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// place your code here
		// we only want a client to be able to access resources in the pages folder only if they have a session.
		// we need to downcast to HttpServletRequest in order to get a session
//		
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		
//		// Remember that if we call the getSession without initializing it to false, clients will get a session
//		HttpSession session = httpRequest.getSession(false);
//		
//		if(session == null) {			
////			httpResponse.sendRedirect("/TuitionExpense/index.html");
//	//		RequestDispatcher dispatcher =  httpRequest.getRequestDispatcher("./index.html");
//	//		dispatcher.forward(httpRequest, httpResponse);
//		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
