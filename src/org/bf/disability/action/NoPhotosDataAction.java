/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.NoPhotosDataForm;
import org.bf.disability.service.NoPhotosDataService;
import org.bf.disability.servicefactory.NoPhotosDataServiceFactory;

/**
 *
 * @author 728056
 */
public class NoPhotosDataAction extends DispatchAction {

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
        ArrayList Nophotolist = new ArrayList();

        HttpSession session = request.getSession(true);


        NoPhotosDataForm photosDataForm = (NoPhotosDataForm) form;
        NoPhotosDataService service = NoPhotosDataServiceFactory.getPhotosDataServiceImpl();


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            if (session.getAttribute("districtId") != null) {
                photosDataForm.setDistrictId((String) session.getAttribute("districtId"));
            }

            if (photosDataForm.getFromDate() == null || (photosDataForm.getFromDate() != null && photosDataForm.getFromDate().length() < 1)) {
                photosDataForm.setFromDate("01/01/2010");
            }

            if (photosDataForm.getToDate() == null || (photosDataForm.getToDate() != null && photosDataForm.getToDate().length() < 1)) {
                CommonDAO commonDAO = new CommonDAO();
                Date pre = new Date();
                photosDataForm.setToDate(commonDAO.getCurrentEndtDate(pre.getMonth() + "", pre.getYear() + ""));
               
            }
         
            ArrayList campList = service.getCampDetails(ds, photosDataForm.getDistrictId());
            photosDataForm.setCampList(campList);
            Nophotolist = service.getNophotosData(ds, photosDataForm);
            if (Nophotolist.size() == 0) {
                request.setAttribute("msg1", "Photos Not Uploaded Details Not Avaliable!");
            } else {
                request.setAttribute("msg", Nophotolist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }
//    public ActionForward getNoPhotosData(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        ArrayList Nophotolist = new ArrayList();
//
//        HttpSession session = request.getSession(true);
//        String s = session.getAttribute("districtId").toString();
//
//        NoPhotosDataForm photosDataForm = (NoPhotosDataForm) form;
//        NoPhotosDataService service = NoPhotosDataServiceFactory.getPhotosDataServiceImpl();
//
//        try {
//            ds = getDataSource(request);
//            if (ds == null || "null".equals(ds)) {
//                ds = JNDIDataSource.getConnection();
//            }
//
//            Nophotolist = service.getNophotosData(ds, session);
//            if (Nophotolist.size() == 0) {
//                request.setAttribute("msg", "No DataFound");
//            } else {
//                request.setAttribute("msg", Nophotolist);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mapping.findForward(SUCCESS);
//    }
}
