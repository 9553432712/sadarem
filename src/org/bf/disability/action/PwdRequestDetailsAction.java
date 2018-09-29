/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dao.PwdRequestDAO;
import org.bf.disability.dto.AppealPersonalDataDTO;
import org.bf.disability.dto.PwdRequestDTO;
import org.bf.disability.form.PwdRequestForm;
import org.bf.disability.service.AppletAuthorityService;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.service.pwdRequestService;
import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.servicefactory.PwdRequestServiceFactory;
import org.bf.disability.util.EmailUtility;

/**
 *
 * @author 693461
 */
public class PwdRequestDetailsAction extends DispatchAction {

    /* forward name="success" path="" */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";

        return mapping.findForward(target);
    }

    public ActionForward backRequest(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "loginHome";

        PwdRequestForm PwdRequestForm = (PwdRequestForm) form;

        //personal
        //PwdRequestForm.setPersonal("2");


        return mapping.findForward(target);
    }

    //To Forward  Renual Details
    public ActionForward renualDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "renual";

        PwdRequestForm PwdRequestForm = (PwdRequestForm) form;

        String renualId = request.getParameter("renualId");
        request.setAttribute("renualId", renualId);


        return mapping.findForward(target);
    }

    //End For Renula Details.
    //To Forward  ReAssessment
    public ActionForward reAssessmentDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "renual";

        PwdRequestForm PwdRequestForm = (PwdRequestForm) form;

        String reAssessmentId = request.getParameter("reAssessmentId");

        request.setAttribute("reAssessmentId", reAssessmentId);
        return mapping.findForward(target);
    }

    //End For ReAssessment
    public ActionForward getRequestTypeDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        PwdRequestForm PwdRequestForm = (PwdRequestForm) form;

        DataSource ds = null;

        ArrayList requestListPersonal = new ArrayList();
        ArrayList requestListNewDetails = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            pwdRequestService pwdRequestService = PwdRequestServiceFactory.getPwdRequestServiceImpl();

            if (PwdRequestForm.getPersonal().equals("1")) {
                requestListPersonal = pwdRequestService.getPwdRequestList(ds, PwdRequestForm.getPersonal());
                PwdRequestForm.setRequestTypeList(requestListPersonal);
                request.setAttribute("radio1", "radio1");
            } else if (PwdRequestForm.getPersonal().equals("2")) {
                requestListNewDetails = pwdRequestService.getPwdRequestList(ds, PwdRequestForm.getPersonal());
                request.setAttribute("requestListNewDetails", requestListNewDetails);
                PwdRequestForm.setRequestTypeDetails(requestListNewDetails);
                request.setAttribute("radio2", "radio2");
            }

            PwdRequestForm.reset(mapping, request);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getMandalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        PwdRequestForm PwdRequestForm = (PwdRequestForm) form;
        pwdRequestService pwdRequestService = PwdRequestServiceFactory.getPwdRequestServiceImpl();

        DataSource ds = null;

        ArrayList mandallist = new ArrayList();
        ArrayList panchayList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();

            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"),"");
                PwdRequestForm.setMandallist(mandallist);
            }

            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("") && request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                panchayList = pwdRequestService.getPanchayatDetails(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                PwdRequestForm.setPanchayatlist(panchayList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);

    }

    public ActionForward getVillageDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";

        PwdRequestForm pwdRequestForm = (PwdRequestForm) form;

        DataSource ds = null;

        ArrayList villagelist = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();

            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                villagelist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                pwdRequestForm.setVillagelist(villagelist);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getValidateSadaremDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String target = "success";

        PwdRequestForm PwdRequestForm = (PwdRequestForm) form;
        AppealPersonalDataDTO dto1 = new AppealPersonalDataDTO();
        PartADAO dao = new PartADAO();
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();
        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();

        String msg = null;
        String renual = null;
        String duplicate = null;
        DataSource ds = null;

        int checkSadarermIDNo = 0;
        ArrayList requestListPersonal = new ArrayList();
        ArrayList requestListNewDetails = new ArrayList();
        AppealPersonalDataDTO dto = new AppealPersonalDataDTO();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (!PwdRequestForm.getRequestDetailsId().equals(null) && PwdRequestForm.getRequestDetailsId().equals("6")) {
                //   request.setAttribute("99","99");
            }


            pwdRequestService pwdRequestService = PwdRequestServiceFactory.getPwdRequestServiceImpl();
            AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();



//            pwdRequestDTO = pwdRequestService.checkSadarermIDNo(ds, PwdRequestForm.getSadaremID());


