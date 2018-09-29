package com.tcs.sadarem.util;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class LoggerServlet extends HttpServlet 
{
	static final Logger logger = Logger.getLogger(LoggerServlet.class);
	@Override	
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);				
		String filePath = getInitParameter("loggerFile");
		String path=getServletContext().getRealPath(filePath);
		PropertyConfigurator.configure(path);
		logger.info("init : The Logger File Configuration successfully completed.");
	}
	
	private static final long serialVersionUID = 1L;
    
	public LoggerServlet() 
	{
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		super.doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doPost(request, response);
	}
}
