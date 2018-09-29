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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.ReassessmentFiltringForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.service.ReassessmentFiltringService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.servicefactory.ReassessmentFiltringFactory;

/**
 *
 * @author 490058
 */
public class ReassessmentFiltringAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        ReassessmentFiltringForm reassessmentForm = (ReassessmentFiltringForm) form;
        String target = "success";
        ArrayList mandalList = null;
        ArrayList villageList = null;
        ArrayList habitationList = null;

        ArrayList reassessmentDetails = null;
        DataSource ds = null;
        HttpSession session = request.getSession();
        FunctionalNeedReportService functionalNeedService = FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        ReassessmentFiltringService reassessmentFiltring = ReassessmentFiltringFactory.getReassessmentFiltringImpl();

        int deleteRepords = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            if (session.getAttribute("districtId") != null) {
                mandalList = functionalNeedService.getMandals(ds, session.getAttribute("districtId").toString(),"");
                reassessmentForm.setMandal_list(mandalList);
            }


            if ("getReport".equalsIgnoreCase(reassessmentForm.getMode())) {
                /** Getting the Reassessment Details */
                reassessmentDetails = reassessmentFiltring.getReassessmentDetails(ds, session.getAttribute("districtId").toString(), reassessmentForm.getMandal_id(), reassessmentForm.getVillage_id(), reassessmentForm.getHabitation_id());
                if (reassessmentDetails.size() > 0) {
                    villageList = functionalNeedService.getVillageAll(ds, session.getAttribute("districtId").toString(), reassessmentForm.getMandal_id());
                    reassessmentForm.setVillage_list(villageList);
                    habitationList = reassessmentFiltring.getHabitations(ds, session.getAttribute("districtId").toString(), reassessmentForm.getMandal_id(), reassessmentForm.getVillage_id());
                    reassessmentForm.setHabitation_list(habitationList);
                    request.setAttribute("reassessmentDetails", reassessmentDetails);
                } else {
                    villageList = functionalNeedService.getVillageAll(ds, session.getAttribute("districtId").toString(), reassessmentForm.getMandal_id());
                    reassessmentForm.setVillage_list(villageList);
                    habitationList = reassessmentFiltring.getHabitations(ds, session.getAttribute("districtId").toString(), reassessmentForm.getMandal_id(), reassessmentForm.getVillage_id());
                    reassessmentForm.setHabitation_list(habitationList);
                    request.setAttribute("msg", "No Details Found");
                }
            }


            if ("deleteRecords".equalsIgnoreCase(reassessmentForm.getMode())) {
                if (reassessmentForm.getCheckedCandidates() != null && reassessmentForm.getCheckedCandidates().length > 0) {
                    for (int i = 0; i < reassessmentForm.getCheckedCandidates().length; i++) {
                        deleteRepords = reassessmentFiltring.updateFlagForPersons(ds, reassessmentForm.getCheckedCandidates()[i]);
                    }
                }

                if (deleteRepords > 0) {
                    villageList = functionalNeedService.getVillageAll(ds, session.getAttribute("districtId").toString(), reassessmentForm.getMandal_id());
                    reassessmentForm.setVillage_list(villageList);
                    habitationList = reassessmentFiltring.getHabitations(ds, session.getAttribute("districtId").toString(), reassessmentForm.getMandal_id(), reassessmentForm.getVillage_id());
                    reassessmentForm.setHabitation_list(habitationList);
                    request.setAttribute("msg", reassessmentForm.getCheckedCandidates().length + " Record(s) is Deleted ");

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return mapping.findForward(target);
    }
}
