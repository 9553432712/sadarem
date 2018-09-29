/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import org.bf.disability.dao.CMPUploadDAO;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import org.bf.disability.dto.CMPUploadDTO;
import org.bf.disability.form.CMPUploadsForm;
import org.bf.disability.servicefactory.CMPUploadsFactory;
import org.bf.disability.service.CMPUploadsService;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.Constants.CommonConstants;
import javax.sql.DataSource;
import org.bf.disability.common.CommonDAO;

/**
 *
 * @author 484898
 */
public class CMPUploadsAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

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

        CMPUploadsService cmpUploadsService = CMPUploadsFactory.getCMPUploadsImpl();
        CMPUploadDTO cmpUploadDTO = new CMPUploadDTO();
        ArrayList CMPUploadsWithDoc = new ArrayList();
        HttpSession session = null;
        session = request.getSession(true);
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        if (session.getAttribute("RoleId") != null && session.getAttribute("RoleId").toString().length() > 0 && !session.getAttribute("RoleId").equals("null")) {
            cmpUploadDTO.setRoleId(session.getAttribute("RoleId").toString());
        }

        CMPUploadsWithDoc = cmpUploadsService.getCMPUploadsDetails(cmpUploadDTO, ds);

        if (CMPUploadsWithDoc.size() > 0) {
            request.setAttribute("cmpUploadsDetails", CMPUploadsWithDoc);
        }

        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for generating the circular number
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward generateCircularNumber(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CMPUploadsService cmpUploadsService = CMPUploadsFactory.getCMPUploadsImpl();
        CMPUploadDTO cmpUploadDTO = new CMPUploadDTO();
        CMPUploadsForm cMPUploadsForm = (CMPUploadsForm) form;
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        String circularNumber = null;
        HttpSession session = null;
        session = request.getSession(true);
        if (!cMPUploadsForm.getDocumentType().equals("00")) {

            //cmpUploadDTO.setRoleId(session.getAttribute("RoleId").toString());
            cmpUploadDTO.setDocumentType(cMPUploadsForm.getDocumentType());
            circularNumber = cmpUploadsService.generateCircularNumber(cmpUploadDTO, ds);
            if (circularNumber != null && circularNumber.length() > 0) {
                request.setAttribute("circularNumber", circularNumber);
                //cMPUploadsForm.setMemoNumber(circularNumber);
            } else {
                request.setAttribute("circularNumberError", "Problem in generating the number");
            }
        } else {
            cMPUploadsForm.setMemoNumber("");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for saving the details of Memos/Circulars/Proceedings etc
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
      
        CMPUploadsService cmpUploadsService = CMPUploadsFactory.getCMPUploadsImpl();
        CMPUploadDTO cmpUploadDTO = new CMPUploadDTO();
        CMPUploadsForm cMPUploadsForm = (CMPUploadsForm) form;
        HttpSession session = null;
        int insertStatus = 0;
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        session = request.getSession(true);
        BeanUtils.copyProperties(cmpUploadDTO, cMPUploadsForm);
        cmpUploadDTO.setUrl(getServlet().getServletContext().getRealPath("/"));
        //cmpUploadDTO.setRoleId(session.getAttribute("RoleId").toString());

        // For Circular / Memo / Procedure number Adding

        if (request.getParameter("cirOne") != null && request.getParameter("cirOne").length() > 0
                && request.getParameter("cirTwo") != null && request.getParameter("cirTwo").length() > 0
                && request.getParameter("fileNumber") != null && request.getParameter("fileNumber").length() > 0) {
            cmpUploadDTO.setCircularCount(request.getParameter("cirOne"));
            cmpUploadDTO.setFileNumber(request.getParameter("fileNumber").toString());
            cmpUploadDTO.setMemoNumber(request.getParameter("cirOne") + "/" + request.getParameter("fileNumber") + "/" + request.getParameter("cirTwo"));
        }

        // End

        insertStatus = cmpUploadsService.saveDetails(cmpUploadDTO, request, ds);
        if (insertStatus != 0) {
            request.setAttribute("msg", cMPUploadsForm.getDocumentType() + " Uploaded Successfully");
            cMPUploadsForm.setDocumentType("00");
            cMPUploadsForm.setIssueingDate("");
            cMPUploadsForm.setSubject("");
            cMPUploadsForm.setReference("");
            cMPUploadsForm.setMemoNumber("");
        } else {
            request.setAttribute("msg", "Error in " + cMPUploadsForm.getDocumentType() + " Upload");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method returns the keycontacts and set into the form bean for editing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getValuesWhenClickOnEdit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CMPUploadsService cmpUploadsService = CMPUploadsFactory.getCMPUploadsImpl();
        CMPUploadDTO cmpUploadDTO = new CMPUploadDTO();
        CMPUploadsForm cMPUploadsForm = (CMPUploadsForm) form;

        ArrayList cmpUploads = new ArrayList();
        Map elements = null;
        String finalString = null;
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        // Set Rowid to DTO
        if (request.getParameter("RowId") != null && request.getParameter("RowId").toString().length() > 0) {
            cmpUploadDTO.setRowId(request.getParameter("RowId").toString());
        }

        // FETCHING THE ENTERED KEY CONTACTS DATA

        cmpUploads = cmpUploadsService.getCMPDetailsForEdit(cmpUploadDTO, ds);
        if (cmpUploads.size() > 0) {
            request.setAttribute("cmpUploads", cmpUploads);
        }

        // when click on edit selected values is set into form bean
        if (cmpUploads != null && cmpUploads.size() > 0) {
            try {

                Iterator values = cmpUploads.iterator();
                while (values.hasNext()) {
                    elements = (Map) values.next();
                    cMPUploadsForm.setDocumentType(elements.get("DocumentType").toString());
                    cMPUploadsForm.setFileNumber(elements.get("CMPNumber").toString());
                    cMPUploadsForm.setIssueingDate(elements.get("IssuingDate").toString());
                    cMPUploadsForm.setSubject(elements.get("Subject").toString());
                    cMPUploadsForm.setReference(elements.get("Reference").toString());
                    cMPUploadsForm.setFileNumber(elements.get("FileNumber").toString());

                    finalString = "&nbsp;&nbsp;<input type=\"text\" name=\"memoNumberOne\" size=\"2\" disabled onkeydown=\"return space(event,this)\" style=\"text-align:center;\" value=" + elements.get("CircularCount").toString() + "><input type=\"hidden\" name=\"cirOne\"  value=" + elements.get("CircularCount").toString() + ">" + "&nbsp;&nbsp;/"
                            + "&nbsp;&nbsp;<input type=\"text\" name=\"fileNumber\" size=\"15\" onkeydown=\"return space(event,this)\" value=" + elements.get("FileNumber").toString() + ">" + "&nbsp;&nbsp;/"
                            + "&nbsp;&nbsp;<input type=\"text\" name=\"memoNumberTwo\" size=\"2\" disabled onkeydown=\"return space(event,this)\" style=\"text-align:center;\" value=" + elements.get("IssuingYear").toString() + "><input type=\"hidden\" name=\"cirTwo\" value=" + elements.get("IssuingYear").toString() + ">";
                    request.setAttribute("circularNumber", finalString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("enableEdit", "enableEdit");
        }

        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for Update the CMP details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CMPUploadsService cmpUploadsService = CMPUploadsFactory.getCMPUploadsImpl();
        CMPUploadDTO cmpUploadDTO = new CMPUploadDTO();
        CMPUploadsForm cMPUploadsForm = (CMPUploadsForm) form;
        int insertStatus = 0;
        HttpSession session = null;
        session = request.getSession(true);
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        cmpUploadDTO.setRowId(request.getParameter("rowIdForUpdate"));
        BeanUtils.copyProperties(cmpUploadDTO, cMPUploadsForm);
        cmpUploadDTO.setUrl(getServlet().getServletContext().getRealPath("/"));
        cmpUploadDTO.setRoleId(session.getAttribute("RoleId").toString());

// For Circular / Memo / Procedure number Adding

        if (request.getParameter("cirOne") != null && request.getParameter("cirOne").length() > 0
                && request.getParameter("cirTwo") != null && request.getParameter("cirTwo").length() > 0
                && request.getParameter("fileNumber") != null && request.getParameter("fileNumber").length() > 0) {
            cmpUploadDTO.setCircularCount(request.getParameter("cirOne"));
            cmpUploadDTO.setFileNumber(request.getParameter("fileNumber").toString());
            cmpUploadDTO.setMemoNumber(request.getParameter("cirOne") + "/" + request.getParameter("fileNumber") + "/" + request.getParameter("cirTwo"));
        }
// End
        insertStatus = cmpUploadsService.updateDetails(cmpUploadDTO, request, ds);
        if (insertStatus != 0) {
            request.setAttribute("msg", "CMP Details Updated Successfully");
            request.setAttribute("enableEdit", "enableEdit");
            cMPUploadsForm.setDocumentType("00");
            cMPUploadsForm.setIssueingDate("");
            cMPUploadsForm.setSubject("");
            cMPUploadsForm.setReference("");
            cMPUploadsForm.setMemoNumber("");
        } else {
            request.setAttribute("msg", "Error in CMP Details Updating");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for inactive the CMP Uploads details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward inactiveRecord(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CMPUploadsService cmpUploadsService = CMPUploadsFactory.getCMPUploadsImpl();
        CMPUploadDTO cmpUploadDTO = new CMPUploadDTO();
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        int insertStatus = 0;
        cmpUploadDTO.setRowId(request.getParameter("inactive"));
        insertStatus = cmpUploadsService.inactiveRecord(cmpUploadDTO, ds);
        if (insertStatus != 0) {
            request.setAttribute("msg", "Selected Record Deleted Successfully");
        } else {
            request.setAttribute("msg", "Error in Record Deletion");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     *  This method is used for copy files for source folder to destination folder
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward downloadGo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        if (request.getParameter("Go") != null && request.getParameter("Go").toString().length() > 0) {
            try {

                BufferedInputStream in = null;
                File dir = new File(CommonConstants.CIRCULARS_MEMOS_PROCEEDINGS + "\\" + request.getParameter("Go"));

                FileInputStream fin = new FileInputStream(dir);

                in = new BufferedInputStream(fin);
                response.setContentType("application/pdf");
                ServletOutputStream out = response.getOutputStream();
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment; filename=\"" + dir + "\"");


                byte[] buffer = new byte[4 * 1024];

                int data = 0;
                while ((data = in.read(buffer)) != -1) {
                    out.write(buffer, 0, data);
                }

                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                target = "success";
                request.setAttribute("errorMsg", " >>>>>>>>>>>>> Requested Document Was Not Available. <<<<<<<<<<<<<");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("msg", "Selected Go Not Available");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(target);

    }

    /**
     * This method is for view the go
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewGo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CommonDAO commonDAO = new CommonDAO();
        if (request.getParameter("Go") != null && request.getParameter("Go").toString().length() > 0) {
            commonDAO.moveFile(CommonConstants.CIRCULARS_MEMOS_PROCEEDINGS + "\\", getServlet().getServletContext().getRealPath("/") + CommonConstants.PDFWEBPATH + "\\", request.getParameter("Go"));
            request.setAttribute("filePath", request.getParameter("Go"));
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward("success");
    }
}
