/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.MandalWiseClusterReportForm;
import org.bf.disability.service.MandalWiseClusterReportService;
import org.bf.disability.servicefactory.MandalWiseClusterReportServicefactory;

/**
 *
 * @author 707797
 */
public class MandalWiseClusterReportAction extends DispatchAction {

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

        ArrayList DistrictName = new ArrayList();
        ArrayList MandalName = new ArrayList();
        ArrayList VillageName = new ArrayList();


        MandalWiseClusterReportForm pwdReportsForm = (MandalWiseClusterReportForm) form;
        MandalWiseClusterReportService dataService = MandalWiseClusterReportServicefactory.getMandalWiseClusterReportImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            DistrictName = dataService.getDistrictNames(ds, pwdReportsForm);
            pwdReportsForm.setDistrictList(DistrictName);

            MandalName = dataService.getMandalNames(ds, pwdReportsForm);
            pwdReportsForm.setMandalList(MandalName);

            VillageName = dataService.getVillageNames(ds, pwdReportsForm);
            pwdReportsForm.setVillageList(VillageName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward getMandalData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArrayList MandalName = new ArrayList();


        MandalWiseClusterReportForm pwdReportsForm = (MandalWiseClusterReportForm) form;
        MandalWiseClusterReportService dataService = MandalWiseClusterReportServicefactory.getMandalWiseClusterReportImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            MandalName = dataService.getMandalNames(ds, pwdReportsForm);
            pwdReportsForm.setMandalList(MandalName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getvillageData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArrayList VillageName = new ArrayList();



        MandalWiseClusterReportForm pwdReportsForm = (MandalWiseClusterReportForm) form;
        MandalWiseClusterReportService dataService = MandalWiseClusterReportServicefactory.getMandalWiseClusterReportImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            VillageName = dataService.getVillageNames(ds, pwdReportsForm);
            pwdReportsForm.setVillageList(VillageName);



        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getpwdDData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        ArrayList PwdData = new ArrayList();
        ArrayList PwdData1 = new ArrayList();
        ArrayList PwdData2 = new ArrayList();


        MandalWiseClusterReportForm pwdReportsForm = (MandalWiseClusterReportForm) form;
        MandalWiseClusterReportService dataService = MandalWiseClusterReportServicefactory.getMandalWiseClusterReportImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            request.setAttribute("distId", pwdReportsForm.getDistrict());
            request.setAttribute("mandalId", pwdReportsForm.getMandal());
            PwdData = dataService.getPwdData(ds, pwdReportsForm);
            request.setAttribute("PwdData", PwdData);

            PwdData1 = dataService.getPwdData1(ds, pwdReportsForm);
            request.setAttribute("PwdData1", PwdData1);

            PwdData2 = dataService.getPwdData2(ds, pwdReportsForm);
            request.setAttribute("PwdData2", PwdData2);


            if (PwdData.size() == 0 && PwdData1.size() == 0 && PwdData2.size() == 0) {
                request.setAttribute("msg", "No DataFound");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getassetsData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArrayList PwdData1 = new ArrayList();


        MandalWiseClusterReportForm pwdReportsForm = (MandalWiseClusterReportForm) form;
        pwdReportsForm.setDistrict(request.getParameter("distId"));
        pwdReportsForm.setMandal(request.getParameter("mandalId"));
        MandalWiseClusterReportService dataService = MandalWiseClusterReportServicefactory.getMandalWiseClusterReportImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            request.setAttribute("distId", pwdReportsForm.getDistrict());
            request.setAttribute("mandalId", pwdReportsForm.getMandal());
            PwdData1 = dataService.getPwdData(ds, pwdReportsForm);

            request.setAttribute("assetsData", PwdData1);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("Excel");
    }

    public ActionForward getIBDatabase(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        ArrayList PwdData2 = new ArrayList();


        MandalWiseClusterReportForm pwdReportsForm = (MandalWiseClusterReportForm) form;
        pwdReportsForm.setDistrict(request.getParameter("distId"));
        pwdReportsForm.setMandal(request.getParameter("mandalId"));
        MandalWiseClusterReportService dataService = MandalWiseClusterReportServicefactory.getMandalWiseClusterReportImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            request.setAttribute("distId", pwdReportsForm.getDistrict());
            request.setAttribute("mandalId", pwdReportsForm.getMandal());
            PwdData2 = dataService.getPwdData1(ds, pwdReportsForm);

            request.setAttribute("IBDatabase", PwdData2);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("Excel");
    }

    public ActionForward getMISDatabase(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        ArrayList PwdData3 = new ArrayList();


        MandalWiseClusterReportForm pwdReportsForm = (MandalWiseClusterReportForm) form;
        pwdReportsForm.setDistrict(request.getParameter("distId"));
        pwdReportsForm.setMandal(request.getParameter("mandalId"));
        MandalWiseClusterReportService dataService = MandalWiseClusterReportServicefactory.getMandalWiseClusterReportImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            request.setAttribute("distId", pwdReportsForm.getDistrict());
            request.setAttribute("mandalId", pwdReportsForm.getMandal());
            PwdData3 = dataService.getPwdData2(ds, pwdReportsForm);

            request.setAttribute("MISDatabase", PwdData3);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("Excel");
    }
}
