package com.tcs.sadarem.openreports.actions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.tcs.sadarem.openreports.DAO.ExpiredSadaremCertificatesDAO;
import com.tcs.sadarem.openreports.DAO.ExpiredSadaremCertificatesImpl;
import com.tcs.sadarem.util.CommonUtility;


public class DistrictWiseExpiredSadaremAction extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "districtwise";
       HttpSession sess = request.getSession(true);
		   	ArrayList villagelist 			= new ArrayList();
		   	ArrayList districtList 			= new ArrayList();
		   	ArrayList habitationList 			= new ArrayList();

       String logdistrict_id  = (String) sess.getAttribute("districtId");
   	String mandalid  				=  (String) sess.getAttribute("mandalId");
	String fromdate  = CommonUtility.checkNullObj(request.getParameter("startDate"));
 	String todate    = CommonUtility.checkNullObj(request.getParameter("endDate"));
	
    try 
    {
	
    	 if(fromdate.equals(""))
		 	{
				fromdate = CommonUtility.getDateAddOrSubDays(-366,"dd/MM/yyyy");
		 	}
			
			if(todate.equals(""))
		 	{
				todate = CommonUtility.getDateTime("dd/MM/yyyy"); //"04/01/2015";
				
		 	}
     
			       ArrayList mandalList      = new ArrayList();
			       CommonDAO comObj 		   = new CommonDAOImpl();

    
			       districtList =(ArrayList)comObj.getDistrictList();
			       mandalList			=(ArrayList) comObj.getMandalListByDistrictID(logdistrict_id);
			   	   villagelist			= (ArrayList)comObj.getVillageListByDistrictIDMandalID(logdistrict_id,mandalid);


		       request.setAttribute("districtList",districtList ); 
		       request.setAttribute("mandalid",mandalid);
		       request.setAttribute("districtid",logdistrict_id); 
		       request.setAttribute("SelFromDate", fromdate);
			 	request.setAttribute("SelToDate", todate);
    } catch (Exception e)
    {
        e.printStackTrace();
    }
       
       return mapping.findForward(target);
   }
	
	  public ActionForward getExpiredSadaremdata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	    {
	    	 String target = "districtwise";
	       
	        try
	        {
	        	String msg="";
	        	ArrayList resultList 			= new ArrayList();
	        	ArrayList mandalList           = new ArrayList();
	        	ArrayList villagelist 			= new ArrayList();
	        	ArrayList habitationlist 		= new ArrayList();
	        	HttpSession sess = request.getSession();
	        	String distid  					= (String) request.getParameter("district");
	        	String mandalid 				= request.getParameter("mandal");
	        	String villageid  				= request.getParameter("village");
	        	String habitationid 			= request.getParameter("habitation");
	        	String sadaremid 			    = request.getParameter("sadaremid");
	        	String fromdate  = CommonUtility.checkNullObj(request.getParameter("startDate"));
	         	String todate    = CommonUtility.checkNullObj(request.getParameter("endDate"));
	         	//System.out.println("district"+distid+""+mandalid);
	        	  
	        	ExpiredSadaremCertificatesDAO obj = new ExpiredSadaremCertificatesImpl();
	        	CommonDAO comObj 		   = new CommonDAOImpl();
	        	  
					
	        	resultList  =(ArrayList)obj.getExpireddistrictdata(fromdate,todate,distid,mandalid,villageid);
	        	//System.out.println("result"+resultList);
	        	if(resultList!=null && resultList.size()>0)
	        	{
	        		msg="";	
	        	}
	        	else
	        	{
	        		msg="No Record Found to Display";		
	        	}
			            mandalList			= (ArrayList)comObj.getMandalListByDistrictID(distid);
			        	villagelist			= (ArrayList)comObj.getVillageListByDistrictIDMandalID(distid,mandalid);
			        	habitationlist		= (ArrayList)comObj.getHabitationListByDistrictIDMandalIDvillageID(distid,mandalid,villageid);
	          request.setAttribute("districtList",(ArrayList)comObj.getDistrictList());
	          request.setAttribute("resultList",resultList);
	          request.setAttribute("mandalList",mandalList);
	          request.setAttribute("villagelist",villagelist);
	          request.setAttribute("habitationlist",habitationlist);
	          request.setAttribute("selDistrictID",distid);
	          request.setAttribute("selMandalID",mandalid);
	          request.setAttribute("villageid",villageid);
	          request.setAttribute("habitationid",habitationid);
	          request.setAttribute("sadaremid",sadaremid);
	          request.setAttribute("SelFromDate", fromdate);
			 	request.setAttribute("SelToDate", todate);
	          request.setAttribute("msg",msg);

	        } catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return mapping.findForward(target);
	    }
	  
	  public ActionForward excelNew(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)
	   {

	        try {
	        	
	        	
	        	ArrayList resultList 	= new ArrayList();
	        	
				String mandalname  = "";
				
				CommonDAO comObj 		= new CommonDAOImpl();
				HttpSession sess        = request.getSession();
				String districtName=CommonUtility.checkNullObj(sess.getAttribute("logDistName")); 
				String distid  					= (String) request.getParameter("district");
	        	String mandalid 				= request.getParameter("mandal");
	        	String villageid  				= request.getParameter("village");
	        	String habitationid 			= request.getParameter("habitation");
	        	String sadaremid 			    = request.getParameter("sadaremid");
	        	String fromdate  = CommonUtility.checkNullObj(request.getParameter("startDate"));
	         	String todate    = CommonUtility.checkNullObj(request.getParameter("endDate"));

			 	ExpiredSadaremCertificatesDAO obj = new ExpiredSadaremCertificatesImpl();
	        
			 	  if(fromdate.equals(""))
				 	{
						fromdate = CommonUtility.getDateAddOrSubDays(-366,"dd/MM/yyyy");
				 	}
					
					if(todate.equals(""))
				 	{
						todate = CommonUtility.getDateTime("dd/MM/yyyy"); //"04/01/2015";
						
				 	}
	        	resultList  =(ArrayList)obj.getExpireddistrictdata(fromdate,todate,distid,mandalid,villageid);
	        	if(distid.equalsIgnoreCase("-1"))
				{
					districtName ="ALL";
				}
				else
				{
					districtName  =  comObj.getDistrictName(distid);
				}
				 
					if(mandalid.equalsIgnoreCase("-1"))
					{
						mandalname ="ALL";
					}
					else
					{
						mandalname  =  comObj.getMandalName(distid,mandalid);
					}
				     
				
	            exportExcel(resultList,request, response, distid,mandalid,villageid,habitationid, sadaremid,districtName,mandalname);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static void exportExcel(ArrayList resultList,HttpServletRequest request, HttpServletResponse response,
	            String district, String mandal,String villageid,String habid,String sadaremid,String districtName,String mandalname)
	    {
	        		OutputStream out = null;

	        try {
	        	String distid  					= CommonUtility.checkNullObj(request.getParameter("district"));
	        	String mandalid 				= request.getParameter("mandal");
	        	//System.out.println("in excel"+distid);
	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "attachment;filename=AbstractReportOfExpiredSadaremCertis.xls");
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
	                s.addCell(new Label(0, 0, "Expired Sadarem Certificates Report not available..."));
	            } 
	          else 
	            {
	        	  
	        	  s.mergeCells(0, 0, 2, 0);
	                s.addCell(new Label(0, 0, "Generated Date:"+currentdate+"", cell111));
	                
	                s.mergeCells(0, 1, 2, 2);
	                s.addCell(new Label(0, 1, " Expired SADAREM Certificates Count for \n"+districtName+" District  ,"+mandalname+" Mandal", cell111));
	            }
	            
	          /*  s.mergeCells(0, 1, 13, 1);
               s.addCell(new Label(0, 1, "", cell13));*/
	               
	                s.addCell(new Label(0, 3, "S.No.", cell111));
	                setWidth(s, 0, 10);
	                
	                
  				 	if(distid.equals("-1"))
  				 	{
  				 	  s.addCell(new Label(1, 3, "District", cell111));
  	                setWidth(s, 1, 20);
  				 	}
  				 	else if(!distid.equals("-1") && mandalid.equals("-1"))
  				 	{
  				 	  s.addCell(new Label(1, 3, "Mandal", cell111));
  	                setWidth(s, 1, 20);
  				 	}
  				 	else if(!distid.equals("-1") && !mandalid.equals("-1"))
  				 	{
  				 	  s.addCell(new Label(1, 3, "Village", cell111));
  	                setWidth(s, 1, 20);
  				 	}
  				 	
	                
	                
	                s.addCell(new Label(2, 3, "No. of Expired\n Sadarem\n Certificates", cell111));
	                setWidth(s, 2, 20);
	                
	                
	                
	                
	               
	                
	            int x = 4;
	            int k = 0;
	           
	            ArrayList templist = new ArrayList(); 
	            for (int i = 0; i < resultList.size(); i++, x++) 
	            {
	            	 int j = 0;
	                k++;
	                templist = (ArrayList)resultList.get(i);
	                s.addCell(new Label(j++, x, i + 1 + "", cell));
	       if(templist.get(0).toString().equals("ZZTOTAL"))
	       {
	    	   s.addCell(new Label(j++, x,"Total", cell));  
	       }
	       else
	    	   {
	    	   s.addCell(new Label(j++, x,templist.get(0).toString(), cell));
	    	   }
	                s.addCell(new Label(j++, x,templist.get(1).toString(), cellLeft));
	                
	                

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
	
	
	public static void setWidth(WritableSheet s, int col, int widthInChars)
	{
	 s.setColumnView(col, widthInChars);
	}	
   
}

