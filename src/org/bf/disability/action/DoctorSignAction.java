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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.DoctorSignDTO;
import org.bf.disability.form.DoctorSignForm;
import org.bf.disability.service.DoctorSignService;
import org.bf.disability.servicefactory.DoctorSignFactory;

/**
 *
 * @author 484898
 */
public class DoctorSignAction extends DispatchAction {

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
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DoctorSignService doctorService = null;
        DoctorSignDTO doctorSignDTO = null;
        DoctorSignForm doctorSignForm = (DoctorSignForm) form;
        HttpSession session = null;
        session = request.getSession(true);
        ArrayList pwdDeatils = null;
        DataSource ds = null;

        if (session.getAttribute("username") != null && session.getAttribute("username").toString().length() > 0) {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            doctorSignDTO = new DoctorSignDTO();
            BeanUtils.copyProperties(doctorSignDTO, doctorSignForm);
            doctorService = DoctorSignFactory.getDoctorSignImpl();
            doctorSignDTO.setDoctorRegNumber(session.getAttribute("username").toString());
            doctorSignDTO.setDistrictCode(session.getAttribute("districtId").toString());

            pwdDeatils = doctorService.getPWDDetails(ds, doctorSignDTO);
            if (pwdDeatils != null && pwdDeatils.size() > 0) {
                request.setAttribute("pwdDetails", pwdDeatils);
            } else {
                request.setAttribute("msg", "<font color=\"red\">PWD Details Not Available</font>");
            }
        }
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for encrypt the selected data
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward signData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DoctorSignForm doctorSignForm = (DoctorSignForm) form;
        DoctorSignService doctorService = null;
        DoctorSignDTO doctorSignDTO = null;
        DataSource ds = null;
        HttpSession session = null;
        int status = 0;

        if (doctorSignForm.getDoSign() != null && doctorSignForm.getDoSign().length > 0) {
            session = request.getSession(true);
            doctorSignDTO = new DoctorSignDTO();
            doctorSignDTO.setDoctorRegNumber(session.getAttribute("username").toString());
            doctorSignDTO.setSystemIp(request.getRemoteAddr());
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            doctorService = DoctorSignFactory.getDoctorSignImpl();
            BeanUtils.copyProperties(doctorSignDTO, doctorSignForm);
            status = doctorService.signData(ds, doctorSignDTO);

            if (status != 0) {
                request.setAttribute("msg", "<font color=\"green\">Selected Records Signed Successfully.</font>");
            } else {
                request.setAttribute("msg", "<font color=\"red\">Error :: Error While Sign the Data.</font>");
            }
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }
}
