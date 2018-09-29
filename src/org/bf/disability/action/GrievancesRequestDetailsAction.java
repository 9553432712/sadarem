/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.axis.message.MessageElement;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.TripleDESEncryption;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.GrievancesRequestDetailsDAO;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.GrievancesRequestDetailsDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.GrievancesRequestDetailsForm;
import org.bf.disability.form.RDCallCentreStatusForm;
import org.bf.disability.service.GrievancesRequestDetailsService;
import org.bf.disability.service.LoginService;
import org.bf.disability.service.PartAService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.GrievancesRequestDetailsServiceFactory;
import org.bf.disability.servicefactory.LoginServiceFactory;
import org.bf.disability.servicefactory.PartAServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import profileservice.ServicesLocator;
import profileservice.ServicesPortType;
import profileservice.ServicesSOAP11BindingStub;

import com.meesevaWS.GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult;
import com.meesevaWS.GetPaymentTransIdResponseGetPaymentTransIdResult;
import com.meesevaWS.MeeSevaWebServiceSoapProxy;
import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 728056
 */
public class GrievancesRequestDetailsAction extends DispatchAction {

    /* forward name="success" path="" */
    String SUCCESS = "success";
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
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String districtId = null;
        ArrayList RequestNames = new ArrayList();
        GrievancesRequestDetailsForm detailForm = (GrievancesRequestDetailsForm) form;
        GrievancesRequestDetailsService detailsService = (GrievancesRequestDetailsService) GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();


