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
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.servicefactory.ReAssessementCampWiseReportServiceFactory;
import org.bf.disability.serviceimpl.ReAssessmentCampWiseServiceImpl;

/**
 *
 * @author 509862
 */
public class ReAssessmentCampWiseReportAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    // private static final String SUCCESS = "success";
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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DataSource ds = null;
        ArrayList districtList = new ArrayList();
        ArrayList campList = new ArrayList();
        ArrayList assesmentList = null;
        String district_id = null;
        String target = "success";

        ReAssessmentCampWiseServiceImpl reAssessmentCampWiseService = null;
        FunctionalNeedReportForm functionalNeedReportForm = (FunctionalNeedReportForm) form;
        
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            reAssessmentCampWiseService = ReAssessementCampWiseReportServiceFactory.getReAssessementCampWiseReportServiceImpl();

            districtList = reAssessmentCampWiseService.getDistrictDetials(ds);

            if (districtList != null) {

                functionalNeedReportForm.setDistrictList(districtList);
            }

            if (functionalNeedReportForm.getDistrict_id() != null && !functionalNeedReportForm.getDistrict_id().equals("0")) {
                campList = reAssessmentCampWiseService.getCampDetails(ds, functionalNeedReportForm.getDistrict_id());
                functionalNeedReportForm.setCampList(campList);
                districtList = reAssessmentCampWiseService.getDistrictDetials(ds);
                functionalNeedReportForm.setDistrictList(districtList);


            }

            if ("ReAssessmentValues".equalsIgnoreCase(functionalNeedReportForm.getMode())) {

                assesmentList = new ArrayList();
                if (functionalNeedReportForm.getDistrict_id() != null && functionalNeedReportForm.getDistrict_id() != "0" && functionalNeedReportForm.getCamp_id() != null && functionalNeedReportForm.getCamp_id() != "0") {

                    assesmentList = reAssessmentCampWiseService.getReAssessmentDetails(ds, functionalNeedReportForm.getDistrict_id(), functionalNeedReportForm.getCamp_id());

                    if (assesmentList.size() > 0) {
                        request.setAttribute("AssesmentList", assesmentList);
                        target = "success";

                    } else {
                        request.setAttribute("msgss", "No Data Found");
                        target = "success";
                    }

                }

            } else if ("getReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                request.setAttribute("districtId", request.getParameter("districtId"));
                target = "print";
            } else if ("getReportExcel".equalsIgnoreCase(request.getParameter("status"))) {
                request.setAttribute("districtId", request.getParameter("districtId"));
                target = "excel";
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return mapping.findForward(target);
    }
}
