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
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.SQLException;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.AppletAuthorityForPartBForm;
import org.bf.disability.service.AppletAuthorityService;

import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;

/**
 *
 * @author 484898
 */
public class AppealPrintsAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        AppletAuthorityForPartBForm partAForm = (AppletAuthorityForPartBForm) form;
        DataSource ds = null;

        String target = "target";
        HttpSession session = request.getSession();
        ArrayList personalDetails = new ArrayList();
        String districtName = null;
        TerritoryDAO terrority = new TerritoryDAO();
        String personStatus = null;
        PartADAO dao = new PartADAO();
        PartADTO dto = new PartADTO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();



            String selectflow = partAForm.getSelectFlow();
            selectflow = request.getParameter("selectFlow");
            if (selectflow != null) {
                session.setAttribute("selectFlow", selectflow);
            }
            String catid = request.getParameter("catId");
            if (catid != null && (catid.equalsIgnoreCase("2") || catid.equalsIgnoreCase("3"))) {
                partAForm.setCatId(catid);
                session.setAttribute("catid", catid);
            }

            if ("getValuesForPrints".equalsIgnoreCase(partAForm.getMode())) {

                if (partAForm.getSadaremCode() != null) {

                    if (session.getAttribute("selectFlow") != null) {
                        selectflow = session.getAttribute("selectFlow").toString();
                    }
                    personStatus = appletAuthorityService.getPersonStatus(ds, partAForm.getSadaremCode());

                    districtName = terrority.getDistrictsName(ds, (String) session.getAttribute("districtId"));
                    CommonDetails commonDetails = new CommonDetails();
                    String url = getServlet().getServletContext().getRealPath("/");
                    commonDetails.copyPhotoDtoRelativePath(partAForm.getSadaremCode(), districtName, request, url);
                    session.setAttribute("personcode", partAForm.getSadaremCode());

                    session.setAttribute("personstatus", personStatus);


                    if (selectflow != null && selectflow.equalsIgnoreCase("Physical")) {
                        personalDetails = appletAuthorityService.getPhysicalReqDetailsPrints(ds, partAForm.getSadaremCode(), request);


                        dto.setPersoncode(partAForm.getSadaremCode());
                        dto = dao.setPreviousPhysicalRequirements(ds, dto);

                        BeanUtils.copyProperties(partAForm, dto);                     
                        // String disability = dao.getDisabilityIdforCertificate(ds, partAForm.getSadaremCode());
                     
                        if (session.getAttribute("dis") != null && !session.getAttribute("dis").equals("")) {
                            partAForm.setDisabilityId(Integer.parseInt(session.getAttribute("dis").toString()));
                               String disability = session.getAttribute("dis").toString();
                        }
                    } else {
                        if (session.getAttribute("catid") != null && (session.getAttribute("catid").toString().equalsIgnoreCase("3") || session.getAttribute("catid").toString().equalsIgnoreCase("2"))) {
                            personalDetails = appletAuthorityService.getAppletAuthorityDetailsPrints(ds, partAForm.getSadaremCode(), request);
                        }
                    }
                    //  session.setAttribute("disability", "1");
                    if (personalDetails.size() > 0) {

                        if (selectflow != null && selectflow.equalsIgnoreCase("Physical")) {

                            request.setAttribute("personDetails", personalDetails);
                            target = "targetPrints";
                        } else //if(selectflow!=null && selectflow.equalsIgnoreCase("Physical") )
                        {
                            request.setAttribute("personDetails", personalDetails);
                            target = "targetPrintsapp";
                        }



                    } else {

                        request.setAttribute("msg", "You are not Authorized / No Data Found");
                        target = "back";
                    }
                } else {
                    target = "back";
                }

            } else if ("goForPartB".equalsIgnoreCase(partAForm.getMode())) {
                String status = (String) session.getAttribute("personstatus");
                if (status.equals("Eligible")) {
                    target = "targetPrintsGo";
                } else {
                    target = "Rejected";
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
     
        return mapping.findForward(target);
    }
}
