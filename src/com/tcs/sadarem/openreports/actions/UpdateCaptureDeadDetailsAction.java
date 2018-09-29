package com.tcs.sadarem.openreports.actions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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
import org.bf.disability.common.DataBase.JNDIDataSource;

import com.lowagie.text.pdf.codec.Base64.OutputStream;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.openreports.DAO.UpdateCaptureDeadDetailsDAO;
import com.tcs.sadarem.openreports.DAO.UpdateCaptureDeadDetailsImpl;
import com.tcs.sadarem.util.CommonUtility;

public class UpdateCaptureDeadDetailsAction extends DispatchAction
{
	 
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
     {
        String target = "success";
        HttpSession sess = request.getSession(true);
    	ArrayList villagelist 			= new ArrayList();

        String logdistrict_id  = (String) sess.getAttribute("districtId");
    	String mandalid  				=  CommonUtility.checkNullObject((String) sess.getAttribute("mandalId"));

      
        ArrayList mandalList      = new ArrayList();
        CommonDAO comObj 		   = new CommonDAOImpl();
        
        mandalList			=(ArrayList) comObj.getMandalListByDistrictID(logdistrict_id);
    	villagelist			= (ArrayList)comObj.getVillageListByDistrictIDMandalID(logdistrict_id,mandalid);
    	
          // System.out.println("mandallist"+mandalList+logdistrict_id+mandalid);
    	
         request.setAttribute("mandalList", mandalList); 
       request.setAttribute("villagelist",villagelist);
       request.setAttribute("mandalid",mandalid);
       request.setAttribute("districtid",logdistrict_id);  
       
        
        return mapping.findForward(target);
    }
    public ActionForward getpwdsdata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
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
        	String distid  					= (String) sess.getAttribute("districtId");
        	String mandalid 				= request.getParameter("mandal");
        	String villageid  				= request.getParameter("village");
        	String habitationid 			= request.getParameter("habitation");
        	String sadaremid 			    = request.getParameter("sadaremid");
        	String roleid                   = CommonUtility.checkNullObj(sess.getAttribute("roleId"));  
        	UpdateCaptureDeadDetailsDAO obj = new UpdateCaptureDeadDetailsImpl();
        	CommonDAO comObj 		   = new CommonDAOImpl();
        	resultList  =(ArrayList)obj.getPWDdata(distid,mandalid,villageid,habitationid,sadaremid,roleid);
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
 
          request.setAttribute("resultList",resultList);
          request.setAttribute("mandalList",mandalList);
          request.setAttribute("villagelist",villagelist);
          request.setAttribute("habitationlist",habitationlist);
          request.setAttribute("districtid",distid);
          request.setAttribute("mandalid",mandalid);
          request.setAttribute("villageid",villageid);
          request.setAttribute("habitationid",habitationid);
          request.setAttribute("sadaremid",sadaremid);
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
			 	String distId    = CommonUtility.checkNullObj(request.getParameter("distid"));
			 	String mandalId  = CommonUtility.checkNullObj(request.getParameter("mandalid"));
			 	String villageId = CommonUtility.checkNullObj(request.getParameter("villageid"));
				String habid  = CommonUtility.checkNullObj(request.getParameter("habid"));
			 	String sadaremid    = CommonUtility.checkNullObj(request.getParameter("selectid"));
			
			 	String roleid                   = CommonUtility.checkNullObj(sess.getAttribute("roleId"));  
	        	UpdateCaptureDeadDetailsDAO obj = new UpdateCaptureDeadDetailsImpl();
	         
