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



public class IssueTrackingDashboardAction extends BaseDispatchAction 
{
  
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		 
		HashMap dashBoardInput = new HashMap();
		ArrayList dtls = new ArrayList();
		ArrayList districtList = new ArrayList();
		ArrayList rolesList = new ArrayList();
		IssueTrackingDashboardDAO obj= new IssueTrackingDashboardDAOImpl();
		CommonDAO comObj = new  CommonDAOImpl();
		String fromdate,todate,selDistrictId,role;
		districtList  = comObj.getDistrictList();
		rolesList     = obj.getApproverRolesList();
		String sesDistId="",sesRoleId="",disable="";
		HttpSession  session = request.getSession();
		fromdate      = CommonUtility.checkNullObj(request.getParameter("fromdate"));
		todate        = CommonUtility.checkNullObj(request.getParameter("todate"));
		
		selDistrictId = CommonUtility.checkNullObj(request.getParameter("district"));
		role          = CommonUtility.checkNullObj(request.getParameter("role"));		
		sesDistId     = CommonUtility.checkNullObj(session.getAttribute("districtId"));
		sesRoleId     = CommonUtility.checkNullObj(session.getAttribute("roleId"));
		
		if(fromdate.equals(""))
		{
		 fromdate = CommonUtility.getDateAddOrSubDays(-10,"yyyy-MM-dd");
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
		if(role.equals("-1"))
		{
			role="";
		}
		 
		if(!sesRoleId.equals(CommonConstants.SERPLOGIN_ROLEID))
		{
			selDistrictId = sesDistId;
		    disable       = "Y";
		}
		
		
		 dashBoardInput.put("fromdate",fromdate);
		 dashBoardInput.put("todate",todate);
		 dashBoardInput.put("distId",selDistrictId);
		 dashBoardInput.put("role",role);
		 
		 dtls = obj.getDashboardDetails(dashBoardInput);
		 
		 request.setAttribute("districtList", districtList);
		 request.setAttribute("rolesList", rolesList);
		 request.setAttribute("dataList", dtls);
		 request.setAttribute("selDist", selDistrictId);
		 request.setAttribute("role", role);
		 request.setAttribute("disable", disable);
		 request.setAttribute("fromdate", CommonUtility.getDateinFormat(fromdate, "yyyy-MM-dd", "dd/MM/yyyy"));
		 request.setAttribute("todate",CommonUtility.getDateinFormat(todate, "yyyy-MM-dd", "dd/MM/yyyy"));
		
		 
		 
		return mapping.findForward("dashboard");
	}
	public ActionForward excelNew(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		try
		{
			HashMap dashBoardInput = new HashMap();
			ArrayList dtls = new ArrayList();
			ArrayList districtList = new ArrayList();
			ArrayList rolesList = new ArrayList();
			IssueTrackingDashboardDAO obj= new IssueTrackingDashboardDAOImpl();
			CommonDAO comObj = new CommonDAOImpl();
			String fromdate,todate,selDistrictId,role;
			districtList  = comObj.getDistrictList();
			rolesList     = obj.getApproverRolesList();
			String sesDistId="",sesRoleId="",disable="";
			HttpSession  session = request.getSession();
			fromdate      = CommonUtility.checkNullObj(request.getParameter("fromdate"));
			todate        = CommonUtility.checkNullObj(request.getParameter("todate"));
			selDistrictId = CommonUtility.checkNullObj(request.getParameter("district"));
			role          = CommonUtility.checkNullObj(request.getParameter("role"));
			
			sesDistId     = CommonUtility.checkNullObj(session.getAttribute("districtId"));
			sesRoleId     = CommonUtility.checkNullObj(session.getAttribute("roleId"));
			
			if(fromdate.equals(""))
			{
			 fromdate = CommonUtility.getDateAddOrSubDays(-10,"yyyy-MM-dd");
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
			if(role.equals("-1"))
			{
				role="";
			}
			 
			if(!sesRoleId.equals(CommonConstants.SERPLOGIN_ROLEID))
			{
				selDistrictId = sesDistId;
			    disable       = "Y";
			}
			String Rolename=""; String districtName="";
			
			 dashBoardInput.put("fromdate",fromdate);
			 dashBoardInput.put("todate",todate);
			 dashBoardInput.put("distId",selDistrictId);
			 dashBoardInput.put("role",role);
			 
			 dtls = obj.getDashboardDetails(dashBoardInput);
			
			 if(role.equals("-1") || role.equals(""))
				{
				 Rolename="All";							 
				}
			 else
			    {
				 Rolename =comObj.getRoleName(Integer.parseInt(role));
			    }
			 
			 if( selDistrictId.equals("00") || selDistrictId.equals("") || selDistrictId.equals("-1"))
			 {
				 districtName="All";
			 }
			 else
			 {
				 districtName=comObj.getDistrictName(selDistrictId);
			 }
			
		
			 
			 
			  exportExcel(request, response,dtls,districtName,Rolename,fromdate,todate); 

        } catch (Exception e) {
            e.printStackTrace();
        }
		 return null;
	
	}
	 public static void exportExcel(HttpServletRequest request, HttpServletResponse response,ArrayList resultlist,String districtName,
			 String RoleName,String fromdate,String todate)
	 {
	     		OutputStream out = null;

	     try {
	    	 String  currentdate = CommonUtility.getDateTime("dd/MM/yyyy_hh_mm_ss"); 
	    		//System.out.println("enter excel");
	         response.setContentType("application/vnd.ms-excel");
	         response.setHeader("Content-Disposition", "attachment;filename=SADAREM_Dashboard_IssueTracking_"+currentdate+".xls");
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
	         
	         
	         WritableCellFormat cell1 = new WritableCellFormat();
	         cell1.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell1.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell1.setAlignment(Alignment.CENTRE);
	         cell1.setVerticalAlignment(VerticalAlignment.CENTRE);
	         cell1.setBackground(Colour.RED);
	         
	         WritableCellFormat cell2 = new WritableCellFormat();
	         cell2.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell2.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell2.setAlignment(Alignment.CENTRE);
	         cell2.setVerticalAlignment(VerticalAlignment.CENTRE);
	         cell2.setBackground(Colour.BLUE);

	         WritableCellFormat cellLeft1 = new WritableCellFormat();	       	        
	         cellLeft1.setBorder(Border.ALL, BorderLineStyle.THIN);	     
	         cellLeft1.setAlignment(Alignment.LEFT);
	         cellLeft1.setBackground(Colour.RED);
	         
	         WritableCellFormat cellLeft2 = new WritableCellFormat();	       	        
	         cellLeft2.setBorder(Border.ALL, BorderLineStyle.THIN);	     
	         cellLeft2.setAlignment(Alignment.LEFT);
	         cellLeft2.setBackground(Colour.BLUE);
	         
	         
	         
	         WritableCellFormat cell111 = new WritableCellFormat(bold);
	         cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell111.setAlignment(Alignment.CENTRE);
	         cell111.setBackground(Colour.GRAY_25);

	         cell111.setVerticalAlignment(VerticalAlignment.CENTRE);
	         
	         WritableCellFormat cell444 = new WritableCellFormat(bold);
	         cell444.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell444.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell444.setAlignment(Alignment.RIGHT);
	         cell444.setBackground(Colour.GRAY_25);

	         cell444.setVerticalAlignment(VerticalAlignment.CENTRE);
	         
	         WritableCellFormat cell222 = new WritableCellFormat(bold);
	         cell222.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell222.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell222.setAlignment(Alignment.RIGHT);	    
	         cell222.setVerticalAlignment(VerticalAlignment.CENTRE);
	         
	         WritableCellFormat cellred = new WritableCellFormat(bold);
	         cellred.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cellred.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cellred.setAlignment(Alignment.RIGHT);	    
	         cellred.setVerticalAlignment(VerticalAlignment.CENTRE);
	         cellred.setBackground(Colour.RED);
	         
	         WritableCellFormat cellblue = new WritableCellFormat(bold);
	         cellblue.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cellblue.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cellblue.setAlignment(Alignment.RIGHT);	    
	         cellblue.setVerticalAlignment(VerticalAlignment.CENTRE);
	         cellblue.setBackground(Colour.BLUE);
	         
	         
	         WritableCellFormat cell12 = new WritableCellFormat(bold);
	         cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell12.setAlignment(Alignment.CENTRE);
	         cell12.setBackground(Colour.BLUE);
	         
	         
	         

	         WritableCellFormat cell13 = new WritableCellFormat(bold);
	         cell13.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell13.setBorder(Border.ALL, BorderLineStyle.THIN);
	         cell13.setAlignment(Alignment.CENTRE);
	         cell13.setBackground(Colour.RED);
	         
	         WritableCellFormat integerstyle = new WritableCellFormat(NumberFormats.INTEGER);
	        //Number num1=new Number(2,1,2.121212,integerstyle);
	         integerstyle.setAlignment(Alignment.RIGHT);
	         integerstyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	         integerstyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	         int page = 1;
	         WritableSheet s = w.createSheet("Sheet" + page, page - 1);

	       
	        
	    

	         if (resultlist.size() == 0) 
	         {
	             s.addCell(new Label(0, 0, "SADAREM Issue Tracking Dashboard ................"));
	         } 
	       else 
	         {
	     	  
	     	  s.mergeCells(0, 0, 2, 0);
	             s.addCell(new Label(0, 0, "Generated Date:"+currentdate1+"", cell111));
	     
	             
	            	  s.mergeCells(3, 0, 8, 0);
	                  s.addCell(new Label(3, 0, "SADAREM ISSUE TRACKING DASHBOARD", cell111)); 
	             
	         }
	         
	       
	      
	        	 
	         
	        	 s.mergeCells(0, 1, 8, 1);
	             s.addCell(new Label(0, 1,"From :"+fromdate+"--To :"+todate+",District:"+districtName+",Role Name:"+RoleName+".",cell111)); 
	             
	             
	             s.mergeCells(0, 2, 2, 2);
	             s.addCell(new Label(0, 2,"Indicates pending percentage > 75",cell13)); 
	             
	             s.mergeCells(3, 2, 5, 2);
	             s.addCell(new Label(3, 2,"Indicates pending percentage between 50 and 75",cell12)); 
	             
	             s.mergeCells(6, 2, 8, 2);
	             s.addCell(new Label(6, 2,"",cell111)); 
	             
	             
	             s.mergeCells(0, 3, 8, 3);
	             s.addCell(new Label(0, 3,"",cell111)); 
	             
	  


		   s.mergeCells(0, 4, 0, 5);
	       s.addCell(new Label(0, 4, "S.No", cell111));
	       setWidth(s, 0, 10);
	       

			s.mergeCells(1, 4, 1, 5);
			s.addCell(new Label(1, 4, "Issue Type", cell111));
			setWidth(s, 1, 30);
					           

	   
	          s.mergeCells(2, 4, 2, 5);
	          s.addCell(new Label(2, 4, "Total", cell111));
	          setWidth(s, 1, 30);
	          
	          
	          s.mergeCells(3, 4, 4, 4);
	          s.addCell(new Label(3, 4, "Pending", cell111));
	          setWidth(s, 1, 30);
	          
	          s.mergeCells(3, 5, 3, 5);
	          s.addCell(new Label(3, 5, "Count", cell111));
	          setWidth(s, 1, 30);
	          
	          s.mergeCells(4, 5, 4, 5);
	          s.addCell(new Label(4, 5, "%", cell111));
	          setWidth(s, 1, 30);
	          
	          s.mergeCells(5, 4, 6, 4);
	          s.addCell(new Label(5, 4, "Approved", cell111));
	          setWidth(s, 1, 30);
	          
	          s.mergeCells(5, 5, 5, 5);
	          s.addCell(new Label(5, 5, "Count", cell111));
	          setWidth(s, 1, 30);
	          
	          s.mergeCells(6, 5, 6, 5);
	          s.addCell(new Label(6, 5, "%", cell111));
	          setWidth(s, 1, 30);
	          
	          s.mergeCells(7, 4, 8, 4);
	          s.addCell(new Label(7, 4, "Rejected", cell111));
	          setWidth(s, 1, 30);
	          
	          s.mergeCells(7, 5, 7, 5);
	          s.addCell(new Label(7, 5, "Count", cell111));
	          setWidth(s, 1, 30);
	          
	          s.mergeCells(8, 5, 8, 5);
	          s.addCell(new Label(8, 5, "%", cell111));
	          setWidth(s, 1, 30);
	          
	         
	       
	   
	       
	       s.addCell(new Label(0, 6, "1", cell111));
	       setWidth(s, 4, 30); 
	       s.addCell(new Label(1, 6, "2", cell111));
	       setWidth(s, 4, 30); 
	       s.addCell(new Label(2, 6, "3", cell111));
	       setWidth(s, 4, 30); 
	       s.addCell(new Label(3, 6, "4", cell111));
	       setWidth(s, 4, 30); 
	       s.addCell(new Label(4, 6, "5", cell111));
	       setWidth(s, 4, 30); 
	       s.addCell(new Label(5, 6, "6", cell111));
	       setWidth(s, 4, 30);
	       s.addCell(new Label(6, 6, "7", cell111));
	       setWidth(s, 4, 30);
	       s.addCell(new Label(7, 6, "8", cell111));
	       setWidth(s, 4, 30);
	       s.addCell(new Label(8, 6, "9", cell111));
	       setWidth(s, 4, 30);
	  


	         int x = 7;
	         int k = 0;
	        
	         ArrayList templist = new ArrayList(); 
	         for (int i = 0; i < resultlist.size(); i++, x++) 
	         {
	        	 int j = 0;
	             k++;
	             templist = (ArrayList)resultlist.get(i);
	        	 
	        
	             if(Integer.parseInt(templist.get(3).toString()) >75)
			   	 {
	            	 if(i==resultlist.size()-1)
	            	 {
	            	 s.addCell(new Label(j++, x, "", cell1));
	            	 }
	            	 else
	            	 {
	            		 s.addCell(new Label(j++, x, i + 1 + "", cell1)); 
	            	 }
			   	 }
    			 else if(Integer.parseInt(templist.get(3).toString()) >=50 && Integer.parseInt(templist.get(3).toString()) <75)
			   	 {
    				 if(i==resultlist.size()-1)
	            	 {
	            	 s.addCell(new Label(j++, x, "", cell2));
	            	 }
	            	 else
	            	 {
	            		 s.addCell(new Label(j++, x, i + 1 + "", cell2)); 
	            	 }
    				
			   	 }
    			 else
	             {
    				 if(i==resultlist.size()-1)
	            	 {
	            	 s.addCell(new Label(j++, x, "", cell));
	            	 }
	            	 else
	            	 {
	            		 s.addCell(new Label(j++, x, i + 1 + "", cell)); 
	            	 }    
	             }
	             
	             
	             if(templist.get(0).toString().equalsIgnoreCase("ZGrand Total"))
	             {
	            	 
	             s.addCell(new Label( j++, x ,"TOTAL", cell111));
	            	 
	            	 
	             }
	             else
	             {
	                 if(Integer.parseInt(templist.get(3).toString()) >75)
				   	 {
	                	  s.addCell(new Label(j++, x,templist.get(0).toString(), cellLeft1));
				   	 }
	    			 else if(Integer.parseInt(templist.get(3).toString()) >=50 && Integer.parseInt(templist.get(3).toString()) <75)
				   	 {
	    				  s.addCell(new Label(j++, x,templist.get(0).toString(), cellLeft2));
				   	 }
	    			 else
		             {
	    				  s.addCell(new Label(j++, x,templist.get(0).toString(), cellLeft));	    
		             }
		             
	             	
	             }	            	          	     
	             
	             
	             
	             
	             if(Integer.parseInt(templist.get(3).toString()) >75)
			   	 {
	            	    s.addCell(new Label(j++, x,templist.get(1).toString(), cellred));
	   	             s.addCell(new Label(j++, x,templist.get(2).toString(), cellred));
	   	             s.addCell(new Label(j++, x,templist.get(3).toString(), cellred));
	   	             s.addCell(new Label(j++, x,templist.get(4).toString(), cellred));
	   	             s.addCell(new Label(j++, x,templist.get(5).toString(), cellred));
	   	             s.addCell(new Label(j++, x,templist.get(6).toString(), cellred));
	   	             s.addCell(new Label(j++, x,templist.get(7).toString(), cellred));
	        		
			   	 }
    			 else if(Integer.parseInt(templist.get(3).toString()) >=50 && Integer.parseInt(templist.get(3).toString()) <75)
			   	 {
    				    s.addCell(new Label(j++, x,templist.get(1).toString(), cellblue));
    		             s.addCell(new Label(j++, x,templist.get(2).toString(), cellblue));
    		             s.addCell(new Label(j++, x,templist.get(3).toString(), cellblue));
    		             s.addCell(new Label(j++, x,templist.get(4).toString(), cellblue));
    		             s.addCell(new Label(j++, x,templist.get(5).toString(), cellblue));
    		             s.addCell(new Label(j++, x,templist.get(6).toString(), cellblue));
    		             s.addCell(new Label(j++, x,templist.get(7).toString(), cellblue));
	        		
			   	 } 
    			 else
    			 {
    		
	             s.addCell(new Label(j++, x,templist.get(1).toString(), cell222));
	             s.addCell(new Label(j++, x,templist.get(2).toString(), cell222));
	             s.addCell(new Label(j++, x,templist.get(3).toString(), cell222));
	             s.addCell(new Label(j++, x,templist.get(4).toString(), cell222));
	             s.addCell(new Label(j++, x,templist.get(5).toString(), cell222));
	             s.addCell(new Label(j++, x,templist.get(6).toString(), cell222));
	             s.addCell(new Label(j++, x,templist.get(7).toString(), cell222));
	           
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
