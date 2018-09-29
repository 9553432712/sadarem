/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.HearingImpairmentDto;
import org.bf.disability.service.HearingImpairmentService;
import org.bf.disability.servicefactory.HearingImpairmentServiceFactory;
import javax.sql.DataSource;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.HearingImpairmentDao;
import org.bf.disability.form.HearingImpairmentActionForm;

/**
 *
 * @author 490058
 */
public class HearingGraphforPersoncode extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private String target = "success";
    private ActionMessages actionMessages;

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
            throws SADAREMDBException, SQLException {

        DataSource ds = null;
        String person_code = null;
        //DataSource ds=getDataSource(request);
        HttpSession session = request.getSession();
        HearingImpairmentActionForm hearingform = (HearingImpairmentActionForm) form;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }



            person_code = (String) request.getParameter("pensioncode");

            if (person_code == null) {
                target = "success";
            } else {
                request.setAttribute("pid", person_code);
                String print = (String) request.getParameter("print");
                CommonDetails commondetails = new CommonDetails();

                String districtid = (String) session.getAttribute("districtId");
                boolean districtLevelAccessFlag = commondetails.checkDistrictFlag(person_code, districtid);
                if (districtLevelAccessFlag) {
                    hearingform.setLeftear1000("");
                    hearingform.setLeftear2000("");
                    hearingform.setLeftear250("");
                    hearingform.setLeftear4000("");
                    hearingform.setLeftear500("");
                    hearingform.setLeftear8000("");
                    hearingform.setRightear1000("");
                    hearingform.setRightear2000("");
                    hearingform.setRightear250("");
                    hearingform.setRightear500("");
                    hearingform.setRightear8000("");
                    hearingform.setSpeechaudiometryleftear_pta("");
                    hearingform.setSpeechaudiometryrightear_pta("");

                    HearingImpairmentService hearingservice = HearingImpairmentServiceFactory.getHearingServiceImpl();
                    HearingImpairmentDto hearingdto = new HearingImpairmentDto();

                    HearingImpairmentDao dao = new HearingImpairmentDao();
                    hearingdto = dao.getHearingDetailsforprint(ds, person_code);
                    if (hearingdto != null) {
                        hearingdto = dao.Checknulls(hearingdto);
                        request.setAttribute("data", hearingdto);
                        hearingform.setLeftear1000(hearingdto.getLeftear1000());
                        hearingform.setLeftear2000(hearingdto.getLeftear2000());
                        hearingform.setLeftear250(hearingdto.getLeftear250());
                        hearingform.setLeftear4000(hearingdto.getLeftear4000());
                        hearingform.setLeftear500(hearingdto.getLeftear500());
                        hearingform.setLeftear8000(hearingdto.getRightear8000());
                        hearingform.setRightear1000(hearingdto.getLeftear1000());
                        hearingform.setRightear2000(hearingdto.getRightear2000());
                        hearingform.setRightear250(hearingdto.getLeftear250());
                        hearingform.setRightear500(hearingdto.getRightear500());
                        hearingform.setRightear8000(hearingdto.getRightear8000());


                        if (print != null && print.equalsIgnoreCase("print")) {
                            target = "print";
                        } else {
                            target = "graphdata";
                        }
                    } else {
                        target = "success";
                        request.setAttribute("msg", "NO DATA FOR PERSONCODE:::::" + person_code);
                    }



                } else {


                    request.setAttribute("msg", "<font color=\"red\"><B>Enter Valid PersonCode:::::</B></font>");
                    target = "success";
                }

            }


        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
}
