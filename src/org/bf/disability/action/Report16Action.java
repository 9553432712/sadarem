/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.Report16DAO;
import org.bf.disability.form.Report16Form;

/**
 *
 * @author 567999
 */
public class Report16Action extends DispatchAction 
{

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    {
        Report16Form formBean = (Report16Form) form;
        Report16DAO dao = new Report16DAO();
        ArrayList report16 = null;
        DataSource ds = null;
        try 
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) 
            {
                ds = JNDIDataSource.getConnection();
            }
            
            Calendar c = Calendar.getInstance();
            int dayOfWeek = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH)+1;
            int year = c.get(Calendar.YEAR);
            String day = null;
            String mnth = null;
            if(Integer.toString(dayOfWeek).length()==1)
            {
                day = "0"+Integer.toString(dayOfWeek);
            }
            else
            {
                day = Integer.toString(dayOfWeek);
            }
            if(Integer.toString(month).length()==1)
            {
                mnth = "0"+Integer.toString(month);
            }
            else
            {
                mnth = Integer.toString(month);
            }
            
            String date = day+"/"+mnth+"/"+year;

            formBean.setFromdate("01/01/2010");
            formBean.setTodate(date);
            report16 = dao.getReport16(ds, formBean.getFromdate(), formBean.getTodate());
            if (report16.size() > 0)
            {
                request.setAttribute("report", report16);
            } 
            else
            {
                request.setAttribute("nodata", "Data Not Available");
            }
            
            request.setAttribute("fdate", formBean.getFromdate());
            request.setAttribute("tdate", formBean.getTodate());

        } catch (Exception e) 
        {
            e.printStackTrace();
        }

        return mapping.findForward("success");
    }

    public ActionForward getReport16(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
        Report16Form formBean = (Report16Form) form;
        Report16DAO dao = new Report16DAO();
        ArrayList report16 = null;
        DataSource ds = null;
        try 
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds))
            {
                ds = JNDIDataSource.getConnection();
            }
            report16 = dao.getReport16(ds, formBean.getFromdate(), formBean.getTodate());
            if (report16.size() > 0)
            {
                request.setAttribute("report", report16);
            } 
            else 
            {
                request.setAttribute("nodata", "Data Not Available");
            }
            request.setAttribute("date", "date");
            request.setAttribute("fdate", formBean.getFromdate());
            request.setAttribute("tdate", formBean.getTodate());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }


     public ActionForward getReport16Excel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
     {
        Report16Form formBean = (Report16Form) form;
        Report16DAO dao = new Report16DAO();
        ArrayList report16 = null;
        DataSource ds = null;
        try 
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) 
            {
                ds = JNDIDataSource.getConnection();
            }

            report16 = dao.getReport16(ds, request.getParameter("fdate"), request.getParameter("tdate"));
            if (report16.size() > 0) 
            {
                request.setAttribute("report", report16);
            }
            else
            {
                request.setAttribute("nodata", "Data Not Available");
            }
            request.setAttribute("fdate", request.getParameter("fdate"));
            request.setAttribute("tdate", request.getParameter("tdate"));

        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return mapping.findForward("pwdValAbstReportExcel");
    }
}

