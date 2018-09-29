package org.bf.disability.action;

import java.util.ArrayList;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.bean.PopulateCalculationsLinksBean;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ShowCalculationsDAO;

public class ShowCalculationsAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String target = null;
        String personCode = null;
        DataSource dataSource = null;
        ArrayList totalpercentagelist = null;



        HttpSession session = request.getSession(true);
        ShowCalculationsDAO showCalculationsDAO = new ShowCalculationsDAO();
        try {
            dataSource = getDataSource(request);
            if (dataSource == null || "null".equals(dataSource)) {
                dataSource = JNDIDataSource.getConnection();
            }

            if (request.getParameter("personCodeFlag") != null) {
                personCode = request.getParameter("personCodeFlag");
                session.setAttribute("personcode", personCode);
                session.setAttribute("backbuttonflag", "hide");
            } else {
                personCode = (String) session.getAttribute("personcode");
                if (request.getParameter("backbuttonflagflow") != null) {
                    session.setAttribute("backbuttonflag", "show");
                } else if ((String) session.getAttribute("backbuttonflag") == "hide") {
                } else {
                    session.setAttribute("backbuttonflag", "show");
                }
            }
            if (request.getParameter("personcode") != null) {
                personCode = request.getParameter("personcode");

                session.setAttribute("personcode", personCode);
                session.setAttribute("backbuttonflag", "hide");
            }
            boolean checkforPersonCode = showCalculationsDAO.checkPersoncode(personCode, dataSource);

            if (checkforPersonCode) {
                PopulateCalculationsLinksBean calBean = showCalculationsDAO.decideTypeofDisability(dataSource, personCode);
                if (calBean.getHi() == "true" || calBean.getMi() == "true" || calBean.getMr() == "true" || calBean.getOh() == "true" || calBean.getVi() == "true") {
                    calBean.setToaldisability("true");
                } else {
                    calBean.setToaldisability("false");
                }

                request.setAttribute("calBean", calBean);
                String doctor = request.getParameter("doctor");
                 doctor = request.getParameter("doctor");
                request.setAttribute("doctor", doctor);
                if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                    target = "doctorsuccess";
                } else {
                    target = "showcalculationslink";
                }
            } else {
                target = "nopersoncode";
            }

            if (request.getParameter("decisionparameter") != null) {
                String decisionparameter = request.getParameter("decisionparameter");
                request.setAttribute("decisionparameter", decisionparameter);
                if (request.getParameter("decisionparameter").equals("true")) {
                    String name = showCalculationsDAO.getPersonName(personCode, dataSource);
                    session.setAttribute("Name", name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }
}
