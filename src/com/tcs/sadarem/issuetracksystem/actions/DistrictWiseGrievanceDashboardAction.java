

package com.tcs.sadarem.issuetracksystem.actions;



import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;

import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.tcs.sadare.issuetracksystem.DAO.DistrictWiseGrievanceDashboardDAO;
import com.tcs.sadare.issuetracksystem.DAO.DistrictWiseGrievanceDashboardDAOImpl;
import com.tcs.sadare.issuetracksystem.DAO.IssueTrackingDashboardDAO;
import com.tcs.sadare.issuetracksystem.DAO.IssueTrackingDashboardDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

import jxl.Workbook;

import java.io.IOException;
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



public class DistrictWiseGrievanceDashboardAction extends BaseDispatchAction 
{
  
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		 
		HashMap dashBoardInput = new HashMap();
		ArrayList dtls = new ArrayList();
		ArrayList typeList = new ArrayList();

		DistrictWiseGrievanceDashboardDAO obj= new DistrictWiseGrievanceDashboardDAOImpl();
		CommonDAO comObj = new  CommonDAOImpl();
		String fromdate,todate;

		fromdate      = CommonUtility.checkNullObj(request.getParameter("fromdate"));
		todate        = CommonUtility.checkNullObj(request.getParameter("todate"));
		

		
		if(fromdate.equals(""))
		{
		 fromdate = CommonUtility.getDateAddOrSubDays(-60,"yyyy-MM-dd");
		}
		else
		{
		fromdate = CommonUtility.getDateinFormat(fromdate, "dd/MM/yyyy", "yyyy-MM-dd");
		}
		if(todate.equals(""))
		{
		 todate = CommonUtility.getDateTime("yyyy-MM-dd"); //"04/01/2015";
		} 
		else
		{
			todate = CommonUtility.getDateinFormat(todate, "dd/MM/yyyy", "yyyy-MM-dd");
		}
	

		
		
		 dashBoardInput.put("fromdate",fromdate);
		 dashBoardInput.put("todate",todate);
     //     System.out.println("fromdate"+fromdate+"todate"+todate);
		 dtls = obj.getDashboardDetails(dashBoardInput); 
		 typeList = obj.gettypeList();
		 
		 request.setAttribute("typeList", typeList);
		 request.setAttribute("dataList", dtls);

		 request.setAttribute("fromdate", CommonUtility.getDateinFormat(fromdate, "yyyy-MM-dd", "dd/MM/yyyy"));
		 request.setAttribute("todate",CommonUtility.getDateinFormat(todate, "yyyy-MM-dd", "dd/MM/yyyy"));
		
		 
		 
