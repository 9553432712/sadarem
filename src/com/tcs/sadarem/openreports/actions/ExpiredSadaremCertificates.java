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


public class ExpiredSadaremCertificates extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "success";
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
	    	 String target = "success";
	       
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
	        	  
					
	        	resultList  =(ArrayList)obj.getExpireddata(fromdate,todate,distid,mandalid,villageid);
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
	        	resultList  =(ArrayList)obj.getExpireddata(fromdate,todate,distid,mandalid,villageid);
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
	            response.setHeader("Content-Disposition", "attachment;filename=ExpiredSadaremCertis.xls");
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
	                
	                s.mergeCells(3, 0, 15, 0);
	                s.addCell(new Label(3, 0, " Expired SADAREM Certificates Report for "+districtName+" District  "+mandalname+" Mandal", cell111));
	            }
	            
	            s.mergeCells(0, 1, 15, 1);
               s.addCell(new Label(0, 1, "", cell13));
	               
	                s.addCell(new Label(0, 2, "S.No.", cell111));
	                setWidth(s, 0, 10);
	                
	            	  s.addCell(new Label(1, 2, "District", cell111));
  	                setWidth(s, 1, 20);
  				 	
  				 	s.addCell(new Label(2, 2, "Mandal", cell111));
	                setWidth(s, 2, 20);
	                s.addCell(new Label(3, 2, "Village", cell111));
	                setWidth(s, 3, 20);
	                
	                
	                s.addCell(new Label(4, 2, "Person Id", cell111));
	                setWidth(s, 4, 20);
	                
	                s.addCell(new Label(5, 2, "SADAREM ID", cell111));
	                setWidth(s, 5, 20);
	                
	                s.addCell(new Label(6, 2, "Name", cell111));
	                setWidth(s, 6, 20);
	                
	                s.addCell(new Label(7, 2, "Relation Name", cell111));
	                setWidth(s, 7, 20);
	                
	                s.addCell(new Label(8, 2, "Gender", cell111));
	                setWidth(s, 8, 20);
	                
	                s.addCell(new Label(9, 2, "Age", cell111));
	                setWidth(s, 9, 20);
	                
	                s.addCell(new Label(10, 2, "Proof Id", cell111));
	                setWidth(s, 10, 20);
	                
	                s.addCell(new Label(11, 2, "Proof Type", cell111));
	                setWidth(s, 11, 20);
	                
	                s.addCell(new Label(12, 2, "Type Of Disability", cell111));
	                setWidth(s, 12, 20);
	                
	                s.addCell(new Label(13, 2, "House Number", cell111));
	                setWidth(s, 13, 20);
	                
	                s.addCell(new Label(14, 2, "Issued Date", cell111));
	                setWidth(s, 14, 20);
	                
	                s.addCell(new Label(15, 2, "Expired Date", cell111));
	                setWidth(s, 15, 20);
	               
	                
	            int x = 3;
	            int k = 0;
	           
	            ArrayList templist = new ArrayList(); 
	            for (int i = 0; i < resultList.size(); i++, x++) 
	            {
	            	 int j = 0;
	                k++;
	                templist = (ArrayList)resultList.get(i);
	                s.addCell(new Label(j++, x, i + 1 + "", cell));
	       
	                s.addCell(new Label(j++, x,templist.get(0).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(1).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(2).toString(), cell));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(3)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(4)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(5)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(6)), cellLeft));
	               
	                if(CommonUtility.checkNullObj(templist.get(7)).equals("")||CommonUtility.checkNullObj(templist.get(7)).equals(" ")||CommonUtility.checkNullObj(templist.get(7)).equals("null"))
	                { 
	                s.addCell(new Label(j++, x,"-", cellLeft));
	                }else
	                {
	                	s.addCell(new Label(j++, x,templist.get(7).toString(), cellLeft));
	                }
	                if(CommonUtility.checkNullObj(templist.get(8)).equals("")||CommonUtility.checkNullObj(templist.get(8)).equals(" ")||CommonUtility.checkNullObj(templist.get(8)).equals("null"))
	                { 
	                s.addCell(new Label(j++, x,"-", cellLeft));
	                }else
	                {
	                	s.addCell(new Label(j++, x,templist.get(8).toString(), cellLeft));
	                }
	                if(CommonUtility.checkNullObj(templist.get(9)).equals("")||CommonUtility.checkNullObj(templist.get(9)).equals(" ")||CommonUtility.checkNullObj(templist.get(9)).equals("null"))
	                { 
	                s.addCell(new Label(j++, x,"-", cellLeft));
	                }else
	                {
	                	s.addCell(new Label(j++, x,templist.get(9).toString(), cellLeft));
	                }
	               
	                if(CommonUtility.checkNullObj(templist.get(10)).equals("")||CommonUtility.checkNullObj(templist.get(10)).equals(" ")||CommonUtility.checkNullObj(templist.get(10)).equals("null"))
	                { 
	                s.addCell(new Label(j++, x,"-", cellLeft));
	                
	                }else
	                {
	                	s.addCell(new Label(j++, x,templist.get(10).toString(), cellLeft));
	                }
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(11)), cellLeft));
	                if(CommonUtility.checkNullObj(templist.get(12)).equals("")||CommonUtility.checkNullObj(templist.get(12)).equals(" ")||CommonUtility.checkNullObj(templist.get(12)).equals("null"))
	                { 
	                s.addCell(new Label(j++, x,"-", cellLeft));
	                
	                }else
	                {
	                	s.addCell(new Label(j++, x,templist.get(12).toString(), cellLeft));
	                }
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(13)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(14)), cellLeft));
	                

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
