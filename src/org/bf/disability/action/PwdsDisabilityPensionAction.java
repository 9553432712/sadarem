 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PwdsDisabilityPensionDAO;
import org.bf.disability.service.PwdsDisabilityPensionService;
import org.bf.disability.servicefactory.PwdsDisabilityPensionServiceFactory;
import com.lowagie.text.pdf.codec.Base64.OutputStream;
import java.sql.SQLException;
import java.util.Map;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author 842412
 */
public class PwdsDisabilityPensionAction extends DispatchAction {

    private static final String target = "success";
   

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(target);
    }

    public ActionForward getDisabilityPensionsDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            PwdsDisabilityPensionService pwdsDisabilityPensionService = PwdsDisabilityPensionServiceFactory.getPwdsDisabilityPensionServiceImpl();
            PwdsDisabilityPensionDAO pwdsDisabilityPensionDAO = new PwdsDisabilityPensionDAO();
            ArrayList pensionData = null;
             DataSource ds = null;
            ArrayList pwdPensionData = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            pensionData = pwdsDisabilityPensionService.getDisabilityPensionsDetails(ds);

            if (pensionData != null && pensionData.size() > 0) {
                request.setAttribute("pensionData", pensionData);
            }
            pwdPensionData = pwdsDisabilityPensionService.getPwdsPensionDetails(ds);
            if (pwdPensionData != null && pwdPensionData.size() > 0) {
                request.setAttribute("pwdPensionData", pwdPensionData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward excelWriting(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException, Exception {
        try {

        } catch (Exception e) {
        }
        ArrayList pensionData = null;
        ArrayList pwdPensionData = null;
         DataSource ds = null;
          ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
        PwdsDisabilityPensionService pwdsDisabilityPensionService = PwdsDisabilityPensionServiceFactory.getPwdsDisabilityPensionServiceImpl();
//        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
//        PercentageWiseReportForm percentageWiseReportForm = (PercentageWiseReportForm) form;
//        PercentageWiseReportDAO percentageWiseReportDAO = new PercentageWiseReportDAO();
//        DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();
//        PercentageWiseReportService percentageWiseReportService = PercentageWiseReportServiceFactory.getPercentageWiseReportServiceImpl();
//        HttpSession session = request.getSession();
//        String fromDate = percentageWiseReportForm.getFromdate();
//        String toDate = percentageWiseReportForm.getTodate();
//        String mandalName = request.getParameter("mandalname");
//        String districtName = request.getParameter("districtName");
//        String villageName = request.getParameter("villagename");
//        String individualexcel = request.getParameter("individualexcel");
//        request.setAttribute("mandalName", percentageWiseReportForm.getMandalname());
//        request.setAttribute("districtName", percentageWiseReportForm.getDistrictName());
//        request.setAttribute("villageName", percentageWiseReportForm.getVillagename());

//        if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equals("Date Wise")) {
//            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
//            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
//            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
//            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
////                if (percentageWiseReportForm.getDistrict_id() == null && percentageWiseReportForm.getDistrict_id() == "") {
//        } else if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equalsIgnoreCase("Financial Year Wise")) {
//            percentageWiseReportForm.setFinancialYearWise(request.getParameter("FinancialYearWise"));
//            //request.setAttribute("FinancialYearWise", percentageWiseReportForm.getFinancialYearWise());
//            String givenFromDate = percentageWiseReportForm.getFromdate();
//            String givenToDate = percentageWiseReportForm.getTodate();
//            if (percentageWiseReportForm.getFinancialYearWise() != null && percentageWiseReportForm.getFinancialYearWise() != "") {
//                String[] financialYear = percentageWiseReportForm.getFinancialYearWise().split("-");
//                int givenYear = Integer.parseInt(financialYear[0].toString());
//                givenFromDate = "01/04/" + givenYear;
//                givenToDate = dailyDisabilityAndPercentageDAO.getAssignedDate(givenYear + 1, ds, givenFromDate);
//            }
//
//            request.setAttribute("FinancialYearWise", percentageWiseReportForm.getFinancialYearWise());
//            request.setAttribute("fromdate", percentageWiseReportForm.getFromdate());
//            request.setAttribute("todate", percentageWiseReportForm.getTodate());
//        }

        pensionData = pwdsDisabilityPensionService.getDisabilityPensionsDetails(ds);
        pwdPensionData = pwdsDisabilityPensionService.getPwdsPensionDetails(ds);
//        if (individualexcel != null && !individualexcel.equalsIgnoreCase("") && !individualexcel.equalsIgnoreCase("null")) {
//            PecentageWiseData = percentageWiseReportService.getIndividualDetails(percentageWiseReportForm, ds);
//        } else {
//            PecentageWiseData = percentageWiseReportService.getPecentageWiseData(percentageWiseReportForm, ds);
//        }
//        if (!PecentageWiseData.equals("null") && PecentageWiseData.size() > 0) {
//            request.setAttribute("PecentageWiseData", PecentageWiseData);
//            request.setAttribute("district_id", percentageWiseReportForm.getDistrict_id());
//            request.setAttribute("fromdate", percentageWiseReportForm.getFromdate());
//            request.setAttribute("todate", percentageWiseReportForm.getTodate());
//
//
//        }

//        if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {
//
//            percentageWiseReportForm.setMandalname(request.getParameter("mandalname"));
//            if (mandalName != null && mandalName != "") {
//                request.setAttribute("mandalName", mandalName);
//            }
//            percentageWiseReportForm.setVillagename(request.getParameter("villagename"));
//            if (villageName != null && villageName != "") {
//                request.setAttribute("villageName", villageName);
//            }
//            request.setAttribute("habitation", "habitation");
//        } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
//            districtName = territoryservice.getDistrictsName(ds, percentageWiseReportForm.getDistrict_id());
//            if (districtName != null && districtName != "") {
//                request.setAttribute("districtName", districtName);
//            }
//            percentageWiseReportForm.setMandalname(request.getParameter("mandalname"));
//            if (mandalName != null && mandalName != "") {
//                request.setAttribute("mandalName", mandalName);
//            }
//            request.setAttribute("village", "village");
//        } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {
//
//            request.setAttribute("mandal", "mandal");
//        } else {
//            request.setAttribute("district", "district");
//        }
        //request.setAttribute("DateH", "DateH");
//        ArrayList PecentageWiseData = (ArrayList) session.getAttribute("PecentageWiseData");
        exportExcel(pensionData, pwdPensionData, form, request, response);
        unspecified(mapping, form, request, response);
//        if (individualexcel != null && !individualexcel.equalsIgnoreCase("") && !individualexcel.equalsIgnoreCase("null")) {
//            individualExportExcel(PecentageWiseData, form, request, response);
//        } else {
//            exportExcel(PecentageWiseData, form, request, response);
//        }
        return null;
    }

    public static void exportExcel(ArrayList dataList1,ArrayList dataList2, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        //PercentageWiseReportForm percentageWiseReportForm = (PercentageWiseReportForm) form;

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
            WritableCellFormat cell1 = new WritableCellFormat();
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setAlignment(Alignment.CENTRE);
            cell1.setAlignment(Alignment.LEFT);
            cell1.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setVerticalAlignment(VerticalAlignment.CENTRE);

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            if (dataList1.size() == 0) {
                s.addCell(new Label(0, 0, "Percentage Wise Details are not Available................"));
            }else if (dataList2.size() == 0) {
                s.addCell(new Label(0, 0, "Percentage Wise Details are not Available................"));
            } else {

                // s.mergeCells(3, 1, 7, 1);
                if(dataList1.size()>0){
                s.mergeCells(0, 1, 5, 1);
                s.addCell(new Label(0, 1, "PwDs - PENSIONS COVERED Disability Wise", cellFormat));
                headerList.add("S.No");
                headerList.add("Disability");
                headerList.add("40 to 79");
                headerList.add(" %");
                headerList.add("80 to 100");
                headerList.add(" %40 to 79");
                headerList.add("Total");
                addHeaders(s, headerList, cell, "dataList1");
                }

             
                if(dataList2.size()>0){
                  s.mergeCells(0, 11, 6, 11);
                s.addCell(new Label(0, 11, "Analysis of PwDs Population,Assessement,Eligibility & Pensions Covered ", cellFormat));
                headerList= new ArrayList();
                headerList.add("S.No");
                headerList.add("District");
                headerList.add("Census");
                headerList.add(" SADAREM Assessed");
                headerList.add("% of Coverage against Census");
                headerList.add("Eligible PwDs");
                headerList.add("Pensions Coverage");
                headerList.add("% of Pensions Covered against Eligiblity");
                
                addHeaders(s, headerList, cell, "dataList2");
                }

            }
            int x = 3;
            if(dataList1.size()>0){

            int k = 0;
            for (int i = 0; i < dataList1.size(); i++, x++) {
                int j = 0;
                k++;
                Map m = (Map) dataList1.get(i);
                s.addCell(new Label(j++, x, i + 1 + "", cell));
//                if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {
//                    s.addCell(new Label(j++, x, m.get("habitationname").toString(), cell));
//                } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
//                    s.addCell(new Label(j++, x, m.get("villagename").toString(), cell));
//                } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {
//                    s.addCell(new Label(j++, x, m.get("mandalname").toString(), cell));
//                } else {
//                    s.addCell(new Label(j++, x, m.get("districtname").toString(), cell));
//                }
                //s.addCell(new Label(j++, x, m.get("mandalName").toString(), cell));
                String disCountData =    m.get("dis40to90").toString()+"("+  m.get("inter40to70").toString()+")";
                String dis80 =    m.get("dis80").toString()+"("+  m.get("inter80").toString()+")";

                s.addCell(new Label(j++, x, m.get("disability").toString(), cell1));
                s.addCell(new Label(j++, x, disCountData, cell));
                s.addCell(new Label(j++, x, m.get("per40to79").toString(), cell));
                s.addCell(new Label(j++, x, dis80, cell));
                s.addCell(new Label(j++, x, m.get("per80").toString(), cell));
                s.addCell(new Label(j++, x, m.get("total").toString(), cell));

                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;
                    addHeaders(s, headerList, cell, "dataList1");
                }
            }
            Map m = (Map) dataList1.get(k - 1);
            s.mergeCells(0, x, 1, x);
            
            s.addCell(new Label(0, x, "Total", cellFormat));
            s.addCell(new Label(2, x, m.get("totalDis40to90").toString(), cellFormat));
            s.addCell(new Label(3, x, m.get("avg80").toString(), cellFormat));
            
           
            s.addCell(new Label(4, x, m.get("avg40to79").toString(), cellFormat));
             s.addCell(new Label(5, x, m.get("totalDis80").toString(), cellFormat));
            s.addCell(new Label(6, x, m.get("allTotal").toString(), cellFormat));
            //s.addCell(new Label(7, x, m.get("totalEligibleData").toString(), cellFormat));
           
            }
            x=13;
            if(dataList2.size()>0){
          
            int k = 0;
            for (int i = 0; i < dataList2.size(); i++, x++) {
                int j = 0;
                k++;
                Map m = (Map) dataList2.get(i);
                s.addCell(new Label(j++, x, i + 1 + "", cell));
//                if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {
//                    s.addCell(new Label(j++, x, m.get("habitationname").toString(), cell));
//                } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
//                    s.addCell(new Label(j++, x, m.get("villagename").toString(), cell));
//                } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {
//                    s.addCell(new Label(j++, x, m.get("mandalname").toString(), cell));
//                } else {
//                    s.addCell(new Label(j++, x, m.get("districtname").toString(), cell));
//                }
                //s.addCell(new Label(j++, x, m.get("mandalName").toString(), cell));
                s.addCell(new Label(j++, x, m.get("district_name").toString(), cell1));
                s.addCell(new Label(j++, x, m.get("census").toString(), cell));
                s.addCell(new Label(j++, x, m.get("Totalassessed").toString(), cell));
                s.addCell(new Label(j++, x, m.get("coverageaganistcensus").toString(), cell));
                s.addCell(new Label(j++, x, m.get("Eligible").toString(), cell));
                s.addCell(new Label(j++, x, m.get("pensionscoverage").toString(), cell));
                s.addCell(new Label(j++, x, m.get("pensioncoveredaganisteligibility").toString(), cell));

                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;
                    addHeaders(s, headerList, cell, "dataList2");
                    //addHeaders(s, headerList);
                }
            }
            Map m = (Map) dataList2.get(k - 1);
            s.mergeCells(0, x, 1, x);
            s.addCell(new Label(0, x, "Total", cellFormat));
            s.addCell(new Label(2, x, m.get("totalCensus").toString(), cellFormat));
            s.addCell(new Label(3, x, m.get("totalAssessed").toString(), cellFormat));
            s.addCell(new Label(4, x, m.get("angCoverageaganistcensus").toString(), cellFormat));
            s.addCell(new Label(5, x, m.get("totalEligible").toString(), cellFormat));
            s.addCell(new Label(6, x, m.get("totalPensionscoverage").toString(), cellFormat));
            s.addCell(new Label(7, x, m.get("avgPensioncoveredaganisteligibility").toString(), cellFormat));
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

    public static void addHeaders(WritableSheet s, ArrayList list,WritableCellFormat cell, String flag) throws WriteException {

        WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableCellFormat cellFormat = new WritableCellFormat(bold);
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setAlignment(Alignment.CENTRE);
        for (int x = 0; x < list.size(); x++) {
            try {
                 if (flag.equals("dataList1")) {
                 s.addCell(new Label(x, 2, list.get(x).toString(), cellFormat));
                setWidth(s, 7, 30);
                 }if (flag.equals("dataList2")) {
                 s.addCell(new Label(x, 12, list.get(x).toString(), cellFormat));
                setWidth(s, 7, 30);
                 }

            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
