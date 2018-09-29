/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.FunctionalNeedReportDAO;
import org.bf.disability.form.PhysicalRequirementsDetailsForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.service.PhysicalRequirementsDetailsService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.servicefactory.PhysicalRequirementsDetailsServiceFactory;

/**
 *
 * @author 693461
 */
public class PhysicalRequirementsDetailsAction extends DispatchAction {

    /* forward name="success" path="" */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        DataSource ds = null;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        PhysicalRequirementsDetailsForm physicalRequirementsDetailsForm = (PhysicalRequirementsDetailsForm) form;
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            PhysicalRequirementsDetailsService physicalRequirementsDetailsService =
                    PhysicalRequirementsDetailsServiceFactory.getPhysicalRequirementsDetailsServiceImpl();
            if (request.getParameter("district_id") == null) {
                districtId = "00";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "00";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "00";
            } else {
                villageId = request.getParameter("village_id");
            }
            ArrayList physicalRequiremenList = physicalRequirementsDetailsService.getphysicalRequirementDetails(ds, districtId, mandalId, villageId);
            if (physicalRequiremenList.isEmpty()) {
                request.setAttribute("msg", "No DataFound");
            } else {
                request.setAttribute("physicalRequiremenList", physicalRequiremenList);
            }
            request.setAttribute("ExcelHeader", "District");
            request.setAttribute("district", "district");
            String name = dao.getDistMandVilHabname(ds, physicalRequirementsDetailsForm.getDistrict_id(), physicalRequirementsDetailsForm.getMandal_id(), physicalRequirementsDetailsForm.getVillage_id(), "0");
            request.setAttribute("names", name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    public ActionForward getReportDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        PhysicalRequirementsDetailsForm physicalRequirementsDetailsForm = (PhysicalRequirementsDetailsForm) form;
        DataSource ds = null;
        ArrayList physicalRequiremenList = new ArrayList();
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("district_id") == null) {
                districtId = "00";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "00";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "00";
            } else {
                villageId = request.getParameter("village_id");
            }
            PhysicalRequirementsDetailsService physicalRequirementsDetailsService =
                    PhysicalRequirementsDetailsServiceFactory.getPhysicalRequirementsDetailsServiceImpl();
            physicalRequiremenList = physicalRequirementsDetailsService.getphysicalRequirementDetails(ds, districtId, mandalId, villageId);
            if (physicalRequiremenList.isEmpty()) {
                request.setAttribute("msg", "No DataFound");
            } else {
                request.setAttribute("physicalRequiremenList", physicalRequiremenList);
            }
            if (districtId.equals("00") && mandalId.equals("00") && villageId.equals("00")) {
                request.setAttribute("district", "district");
                request.setAttribute("ExcelHeader", "District");
            } else if (!districtId.equals("00") && mandalId.equals("00") && villageId.equals("00")) {
                request.setAttribute("mandal", "mandal");
                request.setAttribute("ExcelHeader", "Mandal");
            } else if (!districtId.equals("00") && !mandalId.equals("00") && villageId.equals("00")) {
                request.setAttribute("village", "village");
                request.setAttribute("ExcelHeader", "Village");
            } else if (!districtId.equals("00") && !mandalId.equals("00") && !villageId.equals("00")) {
                request.setAttribute("habitation", "habitation");
                request.setAttribute("ExcelHeader", "Habitation");
            }
            String name = dao.getDistMandVilHabname(ds, physicalRequirementsDetailsForm.getDistrict_id(), physicalRequirementsDetailsForm.getMandal_id(), physicalRequirementsDetailsForm.getVillage_id(), "0");
            request.setAttribute("names", name);
            if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Excel")) {
                target = "excel";
            } else if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Print")) {
                target = "print";
            } else {
                target = "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getphysicalRequirementPersonalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        HttpSession session = request.getSession();
        String target = "success";
        DataSource ds = null;
        ArrayList personalList = new ArrayList();
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String refId = null;
        String districtName = request.getParameter("dname");
        request.setAttribute("districtName", districtName);
        String mandalName = request.getParameter("mname");
        request.setAttribute("mandalName", mandalName);
        String villageName = request.getParameter("vname");
        request.setAttribute("villageName", villageName);

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            PhysicalRequirementsDetailsService physicalRequirementsDetailsService =
                    PhysicalRequirementsDetailsServiceFactory.getPhysicalRequirementsDetailsServiceImpl();

            if (request.getParameter("dID").equals("")) {
                districtId = "00";
            } else {
                districtId = request.getParameter("dID");
            }
            if (request.getParameter("mID").equals("")) {
                mandalId = "00";
            } else {
                mandalId = request.getParameter("mID");
            }
            if (request.getParameter("vID").equals("")) {
                villageId = "00";
            } else {
                villageId = request.getParameter("vID");
            }
            personalList = physicalRequirementsDetailsService.getphysicalRequirementPersonalDetails(ds, districtId, mandalId, villageId);
            if (personalList.size() > 0) {
                request.setAttribute("personalList", personalList);
                session.setAttribute("personalListData", personalList);
                target = "personalDetails";
            }
            if (personalList.isEmpty()) {
                request.setAttribute("msg", "No Data Found");
            }
            if (request.getParameter("dID").equals("")) {
                districtId = "0";
            } else {
                districtId = request.getParameter("dID");
            }
            if (request.getParameter("mID").equals("")) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mID");
            }
            if (request.getParameter("vID").equals("")) {
                villageId = "0";
            } else {
                villageId = request.getParameter("vID");
            }
             String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
            request.setAttribute("names", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    // To populate Data in Excel.
    public ActionForward getphysicalRequirementReportExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        String target = "getphysicalRequirementReportExcel";

        PhysicalRequirementsDetailsForm physicalRequirementsDetailsForm = (PhysicalRequirementsDetailsForm) form;
        DataSource ds = null;

        ArrayList physicalRequiremenListExcel = new ArrayList();

        String districtId = null;
        String mandalId = null;
        String villageId = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            PhysicalRequirementsDetailsService physicalRequirementsDetailsService =
                    PhysicalRequirementsDetailsServiceFactory.getPhysicalRequirementsDetailsServiceImpl();


            if (request.getParameter("dID") != null) {
                districtId = request.getParameter("dID");

            } else {
                districtId = "00";

            }

            if (request.getParameter("mID") != null) {
                mandalId = request.getParameter("mID");

            } else {
                mandalId = "00";

            }

            if (request.getParameter("vID") != null) {
                villageId = request.getParameter("vID");

            } else {
                villageId = "00";

            }
            physicalRequiremenListExcel = physicalRequirementsDetailsService.getphysicalRequirementDetails(ds, districtId, mandalId, villageId);
            request.setAttribute("physicalRequiremenListExcel", physicalRequiremenListExcel);

            if (physicalRequiremenListExcel.isEmpty()) {
                request.setAttribute("msg", "No DataFound");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    //To Print the Report
    public ActionForward getphysicalRequirementReportPrint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        String target = "getphysicalRequirementReportPrint";

        PhysicalRequirementsDetailsForm physicalRequirementsDetailsForm = (PhysicalRequirementsDetailsForm) form;
        DataSource ds = null;

        ArrayList physicalRequiremenListExcel = new ArrayList();

        String districtId = null;
        String mandalId = null;
        String villageId = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            PhysicalRequirementsDetailsService physicalRequirementsDetailsService =
                    PhysicalRequirementsDetailsServiceFactory.getPhysicalRequirementsDetailsServiceImpl();


            if (request.getParameter("dID") != null) {
                districtId = request.getParameter("dID");

            } else {
                districtId = "00";

            }

            if (request.getParameter("mID") != null) {
                mandalId = request.getParameter("mID");

            } else {
                mandalId = "00";

            }

            if (request.getParameter("vID") != null) {
                villageId = request.getParameter("vID");

            } else {
                villageId = "00";

            }

            physicalRequiremenListExcel = physicalRequirementsDetailsService.getphysicalRequirementDetails(ds, districtId, mandalId, villageId);
            request.setAttribute("physicalRequiremenListPrint", physicalRequiremenListExcel);
            request.setAttribute("physicalRequirementReportPrint", physicalRequiremenListExcel);

            if (physicalRequiremenListExcel.isEmpty()) {
                request.setAttribute("msg", "No DataFound");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getrep(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {



        return mapping.findForward("success");
    }
    //To Populate Individual Excel

    /* public ActionForward getphysicalRequirementIndividualExcel(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    HttpSession session = request.getSession();

    String target = "getphysicalRequirementIndividualExcel";

    PhysicalRequirementsDetailsForm physicalRequirementsDetailsForm = (PhysicalRequirementsDetailsForm) form;
    DataSource ds = null;         ds = getDataSource(request);         if (ds == null || "null".equals(ds)) {             ds = JNDIDataSource.getConnection();         }
    ArrayList personalList = new ArrayList();
    String districtId = null;
    String mandalId = null;
    String villageId = null;

    String districtName = request.getParameter("dname");
    request.setAttribute("districtName", districtName);
    String mandalName = request.getParameter("mname");
    request.setAttribute("mandalName", mandalName);
    String villageName = request.getParameter("vname");
    request.setAttribute("villageName", villageName);

    try {
    ds = getDataSource(request);
    if (ds == null || "null".equals(ds)) {
    ds = JNDIDataSource.getConnection();
    }

    PhysicalRequirementsDetailsService physicalRequirementsDetailsService =
    PhysicalRequirementsDetailsServiceFactory.getPhysicalRequirementsDetailsServiceImpl();

    districtId = request.getParameter("individualIdValue");
    mandalId = request.getParameter("mID");
    villageId = request.getParameter("vID");



    personalList = physicalRequirementsDetailsService.getphysicalRequirementPersonalDetails(ds, districtId, mandalId, villageId);


    session.setAttribute("personalList", personalList);

    if(personalList.isEmpty()){
    request.setAttribute("msg","No Data Found");
    }

    } catch (Exception e) {
    e.printStackTrace();
    }
    return mapping.findForward(target);
    } */
}
