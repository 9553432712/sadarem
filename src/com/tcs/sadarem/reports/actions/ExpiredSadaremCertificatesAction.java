
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
import com.tcs.sadarem.openreports.DAO.ExpiredSadaremCertificatesDAO;
import com.tcs.sadarem.openreports.DAO.UpdateCaptureDeadDetailsDAO;
import com.tcs.sadarem.openreports.DAO.UpdateCaptureDeadDetailsImpl;
import com.tcs.sadarem.reports.DAO.CommonReportsDAO;
import com.tcs.sadarem.reports.DAO.DirectorReportOutsideDAO;
import com.tcs.sadarem.reports.DAO.EducationWiseDAO;
import com.tcs.sadarem.reports.DAO.ExpiredSadaremCertificateDAO;
import com.tcs.sadarem.reports.DAOImpl.CommonReportsDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.DirectorReportOutsideDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.EducationWiseDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.ExpiredSadaremCertificatesDAOImpl;
import com.tcs.sadarem.util.CommonUtility;


/**
 *
 * @author Administrator
 */
public class ExpiredSadaremCertificatesAction extends DispatchAction {

    private String target = "success"; 

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException 
            { 

	    	CommonReportsDAO comObj1 = new CommonReportsDAOImpl();
	    	
	
	      	ArrayList districtList = new ArrayList(); 
	    	ArrayList educationList = new ArrayList();
	    	
	      	districtList = comObj1.getDistrictList();		 	
	      	educationList = (ArrayList)comObj1.getEducationList();
	  
	      	 request.setAttribute("districtList", districtList);
	  
    	
    	
    	 
    	return mapping.findForward(target);
            }
    
   
	      	
    public ActionForward getData(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)
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
	       String district=CommonUtility.checkNullObj(request.getParameter("district1"));
	       String mandal=CommonUtility.checkNullObj(request.getParameter("mandal"));
	       String TRIGPSel = CommonUtility.checkNullObj(request.getParameter("TRIGPSel"));
	       String areatype=CommonUtility.checkNullObj(request.getParameter("areatype1"));
	       
	      ArrayList resultset=new ArrayList();
	      ExpiredSadaremCertificateDAO comObj1 = new ExpiredSadaremCertificatesDAOImpl();
	      
	      resultset=comObj1.getabstractsadaremexpireddata(fromdate,todate,district,mandal,areatype,TRIGPSel);
	    
	  	String distname=obj.getDistrictName(district);
	  	String mandalname=obj.getMandalName(district,mandal);
	  	
	  	 districtList = (ArrayList)obj.getDistrictListbyAreatype(areatype);
	 	 mandalList = (ArrayList)obj.getMandalListbyDistrictIdAreatype(district,areatype); 	
	  	villageList = (ArrayList)obj.getVillageListByDistrictIDMandalIDAreaWise(district, mandal,areatype);
	  	
	  	 request.setAttribute("resultList",resultset);
	  	 request.setAttribute("fromdate",fromdate);
	  	request.setAttribute("todate",todate);
	  	request.setAttribute("district",district);
	  	request.setAttribute("mandal",mandal);
	  	//request.setAttribute("village",village);
	  	request.setAttribute("areatype1",areatype);
	  	request.setAttribute("TRIGPSel",TRIGPSel);
	  	

		
	
	  	request.setAttribute("districtList",districtList);
		 request.setAttribute("mandalList",mandalList);
	 request.setAttribute("villageList",villageList);
		 
	  	String heading= comObj1.getHeading(fromdate,todate,distname,mandalname,district,mandal,areatype,TRIGPSel);
	  	
	  	request.setAttribute("heading",heading);
	  	 
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
	           String fromdate=CommonUtility.checkNullObj(request.getParameter("fromdate"));	       
	  	       String todate=CommonUtility.checkNullObj(request.getParameter("todate"));
	  	       String district=CommonUtility.checkNullObj(request.getParameter("district"));
	  	       String mandal=CommonUtility.checkNullObj(request.getParameter("mandal"));
	  	      String heading=CommonUtility.checkNullObj(request.getParameter("heading"));
	  	    String areatype=CommonUtility.checkNullObj(request.getParameter("areatype"));
	  	  String TRIGPSel = CommonUtility.checkNullObj(request.getParameter("TRIGPSel"));
	  	      heading= heading.replaceAll("\\<.*?\\>", "");
	  	       
