/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dao.PersonCodeCheckDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.PartAForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.service.PartAService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.servicefactory.PartAServiceFactory;

import com.tcs.sadarem.util.CommonUtility;


/**
 *
 * @author SADAREM
 */
public class RationcardDetails extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    //static Connection con = null;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PartAForm partAForm = (PartAForm) form;
        DataSource ds = null;
        DataSource civilDs = null;
        String districtid = "";
        ArrayList rationCardDetails = new ArrayList();
        PersonCodeCheckDAO dao = new PersonCodeCheckDAO();
        int updateStatus = 0;
        String[] rationCardSlno = null;
        String[] rationCardHidden = null;
        String target = "success";
        String rationCardSerialNoStatus = null;
        ArrayList rationCardCheckWithWebService = new ArrayList();
        HttpSession session = request.getSession();
        String personcodemax = null;
        ArrayList getRationData = new ArrayList();
        boolean districtLevelAccessFlag = false;
        boolean adminLevelAccessFlag = false;
        ArrayList webServiceList = new ArrayList();

        String webService = "success";
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
//            civilDs = getDataSource(request);
//            if (civilDs == null || "null".equals(civilDs)) {
//                civilDs = JNDIDataSource.getCivilSuppliesConnection();
//            }

            if (request.getParameter("PartArestrict") != null) {
                session.setAttribute("PartArestrict", request.getParameter("PartArestrict"));
            }
            if ("insertPersonalDetails".equalsIgnoreCase(request.getParameter("mode"))) {

                PartADAO partADAO = new PartADAO();
                PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
                //DataSource ds= getDataSource(request);
                // HttpSession session = request.getSession(true);
                String district_id = (String) request.getParameter("district_id");
                String mandal_id = (String) request.getParameter("mandal_id");
                String village_id = (String) request.getParameter("village_id");
                String habitation_id = (String) request.getParameter("habitation_id");
                String panchayat_id = (String) request.getParameter("panchayat_id");
                String assembly_id = (String) request.getParameter("assembly_id");
                String habcode = (String) request.getParameter("habitation_id");
                //  TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
                TerritoryDAO territoryDAO = new TerritoryDAO();
                // personcodemax = territoryDAO.getHabitationCode(district_id, mandal_id, panchayat_id, assembly_id, village_id, habitation_id, ds);
                //   String reasonforstatus = (String) request.getParameter("reasonforstatus");

                String partACheckForDuplicatePersonCode = null;

                String relation = null;
                String status = null;
                String personcode = null;
                String loginid = null;
                //    String target = null;

                PartADTO partADTO = new PartADTO();

                partACheckForDuplicatePersonCode = partADAO.getPartACheckForDuplicate(ds, district_id, mandal_id, village_id, habitation_id, partAForm.getSurname(), partAForm.getFirstname(), partAForm.getLastname(), partAForm.getNoOfYears(), partAForm.getGender(), partAForm.getGsurname(), partAForm.getHouseno());


                if (partACheckForDuplicatePersonCode != null && !"null".equals(partACheckForDuplicatePersonCode)) {
                    request.setAttribute("partACheckForDuplicatePersonCode", partACheckForDuplicatePersonCode);
                    return mapping.findForward("duplicate");
                }
                String aadharNumber = "";
                if (partAForm.getAadharCardNo() != null) {
                    aadharNumber = PartADAO.checkAdharCardNumberWithPensionNo(ds, partAForm.getAadharCardNo(), "AdharCard", null);
                }
                if (!aadharNumber.equals("Invalid") && !aadharNumber.equals("")) {
                    request.setAttribute("alreadyEnterPersonId", PartADAO.checkAdharCardNumberWithPensionNo(ds, aadharNumber, "AdharCardNumber", ""));
                    request.setAttribute("aadharNoPersonCode", "aadharNoPersonCode");
                    request.setAttribute("aadharNoForDisplay", aadharNumber);
//                    partAForm.setFormno("");
//                    partAForm.setFirstname("");
//                    partAForm.setSurname("");
//                    partAForm.setSurnametelugu("");
//                    partAForm.setLastname("");
//                    partAForm.setFirstnametelugu("");
//                    partAForm.setAge("");
//                    partAForm.setGender("0");
//                    partAForm.setDay("0");
//                    partAForm.setMonth("0");
//                    partAForm.setYear("0");
//                    partAForm.setEducation("0");
//                    partAForm.setEmployment("0");
//                    partAForm.setMarital("0");
//                    partAForm.setCaste("0");
//                    partAForm.setRelation(null);
//                    partAForm.setRationCardNo("0");
//                    partAForm.setCard(null);
//                    partAForm.setRationCardSlno(null);
//                    partAForm.setRtype("0");
//                    partAForm.setRationCardNo("");
//                    partAForm.setMole1("");
//                    partAForm.setMole2("");
//                    partAForm.setParents_marriage("");
//                    partAForm.setRelationname("");
//                    partAForm.setGsurname("");
//                    partAForm.setGrelation("0");
//                    partAForm.setFirstnametelugu("");
//                    partAForm.setHouseno("");
//                    partAForm.setDistrict("");
//                    partAForm.setMandal_id("0");
//                    partAForm.setPanchayat_id("0");
//                    partAForm.setVillage_id("0");
//                    partAForm.setHabitation_id("0");
//                    partAForm.setPhoneno("");
//                    partAForm.setEmail("");
//                    partAForm.setPin("");
//                    partAForm.setDisability("");
//                    partAForm.setAadharCardNo(null);
//                    partAForm.setType_disability("");
                    return mapping.findForward("rationduplicate");

                } else {
                //added by kavya
                URL url1 = new URL(CommonConstants.Url1);
                // URL url2 = new URL(CommonConstants.Url2);

                // AponlineWSClient wsClient = new AponlineWSClient();
                String message = null;
                String rationcardtype = partAForm.getRationCardNo().substring(0, 3);
//                if (rationcardtype.equalsIgnoreCase("RAP") || rationcardtype.equalsIgnoreCase("TAP")) {

                message = partADAO.getRAPTAPRationCardSerialNumberStatus(ds, partAForm.getRationCardNo().toString(), partAForm.getRationCardSlno().toString(), district_id);

//                } else {
                // message = wsClient.getRationcardSerialNumbers(ds, url1, null, partAForm.getRationCardNo().toString(), partAForm.getRationCardSlno().toString(), request);
//                }
                if (message != null && message.equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    message = partADAO.getAssesmentStatusRationcardAndSlnoStatus(ds, partAForm.getRationCardNo(), partAForm.getRationCardSlno(), partACheckForDuplicatePersonCode);

                }
                if (message != null && message.equalsIgnoreCase(CommonConstants.ERROR)) {
                    request.setAttribute("MSG", "You Have Entered Invalid Rationcard serial number ");

                    return mapping.findForward("exception");
                } else if (message != null && !message.equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    request.setAttribute("MSG", message);
                    return mapping.findForward("exception");
                }

//ended by kavya
                String day = request.getParameter("day");
                String Systemip = request.getRemoteAddr();

                relation = convertToString(partAForm.getRelation());

                partAForm.setRelationStr(relation);
                // personcode = (String) session.getAttribute("personcode");
                loginid = CommonUtility.checkNullObj(session.getAttribute("loginid"));
                int campId = 0;
                if (session.getAttribute("campId") != null) {
                    campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
                }
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
                //  partAForm.setReasonforstatus(reasonforstatus);
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


                //   PartADAO partADAO = new PartADAO();

                boolean duplicate_slno = partADAO.isDuplicateSlno(partADTO, ds, request);
                if (!duplicate_slno) {
                    personcodemax = partAService.insertPersonalDetailsForRationCard(partADTO, ds, request);
                        session.setAttribute("personcode", personcodemax);
                    if (personcodemax != null && !personcodemax.equals("Not Match")) {

                        if (partADTO.getPersonstatus().equals("Eligible")) {
                            target = "finish";
                        } else {
                            target = "rejected";
                            }
                            if (request.getAttribute("dvalues") != null) {
                                request.setAttribute("dvaluess", "<font color='red' size='2'>Select Disability Exceeded in Seleted Camp</font>");
                                partADAO.removeSadaremids(ds, personcodemax);
                                partAForm.setType_disability("");
                                request.setAttribute("dvaluess", "<font color='red' size='2'>Select Disability Exceeded in Seleted Camp</font>");
                                FunctionalNeedReportService functionalNeedService =
                                        FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
                                // Here Getting the personal details data from web service i.e slno and rationcard number

                                ArrayList mandalList = new ArrayList();
                                TerritoryDAO dAO = new TerritoryDAO();
                                String districtId = null;
                                if (partAForm.getDistrict_id() == null) {
                                    districtId = request.getParameter("district");
                                    request.setAttribute("districtId", districtId);
                                } else {
                                    districtId = partAForm.getDistrict_id();
                                    request.setAttribute("districtId", partAForm.getDistrict_id());
                                    ArrayList campList = new ArrayList();
                                    campList = partADAO.getMultipleCampDetails(ds, districtId);
                                    partAForm.setCampsList(campList);
                                    request.setAttribute("campsList", campList);
                                }
                                mandalList = functionalNeedService.getMandals(ds, districtId, "");
                                partAForm.setMandallist(mandalList);

                                ArrayList panchayatList = new ArrayList();
                                panchayatList = dAO.getPanchayats(ds, districtId, partAForm.getMandal_id());
                                partAForm.setPanchayatlist(panchayatList);

                                ArrayList villageList = new ArrayList();
                                villageList = dAO.getVillages(ds, districtId, partAForm.getMandal_id(), partAForm.getPanchayat_id());
                                partAForm.setVillagelist(villageList);

                                ArrayList habitationList = new ArrayList();
                                habitationList = dAO.getHabitations(ds, districtId, partAForm.getMandal_id(), partAForm.getPanchayat_id(), partAForm.getVillage_id());
                                partAForm.setHabitationlist(habitationList);

                                String dname = partADAO.getDistrictOrMandalname(ds, districtId, "0");
                                partAForm.setDistrict(dname);
                                if (partAForm.getCard() != null && partAForm.getCard().trim().length() > 0) {
                                    request.setAttribute("RationReadOnly", "true");
                                }
                                if (partAForm.getRationCardSlno() != null && partAForm.getRationCardSlno().trim().length() > 0) {
                                    request.setAttribute("SlNoReadOnly", "true");
                                }
                                return mapping.findForward("nocamp");
                            } else {
                                String messages = null;
                                if (partADTO.getPhoneno() != null && partADTO.getPhoneno().length() > 0) {
                                    ArrayList dpmList = partADAO.getDpmuDetails(ds, district_id, partADTO.getPartaCampId(), personcodemax);
                                    ArrayList campDetails = partADAO.getsmsCampDetials(ds, personcodemax, partADTO);
                                    if (dpmList.size() > 0 && campDetails.size() > 0) {
                                        messages = "Camp Scheduled For " + partADTO.getFirstname().trim() + ",On " + campDetails.get(1) + ", At " + campDetails.get(0) + "."
                                                + "With SID As " + personcodemax + ".Contact To Camp Incharge (" + dpmList.get(1) + ").";
                                    } else if (campDetails.size() > 0) {
                                        message = "Camp Scheduled For " + partADTO.getFirstname().trim() + ",On " + campDetails.get(1) + ", At " + campDetails.get(0) + "."
                                                + "With SID As " + partAForm.getPersoncode() + ".Contact To NA";
                                    } else {
                                        messages = "Camp Scheduled For " + partADTO.getFirstname() + ","
                                                + "For Sadarem Certificate With SADAREMID As " + personcodemax + "";
                                    }
                                    //com.tpSMS.TpSendSMS.sendSMS(partADTO.getPhoneno(), messages);
                                    partADAO.insertSmsLogDetails(ds, partADTO, messages, "Sent");
                                }
                        }
                        session.setAttribute("personstatus", partADTO.getPersonstatus());
                        session.setAttribute("personcode", personcodemax);
                        session.setAttribute("teluguname", partADTO.getTelugupersonname());
                        session.setAttribute("Name", partADTO.getFirstname());
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
                        partAForm.setFormno("");
                        partAForm.setFirstname("");
                        partAForm.setSurname("");
                        partAForm.setSurnametelugu("");
                        partAForm.setLastname("");
                        partAForm.setFirstnametelugu("");
                        partAForm.setAge("");
                        partAForm.setGender("0");
                        partAForm.setDay("0");
                        partAForm.setMonth("0");
                        partAForm.setYear("0");
                        partAForm.setEducation("0");
                        partAForm.setEmployment("0");
                        partAForm.setMarital("0");
                        partAForm.setCaste("0");
                        partAForm.setRelation(null);
                        partAForm.setRationCardNo("0");
                            partAForm.setCard(null);
                            partAForm.setRationCardSlno(null);
                        partAForm.setRtype("0");
                        partAForm.setRationCardNo("");
                        partAForm.setMole1("");
                        partAForm.setMole2("");
                        partAForm.setParents_marriage("");
                        partAForm.setRelationname("");
                        partAForm.setGsurname("");
                        partAForm.setGrelation("0");
                        partAForm.setFirstnametelugu("");
                        partAForm.setHouseno("");
                        partAForm.setDistrict("");
                        partAForm.setMandal_id("0");
                        partAForm.setPanchayat_id("0");
                        partAForm.setVillage_id("0");
                        partAForm.setHabitation_id("0");
                        partAForm.setPhoneno("");
                        partAForm.setEmail("");
                        partAForm.setPin("");
                        partAForm.setDisability("");
                            partAForm.setAadharCardNo(null);
                            partAForm.setType_disability("");

                        if (((String) session.getAttribute("PartArestrict") != null) && "true".equals((String) session.getAttribute("PartArestrict"))) {
                            request.setAttribute("PartArestrict", (String) session.getAttribute("PartArestrict"));
                            target = "restrictPartA";
                            request.setAttribute("restrict", "yes");
                        }/* else if (((String) session.getAttribute("restrictPartA") != null) && "pensionNumberRestrictPartA".equals((String) session.getAttribute("restrictPartA"))) {
                        request.setAttribute("restrictPartA", (String) session.getAttribute("restrictPartA"));
                        target = "restrictPartA";
                        }
                        if (((String) request.getParameter("restrictPartA") != null) && "true".equals((String) request.getParameter("restrictPartA"))) {
                        request.setAttribute("restrictPartA", (String) request.getParameter("restrictPartA"));
                        target = "restrictPartA";
                        }*/
                        session.removeAttribute("PartArestrict");


                    } else {
                        target = "habcodeNotMatch";
                    }

                } else {
                    request.setAttribute("card", partADTO.getCard());
                    request.setAttribute("slno", partADTO.getRationCardSlno());
                    target = "rationduplicate";

                }
                }

            }



            if ("getNotLoginData".equalsIgnoreCase(partAForm.getMode())) {

                //    getRationData = dao.getDataFromCivilSuppliesDatabase(con, partAForm.getRationCardNo()); // Get data from Civil supplies database
                if (partAForm.getRationCardNo() != null) {

                    //  reportlist = reportservice.getDetailsForCertificate(ds, territoryForm, personcode);
                    CommonDetails commondetails = new CommonDetails();

                    districtid = partAForm.getRationCardNo().substring(3, 5);
                    rationCardDetails = dao.ExistingRationCardPerson(partAForm.getRationCardNo().toUpperCase(), districtid, ds);
                    rationCardSerialNoStatus = dao.getRationCardSerialNoStatus(ds, partAForm.getRationCardNo(), districtid);
                    if (rationCardDetails.size() > 0) {
                        request.setAttribute("rationCardDetails", rationCardDetails);

                    } else {
                        // if (rationCardSerialNoStatus != null) {
                        /** Calling data from civil suppelies data  **/
                        //con = DbManager.getConnection("civilDB");
                        getRationData = dao.getDataFromCivilSuppliesDatabaseNew(ds, civilDs, partAForm.getRationCardNo(), request); // Get data from Civil supplies database
                        request.setAttribute("data", getRationData);
                        request.setAttribute("ration", partAForm.getRationCardNo());

                        target = "memberdetails";
                        if (getRationData.size() == 0) {
                            request.setAttribute("msgFailure", "No Details Found");
                            target = "success";
                        }

                    }

                }




            } else if ("submitRationCardDetails".equalsIgnoreCase(partAForm.getMode())) {
                //added by kavya

                URL url1 = new URL(CommonConstants.Url1);
                //  URL url2 = new URL(CommonConstants.Url2);
                String message = null;
                // AponlineWSClient wsClient = new AponlineWSClient();
                rationCardSlno = partAForm.getRationCardslNo();
                rationCardHidden = partAForm.getRationCardHidden();
                String ration = partAForm.getRationCardNo();
                String invalidSlno = "";
                PartADAO partADAO = new PartADAO();
//                String rationcardtype = ration.substring(0, 3);
//                int maxno = dao.getMaximumSerialNumber(ds, ration, request);
//                boolean entermaxslno = false;
                if (session.getAttribute("districtId") != null) {
                    districtid = (String) session.getAttribute("districtId");
                }
                for (int i = 0; i < rationCardSlno.length; i++) {

//                    if (rationcardtype.equalsIgnoreCase("RAP") || rationcardtype.equalsIgnoreCase("TAP")) {
                    message = partADAO.getRAPTAPRationCardSerialNumberStatus(ds, ration, rationCardSlno[i], districtid);
//                    } else {
                    // message = wsClient.getRationcardSerialNumbers(ds, url1, null, ration, rationCardSlno[i], request);
//                    }

                    if (message != null && !message.equalsIgnoreCase(CommonConstants.SUCCESS)) {
                        break;
                    }

                }

                if (message != null && message.equalsIgnoreCase(CommonConstants.ERROR)) {
                    request.setAttribute("msgFailure", "You Have Entered Invalid Rationcard serial number ");

                } else if (message != null && message.equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    for (int i = 0, j = 0; i < rationCardSlno.length && j < rationCardHidden.length; i++, j++) {
                       
                        invalidSlno = rationCardSlno[i];
                        updateStatus = dao.UpdateRationCardSerialnumber(ration, rationCardHidden[j], rationCardSlno[i], ds);
                        if (updateStatus == 0) {
                            break;
                        }
                    }

                    if (updateStatus > 0) {
                        request.setAttribute("msgSuccess", "RationCard serial Number Updated Successfully");
                        Connection con = null;
                        /** Calling data from civil suppelies data  **/
                        //con = DbManager.getConnection("civilDB");
                        getRationData = getWebRationcardData(partAForm, session, request, getRationData, dao, ds, target);
                        target = request.getAttribute("targetPage").toString();
                        request.setAttribute("data", getRationData);
                        request.setAttribute("ration", partAForm.getRationCardNo());

                        /** End of calling web service data **/
                    } else {
                        request.setAttribute("msgFailure", "Already Assessment completed with this Ration card Serial Number " + invalidSlno);
                    }
                } else {
                    request.setAttribute("msgFailure", message);
                }
            } else if ("getPensionData".equalsIgnoreCase(partAForm.getMode())) {

                if (partAForm.getRationCardNo() != null) {
                    CommonDetails commondetails = new CommonDetails();
                    int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
                    adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);

                    if (!adminLevelAccessFlag) {
                        districtid = (String) session.getAttribute("districtId");
                    }

                    List<String> dist = dao.getIssueRaisingRationCard(ds, partAForm.getRationCardNo());

                    if (!dist.get(1).equals("")) {

                        if (dist.get(1).equals(districtid)) {
                            districtLevelAccessFlag = true;
                        } else {
                            districtLevelAccessFlag = false;
                        }

                    } else {

                        if (districtid.equals(partAForm.getRationCardNo().substring(3, 5))) {
                            districtLevelAccessFlag = true;
                        }
                    }
                }

                if (districtLevelAccessFlag || adminLevelAccessFlag) {

                    ArrayList rationCardDetailsFromCard = dao.ExistingRationCardPerson(partAForm.getRationCardNo().toUpperCase(), partAForm.getRationCardNo().substring(3, 5), ds);

                    if (rationCardDetailsFromCard.size() == 0) {

                        rationCardDetails = dao.ExistingRationCardPerson(partAForm.getRationCardNo().toUpperCase(), districtid, ds);
                        rationCardSerialNoStatus = dao.getRationCardSerialNoStatus(ds, partAForm.getRationCardNo(), districtid);
                    } else {

                        rationCardDetails = rationCardDetailsFromCard;
                        rationCardSerialNoStatus = dao.getRationCardSerialNoStatus(ds, partAForm.getRationCardNo(), partAForm.getRationCardNo().substring(3, 5));
                    }
                    if (rationCardDetails.size() > 0) {
                        request.setAttribute("rationCardDetails", rationCardDetails);

                    } else {
                        PartADAO aDAO = new PartADAO();
                        request.setAttribute("rationcard", partAForm.getRationCardNo());
                        ArrayList pensionList = aDAO.getAllDataBasedonRationCard(ds, "", session.getAttribute("districtId").toString(), request);

                        request.setAttribute("Pensiondata", pensionList);


                        request.setAttribute("WebService", "true");
                        partAForm.setRationCardNo(partAForm.getRationCardNo());
                        target = "RationcardPensionersDatatile";
                    }

                }
            } else if ("getData".equalsIgnoreCase(partAForm.getMode())) {

                ArrayList rationCardWS1List = new ArrayList();
                // ArrayList rationCardWS2List = new ArrayList();
                ArrayList rationCardWS1DisplayList = new ArrayList();
                // ArrayList rationCardWS2DisplayList = new ArrayList();
                URL url1 = new URL(CommonConstants.Url1);
                // URL url2 = new URL(CommonConstants.Url2);
                String url1IP = url1.toString();
                //String url2IP = url2.toString();
                // AponlineWSClient wsClient = new AponlineWSClient();
                int rationCardStatus = 0;
                String loginId = null;
                String systemIp = null;
                String sadaremDB = null;
                String dataSeverDescription = "";
                loginId = (String) session.getAttribute("loginid");
                systemIp = request.getRemoteAddr();
                String expection = null;
                String rationcardtype = "";
                //    getRationData = dao.getDataFromCivilSuppliesDatabase(con, partAForm.getRationCardNo()); // Get data from Civil supplies database

                //sorting arraylist start

                //end
                if (partAForm.getRationCardNo() != null) {

                    //  reportlist = reportservice.getDetailsForCertificate(ds, territoryForm, personcode);
                    CommonDetails commondetails = new CommonDetails();
                    rationcardtype = partAForm.getRationCardNo().substring(0, 3);
                    int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
                    adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);




                    //    boolean adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);
                    if (!adminLevelAccessFlag) {
                        districtid = (String) session.getAttribute("districtId");
//                        if (!(rationcardtype.equalsIgnoreCase("RAP") || rationcardtype.equalsIgnoreCase("TAP"))) {
//                            String issueRaiseDistrict = "";
//                            List<String> dist = dao.getIssueRaisingRationCard(ds, partAForm.getRationCardNo());
//                            if (dist.size() > 0 && !dist.get(1).equals("")) {
//                                issueRaiseDistrict = dist.get(1);
//                            }
//                            districtLevelAccessFlag = true;
//                            // districtLevelAccessFlag = wsClient.compareDistrict(url1, url2, partAForm.getRationCardNo().toUpperCase(), request, districtid, issueRaiseDistrict);
//                        } else {


                            List<String> dist = dao.getIssueRaisingRationCard(ds, partAForm.getRationCardNo());
                            if (dist.size()>0 && !dist.get(1).equals("")) {

                                if (dist.get(1).equals(districtid)) {
                                    districtLevelAccessFlag = true;
                                } else {
                                    districtLevelAccessFlag = false;
                                }

                            } else {
                                if (districtid.equals(partAForm.getRationCardNo().substring(3, 5))) {
                                    districtLevelAccessFlag = true;
                            }

                        }

                        //}

                    }
                }
                if (districtLevelAccessFlag || adminLevelAccessFlag) {

                    ArrayList rationCardDetailsFromCard = dao.ExistingRationCardPerson(partAForm.getRationCardNo().toUpperCase(), partAForm.getRationCardNo().substring(3, 5), ds);

                    if (rationCardDetailsFromCard.size() == 0) {

                        rationCardDetails = dao.ExistingRationCardPerson(partAForm.getRationCardNo().toUpperCase(), districtid, ds);
                        rationCardSerialNoStatus = dao.getRationCardSerialNoStatus(ds, partAForm.getRationCardNo(), districtid);
                    } else {

                        rationCardDetails = rationCardDetailsFromCard;
                        rationCardSerialNoStatus = dao.getRationCardSerialNoStatus(ds, partAForm.getRationCardNo(), partAForm.getRationCardNo().substring(3, 5));
                    }
                    if (rationCardDetails.size() > 0) {
                        request.setAttribute("rationCardDetails", rationCardDetails);

                    } else {
                        // if (rationCardSerialNoStatus != null) {

                        if (partAForm.getMore() != null) {

                            getRationData = dao.getDataFromCivilSuppliesDatabase(ds, partAForm.getRationCardNo().toUpperCase(), request); // Get data from Civil supplies database
                                if (getRationData.size() == 0) {
                                    List<String> dists = dao.getIssueRaisingRationCard(ds, partAForm.getRationCardNo());
                                    String oldDist = "";
                                    if (dists.size() > 0 && !dists.get(0).equals("")) {
                                        oldDist = dists.get(0);
                                        getRationData = dao.getDataFromCivilDatabaseWithOld(ds, partAForm.getRationCardNo().toUpperCase(), oldDist);
                                       // Collections.sort(getRationData, myComparator);
                                    }

                            }
                            request.setAttribute("data", getRationData);
                            request.setAttribute("ration", partAForm.getRationCardNo());
                            if (getRationData != null && getRationData.size() > 0) {
                                sadaremDB = "List From SADAREMDB";
                                rationCardStatus = dao.insertRationCardStatus(ds, partAForm.getRationCardNo(), "SADAREMDB", sadaremDB, loginId, systemIp, "");
                                target = "memberdetails";
                            } else {
                                sadaremDB = "No Details Found";
                                rationCardStatus = dao.insertRationCardStatus(ds, partAForm.getRationCardNo(), "SADAREMDB", sadaremDB, loginId, systemIp, "");
                                target = "success";
                                request.setAttribute("msgFailure", "Rationcard details not available.");
                            }

                            /** End of calling web service data**/
                        }
                        //added by kavya
                        if (partAForm.getMore() == null) {
                            target = "memberdetails";
                            partAForm.setRationCardNo(partAForm.getRationCardNo());
                        }
                        //ended
                        request.setAttribute("rationCardNo", partAForm.getRationCardNo().toUpperCase());
                        if (request.getAttribute("districtNotMatch") != null) {
                            request.setAttribute("msgFailure", "Please Enter Valid Rationcard Number Of This District");
                        }
//added by kavya
                        PartADAO aDAO = new PartADAO();
                        request.setAttribute("rationcard", partAForm.getRationCardNo());
                        ArrayList pensionList = aDAO.getAllDataBasedonRationCard(ds, "", session.getAttribute("districtId").toString(), request);

                        request.setAttribute("Pensiondata", pensionList);
//ended
                    }

                } else {
                    request.setAttribute("msgFailure", "Please Enter Valid Rationcard Number");
                    if (request.getAttribute("districtNotMatch") != null) {
                        request.setAttribute("msgFailure", "Please Enter Valid Rationcard Number Of This District");
                    }
                    target = "success";
                }



            } else if ("getWebDetails".equalsIgnoreCase(partAForm.getMode())) {

                // Here Getting the data from web service i.e slno and rationcard number
                if (request.getParameter("rCardSlno") != null) {
                    rationCardCheckWithWebService = dao.rationCardCheckWithWebService(ds, request.getParameter("rationCardNo"), request.getParameter("rCardSlno"), (String) session.getAttribute("districtId"));
                }
                if (request.getParameter("reset") != null) {
                    partAForm.formReset(mapping, request);
                }

                if (rationCardCheckWithWebService.size() == 0) {
                    FunctionalNeedReportService functionalNeedService =
                            FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
                    // Here Getting the personal details data from web service i.e slno and rationcard number


                    ArrayList mandalList = new ArrayList();
                    PartADAO partADAO = new PartADAO();
                    TerritoryDAO dAO = new TerritoryDAO();
                    String districtId = null;
                    if (partAForm.getDistrict_id() == null) {
                        districtId = request.getParameter("district");
                        request.setAttribute("districtId", districtId);
                    } else {
                        districtId = partAForm.getDistrict_id();
                        request.setAttribute("districtId", partAForm.getDistrict_id());
                        ArrayList campList = new ArrayList();
                        campList = partADAO.getMultipleCampDetails(ds, districtId);
                        partAForm.setCampsList(campList);
                        request.setAttribute("campsList", campList);
                    }
                    mandalList = functionalNeedService.getMandals(ds, districtId, "");
                    partAForm.setMandallist(mandalList);

                    ArrayList panchayatList = new ArrayList();
                    panchayatList = dAO.getPanchayats(ds, districtId, partAForm.getMandal_id());
                    partAForm.setPanchayatlist(panchayatList);

                    ArrayList villageList = new ArrayList();
                    villageList = dAO.getVillages(ds, districtId, partAForm.getMandal_id(), partAForm.getPanchayat_id());
                    partAForm.setVillagelist(villageList);

                    ArrayList habitationList = new ArrayList();
                    habitationList = dAO.getHabitations(ds, districtId, partAForm.getMandal_id(), partAForm.getPanchayat_id(), partAForm.getVillage_id());
                    partAForm.setHabitationlist(habitationList);

                    String dname = partADAO.getDistrictOrMandalname(ds, districtId, "0");

                    int length = 0;
                    if (request.getParameter("name") != null && request.getParameter("name").length() > 0) {
                        StringTokenizer stringTokenizer = new StringTokenizer(request.getParameter("name"), " ");
                        if (stringTokenizer.hasMoreElements()) {
                            String surname=stringTokenizer.nextToken();
                            length=surname.length();
                            partAForm.setSurname(surname);
                        }
                        if (stringTokenizer.hasMoreElements()) {
                            partAForm.setFirstname(request.getParameter("name").substring(length+1,
                                    request.getParameter("name").length()));
                        } else {
                            partAForm.setSurname(null);
                            partAForm.setFirstname(request.getParameter("name"));
                        }
                    } 
                    if (partAForm.getGrelation() == null) {
                        partAForm.setGrelation(request.getParameter("relation"));
                    }
                    if (request.getParameter("rationCardNo") != null) {
                        partAForm.setCard(request.getParameter("rationCardNo"));
                    }
                    if (request.getParameter("rCardSlno") != null) {
                        partAForm.setRationCardSlno(request.getParameter("rCardSlno"));
                    }

                    if (partAForm.getGender() == null) {
                        partAForm.setGender(request.getParameter("gender"));
                    }
                    if (partAForm.getNoOfYears() == null) {
                        partAForm.setNoOfYears(request.getParameter("age"));
                    }
                    partAForm.setDistrict(dname);

                    if (partAForm.getType_disability() == null) {
                        partAForm.setType_disability("0");
                    }
                    //addded by kavya
                    if (partAForm.getSurname() != null && partAForm.getSurname().trim().length() > 0
                            && !partAForm.getSurname().equalsIgnoreCase("-")) {
                        request.setAttribute("surname", "true");
                    }
                    if (partAForm.getFirstname() != null && partAForm.getFirstname().trim().length() > 0
                            && !partAForm.getFirstname().equalsIgnoreCase("-")) {
                        request.setAttribute("name", "true");
                    }
                    if (partAForm.getAadharCardNo() != null && partAForm.getAadharCardNo().trim().length() > 0
                            && !partAForm.getAadharCardNo().equalsIgnoreCase("-")) {
                        request.setAttribute("aadharcardNo", "true");
                    }

                    if (partAForm.getCard() != null && partAForm.getCard().trim().length() > 0) {
                        request.setAttribute("RationReadOnly", "true");
                    }
                    if (partAForm.getRationCardSlno() != null && partAForm.getRationCardSlno().trim().length() > 0) {
                        request.setAttribute("SlNoReadOnly", "true");
                    }
                    if (request.getParameter("noofrows") != null) {
                        request.setAttribute("noOfRows", request.getParameter("noofrows").toString());
                    }

                    //ended

                    target = "webDetails";
                } else {
                    target = "duplicate";
                }
            } else if ("campdropdownList".equalsIgnoreCase(partAForm.getMode())) {
                PartADAO partADAO = new PartADAO();
                String districtId = null;
                PrintWriter out = null;
                try {
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
                    out.close();
                }
                //target = "webDetails";
                target = null;
            } else if ("campdatesdropdownList".equalsIgnoreCase(partAForm.getMode())) {
                PartADAO partADAO = new PartADAO();
                PrintWriter out = null;
                String jobcardDetails = null;
                String districtId = null;
                try {
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

                target = "webDetails";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // DbManager.close(con);
        }


        return mapping.findForward(target);
    }

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

    private ArrayList getWebRationcardData(PartAForm partAForm, HttpSession session, HttpServletRequest request, ArrayList getRationData,
            PersonCodeCheckDAO dao, DataSource ds, String target) throws MalformedURLException, SADAREMDBException, SQLException {
        int rationCardStatus = 0;
        ArrayList rationCardWS1List = new ArrayList();
        ArrayList rationCardWS2List = new ArrayList();
        ArrayList rationCardWS1DisplayList = new ArrayList();
        ArrayList rationCardWS2DisplayList = new ArrayList();

        URL url1 = new URL(CommonConstants.Url1);
//        URL url2 = new URL(CommonConstants.Url2);
        String url1IP = url1.toString();
        // String url2IP = url2.toString();
        //  AponlineWSClient wsClient = new AponlineWSClient();
        String loginId = null;
        String systemIp = null;
        String sadaremDB = null;

        String dataSeverDescription = "";
        loginId = (String) session.getAttribute("loginid");
        systemIp = request.getRemoteAddr();
        String expection = null;
        CommonDetails commondetails = new CommonDetails();

        int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
        boolean adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);

        String rationcardtype = partAForm.getRationCardNo().substring(0, 3);
        //added by kavya
        if (partAForm.getMore() != null) {
            //ended

            getRationData = dao.getDataFromCivilSuppliesDatabase(ds, partAForm.getRationCardNo().toUpperCase(), request); // Get data from Civil supplies database
                    if (getRationData.size() == 0) {
                        List<String> dists = dao.getIssueRaisingRationCard(ds, partAForm.getRationCardNo());
                        String oldDist = "";
                        if (dists.size() > 0 && !dists.get(0).equals("")) {
                            oldDist = dists.get(0);
                            getRationData = dao.getDataFromCivilDatabaseWithOld(ds, partAForm.getRationCardNo().toUpperCase(), oldDist);
                        }

                    }
                    request.setAttribute("data", getRationData);
                    if (getRationData.size() > 0) {
                        sadaremDB = "List From SADAREMDB";
                        rationCardStatus = dao.insertRationCardStatus(ds, partAForm.getRationCardNo(), "SADAREMDB", sadaremDB, loginId, systemIp, "");
                    }
                    request.setAttribute("ration", partAForm.getRationCardNo());

            target = "memberdetails";
            if (getRationData.size() == 0) {
                sadaremDB = "No Details Found";
                rationCardStatus = dao.insertRationCardStatus(ds, partAForm.getRationCardNo(), "SADAREMDB", sadaremDB, loginId, systemIp, "");
                request.setAttribute("msgFailure", "Rationcard details not available");
                target = "success";
            }
        }




        //added by kavya
        if (partAForm.getMore() == null) {
            target = "memberdetails";
            partAForm.setRationCardNo(partAForm.getRationCardNo());
        }
        //ended
        request.setAttribute("rationCardNo", partAForm.getRationCardNo().toUpperCase());
        if (request.getAttribute("districtNotMatch") != null) {
            request.setAttribute("msgFailure", "Please Enter Valid Rationcard Number Of This District");
        }
        //added by kavya
        PartADAO aDAO = new PartADAO();
        request.setAttribute("rationcard", partAForm.getRationCardNo());
        ArrayList pensionList = aDAO.getAllDataBasedonRationCard(ds, "", session.getAttribute("districtId").toString(), request);

        request.setAttribute("Pensiondata", pensionList);

//ended
        request.setAttribute("targetPage", target);
        return rationCardWS1DisplayList;
    }

    public static String getRationcardSerialNumbers(DataSource ds, URL url1, URL url2, String rationCard, String serialNo, HttpServletRequest request) throws SADAREMDBException {
        String validStatus = "ERROR";

        String expection = null;
        ArrayList rationCardWS1List = new ArrayList<Object>();
        ArrayList rationCardWS2List = new ArrayList<Object>();

        String rationcardtype = "";
        try {

            PartADAO partADAO = new PartADAO();
            HttpSession session = request.getSession();
            if (session.getAttribute("districtId") != null) {
                validStatus = partADAO.getRAPTAPRationCardSerialNumberStatus(ds, rationCard, serialNo, session.getAttribute("districtId").toString());
            } else {
                validStatus = "RationCard not available in Civil Supplies Web Site";
            }
            for (int x = 0; x < rationCardWS1List.size(); x++) {
                Map m = (Map) rationCardWS1List.get(x);
                if (m.get("slNo") != null && serialNo != null && m.get("slNo").toString().equals(serialNo)) {
                    validStatus = "SUCCESS";
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return validStatus;
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

}
