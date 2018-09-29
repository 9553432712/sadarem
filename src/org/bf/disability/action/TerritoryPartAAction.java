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
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.service.FilterCandidateService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.FilterCandidateServiceFactory;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

/**
 *
 * @author 740996
 */
public class TerritoryPartAAction extends DispatchAction {

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

       
        HttpSession session = null;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        String districtid = null;
        String districtName = null;
        session = request.getSession(true);
        DataSource ds = null;
        FilterCandidateService functionalNeedService = FilterCandidateServiceFactory.getFilterCandidateServiceImpl();




        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            
            if (session.getAttribute("districtId") != null) {
                districtid = (String) session.getAttribute("districtId");
                request.setAttribute("districtidData", districtid);
            }
            districtName = functionalNeedService.getDistrictNameDetails(ds, districtid);
            session.setAttribute("districtNameData", districtName);
       
            if(request.getParameter("personCodeFlag")!=null){
            request.setAttribute("restrictDataFlag", request.getParameter("personCodeFlag"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
   
        return mapping.findForward("success");

    }
}
