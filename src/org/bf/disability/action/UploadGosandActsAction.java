/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import java.io.File;
import java.io.FileInputStream;
import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.UploadGosandActsDTO;
import org.bf.disability.form.UploadGosandActsForm;
import org.bf.disability.service.UploadGosandActsService;
import org.bf.disability.servicefactory.UploadGosandActsServiceFactory;
import org.bf.disability.util.FileUtils;

/**
 *
 * @author 695048
 */
public class UploadGosandActsAction extends DispatchAction {

    /* forward name="success" path="" */
    String SUCCESS = "success";
    String rowid = null;
    String rowid1 = null;

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
        UploadGosandActsForm contactsDetailsForm = (UploadGosandActsForm) form;
        contactsDetailsForm.reset(mapping, request);
        ArrayList uploadDetails = new ArrayList();
        UploadGosandActsDTO uploadGosandActsDTO = new UploadGosandActsDTO();
        UploadGosandActsService gosandActsService = UploadGosandActsServiceFactory.getGosandActsServiceImpl();
        ArrayList gosDetailsHome = new ArrayList();
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // contactsDetailsForm.setDepartmentList(gosandActsService.getDepartmentList(ds, contactsDetailsForm));
            String loginId = null;
            HttpSession session = request.getSession();
            if (session.getAttribute("username") != null) {
                loginId = session.getAttribute("username").toString();
            }
            uploadGosandActsDTO.setLoginId(loginId);
            uploadDetails = gosandActsService.getGosUploadDetails(ds, uploadGosandActsDTO);
            if (uploadDetails.size() > 0) {
                request.setAttribute("uploadDetails", uploadDetails);
            } else {
                request.setAttribute("nodata", "GOs Not Available");
            }

            //gosDetailsHome = gosandActsService.getGosDetailsHomePage(ds, contactsDetailsForm);
            ArrayList gosDetailssec1 = gosandActsService.getGosDetailsBySection(ds, contactsDetailsForm, "1");
            ArrayList gosDetailssec2 = gosandActsService.getGosDetailsBySection(ds, contactsDetailsForm, "2");
            if (gosDetailssec1.size() > 0) {
                request.setAttribute("gosDetailssec1", gosDetailssec1);
            }else {
                request.setAttribute("nodataMSG1", "General GO's Not Available");
            }
            if (gosDetailssec2.size() > 0) {
                request.setAttribute("gosDetailssec2", gosDetailssec2);
            } else {
                request.setAttribute("nodataMSG2", "SADAREM GO's Not Available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward insertDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int details = 0;
        String target = "success";
        UploadGosandActsForm bdrform = (UploadGosandActsForm) form;
        UploadGosandActsDTO dto = new UploadGosandActsDTO();
        UploadGosandActsService gosandActsService = UploadGosandActsServiceFactory.getGosandActsServiceImpl();
        String url = getServlet().getServletContext().getRealPath("/");
        HttpSession session = request.getSession();
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String loginid = null, deptId = null;
            if (session.getAttribute("username") != null) {
                loginid = session.getAttribute("username").toString();
            }
            int fileSize = 0;
            if (bdrform.getUploadGosActs() != null && bdrform.getUploadGosActs().getFileSize() > 0) {
                if (bdrform.getUploadGosActs().getFileSize() >= 1024) {
                    fileSize = bdrform.getUploadGosActs().getFileSize() / 1024;
                } else {
                    fileSize = bdrform.getUploadGosActs().getFileSize();
                }
            }

            int fileExtensionCount = StringUtils.countMatches(bdrform.getUploadGosActs().getFileName(), ".");
            //if (bdrform.getUploadGosActs().getContentType().equalsIgnoreCase("application/pdf")) {
                if (fileExtensionCount == 1) {
                    if (fileSize > 0 && fileSize <= 10240) {
                        details = gosandActsService.insertDetails(ds, dto, bdrform, request, url);
                        if (details != 0) {
                            request.setAttribute("msg", "G.O Inserted Successfully");
                            unspecified(mapping, form, request, response);
                            bdrform.setGoCategory("");
                            bdrform.setGoDepartment("");
                            bdrform.setGoNumber("");
                            bdrform.setGoSection("");
                            bdrform.setGodescription("");
                            bdrform.setGoType("");
                            bdrform.setSubject_id("");
                            bdrform.setDateofApplication("");
                            bdrform.setSubSection("");
                        } else {
                            request.setAttribute("nodata", "Error in Inserting G.O");
                        }
                    } else {
                        request.setAttribute("nodata", "File Size Must Be Bellow 10MB");
                    }
                } else {
                    request.setAttribute("nodata", "File Extension should be .pdf only");
                }
//            } else {
//                request.setAttribute("nodata", "Content Type Should be PDF Only");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        unspecified(mapping, form, request, response);
        return mapping.findForward(target);
    }

    public ActionForward downloadFileHomePage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UploadGosandActsForm frm = (UploadGosandActsForm) form;
        BufferedInputStream in = null;
        ServletOutputStream out = null;
        try {
            getServlet().getServletContext().getRealPath("");


            String downloadpath = FileUtils.filePath("departmentGos");


            //File fileDetailsData = new File(downloadpath + "\\" + request.getParameter("id") + "\\" + request.getParameter("id") + ".pdf");
            File fileDetailsData = new File(downloadpath + "\\" + request.getParameter("id"));
          

            File[] listOfFiles = fileDetailsData.listFiles();
            fileDetailsData =listOfFiles[0];
            FileInputStream fin = new FileInputStream(fileDetailsData);

            in = new BufferedInputStream(fin);

            out = response.getOutputStream();
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileDetailsData.getName() + "\"");
            byte[] buffer = new byte[4 * 1024];

            int data = 0;
            while ((data = in.read(buffer)) != -1) {
                out.write(buffer, 0, data);
            }
            out.close();
            in.close();

            //out.flush();


        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("nodata", "G.O you are looking for is not found");
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }

