/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

//import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.FilterCandidateForm;
import org.bf.disability.service.FilterCandidateService;
import org.bf.disability.servicefactory.FilterCandidateServiceFactory;

/**
 *
 * @author vijay
 */
public class FilterCandidateAction extends DispatchAction {

    /* forward name="success" path="" */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FilterCandidateForm filterCandidateForm = (FilterCandidateForm) form;
        DataSource ds = null;
        String target = "success";
        HttpSession session = request.getSession();

        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            ArrayList mandalList = new ArrayList();
            String district_id = (String) session.getAttribute("districtId");

            FilterCandidateService functionalNeedService = FilterCandidateServiceFactory.getFilterCandidateServiceImpl();

            // mandalList = functionalNeedService.getMandalDetails(ds, (String) session.getAttribute("districtId"));

            mandalList = functionalNeedService.getMandalDetails(ds, district_id);
            filterCandidateForm.setMandalList(mandalList);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return mapping.findForward(target);
    }

    public ActionForward getVillageDataDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        FilterCandidateForm filterCandidateForm = (FilterCandidateForm) form;
        DataSource ds = null;
        HttpSession session = request.getSession();

        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            ArrayList villageList = new ArrayList();

            FilterCandidateService functionalNeedService = FilterCandidateServiceFactory.getFilterCandidateServiceImpl();

            String district_id = (String) session.getAttribute("districtId");

            villageList = functionalNeedService.getVillageDetails(ds, district_id, filterCandidateForm.getMandal_id());
            filterCandidateForm.setVillageList(villageList);


            unspecified(mapping, form, request, response);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return mapping.findForward(target);
    }

    public ActionForward getRationCardDuplicateData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FilterCandidateForm filterCandidateForm = (FilterCandidateForm) form;
        HttpSession session = request.getSession();
        DataSource ds = null;
        String districtId = null;
        String mandal_id = null;
        String village_id = null;
        String habCode = null;

        String target = "success";
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }



            request.setAttribute("ds", ds);

            ArrayList duplicateRationCardDetails = new ArrayList();

            FilterCandidateService functionalNeedService = FilterCandidateServiceFactory.getFilterCandidateServiceImpl();

            districtId = (String) session.getAttribute("districtId");
            // districtId = "10";

            if (request.getParameter("mandal_id") == null) {
                mandal_id = "0";
            } else {
                mandal_id = request.getParameter("mandal_id");
            }

            if (request.getParameter("village_id") == null) {
                village_id = "0";
            } else {
                village_id = request.getParameter("village_id");
            }


            duplicateRationCardDetails = functionalNeedService.getReportRationCardDuplicateDetails(ds, districtId, mandal_id, village_id);

            request.setAttribute("duplicateRationCardDetails", duplicateRationCardDetails);
            if (duplicateRationCardDetails.isEmpty()) {
                request.setAttribute("duplicateRationMsgs", "No Data Found");
            }

            getVillageDataDetails(mapping, form, request, response);
            unspecified(mapping, form, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return mapping.findForward(target);
    }

    public ActionForward updateDuplicateData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FilterCandidateForm filterCandidateForm = (FilterCandidateForm) form;
        DataSource ds = null;

        HttpSession session = request.getSession();
        String target = "success";
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            int updateDuplicateDetails = 0;
            int updatePersonDetails = 0;

            FilterCandidateService functionalNeedService = FilterCandidateServiceFactory.getFilterCandidateServiceImpl();
            String district = (String) session.getAttribute("districtId");
            // district = "10";


            String pensionId[] = filterCandidateForm.getPensionids();
            String pensionCardDuplicate[] = filterCandidateForm.getPensionCardDuplicate();


            for (int i = 0, j = 0; i < pensionId.length && j < pensionCardDuplicate.length; i++, j++) {
                if (!pensionId[i].equals("0") && !pensionCardDuplicate[j].equals("0")) {
                    updateDuplicateDetails = functionalNeedService.getUpdateDuplicateDetails(ds, pensionId[i], pensionCardDuplicate[j], district);
                }
            }


            if (updateDuplicateDetails > 0) {
                request.setAttribute("msg", "Updated SuccessFully");
                filterCandidateForm.setPensionids(null);
                filterCandidateForm.setPensionCardDuplicate(null);

            } else {
                request.setAttribute("msg", "Error in Updating");
                filterCandidateForm.setPensionids(null);
                filterCandidateForm.setPensionCardDuplicate(null);
            }

            unspecified(mapping, form, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return mapping.findForward(target);
    }

    public ActionForward photoUploadDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String target = "photoView";
        HttpSession session = request.getSession();

        FilterCandidateForm filterCandidateForm = (FilterCandidateForm) form;
        CommonDetails commonDetails = new CommonDetails();

        FilterCandidateService functionalNeedService = FilterCandidateServiceFactory.getFilterCandidateServiceImpl();

        String personcode = null;
        String districtName = null;
        String districtId = null;
        DataSource ds = null;


        districtId = (String) session.getAttribute("districtId");
        //  districtId = "10";sadaremID

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            districtName = functionalNeedService.getDistrictNameDetails(ds, districtId);
            session.setAttribute("districtname", districtName);
            commonDetails.copyPhotoDtoRelativePath((String) request.getParameter("sadaremID"), districtName, request, getServlet().getServletContext().getRealPath("/"));


        } catch (Exception e) {

            e.printStackTrace();
        }
        return mapping.findForward("photoView");
    }
}
