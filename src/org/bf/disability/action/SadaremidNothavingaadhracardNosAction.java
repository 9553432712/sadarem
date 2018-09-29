/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;


import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

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
import jxl.write.WriteException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ReportDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.form.SadaremidNothavingaadhracardNosForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;

/**
 *
 * @author 740996
 */
public class SadaremidNothavingaadhracardNosAction extends DispatchAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


        DataSource ds = null;
        HttpSession session = null;
        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);
            ReportDAO reportDAO = new ReportDAO();
            ArrayList shgData = new ArrayList();
            //ReportForm reportForm = (ReportForm) form;

            SadaremidNothavingaadhracardNosForm reportForm = (SadaremidNothavingaadhracardNosForm) form;
            TerritoryDAO territoryDAO = new TerritoryDAO();
            ArrayList districtsList = territoryDAO.getDistricts(ds);
            ArrayList mandallist = new ArrayList();
            reportForm.setDistrictlist(districtsList);
            String districtName = "All";
            String mandalName = "All";
            String villageName = "All";
            CommonDAOImpl comObj = new CommonDAOImpl();
            
            

            if (reportForm.getDistrict_id() != null && reportForm.getDistrict_id().length() > 0) {

                if (reportForm.getMandal_id() == null || reportForm.getMandal_id().equals("0")) { // If mandalid is not select
                    reportForm.setMandal_id("All");
                }

                
                if(!(reportForm.getDistrict_id()).equals("-1"))
            	{
                	districtName = comObj.getDistrictName(reportForm.getDistrict_id());
            	}
            	if(!((reportForm.getMandal_id()).equals("-1")))
            	{
            		mandalName=comObj.getMandalName(reportForm.getDistrict_id(), reportForm.getMandal_id());
            	}
            	if(!((reportForm.getVillage_id()).equals("-1")))
            	{
            		villageName = comObj.getVillageName(reportForm.getDistrict_id(), reportForm.getMandal_id(),reportForm.getVillage_id());
            	}
            	
                request.setAttribute("distName",districtName);
                request.setAttribute("mandalName",mandalName);
                request.setAttribute("villageName",villageName);

                shgData = reportDAO.getSadaremnothavingaadhracardnos(ds, reportForm.getDistrict_id(), reportForm.getMandal_id(), reportForm.getVillage_id(),"All");

                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), "");
                reportForm.setMandallist(mandallist);
                reportForm.setVillagelist(functionalNeedService.getVillageAll(ds, reportForm.getDistrict_id(), reportForm.getMandal_id()));

                if (shgData != null && shgData.size() > 0) {
                    request.setAttribute("shgAbstract", shgData);

                } else {
                    request.setAttribute("msg", "SADAREM ID Not Tagged To AADHARCARD No Details Not Available!");
                }

                String districtid = request.getParameter("districtid");
                String mandalid = request.getParameter("mandalid");
                String villageid = request.getParameter("villageid");

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return mapping.findForward("success");
    }

    public ActionForward loadabstractsadaremnothavingaadhar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


        DataSource ds = null;
        HttpSession session = null;
        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);
            ReportDAO reportDAO = new ReportDAO();
            ArrayList shgData = new ArrayList();
            //ReportForm reportForm = (ReportForm) form;

            SadaremidNothavingaadhracardNosForm reportForm = (SadaremidNothavingaadhracardNosForm) form;
            TerritoryDAO territoryDAO = new TerritoryDAO();
            ArrayList districtsList = territoryDAO.getDistricts(ds);
            ArrayList mandallist = new ArrayList();
            reportForm.setDistrictlist(districtsList);
            String districtName = "All";
            String mandalName = "All";
            String villageName = "All";
            CommonDAOImpl comObj = new CommonDAOImpl();

            if (reportForm.getDistrict_id() != null && reportForm.getDistrict_id().length() > 0) {

                if (reportForm.getMandal_id() == null || reportForm.getMandal_id().equals("0")) { // If mandalid is not select
                    reportForm.setMandal_id("All");
                }

                if(!(reportForm.getDistrict_id()).equals("-1"))
            	{
                	districtName = comObj.getDistrictName(reportForm.getDistrict_id());
            	}
            	if(!((reportForm.getMandal_id()).equals("-1")))
            	{
            		mandalName=comObj.getMandalName(reportForm.getDistrict_id(), reportForm.getMandal_id());
            	}
            	if(!((reportForm.getVillage_id()).equals("-1")))
            	{
            		villageName = comObj.getVillageName(reportForm.getDistrict_id(), reportForm.getMandal_id(),reportForm.getVillage_id());
            	}
            	
                request.setAttribute("distName",districtName);
                request.setAttribute("mandalName",mandalName);
                request.setAttribute("villageName",villageName);

                shgData = reportDAO.getabstractSadaremnothavingaadhracardnos(ds, reportForm.getDistrict_id(), reportForm.getMandal_id(), reportForm.getVillage_id(),"All");

                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), "");
                reportForm.setMandallist(mandallist);
                reportForm.setVillagelist(functionalNeedService.getVillageAll(ds, reportForm.getDistrict_id(), reportForm.getMandal_id()));

                if (shgData != null && shgData.size() > 0) {
                    request.setAttribute("shgAbstract", shgData);

                } else {
                    request.setAttribute("msg", "SADAREM ID Not Tagged To AADHARCARD No Details Not Available!");
                }

                 request.setAttribute("district_id",reportForm.getDistrict_id());
                 request.setAttribute("mandal_id",reportForm.getMandal_id());
                 request.setAttribute("village_id",reportForm.getVillage_id());

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return mapping.findForward("abstract");
    }
    
    
    
    
    
    public ActionForward excelWriting(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException, Exception {


        DataSource ds = null;
        HttpSession session = null;
        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);
            ReportDAO reportDAO = new ReportDAO();
            ArrayList shgData = new ArrayList();
            //ReportForm reportForm = (ReportForm) form;
            String districtName = (String) request.getParameter("district_name");
            String mandalName = (String) request.getParameter("mandal_name");
            String villageName = (String) request.getParameter("village_name");
            SadaremidNothavingaadhracardNosForm reportForm = (SadaremidNothavingaadhracardNosForm) form;
            TerritoryDAO territoryDAO = new TerritoryDAO();
            ArrayList districtsList = territoryDAO.getDistricts(ds);
            ArrayList mandallist = new ArrayList();
            reportForm.setDistrictlist(districtsList);
            shgData = reportDAO.getSadaremnothavingaadhracardnos(ds, request.getParameter("districtid"), request.getParameter("mandalid"), request.getParameter("villageid"),"All");

            mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), "");
            reportForm.setMandallist(mandallist);





            String villageLevel = reportDAO.getVillageAll(ds, request.getParameter("districtid"), request.getParameter("mandalid"), request.getParameter("villageid"));


            if (shgData != null && shgData.size() > 0) {
                request.setAttribute("shgAbstract", shgData);

            } else {
                request.setAttribute("msg", "No Data Found");
            }


            String districtid = request.getParameter("districtid");
            String mandalid = request.getParameter("mandalid");
            String villageid = request.getParameter("villageid");
            String village_name = request.getParameter("village_name");



            request.setAttribute("village_name", village_name);
            exportExcel(shgData, form, request, response);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
    public ActionForward excelWritingabstract(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException, Exception {


        DataSource ds = null;
        HttpSession session = null;
        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);
            ReportDAO reportDAO = new ReportDAO();
            ArrayList shgData = new ArrayList();
            //ReportForm reportForm = (ReportForm) form;
            String districtName = (String) request.getParameter("district_name");
            String mandalName = (String) request.getParameter("mandal_name");
            String villageName = (String) request.getParameter("village_name");
            SadaremidNothavingaadhracardNosForm reportForm = (SadaremidNothavingaadhracardNosForm) form;
            TerritoryDAO territoryDAO = new TerritoryDAO();
            ArrayList districtsList = territoryDAO.getDistricts(ds);
            ArrayList mandallist = new ArrayList();
            reportForm.setDistrictlist(districtsList);
            shgData = reportDAO.getabstractSadaremnothavingaadhracardnos(ds, request.getParameter("districtid"), request.getParameter("mandalid"), request.getParameter("villageid"),"ALL");

            mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), "");
            reportForm.setMandallist(mandallist);





            String villageLevel = reportDAO.getVillageAll(ds, request.getParameter("districtid"), request.getParameter("mandalid"), request.getParameter("villageid"));


            if (shgData != null && shgData.size() > 0) {
                request.setAttribute("shgAbstract", shgData);

            } else {
                request.setAttribute("msg", "No Data Found");
            }


            String districtid = request.getParameter("districtid");
            String mandalid = request.getParameter("mandalid");
            String villageid = request.getParameter("villageid");
            String village_name = request.getParameter("village_name");



            request.setAttribute("villages_name", village_name);
            request.setAttribute("district_id",reportForm.getDistrict_id());
            request.setAttribute("mandal_id",reportForm.getMandal_id());
            request.setAttribute("village_id",reportForm.getVillage_id());
            exportExcelabstract(shgData, form, request, response);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public static void exportExcel(ArrayList dataList, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        SadaremidNothavingaadhracardNosForm reportForm = (SadaremidNothavingaadhracardNosForm) form;
        String districtName = (String) request.getParameter("district_name");
        String mandalName = (String) request.getParameter("mandal_name");
        String villageName = (String) request.getParameter("village_name");


        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=SadaremId_notTagged_to_Aadhar.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(bold);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat.setAlignment(Alignment.LEFT);
            cellFormat.setBackground(Colour.GRAY_25);

            WritableCellFormat cell = new WritableCellFormat();
            WritableCellFormat cellLeft = new WritableCellFormat();
            WritableCellFormat cell111 = new WritableCellFormat(bold);
            
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellLeft.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setAlignment(Alignment.CENTRE);
            cellLeft.setAlignment(Alignment.LEFT);
            cell.setVerticalAlignment(VerticalAlignment.CENTRE);
            cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell111.setAlignment(Alignment.CENTRE);
            cell111.setBackground(Colour.GRAY_25);
            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);


            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "SADAREM ID Not Tagged To AADHARCARD No Details are not Available................"));
            } else {

     //           s.mergeCells(3, 1, 5, 1);
               
                
                s.mergeCells(0, 0, 11, 0);
                s.addCell(new Label(0, 0, " SADAREM ID Not Tagged To AADHARCARD No", cell111));

                s.mergeCells(0, 1, 11, 1);
                s.addCell(new Label(0, 1, "District: " + districtName + " , Mandal: " + mandalName + ",  Village: " + villageName, cellFormat));
                headerList.add("S.No");
                headerList.add("Date Of Issue");
                headerList.add("SADAREM ID");
                headerList.add("Person Name");
                headerList.add("Relation Name");
                headerList.add("Gender");
                headerList.add("Age");
                
                headerList.add("Phone number");
                headerList.add("House No");
                headerList.add("Village");
                headerList.add("Mandal");
                headerList.add("District");
                
                
                
                addHeaders(s, headerList,2);
                headerList = new ArrayList();
                headerList.add("1");
                headerList.add("2"); 
                headerList.add("3");
                headerList.add("4");
                headerList.add("5");
                headerList.add("6");
                headerList.add("7");
                headerList.add("8");
                headerList.add("9");
                headerList.add("10");
                headerList.add("11");
                headerList.add("12");
               
                
                
                addHeaders(s, headerList,3);



            }

            int x = 4;
            int k = 0;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                k++;
                Map m = (Map) dataList.get(i);
                s.addCell(new Label(j++, x, i + 1 + "", cell));
                s.addCell(new Label(j++, x, m.get("Date_of_Issue").toString(), cellLeft));
                s.addCell(new Label(j++, x, m.get("SADAREMCODE").toString(), cell));
               
                s.addCell(new Label(j++, x, m.get("PERSONNAME").toString(), cellLeft));
                s.addCell(new Label(j++, x, m.get("RELATIONNAME").toString(), cellLeft));
                s.addCell(new Label(j++, x, m.get("GENDER").toString(), cellLeft));
                s.addCell(new Label(j++, x, m.get("age").toString(), cell));
                s.addCell(new Label(j++, x, m.get("phone_no").toString(), cell));
                s.addCell(new Label(j++, x, m.get("house_number").toString(), cellLeft));
                s.addCell(new Label(j++, x, m.get("village_name").toString(), cell));
                s.addCell(new Label(j++, x, m.get("mandal_name").toString(), cell));
                s.addCell(new Label(j++, x, m.get("district_name").toString(), cell));
                
                
                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;

                    addHeaders(s, headerList,2);





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
    public static void exportExcelabstract(ArrayList dataList, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        SadaremidNothavingaadhracardNosForm reportForm = (SadaremidNothavingaadhracardNosForm) form;
        String districtName = (String) request.getParameter("district_name");
        String mandalName = (String) request.getParameter("mandal_name");
        String villageName = (String) request.getParameter("village_name");
        String districtid = request.getParameter("districtid");
        String mandalid = request.getParameter("mandalid");
        String villageid = request.getParameter("villageid");


        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=Abstract_SadaremId_notTagged_to_Aadhar.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(bold);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat.setAlignment(Alignment.LEFT);

            WritableCellFormat cell = new WritableCellFormat();
            WritableCellFormat cellLeft = new WritableCellFormat();
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
            cellFormat.setBackground(Colour.GRAY_25);
            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
System.out.println("datalist "+dataList);

            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "SADAREM ID Not Tagged To AADHARCARD No Details are not Available................"));
            } else {

                s.mergeCells(3, 1, 5, 1);
                headerList.add("S.No");
                if (districtid.equals("-1") ) {
                	headerList.add("District"); 
                } else if(!districtid.equals("-1") && mandalid.equals("-1")){
                	headerList.add("Mandal");
                }
                else if(!districtid.equals("-1") && !mandalid.equals("-1") && villageid.equals("-1")){
                	headerList.add("Village");
                }
                else if(!districtid.equals("-1") && !mandalid.equals("-1")  && !villageid.equals("-1")){
                	headerList.add("Habitation");
                }
                
                headerList.add("Count of Sadarem Id's\n not tagged to Aadhar Number");
                
                s.mergeCells(0, 0, 2, 0);
                s.addCell(new Label(0, 0, " Abstract Report of SADAREM ID Not Tagged To AADHARCARD No", cell111));

                s.mergeCells(0, 1, 2, 1);
                s.addCell(new Label(0, 1, "District: " + districtName + " , Mandal: " + mandalName + ",  Village: " + villageName, cellFormat));

                addHeaders(s, headerList,2);
                headerList = new ArrayList();
                headerList.add("1");
                headerList.add("2"); 
                headerList.add("3");
                addHeaders(s, headerList,3);



            }

            int x = 4;
            int k = 0;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                k++;
                Map m = (Map) dataList.get(i);
                
                
                if(i==dataList.size()-1)
                {
                	 s.addCell(new Label(j++, x,"", cell));
                }else
                	 {
                s.addCell(new Label(j++, x, i + 1 + "", cell));
                     }
               if(i==dataList.size()-1)
               {
            	   s.addCell(new Label(j++, x, "Total", cell));
               }else
                  {
                s.addCell(new Label(j++, x, m.get("location_name").toString(), cell));
                  }
                s.addCell(new Label(j++, x, m.get("total").toString(), cellLeft));
               
                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;

                    addHeaders(s, headerList,3);





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
        for (int i = 0; i < col; i++) {
            s.setColumnView(i, 30);
        }

    }

    public static void addHeaders(WritableSheet s, ArrayList list,int y) throws WriteException {

        WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableCellFormat cellFormat = new WritableCellFormat(bold);
        cellFormat.setBackground(Colour.GRAY_25);
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setAlignment(Alignment.CENTRE);
        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, y, list.get(x).toString(), cellFormat));
                setWidth(s, 12, 40);
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


        DataSource ds = null;
        HttpSession session = null;
        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);
            ReportDAO reportDAO = new ReportDAO();
            ArrayList shgData = new ArrayList();
            SadaremidNothavingaadhracardNosForm reportForm = (SadaremidNothavingaadhracardNosForm) form;
            TerritoryDAO territoryDAO = new TerritoryDAO();
            ArrayList districtsList = territoryDAO.getDistricts(ds);
            ArrayList mandallist = new ArrayList();
            reportForm.setDistrictlist(districtsList);
            String districtName = (String) request.getParameter("district_names");
            String mandalName = (String) request.getParameter("mandal_names");
            String villageName = (String) request.getParameter("village_names");
            shgData = reportDAO.getSadaremnothavingaadhracardnos(ds, request.getParameter("districtids"), request.getParameter("mandalids"), request.getParameter("villageids"),"All");
            mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), "");
            reportForm.setMandallist(mandallist);
            reportForm.setVillagelist(functionalNeedService.getVillageAll(ds, reportForm.getDistrict_id(), reportForm.getMandal_id()));

            String villageLevel = reportDAO.getVillageAll(ds, request.getParameter("districtids"), request.getParameter("mandalids"), request.getParameter("villageids"));
            if (request.getParameter("villageids").equals("All")) {
                request.setAttribute("villageName", "ALL");


            } else {
                request.setAttribute("villageName", reportDAO.getVillageAll(ds, request.getParameter("districtids"), request.getParameter("mandalids"), request.getParameter("villageids")));

            }



            if (shgData != null && shgData.size() > 0) {
                request.setAttribute("shgAbstract", shgData);
                request.setAttribute("Note", "If you want Print make it Landscape");
            } else {
                request.setAttribute("msg", "SADAREM ID Not Tagged To AADHARCARD No Details Not Available!");
            }


            request.setAttribute("village_id", reportForm.getVillage_id());
            String districtid = request.getParameter("districtids");
            String mandalid = request.getParameter("mandalids");
            String villageid = request.getParameter("villageids");
            request.setAttribute("districtid", districtid);
            request.setAttribute("mandalid", mandalid);
            request.setAttribute("villageid", villageid);

            request.setAttribute("districtName", districtName);
            request.setAttribute("mandalName", mandalName);




        } catch (Exception e) {

            e.printStackTrace();
        }

        return mapping.findForward("print");
    }
}
