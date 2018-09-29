/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.PartAForm;
import org.bf.disability.service.AppletAuthorityService;
import org.bf.disability.service.PartAService;
import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;
import org.bf.disability.servicefactory.PartAServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author s_bonthi
 */
public class GetExistingDataAction extends BaseDispatchAction {

    public ActionForward getExistingData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        ActionMessages actionMessages = null;
        String districtId = null;
        String pensionCode = null;
        String target = "success";
        PartADTO partADTO = null;
        String rationCardNumber = null;
        DataSource ds = null;
        String powerCutIds = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            PartAForm partAForm = (PartAForm) form;
            HttpSession session = request.getSession(true);
            districtId = (String) session.getAttribute("districtId");
            pensionCode = (String) request.getParameter("pensioncode");
            session.setAttribute("pensionIdDis", pensionCode);




            CommonDetails commondetails = new CommonDetails();
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            boolean adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);

            /* Dont delete data for login role id is 5 */
            if (!adminLevelAccessFlag) {
                /** To Release the PowerCut Ids */
                AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
                /** Releasing the powercut problems */
                powerCutIds = appletAuthorityService.getPowerCutIds(ds, "Completed", pensionCode, request);
                if (powerCutIds != null && powerCutIds.length() > 0) {
                    appletAuthorityService.powerCutIds(ds, powerCutIds, request);
                }
            }

            /** end */
            if (request.getParameter("restrictPartA") != null) {
                session.setAttribute("restrictPartA", request.getParameter("restrictPartA"));
            }


            partADTO = new PartADTO();
            PartAService partAService = PartAServiceFactory.getPartAServiceImpl();

            /* Added by Rekha */
            String mode = (String) request.getParameter("mode");

            if (mode != null && mode.equalsIgnoreCase("Data")) {
                partAForm.setMode("");

                ArrayList data = partAService.getAllDataBasedonRationCard(ds, pensionCode, districtId, request);

                if (data != null) {
                    request.setAttribute("pensionnumber", pensionCode);
                    request.setAttribute("Pensiondata", data);
                    return mapping.findForward("RationcardPensionersData");
                    // request.setAttribute("pensionid", pensionCode);
                } else if (data == null) {
                    request.setAttribute("msg", "This pensionid is not allowed.");
                    return mapping.findForward("RationcardPensionersData");
                }
            }
            /* END */
            String aadharNumber = PartADAO.checkAdharCardNumberWithPensionNo(ds, pensionCode, "PensionCode", districtId);
            if (aadharNumber.equals("Invalid")) {
                partADTO = partAService.getExistingPensionDetails(pensionCode, districtId, ds, request);

            if (partADTO != null) {
                if (partADTO.getDeathcase() != null && CommonConstants.DEATH_CASE.equals(partADTO.getDeathcase())) {
                    request.setAttribute("msg", "Pensioner Deleted due to " + partADTO.getReasonforstatus());
                    target = "invalid";
                } else if (partADTO.getPersoncode() != null && !"".equals(partADTO.getPersoncode())) {
                    request.setAttribute("existingpersoncode", partADTO.getPersoncode());
                    request.setAttribute("pensionnumber", pensionCode);
                    target = "existingpensionnumber";
                } else {
                    rationCardNumber = partADTO.getCard();
                    if (rationCardNumber != null && !"".equals(rationCardNumber)) {
                        if (rationCardNumber.contains("WAP")) {
                            partADTO.setRtype("1");
                        } else if (rationCardNumber.contains("PAP")) {
                            partADTO.setRtype("2");
                        } else if (rationCardNumber.contains("AAY")) {
                            partADTO.setRtype("3");
                        } else if (rationCardNumber.contains("AAp")) {
                            partADTO.setRtype("4");
                        } else if (rationCardNumber.contains("YAP")) {
                            partADTO.setRtype("5");
                        } else if (rationCardNumber.contains("TAP")) {
                            partADTO.setRtype("6");
                        } else if (rationCardNumber.contains("RAP")) {
                            partADTO.setRtype("7");
                        }
                        else if (rationCardNumber.contains("WAD")) {
                            partADTO.setRtype("8");
                        }
                    }
                    //addded by kavya

                    if (partADTO.getCard() != null && partADTO.getCard().trim().length() > 0) {
                        request.setAttribute("RationReadOnly", "true");
                    }
                    if (partADTO.getRationCardSlno() != null && partADTO.getRationCardSlno().trim().length() > 0) {
                        request.setAttribute("SlNoReadOnly", "true");
                    }

                    //ended

                    if (partADTO != null && !"".equals(partADTO)) {
                        if (partADTO.getDistrict() != null && !"".equals(partADTO.getDistrict())) {
                            request.setAttribute("district_name", partADTO.getDistrict());
                        }
                        if (partADTO.getMandal() != null && !"".equals(partADTO.getMandal())) {
                            request.setAttribute("mandal_name", partADTO.getMandal());
                        }
                        if (partADTO.getTownVillage() != null && !"".equals(partADTO.getTownVillage())) {
                            request.setAttribute("village_name", partADTO.getTownVillage());
                        }
                        if (partADTO.getHabitation() != null && !"".equals(partADTO.getHabitation())) {
                            request.setAttribute("habitation_name", partADTO.getHabitation());
                        }
                        if (partADTO.getDistrtictid() != null && !"".equals(partADTO.getDistrtictid())) {
                            request.setAttribute("district_id", partADTO.getDistrtictid());
                        }
                        if (partADTO.getMandalid() != null && !"".equals(partADTO.getMandalid())) {
                            request.setAttribute("mandal_id", partADTO.getMandalid());
                        }
                        if (partADTO.getVillageid() != null && !"".equals(partADTO.getVillageid())) {
                            request.setAttribute("village_id", partADTO.getVillageid());
                        }
                        if (partADTO.getHabitationid() != null && !"".equals(partADTO.getHabitationid())) {
                            request.setAttribute("habitation_id", partADTO.getHabitationid());
                        }
                        if (partADTO.getAssemblyid() != null && !"".equals(partADTO.getAssemblyid())) {
                            request.setAttribute("assembly_id", partADTO.getAssemblyid());
                        }
                        if (partADTO.getPanchayatiid() != null && !"".equals(partADTO.getPanchayatiid())) {
                            request.setAttribute("panchayat_id", partADTO.getPanchayatiid());
                        }
                        if (partADTO.getHabCode() != null && !"".equals(partADTO.getHabCode().trim())) {
                            request.setAttribute("habcode", partADTO.getHabCode().trim());
                        }
                        if (partADTO.getHouseno() != null && !"".equals(partADTO.getHouseno().trim())) {
                            request.setAttribute("houseNo", partADTO.getHouseno().trim());
                        }
                        request.setAttribute("reasonforstatus", partADTO.getReasonforstatus());

                        BeanUtils.copyProperties(partAForm, partADTO);
                    }
                    request.setAttribute("roleId", session.getAttribute("roleId"));
                    request.setAttribute("surname", partADTO.getSurname());
                    request.setAttribute("name", partADTO.getFirstname());
                    request.setAttribute("relName", partADTO.getGsurname());
                    target = "success";
                }
            } else {
                request.setAttribute("msg", "Please Enter Valid Pension Number");
                target = "invalid";
            }
            } else {

                request.setAttribute("alreadyEnterPersonId", PartADAO.checkAdharCardNumberWithPensionNo(ds, aadharNumber, "AdharCardNumber", ""));
                request.setAttribute("aadharNoPersonCode", "aadharNoPersonCode");
                request.setAttribute("aadharNoForDisplay", aadharNumber);
                target = "alreadyEnteredDataDetails";

                // request.setAttribute("msg", "Alredy Data Entered With This Pension Number");
                // target = "invalid";

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

    public ActionForward getEmployeeDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "success";
        String districtId = null;
        DataSource ds = null;
        String employeeId = null;
        PartADTO partADTO = null;
        ActionMessages actionMessages = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            PartAForm partAForm = (PartAForm) form;

            HttpSession session = request.getSession(true);
            districtId = (String) session.getAttribute("districtId");
            employeeId = (String) request.getParameter("employeeid");
            PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
            partADTO = new PartADTO();
            partADTO = partAService.getEmployeeDetails(employeeId, districtId, ds);
            //addded by kavya

            if (partADTO.getCard() != null && partADTO.getCard().trim().length() > 0) {
                request.setAttribute("RationReadOnly", "true");
            }
            if (partADTO.getRationCardSlno() != null && partADTO.getRationCardSlno().trim().length() > 0) {
                request.setAttribute("SlNoReadOnly", "true");
            }
            //ended
            if ("B".equals(partADTO.getStatus())) {
                target = "invalid";
                request.setAttribute("msg", "Entered Employee " + partADTO.getReasonforstatus());
            } else {
                BeanUtils.copyProperties(partAForm, partADTO);
            }
            request.setAttribute("employeeId", employeeId);

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

    public ActionForward setNoOfcampvalueAction(ActionMapping mapping, ActionForm form,
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
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String habitation_id = (String) request.getParameter("habitation_id");
            String panchayat_id = (String) request.getParameter("panchayat_id");
            String assembly_id = (String) request.getParameter("assembly_id");
            String habcode = (String) request.getParameter("habitation_id");
            request.setAttribute("pensionnumber", partAForm.getPensioncardno());
            request.setAttribute("district_id", district_id);
            request.setAttribute("mandal_id", mandal_id);
            request.setAttribute("village_id", village_id);
            request.setAttribute("habitation_id", habitation_id);
            request.setAttribute("panchayat_id", panchayat_id);
            request.setAttribute("assembly_id", assembly_id);
            request.setAttribute("habcode", habcode);
            request.setAttribute("surnameenglish", request.getParameter("surnameenglish"));
            DataSource ds = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            partAForm.setCampsList(partADAO.getMultipleCampDetails(ds, districtId));
            request.setAttribute("campsList", partADAO.getMultipleCampDetails(ds, districtId));
            // getExistingData(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    public ActionForward setNoOfcampvalueActionnon(ActionMapping mapping, ActionForm form,
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
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String habitation_id = (String) request.getParameter("habitation_id");
            String panchayat_id = (String) request.getParameter("panchayat_id");
            String assembly_id = (String) request.getParameter("assembly_id");
            String habcode = (String) request.getParameter("habitation_id");
            request.setAttribute("pensionnumber", partAForm.getPensioncardno());
            request.setAttribute("district_id", districtId);
            request.setAttribute("mandal_id", mandal_id);
            request.setAttribute("village_id", village_id);
            request.setAttribute("habitation_id", habitation_id);
            request.setAttribute("panchayat_id", panchayat_id);
            request.setAttribute("assembly_id", assembly_id);
            request.setAttribute("habcode", habcode);
            request.setAttribute("surnameenglish", request.getParameter("surnameenglish"));
            DataSource ds = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            partAForm.setCampsList(partADAO.getMultipleCampDetails(ds, districtId));
            request.setAttribute("campsList", partADAO.getMultipleCampDetails(ds, districtId));
            // getExistingData(mapping, form, request, response);
            request.setAttribute("districtId", districtId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("webDetails");
    }

    public ActionForward getMultipleCampDetails1(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartADAO partADAO = new PartADAO();
        String jobcardDetails = null;
        PrintWriter out = null;
        HttpSession session = request.getSession();
        String districtId = null;
        try {
            if (session.getAttribute("districtId") != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            DataSource ds = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            response.setHeader("Cache-Control", "private");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setContentType("text/xml");
            out = response.getWriter();
            //jobcardDetails = partADAO.getMultipleCampDetails(ds, districtId, request.getParameter("rowID"), request.getParameter("flag"));

            out.println("<jobcard>");
            out.println("<jobcard>" + jobcardDetails + "</jobcard>");
            out.println("</jobcard>");

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
        }
        return null;
    }
}
