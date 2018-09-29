/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.SearchRequestStatusDTO;
import org.bf.disability.form.SearchRequestStatusForm;
import org.bf.disability.service.SearchRequestStatusService;
import org.bf.disability.servicefactory.SearchRequestStatusServiceFactory;

/**
 *
 * @author 310926
 */
public class SearchRequestStatusAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

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

        return mapping.findForward(SUCCESS);
    }

    public ActionForward getData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SearchRequestStatusForm searchRequestStatusForm = (SearchRequestStatusForm) form;
        SearchRequestStatusDTO searchRequestStatusDTO = new SearchRequestStatusDTO();

        HttpSession session = request.getSession();
        DataSource ds = null;
        ArrayList getDetailsList = new ArrayList();
        String target = null;

        String personCode = null;
        String type = null;
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            personCode = (String) request.getParameter("searchId");
            type = (String) request.getParameter("type");
            if (personCode != null && !personCode.equalsIgnoreCase("")) {
                searchRequestStatusDTO.setSadaremId(personCode);
            }
            if (type != null && !type.equalsIgnoreCase("")) {
                searchRequestStatusDTO.setType(type);
            }


            SearchRequestStatusService searchRequestStatusService = SearchRequestStatusServiceFactory.getSearchRequestStatusServiceImpl();
            //BeanUtils.copyProperties(searchRequestStatusDTO, searchRequestStatusForm);
            getDetailsList = searchRequestStatusService.getDetails(ds, searchRequestStatusDTO);

            if (getDetailsList.size() > 0) {
                target = "success";
                request.setAttribute("getDetailsList", getDetailsList);
            } else {

                request.setAttribute("msg", "Invalid Id");
                target = "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return mapping.findForward(target);
    }
}