        try {
            HttpSession session = request.getSession();
            districtId = (String) session.getAttribute("districtId");
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            RequestNames = detailsService.getRequestName(ds, currentRoleId);
            detailForm.setRequestList(RequestNames);
            if (currentRoleId != 0 && (currentRoleId == 24 || currentRoleId == 25)) {
                return mapping.findForward("pdApproval"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("exception");
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String districtId = null;
        ArrayList details = new ArrayList();

        GrievancesRequestDetailsForm detailForm = (GrievancesRequestDetailsForm) form;

        GrievancesRequestDetailsService detailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
        try {
            HttpSession session = request.getSession();
            districtId = (String) session.getAttribute("districtId");


            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            details = detailsService.getRequestDetails(ds, detailForm, districtId);
            request.setAttribute("request", details);
            if (details.isEmpty()) {
                request.setAttribute("msg", "Data Not Available");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward grievancesDPMApproval(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String districtId = null;
        String target = "grievancesDPMApprove";
        ArrayList RequestNames = new ArrayList();
        GrievancesRequestDetailsForm detailForm = (GrievancesRequestDetailsForm) form;
        GrievancesRequestDetailsService detailsService = (GrievancesRequestDetailsService) GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
        LoginService loginService = LoginServiceFactory.getLoginServiceImpl();
        try {
            HttpSession session = request.getSession();
            districtId = (String) session.getAttribute("districtId");
            String userid = (String) session.getAttribute("loginid");
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (currentRoleId != 0 && currentRoleId == 23) {
                RequestNames = detailsService.getRequestName(ds, currentRoleId);
                detailForm.setRequestList(RequestNames);
                return mapping.findForward(target);
            } else {
                loginService.unAuthorisedAccessStatus(ds, userid);
                session.invalidate();
            }
        } catch (Exception e) {
            target = "exception";
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward grievancesDPMApprovalList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String districtId = null;
        String flag = null;
        String target = "grievancesDPMApprove";
        String BenificiaryProblemId = null;
        ArrayList RequestNames = new ArrayList();
        ArrayList approvalList = null;
        GrievancesRequestDetailsForm detailForm = (GrievancesRequestDetailsForm) form;
        GrievancesRequestDetailsService detailsService = (GrievancesRequestDetailsService) GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
        int isValid = 0;
        //String isValid =null;
        String validationMsg = null;
        try {
            HttpSession session = request.getSession();
            districtId = (String) session.getAttribute("districtId");

            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            flag = request.getParameter("flag");
            if (request.getParameter("BenificiaryProblemId") != null) {
                BenificiaryProblemId = request.getParameter("BenificiaryProblemId");
            } else {
                BenificiaryProblemId = detailForm.getBenificiaryProblemId();
            }

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            RequestNames = detailsService.getRequestName(ds, currentRoleId);
            detailForm.setRequestList(RequestNames);
            detailForm.setBenificiaryProblemId(BenificiaryProblemId);
            approvalList = detailsService.getDPMApprovalList(ds, detailForm, districtId);
            if (BenificiaryProblemId != null) {

                String requestId = request.getParameter("reqId");
                String subRequestId = request.getParameter("subReqId");


                ArrayList proofList = detailsService.getUploadProofTypeList(ds, requestId, subRequestId);
                detailForm.setProofList(proofList);
                if (requestId != null && requestId.equalsIgnoreCase("5")) {
                    isValid = detailsService.getValidForNewCertificate(ds, BenificiaryProblemId);
                    if (isValid > 0) {
                        validationMsg = detailsService.getValidForNewCertificateMsg(ds, BenificiaryProblemId);
                    }

                    request.setAttribute("isValid", isValid);

                    request.setAttribute("validationMsg", validationMsg);

                }

            }
//            if (approvalList.isEmpty()) {
//                request.setAttribute("msg", "Data Not Available");
//            }

            if (flag != null && flag.equalsIgnoreCase("Action")) {
                target = "grievancesApprovalDetails";
            }
            if (approvalList.size() > 0) {
                request.setAttribute("approvalList", approvalList);
                Iterator it = approvalList.iterator();

                while (it.hasNext()) {
                    Map m = (Map) it.next();
                    detailForm.setProofType(m.get("proofdocType").toString());

                    detailForm.setProofId(m.get("proofId").toString());

                }
            } else {
                request.setAttribute("msg", "Data Not Available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward approveOrRejectDPMRequest(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        GrievancesRequestDetailsForm grievancesRequestDetailsForm = (GrievancesRequestDetailsForm) form;

        GrievancesRequestDetailsService grievancesRequestDetailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
        String extension = null;
        File dir = null;
        String name = null;
        CommonDetails commonDetails = new CommonDetails();
        boolean uploadSuccess = false;

        String Systemip = null;

        try {
            String districtId = (String) session.getAttribute("districtId");

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            grievancesRequestDetailsForm.setSystemIp(Systemip);
            if (grievancesRequestDetailsForm.getUploadDocument() != null
                    && grievancesRequestDetailsForm.getUploadDocument().toString().length() > 0) {
                FormFile myFile = grievancesRequestDetailsForm.getUploadDocument();
                String fileName = myFile.getFileName();
                int dotPos = fileName.lastIndexOf(".");
                extension = fileName.substring(dotPos);
                File sourceDir = null;
                File targetDir = null;
                dir = new File(getServlet().getServletContext().getRealPath("/") + "RDSADAREMDOWNLOADS");

                if (!dir.exists()) {
                    dir.mkdir();
                }
                name = grievancesRequestDetailsForm.getBenificiaryProblemId() + extension;
                int filesizeInKB = myFile.getFileSize() / 1024;
                if (filesizeInKB <= 100) {

                    uploadSuccess = commonDetails.uploadingFile(grievancesRequestDetailsForm.getUploadDocument(), "" + dir, name);
                    sourceDir = new File(getServlet().getServletContext().getRealPath("/") + "RDSADAREMDOWNLOADS");

                    targetDir = new File(CommonConstants.FILEVIEWPATH);
                    if (sourceDir.exists()) {
                        commonDetails.copyDirectoryandFile(sourceDir, targetDir);
                    }
                    //delete directory start
                    File directory = new File(getServlet().getServletContext().getRealPath("/") + "RDSADAREMDOWNLOADS");
                    //make sure directory exists
                    if (!directory.exists()) {
                        //System.exit(0);
                    } else {
                        try {
                            commonDetails.delete(directory);
                        } catch (Exception e) {
                            e.printStackTrace();
                            //System.exit(0);
                        }
                    }
                } else {

                    ArrayList approvalList = grievancesRequestDetailsService.getDPMApprovalList(ds, grievancesRequestDetailsForm, districtId);
                    if (grievancesRequestDetailsForm.getBenificiaryProblemId() != null) {
                        String requestId = request.getParameter("reqId");
                        String subRequestId = request.getParameter("subReqId");
                        ArrayList proofList = grievancesRequestDetailsService.getUploadProofTypeList(ds, requestId, subRequestId);
                        grievancesRequestDetailsForm.setProofList(proofList);
                    }
                    if (approvalList.isEmpty()) {
                        request.setAttribute("msg", "Data Not Available");
                    }
                    request.setAttribute("approvalList", approvalList);
                    request.setAttribute("uploadMessage", "You Need to upload Document less than 100KB");
                    return mapping.findForward("grievancesApprovalDetails");

                }
            }
            //delete directory start
            if (request.getParameter("reqId") != null) {
                grievancesRequestDetailsForm.setRequestId(request.getParameter("reqId"));
            }
            int flag = grievancesRequestDetailsService.approveOrRejectDPMRequests(ds, grievancesRequestDetailsForm, request);
            if (flag == 1) {
                request.setAttribute("msg", "Your Request Processed SuccessFully!");
                request.setAttribute("closewindow", "closewindow");
            } else {
                request.setAttribute("msg", "Error Occured While Process Your Request!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (grievancesRequestDetailsForm.getBeneficiaryIds() != null) {
//            ArrayList details = (ArrayList) grievancesRequestDetailsService.getAllRequestedDetails(ds, grievancesRequestDetailsForm, districtid, currentRoleId, request);
//            request.setAttribute("RequestedList", details);
//
//            grievancesRequestDetailsForm.setRequestList(grievancesRequestDetailsService.getRequestName(ds, grievancesRequestDetailsForm));
//
//            return mapping.findForward("success");
//        }

        return mapping.findForward("grievancesApprovalDetails");
    }

    public ActionForward getAllRequestedDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArrayList details = new ArrayList();

        GrievancesRequestDetailsForm grievancesRequestDetailsForm = (GrievancesRequestDetailsForm) form;

        GrievancesRequestDetailsService grievancesRequestDetailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
        HttpSession session = request.getSession();

        try {
            String districtId = (String) session.getAttribute("districtId");
            String mandalId = (String) session.getAttribute("mandalId");
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("beneficiaryId") != null) {
                grievancesRequestDetailsForm.setBenificiaryProblemId(request.getParameter("beneficiaryId"));

            }
            details = grievancesRequestDetailsService.getAllRequestedDetails(ds, grievancesRequestDetailsForm, districtId, currentRoleId, request, mandalId);
            request.setAttribute("RequestedList", details);
            if (grievancesRequestDetailsForm.getBenificiaryProblemId() != null) {
                String dpmRemarks = grievancesRequestDetailsService.getDPMRemarks(ds, grievancesRequestDetailsForm);
                grievancesRequestDetailsForm.setDpmRemarks(dpmRemarks);
            }
            if (details.isEmpty()) {
                request.setAttribute("msg", "No Data Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (request.getParameter("beneficiaryId") != null) {
            return mapping.findForward("nextPage");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward("pdApproval");
    }

    public ActionForward approveOrRejectRequest(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        GrievancesRequestDetailsForm grievancesRequestDetailsForm = (GrievancesRequestDetailsForm) form;
        GrievancesRequestDetailsService grievancesRequestDetailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
        String Systemip = request.getRemoteAddr();
        try {
            String districtId = CommonUtility.checkNullObj(session.getAttribute("districtId"));
            String mandalId = CommonUtility.checkNullObj(session.getAttribute("mandalId"));
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            grievancesRequestDetailsForm.setSystemIp(Systemip);
            int flag = grievancesRequestDetailsService.approveOrRejectRequests(ds, grievancesRequestDetailsForm, currentRoleId, request);
            if (flag == 1) {
                request.setAttribute("msg1", "Your Request Processed SuccessFully!");
                request.setAttribute("closewindow", "closewindow");
            }

            if (grievancesRequestDetailsForm.getBeneficiaryIds() != null) {
                ArrayList details = (ArrayList) grievancesRequestDetailsService.getAllRequestedDetails(ds, grievancesRequestDetailsForm, districtId, currentRoleId, request, mandalId);
                request.setAttribute("RequestedList", details);
                grievancesRequestDetailsForm.setRequestList(grievancesRequestDetailsService.getRequestName(ds, currentRoleId));

                return mapping.findForward("pdApproval");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("nextPage");
    }

    public ActionForward viewDocument(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String fileName = request.getParameter("fileName");
            String fileType = request.getParameter("fileType");
            String Proof_Id = request.getParameter("Proof_Id");
            String beneficiaryProblemId = request.getParameter("beneficiaryProblemId");
            viewFile(fileName, fileType, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward getReportBasedOnStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        RDCallCentreStatusForm rDCallCentreStatusForm = (RDCallCentreStatusForm) form;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ArrayList districtlist = territoryservice.getDistricts(ds);
            rDCallCentreStatusForm.setDistrictList(districtlist);

            if (request.getParameter("districtId") != null) {
                rDCallCentreStatusForm.setDistrict_id(request.getParameter("districtId"));
            }
            if (request.getParameter("urbanId") != null) {
                rDCallCentreStatusForm.setUrban_id(request.getParameter("urbanId"));
            }
            if (rDCallCentreStatusForm.getDistrict_id() != null) {
                GrievancesRequestDetailsService grievancesRequestDetailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();

                ArrayList reportList = grievancesRequestDetailsService.getCountOfStatusesReport(ds, rDCallCentreStatusForm);
                if (reportList.isEmpty()) {
                    request.setAttribute("msg", "Data Not Available");
                } else {
                    request.setAttribute("ReportList", reportList);
                }
                request.setAttribute("districtId", rDCallCentreStatusForm.getDistrict_id());
                request.setAttribute("urbanId", rDCallCentreStatusForm.getUrban_id());
                if (request.getParameter("pageType") != null) {
                    if (request.getParameter("pageType").equalsIgnoreCase("print")) {
                        return mapping.findForward("statusPrint");
                    } else if (request.getParameter("pageType").equalsIgnoreCase("excel")) {
                        exportExcel(reportList, "ReportList", request, response);

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward getIndividualReportBasedOnStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        RDCallCentreStatusForm rDCallCentreStatusForm = (RDCallCentreStatusForm) form;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        String status = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ArrayList districtlist = territoryservice.getDistricts(ds);
            rDCallCentreStatusForm.setDistrictList(districtlist);

            if (request.getParameter("districtId") != null) {
                rDCallCentreStatusForm.setDistrict_id(request.getParameter("districtId"));
            }
            if (request.getParameter("urbanId") != null) {
                rDCallCentreStatusForm.setUrban_id(request.getParameter("urbanId"));
            }
            if (request.getParameter("status") != null) {
                status = request.getParameter("status");
            }

            if (rDCallCentreStatusForm.getDistrict_id() != null) {
                GrievancesRequestDetailsService grievancesRequestDetailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();

                ArrayList reportList = grievancesRequestDetailsService.getIndividualDetailsOfStatusesReport(ds, rDCallCentreStatusForm, status);
                if (reportList.isEmpty()) {
                    request.setAttribute("msg", "Data Not Available");
                } else {
                    request.setAttribute("ReportList", reportList);
                }
                request.setAttribute("districtId", rDCallCentreStatusForm.getDistrict_id());
                request.setAttribute("urbanId", rDCallCentreStatusForm.getUrban_id());
                request.setAttribute("status", status);
                if (request.getParameter("pageType") != null) {
                    if (request.getParameter("pageType").equalsIgnoreCase("print")) {
                        return mapping.findForward("printIndividualReport");
                    } else if (request.getParameter("pageType").equalsIgnoreCase("excel")) {
                        exportExcel(reportList, "individualDetails", request, response);

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("individualReport");
    }

    public ActionForward getMonthWiseCallCentreReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArrayList reportList = new ArrayList();
        RDCallCentreStatusForm rDCallCentreStatusForm = (RDCallCentreStatusForm) form;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ArrayList districtlist = territoryservice.getDistricts(ds);
            rDCallCentreStatusForm.setDistrictList(districtlist);
            request.setAttribute("currentYear", new Date().getYear() + 1900);
            if (request.getParameter("districtId") != null) {
                rDCallCentreStatusForm.setDistrict_id(request.getParameter("districtId"));
            }
            if (request.getParameter("month") != null) {
                rDCallCentreStatusForm.setMonth(request.getParameter("month"));
            }
            if (request.getParameter("year") != null) {
                rDCallCentreStatusForm.setYear(request.getParameter("year"));
            }
            if (request.getParameter("urbanId") != null) {
                rDCallCentreStatusForm.setUrban_id(request.getParameter("urbanId"));
            }
            request.setAttribute("month", rDCallCentreStatusForm.getMonth());
            request.setAttribute("year", rDCallCentreStatusForm.getYear());
            if (rDCallCentreStatusForm.getDistrict_id() != null && rDCallCentreStatusForm != null) {

                GrievancesRequestDetailsService grievancesRequestDetailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();



                if (rDCallCentreStatusForm.getMonth() != null) {

                    reportList = grievancesRequestDetailsService.getMonthWiseCallCentreReport(ds, rDCallCentreStatusForm);
                    if (reportList.isEmpty()) {
                        request.setAttribute("msg", "Data Not Available");
                    } else {
                        request.setAttribute("DistrictMonthwiseList", reportList);
                    }

                } else {

                    reportList = grievancesRequestDetailsService.getMonthWiseCallCentreReport(ds, rDCallCentreStatusForm);
                    if (reportList.isEmpty()) {
                        request.setAttribute("msg", "Data Not Available");
                    } else {

//                        ArrayList types = new ArrayList();
//                        for (int x = 0; x < reportList.size(); x++) {
//                            Map m = (Map) reportList.get(x);
//                            types.add(m.get("Typeofcomplaints"));
//                        }
//                        request.setAttribute("RequestTypes", types);
                        request.setAttribute("DistrictAllList", reportList);
                    }
                }
                request.setAttribute("districtId", rDCallCentreStatusForm.getDistrict_id());
                request.setAttribute("urbanId", rDCallCentreStatusForm.getUrban_id());
            }
            if (request.getParameter("pageType") != null) {
                if (request.getParameter("pageType").equalsIgnoreCase("distprint")) {
                    return mapping.findForward("distprint");
                } else if (request.getParameter("pageType").equalsIgnoreCase("monthprint")) {
                    return mapping.findForward("monthprint");
                } else if (request.getParameter("pageType").equalsIgnoreCase("distexcel")) {
                    exportExcel(reportList, "district", request, response);
                } else if (request.getParameter("pageType").equalsIgnoreCase("monthexcel")) {
                    exportExcel(reportList, "month", request, response);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (request.getParameter("page") != null) {
            return mapping.findForward("calDistReport");
        }
        return mapping.findForward("monthReport");
    }

    public static void viewFile(String fileName, String fileType, HttpServletRequest request, HttpServletResponse response) {

        try {

            File folder = new File(CommonConstants.FILEVIEWPATH);

            File[] listOfFiles = folder.listFiles();
            int size = 0;
            if (listOfFiles != null && !listOfFiles.equals("")) {
                size = listOfFiles.length + 1;
            } else {
                size = 0 + 1;
            }

            CommonDetails commonDetails = new CommonDetails();

            String contentType = null;
            contentType = "txt";

            BufferedInputStream in = null;
            File fileDetailsData = new File(folder + "\\" + fileName);

            FileInputStream fin = new FileInputStream(fileDetailsData);

            in = new BufferedInputStream(fin);

            ServletOutputStream out = response.getOutputStream();

            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + CommonConstants.FILEVIEWPATH + fileName + "\"");


            byte[] buffer = new byte[4 * 1024];

            int data = 0;
            while ((data = in.read(buffer)) != -1) {
                out.write(buffer, 0, data);
            }
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static void exportExcel(ArrayList dataList, String type, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        String districtId = "";
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=grievanceReport.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        	WritableCellFormat cell =new WritableCellFormat(bold);
        	 
    			cell.setBorder(Border.ALL, BorderLineStyle.THIN);
    			cell.setAlignment(Alignment.CENTRE);
    			cell.setVerticalAlignment(VerticalAlignment.CENTRE);
    			cell.setBackground(Colour.GRAY_25);
    			

            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "Data Not Available................"));
            } else {
                if (type.equals("ReportList")) {
                	s.mergeCells(0, 0, 20, 0);
                	  s.addCell(new Label(0, 0, "R6.1 District and Status Wise Grievances Report",cell));
                    headerList.add("S No");
                    headerList.add("District");
                    headerList.add("Application Registered");
                    headerList.add("Documents To Be Uploaded");
                    headerList.add("Documents Uploaded");
                    headerList.add("Documents Upload Rejected");
                    headerList.add("For Field Verification");
                    headerList.add("Field Verification Completed");
                    headerList.add("Field Verification Rejected");
                    headerList.add("MPDO Pending");
                    headerList.add("MPDO Approved");
                    headerList.add("MPDO Rejected");
                    headerList.add("PD Pending");
                    headerList.add("PD Approved");
                    headerList.add("PD Rejected");
                    headerList.add("Schedule To Camp Pending");
                    headerList.add("Schedule To Camp Approved");
                    headerList.add("Schedule To Camp Rejected");
                    headerList.add("Certificate Issued");
                    headerList.add("Certificate Rejected");
                    headerList.add("OverAll Pending");
                }
                if (type.equals("district")) {

                    if (request.getAttribute("districtId") != null) {
                        districtId = request.getAttribute("districtId").toString();
                    }
                    headerList.add("S No");
                    if (districtId.equalsIgnoreCase("ALL")) {
                        headerList.add("District");
                        headerList.add("Assessment Completed. But Not Getting Certificate Pending");
                        headerList.add("Date of Birth Pending");
                        headerList.add("Duplicate Certificate Pending");
                        headerList.add("Identification Marks Pending");
                        headerList.add("Name Pending");
                        headerList.add("New Certificate Pending");
                        headerList.add("Physical Impairment Pending");
                        headerList.add("Re Assessment Pending");
                        headerList.add("Relation Name Pending");
                        headerList.add("Renewal Pending");
                    } else {
                        headerList.add("Request Type");
                        headerList.add("Pending");
                    }

                }
                if (type.equals("month")) {
                	s.mergeCells(0, 0, 5, 0);
                	s.addCell(new Label(0, 0, "R6.2 : District and Month Wise Grievances Report ",cell));
                	
                    headerList.add("S No");
                    headerList.add("District");
                    headerList.add("No of complaints registered ");
                    headerList.add("No of complaints registered in this month");
                    headerList.add("No of complaints resolved in this month");
                    headerList.add("No of complaints pending");

                }
                if (type.equals("individualDetails")) {
                	s.mergeCells(0, 0, 12, 0);
                	s.addCell(new Label(0, 0, "R6.1 District and Status Wise Grievances Detailed Report",cell));
                    headerList.add("S No.");
                    headerList.add("Request ID");
                    headerList.add("SADAREM ID");
                    headerList.add("Pension No.");
                    headerList.add("SurName");
                    headerList.add("FirstName");
                    headerList.add("District");
                    headerList.add("Mandal");
                    headerList.add("Request Type");
                    headerList.add("Generated SADAREM ID");
                    headerList.add("Mobile Number");
                    headerList.add("Request Raised Date");
                    headerList.add("Scheduled Camp Date");
                }


                addHeaders(s, headerList);
            }
            int applicationRegistered = 0;
            int documentsUploaded = 0, documentsToBeUploaded = 0, documentsUploadedRejected = 0;
            int documentsVerifiedPending = 0, documentsVerified = 0, documentsVerifiedRejected = 0;
            int mPDOPending = 0, mPDOApproved = 0;
            int mPDORejected = 0;
            int pDPending = 0;
            int pDApproved = 0;
            int pDRejected = 0;
            int scheduleCampPending = 0;
            int scheduleCampApproved = 0, scheduleCampRejected = 0;
            int certificateIssued = 0, certificateRejected = 0;
            int assessPendingNewComp = 0;
            int dOBPendingNewComp = 0;
            int duplPendingNewComp = 0, iDPendingNewComp = 0;
            int namePendingNewComp = 0, nCPendingNewComp = 0;
            int physiPendingNewComp = 0, ressessPendingNewComp = 0;
            int renewalPendingNewComp = 0, relationNamePendingNewComp = 0;
            int registerdBeforethismonth = 0, registerdthismonth = 0, resolvdthismonth = 0, pending = 0;
            int ovrAllPending = 0;
            int Pending = 0;
            int x = 2;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                Map m = (Map) dataList.get(i);
                if (type.equals("ReportList")) {
                    applicationRegistered = applicationRegistered + Integer.parseInt(m.get("ApplicationRegistered").toString());
                    documentsToBeUploaded = documentsToBeUploaded + Integer.parseInt(m.get("DocumentsToBeUploaded").toString());
                    documentsUploadedRejected = documentsUploadedRejected + Integer.parseInt(m.get("DocumentsUploadedRejected").toString());
                    documentsUploaded = documentsUploaded + Integer.parseInt(m.get("DocumentsUploaded").toString());
                    documentsVerifiedPending = documentsVerifiedPending + Integer.parseInt(m.get("DocumentsVerifiedPending").toString());
                    documentsVerified = documentsVerified + Integer.parseInt(m.get("DocumentsVerified").toString());
                    documentsVerifiedRejected = documentsVerifiedRejected + Integer.parseInt(m.get("DocumentsVerifiedRejected").toString());
                    mPDOPending = mPDOPending + Integer.parseInt(m.get("MPDOPending").toString());
                    mPDOApproved = mPDOApproved + Integer.parseInt(m.get("MPDOApproved").toString());
                    mPDORejected = mPDORejected + Integer.parseInt(m.get("MPDORejected").toString());
                    pDPending = pDPending + Integer.parseInt(m.get("PDPending").toString());
                    pDApproved = pDApproved + Integer.parseInt(m.get("PDApproved").toString());
                    pDRejected = pDRejected + Integer.parseInt(m.get("PDRejected").toString());
                    scheduleCampPending = scheduleCampPending + Integer.parseInt(m.get("ScheduleCampPending").toString());
                    scheduleCampApproved = scheduleCampApproved + Integer.parseInt(m.get("ScheduleCampApproved").toString());
                    scheduleCampRejected = scheduleCampRejected + Integer.parseInt(m.get("ScheduleCampRejected").toString());
                    certificateIssued = certificateIssued + Integer.parseInt(m.get("certificateIssued").toString());
                    certificateRejected = certificateRejected + Integer.parseInt(m.get("certificateRejected").toString());
                    ovrAllPending = ovrAllPending + Integer.parseInt(m.get("ovrAllPending").toString());
                    s.addCell(new Label(j++, x, i + 1 + ""));

                    s.addCell(new Label(j++, x, m.get("District").toString()));
                    s.addCell(new Label(j++, x, m.get("ApplicationRegistered").toString()));
                    s.addCell(new Label(j++, x, m.get("DocumentsToBeUploaded").toString()));
                    s.addCell(new Label(j++, x, m.get("DocumentsUploaded").toString()));
                    s.addCell(new Label(j++, x, m.get("DocumentsUploadedRejected").toString()));
                    s.addCell(new Label(j++, x, m.get("DocumentsVerifiedPending").toString()));
                    s.addCell(new Label(j++, x, m.get("DocumentsVerified").toString()));
                    s.addCell(new Label(j++, x, m.get("DocumentsVerifiedRejected").toString()));
                    s.addCell(new Label(j++, x, m.get("MPDOPending").toString()));
                    s.addCell(new Label(j++, x, m.get("MPDOApproved").toString()));
                    s.addCell(new Label(j++, x, m.get("MPDORejected").toString()));
                    s.addCell(new Label(j++, x, m.get("PDPending").toString()));
                    s.addCell(new Label(j++, x, m.get("PDApproved").toString()));
                    s.addCell(new Label(j++, x, m.get("PDRejected").toString()));
                    s.addCell(new Label(j++, x, m.get("ScheduleCampPending").toString()));
                    s.addCell(new Label(j++, x, m.get("ScheduleCampApproved").toString()));
                    s.addCell(new Label(j++, x, m.get("ScheduleCampRejected").toString()));
                    s.addCell(new Label(j++, x, m.get("certificateIssued").toString()));
                    s.addCell(new Label(j++, x, m.get("certificateRejected").toString()));
                    s.addCell(new Label(j++, x, m.get("ovrAllPending").toString()));

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
                        s.addCell(new Label(j++, x, applicationRegistered + ""));
                        s.addCell(new Label(j++, x, documentsToBeUploaded + ""));
                        s.addCell(new Label(j++, x, documentsUploaded + ""));
                        s.addCell(new Label(j++, x, documentsUploadedRejected + ""));
                        s.addCell(new Label(j++, x, documentsVerifiedPending + ""));
                        s.addCell(new Label(j++, x, documentsVerified + ""));
                        s.addCell(new Label(j++, x, documentsVerifiedRejected + ""));
                        s.addCell(new Label(j++, x, mPDOPending + ""));
                        s.addCell(new Label(j++, x, mPDOApproved + ""));
                        s.addCell(new Label(j++, x, mPDORejected + ""));
                        s.addCell(new Label(j++, x, pDPending + ""));
                        s.addCell(new Label(j++, x, pDApproved + ""));
                        s.addCell(new Label(j++, x, pDRejected + ""));
                        s.addCell(new Label(j++, x, scheduleCampPending + ""));
                        s.addCell(new Label(j++, x, scheduleCampApproved + ""));
                        s.addCell(new Label(j++, x, scheduleCampRejected + ""));
                        s.addCell(new Label(j++, x, certificateIssued + ""));
                        s.addCell(new Label(j++, x, certificateRejected + ""));
                        s.addCell(new Label(j++, x, ovrAllPending + ""));

                    }

                }
                if (type.equals("district")) {

                    if (districtId.equalsIgnoreCase("ALL")) {
                        assessPendingNewComp = assessPendingNewComp + Integer.parseInt(m.get("AssessPendingNewComp").toString());
                        dOBPendingNewComp = dOBPendingNewComp + Integer.parseInt(m.get("DOBPendingNewComp").toString());
                        duplPendingNewComp = duplPendingNewComp + Integer.parseInt(m.get("DuplPendingNewComp").toString());
                        iDPendingNewComp = iDPendingNewComp + Integer.parseInt(m.get("IDPendingNewComp").toString());
                        namePendingNewComp = namePendingNewComp + Integer.parseInt(m.get("NamePendingNewComp").toString());
                        nCPendingNewComp = nCPendingNewComp + Integer.parseInt(m.get("NCPendingNewComp").toString());
                        physiPendingNewComp = physiPendingNewComp + Integer.parseInt(m.get("PhysiPendingNewComp").toString());
                        ressessPendingNewComp = ressessPendingNewComp + Integer.parseInt(m.get("RessessPendingNewComp").toString());
                        renewalPendingNewComp = renewalPendingNewComp + Integer.parseInt(m.get("RenewalPendingNewComp").toString());
                        relationNamePendingNewComp = relationNamePendingNewComp + Integer.parseInt(m.get("RelationNamePendingNewComp").toString());
                    } else {
                        Pending = Pending + Integer.parseInt(m.get("Pending").toString());
                    }

                    s.addCell(new Label(j++, x, i + 1 + ""));
                    if (districtId.equalsIgnoreCase("ALL")) {
                        s.addCell(new Label(j++, x, m.get("District").toString()));
                        s.addCell(new Label(j++, x, m.get("AssessPendingNewComp").toString()));
                        s.addCell(new Label(j++, x, m.get("DOBPendingNewComp").toString()));
                        s.addCell(new Label(j++, x, m.get("DuplPendingNewComp").toString()));
                        s.addCell(new Label(j++, x, m.get("IDPendingNewComp").toString()));
                        s.addCell(new Label(j++, x, m.get("NamePendingNewComp").toString()));
                        s.addCell(new Label(j++, x, m.get("NCPendingNewComp").toString()));
                        s.addCell(new Label(j++, x, m.get("PhysiPendingNewComp").toString()));
                        s.addCell(new Label(j++, x, m.get("RessessPendingNewComp").toString()));
                        s.addCell(new Label(j++, x, m.get("RenewalPendingNewComp").toString()));
                        s.addCell(new Label(j++, x, m.get("RelationNamePendingNewComp").toString()));
                    } else {
                        s.addCell(new Label(j++, x, m.get("RequestName").toString()));
                        s.addCell(new Label(j++, x, m.get("Pending").toString()));
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
                        x = x + 1;
                        s.addCell(new Label(j++, x, ""));
                        s.addCell(new Label(j++, x, "Total"));

                        if (districtId.equalsIgnoreCase("ALL")) {
                            s.addCell(new Label(j++, x, assessPendingNewComp + ""));
                            s.addCell(new Label(j++, x, dOBPendingNewComp + ""));
                            s.addCell(new Label(j++, x, duplPendingNewComp + ""));
                            s.addCell(new Label(j++, x, iDPendingNewComp + ""));
                            s.addCell(new Label(j++, x, namePendingNewComp + ""));
                            s.addCell(new Label(j++, x, nCPendingNewComp + ""));
                            s.addCell(new Label(j++, x, physiPendingNewComp + ""));
                            s.addCell(new Label(j++, x, ressessPendingNewComp + ""));
                            s.addCell(new Label(j++, x, renewalPendingNewComp + ""));
                            s.addCell(new Label(j++, x, relationNamePendingNewComp + ""));
                        } else {
                            s.addCell(new Label(j++, x, Pending + ""));
                        }

                    }

                }
                if (type.equals("month")) {
                    registerdBeforethismonth = registerdBeforethismonth + Integer.parseInt(m.get("registerdBeforethismonth").toString());
                    registerdthismonth = registerdthismonth + Integer.parseInt(m.get("registerdthismonth").toString());
                    resolvdthismonth = resolvdthismonth + Integer.parseInt(m.get("resolvdthismonth").toString());
                    pending = pending + Integer.parseInt(m.get("pending").toString());
                    s.addCell(new Label(j++, x, i + 1 + ""));

                    s.addCell(new Label(j++, x, m.get("District").toString()));
                    s.addCell(new Label(j++, x, m.get("registerdBeforethismonth").toString()));
                    s.addCell(new Label(j++, x, m.get("registerdthismonth").toString()));
                    s.addCell(new Label(j++, x, m.get("resolvdthismonth").toString()));
                    s.addCell(new Label(j++, x, m.get("pending").toString()));

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
                        s.addCell(new Label(j++, x, registerdBeforethismonth + ""));
                        s.addCell(new Label(j++, x, registerdthismonth + ""));
                        s.addCell(new Label(j++, x, resolvdthismonth + ""));
                        s.addCell(new Label(j++, x, pending + ""));

                    }

                }
                if (type.equals("individualDetails")) {
                    s.addCell(new Label(j++, x, i + 1 + ""));
                    s.addCell(new Label(j++, x, m.get("requestNo").toString()));
                    s.addCell(new Label(j++, x, m.get("sadaremId").toString()));
                    s.addCell(new Label(j++, x, m.get("pensionNo").toString()));
                    s.addCell(new Label(j++, x, m.get("surName").toString()));
                    s.addCell(new Label(j++, x, m.get("firstName").toString()));
                    s.addCell(new Label(j++, x, m.get("district").toString()));
                    s.addCell(new Label(j++, x, m.get("mandal").toString()));
                    s.addCell(new Label(j++, x, m.get("requestName").toString()));
                    s.addCell(new Label(j++, x, m.get("generatedSadaremId").toString()));
                    s.addCell(new Label(j++, x, m.get("mobileNo").toString()));
                    s.addCell(new Label(j++, x, m.get("raisedDate").toString()));
                    s.addCell(new Label(j++, x, m.get("scheduledCampdate").toString()));

                    if (i == (50000) * page) {
                        int sheet = ++page;
                        s = w.createSheet("Sheet" + sheet, sheet - 1);
                        j = 0;
                        x = 2;

                        addHeaders(s, headerList);
                    }


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

    	WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
    	WritableCellFormat cell =new WritableCellFormat(bold);
    	try {
			cell.setBorder(Border.ALL, BorderLineStyle.THIN);
			cell.setAlignment(Alignment.CENTRE);
			cell.setVerticalAlignment(VerticalAlignment.CENTRE);
			cell.setBackground(Colour.GRAY_25);
			
		
    	 for (int x = 0; x < list.size(); x++) {
	            try {
	                s.addCell(new Label(x, 1, list.get(x).toString(),cell));
	            } catch (WriteException ex) {
	                ex.printStackTrace();
	            }
	        }
    	 
    	} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public ActionForward getRdcallcentreRequestSearch(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        GrievancesRequestDetailsForm grievancesRequestDetailsForm = (GrievancesRequestDetailsForm) form;
        String reqNumber = request.getParameter("reqNumber");
        grievancesRequestDetailsForm.setRequestNumber(reqNumber);
        if (grievancesRequestDetailsForm.getRequestNumber() != null) {
            String districtId = null;
            ArrayList details = new ArrayList();
            GrievancesRequestDetailsService detailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
            HttpSession session = request.getSession();
            //districtId = (String) session.getAttribute("districtId");

            try {
                ds = getDataSource(request);
                if (ds == null || "null".equals(ds)) {
                    ds = JNDIDataSource.getConnection();
                }

                details = detailsService.getRequestDetailsBasedonRequestNumber(ds, grievancesRequestDetailsForm);
                request.setAttribute("request", details);
                if (details.isEmpty()) {
                    request.setAttribute("msg", "Data Not Available");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward getSADAREMCampReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        GrievancesRequestDetailsService detailsService = GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String benificiaryProblemId = request.getParameter("benificiaryProblemId");
            String districtId = request.getParameter("districtId");
            String mandalId = request.getParameter("mandalId");
            String villageId = request.getParameter("villageId");
            String panchayatId = request.getParameter("panchayatId");
            String habitationId = request.getParameter("habitationId");

            ArrayList list = detailsService.getSADAREMCampReport(ds, districtId, mandalId, villageId, panchayatId, habitationId);
            if (list.size() > 0) {
                request.setAttribute("sadremCampList", list);
            } else {
                request.setAttribute("msg", "Data Not Available");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("existingCampReport");
    }

    public ActionForward getMesevaNewCertificateRegistration(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String enc = null;
        String uniqueNo = null;
        String scaUserId = null;
        String userId = null;
        String operatorId = null;
        String checkSum = null;
        String requestId = null;
        String serviceid = null;
        String scaPassword = null;
        String applicationNo = null;
        String meesevaflag = null;
        String flag = null;
        GrievancesRequestDetailsForm grievancesRequestDetailsForm = (GrievancesRequestDetailsForm) form;
        String target = null;
        String decryptedValue = null;
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            if (request.getParameter("flag") != null && !request.getParameter("flag").equalsIgnoreCase("")) {
                flag = request.getParameter("flag");
            }

            if (flag != null && !flag.equalsIgnoreCase("") && flag.equalsIgnoreCase("back")) {
                scaUserId = grievancesRequestDetailsForm.getSCAUserId();
                scaPassword = grievancesRequestDetailsForm.getScaPassword();
                userId = grievancesRequestDetailsForm.getLoginId();
                uniqueNo = grievancesRequestDetailsForm.getUniqueNo();
                checkSum = grievancesRequestDetailsForm.getCheckSum();

            } else {
                if (request.getParameter("enc") != null && !request.getParameter("enc").equalsIgnoreCase("")) {
                    enc = request.getParameter("enc");
                    grievancesRequestDetailsForm.setEncryptedString(enc);
                }

                byte[] myIV = {(byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57};
                byte[] tdesKeyData = {
                    (byte) 0xA2, (byte) 0x15, (byte) 0x37, (byte) 0x07, (byte) 0xCB, (byte) 0x62,
                    (byte) 0xC1, (byte) 0xD3, (byte) 0xF8, (byte) 0xF1, (byte) 0x97, (byte) 0xDF,
                    (byte) 0xD0, (byte) 0x13, (byte) 0x4F, (byte) 0x79, (byte) 0x01, (byte) 0x67,
                    (byte) 0x7A, (byte) 0x85, (byte) 0x94, (byte) 0x16, (byte) 0x31, (byte) 0x92};


//                Identity.instance().unAuthenticate();
                TripleDESEncryption objText = new TripleDESEncryption(tdesKeyData, myIV);
                decryptedValue = objText.decryptText(enc);
                if (decryptedValue != null) {
                    String values[] = decryptedValue.split("&");
                    //String values[] = enc.split(",");

                    for (int i = 0; i < values.length; i++) {
                        String paramValues[] = values[i].split("=");
                        if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("P1")) {
                            uniqueNo = paramValues[1];
                            grievancesRequestDetailsForm.setUniqueNo(uniqueNo);
                        }
                        if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p2")) {
                            scaUserId = paramValues[1];
                            grievancesRequestDetailsForm.setSCAUserId(scaUserId);
                        }
                        if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p3")) {
                            userId = paramValues[1];
                            grievancesRequestDetailsForm.setLoginId(userId);
                        }
                        if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p4")) {
                            operatorId = paramValues[1];
                            grievancesRequestDetailsForm.setChannelId(operatorId);
                        }
                        if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p5")) {
                            checkSum = paramValues[1];
                            grievancesRequestDetailsForm.setCheckSum(checkSum);
                        }
                        if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p6")) {
                            requestId = paramValues[1];
                            grievancesRequestDetailsForm.setRequestId(requestId);
                        }
                        if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p7")) {
                            serviceid = paramValues[1];
                            grievancesRequestDetailsForm.setServiceid(serviceid);
                        }
                        if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p8")) {
                            scaPassword = paramValues[1];
                            grievancesRequestDetailsForm.setScaPassword(scaPassword);
                        }
                        if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p9")) {
                            applicationNo = paramValues[1];
                            grievancesRequestDetailsForm.setApplicationNo(applicationNo);
                        }
                        if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p10")) {
                            meesevaflag = paramValues[1];
                            grievancesRequestDetailsForm.setMeesevaFlag(meesevaflag);
                        }

                    }
                }
            }
            if (compareCheckSum(scaUserId, scaPassword, scaUserId, userId, uniqueNo, checkSum)) {
                target = "success";
            } else {
                target = "exception";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    private String GenerateCheckSum(String scaUserId, String scaPassword, String centerId, String operatorId, String uniqueNo) {
        String checkSum = "";
        try {
            String str1 = centerId + uniqueNo;
            String str2 = operatorId;
            String l3 = (str1 + scaUserId) + (str2 + scaPassword);
            int n1 = str1.length();
            int n2 = str2.length();
            String l1 = str1.substring(0, n1 - 3);
            String l2 = str2.substring(0, n2 - 2);
            Random random = new Random();
            int intl4 = random.nextInt(9);
            String l4 = Integer.toString(intl4);
            checkSum = l4 + l3 + l2 + uniqueNo + l1 + l4;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return checkSum;
    }

    private boolean compareCheckSum(String scaUserId, String scaPassword, String centerId, String operatorId, String uniqueNo, String Checksum) {
        //string checkSum = "";
        try {
            String compareChecksum = Checksum.substring(1, Checksum.length() - 1);
            String str1 = centerId + uniqueNo;
            String str2 = operatorId;
            String l3 = (str1 + scaUserId) + (str2 + scaPassword);
            int n1 = str1.length();
            int n2 = str2.length();
            String l1 = str1.substring(0, n1 - 3);
            String l2 = str2.substring(0, n2 - 2);

            if (compareChecksum.equalsIgnoreCase(l3 + l2 + uniqueNo + l1)) {
                return true;
            } else {
                return false;
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public ActionForward getMesevaNewCertificatePersonalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrievancesRequestDetailsForm grievancesRequestDetailsForm = (GrievancesRequestDetailsForm) form;
        String target = null;
        ArrayList districtList = new ArrayList();
        ArrayList proofList = new ArrayList();
        ArrayList mandalList = new ArrayList();
        try {
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            GrievancesRequestDetailsService detailsService = (GrievancesRequestDetailsService) GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            proofList = detailsService.getUploadProofTypeList(ds, "5", "1");
            grievancesRequestDetailsForm.setProofList(proofList);
            districtList = territoryservice.getDistricts(ds);
            grievancesRequestDetailsForm.setDistrictlist(districtList);
            grievancesRequestDetailsForm.setDistrict_id(grievancesRequestDetailsForm.getDistrict_id());
            mandalList = territoryservice.getMandals(ds, grievancesRequestDetailsForm.getDistrict_id());
            grievancesRequestDetailsForm.setMandallist(mandalList);
            if (grievancesRequestDetailsForm.getPensionNo() != null) {
                if (grievancesRequestDetailsForm.getPensionNo().equalsIgnoreCase("Yes")) {
                    target = "submitPensionNo";
                } else {
                    target = "withoutPensionNo";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward submitBackPersonalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrievancesRequestDetailsForm grievancesRequestDetailsForm = (GrievancesRequestDetailsForm) form;
        ArrayList districtList = new ArrayList();

        try {
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryservice.getDistricts(ds);
            grievancesRequestDetailsForm.setDistrictlist(districtList);
            grievancesRequestDetailsForm.setDistrict_id(null);
//            if (request.getParameter("enc") != null && !request.getParameter("enc").equalsIgnoreCase("")) {
//                grievancesRequestDetailsForm.setEncryptedString(request.getParameter("enc"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("submitPensionNo");
    }

    public ActionForward getMesevaNewCertificatePensionDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrievancesRequestDetailsForm grievancesRequestDetailsForm = (GrievancesRequestDetailsForm) form;
        ArrayList proofList = new ArrayList();
        ArrayList districtList = new ArrayList();
        String target = null;
        String pensionNo = null;
        ArrayList requestDetails = new ArrayList();
        try {
            GrievancesRequestDetailsService detailsService = (GrievancesRequestDetailsService) GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
            PartADTO partADTO = new PartADTO();
            PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryservice.getDistricts(ds);
            grievancesRequestDetailsForm.setDistrictlist(districtList);
            proofList = detailsService.getUploadProofTypeList(ds, "5", "1");
            grievancesRequestDetailsForm.setProofList(proofList);
            pensionNo = grievancesRequestDetailsForm.getPensioncardno();
            if (grievancesRequestDetailsForm.getPensioncardno() != null) {
                requestDetails = detailsService.getMeesevaRequestDetails(ds, grievancesRequestDetailsForm.getPensioncardno(), grievancesRequestDetailsForm.getDistrict_id());
                if (requestDetails != null && requestDetails.size() > 0) {
                    request.setAttribute("msg", "Request for Pension No " + grievancesRequestDetailsForm.getPensioncardno() + " already raised on " + requestDetails.get(2) + ". Application No. " + requestDetails.get(0) + " and current status is " + requestDetails.get(1));
                    target = "submitPensionNo";
                } else {
                    partADTO = partAService.getExistingPensionDetails(grievancesRequestDetailsForm.getPensioncardno(), grievancesRequestDetailsForm.getDistrict_id(), ds, request);

                    if (partADTO != null && !"".equals(partADTO)) {
                        if (partADTO.getDeathcase() != null && CommonConstants.DEATH_CASE.equals(partADTO.getDeathcase())) {
                            request.setAttribute("msg", "Pensioner Deleted due to " + partADTO.getReasonforstatus());
                            target = "submitPensionNo";
                        } else if (partADTO.getPersoncode() != null && !"".equals(partADTO.getPersoncode())) {
                            request.setAttribute("msg", "SADAREM ID " + partADTO.getPersoncode() + " already Generated for the Pension Number");
                            target = "submitPensionNo";
                        } else {
                            if (partADTO.getDistrtictid() != null && !"".equals(partADTO.getDistrtictid())) {
                                request.setAttribute("district_id", partADTO.getDistrtictid());
                            }
                            if (partADTO.getMandalid() != null && !"".equals(partADTO.getMandalid())) {
                                request.setAttribute("mandal_id", partADTO.getMandalid());
                            }
                            if (partADTO.getVillageid() != null && !"".equals(partADTO.getVillageid())) {
                                request.setAttribute("village_id", partADTO.getVillageid());
                            }
                            if (partADTO.getHabitationid() != null && !"".equals(partADTO.getHabitationid())) {
                                request.setAttribute("habitation_id", partADTO.getHabitationid());
                            }
                            if (partADTO.getPanchayatiid() != null && !"".equals(partADTO.getPanchayatiid())) {
                                request.setAttribute("panchayat_id", partADTO.getPanchayatiid());
                            }

                            if (partADTO.getHouseno() != null && !"".equals(partADTO.getHouseno().trim())) {
                                request.setAttribute("houseno", partADTO.getHouseno().trim());
                            }
                            BeanUtils.copyProperties(grievancesRequestDetailsForm, partADTO);
                            if (partADTO.getDistrict() != null && !"".equals(partADTO.getDistrict())) {
                                grievancesRequestDetailsForm.setDistrictName(partADTO.getDistrict());
                            }
                            if (partADTO.getMandal() != null && !"".equals(partADTO.getMandal())) {
                                grievancesRequestDetailsForm.setMandalName(partADTO.getMandal());
                            }
                            if (partADTO.getTownVillage() != null && !"".equals(partADTO.getTownVillage())) {
                                grievancesRequestDetailsForm.setVillageName(partADTO.getTownVillage());
                            }
                            if (partADTO.getHabitation() != null && !"".equals(partADTO.getHabitation())) {
                                grievancesRequestDetailsForm.setHabitationName(partADTO.getHabitation());
                            }
                            grievancesRequestDetailsForm.setRelationName(partADTO.getGsurname());
                            grievancesRequestDetailsForm.setAge(partADTO.getNoOfYears());
                            grievancesRequestDetailsForm.setPensioncardno(pensionNo);
                            target = "withPensionNo";
                        }
                    } else {
                        request.setAttribute("msg", "Please Enter Valid Pension Number");
                        target = "submitPensionNo";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward submitMesevaNewCertificateDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrievancesRequestDetailsForm grievancesRequestDetailsForm = (GrievancesRequestDetailsForm) form;
        String target = "exception";
        int success = 0;
        String extension = null;
        File dir = null;
        String name = null;
        CommonDetails commonDetails = new CommonDetails();
        boolean uploadSuccess = false;
        ArrayList proofList = new ArrayList();
        ArrayList districtList = new ArrayList();
        ArrayList mandalList = new ArrayList();
        ArrayList panchayatList = new ArrayList();
        ArrayList villageList = new ArrayList();
        ArrayList habitationList = new ArrayList();
        String flag = null;
        String habCode = null;
        String flags = null;
        int updateMeesevaId = 0;
        File sourceDir = null;
        File targetDir = null;
        boolean valid = true;

        try {
            String Systemip = request.getRemoteAddr();
            GrievancesRequestDetailsService detailsService = (GrievancesRequestDetailsService) GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
            GrievancesRequestDetailsDTO grievancesRequestDetailsDTO = new GrievancesRequestDetailsDTO();
            GrievancesRequestDetailsDAO grievancesRequestDetailsDAO = new GrievancesRequestDetailsDAO();
            GrievancesRequestDetailsDTO RequestDetailsDTO = null;
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            PartADAO partADAO = new PartADAO();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("flag") != null && !request.getParameter("flag").equalsIgnoreCase("")) {
                flag = request.getParameter("flag");
            }
            TerritoryDAO territoryDAO = new TerritoryDAO();
            districtList = territoryservice.getDistricts(ds);
            grievancesRequestDetailsForm.setDistrictlist(districtList);

            mandalList = territoryDAO.getMandals(ds, grievancesRequestDetailsForm.getDistrict_id());
            grievancesRequestDetailsForm.setMandallist(mandalList);

            panchayatList = territoryDAO.getPanchayats(ds, grievancesRequestDetailsForm.getDistrict_id(), grievancesRequestDetailsForm.getMandal_id());
            grievancesRequestDetailsForm.setPanchayatlist(panchayatList);

            villageList = territoryDAO.getVillages(ds, grievancesRequestDetailsForm.getDistrict_id(), grievancesRequestDetailsForm.getMandal_id(), grievancesRequestDetailsForm.getPanchayat_id());
            grievancesRequestDetailsForm.setVillagelist(villageList);

            habitationList = territoryDAO.getHabitations(ds, grievancesRequestDetailsForm.getDistrict_id(), grievancesRequestDetailsForm.getMandal_id(), grievancesRequestDetailsForm.getPanchayat_id(), grievancesRequestDetailsForm.getVillage_id());
            grievancesRequestDetailsForm.setHabitationlist(habitationList);

            proofList = detailsService.getUploadProofTypeList(ds, "5", "1");
            grievancesRequestDetailsForm.setProofList(proofList);

            if (flag != null && flag.equalsIgnoreCase("without")) {
                habCode = partADAO.checkHabitation(ds, grievancesRequestDetailsForm.getDistrict_id(),
                        grievancesRequestDetailsForm.getMandal_id(),
                        grievancesRequestDetailsForm.getPanchayat_id(),
                        null, grievancesRequestDetailsForm.getVillage_id(), grievancesRequestDetailsForm.getHabitation_id());
                if (habCode == null) {
                    request.setAttribute("msg", "<font color='red'>HabitationCode does not match. Please select valid Habitation</font>");
                    if (grievancesRequestDetailsForm.getProofType() != null
                            && grievancesRequestDetailsForm.getProofType().equalsIgnoreCase("Adhaar Card")) {
                        grievancesRequestDetailsForm.setRelationName("");
                        grievancesRequestDetailsForm.setProofId("");
                        grievancesRequestDetailsForm.setProofType("");
                        grievancesRequestDetailsForm.setRelationType("");
                        grievancesRequestDetailsForm.setAge("");
                        grievancesRequestDetailsForm.setPhoneno("");
                    }

                    return mapping.findForward("withoutPensionNo");
                }
            }
            if ((flag != null && flag.equalsIgnoreCase("without") && habCode != null) || (flag != null && flag.equalsIgnoreCase("with"))) {
                if (grievancesRequestDetailsForm.getProofType() != null
                        && !grievancesRequestDetailsForm.getProofType().equalsIgnoreCase("Adhaar Card")) {
                    if (flag.equalsIgnoreCase("without") && grievancesRequestDetailsForm.getUploadDocument() != null && !grievancesRequestDetailsForm.getUploadDocument().equals("")) {
                        FormFile myFile = grievancesRequestDetailsForm.getUploadDocument();
                        String fileName = myFile.getFileName();
                        int dotPos = fileName.lastIndexOf(".");
                        extension = fileName.substring(dotPos);

                        dir = new File(getServlet().getServletContext().getRealPath("/") + "RDSADAREMDOWNLOADS");

                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        name = grievancesRequestDetailsForm.getApplicationNo() + extension;
                        int filesizeInKB = myFile.getFileSize() / 1024;
                        if (filesizeInKB > 100) {
                            request.setAttribute("msg", "You Need to upload Document less than 100KB");
                            valid = false;
                            return mapping.findForward("withoutPensionNo");

                        }
                    }
                }
                if (grievancesRequestDetailsForm.getProofType() != null
                        && grievancesRequestDetailsForm.getProofType().equalsIgnoreCase("Adhaar Card")) {
                    System.out.println("surname--"+grievancesRequestDetailsForm.getAdhaarSurname());
                    System.out.println("getAdhaarName--"+grievancesRequestDetailsForm.getAdhaarName());
                    grievancesRequestDetailsForm.setSurname(grievancesRequestDetailsForm.getAdhaarSurname());
                    grievancesRequestDetailsForm.setFirstname(grievancesRequestDetailsForm.getAdhaarName());
                    grievancesRequestDetailsForm.setRelationName(grievancesRequestDetailsForm.getAdhaarRelationName());
                    grievancesRequestDetailsForm.setDistrict_id(grievancesRequestDetailsForm.getAdhaarDistrictId());
                    grievancesRequestDetailsForm.setMandal_id(grievancesRequestDetailsForm.getAdhaarMandalId());
                    grievancesRequestDetailsForm.setGender(grievancesRequestDetailsForm.getAdhaarGender());
                    grievancesRequestDetailsForm.setHouseno(grievancesRequestDetailsForm.getAdhaarHouseNo());
                    valid = true;
                }
                if (valid) {
                    String checksum = null;
                    MeeSevaWebServiceSoapProxy proxy = new MeeSevaWebServiceSoapProxy();
                    try {
                        String[] arrPaymentDetails = new String[9];
                        arrPaymentDetails[0] = grievancesRequestDetailsForm.getUniqueNo();
                        arrPaymentDetails[1] = grievancesRequestDetailsForm.getSCAUserId();
                        arrPaymentDetails[2] = "CA";
                        arrPaymentDetails[3] = grievancesRequestDetailsForm.getLoginId();
                        arrPaymentDetails[4] = grievancesRequestDetailsForm.getChannelId();
                        arrPaymentDetails[5] = grievancesRequestDetailsForm.getApplicationNo();
                        arrPaymentDetails[6] = grievancesRequestDetailsForm.getRequestId();
                        arrPaymentDetails[7] = grievancesRequestDetailsForm.getServiceid();
                        arrPaymentDetails[8] = grievancesRequestDetailsForm.getScaPassword();

                        String[] arrAmount = new String[5];
                        arrAmount[0] = "25";// this.getUserCharge() + "";
                        arrAmount[1] = "0";// this.getDocAmount() + "";
                        arrAmount[2] = "0";
                        arrAmount[3] = "0";// this.getAppAmount() + "";
                        arrAmount[4] = "0";//

                        String[] arrTransParams = new String[4];
                        arrTransParams[0] = "Applicant Name";
                        arrTransParams[1] = "District";
                        arrTransParams[2] = "MobileNo";
                        arrTransParams[3] = "Total Amount";

                        String[] arrTransDetails = new String[4];
                        arrTransDetails[0] = grievancesRequestDetailsForm.getFirstname();// this.dealerName;
                        arrTransDetails[1] = grievancesRequestDetailsForm.getDistrict_id();// this.getDistrict();
                        arrTransDetails[2] = grievancesRequestDetailsForm.getPhoneno();// this.getMobileNo();
                        arrTransDetails[3] = "25";// this.getTotAmount() + "";

                        checksum = GenerateCheckSum(grievancesRequestDetailsForm.getSCAUserId(), grievancesRequestDetailsForm.getScaPassword(),
                                grievancesRequestDetailsForm.getSCAUserId(), grievancesRequestDetailsForm.getLoginId(), grievancesRequestDetailsForm.getUniqueNo());
                        GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult xmlObj;

                        xmlObj = proxy.getPaymentGatewayResponse(arrPaymentDetails,
                                arrAmount, arrTransParams, arrTransDetails, "MEESEVA", "MEESEVA", checksum);

                        MessageElement[] elements = xmlObj.get_any();

                        Iterator iterator = elements[0].getChildElements();
                        String result = "";

                        while (iterator.hasNext()) {
                            MessageElement ele = (MessageElement) iterator.next();
                            result += ele.getValue() + "#";
                        }
                        String[] response1 = result.split("#");
                        flags = response1[0];

                    } catch (Exception exp) {
                        target = "meesevaException";
                    }
                    //End

                    if (flags != null && flags.length() > 0 && flags.equals("0")) {
                        if (flag.equalsIgnoreCase("without") && grievancesRequestDetailsForm.getUploadDocument() != null && !grievancesRequestDetailsForm.getUploadDocument().equals("")) {

                            uploadSuccess = commonDetails.uploadingFile(grievancesRequestDetailsForm.getUploadDocument(), "" + dir, name);
                            sourceDir = new File(getServlet().getServletContext().getRealPath("/") + "RDSADAREMDOWNLOADS");

                            targetDir = new File(CommonConstants.FILEVIEWPATH);
                            if (sourceDir.exists()) {
                                commonDetails.copyDirectoryandFile(sourceDir, targetDir);
                            }
                            //delete directory start
                            File directory = new File(getServlet().getServletContext().getRealPath("/") + "RDSADAREMDOWNLOADS");
                            //make sure directory exists
                            if (!directory.exists()) {
                                //System.exit(0);
                            } else {
                                try {
                                    commonDetails.delete(directory);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    //System.exit(0);
                                }
                            }
                        }
                        grievancesRequestDetailsForm.setSystemIp(request.getRemoteAddr());
                        BeanUtils.copyProperties(grievancesRequestDetailsDTO, grievancesRequestDetailsForm);

                        if (grievancesRequestDetailsForm.getProofType().equalsIgnoreCase("Adhaar Card")) {
                            uploadSuccess = true;
                        }

                        if ((uploadSuccess && flag.equalsIgnoreCase("without")) || (flag.equalsIgnoreCase("with"))) {
                            success = detailsService.submitMesevaNewCertificateDetails(ds, grievancesRequestDetailsDTO);
                            if (success > 0) {

                                try {
                                    // TODO initialize WS operation arguments here

                                    String checksum1 = null;
                                    String transId = null;
                                    checksum1 = GenerateCheckSum(grievancesRequestDetailsForm.getSCAUserId(), grievancesRequestDetailsForm.getScaPassword(),
                                            grievancesRequestDetailsForm.getSCAUserId(), grievancesRequestDetailsForm.getLoginId(), grievancesRequestDetailsForm.getUniqueNo());

                                    String[] arrPaymentDetails1 = new String[12];

                                    arrPaymentDetails1[0] = grievancesRequestDetailsForm.getUniqueNo();
                                    arrPaymentDetails1[1] = grievancesRequestDetailsForm.getSCAUserId();
                                    arrPaymentDetails1[2] = "CA";
                                    arrPaymentDetails1[3] = grievancesRequestDetailsForm.getLoginId();
                                    arrPaymentDetails1[4] = grievancesRequestDetailsForm.getChannelId();
                                    arrPaymentDetails1[5] = grievancesRequestDetailsForm.getApplicationNo();
                                    arrPaymentDetails1[6] = grievancesRequestDetailsForm.getRequestId();
                                    arrPaymentDetails1[7] = grievancesRequestDetailsForm.getServiceid();
                                    arrPaymentDetails1[8] = grievancesRequestDetailsDAO.SADAREMTransctionId(ds, grievancesRequestDetailsForm.getApplicationNo());
                                    arrPaymentDetails1[9] = "00";
                                    arrPaymentDetails1[10] = grievancesRequestDetailsForm.getScaPassword();
                                    arrPaymentDetails1[11] = grievancesRequestDetailsForm.getMeesevaFlag();
                                    String[] arrAmount1 = new String[5];
                                    arrAmount1[0] = "25";
                                    arrAmount1[1] = "0";
                                    arrAmount1[2] = "0";
                                    arrAmount1[3] = "0";
                                    arrAmount1[4] = "0";

                                    String[] arrTransParams1 = new String[9];
                                    arrTransParams1[0] = "Applicant Name";
                                    arrTransParams1[1] = "DistrictId";
                                    arrTransParams1[2] = "MandalId";
                                    arrTransParams1[3] = "VillageId";
                                    arrTransParams1[4] = "SLA";
                                    arrTransParams1[5] = "DeliveryType";
                                    arrTransParams1[6] = "Total Amount";
                                    arrTransParams1[7] = "Status";
                                    arrTransParams1[8] = "SLAEndDate";

                                    String[] arrTransDetails1 = new String[9];
                                    arrTransDetails1[0] = grievancesRequestDetailsForm.getFirstname();
                                    arrTransDetails1[1] = grievancesRequestDetailsForm.getDistrict_id();
                                    arrTransDetails1[2] = grievancesRequestDetailsForm.getMandal_id();
                                    arrTransDetails1[3] = grievancesRequestDetailsForm.getVillage_id();
                                    arrTransDetails1[4] = "0";
                                    arrTransDetails1[5] = "Manual";
                                    arrTransDetails1[6] = "25";
                                    arrTransDetails1[7] = "02";
                                    arrTransDetails1[8] = "";

                                    GetPaymentTransIdResponseGetPaymentTransIdResult transResp = proxy.getPaymentTransId(
                                            arrPaymentDetails1, arrAmount1, arrTransParams1,
                                            arrTransDetails1, "MEESEVA", "MEESEVA", checksum1);

                                    MessageElement[] transElements = transResp.get_any();
                                    Iterator transIt = transElements[0].getChildElements();
                                    String transResult = "";


                                    while (transIt.hasNext()) {
                                        MessageElement tranEle = (MessageElement) transIt.next();
                                        ;
                                        transResult += tranEle.getValue() + "#";

                                    }

                                    String[] transRespDtls = transResult.split("#");
                                    if (transRespDtls[1].toString().equals("0")) {
                                        transId = transRespDtls[2];
                                    }
                                    if (transId != null && !transId.equalsIgnoreCase("")) {
                                        updateMeesevaId = detailsService.updateMEESEVATransctionId(ds, grievancesRequestDetailsForm.getApplicationNo(), transId);
                                    } else {
                                        //balue start
                                        grievancesRequestDetailsForm.setSystemIp(Systemip);
                                        int failtrans = 0;

                                        failtrans = detailsService.submitMeesevaFialTransctionDetails(
                                                ds,
                                                grievancesRequestDetailsForm.getApplicationNo(),
                                                grievancesRequestDetailsForm.getUniqueNo(),
                                                grievancesRequestDetailsForm.getSCAUserId(),
                                                grievancesRequestDetailsForm.getLoginId(),
                                                grievancesRequestDetailsForm.getChannelId(),
                                                grievancesRequestDetailsForm.getRequestId(),
                                                grievancesRequestDetailsForm.getServiceid(),
                                                grievancesRequestDetailsDAO.SADAREMTransctionId(ds, grievancesRequestDetailsForm.getApplicationNo()),
                                                grievancesRequestDetailsForm.getScaPassword(),
                                                grievancesRequestDetailsForm.getMeesevaFlag(),
                                                grievancesRequestDetailsForm.getFirstname(),
                                                grievancesRequestDetailsForm.getDistrict_id(),
                                                grievancesRequestDetailsForm.getMandal_id(),
                                                grievancesRequestDetailsForm.getVillage_id(),
                                                grievancesRequestDetailsForm.getSystemIp());

                                        //System.out.println("failtrans=> "+failtrans);

                                        //balu end
                                    }

                                    RequestDetailsDTO = detailsService.getRequestDetails(ds, grievancesRequestDetailsForm.getApplicationNo());

                                    if (RequestDetailsDTO != null) {
                                        request.setAttribute("applicationNo", grievancesRequestDetailsForm.getApplicationNo());
                                        request.setAttribute("surName", RequestDetailsDTO.getSurname());
                                        request.setAttribute("name", RequestDetailsDTO.getFirstname());
                                        request.setAttribute("age", RequestDetailsDTO.getAge());
                                        request.setAttribute("houseNo", RequestDetailsDTO.getHouseno());
                                        request.setAttribute("districtName", RequestDetailsDTO.getDistrictName());
                                        request.setAttribute("mandalName", RequestDetailsDTO.getMandalName());
                                        request.setAttribute("villageName", RequestDetailsDTO.getVillageName());
                                        request.setAttribute("habitationName", RequestDetailsDTO.getHabitationName());
                                        request.setAttribute("panchayatName", RequestDetailsDTO.getPanchayatName());
                                        request.setAttribute("created_date", RequestDetailsDTO.getCreated_date());
                                        if (RequestDetailsDTO.getMeesevaId() != null && !RequestDetailsDTO.getMeesevaId().equalsIgnoreCase("")) {
                                            request.setAttribute("meesevaId", RequestDetailsDTO.getMeesevaId());
                                        } else {
                                            request.setAttribute("meesevaId", "-");
                                        }
                                        request.setAttribute("channelId", grievancesRequestDetailsForm.getChannelId());

                                    }
                                    target = "acknowledgment";
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward acknowlegmentPrint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrievancesRequestDetailsForm grievancesRequestDetailsForm = (GrievancesRequestDetailsForm) form;
        GrievancesRequestDetailsDTO RequestDetailsDTO = null;
        String channelId = "";
        String applicationNo = null;
        try {
            GrievancesRequestDetailsService detailsService = (GrievancesRequestDetailsService) GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
            if (request.getParameter("channelId") != null && !request.getParameter("channelId").equalsIgnoreCase("")) {
                channelId = request.getParameter("channelId");
            }
            if (request.getParameter("applicationNo") != null && !request.getParameter("applicationNo").equalsIgnoreCase("")) {
                applicationNo = request.getParameter("applicationNo");
            }
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (applicationNo != null) {
                RequestDetailsDTO = detailsService.getRequestDetails(ds, applicationNo);

                if (RequestDetailsDTO != null) {
                    request.setAttribute("applicationNo", grievancesRequestDetailsForm.getApplicationNo());
                    request.setAttribute("surName", RequestDetailsDTO.getSurname());
                    request.setAttribute("name", RequestDetailsDTO.getFirstname());
                    request.setAttribute("age", RequestDetailsDTO.getAge());
                    request.setAttribute("houseNo", RequestDetailsDTO.getHouseno());
                    request.setAttribute("districtName", RequestDetailsDTO.getDistrictName());
                    request.setAttribute("mandalName", RequestDetailsDTO.getMandalName());
                    request.setAttribute("villageName", RequestDetailsDTO.getVillageName());
                    request.setAttribute("habitationName", RequestDetailsDTO.getHabitationName());
                    request.setAttribute("panchayatName", RequestDetailsDTO.getPanchayatName());
                    request.setAttribute("created_date", RequestDetailsDTO.getCreated_date());
                    request.setAttribute("channelId", channelId);
                    if (RequestDetailsDTO.getMeesevaId() != null && !RequestDetailsDTO.getMeesevaId().equalsIgnoreCase("")) {
                        request.setAttribute("meesevaId", RequestDetailsDTO.getMeesevaId());
                    } else {
                        request.setAttribute("meesevaId", "-");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("acknowledgmentPrint");
    }

    public ActionForward getAadhaarProofDetials(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        GrievancesRequestDetailsForm grievancesRequestDetailsForm = (GrievancesRequestDetailsForm) form;
        GrievancesRequestDetailsService detailsService = (GrievancesRequestDetailsService) GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
        ArrayList proofList = new ArrayList();
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        ArrayList districtList = new ArrayList();
        ArrayList mandalList = new ArrayList();
        ArrayList panchayatList = new ArrayList();
        ArrayList villageList = new ArrayList();
        ArrayList habitationList = new ArrayList();
        GrievancesRequestDetailsDAO grievancesRequestDetailsDAO = new GrievancesRequestDetailsDAO();
        String districtName = null;
        String mandalName = null;


        try {

            proofList = detailsService.getUploadProofTypeList(ds, "5", "1");
            grievancesRequestDetailsForm.setProofList(proofList);
            districtList = territoryservice.getDistricts(ds);
            grievancesRequestDetailsForm.setDistrictlist(districtList);

            grievancesRequestDetailsForm.setDistrict_id(grievancesRequestDetailsForm.getDistrict_id());
            //System.out.println("Dist Action===" + grievancesRequestDetailsForm.getDistrict_id());
            mandalList = territoryservice.getMandals(ds, grievancesRequestDetailsForm.getDistrict_id());
            grievancesRequestDetailsForm.setMandallist(mandalList);
            if (grievancesRequestDetailsForm.getProofId() != null) {
                grievancesRequestDetailsForm.setProofId(grievancesRequestDetailsForm.getProofId());
            }


            if (grievancesRequestDetailsForm.getProofType() != null
                    && grievancesRequestDetailsForm.getProofType().equalsIgnoreCase("Adhaar Card")
                    && grievancesRequestDetailsForm.getProofId() != null
                    && !grievancesRequestDetailsForm.getProofId().equals("")) {

                ServicesLocator l = new ServicesLocator();
                String endpoinaddr = l.getServicesSOAP11port_httpAddress();
                ServicesPortType proxy = null;
                try {
                    proxy = l.getServicesSOAP11port_http((new URL(endpoinaddr)));
                } catch (Exception e) {
                    // TODO Auto-generated catch block 548975603635
                    e.printStackTrace();
                }
                ((ServicesSOAP11BindingStub) proxy).setUsername("srdhuidaidemo");
                ((ServicesSOAP11BindingStub) proxy).setPassword("srdhuidaidemo@#$#");
                ((ServicesSOAP11BindingStub) proxy).setTimeout(1000000);

                if (grievancesRequestDetailsForm.getProofId() != null) {

                    int districtId = Integer.parseInt(((ServicesSOAP11BindingStub) proxy).getUIDSRDHInfo(grievancesRequestDetailsForm.getProofId()).getDistrict());
                    int invalidFlag = Integer.parseInt(((ServicesSOAP11BindingStub) proxy).getUIDSRDHInfo(grievancesRequestDetailsForm.getProofId()).getDistrict());

                    String district = ((ServicesSOAP11BindingStub) proxy).getUIDSRDHInfo(grievancesRequestDetailsForm.getProofId()).getDistrict();
                    if (district != null && district.length() == 1) {
                        district = "0" + district;
                    }
                    grievancesRequestDetailsForm.setDistrict_id(district);
                    if (districtId > 13 && districtId <= 23) {
                        request.setAttribute("adhaarFlag", "adhaarFlag");
                        districtName = grievancesRequestDetailsDAO.getDistrictName(ds, grievancesRequestDetailsForm.getDistrict_id());
                        grievancesRequestDetailsForm.setAdhaarDistrictName(districtName);

                        String name = (((ServicesSOAP11BindingStub) proxy).getUIDSRDHInfo(grievancesRequestDetailsForm.getProofId()).getName());

                        String a[] = name.split(" ", 2);
                        if (a.length >= 2) {
                            for (int j = 0; j < a.length; j++) {
                                grievancesRequestDetailsForm.setAdhaarSurname(a[0]);
                                grievancesRequestDetailsForm.setAdhaarName(a[1]);
                                break;
                            }
                        } else {
                            grievancesRequestDetailsForm.setAdhaarName(name);
                        }

                        grievancesRequestDetailsForm.setAdhaarRelationName((((ServicesSOAP11BindingStub) proxy).getUIDSRDHInfo(grievancesRequestDetailsForm.getProofId()).getCareof()));
                        grievancesRequestDetailsForm.setDistrict_id(district);
                        grievancesRequestDetailsForm.setMandal_id((((ServicesSOAP11BindingStub) proxy).getUIDSRDHInfo(grievancesRequestDetailsForm.getProofId()).getMandal()));
                        grievancesRequestDetailsForm.setVillage_id((((ServicesSOAP11BindingStub) proxy).getUIDSRDHInfo(grievancesRequestDetailsForm.getProofId()).getVillage()));
                        grievancesRequestDetailsForm.setAdhaarHouseNo((((ServicesSOAP11BindingStub) proxy).getUIDSRDHInfo(grievancesRequestDetailsForm.getProofId()).getBuildingName()));


                        mandalName = grievancesRequestDetailsDAO.getMandalName(ds, grievancesRequestDetailsForm.getDistrict_id(), grievancesRequestDetailsForm.getMandal_id());
                        grievancesRequestDetailsForm.setAdhaarMandalName(mandalName);

                        panchayatList = territoryservice.getPanchayats(ds, grievancesRequestDetailsForm.getDistrict_id(), grievancesRequestDetailsForm.getMandal_id());
                        grievancesRequestDetailsForm.setPanchayatlist(panchayatList);

                        String gender = (((ServicesSOAP11BindingStub) proxy).getUIDSRDHInfo(grievancesRequestDetailsForm.getProofId()).getGender());

                        if (gender != null && gender.equalsIgnoreCase("M")) {
                            grievancesRequestDetailsForm.setAdhaarGender("Male");
                        } else {
                            grievancesRequestDetailsForm.setAdhaarGender("Female");
                        }
                        grievancesRequestDetailsForm.setAdhaarDistrictId(grievancesRequestDetailsForm.getDistrict_id());
                        grievancesRequestDetailsForm.setAdhaarMandalId(grievancesRequestDetailsForm.getMandal_id());
                    } else if (invalidFlag == 201) {
                        request.setAttribute("msg", "<font color='red'>This No " + grievancesRequestDetailsForm.getProofId() + " not belongs to Aadhaar Card No.</font>");
                        grievancesRequestDetailsForm.setProofId("");
                        grievancesRequestDetailsForm.setProofType("");
                    } else if (districtId < 13) {
                        request.setAttribute("msg", "<font color='red'>This AadharCard No " + grievancesRequestDetailsForm.getProofId() + " is belongs to Andhra Pradesh State so please check in telangana SADAREM website.</font>");
                        grievancesRequestDetailsForm.setFirstname("");
                        grievancesRequestDetailsForm.setProofType("");
                        grievancesRequestDetailsForm.setProofId("");
                        grievancesRequestDetailsForm.setRelationName("");
                        grievancesRequestDetailsForm.setMandal_id("0");
                        grievancesRequestDetailsForm.setDistrict_id("0");
                        grievancesRequestDetailsForm.setVillage_id("0");
                        //grievancesRequestDetailsForm.setRelation("0");
                        grievancesRequestDetailsForm.setRelationType("0");
                        grievancesRequestDetailsForm.setAge("");
                        grievancesRequestDetailsForm.setHouseno("");

                    } else {
                        request.setAttribute("msg", "<font color='red'>This No " + grievancesRequestDetailsForm.getProofId() + " not belongs to Aadhaar Card No.</font>");
                        grievancesRequestDetailsForm.setProofId("");
                        grievancesRequestDetailsForm.setProofType("");
                    }
                }
            } else {
                grievancesRequestDetailsForm.setFirstname("");
                //grievancesRequestDetailsForm.setProofType("");
                //grievancesRequestDetailsForm.setProofId("");
                grievancesRequestDetailsForm.setRelationName("");
                grievancesRequestDetailsForm.setMandal_id("0");
                grievancesRequestDetailsForm.setDistrict_id("0");
                grievancesRequestDetailsForm.setVillage_id("0");
                //grievancesRequestDetailsForm.setRelation("0");588964152644

                grievancesRequestDetailsForm.setRelationType("0");
                grievancesRequestDetailsForm.setAge("");
                grievancesRequestDetailsForm.setHouseno("");

                //System.out.println("33Action==");
            }
            if (grievancesRequestDetailsForm.getProofType() != null
                    && !grievancesRequestDetailsForm.getProofType().equalsIgnoreCase("Adhaar Card")) {
                request.setAttribute("upladFlag", "upladFlag");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return mapping.findForward("withoutPensionNo");
    }
}