            return mapping.findForward(SUCCESS);
        }

    }

    public ActionForward getSubjectData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        UploadGosandActsForm gosandActsForm = (UploadGosandActsForm) form;
        UploadGosandActsDTO uploadGosandActsDTO = new UploadGosandActsDTO();
        UploadGosandActsService gosandActsService = UploadGosandActsServiceFactory.getGosandActsServiceImpl();
        ArrayList data = new ArrayList();
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            uploadGosandActsDTO.setGoSection(gosandActsForm.getGoSection());
            uploadGosandActsDTO.setSubSection(gosandActsForm.getSubSection());
            data = gosandActsService.getSubject(ds, uploadGosandActsDTO);
            gosandActsForm.setSubjectList(data);
//            unspecified(mapping, form, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward getEditDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ArrayList getEditDetails = new ArrayList();
            ArrayList getDepartmentlist = new ArrayList();
            ArrayList uploadDetails = new ArrayList();
            UploadGosandActsForm gosandActsForm = (UploadGosandActsForm) form;
            UploadGosandActsDTO gosandActsDTO = new UploadGosandActsDTO();
            UploadGosandActsService gosandActsService = UploadGosandActsServiceFactory.getGosandActsServiceImpl();
            getDepartmentlist = gosandActsService.getDepartmentList(ds, gosandActsForm);
            gosandActsForm.setDepartmentList1(getDepartmentlist);
            String loginId = null;
            HttpSession session = request.getSession();
            if (session.getAttribute("username") != null) {
                loginId = session.getAttribute("username").toString();
            }
            gosandActsDTO.setLoginId(loginId);
            uploadDetails = gosandActsService.getGosUploadDetails(ds, gosandActsDTO);
            if (uploadDetails.size() > 0) {
                request.setAttribute("uploadDetails", uploadDetails);
            }

            rowid1 = request.getParameter("regNo");

            getEditDetails = gosandActsService.getEditDetails(ds, rowid1);
            if (getEditDetails.size() > 0) {
                gosandActsForm.setGoSection(getEditDetails.get(0).toString());
                gosandActsForm.setGoType(getEditDetails.get(1).toString());
                gosandActsForm.setGoNumber(getEditDetails.get(2).toString());
                gosandActsForm.setGoCategory(getEditDetails.get(3).toString());
                gosandActsForm.setGodescription(getEditDetails.get(4).toString());
                gosandActsForm.setDateofApplication(getEditDetails.get(5).toString());
                gosandActsForm.setRowId(rowid1);
            }
            request.setAttribute("updateButton", "updateButton");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward updateDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UploadGosandActsForm bdrform = (UploadGosandActsForm) form;
        UploadGosandActsDTO gosandActsDTO = new UploadGosandActsDTO();
        UploadGosandActsService gosandActsService = UploadGosandActsServiceFactory.getGosandActsServiceImpl();
        int details = 0;
        String url = getServlet().getServletContext().getRealPath("/");
        HttpSession session = request.getSession();
        String sesionTokenCode = null;
        String requestTokenCode = null;
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String loginid = null, deptId = null;
            if (session.getAttribute("username") != null) {
                loginid = session.getAttribute("username").toString();
            }
            if (session.getAttribute("department") != null) {
                deptId = session.getAttribute("department").toString();
            }

//            gosandActsDTO.setRowId(session.getAttribute("rowID").toString());
            BeanUtils.copyProperties(gosandActsDTO, bdrform);
            details = gosandActsService.inActiveUploadGo(ds, gosandActsDTO, bdrform, request, url, rowid1);
            if (details != 0) {
                request.setAttribute("msg", "GOs and Acts Details Updated Successfully");
                bdrform.setGoCategory("");
                bdrform.setGoDepartment("00");
                bdrform.setGoNumber("");
                bdrform.setGoSection("");
                bdrform.setGodescription("");
                bdrform.setGoType("");
                bdrform.setSubject_id("");
                bdrform.setDateofApplication("");
                bdrform.setSubSection("");
                bdrform.setRowId("");
                unspecified(mapping, form, request, response);
            } else {
                request.setAttribute("nodata", "Error in Updation");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward inActiveStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = SUCCESS;
        UploadGosandActsService gosandActsService = UploadGosandActsServiceFactory.getGosandActsServiceImpl();
        int details = 0;
        HttpSession session = request.getSession();
        String sesionTokenCode = null;
        String requestTokenCode = null;
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String loginid = null, deptId = null;
            if (session.getAttribute("username") != null) {
                loginid = session.getAttribute("username").toString();
            }
            if (session.getAttribute("department") != null) {
                deptId = session.getAttribute("department").toString();
            }


            details = gosandActsService.inActiveStatus(ds, request.getParameter("regNo").toString());
            if (details != 0) {
                request.setAttribute("msg", "GO Deleted Successfully");
                unspecified(mapping, form, request, response);
            } else {
                request.setAttribute("nodata", "Error in Updation");
            }
            if (request.getParameter("targertype") != null && request.getParameter("targertype").equals("sectiongo")) {
                target = "uploadSectionsGos";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }
}
