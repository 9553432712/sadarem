/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.form.GrievancesRequestDetailsForm;
import org.bf.disability.form.RDCallCentreStatusForm;
import org.bf.disability.service.GrievancesRequestDetailsService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.GrievancesRequestDetailsServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 728056
 */
public class RDCallCentrePensionStatusAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String target = "success";

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
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = getDataSource(request);
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        GrievancesRequestDetailsService grievancesRequestDetailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
        GrievancesRequestDetailsForm detailsForm = new GrievancesRequestDetailsForm();
         HttpSession session = request.getSession();
        try {
              int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            ArrayList categoryList = grievancesRequestDetailsService.getRequestName(ds, currentRoleId);
            RDCallCentreStatusForm rDCallCentreStatusForm = (RDCallCentreStatusForm) form;

            ArrayList districtlist = territoryservice.getDistricts(ds);
            rDCallCentreStatusForm.setCategoryList(categoryList);
            rDCallCentreStatusForm.setDistrictList(districtlist);
            request.setAttribute("fromdate", rDCallCentreStatusForm.getFromdate());
            request.setAttribute("todate", rDCallCentreStatusForm.getTodate());
            request.setAttribute("requestId", rDCallCentreStatusForm.getRequestId());
            request.setAttribute("districtId", rDCallCentreStatusForm.getDistrict_id());
            request.setAttribute("level", rDCallCentreStatusForm.getLevel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = getDataSource(request);
        RDCallCentreStatusForm rDCallCentreStatusForm = (RDCallCentreStatusForm) form;
        GrievancesRequestDetailsService grievancesRequestDetailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
        try {
            ArrayList statusList = grievancesRequestDetailsService.getRDCallCentreStatusReport(ds, rDCallCentreStatusForm);
            if (statusList.size() == 0) {
                request.setAttribute("msg", "No Records Found");
            } else {
                request.setAttribute("statusList", statusList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);

        return mapping.findForward(SUCCESS);
    }

    public ActionForward getPrint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = getDataSource(request);
        RDCallCentreStatusForm rDCallCentreStatusForm = (RDCallCentreStatusForm) form;
        try {
            rDCallCentreStatusForm.setFromdate(request.getParameter("fromdate"));
            rDCallCentreStatusForm.setTodate(request.getParameter("todate"));
            rDCallCentreStatusForm.setRequestId(request.getParameter("requestId"));
            rDCallCentreStatusForm.setDistrict_id(request.getParameter("districtId"));
            rDCallCentreStatusForm.setLevel(request.getParameter("level"));
            GrievancesRequestDetailsService grievancesRequestDetailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();

            ArrayList statusList = grievancesRequestDetailsService.getRDCallCentreStatusReport(ds, rDCallCentreStatusForm);
            request.setAttribute("statusList", statusList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("print");
    }

    public ActionForward getExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = getDataSource(request);
        RDCallCentreStatusForm rDCallCentreStatusForm = (RDCallCentreStatusForm) form;
        try {
            rDCallCentreStatusForm.setFromdate(request.getParameter("fromdate"));
            rDCallCentreStatusForm.setTodate(request.getParameter("todate"));
            rDCallCentreStatusForm.setRequestId(request.getParameter("requestId"));
            rDCallCentreStatusForm.setDistrict_id(request.getParameter("districtId"));
            rDCallCentreStatusForm.setLevel(request.getParameter("level"));
            GrievancesRequestDetailsService grievancesRequestDetailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();

            ArrayList statusList = grievancesRequestDetailsService.getRDCallCentreStatusReport(ds, rDCallCentreStatusForm);

            exportExcel(statusList, "totalReport", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public static void exportExcel(ArrayList dataList, String reportType, HttpServletResponse response) {
        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=RDCallCenterPensionStatusReport.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);

            if (dataList == null) {
                s.addCell(new Label(0, 0, "No Data Found................"));
            } else {

                if (reportType.equals("totalReport")) {


                    headerList.add("S.No.");
                    headerList.add("District");
                    headerList.add("Total Registered");
                    headerList.add("Rd Call centre Pending");
                    headerList.add("Rd Call centre Closed");
                    headerList.add("SADAREM Pending");
                    headerList.add("SADAREM Closed");
                    headerList.add("New Certificate");
                    headerList.add("Name change");
                    headerList.add("Relation name");
                    headerList.add("Date of birth");
                    headerList.add("Spelling mistake");
                    headerList.add("Identification marks Change");
                    headerList.add("Duplicate Certificate");
                    headerList.add("Reassessment");
                    headerList.add("Physical Requirements");
                    headerList.add("Renual Certificate");
                    headerList.add("Assessement completed but not Getting Certificate");
                    headerList.add("Not Getting Penion");
                    headerList.add("Having Certificate But not Getting Pension Like (Not Eligible)");
                    headerList.add("Having Certificate But not Getting Pension Like (Abayastam)");
                    headerList.add("Having Certificate But not Getting Pension Like (ICFS Delation)");
                    headerList.add("Having Certificate But not Getting Pension Like (Finger Print Problems)");
                    headerList.add("Alive But in the pension Dead");
                    headerList.add("Getting double pension");
                    headerList.add("Others");
                    //setWidth(s, 7, 20);

                }
                addHeaders(s, headerList);
            }
            int totalRegistered = 0;
            int rdcallCenterPending = 0;
            int rdcallCenterClosed = 0;
            int sadaremPending = 0;
            int sadaremClosed = 0;
            int newCertificate = 0;
            int nameChange = 0;
            int relationName = 0;
            int dateOfBirth = 0;
            int spellingMistake = 0;
            int identificationMarksChange = 0;
            int duplicateCertificate = 0;
            int reassessment = 0;
            int otherNeedsHIPhysicalRequirements = 0;
            int renualCertificate = 0;
            int assessementComptdNtGettingCertificate = 0;
            int notGettingPenion = 0;
            int notEligible = 0;
            int abayastam = 0;
            int iCFSDelation = 0;
            int fingerPrintProblems = 0;
            int aliveButpensionDead = 0;
            int gettingDoublePension = 0;
            int others = 0;
            Iterator iter = dataList.iterator();
            while (iter.hasNext()) {
                Map m = (Map) iter.next();
                totalRegistered = totalRegistered + Integer.parseInt(m.get("totalRegistered").toString());
                rdcallCenterPending = rdcallCenterPending + Integer.parseInt(m.get("rdcallCenterPending").toString());
                rdcallCenterClosed = rdcallCenterClosed + Integer.parseInt(m.get("rdcallCenterClosed").toString());
                sadaremPending = sadaremPending + Integer.parseInt(m.get("sadaremPending").toString());
                sadaremClosed = sadaremClosed + Integer.parseInt(m.get("sadaremClosed").toString());
                newCertificate = newCertificate + Integer.parseInt(m.get("NewCertificate").toString());
                nameChange = nameChange + Integer.parseInt(m.get("NameChange").toString());
                relationName = relationName + Integer.parseInt(m.get("RelationName").toString());
                dateOfBirth = dateOfBirth + Integer.parseInt(m.get("DateOfBirth").toString());
                spellingMistake = spellingMistake + Integer.parseInt(m.get("SpellingMistake").toString());
                identificationMarksChange = identificationMarksChange + Integer.parseInt(m.get("IdentificationMarksChange").toString());
                duplicateCertificate = duplicateCertificate + Integer.parseInt(m.get("DuplicateCertificate").toString());
                reassessment = reassessment + Integer.parseInt(m.get("Reassessment").toString());
                otherNeedsHIPhysicalRequirements = otherNeedsHIPhysicalRequirements + Integer.parseInt(m.get("PhysicalRequirements").toString());
                renualCertificate = renualCertificate + Integer.parseInt(m.get("RenualCertificate").toString());
                assessementComptdNtGettingCertificate = assessementComptdNtGettingCertificate + Integer.parseInt(m.get("AssessementComptdNtGettingCertificate").toString());
                notGettingPenion = notGettingPenion + Integer.parseInt(m.get("NotGettingPenion").toString());
                notEligible = notEligible + Integer.parseInt(m.get("NotEligible").toString());
                abayastam = abayastam + Integer.parseInt(m.get("Abayastam").toString());
                iCFSDelation = iCFSDelation + Integer.parseInt(m.get("ICFSDelation").toString());
                fingerPrintProblems = fingerPrintProblems + Integer.parseInt(m.get("FingerPrintProblems").toString());
                aliveButpensionDead = aliveButpensionDead + Integer.parseInt(m.get("AliveButpensionDead").toString());
                gettingDoublePension = gettingDoublePension + Integer.parseInt(m.get("GettingDoublePension").toString());
                others = others + Integer.parseInt(m.get("Others").toString());
            }
            int x = 2;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                Map map = (Map) dataList.get(i);

                s.addCell(new Label(j++, x, i + 1 + ""));
                if (reportType.equals("totalReport")) {

                    s.addCell(new Label(j++, x, map.get("districtName") + ""));
                    s.addCell(new Label(j++, x, map.get("totalRegistered").toString()));
                    s.addCell(new Label(j++, x, map.get("rdcallCenterPending").toString()));
                    s.addCell(new Label(j++, x, map.get("rdcallCenterClosed").toString()));
                    s.addCell(new Label(j++, x, map.get("sadaremPending").toString()));
                    s.addCell(new Label(j++, x, map.get("sadaremClosed").toString()));
                    s.addCell(new Label(j++, x, map.get("NewCertificate").toString()));
                    s.addCell(new Label(j++, x, map.get("NameChange").toString()));
                    s.addCell(new Label(j++, x, map.get("RelationName").toString()));
                    s.addCell(new Label(j++, x, map.get("DateOfBirth").toString()));
                    s.addCell(new Label(j++, x, map.get("SpellingMistake").toString()));
                    s.addCell(new Label(j++, x, map.get("IdentificationMarksChange").toString()));
                    s.addCell(new Label(j++, x, map.get("DuplicateCertificate").toString()));
                    s.addCell(new Label(j++, x, map.get("Reassessment").toString()));
                    s.addCell(new Label(j++, x, map.get("PhysicalRequirements").toString()));
                    s.addCell(new Label(j++, x, map.get("RenualCertificate").toString()));
                    s.addCell(new Label(j++, x, map.get("AssessementComptdNtGettingCertificate").toString()));
                    s.addCell(new Label(j++, x, map.get("NotGettingPenion").toString()));
                    s.addCell(new Label(j++, x, map.get("NotEligible").toString()));
                    s.addCell(new Label(j++, x, map.get("Abayastam").toString()));
                    s.addCell(new Label(j++, x, map.get("ICFSDelation").toString()));
                    s.addCell(new Label(j++, x, map.get("FingerPrintProblems").toString()));
                    s.addCell(new Label(j++, x, map.get("AliveButpensionDead").toString()));
                    s.addCell(new Label(j++, x, map.get("GettingDoublePension").toString()));
                    s.addCell(new Label(j++, x, map.get("Others").toString()));
                }

                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;
                    addHeaders(s, headerList);
                }
                if (i == dataList.size() - 1) {
                    j = 0;
                    x = ++x;
                    s.addCell(new Label(j++, x, "                                  Total"));
                    s.addCell(new Label(j++, x, "  "));
                    s.mergeCells(0, x, 1, 0);
                    s.addCell(new Label(j++, x, totalRegistered + ""));
                    s.addCell(new Label(j++, x, rdcallCenterPending + ""));
                    s.addCell(new Label(j++, x, rdcallCenterClosed + ""));
                    s.addCell(new Label(j++, x, sadaremPending + ""));
                    s.addCell(new Label(j++, x, sadaremClosed + ""));
                    s.addCell(new Label(j++, x, newCertificate + ""));
                    s.addCell(new Label(j++, x, nameChange + ""));
                    s.addCell(new Label(j++, x, relationName + ""));
                    s.addCell(new Label(j++, x, dateOfBirth + ""));
                    s.addCell(new Label(j++, x, spellingMistake + ""));
                    s.addCell(new Label(j++, x, identificationMarksChange + ""));
                    s.addCell(new Label(j++, x, duplicateCertificate + ""));
                    s.addCell(new Label(j++, x, reassessment + ""));
                    s.addCell(new Label(j++, x, otherNeedsHIPhysicalRequirements + ""));
                    s.addCell(new Label(j++, x, renualCertificate + ""));
                    s.addCell(new Label(j++, x, assessementComptdNtGettingCertificate + ""));
                    s.addCell(new Label(j++, x, notGettingPenion + ""));
                    s.addCell(new Label(j++, x, notEligible + ""));
                    s.addCell(new Label(j++, x, abayastam + ""));
                    s.addCell(new Label(j++, x, iCFSDelation + ""));
                    s.addCell(new Label(j++, x, fingerPrintProblems + ""));
                    s.addCell(new Label(j++, x, aliveButpensionDead + ""));
                    s.addCell(new Label(j++, x, gettingDoublePension + ""));
                    s.addCell(new Label(j++, x, others + ""));

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

    public static void addHeaders(WritableSheet s, ArrayList list) {

        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 0, list.get(x).toString()));
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
            setWidth(s, x, 20);
        }
    }
}
