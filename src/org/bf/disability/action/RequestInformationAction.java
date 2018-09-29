/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.RequestInformationDTO;
import org.bf.disability.form.RequestInformationForm;
import org.bf.disability.service.RequestInformationService;
import org.bf.disability.servicefactory.RequestInformationServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 693461
 */
public class RequestInformationAction extends DispatchAction {

    /* forward name="success" path="" */
    //private
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String target = "success";
        RequestInformationForm RequestInformationForm = (RequestInformationForm) form;
        RequestInformationService requestInformationService = RequestInformationServiceFactory.getRequestInformationServiceImpl();

        DataSource ds = null;
        ArrayList requestTypeList = new ArrayList();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            requestTypeList = requestInformationService.getRequestDetails(ds);
            RequestInformationForm.setRequestTypeList(requestTypeList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getRequestStatusDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";

        RequestInformationForm requestInformationForm = (RequestInformationForm) form;

        RequestInformationService requestInformationService = RequestInformationServiceFactory.getRequestInformationServiceImpl();

        DataSource ds = null;
        HttpSession session = request.getSession();
        CommonDetails commondetails = new CommonDetails();
        boolean adminLevelAccessFlag = false;
        boolean urbanLevlAccessFlag = false;
        String districtid = null;
        ArrayList requestInformationList = new ArrayList();
        ArrayList requestSingleList = new ArrayList();
        ArrayList personCodeList = new ArrayList();
        String requestTypeId = null;
        String status = null;
        String requestId = null;
        ArrayList fileName = new ArrayList();
        ArrayList totalList = new ArrayList();

        String url = getServlet().getServletContext().getRealPath("/");

        CommonDetails commonDetails = new CommonDetails();

        RequestInformationDTO requestInformationDTO = new RequestInformationDTO();
        RequestInformationDTO fileNameDTO = new RequestInformationDTO();
        request.setAttribute("statusIdData", requestInformationForm.getStatus());
        String personCodeValidate = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            request.setAttribute("requestTypeId", requestInformationForm.getRequestTypeDetails());
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_RURALDRDA);

            urbanLevlAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_URBANMEPMA);


            if (adminLevelAccessFlag) {

                // personCodeList = requestInformationService.getPersonCodeDetails(ds);

                if (requestInformationForm.getStatus().equals("Approval")) {
                    request.setAttribute("Approval", "Approval");
                }
                if (requestInformationForm.getStatus().equals("Reject")) {
                    request.setAttribute("Reject", "Reject");
                }
                if (requestInformationForm.getStatus().equals("Pending")) {
                    request.setAttribute("Pending", "Pending");
                }

                if (requestInformationForm.getRequestTypeDetails().equals("6")) {

                    request.setAttribute("newCertificate", "newCertificate");

                } else {
                    request.setAttribute("otherDetails", "otherDetails");

                    request.setAttribute("newCertificate1", "newCertificate1");
                }


                if (requestInformationForm.getStatus().equals("Approval") && requestInformationForm.getRequestTypeDetails().equals("1")) {
                    request.setAttribute("ApprovalData", "ApprovalData");

                }


                if ((requestInformationForm.getRequestTypeDetails().equals("1") || requestInformationForm.getRequestTypeDetails().equals("2")
                        || requestInformationForm.getRequestTypeDetails().equals("3") || requestInformationForm.getRequestTypeDetails().equals("5")
                        || requestInformationForm.getRequestTypeDetails().equals("7") || requestInformationForm.getRequestTypeDetails().equals("8")
                        || requestInformationForm.getRequestTypeDetails().equals("9")) && requestInformationForm.getStatus().equalsIgnoreCase("Pending")) {

                    request.setAttribute("disupdate", "1");

                }
                if (requestInformationForm.getRequestTypeDetails().equals("1")) {
                    request.setAttribute("requestId", "requestId");
                } else if (requestInformationForm.getRequestTypeDetails().equals("2")) {
                    request.setAttribute("requestId", "requestId");
                } else {
                    request.setAttribute("otherRequestId", "otherRequestId");
                }


                if (session.getAttribute("districtId") != null) {
                    districtid = (String) session.getAttribute("districtId");
                }

                if (requestInformationForm.getRequestTypeDetails() != null && requestInformationForm.getRequestTypeDetails().equals("")) {
                    requestTypeId = requestInformationForm.getRequestTypeDetails();
                }

                if (requestInformationForm.getStatus() != null && !requestInformationForm.getStatus().equals("")) {
                    status = requestInformationForm.getStatus();
                }

                if (requestInformationForm.getStatus() != null && requestInformationForm.getStatus().equals("Pending")) {
                    request.setAttribute("status", "1");
                }

//                requestInformationList = requestInformationService.getRequestInformationDetails(ds, districtid, requestInformationForm.getRequestTypeDetails(), requestInformationForm.getStatus());
//                request.setAttribute("requestInformationList", requestInformationList);

                if (requestInformationForm.getMultiple().equals("1")) {

                    request.setAttribute("multiple", "1");
                    requestInformationList = requestInformationService.getMultipleDetails(ds, districtid, requestInformationForm);
                    request.setAttribute("requestInformationList", requestInformationList);
                }


                if (requestInformationForm.getMultiple().equals("2")) {

                    request.setAttribute("single", "2");
                    requestInformationList = requestInformationService.getSingleDetails(ds, districtid, requestInformationForm);
                    request.setAttribute("requestInformationList", requestInformationList);
                }

                Iterator it = requestInformationList.iterator();

                while (it.hasNext()) {
                    requestInformationDTO = (RequestInformationDTO) it.next();
                    personCodeValidate = requestInformationDTO.getRequestTypeDetailsId();

                }

                if (personCodeValidate != null && personCodeValidate.equals("6")) {
                    request.setAttribute("personCodeValidate", "1");
                } else {
                    request.setAttribute("personCodeValidate", "0");

                }
                //request.setAttribute("cccc", z++);

                if (requestInformationForm.getRequestTypeDetails() != null
                        && (requestInformationForm.getRequestTypeDetails().equalsIgnoreCase("1")
                        || requestInformationForm.getRequestTypeDetails().equalsIgnoreCase("2")
                        || requestInformationForm.getRequestTypeDetails().equalsIgnoreCase("3")
                        || requestInformationForm.getRequestTypeDetails().equalsIgnoreCase("5")
                        || requestInformationForm.getRequestTypeDetails().equalsIgnoreCase("8"))) {
//                    request.setAttribute("requestType", "1");
                }

                if (requestInformationForm.getMultiple().equals("1")) {

                    request.setAttribute("requestType", "1");
                }
                if (requestInformationForm.getMultiple().equals("2")) {

                    request.setAttribute("requestsingle", "1");
                }





                request.setAttribute("cc", requestInformationList);
                requestInformationForm.setHiddenlist(requestInformationList.size());

                if (requestInformationList.isEmpty()) {
                    request.setAttribute("msg", "No Data Found");
                }
            }

            if (urbanLevlAccessFlag) {

                if (requestInformationForm.getStatus().equals("Approval")) {
                    request.setAttribute("Approval", "Approval");
                }
                if (requestInformationForm.getStatus().equals("Reject")) {
                    request.setAttribute("Reject", "Reject");
                }
                if (requestInformationForm.getStatus().equals("Pending")) {
                    request.setAttribute("Pending", "Pending");
                }

                if (requestInformationForm.getRequestTypeDetails().equals("6")) {
                    request.setAttribute("newCertificate", "newCertificate");
                } else {
                    request.setAttribute("otherDetails", "otherDetails");
                }
                districtid = (String) session.getAttribute("districtId");
                // personCodeList = requestInformationService.getPersonCodeDetails(ds);


                if (requestInformationForm.getStatus().equals("Approval") && requestInformationForm.getRequestTypeDetails().equals("1")) {
                    request.setAttribute("ApprovalData", "ApprovalData");
                }

                if (requestInformationForm.getStatus() != null && requestInformationForm.getStatus().equals("Pending")) {
                    request.setAttribute("status", "1");
                }
                if ((requestInformationForm.getRequestTypeDetails().equals("1") || requestInformationForm.getRequestTypeDetails().equals("2")
                        || requestInformationForm.getRequestTypeDetails().equals("3") || requestInformationForm.getRequestTypeDetails().equals("5")
                        || requestInformationForm.getRequestTypeDetails().equals("7") || requestInformationForm.getRequestTypeDetails().equals("8")
                        || requestInformationForm.getRequestTypeDetails().equals("9")) && requestInformationForm.getStatus().equalsIgnoreCase("Pending")) {

                    request.setAttribute("disupdate", "1");

                }

                if (requestInformationForm.getRequestTypeDetails() != null
                        && (requestInformationForm.getRequestTypeDetails().equalsIgnoreCase("1")
                        || requestInformationForm.getRequestTypeDetails().equalsIgnoreCase("2")
                        || requestInformationForm.getRequestTypeDetails().equalsIgnoreCase("3")
                        || requestInformationForm.getRequestTypeDetails().equalsIgnoreCase("5")
                        || requestInformationForm.getRequestTypeDetails().equalsIgnoreCase("8"))) {
//                    request.setAttribute("requestType", "1");
                }
                if (requestInformationForm.getMultiple().equals("1")) {

                    request.setAttribute("requestType", "1");
                }

                if (requestInformationForm.getMultiple().equals("2")) {

                    request.setAttribute("requestsingle", "1");
                }

                // requestInformationList = requestInformationService.getRequestUrbanIformationDetails(ds, districtid, requestInformationForm.getRequestTypeDetails(), requestInformationForm.getStatus());
                //request.setAttribute("requestInformationList", requestInformationList);


                if (requestInformationForm.getMultiple().equals("1")) {
                    request.setAttribute("multiple", "1");
                    requestInformationList = requestInformationService.getMultipleUrbanDetails(ds, districtid, requestInformationForm);
                    request.setAttribute("requestInformationList", requestInformationList);
                }

                if (requestInformationForm.getMultiple().equals("2")) {
                    request.setAttribute("single", "2");
                    requestInformationList = requestInformationService.getSingleUrbanDetails(ds, districtid, requestInformationForm);
                    request.setAttribute("requestInformationList", requestInformationList);
                }

                request.setAttribute("cc", requestInformationList);
                requestInformationForm.setHiddenlist(requestInformationList.size());

                if (requestInformationForm.getMultiple().equals("2")) {
                    request.setAttribute("single", "2");
                }

                Iterator it = requestInformationList.iterator();

                while (it.hasNext()) {
                    requestInformationDTO = (RequestInformationDTO) it.next();
                    personCodeValidate = requestInformationDTO.getRequestTypeDetailsId();

                }

                if (personCodeValidate != null && personCodeValidate.equals("6")) {
                    request.setAttribute("personCodeValidate", "1");
                } else {
                    request.setAttribute("personCodeValidate", "0");

                }

                if (requestInformationList.isEmpty()) {
                    request.setAttribute("msg", "No Data Found");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.unspecified(mapping, form, request, response);
        return mapping.findForward(target);
    }

    public ActionForward updateApprovalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";

        RequestInformationForm requestInformationForm = (RequestInformationForm) form;
        RequestInformationService requestInformationService = RequestInformationServiceFactory.getRequestInformationServiceImpl();

        DataSource ds = null;
        String requestId = null;
        int updateRequestDetails = 0;
        RequestInformationDTO requestInformationDTO = null;
        String editErrormsg = null;
        String viewErrormsg = null;
        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute("loginid");
        ArrayList renualList = new ArrayList();
        int duplicateDetails = 0;
        String status = null;
        String reqid = null;

        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            //This Block is Updated  RequestSattus  in RequestTypeMapping
            for (int i = 0; i < requestInformationForm.getRecordStatus1().length; i++) {

                if (requestInformationForm.getRecordStatus1()[i] != null && !requestInformationForm.getRecordStatus1()[i].equals("")) {

                    // String[] currentLineArray = requestInformationForm.getRecordStatus1()[i].replaceAll("\\s", "").split("#");



                    if (requestInformationForm.getRecordStatus1()[i] != null && !requestInformationForm.getRecordStatus1()[i].equals("")) {

                        String s = requestInformationForm.getRecordStatus1()[i].toString();
                        String[] parts = s.split("#");
                        status = parts[0];
                        reqid = parts[1];
                    }
                    //  String reqid = (requestInformationForm.getRecordStatus1()[i].split("#"))[1];
                    updateRequestDetails = requestInformationService.updateRequestDetails(ds, status, reqid, requestInformationForm.getRequestTypeDetails());
                }

            }

            if (updateRequestDetails > 0) {
                request.setAttribute("raisedRequest", "Request Updated SuccessFully!");
            } else {
                request.setAttribute("raisedRequest", "Error in Updating");
            }
            //This Block Check Ressessment andUpdated in AppellateAuthorityandTemporary_RegistrationDetails

            if (requestInformationForm.getRequestTypeDetails().equals("7")) {
                for (int i = 0; i < requestInformationForm.getHiddenPersonCode().length; i++) {
                    requestInformationDTO = requestInformationService.checkRessessmentDetails(ds, requestInformationForm.getRequestTypeDetails(), requestInformationForm.getHiddenPersonCode()[i], loginId);
                }

                if (requestInformationDTO.getEditErrormsg() != null) {
                    request.setAttribute("editError", requestInformationDTO.getEditErrormsg());
                }
                if (requestInformationDTO.getViewErrormsg() != null) {
                    request.setAttribute("editError", requestInformationDTO.getViewErrormsg());
                }
                if (requestInformationDTO.getInsertDetails() > 0) {
                    //request.setAttribute("msg", "We Request is Inserted SucessFully!");
                } else {
                    // request.setAttribute("msg", "Error in Inserting!");
                }
            }
            // end Block

            //This Block Check Ressessment andUpdated in AppellateAuthorityandTemporary_RegistrationDetails


            if (requestInformationForm.getRequestTypeDetails() != null && requestInformationForm.getRequestTypeDetails().equals("9")) {

                for (int i = 0; i < requestInformationForm.getHiddenPersonCode().length; i++) {
                    requestInformationDTO = requestInformationService.checkRenualDetails(ds, requestInformationForm.getRequestTypeDetails(), requestInformationForm.getHiddenPersonCode()[i], loginId);
                }


                if (requestInformationDTO.getRenualstatus() != null) {
                    //request.setAttribute("editError", "AlReady Registered for Renual");
                }
            }

            //This Block Check Ressessment andUpdated in AppellateAuthorityandTemporary_RegistrationDetails

            if (requestInformationForm.getRequestTypeDetails() != null && requestInformationForm.getRequestTypeDetails().equals("8")) {

                for (int i = 0; i < requestInformationForm.getHiddenPersonCode().length; i++) {

                    duplicateDetails = requestInformationService.duplicateDetails(ds, requestInformationForm.getRequestTypeDetails(), requestInformationForm.getHiddenPersonCode()[i], loginId);
                }


                if (duplicateDetails > 0) {
                    // request.setAttribute("duplicateDetails", "Inserted SuccessFully!");
                } else {
                    // request.setAttribute("duplicateDetails", "Error in Inserting!!");
                }
            }


            if (requestInformationForm.getRequestTypeDetails() != null && requestInformationForm.getRequestTypeDetails().equals("10")) {
                for (int i = 0; i < requestInformationForm.getHiddenPersonCode().length; i++) {

                    duplicateDetails = requestInformationService.physicalImpairment(ds, requestInformationForm.getRequestTypeDetails(), requestInformationForm.getHiddenPersonCode()[i], loginId);
                }

                if (duplicateDetails > 0) {
                    // request.setAttribute("duplicateDetails", "Inserted SuccessFully!");
                } else {
                    //  request.setAttribute("duplicateDetails", "Error in Inserting!!");
                }
            }


            //end//
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.unspecified(mapping, form, request, response);
        return mapping.findForward(target);
    }

    public ActionForward getNewCertificateDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "newCertificateDetails";


        RequestInformationForm RequestInformationForm = (RequestInformationForm) form;
        RequestInformationService requestInformationService = RequestInformationServiceFactory.getRequestInformationServiceImpl();

        DataSource ds = null;
        ArrayList newCertificateList = new ArrayList();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            String requestDetailsId = request.getParameter("requestDetailsId");
            request.setAttribute("requestDetailsId", requestDetailsId);
            String requestTypeIdDetails = request.getParameter("requestTypeIdDetails");


            request.setAttribute("statusId", request.getParameter("statusId"));

            newCertificateList = requestInformationService.getNewCertificateDetails(ds, requestDetailsId, requestTypeIdDetails);
            request.setAttribute("newCertificateList", newCertificateList);

            if (newCertificateList.isEmpty()) {
                request.setAttribute("msg", "No Data Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward insertDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String target = "newCertificateDetails";


        String requestStatusIdData = (String) request.getAttribute("requestStatusIdData");



        int newCertificateDetails = 0;
        HttpSession session = request.getSession();
        RequestInformationDTO requestInformationDTO = null;
        String sadaremId = null;
        String loginId = null;
        String systemIp = null;
        RequestInformationForm RequestInformationForm = (RequestInformationForm) form;
        RequestInformationService requestInformationService = RequestInformationServiceFactory.getRequestInformationServiceImpl();

        DataSource ds = null;
        ArrayList newCertificateList = new ArrayList();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }



            loginId = (String) session.getAttribute("loginid");

            InetAddress ownIP = InetAddress.getLocalHost();
            systemIp = ownIP.getHostAddress();

            systemIp = request.getRemoteAddr();

            newCertificateDetails = requestInformationService.insertNewCertificate(ds, RequestInformationForm.getNewCertificateReqiestId(), RequestInformationForm.getNewCertificateReqiestTypeId(), loginId, systemIp, request.getParameter("statusIdHidden"), request);


            // sadaremId = requestInformationDTO.getMsgSadaremId();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    //New Certificate Reject Status
    public ActionForward rejectDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String target = "newCertificateDetails";


        String requestStatusIdData = (String) request.getAttribute("requestStatusIdData");



        int newCertificateDetails = 0;
        HttpSession session = request.getSession();
        int rejectStatus = 0;
        RequestInformationForm RequestInformationForm = (RequestInformationForm) form;
        RequestInformationService requestInformationService = RequestInformationServiceFactory.getRequestInformationServiceImpl();

        DataSource ds = null;
        ArrayList newCertificateList = new ArrayList();

        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

//, RequestInformationForm.getNewCertificateReqiestTypeId()
            rejectStatus = requestInformationService.rejectNewCertificate(ds, RequestInformationForm.getNewCertificateReqiestId(), RequestInformationForm.getNewCertificateReqiestTypeId());

            if (rejectStatus > 0) {
                request.setAttribute("rejectStatus", "Request is Rejected!");
            } else {
                request.setAttribute("rejectStatus", "Error in Rejected");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
    //end  photoDetails

    public ActionForward getPhotoDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        String target = "requestPhotoView";

        CommonDetails commonDetails = new CommonDetails();
        String fileName = null;
        String folderName = null;
        String fileWithExt = null;
        // RequestInformationDTO requestInformationDTO  = new RequestInformationDTO ();


        try {

            String photoId = request.getParameter("photoId");
            String flag = request.getParameter("flag");
            request.setAttribute("photoId", photoId);

            String personCodeId = request.getParameter("personCodeId");

            request.setAttribute("personCodeId", personCodeId);
            String url = getServlet().getServletContext().getRealPath("/");
            String strDirectoytemp = "D:\\SADAREMTG\\PWDRequestDOCUMNETS";

            if (flag.equalsIgnoreCase("1")) {
                folderName = "SSCDOCUMENTS";

            }
            if (flag.equalsIgnoreCase("2")) {
                folderName = "ADDRESSDOCUMENTS";

            }


            File folder = new File(CommonConstants.PWDREQUESTDOCUMNETS + folderName);


            File[] listOfFiles = folder.listFiles();
            int size = 0;
            if (listOfFiles != null && !listOfFiles.equals("")) {

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        String file = listOfFiles[i].getName();

                        String filename = file.substring(0, file.lastIndexOf("."));
                        if (filename.equalsIgnoreCase(photoId)) {
                            fileWithExt = listOfFiles[i].getName();
//                        } else {
//                            fileWithExt = null;
//                        }
                        }
                    }
                }

                if (fileWithExt != null) {
                    fileName = fileWithExt;


//strDirectoy = url + "DisabilityUITG\\uploadedphotos\\" + DistrictName;

                    String contentType = null;
                    contentType = "txt";

                    BufferedInputStream in = null;
                    File fileDetailsData = new File(folder + "\\" + fileName);



                    FileInputStream fin = new FileInputStream(fileDetailsData);

                    in = new BufferedInputStream(fin);

                    ServletOutputStream out = response.getOutputStream();
                    // response.setContentType("application/txt");
                    // response.addHeader("Content-Disposition", "attachment; filename="+ files+".zip");
                    response.setContentType("application/force-download");
                    response.addHeader("Content-Disposition", "attachment; filename=\"" + fileDetailsData + "\"");


                    byte[] buffer = new byte[4 * 1024];

                    int data = 0;

                    try {
                        while ((data = in.read(buffer)) != -1) {
                            out.write(buffer, 0, data);
                        }
                        out.flush();
                    } catch (Exception e) {
                        //System.exit(1);
                    }

                }

            }


        } catch (Exception e) {
            e.printStackTrace();

        }


