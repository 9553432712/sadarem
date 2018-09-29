package org.bf.disability.action;
//package org.bf.disability.action;
//


import com.lowagie.text.pdf.codec.Base64.OutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.Workbook;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import java.util.Date;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.DailyDisabilityAndPercentageDAO;
import org.bf.disability.dao.PercentageWiseReportDAO;
import org.bf.disability.form.PercentageWiseReportForm;
import org.bf.disability.service.PercentageWiseReportService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.PercentageWiseReportServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

public class PercentageWiseReportAction extends DispatchAction {

    private static final String target = "success";
    DataSource ds = null;

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PercentageWiseReportForm percentageWiseReportForm = (PercentageWiseReportForm) form;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        PercentageWiseReportDAO percentageWiseReportDAO = new PercentageWiseReportDAO();
        HttpSession session = request.getSession();
        ArrayList districtlist = null;
        String districtName = null;
        ArrayList FinancialYearList = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtlist = territoryservice.getDistricts(ds);
            if (districtlist != null && districtlist.size() > 0) {
                percentageWiseReportForm.setDistrictlist(districtlist);
            }
            districtName = territoryservice.getDistrictsName(ds, percentageWiseReportForm.getDistrict_id());
            if (districtName != null && districtName != "") {
                request.setAttribute("districtName", districtName);
            }
            FinancialYearList = percentageWiseReportDAO.getFinancialYears();
            if (FinancialYearList != null && FinancialYearList.size() > 0) {
                percentageWiseReportForm.setFinancialYearList(FinancialYearList);
            }
            request.setAttribute("FinancialYearWise", percentageWiseReportForm.getFinancialYearWise());



