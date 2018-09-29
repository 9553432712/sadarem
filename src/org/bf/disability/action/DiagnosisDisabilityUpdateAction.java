/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.DiagnosisDisabilityUpdateForm;
import org.bf.disability.service.DiagnosisDisabilityUpdateService;
import org.bf.disability.servicefactory.DiagnosisDisabilityUpdateServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 693461
 */
public class DiagnosisDisabilityUpdateAction extends DispatchAction {

    /* forward name="success" path="" */
    private String target = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward(target);
    }

    public ActionForward diagnosisDisabilityDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        boolean adminLevelAccessFlag = false;
        boolean districtLevelAccessFlag = false;
        CommonDetails commondetails = new CommonDetails();

        DiagnosisDisabilityUpdateForm diagnosisDisabilityUpdateForm = (DiagnosisDisabilityUpdateForm) form;
        String diagnosisDisabilityDetails = null;
        DataSource ds = null;
        String districtid = null;
        String personcode = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }



            if (diagnosisDisabilityUpdateForm.getPersonCode() != null) {

                int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
                adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);

                if (!adminLevelAccessFlag) {
                    districtid = (String) session.getAttribute("districtId");
                    districtLevelAccessFlag = commondetails.checkDistrictFlag(diagnosisDisabilityUpdateForm.getPersonCode(), districtid);
                }

            }
            if (districtLevelAccessFlag || adminLevelAccessFlag) {

                // DiagnosisDisabilityUpdateService diagnosisDisabilityUpdateService = DiagnosisDisabilityUpdateServiceFactory.getDiagnosisDisabilityUpdateServiceImpl();
                // diagnosisDisabilityDetails = diagnosisDisabilityUpdateService.getDiagnosisDisabilityDetails(ds, diagnosisDisabilityUpdateForm.getPersonCode());

                DiagnosisDisabilityUpdateService diagnosisDisabilityUpdateService = DiagnosisDisabilityUpdateServiceFactory.getDiagnosisDisabilityUpdateServiceImpl();
                diagnosisDisabilityDetails = diagnosisDisabilityUpdateService.getDiagnosisDisabilityDetails(ds, diagnosisDisabilityUpdateForm.getPersonCode());


                if (diagnosisDisabilityDetails != null) {
                    request.setAttribute("diagnosisDisabilityDetails", diagnosisDisabilityDetails);

                } else {
                    request.setAttribute("personCode", "personcode is not eligible to update!!");
                    diagnosisDisabilityUpdateForm.setPersonCode("");

                }

            } else {
                request.setAttribute("InvalidPersonCodeNumber", "PersonCode is Invalid Number!!");
                diagnosisDisabilityUpdateForm.setPersonCode("");
            }




        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    public ActionForward updateDiagnosisDisabilityDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DiagnosisDisabilityUpdateForm diagnosisDisabilityUpdateForm = (DiagnosisDisabilityUpdateForm) form;
        int updateDiagnosisDisabilityDetails = 0;
        DataSource ds = null;
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            DiagnosisDisabilityUpdateService diagnosisDisabilityUpdateService = DiagnosisDisabilityUpdateServiceFactory.getDiagnosisDisabilityUpdateServiceImpl();
            updateDiagnosisDisabilityDetails = diagnosisDisabilityUpdateService.updateDiagnosisDisabilityDetails(ds, diagnosisDisabilityUpdateForm.getDiagnosisDisability(), diagnosisDisabilityUpdateForm.getPersonCode());

            if (updateDiagnosisDisabilityDetails != 0) {
                request.setAttribute("msg", "Update SuccessFully");
                diagnosisDisabilityUpdateForm.setPersonCode("");
            } else {
                request.setAttribute("msg", "Error in Updating");
                diagnosisDisabilityUpdateForm.setPersonCode("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
}
