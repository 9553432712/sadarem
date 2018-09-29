package org.bf.disability.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.DoctorsInformationDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dto.DoctorsInformationDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.DisabilityIdForm;
import org.bf.disability.form.PartAForm;
import org.bf.disability.service.AppletAuthorityService;
import org.bf.disability.service.FilterCandidateService;
import org.bf.disability.service.PartAService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;
import org.bf.disability.servicefactory.FilterCandidateServiceFactory;
import org.bf.disability.servicefactory.PartAServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import Aadhar.AadharDetails;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.reports.DAO.CommonReportsDAO;
import com.tcs.sadarem.reports.DAOImpl.CommonReportsDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

/**
 * This dispatch action class is used to manipulate the person personal details
 * @author deviprasad.t
 * @version 1.0
 */
public class PartAAction extends BaseDispatchAction {

    ActionMessages actionMessages = null;
    DataSource ds = null;

    /**
     * this method will insert personal details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action forward
     */
    public ActionForward insertPersonalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {





        PartAForm partAForm = (PartAForm) form;
        PartADAO partADAO = new PartADAO();

        PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
        AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
        //DataSource ds= getDataSource(request);
        HttpSession session = request.getSession(true);
        String district_id = (String) request.getParameter("district_id");
        String mandal_id = (String) request.getParameter("mandal_id");
        String village_id = (String) request.getParameter("village_id");
        String habitation_id = (String) request.getParameter("habitation_id");
        String panchayat_id = (String) request.getParameter("panchayat_id");
        String assembly_id = (String) request.getParameter("assembly_id");
        String habcode = (String) request.getParameter("habitation_id");

        //   String reasonforstatus = (String) request.getParameter("reasonforstatus");
        String partACheckForDuplicatePersonCode = null;
        String personcodemax = null;
        String relation = null;
        String status = null;
        String personcode = null;
        String loginid = null;
        String target = null;
        String agePersonalInsert = null;
        int pensionNumberCheck = 0;
        String personIdFromPensionCode = null;
        PartADTO partADTO = new PartADTO();


        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("sadaremCodeAu") != null) {
                session.removeAttribute("sadaremCodeAu");
            }

            boolean flag = isTokenValid(request, true);

            partACheckForDuplicatePersonCode = partADAO.getPartACheckForDuplicate(ds, district_id, mandal_id, village_id, habitation_id, partAForm.getSurname(), partAForm.getFirstname(), partAForm.getLastname(), partAForm.getNoOfYears(), partAForm.getGender(), partAForm.getGsurname(), partAForm.getHouseno());
            if (partACheckForDuplicatePersonCode != null && !"null".equals(partACheckForDuplicatePersonCode)) {
                request.setAttribute("partACheckForDuplicatePersonCode", partACheckForDuplicatePersonCode);
                return mapping.findForward("duplicate");
            }

            String day = request.getParameter("day");
            String Systemip = request.getRemoteAddr();
            relation = convertToString(partAForm.getRelation());

            partAForm.setRelationStr(relation);
            personcode = (String) session.getAttribute("personcode");
            loginid = (String) session.getAttribute("loginid");
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));

            partAForm.setLoginid(loginid);
            partAForm.setCampid(campId);
            String district_name = partAForm.getDistrict();
            String mandal_name = partAForm.getMandal();
            String village_name = partAForm.getTownVillage();
            String habitation_name = partAForm.getHabitation();

            partAForm.setDistrict(district_id);
            partAForm.setMandal(mandal_id);
            partAForm.setTownVillage(village_id);
            partAForm.setHabitation(habitation_id);
            partAForm.setHabCode(habcode);

            agePersonalInsert = appletAuthorityService.getAge(ds, personcode);
            session.setAttribute("agePersonalInsert", agePersonalInsert);

            //  partAForm.setReasonforstatus(reasonforstatus);
            if (panchayat_id != null && !"".equals(panchayat_id)) {
                partAForm.setPanchayatiid(panchayat_id);
            }
            String districtid = null;
            FilterCandidateService functionalNeedService = FilterCandidateServiceFactory.getFilterCandidateServiceImpl();
            if (session.getAttribute("districtId") != null) {
                districtid = (String) session.getAttribute("districtId");
                request.setAttribute("districtidData", districtid);
            }
            String date = partAForm.getDay() + "/" + partAForm.getMonth() + "/" + partAForm.getYear();
            district_name = functionalNeedService.getDistrictNameDetails(ds, districtid);


            //Chrononical age  calculation for Mental Retardation tests.
            Date d1 = new GregorianCalendar(Integer.parseInt(partAForm.getYear()), Integer.parseInt(partAForm.getMonth()), Integer.parseInt(partAForm.getDay())).getTime();
            Date today = new Date();
            long diff = today.getTime() - d1.getTime();
            long days = diff / (1000 * 60 * 60 * 24);

            double years = (double) days / 365;
            session.setAttribute("chronologicalage", new Double(years));
            partAForm.setDobday(date);
            partAForm.setSystemip(Systemip);
            if (request.getParameter("noofrowvalue") != null) {
                partAForm.setNoofrowvalue(Integer.parseInt(request.getParameter("noofrowvalue")));
                partADTO = partAService.getExistingPensionDetails(session.getAttribute("pensionIdDis").toString(), district_id, ds, request);
                partAForm.setDistrict(district_id);
                partAForm.setMandal(partADTO.getMandalid());
                partAForm.setTownVillage(partADTO.getVillageid());
                partAForm.setHabitation(partADTO.getHabitationid());
                partAForm.setPanchayatiid(partADTO.getPanchayatiid());
            }
            BeanUtils.copyProperties(partADTO, partAForm);

            /* Checking the pensioncard status in tblperson_personal_details table **/

            // pensionNumberCheck = partAService.checkPensionCardNumber(ds, session.getAttribute("pensionIdDis").toString(), session.getAttribute("districtId").toString());

//            String aadharNumber = PartADAO.checkAdharCardNumberWithPensionNo(ds, request.getParameter("aadharCardNo"), "AdharCard", district_id);
//            if (aadharNumber.equals("Invalid") || !request.getParameter("aadharCardNo").equals("") && !aadharNumber.equals(request.getParameter("aadharCardNo"))) {
            if (pensionNumberCheck == 0) {  // added by mohan on 30/12/2011 (Only this if block)

                //added by kavya

                if (partAForm.getCard() != null && partAForm.getRationCardSlno() != null && request.getParameter("readonly") != null) {
                    URL url1 = new URL(CommonConstants.Url1);
//                    URL url2 = new URL(CommonConstants.Url2);

                    // AponlineWSClient wsClient = new AponlineWSClient();
                    String message = null;
                    String rationcardtype = partAForm.getCard().substring(0, 3);
//                    String districtid = (String) session.getAttribute("districtId");
                    if (rationcardtype.equalsIgnoreCase("RAP") || rationcardtype.equalsIgnoreCase("TAP")) {
//                        message = partADAO.getRAPTAPRationCardSerialNumberStatus(ds, partAForm.getCard().toString(), partAForm.getRationCardSlno().toString(), districtid);
                        message = partADAO.getRAPTAPRationCardSerialNumberStatus(ds, partAForm.getCard().toString(), partAForm.getRationCardSlno().toString(), districtid);
//                    } else {
                        // message = wsClient.getRationcardSerialNumbers(ds, url1, null, partAForm.getCard().toString(), partAForm.getRationCardSlno().toString(), request);
                    }


                    if (message != null && message.equalsIgnoreCase(CommonConstants.SUCCESS)) {
                        message = partADAO.getAssesmentStatusRationcardAndSlnoStatus(ds, partADTO.getCard(), partADTO.getRationCardSlno(), personcode);

                    }
                    if (message != null && message.equalsIgnoreCase(CommonConstants.ERROR)) {
                        request.setAttribute("MSG", "You Have Entered Invalid Rationcard serial number ");
                        flag = false;
                        return mapping.findForward("exception");
                    } else if (message != null && !message.equalsIgnoreCase(CommonConstants.SUCCESS)) {
                        request.setAttribute("MSG", message);
                        flag = false;
                        return mapping.findForward("exception");
                    }
                }

                //ended
                //if (flag) {
                // String districtid = "";

                personcodemax = partAService.insertPersonalDetails(partADTO, ds, request);



                // }
                if (partADTO.getPersonstatus() != null && partADTO.getPersonstatus().equals("Eligible")) {

                    target = "finish";



                } else if (partADTO.getPersonstatus() != null && partADTO.getPersonstatus().equals("Rejected")) {
                    target = "rejected";


                }
                if (request.getAttribute("dvalues") != null) {
                    request.setAttribute("dvaluess", "<font color='red' size='2'>Select Disabilities Exceeded To Selete Camp</font>");
                    partADAO.removeSadaremids(ds, personcodemax);
                    partAForm.setType_disability("");
                    request.setAttribute("dvaluess", "<font color='red' size='2'>Select Disabilities Exceeded To Selete Camp</font>");
                    return mapping.findForward("nocamp");
                } else {
                    String message = null;
                    if (partADTO.getPhoneno() != null) {
                        ArrayList dpmList = partADAO.getDpmuDetails(ds, district_id, partADTO.getPartaCampId(), personcodemax);
                        ArrayList campDetails = partADAO.getsmsCampDetials(ds, personcodemax, partADTO);
                        if (dpmList.size() > 0 && campDetails.size() > 0) {
                            message = "Camp Scheduled For " + partADTO.getFirstname().trim() + ",On " + campDetails.get(1) + ", At " + campDetails.get(0) + "."
                                    + "With SID As " + personcodemax + ".Contact To Camp Incharge (" + dpmList.get(1) + ").";
                        } else if (campDetails.size() > 0) {
                            message = "Camp Scheduled For " + partADTO.getFirstname().trim() + ",On " + campDetails.get(1) + ", At " + campDetails.get(0) + "."
                                    + "With SID As " + partAForm.getPersoncode() + ".Contact To NA";
                        } else {
                            message = "Camp Scheduled For " + partADTO.getFirstname() + ","
                                    + "For Sadarem Certificate With SADAREMID As " + personcodemax + "";
                        }
                        // com.tpSMS.TpSendSMS.sendSMS(partADTO.getPhoneno(), message);
                        // partADAO.insertSmsLogDetails(ds, partADTO, message, "Sent");
                    }

                }

                session.setAttribute("personstatus", partADTO.getPersonstatus());
                session.setAttribute("personcode", personcodemax);
                session.setAttribute("teluguname", partADTO.getTelugupersonname());
                session.setAttribute("Name", partADTO.getFirstname());

                if (((String) session.getAttribute("restrictPartA") != null) && "true".equals((String) session.getAttribute("restrictPartA"))) {
                    request.setAttribute("restrictPartA", (String) session.getAttribute("restrictPartA"));
                    target = "restrictPartA";
                } else if (((String) session.getAttribute("restrictPartA") != null) && "pensionNumberRestrictPartA".equals((String) session.getAttribute("restrictPartA"))) {
                    request.setAttribute("restrictPartA", (String) session.getAttribute("restrictPartA"));
                    target = "restrictPartA";
                }

                if (((String) request.getParameter("restrictPartA") != null) && "true".equals((String) request.getParameter("restrictPartA"))) {
                    request.setAttribute("restrictPartA", (String) request.getParameter("restrictPartA"));
                    if (request.getParameter("restrictDataFlag") != null) {
                        request.setAttribute("restrictDataFlagTest", request.getParameter("restrictDataFlag"));
                    }

                    target = "restrictPartA";
                }

                if (((String) session.getAttribute("restrictPartA") != null) && "true".equals((String) session.getAttribute("restrictPartA"))) {


                    request.setAttribute("restrictPartA", (String) session.getAttribute("restrictPartA"));
                    target = "restrictPartA";


                    request.setAttribute("restrict", "yes");
                }
                session.removeAttribute("restrictPartA");
                request.setAttribute("districtName", district_name);
                request.setAttribute("district_name", district_name);
                request.setAttribute("mandal_name", mandal_name);
                request.setAttribute("village_name", village_name);
                request.setAttribute("habitation_name", habitation_name);
                request.setAttribute("district_id", district_id);
                request.setAttribute("mandal_id", mandal_id);
                request.setAttribute("village_id", village_id);
                request.setAttribute("habitation_id", habitation_id);
                request.setAttribute("panchayat_id", panchayat_id);
                request.setAttribute("assembly_id", assembly_id);
                request.setAttribute("habcode", habcode);
                session.removeAttribute("pensionIdDis");
                
                
                request.setAttribute("Name", partADTO.getFirstname());
                request.setAttribute("Surname", partADTO.getSurname());
                request.setAttribute("teluguname", partADTO.getTelugupersonname());
                
                
                
            } else {
                personIdFromPensionCode = partAService.getPersonCode(ds, session.getAttribute("pensionIdDis").toString());
                request.setAttribute("alreadyEnterPersonId", personIdFromPensionCode);
                target = "alreadyEnteredDataDetails";


            }