             if (percentageWiseReportForm.getFromdate()!= null) {
                percentageWiseReportForm.setFromdate("01/01/2010");
            } else {
                percentageWiseReportForm.setFromdate(percentageWiseReportForm.getFromdate());
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getPecentageWiseData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PercentageWiseReportForm percentageWiseReportForm = (PercentageWiseReportForm) form;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        PercentageWiseReportService percentageWiseReportService = PercentageWiseReportServiceFactory.getPercentageWiseReportServiceImpl();
        PercentageWiseReportDAO percentageWiseReportDAO = new PercentageWiseReportDAO();
        DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();
        HttpSession session = request.getSession();
        ArrayList districtlist = null;
        ArrayList PecentageWiseData = null;
        ArrayList FinancialYearList = null;
        String districtName = null;
        String fromDate = percentageWiseReportForm.getFromdate();
        String toDate = percentageWiseReportForm.getTodate();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtlist = territoryservice.getDistricts(ds);
            percentageWiseReportForm.setDistrict_id(request.getParameter("district_id"));
            //percentageWiseReportForm.setFinancialYear(percentageWiseReportForm.getFinancialYearWise());
            String mandalName = request.getParameter("mandalname");
            String villageName = request.getParameter("villagename");
            if (request.getParameter("district_id") != null && request.getParameter("district_id").length() > 0 && !request.getParameter("district_id").equalsIgnoreCase("null")) {
                percentageWiseReportForm.setDistrict_id(request.getParameter("district_id"));
            }
            districtName = territoryservice.getDistrictsName(ds, percentageWiseReportForm.getDistrict_id());
            if (districtName != null && districtName != "") {
                request.setAttribute("districtName", districtName);
            }
            if (districtlist != null && districtlist.size() > 0) {
                percentageWiseReportForm.setDistrictlist(districtlist);
            }
            FinancialYearList = percentageWiseReportDAO.getFinancialYears();
            if (FinancialYearList != null && FinancialYearList.size() > 0) {
                percentageWiseReportForm.setFinancialYearList(FinancialYearList);
            }
            if(request.getParameter("FinancialYearWise")!=null){
                percentageWiseReportForm.setFinancialYearWise(request.getParameter("FinancialYearWise"));
            }

            
            percentageWiseReportForm.setFinancialYear(percentageWiseReportForm.getFinancialYearWise());
            if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equals("Date Wise")) {
                Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
                String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
                Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
                String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
                 request.setAttribute("DateH", "DateH");
            } else if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equalsIgnoreCase("Financial Year Wise")) {

                //request.setAttribute("FinancialYearWise", percentageWiseReportForm.getFinancialYearWise());
                String givenFromDate = percentageWiseReportForm.getFromdate();
                String givenToDate = percentageWiseReportForm.getTodate();
                if (percentageWiseReportForm.getFinancialYearWise() != null && percentageWiseReportForm.getFinancialYearWise() != "") {
                    String[] financialYear = percentageWiseReportForm.getFinancialYearWise().split("-");
                    int givenYear = Integer.parseInt(financialYear[0].toString());
                    givenFromDate = "01/04/" + givenYear;
                    givenToDate = dailyDisabilityAndPercentageDAO.getAssignedDate(givenYear + 1, ds, givenFromDate);
                }

                request.setAttribute("FinancialYearWise", percentageWiseReportForm.getFinancialYearWise());
                request.setAttribute("fromdate", percentageWiseReportForm.getFromdate());
                request.setAttribute("todate", percentageWiseReportForm.getTodate());
                request.setAttribute("Finance", "Finance");
            }
            PecentageWiseData = percentageWiseReportService.getPecentageWiseData(percentageWiseReportForm, ds);
            if (!PecentageWiseData.equals("null") && PecentageWiseData.size() > 0) {
                request.setAttribute("PecentageWiseData", PecentageWiseData);
                request.setAttribute("district_id", percentageWiseReportForm.getDistrict_id());
                request.setAttribute("fromdate", percentageWiseReportForm.getFromdate());
                request.setAttribute("todate", percentageWiseReportForm.getTodate());

            } else {
                request.setAttribute("msg", "Percentage Wise Details are not Available");
            }
            if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {
                if(request.getParameter("mandalname")!=""){
                    percentageWiseReportForm.setMandalname(request.getParameter("mandalname"));
                }
                if (mandalName != null && mandalName != "") {
                    request.setAttribute("mandalName", mandalName);
                }
                percentageWiseReportForm.setVillagename(request.getParameter("villagename"));
                if (villageName != null && villageName != "") {
                    request.setAttribute("villageName", villageName);
                }
                request.setAttribute("habitation", "habitation");
            } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
                request.setAttribute("village", "village");
                percentageWiseReportForm.setMandalname(request.getParameter("mandalname"));
                if (mandalName != null && mandalName != "") {
                    request.setAttribute("mandalName", mandalName);
                }
                //String mandalName=request.getParameter("mandalname");

            } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {
                percentageWiseReportForm.setMandalname(request.getParameter("mandalname"));
                request.setAttribute("mandal", "mandal");
                if (mandalName != null && mandalName != "") {
                    request.setAttribute("mandalName", mandalName);
                }
            } else {
                request.setAttribute("district", "district");
            }
            request.setAttribute("district_id", percentageWiseReportForm.getDistrict_id());
            request.setAttribute("fromdate", percentageWiseReportForm.getFromdate());
            request.setAttribute("todate", percentageWiseReportForm.getTodate());
            request.setAttribute("typeOfSearch", percentageWiseReportForm.getTypeOfSearch());

        } catch (Exception e) {
            e.printStackTrace();
        }
        //percentageWiseReportForm.reset(mapping, request);
        return mapping.findForward(target);
    }

    

    public ActionForward PecentageWiseDataPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArrayList districtlist = null;
        String districtName = null;
        String target = "print";
        ArrayList FinancialYearList = null;
        ArrayList PecentageWiseData = null;
        HttpSession session = request.getSession();
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        PercentageWiseReportForm percentageWiseReportForm = (PercentageWiseReportForm) form;
        PercentageWiseReportDAO percentageWiseReportDAO = new PercentageWiseReportDAO();
        String fromDate = percentageWiseReportForm.getFromdate();
        String toDate = percentageWiseReportForm.getTodate();
        DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();
        PercentageWiseReportService percentageWiseReportService = PercentageWiseReportServiceFactory.getPercentageWiseReportServiceImpl();
        try {
            percentageWiseReportForm.setDistrict_id(request.getParameter("district_id"));
            percentageWiseReportForm.setMandal_id(request.getParameter("mandal_id"));
            percentageWiseReportForm.setVillage_id(request.getParameter("village_id"));
            percentageWiseReportForm.setMandalname(request.getParameter("mandalname"));
            request.getParameter("district_id");
            request.getParameter("mandal_id");
            request.getParameter("mandalname");
            //districtName = territoryservice.getDistrictsName(ds, percentageWiseReportForm.getDistrict_id());
            if (districtName != null && districtName != "") {
                request.setAttribute("districtName", districtName);
            }
            String mandalName = request.getParameter("mandalname");
            String villageName = request.getParameter("villagename");


            if (request.getParameter("FIR") != null) {
                percentageWiseReportForm.setFinancialYearWise(request.getParameter("FIR"));
            }
            if (districtlist != null && districtlist.size() > 0) {
                percentageWiseReportForm.setDistrictlist(districtlist);
            }

            if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equals("Date Wise")) {
                Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
                String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
                Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
                String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
                 request.setAttribute("DateH", "DateH");
//                if (percentageWiseReportForm.getDistrict_id() == null && percentageWiseReportForm.getDistrict_id() == "") {
            } else if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equalsIgnoreCase("Financial Year Wise")) {
                percentageWiseReportForm.setFinancialYearWise(request.getParameter("FinancialYearWise"));
                //request.setAttribute("FinancialYearWise", percentageWiseReportForm.getFinancialYearWise());
                String givenFromDate = percentageWiseReportForm.getFromdate();
                String givenToDate = percentageWiseReportForm.getTodate();
                if (percentageWiseReportForm.getFinancialYearWise() != null && percentageWiseReportForm.getFinancialYearWise() != "") {
                    String[] financialYear = percentageWiseReportForm.getFinancialYearWise().split("-");
                    int givenYear = Integer.parseInt(financialYear[0].toString());
                    givenFromDate = "01/04/" + givenYear;
                    givenToDate = dailyDisabilityAndPercentageDAO.getAssignedDate(givenYear + 1, ds, givenFromDate);
                }

                request.setAttribute("FinancialYearWise", percentageWiseReportForm.getFinancialYearWise());
                request.setAttribute("fromdate", percentageWiseReportForm.getFromdate());
                request.setAttribute("todate", percentageWiseReportForm.getTodate());
                 request.setAttribute("Finance", "Finance");
            }
            PecentageWiseData = percentageWiseReportService.getPecentageWiseData(percentageWiseReportForm, ds);
            if (!PecentageWiseData.equals("null") && PecentageWiseData.size() > 0) {
                request.setAttribute("PecentageWiseData", PecentageWiseData);
                request.setAttribute("district_id", percentageWiseReportForm.getDistrict_id());
                request.setAttribute("fromdate", percentageWiseReportForm.getFromdate());
                request.setAttribute("todate", percentageWiseReportForm.getTodate());

            }
            if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {
                request.setAttribute("habitation", "habitation");
                if(request.getParameter("mandalname")!=null){
                     percentageWiseReportForm.setMandalname(request.getParameter("mandalname"));
                }
               
                if (mandalName != null && mandalName != "") {
                    request.setAttribute("mandalName", mandalName);
                }
                percentageWiseReportForm.setVillagename(request.getParameter("villagename"));
                if (villageName != null && villageName != "") {
                    request.setAttribute("villageName", villageName);
                }
            } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
                request.setAttribute("village", "village");
                percentageWiseReportForm.setMandalname(request.getParameter("mandalname"));
                if (mandalName != null && mandalName != "") {
                    request.setAttribute("mandalName", mandalName);
                }
                //String mandalName=request.getParameter("mandalname");

            } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {
                percentageWiseReportForm.setMandalname(request.getParameter("mandalname"));
                request.setAttribute("mandal", "mandal");
                if (mandalName != null && mandalName != "") {
                    request.setAttribute("mandalName", mandalName);
                }
            } else {
                request.setAttribute("district", "district");
            }

