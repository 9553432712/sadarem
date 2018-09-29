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
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.PartAForm;
import org.bf.disability.service.PartAService;
import org.bf.disability.servicefactory.PartAServiceFactory;

/**
 *
 * @author kavyav
 */
public class PersonalDetailsForPersonCodeAction extends DispatchAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartAForm partAForm = (PartAForm) form;
        PartADAO partADAO = new PartADAO();
        String target = "success";
        DataSource ds = null;
        String success = null;
        ArrayList alreadyRegistered = new ArrayList();
        boolean districtLevelAccessFlag = false;
        HttpSession session = request.getSession();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (partAForm.getPersoncode() != null) {
                CommonDetails commondetails = new CommonDetails();
                if (session.getAttribute("districtId") != null) {
                    partAForm.setDistrict_id((String) session.getAttribute("districtId"));
                }

                districtLevelAccessFlag = commondetails.checkDistrictFlag(partAForm.getPersoncode(), partAForm.getDistrict_id());

                if (districtLevelAccessFlag) {
                    alreadyRegistered = partADAO.checkAlreadyRegistered(ds, partAForm.getPersoncode());
                    if (alreadyRegistered.size() == 0) {
                        success = partADAO.checkEligibility(ds, partAForm.getPersoncode());
                        if (success == null) {
                            request.setAttribute("msg", "SADAREM ID not eligible for Part B or Reassessment or Temporary certificate");
                        } else {
                            request.setAttribute("result", success);
                            target = "next";
                        }
                    } else {
                        request.setAttribute("alreadyRegistered", alreadyRegistered);
                        target = "success";
                    }
                }
            } else {
                partAForm.setPersoncode("");
                //request.setAttribute("msg", "Invalid Sadarem Id");
            }
            request.setAttribute("personcode", partAForm.getPersoncode());
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "unspecified", "PersonalDetailsForPersonCodeAction", "Code");
            //////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "insertPersonalDetails");
        }
        return mapping.findForward(target);
    }

    public ActionForward setNoOfcampvalue(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        PartAForm partAForm = (PartAForm) form;
        String districtId = null;
        PartADAO partADAO = new PartADAO();
        try {
            request.setAttribute("noOfRows", request.getParameter("noofrows").toString());
            if (session.getAttribute("districtId") != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            DataSource ds = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            partAForm.setCampsList(partADAO.getMultipleCampDetails(ds, districtId));
            request.setAttribute("campsList", partADAO.getMultipleCampDetails(ds, districtId));
            request.setAttribute("personcode", partAForm.getPersoncode());
            // getExistingData(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("next");
    }

    public ActionForward insertCampScheduleDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartAForm partAForm = (PartAForm) form;
        PartADAO partADAO = new PartADAO();
        PartADTO partADTO = new PartADTO();
        String target = "success";
        DataSource ds = null;
        int success = 0;
        ArrayList aknowledgementDetails = new ArrayList();
        PartAService partAService = PartAServiceFactory.getPartAServiceImpl();

        try {
            HttpSession session = request.getSession();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("districtId") != null) {
                partAForm.setDistrict_id((String) session.getAttribute("districtId"));
            }
            request.setAttribute("result", partAForm.getCategory());
            BeanUtils.copyProperties(partADTO, partAForm);
            if (partAForm.getPersoncode() != null) {
                success = partADAO.insertCampScheduleDetails(partADTO, ds, request);
                if (success == 1) {
//                    aknowledgementDetails = partADAO.getPartaacknowledgementDetails(ds, personcode, partADTO);
//                    ArrayList aknowledgementDetailsmultiple = partADAO.getmultipleDisabilityDetails(ds, personcode, partADTO);
//                    if (aknowledgementDetailsmultiple.size() > 0) {
//                        request.setAttribute("aknowledgementDetailsmultiple", aknowledgementDetailsmultiple);
//                    }
//                    if (aknowledgementDetails.size() > 0) {
//                        request.setAttribute("aknowledgementDetails", aknowledgementDetails);
//                    } else {
//                        request.setAttribute("noData", "no Data Found");
//                    }
//                    target = "acknowledgement";
                    session.setAttribute("personcode", partAForm.getPersoncode());

                }

                request.setAttribute("personcode", partAForm.getPersoncode());

                if (request.getAttribute("dvalues") != null) {
                    request.setAttribute("dvaluess", "dvaluess");

                } else {
                    String message = null;
                    if (partADTO.getPhoneno() != null) {
                        String firstName = partADAO.getFirstNameForsms(ds, partAForm.getPersoncode(), partADTO);
                        if (firstName == null) {
                            firstName = "";
                        }
                        ArrayList dpmList = partADAO.getDpmuDetails(ds, partADTO.getDistrict_id(), partADTO.getPartaCampId(),partAForm.getPersoncode());
                        ArrayList campDetails = partADAO.getsmsCampDetials(ds, partAForm.getPersoncode(), partADTO);
                        if (dpmList.size() > 0 && campDetails.size() > 0) {

                            message = "Camp Scheduled For " + firstName + ",On " + campDetails.get(1) + ", At " + campDetails.get(0) + "."
                                    + "With SID As " + partAForm.getPersoncode() + ".Contact To Camp Incharge (" + dpmList.get(1) + ").";
                        } else if (campDetails.size() > 0) {
                            message = "Camp Scheduled For " + firstName + ",On " + campDetails.get(1) + ", At " + campDetails.get(0) + "."
                                    + "With SID As " + partAForm.getPersoncode() + ".Contact To NA";
                        } else {
                            message = "Camp Scheduled For " + firstName + ","
                                    + "For Sadarem Certificate With SADAREMID As " + partAForm.getPersoncode() + "";
                        }
//                        com.tpSMS.TpSendSMS.sendSMS(partADTO.getPhoneno(), message);
                        partADAO.insertSmsLogDetails(ds, partADTO, message, "Sent");
                    }
                }
            }
            target = "restrictPartA";
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCampScheduleDetails", "PersonalDetailsForPersonCodeAction", "Code");
            //////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "insertPersonalDetails");
        }
        return mapping.findForward(target);
    }
}