	  	 	if(district.equals(""))
		 	{
	  	 		district  =  "-1";
		 	}
			if(mandal.equals(""))
		 	{
				mandal  =  "-1";
		 	}
			if(areatype.equals(""))
		 	{
				areatype  =  "-1";
		 	}
			if(TRIGPSel.equals(""))
		 	{
				TRIGPSel  =  "-1";
		 	}
			
		  	   ArrayList resultset=new ArrayList();
		  	   ArrayList resultset1=new ArrayList();
		  	  ExpiredSadaremCertificateDAO comObj1 = new ExpiredSadaremCertificatesDAOImpl();
	  	       
	  	 	 
	  	      resultset=  comObj1.getabstractsadaremexpireddata(fromdate,todate,district,mandal,areatype,TRIGPSel);
	  	    exportExcel(resultset,request, response, district,mandal,fromdate, todate,heading,areatype);
	 

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
 public static void exportExcel(ArrayList resultList,HttpServletRequest request, HttpServletResponse response,
         String district, String mandal,String fromdate,String todate ,String headingmsg,String areatype )
 {
     		OutputStream out = null;

     try {
    	 String  currentdate = CommonUtility.getDateTime("dd/MM/yyyy_hh_mm_ss"); 
    		//System.out.println("enter excel");
         response.setContentType("application/vnd.ms-excel");
         response.setHeader("Content-Disposition", "attachment;filename=R_5.3_Expired_SADAREM_Certificates"+currentdate+".xls");
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
         
         WritableCellFormat cell222 = new WritableCellFormat(bold);
         cell222.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell222.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell222.setAlignment(Alignment.RIGHT);
         cell222.setBackground(Colour.GRAY_25);

         cell222.setVerticalAlignment(VerticalAlignment.CENTRE);
         
         
         WritableCellFormat cell12 = new WritableCellFormat(bold);
         cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
         cell12.setAlignment(Alignment.CENTRE);
         cell12.setVerticalAlignment(VerticalAlignment.CENTRE);
         
         WritableCellFormat cellred = new WritableCellFormat(bold);
         cellred.setBorder(Border.ALL, BorderLineStyle.THIN);
         cellred.setBorder(Border.ALL, BorderLineStyle.THIN);
         cellred.setAlignment(Alignment.CENTRE);
         cellred.setBackground(Colour.RED);
         
         

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
             s.addCell(new Label(0, 0, "SADAREM Assessed report................"));
         } 
       else 
         {
     	  
    	   if(fromdate.length()==10 && todate.length()==10 && !district.equals("-1") && !mandal.equals("-1")  )  
           {
     	  s.mergeCells(0, 0, 3, 0);
             s.addCell(new Label(0, 0, "Generated Date:"+currentdate1+"", cell111));
             
             
          
           }
    	   else
    	   {
    		  
    			  s.mergeCells(0, 0, 5, 0);
    	             s.addCell(new Label(0, 0, "Generated Date:"+currentdate1+"", cell111));   
    	        
    	   }
     
             if(fromdate.length()==10 && todate.length()==10 && !district.equals("-1") && !mandal.equals("-1"))  
             {	            
             s.mergeCells(4, 0, 20, 0);
             s.addCell(new Label(4, 0, " "+"R 5.3 Expired Sadarem Certificates "+headingmsg, cell13));
             }
         }
         
         if(fromdate.length()==10 && todate.length()==10 && !district.equals("-1") && !mandal.equals("-1"))  
         {	
        	   s.mergeCells(0, 1, 1, 1);
               s.addCell(new Label(0, 1,"",cell111)); 
  
              
              s.mergeCells(2, 1, 2, 1);
              s.addCell(new Label(2, 1, "Expired", cellred));
              
             s.mergeCells(3, 1, 20, 1);
             s.addCell(new Label(3, 1,"",cell111)); 
             
           
             
             
         }
         else
        	 
         {
        	 s.mergeCells(0, 1, 5, 1);
             s.addCell(new Label(0, 1, " "+"R 5.3 Expired Sadarem Certificates "+headingmsg, cell13));
        	 //s.mergeCells(0, 1, 10, 1);
            // s.addCell(new Label(0, 1,"",cell111)); 
         }
             
             
if(fromdate.length()==10 && todate.length()==10 && !district.equals("-1") && !mandal.equals("-1"))  
{	
             s.mergeCells(0, 2, 0, 2);
             s.addCell(new Label(0, 2, "S.No", cell111));
             setWidth(s, 0, 10);
             
      
       	     s.mergeCells(1, 2, 1, 2);
               s.addCell(new Label(1, 2, "SADAREM ID", cell111));
               setWidth(s, 1, 25);
         
       
	            s.mergeCells(2, 2, 2, 2);
	            s.addCell(new Label(2, 2, "District", cell111));
	            setWidth(s, 1, 25);
	            
	            s.mergeCells(3, 2, 3, 2);
	            s.addCell(new Label(3, 2, "Mandal", cell111));
	            setWidth(s, 1, 25);
	            
	            s.mergeCells(4, 2, 4, 2);
	            s.addCell(new Label(4, 2, "Village", cell111));
	            setWidth(s, 1, 25);
	            
	            s.mergeCells(5, 2, 5, 2);
	            s.addCell(new Label(5, 2, "Habitation", cell111));
	            setWidth(s, 1, 25);
	            
	            
	            s.mergeCells(6, 2, 6, 2);
	            s.addCell(new Label(6, 2, "Person Name", cell111));
	            setWidth(s, 2, 40);
	            
	            s.mergeCells(7, 2, 7, 2);
	            s.addCell(new Label(7, 2, "Date Of \n Birth", cell111));
	            setWidth(s, 2, 15);
	            
	            s.mergeCells(8, 2, 8, 2);
	            s.addCell(new Label(8, 2, "Gender", cell111));
	            setWidth(s, 2, 15);
	            
	            s.mergeCells(9, 2, 9, 2);
	            s.addCell(new Label(9, 2, "Relation \n Details", cell111));
	            setWidth(s, 2, 40);
	            
	            s.mergeCells(10, 2, 10, 2);
	            s.addCell(new Label(10, 2, "Person Live \nStatus", cell111));
	            setWidth(s, 2, 20);
	            
	            s.mergeCells(11, 2, 11, 2);
	            s.addCell(new Label(11, 2, "Contact", cell111));
	            setWidth(s, 2, 20);
	            
	            s.mergeCells(12, 2, 12, 2);
	            s.addCell(new Label(12, 2, "Qualification", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(13, 2, 13, 2);
	            s.addCell(new Label(13, 2, "Proof \nType", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(14, 2, 14, 2);
	            s.addCell(new Label(14, 2, "Proof", cell111));
	            setWidth(s, 2, 25);
	            
	           
	            
	            s.mergeCells(15, 2, 15, 2);
	            s.addCell(new Label(15, 2, "Disability", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(16, 2, 16, 2);
	            s.addCell(new Label(16, 2, "Disability\nPercentage", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(17, 2, 17, 2);
	            s.addCell(new Label(17, 2, "Certificate \nStatus", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(18, 2, 18, 2);
	            s.addCell(new Label(18, 2, "Certificate \nType", cell111));
	            setWidth(s, 2, 25);
	            s.mergeCells(19, 2, 19, 2);
	            s.addCell(new Label(19, 2, "Certificate \nIssue Date", cell111));
	            setWidth(s, 2, 25);
	            s.mergeCells(20, 2, 20, 2);
	            s.addCell(new Label(20, 2, "Certificate \nExpiry Date", cell111));
	            setWidth(s, 2, 25);
	           
	          
             
             s.addCell(new Label(0, 3, "1", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(1, 3, "2", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(2, 3, "3", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(3, 3, "4", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(4, 3, "5", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(5, 3, "6", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(6, 3, "7", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(7, 3, "8", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(8, 3, "9", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(9, 3, "10", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(10,3, "11", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(11,3, "12", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(12,3, "13", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(13,3, "14", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(14,3, "15", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(15,3, "16", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(16,3, "17", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(17,3, "18", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(18,3, "19", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(19,3, "20", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(20,3, "21", cell111));
             setWidth(s, 4, 30); 
            
            
}   
else
{
	   s.mergeCells(0, 2, 0, 3);
       s.addCell(new Label(0, 2, "S.No", cell111));
       setWidth(s, 0, 10);
			         if (district.equalsIgnoreCase("-1") && mandal.equalsIgnoreCase("-1") ) 
				           {
				         	     s.mergeCells(1, 2, 1, 3);
				                 s.addCell(new Label(1, 2, "District", cell111));
				                 setWidth(s, 1, 30);
				           }
				         else if (!district.equalsIgnoreCase("-1") && mandal.equalsIgnoreCase("-1") ) 
				           {
				         	     s.mergeCells(1, 2, 1, 3);
				                 s.addCell(new Label(1, 2, "Mandal", cell111));
				                 setWidth(s, 1, 30);
				           }
				        
				       					     
	  				 	

 
          s.mergeCells(2, 2, 2, 3);
          s.addCell(new Label(2, 2, "Total Assessed", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(3, 2, 3, 3);
          s.addCell(new Label(3, 2, "Total Temporary\n Certificates", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(4, 2, 5, 2);
          s.addCell(new Label(4, 2, "Total", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(4, 3, 4, 3);
          s.addCell(new Label(4, 3, "Expired", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(5, 3, 5, 3);
          s.addCell(new Label(5, 3, "Yet to be expired", cell111));
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
          s.addCell(new Label(5, 4, "6", cell111));
          setWidth(s, 4, 30); 
        
        
     

}

    
if(fromdate.length()==10 && todate.length()==10 && !district.equals("-1") && !mandal.equals("-1"))  
{	
         int x = 4;
         int k = 0;
        
         ArrayList templist = new ArrayList(); 
         for (int i = 0; i < resultList.size(); i++, x++) 
         {
         	 int j = 0;
             k++;
             templist = (ArrayList)resultList.get(i);
             s.addCell(new Label(j++, x, i + 1 + "", cell));
    
             s.addCell(new Label(j++, x,templist.get(0).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(1).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(2).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(3).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(4).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(5).toString()+" "+templist.get(6).toString(), cellLeft));
            // s.addCell(new Label(j++, x,templist.get(6).toString(), cell));
             s.addCell(new Label(j++, x,templist.get(7).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(8).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(9).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(10).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(11).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(12).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(13).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(14).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(15).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(16).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(17).toString(), cellLeft));
             s.addCell(new Label(j++, x,templist.get(18).toString(), cellLeft));
            s.addCell(new Label(j++, x,templist.get(19).toString(), cellLeft));
            
            if(templist.get(21).toString().equals("0"))
            {
            s.addCell(new Label(j++, x,templist.get(20).toString(), cellred));
            }
            else
            {
            s.addCell(new Label(j++, x,templist.get(20).toString(), cellLeft));  	
            }
         

        
             if (i == (100000) * page)
             {
                 int sheet = ++page;
                 s = w.createSheet("Sheet" + sheet, sheet - 1);
                 j = 0;
                 x = 2;
             }
         }
         
}
else
{
	  int x = 5;
      int k = 0;
     
      ArrayList templist = new ArrayList(); 
      for (int i = 0; i < resultList.size()-1; i++, x++) 
      {
      	 int j = 0;
          k++;
          templist = (ArrayList)resultList.get(i);
          s.addCell(new Label(j++, x, i + 1 + "", cell));
 
          //s.addCell(new Label(j++, x,templist.get(0).toString(), cellLeft));
          s.addCell(new Label(j++, x,templist.get(0).toString(), cellLeft));
        
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(1).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(2).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(3).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(4).toString()), integerstyle));
          
         
   
        
   
          
          if (i == (100000) * page)
          {
              int sheet = ++page;
              s = w.createSheet("Sheet" + sheet, sheet - 1);
              j = 0;
              x = 2;
          }
      }
      templist=(ArrayList)resultList.get(resultList.size()-1);	   
      int j = 0; 
      s.addCell(new Label(j++, x, "  ", cell111));
      if(templist.get(0).toString().equalsIgnoreCase("ZZZ_TOTAL"))
      {
      s.addCell(new Label(j++, x,"TOTAL", cell111));
      }
      else
      {
      	  s.addCell(new Label(j++, x,templist.get(0).toString(), cell111));
      }
      
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(1).toString()), cell222)); 
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(2).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(3).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(4).toString()), cell222));
  
       
      
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


