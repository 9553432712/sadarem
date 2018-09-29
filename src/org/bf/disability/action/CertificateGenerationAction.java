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
import org.bf.disability.form.CertificateGenerationForm;
import org.bf.disability.service.CertificateGenerationService;
import org.bf.disability.servicefactory.CertificateGenerationServiceFoctory;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author 728056
 */
public class CertificateGenerationAction extends DispatchAction {

    /* forward name="success" path="" */
    String SUCCESS = "success";
    DataSource ds = null;
    String districtid = null;

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

//        String districtid = null;
        ArrayList MandalNames = new ArrayList();
        ArrayList villageNames = new ArrayList();
        ArrayList HabitationNames = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            districtid = (String) session.getAttribute("districtId");

            CertificateGenerationForm cgform = (CertificateGenerationForm) form;
            CertificateGenerationService service = CertificateGenerationServiceFoctory.certificateGenerationImpl();



            MandalNames = service.getMandalList(ds, districtid);
            cgform.setMandalList(MandalNames);

            villageNames = service.getviiageNames(ds, cgform, districtid);
            cgform.setVillageList(villageNames);
            if (cgform.getVillage() != null && cgform.getVillage().equals("0")) {
                HabitationNames = service.getHabitationNames(ds, cgform, districtid);
            }
            cgform.setHabitationList(HabitationNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getVillageNames(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArrayList villageNames = new ArrayList();
        String districtid = null;


        CertificateGenerationForm cgform = (CertificateGenerationForm) form;
        CertificateGenerationService service = CertificateGenerationServiceFoctory.certificateGenerationImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            villageNames = service.getviiageNames(ds, cgform, districtid);
            cgform.setVillageList(villageNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getHabitationNames(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArrayList Habitation = new ArrayList();

        CertificateGenerationForm cgform = (CertificateGenerationForm) form;
        CertificateGenerationService service = CertificateGenerationServiceFoctory.certificateGenerationImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession();
            String districtid = (String) session.getAttribute("districtId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        Habitation = service.getHabitationNames(ds, cgform, districtid);
        cgform.setHabitationList(Habitation);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArrayList TotalData = new ArrayList();
        ArrayList box = new ArrayList();
        CertificateGenerationForm cgform = (CertificateGenerationForm) form;
        CertificateGenerationService service = CertificateGenerationServiceFoctory.certificateGenerationImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            districtid = (String) session.getAttribute("districtId");




            TotalData = service.getPersonDetails(ds, cgform, districtid);

            request.setAttribute("msg", TotalData);
            if (TotalData.isEmpty()) {
                request.setAttribute("msg1", "No Data Found");
            }
            cgform.reset(TotalData.size(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        ArrayList Habitation = service.getHabitationNames(ds, cgform, districtid);
        cgform.setHabitationList(Habitation);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward insertDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArrayList TotalData = new ArrayList();
        boolean insert = false;
        HttpSession session = request.getSession();
        districtid = (String) session.getAttribute("districtId");
        CertificateGenerationForm cgform = (CertificateGenerationForm) form;
        CertificateGenerationService service = CertificateGenerationServiceFoctory.certificateGenerationImpl();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            cgform.setDistrictId(districtid);
            cgform.setLoginId(session.getAttribute("loginid").toString());
            TotalData = service.getPersonDetails(ds, cgform, districtid);
            String remarks[] = new String[TotalData.size()];
            String date[] = new String[TotalData.size()];
            int i = 0;
            for (int j = 1; j <= TotalData.size(); j++) {

                if ((cgform.getDynaProperty((j + "#1")) != null && !cgform.getDynaProperty(j + "#1").toString().equals(""))
                        && (cgform.getDynaProperty((j + "#2")) != null && !cgform.getDynaProperty(j + "#2").toString().equals(""))) {
                    remarks[i] = cgform.getDynaProperty(j + "#1").toString();
                    date[i] = cgform.getDynaProperty(j + "#2").toString();
                    i++;
                }

            }

            cgform.setRemarks(remarks);
            cgform.setFromdate(date);
            insert = service.insertDetails(ds, cgform);
            if (insert == false) {
                request.setAttribute("msg2", "Successfully Inserted");
            }
            TotalData = service.getPersonDetails(ds, cgform, districtid);
            request.setAttribute("msg", TotalData);


        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        cgform.reset(TotalData.size(), 2);
        ArrayList Habitation = service.getHabitationNames(ds, cgform, districtid);
        cgform.setHabitationList(Habitation);
        return mapping.findForward(SUCCESS);
    }
}
