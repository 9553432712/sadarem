/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.ScheduleForAuForm;
import org.bf.disability.service.AppletAuthorityService;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;

/**
 *
 * @author 484898
 */
public class AppellateAuthorityScheduleReportAction extends org.apache.struts.action.Action {

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
        ScheduleForAuForm scheduleForm = (ScheduleForAuForm) form;
        DataSource ds = null;
       
        //  Connection con = null;
        ArrayList mandalList = new ArrayList();
        HttpSession session = request.getSession();
        String target = "success";
        ArrayList villageData = new ArrayList();
        ArrayList areaDetails = new ArrayList();
        String villageId = "0";
        String caste = "0";
        try {
 ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            //    con = DbManager.getConnection(ds);
            //    con.setAutoCommit(false);

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();

            AppletAuthorityService appealService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();

            //    FunctionalNeedReportDTO functionalNeedReportDTO = new FunctionalNeedReportDTO();

            if (session.getAttribute("districtId") != null && !session.getAttribute("districtId").equals("")) {
                mandalList = functionalNeedService.getMandals(ds, (String) session.getAttribute("districtId"),"");
                scheduleForm.setMandallist(mandalList);
            }

            if ("getVillages".equalsIgnoreCase(scheduleForm.getMode())) {
                villageData = appealService.getScheduleData(ds, session.getAttribute("districtId").toString(), request.getParameter("mandal_id"));

                if (villageData.size() > 0) {
                    request.setAttribute("scheduleData", villageData);
                } else {
                    request.setAttribute("msg", "No Data Found");
                }
            } else if ("print".equals(request.getParameter("mode"))) {
                areaDetails = functionalNeedService.getAreaDetails(ds, session.getAttribute("districtId").toString(), request.getParameter("mandalId"), villageId, caste);
                request.setAttribute("areaDetails", areaDetails);
                villageData = appealService.getScheduleData(ds, session.getAttribute("districtId").toString(), request.getParameter("mandalId"));
                request.setAttribute("scheduleData", villageData);
                target = "print";
            } else if ("excel".equals(request.getParameter("mode"))) {
                areaDetails = functionalNeedService.getAreaDetails(ds, session.getAttribute("districtId").toString(), request.getParameter("mandalId"), villageId, caste);
                request.setAttribute("areaDetails", areaDetails);
                villageData = appealService.getScheduleData(ds, session.getAttribute("districtId").toString(), request.getParameter("mandalId"));
                request.setAttribute("scheduleData", villageData);
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