//            request.setAttribute("DateH", "DateH");

//                if (!PecentageWiseData.equals("null") && PecentageWiseData.size() > 0) {
//                    request.setAttribute("PecentageWiseData", PecentageWiseData);
//                    request.setAttribute("fromdate", percentageWiseReportForm.getFromdate());
//                    request.setAttribute("todate", percentageWiseReportForm.getTodate());
////                request.setAttribute("district", "districr");
////                request.setAttribute("mandal", "mandal");
////                request.setAttribute("village", "village");
////                request.setAttribute("habitation", "habitation");
//
//                }
//                else {
//                    request.setAttribute("msg", "Percentage Wise Details are not Available");
//                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(target);
    }

    public ActionForward excelWriting(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException, Exception {
        ArrayList PecentageWiseData = null;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        PercentageWiseReportForm percentageWiseReportForm = (PercentageWiseReportForm) form;
        PercentageWiseReportDAO percentageWiseReportDAO = new PercentageWiseReportDAO();
        DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();
        PercentageWiseReportService percentageWiseReportService = PercentageWiseReportServiceFactory.getPercentageWiseReportServiceImpl();
        HttpSession session = request.getSession();
        String fromDate = percentageWiseReportForm.getFromdate();
        String toDate = percentageWiseReportForm.getTodate();
        String mandalName = request.getParameter("mandalname");
        String districtName = request.getParameter("districtName");
        String villageName = request.getParameter("villagename");
        String individualexcel = request.getParameter("individualexcel");
//        request.setAttribute("mandalName", percentageWiseReportForm.getMandalname());
//        request.setAttribute("districtName", percentageWiseReportForm.getDistrictName());
//        request.setAttribute("villageName", percentageWiseReportForm.getVillagename());

        if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equals("Date Wise")) {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
//                if (percentageWiseReportForm.getDistrict_id() == null && percentageWiseReportForm.getDistrict_id() == "") {
        } else if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equalsIgnoreCase("Financial Year Wise")) {
            percentageWiseReportForm.setFinancialYearWise(request.getParameter("FinancialYearWise"));
            //request.setAttribute("FinancialYearWise", percentageWiseReportForm.getFinancialYearWise());
            String givenFromDate = percentageWiseReportForm.getFromdate();
            String givenToDate = percentageWiseReportForm.getTodate();
            if (percentageWiseReportForm.getFinancialYearWise() != null && percentageWiseReportForm.getFinancialYearWise() != "") {
                String[] financialYear = percentageWiseReportForm.getFinancialYearWise().split("-");
                int givenYear = Integer.parseInt(financialYear[0].toString());
                givenFromDate = "01/04/" + givenYear;
                givenToDate = dailyDisabilityAndPercentageDAO.getAssignedDate(givenYear + 1, ds, givenFromDate);
            }

            request.setAttribute("FinancialYearWise", percentageWiseReportForm.getFinancialYearWise());
            request.setAttribute("fromdate", percentageWiseReportForm.getFromdate());
            request.setAttribute("todate", percentageWiseReportForm.getTodate());
        }
        if (individualexcel != null && !individualexcel.equalsIgnoreCase("") && !individualexcel.equalsIgnoreCase("null")) {
            PecentageWiseData = percentageWiseReportService.getIndividualDetails(percentageWiseReportForm, ds);
        } else {
            PecentageWiseData = percentageWiseReportService.getPecentageWiseData(percentageWiseReportForm, ds);
        }
        if (!PecentageWiseData.equals("null") && PecentageWiseData.size() > 0) {
            request.setAttribute("PecentageWiseData", PecentageWiseData);
            request.setAttribute("district_id", percentageWiseReportForm.getDistrict_id());
            request.setAttribute("fromdate", percentageWiseReportForm.getFromdate());
            request.setAttribute("todate", percentageWiseReportForm.getTodate());


        }

        if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {

            percentageWiseReportForm.setMandalname(request.getParameter("mandalname"));
            if (mandalName != null && mandalName != "") {
                request.setAttribute("mandalName", mandalName);
            }
            percentageWiseReportForm.setVillagename(request.getParameter("villagename"));
            if (villageName != null && villageName != "") {
                request.setAttribute("villageName", villageName);
            }
            request.setAttribute("habitation", "habitation");
        } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
            districtName = territoryservice.getDistrictsName(ds, percentageWiseReportForm.getDistrict_id());
            if (districtName != null && districtName != "") {
                request.setAttribute("districtName", districtName);
            }
            percentageWiseReportForm.setMandalname(request.getParameter("mandalname"));
            if (mandalName != null && mandalName != "") {
                request.setAttribute("mandalName", mandalName);
            }
            request.setAttribute("village", "village");
        } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {

            request.setAttribute("mandal", "mandal");
        } else {
            request.setAttribute("district", "district");
        }
         request.setAttribute("DateH", "DateH");
