/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.FileUploadDownloadForm;
import org.bf.disability.service.FileUploadDownloadService;
import org.bf.disability.servicefactory.FileUploadDownloadServiceFactory;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletOutputStream;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.TerritoryServiceFactory;
import org.bf.disability.util.EmailUtility;

/**
 *
 * @author 693461
 */
public class FileUploadDownloadAction extends DispatchAction {

    /* forward name="success" path="" */
    private String target = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ArrayList districtlist = new ArrayList();
        FileUploadDownloadForm fileUploadDownloadForm = (FileUploadDownloadForm) form;
        TerritoryService territoryservice =
                TerritoryServiceFactory.getTerritoryServiceImpl();
        try {
            ds = getDataSource(request);

            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtlist = territoryservice.getDistricts(ds);
            fileUploadDownloadForm.setDistrictlist(districtlist);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    public ActionForward uploadFileDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        boolean uploadSuccess = false;
        String username = null;
        String password = null;
        ArrayList districtlist = new ArrayList();
        TerritoryService territoryservice =
                TerritoryServiceFactory.getTerritoryServiceImpl();
        FileUploadDownloadForm fileUploadDownloadForm = (FileUploadDownloadForm) form;
        String extension = null;
        String districtName = null;
        String name = null;

        File dir = null;
        File dist_dir = null;

        File sourceDir = null;
        File targetDir = null;



        int insertFileUpdateDetails = 0;

        String email = null;
        String ccData = null;
        InternetAddress emailId = null;
        String EmailBody = "";
        boolean emailStatus = false;
        ArrayList<InternetAddress> RecMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> CCMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> BCCMail = new ArrayList<InternetAddress>();

        String download = CommonConstants.FILEUPLOAD_PATH_PRODUCTION;

        DataSource ds = null;
        CommonDetails commonDetails = new CommonDetails();
        ArrayList CCList = null;

        FormFile myFile = fileUploadDownloadForm.getUploadFile();
        String fileName = myFile.getFileName();
        int dotPos = fileName.lastIndexOf(".");
        extension = fileName.substring(dotPos);

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtlist = territoryservice.getDistricts(ds);
            fileUploadDownloadForm.setDistrictlist(districtlist);
            FileUploadDownloadService fileUploadDownloadService =
                    FileUploadDownloadServiceFactory.getFileUploadDownloadServiceImpl();

            districtName = fileUploadDownloadService.getDistrictLoginNameDetails(ds, fileUploadDownloadForm.getDistrict_id());

            ArrayList userDetails = fileUploadDownloadService.getUserDetailsForMail(ds, fileUploadDownloadForm.getDistrict_id());

            ArrayList emailList = fileUploadDownloadService.getDistEmailIds(ds, fileUploadDownloadForm.getDistrict_id());
            CCList = fileUploadDownloadService.getDistrictCCMails(ds, fileUploadDownloadForm.getDistrict_id());

            if (userDetails.size() > 0 && emailList.size() > 0) {
                //create director start
                dir = new File(getServlet().getServletContext().getRealPath("/") + "SADAREMDOCUMENTS");
                if (!dir.exists()) {
                    dir.mkdir();
                }
                dist_dir = new File(dir + "/" + districtName);
                if (!dist_dir.exists()) {
                    dist_dir.mkdir();
                }
                //file name setting
                File folder = new File(CommonConstants.FILEUPLOAD_PATH_PRODUCTION + districtName);
                File[] listOfFiles = folder.listFiles();
                int size = 0;
                if (listOfFiles != null && !listOfFiles.equals("")) {
                    size = listOfFiles.length + 1;
                } else {
                    size = 0 + 1;
                }
                name = districtName + "_" + size + extension;
                uploadSuccess = CommonDetails.uploadingFile(fileUploadDownloadForm.getUploadFile(), "" + dist_dir, name);

                File tempdir = new File(download);
                if (!tempdir.exists()) {
                    tempdir.mkdir();
                }
                File tempdistdir = new File(tempdir + "/" + districtName);
                if (!tempdistdir.exists()) {
                    tempdistdir.mkdir();
                }
                CommonDetails.uploadingFile(fileUploadDownloadForm.getUploadFile(), "" + tempdistdir, name);


            } else {
                fileUploadDownloadForm.setBodyMailData("");
                fileUploadDownloadForm.setSubjectData("");
                fileUploadDownloadForm.setDistrict_id("");
                request.setAttribute("uploadSuccess", "User Details are not Found/Emails not available");

            }

            if (uploadSuccess) {

                request.setAttribute("uploadSuccess", "uploaded SuccessFully");

                insertFileUpdateDetails = fileUploadDownloadService.insertFileUpdateDetails(ds, fileUploadDownloadForm.getDistrict_id(), name);
                if (insertFileUpdateDetails > 0) {
                    fileUploadDownloadForm.setDistrict_id("");
                    // fileUploadDownloadForm.setSubjectData("");
                    // fileUploadDownloadForm.setBodyMailData("");
                    request.setAttribute("msg", "Data is Inserted SuccessFully");

                    String filePath = "Menu->Others->DownloadFiles";
                    for (int i = 0; i < emailList.size(); i++) {
                        email = emailList.get(i).toString();
                        emailId = new InternetAddress(email, "");
                        RecMail.add(emailId);
                    }

                    if (CCList.size() > 0) {
                        for (int i = 0; i < CCList.size(); i++) {
                            ccData = CCList.get(i).toString();
                            emailId = new InternetAddress(ccData, "");
                            CCMail.add(emailId);
                        }
                    }
                    String date = fileUploadDownloadService.getFileDeleteDetials(ds);
                    EmailUtility eu = new EmailUtility();
                    username = userDetails.get(0).toString();
                    password = userDetails.get(1).toString();
                    EmailBody = eu.MailFormatForFileDownload(username, password, filePath, name, date, fileUploadDownloadForm.getSubjectData(), fileUploadDownloadForm.getBodyMailData());
                    emailStatus = EmailUtility.SendEmail(RecMail, CCMail, BCCMail, name, EmailBody, CommonConstants.Email_ID);
                    fileUploadDownloadForm.setBodyMailData("");
                    fileUploadDownloadForm.setSubjectData("");
                } else {
                    request.setAttribute("msg", "Error in Inserting");
                    fileUploadDownloadForm.setBodyMailData("");
                    fileUploadDownloadForm.setSubjectData("");
                }

                //copy web to local
                sourceDir = new File(getServlet().getServletContext().getRealPath("/") + "SADAREMDOCUMENTS");
                targetDir = new File(CommonConstants.FILEUPLOAD_PATH_PRODUCTION);
                if (sourceDir.exists()) {
                    commonDetails.copyDirectoryandFile(sourceDir, targetDir);
                }
                //copy web to local end

                //delete directory start
                File directory = new File(getServlet().getServletContext().getRealPath("/") + "SADAREMDOCUMENTS");
                //make sure directory exists
                if (!directory.exists()) {
                    System.exit(0);
                } else {
                    try {
                        commonDetails.delete(directory);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(0);
                    }
                }
                //delete directory start
                fileUploadDownloadForm.setBodyMailData("");
                fileUploadDownloadForm.setSubjectData("");
                fileUploadDownloadForm.setDistrict_id("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward LinkDownloadDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        FileUploadDownloadForm fileUploadDownloadForm = (FileUploadDownloadForm) form;
        DataSource ds = null;
        String districtName = null;
        String distid = null;
        TerritoryService territoryservice =
                TerritoryServiceFactory.getTerritoryServiceImpl();
        ArrayList districtlist = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            districtlist = territoryservice.getDistricts(ds);
            fileUploadDownloadForm.setDistrictlist(districtlist);

            if ((String) session.getAttribute("useridData") != null) {
                distid = (String) session.getAttribute("useridData");
            }

            FileUploadDownloadService fileUploadDownloadService =
                    FileUploadDownloadServiceFactory.getFileUploadDownloadServiceImpl();
            String files = (String) request.getParameter("imgId");
            districtName = fileUploadDownloadService.basedOnSessionDetails(ds, distid);

            File folder = new File(CommonConstants.FILEUPLOAD_PATH_PRODUCTION + districtName);

            File[] listOfFiles = folder.listFiles();
            int size = 0;
            if (listOfFiles != null && !listOfFiles.equals("")) {
                size = listOfFiles.length + 1;
            } else {
                size = 0 + 1;
            }
            //String   name1 = districtName+"_"+size+extension+"_____"+fileUploadDownloadForm.getSubjectData();

            CommonDetails commonDetails = new CommonDetails();

            // String path="http://localhost:8085/SADAREMCVS/";
            String contentType = null;
            contentType = "txt";

            BufferedInputStream in = null;
            File fileDetailsData = new File(folder + "\\" + files);


            FileInputStream fin = new FileInputStream(fileDetailsData);

            in = new BufferedInputStream(fin);

            ServletOutputStream out = response.getOutputStream();
            // response.setContentType("application/txt");
            // response.addHeader("Content-Disposition", "attachment; filename="+ files+".zip");
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileDetailsData + "\"");


            byte[] buffer = new byte[4 * 1024];

            int data = 0;
            while ((data = in.read(buffer)) != -1) {
                out.write(buffer, 0, data);
            }
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getFiles(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession(true);
        FileUploadDownloadForm fileUploadDownloadForm = (FileUploadDownloadForm) form;
        String distid = null;
        DataSource ds = null;
        TerritoryService territoryservice =
                TerritoryServiceFactory.getTerritoryServiceImpl();
        ArrayList districtlist = new ArrayList();
        if (fileUploadDownloadForm.getDistrict_id() != null) {
            distid = fileUploadDownloadForm.getDistrict_id();
            fileUploadDownloadForm.setDistrict_id(distid);
            request.setAttribute("dist", distid);

        } else {
            distid = (String) session.getAttribute("useridData");
        }

        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtlist = territoryservice.getDistricts(ds);
            fileUploadDownloadForm.setDistrictlist(districtlist);
            String districtName = null;

            FileUploadDownloadService fileUploadDownloadService =
                    FileUploadDownloadServiceFactory.getFileUploadDownloadServiceImpl();
            districtName = fileUploadDownloadService.getDistrictLoginNameDetails(ds, distid);

            String download = CommonConstants.FILEUPLOAD_PATH_PRODUCTION + districtName;
            File folder = new File(download);
            File[] listOfFiles = folder.listFiles();
            ArrayList files = new ArrayList();
            if (listOfFiles != null && !listOfFiles.equals("")) {

                if (listOfFiles.length != 0) {
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            String file = listOfFiles[i].getName();
                            files.add(file);
                        }
                    }
                }
            }

            request.setAttribute("files", files);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getFilesData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        DataSource ds = null;
        HttpSession session = request.getSession(true);
        FileUploadDownloadForm fileUploadDownloadForm = (FileUploadDownloadForm) form;
        String distid = null;
        TerritoryService territoryservice =
                TerritoryServiceFactory.getTerritoryServiceImpl();
        ArrayList districtlist = new ArrayList();
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String districtName = null;
            districtlist = territoryservice.getDistricts(ds);
            fileUploadDownloadForm.setDistrictlist(districtlist);
            FileUploadDownloadService fileUploadDownloadService =
                    FileUploadDownloadServiceFactory.getFileUploadDownloadServiceImpl();

            if (fileUploadDownloadForm.getDistrict_id() != null) {
                distid = fileUploadDownloadForm.getDistrict_id();
                districtName = fileUploadDownloadService.getDistrictLoginNameDetails(ds, distid);
            } else {
                distid = (String) session.getAttribute("useridData");

                districtName = fileUploadDownloadService.basedOnSessionDetails(ds, distid);
            }

            String download = CommonConstants.FILEUPLOAD_PATH_PRODUCTION + districtName;
            File folder = new File(download);
            File[] listOfFiles = folder.listFiles();
            ArrayList files = new ArrayList();
            if (listOfFiles != null && !listOfFiles.equals("")) {

                if (listOfFiles.length != 0) {
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            String file = listOfFiles[i].getName();
                            files.add(file);
                        }
                    }
                }
            }
            if (files.size() > 0) {
                request.setAttribute("files", files);
            } else {
                request.setAttribute("msg", "No Data Found");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward FilelinkDownlaodDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        FileUploadDownloadForm fileUploadDownloadForm = (FileUploadDownloadForm) form;
        DataSource ds = null;
        String districtName = null;
        String distid = null;
        TerritoryService territoryservice =
                TerritoryServiceFactory.getTerritoryServiceImpl();
        ArrayList districtlist = new ArrayList();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            districtlist = territoryservice.getDistricts(ds);
            fileUploadDownloadForm.setDistrictlist(districtlist);
            if (request.getParameter("district_id") != null) {
                distid = (String) request.getParameter("district_id");
            }


            FileUploadDownloadService fileUploadDownloadService =
                    FileUploadDownloadServiceFactory.getFileUploadDownloadServiceImpl();
            String files = (String) request.getParameter("imgId");
            districtName = fileUploadDownloadService.getDistrictLoginNameDetails(ds, distid);
            // String Path = CommonConstants.FILEUPLOAD_PATH_PRODUCTION + districtName;


            File folder = new File(CommonConstants.FILEUPLOAD_PATH_PRODUCTION + districtName + "\\" + files);



            File[] listOfFiles = folder.listFiles();
            int size = 0;
            if (listOfFiles != null && !listOfFiles.equals("")) {
                size = listOfFiles.length + 1;
            } else {
                size = 0 + 1;
            }

            //String   name1 = districtName+"_"+size+extension+"_____"+fileUploadDownloadForm.getSubjectData();

            CommonDetails commonDetails = new CommonDetails();

            String contentType = null;
            contentType = "txt";

            BufferedInputStream in = null;
            // File fileDetailsData = new File(totalPathDetails);

            /* File[] files11 = new File(CommonConstants.FILEUPLOAD_PATH_PRODUCTION+districtName+"\\"+files).listFiles();
            

            for (File file : files11) {
            if (file.isDirectory()) {
            
            file.listFiles();
            }
            }*/

            FileInputStream fin = new FileInputStream(CommonConstants.FILEUPLOAD_PATH_PRODUCTION + districtName + "\\" + files);

            in = new BufferedInputStream(fin);

            ServletOutputStream out = response.getOutputStream();
            // response.setContentType("application/txt");
            // response.addHeader("Content-Disposition", "attachment; filename="+ files+".zip");


            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + files + "\"");


            byte[] buffer = new byte[4 * 1024];

            int data = 0;
            while ((data = in.read(buffer)) != -1) {
                out.write(buffer, 0, data);
            }
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward downloadFileFromProduction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        viewFile(CommonConstants.HOMEDOWNLOADFILENAME, CommonConstants.HOMEDOWNLOADFILETYPE, request, response);

        return mapping.findForward(target);
    }

    public static void viewFile(String fileName, String fileType, HttpServletRequest request, HttpServletResponse response) {

        try {
            fileName = fileName + fileType;
            File folder = new File(CommonConstants.DATADOWNLOADS);

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
            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");


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
}
