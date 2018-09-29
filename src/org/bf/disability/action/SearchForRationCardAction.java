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
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.SearchForRationCardForm;
import org.bf.disability.service.SearchForRationCardservice;
import org.bf.disability.servicefactory.SearchForRationCardserviceFactory;

/**
 *
 * @author 525483
 */
public class SearchForRationCardAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String success = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward(success);
    }

    public ActionForward getrationCrdNo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SearchForRationCardForm searchRationCardForm = (SearchForRationCardForm) form;
        HttpSession session = request.getSession();
        DataSource ds = null;
        ArrayList getRationCardList = new ArrayList();
        boolean districtLevelAccessFlag = false;

        String districtid = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            CommonDetails commondetails = new CommonDetails();
            /*districtid = (String) session.getAttribute("districtId");
            if (searchRationCardForm.getRationCard().equals("1")) {
                districtLevelAccessFlag = commondetails.checkDistrictRationcardFlag(searchRationCardForm.getTextBox(), districtid);
            } else {
                districtLevelAccessFlag = commondetails.checkDistrictFlag(searchRationCardForm.getTextBox(), districtid);
            }

            if (districtLevelAccessFlag) {

                SearchForRationCardservice rationCardService = SearchForRationCardserviceFactory.getSearchForRationCardserviceImpl();
                getRationCardList = rationCardService.getRationCardDetails(searchRationCardForm, ds);
            } else {
                searchRationCardForm.setTextBox("");
                request.setAttribute("msg", "Invalid personcode");
            }*/
            SearchForRationCardservice rationCardService = SearchForRationCardserviceFactory.getSearchForRationCardserviceImpl();
            getRationCardList = rationCardService.getRationCardDetails(searchRationCardForm, ds);
            if(getRationCardList.size()>0)
            {
            	request.setAttribute("getRationCardList", getRationCardList);
            }
            else
            {
            	request.setAttribute("msg", "No Details Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return mapping.findForward(success);
    }
}