//        ArrayList PecentageWiseData = (ArrayList) session.getAttribute("PecentageWiseData");
        unspecified(mapping, form, request, response);
        if (individualexcel != null && !individualexcel.equalsIgnoreCase("") && !individualexcel.equalsIgnoreCase("null")) {
            individualExportExcel(PecentageWiseData, form, request, response);
        } else {
            exportExcel(PecentageWiseData, form, request, response);
        }
        return null;
    }

    public static void exportExcel(ArrayList dataList, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        PercentageWiseReportForm percentageWiseReportForm = (PercentageWiseReportForm) form;

        String fromdate = (String) request.getParameter("fromdate");
        String todate = (String) request.getParameter("todate");

        String districtName = (String) request.getParameter("districtName");
        String mandalName = (String) request.getParameter("mandalname");
        String villageName = (String) request.getParameter("villagename");
        String FinancialYearWise = (String) request.getParameter("FinancialYearWise");
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=20-39 Percentage Assessed Report.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(bold);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat.setAlignment(Alignment.CENTRE);

            WritableCellFormat cell = new WritableCellFormat();
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setAlignment(Alignment.CENTRE);
            cell.setVerticalAlignment(VerticalAlignment.CENTRE);

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "Percentage Wise Details are not Available................"));
            } else {

               // s.mergeCells(3, 1, 7, 1);
                 s.mergeCells(3, 1, 5, 1);
                s.addCell(new Label(3, 2, "Rejected", cellFormat));
                if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {
                    s.addCell(new Label(0, 1, "District:" + districtName +""+ "Mandal:" + mandalName +""+ "Village:" + villageName, cellFormat));
                } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
                    s.addCell(new Label(0, 1, "District:" + districtName + "Mandal:" + mandalName + "Village:ALL", cellFormat));
                } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {
                    s.addCell(new Label(0, 1, "District:" + districtName + "Mandal:ALL"+""+   "Village:ALL", cellFormat));
                } else {
                    s.addCell(new Label(0, 1, "District:ALL"+""+"  Mandal:ALL"+""+"  Village:ALL", cellFormat));
                }
                //s.addCell(new Label(0, 1, "Rejected====", cellFormat));
                //s.addCell(new Label(1,2,"S.No", cellFormat));
                headerList.add("S.No");
                if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {
                    headerList.add("Habitation");
                } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
                    headerList.add("Village");
                } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {
                    headerList.add("Mandal");
                } else {
                    headerList.add("District");
                }

                //headerList.add("Mandal");
                headerList.add("Assessed");
                headerList.add("Before Assment (No visible disb.)");
                headerList.add("After Assment (<40% disb.)");
                headerList.add("Total Rejected");
                headerList.add("Eligible(40% to 79%)");
                headerList.add("Eligible(80% to 100%)");
                 headerList.add("Eligible(40% to 100%)");
                s.mergeCells(0, 0, 7, 0);
