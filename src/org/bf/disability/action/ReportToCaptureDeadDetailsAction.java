/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

/**
 *
 * @author 310926
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.ReportToCaptureDeadDetailsDAO;
import org.bf.disability.form.ReportToCaptureDeadDetailsForm;
import org.bf.disability.service.ReportToCaptureDeadDetailsService;
import org.bf.disability.servicefactory.ReportToCaptureDeadDetailsServiceFactory;

public class ReportToCaptureDeadDetailsAction extends DispatchAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String target = "success";
        return mapping.findForward(target);
    }

    public ActionForward validateSADAREMID(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        DataSource ds = null;
        HttpSession session = null;
        ArrayList SADAREMIDValidDetails = new ArrayList();
        boolean districtLevelAccessFlag = false;
        ArrayList voMappingList = new ArrayList();
        ArrayList shgMappingList = new ArrayList();
        String voIdData = "";
        int mappingCount = 0;
        String voName = null;
        ArrayList voList = new ArrayList();
        ArrayList shgDropDownDetails = new ArrayList();
        String editPwdStatus = "";

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);
            ReportToCaptureDeadDetailsDAO reportToCaptureDeadDetailsDAO = new ReportToCaptureDeadDetailsDAO();

            ReportToCaptureDeadDetailsForm reportCaptureDeadForm = (ReportToCaptureDeadDetailsForm) form;
            ReportToCaptureDeadDetailsService reportCaptureDeadService =
                    ReportToCaptureDeadDetailsServiceFactory.getReportToCaptureDeadDetailsServiceImpl();
            if (reportCaptureDeadForm.getPersoncode() != null) {
                CommonDetails commondetails = new CommonDetails();

                if (session.getAttribute("districtId") != null) {
                    reportCaptureDeadForm.setDistrict_id((String) session.getAttribute("districtId"));
                }
                districtLevelAccessFlag = commondetails.checkDistrictFlag(reportCaptureDeadForm.getPersoncode(), reportCaptureDeadForm.getDistrict_id());


            
                if (districtLevelAccessFlag) {
                    int i = reportCaptureDeadService.alreadyExist(ds, reportCaptureDeadForm);
                    if (i == 0) {


                        editPwdStatus = reportToCaptureDeadDetailsDAO.EditPwdValidation(ds, reportCaptureDeadForm.getPersoncode(), reportCaptureDeadForm);



                        if (editPwdStatus != null && editPwdStatus.equalsIgnoreCase("View")) {

                            String validateEligibleDetails = null;
                            validateEligibleDetails = reportToCaptureDeadDetailsDAO.validateAAdhracardEligible(ds, reportCaptureDeadForm.getPersoncode(), reportCaptureDeadForm);

                            if (validateEligibleDetails != null) {
                                request.setAttribute("validateEligibleDetails", validateEligibleDetails);


                              


                            }
                        }

                        SADAREMIDValidDetails = reportCaptureDeadService.validateSADAREMID(ds, reportCaptureDeadForm);
                        Map map = null;
                        if (SADAREMIDValidDetails != null && SADAREMIDValidDetails.size() > 0) {
                            request.setAttribute("SADAREMIDValidDetails", SADAREMIDValidDetails);
                            Iterator it = SADAREMIDValidDetails.iterator();
                            while (it.hasNext()) {
                                map = new HashMap<Object, Object>();
                                map = (Map) it.next();
                                if (map.get("proof_id") != null) {
                                    request.setAttribute("readonly", map.get("proof_id"));
                                }

                                if (reportCaptureDeadForm.getAadharCardNo() != null) {
                                    request.setAttribute("aadharCardNo", reportCaptureDeadForm.getAadharCardNo());
                                }
                            }


                            mappingCount = reportCaptureDeadService.getMappingCount(ds, reportCaptureDeadForm);

                            if (mappingCount >= 1) {
                                voMappingList = reportCaptureDeadService.voNameshgMappingDetails(ds, reportCaptureDeadForm);

                                if (voMappingList.size() > 0) {
                                    voIdData = voMappingList.get(0).toString();
                                    voName = voMappingList.get(1).toString();

                                }

                                shgMappingList = reportCaptureDeadService.shgMappingName(ds, reportCaptureDeadForm);
                                if (shgMappingList.size() > 0) {
                                    request.setAttribute("shgName", shgMappingList.get(2).toString());
                                    reportCaptureDeadForm.setShgid(shgMappingList.get(1).toString());
                                    request.setAttribute("shgid", shgMappingList.get(1).toString());
                                }

                                request.setAttribute("mappedShg", "mappedShg");
                                request.setAttribute("voName", voName);
                                request.setAttribute("voId", voIdData);
                                reportCaptureDeadForm.setVoId(voIdData);

                            } else {
                                shgDropDownDetails = reportCaptureDeadService.getShgDropdownDetails(ds, reportCaptureDeadForm);

                                reportCaptureDeadForm.setShgList(shgDropDownDetails);

                                voList = reportCaptureDeadService.getVoList(ds, reportCaptureDeadForm);
                                reportCaptureDeadForm.setVoList(voList);
                                request.setAttribute("notMappedShg", "notMappedShg");
                                request.setAttribute("notMappedFlag", "notMappedFlag");

                            }
                            target = "details";



                        } else {


                            String validateInActive = null;
                            validateInActive = reportToCaptureDeadDetailsDAO.validateInActive(ds, reportCaptureDeadForm.getPersoncode(), reportCaptureDeadForm);
                            String reasonforStatus = null;
                            reasonforStatus = reportToCaptureDeadDetailsDAO.personstatus(ds, reportCaptureDeadForm.getPersoncode(), reportCaptureDeadForm);



                            if (validateInActive != null && !validateInActive.isEmpty()) {



                                request.setAttribute("errmsg", " SADAREM ID is " + reasonforStatus + "");

                            }

                        }




                    } else {
                        request.setAttribute("errmsg", "Details Already Updated for Given SADAREM ID");
                    }
                } else {
                    reportCaptureDeadForm.setPersoncode("");
                    request.setAttribute("errmsg", "SADAREM ID not belongs to this district");
                }

                if (editPwdStatus != null && editPwdStatus.equalsIgnoreCase("Edit")) {
                    request.setAttribute("editPwdStatus", "PartA Entered");
                    request.setAttribute("Edit", "Edit");

                    target = "success";

                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validateSADAREMID", "ReportToCaptureDeadDetailsAction", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportAction", "getSHGAbstract");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validateSADAREMID", "ReportToCaptureDeadDetailsAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportAction", "getSHGAbstract");
        }

        return mapping.findForward(target);
    }

    public ActionForward insertDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        DataSource ds = null;
        HttpSession session = null;
        int i = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);
            ReportToCaptureDeadDetailsForm reportCaptureDeadForm = (ReportToCaptureDeadDetailsForm) form;
            ReportToCaptureDeadDetailsService reportCaptureDeadService =
                    ReportToCaptureDeadDetailsServiceFactory.getReportToCaptureDeadDetailsServiceImpl();

            String loginID = (String) session.getAttribute("loginid");
            String systemIP = request.getRemoteAddr();
            reportCaptureDeadForm.setSystemIp(systemIP);
            reportCaptureDeadForm.setLoginId(loginID);
            CommonDetails commondetails = new CommonDetails();

            i = reportCaptureDeadService.insertDetails(ds, reportCaptureDeadForm);

            if (i > 0) {
                request.setAttribute("succmsg", " PwD Data Validation Details Inserted Successfully");
                reportCaptureDeadForm.setServeid("");
                reportCaptureDeadForm.setServeDoneBy("");
                reportCaptureDeadForm.setDesignation("");
                reportCaptureDeadForm.setRecivedDate("");
            } else {
                request.setAttribute("errmsg", "Error Occured while Updating Details");
            }



//        }catch (SQLException sqlEx) {
//            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDetails", "ReportToCaptureDeadDetailsAction", "DataBase");
//            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsAction", "insertDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDetails", "ReportToCaptureDeadDetailsAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsAction", "insertDetails");
        }

        return mapping.findForward(target);
    }

    public ActionForward getPwdShgData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ReportToCaptureDeadDetailsForm reportCaptureDeadForm = (ReportToCaptureDeadDetailsForm) form;
        ReportToCaptureDeadDetailsService reportCaptureDeadService =
                ReportToCaptureDeadDetailsServiceFactory.getReportToCaptureDeadDetailsServiceImpl();
        String target = "details";
        DataSource ds = null;
        ArrayList shgDropDownDetails = new ArrayList();
        HttpSession session = null;
        Map map = null;
        String voidData = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            session = request.getSession();

            if (session.getAttribute("districtId") != null) {
                reportCaptureDeadForm.setDistrict_id((String) session.getAttribute("districtId"));
            }

            shgDropDownDetails = reportCaptureDeadService.getShgDropdownDetails(ds, reportCaptureDeadForm);
            reportCaptureDeadForm.setShgList(shgDropDownDetails);
            Iterator it = shgDropDownDetails.iterator();
            while (it.hasNext()) {
                map = new HashMap();
                map = (Map) it.next();
                voidData = map.get("void").toString();

            }
            request.setAttribute("shgFlag", "shgFlag");
            request.setAttribute("notMappedShg", "notMappedShg");
            request.setAttribute("voidData", voidData);
            
            if (request.getParameter("aadharRadio") != null && request.getParameter("aadharRadio").equalsIgnoreCase("Exist")) {
                request.setAttribute("existFlag", "existFlag");
            } else {
                request.setAttribute("notExist", "notExist");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.validateSADAREMID(mapping, form, request, response);
        return mapping.findForward(target);
    }

    public ActionForward getShgIdDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "details";
        ReportToCaptureDeadDetailsForm reportCaptureDeadForm = (ReportToCaptureDeadDetailsForm) form;
        ReportToCaptureDeadDetailsService reportCaptureDeadService =
                ReportToCaptureDeadDetailsServiceFactory.getReportToCaptureDeadDetailsServiceImpl();
        String shgId = "";
        DataSource ds = null;
        HttpSession session = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession();

            if (session.getAttribute("districtId") != null) {
                reportCaptureDeadForm.setDistrict_id((String) session.getAttribute("districtId"));
            }

            if (request.getParameter("voidData") != null) {
                reportCaptureDeadForm.setVoId(request.getParameter("voidData"));
            }

            shgId = reportCaptureDeadService.getShgIdDetails(ds, reportCaptureDeadForm);
            reportCaptureDeadForm.setShgid(shgId);


            request.setAttribute("shgFlag", "shgFlag");
            request.setAttribute("shgId", shgId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getPwdShgData(mapping, form, request, response);
        this.validateSADAREMID(mapping, form, request, response);
        return mapping.findForward(target);
    }
}
