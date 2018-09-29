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
import org.bf.disability.form.PensionForReportSearchForm;
import org.bf.disability.service.PensionForSearchReportService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.PensionForReportSearchServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

/**
 *
 * @author 484898
 */
public class PensionForSearchReportAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
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

        DataSource ds = null;
        ArrayList districtList = new ArrayList();
        ArrayList mandallist = new ArrayList();
        ArrayList villagelist = new ArrayList();
        ArrayList searchList = new ArrayList();

        PensionForReportSearchForm pensionForReportSearchForm = (PensionForReportSearchForm) form;
        PensionForSearchReportService pensionForService = PensionForReportSearchServiceFactory.getpensionForSearchReportServiceImpl();
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            // Get District list





            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                pensionForReportSearchForm.setDistrictList(districtList);
            }

            if (pensionForReportSearchForm.getDistrict_id() != null) {
                if (pensionForReportSearchForm.getDistrict_id() != "0" && !pensionForReportSearchForm.getDistrict_id().equals("0")) {
                    mandallist = pensionForService.getMandals(ds, pensionForReportSearchForm.getDistrict_id());
                    pensionForReportSearchForm.setMandallist(mandallist);
                }
            }
            if (pensionForReportSearchForm.getMandal_id() != null) {
                if (pensionForReportSearchForm.getMandal_id() != "0" && !pensionForReportSearchForm.getMandal_id().equals("0")) {
                    villagelist = pensionForService.getVillageAll(ds, pensionForReportSearchForm.getDistrict_id(), pensionForReportSearchForm.getMandal_id());
                    pensionForReportSearchForm.setVillagelist(villagelist);
                    pensionForReportSearchForm.setMandallist(mandallist);
                }
            }

            if ("getResults".equalsIgnoreCase(pensionForReportSearchForm.getMode())) {


                if (pensionForReportSearchForm.getPersoncode() != null && !pensionForReportSearchForm.getPersoncode().equals("")
                        && !pensionForReportSearchForm.getDistrict_id().equals("0") && !pensionForReportSearchForm.getDistrict_id().equals("")) {
                    searchList = pensionForService.getPhaseSearchDetails(ds, pensionForReportSearchForm.getPersoncode(), pensionForReportSearchForm.getSadaremId(), pensionForReportSearchForm.getDistrict_id(), pensionForReportSearchForm.getMandal_id(), pensionForReportSearchForm.getVillage_id());
                } else if (pensionForReportSearchForm.getSadaremId() != null && !pensionForReportSearchForm.getSadaremId().equals("")) {
                    searchList = pensionForService.getPhaseSearchDetails(ds, pensionForReportSearchForm.getPersoncode(), pensionForReportSearchForm.getSadaremId(), pensionForReportSearchForm.getDistrict_id(), pensionForReportSearchForm.getMandal_id(), pensionForReportSearchForm.getVillage_id());
                }
                if (searchList.size() > 0) {
                    request.setAttribute("searchListData", searchList);

                } else {
                    request.setAttribute("msg", "No Data Found");
                }

            }
            request.setAttribute("searchList", searchList);



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
