
package com.tcs.sadarem.reports.actions;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.common.CommonDAO;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;

import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.DailyDisabilityAndPercentageDAO;
import org.bf.disability.dao.ReportDAO;
import org.bf.disability.dto.ReportDTO;
import org.bf.disability.form.DashboardReportsForm;
import org.bf.disability.service.CampDailyReportService;
import org.bf.disability.service.DashboardReportsService;
import org.bf.disability.servicefactory.CampDailyReportServiceFactory;
import org.bf.disability.servicefactory.DashboardReportsServiceFactory;

import jxl.Workbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.bf.disability.dao.TerritoryDAO;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.openreports.DAO.UpdateCaptureDeadDetailsDAO;
import com.tcs.sadarem.openreports.DAO.UpdateCaptureDeadDetailsImpl;
import com.tcs.sadarem.reports.DAO.CommonReportsDAO;
import com.tcs.sadarem.reports.DAO.DirectorReportOutsideDAO;
import com.tcs.sadarem.reports.DAO.TotalAssessmentReportDAO;
import com.tcs.sadarem.reports.DAOImpl.CommonReportsDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.DirectorReportOutsideDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.TotalAssessmentReportDAOImpl;
import com.tcs.sadarem.util.CommonUtility;


/**
 *
 * @author Administrator
 */
public class TotalAssessmentReportAction extends DispatchAction {