//                 s.addCell(new Label(1, 0, "Percentagewise Report " ,cell));
                if (fromdate != null && !fromdate.equals("") && todate != null && !todate.equals("")) {
                    s.addCell(new Label(0, 0, "Percentage Report -  status as on  "
                            + fromdate + " To " + todate + "   District: " + districtName, cellFormat));
                } else if (!FinancialYearWise.equals("0")) {
                    s.addCell(new Label(0, 0, " Percentage Report -  status as on    Finance Year " + FinancialYearWise + "   District: " + districtName, cellFormat));
                }
                addHeaders(s, headerList);
            }

            int x = 4;
            int k = 0;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                k++;
                Map m = (Map) dataList.get(i);
                s.addCell(new Label(j++, x, i + 1 + "", cell));
                if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {
                    s.addCell(new Label(j++, x, m.get("habitationname").toString(), cell));
                } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
                    s.addCell(new Label(j++, x, m.get("villagename").toString(), cell));
                } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {
                    s.addCell(new Label(j++, x, m.get("mandalname").toString(), cell));
                } else {
                    s.addCell(new Label(j++, x, m.get("districtname").toString(), cell));
                }
                //s.addCell(new Label(j++, x, m.get("mandalName").toString(), cell));
                s.addCell(new Label(j++, x, m.get("Assessed").toString(), cell));
                s.addCell(new Label(j++, x, m.get("directRejected").toString(), cell));
                s.addCell(new Label(j++, x, m.get("assessedRejected").toString(), cell));
                s.addCell(new Label(j++, x, m.get("rejected").toString(), cell));
                s.addCell(new Label(j++, x, m.get("eligible").toString(), cell));
                s.addCell(new Label(j++, x, m.get("eligibleData").toString(), cell));
                 s.addCell(new Label(j++, x, m.get("eligibledata40to100").toString(), cell));

                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;

                    addHeaders(s, headerList);
                }
            }
            Map m = (Map) dataList.get(k - 1);
            s.mergeCells(0, x, 1, x);
            s.addCell(new Label(0, x, "Total", cellFormat));
            s.addCell(new Label(2, x, m.get("totalAssessed").toString(), cellFormat));
            s.addCell(new Label(3, x, m.get("totalDirectRejected").toString(), cellFormat));
            s.addCell(new Label(4, x, m.get("totalAssessedRejected").toString(), cellFormat));
            s.addCell(new Label(5, x, m.get("totalRejected").toString(), cellFormat));
            s.addCell(new Label(6, x, m.get("totalEligible").toString(), cellFormat));
            s.addCell(new Label(7, x, m.get("totalEligibleData").toString(), cellFormat));
            s.addCell(new Label(8, x, m.get("totaleligible40to80").toString(), cellFormat));
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
        for (int i = 0; i < 9; i++) {
            s.setColumnView(i, 30);
        }

    }

    public static void addHeaders(WritableSheet s, ArrayList list) throws WriteException {

        WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableCellFormat cellFormat = new WritableCellFormat(bold);
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setAlignment(Alignment.CENTRE);
        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 2, list.get(x).toString(), cellFormat));
                setWidth(s, 7, 30);
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void individualExportExcel(ArrayList dataList, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        PercentageWiseReportForm percentageWiseReportForm = (PercentageWiseReportForm) form;

        String fromdate = (String) request.getParameter("fromdate");
        String todate = (String) request.getParameter("todate");

        String districtName = (String) request.getParameter("districtName");
        String FinancialYearWise = (String) request.getParameter("FinancialYearWise");
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=20-39 Percentage Assessed Report.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(bold);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat.setAlignment(Alignment.CENTRE);

            WritableCellFormat cell = new WritableCellFormat();
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setAlignment(Alignment.CENTRE);
            cell.setVerticalAlignment(VerticalAlignment.CENTRE);

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "Percentage Wise Details are not Available................"));
            } else {
                //headerList.add("Mandal");
                headerList.add("S No.");
                headerList.add("SADAREM ID");
                headerList.add("Pension No");
                headerList.add("Name");
                headerList.add("Gender");
                headerList.add("Caste");
                headerList.add("Age");
                headerList.add("Marital Status");
                headerList.add("Relation Name");
                headerList.add("Disability");
                headerList.add("Total Disability");
                headerList.add("Education");
                headerList.add("Employment");
                headerList.add("Rationcard No");
                headerList.add("Pension Phase");
//                headerList.add("Pension Status");
                headerList.add("Mandal");
                headerList.add("Village");
                headerList.add("Habitation");
                headerList.add("House No");
                headerList.add("Phone No");
                headerList.add("Person Status");
                headerList.add("Assessment");
                headerList.add("LoginId");
                headerList.add("Assessment Date");
                s.mergeCells(0, 0, 7, 0);
//                 s.addCell(new Label(1, 0, "Percentagewise Report " ,cell));
                if (fromdate != null && !fromdate.equals("") && todate != null && !todate.equals("")) {
                    s.addCell(new Label(0, 0, "Percentage Report -  status as on  "
                            + fromdate + " To " + todate + "   District: " + districtName+ "   Eligible 80 -100 %", cellFormat));
                } else if (!FinancialYearWise.equals("0")) {
                    s.addCell(new Label(0, 0, " Percentage Report -  status as on    Finance Year " + FinancialYearWise + "   District: " + districtName+ "   Eligible 80 -100 %", cellFormat));
                }
                addHeaders(s, headerList);
            }

            int x = 3;
            int k = 0;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                k++;
                Map m = (Map) dataList.get(i);
                s.addCell(new Label(j++, x, i + 1 + "", cell));

                s.addCell(new Label(j++, x, m.get("personcode").toString(), cell));
                s.addCell(new Label(j++, x, m.get("pensionno").toString(), cell));
                s.addCell(new Label(j++, x, m.get("name").toString(), cell));
                s.addCell(new Label(j++, x, m.get("gender").toString(), cell));
                s.addCell(new Label(j++, x, m.get("caste").toString(), cell));
                s.addCell(new Label(j++, x, m.get("age").toString(), cell));
                s.addCell(new Label(j++, x, m.get("maritalstatus").toString(), cell));
                s.addCell(new Label(j++, x, m.get("relationname").toString(), cell));
                s.addCell(new Label(j++, x, m.get("disability").toString(), cell));
                s.addCell(new Label(j++, x, m.get("totaldisability").toString(), cell));
                s.addCell(new Label(j++, x, m.get("education").toString(), cell));
                s.addCell(new Label(j++, x, m.get("employment").toString(), cell));
                s.addCell(new Label(j++, x, m.get("rationcardno").toString(), cell));
                s.addCell(new Label(j++, x, m.get("pensionphase").toString(), cell));
//                s.addCell(new Label(j++, x, m.get("pensionstatus").toString(), cell));
                s.addCell(new Label(j++, x, m.get("mandalname").toString(), cell));
                s.addCell(new Label(j++, x, m.get("villagename").toString(), cell));
                s.addCell(new Label(j++, x, m.get("habitationname").toString(), cell));
                s.addCell(new Label(j++, x, m.get("houseno").toString(), cell));
                s.addCell(new Label(j++, x, m.get("phoneno").toString(), cell));
                s.addCell(new Label(j++, x, m.get("reasonforpersonstatus").toString(), cell));
                s.addCell(new Label(j++, x, m.get("assessment").toString(), cell));
                s.addCell(new Label(j++, x, m.get("loginid").toString(), cell));
                s.addCell(new Label(j++, x, m.get("createddate").toString(), cell));


                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;

                    individualaddHeaders(s, headerList);
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

    public static void individualaddHeaders(WritableSheet s, ArrayList list) throws WriteException {

        WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableCellFormat cellFormat = new WritableCellFormat(bold);
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setAlignment(Alignment.CENTRE);
        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 2, list.get(x).toString(), cellFormat));
                setWidth(s, 30, 30);
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }
}
