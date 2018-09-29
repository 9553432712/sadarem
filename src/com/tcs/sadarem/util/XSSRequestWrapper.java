package com.tcs.sadarem.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper 
{

    public XSSRequestWrapper(HttpServletRequest servletRequest) 
    {
        super(servletRequest);
    }

    public String[] getParameterValues(String parameter) 
    {
        String[] values = super.getParameterValues(parameter);

        if (values == null) 
        {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) 
        {
            encodedValues[i] = stripXSS(values[i]);
        }
        return encodedValues;
    }


    public String getParameter(String parameter) 
    {
        String value = super.getParameter(parameter);
   // System.out.println("getParameter value : "+value + "\n stripXSS(value) : "+stripXSS(value));
        return stripXSS(value);
    }

    public String getHeader(String name) 
    {
        String value = super.getHeader(name);
        return stripXSS(value);
    }

    private String stripXSS(String value) 
    {
        if (value != null) 
        {
        	value=  CommonUtility.stripXSS(value);            
        }
      //  System.out.println("Values : "+value);
        return value;
    }
}