package com.tcs.sadarem.Appellate.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadarem.Appellate.DAO.AppellateAuthorityDAO;
import com.tcs.sadarem.Appellate.DAO.AppellateAuthorityDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class AppellateStatusTrackerAction extends BaseDispatchAction 
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String targetPage = "success"; 
		HttpSession session = request.getSession();	  
        String sesUserId	= CommonUtility.checkNullObj(session.getAttribute("loginid"));
     
        try
        {    
        	 if(!sesUserId.equals(""))
	    	 { 
        			AppellateAuthorityDAO obj = new AppellateAuthorityDAOImpl(); 
				    request.setAttribute("reassessList", obj.getAppellateStatusTrackerReport());	 
	    	 }
        	 else
        	 {
	    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
	    	   targetPage="exception";
	         }
         } 
         catch (Exception e) 
         {
            targetPage="exception";
     	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
     	   e.printStackTrace();
         }    	 
		return mapping.findForward(targetPage);
	}
	
	public ActionForward exportExcel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String title = "APPELLATE AUTHORITY AT SPMU STATUS REPORT";
		ArrayList PendList = new ArrayList();
		ArrayList HeaderList = new ArrayList();
		
				
		try
		{
			AppellateAuthorityDAO obj = new AppellateAuthorityDAOImpl();			
			PendList = obj.getAppellateStatusTrackerReport();
			HeaderList.add("District");
			HeaderList.add("Total Applications (Online Grievance)");
			HeaderList.add("Total");
			HeaderList.add("Doubtful Case");
			HeaderList.add("Third time Reassessment");
			
			HeaderList.add("Requests raised");
			HeaderList.add("Approved");
			HeaderList.add("Assessed");
			HeaderList.add("Pending");
			HeaderList.add("Holded");
			
			HeaderList.add("Assessed");
			HeaderList.add("Eligible");
			HeaderList.add("Rejected");
			
			HeaderList.add("Total");
			HeaderList.add("Eligible");
			HeaderList.add("Rejected");
			
			HeaderList.add("Total");
			HeaderList.add("Eligible");
			HeaderList.add("Rejected");

	    	exportExcel(HeaderList, PendList , request,  response,  title);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return null;		  
	}
	public void exportExcel(ArrayList HeaderList ,ArrayList resultList ,HttpServletRequest request, HttpServletResponse response, String title)
	{

 		OutputStream out = null;
		 try 
		 {
			 String  currentdate = CommonUtility.getDateTime("dd/MM/yyyy_hh_mm_ss"); 
				//System.out.println("enter excel");
		     response.setContentType("application/pms.ms-excel");
		     response.setHeader("Content-Disposition", "attachment;filename="+title+"_"+currentdate+".xls");
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
		     
		     WritableCellFormat cell12 = new WritableCellFormat(bold);
		     cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
		     cell12.setBorder(Border.ALL, BorderLineStyle.THIN);
		     cell12.setAlignment(Alignment.CENTRE);
		     cell12.setVerticalAlignment(VerticalAlignment.CENTRE);		     
		     
		
		     WritableCellFormat cell13 = new WritableCellFormat(bold);
		     cell13.setBorder(Border.ALL, BorderLineStyle.THIN);
		     cell13.setBorder(Border.ALL, BorderLineStyle.THIN);
		     cell13.setAlignment(Alignment.LEFT);
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
		         s.addCell(new Label(0, 0, title));
		     } 
		     else 
		     {		         
		        s.mergeCells(0, 0, HeaderList.size(), 0);//Fromcol,fromrow,tocol,torow
		        s.addCell(new Label(0, 0, title, cell12));
		     }		     
		     //s.addCell(new Label(0, 1,"S.No.",cell12));		     
		     s.mergeCells(0, 1, 0, 2);
		     s.addCell(new Label(0, 1,"S.No.",cell12)   );
		     
		     
		     
		     s.mergeCells(1, 1, 1, 2);
		     s.addCell(new Label(1, 1,HeaderList.get(0).toString(),cell13)   );
		     
		     s.mergeCells(2, 1, 6, 1);
		     s.addCell(new Label(2, 1,HeaderList.get(1).toString(),cell13)   );
		     
		     s.mergeCells(7, 1, 9, 1);
		     s.addCell(new Label(7, 1,HeaderList.get(2).toString(),cell13)   );
		     
		     s.mergeCells(10, 1, 12, 1);
		     s.addCell(new Label(10, 1,HeaderList.get(3).toString(),cell13)   );
		     
		     s.mergeCells(13, 1, 15, 1);
		     s.addCell(new Label(13, 1,HeaderList.get(4).toString(),cell13)   );
			 
		     
		     s.addCell(new Label(2, 2,HeaderList.get(5).toString(),cell13)  );
		     s.addCell(new Label(3, 2,HeaderList.get(6).toString(),cell13)  );
		     s.addCell(new Label(4, 2,HeaderList.get(7).toString(),cell13)  );
		     s.addCell(new Label(5, 2,HeaderList.get(8).toString(),cell13)  );
		     s.addCell(new Label(6, 2,HeaderList.get(9).toString(),cell13)  );
		     s.addCell(new Label(7, 2,HeaderList.get(10).toString(),cell13)  );
		     s.addCell(new Label(8, 2,HeaderList.get(11).toString(),cell13)  );
		     s.addCell(new Label(9, 2,HeaderList.get(12).toString(),cell13)  );
		     s.addCell(new Label(10, 2,HeaderList.get(13).toString(),cell13)  );
		     s.addCell(new Label(11, 2,HeaderList.get(14).toString(),cell13)  );
		     s.addCell(new Label(12, 2,HeaderList.get(15).toString(),cell13)  );
		     s.addCell(new Label(13, 2,HeaderList.get(16).toString(),cell13)  );
		     s.addCell(new Label(14, 2,HeaderList.get(17).toString(),cell13)  );
		     s.addCell(new Label(15, 2,HeaderList.get(18).toString(),cell13)  );
				    
		     ArrayList templist = new ArrayList();
		     ArrayList templist1 = new ArrayList();
		     for (int row = 3; row < resultList.size()+3; row++) 
		     {
		    	 templist = (ArrayList)resultList.get(row-3);		    	 
		    	 
		    	 for(int col=0,fcol=0;col<HeaderList.size()-4;col++,fcol++)
		    	 {
		    		 if(fcol==0)
		    		 {
		    			 s.addCell(new Label(0, row,row-2+"",cell13));
		    		 }
		    		 
		    		 s.addCell(new Label(col+1, row,templist.get(col+1).toString(),cell13));
		    		 
		    	 }	         
		     }	     
		     w.write();
		     w.close();
		
		 } 
		 catch (Exception e)
		 {
		     e.printStackTrace();
		 } 
		 finally
		 {
		     if (out != null)
		     {
		         try 
		         {
		             out.close();
		         } 
		         catch (IOException ex) 
		         {
		             ex.printStackTrace();
		         }
		     }
		 }

	}
}
