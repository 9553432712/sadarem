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
import com.tcs.sadarem.reports.DAOImpl.CommonReportsDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.DirectorReportOutsideDAOImpl;
import com.tcs.sadarem.util.CommonUtility;


/**
 *
 * @author Administrator
 */
public class DirectorReportOutside extends DispatchAction {

    private String target = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException 
            { 
    	String action=CommonUtility.checkNullObj(request.getParameter("actionval"));
    	//System.out.println("vinnu  -"+CommonUtility.checkNullObj(request.getParameter("action")));
    	if(action.equals("getDistrictWise"))
    		{
    		getDistrictWise(mapping,form,request,response);
    		}
    	else if(action.equals("getCampWise"))
    		{
    		getCampWise(mapping,form,request,response);
    		}
    
	    	CommonReportsDAO comObj1 = new CommonReportsDAOImpl();
	    	
	
	      	ArrayList districtList = new ArrayList(); 
	    	ArrayList disabilityList = new ArrayList();
	    	
	      	districtList = comObj1.getDistrictList();		 	
	      	disabilityList = (ArrayList)comObj1.getDisabilityList();
	  
	      	request.setAttribute("districtList", districtList);
	      	request.setAttribute("disabilityList", disabilityList);
    	
    	
    	 
    	return mapping.findForward(target);
            }
    
    
    public ActionForward getCampWise(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)
            throws SADAREMException, SQLException 
    {
	     
    	try{
    		ArrayList districtList = new ArrayList(); 
        	ArrayList mandalList = new ArrayList(); 
        	ArrayList villageList = new ArrayList(); 
        	ArrayList campList = new ArrayList(); 
    		 String msg="";
    		 CommonReportsDAO obj=new CommonReportsDAOImpl();
    	   String fromdate=CommonUtility.checkNullObj(request.getParameter("fromdate2"));
	       String todate=CommonUtility.checkNullObj(request.getParameter("todate2"));
	       String district1=CommonUtility.checkNullObj(request.getParameter("district2"));
	       String mandal=CommonUtility.checkNullObj(request.getParameter("mandal1"));
	       String village=CommonUtility.checkNullObj(request.getParameter("village1"));
	       String campId=CommonUtility.checkNullObj(request.getParameter("campId"));
	       String areatype2=CommonUtility.checkNullObj(request.getParameter("areatype2"));
	 
	     
	       
	      // System.out.println(" fromdate "+fromdate+" todate "+todate+" district1 "+district1+" mandal "+mandal+" village "+village+" campId "+campId);
	       ArrayList resultset=new ArrayList();
		      DirectorReportOutsideDAO comObj1 = new DirectorReportOutsideDAOImpl();
		      resultset=comObj1.getabstractassessedcampdata(fromdate,todate,district1,mandal,village,campId,areatype2);
		      
			  	if(resultset!=null && resultset.size()>0)
		    	{
		    		msg="";	
		    	}
		    	else
		    	{
		    		msg="No Record Found to Display";		
		    	}
			  	String distname=obj.getDistrictName(district1);
			  	String mandalname=obj.getMandalName(district1,mandal);
			  	String villagename=obj.getVillageName(district1,mandal,village);
			  	String campname=obj.getCampName(campId);
			  	
			  	 request.setAttribute("campresultList",resultset);
			  	 request.setAttribute("fromdate4",fromdate);
			  	request.setAttribute("todate4",todate);
			  	request.setAttribute("district14",district1);
			  	request.setAttribute("mandal4",mandal);
			  	request.setAttribute("village4",village);
			  	request.setAttribute("campId",campId);
			  	request.setAttribute("distname",distname);
			  	request.setAttribute("mandalname",mandalname);
			  	request.setAttribute("villagename",villagename);
				request.setAttribute("campname",campname);
				request.setAttribute("areatype2",areatype2);
			
				 campList = (ArrayList)obj.getCampListByDistrictID(district1);
				 districtList = (ArrayList)obj.getDistrictListbyAreatype(areatype2);
			 	 mandalList = (ArrayList)obj.getMandalListbyDistrictIdAreatype(district1,areatype2);
			  	villageList = (ArrayList)obj.getVillageListByDistrictIDMandalIDAreaWise(district1, mandal,areatype2);
				
				String campheadingmsg= comObj1.getCampWiseHeading(fromdate,todate,distname,mandalname,villagename,district1,mandal,village,campId,campname,areatype2);
				 request.setAttribute("districtList",districtList);
				 request.setAttribute("mandalList",mandalList);
				 request.setAttribute("villageList",villageList);
				 request.setAttribute("campList",campList);
				request.setAttribute("campheadingmsg",campheadingmsg);
			  	 
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
	       String district1=CommonUtility.checkNullObj(request.getParameter("district1"));
	       String mandal=CommonUtility.checkNullObj(request.getParameter("mandal"));
	       String village=CommonUtility.checkNullObj(request.getParameter("village"));
	       String disabilityid=CommonUtility.checkNullObj(request.getParameter("disability1"));
	       String areatype1=CommonUtility.checkNullObj(request.getParameter("areatype1"));
	       String TRIGPSel1 =CommonUtility.checkNullObj(request.getParameter("TRIGPSel1"));
	     //  System.out.println("fromdate2 "+fromdate+"todate "+todate+"district1 "+district1+"mandal "+mandal+"village "+village+"disabilityid "+disabilityid);
	       
	      ArrayList resultset=new ArrayList();
	      DirectorReportOutsideDAO comObj1 = new DirectorReportOutsideDAOImpl();
	      
	      resultset=comObj1.getabstractassesseddata(fromdate,todate,district1,mandal,village,disabilityid,areatype1,TRIGPSel1);
	    
	  	String distname=obj.getDistrictName(district1);
	  	String mandalname=obj.getMandalName(district1,mandal);
	  	String villagename=obj.getVillageName(district1,mandal,village);
	  	
	  	 districtList = (ArrayList)obj.getDistrictListbyAreatype(areatype1);
	 	 mandalList = (ArrayList)obj.getMandalListbyDistrictIdAreatype(district1,areatype1);
	  	villageList = (ArrayList)obj.getVillageListByDistrictIDMandalIDAreaWise(district1, mandal,areatype1);
	  	
	  	 request.setAttribute("resultList",resultset);
	  	 request.setAttribute("fromdate3",fromdate);
	  	request.setAttribute("todate3",todate);
	  	request.setAttribute("district13",district1);
	  	request.setAttribute("mandal3",mandal);
	  	request.setAttribute("village3",village);
	  	request.setAttribute("disabilityid3",disabilityid);
	  	
	  	request.setAttribute("distname",distname);
	  	request.setAttribute("mandalname",mandalname);
	  	request.setAttribute("villagename",villagename);
	  	request.setAttribute("TRIGPSel1",TRIGPSel1);
		request.setAttribute("areatype1",areatype1);
		 request.setAttribute("districtList",districtList);
		 request.setAttribute("mandalList",mandalList);
		 request.setAttribute("villageList",villageList);
	  	String districtheadingmsg= comObj1.getDistrictWiseHeading(fromdate,todate,distname,mandalname,villagename,district1,mandal,village,areatype1,TRIGPSel1);
	  	
	  	request.setAttribute("districtheadingmsg",districtheadingmsg);
	  	 
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
	           String fromdate2=CommonUtility.checkNullObj(request.getParameter("fromdate2"));
	  	       String todate=CommonUtility.checkNullObj(request.getParameter("todate"));
	  	       String district=CommonUtility.checkNullObj(request.getParameter("district"));
	  	       String mandal=CommonUtility.checkNullObj(request.getParameter("mandal"));
	  	       String village=CommonUtility.checkNullObj(request.getParameter("village"));
	  	       String campRdisId=CommonUtility.checkNullObj(request.getParameter("campRdisId"));
	  	      String heading=CommonUtility.checkNullObj(request.getParameter("heading"));
	  	    String areatype=CommonUtility.checkNullObj(request.getParameter("areatype"));
	  	  String TRIGPSel =CommonUtility.checkNullObj(request.getParameter("TRIGPSel"));
	  	      heading= heading.replaceAll("\\<.*?\\>", "");
	  	       
	  	 	if(district.equals(""))
		 	{
	  	 		district  =  "-1";
		 	}
			if(mandal.equals(""))
		 	{
				mandal  =  "-1";
		 	}
			if(village.equals(""))
		 	{
				village  =  "-1";
		 	}
			if(TRIGPSel.equals(""))
		 	{
				TRIGPSel  =  "-1";
		 	}
			if(campRdisId.equals(""))
		 	{
				campRdisId  =  "-1";
		 	}
		  	   ArrayList resultset=new ArrayList();
		  	   ArrayList resultset1=new ArrayList();
	  	       DirectorReportOutsideDAO comObj1 = new DirectorReportOutsideDAOImpl();
	  	       
	  	 	String flag="DistrictWise";
	  	 	String cflag="CampWise";
	  	 	if(fromdate1.length()>0)
	  		{
	  	      resultset=comObj1.getabstractassesseddata(fromdate1,todate,district,mandal,village,campRdisId,areatype,TRIGPSel);
	  	    exportExcel(resultset,request, response, district,mandal,village,fromdate1, todate,campRdisId,heading,flag);
	  		}
			    
	  		
	  		if(fromdate2.length()>0)
	  		{
			      resultset1=comObj1.getabstractassessedcampdata(fromdate2,todate,district,mandal,village,campRdisId,areatype);
			      exportExcel(resultset1,request, response, district,mandal,village,fromdate2,todate,campRdisId,heading,cflag);
	  		}//    System.out.println("uhfoes"+resultset1);
			    
				  
					
				
		
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
 public static void exportExcel(ArrayList resultList,HttpServletRequest request, HttpServletResponse response,
         String district, String mandal,String villageid,String fromdate,String todate,String campRdisId,String headingmsg,String flag)
 {
     		OutputStream out = null;

     try {
    	 String  currentdate = CommonUtility.getDateTime("dd/MM/yyyy_hh_mm_ss"); 
    		//System.out.println("enter excel");
         response.setContentType("application/vnd.ms-excel");
         response.setHeader("Content-Disposition", "attachment;filename=R_1.2_"+flag+"AssessmentReport_"+currentdate+".xls");
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
     
             if(!district.equalsIgnoreCase("-1") && !mandal.equalsIgnoreCase("-1") &&  !villageid.equalsIgnoreCase("-1") && !campRdisId.equalsIgnoreCase("-1"))  
             {	            
             s.mergeCells(3, 0, 19, 0);
             s.addCell(new Label(3, 0, " "+"SADAREM Assessed Report"+" "+flag+" "+headingmsg, cell13));
             }
             else
             {
            	  s.mergeCells(3, 0, 29, 0);
                  s.addCell(new Label(3, 0, " "+"SADAREM Assessed Report"+" "+flag+" "+headingmsg, cell13)); 
             }
         }
         
         if(!district.equalsIgnoreCase("-1") && !mandal.equalsIgnoreCase("-1") &&  !villageid.equalsIgnoreCase("-1") && !campRdisId.equalsIgnoreCase("-1"))  
         {	
             s.mergeCells(0, 1, 19, 1);
             s.addCell(new Label(0, 1,"",cell111));
         }
         else
        	 
         {
        	 s.mergeCells(0, 1, 29, 1);
             s.addCell(new Label(0, 1,"",cell111)); 
         }
             
             
if(!district.equalsIgnoreCase("-1") && !mandal.equalsIgnoreCase("-1") &&  !villageid.equalsIgnoreCase("-1") && !campRdisId.equalsIgnoreCase("-1"))  
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
	            s.addCell(new Label(12, 2, "Proof \nType", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(13, 2, 13, 2);
	            s.addCell(new Label(13, 2, "Proof", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(14, 2, 14, 2);
	            s.addCell(new Label(14, 2, "Disability", cell111));
	            setWidth(s, 2, 25);
	            
	           
	            
	            s.mergeCells(15, 2, 15, 2);
	            s.addCell(new Label(15, 2, "Disability\nPercentage", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(16, 2, 16, 2);
	            s.addCell(new Label(16, 2, "Certificate \nStatus", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(17, 2, 17, 2);
	            s.addCell(new Label(17, 2, "Certificate \nType", cell111));
	            setWidth(s, 2, 25);
	            
	            s.mergeCells(18, 2, 18, 2);
	            s.addCell(new Label(18, 2, "Certificate \nIssue Date", cell111));
	            setWidth(s, 2, 25);
	            s.mergeCells(19, 2, 19, 2);
	            s.addCell(new Label(19, 2, "Certificate \nExpiry Date", cell111));
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
            
            
}   
else
{
	   s.mergeCells(0, 2, 0, 3);
       s.addCell(new Label(0, 2, "S.No", cell111));
       setWidth(s, 0, 10);
       
if(flag.equalsIgnoreCase("DistrictWise"))
{
				         if (district.equalsIgnoreCase("-1")) 
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
				         else if (!district.equalsIgnoreCase("-1") && !mandal.equalsIgnoreCase("-1") &&  villageid.equalsIgnoreCase("-1")) 
				           {
				         	     s.mergeCells(1, 2, 1, 3);
				                 s.addCell(new Label(1, 2, "Village", cell111));
				                 setWidth(s, 1, 30);
				           }
				         else if (!district.equalsIgnoreCase("-1") && !mandal.equalsIgnoreCase("-1") &&  !villageid.equalsIgnoreCase("-1")) 
				           {
				         	     s.mergeCells(1, 2, 1, 3);
				                 s.addCell(new Label(1, 2, "Habitation", cell111));
				                 setWidth(s, 1, 30);
				           }					     
	  				 	
}
else if(flag.equalsIgnoreCase("CampWise"))
{
	 
 	     s.mergeCells(1, 2, 1, 3);
         s.addCell(new Label(1, 2, "Camp", cell111));
         setWidth(s, 1, 30);
   
}
   
          s.mergeCells(2, 2, 2, 3);
          s.addCell(new Label(2, 2, "Total \n Assessed", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(3, 2, 3, 3);
          s.addCell(new Label(3, 2, "Eligible", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(4, 2, 4, 3);
          s.addCell(new Label(4, 2, "Not \n Eligible", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(5, 2, 5, 3);
          s.addCell(new Label(5, 2, "Total \n Pensioners", cell111));
          setWidth(s, 1, 30);
       
          s.mergeCells(6, 2, 9, 2);
          s.addCell(new Label(6, 2, "Locomotor/OH", cell111));
          setWidth(s, 4, 30);
          
          s.mergeCells(6, 3, 6, 3);
          s.addCell(new Label(6, 3, "Assessed", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(7, 3, 7, 3);
          s.addCell(new Label(7, 3, "Eligible", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(8, 3, 8, 3);
          s.addCell(new Label(8, 3, "Not \n Eligible", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(9, 3, 9, 3);
          s.addCell(new Label(9, 3, "Pensioners", cell111));
          setWidth(s, 1, 30);
          
        
          
          
    
       s.mergeCells(10, 2, 13, 2);
       s.addCell(new Label(10, 2, "Visual Impairment", cell111));
       setWidth(s, 4, 30);
       
       s.mergeCells(10, 3, 10, 3);
          s.addCell(new Label(10, 3, "Assessed", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(11, 3, 11, 3);
          s.addCell(new Label(11, 3, "Eligible", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(12, 3, 12, 3);
          s.addCell(new Label(12, 3, "Not \n Eligible", cell111));
          setWidth(s, 1, 30);
          
          s.mergeCells(13, 3, 13, 3);
          s.addCell(new Label(13, 3, "Pensioners", cell111));
          setWidth(s, 1, 30);
      
          
          s.mergeCells(14, 2, 17, 2);
           s.addCell(new Label(14, 2, "Hearing Impairment", cell111));
           setWidth(s, 4, 30);
           
           s.mergeCells(14, 3, 14, 3);
	            s.addCell(new Label(14, 3, "Assessed", cell111));
	            setWidth(s, 1, 30);
	            
	            s.mergeCells(15, 3, 15, 3);
	            s.addCell(new Label(15, 3, "Eligible", cell111));
	            setWidth(s, 1, 30);
	            
	            s.mergeCells(16, 3, 16, 3);
	            s.addCell(new Label(16, 3, "Not \n Eligible", cell111));
	            setWidth(s, 1, 30);
	            
	            s.mergeCells(17, 3, 17, 3);
	            s.addCell(new Label(17, 3, "Pensioners", cell111));
	            setWidth(s, 1, 30);
	            
	            
	            
	            s.mergeCells(18, 2, 21, 2);
	             s.addCell(new Label(18, 2, "Mental Retardation", cell111));
	             setWidth(s, 4, 30);
	             
	             s.mergeCells(18, 3, 18, 3);
		            s.addCell(new Label(18, 3, "Assessed", cell111));
		            setWidth(s, 1, 30);
		            
		            s.mergeCells(19, 3, 19, 3);
		            s.addCell(new Label(19, 3, "Eligible", cell111));
		            setWidth(s, 1, 30);
		            
		            s.mergeCells(20, 3, 20, 3);
		            s.addCell(new Label(20, 3, "Not \n Eligible", cell111));
		            setWidth(s, 1, 30);
		            
		            s.mergeCells(21, 3, 21, 3);
		            s.addCell(new Label(21, 3, "Pensioners", cell111));
		            setWidth(s, 1, 30);
       
    
		            s.mergeCells(22, 2, 25, 2);
		             s.addCell(new Label(22, 2, "Mental Illness", cell111));
		             setWidth(s, 4, 30);
		             
		             s.mergeCells(22, 3, 22, 3);
			            s.addCell(new Label(22, 3, "Assessed", cell111));
			            setWidth(s, 1, 30);
			            
			            s.mergeCells(23, 3, 23, 3);
			            s.addCell(new Label(23, 3, "Eligible", cell111));
			            setWidth(s, 1, 30);
			            
			            s.mergeCells(24, 3, 24, 3);
			            s.addCell(new Label(24, 3, "Not \n Eligible", cell111));
			            setWidth(s, 1, 30);
			            
			            s.mergeCells(25, 3, 25, 3);
			            s.addCell(new Label(25, 3, "Pensioners", cell111));
			            setWidth(s, 1, 30);
			            
			            
			            s.mergeCells(26, 2, 29, 2);
			             s.addCell(new Label(26, 2, "Multiple Disability", cell111));
			             setWidth(s, 4, 30);
			             
			             s.mergeCells(26, 3, 26, 3);
				            s.addCell(new Label(26, 3, "Assessed", cell111));
				            setWidth(s, 1, 30);
				            
				            s.mergeCells(27, 3, 27, 3);
				            s.addCell(new Label(27, 3, "Eligible", cell111));
				            setWidth(s, 1, 30);
				            
				            s.mergeCells(28, 3, 28, 3);
				            s.addCell(new Label(28, 3, "Not \n Eligible", cell111));
				            setWidth(s, 1, 30);
				            
				            s.mergeCells(29, 3, 29, 3);
				            s.addCell(new Label(29, 3, "Pensioners", cell111));
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
       s.addCell(new Label(6, 4, "7", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(7, 4, "8", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(8, 4, "9", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(9, 4, "10", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(10,4, "11", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(11,4, "12", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(12,4, "13", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(13,4, "14", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(14,4, "15", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(15,4, "16", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(16,4, "17", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(17,4, "18", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(18,4, "19", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(19,4, "20", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(20,4, "21", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(21,4, "22", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(22,4, "23", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(23,4, "24", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(24,4, "25", cell111));
       setWidth(s, 4, 30); 
       s.addCell(new Label(25,4, "26", cell111));
       setWidth(s, 4, 30); 
       
       s.addCell(new Label(26,4, "27", cell111));
       setWidth(s, 4, 30); 
      
       s.addCell(new Label(27,4, "28", cell111));
       setWidth(s, 4, 30); 
      
       s.addCell(new Label(28,4, "29", cell111));
       setWidth(s, 4, 30); 
      
       s.addCell(new Label(29,4, "30", cell111));
       setWidth(s, 4, 30); 
	
}
    
if(!district.equalsIgnoreCase("-1") && !mandal.equalsIgnoreCase("-1") &&  !villageid.equalsIgnoreCase("-1") && !campRdisId.equalsIgnoreCase("-1"))  
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
          s.addCell(new Label(j++, x,templist.get(1).toString(), cellLeft));
          
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(2).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(3).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(4).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(5).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(6).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(7).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(8).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(9).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(10).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(11).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(12).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(13).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(14).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(15).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(16).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(17).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(18).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(19).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(20).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(21).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(22).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(23).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(24).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(25).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(26).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(27).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(28).toString()), integerstyle));
          s.addCell(new Number(j++, x,Double.parseDouble(templist.get(29).toString()), integerstyle));
        
   
          
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
      if(templist.get(1).toString().equalsIgnoreCase("ZZZ_TOTAL"))
      {
      s.addCell(new Label(j++, x,"TOTAL", cell111));
      }
      else
      {
      	  s.addCell(new Label(j++, x,templist.get(1).toString(), cell111));
      }
      
       
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(2).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(3).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(4).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(5).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(6).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(7).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(8).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(9).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(10).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(11).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(12).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(13).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(14).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(15).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(16).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(17).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(18).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(19).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(20).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(21).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(22).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(23).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(24).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(25).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(26).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(27).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(28).toString()), cell222));
      s.addCell(new Number(j++, x,Double.parseDouble(templist.get(29).toString()), cell222));
       
      
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