//            } else {
//
//                request.setAttribute("alreadyEnterPersonId", partADAO.checkAdharCardNumberWithPensionNo(ds, request.getParameter("aadharCardNo"), "AdharCardNumber", district_id));
//                request.setAttribute("aadharNoPersonCode", "aadharNoPersonCode");
//                request.setAttribute("aadharNoForDisplay", request.getParameter("aadharCardNo"));
//                target = "alreadyEnteredDataDetails";
//                 System.out.println("alreadyEnteredDataDetails=="+target);
//            }
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            target = "exception";


            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetails", "PartAAction", "Code");
            // throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "insertPersonalDetails");
        }


        return mapping.findForward(target);
    }

    public ActionForward PersonCodeAcknowledgement(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "PersonCodeAcknowledgement";

        PartAForm partAForm = (PartAForm) form;

        PartADTO partADTO = new PartADTO();
        PartADAO partADAO = new PartADAO();
        ArrayList aknowledgementDetails = new ArrayList();
        PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
//        AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();

        HttpSession session = request.getSession(true);
        String personcode = (String) session.getAttribute("personcode");

        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String result = null;
            if (request.getParameter("result") != null) {
                result = (String) request.getParameter("result");
            }
            aknowledgementDetails = partADAO.getPartaacknowledgementDetails(ds, personcode, partADTO);
            ArrayList aknowledgementDetailsmultiple = partADAO.getmultipleDisabilityDetails(ds, personcode, partADTO);
            if (aknowledgementDetailsmultiple.size() > 0) {

                request.setAttribute("aknowledgementDetailsmultiple", aknowledgementDetailsmultiple);
            }
            if (aknowledgementDetails.size() > 0) {

                request.setAttribute("aknowledgementDetails", aknowledgementDetails);
            } else {
                request.setAttribute("noData", "No Data Found");

            }
            session.setAttribute("personcode", personcode);
            request.setAttribute("result", result);
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "personCodeAcknowledgement", "PartAAction", "Code");
        }


        return mapping.findForward(target);
    }

    public ActionForward print(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "print";
        PartAForm partAForm = (PartAForm) form;
        PartADAO partADAO = new PartADAO();
        ArrayList aknowledgementDetails = new ArrayList();
        PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
        AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
        HttpSession session = request.getSession(true);
        String personcode = (String) session.getAttribute("personcode");

        PartADTO partADTO = new PartADTO();


        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String result = null;
            if (request.getParameter("result") != null) {
                result = (String) request.getParameter("result");
            }
            aknowledgementDetails = partADAO.getPartaacknowledgementDetails(ds, personcode, partADTO);
            ArrayList aknowledgementDetailsmultiple = partADAO.getmultipleDisabilityDetails(ds, personcode, partADTO);
            if (aknowledgementDetailsmultiple.size() > 0) {

                request.setAttribute("aknowledgementDetailsmultiple", aknowledgementDetailsmultiple);
            }
            if (aknowledgementDetails.size() > 0) {

                request.setAttribute("aknowledgementDetails", aknowledgementDetails);
            } else {
                request.setAttribute("noData", "No Data Found");

            }
            session.setAttribute("personcode", personcode);
            request.setAttribute("result", result);
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetails", "PartAAction", "Code");
        }


        return mapping.findForward(target);
    }

    /**
     * this method will inset disability details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward insertDisabilityDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        request.setAttribute("selectedValue", request.getParameter("selectFlow"));
        PartAForm partAForm = (PartAForm) form;
        ArrayList disabilityList = new ArrayList();
        int disabilityId = 0;

        PartADAO partADAO = new PartADAO();
        PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
        //DataSource ds= getDataSource(request);

        String day = request.getParameter("day");
        HttpSession session = request.getSession(true);

        String personstatus = null;
        if (session.getAttribute("personStatusForAU") != null) {
            personstatus = (String) session.getAttribute("personStatusForAU");
        } else {
            personstatus = (String) session.getAttribute("personstatus");
        }

        int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
        if (partAForm.getYearsfortemporary() == null) {
            partAForm.setYearsfortemporary("0");
        }

        String status = request.getParameter("partAStatus");
        disabilityId = partAForm.getDisabilityId();
        session.setAttribute("disabilityid", new Integer(disabilityId));
        String disabilitySubIds = null;
        String disabilitySubSubIds = null;
        String conditiondisabilityIds = null;
        String relation = null;
        String personcode = null;
        String loginid = null;
        String target = "repeat";
        String Systemip = request.getRemoteAddr();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (status == null) {
                status = "update";
            }
            if (status.equalsIgnoreCase("update")) {
                partAForm.setCongenital(false);
                partAForm.setCongenitalbettereye(false);
                partAForm.setCongenitalworseeye(false);
                partAForm.setHereditary(false);
                partAForm.setHereditarybettereye(false);
                partAForm.setHereditaryworseeye(false);
                partAForm.setBirthinjury(false);
                partAForm.setBirthinjurybettereye(false);
                partAForm.setBirthinjuryworseeye(false);
                partAForm.setDiseaseInfection(false);
                partAForm.setDiseaseInfectionbettereye(false);
                partAForm.setDiseaseInfectionworseeye(false);
                partAForm.setHighfever(false);
                partAForm.setEpilepsy(false);
                partAForm.setBirthasphyxia(false);
                partAForm.setMalnutrition(false);
                partAForm.setMalnutritionbettereye(false);
                partAForm.setMalnutritionworseeye(false);
                partAForm.setAccident(false);
                partAForm.setAccidentbettereye(false);
                partAForm.setAccidentworseeye(false);
                partAForm.setOthertypeofdisability("");
                partAForm.setDiagnosisofdisability("");
                int[] condisability = {0};
                partAForm.setConditiondisability(condisability);
                partAForm.setConditionindisability("");
                partAForm.setKindofdisability("");
                partAForm.setYearsfortemporary("0");

                //partAForm.setHospitalname("");
                //partAForm.setHospitaladdress("");
                partAForm.setDoctor1name("");
                partAForm.setDoctor2name("");
                partAForm.setDoctor3name("");
                partAForm.setDoctor1designation("");
                partAForm.setDoctor2designation("");
                partAForm.setDoctor3designation("");
                partAForm.setDoctor1regnumber("");
                partAForm.setDoctor2regnumber("");
                partAForm.setDoctor3regnumber("");
                partAForm.setF_can(null);
                partAForm.setPp_can(null);
                partAForm.setL_can(null);
                partAForm.setKc_can(null);
                partAForm.setB_can(null);
                partAForm.setS_can(null);
                partAForm.setSt_can(null);
                partAForm.setW_can(null);
                partAForm.setSe_can(null);
                partAForm.setRw_can(null);
                partAForm.setH_can(null);

                disabilityList = partADAO.getDisabilityList(ds);
                partAForm = populateForm(disabilityId, form, ds, "no", campId);
                partAForm.setDisabilityList(disabilityList);

            }

            if (status.equalsIgnoreCase("finish")) {
                disabilitySubIds = convertToString(partAForm.getDisabilityLocoSubIds());
                disabilitySubSubIds = convertToString(partAForm.getDisabilityLocoSubSubIds());
                conditiondisabilityIds = convertToString(partAForm.getConditiondisability());

                relation = convertToString(partAForm.getRelation());
                partAForm.setRelationStr(relation);
                partAForm.setDisabilitySubIds(disabilitySubIds);
                partAForm.setDisabilitySubSubIds(disabilitySubSubIds);
                partAForm.setConditiondisabilityIds(conditiondisabilityIds);

                partAForm.setPersonstatus(personstatus);

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }

                loginid = (String) session.getAttribute("loginid");
                partAForm.setLoginid(loginid);
                partAForm.setSystemip(Systemip);
                PartADTO partADTO = new PartADTO();
//                if(partAForm.getDisabilityId() == 4 ||partAForm.getDisabilityId() == 6 ){
//                String doctorName = partAForm.getSpecialistprefix().concat(".").concat(partAForm.getDoctor1name());
//                partAForm.setDoctor1name(doctorName);
//                }
                // Added for appending Dr. Prefix to first doctor name
                if (partAForm.getDisabilityId() == 4) {
                    String doctorName = null;
                    if (partAForm.getSpecialistprefix().equals("Dr")) {
                        if (!partAForm.getDoctor1name().substring(0, 1).equals("Dr")) {
                            doctorName = partAForm.getSpecialistprefix().concat(".").concat(" ").concat(partAForm.getDoctor1name());
                        }
                    } else {
                        doctorName = partAForm.getDoctor1name();
                    }
                    partAForm.setDoctor1name(doctorName);

                    //session.setAttribute("doctorname1", doctorName);


                } else {
                    if (!"".equals(partAForm.getSpecialistprefix()) && partAForm.getSpecialistprefix() != null) {
                        if (!partAForm.getDoctor1name().startsWith("Dr")) {
                            String doctorName = partAForm.getSpecialistprefix().concat(".").concat(" ").concat(partAForm.getDoctor1name());
                            partAForm.setDoctor1name(doctorName);
                        }
                    }
                }
                // endedd for appending Dr. Prefix to first doctor name
                BeanUtils.copyProperties(partADTO, partAForm);

                if (partAForm.getDisabilityId() == 6 && partADTO.getPersonstatus().equals("Rejected")) {
                    target = "failure";
                } else {
                    boolean flag = isTokenValid(request, true);
                    if (partAForm.getDisabilityId() == 6) {
                        Map multipleDisabilityMap = new HashMap();
                        ArrayList multipleDisabilityList = null;
                        String ohdoctor = request.getParameter("ohdoctor");
                        String vidoctor = request.getParameter("vidoctor");
                        String hidoctor = request.getParameter("hidoctor");
                        String mrdoctor = request.getParameter("mrdoctor");
                        String midoctor = request.getParameter("midoctor");
                        session.setAttribute("ohdoctor", ohdoctor);
                        session.setAttribute("vidoctor", vidoctor);
                        session.setAttribute("hidoctor", hidoctor);
                        session.setAttribute("mrdoctor", mrdoctor);
                        session.setAttribute("midoctor", midoctor);
                        multipleDisabilityList = new ArrayList();
                        if (ohdoctor != null) {
                            multipleDisabilityList.add(ohdoctor);
                        }
                        if (vidoctor != null) {
                            multipleDisabilityList.add(vidoctor);
                        }
                        if (hidoctor != null) {
                            multipleDisabilityList.add(hidoctor);
                        }
                        if (mrdoctor != null) {
                            multipleDisabilityList.add(mrdoctor);
                        }
                        if (midoctor != null) {
                            multipleDisabilityList.add(midoctor);
                        }
                        String[] multipleDisabilityID = (String[]) multipleDisabilityList.toArray(new String[0]);
                        for (int i = 0; i < multipleDisabilityID.length; i++) {
                            String id = multipleDisabilityID[i];
                            if (id.equals("1")) {
                                PartADTO multipleDisabilityDTO1 = new PartADTO();
                                multipleDisabilityDTO1.setDoctorname(partAForm.getDoctornameOH());
                                multipleDisabilityDTO1.setDoctorregnumber(partAForm.getDoctorregnumberOH());
                                multipleDisabilityDTO1.setDoctordesignation(partAForm.getDoctordesignationOH());
                                multipleDisabilityMap.put(id, multipleDisabilityDTO1);
                            }
                            if (id.equals("2")) {
                                PartADTO multipleDisabilityDTO2 = new PartADTO();
                                multipleDisabilityDTO2.setDoctorname(partAForm.getDoctornameVI());
                                multipleDisabilityDTO2.setDoctorregnumber(partAForm.getDoctorregnumberVI());
                                multipleDisabilityDTO2.setDoctordesignation(partAForm.getDoctordesignationVI());
                                multipleDisabilityMap.put(id, multipleDisabilityDTO2);

                            }
                            if (id.equals("3")) {
                                PartADTO multipleDisabilityDTO3 = new PartADTO();
                                multipleDisabilityDTO3.setDoctorname(partAForm.getDoctornameHI());
                                multipleDisabilityDTO3.setDoctorregnumber(partAForm.getDoctorregnumberHI());
                                multipleDisabilityDTO3.setDoctordesignation(partAForm.getDoctordesignationHI());
                                multipleDisabilityMap.put(id, multipleDisabilityDTO3);

                            }
                            if (id.equals("4")) {
                                PartADTO multipleDisabilityDTO4 = new PartADTO();

                                /** Added by rakha on 16/08/2011 */
                                String doctorName = null;
                                if (partAForm.getMdspecialistprefix().equals("Dr")) {
                                    if (!partAForm.getDoctornameMR().substring(0, 1).equals("Dr")) {
                                        doctorName = partAForm.getMdspecialistprefix().concat(".").concat(" ").concat(partAForm.getDoctornameMR());
                                    }
                                } else {
                                    doctorName = partAForm.getDoctornameMR();
                                    String e = partAForm.getMdspecialistprefix();
                                    if (e != null) {
                                        e = e.toLowerCase();
                                        if (e.contains("psychologist")) {
                                            session.setAttribute("des", "yes");
                                        }
                                    }
                                }
                                multipleDisabilityDTO4.setDoctorname(doctorName);

                                /** End */
                                //multipleDisabilityDTO4.setDoctorname(partAForm.getDoctornameMR());
                                multipleDisabilityDTO4.setDoctorregnumber(partAForm.getDoctorregnumberMR());
                                multipleDisabilityDTO4.setDoctordesignation(partAForm.getDoctordesignationMR());
                                multipleDisabilityMap.put(id, multipleDisabilityDTO4);

                            }
                            if (id.equals("5")) {
                                PartADTO multipleDisabilityDTO5 = new PartADTO();
                                multipleDisabilityDTO5.setDoctorname(partAForm.getDoctornameMI());
                                multipleDisabilityDTO5.setDoctorregnumber(partAForm.getDoctorregnumberMI());
                                multipleDisabilityDTO5.setDoctordesignation(partAForm.getDoctordesignationMI());
                                multipleDisabilityMap.put(id, multipleDisabilityDTO5);
                            }

                        }
                        partADAO.insertMultipleDoctors(ds, personcode, multipleDisabilityID, multipleDisabilityMap, loginid, Systemip, request);
                        session.setAttribute("multipleDisabilityList", multipleDisabilityList);
                        session.setAttribute("Mdspecialistprefix", partADTO.getMdspecialistprefix());
                    }

                    //  if (flag) {
                    partAService.insertDisabilityDetails(personcode, partADTO, ds, request);
                    // }

                    DisabilityIdForm disabilityIdForm = new DisabilityIdForm();
                    disabilityIdForm.setDisabilityId(String.valueOf(partAForm.getDisabilityId()));
                    session.setAttribute("disabilityIdForm", disabilityIdForm);
                    target = "finish";
                }
            }
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDisabilityDetails", "PartAAction", "Code");
            //////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "insertDisabilityDetails");
            sqlEx.printStackTrace();
        }
        return mapping.findForward(target);
    }

    /**
     * this method will get the personal details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward getPersonalDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException 
    {
        String target = null;
        int[] disabilityLocoSubIds = null;
        int[] disabilityLocoSubSubIds = null;
        int[] conditiondisability = null;
        int[] relation = null;
        String rationCardNumber = null;
        String loginid = null;
        String rejectedViewstatus = null;
        boolean editPermissionFlag = false;
        String personcode = null;
        String district_name = null;
        String mandal_name = null;
        String village_name = null;
        String panchayat_name = null;
        String habitation_name = null;
        
        try 
        {
        	
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) 
            {
                ds = JNDIDataSource.getConnection();
            }
            district_name = (String) request.getAttribute("district_name");
            mandal_name = (String) request.getAttribute("mandal_name");
            village_name = (String) request.getAttribute("village_name");
            panchayat_name = (String) request.getAttribute("panchayat_name");
            habitation_name = (String) request.getAttribute("habitation_name");

            String delete = (String) request.getParameter("delete");
            HttpSession session = request.getSession(true);
            PartAForm partAForm = (PartAForm) form;
            PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
            PartADAO partADAO = new PartADAO();
            PartADTO partADTO = new PartADTO();
            ArrayList disabilityList = new ArrayList();

            String personstatus = (String) session.getAttribute("personstatus");
            personcode = (String) session.getAttribute("personcode");
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            loginid = (String) session.getAttribute("loginid");

            String pensionNumberFlow = (String) request.getParameter("pensionNumberFlow");
            boolean partAUpdateOnly = Boolean.parseBoolean(request.getParameter("partAUpdate"));
            request.setAttribute("partAUpdate", partAUpdateOnly);
            rejectedViewstatus = (String) request.getParameter("rejectedViewstatus");

            partADTO = partAService.getPersonalDetails(personcode, personstatus, ds, campId);
            String[] stringArray = partADTO.getDobday().split("/");
            partADTO.setDay(stringArray[0]);
            partADTO.setMonth(stringArray[1]);
            partADTO.setYear(stringArray[2]);
            request.setAttribute("roleId", session.getAttribute("roleId"));

            //Chrononical age  calculation for Mental Retardation tests.
            Date d1 = new GregorianCalendar(Integer.parseInt(partADTO.getYear()), Integer.parseInt(partADTO.getMonth()), Integer.parseInt(partADTO.getDay())).getTime();
            Date today = new Date();
            long diff = today.getTime() - d1.getTime();
            long days = diff / (1000 * 60 * 60 * 24);

            double years = (double) days / 365;

            session.setAttribute("chronologicalage", new Double(years));


            int disablityid = partADTO.getDisabilityId();
            request.setAttribute("olddisablityid", disablityid);
            //session.setAttribute("disabilityid", new Integer(disablityid));
            DisabilityIdForm disabilityIdForm = new DisabilityIdForm();
            disabilityIdForm.setDisabilityId(String.valueOf(partADTO.getDisabilityId()));
//rationCardSlno

            //session.setAttribute("disabilityIdForm", disabilityIdForm);
            String teluguname = partADTO.getTelugupersonname();
            String telugufathername = partADTO.getTelugufathername();
            session.setAttribute("teluguname", teluguname);
            session.setAttribute("telugufathername", telugufathername);
            session.setAttribute("Name", partADTO.getFirstname());

            request.setAttribute("surname", partADTO.getSurname());
            request.setAttribute("name", partADTO.getFirstname());
            request.setAttribute("relName", partADTO.getGsurname());
            // added code for getting rationtype based on reationcard number.
            
            String vieweditmode = CommonUtility.checkNullObj(partADTO.getOperatorStatus());

            rationCardNumber = partADTO.getCard();
            if (rationCardNumber != null && !"".equals(rationCardNumber)) {
                if (rationCardNumber.contains("WAP") || rationCardNumber.contains("wap")) 
                {
                    partADTO.setRtype("1");
                } 
                else if (rationCardNumber.contains("PAP") || rationCardNumber.contains("pap"))
                {
                    partADTO.setRtype("2");
                }
                else if (rationCardNumber.contains("AAY") || rationCardNumber.contains("aay")) 
                {
                    partADTO.setRtype("3");
                } 
                else if (rationCardNumber.contains("AAP") || rationCardNumber.contains("aap")) 
                {
                    partADTO.setRtype("4");
                } 
                else if (rationCardNumber.contains("YAP") || rationCardNumber.contains("yap"))
                {
                    partADTO.setRtype("5");
                }
                else if (rationCardNumber.contains("TAP") || rationCardNumber.contains("tap")) 
                {
                    partADTO.setRtype("6");
                } 
                else if (rationCardNumber.contains("RAP") || rationCardNumber.contains("rap"))
                {
                    partADTO.setRtype("7");
                } 
                else if (rationCardNumber.contains("WAD") || rationCardNumber.contains("wad")) 
                {
                    partADTO.setRtype("8");
                }
            }
            //addded by kavya



            if (partADTO.getCard() != null && partADTO.getCard().trim().length() > 0) 
            {
                request.setAttribute("RationReadOnly", "true");
            }
            
            if (partADTO.getRationCardSlno() != null && partADTO.getRationCardSlno().trim().length() > 0) 
            {
                request.setAttribute("SlNoReadOnly", "true");
            }

            //ended
            BeanUtils.copyProperties(partAForm, partADTO);
//            if (personstatus.equals("Eligible")) {
//                disabilityList = partADAO.getDisabilityList(ds);
//                //partADTO = partADAO.getCampDetails(ds, campId);
//            } else {
//                disabilityList = partADAO.getDisabilityList(ds);
//                //partADTO = partADAO.getCampDetails(ds, campId);
//                disabilityList.remove(5);
//            }
//
//            partAForm.setDisabilityList(disabilityList);
//            partAForm = populateForm(partADTO.getDisabilityId(), form, ds, "yes", campId);
//
//
//            disabilityLocoSubIds = convertToIntArray(partADTO.getDisabilitySubIds());
//            disabilityLocoSubSubIds = convertToIntArray(partADTO.getDisabilitySubSubIds());
//            conditiondisability = convertToIntArray(partADTO.getConditiondisabilityIds());
//
//            partAForm.setDisabilityId(partADTO.getDisabilityId());
//            partAForm.setDisabilityLocoSubIds(disabilityLocoSubIds);
//            partAForm.setDisabilityLocoSubSubIds(disabilityLocoSubSubIds);
//            partAForm.setConditiondisability(conditiondisability);
//
            relation = convertToIntArray(partADTO.getRelationStr());
            partAForm.setRelation(relation);
            partAForm.setRationCardSlno(partADTO.getRationCardSlno());

            partADTO = partADAO.getLoginDetails(ds, loginid);
            if (CommonConstants.PERSONSTATUS_REJECT.equals(personstatus)) 
            {
                editPermissionFlag = compareRoleIds(partADTO.getRoleid(), CommonConstants.REJECT_EDIT_MODE_PERMISSION_IDS, rejectedViewstatus, personstatus);
            }
            
            if (CommonConstants.PERSONSTATUS_ELIGIBLE.equals(personstatus))
            {
                editPermissionFlag = compareRoleIds(partADTO.getRoleid(), CommonConstants.EDIT_MODE_PERMISSION_IDS, partAForm.getOperatorStatus(), personstatus);
            }
            
            if (editPermissionFlag || partAUpdateOnly) 
            {
                target = "success";
                
                if (pensionNumberFlow != null && "pensionNumberFlow".equals(pensionNumberFlow))
                {
                    target = "pensionNumberFlow";
                } 
                else if (partAUpdateOnly)
                {
                	  target = "pensionNumberFlow1";
                	  request.setAttribute("vieweditmode",vieweditmode);
                	 
                } 
                CommonDAO comObj			   = new CommonDAOImpl();  
                
		        request.setAttribute("PartABasicDetailsList", comObj.getSADAREMPartABasicDetailsByID(personcode));  

            } 
            else if (!editPermissionFlag) 
            {
                target = "viewmode";
            }
            
            if (((String) request.getAttribute("resPartB")) != null && ((String) request.getAttribute("resPartB")).equals("true"))
            {
                target = "restrictPartB";
            }
            
            if (((String) request.getAttribute("restrictPartBUpdateByTerr")) != null && ((String) request.getAttribute("restrictPartBUpdateByTerr")).equals("true"))
            {
                target = "restrictPartBUpdateByTerr";
            }
            
            if (delete != null) 
            {
                target = "delete";
            }
            request.setAttribute("district_name", district_name);
            request.setAttribute("mandal_name", mandal_name);
            request.setAttribute("village_name", village_name);
            request.setAttribute("panchayat_name", panchayat_name);
            request.setAttribute("habitation_name", habitation_name);

            saveToken(request);
        } 
        catch (Exception sqlEx) 
        {

            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetails", "PartAAction", "Code");
            //////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "getPersonalDetails");
            sqlEx.printStackTrace();
        }
        
        System.out.println("target : "+target);
        
        return mapping.findForward(target);
    }

    /**
     * this method will get the Disability Details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward getDisabilityDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws SADAREMDBException, SQLException 
    {
        String target = null;
        int[] disabilityLocoSubIds = null;
        int[] disabilityLocoSubSubIds = null;
        int[] conditiondisability = null;
        int[] relation = null;
        String rationCardNumber = null;
        String loginid = null;
        String rejectedViewstatus = null;
        boolean editPermissionFlag = false;
        String personcode = null;
        String district_name = null;
        String mandal_name = null;
        String village_name = null;
        String panchayat_name = null;
        String habitation_name = null;
        

        HttpSession session = request.getSession(true);
        
        int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
        try 
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            district_name = (String) request.getAttribute("district_name");
            mandal_name = (String) request.getAttribute("mandal_name");
            village_name = (String) request.getAttribute("village_name");
            panchayat_name = (String) request.getAttribute("panchayat_name");
            habitation_name = (String) request.getAttribute("habitation_name");

            String delete = (String) request.getParameter("delete");
            PartAForm partAForm = (PartAForm) form;
            PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
            PartADAO partADAO = new PartADAO();
            PartADTO partADTO = new PartADTO();
            ArrayList disabilityList = new ArrayList();

            String personstatus = null;//(String) session.getAttribute("personstatus");
            personcode = null;//(String) session.getAttribute("personcode");

            if (session.getAttribute("personStatusForAU") != null) {
                personstatus = (String) session.getAttribute("personStatusForAU");
            } else {
                personstatus = (String) session.getAttribute("personstatus");
            }

            if (session.getAttribute("sCodeForPrints") != null) {
                personcode = (String) session.getAttribute("sCodeForPrints");
            }



            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            loginid = (String) session.getAttribute("loginid");

            String pensionNumberFlow = (String) request.getParameter("pensionNumberFlow");
            boolean partAUpdateOnly = Boolean.parseBoolean(request.getParameter("partAUpdate"));
            request.setAttribute("partAUpdate", partAUpdateOnly);
            rejectedViewstatus = (String) request.getParameter("rejectedViewstatus");

            partADTO = partAService.getDesabilityDetails(personcode, personstatus, ds, campId);


            int disablityid = partADTO.getDisabilityId();
            request.setAttribute("olddisablityid", disablityid);
            session.setAttribute("disabilityid", new Integer(disablityid));
            DisabilityIdForm disabilityIdForm = new DisabilityIdForm();
            disabilityIdForm.setDisabilityId(String.valueOf(partADTO.getDisabilityId()));




            BeanUtils.copyProperties(partAForm, partADTO);
            if (personstatus.equals("Eligible")) {
                disabilityList = partADAO.getDisabilityList(ds);
                //partADTO = partADAO.getCampDetails(ds, campId);
            } else {
                disabilityList = partADAO.getDisabilityList(ds);
                //partADTO = partADAO.getCampDetails(ds, campId);
                disabilityList.remove(5);
            }

            partAForm.setDisabilityList(disabilityList);
            request.setAttribute("disforPrint", partADTO.getDisabilityId());
            partAForm = populateForm(partADTO.getDisabilityId(), form, ds, "yes", campId);


            disabilityLocoSubIds = convertToIntArray(partADTO.getDisabilitySubIds());
            disabilityLocoSubSubIds = convertToIntArray(partADTO.getDisabilitySubSubIds());
            conditiondisability = convertToIntArray(partADTO.getConditiondisabilityIds());
            relation = convertToIntArray(partADTO.getRelationStr());
            partAForm.setDisabilityId(partADTO.getDisabilityId());
            partAForm.setDisabilityLocoSubIds(disabilityLocoSubIds);
            partAForm.setDisabilityLocoSubSubIds(disabilityLocoSubSubIds);
            partAForm.setConditiondisability(conditiondisability);
            partAForm.setRelation(relation);

            target = "success";
            request.setAttribute("district_name", district_name);
            request.setAttribute("mandal_name", mandal_name);
            request.setAttribute("village_name", village_name);
            request.setAttribute("panchayat_name", panchayat_name);
            request.setAttribute("habitation_name", habitation_name);
            saveToken(request);
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityDetails", "PartAAction", "Code");
            ////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "getDisabilityDetails");
        }
        return mapping.findForward(target);
    }

    /**
     * this method will update person personal  details into database
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward updatePersonalDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException 
    {
        HttpSession session = request.getSession(true);
        PartAForm partAForm = (PartAForm) form;
        PartADAO partADAO = new PartADAO();
        PartADTO partADTO = new PartADTO();
        int disabilityId = 0;
        String categoryId = null;
        String categoryCount = null;

        //DataSource ds= getDataSource(request);

        if (session.getAttribute("sadaremCodeAu") != null) 
        {
            session.removeAttribute("sadaremCodeAu");
        }

        AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
        String status = request.getParameter("partAStatus");
        String vieweditmode = CommonUtility.checkNullObj(request.getParameter("vieweditmode"));
        boolean partAUpdateOnly = Boolean.parseBoolean(request.getParameter("partAUpdateOnly"));
        String target = "";
        disabilityId = partAForm.getDisabilityId();
        String disabilitySubIds = null;
        String disabilitySubSubIds = null;
        String personcode =  CommonUtility.checkNullObj(session.getAttribute("personcode"));
        String relation = null;
        String loginid = CommonUtility.checkNullObj(session.getAttribute("loginid"));
        String agePersonal = null;
        String Systemip = request.getRemoteAddr();
        int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
        int updateStatus=0;
        // session.removeAttribute("categoryIdAu");
        // session.removeAttribute("categoryCountAu");

        try 
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (status == null) {
                status = "update";
            }

            if (status.equals("update"))
            {
                boolean flag = isTokenValid(request, true);
                ArrayList disabilityList = new ArrayList();
                disabilityList = partADAO.getDisabilityList(ds);
               
               System.out.println("vieweditmode : "+vieweditmode);
                
                
                if(!("View".equalsIgnoreCase(vieweditmode)))
                {
                                    partAForm = populateForm(disabilityId, form, ds, "no", campId);


                                    partAForm.setDisabilityList(disabilityList);

                                    disabilitySubIds = convertToString(partAForm.getDisabilityLocoSubIds());
                                    disabilitySubSubIds = convertToString(partAForm.getDisabilityLocoSubSubIds());
                                    relation = convertToString(partAForm.getRelation());

                                    partAForm.setDisabilitySubIds(disabilitySubIds);
                                    partAForm.setDisabilitySubSubIds(disabilitySubSubIds);
                                    partAForm.setRelationStr(relation);
                                    partAForm.setCampid(campId); 
                                    partAForm.setLoginid(loginid); 

                                    agePersonal = appletAuthorityService.getAge(ds, personcode);
                                    session.setAttribute("agePersonal", agePersonal);
                                    partAForm.setDistrict((String) request.getParameter("district_id"));
                                    partAForm.setMandal((String) request.getParameter("mandal_id"));
                                    partAForm.setTownVillage((String) request.getParameter("village_id"));
                                    partAForm.setHabitation((String) request.getParameter("habitation_id"));
                                    partAForm.setSystemip(Systemip);
                                    partAForm.setCampid(campId);
                                    String date = partAForm.getDay() + "/" + partAForm.getMonth() + "/" + partAForm.getYear();


//artAForm.getdoctor

                                    //Chrononical age  calculation for Mental Retardation tests.
                                    Date d1 = new GregorianCalendar(Integer.parseInt(CommonUtility.checkNullObj(partAForm.getYear())), Integer.parseInt(CommonUtility.checkNullObj(partAForm.getMonth())), Integer.parseInt(CommonUtility.checkNullObj(partAForm.getDay()))).getTime();
                                    Date today = new Date();
                                    long diff = today.getTime() - d1.getTime();
                                    long days = diff / (1000 * 60 * 60 * 24);

                                    double years = (double) days / 365;

                                    session.setAttribute("chronologicalage", new Double(years));

                                    partAForm.setDobday(date);

                                    /** Added by mohan on 13/09/2011 for Appeal authority update to maintain the
                                    Categoryid and categoryCount **/
                                    int personStatus = 0;
                                    //  categoryId=(String)session.getAttribute("categoryIdAu");
                                    personStatus = appletAuthorityService.checkStatusForAppealCheck(ds, personcode, "2");


                                    if (session.getAttribute("roleId").equals(5) || personStatus != 0) {

                                        // categoryId = appletAuthorityService.getCategoryId(ds, personcode);
                                        //added by rekha --> for appellate and temporary
                                        categoryCount = appletAuthorityService.getCategoryCount(ds, personcode, "2");
                                        categoryId = "2";
                                        //end
                                        session.setAttribute("categoryIdUpdateAu", categoryId);
                                        session.setAttribute("categoryCountUpdateAu", categoryCount);
                                    }
                                    //added by rekha --> for appellate and temporary
                                    if (personStatus == 0) {
                                        personStatus = appletAuthorityService.checkStatusForAppealCheck(ds, personcode, "3");
                                    }
                                    categoryId = "3";
                                    if (session.getAttribute("roleId").equals(5) || personStatus != 0) {

                                        // categoryId = appletAuthorityService.getCategoryId(ds, personcode);
                                        categoryCount = appletAuthorityService.getCategoryCount(ds, personcode, "3");
                                        session.setAttribute("categoryIdUpdateAu", categoryId);
                                        session.setAttribute("categoryCountUpdateAu", categoryCount);
                                    }//end
                                    
                                    if (personStatus == 0)
                                    {
                                        session.removeAttribute("categoryIdUpdateAu");
                                        session.removeAttribute("categoryCountUpdateAu");
                                        session.setAttribute("categoryIdUpdateAu", null);
                                        session.setAttribute("categoryCountUpdateAu", null);
                                    }

                                    /** ----------------------------------------------- END Mohan Code------------------------------------------------ **/
                                    BeanUtils.copyProperties(partADTO, partAForm);
                                    //added by kavya


                                    if (partAForm.getCard() != null && partAForm.getRationCardSlno() != null && request.getParameter("readonly") != null) 
                                    {
                                        URL url1 = new URL(CommonConstants.Url1);
                                        // URL url2 = new URL(CommonConstants.Url2);

                                       // AponlineWSClient wsClient = new AponlineWSClient();
                                        String message = null;
                                        String rationcardtype = partAForm.getCard().substring(0, 3);
                                        String districtid = (String) session.getAttribute("districtId");
					//                    if (rationcardtype.equalsIgnoreCase("RAP") || rationcardtype.equalsIgnoreCase("TAP")) {
					                        message = partADAO.getRAPTAPRationCardSerialNumberStatus(ds, partAForm.getCard().toString(), partAForm.getRationCardSlno().toString(), districtid);
					//                    } else {
					                                        //message = wsClient.getRationcardSerialNumbers(ds, url1, null, partAForm.getCard().toString(), partAForm.getRationCardSlno().toString(), request);
					//                    }
                                        if (message != null && message.equalsIgnoreCase(CommonConstants.SUCCESS)) {
                                            message = partADAO.getAssesmentStatusRationcardAndSlnoStatus(ds, partADTO.getCard(), partADTO.getRationCardSlno(), personcode);

                                        }

                                        if (message != null && message.equalsIgnoreCase(CommonConstants.ERROR)) 
                                        {
                                            request.setAttribute("MSG", "You Have Entered Invalid Rationcard serial number ");
                                            flag = false;
                                            return mapping.findForward("exception");
                                        } 
                                        else if (message != null && !message.equalsIgnoreCase(CommonConstants.SUCCESS)) 
                                        {
                                            request.setAttribute("MSG", message);
                                            flag = false;
                                            return mapping.findForward("exception");
                                        }
                                    }
                                    //ended
                                    
                                    if (flag)
                                    {
                                        partADAO.updatePersonalDetails(personcode, partADTO, ds, request);
                                    }

                                    if (partAUpdateOnly) 
                                    {
                                        request.setAttribute("msg", "Part-A details Updated Successfully");
                                        target = "partAUpdate";
                                    } 
                                    else if (partADTO.getPersonstatus().equals("Eligible")) 
                                    {
                                        target = "finish";
                                    }
                                    else 
                                    {
                                        target = "rejected";
                                    }
                }
                else
                {
                	 
                	 loginid = (String) session.getAttribute("loginid");
                     partAForm.setLoginid(loginid);
                     personcode = (String) session.getAttribute("personcode");
                	 BeanUtils.copyProperties(partADTO, partAForm);
                     updateStatus = partADAO.updatePartADetails(partADTO,personcode);
                     if(updateStatus>0)
                     {
                    	 request.setAttribute("msg", "Part-A details Updated Successfully");
                         target = "partAUpdate"; 
                     }
                     else
                     {
                    	 request.setAttribute("MSG", "Part A updation failed.");
                    	 return mapping.findForward("exception");
                     }
                }
              session.setAttribute("personstatus", partADTO.getPersonstatus());


            }
        } 
        catch (Exception sqlEx) 
        {
            target = "exception";
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonalDetails", "PartAAction", "Code");
            ////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "updatePersonalDetails");
        }
       // System.out.println("target : "+target);
        return mapping.findForward(target); 
    }

    /**
     * this method will update disability details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward updateDisabilityDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        HttpSession session = request.getSession(true);
        PartAForm partAForm = (PartAForm) form;
        request.setAttribute("selectedValue", "PartAUpdate");
        PartADAO partADAO = new PartADAO();
        PartADTO partADTO = new PartADTO();
        String target = null;
        int disabilityId = 0;
        //DataSource ds= getDataSource(request);
        session.removeAttribute("Mdspecialistprefix");
        session.removeAttribute("des");


        String status = request.getParameter("partAStatus");
        int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
        String personstatus = null;//(String) session.getAttribute("personstatus");


        if (session.getAttribute("personStatusForAU") != null) {
            personstatus = (String) session.getAttribute("personStatusForAU");
        } else {
            personstatus = (String) session.getAttribute("personstatus");
        }


        String previousdisabilityid = request.getParameter("previousdisabilityid");
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (partAForm.getYearsfortemporary() == null) {
                partAForm.setYearsfortemporary("0");
            }
            target = "repeat";
            disabilityId = partAForm.getDisabilityId();
            String disabilitySubIds = null;
            String disabilitySubSubIds = null;
            String conditiondisabilityIds = null;
            String personcode = null;
            String relation = null;
            String loginid = null;
            String Systemip = request.getRemoteAddr();


            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {
                partAForm.setCongenital(false);
                partAForm.setCongenitalbettereye(false);
                partAForm.setCongenitalworseeye(false);
                partAForm.setHereditary(false);
                partAForm.setHereditarybettereye(false);
                partAForm.setHereditaryworseeye(false);
                partAForm.setBirthinjury(false);
                partAForm.setBirthinjurybettereye(false);
                partAForm.setBirthinjuryworseeye(false);
                partAForm.setDiseaseInfection(false);
                partAForm.setDiseaseInfectionbettereye(false);
                partAForm.setDiseaseInfectionworseeye(false);
                partAForm.setMalnutrition(false);
                partAForm.setHighfever(false);
                partAForm.setBirthasphyxia(false);
                partAForm.setEpilepsy(false);
                partAForm.setMalnutritionbettereye(false);
                partAForm.setMalnutritionworseeye(false);
                partAForm.setAccident(false);
                partAForm.setAccidentbettereye(false);
                partAForm.setAccidentworseeye(false);
                partAForm.setOthertypeofdisability("");
                partAForm.setDiagnosisofdisability("");
                int[] condisability = {0};
                partAForm.setConditiondisability(condisability);
                partAForm.setConditionindisability("");
                partAForm.setKindofdisability("");
                partAForm.setYearsfortemporary("0");
                partAForm.setOtherconditionofdisability("");

                //partAForm.setHospitalname("");
                //partAForm.setHospitaladdress("");
                partAForm.setDoctor1name("");
                partAForm.setDoctor2name("");
                partAForm.setDoctor3name("");
                partAForm.setDoctor1designation("");
                partAForm.setDoctor2designation("");
                partAForm.setDoctor3designation("");
                partAForm.setDoctor1regnumber("");
                partAForm.setDoctor2regnumber("");
                partAForm.setDoctor3regnumber("");

                partAForm.setOthertypeofdisability("");
                partAForm.setReferredto("");
                partAForm.setSurgery("");
                partAForm.setLowvisionaid("");
                partAForm.setCouncellingandguidance("");
                partAForm.setAnyotherneed("");
                partAForm.setPhychotherapy("");
                partAForm.setPhysiotherapy("");
                partAForm.setAnyotherneed("");
                partAForm.setHearingaid("");
                partAForm.setBehaviourmodification("");
                partAForm.setAdmissioninpsychiatrichospital("");
                partAForm.setPhysiotherapy("");

                partAForm.setF_can(null);
                partAForm.setPp_can(null);
                partAForm.setL_can(null);
                partAForm.setKc_can(null);
                partAForm.setB_can(null);
                partAForm.setS_can(null);
                partAForm.setSt_can(null);
                partAForm.setW_can(null);
                partAForm.setSe_can(null);
                partAForm.setRw_can(null);
                partAForm.setH_can(null);
                ArrayList disabilityList = new ArrayList();
                disabilityList = partADAO.getDisabilityList(ds);
                partAForm = populateForm(disabilityId, form, ds, "no", campId);
                partAForm.setDisabilityList(disabilityList);
                partAForm.setDisabilityLocoSubIds(null);
                partAForm.setDisabilityLocoSubSubIds(null);
                if (previousdisabilityid != null && !"".equals(previousdisabilityid)) {
                    request.setAttribute("olddisablityid", Integer.parseInt(previousdisabilityid));
                }
                //  session.setAttribute("desig", partAForm.getdoc);
            } else {
                disabilitySubIds = convertToString(partAForm.getDisabilityLocoSubIds());
                disabilitySubSubIds = convertToString(partAForm.getDisabilityLocoSubSubIds());
                conditiondisabilityIds = convertToString(partAForm.getConditiondisability());
                relation = convertToString(partAForm.getRelation());

                partAForm.setDisabilitySubIds(disabilitySubIds);
                partAForm.setDisabilitySubSubIds(disabilitySubSubIds);
                partAForm.setConditiondisabilityIds(conditiondisabilityIds);
                partAForm.setRelationStr(relation);
                loginid = (String) session.getAttribute("loginid");
                partAForm.setLoginid(loginid);
                //   personcode = (String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");

                } else {

                    personcode = (String) session.getAttribute("personcode");

                }

                partAForm.setPersonstatus(personstatus);
                partAForm.setSystemip(Systemip);

                // Added for appending Dr. Prefix to first doctor name
                if (partAForm.getDisabilityId() == 4) {
                    String doctorName = null;
                    if (partAForm.getSpecialistprefix().equals("Dr")) {

                        if (!partAForm.getDoctor1name().substring(0, 1).equals("Dr")) {
                            doctorName = partAForm.getSpecialistprefix().concat(".").concat(" ").concat(partAForm.getDoctor1name());

                        }
                    } else {

                        doctorName = partAForm.getDoctor1name();
                        String e = partAForm.getSpecialistprefix();
                        if (e != null) {
                            e = e.toLowerCase();
                            if (e.contains("psychologist")) {
                                session.setAttribute("des", "yes");

                            }
                        }



                    }
                    //  String doc=partAForm.getDoctor1name();

                    //  String s=doc.s
                    //  if(!doc.startsWith("Dr.")){

                    //  session.setAttribute("des", "yes");
                    //  }

                    partAForm.setDoctor1name(doctorName);
                } else {
                    if (!"".equals(partAForm.getSpecialistprefix()) && partAForm.getSpecialistprefix() != null) {
                        if (!partAForm.getDoctor1name().startsWith("Dr")) {
                            String doctorName = partAForm.getSpecialistprefix().concat(".").concat(" ").concat(partAForm.getDoctor1name());
                            partAForm.setDoctor1name(doctorName);
                        }
                    }
                }
                // endedd for appending Dr. Prefix to first doctor name


                BeanUtils.copyProperties(partADTO, partAForm);
                partADTO.setSystemip(request.getRemoteAddr());


                // added for Role Id Check Flag for Enable and Disable the Disability Links.
                CommonDetails commonDetails = new CommonDetails();
                int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
                boolean disabilityEditFlag = commonDetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.DISABILITY_DETAILS_EDIT_PERMISSION_IDS);
                session.setAttribute("disabilityEditFlag", disabilityEditFlag);


                //int disabilityid = ((Integer) session.getAttribute("disabilityid")).intValue();
                if (previousdisabilityid != null && !"".equals(previousdisabilityid)) {
                    int disabilityid = Integer.parseInt(previousdisabilityid);
                    session.setAttribute("disabilityid", new Integer(partAForm.getDisabilityId()));
                    if (disabilityid != partAForm.getDisabilityId()) {
//                    session.setAttribute("totalphysical", new Double(0));
//                    session.setAttribute("upperextrimity", new Double(0));
//                    session.setAttribute("lowerextremity", new Double(0));
//                    session.setAttribute("amputation", new Double(0));
//                    session.setAttribute("transverse", new Double(0));
//                    session.setAttribute("trunk", new Double(0));
//                    session.setAttribute("Evaluation", new Double(0));
//                    session.setAttribute("cardiopulmonary", new Double(0));
//                    session.setAttribute("dwarfism", new Double(0));
//                    session.setAttribute("hearingimpairment", new Double(0));
//                    session.setAttribute("visualimpairment", new Double(0));
//                    session.setAttribute("mentalretardation", new Double(0));
//                    session.setAttribute("mentalillness", new Double(0));

                        //stored procedure to delete previous disability details
                        partADAO.deletePreviousDisabilityDetailsForUpdateDisability(ds, personcode);

                    }
                }
                if (partAForm.getDisabilityId() == 6 && partADTO.getPersonstatus().equals("Rejected")) {
                    target = "failure";
                } else {
                    boolean flag = isTokenValid(request, true);
                    if (partAForm.getDisabilityId() == 6) {
                        Map multipleDisabilityMap = new HashMap();
                        ArrayList multipleDisabilityList = null;
                        ArrayList dbDisabilityIdList = null;
                        String ohdoctor = request.getParameter("ohdoctor");
                        String vidoctor = request.getParameter("vidoctor");
                        String hidoctor = request.getParameter("hidoctor");
                        String mrdoctor = request.getParameter("mrdoctor");
                        String midoctor = request.getParameter("midoctor");
                        session.setAttribute("ohdoctor", ohdoctor);
                        session.setAttribute("vidoctor", vidoctor);
                        session.setAttribute("hidoctor", hidoctor);
                        session.setAttribute("mrdoctor", mrdoctor);
                        session.setAttribute("midoctor", midoctor);
                        session.setAttribute("disabilityid", partAForm.getDisabilityId());
                        multipleDisabilityList = new ArrayList();
                        if (ohdoctor != null) {
                            multipleDisabilityList.add(ohdoctor);
                        }
                        if (vidoctor != null) {
                            multipleDisabilityList.add(vidoctor);
                        }
                        if (hidoctor != null) {
                            multipleDisabilityList.add(hidoctor);
                        }
                        if (mrdoctor != null) {
                            multipleDisabilityList.add(mrdoctor);
                        }
                        if (midoctor != null) {
                            multipleDisabilityList.add(midoctor);
                        }
                        String[] multipleDisabilityID = (String[]) multipleDisabilityList.toArray(new String[0]);
                        for (int i = 0; i < multipleDisabilityID.length; i++) {
                            String id = multipleDisabilityID[i];
                            if (id.equals("1")) {
                                PartADTO multipleDisabilityDTO1 = new PartADTO();
                                multipleDisabilityDTO1.setDoctorname(partAForm.getDoctornameOH());
                                multipleDisabilityDTO1.setDoctorregnumber(partAForm.getDoctorregnumberOH());
                                multipleDisabilityDTO1.setDoctordesignation(partAForm.getDoctordesignationOH());
                                multipleDisabilityMap.put(id, multipleDisabilityDTO1);
                            }
                            if (id.equals("2")) {
                                PartADTO multipleDisabilityDTO2 = new PartADTO();
                                multipleDisabilityDTO2.setDoctorname(partAForm.getDoctornameVI());
                                multipleDisabilityDTO2.setDoctorregnumber(partAForm.getDoctorregnumberVI());
                                multipleDisabilityDTO2.setDoctordesignation(partAForm.getDoctordesignationVI());
                                multipleDisabilityMap.put(id, multipleDisabilityDTO2);

                            }
                            if (id.equals("3")) {
                                PartADTO multipleDisabilityDTO3 = new PartADTO();
                                multipleDisabilityDTO3.setDoctorname(partAForm.getDoctornameHI());
                                multipleDisabilityDTO3.setDoctorregnumber(partAForm.getDoctorregnumberHI());
                                multipleDisabilityDTO3.setDoctordesignation(partAForm.getDoctordesignationHI());
                                multipleDisabilityMap.put(id, multipleDisabilityDTO3);

                            }
                            if (id.equals("4")) {
                                PartADTO multipleDisabilityDTO4 = new PartADTO();

                                /** Added by rekha on 16/09/2011 **/
                                String doctorName = null;
                                if (partAForm.getMdspecialistprefix().equals("Dr")) {
                                    if (!partAForm.getDoctornameMR().substring(0, 1).equals("Dr")) {
                                        doctorName = partAForm.getMdspecialistprefix().concat(".").concat(" ").concat(partAForm.getDoctornameMR());
                                    }
                                } else {
                                    doctorName = partAForm.getDoctornameMR();
                                    String e = partAForm.getMdspecialistprefix();
                                    if (e != null) {
                                        e = e.toLowerCase();
                                        if (e.contains("psychologist")) {
                                            session.setAttribute("des", "yes");
                                        }
                                    }
                                }
                                multipleDisabilityDTO4.setDoctorname(doctorName);

                                /** End  */
                                //   multipleDisabilityDTO4.setDoctorname(partAForm.getDoctornameMR());
                                multipleDisabilityDTO4.setDoctorregnumber(partAForm.getDoctorregnumberMR());
                                multipleDisabilityDTO4.setDoctordesignation(partAForm.getDoctordesignationMR());
                                multipleDisabilityMap.put(id, multipleDisabilityDTO4);

                            }
                            if (id.equals("5")) {
                                PartADTO multipleDisabilityDTO5 = new PartADTO();
                                multipleDisabilityDTO5.setDoctorname(partAForm.getDoctornameMI());
                                multipleDisabilityDTO5.setDoctorregnumber(partAForm.getDoctorregnumberMI());
                                multipleDisabilityDTO5.setDoctordesignation(partAForm.getDoctordesignationMI());
                                multipleDisabilityMap.put(id, multipleDisabilityDTO5);
                            }

                        }
                        dbDisabilityIdList = partADAO.getMultipleDisabilityDisabilityIds(ds, personcode);
                        for (int i = 0; i < dbDisabilityIdList.size(); i++) {
                            List inputArrayList = Arrays.asList(multipleDisabilityID);
                            Set inputArraySet = new HashSet(inputArrayList);
                            boolean flagArray = inputArraySet.contains(dbDisabilityIdList.get(i).toString());
                            //  Delete previous details of disability
                            if (!flagArray) {
                                if ("1".equals(dbDisabilityIdList.get(i))) {
                                    partADAO.deletePreviousLocomotorDetails(ds, personcode);
                                } else if ("2".equals(dbDisabilityIdList.get(i))) {
                                    partADAO.deletePreviousVisualImpaimentDetails(ds, personcode);
                                } else if ("3".equals(dbDisabilityIdList.get(i))) {
                                    partADAO.deletePreviousHearingImpairmentDetails(ds, personcode);
                                } else if ("4".equals(dbDisabilityIdList.get(i))) {
                                    partADAO.deletePreviousMentalRetardationDetails(ds, personcode);
                                } else if ("5".equals(dbDisabilityIdList.get(i))) {
                                    partADAO.deletePreviousMentalIllnessDetails(ds, personcode);
                                }
                            }
                        }

                        partADAO.updateMultipleDoctors(ds, personcode, multipleDisabilityID, multipleDisabilityMap, loginid, Systemip, request);
                        session.setAttribute("multipleDisabilityList", multipleDisabilityList);
                        session.setAttribute("Mdspecialistprefix", partADTO.getMdspecialistprefix());

                    }

                    //  if(session.getAttribute("sadaremCodeAu")!=null) {
                    //       partADAO.insertDisabilityDetails(personcode, partADTO, ds,request);
                    //   }else {

                    if (flag) {
                        partADAO.updateDisabilityDetails(personcode, partADTO, ds, request);
                    }
                    // }
                    DisabilityIdForm disabilityIdForm = null;
                    disabilityIdForm = new DisabilityIdForm();

                    disabilityIdForm.setDisabilityId(String.valueOf(partAForm.getDisabilityId()));
                    session.setAttribute("disabilityIdForm", disabilityIdForm);
                    target = "finish";
                }
            }

        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateDisabilityDetails", "PartAAction", "Code");
            ////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "updateDisabilityDetails");
        }
        return mapping.findForward(target);
    }

    /**
     * this method will convert the integer array to string
     * @param intArray
     * @return String
     */
    public String convertToString(int[] intArray) {
        StringBuffer stringBuffer = new StringBuffer();
        if (intArray != null) {
            for (int i = 0; i < intArray.length; i++) {
                if (intArray[i] != 0) {
                    stringBuffer.append(intArray[i]);
                    if (i + 1 < intArray.length) {
                        if (intArray[i + 1] != 0) {
                            stringBuffer.append(",");
                        }
                    }
                }

            }
            String support = stringBuffer.toString();
            if (support.length() != 0) {
                return support;
            } else {
                return null;
            }

        } else {
            return null;
        }

    }

    /**
     * this method converts String to integer array
     * @param inputString
     * @return integer array
     */
    public int[] convertToIntArray(String inputString) {

        if (inputString != null) {
            String[] stringArray = inputString.split(",");
            int[] intArray = new int[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                intArray[i] = Integer.valueOf(stringArray[i]).intValue();

            }
            return intArray;
        } else {
            return null;
        }

    }

    /**
     * this method converts data of birth to years based on todate of the system
     * @param a
     * @return float
     */
    public float convertDOBToYears(String a) {
        String[] stringArray;
        float b = 0;
        return b;
    }

    /**
     * this will populate the form
     * @param disabilityId
     * @param form
     * @param ds
     * @throws org.bf.disability.Exception.SADAREMException
     * @return form bean
     */
    public PartAForm populateForm(int disabilityId, ActionForm form, DataSource ds, String decide, int campId) throws SADAREMDBException, SQLException {
        PartAForm partAForm = (PartAForm) form;
        ArrayList disabilityLocoSubList = new ArrayList();
        ArrayList disabilityLocoSubSubList = new ArrayList();
        ArrayList disabilityVisualSubList = new ArrayList();
        ArrayList disabilityHearingSubList = new ArrayList();
        ArrayList disabilityMentalRetdSubList = new ArrayList();
        ArrayList disabilityMentalIllnessSubList = new ArrayList();
        PartADAO partADAO = new PartADAO();
        DoctorsInformationDAO doctorsInformationDAO = new DoctorsInformationDAO();
        DoctorsInformationDTO doctorsInformationDTO = new DoctorsInformationDTO();
        try {
            doctorsInformationDTO = doctorsInformationDAO.selectDoctorDetails(ds, "", campId);

            if (disabilityId != 0) {
                switch (disabilityId) {
                    case 1: {
                        if (decide.equals("no")) {
                            doctorsInformationDTO = doctorsInformationDAO.selectDoctorDetails(ds, "Locomotor/OH", campId);
                        }
                        disabilityLocoSubList = partADAO.getDisabilityLocoSubList(disabilityId, ds);
                        partAForm.setDisabilityLocoSubList(disabilityLocoSubList);
                        disabilityLocoSubSubList = partADAO.getDisabilityLocoSubSubList(disabilityId, ds);
                        partAForm.setDisabilityLocoSubSubList(disabilityLocoSubSubList);
                        break;
                    }
                    case 2: {
                        if (decide.equals("no")) {
                            doctorsInformationDTO = doctorsInformationDAO.selectDoctorDetails(ds, "Visual Impairment", campId);
                        }
                        disabilityVisualSubList = partADAO.getDisabilityLocoSubList(disabilityId, ds);
                        partAForm.setDisabilityVisualSubList(disabilityVisualSubList);
                        break;
                    }
                    case 3: {
                        if (decide.equals("no")) {
                            doctorsInformationDTO = doctorsInformationDAO.selectDoctorDetails(ds, "Hearing Impairment", campId);
                        }
                        //disabilityHearingSubList = partADAO.getDisabilityLocoSubList(disabilityId,ds);
                        //partAForm.setDisabilityHearingSubList(disabilityHearingSubList);
                        break;
                    }
                    case 4: {
                        if (decide.equals("no")) {
                            doctorsInformationDTO = doctorsInformationDAO.selectDoctorDetails(ds, "Mental Retardation", campId);
                        }
                        // disabilityMentalRetdSubList = partADAO.getDisabilityLocoSubList(disabilityId,ds);
                        //partAForm.setDisabilityMentalRetdSubList(disabilityMentalRetdSubList);
                        break;
                    }
                    case 5: {
                        if (decide.equals("no")) {
                            doctorsInformationDTO = doctorsInformationDAO.selectDoctorDetails(ds, "Mental Illness", campId);
                        }
                        //disabilityMentalIllnessSubList = partADAO.getDisabilityLocoSubList(disabilityId,ds);
                        //partAForm.setDisabilityMentalIllnessSubList(disabilityMentalIllnessSubList);
                        break;
                    }
                    case 6: {
                        doctorsInformationDTO = doctorsInformationDAO.selectDoctorDetails(ds, "Multiple Disability", campId);
                        disabilityLocoSubList = partADAO.getDisabilityLocoSubList(1, ds);
                        partAForm.setDisabilityLocoSubList(disabilityLocoSubList);
                        disabilityLocoSubSubList = partADAO.getDisabilityLocoSubSubList(1, ds);
                        partAForm.setDisabilityLocoSubSubList(disabilityLocoSubSubList);

                        disabilityVisualSubList = partADAO.getDisabilityLocoSubList(2, ds);
                        partAForm.setDisabilityVisualSubList(disabilityVisualSubList);

                        //disabilityHearingSubList = partADAO.getDisabilityLocoSubList(3,ds);
                        //partAForm.setDisabilityHearingSubList(disabilityHearingSubList);

                        //disabilityMentalRetdSubList = partADAO.getDisabilityLocoSubList(4,ds);
                        //partAForm.setDisabilityMentalRetdSubList(disabilityMentalRetdSubList);

                        //disabilityMentalIllnessSubList = partADAO.getDisabilityLocoSubList(5,ds);
                        //partAForm.setDisabilityMentalIllnessSubList(disabilityMentalIllnessSubList);

                        break;
                    }
                    case 7: {
                        break;
                    }
                }

                if (decide.equals("no")) {
                    // partAForm.setHospitalname(doctorsInformationDTO.getHospitalname());
                    //partAForm.setHospitaladdress(doctorsInformationDTO.getHospitaladdress());
                    partAForm.setSpecialistprefix(doctorsInformationDTO.getSpecialistprefix());
                    partAForm.setDoctor1name(doctorsInformationDTO.getDoctorname1());
                    partAForm.setDoctor2name(doctorsInformationDTO.getDoctorname2());
                    partAForm.setDoctor3name(doctorsInformationDTO.getDoctorname3());
                    partAForm.setDoctor1designation(doctorsInformationDTO.getDesignation1());
                    partAForm.setDoctor2designation(doctorsInformationDTO.getDesignation2());
                    partAForm.setDoctor3designation(doctorsInformationDTO.getDesignation3());
                    partAForm.setDoctor1regnumber(doctorsInformationDTO.getRegisterno1());
                    partAForm.setDoctor2regnumber(doctorsInformationDTO.getRegisterno2());
                    partAForm.setDoctor3regnumber(doctorsInformationDTO.getRegisterno3());
                }
            }
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "populateForm", "PartAAction", "Code");
            ////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "populateForm");
        }
        return partAForm;
    }

    public ActionForward repeatPartAAddToRestrtictPartB(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String target = "partADetailsInsert";
        try {
            HttpSession session = request.getSession(true);
            if (request.getParameter("restrictPartA") != null) {
                if ("true".equals((String) request.getParameter("restrictPartA"))) {
                    request.setAttribute("restrictPartA", "true");
                    target = "partADetailsInsert";
                } else if ("pensionNumberRestrictPartA".equals((String) request.getParameter("restrictPartA"))) {
                    request.setAttribute("restrictPartA", "pensionNumberRestrictPartA");
                    target = "pensionNumberRestrictPartA";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);


    }

    /**
     * this method will get the personal details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward getPersonalDetailsForUpdate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = null;
        String districtid = null;
        int[] relation = null;
        //DataSource ds= getDataSource(request);


        String personstatus = null;
        String personcode = null;
        String districtname = null;


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession(true);
            PartAForm partAForm = (PartAForm) form;
            PartADTO partADTO = new PartADTO();
            PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            ArrayList habitationlist = new ArrayList();
            ArrayList personStatusList = new ArrayList();

            personcode = partAForm.getPersoncode();
            districtid = (String) session.getAttribute("districtId");
            
            CommonDAOImpl comObj = new CommonDAOImpl();
            HashMap GEODtls = new HashMap();
			GEODtls=comObj.getGEODetailsbySADAREMID(personcode);
            
            //Added code for Checking person code based on district id
            //String personcodeDistrictId = personcode.substring(0, 2);
			String personcodeDistrictId = GEODtls.get("districtid").toString();
            if (districtid.equals(personcodeDistrictId)) {
                session.setAttribute("personcode", personcode);
                TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
                personStatusList = territoryservice.getPersonStatus(ds, personcode);
                if (personStatusList.size() == 0) {
                    request.setAttribute("msg", "Please Enter Valid Personcode");
                    target = "PersoncodeFailure";
                } else {
                    Iterator iterator = personStatusList.iterator();
                    iterator.hasNext();
                    TerritoryDTO territoryDTO = (TerritoryDTO) iterator.next();
                    personstatus = territoryDTO.getPersonstatus();
                    districtname = territoryDTO.getDistrict_name();
                    partADTO = partAService.getPersonalDetails(personcode, personstatus, ds, campId);
                    String[] stringArray = partADTO.getDobday().split("/");
                    partADTO.setDay(stringArray[0]);
                    partADTO.setMonth(stringArray[1]);
                    partADTO.setYear(stringArray[2]);
                    //Chrononical age  calculation for Mental Retardation tests.
                    Date d1 = new GregorianCalendar(Integer.parseInt(partADTO.getYear()), Integer.parseInt(partADTO.getMonth()), Integer.parseInt(partADTO.getDay())).getTime();
                    Date today = new Date();
                    long diff = today.getTime() - d1.getTime();
                    long days = diff / (1000 * 60 * 60 * 24);
                    double years = (double) days / 365;
                    session.setAttribute("chronologicalage", new Double(years));
                    String teluguname = partADTO.getTelugupersonname();
                    String telugufathername = partADTO.getTelugufathername();
                    session.setAttribute("teluguname", teluguname);
                    session.setAttribute("telugufathername", telugufathername);
                    session.setAttribute("Name", partADTO.getFirstname());
                    BeanUtils.copyProperties(partAForm, partADTO);
                    if (partADTO.getDistrict() != null && !"".equals(partADTO.getDistrict())) {
                        mandallist = partAService.getMandalsList(ds, partADTO.getDistrict());
                        partAForm.setMandallist(mandallist);
                    }
                    if (partADTO.getDistrict() != null && !"".equals(partADTO.getDistrict())
                            && partADTO.getMandal() != null && !"".equals(partADTO.getMandal())) {
                        villagelist = partAService.getVillagesList(ds, partADTO.getDistrict(), partADTO.getMandal());
                        partAForm.setVillagelist(villagelist);
                    }
                    if (partADTO.getDistrict() != null && !"".equals(partADTO.getDistrict())
                            && partADTO.getMandal() != null && !"".equals(partADTO.getMandal())
                            && partADTO.getTownVillage() != null && !"".equals(partADTO.getTownVillage())) {
                        habitationlist = partAService.getHabitationsList(ds, partADTO.getDistrict(), partADTO.getMandal(), partADTO.getTownVillage());
                        partAForm.setHabitationlist(habitationlist);
                    }
                    partAForm.setRelation(relation);
                    request.setAttribute("habitationcode", partADTO.getHabitation());
                    request.setAttribute("districtname", districtname);
                    request.setAttribute("existingmandal_id", partAForm.getMandal());
                    request.setAttribute("existingvillage_id", partAForm.getTownVillage());
                    request.setAttribute("existinghabitation_id", partAForm.getHabitation());
                    target = "success";



                }


            } else {
                request.setAttribute("msg", "Please Enter Valid Personcode");
                target = "PersoncodeFailure";
            }
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetailsForUpdate", "PartAAction", "Code");
            ////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "getPersonalDetailsForUpdate");
        }
        return mapping.findForward(target);
    }

    /**
     * this method will update person personal  details into database
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward updatePersonalCode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        HttpSession session = request.getSession(true);

        String target = "";
//        disabilityId=partAForm.getDisabilityId();
//        String disabilitySubIds=null;
//        String disabilitySubSubIds=null;
        String personcode = null;
        String relation = null;
        String loginid = null;
        String existingloginid = null;
        String Systemip = request.getRemoteAddr();
        ArrayList mandallist = new ArrayList();
        ArrayList villagelist = new ArrayList();
        ArrayList habitationlist = new ArrayList();
        String mandalId = null;
        String villageId = null;
        String NewPersonCode = null;
        String existinghabitation_id = null;
        String existingmandal_id = null;
        String existingvillage_id = null;
        String districtname = null;
        String districtid = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            existingmandal_id = request.getParameter("existingmandal_id");
            existingvillage_id = request.getParameter("existingvillage_id");
            existinghabitation_id = request.getParameter("existinghabitation_id");
            districtid = (String) session.getAttribute("districtId");
            PartAForm partAForm = (PartAForm) form;
            districtname = partAForm.getDistrict();
            PartADAO partADAO = new PartADAO();
            PartADTO partADTO = new PartADTO();
            //DataSource ds= getDataSource(request);

            String status = request.getParameter("status");
            mandalId = partAForm.getMandal();
            villageId = partAForm.getTownVillage();
            PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {
                if (districtid != null && !"".equals(districtid)) {
                    mandallist = partAService.getMandalsList(ds, districtid);
                    partAForm.setMandallist(mandallist);
                }
                if (districtid != null && !"".equals(districtid)
                        && mandalId != null && !"".equals(mandalId)) {
                    villagelist = partAService.getVillagesList(ds, districtid, mandalId);
                    partAForm.setVillagelist(villagelist);

                }
                if (districtid != null && !"".equals(districtid)
                        && mandalId != null && !"".equals(mandalId)
                        && villageId != null && !"".equals(villageId)) {
                    habitationlist = partAService.getHabitationsList(ds, districtid, mandalId, villageId);
                    partAForm.setHabitationlist(habitationlist);

                }
                request.setAttribute("existingmandal_id", existingmandal_id);
                request.setAttribute("existingvillage_id", existingvillage_id);
                request.setAttribute("existinghabitation_id", existinghabitation_id);
                request.setAttribute("districtname", districtname);
                return mapping.findForward("repeat");

            }

            if (status.equals("finish")) {

                if (existingmandal_id.equals(partAForm.getMandal()) && existingvillage_id.equals(partAForm.getTownVillage())
                        && existinghabitation_id.equals(partAForm.getHabitation())) {
                    request.setAttribute("msg", "you are not modified address of the person");
                    target = "PersoncodeFailure";
                } else {
                    existingloginid = partAForm.getLoginid();
                    relation = convertToString(partAForm.getRelation());
                    partAForm.setRelationStr(relation);
                    loginid = (String) session.getAttribute("loginid");
                    partAForm.setLoginid(loginid);
                    personcode = (String) session.getAttribute("personcode");
                    partAForm.setSystemip(Systemip);
                    partAForm.setDistrict(districtid);
                    String date = partAForm.getDay() + "/" + partAForm.getMonth() + "/" + partAForm.getYear();
                    //Chrononical age  calculation for Mental Retardation tests.
                    Date d1 = new GregorianCalendar(Integer.parseInt(partAForm.getYear()), Integer.parseInt(partAForm.getMonth()), Integer.parseInt(partAForm.getDay())).getTime();
                    Date today = new Date();
                    long diff = today.getTime() - d1.getTime();
                    long days = diff / (1000 * 60 * 60 * 24);
                    double years = (double) days / 365;
                    session.setAttribute("chronologicalage", new Double(years));
                    partAForm.setDobday(date);
                    BeanUtils.copyProperties(partADTO, partAForm);
                    NewPersonCode = partADAO.updatePersonaCode(ds, personcode, partADTO, existingloginid);

                    if (NewPersonCode != null && !"".equals(NewPersonCode)) {
                        target = "success";
                        request.setAttribute("NewPersonCode", NewPersonCode);
                        request.setAttribute("personcode", personcode);
                    }
                }


            }
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePersonalCode", "PartAAction", "Code");
            ////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "updatePersonalCode");
        }
        return mapping.findForward(target);

    }

    //Delete person code details
    public ActionForward deletPersonalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = null;
        PartADAO partADAO = new PartADAO();
        int i;
        //PartADTO partADTO=new PartADTO();

        //DataSource ds= getDataSource(request);



        HttpSession session = request.getSession(true);
        String personcode = (String) session.getAttribute("personcode");

        //String personcode = request.getParameter("personcode");

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            i = partADAO.deletePersonDetails(ds, personcode);
            if (i == 1) {

                partADAO.deletePersonDetails(ds, personcode);
                target = "success";
                request.setAttribute("msg", "Data Deleted Successfully !");
            } else {
                target = "failure";
            }
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "deletPersonalDetails", "PartAAction", "Code");
            ////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "deletPersonalDetails");
        }
        return mapping.findForward(target);
    }

    public boolean compareRoleIds(String currentRoleId, String permisssionIds, String operatorStatus, String personstatus) throws SADAREMDBException, SQLException {
        boolean operatorFlag = false;
        try {
            String[] roleIds = permisssionIds.split(",");
            for (int i = 0; i < roleIds.length; i++) {
                if (currentRoleId != null && !"".equals(currentRoleId)) {
                    if (currentRoleId.equals(roleIds[i])) {
                        operatorFlag = true;
                    } else if (null != operatorStatus && !"".equals(operatorStatus)) {
                        if ((!CommonConstants.VIEW_STATUS.equals(operatorStatus) && CommonConstants.EDIT_STATUS.equals(operatorStatus)) || "true".equals(operatorStatus)) {

                            operatorFlag = true;
                        }
                    }
                }
            }
        } catch (Exception sqlEx) {

            sqlEx.printStackTrace();
        }

        return operatorFlag;
    }

    /**
     * this method will insert personal details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action forward
     */
    public ActionForward insertEmployeePersonalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        PartAForm partAForm = (PartAForm) form;
        PartADAO partADAO = new PartADAO();
        PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
        //DataSource ds= getDataSource(request);
        HttpSession session = request.getSession(true);
        String district_id = (String) request.getParameter("district_id");
        if (district_id == null || "".equals(district_id)) {
            district_id = (String) request.getParameter("districtid");
        }
        String mandal_id = (String) request.getParameter("mandal_id");
        String village_id = (String) request.getParameter("village_id");
        String habitation_id = (String) request.getParameter("habitation_id");
        String panchayat_id = (String) request.getParameter("panchayat_id");
        String assembly_id = (String) request.getParameter("assemblyid");
        String employeeId = (String) request.getParameter("employeeId");
        String partACheckForDuplicatePersonCode = null;
        String personcodemax = null;
        String relation = null;
        String status = null;
        String personcode = null;
        String loginid = null;
        String target = null;

        PartADTO partADTO = new PartADTO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            partACheckForDuplicatePersonCode = partADAO.getPartACheckForDuplicate(ds, district_id, mandal_id, village_id, habitation_id, partAForm.getSurname(), partAForm.getFirstname(), partAForm.getLastname(), partAForm.getNoOfYears(), partAForm.getGender(), partAForm.getGsurname(), partAForm.getHouseno());
            if (partACheckForDuplicatePersonCode != null && !"null".equals(partACheckForDuplicatePersonCode)) {
                request.setAttribute("partACheckForDuplicatePersonCode", partACheckForDuplicatePersonCode);
                return mapping.findForward("duplicate");
            }
            String day = request.getParameter("day");
            String Systemip = request.getRemoteAddr();
            relation = convertToString(partAForm.getRelation());

            partAForm.setRelationStr(relation);
            personcode = (String) session.getAttribute("personcode");
            loginid = (String) session.getAttribute("loginid");
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            partAForm.setLoginid(loginid);
            partAForm.setCampid(campId);
            String district_name = partAForm.getDistrict();
            String mandal_name = partAForm.getMandal();
            String village_name = partAForm.getTownVillage();
            String habitation_name = partAForm.getHabitation();

            partAForm.setDistrict(district_id);
            partAForm.setMandal(mandal_id);
            partAForm.setTownVillage(village_id);
            partAForm.setHabitation(habitation_id);
            if (panchayat_id != null && !"".equals(panchayat_id)) {
                partAForm.setPanchayatiid(panchayat_id);
            }
            String date = partAForm.getDay() + "/" + partAForm.getMonth() + "/" + partAForm.getYear();

            //Chrononical age  calculation for Mental Retardation tests.
            Date d1 = new GregorianCalendar(Integer.parseInt(partAForm.getYear()), Integer.parseInt(partAForm.getMonth()), Integer.parseInt(partAForm.getDay())).getTime();
            Date today = new Date();
            long diff = today.getTime() - d1.getTime();
            long days = diff / (1000 * 60 * 60 * 24);

            double years = (double) days / 365;
            session.setAttribute("chronologicalage", new Double(years));
            partAForm.setDobday(date);
            partAForm.setSystemip(Systemip);
            BeanUtils.copyProperties(partADTO, partAForm);
            String statusofemployee = partADAO.getEmployeeStatus(employeeId, partADTO, ds);
            if (statusofemployee != null && !"".equals(statusofemployee)) {
                if ("true".equals(statusofemployee)) {
                    personcodemax = partAService.insertSerpEmployeesPersonalDetails(partADTO, ds, request);
                    if (partADTO.getPersonstatus().equals("Eligible")) {
                        target = "finish";
                    } else {
                        target = "rejected";
                    }
                } else {
                    target = "invalid";
                    request.setAttribute("msg", "Entered Employee AssesmentCompleted PersonCode is " + statusofemployee);
                }
            }

            session.setAttribute("personstatus", partADTO.getPersonstatus());
            session.setAttribute("personcode", personcodemax);
            session.setAttribute("teluguname", partADTO.getTelugupersonname());
            session.setAttribute("Name", partADTO.getFirstname());

            if (((String) session.getAttribute("restrictPartA") != null) && "true".equals((String) session.getAttribute("restrictPartA"))) {
                request.setAttribute("restrictPartA", (String) session.getAttribute("restrictPartA"));
                target = "restrictPartA";
            } else if (((String) session.getAttribute("restrictPartA") != null) && "pensionNumberRestrictPartA".equals((String) session.getAttribute("restrictPartA"))) {
                request.setAttribute("restrictPartA", (String) session.getAttribute("restrictPartA"));
                target = "restrictPartA";
            }
            if (((String) request.getParameter("restrictPartA") != null) && "true".equals((String) request.getParameter("restrictPartA"))) {
                request.setAttribute("restrictPartA", (String) request.getParameter("restrictPartA"));
                target = "restrictPartA";
            }
            session.removeAttribute("restrictPartA");
            request.setAttribute("district_name", district_name);
            request.setAttribute("mandal_name", mandal_name);
            request.setAttribute("village_name", village_name);
            request.setAttribute("habitation_name", habitation_name);
            request.setAttribute("district_id", district_id);
            request.setAttribute("mandal_id", mandal_id);
            request.setAttribute("village_id", village_id);
            request.setAttribute("habitation_id", habitation_id);
            request.setAttribute("panchayat_id", panchayat_id);
            request.setAttribute("assembly_id", assembly_id);
        } catch (SADAREMDBException sqlEx) {
            partADAO.rollBackEmployeeStatus(employeeId, partADTO, ds);
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertEmployeePersonalDetails", "PartAAction", "Code");
            ////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "insertEmployeePersonalDetails");

        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertEmployeePersonalDetails", "PartAAction", "Code");
            ////throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAAction", "insertEmployeePersonalDetails");
        }
        return mapping.findForward(target);
    }

    /**
     * THis method is for validating the aadhar number with aadhar db through webservice. Added By Mohan
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
      public ActionForward validateAadharNumberWithAadharDB(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException, IOException {
        String msg = null;
        PartADAO partADAO = new PartADAO();
        AadharDetails aadharDetails = new AadharDetails();
        int i = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            response.setHeader("Cache-Control", "private");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setContentType("text/plain");
            if (request.getParameter("aadharNo") != null && request.getParameter("aadharNo").length() > 0) {

                msg = aadharDetails.validateAadhar( request.getParameter("aadharNo").toString());

                if (msg.equalsIgnoreCase("100") || msg.equalsIgnoreCase("101")) {
                    i = partADAO.checkAdharCardNumberMapping(ds, request.getParameter("aadharNo").toString(), request.getParameter("personcode"));

                    if (i > 0) {
                        response.getWriter().write("mapping");
                    } else {
                        response.getWriter().write("success");
                    }
                } else {
                    response.getWriter().write(msg);
                }
            }
        } catch (RemoteException ex) {
            response.getWriter().write("fail");
            ex.printStackTrace();
        } catch (Exception ex) {
            response.getWriter().write("fail");
            ex.printStackTrace();
        }
        return null;
    }

    public ActionForward getCampDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartADAO partADAO = new PartADAO();
        PrintWriter out = null;
        HttpSession session = request.getSession();
        String districtId = null;
        try {
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
            if (session.getAttribute("districtId") != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            ArrayList territory_info = new ArrayList();
            ArrayList territory_id_list = new ArrayList();
            ArrayList territory_name_list = new ArrayList();
            territory_info = partADAO.getCampBasedOnLoginDetailsForPartA(ds, districtId, request.getParameter("disabilityid"));

            territory_name_list = (ArrayList) territory_info.get(0);
            territory_id_list = (ArrayList) territory_info.get(1);
            int id_size = territory_name_list.size();
            int count = 0;
            out.println("<root>");
            if (id_size != 0) {

                out.println("<name>" + id_size + "</name>");
                out.println("<id>" + id_size + "</id>");
                for (count = 0; count < territory_id_list.size(); count++) {
                    out.println("<name>" + (String) territory_name_list.get(count) + "</name>");
                    out.println("<id>" + territory_id_list.get(count) + "</id>");
                }
            } else {
                out.println("<name>" + 0 + "</name>");
                out.println("<id>" + 0 + "</id>");
            }
            out.println("</root>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //out.close();
        }
        return null;
    }

    public ActionForward getCampDateDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartADAO partADAO = new PartADAO();
        PrintWriter out = null;
        HttpSession session = request.getSession();
        String districtId = null;
        try {
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
            if (session.getAttribute("districtId") != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            ArrayList territory_info = new ArrayList();
            ArrayList territory_id_list = new ArrayList();
            territory_info = partADAO.getCampDatesBasedOnLoginDetailsForPartA(ds, districtId, request.getParameter("disabilityid"));
            territory_id_list = (ArrayList) territory_info.get(0);
            int id_size = territory_id_list.size();
            int count = 0;
            out.println("<root>");
            if (id_size != 0) {
                out.println("<name>" + id_size + "</name>");
                out.println("<id>" + id_size + "</id>");
                for (count = 0; count < territory_id_list.size(); count++) {
                    out.println("<name>" + (String) territory_id_list.get(count) + "</name>");
                    out.println("<id>" + (String) territory_id_list.get(count) + "</id>");
                }
            } else {
                out.println("<name>" + 0 + "</name>");
                out.println("<id>" + 0 + "</id>");
            }
            out.println("</root>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return null;
    }
    //jobcardDetails = partADAO.getMultipleCampDetails(ds, districtId, request.getParameter("rowID"), request.getParameter("flag"));

    public ActionForward getMultipleCampDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartADAO partADAO = new PartADAO();
        String jobcardDetails = null;
        PrintWriter out = null;
        HttpSession session = request.getSession();
        String districtId = null;
        try {
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
            if (session.getAttribute("districtId") != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            ArrayList territory_info = new ArrayList();
            ArrayList territory_id_list = new ArrayList();
            //territory_info =  partADAO.getMultipleCampDetails(ds, districtId, request.getParameter("rowID"), request.getParameter("flag"));
            territory_id_list = (ArrayList) territory_info.get(0);
            int id_size = territory_id_list.size();
            int count = 0;
            out.println("<root>");
            if (id_size != 0) {
                out.println("<name>" + id_size + "</name>");
                out.println("<id>" + id_size + "</id>");
                for (count = 0; count < territory_id_list.size(); count++) {
                    out.println("<name>" + (String) territory_id_list.get(count) + "</name>");
                    out.println("<id>" + (String) territory_id_list.get(count) + "</id>");
                }
            } else {
                out.println("<name>" + 0 + "</name>");
                out.println("<id>" + 0 + "</id>");
            }
            out.println("</root>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return null;
    }

    public ActionForward getMultipleDisabilitiesDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartADAO partADAO = new PartADAO();
        String jobcardDetails = null;
        PrintWriter out = null;
        HttpSession session = request.getSession();
        String districtId = null;
        try {
            DataSource ds = null;
            if (session.getAttribute("districtId") != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            response.setHeader("Cache-Control", "private");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setContentType("text/xml");
            out = response.getWriter();
            jobcardDetails = partADAO.getMultipleDisabilitiesDetails(ds, request.getParameter("campid"), districtId, request.getParameter("rowID"), request.getParameter("flag"), request.getParameter("date"));

            out.println("<jobcard>");
            out.println("<jobcard>" + jobcardDetails + "</jobcard>");
            out.println("</jobcard>");

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward getMultipleCampDatesDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartADAO partADAO = new PartADAO();
        String jobcardDetails = null;
        PrintWriter out = null;
        HttpSession session = request.getSession();
        String districtId = null;
        try {
            DataSource ds = null;
            if (session.getAttribute("districtId") != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            response.setHeader("Cache-Control", "private");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setContentType("text/xml");
            out = response.getWriter();
            jobcardDetails = partADAO.getMultipleCampDatesDetails(ds, districtId, request.getParameter("rowID"), request.getParameter("flag"), request.getParameter("campid"));

            out.println("<jobcard>");
            out.println("<jobcard>" + jobcardDetails + "</jobcard>");
            out.println("</jobcard>");

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward getCampDatesDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartADAO partADAO = new PartADAO();
        String jobcardDetails = null;
        PrintWriter out = null;
        HttpSession session = request.getSession();
        String districtId = null;
        try {
            DataSource ds = null;
            if (session.getAttribute("districtId") != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            response.setHeader("Cache-Control", "private");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setContentType("text/xml");
            out = response.getWriter();
            jobcardDetails = partADAO.getCampDatesDetails(ds, districtId, request.getParameter("campid"), request.getParameter("disabilityId"));

            out.println("<jobcard>");
            out.println("<jobcard>" + jobcardDetails + "</jobcard>");
            out.println("</jobcard>");

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward setNoOfcampvalueActionss(ActionMapping mapping, ActionForm form,
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
        return mapping.findForward("webDetails");
    }
}