	        	resultList  =(ArrayList)obj.getDetailedLatestPWDdata(distId,mandalId,villageId,habid,sadaremid,roleid);

				 
					if(mandalId.equalsIgnoreCase("-1"))
					{
						mandalname ="ALL";
					}
					else
					{
						mandalname  =  comObj.getMandalName(distId,mandalId);
					}
				     
				
	            exportExcel(resultList,request, response, distId,mandalId,villageId,habid, sadaremid,districtName,mandalname);
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

	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "attachment;filename=PwDDataValidation.xls");
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
	                s.addCell(new Label(0, 0, "PwD Data Validation Report not available..."));
	            } 
	          else 
	            {
	        	  
	        	  s.mergeCells(0, 0, 2, 0);
	                s.addCell(new Label(0, 0, "Generated Date:"+currentdate+"", cell111));
	                
	                s.mergeCells(3, 0, 32, 0);
	                s.addCell(new Label(3, 0, " PwD Data Validation Report for "+districtName+" District  "+mandalname+" Mandal", cell111));
	            }
	            
	            s.mergeCells(0, 1, 32, 1);
                s.addCell(new Label(0, 1, "Note: NA specifies that the particular data is not available.", cell13));
	               
	                s.addCell(new Label(0, 2, "S.No.", cell111));
	                setWidth(s, 0, 10);
	                
	                s.addCell(new Label(1, 2, "SADAREM ID", cell111));
	                setWidth(s, 1, 20);
	                
	                s.addCell(new Label(2, 2, "Name", cell111));
	                setWidth(s, 2, 20);
	                
	                s.addCell(new Label(3, 2, "Relation Name", cell111));
	                setWidth(s, 3, 20);
	                
	                s.addCell(new Label(4, 2, "Age", cell111));
	                setWidth(s, 4, 20);
	                
	                s.addCell(new Label(5, 2, "District", cell111));
	                setWidth(s, 5, 20);
	                
	                s.addCell(new Label(6, 2, "Mandal", cell111));
	                setWidth(s, 6, 20);
	                
	                s.addCell(new Label(7, 2, "Village", cell111));
	                setWidth(s, 7, 20);
	                
	                s.addCell(new Label(8, 2, "Person Status", cell111));
	                setWidth(s, 8, 20);
	                
	                s.addCell(new Label(9, 2, "Death Date", cell111));
	                setWidth(s, 9, 20);
	                
	                s.addCell(new Label(10, 2, "Aadhar Status", cell111));
	                setWidth(s, 10, 20);
	                
	                 s.addCell(new Label(11, 2, "Aadhar Number", cell111));
	                setWidth(s, 11, 20);
	                
	                s.addCell(new Label(12, 2, "SHG Tagged Status", cell111));
	                setWidth(s, 12, 20);
	                
	                s.addCell(new Label(13, 2, "SHG Name", cell111));
	                setWidth(s, 13, 20);
	                
	                s.addCell(new Label(14, 2, "SHG Date", cell111));
	                setWidth(s, 14, 20);
	                
	                s.addCell(new Label(15, 2, "SHG Not tagged reason", cell111));
	                setWidth(s, 15, 20);
	                
	                s.addCell(new Label(16, 2, "Aids and appliances status", cell111));
	                setWidth(s, 16, 20);
	                
	                s.addCell(new Label(17, 2, "Appliances type", cell111));
	                setWidth(s, 17, 20);
	                
	                s.addCell(new Label(18, 2, "Appliances Organisation", cell111));
	                setWidth(s, 18, 20);
	                
	                s.addCell(new Label(19, 2, "Appliances date", cell111));
	                setWidth(s, 19, 20);
	                
	                s.addCell(new Label(20, 2, "Appliances reason", cell111));
	                setWidth(s, 20, 20);
	                
	                
	                s.addCell(new Label(21, 2, "Surgical correction", cell111));
	                setWidth(s, 21, 20);
	                
	                s.addCell(new Label(22, 2, "Surgical type", cell111));
	                setWidth(s, 22, 20);
	                
	                s.addCell(new Label(23, 2, "Surgical Organisation", cell111));
	                setWidth(s, 23, 20);
	                
	                s.addCell(new Label(24, 2, "Surgical date", cell111));
	                setWidth(s, 24, 20);
	                
	                s.addCell(new Label(25, 2, "Surgical reason", cell111));
	                setWidth(s, 25, 20);
	                
	                
	                s.addCell(new Label(26, 2, "Aasara Status", cell111));
	                setWidth(s, 26, 20);
	                
	                s.addCell(new Label(27, 2, "Aasara Remarks", cell111));
	                setWidth(s, 27, 20);
	                
	                
	                
	                 
	                s.addCell(new Label(28, 2, "PMJDY Status", cell111));
	                setWidth(s, 28, 20);
	                
	                s.addCell(new Label(29, 2, "PMJDY Account No.", cell111));
	                setWidth(s, 29, 20);
	                s.addCell(new Label(30, 2, "PMJDY Bank", cell111));
	                setWidth(s, 30, 20);
	                
	                s.addCell(new Label(31, 2, "PMJDY Branch", cell111));
	                setWidth(s, 31, 20);
	                
	                s.addCell(new Label(32, 2, "PMJDY IFSC Code", cell111));
	                setWidth(s, 32, 20);
	                
	                s.addCell(new Label(0, 3, "1", cell111));
	                setWidth(s, 0, 10);
	                
	                s.addCell(new Label(1, 3, "2",  cell111));
	                setWidth(s, 1, 20);
	                
	                s.addCell(new Label(2, 3, "3", cell111));
	                setWidth(s, 2, 20);
	                
	                s.addCell(new Label(3,3, "4",  cell111));
	                setWidth(s, 3, 20);
	                
	                s.addCell(new Label(4,3, "5",  cell111));
	                setWidth(s, 4, 20);
	                
	                s.addCell(new Label(5, 3, "6",  cell111));
	                setWidth(s, 5, 20);
	                
	                s.addCell(new Label(6,3, "7",  cell111));
	                setWidth(s, 6, 20);
	                
	                s.addCell(new Label(7, 3, "8",  cell111));
	                setWidth(s, 7, 20);
	                
	                s.addCell(new Label(8, 3, "9",  cell111));
	                setWidth(s, 8, 20);
	                
	                s.addCell(new Label(9, 3, "10",  cell111));
	                setWidth(s, 9, 20);
	                
	                s.addCell(new Label(10,3, "11",  cell111));
	                setWidth(s, 10, 20);
	                
	                 s.addCell(new Label(11, 3, "12",  cell111));
	                setWidth(s, 11, 20);
	                
	                s.addCell(new Label(12, 3, "13", cell111));
	                setWidth(s, 12, 20);
	                
	                s.addCell(new Label(13,  3, "14", cell111));
	                setWidth(s, 13, 20);
	                
	                s.addCell(new Label(14,   3, "15",  cell111));
	                setWidth(s, 14, 20);
	                
	                s.addCell(new Label(15,  3, "16",  cell111));
	                setWidth(s, 15, 20);
	                
	                s.addCell(new Label(16,  3, "17",  cell111));
	                setWidth(s, 16, 20);
	                
	                s.addCell(new Label(17, 3, "18",  cell111));
	                setWidth(s, 17, 20);
	                
	                s.addCell(new Label(18,  3, "19",  cell111));
	                setWidth(s, 18, 20);
	                
	                s.addCell(new Label(19, 3, "20", cell111));
	                setWidth(s, 19, 20);
	                
	                s.addCell(new Label(20,  3, "21", cell111));
	                setWidth(s, 20, 20);
	                
	                
	                s.addCell(new Label(21,  3, "22",  cell111));
	                setWidth(s, 21, 20);
	                
	                s.addCell(new Label(22,  3, "23",  cell111));
	                setWidth(s, 22, 20);
	                
	                s.addCell(new Label(23, 3, "24",  cell111));
	                setWidth(s, 23, 20);
	                
	                s.addCell(new Label(24, 3, "25", cell111));
	                setWidth(s, 24, 20);
	                
	                s.addCell(new Label(25, 3, "26",  cell111));
	                setWidth(s, 25, 20);
	                
	                
	                s.addCell(new Label(26, 3, "27",  cell111));
	                setWidth(s, 26, 20);
	                
	                s.addCell(new Label(27,  3, "28",  cell111));
	                setWidth(s, 27, 20);
	                
	                
	                
	                 
	                s.addCell(new Label(28,  3, "29",  cell111));
	                setWidth(s, 28, 20);
	                
	                s.addCell(new Label(29,  3, "30",  cell111));
	                setWidth(s, 29, 20);
	                s.addCell(new Label(30, 3, "31",  cell111));
	                setWidth(s, 30, 20);
	                
	                s.addCell(new Label(31,  3, "32",  cell111));
	                setWidth(s, 31, 20);
	                
	                s.addCell(new Label(32,  3, "33",  cell111));
	                setWidth(s, 32, 20);
	                
	                //System.out.println("resultList"+resultList);
	                
	            int x = 4;
	            int k = 0;
	           
	            ArrayList templist = new ArrayList(); 
	            for (int i = 0; i < resultList.size(); i++, x++) 
	            {
	            	 int j = 0;
	                k++;
	                templist = (ArrayList)resultList.get(i);
	                s.addCell(new Label(j++, x, i + 1 + "", cell));
	             
	                s.addCell(new Label(j++, x,templist.get(0).toString(), cell));
	                s.addCell(new Label(j++, x,templist.get(1).toString(), cellLeft));
	                s.addCell(new Label(j++, x,templist.get(2).toString(), cellLeft));
	                s.addCell(new Label(j++, x,templist.get(3).toString(), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(4)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(5)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(6)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(8)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(9)), cellLeft));
	                
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(10)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(11)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(12)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(13)), cellLeft));
	                
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(14)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(15)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(16)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(17)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(18)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(19)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(20)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(21)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(22)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(23)), cellLeft));
	                
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(24)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(25)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(26)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(27)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(28)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(29)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(30)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(31)), cellLeft));
	                s.addCell(new Label(j++, x,CommonUtility.checkNullObj(templist.get(32)), cellLeft));
	                
	                 

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
    
    public ActionForward loadpwdsdata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    {
    	 String target = "editScreen";
       
        try
        {
        	String msg="";
        	
        	ArrayList resultList 			= new ArrayList();
        	ArrayList shgReasonMaster = new ArrayList();
        	ArrayList organisanationMaster = new ArrayList();
        	ArrayList validateEligibleDetails = new ArrayList();
        	ArrayList tempList = new ArrayList();
        	tempList.add("Above poverty line");tempList.add("Above poverty line");
        	shgReasonMaster.add(tempList);
        	
        	tempList = new ArrayList();
        	tempList.add("Above 50 years");tempList.add("Above 50 years");
        	shgReasonMaster.add(tempList);
        	
        	tempList = new ArrayList();
        	tempList.add("Migrated");tempList.add("Migrated");
        	shgReasonMaster.add(tempList);
        	
        	tempList = new ArrayList();
        	tempList.add("No sufficient members to form");tempList.add("No sufficient members to form");
        	shgReasonMaster.add(tempList);
        	
        	tempList = new ArrayList();
        	tempList.add("Not interested");tempList.add("Not interested");
        	shgReasonMaster.add(tempList);
        	
        	tempList = new ArrayList();
        	tempList.add("Orphan");tempList.add("Orphan");
        	shgReasonMaster.add(tempList);
        	
  /*      	tempList = new ArrayList();
        	tempList.add("Any other reason");tempList.add("Any other reason");
        	shgReasonMaster.add(tempList);
        	*/
              tempList = new ArrayList();
        	tempList.add("Exist but not available in IB");tempList.add("Exist but not available in IB");
        	shgReasonMaster.add(tempList);
        	
        	
        	tempList = new ArrayList();
        	tempList.add("SERP");tempList.add("SERP");
        	organisanationMaster.add(tempList);
        	
        	tempList = new ArrayList();
        	tempList.add("Disability welfare department");tempList.add("Disability welfare department");
        	organisanationMaster.add(tempList);
        	
        	tempList = new ArrayList();
        	tempList.add("SSA");tempList.add("SSA");
        	organisanationMaster.add(tempList);
        	
        	tempList = new ArrayList();
        	tempList.add("NGO");tempList.add("NGO");
        	organisanationMaster.add(tempList);
        	
        	tempList = new ArrayList();
        	tempList.add("Others");tempList.add("Others");
        	organisanationMaster.add(tempList);
        	
        	HttpSession session = request.getSession();
        	ArrayList List = new ArrayList();
        	ArrayList RejectList = new ArrayList();
        	ArrayList BasicList = new ArrayList();
        	
        	String selId  					    = request.getParameter("selectid");
        	String selSadaremId 				= request.getParameter("transID"+selId);
        	String mandalid 				    = request.getParameter("mandal");

        //	System.out.println("selSadaremId"+selSadaremId);
        	UpdateCaptureDeadDetailsDAO obj = new UpdateCaptureDeadDetailsImpl();
        	
        	BasicList   =(ArrayList)obj.getBisicDetails(selSadaremId);
        	String oldOrNew="";
        	oldOrNew = obj.isOldOrNew(selSadaremId); 
        	request.setAttribute("isOldOrNew",oldOrNew);
        	
        	validateEligibleDetails  = (ArrayList)obj.getEligibleDetails(selSadaremId);
        	
        if(validateEligibleDetails.size()>0)	
        {	
        	List=(ArrayList) validateEligibleDetails.get(0);
        	
 	
        	if(((String)List.get(1)).trim().equals("Rejected"))
        	{
        		target = "RejectedScreen";
        		if(oldOrNew.equals("Y"))
            	{
        		RejectList = obj.getRejectDetails(selSadaremId);
            	}
        		else
        		{
        			RejectList = obj.getNewRejectDetails(selSadaremId);
        		}
        		request.setAttribute("RejectList",RejectList);
        		request.setAttribute("selSadaremId",selSadaremId);
        		request.setAttribute("BasicList",BasicList);
                request.setAttribute("mandalid",mandalid);

        		
        	}
        	else
        	{
        	 
        	if(oldOrNew.equals("Y"))
        	{
        	  resultList  =(ArrayList)obj.getSadaremPWDdata(selSadaremId);
        	}
        	else
        	{
        		resultList  =(ArrayList)obj.getNewSadaremPWDdata(selSadaremId);
        	}
        	System.out.println("resultList"+resultList); 
        	if(resultList.size()>0)
        	{
        		resultList = (ArrayList)resultList.get(0);
        		String aliveStatus = (String)resultList.get(4);
        		String selShgId;
        		 selShgId = (String)resultList.get(9);
        		  String distid,mandal_id,panchayatid;
        		  distid=selSadaremId.substring(0, 2);
        		  mandal_id=selSadaremId.substring(5, 7);
	   			  panchayatid=selSadaremId.substring(12, 14);
	   			  
        		if("Alive".equals(aliveStatus)){
        			
        			
        			int mapCount ; //= obj.getMappingCount(selSadaremId);
        			mapCount=0;
        			if(mapCount <1)
        			{
        			  String  selVoId;
        			  ArrayList voList = new ArrayList();
        			  ArrayList shgList = new ArrayList();
        			  
        			   voList = obj.getVoList(distid,mandal_id, panchayatid);
        			   selVoId = obj.getSelVoId(distid, selShgId);
        			  shgList = obj.getShgList(distid, selVoId);
        			  
        			  request.setAttribute("selShgId",selShgId);
        			  request.setAttribute("selVoId",selVoId);
        			  request.setAttribute("voList",voList); 
        			  request.setAttribute("shgList",shgList);
        			  request.setAttribute("MappedFlag", "N");
        			  
        			  
        			}
        			
        			else
        			{
        				ArrayList voList = new ArrayList();
        				voList = obj.getVoNameShgMappingDtls(selShgId, distid);
        				if(voList.size()>0){
        					voList = (ArrayList)voList.get(0);
        					request.setAttribute("selVoId", (String)voList.get(0));
        					request.setAttribute("selVoName", (String)voList.get(1));
        					 request.setAttribute("MappedFlag", "Y");
        				}
        			}
        		}
        		else
        		{
        			 
        			int mapCount ; //= obj.getMappingCount(selSadaremId);
        			mapCount=0;
        			if(mapCount <1)
        			{
        			  String  selVoId;
        			  ArrayList voList = new ArrayList();
        			  ArrayList shgList = new ArrayList();
        			  
        			   voList = obj.getVoList(distid,mandal_id, panchayatid);
        			   selVoId = obj.getSelVoId(distid, selShgId);
        			  shgList = obj.getShgList(distid, selVoId);
        			  
        			  request.setAttribute("selShgId",selShgId);
        			  request.setAttribute("selVoId",selVoId);
        			  request.setAttribute("voList",voList);
        			  request.setAttribute("shgList",shgList);
        			  request.setAttribute("MappedFlag", "N");
        			  
        			  
        			}
        			
        			else
        			{
        				ArrayList voList = new ArrayList();
        				voList = obj.getVoNameShgMappingDtls(selShgId, distid);
        				if(voList.size()>0){
        					voList = (ArrayList)voList.get(0);
        					request.setAttribute("selVoId", (String)voList.get(0));
        					request.setAttribute("selVoName", (String)voList.get(1));
        					 request.setAttribute("MappedFlag", "Y");
        				}
        			}
        		}
        		
        	}
        	
         
        }
        }
    	else
    	{
    		msg = "PwD updation is not allowed as Part B is not filled.";
    	} 
        request.setAttribute("resultList",resultList);
        request.setAttribute("organisanationMaster",organisanationMaster);
        request.setAttribute("reasonList",shgReasonMaster);
        request.setAttribute("selSadaremId",selSadaremId);
        request.setAttribute("msg",msg);
        request.setAttribute("BasicList",BasicList);
        request.setAttribute("mandalid",mandalid);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
    

	public ActionForward submitconfirmdtls(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    {
    	 String target = "success";
         HttpSession sess = request.getSession(true);

       
        try
        {
        	
        	String resultmsg  ="";
        	
        	String sadaremid 			    = request.getParameter("sadaremid");
        	String loginid					= (String)sess.getAttribute("loginid");
        	String mandalid 				= request.getParameter("mandal");
            String roleid                   = CommonUtility.checkNullObj(sess.getAttribute("roleId"));
        	
        	UpdateCaptureDeadDetailsDAO obj = new UpdateCaptureDeadDetailsImpl();
     
        	resultmsg  = obj.submitConfirmedDetails(sadaremid,loginid,roleid); 
        	
        	String logdistrict_id  = (String) sess.getAttribute("districtId");
            
            ArrayList mandalList      = new ArrayList();
            CommonDAO comObj 		   = new CommonDAOImpl();
            
            mandalList			=(ArrayList) comObj.getMandalListByDistrictID(logdistrict_id);
            
           request.setAttribute("mandalList", mandalList); 
           request.setAttribute("districtid",logdistrict_id); 
           request.setAttribute("mandalid",mandalid);
          request.setAttribute("resultmsg",resultmsg);
          request.setAttribute("sadaremid",sadaremid);
          

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
 
    
    public ActionForward updatepwdDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    
    {
    	String target = "success";
    	int status=0;
    	DataSource ds = null;
    	EditPwdDetailsActionForm editForm=(EditPwdDetailsActionForm)form;
    	 HttpSession sess = request.getSession(true);
    	 String msg="";
    	 ArrayList DetailsList = new ArrayList();
    	 int count=0;
    	 String logdistrict_id          = (String) sess.getAttribute("districtId");
     	 String mandalid 				= request.getParameter("mandal");

         
         ArrayList mandalList      = new ArrayList();
         CommonDAO comObj 		   = new CommonDAOImpl();
         
         mandalList			=(ArrayList) comObj.getMandalListByDistrictID(logdistrict_id);
        
    	 System.out.println((!(CommonUtility.checkNullObj(editForm.getShg())).equals("1")) +" dfsd"+ (CommonUtility.checkNullObj(editForm.getAlive())).equals("Dead"));
    	try
    	{
    		String sadaremid = request.getParameter("personcode");
    		
	    	String isOldOrNew = editForm.getIsOldOrNew();
	    	String loginid	= (String)sess.getAttribute("loginid");
	    	ds = getDataSource(request);
	        if (ds == null || "null".equals(ds)) {
	            ds = JNDIDataSource.getConnection();
	        }
	    	UpdateCaptureDeadDetailsDAO obj = new UpdateCaptureDeadDetailsImpl();
	    	
	    	if(!(CommonUtility.checkNullObj(editForm.getAids())).equals("Received"))
	    	{
	    		editForm.setAidsorganisanation("");
	    		editForm.setApplType("");
	    		editForm.setAidsdate("");
	    	}
	    	if(!(CommonUtility.checkNullObj(editForm.getSurgical()).equals("Done")))
	    	{
	    		editForm.setSurType("");
	    		editForm.setSurorganisanation("");
	    		editForm.setSurdate("");
	    	}
	    	
	    	if(!(CommonUtility.checkNullObj(editForm.getPmjdy())).equals("Covered"))
	    	{
	    	   editForm.setBank("");
	    	   editForm.setBank("");
	    	   editForm.setIfsc("");
	    	   editForm.setAccno("");
	    	}
	    	
	    	if( (!(CommonUtility.checkNullObj(editForm.getShg())).equals("1")))
	    	{
	    		editForm.setVo("");
	    		editForm.setShglist("");
	    		editForm.setShgdate("");
	    		editForm.setShgname("");
	    	}
	    	else  
	    	{
	    		editForm.setNotshgreason("");
	    	}
	    	if((CommonUtility.checkNullObj(editForm.getAlive())).equals("Dead"))
	    	{
	    		editForm.setNotshgreason("");
	    		editForm.setVo("");
	    		editForm.setShglist("");
	    		editForm.setShgdate("");
	    		editForm.setShgname("");
	    	}
	    	if(!(CommonUtility.checkNullObj(editForm.getExist()).equals("Exist")))
	    	{
	    		editForm.setAadharno("");
	    	}
	    	else
	    	{
	    	  count = obj.getAadharCount(sadaremid,editForm.getAadharno());
	    	} 
			
	    	if(count >0 )
	    	{
	    		 msg ="<font color='red'>"+ sadaremid+" Update failed as Aadhar ID already Exists</b></font>"; 
	    	}
	    	else
	    	{	
	    	
	    	           if(isOldOrNew.equals("Y"))
						{
						   status = obj.insertOldPwdDetails(ds, editForm,loginid)	;
						  }
						else
						{
							status = obj.updateOldPwdDetails(ds, editForm, loginid);
						}
						 if(status>0)
						   {
							 target = "confirmpage"; 
							 
							 msg="<font color='green'><b>"+sadaremid+" Details updated successfully</b></font>";
							  DetailsList = (ArrayList)obj.getReqSadaremDetails(sadaremid);  
							   
						   }
						   else
						   {
							   msg="<font color='red'><b>"+sadaremid+" Updation failed</b></font>"; 
						   }
	    	}
			 request.setAttribute("DetailsList",DetailsList);
			 request.setAttribute("resultmsg",msg); 
		     request.setAttribute("sadaremid",sadaremid);		
		     request.setAttribute("mandalList", mandalList); 
		     request.setAttribute("districtid",logdistrict_id); 
	        request.setAttribute("mandalid",mandalid);

    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return mapping.findForward(target);
    }
    
public ActionForward submitRejectedpwdDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    
    {
			 String target = "success";
		     HttpSession sess = request.getSession(true);
		 	DataSource ds = null;
		 	String logdistrict_id  = (String) sess.getAttribute("districtId");
		      
	        ArrayList mandalList      = new ArrayList();
	        CommonDAO comObj 		   = new CommonDAOImpl();
	        
	        mandalList			=(ArrayList) comObj.getMandalListByDistrictID(logdistrict_id);
	       
		    try
		    {
		    	
		    	String resultmsg  ="";
		    	ArrayList DetailsList =new ArrayList();
		    	String sadaremid 			    = request.getParameter("sadaremid");
		    	String aadharid 			    = request.getParameter("aadharno1");
		    	String serveid1					= request.getParameter("serveid1");
		      	String serveDoneBy1				= request.getParameter("serveDoneBy1");
		      	String designation1				= request.getParameter("designation1");
		      	String recivedDate1				= request.getParameter("recivedDate1");
		      	String oldOrNew					= request.getParameter("oldOrNew");
		      	String loginId 					= (String)sess.getAttribute("loginid");
		      	String aadharexist              = request.getParameter("exist");
	        	String mandalid 				    = request.getParameter("mandal");

		     	int count = 0;
		    	UpdateCaptureDeadDetailsDAO obj = new UpdateCaptureDeadDetailsImpl();
		    	
	    	 //	resultmsg  = obj.insertRejectedpwdDetails(ds,sadaremid,aadharid,serveid1,serveDoneBy1,designation1,recivedDate1,loginId);
		    	if(!(aadharexist.equals("Exist")))
		    	{
		    		aadharid = "";
		    	}
		    	else
		    	{
		    		count = obj.getAadharCount(sadaremid,aadharid);
		    	} 
				
		    	if(count >0 )
		    	{
		    		resultmsg ="<font color='red'>"+ sadaremid+" Update failed as Aadhar ID already Exists</b></font>"; 
		    	}
		    	else
		    	{
		    	        if(oldOrNew.equals("Y")) 
				    	{
				    		resultmsg  = obj.insertRejectedpwdDetails(ds,sadaremid,aadharid,serveid1,serveDoneBy1,designation1,recivedDate1,loginId,aadharexist);
			        	}
			        	else
			        	{
			        		resultmsg  = obj.updateRejectedpwdDetails(sadaremid,aadharid,serveid1,serveDoneBy1,designation1,recivedDate1,loginId,aadharexist);
			        	} 
				    
				    	 if(resultmsg.equals("Succesfully"))
				    	 {
				    		  resultmsg = "<font color='green'>"+ sadaremid+" Update Succesfully</b></font>"; 
				    		  DetailsList = (ArrayList)obj.getRejectedSadaremDetails(sadaremid); 
				    		  target = "rejectconfirmpage";
				    	 }
				    	 else
				    	 {
				    		 resultmsg ="<font color='red'>"+ sadaremid+" Update failed Try Again</b></font>"; 
				    	 }
		    	}
		    	  request.setAttribute("resultList",DetailsList);
		      request.setAttribute("resultmsg",resultmsg);
		      request.setAttribute("sadaremid",sadaremid);
		      request.setAttribute("mandalList", mandalList); 
		       request.setAttribute("districtid",logdistrict_id); 
		       request.setAttribute("mandalid",mandalid);
		
		    } catch (Exception e)
		    {
		        e.printStackTrace();
		    }
		    return mapping.findForward(target);
		    }  
}

