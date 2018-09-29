


package com.tcs.sadarem.reports.actions;

 //* To change this template, choose Tools | Templates



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



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import jxl.Workbook;
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
import com.tcs.sadarem.reports.DAO.CertiUploadReportDAO;
import com.tcs.sadarem.reports.DAO.CommonReportsDAO;
import com.tcs.sadarem.reports.DAO.DirectorReportOutsideDAO;
import com.tcs.sadarem.reports.DAO.EducationWiseDAO;
import com.tcs.sadarem.reports.DAO.ExpiredSadaremCertificateDAO;
import com.tcs.sadarem.reports.DAO.MultipleCasesReportDAO;
import com.tcs.sadarem.reports.DAOImpl.CertiUploadReportDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.CommonReportsDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.DirectorReportOutsideDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.EducationWiseDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.ExpiredSadaremCertificatesDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.MultipleCasesReportDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author Administrator
 */
public class MultipleCasesReportAction extends DispatchAction {

    private String target = "success"; 

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException 
            { 

	    	CommonReportsDAO comObj1 = new CommonReportsDAOImpl();
	    	
	
	      	ArrayList casteList = new ArrayList(); 
	    	ArrayList educationList = new ArrayList();
	    	ArrayList employmentList = new ArrayList();
	    	ArrayList disabilityList = new ArrayList();
	    	ArrayList districtList = new ArrayList(); 
	    	
	    	casteList = comObj1.getCasteList();
	    	educationList=comObj1.getEducationList();
	    	employmentList=comObj1.getEmploymentList();
	    	disabilityList=comObj1.getDisabilityList();
	    	districtList = (ArrayList)comObj1.getDistrictList();
	    	
	    	request.setAttribute("fromage","0");
		  	request.setAttribute("toage","100");
		  	request.setAttribute("fromdisbpercent","0");
		  	request.setAttribute("todisbpercent","100");
	    	request.setAttribute("casteList",casteList);
	    	request.setAttribute("educationList",educationList);
	    	request.setAttribute("employmentList",employmentList);
	    	request.setAttribute("disabilityList",disabilityList);
	    	request.setAttribute("districtList",districtList);
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
        	ArrayList casteList = new ArrayList(); 
        	ArrayList educationList = new ArrayList();
        	ArrayList employmentList = new ArrayList();
        	ArrayList disabilityList = new ArrayList();
        	
        	CommonReportsDAO obj=new CommonReportsDAOImpl();
        
    	   String msg="";
    	   String fromdate=CommonUtility.checkNullObj(request.getParameter("fromdate1"));
	       String todate=CommonUtility.checkNullObj(request.getParameter("todate1"));
	       String district=CommonUtility.checkNullObj(request.getParameter("district1"));
	       String mandal=CommonUtility.checkNullObj(request.getParameter("mandal"));
	      String village=CommonUtility.checkNullObj(request.getParameter("village"));
	      	String TRIGPSel = CommonUtility.checkNullObj(request.getParameter("TRIGPSel"));
	       String areatype=CommonUtility.checkNullObj(request.getParameter("areatype1"));
	       String caste=CommonUtility.checkNullObj(request.getParameter("caste"));
	       String disability=CommonUtility.checkNullObj(request.getParameter("disability"));
	       String gender=CommonUtility.checkNullObj(request.getParameter("gender"));
	       String education=CommonUtility.checkNullObj(request.getParameter("education"));
	       String employment=CommonUtility.checkNullObj(request.getParameter("employment"));
	       String agefrom=CommonUtility.checkNullObj(request.getParameter("fromage"));
	       String ageto=CommonUtility.checkNullObj(request.getParameter("toage"));
	       String disfrom=CommonUtility.checkNullObj(request.getParameter("fromdisbpercent"));
	       String disto=CommonUtility.checkNullObj(request.getParameter("todisbpercent"));
	   
	       
	 //System.out.println("vinnu"+agefrom+ageto+disfrom+disto);
	
	      ArrayList resultset=new ArrayList();
	      MultipleCasesReportDAO comObj1 = new MultipleCasesReportDAOImpl();
	      
	      resultset=comObj1.getsearchddata(fromdate,todate,district,mandal,village,areatype,disability,gender,caste,education,employment,agefrom,ageto,disfrom,disto,TRIGPSel);
	    
	  	String distname=obj.getDistrictName(district);
	  	String mandalname=obj.getMandalName(district,mandal);
	  	String villagename=obj.getVillageName(district,mandal,village);
	  	
	  	 districtList = (ArrayList)obj.getDistrictListbyAreatype(areatype);
	 	 mandalList = (ArrayList)obj.getMandalListbyDistrictIdAreatype(district,areatype); 	
	  	villageList = (ArrayList)obj.getVillageListByDistrictIDMandalIDAreaWise(district, mandal,areatype);
	  	
	  	 request.setAttribute("resultList",resultset);
	  	 request.setAttribute("fromdate",fromdate);
	  	request.setAttribute("todate",todate);
	  	request.setAttribute("district",district);
	  	request.setAttribute("mandal",mandal);
	  	request.setAttribute("village",village);
	  	request.setAttribute("areatype1",areatype);
	  	request.setAttribute("TRIGPSel",TRIGPSel);
	  	request.setAttribute("caste",caste);
	  	request.setAttribute("disability",disability);
	  	request.setAttribute("gender",gender);
	  	request.setAttribute("employment",employment);
	  	request.setAttribute("fromage",agefrom);
	  	request.setAttribute("toage",ageto);
	  	request.setAttribute("fromdisbpercent",disfrom);
	  	request.setAttribute("todisbpercent",disto);
	  	request.setAttribute("education",education);
	  
	  	
	
    	casteList = obj.getCasteList();
    	educationList=obj.getEducationList();
    	employmentList=obj.getEmploymentList();
    	disabilityList=obj.getDisabilityList();

	  	request.setAttribute("casteList",casteList);
    	request.setAttribute("educationList",educationList);
    	request.setAttribute("employmentList",employmentList);
    	request.setAttribute("disabilityList",disabilityList);
	
	  	request.setAttribute("districtList",districtList);
		 request.setAttribute("mandalList",mandalList);
	 request.setAttribute("villageList",villageList);
		 
	  	String heading= comObj1.getHeading(fromdate,todate,distname,mandalname,villagename,district,mandal,village,areatype,TRIGPSel);
  	
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
	        MultipleCasesReportDAO comObj1 = new MultipleCasesReportDAOImpl();
	           String fromdate=CommonUtility.checkNullObj(request.getParameter("fromdate"));	       
	  	       String todate=CommonUtility.checkNullObj(request.getParameter("todate"));
	  	       String district=CommonUtility.checkNullObj(request.getParameter("district"));
	  	       String mandal=CommonUtility.checkNullObj(request.getParameter("mandal"));
	  	     String village=CommonUtility.checkNullObj(request.getParameter("village"));
	  	   String TRIGPSel = CommonUtility.checkNullObj(request.getParameter("TRIGPSel"));
	  	    String areatype=CommonUtility.checkNullObj(request.getParameter("areatype"));
	  	  String caste=CommonUtility.checkNullObj(request.getParameter("caste"));
	       String disability=CommonUtility.checkNullObj(request.getParameter("disability"));
	       String heading=CommonUtility.checkNullObj(request.getParameter("heading"));
	       String gender=CommonUtility.checkNullObj(request.getParameter("gender"));
	       String education=CommonUtility.checkNullObj(request.getParameter("education"));
	       String employment=CommonUtility.checkNullObj(request.getParameter("employment"));
	       String agefrom=CommonUtility.checkNullObj(request.getParameter("fromage"));
	       String ageto=CommonUtility.checkNullObj(request.getParameter("toage"));
	       String disfrom=CommonUtility.checkNullObj(request.getParameter("fromdisbpercent"));
	       String disto=CommonUtility.checkNullObj(request.getParameter("todisbpercent"));
	       heading=heading.replaceAll("\\<.*?\\>", "");
	  	       
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
			if(agefrom.equals(""))
		 	{
				agefrom  =  "-1";
		 	}
			if(ageto.equals(""))
		 	{
				ageto  =  "-1";
		 	}
			if(village.equals(""))
		 	{
				village  =  "-1";
		 	}
			if(disfrom.equals(""))
		 	{
				disfrom  =  "-1";
		 	}
			if(disto.equals(""))
		 	{
				disto  =  "-1";
		 	}
			if(caste.equals(""))
		 	{
				caste  =  "-1";
		 	}if(disability.equals(""))
		 	{
				disability  =  "-1";
		 	}if(education.equals(""))
		 	{
				education  =  "-1";
		 	}if(employment.equals(""))
		 	{
				employment  =  "-1";
		 	}if(gender.equals(""))
		 	{
				gender  =  "-1";
		 	}
		 	if(TRIGPSel.equals(""))
		 	{
		 		TRIGPSel  =  "-1";
		 	}
		  	   ArrayList resultset=new ArrayList();
		  	   ArrayList resultset1=new ArrayList();
		  	   
	  	       
	  	 	 
		  	   resultset=comObj1.getsearchddata(fromdate,todate,district,mandal,village,areatype,disability,gender,caste,education,employment,agefrom,ageto,disfrom,disto,TRIGPSel);
	  	    exportExcel(resultset,request, response, district,mandal,village,fromdate, todate,heading,areatype);
	 

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
 public static void exportExcel(ArrayList resultList,HttpServletRequest request, HttpServletResponse response,
         String district, String mandal,String village,String fromdate,String todate ,String headingmsg,String areatype )
 {
     		OutputStream out = null;

     try {
    	 String  currentdate = CommonUtility.getDateTime("dd/MM/yyyy_hh_mm_ss"); 
    		//System.out.println("enter excel");
         response.setContentType("application/vnd.ms-excel");
         response.setHeader("Content-Disposition", "attachment;filename=R_4.4_Search_Details_Report"+currentdate+".xls");
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
     	  
    	   
     	  s.mergeCells(0, 0, 2, 0);
             s.addCell(new Label(0, 0, "Generated Date:"+currentdate1+"", cell111));
       
    	  
     
            	            
             s.mergeCells(3, 0, 24, 0);
             s.addCell(new Label(3, 0, " "+"R 4.4 Search Details Report "+headingmsg, cell13));
            
           
         }
         
       	
             s.mergeCells(0, 1, 24, 1);
             s.addCell(new Label(0, 1,"",cell111));
        
        
             
	
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
	            s.addCell(new Label(8, 2, "Age", cell111));
	            setWidth(s, 2, 15);
	           	      
	            
	            s.mergeCells(9, 2, 9, 2);
	            s.addCell(new Label(9, 2, "Gender", cell111));
	            setWidth(s, 2, 40);
	            
	            s.mergeCells(10, 2, 10, 2);
	            s.addCell(new Label(10, 2, "Relation \n Details", cell111));
	            setWidth(s, 2, 20);
	            
	            s.mergeCells(11, 2, 11, 2);
	            s.addCell(new Label(11, 2, "Person Live \nStatus", cell111));
	            setWidth(s, 2, 20);
	            
	            s.mergeCells(12, 2, 12, 2);
	            s.addCell(new Label(12, 2, "Contact", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(13, 2, 13, 2);
	            s.addCell(new Label(13, 2, "Qualification", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(14, 2, 14, 2);
	            s.addCell(new Label(14, 2, "Employment\nType ", cell111));
	            setWidth(s, 2, 25);
	            
	           
	            
	            s.mergeCells(15, 2, 15, 2);
	            s.addCell(new Label(15, 2, "Caste ", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(16, 2, 16, 2);
	            s.addCell(new Label(16, 2, "Marital\nStatus", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(17, 2, 17, 2);
	            s.addCell(new Label(17, 2, "Proof \nType", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(18, 2, 18, 2);
	            s.addCell(new Label(18, 2, "Proof", cell111));
	            setWidth(s, 2, 25);
	            s.mergeCells(19, 2, 19, 2);
	            s.addCell(new Label(19, 2, "Disability", cell111));
	            setWidth(s, 2, 25);
	            s.mergeCells(20, 2, 20, 2);
	            s.addCell(new Label(20, 2, "Disability\nPercentage", cell111));
	            setWidth(s, 2, 25);
	            s.mergeCells(21, 2, 21, 2);
	            s.addCell(new Label(21, 2, "Certificate \nStatus", cell111));
	            setWidth(s, 2, 25);
	            s.mergeCells(22, 2, 22, 2);
	            s.addCell(new Label(22, 2, "Certificate \nType", cell111));
	            setWidth(s, 2, 25);
	            s.mergeCells(23, 2, 23, 2);
	            s.addCell(new Label(23, 2, "Certificate \nIssue Date", cell111));
	            setWidth(s, 2, 25);
	            s.mergeCells(24, 2, 24, 2);
	            s.addCell(new Label(24, 2, "Certificate \nExpiry Date", cell111));
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
             s.addCell(new Label(21,3, "22", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(22,3, "23", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(23,3, "24", cell111));
             setWidth(s, 4, 30); 
             s.addCell(new Label(24,3, "25", cell111));
             setWidth(s, 4, 30); 
            
            



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
            s.addCell(new Label(j++, x,templist.get(20).toString(), cellLeft));
            s.addCell(new Label(j++, x,templist.get(21).toString(), cellLeft));
            s.addCell(new Label(j++, x,templist.get(22).toString(), cellLeft));
            s.addCell(new Label(j++, x,templist.get(23).toString(), cellLeft));
            s.addCell(new Label(j++, x,templist.get(24).toString(), cellLeft));
            
         

        
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


