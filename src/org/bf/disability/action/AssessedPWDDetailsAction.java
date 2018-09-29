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
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.AssessedPWDDetailsDTO;
import org.bf.disability.form.AssessedPWDDetailsForm;
import org.bf.disability.service.DailyDisabilityAndPercentageService;
import org.bf.disability.servicefactory.DailyDisabilityAndPercentageServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 728056
 */
public class AssessedPWDDetailsAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    DataSource ds = null;

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
      
        AssessedPWDDetailsForm assessedPWDDetailsForm = (AssessedPWDDetailsForm) form;
        HttpSession session = request.getSession();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("districtId") != null && session.getAttribute("roleId") != null) {
                int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
                if (currentRoleId != CommonConstants.PHASEPWDREPORTROLE) {
                    String disid = session.getAttribute("districtId").toString();
                    TerritoryDAO territoryDAO = new TerritoryDAO();
                    String districtName = territoryDAO.getDistrictsName(ds, disid);
                    assessedPWDDetailsForm.setDistrict_id(disid);
                    assessedPWDDetailsForm.setDistrict_name(districtName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getResults(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {


        String target = null;

        AssessedPWDDetailsForm assessedPWDDetailsForm = (AssessedPWDDetailsForm) form;
        AssessedPWDDetailsDTO assessedPWDDetailsDTO = new AssessedPWDDetailsDTO();
        DailyDisabilityAndPercentageService dailyreportservice =
                DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();

        request.setAttribute("typeOfSearchValue", assessedPWDDetailsForm.getTypeOfSearch());
        request.setAttribute("distId", assessedPWDDetailsForm.getDistrict_id());
        request.setAttribute("mandalId", assessedPWDDetailsForm.getMandal_id());
        request.setAttribute("panchaytId", assessedPWDDetailsForm.getPanchayat_id());
        request.setAttribute("villId", assessedPWDDetailsForm.getVillage_id());

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            BeanUtils.copyProperties(assessedPWDDetailsDTO, assessedPWDDetailsForm);
            ArrayList partBList = null;
            partBList = new ArrayList();
            partBList = dailyreportservice.statusreportforPartBModified(ds, assessedPWDDetailsDTO);
            ArrayList districtList = dailyreportservice.setDistrictList(ds, assessedPWDDetailsDTO);
            assessedPWDDetailsDTO.setDistrictlist(districtList);
            ArrayList mandalList = dailyreportservice.setMandalList(ds, assessedPWDDetailsDTO);
            ArrayList pancytList = dailyreportservice.setPanchayatList(ds, assessedPWDDetailsDTO);
            ArrayList villageList = dailyreportservice.setVillageList(ds, assessedPWDDetailsDTO);
            assessedPWDDetailsDTO.setMandallist(mandalList);
            assessedPWDDetailsDTO.setPanchayatList(pancytList);
            assessedPWDDetailsDTO.setVillagelist(villageList);
            BeanUtils.copyProperties(assessedPWDDetailsForm, assessedPWDDetailsDTO);
            if (partBList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
            } else {
                request.setAttribute("dailyreportlist", partBList);
            }

            target = "view";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward excelWriting(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        HttpSession session = request.getSession();
        ArrayList dailyreportlist = (ArrayList) session.getAttribute("partBDistrictReport");
        exportExcel(dailyreportlist, request, response);

        return null;
    }

    public static void exportExcel(ArrayList dataList, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        String fromdate = (String) request.getParameter("fromdate");
        String todate = (String) request.getParameter("todate");
        String pensionPhase = (String) request.getParameter("pensionPhase");
        String district_id = (String) request.getParameter("dID");
        String mandal_id = (String) request.getParameter("mID");
        String dName = (String) request.getParameter("dN");
        String mName = (String) request.getParameter("mN");
        String panchayat_id = (String) request.getParameter("pID");
        String village_id = (String) request.getParameter("vID");
        String pName = (String) request.getParameter("pName");
        String vName = (String) request.getParameter("vName");
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=sadaremStatusReportExcel.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);

            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "No Data Found................"));
            } else {

                headerList.add("S No");
                if (village_id != null && !village_id.equals("null") && !village_id.equals("0")) {
                    headerList.add("Habitation");
                } else if (panchayat_id != null && !panchayat_id.equals("null") && !panchayat_id.equals("0")) {
                    headerList.add("Village");
                } else if (mandal_id != null && !mandal_id.equals("null") && !mandal_id.equals("0")) {
                    headerList.add("Panchayat");
                } else if (district_id != null && !district_id.equals("null") && !district_id.equals("0")) {
                    headerList.add("Mandal");
                } else if (district_id != null) {
                    headerList.add("District");
                }
                headerList.add("Total Pensioners(Live,ABH ,Not ELG,Suspend,Death TM & PM)");
                headerList.add("Under Go SADAREM(Live,ABH ,Not ELG)");
                headerList.add("Total Assessed");
                headerList.add("Eligible");
                headerList.add("Before Assment (No visible disb.)");
                headerList.add("After Assment (<40% disb.)");
                headerList.add("Total Rejected");
                headerList.add("To Be Assessed");
                //mergeCells(int col1, int row1, int col2, int row2)
                s.mergeCells(0, 0, 0, 0);
                s.mergeCells(1, 0, 15, 0);

                s.addCell(new Label(1, 0, "SADAREM  Phase: " + pensionPhase + " - Assessment status as on "
                        + fromdate + " To " + todate + " District: " + dName + ", Mandal: " + mName + ",Panchayat: " + pName + ",Village: " + vName));

                addHeaders(s, headerList);
            }
            int underGoSadarem = 0, toBeAssesed = 0;
            int beforeAssest = 0, existingPensioners = 0;
            int total = 0, directRejected = 0;
            int assessedAndRejected = 0, totalRejected = 0;
            int x = 2;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                Map m = (Map) dataList.get(i);
                existingPensioners = existingPensioners + Integer.parseInt(m.get("ExistingPensioners").toString());
                underGoSadarem = underGoSadarem + Integer.parseInt(m.get("underGoSadarem").toString());
                total = total + Integer.parseInt(m.get("total").toString());
                beforeAssest = beforeAssest + Integer.parseInt(m.get("beforeAssest").toString());
                directRejected = directRejected + Integer.parseInt(m.get("directrejected").toString());
                assessedAndRejected = assessedAndRejected + Integer.parseInt(m.get("assessedandrejected").toString());
                totalRejected = totalRejected + Integer.parseInt(m.get("totalRejected").toString());
                toBeAssesed = toBeAssesed + Integer.parseInt(m.get("toBeAssesed").toString());

                s.addCell(new Label(j++, x, i + 1 + ""));

                if (m.get("district") != null) {
                    s.addCell(new Label(j++, x, m.get("district").toString()));
                } else if (m.get("mandal") != null) {
                    s.addCell(new Label(j++, x, m.get("mandal").toString()));
                } else if (m.get("panchayat_name") != null) {
                    s.addCell(new Label(j++, x, m.get("panchayat_name").toString()));
                } else if (m.get("townVillage") != null) {
                    s.addCell(new Label(j++, x, m.get("townVillage").toString()));
                } else if (m.get("habitation_name") != null) {
                    s.addCell(new Label(j++, x, m.get("habitation_name").toString()));
                }
                s.addCell(new Label(j++, x, m.get("ExistingPensioners").toString()));
                s.addCell(new Label(j++, x, m.get("underGoSadarem").toString()));
                s.addCell(new Label(j++, x, m.get("total").toString()));
                s.addCell(new Label(j++, x, m.get("beforeAssest").toString()));
                s.addCell(new Label(j++, x, m.get("directrejected").toString()));
                s.addCell(new Label(j++, x, m.get("assessedandrejected").toString()));
                s.addCell(new Label(j++, x, m.get("totalRejected").toString()));
                s.addCell(new Label(j++, x, m.get("toBeAssesed").toString()));

                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;

                    addHeaders(s, headerList);
                }
                if (i == dataList.size() - 1) {
                    j = 0;
                    x = x + 1;
                    s.addCell(new Label(j++, x, ""));
                    s.addCell(new Label(j++, x, "Total"));

                    s.addCell(new Label(j++, x, existingPensioners + ""));
                    s.addCell(new Label(j++, x, underGoSadarem + ""));
                    s.addCell(new Label(j++, x, total + ""));
                    s.addCell(new Label(j++, x, beforeAssest + ""));
                    s.addCell(new Label(j++, x, directRejected + ""));
                    s.addCell(new Label(j++, x, assessedAndRejected + ""));
                    s.addCell(new Label(j++, x, totalRejected + ""));
                    s.addCell(new Label(j++, x, toBeAssesed + ""));
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

    public static void addHeaders(WritableSheet s, ArrayList list) {

        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 1, list.get(x).toString()));
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ActionForward getAllDataReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
      
        AssessedPWDDetailsForm assessedPWDDetailsForm = (AssessedPWDDetailsForm) form;
        HttpSession session = request.getSession();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            DailyDisabilityAndPercentageService dailyreportservice =
                    DailyDisabilityAndPercentageServiceFactory.getDailyDisabilityAndPercentageServiceImpl();
            AssessedPWDDetailsDTO assessedPWDDetailsDTO = new AssessedPWDDetailsDTO();

            if (assessedPWDDetailsForm.getMandal_id() != null) {
              
                String mandalId = assessedPWDDetailsForm.getMandal_id();
                String districtId = assessedPWDDetailsForm.getDistrict_id();
                ArrayList allDataList = dailyreportservice.getAllDataByMandal(ds, districtId, mandalId);
           
                exportAllDataExcel(allDataList, request, response);
            }
            if (session.getAttribute("districtId") != null) {
                String districtId = session.getAttribute("districtId").toString();
                assessedPWDDetailsDTO.setDistrict_id(districtId);
                ArrayList mandalList = dailyreportservice.setMandalList(ds, assessedPWDDetailsDTO);              
                assessedPWDDetailsForm.setMandallist(mandalList);
                assessedPWDDetailsForm.setDistrict_id(districtId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public static void exportAllDataExcel(ArrayList dataList, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;

        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=20-39 Percentage Assessed Report.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);

            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "No Data Found................"));
            } else {

                headerList.add("S No");
                headerList.add("Pension ID");
                headerList.add("SADAREM Code");
                headerList.add("Person Name");
                headerList.add("Gender");
                headerList.add("Education");
                headerList.add("Relation Name");
                headerList.add("Rationcard Number");
                headerList.add("Type of Disability");
                headerList.add("Total Disability");
                headerList.add("Reason for Person Status");
                headerList.add("Assessment Status");
                headerList.add("District Name");
                headerList.add("Mandal Name");
                headerList.add("Village Name");
                //mergeCells(int col1, int row1, int col2, int row2)
                // s.mergeCells(0, 0, 0, 0);
                // s.mergeCells(1, 0, 15, 0);


                addHeaders(s, headerList);
            }

            int x = 2;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                Map m = (Map) dataList.get(i);
                s.addCell(new Label(j++, x, i + 1 + ""));
                s.addCell(new Label(j++, x, m.get("PENSIONID").toString()));
                s.addCell(new Label(j++, x, m.get("SADAREMCODE").toString()));
                s.addCell(new Label(j++, x, m.get("PERSONNAME").toString()));
                s.addCell(new Label(j++, x, m.get("GENDER").toString()));
                s.addCell(new Label(j++, x, m.get("EDUCATION").toString()));
                s.addCell(new Label(j++, x, m.get("RELATIONNAME").toString()));
                s.addCell(new Label(j++, x, m.get("RATIONCARD_NUMBER").toString()));
                s.addCell(new Label(j++, x, m.get("TYPEOFDISABILITY").toString()));
                s.addCell(new Label(j++, x, m.get("TOTALDISABILITY").toString()));
                s.addCell(new Label(j++, x, m.get("REASONFORPERSONSTATUS").toString()));
                s.addCell(new Label(j++, x, m.get("ASSESSEMENTSTATUS").toString()));
                s.addCell(new Label(j++, x, m.get("district_name").toString()));
                s.addCell(new Label(j++, x, m.get("mandal_name").toString()));
                s.addCell(new Label(j++, x, m.get("village_name").toString()));

                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;

                    addHeaders(s, headerList);
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
}
