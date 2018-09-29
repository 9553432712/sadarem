/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.File;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.PwdRequestForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.service.pwdRequestService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.servicefactory.PwdRequestServiceFactory;

/**
 *
 * @author 693461
 */
public class PwdRequestAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward(SUCCESS);
    }

    public ActionForward getRequestTypeDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
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
                PwdRequestForm.setRequestTypeDetails(requestListNewDetails);
                request.setAttribute("radio2", "radio2");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getMandalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PwdRequestForm PwdRequestForm = (PwdRequestForm) form;

        DataSource ds = null;

        ArrayList mandallist = new ArrayList();
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);

    }

    public ActionForward getVillageDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

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
        return mapping.findForward(SUCCESS);
    }

    public ActionForward insertDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PwdRequestForm pwdRequestForm = (PwdRequestForm) form;

        DataSource ds = null;

        ArrayList villagelist = new ArrayList();
        String extension = null;
        String extensionAdress = null;
        boolean uploadSuccess = false;
        CommonDetails CommonDetails = new CommonDetails();

        FormFile myFile = pwdRequestForm.getSscFile();
        String fileName = myFile.getFileName();
        int dotPos = fileName.lastIndexOf(".");
        extension = fileName.substring(dotPos);
        String sscFolder = "SSCDOCUMENTS";

        File sourceDir = null;
        File targetDir = null;

        FormFile myFileAdress = pwdRequestForm.getAddressProof();
        String fileAddressName = myFileAdress.getFileName();
        int dotPosAdress = fileAddressName.lastIndexOf(".");
        extension = fileName.substring(dotPosAdress);
        String sscFolderAddress = "AddressDOCUMENTS";

        File sourceDirAddress = null;
        File targetDirAddress = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            File dir = new File(getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\" + sscFolder);

            if (!dir.exists()) {
                dir.mkdirs();
            }
            uploadSuccess = CommonDetails.uploadingFilePWD(pwdRequestForm.getSscFile(), "" + dir, fileName);



            if (uploadSuccess) {
                sourceDir = new File(getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\" + sscFolder);



                targetDir = new File(CommonConstants.FILEUPLOAD_PATH_PRODUCTION + sscFolder);


                if (sourceDir.exists()) {
                    CommonDetails.copyDirectoryandFileAddress(sourceDir, targetDir);
                }
                //copy web to local end

                //delete directory start
                File directory = new File(getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\" + sscFolder);
                //make sure directory exists
                if (!directory.exists()) {
                    System.exit(0);
                } else {
                    try {
                        CommonDetails.delete(directory);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(0);
                    }
                }
            }

            File addreesDir = new File(getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\" + sscFolderAddress);


            if (!addreesDir.exists()) {
                addreesDir.mkdirs();
            }
            uploadSuccess = CommonDetails.uploadingFile(pwdRequestForm.getAddressProof(), "" + addreesDir, fileAddressName);
            PwdRequestForm pwdRequestForm1 = (PwdRequestForm) form;

            pwdRequestForm1.setDistrict_id("");

            if (uploadSuccess) {
                sourceDir = new File(getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\" + sscFolderAddress);

                targetDir = new File(CommonConstants.FILEUPLOAD_PATH_PRODUCTION + sscFolderAddress);

                if (sourceDir.exists()) {
                    CommonDetails.copyDirectoryandFile(sourceDir, targetDir);
                }
                //copy web to local end

                //delete directory start
                File directory = new File(getServlet().getServletContext().getRealPath("/") + "PWDRequestDOCUMNETS\\" + sscFolderAddress);
                //make sure directory exists
                if (!directory.exists()) {
                    System.exit(0);
                } else {
                    try {
                        CommonDetails.delete(directory);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }
}