//            if (pwdRequestDTO.getSadaremValidMsg()==1) {

            if (!PwdRequestForm.getPersonal().equals(null) && PwdRequestForm.getPersonal().equals("1")) {
                requestListPersonal = pwdRequestService.getPwdRequestList(ds, PwdRequestForm.getPersonal());
                PwdRequestForm.setRequestTypeList(requestListPersonal);
                request.setAttribute("radio1", "radio1");

            } else if (!PwdRequestForm.getPersonal().equals(null) && PwdRequestForm.getPersonal().equals("2")) {
                requestListNewDetails = pwdRequestService.getPwdRequestList(ds, PwdRequestForm.getPersonal());
                PwdRequestForm.setRequestTypeDetails(requestListNewDetails);
                request.setAttribute("radio2", "radio2");

                if (!PwdRequestForm.getRequestDetailsId().equals(null) && PwdRequestForm.getRequestDetailsId().equals("6")) {
                    request.setAttribute("99", "99");
                }
            }

            if (!PwdRequestForm.getRequestDetailsId().equals(null) && PwdRequestForm.getRequestDetailsId().equals("6")) {
            } else if (!PwdRequestForm.getRequestDetailsId().equals(null) && PwdRequestForm.getRequestDetailsId().equals("7")) {

                dto1 = dao.getPersonalDetailsForAppealAuthority(ds, PwdRequestForm.getSadaremID());
                String errormsg = dto1.getError_msg();

                if (errormsg != null && errormsg.equalsIgnoreCase("NO")) {
                    request.setAttribute("ressessment", "ressessment");
                    request.setAttribute("document", "document");

                } else {
                    request.setAttribute("errormsg", errormsg);
                    PwdRequestForm.setSadaremID("");
                    // request.setAttribute("ressessment", "ressessment");
                    request.setAttribute("document", "document");
                }

            } else if (!PwdRequestForm.getRequestDetailsId().equals(null) && PwdRequestForm.getRequestDetailsId().equals("8")) {

                // request.setAttribute("errormsg", "");
                pwdRequestDTO = pwdRequestService.duplicateCertificateDetails(ds, PwdRequestForm.getSadaremID());

                String duplicateerrorMsg = pwdRequestDTO.getError_msg();
                if (duplicateerrorMsg == null) {
//                        request.setAttribute("ressessment", "ressessment");
                    request.setAttribute("ressessment", "ressessment");
                    request.setAttribute("ressessment1", "ressessment1");
                    request.setAttribute("document", "document");

                } else {

                    request.setAttribute("duplicateerrormsg", duplicateerrorMsg);
                    // PwdRequestForm.setSadaremID("");

                    request.setAttribute("ressessment1", "ressessment1");
                    request.setAttribute("document", "document");

                }
            } else if (!PwdRequestForm.getRequestDetailsId().equals(null) && PwdRequestForm.getRequestDetailsId().equals("9")) {

                //request.setAttribute("errormsg", "");
                pwdRequestDTO = pwdRequestService.tempararyDetails(ds, PwdRequestForm.getSadaremID());

                if (renual != null) {
                    request.setAttribute("ressessment", "ressessment");
                    request.setAttribute("document", "document");
                } else {
                    request.setAttribute("errormsg", "Not Elgible For Renewal!");
                    PwdRequestForm.setSadaremID("");
                    //   request.setAttribute("ressessment", "ressessment");
                    request.setAttribute("document", "document");
                }

            } else if (!PwdRequestForm.getRequestDetailsId().equals(null) && PwdRequestForm.getRequestDetailsId().equals("10")) {

                //  boolean exist = appletAuthorityService.isEligibleforPhysicalRequirements(ds, PwdRequestForm.getSadaremID(), request);

                boolean exist = pwdRequestService.physicalImpairment(ds, PwdRequestForm.getSadaremID());


                if (exist) {
                    request.setAttribute("ressessment", "ressessment");
                    request.setAttribute("document", "document");
                } else {
                    // request.setAttribute("ressessment", "ressessment");
                    request.setAttribute("document", "document");
                    request.setAttribute("errormsg", "Personcode is not eligible for physical Requirements!!!");
                }
                //}
//            } else {
//                request.setAttribute("invalid", pwdRequestDTO.getSadaremIdErrorMsg());
//               //  PwdRequestForm.setSadaremID("");
//
//                if (!PwdRequestForm.getPersonal().equals(null) && PwdRequestForm.getPersonal().equals("1")) {
//                    requestListPersonal = pwdRequestService.getPwdRequestList(ds, PwdRequestForm.getPersonal());
//                    PwdRequestForm.setRequestTypeList(requestListPersonal);
//                    request.setAttribute("radio1", "radio1");
//
//                } else if (!PwdRequestForm.getPersonal().equals(null) && PwdRequestForm.getPersonal().equals("2")) {
//                    requestListNewDetails = pwdRequestService.getPwdRequestList(ds, PwdRequestForm.getPersonal());
//                    PwdRequestForm.setRequestTypeDetails(requestListNewDetails);
//                    request.setAttribute("radio2", "radio2");
//                }
//
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    // Start For Renual
    public ActionForward getValidateRenualDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String target = "renual";
        PwdRequestForm PwdRequestForm = (PwdRequestForm) form;
        AppealPersonalDataDTO dto1 = new AppealPersonalDataDTO();
        PartADAO dao = new PartADAO();
        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();
        PwdRequestDTO temaparyDetails = new PwdRequestDTO();
        String errormsg = "NO";
        DataSource ds = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            pwdRequestService pwdRequestService = PwdRequestServiceFactory.getPwdRequestServiceImpl();
            AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();


            pwdRequestDTO = pwdRequestService.checkSadarermIDNo(ds, PwdRequestForm.getSadaremID());

            if (pwdRequestDTO.getSadaremValidMsg() == 1) {


                if (!PwdRequestForm.getRenualId().equals(null) && PwdRequestForm.getRenualId().equals("9")) {

                    //request.setAttribute("errormsg", "");
                    temaparyDetails = pwdRequestService.tempararyDetails(ds, PwdRequestForm.getSadaremID());
                    // pwdRequestService.checkSadarermIDNo(ds, PwdRequestForm.getSadaremID());

                    errormsg = temaparyDetails.getTemparyerrormsg();


                    if (errormsg != null && errormsg.equalsIgnoreCase("NO")) {


                        request.setAttribute("ressessment", "ressessment");
                        request.setAttribute("document", "document");

                    } else {
                        request.setAttribute("errormsg", errormsg);

                        request.setAttribute("ressessment", "ressessment");

                        // request.setAttribute("ressessment", "ressessment");
                        request.setAttribute("document", "document");
                    }

                } // Start ReAssessment
                else if (!PwdRequestForm.getReAssessmentId().equals(null) && PwdRequestForm.getReAssessmentId().equals("7")) {

                    dto1 = dao.getPersonalDetailsForAppealAuthority(ds, PwdRequestForm.getSadaremID());
                    errormsg = dto1.getError_msg();

                    if (errormsg != null && errormsg.equalsIgnoreCase("NO")) {
                        request.setAttribute("ressessment", "ressessment");
                        request.setAttribute("document", "document");

                    } else {
                        request.setAttribute("errormsg", errormsg);
                        PwdRequestForm.setSadaremID("");
                        // request.setAttribute("ressessment", "ressessment");
                        request.setAttribute("document", "document");
                    }

                }
                //End.

            } else {
                request.setAttribute("invalid", pwdRequestDTO.getSadaremIdErrorMsg());
                //  PwdRequestForm.setSadaremID("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    //End
    public ActionForward getValidateSadaremDataPersoncode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String target = "success";

        PwdRequestForm PwdRequestForm = (PwdRequestForm) form;
        ArrayList requestListPersonal = new ArrayList();
        ArrayList requestListNewDetails = new ArrayList();
        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();
        DataSource ds = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            pwdRequestService pwdRequestService = PwdRequestServiceFactory.getPwdRequestServiceImpl();
            pwdRequestDTO = pwdRequestService.checkSadarermIDNoPersoncode(ds, PwdRequestForm.getSadaremIDDetails());

            if (pwdRequestDTO.getValidMsg() == 1) {
                request.setAttribute("radio", "radio");

                if (PwdRequestForm.getPersonal() != null && PwdRequestForm.getPersonal().equals("1")) {
                    requestListPersonal = pwdRequestService.getPwdRequestList(ds, PwdRequestForm.getPersonal());
                    PwdRequestForm.setRequestTypeList(requestListPersonal);
                    request.setAttribute("radio1", "radio1");
                } else if (PwdRequestForm.getPersonal() != null && PwdRequestForm.getPersonal().equals("2")) {
                    requestListNewDetails = pwdRequestService.getPwdRequestList(ds, PwdRequestForm.getPersonal());
                    PwdRequestForm.setRequestTypeDetails(requestListNewDetails);
                    request.setAttribute("radio2", "radio2");
                }
                /* if (PwdRequestForm.getRequestId() != null) {
                if (PwdRequestForm.getRequestId().length == 1) {
                request.setAttribute("personalName", "personalName");
                } else if (PwdRequestForm.getRequestId().length == 2) {
                request.setAttribute("personalName", "personalName");
                } else if (PwdRequestForm.getRequestId().length == 3) {
                request.setAttribute("personalName", "personalName");
                } else if (PwdRequestForm.getRequestId().length == 5) {
                request.setAttribute("personalName", "personalName");
                }
                }*/
            } else {
                request.setAttribute("invalid", pwdRequestDTO.getInvalidErrorMsg());
                // PwdRequestForm.setSadaremIDDetails("");
                if (PwdRequestForm.getPersonal() != null && PwdRequestForm.getPersonal().equals("1")) {
                    requestListPersonal = pwdRequestService.getPwdRequestList(ds, PwdRequestForm.getPersonal());
                    PwdRequestForm.setRequestTypeList(requestListPersonal);
                    request.setAttribute("radio1", "radio1");
                } else if (PwdRequestForm.getPersonal() != null && PwdRequestForm.getPersonal().equals("2")) {
                    requestListNewDetails = pwdRequestService.getPwdRequestList(ds, PwdRequestForm.getPersonal());
                    PwdRequestForm.setRequestTypeDetails(requestListNewDetails);
                    request.setAttribute("radio2", "radio2");
                }
                //  sadaremIDDetails

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward insertDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        PwdRequestForm pwdRequestForm = (PwdRequestForm) form;

        CommonDetails CommonDetails = new CommonDetails();
        InetAddress ownIP = InetAddress.getLocalHost();
        String systemIp = ownIP.getHostAddress();
        int requestInformationDetails = 0;
        String personCode = null;
        String fileName = null;
        String fileAddressName = null;
        DataSource ds = null;

        String extensionssc = null;
        String extensionAdress = null;
        String sscFolder = "SSCDOCUMENTS";
        String addressFloder = "AddressDOCUMENTS";
        int filesizeInKB = 0;
        int filesizeInKB1 = 0;
        boolean filePath = false;
        boolean fileaddressPath = false;
        int name = 0;
        int relationName = 0;
        boolean emailStatus = false;
        String emailBody = "";
        ArrayList emailBodyList = new ArrayList();
        EmailUtility emailUtility = new EmailUtility();
        String validRequestId = null;
        boolean vaildRequestSadaremId = false;

        ArrayList<InternetAddress> ToMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> CCMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> BCCMail = new ArrayList<InternetAddress>();

//File 1
        if (pwdRequestForm.getSscFile() != null && !pwdRequestForm.getSscFile().equals("")) {
            FormFile myFile = pwdRequestForm.getSscFile();
            fileName = myFile.getFileName();
            int dotPos = fileName.lastIndexOf(".");
            extensionssc = fileName.substring(dotPos);
            filesizeInKB = myFile.getFileSize() / 1024;

        }

//        //File 2
        if (pwdRequestForm.getAddressProof() != null && !pwdRequestForm.getAddressProof().equals("")) {
            FormFile myFileAdress = pwdRequestForm.getAddressProof();
            fileAddressName = myFileAdress.getFileName();
            int dotPosAdress = fileAddressName.lastIndexOf(".");
            extensionAdress = fileAddressName.substring(dotPosAdress);
            filesizeInKB1 = myFileAdress.getFileSize() / 1024;//
        }


        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            pwdRequestService pwdRequestService = PwdRequestServiceFactory.getPwdRequestServiceImpl();

            String arr[] = pwdRequestForm.getRequestId();
            StringBuilder builder = new StringBuilder();
            for (String s : arr) {
                builder.append(s);
                builder.toString();

            }

            // String pId1 = pwdRequestForm.getRequestId();

            request.setAttribute("pId1", builder.toString());

            if (extensionssc.equalsIgnoreCase(".jpg") && filesizeInKB <= 100 || extensionssc.equalsIgnoreCase(".gif") && filesizeInKB <= 100 || extensionssc.equalsIgnoreCase(".pdf")) {
                filePath = true;

            }

            if (extensionAdress.equalsIgnoreCase(".jpg") && filesizeInKB1 <= 100 || extensionAdress.equalsIgnoreCase(".gif") && filesizeInKB1 <= 100 || extensionAdress.equalsIgnoreCase(".pdf")) {
                fileaddressPath = true;

            }

            for (int i = 0; i < pwdRequestForm.getRequestId().length; i++) {
                validRequestId = pwdRequestForm.getRequestId()[i];

            }

            pwdRequestDTO = pwdRequestService.validRequestSADAREMID(ds, pwdRequestForm.getSadaremIDDetails(), validRequestId);


            if (pwdRequestDTO.getRequestCountId() != null && pwdRequestDTO.getRequestCountId().equals("1")) {
                vaildRequestSadaremId = true;
                request.setAttribute("validRequestName", "Already request is raised " + pwdRequestDTO.getRequestCountName());
                target = "success";
                // request.setAttribute("radio", "radio");

            } else if (filePath == true && fileaddressPath == true) {
                requestInformationDetails = pwdRequestService.insertRequestInformationDetails(ds, pwdRequestForm, systemIp);
            }


            String arr1[] = pwdRequestForm.getRequestId();

            StringBuilder builder1 = new StringBuilder();
            for (String s : arr1) {
                builder.append(s);
                builder1.toString();

            }
            name = builder1.toString().indexOf('1');

            relationName = builder1.toString().indexOf('2');

//           if(name>=0 || relationName>=0 && requestInformationDetails >0){

            if (requestInformationDetails > 0) {
                // toList = pwdRequestService.getTORequestNameDetails(ds);
                // ccList = pwdRequestService.getCCRequestNameDetails(ds, pwdRequestForm);
//              for (int i = 0; i < toList.size(); i++) {
//                        toEmailId = toList.get(i).toString();
//                        emailId = new InternetAddress(toEmailId, "");
//                        ToMail.add(emailId);
//                    }
//
//               for (int i = 0; i < ccList.size(); i++) {
//                        ccEmailId = ccList.get(i).toString();
//                        emailId = new InternetAddress(ccEmailId, "");
//                        CCMail.add(emailId); sadaremIDDetails
//                    }
                emailBodyList = pwdRequestService.getPwdBodyDetails(ds, pwdRequestForm.getSadaremIDDetails());
                emailBody = emailUtility.pwdEmailFormatBody(emailBodyList);
                emailStatus = EmailUtility.SendEmail(ToMail, CCMail, BCCMail, "Subject:", emailBody, CommonConstants.Email_ID);
            } else {
                request.setAttribute("msg", "Document Size Must be Less Than or Equal to 100 KB For JPG,GIF Format");
                pwdRequestForm.setHouseNo("");
                pwdRequestForm.setDistrict_id("");
                pwdRequestForm.setMandal_id("");
                pwdRequestForm.setVillage_id("");
                pwdRequestForm.setHabitation_id("");
                pwdRequestForm.setPinCode("");
                pwdRequestForm.setContactNo("");
                pwdRequestForm.setEmail("");
                target = "success";
            }

            if (requestInformationDetails > 0) {
                String reqId = pwdRequestService.getrequestMaxId(ds);
                if (pwdRequestForm.getSscFile() != null && !pwdRequestForm.getSscFile().equals("")) {
                    FormFile myFile = pwdRequestForm.getSscFile();
                    fileName = myFile.getFileName();

                    int dotPos = fileName.lastIndexOf(".");
                    extensionssc = fileName.substring(dotPos);
                    fileName = reqId + extensionssc;
                    String url = getServlet().getServletContext().getRealPath("/");
                    String strDirectoytemp = CommonConstants.PWDREQUESTDOCUMNETS;
                    File dir = null;
                    File subdir = null;
                    subdir = new File(getServlet().getServletContext().getRealPath("/") + "SSCDOCUMENTS");
                    if (!subdir.exists()) {
                        subdir.mkdir();
                    }
                    CommonDetails.uploadingFile(pwdRequestForm.getSscFile(), "" + subdir, fileName);

                    File tempdir = new File(strDirectoytemp);
                    if (!tempdir.exists()) {
                        tempdir.mkdir();
                    }
                    File tempdistdir = new File(tempdir + "/" + "SSCDOCUMENTS");
                    if (!tempdistdir.exists()) {
                        tempdistdir.mkdir();
                    }
                    CommonDetails.uploadingFile(pwdRequestForm.getSscFile(), "" + tempdistdir, fileName);

                }
                if (pwdRequestForm.getAddressProof() != null && !pwdRequestForm.getAddressProof().equals("")) {
                    FormFile myFile = pwdRequestForm.getAddressProof();
                    fileName = myFile.getFileName();
                    int dotPos = fileName.lastIndexOf(".");
                    extensionAdress = fileName.substring(dotPos);
                    fileName = reqId + extensionAdress;
                    String url = getServlet().getServletContext().getRealPath("/");
                    String strDirectoytemp = CommonConstants.PWDREQUESTDOCUMNETS;
                    File dir = null;
                    File subdir = null;
                    subdir = new File(getServlet().getServletContext().getRealPath("/") + "ADDRESSDOCUMENTS");
                    if (!subdir.exists()) {
                        subdir.mkdir();
                    }


                    CommonDetails.uploadingFile(pwdRequestForm.getAddressProof(), "" + subdir, fileName);

                    File tempdir = new File(strDirectoytemp);
                    if (!tempdir.exists()) {
                        tempdir.mkdir();
                    }
                    File tempdistdir = new File(tempdir + "/" + "ADDRESSDOCUMENTS");
                    if (!tempdistdir.exists()) {
                        tempdistdir.mkdir();
                    }
                    CommonDetails.uploadingFile(pwdRequestForm.getAddressProof(), "" + tempdistdir, fileName);

                }


                //acknowledge ment start
                if (pwdRequestForm.getSadaremID() != null && !pwdRequestForm.getSadaremID().equals("")) {
                    personCode = pwdRequestForm.getSadaremID();
                }

                if (pwdRequestForm.getSadaremIDDetails() != null && !pwdRequestForm.getSadaremIDDetails().equals("")) {
                    personCode = pwdRequestForm.getSadaremIDDetails();
                }
                pwdRequestDTO = pwdRequestService.getReciptDetils(ds, reqId);
                request.setAttribute("pwdRequestDTO", pwdRequestDTO);
                target = "acknowledgement";
                //acknowledge ment end
            } else {
                // request.setAttribute("msg", "Error in Inserting");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    // For Ressessment,Duplicate,RenualPhysicalImpairment Details
    public ActionForward insertRessessmentDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        PwdRequestForm pwdRequestForm = (PwdRequestForm) form;

        CommonDetails CommonDetails = new CommonDetails();
        InetAddress ownIP = InetAddress.getLocalHost();
        String systemIp = ownIP.getHostAddress();
        int requestInformationDetails = 0;
        String personCode = null;
        String fileName = null;
        String fileAddressName = null;
        DataSource ds = null;

        String extensionssc = null;
        String extensionAdress = null;
        boolean filePath = false;
        int filesizeInKB = 0;
        int filesizeInKB1 = 0;
        String renualId = null;
        String reAssessmentId = null;

        //File 1
        if (pwdRequestForm.getDocuments() != null && !pwdRequestForm.getDocuments().equals("")) {
            FormFile myFile = pwdRequestForm.getDocuments();
            fileName = myFile.getFileName();
            int dotPos = fileName.lastIndexOf(".");
            extensionssc = fileName.substring(dotPos);

            filesizeInKB = myFile.getFileSize() / 1024;
        }

        //File 2
        if (pwdRequestForm.getAddressDocuments() != null && !pwdRequestForm.getAddressDocuments().equals("")) {
            FormFile myFileAdress = pwdRequestForm.getAddressDocuments();
            fileAddressName = myFileAdress.getFileName();
            int dotPosAdress = fileAddressName.lastIndexOf(".");
            extensionAdress = fileAddressName.substring(dotPosAdress);

            filesizeInKB1 = myFileAdress.getFileSize() / 1024;
        }


        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            pwdRequestService pwdRequestService = PwdRequestServiceFactory.getPwdRequestServiceImpl();

            request.setAttribute("RenualFormId", pwdRequestForm.getRenualId());
            request.setAttribute("reesId", pwdRequestForm.getReAssessmentId());

            String pId = pwdRequestForm.getRequestDetailsId();
            request.setAttribute("pId", pId);
            renualId = (String) request.getAttribute("renualId");

            reAssessmentId = (String) request.getAttribute("reAssessmentId");

            pwdRequestDTO = pwdRequestService.validRequestSADAREMID(ds, pwdRequestForm.getSadaremID(), pwdRequestForm.getRequestDetailsId());

            if (pwdRequestDTO.getRequestCountId() != null && pwdRequestDTO.getRequestCountId().equals("1")) {




                request.setAttribute("validRequestName", "AlReady Request is Raised " + pwdRequestDTO.getRequestCountName());

                target = "success";
            } else if (pwdRequestForm.getRequestDetailsId() != null && pwdRequestForm.getRequestDetailsId().equals("8")) {
                if (extensionssc.equalsIgnoreCase(".jpg") && filesizeInKB <= 100 || extensionssc.equalsIgnoreCase(".gif") && filesizeInKB <= 100 || extensionssc.equalsIgnoreCase(".pdf")) {
                    filePath = true;
                }

                if (filePath == true) {

                    requestInformationDetails = pwdRequestService.insertRessessmentRequestInformationDetails(ds, pwdRequestForm, systemIp);
                    //   requestInformationDetails = pwdRequestService.insertRequestInformationDetails(ds, pwdRequestForm, systemIp);
                } else {
                    request.setAttribute("msg", "false::Document Size Must be Less Than or Equal to 100 KB For JPG,GIF Format");


                    pwdRequestForm.setHouseNo("");
                    pwdRequestForm.setDistrict_id("");
                    pwdRequestForm.setMandal_id("");
                    pwdRequestForm.setVillage_id("");
                    pwdRequestForm.setHabitation_id("");
                    pwdRequestForm.setPinCode("");
                    pwdRequestForm.setContactNo("");
                    pwdRequestForm.setEmail("");
                    target = "success";
                }
            }


            if (pwdRequestForm.getRequestDetailsId() != null && pwdRequestForm.getRequestDetailsId().equals("10")) {
                requestInformationDetails = pwdRequestService.insertRessessmentRequestInformationDetails(ds, pwdRequestForm, systemIp);
            }

            if (pwdRequestForm.getRenualId() != null && pwdRequestForm.getRenualId().equals("9")) {
                requestInformationDetails = pwdRequestService.insertRessessmentRequestInformationDetails(ds, pwdRequestForm, systemIp);
            } // reAssessmentId
            else if (pwdRequestForm.getReAssessmentId() != null && pwdRequestForm.getReAssessmentId().equals("7")) {
                requestInformationDetails = pwdRequestService.insertRessessmentRequestInformationDetails(ds, pwdRequestForm, systemIp);
            } else {
                // requestInformationDetails = pwdRequestService.insertRessessmentRequestInformationDetails(ds, pwdRequestForm, systemIp);
            }


            if (requestInformationDetails > 0) {
                String reqId = pwdRequestService.getrequestMaxId(ds);

                if (pwdRequestForm.getDocuments() != null && !pwdRequestForm.getDocuments().equals("")) {
                    FormFile myFile = pwdRequestForm.getDocuments();
                    fileName = myFile.getFileName();
                    int dotPos = fileName.lastIndexOf(".");
                    extensionssc = fileName.substring(dotPos);
                    fileName = reqId + extensionssc;


                    String url = getServlet().getServletContext().getRealPath("/");
                    String strDirectoytemp = CommonConstants.PWDREQUESTDOCUMNETS;


                    File dir = null;
                    File subdir = null;

                    subdir = new File(getServlet().getServletContext().getRealPath("/") + "SSCDOCUMENTS");
                    if (!subdir.exists()) {
                        subdir.mkdir();
                    }


                    CommonDetails.uploadingFile(pwdRequestForm.getDocuments(), "" + subdir, fileName);

                    File tempdir = new File(strDirectoytemp);
                    if (!tempdir.exists()) {
                        tempdir.mkdir();
                    }
                    File tempdistdir = new File(tempdir + "/" + "SSCDOCUMENTS");
                    if (!tempdistdir.exists()) {
                        tempdistdir.mkdir();
                    }
                    CommonDetails.uploadingFile(pwdRequestForm.getDocuments(), "" + tempdistdir, fileName);

                }
                if (pwdRequestForm.getAddressDocuments() != null && !pwdRequestForm.getAddressDocuments().equals("")) {
                    FormFile myFile = pwdRequestForm.getAddressDocuments();
                    fileName = myFile.getFileName();
                    int dotPos = fileName.lastIndexOf(".");
                    extensionAdress = fileName.substring(dotPos);
                    fileName = reqId + extensionAdress;

                    String url = getServlet().getServletContext().getRealPath("/");
                    String strDirectoytemp = CommonConstants.PWDREQUESTDOCUMNETS;

                    File dir = null;
                    File subdir = null;

                    subdir = new File(getServlet().getServletContext().getRealPath("/") + "ADDRESSDOCUMENTS");
                    if (!subdir.exists()) {
                        subdir.mkdir();
                    }


                    CommonDetails.uploadingFile(pwdRequestForm.getDocuments(), "" + subdir, fileName);

                    File tempdir = new File(strDirectoytemp);
                    if (!tempdir.exists()) {
                        tempdir.mkdir();
                    }
                    File tempdistdir = new File(tempdir + "/" + "ADDRESSDOCUMENTS");
                    if (!tempdistdir.exists()) {
                        tempdistdir.mkdir();
                    }
                    CommonDetails.uploadingFile(pwdRequestForm.getAddressDocuments(), "" + tempdistdir, fileName);

                }


                //acknowledge ment start
                if (pwdRequestForm.getSadaremID() != null && !pwdRequestForm.getSadaremID().equals("")) {
                    personCode = pwdRequestForm.getSadaremID();
                }

                if (pwdRequestForm.getSadaremIDDetails() != null && !pwdRequestForm.getSadaremIDDetails().equals("")) {
                    personCode = pwdRequestForm.getSadaremIDDetails();
                }
                pwdRequestDTO = pwdRequestService.getReciptDetils(ds, reqId);
                request.setAttribute("pwdRequestDTO", pwdRequestDTO);
                target = "acknowledgement";
                //acknowledge ment end

            } else {
                // request.setAttribute("msgData", "Error in Inserting");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    //Ressessment,Duplicate,RenualPhysicalImpairment Details
    //To get the Form from NewCertificate to PartA
    public ActionForward partADetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String target = "partADetails";
        DataSource ds = null;

        PwdRequestForm pwdRequestForm = (PwdRequestForm) form;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            InetAddress ownIP = InetAddress.getLocalHost();
            String systemIp = ownIP.getHostAddress();


            String newCertificateId = request.getParameter("newCertificateId");

            request.setAttribute("newCertificateId", newCertificateId);
            //partADetails = pwdRequestService.newCertificateDetails(ds, pwdRequestForm, systemIp);

            request.setAttribute("requestidForm", pwdRequestForm.getRequestDetailsId());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    //Insert partA Details
    public ActionForward insertPartADetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        String target = "partADetails";

        PwdRequestForm pwdRequestForm = (PwdRequestForm) form;
        DataSource ds = null;

        HttpSession session = request.getSession();
        String extensionssc = null;
        String extensionAdress = null;
        String documentPath = null;
        String addresspath = null;
        String sscFolder = "SSCDOCUMENTS";
        String addressFloder = "AddressDOCUMENTS";
        int partADetails = 0;
        int requestInformationDetails = 0;
        String fileName = null;
        String addressfilename = null;
        String reqId = null;
        boolean fileaddressPath = false;
        boolean filePath = false;
        int filesizeInKB = 0;
        int filesizeInKB1 = 0;

        CommonDetails commonDetails = new CommonDetails();

        //File 1
        if (pwdRequestForm.getDocuments() != null && !pwdRequestForm.getDocuments().equals("")) {

            FormFile myFile = pwdRequestForm.getDocuments();
            fileName = myFile.getFileName();
            int dotPos = fileName.lastIndexOf(".");


            extensionssc = fileName.substring(dotPos);
            filesizeInKB = myFile.getFileSize() / 1024;
        }

        //File2

        if (pwdRequestForm.getAddressDocuments() != null && !pwdRequestForm.getAddressDocuments().equals("")) {
            FormFile addresMyFile = pwdRequestForm.getAddressDocuments();
            String addresFileName = addresMyFile.getFileName();
            int dotPoss = addresFileName.lastIndexOf(".");
            extensionAdress = addresFileName.substring(dotPoss);

            filesizeInKB1 = addresMyFile.getFileSize() / 1024;
        }
        Boolean uploadSuccess = false;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();


            String loginId = (String) session.getAttribute("loginid");
            InetAddress ownIP = InetAddress.getLocalHost();
            String systemIp = ownIP.getHostAddress();

            PwdRequestForm PwdRequestForm = (PwdRequestForm) form;
            pwdRequestService pwdRequestService = PwdRequestServiceFactory.getPwdRequestServiceImpl();



            if (extensionssc.equalsIgnoreCase(".jpg") && filesizeInKB <= 100 || extensionssc.equalsIgnoreCase(".gif") && filesizeInKB <= 100 || extensionssc.equalsIgnoreCase(".pdf")) {
                filePath = true;
            }

            if (extensionAdress.equalsIgnoreCase(".jpg") && filesizeInKB1 <= 100 || extensionAdress.equalsIgnoreCase(".gif") && filesizeInKB1 <= 100 || extensionAdress.equalsIgnoreCase(".pdf")) {
                fileaddressPath = true;

            }
            if (filePath == true && fileaddressPath == true) {

                requestInformationDetails = pwdRequestService.newCertificateDetails(ds, pwdRequestForm, systemIp);
            } else {

                request.setAttribute("msg", "false::Document Size Must be Less Than or Equal to 100 KB For JPG,GIF Format");

                pwdRequestForm.setFormno("");
                pwdRequestForm.setFromdate("");
                pwdRequestForm.setSurname("");
                pwdRequestForm.setFirstname("");
                pwdRequestForm.setGender(0);   //Not empty for DoB
                pwdRequestForm.setNoOfYears("");
                pwdRequestForm.setNoOfYears("");
                pwdRequestForm.setReligion(0);
                pwdRequestForm.setCaste(0);
                pwdRequestForm.setMarital(0);
                pwdRequestForm.setEducation(0);
                pwdRequestForm.setEmployment(0);
                pwdRequestForm.setGrelation(0);
                pwdRequestForm.setGsurname("");
                pwdRequestForm.setRtype(0);
                pwdRequestForm.setCard("");
                pwdRequestForm.setRationCardSlno(0);
                pwdRequestForm.setEpiccardno("");
                pwdRequestForm.setPension_type("");
                pwdRequestForm.setPensioncardno("");
                pwdRequestForm.setMole1("");
                pwdRequestForm.setMole2("");
                pwdRequestForm.setHouseno("");
                pwdRequestForm.setPhoneno("");
                pwdRequestForm.setEmailId("");
                pwdRequestForm.setRequestDistrict_idn("");
                pwdRequestForm.setRequestMandal_idn("");
                pwdRequestForm.setRequestVillage_idn("");
                pwdRequestForm.setRequestHabitation_idn("");
                pwdRequestForm.setPin("");
                pwdRequestForm.setFirstnametelugu("");
                pwdRequestForm.setTelugufathername("");
                pwdRequestForm.setType_disability(0);
                pwdRequestForm.setExistingpercentage(0);
                pwdRequestForm.setDocuments(null);
                pwdRequestForm.setAddressDocuments(null);

                target = "partADetails";
            }

            if (requestInformationDetails > 0) {
                reqId = pwdRequestService.getrequestMaxId(ds);

                if (pwdRequestForm.getDocuments() != null && !pwdRequestForm.getDocuments().equals("")) {
                    File dir = new File(getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\" + sscFolder);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    String sscfilename = reqId + extensionssc;
                    documentPath = reqId + extensionssc;

                    CommonDetails.uploadingFile(pwdRequestForm.getDocuments(), "" + dir, sscfilename);

                }
                if (pwdRequestForm.getAddressDocuments() != null && !pwdRequestForm.getAddressDocuments().equals("")) {

                    reqId = pwdRequestService.getrequestMaxId(ds);
                    File addreesDir = new File(getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\" + addressFloder);
                    if (!addreesDir.exists()) {
                        addreesDir.mkdirs();
                    }
                    addressfilename = reqId + extensionAdress;
                    addresspath = reqId + extensionAdress;

                    CommonDetails.uploadingFileAddress(pwdRequestForm.getAddressDocuments(), "" + addreesDir, addressfilename);
                }
                File sourceDir1 = new File(getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\");
                File targetDir1 = new File(CommonConstants.PWDREQUESTDOCUMNETS);
                if (sourceDir1.exists()) {
                    commonDetails.copyDirectoryandFileAddress(sourceDir1, targetDir1);
                }


                //move to wed to local
                File sourceDir = new File(getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\");
                File targetDir = new File(CommonConstants.PWDREQUESTDOCUMNETS);
                if (sourceDir.exists()) {
                    commonDetails.copyDirectoryandFile(sourceDir, targetDir);
                }

                //delete directory start
                File directory = new File(getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\");
                //make sure directory exists
                if (!directory.exists()) {
                } else {
                    try {
                        //commonDetails.delete(directory);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }


                partADetails = pwdRequestService.partAInsertDetails(ds, PwdRequestForm, documentPath, addresspath, loginId, systemIp);


                if (partADetails > 0 && requestInformationDetails > 0) {
                    request.setAttribute("msgData", "Inserted SuccessFully!");

                    pwdRequestForm.setFormno("");
                    pwdRequestForm.setFromdate("");
                    pwdRequestForm.setSurname("");
                    pwdRequestForm.setFirstname("");
                    pwdRequestForm.setGender(0);   //Not empty for DoB
                    pwdRequestForm.setNoOfYears("");
                    pwdRequestForm.setNoOfYears("");
                    pwdRequestForm.setReligion(0);
                    pwdRequestForm.setCaste(0);
                    pwdRequestForm.setMarital(0);
                    pwdRequestForm.setEducation(0);
                    pwdRequestForm.setEmployment(0);
                    pwdRequestForm.setGrelation(0);
                    pwdRequestForm.setGsurname("");
                    pwdRequestForm.setRtype(0);
                    pwdRequestForm.setCard("");
                    pwdRequestForm.setRationCardSlno(0);
                    pwdRequestForm.setEpiccardno("");
                    pwdRequestForm.setPension_type("");
                    pwdRequestForm.setPensioncardno("");
                    pwdRequestForm.setMole1("");
                    pwdRequestForm.setMole2("");
                    pwdRequestForm.setHouseno("");
                    pwdRequestForm.setPhoneno("");
                    pwdRequestForm.setEmailId("");
                    pwdRequestForm.setRequestDistrict_idn("");
                    pwdRequestForm.setRequestMandal_idn("");
                    pwdRequestForm.setRequestVillage_idn("");
                    pwdRequestForm.setRequestHabitation_idn("");
                    pwdRequestForm.setPin("");
                    pwdRequestForm.setFirstnametelugu("");
                    pwdRequestForm.setTelugufathername("");
                    pwdRequestForm.setType_disability(0);
                    pwdRequestForm.setExistingpercentage(0);
                    pwdRequestForm.setDocuments(null);
                    pwdRequestForm.setAddressDocuments(null);


                } else {

                    request.setAttribute("msgData", "Error in Iserting");

                    pwdRequestForm.setFormno("");
                    pwdRequestForm.setFromdate("");
                    pwdRequestForm.setSurname("");
                    pwdRequestForm.setFirstname("");
                    pwdRequestForm.setGender(0);   //Not empty for DoB
                    pwdRequestForm.setNoOfYears("");
                    pwdRequestForm.setNoOfYears("");
                    pwdRequestForm.setReligion(0);
                    pwdRequestForm.setCaste(0);
                    pwdRequestForm.setMarital(0);
                    pwdRequestForm.setEducation(0);
                    pwdRequestForm.setEmployment(0);
                    pwdRequestForm.setGrelation(0);
                    pwdRequestForm.setGsurname("");
                    pwdRequestForm.setRtype(0);
                    pwdRequestForm.setCard("");
                    pwdRequestForm.setRationCardSlno(0);
                    pwdRequestForm.setEpiccardno("");
                    pwdRequestForm.setPension_type("");
                    pwdRequestForm.setPensioncardno("");
                    pwdRequestForm.setMole1("");
                    pwdRequestForm.setMole2("");
                    pwdRequestForm.setHouseno("");
                    pwdRequestForm.setPhoneno("");
                    pwdRequestForm.setEmailId("");
                    pwdRequestForm.setRequestDistrict_idn("");
                    pwdRequestForm.setRequestMandal_idn("");
                    pwdRequestForm.setRequestVillage_idn("");
                    pwdRequestForm.setRequestHabitation_idn("");
                    pwdRequestForm.setPin("");
                    pwdRequestForm.setFirstnametelugu("");
                    pwdRequestForm.setTelugufathername("");
                    pwdRequestForm.setType_disability(0);
                    pwdRequestForm.setExistingpercentage(0);
                    pwdRequestForm.setDocuments(null);
                    pwdRequestForm.setAddressDocuments(null);
                }
                //acknowledgement startOnly For NewCertificate
                pwdRequestDTO = pwdRequestService.getReciptDetils(ds, reqId);

                request.setAttribute("pwdRequestDTO", pwdRequestDTO);
                target = "newCertificateAcknowledgement";
                //  acknowledge ment end
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }
    //End Insert partA Details
}
