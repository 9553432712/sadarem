/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

/**
 *
 * @author 484898
 */
public class RachaBandaReportAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;

        DataSource ds = null;


        String target = "success";
        ArrayList rachaBandaPersonalDetails = new ArrayList();
        String district_id = null;
        HttpSession session = request.getSession(true);
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            ArrayList districtList = new ArrayList();
            // Get District list


            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }


            if ("getReportDetails".equalsIgnoreCase(functionalNeedForm.getMode())) {
                ArrayList rachaBandaData = new ArrayList();
                rachaBandaData = functionalNeedService.getRachaBandaData(ds, functionalNeedForm.getDistrict_id());
                session.setAttribute("district", functionalNeedForm.getDistrict_id());
                if (rachaBandaData.size() > 0) {

                    request.setAttribute("rachaBandaData", rachaBandaData);
                    target = "success";
                } else {
                    request.setAttribute("msg", "No Data Found");
                    target = "success";
                }

            } else if ("getReportExcel".equalsIgnoreCase(request.getParameter("status"))) {
                request.setAttribute("names", request.getParameter("names"));
                target = "excel";
            } else if ("getReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                request.setAttribute("names", request.getParameter("names"));
                target = "print";
            } else if ("getpersonalDetails".equalsIgnoreCase(request.getParameter("status"))) {
                request.setAttribute("distCode", (String) request.getParameter("mID"));
                request.setAttribute("districtName", (String) request.getParameter("dID"));
                request.setAttribute("districtNameDesc", territoryService.getDistrictsName(ds,(String) request.getParameter("dID")));
                request.setAttribute("mandalName", (String) request.getParameter("mandalName"));
                rachaBandaPersonalDetails = functionalNeedService.getRachaBandaPersonalDetails(ds, (String) session.getAttribute("district"), (String) request.getParameter("mID"), (String) request.getParameter("modes"));
                // request.setAttribute("rachaBandaPersonalDetails", rachaBandaPersonalDetails);
                session.setAttribute("rachaBandaPersonalDetails", rachaBandaPersonalDetails);
            } else if ("getExcel".equalsIgnoreCase(request.getParameter("status"))) {
                request.setAttribute("districtName", (String) request.getParameter("distCodes"));
                target = "excel";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return mapping.findForward(target);
    }
}
