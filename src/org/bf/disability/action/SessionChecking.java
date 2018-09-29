package org.bf.disability.action;

import java.io.IOException;

import java.util.Vector;

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

/**
 * @author 525485
 */
public class SessionChecking implements Filter {

    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterChain) throws IOException, ServletException {
        String contextPath = ((HttpServletRequest) servletrequest).getServerName() + ((HttpServletRequest) servletrequest).getContextPath();
/*        
        if (contextPath.equals("sadarem.telangana.gov.in/sadarem")
                || contextPath.equals("www.sadarem.telangana.gov.in/sadarem")
                || ((HttpServletRequest) servletrequest).getServerName().toString().equals("localhost")
                  ||  contextPath.equals("10.100.100.6/sadarem")){ */

            filterChain.doFilter(servletrequest, servletresponse);
/*        } else {
            RequestDispatcher rd = ((HttpServletRequest) servletrequest).getRequestDispatcher("unAuthorised.do");
            rd.forward(((HttpServletRequest) servletrequest), ((HttpServletResponse) servletresponse));
        }*/
    }

    public void destroy() {
    }
}