//            
//            String url = getServlet().getServletContext().getRealPath("/");
//             fileName = commonDetails.copyPhotoRequestDetailsPath(photoId, request, url);
//
//
//                Iterator it = fileName.iterator();
//                while (it.hasNext()) {
//                RequestInformationDTO requestInformationDTO = (RequestInformationDTO)it.next();
//                request.setAttribute("viewData",requestInformationDTO.getPhotoRequestViewId());
//
//            }
//
//        }catch(Exception e) {
//            e.printStackTrace();
//
//        }

//            String photoId= request.getParameter("photoId");
//
//
//
//
//           BufferedInputStream in = null;
//         File fileDetailsData = new File(CommonConstants.FILEUPLOAD_PATH_PRODUCTION +"\\AddressDOCUMENTS");
//
//
//
//
//
//         String url= getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\AddressDOCUMENTS";
//
//         FileInputStream fin = new FileInputStream(url);
//
//           in = new BufferedInputStream(fin);
//
//            ServletOutputStream out = response.getOutputStream();
//
//
//
//            byte[] buffer = new byte[4 * 1024];
//
//            int data =0;
//            while ((data = in.read(buffer)) != -1) {
//                out.write(buffer, 0, data);
//            }
//            out.flush();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return mapping.findForward(target);
    }
}