    private String target = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException 
            { 
    	String action=CommonUtility.checkNullObj(request.getParameter("actionval"));
    	//System.out.println("vinnu  -"+CommonUtility.checkNullObj(request.getParameter("action")));
    	if(action.equals("getDistrictWise"))
    		{
    		//getDistrictWise(mapping,form,request,response);
    		}
    		 try
    	        {
    	        
    	    	   String msg="";
    	    	   String fromdate=CommonUtility.checkNullObj(request.getParameter("fromdate1"));
    		       String todate=CommonUtility.checkNullObj(request.getParameter("todate1"));
    		       
    		       Date date = new Date();
    	           SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	           String currentDate = dateFormat.format(date);
    	           
    	           
    		       if(!(fromdate.length()>0))
    		       {
    		    	   fromdate=CommonUtility.getDateAddOrSubDays(-365,"dd/MM/yyyy");
    		       }
    		       if(!(todate.length()>0))
    		       {
    		    	   todate= CommonUtility.getDateTime("dd/MM/yyyy") ;
    		       }

    		       System.out.println("fromdate2 "+fromdate+"todate "+todate);
    		       
    		      ArrayList resultset=new ArrayList();
    		      TotalAssessmentReportDAO comObj1 = new TotalAssessmentReportDAOImpl();
    		      
    		     resultset=comObj1.getabstractassesseddata(fromdate,todate);
    		    

    		  	
    		  	 request.setAttribute("resultList",resultset);
    		  	 request.setAttribute("fromdate3",fromdate);
    		  	request.setAttribute("todate3",todate);
    		 
    	
    		  	 
    	        } catch (Exception e)
    	        {
    	            e.printStackTrace();
    	        }
    		
    		
    	
     
    	return mapping.findForward(target);
            }
    

	      	
    public ActionForward getDistrictWise(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)
            throws SADAREMException, SQLException 
    {
    	
    	String target = "success";
	       
        try
        {
        	ArrayList districtList = new ArrayList(); 
        	ArrayList mandalList = new ArrayList(); 
        	ArrayList villageList = new ArrayList(); 
        	
        	CommonReportsDAO obj=new CommonReportsDAOImpl();
        
    	   String msg="";
    	   String fromdate=CommonUtility.checkNullObj(request.getParameter("fromdate1"));
	       String todate=CommonUtility.checkNullObj(request.getParameter("todate1"));
	       
	       Date date = new Date();
           SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
           String currentDate = dateFormat.format(date);
           
           
	       if(!(fromdate.length()>0))
	       {
	    	   fromdate=CommonUtility.getDateAddOrSubDays(-365,"dd/MM/yyyy");
	       }
	       if(!(todate.length()>0))
	       {
	    	   todate= CommonUtility.getDateTime("dd/MM/yyyy") ;
	       }

	       System.out.println("fromdate2 "+fromdate+"todate "+todate);
	       
	      ArrayList resultset=new ArrayList();
	      TotalAssessmentReportDAO comObj1 = new TotalAssessmentReportDAOImpl();
	      
	     resultset=comObj1.getabstractassesseddata(fromdate,todate);
	    

	  	
	  	 request.setAttribute("resultList",resultset);
	  	 request.setAttribute("fromdate3",fromdate);
	  	request.setAttribute("todate3",todate);
	 
	  //	String districtheadingmsg= comObj1.getDistrictWiseHeading(fromdate,todate,distname,mandalname,villagename,district1,mandal,village,areatype1);
	  	
	
	  	 
        } catch (Exception e)
        {
            e.printStackTrace();
        }
	       return mapping.findForward(target);
	      	
    }
    public ActionForward excelNew(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)
	   {

	        try {
	      	CommonReportsDAO obj=new CommonReportsDAOImpl();
	           String fromdate1=CommonUtility.checkNullObj(request.getParameter("fromdate1"));
	         
	  	       String todate=CommonUtility.checkNullObj(request.getParameter("todate"));
	  	   
	  	       
	  	     ArrayList resultset=new ArrayList();
		      TotalAssessmentReportDAO comObj1 = new TotalAssessmentReportDAOImpl();
		      
		     resultset=comObj1.getabstractassesseddata(fromdate1,todate);
	  	 
	  	     
	  	    exportExcel(resultset,request, response,fromdate1, todate);
	  		
			    
	  		
	  		  
					
				
		
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
 public static void exportExcel(ArrayList resultList,HttpServletRequest request, HttpServletResponse response,
         String fromdate,String todate)
 {
     		OutputStream out = null;

     try {
    	 String  currentdate = CommonUtility.getDateTime("dd/MM/yyyy_hh_mm_ss"); 
    		//System.out.println("enter excel");
         response.setContentType("application/vnd.ms-excel");
         response.setHeader("Content-Disposition", "attachment;filename=R_1.0_TotalAssessmentReport_"+currentdate+".xls");
         WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
         WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
         String  currentdate1 = CommonUtility.getDateTime("dd/MM/yyyy hh:mm:ss"); 
         WritableCellFormat cell = new WritableCellFormat();
         WritableCellFormat cellLeft = new WritableCellFormat();
         cell.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell.setBorder(Border.ALL, BorderLineStyle.THIN);
         cellLeft.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell.setAlignment(Alignment.CENTRE);
         cellLeft.setAlignment(Alignment.LEFT);
         cell.setVerticalAlignment(VerticalAlignment.CENTRE);

         WritableCellFormat cell111 = new WritableCellFormat(bold);
         cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell111.setAlignment(Alignment.CENTRE);
         cell111.setBackground(Colour.GRAY_25);

         cell111.setVerticalAlignment(VerticalAlignment.CENTRE);
         
         WritableCellFormat cell444 = new WritableCellFormat(bold);
         cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell111.setAlignment(Alignment.RIGHT);
         cell111.setBackground(Colour.GRAY_25);

         cell111.setVerticalAlignment(VerticalAlignment.CENTRE);
         
         WritableCellFormat cell222 = new WritableCellFormat(bold);
         cell222.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell222.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell222.setAlignment(Alignment.RIGHT);
     

         cell222.setVerticalAlignment(VerticalAlignment.CENTRE);
         
         
         WritableCellFormat cell12 = new WritableCellFormat(bold);
         cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell12.setAlignment(Alignment.CENTRE);
         cell12.setVerticalAlignment(VerticalAlignment.CENTRE);
         
         
         

         WritableCellFormat cell13 = new WritableCellFormat(bold);
         cell13.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell13.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell13.setAlignment(Alignment.LEFT);
         cell13.setBackground(Colour.GRAY_25);
         cellLeft.setAlignment(Alignment.LEFT);
         
         WritableCellFormat integerstyle = new WritableCellFormat(NumberFormats.INTEGER);
        //Number num1=new Number(2,1,2.121212,integerstyle);
         integerstyle.setAlignment(Alignment.RIGHT);
         integerstyle.setBorder(Border.ALL, BorderLineStyle.THIN);
         integerstyle.setBorder(Border.ALL, BorderLineStyle.THIN);
         int page = 1;
         WritableSheet s = w.createSheet("Sheet" + page, page - 1);

       
        
    

         if (resultList.size() == 0) 
         {
             s.addCell(new Label(0, 0, "SADAREM Total Assessed report................"));
         } 
       else 
         {
     	  
     	  s.mergeCells(0, 0, 2, 0);
             s.addCell(new Label(0, 0, "Generated Date:"+currentdate1+"", cell111));
     
          
            
             
            	  s.mergeCells(3, 0, 4, 0);
                  s.addCell(new Label(3, 0, " "+"SADAREM R.1.0", cell13)); 
             
         }
         
       
      
        	 
         
        	 s.mergeCells(0, 1, 4, 1);
             s.addCell(new Label(0, 1,"",cell111)); 
         
             
  


	   s.mergeCells(0, 2, 0, 3);
       s.addCell(new Label(0, 2, "S.No", cell111));
       setWidth(s, 0, 10);
       

				         	     s.mergeCells(1, 2, 1, 3);
				                 s.addCell(new Label(1, 2, "District", cell111));
				                 setWidth(s, 1, 30);
				           

   
          s.mergeCells(2, 2, 2, 3);
          s.addCell(new Label(2, 2, "Total \n Assessed", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(3, 2, 3, 3);
          s.addCell(new Label(3, 2, "Eligible", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(4, 2, 4, 3);
          s.addCell(new Label(4, 2, "Not  Eligible", cell111));
          setWidth(s, 1, 30);
          
         
       
   
       
       s.addCell(new Label(0, 4, "1", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(1, 4, "2", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(2, 4, "3", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(3, 4, "4", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(4, 4, "5", cell111));
       setWidth(s, 4, 30); 


         int x = 5;
         int k = 0;
        
         ArrayList templist = new ArrayList(); 
         for (int i = 0; i < resultList.size(); i++, x++) 
         {
         	 int j = 0;
             k++;
             templist = (ArrayList)resultList.get(i);
             s.addCell(new Label(j++, x, i + 1 + "", cell));
    
             
             if(templist.get(1).toString().equalsIgnoreCase("ZZZ_TOTAL"))
             {
             s.addCell(new Label(j++, x,"TOTAL", cell111));
             }
             else
             {
             	  s.addCell(new Label(j++, x,templist.get(1).toString(), cellLeft));
             }
             
             
     
            // s.addCell(new Label(j++, x,templist.get(1).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(2).toString(), cell222));
             s.addCell(new Label(j++, x,templist.get(3).toString(), cell222));
             s.addCell(new Label(j++, x,templist.get(4).toString(), cell222));
         
            
         

        
             if (i == (100000) * page)
             {
                 int sheet = ++page;
                 s = w.createSheet("Sheet" + sheet, sheet - 1);
                 j = 0;
                 x = 2;
             }
         }
         


         
         w.write();
         w.close();

     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         if (out != null) {
             try {
                 out.close();
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
         }
     }
 }


public static void setWidth(WritableSheet s, int col, int widthInChars) {

s.setColumnView(col, widthInChars);
}        	
	      	
		       
    
}