		return mapping.findForward("districtdashboard");
	}
	public ActionForward excelNew(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		try
		{
			HashMap dashBoardInput = new HashMap();
			ArrayList dtls = new ArrayList();
			ArrayList typeList = new ArrayList();
			ArrayList rolesList = new ArrayList();
			DistrictWiseGrievanceDashboardDAO obj= new DistrictWiseGrievanceDashboardDAOImpl();
			CommonDAO comObj = new CommonDAOImpl();
			String fromdate,todate;

			fromdate      = CommonUtility.checkNullObj(request.getParameter("fromdate"));
			todate        = CommonUtility.checkNullObj(request.getParameter("todate"));
			
			if(fromdate.equals(""))
			{
			 fromdate = CommonUtility.getDateAddOrSubDays(-60,"yyyy-MM-dd");
			}
			else
			{
			fromdate = CommonUtility.getDateinFormat(fromdate, "dd/MM/yyyy", "yyyy-MM-dd");
			}
			if(todate.equals(""))
			{
			 todate = CommonUtility.getDateTime("yyyy-MM-dd"); //"04/01/2015";
			} 
			else
			{
				todate = CommonUtility.getDateinFormat(todate, "dd/MM/yyyy", "yyyy-MM-dd");
			}
			
			 typeList = obj.gettypeList();
			 
        //	 System.out.println("length----------"+typeList.size()*3+3);
			 dashBoardInput.put("fromdate",fromdate);
			 dashBoardInput.put("todate",todate);

			 
			 dtls = obj.getDashboardDetails(dashBoardInput);
			 
			  exportExcel(request, response,dtls,fromdate,todate,typeList); 

        } catch (Exception e) {
            e.printStackTrace();
        }
		 return null;
	
	}
	 public static void exportExcel(HttpServletRequest request, HttpServletResponse response,ArrayList resultlist,
			 String fromdate,String todate,ArrayList typeList)
	 {
	     		OutputStream out = null;

	     try {
	    	 String  currentdate = CommonUtility.getDateTime("dd/MM/yyyy_hh_mm_ss"); 
	    		//System.out.println("enter excel");
	         response.setContentType("application/vnd.ms-excel");
	         response.setHeader("Content-Disposition", "attachment;filename=SADAREM_Dist_Wise_Grievance_Dashboard"+currentdate+".xls");
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
	         

	         
	         WritableCellFormat cell222 = new WritableCellFormat();
	         cell222.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell222.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell222.setAlignment(Alignment.RIGHT);	    
	         cell222.setVerticalAlignment(VerticalAlignment.CENTRE);
	         
	         WritableCellFormat cell12 = new WritableCellFormat();
	         cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell12.setAlignment(Alignment.CENTRE);
	         

	         int page = 1;
	         WritableSheet s = w.createSheet("Sheet" + page, page - 1);

	       
	        
	    

	         if (resultlist.size() == 0) 
	         {
	             s.addCell(new Label(0, 0, "SADAREM Issue Tracking District wise Dashboard ................"));
	         } 
	       else 
	         {
	     	  
	     	  s.mergeCells(0, 0, 2, 0);
	             s.addCell(new Label(0, 0, "Generated Date:"+currentdate1+"", cell111));
	     
	             
	            	  s.mergeCells(3, 0, ((typeList.size())*3)+4, 0);
	                  s.addCell(new Label(3, 0, "SADAREM ISSUE TRACKING DISTRICT WISE DASHBOARD", cell111)); 
	             
	         }
	         
	       
	      
	        	 
	         
	        	 s.mergeCells(0, 1, ((typeList.size())*3)+4, 1);
	             s.addCell(new Label(0, 1,"From :"+fromdate+"--To :"+todate+".",cell111)); 
	             
	             
	        
	             
	             
	             s.mergeCells(0, 2, ((typeList.size())*3)+4, 2);
	             s.addCell(new Label(0, 2,"",cell111)); 
	             
	  


		   s.mergeCells(0, 3, 0, 4);
	       s.addCell(new Label(0, 3, "S.No", cell111));
	       setWidth(s, 0, 10);
	       

			s.mergeCells(1, 3, 1, 4);
			s.addCell(new Label(1, 3, "District", cell111));
			setWidth(s, 1, 30);
					           
    if(typeList!=null || typeList.size()>0)
    {
    	int i=0,g=2;
    
	 for(i=0;i<typeList.size();i++)
	 {
		  s.mergeCells(g, 3, g+2, 3);
          s.addCell(new Label(g, 3, (String) ((ArrayList)typeList.get(i)).get(0), cell111));
          setWidth(s, 1, 30);
            
          s.mergeCells(g, 4, g, 4);
          s.addCell(new Label(g, 4, "Pending", cell111));
          setWidth(s, 1, 30);
          
         g++;
          s.mergeCells(g, 4, g, 4);
          s.addCell(new Label(g, 4, "Approved", cell111));
          setWidth(s, 1, 30);
          
          g++;
          s.mergeCells(g, 4, g, 4);
          s.addCell(new Label(g, 4, "Rejected", cell111));
          setWidth(s, 1, 30);
          g++;
          
	 }
	 
	 
	 
	  s.mergeCells(g, 3, g+2, 3);
      s.addCell(new Label(g, 3, "Total", cell111));
      setWidth(s, 1, 30);
     
      s.mergeCells(g, 4, g, 4);
      s.addCell(new Label(g, 4, "Pending", cell111));
      setWidth(s, 1, 30);
      
     g++; 
      s.mergeCells(g, 4, g, 4);
      s.addCell(new Label(g, 4, "Approved", cell111));
      setWidth(s, 1, 30);
      
      g++;
      s.mergeCells(g, 4, g, 4);
      s.addCell(new Label(g, 4, "Rejected", cell111));
      setWidth(s, 1, 30);
      
    

    }     
	       
	   
	       
	       s.addCell(new Label(0, 5, "1", cell111));
	       setWidth(s, 4, 30); 
	       
	       s.addCell(new Label(1, 5, "2", cell111));
	       setWidth(s, 4, 30); 
	       
	       
	  if(typeList!=null || typeList.size()>0)
	     {
	       	int i=0,k=2;
			int g=3;
	       
	   	 for(i=0;i<typeList.size();i++)
	   	 {     
	       
	       s.addCell(new Label(k, 5 , g+"", cell111));
	       setWidth(s, 4, 30); 
	       g++;k++;
	       s.addCell(new Label(k, 5, g+"", cell111));
	       setWidth(s, 4, 30);
	       g++;k++;
	       s.addCell(new Label(k, 5, g+"", cell111));
	       setWidth(s, 4, 30); 
	       g++;k++;
	   	 }
	   	 
	   	   
	       s.addCell(new Label(k, 5 , g+"", cell111));
	       setWidth(s, 4, 30); 
	       g++;k++;
	       s.addCell(new Label(k, 5, g+"", cell111));
	       setWidth(s, 4, 30);
	       g++;k++;
	       s.addCell(new Label(k, 5, g+"", cell111));
	       setWidth(s, 4, 30); 
	       
	 	 }

	         int x = 6;
	         int k = 0;
	        
	         ArrayList templist = new ArrayList(); 
	         for (int i = 0; i < resultlist.size(); i++, x++) 
	         {
	        	 int j = 0;
	             k++;
	             templist = (ArrayList)resultlist.get(i);
	            		 
	            		 if(i==resultlist.size()-1 || i==resultlist.size()-2)
		            	 {
		            	 s.addCell(new Label(j++, x, "", cell12));
		            	 }
		            	 else
		            	 {
		            		 s.addCell(new Label(j++, x, i + 1 + "", cell12)); 
		            	 }    
    		
	             
	             if(templist.get(1).toString().equalsIgnoreCase("ZMisc"))
	             {
	            	 
	             s.addCell(new Label( j++, x ,"Miscellaneous", cell12));
	            	             	 
	             }
	             else if(templist.get(1).toString().equalsIgnoreCase("ZZtotal"))
	             {	 
	            	 
	                	  s.addCell(new Label(j++, x,"Total", cell111));
	             }	            	          	     
	             else
	             {
	             	  s.addCell(new Label(j++, x,templist.get(1).toString(), cell12));
	             }
	   
	  if(i==resultlist.size()-1)
	    {	             
	       	  if(typeList!=null || typeList.size()>0)
	 	     {
	 	       	int m=2,n=0;
	 	   	 for(n=0;n<typeList.size();n++)
	 	   	 {        			
    		
	             s.addCell(new Label(j++, x,templist.get(m).toString(), cell111));
	             m++;
	             s.addCell(new Label(j++, x,templist.get(m).toString(), cell111));
	             m++;
	             s.addCell(new Label(j++, x,templist.get(m).toString(), cell111));	    		
	             m++;
	 	   	 }
	 	   	     
	 	         s.addCell(new Label(j++, x,templist.get(m).toString(), cell111));
	 	         m++;
                 s.addCell(new Label(j++, x,templist.get(m).toString(), cell111));
                 m++;
                 s.addCell(new Label(j++, x,templist.get(m).toString(), cell111));

	 	     }
	            
	    }     
	  else{
		  
		  
		  if(typeList!=null || typeList.size()>0)
	 	     {
	 	       	int m=2,n=0;
	 	   	 for(n=0;n<typeList.size();n++)
	 	   	 {        			
 		
	             s.addCell(new Label(j++, x,templist.get(m).toString(), cell222));
	             m++;
	             s.addCell(new Label(j++, x,templist.get(m).toString(), cell222));
	             m++;
	             s.addCell(new Label(j++, x,templist.get(m).toString(), cell222));	    		
	             m++;
	 	   	 }
	 	   	     
	 	         s.addCell(new Label(j++, x,templist.get(m).toString(), cell222));
	 	         m++;
              s.addCell(new Label(j++, x,templist.get(m).toString(), cell222));
              m++;
              s.addCell(new Label(j++, x,templist.get(m).toString(), cell222));

	 	     }
		  
	  }
	        
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
