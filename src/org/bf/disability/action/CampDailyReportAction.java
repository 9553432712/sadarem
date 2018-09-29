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
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.CampDailyReportForm;
import org.bf.disability.service.CampDailyReportService;
import org.bf.disability.service.RationCardReportService;
import org.bf.disability.servicefactory.CampDailyReportServiceFactory;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.servicefactory.RationCardReportServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author SADAREM
 */
public class CampDailyReportAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    // private static final String SUCCESS = "success";
    private String target = "success";

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


        HttpSession session = request.getSession();
        CampDailyReportForm campDailyReportForm = (CampDailyReportForm) form;
        DataSource datasource = null;
        
        ArrayList campList = new ArrayList();
        ArrayList campDataList = new ArrayList();
        ArrayList disabilityList = new ArrayList();
        int i = 0;
        String mode = null;

        String loginID = (String) session.getAttribute("loginid");
        String systemIP = CommonUtility.getClientIPAddress(request);

        try {datasource = getDataSource(request);
        if (datasource == null || "null".equals(datasource)) {
            datasource = JNDIDataSource.getConnection();
        }


            // FunctionalNeedReportService functionalNeedService =
            FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();

            CampDailyReportService campDailyReportService = CampDailyReportServiceFactory.getcampDailyReportServiceImpl();

            RationCardReportService rationCardReportService = RationCardReportServiceFactory.getRationCardServiceImpl();

            /* Getting the camp Details based on district code */

            if (session.getAttribute("districtId") != null) {
                campList = rationCardReportService.getMandals(datasource, (String) session.getAttribute("districtId"));
                campDailyReportForm.setCampList(campList);
            }

            if (campDataList != null) {

                campDataList = campDailyReportService.getCampDailyReportDetails(datasource, (String) session.getAttribute("districtId"));

            }

            request.setAttribute("campDataList", campDataList);

            if (disabilityList != null) {
                disabilityList = campDailyReportService.getDisabilityDetails(datasource);
                campDailyReportForm.setDisabilityList(disabilityList);
            }

            if ("submitValues".equals(campDailyReportForm.getMode())) {

                i = campDailyReportService.insertPwdAssessementDetails(datasource, campDailyReportForm, (String) session.getAttribute("districtId"), loginID, systemIP);
                if (i != 0) {

                    request.setAttribute("msg", "Inserted SuccessFully");
                    campDailyReportForm.setCampdate("");
                    campDailyReportForm.setDisabiltyId("0");
                    campDailyReportForm.setPwdAssessed("");
                    campDailyReportForm.setPwdAttended("");
                    campDailyReportForm.setDoctorsCount("");
                    campDailyReportForm.setCampData("off");
                } else {
                    request.setAttribute("msg", "Eror in Inserting");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }


        return mapping.findForward(target);
    }
}
