
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
import com.tcs.sadarem.reports.DAO.SadaremNotHavingAadharDAO;
import com.tcs.sadarem.reports.DAOImpl.CommonReportsDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.DirectorReportOutsideDAOImpl;
import com.tcs.sadarem.reports.DAOImpl.SadaremNotHavingAadharDAOImpl;
import com.tcs.sadarem.util.CommonUtility;


/**
 *
 * @author Administrator
 */
public class SadaremNotTaggedToAadhar extends DispatchAction {

    private String target = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException 
            { 
    	
    
	    	CommonReportsDAO comObj1 = new CommonReportsDAOImpl();
	       	ArrayList districtList = new ArrayList(); 
	    	ArrayList disabilityList = new ArrayList();
	    	
	      	districtList = comObj1.getDistrictList();		 	
	      	disabilityList = (ArrayList)comObj1.getDisabilityList();
	  
	      	request.setAttribute("districtList", districtList);
	      	request.setAttribute("disabilityList", disabilityList);
	      	
    	
    	 
    	return mapping.findForward(target);
            }
    public ActionForward getData(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)
            throws SADAREMException, SQLException 
    {
    	
    	String target = "success";
	       
        try
        {
        	CommonReportsDAO obj=new CommonReportsDAOImpl();
    	   String msg="";
    	
	       String district1=CommonUtility.checkNullObj(request.getParameter("district1"));
	       String mandal=CommonUtility.checkNullObj(request.getParameter("mandal"));
	       String village=CommonUtility.checkNullObj(request.getParameter("village"));
	       String Trigpsel = CommonUtility.checkNullObj(request.getParameter("Trigpsel"));
	       //System.out.println("Trigpsel"+Trigpsel);
	       String areatype1=CommonUtility.checkNullObj(request.getParameter("areatype1"));
	   	CommonReportsDAO comObj = new CommonReportsDAOImpl();
	       
	      ArrayList resultset=new ArrayList();
	      SadaremNotHavingAadharDAO comObj1 = new SadaremNotHavingAadharDAOImpl();
	      //System.out.println("HI ---------------------------- "+district1+"HI ---------------------------- "+mandal+village+areatype1+Trigpsel);
	      resultset=comObj1.getData(district1,mandal,village,areatype1,Trigpsel);
	    
	  	String distname=obj.getDistrictName(district1);
	  	String mandalname=obj.getMandalName(district1,mandal);
	  	String villagename=obj.getVillageName(district1,mandal,village);
	  	ArrayList districtList = (ArrayList)comObj.getDistrictListbyAreatype(areatype1);
	  	ArrayList mandalList = (ArrayList)comObj.getMandalListbyDistrictIdAreatype(district1,areatype1);
		ArrayList villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalIDAreaWise(district1,mandal,areatype1);
	  	
	  	 request.setAttribute("resultList",resultset);
	  	 request.setAttribute("districtList",districtList);
	  	 request.setAttribute("mandalList",mandalList);
	  	 request.setAttribute("villageList",villageList);
	  	 
	  	request.setAttribute("district",district1);
	  	request.setAttribute("mandal",mandal);
	  	request.setAttribute("village",village);
	  	request.setAttribute("Trigpsel",Trigpsel);
	  
		request.setAttribute("areatype1",areatype1);
	  	
	 	String sadaremnothavingheadingmsg= comObj1.sadaremnothavingheadingmsg(distname,mandalname,villagename,district1,mandal,village,areatype1,Trigpsel);
	  	
	  	request.setAttribute("sadaremnothavingheadingmsg",sadaremnothavingheadingmsg);
	  	 
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
	      
	  	       String district=CommonUtility.checkNullObj(request.getParameter("district"));
	  	       String mandal=CommonUtility.checkNullObj(request.getParameter("mandal"));
	  	       String village=CommonUtility.checkNullObj(request.getParameter("village"));
	  	       String Trigpsel = CommonUtility.checkNullObj(request.getParameter("Trigpsel"));
	  	      String heading=CommonUtility.checkNullObj(request.getParameter("heading"));
	  	    String areatype=CommonUtility.checkNullObj(request.getParameter("areatype"));
	  	      heading= heading.replaceAll("\\<.*?\\>", "");
	  	       System.out.println("Trigpsel   Trigpsel\n"+Trigpsel);
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
			if(Trigpsel.equals(""))
		 	{
				Trigpsel  =  "-1";
		 	}
			
		  	   ArrayList resultset=new ArrayList();
		  	   ArrayList resultset1=new ArrayList();
		  	 SadaremNotHavingAadharDAO comObj1 = new SadaremNotHavingAadharDAOImpl();
	  	       
	  	 	String flag="DistrictWise";
	  	 	String cflag="CampWise";
	  	 	
	  	      resultset=comObj1.getData(district,mandal,village,areatype,Trigpsel);
	  	      
	  	   
	  	    exportExcel(resultset,request, response, district,mandal,village,heading);
	  		
			    
	  		//    System.out.println("uhfoes"+resultset1);
			    
				  
					
				
		
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
    public static void exportExcel(ArrayList resultList,HttpServletRequest request, HttpServletResponse response,
            String district, String mandal,String villageid,String headingmsg)
    {
        		OutputStream out = null;

        try {
       		//System.out.println("enter excel");
        	 String  currentdate = CommonUtility.getDateTime("dd/MM/yyyy_hh_mm_ss");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=R_1.3_SadaremIDNotHavingAadharReport"+currentdate+".xls");
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
            
            WritableCellFormat cell222 = new WritableCellFormat(bold);
            cell222.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell222.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell222.setAlignment(Alignment.LEFT);
          

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
      
            integerstyle.setAlignment(Alignment.RIGHT);
            integerstyle.setBorder(Border.ALL, BorderLineStyle.THIN);
            integerstyle.setBorder(Border.ALL, BorderLineStyle.THIN);
            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);

            String  currentdate1 = CommonUtility.getDateTime("dd/MM/yyyy hh:mm:ss"); 
           
       

            if (resultList.size() == 0) 
            {
                s.addCell(new Label(0, 0, "SADAREMID Not Having Aadhar Card No.s Report................"));
            } 
          else 
            {
        	  
        	  s.mergeCells(0, 0, 2, 0);
                s.addCell(new Label(0, 0, "Generated Date:"+currentdate1+"", cell111));
        
              	            
                s.mergeCells(3, 0, 17, 0);
                s.addCell(new Label(3, 0, " "+"SADAREM ID's not having Aadhar no's Report"+"  "+headingmsg, cell13));
                
              
            }
            
         	
                s.mergeCells(0, 1, 17, 1);
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
   	            s.addCell(new Label(12, 2, "Disability", cell111));
   	            setWidth(s, 2, 25);
   	            
   	            s.mergeCells(13, 2, 13, 2);
   	            s.addCell(new Label(13, 2, "Disability\nPercentage", cell111));
   	            setWidth(s, 2, 25);
   	            
   	            s.mergeCells(14, 2, 14, 2);
   	            s.addCell(new Label(14, 2, "Certificate \nStatus", cell111));
   	            setWidth(s, 2, 25);
   	            
   	           
   	            
   	            s.mergeCells(15, 2, 15, 2);
   	            s.addCell(new Label(15, 2, "Certificate \nType", cell111));
   	            setWidth(s, 2, 25);
   	            
   	            s.mergeCells(16, 2, 16, 2);
   	            s.addCell(new Label(16, 2, "Certificate \nIssue Date", cell111));
   	            setWidth(s, 2, 25);
   	            
   	            s.mergeCells(17, 2, 17, 2);
   	            s.addCell(new Label(17, 2, "Certificate \nExpiry Date", cell111));
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