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
import org.bf.disability.dao.RejectedAadharMappingDAO;
import org.bf.disability.form.PercentageWiseReportForm;
import org.bf.disability.form.RejectedAadharMappingForm;
import org.bf.disability.service.PercentageWiseReportService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.PercentageWiseReportServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

public class RejectedAadharMappingAction extends DispatchAction {

    private static final String target = "success";
    DataSource ds = null;

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RejectedAadharMappingForm rejectedAadharMappingForm = (RejectedAadharMappingForm) form;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        HttpSession session = request.getSession();
        ArrayList districtlist = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if(rejectedAadharMappingForm.getFromdate()!=null && rejectedAadharMappingForm.getFromdate().equals("")){
                rejectedAadharMappingForm.setFromdate("01/11/2014");
            }

            districtlist = territoryservice.getDistricts(ds); 
            
            if (districtlist != null && districtlist.size() > 0) {
                rejectedAadharMappingForm.setDistrictlist(districtlist);
            }
            request.setAttribute("selFromDate", "01/11/2014");
            request.setAttribute("selToDate", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getAadharMappingDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       RejectedAadharMappingForm rejectedAadharMappingForm = (RejectedAadharMappingForm) form;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        RejectedAadharMappingDAO aadharMappingDAO=new RejectedAadharMappingDAO();
        HttpSession session = request.getSession();
        ArrayList districtlist = null;
        ArrayList mappingDetails = null;
        String districtName = null;
        String fromDate = rejectedAadharMappingForm.getFromdate();
        String toDate = rejectedAadharMappingForm.getTodate();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtlist = territoryservice.getDistricts(ds);
            rejectedAadharMappingForm.setDistrict_id(request.getParameter("district_id"));
            
            if (request.getParameter("district_id") != null && request.getParameter("district_id").length() > 0 && !request.getParameter("district_id").equalsIgnoreCase("null")) {
                rejectedAadharMappingForm.setDistrict_id(request.getParameter("district_id"));
            }
            districtName = territoryservice.getDistrictsName(ds, rejectedAadharMappingForm.getDistrict_id());
            if (districtName != null && districtName != "") {
                request.setAttribute("districtName", districtName);
            }
            if (districtlist != null && districtlist.size() > 0) {
                rejectedAadharMappingForm.setDistrictlist(districtlist);
            }

            mappingDetails = aadharMappingDAO.getAadharMappingDetails(rejectedAadharMappingForm, ds);
            if(mappingDetails.size()>0){
            request.setAttribute("mappingDetails", mappingDetails);
            }else{
            request.setAttribute("msg", "Rejected Aadhar details are not available!");
            }
            if(rejectedAadharMappingForm.getDistrict_id()!=null && rejectedAadharMappingForm.getDistrict_id()!="" && rejectedAadharMappingForm.getDistrict_id().equalsIgnoreCase("All")){
            request.setAttribute("District", "District");
            }
            if(rejectedAadharMappingForm.getDistrict_id()!=null && rejectedAadharMappingForm.getDistrict_id()!="" && !rejectedAadharMappingForm.getDistrict_id().equalsIgnoreCase("All")){
            request.setAttribute("Mandal", "Mandal");
            }
            
            request.setAttribute("selFromDate", fromDate);
            request.setAttribute("selToDate", toDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    


    public ActionForward excelWriting(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException, Exception {
        ArrayList mappingDetails = null;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        RejectedAadharMappingForm rejectedAadharMappingForm = (RejectedAadharMappingForm) form;
        RejectedAadharMappingDAO aadharMappingDAO=new RejectedAadharMappingDAO();
        PercentageWiseReportDAO percentageWiseReportDAO = new PercentageWiseReportDAO();
        DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();
        PercentageWiseReportService percentageWiseReportService = PercentageWiseReportServiceFactory.getPercentageWiseReportServiceImpl();
        HttpSession session = request.getSession();
        String fromDate = rejectedAadharMappingForm.getFromdate();
        String toDate = rejectedAadharMappingForm.getTodate();
        String mandalName = request.getParameter("mandalname");
        String districtName = request.getParameter("districtName");
        String villageName = request.getParameter("villagename");
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if(request.getParameter("districtid")!=null && request.getParameter("districtid")!=""){
        rejectedAadharMappingForm.setDistrict_id(request.getParameter("districtid"));
        }
        mappingDetails = aadharMappingDAO.getAadharMappingDetails(rejectedAadharMappingForm, ds);
        if(mappingDetails.size()>0){
            request.setAttribute("mappingDetails", mappingDetails);
            }else{
            request.setAttribute("msg", "Rejected Aadhar details are not available!");
            }
        } catch (Exception e) {
        }
        
        unspecified(mapping, form, request, response);
        exportExcel(mappingDetails, form, request, response);
        return null;
    }
//
    public static void exportExcel(ArrayList dataList, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        RejectedAadharMappingForm rejectedAadharMappingForm = (RejectedAadharMappingForm) form;

        String fromdate = (String) request.getParameter("fromdate");
        String todate = (String) request.getParameter("todate");

        String districtName = (String) request.getParameter("districtName");
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=20-39 Rejected AADHAR Mapping Report.xls");

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
                s.addCell(new Label(0, 0, "Rejected AADHAR Mapping details are not available................"));
            } else {

                headerList.add("S.No");
                if(rejectedAadharMappingForm.getDistrict_id()!=null && rejectedAadharMappingForm.getDistrict_id()!="" && rejectedAadharMappingForm.getDistrict_id().equalsIgnoreCase("All")){
                    headerList.add("District");
                } else if(rejectedAadharMappingForm.getDistrict_id()!=null && rejectedAadharMappingForm.getDistrict_id()!="" && !rejectedAadharMappingForm.getDistrict_id().equalsIgnoreCase("All")){
                    headerList.add("Mandal");
                } 

                headerList.add("Target");
                headerList.add("Mapped");
                headerList.add("Pending");
                headerList.add("Mapped Percentage(%)");
                s.mergeCells(0, 0, 5, 0);
                if(rejectedAadharMappingForm.getDistrict_id()!=null && rejectedAadharMappingForm.getDistrict_id()!="" && rejectedAadharMappingForm.getDistrict_id().equalsIgnoreCase("All")){
                    s.addCell(new Label(0, 0, "Rejected AADHAR Mapping Report -  status as on  "
                            + fromdate + " To " + todate + "   District: ALL " , cellFormat));
                }else if(rejectedAadharMappingForm.getDistrict_id()!=null && rejectedAadharMappingForm.getDistrict_id()!="" && !rejectedAadharMappingForm.getDistrict_id().equalsIgnoreCase("All")){
                s.addCell(new Label(0, 0, "Rejected AADHAR Mapping Report -  status as on  "
                            + fromdate + " To " + todate + "   District: " + districtName, cellFormat));
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
                if(rejectedAadharMappingForm.getDistrict_id()!=null && rejectedAadharMappingForm.getDistrict_id()!="" && rejectedAadharMappingForm.getDistrict_id().equalsIgnoreCase("All")){
                    s.addCell(new Label(j++, x, m.get("districtname").toString(), cell));
                } else if(rejectedAadharMappingForm.getDistrict_id()!=null && rejectedAadharMappingForm.getDistrict_id()!="" && !rejectedAadharMappingForm.getDistrict_id().equalsIgnoreCase("All")){
                    s.addCell(new Label(j++, x, m.get("mandalname").toString(), cell));
                } 
                s.addCell(new Label(j++, x, m.get("totalTarget1").toString(), cell));
                s.addCell(new Label(j++, x, m.get("mappedcount").toString(), cell));
                s.addCell(new Label(j++, x, m.get("target").toString(), cell));
                s.addCell(new Label(j++, x, m.get("mappedPercentage").toString(), cell));

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
            s.addCell(new Label(2, x, m.get("totalTargetCount").toString(), cellFormat));
            s.addCell(new Label(3, x, m.get("totalmappedcount").toString(), cellFormat));
            s.addCell(new Label(4, x, m.get("totaltarget").toString(), cellFormat));
            s.addCell(new Label(5, x, m.get("TotalmappedPercentage").toString(), cellFormat));
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
        for (int i = 0; i < 8; i++) {
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
                s.addCell(new Label(x, 1, list.get(x).toString(), cellFormat));
                setWidth(s, 7, 30);
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }
}
