/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import com.lowagie.text.pdf.codec.Base64.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.format.Colour;
import jxl.format.Pattern;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.AssessedPWDDetailsDTO;
import org.bf.disability.form.AssessedPWDDetailsForm;
import org.bf.disability.service.AppletAuthorityService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;
import org.apache.commons.beanutils.BeanUtils;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author 728056
 */
public class ExportToExcelAllDataAction extends DispatchAction {

    private static final String SUCCESS = "success";
    DataSource ds = null;

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        AssessedPWDDetailsForm assessedPWDDetailsForm = (AssessedPWDDetailsForm) form;
        HttpSession session = request.getSession();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ArrayList mandalList = territoryservice.getMandals(ds, session.getAttribute("districtId").toString());

            assessedPWDDetailsForm.setMandallist(mandalList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward getVillages(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        AssessedPWDDetailsForm assessedPWDDetailsForm = (AssessedPWDDetailsForm) form;
        HttpSession session = request.getSession();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String distId = session.getAttribute("districtId").toString();
            ArrayList mandalList = territoryservice.getMandals(ds, distId);
            ArrayList villagesList = territoryservice.getVillages(ds, distId, assessedPWDDetailsForm.getMandal_id(), assessedPWDDetailsForm.getMandal_id());
            assessedPWDDetailsForm.setMandallist(mandalList);
            assessedPWDDetailsForm.setVillagelist(villagesList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward getRequestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        AssessedPWDDetailsForm assessedPWDDetailsForm = (AssessedPWDDetailsForm) form;
        HttpSession session = request.getSession();
        String distId = session.getAttribute("districtId").toString();

        AssessedPWDDetailsDTO assessedPWDDetailsDTO = new AssessedPWDDetailsDTO();
        BeanUtils.copyProperties(assessedPWDDetailsDTO, assessedPWDDetailsForm);
        assessedPWDDetailsDTO.setDistrict_id(distId);
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
        ArrayList dataList = appletAuthorityService.getAllPersonalData(ds, assessedPWDDetailsDTO);

        BeanUtils.copyProperties(assessedPWDDetailsForm, assessedPWDDetailsDTO);

        ArrayList mandalList = territoryservice.getMandals(ds, distId);
        ArrayList villagesList = territoryservice.getVillages(ds, distId, assessedPWDDetailsDTO.getMandal_id(), assessedPWDDetailsDTO.getMandal_id());
        assessedPWDDetailsForm.setMandallist(mandalList);
        assessedPWDDetailsForm.setVillagelist(villagesList);

        request.setAttribute("district", distId);
        request.setAttribute("mandal", assessedPWDDetailsDTO.getMandal_id());
        request.setAttribute("village", assessedPWDDetailsDTO.getVillage_id());
        request.setAttribute("pensionPhase", assessedPWDDetailsDTO.getPensionPhase());
        request.setAttribute("fromDate", assessedPWDDetailsDTO.getFromdate());
        request.setAttribute("toDate", assessedPWDDetailsDTO.getTodate());

        request.setAttribute("alldataListSize", dataList.size());

        return mapping.findForward(SUCCESS);
    }

    public ActionForward exportReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }

        AssessedPWDDetailsDTO assessedPWDDetailsDTO = new AssessedPWDDetailsDTO();
        assessedPWDDetailsDTO.setDistrict_id(request.getParameter("district"));
        assessedPWDDetailsDTO.setMandal_id(request.getParameter("mandal"));
        assessedPWDDetailsDTO.setVillage_id(request.getParameter("village"));
        assessedPWDDetailsDTO.setPensionPhase(request.getParameter("pensionPhase"));
        assessedPWDDetailsDTO.setFromdate(request.getParameter("fromDate"));
        assessedPWDDetailsDTO.setTodate(request.getParameter("toDate"));
        AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
        ArrayList<HashMap> dataList = appletAuthorityService.getAllPersonalData(ds, assessedPWDDetailsDTO);
        TerritoryDAO territoryDAO = new TerritoryDAO();
        String districtName = territoryDAO.getDistrictsName(ds, request.getParameter("district"));
        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + districtName + ".xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            ArrayList headerList = new ArrayList();

            if (dataList.size() == 0) {

                s.addCell(new Label(0, 0, "No Data Found................"));
            } else {
                headerList.add("S No");
                headerList.add("SADAREM Id");
                headerList.add("Pension Id");
                headerList.add("Name");
                headerList.add("Gender");
                headerList.add("Caste");
                headerList.add("Age");
                headerList.add("Marital Status");
                headerList.add("Relation Name");
                headerList.add("Education");
                headerList.add("Rationcard Number");
                headerList.add("Disability");
                headerList.add("Disability %");
                headerList.add("Phase");
                headerList.add("Mandal");
                headerList.add("Village");
                headerList.add("Medical Board");
                headerList.add("Medical Board Address");
                headerList.add("Assessed Doctor");
                headerList.add("Doctor RegNo");
                headerList.add("Doctor Designation");
                headerList.add("Assessed Date");
                headerList.add("Person Status");
                headerList.add("Category");
                headerList.add("Login");


                addHeaders(s, headerList);

            }

            int x = 2;
            for (int i = 0; i < dataList.size(); i++, x++) {

                int j = 0;
                HashMap map = dataList.get(i);

                s.addCell(new Label(j++, x, i + 1 + ""));
                s.addCell(new Label(j++, x, map.get("PERSONCODE").toString()));
                s.addCell(new Label(j++, x, map.get("PENSIONID").toString()));
                s.addCell(new Label(j++, x, map.get("PERSONNAME").toString()));
                s.addCell(new Label(j++, x, map.get("GENDER").toString()));
                s.addCell(new Label(j++, x, map.get("CASTE").toString()));
                s.addCell(new Label(j++, x, map.get("AGE").toString()));
                s.addCell(new Label(j++, x, map.get("MARITALSTATUS").toString()));
                s.addCell(new Label(j++, x, map.get("RELATIONNAME").toString()));
                s.addCell(new Label(j++, x, map.get("EDUCATION").toString()));
                s.addCell(new Label(j++, x, map.get("RATIONCARD").toString()));
                s.addCell(new Label(j++, x, map.get("DISABILITY").toString()));
                s.addCell(new Label(j++, x, map.get("PERCENTAGE").toString()));
                s.addCell(new Label(j++, x, map.get("PENSIONPHASE").toString()));
                s.addCell(new Label(j++, x, map.get("MANDAL").toString()));
                s.addCell(new Label(j++, x, map.get("VILLAGE").toString()));
//                s.addCell(new Label(j++, x, map.get("Habitation").toString()));
                s.addCell(new Label(j++, x, map.get("MEDICALBOARD").toString()));
                s.addCell(new Label(j++, x, map.get("MEDICALBOARDADDRESS").toString()));
                s.addCell(new Label(j++, x, map.get("DOCTOR").toString()));
                s.addCell(new Label(j++, x, map.get("REGNUMBER").toString()));
                s.addCell(new Label(j++, x, map.get("DESIGNATION").toString()));
                s.addCell(new Label(j++, x, map.get("ASSESSEDDATE").toString()));
                s.addCell(new Label(j++, x, map.get("REASONFORPERSONSTATUS").toString()));
                s.addCell(new Label(j++, x, map.get("CATEGORY").toString()));
                s.addCell(new Label(j++, x, map.get("Login_ID").toString()));

                if (i == (50000) * page) {
                    s.addCell(new Label(0, x + 2, "", getCellFormat(Colour.RED, Pattern.GRAY_25))); 
                    s.addCell(new Label(1, x + 2, "For More Records Please see the next sheet", getCellFormat(Colour.RED, Pattern.GRAY_25)));
                    s.addCell(new Label(2, x + 2, "", getCellFormat(Colour.RED, Pattern.GRAY_25)));
                    s.addCell(new Label(3, x + 2, "", getCellFormat(Colour.RED, Pattern.GRAY_25)));
                    s.addCell(new Label(4, x + 2, "", getCellFormat(Colour.RED, Pattern.GRAY_25)));
                    s.addCell(new Label(5, x + 2, "", getCellFormat(Colour.RED, Pattern.GRAY_25)));

                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    addHeaders(s, headerList);
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
                out.close();
            }
        }
        return null;
    }

    public static void addHeaders(WritableSheet s, ArrayList list) {

        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 0, list.get(x).toString()));
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static WritableCellFormat getCellFormat(Colour colour, Pattern pattern) throws WriteException {
        WritableFont cellFont = new WritableFont(WritableFont.TIMES, 16);
        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
        cellFormat.setBackground(colour, pattern);
        return cellFormat;
    }
}
