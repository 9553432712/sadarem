package com.tcs.sadarem.openreports.actions;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lowagie.text.pdf.codec.Base64.OutputStream;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.openreports.DAO.OpenReportsDAO;
import com.tcs.sadarem.openreports.DAO.OpenReportsDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class OpenReorts extends DispatchAction
{

	  public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	  {
		  String target="";
		 try
		    {
				 ArrayList districtList = new ArrayList();
				 ArrayList mandalList 	= new ArrayList();
				 ArrayList resultList 	= new ArrayList();
				
			
				CommonDAO comObj 		= new CommonDAOImpl();
				OpenReportsDAO repObj  = new OpenReportsDAOImpl();
					

			 	String distId    = CommonUtility.checkNullObj(request.getParameter("district"));
			 	String mandalId  = CommonUtility.checkNullObj(request.getParameter("mandal"));
			 	String villageId = CommonUtility.checkNullObj(request.getParameter("village"));
				String fromdate  = CommonUtility.checkNullObj(request.getParameter("startDate"));
			 	String todate    = CommonUtility.checkNullObj(request.getParameter("endDate"));
			
			 	//System.out.println("distId--"+distId);
			 	
			 	
				
				if(distId.equals(""))
			 	{
					distId  =  "-1";
			 	}
				if(mandalId.equals(""))
			 	{
					mandalId  =  "-1";
			 	}
				if(villageId.equals(""))
			 	{
					villageId  =  "-1";
			 	}
			 
			    if(fromdate.equals(""))
			 	{
					fromdate = CommonUtility.getDateAddOrSubDays(-10,"dd/MM/yyyy");
			 	}
				
				if(todate.equals(""))
			 	{
					todate = CommonUtility.getDateTime("dd/MM/yyyy"); //"04/01/2015";
					
			 	}
				
				 resultList = (ArrayList)repObj.getpwdvalAbstReport(fromdate, todate, distId, mandalId, villageId);
				 
	
				districtList = (ArrayList) comObj.getDistrictList();
				mandalList 	 = (ArrayList) comObj.getMandalListByDistrictID(distId);
			
				
			 	request.setAttribute("SelFromDate", fromdate);
			 	request.setAttribute("SelToDate", todate);
			 	
			 	request.setAttribute("selDistrictID", distId);
			 	request.setAttribute("selMandalID", mandalId);
			 	
		        request.setAttribute("districtList", districtList);    
		        request.setAttribute("mandalList", mandalList); 
			 	request.setAttribute("resultList", resultList);
			 	
			 	
			 	
			 	target="success";
		    }
		 	catch(Exception e)
		 	{
		 		e.printStackTrace();
		 		target= "failure";
		 	}
		
		
		return mapping.findForward(target);
	}

 public ActionForward excelNew(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)
	   {

	        try {
	        	
	        	
	        	ArrayList resultList 	= new ArrayList();
	        	String districtName="";
				String mandalname  = "";
				
				CommonDAO comObj 		= new CommonDAOImpl();
				OpenReportsDAO repObj  = new OpenReportsDAOImpl();
					

			 	String distId    = CommonUtility.checkNullObj(request.getParameter("distid"));
			 	String mandalId  = CommonUtility.checkNullObj(request.getParameter("mandalid"));
			 	String villageId = CommonUtility.checkNullObj(request.getParameter("village"));
				String fromdate  = CommonUtility.checkNullObj(request.getParameter("fromdate"));
			 	String todate    = CommonUtility.checkNullObj(request.getParameter("todate"));
			
			 	//System.out.println("distId--"+distId);
			 	
			 	
				
				if(distId.equals(""))
			 	{
					distId  =  "-1";
			 	}
				if(mandalId.equals(""))
			 	{
					mandalId  =  "-1";
			 	}
				if(villageId.equals(""))
			 	{
					villageId  =  "-1";
			 	}
			 
			    if(fromdate.equals(""))
			 	{
					fromdate = CommonUtility.getDateAddOrSubDays(-10,"dd/MM/yyyy");
			 	}
				
				if(todate.equals(""))
			 	{
					todate = CommonUtility.getDateTime("dd/MM/yyyy"); //"04/01/2015";
					
			 	}
				 resultList = (ArrayList)repObj.getpwdvalAbstReport(fromdate, todate, distId, mandalId, villageId);

				 if(distId.equalsIgnoreCase("-1"))
					{
						districtName ="ALL";
					}
					else
					{
						districtName  =  comObj.getDistrictName(distId);
					}
					if(mandalId.equalsIgnoreCase("-1"))
					{
						mandalname ="ALL";
					}
					else
					{
						mandalname  =  comObj.getMandalName(distId,mandalId);
					}
				     
				
	            exportExcel(resultList,request, response, distId,mandalId,villageId,fromdate, todate,districtName,mandalname);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static void exportExcel(ArrayList resultList,HttpServletRequest request, HttpServletResponse response,
	            String district, String mandal,String villageid,String fromdate,String todate,String districtName,String mandalname)
	    {
	        		OutputStream out = null;

	        try {

	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "attachment; PwD Data Validation Report.xls");
	            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
	            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

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
	            
	            
	            int page = 1;
	            WritableSheet s = w.createSheet("Sheet" + page, page - 1);

	           String  currentdate = CommonUtility.getDateTime("dd/MM/yyyy"); 
	           
	       

	            if (resultList.size() == 0) 
	            {
	                s.addCell(new Label(0, 0, "R1.6 PwD Data Validation Abstract Report are not Available................"));
	            } 
	          else 
	            {
	        	  
	        	  s.mergeCells(0, 0, 2, 0);
	                s.addCell(new Label(0, 0, "Generated Date:"+currentdate+"", cell111));
	                
	                s.mergeCells(3, 0, 27, 0);
	                s.addCell(new Label(3, 0, " "+districtName+" District ,"+mandalname+" Mandal R1.6:PwD Data Validation Abstract Report From "+fromdate+" To "+todate+"  ", cell13));
	            }
	            
	                s.mergeCells(0, 2, 0, 4);
	                s.addCell(new Label(0, 2, "S.No", cell111));
	                setWidth(s, 0, 10);
	                
	            if (district == null || district.equalsIgnoreCase("-1")) 
	              {
	            	     s.mergeCells(1, 2, 1, 4);
	                    s.addCell(new Label(1, 2, "District", cell111));
	                    setWidth(s, 1, 30);
	              }
	            else if (mandal!= null && !mandal.equalsIgnoreCase("-1")) 
	              {
	            	  s.mergeCells(1, 2, 1, 4);
	                    s.addCell(new Label(1, 2, "Village", cell111));
	                    setWidth(s, 1, 30);
	              }
	            else if (district != null && !district.equalsIgnoreCase("-1")) 
	              {
	            	  s.mergeCells(1, 2, 1, 4);
	                    s.addCell(new Label(1, 2, "Mandal", cell111));
	                    setWidth(s, 1, 30);
	              }
	            
	            
	               s.mergeCells(2, 2, 2, 4);
	                s.addCell(new Label(2, 2, "Total Assessed", cell111));
	                setWidth(s, 2, 30);
	               
	                s.mergeCells(3, 2, 3, 4);
	                s.addCell(new Label(3, 2, "PwD Validation Done", cell111));
	                setWidth(s, 3, 30);
	               
	                s.mergeCells(4, 2, 7, 2);
	                s.addCell(new Label(4, 2, "AADHAR Not Tagged", cell111));
	                setWidth(s, 4, 30);
	                
	                s.mergeCells(4, 3, 4, 4);
	                s.addCell(new Label(4, 3, "Total", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(5, 3, 5, 4);
	                s.addCell(new Label(5, 3, "Alive", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(6, 3, 6, 4);
	                s.addCell(new Label(6, 3, "Dead", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(7, 3, 7, 4);
	                s.addCell(new Label(7, 3, "Unclassified", cell111));
	                setWidth(s, 4, 30);
	               
	                
	                s.mergeCells(8, 2, 10, 2);
	                s.addCell(new Label(8, 2, "PWD", cell111));
	                setWidth(s, 4, 30);
	                
	                s.mergeCells(8, 3, 8, 4);
	                s.addCell(new Label(8, 3, "Alive", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(9, 3, 9, 4);
	                s.addCell(new Label(9, 3, "Dead", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(10, 3, 10, 4);
	                s.addCell(new Label(10, 3, "Unclassified", cell111));
	                setWidth(s, 4, 30);
	                
	                s.mergeCells(11, 2, 21, 2);
	                s.addCell(new Label(11, 2, "SHG Group", cell111));
	                setWidth(s, 4, 30);
	                
	                s.mergeCells(11, 3, 11, 4);
	                s.addCell(new Label(11, 3, "Tagged", cell111));
	                setWidth(s, 4, 30);
	             
	               
	                s.mergeCells(12, 3, 21, 3);
	                s.addCell(new Label(12, 3, "Not Tagged", cell111));
	                setWidth(s, 4, 30);
	                
	                s.mergeCells(12, 4, 12, 4);
	                s.addCell(new Label(12, 4, "Total Rejected", cell111));
	                setWidth(s, 4, 30);
	                
	                s.mergeCells(13, 4, 13, 4);
	                s.addCell(new Label(13, 4, "Total Eligible", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(14, 4, 14, 4);
	                s.addCell(new Label(14, 4, "Migrated", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(15, 4, 15, 4);
	                s.addCell(new Label(15, 4, "Above 50 years", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(16, 4, 16, 4);
	                s.addCell(new Label(16, 4, "Above Poverty Line", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(17, 4, 17, 4);
	                s.addCell(new Label(17, 4, "Any other Reason", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(18, 4, 18, 4);
	                s.addCell(new Label(18, 4, "No sufficient members to form", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(19, 4, 19, 4);
	                s.addCell(new Label(19, 4, "Not interested", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(20, 4, 20, 4);
	                s.addCell(new Label(20, 4, "Orphan", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(21, 4, 21, 4);
	                s.addCell(new Label(21, 4, "Unclassified", cell111));
	                setWidth(s, 4, 30);
	               
	                
	                
	                
	                
	                
	                s.mergeCells(22, 2, 27, 2);
	                s.addCell(new Label(22, 2, "AASARA", cell111));
	                setWidth(s, 4, 30);
	                
	                s.mergeCells(22, 3, 24, 3);
	                s.addCell(new Label(22, 3, "Not Covered", cell111));
	                setWidth(s, 4, 30);
	                
	                s.mergeCells(22, 4, 22, 4);
	                s.addCell(new Label(22, 4, "Total", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(23, 4, 23, 4);
	                s.addCell(new Label(23, 4, "Alive ", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(24, 4, 24, 4);
	                s.addCell(new Label(24, 4, "Dead", cell111));
	                setWidth(s, 4, 30);
	                
	                s.mergeCells(25, 3, 27, 3);
	                s.addCell(new Label(25, 3, "Covered", cell111));
	                setWidth(s, 4, 30);
	                
	                s.mergeCells(25, 4, 25, 4);
	                s.addCell(new Label(25, 4, "Total", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(26, 4, 26, 4);
	                s.addCell(new Label(26, 4, "Alive ", cell111));
	                setWidth(s, 4, 30);
	                s.mergeCells(27, 4,27, 4);
	                s.addCell(new Label(27, 4, "Dead", cell111));
	                setWidth(s, 4, 30);
	                
	                s.addCell(new Label(0, 5, "1", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(1, 5, "2", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(2, 5, "3", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(3, 5, "4", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(4, 5, "5", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(5, 5, "6", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(6, 5, "7", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(7, 5, "8", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(8, 5, "9", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(9, 5, "10", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(10,5, "11", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(11,5, "12", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(12,5, "13", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(13,5, "14", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(14,5, "15", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(15,5, "16", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(16,5, "17", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(17,5, "18", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(18,5, "19", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(19,5, "20", cell111));
	                setWidth(s, 4, 30); 
	                s.addCell(new Label(20,5, "21", cell111));
	                setWidth(s, 4, 30);
	                s.addCell(new Label(21,5, "22", cell111));
	                setWidth(s, 4, 30);
	                s.addCell(new Label(22,5, "23", cell111));
	                setWidth(s, 4, 30);
	                s.addCell(new Label(23,5, "24", cell111));
	                setWidth(s, 4, 30);
	                s.addCell(new Label(24,5, "25", cell111));
	                setWidth(s, 4, 30);
	                s.addCell(new Label(25,5, "26", cell111));
	                setWidth(s, 4, 30);
	                s.addCell(new Label(26,5, "27", cell111));
	                setWidth(s, 4, 30);
	                s.addCell(new Label(27,5, "28", cell111));
	                setWidth(s, 4, 30);
	               
	                
	            int x = 6;
	            int k = 0;
	           
	            ArrayList templist = new ArrayList(); 
	            for (int i = 0; i < resultList.size()-1; i++, x++) 
	            {
	            	 int j = 0;
	                k++;
	                templist = (ArrayList)resultList.get(i);
	                s.addCell(new Label(j++, x, i + 1 + "", cell));
	       
	                s.addCell(new Label(j++, x,templist.get(0).toString(), cellLeft));
	                s.addCell(new Label(j++, x,templist.get(1).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(2).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(3).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(4).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(5).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(6).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(7).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(8).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(9).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(10).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(11).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(12).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(13).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(14).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(15).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(16).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(17).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(18).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(19).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(20).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(21).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(22).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(23).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(24).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(25).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(26).toString(), cell));

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
	            if(templist.get(0).toString().equalsIgnoreCase("ZZTOTAL"))
	            {
	            s.addCell(new Label(j++, x,"TOTAL", cell111));
	            }
	            else
	            {
	            	  s.addCell(new Label(j++, x,templist.get(0).toString(), cell111));
	            }
                s.addCell(new Label(j++, x,templist.get(1).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(2).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(3).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(4).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(5).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(6).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(7).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(8).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(9).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(10).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(11).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(12).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(13).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(14).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(15).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(16).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(17).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(18).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(19).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(20).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(21).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(22).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(23).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(24).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(25).toString(), cell111));
                s.addCell(new Label(j++, x,templist.get(26).toString(), cell111));

	            
	            
	            
	            
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
