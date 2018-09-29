package com.tcs.sadarem.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XSSFilter implements Filter 
{
	private String mode = "SAMEORIGIN";
	
    public void init(FilterConfig filterConfig) throws ServletException
    {
    	String configMode = filterConfig.getInitParameter("mode");
    	
		
    	if ( configMode != null ) 
		{
    		
			mode = configMode;
		}
    	
    }

    public void destroy() 
    { 
    	
    }
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
       {
    	

    	HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
    	
    	String sessionid = req.getSession().getId();
    	
    	
    	
    	
    	// be careful overwriting: JSESSIONID may have been set with other flags
    		res.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + "; HttpOnly"); 
				    
				    String url = ((HttpServletRequest)request).getRequestURL().toString();
				    //String queryString = ((HttpServletRequest)request).getQueryString();  
				
				  
				    if(url.contains(".do"))
				    {
				    	//System.out.println(url+"current url!!!!"+url.indexOf("/sadarem/"));
				    	//System.out.println(url+"current url!!!!"+url.indexOf(".do"));
				    	int startingIndex = url.indexOf("/sadarem/");
				    	int lastIndex = url.indexOf(".do");
				    	url = url.substring(startingIndex+9, lastIndex+3);
				    	
				    } 
				    
				    if(MenuUtil.checkLinkAuthority(req.getSession(), url)==false)
				    { 
				    	req.getSession().setAttribute("urlErrorMsg","You are not authorized to access this service.Please contact administrator.");
						res.sendRedirect(req.getContextPath()+"/logout.do"); 
				    }
				    else
				    { 
		          		chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response); 
		          		((HttpServletResponse) response).addHeader("X-FRAME-OPTIONS", mode );	
				    }
       }

}