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
import org.bf.disability.form.ImportantLinkForm;
import org.bf.disability.service.ImportantLinkService;
import org.bf.disability.servicefactory.ImportantLinkServiceFactory;

/**
 *
 * @author 693461
 */
public class ImportantLinkAction extends DispatchAction {

    String SUCCESS = "success";
    /* forward name="success" path="" */

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ImportantLinkForm ImportantLinkForm = (ImportantLinkForm) form;
        ImportantLinkService importantLinkService = ImportantLinkServiceFactory.getImportantLinkServiceImpll();
        ArrayList impList = new ArrayList();
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession();
            impList = importantLinkService.getImpLinkDetails(ds);
            request.setAttribute("impList", impList);
            if (impList.size() > 0) {
                request.setAttribute("impList", impList);
            } else {
                request.setAttribute("msgerror", "Important Links Data Not Avilable");

            }
            if (impList.size() > 11) {
                request.setAttribute("page", "page");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward insertWebDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String SUCCESS = "success";

        ImportantLinkForm ImportantLinkForm = (ImportantLinkForm) form;
        ImportantLinkService importantLinkService = ImportantLinkServiceFactory.getImportantLinkServiceImpll();
        int insertwebDetails = 0;
        HttpSession session = request.getSession();
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ImportantLinkForm.setSystemIp(request.getRemoteAddr());
            ImportantLinkForm.setLoginId(session.getAttribute("username").toString());
            insertwebDetails = importantLinkService.insertImpLinkDetails(ds, ImportantLinkForm);
            if (insertwebDetails != 0) {
                request.setAttribute("msg", "Important Link Inserted SuccessFully!");
            } else {
                request.setAttribute("msgerror", "Error in Inserting!");
            }
            ImportantLinkForm.setWebSiteName("");
            ImportantLinkForm.setWebSiteUrl("");

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.unspecified(mapping, form, request, response);

        return mapping.findForward(SUCCESS);
    }

    public ActionForward editImpLinkDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String SUCCESS = "success";

        ImportantLinkForm importantLinkForm = (ImportantLinkForm) form;
        ImportantLinkService importantLinkService = ImportantLinkServiceFactory.getImportantLinkServiceImpll();
        ArrayList webList = new ArrayList();
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String webSiteId = request.getParameter("webSiteId");
            request.setAttribute("webSiteId", webSiteId);
            webList = importantLinkService.getEditLinkDetails(ds, webSiteId);
            importantLinkForm.setWebSiteName(webList.get(0).toString());
            importantLinkForm.setWebSiteUrl(webList.get(1).toString());
            request.setAttribute("editData", "editData");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.unspecified(mapping, form, request, response);

        return mapping.findForward(SUCCESS);
    }

//    
    public ActionForward updateImpLinkDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String SUCCESS = "success";

        ImportantLinkForm importantLinkForm = (ImportantLinkForm) form;
        ImportantLinkService importantLinkService = ImportantLinkServiceFactory.getImportantLinkServiceImpll();
        int updateDetails = 0;
        HttpSession session = request.getSession();


        String sesionTokenCode = null;
        String requestTokenCode = null;
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String webSiteId = importantLinkForm.getWebSiteId();
            updateDetails = importantLinkService.updateImpLinkDetails(ds, importantLinkForm, webSiteId);

            if (updateDetails > 0) {
                request.setAttribute("msg", "Important Link Updated SuccessFully");
            } else {
                request.setAttribute("msgerror", "Error in Updating");
            }
            importantLinkForm.setWebSiteName("");
            importantLinkForm.setWebSiteUrl("");
            importantLinkForm.setWebSiteId("");

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.unspecified(mapping, form, request, response);

        return mapping.findForward(SUCCESS);
    }

    public ActionForward deleteImpLinkDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String SUCCESS = "success";

        ImportantLinkForm importantLinkForm = (ImportantLinkForm) form;
        ImportantLinkService importantLinkService = ImportantLinkServiceFactory.getImportantLinkServiceImpll();
        int updateDetails = 0;
        HttpSession session = request.getSession();
        String sesionTokenCode = null;
        String requestTokenCode = null;
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String webSiteId = request.getParameter("webSiteId");
            updateDetails = importantLinkService.deleteImpLinkDetails(ds, webSiteId);

            if (updateDetails > 0) {
                request.setAttribute("msg", "Important Link Deleted Successfully");
            } else {
                request.setAttribute("msgerror", "Error in Deletion");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.unspecified(mapping, form, request, response);
        importantLinkForm.setWebSiteName("");
        importantLinkForm.setWebSiteUrl("");
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getReportDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String SUCCESS = "WebSiteReport";

        ImportantLinkForm importantLinkForm = (ImportantLinkForm) form;
        ImportantLinkService importantLinkService = ImportantLinkServiceFactory.getImportantLinkServiceImpll();
        ArrayList reportList = new ArrayList();

        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String webSiteId = request.getParameter("webSiteId");
            reportList = importantLinkService.getReportDetails(ds);

            if (reportList.size() > 0) {
                request.setAttribute("reportList", reportList);
            } else {
                request.setAttribute("msgerror", "Important Links Data Not Avilable");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.unspecified(mapping, form, request, response);

        return mapping.findForward(SUCCESS);
    }
}
