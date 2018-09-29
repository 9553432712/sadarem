/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.AadharCardMappingDAO;
import org.bf.disability.dao.IssueRaiseApprovalDAO;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dao.PersonCodeCheckDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.IssueRaiseApprovalDTO;
import org.bf.disability.form.IssueRaiseApprovalForm;
import org.bf.disability.service.IssueRaiseApprovalService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.IssueRaiseApprovalServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;
import org.bf.disability.util.EmailUtility;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 693461
 */
public class IssueRaiseApprovalAction extends DispatchAction {

    IssueRaiseApprovalForm issueRaiseApprovalForm = new IssueRaiseApprovalForm();

    /* forward name="success" path="" */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        DataSource ds = null;
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        HttpSession session = request.getSession();
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        ArrayList campList = new ArrayList();
        ArrayList userNameList = new ArrayList();
        ArrayList catgoryList = new ArrayList();
        String districtId = null;
        String personCode = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            catgoryList = issueRaiseApprovalService.categoryApprovalList(ds);
            issueRaiseApprovalForm.setCategoryList(catgoryList);
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            personCode = issueRaiseApprovalForm.getSadaremId();
            // campList = issueRaiseApprovalService.getCampBasedOnLoginDetails(ds, districtId,personCode);
            //issueRaiseApprovalForm.setCampList(campList);
            userNameList = issueRaiseApprovalService.getUserNameDetails(ds, districtId);
            issueRaiseApprovalForm.setUserNameList(userNameList);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return mapping.findForward(target);
    }

    public ActionForward getPensionValidation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String target = "phaseIIIConversion";
        HttpSession session = request.getSession();
        ArrayList penctionPersonalDetails = null;
        ArrayList penstionDetails = new ArrayList();
        String districtId = null;
        DataSource ds = null;
        IssueRaiseApprovalForm formBean = (IssueRaiseApprovalForm) form;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            IssueRaiseApprovalService issueRaiseApprovalService =
                    IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
            //int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            penctionPersonalDetails = issueRaiseApprovalService.getPhaseConversionList(ds, formBean.getPensionId(), districtId);
            if (penctionPersonalDetails != null && penctionPersonalDetails.size() > 0) {
                request.setAttribute("penctionPersonalDetails", penctionPersonalDetails);
                target = "phaseIIIConversion";
            }
            //System.out.println("hii");
            penstionDetails = issueRaiseApprovalService.getPensionPersonalDetails(ds, formBean.getPensionId(), districtId);
            //System.out.println("penstionDetails: "+penstionDetails);
            if (penstionDetails.size() > 0) {
                request.setAttribute("pensionDetails", penstionDetails);
                target = "phaseIIIConversion";
            }


            //end balu

            unspecified(mapping, form, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return mapping.findForward(target);
    }

    public ActionForward insertIssueRaise(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        DataSource ds = null;
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        HttpSession session = request.getSession();
        String systemIp = null;
        String loginId = null;
        int issueRaise = 0;
        String districtId = null;
        int success = 0;

        systemIp = request.getRemoteAddr();
        CommonDetails commonDetails = new CommonDetails();
        boolean districtLevelAccessFlag = false;
        boolean rationCardFlag = false;
        boolean rationCardDataFlag = false;
        boolean pensionId = false;
        ArrayList pensionIdDetails = new ArrayList();
        String campName = null;
        int succ = 1;
        int sadaremid = 0;
        int rationcard = 0;
        int personid = 0;
        ArrayList issueRaiseEmailBodyList = new ArrayList();
        EmailUtility emailUtility = new EmailUtility();
        ArrayList<InternetAddress> RecMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> CCMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> BCCMail = new ArrayList<InternetAddress>();
        String issueEmailBody = null;
        boolean emailStatus = false;
        ArrayList emailList = new ArrayList();
        String email = null;
        InternetAddress emailId = null;
        String subjectMsg = null;
        String reqId = null;
        int checkAddressChange = 0;
        int checkFlag = 0;
        int checkCategoryId = 1;
        int validPId = 0;
        int pensionValidId = 0;
        int technicalId = 0;
        String userName = null;
        String passwordDetails = "";
        String validMsg = null;
        ArrayList validMsgList = new ArrayList();
        StringBuffer validationMsg = null;
        int phaseConversion = 0;
        String phaceConversionValidation = null;
        String requestPhaseIIIConversion = null;
        boolean rationcardValidFlag = false;
        int validSadaremId = 0;
        int rationCardNoValid = 0;
        String rationCardNo = null;
        String passwordData = "";
        int alreadyRaised = 0;
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
        int viceversaNewSADAREMID = 0;
        int viceversaFlag = 0;
        String msg = "";
        ArrayList validRationCardNotOpen = new ArrayList();
        boolean serialNoFlag = false;
        String fileName = null;
        String extension = null;
        int fileRestricttion = 0;
        ArrayList rationCardWS1List = new ArrayList();
        ArrayList rationCardWS2List = new ArrayList();
        ArrayList rationCardWS1DisplayList = new ArrayList();
        ArrayList rationCardWS2DisplayList = new ArrayList();
        URL url1 = new URL(CommonConstants.Url1);
        //AponlineWSClient wsClient = new AponlineWSClient();
        String expection = null;
        PersonCodeCheckDAO dao = new PersonCodeCheckDAO();
        ArrayList getRationData = new ArrayList();
        int count = 0;
        String slNo = null;
        String flag = "1";
        int appelateCount = 0;
        String cat = null;

        String pensioncard_no = null;
        String habitationCode = null;
        CommonDAO commonDAO = new CommonDAO();
        ArrayList list = new ArrayList();
        ArrayList newlist = new ArrayList();
        String distid = null;
        String mandalid = null;
        String villageid = null;
        String panchayatid = null;
        String habitationid = null;
        PartADAO partADAO = new PartADAO();
        ArrayList penctionPersonalDetails = null;
        ArrayList mandalList = new ArrayList();
       // String personcodemax = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            //balu start
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            //balu end
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            rationCardFlag = commonDetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ISSUERAISINGROLE);
            pensionId = commonDetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ISSUERAISINGROLE);
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            if (session.getAttribute("useridData") != null) {
                loginId = (String) session.getAttribute("useridData");
            }
            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                districtLevelAccessFlag = commonDetails.checkDistrictFlag(issueRaiseApprovalForm.getSadaremId(), districtId);
                if (districtLevelAccessFlag == false) {
                    sadaremid = 1;
                    issueRaiseApprovalForm.setSadaremId("");
                }
            }


            if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.ADDRESSCHANGEPHASEIII)) {
                target = "addressChangePhaseIII";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.NODATA)) {
                target = "addressChangePhaseIII";
                //This Category for BlankPages and Others
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.BLANKPAGES) || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.BLANKPAGES)) {
                target = "blankPages";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.FORGOTPASSWORD)) {
                target = "forgetPasswordRequest";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.INVALIDPERSONCODE)) {
                target = "InvalidPersonCode";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.NAME)) {
                target = "nameChangeRequest";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SURNAME)) {
                target = "surNameRequest";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONNAME)) {
                target = "relationNameRequest";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA)) {
                target = "viceVersa";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.PENSIONIDNOTOPENING)) {
                target = "pensionIdNotOpening";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.TECHNICALDIFFICULTIES)) {
                target = "technicalDifficulty";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDNOTOPEN)) {
                target = "rationCardNotOpen";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE)) {
                target = "rationCardSerialNo";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.MEDICALBOARDCHANGE)) {
                target = "medicalBoardChange";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV)) {
                target = "phaseIIIConversion";
            } else {
                //  districtList = territoryDAO.getDistricts(ds);
                target = "success";
            }
            if (sadaremid == 1 || personid == 1) {
                succ = 0;
                validationMsg = new StringBuffer();
                validationMsg.append("Please Enter Valid");
                if (sadaremid == 1) {
                    validationMsg.append(" SADAREM ID");
                    issueRaiseApprovalForm.setPensionId("");
                    issueRaiseApprovalForm.setNewName("");
                    issueRaiseApprovalForm.setNewRelationName("");
                    if (issueRaiseApprovalForm.getRadioPensionId() != null && !issueRaiseApprovalForm.getRadioPensionId().equals("")) {
                        //vicversa.
                        request.setAttribute("sadaremIdflag", issueRaiseApprovalForm.getRadioPensionId());
                        //blankpages
                        // request.setAttribute("sadaremFlag", issueRaiseApprovalForm.getRadioPensionId());
                    }
                }
                if (personid == 1) {
                    validationMsg.append(" Pension Id. ");
                }
                request.setAttribute("error", validationMsg.toString().concat(msg));
            }
            //To check the Category for ForgotPassword
            if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.FORGOTPASSWORD)) {
                userName = issueRaiseApprovalForm.getForgetUserName();
                passwordDetails = issueRaiseApprovalService.getPasswordDetails(ds, userName, districtId);
                checkFlag = 1;
            }
            if (succ == 1) {

                if (issueRaiseApprovalForm.getHiddenFlags() != null) {
                    issueRaiseApprovalForm.setVicversaFlag(issueRaiseApprovalForm.getHiddenFlags());
                }
                if (issueRaiseApprovalForm.getCategoryFormId() != null
                        && !issueRaiseApprovalForm.getCategoryFormId().equals("")
                        && issueRaiseApprovalForm.getCategoryFormId().length() > 0) {
                    checkCategoryId = issueRaiseApprovalService.checkCategoryId(ds, issueRaiseApprovalForm);
                }
                if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && issueRaiseApprovalForm.getSadaremId().length() > 0) {
                    validSadaremId = issueRaiseApprovalService.validSadaremId(ds, issueRaiseApprovalForm);
                }
                //This is used for Msg for already request is raised.
               
                if (checkCategoryId > 0) {
                    alreadyRaised = 1;
                } else {
                    if (issueRaiseApprovalForm.getPensionId() != null
                            && !issueRaiseApprovalForm.getPensionId().equalsIgnoreCase("")
                            && !issueRaiseApprovalForm.getPensionId().equalsIgnoreCase("null")
                            && issueRaiseApprovalForm.getCategoryFormId() != null
                            && !issueRaiseApprovalForm.getCategoryFormId().equalsIgnoreCase("")
                            && issueRaiseApprovalForm.getCategoryFormId().length() > 0
                            && !issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)
                            && !issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)
                            && !issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV)) {

                        pensionIdDetails = issueRaiseApprovalService.getPensionIdDetails(ds, loginId, issueRaiseApprovalForm.getPensionId(), issueRaiseApprovalForm.getCategoryFormId());

                        if (pensionIdDetails != null && pensionIdDetails.size() > 0) {
                            if (pensionIdDetails.get(1) != null
                                    && !pensionIdDetails.get(1).toString().equalsIgnoreCase("")
                                    && issueRaiseApprovalForm.getCategoryFormId().length() > 0) {
                                if (pensionIdDetails.get(1).toString().equalsIgnoreCase("Dead")
                                        && !issueRaiseApprovalForm.getCategoryFormId().equalsIgnoreCase(CommonConstants.PENSIONIDNOTOPENING)) {
                                    succ = 0;

                                    if (issueRaiseApprovalForm.getRadioPensionId() != null
                                            && !issueRaiseApprovalForm.getRadioPensionId().equals("")
                                            && issueRaiseApprovalForm.getRadioPensionId().equals("2")) {
                                        request.setAttribute("pensionFlag", "pensionFlag");
                                    }
                                    issueRaiseApprovalForm.setPensionId("");
                                    request.setAttribute("error", "Pension ID is in Dead");

                                } else if (issueRaiseApprovalForm.getCategoryFormId().equalsIgnoreCase(CommonConstants.PENSIONIDNOTOPENING)) {
                                    if (pensionIdDetails.get(1).toString().equalsIgnoreCase("Permanent Migration")
                                            || pensionIdDetails.get(1).toString().equalsIgnoreCase("Live")
                                            || pensionIdDetails.get(1).toString().equalsIgnoreCase("Not Eligible")
                                            || pensionIdDetails.get(1).toString().equalsIgnoreCase("Abayahastam")
                                            || pensionIdDetails.get(1).toString().equalsIgnoreCase("Dead")) {
                                        succ = 0;
                                        flag = "0";
                                        msg = "Pension ID is in " + pensionIdDetails.get(1).toString();
                                    }
                                    if (succ == 0) {
                                        issueRaiseApprovalForm.setPensionId("");
                                    }
                                    request.setAttribute("error", msg);
                                }
                            }

                        } else {
                            flag = "2";
                            if (issueRaiseApprovalForm.getRadioPensionId() != null
                                    && !issueRaiseApprovalForm.getRadioPensionId().equals("")
                                    && issueRaiseApprovalForm.getRadioPensionId().equals("2")) {
                                request.setAttribute("pensionFlag", "pensionFlag");

                                request.setAttribute("error", "Pension ID is not valid");
                                issueRaiseApprovalForm.setPensionId("");
                            }
                        }
                    }

                    if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.ADDRESSCHANGEPHASEIII)
                            || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.INVALIDPERSONCODE)
                            || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.NODATA)) {
                        if (validSadaremId > 0) {
                            if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.ADDRESSCHANGEPHASEIII)) {
                                //balu Start
                                pensioncard_no = issueRaiseApprovalService.getAddressChangeValidation(ds, issueRaiseApprovalForm.getSadaremId());
                                if (pensioncard_no != null) {
                                    //checkFlag = 1;
//                                    if (pensioncard_no.equals("RDPIII")) {
//                                        request.setAttribute("error", "SADAREMID is Generated through RD Call Center provision for Address Change will not be provided. ");
//                                    } else if (!pensioncard_no.equals("PIII")) {
//                                        request.setAttribute("error", issueRaiseApprovalForm.getSadaremId() + " SADAREMID Already Tagged to Pension Card Number : " + pensioncard_no);
//                                    } else {

                                        mandalList = commonDAO.getMandalsList(ds, districtId);
                                        issueRaiseApprovalForm.setDistrict_id(districtId);
                                        issueRaiseApprovalForm.setMandallist(mandalList);
                                        habitationCode = commonDAO.getHabcodeBySadaremId(ds, issueRaiseApprovalForm.getSadaremId());
                                        list = commonDetails.getSadaremDistDtls(habitationCode); 
                                        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
                                        ArrayList addressDtls = issueRaiseApprovalDAO.getAddressDetails( issueRaiseApprovalForm.getSadaremId());
                                        if(addressDtls.size()>0)
                                        {
                                        	addressDtls = (ArrayList)addressDtls.get(0);
                                        	request.setAttribute("houseNo", (String)addressDtls.get(0));
                                        	request.setAttribute("pinCode", (String)addressDtls.get(1));
                                        	
                                        }
                                        if (list.size() > 0) {
                                            request.setAttribute("addchange", "addchange");
                                            distid = list.get(0).toString();
                                            mandalid = list.get(2).toString();
                                            villageid = list.get(3).toString();
                                            panchayatid = list.get(5).toString();
                                            habitationid = list.get(4).toString();
                                        }
                                        request.setAttribute("distname", partADAO.getDistrictOrMandalname(ds, distid, "0"));
                                        request.setAttribute("mandalname", partADAO.getDistrictOrMandalname(ds, distid, mandalid));
                                        request.setAttribute("villagename", commonDAO.getVillageName(ds, districtId, mandalid, villageid));
                                        request.setAttribute("panchayatname", commonDAO.getPanchayatName(ds, districtId, mandalid, panchayatid));
                                        request.setAttribute("habitationname", commonDAO.getHabitationName(ds, habitationCode));
                                        if (request.getParameter("flag").equals("validate")) {
                                            target = "addressChangePhaseIII";
                                        } else {
                                            newlist = commonDetails.getSadaremDistDtls(issueRaiseApprovalForm.getHabitation_id());
                                            // personcodemax = territoryservice.getHabitationCodeRationCard(newlist.get(0).toString(), newlist.get(2).toString(), newlist.get(5).toString(), newlist.get(1).toString(), newlist.get(3).toString(), newlist.get(4).toString(), ds);
                                            //System.out.println("personcodemax-> " + personcodemax);
                                            issueRaiseApprovalForm.setAddresschangeSADAREMID(issueRaiseApprovalForm.getSadaremId());
                                        //IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
                                        //success =1;// issueRaiseApprovalDAO.issueNewAddressPhaseIIIResolve(ds, issueRaiseApprovalForm.getSadaremId(), issueRaiseApprovalForm.getHabitation_id());
                                            //target = "addressChangePhaseIII";
                                            checkFlag = 1;
                                        }
//                                    }
                                }
                                //balu end
                            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.INVALIDPERSONCODE)) {
                                checkFlag = 1;
                            } //To Check Category  NoData.
                            else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.NODATA)) {

                                String noDataValidation = issueRaiseApprovalService.noDataValidation(ds, issueRaiseApprovalForm);
                                if (noDataValidation.equals("1")) {
                                    checkFlag = 1;
                                } else {
                                    request.setAttribute("error", "Raise the issue in  Techinical Difficulty ");
                                    issueRaiseApprovalForm.setMobile("");
                                    issueRaiseApprovalForm.setDecription("");
                                }
                            }
                        } else {
                            request.setAttribute("error", "Entered SADAREM ID  was Dead/ Invalid!");
                            issueRaiseApprovalForm.setSadaremId("");
                        }
                    }
                    // To check Category For Blank Pages //To Check Category Invalid Person Code and as well as NoData.
                    if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.BLANKPAGES)) {
                        if (issueRaiseApprovalForm.getRadioPensionId().equals("1")) {
                            if (validSadaremId > 0) {
                                issueRaiseApprovalDTO = issueRaiseApprovalService.validblankPagesIds(ds, issueRaiseApprovalForm);
                                if (issueRaiseApprovalDTO.getBlankCount() == 0) { //flag == "1"
                                    checkFlag = 1;
                                } else {
                                    if (issueRaiseApprovalForm.getRadioPensionId().equals("1")) {
                                        request.setAttribute("sadaremFlag", "sadaremFlag");
                                    }
                                    request.setAttribute("error", "Already rasied for this SADAREMID "
                                            + "" + issueRaiseApprovalForm.getSadaremId() + " through this particular PensionID " + issueRaiseApprovalDTO.getBlankPagesPensionId() + " for BlankPages");
                                    issueRaiseApprovalForm.setSadaremId("");
                                }
                            } else {
                                if (issueRaiseApprovalForm.getRadioPensionId().equals("1")) {
                                    request.setAttribute("sadaremFlag", "sadaremFlag");
                                }
                                request.setAttribute("error", "Entered SADAREM ID was Dead!");
                                issueRaiseApprovalForm.setSadaremId("");
                            }

                        } else if (issueRaiseApprovalForm.getRadioPensionId().equals("2")) {
                            if (flag.equals("1")) {

                                issueRaiseApprovalDTO = issueRaiseApprovalService.validblankPagesIds(ds, issueRaiseApprovalForm);
                                if (issueRaiseApprovalDTO.getBlankCount() == 0) {
                                    checkFlag = 1;
                                } else {
                                    if (issueRaiseApprovalForm.getRadioPensionId().equals("2")) {
                                        request.setAttribute("pensionFlag", "pensionFlag");
                                    }
                                    request.setAttribute("error", "Already rasied for this PENSIONID: " + issueRaiseApprovalForm.getPensionId() + " through this particular SADAREMID: " + issueRaiseApprovalDTO.getBlankPagesSadaremId() + "for BlankPages");
                                    issueRaiseApprovalForm.setPensionId("");
                                }
                            }

                        }
                    }


                    //To check Address Change Phase(III)

                    if ((issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.OTHERS) && issueRaiseApprovalForm.getRadioPensionId().equals("2"))
                            || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.NAME) || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONNAME)
                            || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.DATEOFISSUE)
                            || issueRaiseApprovalForm.getCategoryFormId().trim().equals(CommonConstants.RATIONCARDADDRESSCHANGE)) {

                        checkFlag = 1;
                    }

                    if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.PENSIONIDNOTOPENING)) {
                        if (flag.equals("1")) {
                            checkFlag = 1;
                        }
                    }
                    //End



                    //To Check the Category for Technical Difficulties
                    if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.TECHNICALDIFFICULTIES)) {
                        technicalId = issueRaiseApprovalService.checkTechnicalDifficultySADAREMID(ds, issueRaiseApprovalForm);
                        if (technicalId == 1) {
                            checkFlag = 1;
                        } else {
                            request.setAttribute("error", "Entered SADAREM ID  was Dead!");
                            cat = issueRaiseApprovalForm.getCategoryFormId();
                            issueRaiseApprovalForm.reset();
                            issueRaiseApprovalForm.setCategoryFormId(cat);
                        }
                    }
                    //To Check the Category for  Vice versa
                    if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA)) {
                        viceversaNewSADAREMID = issueRaiseApprovalService.vicversaNewSADAREMID(ds, issueRaiseApprovalForm);
                        pensionIdDetails = issueRaiseApprovalService.getPensionIdDetails(ds, loginId, issueRaiseApprovalForm.getNewPensionId(), issueRaiseApprovalForm.getCategoryFormId());
                        if (pensionIdDetails != null && pensionIdDetails.size() > 0) {
                            if (pensionIdDetails.get(1) != null && !pensionIdDetails.get(1).toString().equalsIgnoreCase("") && pensionIdDetails.get(1).toString().equalsIgnoreCase("Dead")) {
                                personid = 1;
                                issueRaiseApprovalForm.setNewPensionId("");
                                request.setAttribute("error", "Pension ID is in Dead");
                            }
                        }
                        if (validSadaremId == 1 && personid == 0 && viceversaNewSADAREMID == 0) {
                            if (issueRaiseApprovalForm.getRadioPensionId().equals("1")) {
                                issueRaiseApprovalForm.setNewSadaremId(issueRaiseApprovalForm.getPopulateviceversaSadaremId());
                                issueRaiseApprovalForm.setNewPensionId(issueRaiseApprovalForm.getPopulateviceversaPensionId());
                            }
                            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                                districtLevelAccessFlag = commonDetails.checkDistrictFlag(issueRaiseApprovalForm.getSadaremId(), districtId);

                                if (districtLevelAccessFlag == false) {
                                    //sadaremid = 1;
                                    request.setAttribute("error", "Enter valid SADAREMID!");
                                    issueRaiseApprovalForm.setSadaremId("");
                                    issueRaiseApprovalForm.setNewName("");
                                    issueRaiseApprovalForm.setNewRelationName("");
                                    issueRaiseApprovalForm.setRadioPensionId("");
                                } else {
                                    checkFlag = 1;
                                }
                            }



                        } else {
                            if (validSadaremId == 0) {
                                issueRaiseApprovalForm.setSadaremId("");
                                request.setAttribute("error", "Entered SADAREM ID  was Dead!");
                            }
                            if (viceversaNewSADAREMID == 1) {
                                issueRaiseApprovalForm.setSadaremId("");
                                if (issueRaiseApprovalForm.getRadioPensionId() != null && !issueRaiseApprovalForm.getRadioPensionId().equals("")) {
                                    request.setAttribute("sadaremIdflag", issueRaiseApprovalForm.getRadioPensionId());
                                }
                                request.setAttribute("error", "Already Raised for SADAREMID to be modified ");
                            }

                        }
                    }


                    // To check Category For MedicalBoard
                    if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.MEDICALBOARDCHANGE)) {
                        if (validSadaremId == 1) {
                            checkFlag = 1;
                        }
                    }

                    if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SURNAME)) {
                        if (validSadaremId == 1) {
                            checkFlag = 1;
                        } else {
                            issueRaiseApprovalForm.reset();
                        }
                    }
                    if (issueRaiseApprovalForm.getRationCardNumber() != null && !issueRaiseApprovalForm.getRationCardNumber().equalsIgnoreCase("")
                            && !issueRaiseApprovalForm.getRationCardNumber().equalsIgnoreCase("null")) {
                        rationCardDataFlag = commonDetails.checkDistrictRationcardFlag(issueRaiseApprovalForm.getRationCardNumber(), districtId);
                        if (rationCardDataFlag == false
                                && (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE))) {
                            rationcard = 1;
                            request.setAttribute("error", "Please Enter Valid RationCard No!");
                            issueRaiseApprovalForm.setRationCardNumber("");
                            cat = issueRaiseApprovalForm.getCategoryFormId();
                            issueRaiseApprovalForm.reset();
                            issueRaiseApprovalForm.setCategoryFormId(cat);
                        } else {
                            rationcardValidFlag = true;

                        }

                        if (rationCardDataFlag == false
                                && (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDNOTOPEN))) {
                            rationcard = 1;
                            request.setAttribute("error", "Please Enter Valid RationCard No!");
                            issueRaiseApprovalForm.setRationCardNumber("");
                            cat = issueRaiseApprovalForm.getCategoryFormId();
                            issueRaiseApprovalForm.reset();
                            issueRaiseApprovalForm.setCategoryFormId(cat);
                            issueRaiseApprovalForm.setMemberName("");
                            issueRaiseApprovalForm.setNewSerialNo("");

                        } else {
                            rationcardValidFlag = true;

                        }
                    }//WAP013402600093
                    AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();
                    if (rationcardValidFlag == true) {
                        if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE)) {
                            String rationCardType = issueRaiseApprovalForm.getRationCardNumber().substring(0, 3);
                            if (issueRaiseApprovalForm.getRationCardNumber() != null) {
                                // rationCardWS1List = wsClient.getDetails(url1, issueRaiseApprovalForm.getRationCardNumber().toUpperCase(), request, "");
                                rationCardWS1List = aadharCardMappingDAO.getCivilSuppliesData(ds, issueRaiseApprovalForm.getRationCardNumber(), districtId);
                                if (rationCardWS1List.size() == 0) {
                                    List<String> dists = dao.getIssueRaisingRationCard(ds, issueRaiseApprovalForm.getRationCardNumber());
                                    String oldDist = "";
                                    if (dists.size() > 0 && !dists.get(0).equals("")) {
                                        oldDist = dists.get(0);
                                        rationCardWS1List = dao.getDataFromCivilDatabaseWithOld(ds, issueRaiseApprovalForm.getRationCardNumber(), oldDist);
                                        // Collections.sort(getRationData, myComparator);
                                    }
                                }
                                if (rationCardWS1List != null && rationCardWS1List.size() > 0) {
                                    Iterator it = rationCardWS1List.listIterator();
                                    rationcard = Integer.parseInt(issueRaiseApprovalForm.getNewSerialNo().trim());
                                    while (it.hasNext()) {
                                        HashMap listData = (HashMap) it.next();
                                        if (Integer.parseInt(listData.get("slNo").toString()) >= rationcard) {
                                            checkFlag = 1;
                                            flag = "2";
                                        } else {
                                            if (flag.equals("0")) {
                                                request.setAttribute("validMsg", "Provided RationCard Serial no is greater than RationCard members");
                                                //issueRaiseApprovalForm.setNewSerialNo("");
                                                //issueRaiseApprovalForm.setRationCardNumber("");
                                            }
                                            if (!flag.equals("2")) {
                                                request.setAttribute("validMsg", "Provided RationCard Serial no is greater than RationCard members");
                                                //issueRaiseApprovalForm.setNewSerialNo("");
                                                // issueRaiseApprovalForm.setRationCardNumber("");
                                            }

                                        }
                                    }
                                } else {
                                    request.setAttribute("validMsg", "Data is not Available");
                                }

                            }

                        } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDNOTOPEN)) {
                            validRationCardNotOpen = issueRaiseApprovalService.validRationCardNotOpen(ds, issueRaiseApprovalForm, districtId);
                            if (validRationCardNotOpen.size() > 0) {
                                for (int i = 0; i < validRationCardNotOpen.size(); i++) {
                                    if (validRationCardNotOpen.get(i).toString().equals(issueRaiseApprovalForm.getNewSerialNo())) {
                                        serialNoFlag = true;
                                    }
                                }

                                if (serialNoFlag == true) {
                                    request.setAttribute("error", "Already Existed With this RationCardSerial No." + issueRaiseApprovalForm.getNewSerialNo());
                                    request.setAttribute("memberFlag", "memberFlag");
                                    issueRaiseApprovalForm.setNewSerialNo("");

                                } else {
                                    checkFlag = 1;
                                }
                            } else {
                                request.setAttribute("error", "RationCardNo Not Found in CivilSupply Database.");
                                issueRaiseApprovalForm.setRationCardNumber("");
                            }
                        }
                    }

                    //This is uesd to Check Phase Conversion.
                    if (issueRaiseApprovalForm.getCategoryFormId() != null && (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)
                            || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)
                            || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV))) {
                        phaseConversion = issueRaiseApprovalService.phasIIIvalidSADAREMID(ds, issueRaiseApprovalForm);
                        if (phaseConversion == 1) {
                            issueRaiseApprovalDTO = issueRaiseApprovalService.pensionPhaseValidation(ds, issueRaiseApprovalForm, districtId);
                        }
                        if (phaseConversion == 1 && issueRaiseApprovalDTO.getPensionPhase() == 1) {
                            //This method is used for Certificate generated or not.
                            phaceConversionValidation = issueRaiseApprovalService.validMedicalBoardSADAREMIDDetails(ds, issueRaiseApprovalForm);
                            if (phaceConversionValidation.equals("1")) {
                                requestPhaseIIIConversion = issueRaiseApprovalService.validPhaseConversion(ds, issueRaiseApprovalForm, districtId);
                                if (requestPhaseIIIConversion.equals("1")) {
                                    checkFlag = 1;
                                } else {
                                    if (requestPhaseIIIConversion.equals("0")) {
                                        request.setAttribute("error", "PensionID provided does not belong to the mentioned phase.");
                                    } else if (requestPhaseIIIConversion.equals("2")) {
                                        request.setAttribute("error", "Pension ID provided has already been assessed in SADAREM Database");
                                    } else if (requestPhaseIIIConversion.equals("3")) {
                                        request.setAttribute("error", "Pension ID has been generated for SADAREMID in PhaseIII.");
                                    }
                                    issueRaiseApprovalForm.setMedicalBoardId("");
                                    issueRaiseApprovalForm.setSadaremId("");
                                    issueRaiseApprovalForm.setPensionId("");
                                    issueRaiseApprovalForm.setMobile("");
                                    issueRaiseApprovalForm.setDecription("");
                                    issueRaiseApprovalForm.setRationCardNumber("");

                                }
                            } else {
                                request.setAttribute("error", "Raise the issue in  Techinical Difficulty");
                                issueRaiseApprovalForm.reset();
                                issueRaiseApprovalForm.setSadaremId("");
                            }
                        } else {
                            if (issueRaiseApprovalDTO.getFlag() == 0) {
                                request.setAttribute("pensionPhaseValidation", issueRaiseApprovalDTO.getPensionPhaseErrorMsg());
                                issueRaiseApprovalForm.setPensionId("");
                            }
                            if (phaseConversion == 0) {
                                request.setAttribute("error", "SADAREMID is not belong to PhaseIII");
                                issueRaiseApprovalForm.setSadaremId("");
                            }
                            issueRaiseApprovalForm.setMobile("");
                            issueRaiseApprovalForm.setDecription("");
                        }
                    }
                }
                if (alreadyRaised == 1 && flag == "1") {
                    validMsgList = issueRaiseApprovalService.getValidMsg(ds, issueRaiseApprovalForm);
                    if (validMsgList.size() > 0) {
                        validMsg = "For this " + validMsgList.get(4).toString() + " for " + validMsgList.get(0).toString() + " is"
                                + " already been raised with requestId:" + validMsgList.get(1).toString() + " on " + validMsgList.get(2).toString() + " and Status is " + validMsgList.get(3).toString() + "";
                        request.setAttribute("error", validMsg);
                        cat = issueRaiseApprovalForm.getCategoryFormId();
                        issueRaiseApprovalForm.reset();
                        issueRaiseApprovalForm.setCategoryFormId(cat);
                    }
                }

                //This is used for to Capture Details
                if (checkFlag == 1) {
                    issueRaise = issueRaiseApprovalService.insertIssueRasingDetails(ds, issueRaiseApprovalForm, campName, systemIp, loginId, districtId, rationCardNo);
                    reqId = issueRaiseApprovalService.getrequestMaxId(ds);
                    if (issueRaiseApprovalForm.getCategoryFormId() != null
                            && !issueRaiseApprovalForm.getCategoryFormId().equals("")
                            && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA)
                            && issueRaiseApprovalForm.getFileUpload() != null
                            && !issueRaiseApprovalForm.getFileUpload().toString().equals("")) {

                        fileRestricttion = issueRaiseApprovalForm.getFileUpload().getFileSize() / 1024;
                        if (fileRestricttion >= 3 && fileRestricttion <= 20) {
                            FormFile myFile = issueRaiseApprovalForm.getFileUpload();
                            fileName = myFile.getFileName();
                            int dotPos = fileName.lastIndexOf(".");
                            extension = fileName.substring(dotPos);
                            fileName = reqId + extension;
                            issueRaiseApprovalForm.setIsseRaisefilePath(fileName);

                            String strDirectoytemp = CommonConstants.ISSUEDOCUMENTS;
                            File subdir = null;
                            subdir = new File(getServlet().getServletContext().getRealPath("/") + "RaisedDocuments");
                            if (!subdir.exists()) {
                                subdir.mkdirs();
                            }
                            CommonDetails.uploadingFile(issueRaiseApprovalForm.getFileUpload(), "" + subdir, fileName);

                            File tempdir = new File(strDirectoytemp);
                            if (!tempdir.exists()) {
                                tempdir.mkdirs();
                            }
                            File tempdistdir = new File(tempdir + "/" + "RaisedDocuments");
                            if (!tempdistdir.exists()) {
                                tempdistdir.mkdirs();
                            }
                            CommonDetails.uploadingFile(issueRaiseApprovalForm.getFileUpload(), "" + tempdistdir, fileName);
                            issueRaiseApprovalService.updateUserRaisedDocumentPath(ds, reqId, fileName);
                        } else {
                            request.setAttribute("statusMsg", "<font color='red'>File Size Minimum 3MB and maximum 20 MB</font>");
                        }
                    }
//                    } else {
//                        issueRaise = issueRaiseApprovalService.insertIssueRasingDetails(ds, issueRaiseApprovalForm, campName, systemIp, loginId, districtId, rationCardNo);
//                        issueRaiseApprovalForm.setCategoryFormId(issueRaiseApprovalForm.getCategoryFormId());
//                        insertRaisngDetails = 1;
//                    }

                    if (issueRaise > 0) {

                        if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONNAME)
                                || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SURNAME)) {
                            request.setAttribute("msg", "Issue  " + reqId + " Raised SuccessFully & Resolved. Please Check in Camp incharge Login.");
                        } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)
                                || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)
                                || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV)) {
                             success = issueRaiseApprovalService.approvalPhaseConversion(ds, issueRaiseApprovalForm, districtId);
                            if (success > 0) {
                                request.setAttribute("msg", "Issue  " + reqId + " Raised SuccessFully & Resolved. Please Check.");
                            } else {
                                request.setAttribute("msg", "Issue  " + reqId + " Raised SuccessFully.Error Occured while resolving. Contact SADAREM Support Team");
                            }
                        }  
                        else 
                        {
                            request.setAttribute("msg", "Issue Raised SuccessFully! generated IssueID :" + reqId);
                            flag = "success";
                        }
                        issueRaiseApprovalForm.reset();
                        subjectMsg = "Reference IssueID: " + reqId;
                        //Email Body
                        issueRaiseEmailBodyList = issueRaiseApprovalService.getMailForEmailIssueRaiseBody(ds, loginId);
                        if ((issueRaiseEmailBodyList != null && issueRaiseEmailBodyList.size() > 0)) {
                            issueEmailBody = emailUtility.issueRaiseBodyFormat(issueRaiseEmailBodyList, passwordData);
                          //  System.out.println("issueEmailBody"+issueEmailBody);
                            emailList = issueRaiseApprovalService.ToIssueRaiseList(ds);
                            for (int i = 0; i < emailList.size(); i++) {
                                email = emailList.get(i).toString();
                                emailId = new InternetAddress(email, "");
                                RecMail.add(emailId);
                            }
                            emailStatus = EmailUtility.SendEmail(RecMail, CCMail, BCCMail, subjectMsg, issueEmailBody, CommonConstants.Email_ID);
                            issueRaiseApprovalForm.reset();
                        }
                        target = "success";
                    } else {
                        request.setAttribute("msg", "Error in Issue Raised!");
                        issueRaiseApprovalForm.reset();
                    }

                    if (issueRaiseApprovalForm.getCategoryFormId() != null
                            && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDADDRESSCHANGE)) {
                        this.rationCardAddressChangebasedUponCivilSupplies(mapping, form, request, response);
                    }
                }
            }

            this.unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward raisedDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "issueRaised";
        DataSource ds = null;
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        TerritoryDAO territoryDAO = new TerritoryDAO();
        ArrayList approvedList = new ArrayList();
        ArrayList catgoryList = new ArrayList();
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            approvedList = issueRaiseApprovalService.getApprovalListDetails(ds, issueRaiseApprovalForm.getDistrictId(), "0", issueRaiseApprovalForm.getCategoryFormId(), issueRaiseApprovalForm.getStatus(), issueRaiseApprovalForm.getId());
            request.setAttribute("approvedList", approvedList);
            districtList = territoryDAO.getDistricts(ds);
            catgoryList = issueRaiseApprovalService.categoryApprovalList(ds);
            issueRaiseApprovalForm.setDistrictList(districtList);
            issueRaiseApprovalForm.setCategoryList(catgoryList);
            //getBoardList(mapping,form,request,response);
            request.setAttribute("statusRequest", issueRaiseApprovalForm.getStatus());


            if (approvedList != null && approvedList.size() > 0) {

                for (int i = 0; i < approvedList.size(); i++) {
                    HashMap list1 = new HashMap();
                    list1 = (HashMap) approvedList.get(i);
                    request.setAttribute("personStatusDet" + i, list1.get("requeststatus").toString());
                }
            }
            if (approvedList.isEmpty() && issueRaiseApprovalForm.getStatus() != null) {
                request.setAttribute("msg", "No Data Found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getParticularRaisedDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "issueResolve";
        DataSource ds = null;
        HttpSession session = request.getSession();
        ArrayList toEmailIdList = new ArrayList();
        ArrayList ccEmailIdList = new ArrayList();
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();

        ArrayList requestRaiseList = new ArrayList();
        String districtId = null;
        String requestRaiseId = null;
        String categoryId = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            if (request.getParameter("requestId") != null) {
                requestRaiseId = request.getParameter("requestId");
            }
            //Category used for Only based on this Approvaed,Reject,Hold
            if (request.getParameter("categoryId") != null) {
                categoryId = request.getParameter("categoryId");
                request.setAttribute("statusCategoryId", categoryId);
            }
            requestRaiseList = issueRaiseApprovalService.getRequestRaisedDetails(ds, requestRaiseId, categoryId);


            if (requestRaiseList.size() > 0) {
                request.setAttribute("requestRaiseList", requestRaiseList);
            } else {
                request.setAttribute("msg", "<font color ='red'>No Data Found!</font>");
            }
            toEmailIdList = issueRaiseApprovalService.ToIssueResloveList(ds, requestRaiseId);
            request.setAttribute("toEmailIdList", toEmailIdList);
            ccEmailIdList = issueRaiseApprovalService.CCIssueResloveList(ds, requestRaiseId);
            request.setAttribute("ccEmailIdList", ccEmailIdList);
            request.setAttribute("requestRaiseId", requestRaiseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        issueRaiseApprovalForm.setMode("");
        return mapping.findForward(target);
    }

    public ActionForward getPhotoDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "requestPhotoView";

        String fileName = null;
        String folderName = null;
        String fileWithExt = null;
        // RequestInformationDTO requestInformationDTO  = new RequestInformationDTO ();
        try {
            String requestId1 = request.getParameter("requestId");
            if (requestId1 != null) {
                folderName = "RaisedDocuments";
            }

            File folder = new File(CommonConstants.ISSUEDOCUMENTS + folderName);

            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null && !listOfFiles.equals("")) {

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        String file = listOfFiles[i].getName();

                        String filename = file.substring(0, file.lastIndexOf("."));

                        if (filename.equalsIgnoreCase(requestId1)) {
                            fileWithExt = listOfFiles[i].getName();

                        }
                    }
                }

                if (fileWithExt != null) {
                    fileName = fileWithExt;
                    BufferedInputStream in = null;
                    File fileDetailsData = new File(folder + "\\" + fileName);

                    FileInputStream fin = new FileInputStream(fileDetailsData);
                    in = new BufferedInputStream(fin);

                    ServletOutputStream out = response.getOutputStream();
                    response.setContentType("application/force-download");
                    response.addHeader("Content-Disposition", "attachment; filename=\"" + fileDetailsData + "\"");
                    byte[] buffer = new byte[4 * 1024];
                    int data = 0;
                    try {
                        while ((data = in.read(buffer)) != -1) {
                            out.write(buffer, 0, data);
                        }
                        out.flush();
                    } catch (Exception e) {
                        //System.exit(1);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return mapping.findForward(target);
    }

    public ActionForward issueTrackerDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        String target = "issueTracker";

        DataSource ds = null;
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        HttpSession session = request.getSession();

        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();

        ArrayList issueList = new ArrayList();
        String userId = (String) session.getAttribute("useridData");
        ArrayList catgoryList = new ArrayList();
        String holdStatus = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            catgoryList = issueRaiseApprovalService.getCatgoryDetails(ds);
            issueRaiseApprovalForm.setCategoryList(catgoryList);

            issueList = issueRaiseApprovalService.getTaskTrackerDetails(ds, issueRaiseApprovalForm.getId(), issueRaiseApprovalForm, userId);


            if (issueList.size() > 0) {
                request.setAttribute("issueList", issueList);

            } else {
                request.setAttribute("msg", "Issue Details are Not Available!");
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
        return mapping.findForward(target);
    }

    public ActionForward getIssueTrackerDocumentDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "issueDocumentView";

        String fileName = null;
        String folderName = null;
        String fileWithExt = null;
        int j = 1;
        String flag = null;

        // RequestInformationDTO requestInformationDTO  = new RequestInformationDTO ();
        try {

            flag = request.getParameter("flag");

            //  request.setAttribute("requestId", requestId);
            String requestFormId = request.getParameter("requestId");
            request.setAttribute("personCodeId", requestFormId);

            if (flag.equals("1")) {
                folderName = "RaisedDocuments";
            } else {
                folderName = "ReslovedDocuments";
            }
            File folder = new File(CommonConstants.ISSUEDOCUMENTS + folderName);

            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null && !listOfFiles.equals("")) {

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        String file = listOfFiles[i].getName();

                        String filename = file.substring(0, file.lastIndexOf("."));

                        if (filename.equalsIgnoreCase(requestFormId)) {
                            fileWithExt = listOfFiles[i].getName();

                        }
                    }
                }

                if (fileWithExt != null) {
                    fileName = fileWithExt;
                    String contentType = null;
                    contentType = "txt";
                    BufferedInputStream in = null;
                    File fileDetailsData = new File(folder + "\\" + fileName);

                    FileInputStream fin = new FileInputStream(fileDetailsData);
                    in = new BufferedInputStream(fin);

                    ServletOutputStream out = response.getOutputStream();

                    response.setContentType("application/force-download");
                    response.addHeader("Content-Disposition", "attachment; filename=\"" + fileDetailsData + "\"");


                    byte[] buffer = new byte[4 * 1024];

                    int data = 0;

                    try {
                        while ((data = in.read(buffer)) != -1) {
                            out.write(buffer, 0, data);
                        }
                        out.flush();
                    } catch (Exception e) {
                        //System.exit(1);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return mapping.findForward(target);
    }

    public ActionForward getBoardList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ArrayList catgoryList = new ArrayList();
        ArrayList getBoardList = new ArrayList();
        ArrayList districtList = new ArrayList();
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        TerritoryDAO territoryDAO = new TerritoryDAO();
        DataSource ds = null;
        String target = "issueRaised";
        String personCode = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            districtList = territoryDAO.getDistricts(ds);

            IssueRaiseApprovalService issueRaiseApprovalService =
                    IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
            personCode = issueRaiseApprovalForm.getSadaremId();
            getBoardList = issueRaiseApprovalService.getCampBasedOnLoginDetails(ds, issueRaiseApprovalForm.getDistrictId(), personCode);

            catgoryList = issueRaiseApprovalService.getCatgoryDetails(ds);
            issueRaiseApprovalForm.setCategoryList(catgoryList);
            issueRaiseApprovalForm.setDistrictList(districtList);
            issueRaiseApprovalForm.setMedicalBoardList(getBoardList);
            issueRaiseApprovalForm.setCategoryFormId("0");
            issueRaiseApprovalForm.setStatus("0");



        } catch (Exception e) {
            e.printStackTrace();

        }
        return mapping.findForward(target);


    }

// Added by Victor
    public ActionForward getIssueResolvedDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String requestRaiseId = null;
        ArrayList IssueResolvedDetails = new ArrayList();
        ArrayList requestRaiseList = new ArrayList();
        DataSource ds = null;
        String categoryId = null;
        String target = "ResolvedDetails";
        String holdStatus = null;

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;

        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("requestId") != null) {
                requestRaiseId = request.getParameter("requestId");
            }
            if (request.getParameter("categoryId") != null) {
                categoryId = request.getParameter("categoryId");
                request.setAttribute("statusCategoryId", categoryId);
            }
            request.setAttribute("requestRaiseId", requestRaiseId);
            requestRaiseList = issueRaiseApprovalService.getRequestRaisedDetails(ds, requestRaiseId, categoryId);
            if (requestRaiseList.size() > 0) {
                request.setAttribute("requestRaiseList", requestRaiseList);
            } else {
                request.setAttribute("requestRaise", "requestRaise");
                request.setAttribute("error", "<font color ='red'>Details are Not Available!</font>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward nameChangesDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        DataSource ds = null;
        ArrayList nameChangesList = new ArrayList();
        HttpSession session = request.getSession();
        CommonDetails commonDetails = new CommonDetails();
        boolean districtLevelAccessFlag = false;
        String districtId = null;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtId = (String) session.getAttribute("districtId");
            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                districtLevelAccessFlag = commonDetails.checkDistrictFlag(issueRaiseApprovalForm.getSadaremId(), districtId);
                if (districtLevelAccessFlag == false) {
                    request.setAttribute("error", "Please Enter Valid SADAREM ID");
                    issueRaiseApprovalForm.setSadaremId("");
                } else {
                    nameChangesList = issueRaiseApprovalService.getSurNameDetails(ds, issueRaiseApprovalForm);
                    if (nameChangesList.size() > 0) {
                        issueRaiseApprovalForm.setPopulateSurName(nameChangesList.get(0).toString());
                        issueRaiseApprovalForm.setPopulateName(nameChangesList.get(1).toString());
                    } else {
                        request.setAttribute("error", "Entered SADAREM ID  was Dead!");
                        issueRaiseApprovalForm.setSadaremId("");

                    }
                    issueRaiseApprovalForm.setMobile("");
                    issueRaiseApprovalForm.setDecription("");

                    if (!issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.NAME)) {
                        issueRaiseApprovalForm.setPopulateName("");
                        issueRaiseApprovalForm.setPopulateSurName("");
                    }
                }
            }
            this.unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("hideNameChangeFlag", "hideNameChangeFlag");
        return mapping.findForward("nameChangeRequest");
    }

    public ActionForward surNameChangesDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        DataSource ds = null;
        ArrayList nameChangesList = new ArrayList();
        HttpSession session = request.getSession();
        CommonDetails commonDetails = new CommonDetails();
        boolean districtLevelAccessFlag = false;
        int sadaremid = 0;
        String districtId = null;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtId = (String) session.getAttribute("districtId");
            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                districtLevelAccessFlag = commonDetails.checkDistrictFlag(issueRaiseApprovalForm.getSadaremId(), districtId);
                if (districtLevelAccessFlag == false) {
                    sadaremid = 1;
                    issueRaiseApprovalForm.setSadaremId("");
                } else {
                    nameChangesList = issueRaiseApprovalService.getSurNameDetails(ds, issueRaiseApprovalForm);
                    if (nameChangesList.size() > 0) {
                        issueRaiseApprovalForm.setPopulateSurName(nameChangesList.get(0).toString());
                        issueRaiseApprovalForm.setPopulateName(nameChangesList.get(1).toString());
                    } else {
                        request.setAttribute("error", "Entered SADAREM ID was Dead!");
                        issueRaiseApprovalForm.setSadaremId("");
                    }
                    issueRaiseApprovalForm.setMobile("");
                    issueRaiseApprovalForm.setDecription("");
                    if (!issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SURNAME)) {
                        issueRaiseApprovalForm.setPopulateName("");
                        issueRaiseApprovalForm.setPopulateSurName("");
                    }
                }
            }
            if (sadaremid == 1) {
                request.setAttribute("error", "Please Enter Valid SADAREM ID");
                issueRaiseApprovalForm.setCategoryFormId("0");
                issueRaiseApprovalForm.setMobile("");
                issueRaiseApprovalForm.setDecription("");
            }
            this.unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("hideNameChangeFlag", "hideNameChangeFlag");
        return mapping.findForward("surNameRequest");
    }

    public ActionForward relationNameChanges(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        String relationName = null;
        HttpSession session = request.getSession();
        CommonDetails commonDetails = new CommonDetails();
        boolean districtLevelAccessFlag = false;
        int sadaremid = 0;
        String districtId = null;
        int succ = 1;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtId = (String) session.getAttribute("districtId");
            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                districtLevelAccessFlag = commonDetails.checkDistrictFlag(issueRaiseApprovalForm.getSadaremId(), districtId);
                if (districtLevelAccessFlag == false) {
                    sadaremid = 1;
                } else {
                    relationName = issueRaiseApprovalService.getRelationNameDetails(ds, issueRaiseApprovalForm);

                    if (relationName != null && !relationName.equals("") && relationName.length() > 0) {
                        issueRaiseApprovalForm.setPopulateRelationName(relationName);
                    } else {
                        request.setAttribute("error", "Entered SADAREM ID was Dead!");
                        issueRaiseApprovalForm.setSadaremId("");
                    }

                }
            }
            if (sadaremid == 1) {
                succ = 0;
                String s = "Please Enter Valid ";
                if (sadaremid == 1) {
                    s = s.concat(" SADAREM ID ,");
                }
                s = s.substring(0, (s.length() - 1));
                request.setAttribute("error", s);
                issueRaiseApprovalForm.reset();
            }
            this.unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("hideRelationNameChangeFlag", "hideRelationNameChangeFlag");
        return mapping.findForward("relationNameRequest");
    }

    public ActionForward getSADAREMIDDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        ArrayList sadaremIdList = new ArrayList();
        String districtId = null;
        HttpSession session = request.getSession();
        boolean rationCardDataFlag = false;
        CommonDetails commonDetails = new CommonDetails();
        int rationcard = 0;
        int succ = 1;
        ArrayList catgoryList = new ArrayList();
        StringBuffer validMsg = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if ((String) session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            if (issueRaiseApprovalForm.getRationCardNumber() != null && !issueRaiseApprovalForm.getRationCardNumber().equalsIgnoreCase("") && !issueRaiseApprovalForm.getRationCardNumber().equalsIgnoreCase("null")) {
                rationCardDataFlag = commonDetails.checkDistrictRationcardFlag(issueRaiseApprovalForm.getRationCardNumber(), districtId);
                if (rationCardDataFlag == false) {
                    rationcard = 1;
                    issueRaiseApprovalForm.setRationCardNumber("");
                }
            }
            if (rationcard == 1) {
                succ = 0;
                validMsg = new StringBuffer();
                validMsg.append("Please Enter Valid");
                if (rationcard == 1) {
                    validMsg.append(" RationCard No,");
                }
                request.setAttribute("error", validMsg.toString().substring(0, (validMsg.length() - 1)));
                // issueRaiseApprovalForm.setCategoryFormId("0");
                issueRaiseApprovalForm.setMobile("");
                issueRaiseApprovalForm.setDecription("");
            }
            if (succ == 1) {
                if (issueRaiseApprovalForm.getRationCardNumber() != null && !issueRaiseApprovalForm.getRationCardNumber().equals("") && issueRaiseApprovalForm.getRationCardNumber().length() > 0) {
                    sadaremIdList = issueRaiseApprovalService.getSADAREMIDDetails(ds, issueRaiseApprovalForm, districtId);
                    if (sadaremIdList.size() > 0) {
                        issueRaiseApprovalForm.setSadaremIdList(sadaremIdList);
                        request.setAttribute("sadarermIdList", "sadarermIdList");
                    } else {
                        request.setAttribute("errorMsg", "No Details for this RationCard No:" + issueRaiseApprovalForm.getRationCardNumber());
                        issueRaiseApprovalForm.setRationCardNumber("");
                    }
                    request.setAttribute("rationCardNoChange", issueRaiseApprovalForm.getRationCardNumber());
                }
            }
            catgoryList = issueRaiseApprovalService.getCatgoryDetails(ds);
            issueRaiseApprovalForm.setCategoryList(catgoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("rationCardSerialNo");
    }

    public ActionForward getMedicalAddressDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        String campName = null;
        ArrayList campAddress = new ArrayList();
        HttpSession session = request.getSession();
        String districtId = null;
        boolean districtLevelAccessFlag = true;
        CommonDetails commonDetails = new CommonDetails();
        int sadaremid = 0;
        int succ = 1;
        StringBuffer validationMsg = new StringBuffer();
        ArrayList campList = new ArrayList();
        String name[] = null;
        String hospitalName = null;
        String venueAddress = null;
        String personCode = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            String campId = issueRaiseApprovalForm.getMedicalBoardId();
            personCode = issueRaiseApprovalForm.getSadaremId();
            campList = issueRaiseApprovalService.getCampBasedOnLoginDetails(ds, districtId, personCode);
            campName = issueRaiseApprovalForm.getMedicalBoardId();
            name = campName.split(",");
            hospitalName = name[0];
            venueAddress = name[1];

            campAddress = issueRaiseApprovalService.getCampAddress(ds, districtId, hospitalName);
            issueRaiseApprovalForm.setCampAddressList(campAddress);
            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                districtLevelAccessFlag = commonDetails.checkDistrictFlag(issueRaiseApprovalForm.getSadaremId(), districtId);
                if (districtLevelAccessFlag == false) {
                    sadaremid = 1;
                    issueRaiseApprovalForm.setSadaremId("");
//                    request.setAttribute("sadaremID", "Please Enter Valid sadaremId!");
                }
            }
            if (sadaremid == 1) {
                succ = 0;
                validationMsg = new StringBuffer();
                //validMsg = "Please Enter Valid ";
                validationMsg.append("Please Enter Valid ");
                if (sadaremid == 1) {
                    validationMsg.append(" SADAREM ID ,");
                }
            }
            //request.setAttribute("error", validationMsg.toString().substring(0, (validationMsg.length() - 1)));
            issueRaiseApprovalForm.setSadaremId(issueRaiseApprovalForm.getSadaremId());
            request.setAttribute("campAddress", "campAddress");
        } catch (Exception e) {
            e.printStackTrace();
        }
        campList = issueRaiseApprovalService.getCampBasedOnLoginDetails(ds, districtId, personCode);
        issueRaiseApprovalForm.setCampList(campList);
        this.unspecified(mapping, form, request, response);
        request.setAttribute("hideMedicalBoardChangeFlag", "hideMedicalBoardChangeFlag");
        request.setAttribute("validMedicalBoard", "validMedicalBoard");
        return mapping.findForward("medicalBoardChange");
    }

    public ActionForward getCategoryWiseDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        TerritoryDAO territoryDAO = new TerritoryDAO();
        DataSource ds = null;
        String target = "";
        String districtId = null;
        HttpSession session = request.getSession();
        ArrayList userNameList = new ArrayList();
        ArrayList districtList = new ArrayList();


        try {
            ds = getDataSource(request);

            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.ADDRESSCHANGEPHASEIII)) {
                request.setAttribute("addressChange", "addressChange"); 
               request.setAttribute("addressMsg", "Note: For Address Change New SADAREMID will not generate.Once the address change issue is approved,all the changes will reflect in the existing SADAREM id ");
                target = "addressChangePhaseIII";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.NODATA)) {
                target = "addressChangePhaseIII";
                //This Category for BlankPages and Others
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.BLANKPAGES) || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.OTHERS)) {
                target = "blankPages";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.FORGOTPASSWORD)) {
                districtId = (String) session.getAttribute("districtId");
                userNameList = issueRaiseApprovalService.getUserNameDetails(ds, districtId);
                issueRaiseApprovalForm.setUserNameList(userNameList);
                target = "forgetPasswordRequest";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.INVALIDPERSONCODE)) {
                target = "InvalidPersonCode";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.NAME)) {
                target = "nameChangeRequest";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SURNAME)) {
                target = "surNameRequest";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONNAME)) {
                target = "relationNameRequest";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA)) {
                target = "viceVersa";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.PENSIONIDNOTOPENING)) {
                target = "pensionIdNotOpening";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.TECHNICALDIFFICULTIES)) {
                target = "technicalDifficulty";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDNOTOPEN)) {
                target = "rationCardNotOpen";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE)) {
                target = "rationCardSerialNo";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.MEDICALBOARDCHANGE)) {
                target = "medicalBoardChange";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)
                    || issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV)) {
                target = "phaseIIIConversion";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.DATEOFISSUE)) {
                target = "dateOfIssue";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONTYPECHANGE)) {
                request.setAttribute("relationtypechange", "relationtypechange");
                target = "subDisabilityType";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SUBTYPEDISABILITYTYPE)) {
                target = "subDisabilityType";
            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.SUB_SUB_TYPEDISABILITYTYPE)) {
                target = "subDisabilityType";

            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.DIAGNOSUBDISABILITYTYPE)) {
                request.setAttribute("diag", "");
                target = "subDisabilityType";

            } else if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.TEMPRARYCERTIFICATE)) {
                target = "subDisabilityType";
            } else {
                districtList = territoryDAO.getDistricts(ds);
                target = "success";
            }
            issueRaiseApprovalForm.setMobile("");
            issueRaiseApprovalForm.setDecription("");
            issueRaiseApprovalForm.setDistrictList(districtList);
            issueRaiseApprovalForm.setRationCardNo(null);
            issueRaiseApprovalForm.setSadaremId("");
            String cat = issueRaiseApprovalForm.getCategoryFormId();
            issueRaiseApprovalForm.reset();
            issueRaiseApprovalForm.setCategoryFormId(cat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.unspecified(mapping, form, request, response);
        return mapping.findForward(target);
    }

    public ActionForward basedUponSADAREMIDDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        ArrayList basedUponSADAREMIDDetails = new ArrayList();
        ArrayList catgoryList = new ArrayList();
        ArrayList sadaremIdList = new ArrayList();
        String districtId = null;
        HttpSession session = request.getSession();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            basedUponSADAREMIDDetails = issueRaiseApprovalService.getbasedUponSADAREMIDDetails(ds, issueRaiseApprovalForm);
            issueRaiseApprovalForm.setPopulateName(basedUponSADAREMIDDetails.get(0).toString());
            issueRaiseApprovalForm.setPopulateRelationName(basedUponSADAREMIDDetails.get(1).toString());
            issueRaiseApprovalForm.setPopulateSerialNo(basedUponSADAREMIDDetails.get(2).toString());
            sadaremIdList = issueRaiseApprovalService.getSADAREMIDDetails(ds, issueRaiseApprovalForm, districtId);
            issueRaiseApprovalForm.setSadaremIdList(sadaremIdList);
            request.setAttribute("sadarermIdList", "sadarermIdList");
            catgoryList = issueRaiseApprovalService.getCatgoryDetails(ds);
            issueRaiseApprovalForm.setCategoryList(catgoryList);
            request.setAttribute("rationCardNoChange", issueRaiseApprovalForm.getHiddentoSetRationCardNo());
            request.setAttribute("sadaremFlag", "sadaremFlag");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("rationCardSerialNo");
    }

    public ActionForward validMedicalBoardSADAREMIDDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        ArrayList catgoryList = new ArrayList();
        String validMedicalBoardSADAREMID = null;
        HttpSession session = request.getSession();
        String districtId = null;
        ArrayList campList = new ArrayList();
        boolean districtLevelAccessFlag = true;
        CommonDetails commonDetails = new CommonDetails();
        int sadaremid = 0;
        int succ = 1;
        boolean errrFlag = true;
        StringBuffer validationMsg = new StringBuffer();
        int validSadaremId = 0;
        String personCode = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtId = (String) session.getAttribute("districtId");

            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                districtLevelAccessFlag = commonDetails.checkDistrictFlag(issueRaiseApprovalForm.getSadaremId(), districtId);
                if (districtLevelAccessFlag == false) {
                    sadaremid = 1;
                    issueRaiseApprovalForm.setSadaremId("");
//                    request.setAttribute("sadaremID", "Please Enter Valid sadaremId!");
                }
            }
            if (sadaremid == 1) {
                succ = 0;
                validationMsg = new StringBuffer();
                //validMsg = "Please Enter Valid ";
                validationMsg.append("Please Enter Valid ");

                if (sadaremid == 1) {
                    validationMsg.append(" SADAREM ID");
                    errrFlag = false;
                    issueRaiseApprovalForm.setSadaremId("");
                }
            }
            request.setAttribute("error", validationMsg.toString());
            if (errrFlag == true) {
                if (issueRaiseApprovalForm.getCategoryFormId() != null
                        && !issueRaiseApprovalForm.getCategoryFormId().equals("")
                        && issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.MEDICALBOARDCHANGE)) {

                    validSadaremId = issueRaiseApprovalService.validSadaremId(ds, issueRaiseApprovalForm);
                    if (validSadaremId > 0) {
                        validMedicalBoardSADAREMID = issueRaiseApprovalService.validMedicalBoardSADAREMIDDetails(ds, issueRaiseApprovalForm);
                        if (validMedicalBoardSADAREMID.equals("1")) {
                            request.setAttribute("validMedicalBoard", "validMedicalBoard");
                        } else {
                            request.setAttribute("msgForNoData", "Raise the issue in  techinical difficulty");
                        }
                    } else {
                        request.setAttribute("validMsg", "Entered SADAREM ID was Dead!");
                        issueRaiseApprovalForm.setSadaremId("");
                    }
                }
            }
            catgoryList = issueRaiseApprovalService.getCatgoryDetails(ds);
            issueRaiseApprovalForm.setCategoryList(catgoryList);
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }

            personCode = issueRaiseApprovalForm.getSadaremId();
            campList = issueRaiseApprovalService.getCampBasedOnLoginDetails(ds, districtId, personCode);
            issueRaiseApprovalForm.setCampList(campList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("medicalBoardChange");


    }

    public ActionForward webServiceRationCardNotOpen(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        ArrayList catgoryList = new ArrayList();
        String loginId = null;
        String systemIp = null;

        HttpSession session = request.getSession();
        String districtId = null;
        ArrayList campList = new ArrayList();
        ArrayList rationCardWS1List = new ArrayList();
        ArrayList rationCardWS2List = new ArrayList();
        ArrayList rationCardWS1DisplayList = new ArrayList();
        ArrayList rationCardWS2DisplayList = new ArrayList();
        URL url1 = new URL(CommonConstants.Url1);
        // AponlineWSClient wsClient = new AponlineWSClient();
        String expection = null;
        PersonCodeCheckDAO dao = new PersonCodeCheckDAO();
        ArrayList getRationData = new ArrayList();
        int count = 0;
        String slNo = null;
        String personCode = null;
        CommonDetails commonDetails = new CommonDetails();
        boolean rationCardDataFlag = false;
        boolean rationcardValidFlag = false;
        int rationcard = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            catgoryList = issueRaiseApprovalService.getCatgoryDetails(ds);
            issueRaiseApprovalForm.setCategoryList(catgoryList);
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            if (session.getAttribute("loginid") != null) {
                loginId = (String) session.getAttribute("loginid");
            }
            systemIp = request.getRemoteAddr();
            personCode = issueRaiseApprovalForm.getSadaremId();
            campList = issueRaiseApprovalService.getCampBasedOnLoginDetails(ds, districtId, personCode);
            issueRaiseApprovalForm.setCampList(campList);

            if (issueRaiseApprovalForm.getRationCardNumber() != null && !issueRaiseApprovalForm.getRationCardNumber().equalsIgnoreCase("") && !issueRaiseApprovalForm.getRationCardNumber().equalsIgnoreCase("null")) {
                rationCardDataFlag = commonDetails.checkDistrictRationcardFlag(issueRaiseApprovalForm.getRationCardNumber(), districtId);
                if (rationCardDataFlag == false && (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RATIONCARDNOTOPEN))) {
                    rationcard = 1;
                    request.setAttribute("error", "Please Enter Valid RationCard No!");
                    issueRaiseApprovalForm.setRationCardNumber("");
                    String cat = issueRaiseApprovalForm.getCategoryFormId();
                    issueRaiseApprovalForm.reset();
                    issueRaiseApprovalForm.setCategoryFormId(cat);
                } else {
                    rationcardValidFlag = true;

                }
            }
            if (issueRaiseApprovalForm.getRationCardNumber() != null && rationcardValidFlag == true) {
                // rationCardWS1List = wsClient.getDetails(url1, issueRaiseApprovalForm.getRationCardNumber().toUpperCase(), request, "");
                AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();
                rationCardWS1List = aadharCardMappingDAO.getCivilSuppliesData(ds, issueRaiseApprovalForm.getRationCardNumber(), districtId);
                if (rationCardWS1List.size() == 0) {
                    List<String> dists = dao.getIssueRaisingRationCard(ds, issueRaiseApprovalForm.getRationCardNumber());
                    String oldDist = "";
                    if (dists.size() > 0 && !dists.get(0).equals("")) {
                        oldDist = dists.get(0);
                        rationCardWS1List = dao.getDataFromCivilDatabaseWithOld(ds, issueRaiseApprovalForm.getRationCardNumber(), oldDist);
                        // Collections.sort(getRationData, myComparator);
                    }
                }
                if (rationCardWS1List != null && rationCardWS1List.size() > 0) {
                    Iterator it = rationCardWS1List.listIterator();
                    while (it.hasNext()) {
                        HashMap listData = (HashMap) it.next();
                        if (listData.get("slNo") != null) {
                            slNo = listData.get("slNo").toString();
                        }
                        //count = dao.getDataFromCivil(ds, issueRaiseApprovalForm.getRationCardNumber(), slNo, request);
                        //ArrayList list = wsClient.getDetailsRationCount(issueRaiseApprovalForm.getRationCardNumber().toUpperCase(), count, ds, listData);
                        //rationCardWS1DisplayList.addAll(list);
                    }
                    request.setAttribute("rationCardListPresent", issueRaiseApprovalForm.getRationCardNumber() + " already Present in Civil Supply. Please check in SADAREM.");
                    // Fectching Data from CivilSupplies Url2
                }
                getRationData = dao.getDataFromCivilSuppliesDatabase(ds, issueRaiseApprovalForm.getRationCardNumber().toUpperCase(), request); // Get data from Civil supplies database
                if (getRationData.size() == 0) {
                    List<String> dists = dao.getIssueRaisingRationCard(ds, issueRaiseApprovalForm.getRationCardNumber().toString());
                    String oldDist = "";
                    if (dists.size() > 0 && !dists.get(0).equals("")) {
                        oldDist = dists.get(0);
                        getRationData = dao.getDataFromCivilDatabaseWithOld(ds, issueRaiseApprovalForm.getRationCardNumber().toUpperCase(), oldDist);
                    }
                } else {
                    request.setAttribute("rationCardListPresent", "Details are not Available in SADAREM DataBase!");
                    issueRaiseApprovalForm.setRationCardNumber("");
                }


                if (getRationData != null && getRationData.size() > 0) {
                    request.setAttribute("hideButton", "hideButton");
                    request.setAttribute("rationCardListPresent", issueRaiseApprovalForm.getRationCardNumber() + "  already Present in Civil Supply. Please check in SADAREM DataBase.");
                } else {
                    request.setAttribute("serverDown", "RationCard No " + issueRaiseApprovalForm.getRationCardNumber() + " is not Found in DataBase.");
                }


            } else {
                issueRaiseApprovalForm.setRationCardNumber("");
            }
            issueRaiseApprovalForm.setRationCardNumber("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("rationCardNotOpen");
    }

    public ActionForward issueApprovalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        int updateApprovalDetails = 0;
        int addressPhaseIII = 0;
        String districtId = null;
        HttpSession session = request.getSession();
        ArrayList toEmailIdList = new ArrayList();
        ArrayList ccEmailIdList = new ArrayList();
        ArrayList<InternetAddress> RecMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> CCMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> BCCMail = new ArrayList<InternetAddress>();
        EmailUtility emailUtility = new EmailUtility();
        String issueEmailBody = null;
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = null;
        String requestId = null;
        String userId = null;
        InternetAddress toemailId = null;
        InternetAddress ccemailId = null;
        boolean emailStatus = false;
        int approvalMedical = 0;
        int nameChangeDetails = 0;
        String systemIp = null;
        String loginId = null;
        int phaseConversion = 0;
        String medicalBoardVenueName = null;
        String sadaremId = null;
        int approvalRationCardNotOpen = 0;
        int approvalRationCardSerialNo = 0;
        String msgFlag = "0";
        String target = "issueRaised";
        ArrayList requestRaiseList = new ArrayList();
        try {
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if ((String) session.getAttribute("useridData") != null) {
                userId = (String) session.getAttribute("useridData");
            }
            systemIp = request.getRemoteAddr();
            if (session.getAttribute("loginid") != null) {
                loginId = (String) session.getAttribute("loginid");
            }

            issueRaiseApprovalForm.setResloveLoginId(loginId);
            requestId = issueRaiseApprovalForm.getRequestModeRaiseId();
            //To Approval Medical Board Change
            if (issueRaiseApprovalForm.getStatusCategoryId() != null
                    && !issueRaiseApprovalForm.getStatusCategoryId().equals("")
                    && issueRaiseApprovalForm.getStatusCategoryId().length() > 0
                    && issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.MEDICALBOARDCHANGE)) {
                sadaremId = issueRaiseApprovalForm.getResolvesadaremId();
                medicalBoardVenueName = issueRaiseApprovalService.approvalVenueDetails(ds, sadaremId, issueRaiseApprovalForm);
                approvalMedical = issueRaiseApprovalService.issueApprovalMedicalBoardDetails(ds, issueRaiseApprovalForm, medicalBoardVenueName);
                if (approvalMedical > 0) {
                    updateApprovalDetails = issueRaiseApprovalService.updateResolveDetails(ds, issueRaiseApprovalForm, requestId, "Approved", systemIp);
                }
            } //To Approval AddressPhaseIII Change
            //            else if (issueRaiseApprovalForm.getStatusCategoryId() != null
            //                    && !issueRaiseApprovalForm.getStatusCategoryId().equals("")
            //                    && issueRaiseApprovalForm.getStatusCategoryId().length() > 0
            //                    && issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.ADDRESSCHANGEPHASEIII)) {
            //                if (issueRaiseApprovalForm.getResolvesadaremId() != null && !issueRaiseApprovalForm.getResolvesadaremId().equals("") && issueRaiseApprovalForm.getResolvesadaremId().length() > 0) {
            //
            //                    addressPhaseIII = issueRaiseApprovalService.issueApprovalAddressPhaseIIIDetails(ds, issueRaiseApprovalForm, districtId);
            //                    if (addressPhaseIII > 0) {
            //                        updateApprovalDetails = issueRaiseApprovalService.updateResolveDetails(ds, issueRaiseApprovalForm, requestId, "Approved", systemIp);
            //                    }
            //                }
            //            } //To Approval NameChange,SurNameChange,RelationNameChange
            else if (issueRaiseApprovalForm.getStatusCategoryId() != null
                    && !issueRaiseApprovalForm.getStatusCategoryId().equals("")
                    && issueRaiseApprovalForm.getStatusCategoryId().length() > 0
                    && (issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.NAME)
                    || issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.RELATIONNAME)
                    || issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.SURNAME))) {

                nameChangeDetails = issueRaiseApprovalService.issueApprovalNameChangeDetails(ds, issueRaiseApprovalForm);
                if (nameChangeDetails > 0) {
                    updateApprovalDetails = issueRaiseApprovalService.updateResolveDetails(ds, issueRaiseApprovalForm, requestId, "Approved", systemIp);
                }
            } //To ApprovalPhaseConversions
            else if (issueRaiseApprovalForm.getStatusCategoryId() != null
                    && !issueRaiseApprovalForm.getStatusCategoryId().equals("")
                    && issueRaiseApprovalForm.getStatusCategoryId().length() > 0
                    && (issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI)
                    || issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII)
                    || issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV))) {
                phaseConversion = issueRaiseApprovalService.approvalPhaseConversion(ds, issueRaiseApprovalForm, districtId);
                if (phaseConversion != 0) {
                    updateApprovalDetails = issueRaiseApprovalService.updateResolveDetails(ds, issueRaiseApprovalForm, requestId, "Approved", systemIp);
                }
            } else if (issueRaiseApprovalForm.getStatusCategoryId() != null
                    && !issueRaiseApprovalForm.getStatusCategoryId().equals("")
                    && issueRaiseApprovalForm.getStatusCategoryId().length() > 0
                    && (issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.RATIONCARDNOTOPEN))) {

                if ((issueRaiseApprovalForm.getResolverationCard() != null
                        && !issueRaiseApprovalForm.getResolverationCard().equals("-")
                        && issueRaiseApprovalForm.getResolverationCard().length() > 0)
                        && issueRaiseApprovalForm.getResolveRationCardserialNo() != null
                        && !issueRaiseApprovalForm.getResolveRationCardserialNo().equals("-")
                        && issueRaiseApprovalForm.getResolveRationCardserialNo().length() > 0
                        && issueRaiseApprovalForm.getResolveMemberName() != null
                        && !issueRaiseApprovalForm.getResolveMemberName().equals("-")
                        && issueRaiseApprovalForm.getResolveMemberName().length() > 0) {
                    approvalRationCardNotOpen = issueRaiseApprovalService.approvalRationCardNotOpen(ds, issueRaiseApprovalForm, "");
                    if (approvalRationCardNotOpen != 0) {
                        updateApprovalDetails = issueRaiseApprovalService.updateResolveDetails(ds, issueRaiseApprovalForm, requestId, "Approved", systemIp);
                    }
                } else {
                    msgFlag = "1";
                    request.setAttribute("error", "<font color='red'>Details are not Provided Properly please reject the issue.</font>");
                    requestRaiseList = issueRaiseApprovalService.getRequestRaisedDetails(ds, issueRaiseApprovalForm.getRequestModeRaiseId(), issueRaiseApprovalForm.getStatusCategoryId());
                    if (requestRaiseList.size() > 0) {
                        request.setAttribute("requestRaiseList", requestRaiseList);
                    } else {
                        request.setAttribute("msg", "<font color ='red'>No Data Found!</font>");
                    }
                    request.setAttribute("statusCategoryId", issueRaiseApprovalForm.getStatusCategoryId());
                    target = "issueResolve";
                    issueRaiseApprovalForm.setRejectComment("");
                }

            } else if (issueRaiseApprovalForm.getStatusCategoryId() != null
                    && !issueRaiseApprovalForm.getStatusCategoryId().equals("")
                    && issueRaiseApprovalForm.getStatusCategoryId().length() > 0
                    && (issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE))) {
                approvalRationCardSerialNo = issueRaiseApprovalService.approvalRationCardSerialNo(ds, issueRaiseApprovalForm);
                if (approvalRationCardSerialNo != 0) {
                    updateApprovalDetails = issueRaiseApprovalService.updateResolveDetails(ds, issueRaiseApprovalForm, requestId, "Approved", systemIp);
                }
            } else if (issueRaiseApprovalForm.getStatusCategoryId() != null
                    && !issueRaiseApprovalForm.getStatusCategoryId().equals("")
                    && issueRaiseApprovalForm.getStatusCategoryId().length() > 0
                    && (issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.DATEOFISSUE))) {
                approvalRationCardSerialNo = issueRaiseApprovalService.approvalForDateOfIssue(ds, issueRaiseApprovalForm);
                if (approvalRationCardSerialNo != 0) {
                    updateApprovalDetails = issueRaiseApprovalService.updateResolveDetails(ds, issueRaiseApprovalForm, requestId, "Approved", systemIp);
                }
            } 
            else if(issueRaiseApprovalForm.getStatusCategoryId() != null
                    && !issueRaiseApprovalForm.getStatusCategoryId().equals("")
                    && issueRaiseApprovalForm.getStatusCategoryId().length() > 0
                    && (issueRaiseApprovalForm.getStatusCategoryId().equals(CommonConstants.ADDRESSCHANGEPHASEIII)))
            {
            	System.out.println("code"+issueRaiseApprovalForm.getHabitation_id());
            IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
              issueRaiseApprovalDAO.issueNewAddressPhaseIIIResolve(ds, issueRaiseApprovalForm.getSadaremId(), issueRaiseApprovalForm.getHabitation_id(),issueRaiseApprovalForm.getNewhouseno(),issueRaiseApprovalForm.getNewpincode());
              updateApprovalDetails = issueRaiseApprovalService.updateResolveDetails(ds, issueRaiseApprovalForm, requestId, "Approved", systemIp);
            }
            
            else { //approvalForDateOfIssue
                updateApprovalDetails = issueRaiseApprovalService.updateResolveDetails(ds, issueRaiseApprovalForm, requestId, "Approved", systemIp);

            } 
            if (updateApprovalDetails != 0) {
                request.setAttribute("msg1", "Issue Approved SuccessFully!");
                issueRaiseApprovalDTO = issueRaiseApprovalService.getMailForEmailIssueResloveBody(ds, requestId, userId);
                districtId = (String) session.getAttribute("districtId");
                toEmailIdList = issueRaiseApprovalService.ToIssueResloveList(ds, requestId);
                //Mail ToList For IssueReslove approvalRationCardNotOpen
                if (toEmailIdList != null && toEmailIdList.size() > 0) {
                    for (int i = 0; i
                            < toEmailIdList.size(); i++) {
                        String email1 = toEmailIdList.get(i).toString();
                        toemailId = new InternetAddress(email1, "");
                        RecMail.add(toemailId);
                    }
                }
                ccEmailIdList = issueRaiseApprovalService.CCIssueResloveList(ds, requestId);
                //Mail CCList For IssueReslove
                if (ccEmailIdList != null && ccEmailIdList.size() > 0) {
                    for (int i = 0; i
                            < ccEmailIdList.size(); i++) {
                        String email1 = ccEmailIdList.get(i).toString();
                        ccemailId = new InternetAddress(email1, "");
                        CCMail.add(ccemailId);
                    }
                }
                issueEmailBody = emailUtility.issueResloveBody(issueRaiseApprovalForm);
                if (RecMail != null) {
                    emailStatus = EmailUtility.SendEmail(RecMail, CCMail, BCCMail, issueRaiseApprovalDTO.getResolverequestId(), issueEmailBody, "sadarem.eservices@gmail.com");
                }

            } else {
                if (msgFlag.equals("0")) {
                    request.setAttribute("msg1", "Error Occured While Resolving!");
                }

            }
            request.setAttribute("closewindow", "closewindow");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward issueRejectDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;


        int rejectDetails = 0;
        String requestId = null;
        ArrayList toEmailIdList = new ArrayList();
        InternetAddress toemailId = null;
        InternetAddress ccemailId = null;


        boolean emailStatus = false;
        ArrayList<InternetAddress> RecMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> CCMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> BCCMail = new ArrayList<InternetAddress>();
        ArrayList ccEmailIdList = new ArrayList();
        EmailUtility emailUtility = new EmailUtility();
        String issueEmailBody = null;
        HttpSession session = request.getSession();
        String userId = null;
        String loginId = null;
        String systemIp = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("loginid") != null) {
                loginId = (String) session.getAttribute("loginid");
            }
            issueRaiseApprovalForm.setResloveLoginId(loginId);
            systemIp = request.getRemoteAddr();

            if ((String) session.getAttribute("useridData") != null) {
                userId = (String) session.getAttribute("useridData");
            }
            requestId = issueRaiseApprovalForm.getRequestModeRaiseId();
            IssueRaiseApprovalDTO issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();

            rejectDetails = issueRaiseApprovalService.updateResolveDetails(ds, issueRaiseApprovalForm, requestId, "Rejected", systemIp);


            if (rejectDetails > 0) {

                toEmailIdList = issueRaiseApprovalService.ToIssueResloveList(ds, requestId);
                //Mail ToList For IssueReslove


                if (toEmailIdList != null && toEmailIdList.size() > 0) {
                    for (int i = 0; i
                            < toEmailIdList.size(); i++) {
                        String email1 = toEmailIdList.get(i).toString();
                        toemailId = new InternetAddress(email1, "");
                        RecMail.add(toemailId);
                    }
                }
                ccEmailIdList = issueRaiseApprovalService.CCIssueResloveList(ds, requestId);
                //Mail CCList For IssueReslove
                if (ccEmailIdList != null && ccEmailIdList.size() > 0) {
                    for (int i = 0; i
                            < ccEmailIdList.size(); i++) {
                        String email1 = ccEmailIdList.get(i).toString();
                        ccemailId = new InternetAddress(email1, "");
                        CCMail.add(ccemailId);
                    }
                }

                issueEmailBody = emailUtility.issueRejectedBody(issueRaiseApprovalForm);
                issueRaiseApprovalDTO = issueRaiseApprovalService.getMailForEmailIssueResloveBody(ds, requestId, userId);


                if (RecMail != null) {
                    emailStatus = EmailUtility.SendEmail(RecMail, CCMail, BCCMail, issueRaiseApprovalDTO.getResolverequestId(), issueEmailBody, "sadarem.eservices@gmail.com");
                }
                request.setAttribute("msg1", "Issue Rejected Successfully!");
            } else {
                request.setAttribute("msg1", "Error Occured While Rejecting!");
            }
            request.setAttribute("closewindow", "closewindow");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("issueRaised");
    }

    public ActionForward issueHoldDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        int issueHoldDetails = 0;
        String requestId = null;
        String loginId = null;
        String systemIp = null;
        try {
            HttpSession session = request.getSession();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("loginid") != null) {
                loginId = (String) session.getAttribute("loginid");
            }
            issueRaiseApprovalForm.setResloveLoginId(loginId);
            systemIp = request.getRemoteAddr();
            requestId = issueRaiseApprovalForm.getRequestModeRaiseId();
            issueHoldDetails = issueRaiseApprovalService.updateResolveDetails(ds, issueRaiseApprovalForm, requestId, "Hold", systemIp);
            if (issueHoldDetails > 0) {
                request.setAttribute("msg1", "Issue Updated as Hold Successfully!");
            } else {
                request.setAttribute("msg1", "Error Occured While Hold!");
            }
            request.setAttribute("closewindow", "closewindow");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("issueRaised");
    }

    /**
     * To Populate Pension Id and their details.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getViceversaPopulateDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        ArrayList visceversaList = new ArrayList();
        String flag = null;
        int checkCategoryId = 1;
        int alreadyRaised = 0;
        int validSadaremId = 0;
        ArrayList validMsgList = new ArrayList();
        String validMsg = null;
        boolean districtLevelAccessFlag = false;
        CommonDetails commonDetails = new CommonDetails();
        int sadaremid = 0;
        String districtId = null;
        StringBuffer validationMsg = new StringBuffer();
        int personid = 0;
        String loginId = null;
        ArrayList pensionIdDetails = new ArrayList();
        String msg = null;
        boolean sadaremFlag = false;
        String sadaremIdValue = null;
        int vicversaCount = 0;
        String sadaremId = "";
        String msgFlag = "0";

        try {
            HttpSession session = request.getSession();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("flag") != null) {
                flag = (String) request.getParameter("flag");
            }
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            if (session.getAttribute("useridData") != null) {
                loginId = (String) session.getAttribute("useridData");
            }
            request.setAttribute("flag", flag);
            issueRaiseApprovalForm.setVicversaFlag(flag);
            //valid SADAREM ID or not.

            if (issueRaiseApprovalForm.getCategoryFormId() != null
                    && !issueRaiseApprovalForm.getCategoryFormId().equalsIgnoreCase("")
                    && issueRaiseApprovalForm.getCategoryFormId().length() > 0) {

                if ((issueRaiseApprovalForm.getVicversaFlag().equals("1"))
                        || (issueRaiseApprovalForm.getRadioPensionId().equals("1"))) {
                    vicversaCount = issueRaiseApprovalService.vicversaNewSADAREMID(ds, issueRaiseApprovalForm);
                    if (vicversaCount == 0) {
                        checkCategoryId = issueRaiseApprovalService.checkCategoryId(ds, issueRaiseApprovalForm);
                    } else {

                        validMsgList = issueRaiseApprovalService.getValidMsg(ds, issueRaiseApprovalForm);
                        if (validMsgList.size() > 0) {
                            if (issueRaiseApprovalForm.getCategoryFormId() != null
                                    && (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.VICEVERSA)
                                    && issueRaiseApprovalForm.getVicversaFlag().equals("1"))) {
                                sadaremId = issueRaiseApprovalForm.getSadaremId();
                            } else {
                                sadaremId = issueRaiseApprovalForm.getPopulateviceversaSadaremId();
                            }

                        }
                    }
                } else {

                    checkCategoryId = issueRaiseApprovalService.checkCategoryId(ds, issueRaiseApprovalForm);
                }

                if (sadaremid == 0 && (issueRaiseApprovalForm.getSadaremId() != null
                        && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("")
                        && issueRaiseApprovalForm.getSadaremId().length() > 0
                        || issueRaiseApprovalForm.getPopulateviceversaSadaremId() != null
                        && !issueRaiseApprovalForm.getPopulateviceversaSadaremId().equalsIgnoreCase("")
                        && issueRaiseApprovalForm.getPopulateviceversaSadaremId().length() > 0)) {
                    validSadaremId = issueRaiseApprovalService.validSadaremId(ds, issueRaiseApprovalForm);
                }

                if ((issueRaiseApprovalForm.getSadaremId() != null
                        && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("")
                        && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null"))) {
                    sadaremIdValue = issueRaiseApprovalForm.getSadaremId();
                    sadaremFlag = true;

                }
                if ((issueRaiseApprovalForm.getPopulateviceversaSadaremId() != null
                        && !issueRaiseApprovalForm.getPopulateviceversaSadaremId().equalsIgnoreCase("")
                        && !issueRaiseApprovalForm.getPopulateviceversaSadaremId().equalsIgnoreCase("null"))) {
                    sadaremIdValue = issueRaiseApprovalForm.getPopulateviceversaSadaremId();
                    sadaremFlag = true;
                }
                if (sadaremFlag == true && sadaremIdValue != null) {
                    districtLevelAccessFlag = commonDetails.checkDistrictFlag(sadaremIdValue, districtId);
                    if (districtLevelAccessFlag == false) {
                        sadaremid = 1;
                        request.setAttribute("sadaremIdflag", issueRaiseApprovalForm.getRadioPensionId());
                        request.setAttribute("error", "Please Enter Valid SADAREM ID");
                        issueRaiseApprovalForm.setMobile("");
                        issueRaiseApprovalForm.setDecription("");
                        issueRaiseApprovalForm.setPopulateviceversaSadaremId("");

                        //issueRaiseApprovalForm.setPensionId("");
                        String sadaremIDFlag = issueRaiseApprovalForm.getSadaremId();
                        if (issueRaiseApprovalForm.getVicversaFlag().equals("1")) {
                            issueRaiseApprovalForm.setSadaremId("");
                            issueRaiseApprovalForm.setNewName("");
                            issueRaiseApprovalForm.setNewRelationName("");
                        }
                        // issueRaiseApprovalForm.setRadioPensionId("");
                        if (issueRaiseApprovalForm.getRadioPensionId() != null && issueRaiseApprovalForm.getRadioPensionId().equals("1")) {
                            issueRaiseApprovalForm.setPopulateviceversaSadaremId("");
                            issueRaiseApprovalForm.setSadaremId(sadaremIDFlag);
                        }


                    }
                }

                if (sadaremid == 0 && (issueRaiseApprovalForm.getSadaremId() != null
                        && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("")
                        && issueRaiseApprovalForm.getSadaremId().length() > 0
                        || issueRaiseApprovalForm.getPopulateviceversaSadaremId() != null
                        && !issueRaiseApprovalForm.getPopulateviceversaSadaremId().equalsIgnoreCase("")
                        && issueRaiseApprovalForm.getPopulateviceversaSadaremId().length() > 0)) {
                    validSadaremId = issueRaiseApprovalService.validSadaremId(ds, issueRaiseApprovalForm);

                    if (validSadaremId == 0) {
                        msgFlag = "1";
                        request.setAttribute("error", "Entered SADAREM ID was Dead!");
                        if (flag != null && flag.equalsIgnoreCase("3")) {
                            issueRaiseApprovalForm.setPopulateviceversaSadaremId("");
                        } else if (flag != null && flag.equalsIgnoreCase("1")) {
                            issueRaiseApprovalForm.setSadaremId("");
                        } else {
                            // issueRaiseApprovalForm.setSadaremId("");
                            issueRaiseApprovalForm.setNewName("");
                            issueRaiseApprovalForm.setNewRelationName("");
                            //issueRaiseApprovalForm.setPensionId(""); ==
                        }
                    }
                }
                if (checkCategoryId > 0) {
                    validMsgList = issueRaiseApprovalService.getValidMsg(ds, issueRaiseApprovalForm);
                    if (validMsgList.size() > 0) {

                        validMsg = "For this " + validMsgList.get(4).toString() + " for " + validMsgList.get(0).toString() + " is"
                                + " already been raised with requestId:" + validMsgList.get(1).toString() + " on " + validMsgList.get(2).toString() + " and Status is " + validMsgList.get(3).toString() + "";
                        request.setAttribute("error", validMsg);
                        String cat = issueRaiseApprovalForm.getCategoryFormId();
                        String radioPensionId = issueRaiseApprovalForm.getRadioPensionId();
                        issueRaiseApprovalForm.reset();
                        issueRaiseApprovalForm.setCategoryFormId(cat);
                        issueRaiseApprovalForm.setRadioPensionId(radioPensionId);
                    }
                } else {
                    if (issueRaiseApprovalForm.getNewPensionId() != null && !issueRaiseApprovalForm.getNewPensionId().equals("") && issueRaiseApprovalForm.getNewPensionId().length() > 0) {
                        pensionIdDetails = issueRaiseApprovalService.getPensionIdDetails(ds, loginId, issueRaiseApprovalForm.getNewPensionId(), issueRaiseApprovalForm.getCategoryFormId());
                        if (pensionIdDetails.isEmpty()) {
                            request.setAttribute("error", "Please Enter Valid Pension ID");
                            if (issueRaiseApprovalForm.getRadioPensionId().equals("2")) {

                                String penId = issueRaiseApprovalForm.getPensionId();
                                issueRaiseApprovalForm.setPensionId(penId);
                                issueRaiseApprovalForm.setNewPensionId("");
                                issueRaiseApprovalForm.setNewSadaremId("");


                            }

                        }

                        if (pensionIdDetails != null && pensionIdDetails.size() > 0) {

                            personid = 1;
                            if (pensionIdDetails.get(1) != null && !pensionIdDetails.get(1).toString().equalsIgnoreCase("")
                                    && pensionIdDetails.get(1).toString().equalsIgnoreCase("Dead")) {
                                msgFlag = "1";
                                request.setAttribute("error", "Pension ID is in Dead");
                            } else if (pensionIdDetails.get(1) != null && !pensionIdDetails.get(1).toString().equalsIgnoreCase("")
                                    && pensionIdDetails.get(1).toString().equalsIgnoreCase("Suspended")) {
                                msgFlag = "1";

                                request.setAttribute("error", "Pension ID is in Suspended");
                            }
                            if (msgFlag.equals("1")) {
                                issueRaiseApprovalForm.setNewPensionId("");
                                issueRaiseApprovalForm.setNewSadaremId("");
                            }
                        }
                    }


                    if (validSadaremId > 0 || personid == 1) {
                        if (issueRaiseApprovalForm.getSadaremId() != null
                                && !issueRaiseApprovalForm.getSadaremId().equals("")
                                && issueRaiseApprovalForm.getSadaremId().length() > 0) {
                            districtLevelAccessFlag = commonDetails.checkDistrictFlag(sadaremIdValue, districtId);
                            if (districtLevelAccessFlag == false) {
                                sadaremid = 1;
                                request.setAttribute("sadaremIdflag", issueRaiseApprovalForm.getRadioPensionId());
                                request.setAttribute("error", "Please Enter Valid SADAREM ID");
                                issueRaiseApprovalForm.setMobile("");
                                issueRaiseApprovalForm.setDecription("");
                                String sadaremIDFlag = issueRaiseApprovalForm.getSadaremId();
                                if (issueRaiseApprovalForm.getVicversaFlag().equals("1")) {
                                    issueRaiseApprovalForm.setSadaremId("");
                                }
                            }
                        }
                        visceversaList = issueRaiseApprovalService.getVicerversaPoulateDetails(ds, issueRaiseApprovalForm, flag);

                        if (visceversaList.size() > 0) {
                            if (flag.equals("1")) {
                                issueRaiseApprovalForm.setPensionId(visceversaList.get(0).toString());
                                issueRaiseApprovalForm.setNewName(visceversaList.get(1).toString());
                                issueRaiseApprovalForm.setNewRelationName(visceversaList.get(2).toString());
                            } else if (flag.equals("3")) {
                                issueRaiseApprovalForm.setPopulateviceversaPensionId(visceversaList.get(0).toString());
                            } else {
                                if (personid == 1 && flag.equals("2")) {
                                    issueRaiseApprovalForm.setNewSadaremId(visceversaList.get(0).toString());

                                }
                            }
                        }
                    } else {
                        if (msgFlag.equals("0")) {
                            request.setAttribute("error", "Please Enter Valid Pension ID!");
                            issueRaiseApprovalForm.setNewPensionId("");
                        }


                    }

                }

            }
            if (issueRaiseApprovalForm.getRadioPensionId() != null && !issueRaiseApprovalForm.getRadioPensionId().equals("")) {
                request.setAttribute("sadaremIdflag", issueRaiseApprovalForm.getRadioPensionId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.unspecified(mapping, form, request, response);
        return mapping.findForward("viceVersa");
    }

    /**
     * To Populate RationCardDetails
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getRationCardDetailsForNotOpen(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        ArrayList rationCardNotOpenList = new ArrayList();
        String rationCard = null;
        String flag = null;
        String target = null;
        try {
            HttpSession session = request.getSession();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("flag") != null) {
                issueRaiseApprovalForm.setVicversaFlag(request.getParameter("flag"));
            }
            rationCard = request.getParameter("rationCard");
            rationCardNotOpenList = issueRaiseApprovalService.getRationCardDetailsForNotOpen(ds, issueRaiseApprovalForm, rationCard);
            if (rationCardNotOpenList.size() > 0) {
                request.setAttribute("rationCardNotOpenList", rationCardNotOpenList);
            } else {
                request.setAttribute("msg", "<font color='red'>No Data Found!</font>");
            }

            if (request.getParameter("flag") != null && request.getParameter("flag").equals("1")) {
                target = "rationCardNotOpenList";

            } else {
                target = "rationCardSerilaNoList";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    /**
     * To populate District Name based Upon RationCardNo Fro mCivivlSupplies
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward rationCardAddressChangebasedUponCivilSupplies(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        ArrayList catgoryList = new ArrayList();
        ArrayList rationCardWS1List = new ArrayList();
        ArrayList rationCardWS2List = new ArrayList();
        URL url1 = new URL(CommonConstants.Url1);
        //AponlineWSClient wsClient = new AponlineWSClient();
        String expection = null;
        String districtId = null;
        String districtName = null;
        String rationCardNo = null;
        String rationCardAddessChange = null;
        String flag = "0";
        HttpSession session = request.getSession();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            catgoryList = issueRaiseApprovalService.getCatgoryDetails(ds);
            issueRaiseApprovalForm.setCategoryList(catgoryList);
            rationCardAddessChange = issueRaiseApprovalForm.getCategoryFormId();

            if (issueRaiseApprovalForm.getRationCardNumber() != null && !issueRaiseApprovalForm.getRationCardNumber().equals("")) {
                AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();
                rationCardWS1List = aadharCardMappingDAO.getCivilSuppliesData(ds, issueRaiseApprovalForm.getRationCardNumber(), districtId);
                if (rationCardWS1List.size() == 0) {
                    PersonCodeCheckDAO dao = new PersonCodeCheckDAO();
                    List<String> dists = dao.getIssueRaisingRationCard(ds, issueRaiseApprovalForm.getRationCardNumber());
                    String oldDist = "";
                    if (dists.size() > 0 && !dists.get(0).equals("")) {
                        oldDist = dists.get(0);
                        if (oldDist != null && oldDist.length() > 0) {
                            rationCardWS1List = dao.getDataFromCivilDatabaseWithOld(ds, issueRaiseApprovalForm.getRationCardNumber(), oldDist);
                        }

                        // Collections.sort(getRationData, myComparator);
                    }
                }

                if (rationCardWS1List != null && rationCardWS1List.size() > 0) {
                    Iterator it = rationCardWS1List.listIterator();
                    while (it.hasNext()) {
                        HashMap listData = (HashMap) it.next();
                        if (listData.get("district") != null) {
                            districtId = listData.get("district").toString();
                        }

                        }
                        if (issueRaiseApprovalForm.getRationCardNumber() != null
                                && !issueRaiseApprovalForm.getRationCardNumber().equals("")
                                && issueRaiseApprovalForm.getRationCardNumber().length() > 0) {
                            rationCardNo = issueRaiseApprovalForm.getRationCardNumber().substring(3, 5);
                            issueRaiseApprovalForm.setDistrict_id(districtId);
                            request.setAttribute("districtId", districtId);
                            districtName = issueRaiseApprovalService.getDistrictName(ds, issueRaiseApprovalForm);
                            if (districtId != null && !districtId.equals("") && rationCardNo.equalsIgnoreCase(districtId)) {
                                request.setAttribute("error", "As per Civil Supplies, this Rationcard No district is " + districtName + " only. District is not changed.");
                                issueRaiseApprovalForm.setRationCardNumber("");
                            } else {
                                issueRaiseApprovalForm.setDistrict_name(districtName);
                                request.setAttribute("districtName", "districtName");
                            }
                        }

                    } else {
                        request.setAttribute("error", "RationCard No " + issueRaiseApprovalForm.getRationCardNumber() + " is not Found in Civil Supply DataBase.");
                        issueRaiseApprovalForm.setRationCardNumber("");
                    }

            } else {
                issueRaiseApprovalForm.setRationCardNumber("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    /**
     * This method to populate pensionCardNotOpen List
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getPensionCardNotOpeningList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        ArrayList pensionIdNotOpenList = new ArrayList();
        String pensionId = null;
        String districtId = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("pensionId") != null) {
                pensionId = (String) request.getParameter("pensionId");
            }
            if (request.getParameter("districtId") != null) {
                districtId = (String) request.getParameter("districtId");
            }
            pensionIdNotOpenList = issueRaiseApprovalService.getPensionCardNotOpeningList(ds, pensionId, districtId);

            if (pensionIdNotOpenList.size() > 0) {
                request.setAttribute("pensionIdNotOpenList", pensionIdNotOpenList);
            } else {
                request.setAttribute("msg", "<font color ='red'>Pension Details Not Available!</font>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("pensionIdNotOpenList");
    }

    public ActionForward getMedicalBoardUserList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        ArrayList medicalUserNameList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            medicalUserNameList = issueRaiseApprovalService.getMedicalBoardUserList(ds, issueRaiseApprovalForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getMedicalAddressDetails(mapping, form, request, response);
        issueRaiseApprovalForm.setUserNameList(medicalUserNameList);
        request.setAttribute("medicalBoardFlag", "medicalBoardFlag");
        return mapping.findForward("medicalBoardChange");
    }

    public ActionForward getDateOfIssueSADAREMIDDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        String dateOfIssue = null;
        int validSadaremId = 0;
        boolean districtLevelAccessFlag = false;
        CommonDetails commonDetails = new CommonDetails();
        int sadaremid = 0;
        String districtId = null;
        HttpSession session = request.getSession();
        ArrayList catgoryList = new ArrayList();
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = null;
        int validFlag = 0;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            catgoryList = issueRaiseApprovalService.getCatgoryDetails(ds);
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");

            }

            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                districtLevelAccessFlag = commonDetails.checkDistrictFlag(issueRaiseApprovalForm.getSadaremId(), districtId);
                if (districtLevelAccessFlag == false) {
                    sadaremid = 1;
                    request.setAttribute("error", "Please Enter Valid SADAREMID!");
                    issueRaiseApprovalForm.setSadaremId("");
                }

                if (sadaremid == 0) {
                    issueRaiseApprovalDTO = issueRaiseApprovalService.getDateOfIssue(ds, issueRaiseApprovalForm);


                    if (issueRaiseApprovalDTO.getPersonStatus() != null
                            && !issueRaiseApprovalDTO.getPersonStatus().equals("")) {

                        if (issueRaiseApprovalDTO.getPersonStatus().equals("0")) {
                            request.setAttribute("error", "Date Of Issue Details are not Available!");
                            // issueRaiseApprovalForm.setSadaremId("");
                            validFlag = 1;
                        } else {
                            request.setAttribute("error", "Entered SADAREM ID  was " + issueRaiseApprovalDTO.getPersonStatus() + "!");
                            // issueRaiseApprovalForm.setSadaremId("");
                            validFlag = 1;
                        }
                        //if (validFlag == 1) {
                        // issueRaiseApprovalForm.setSadaremId("");
                        //}

                    }
                    if (issueRaiseApprovalDTO.getPersonStatus() != null
                            && issueRaiseApprovalDTO.getPersonStatus().equals("1")) {
                        issueRaiseApprovalDTO = issueRaiseApprovalService.validDateOfIssue(ds, issueRaiseApprovalForm);
                        request.setAttribute("fromDate", issueRaiseApprovalDTO.getFromDate());
                        request.setAttribute("toDate", issueRaiseApprovalDTO.getToDate());
                        request.setAttribute("dateOfIssueMsg", "Request Details are  to be selected in between  thes Dates: CampStartDate : " + issueRaiseApprovalDTO.getFromDate() + " and CampEndDate " + issueRaiseApprovalDTO.getToDate() + "!");

                    }
                }
            }

            issueRaiseApprovalForm.setCategoryList(catgoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("dateOfIssue");
    }

    public ActionForward relationTypeChange(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        DataSource ds = null;
        String relationType = null;
        HttpSession session = request.getSession();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                relationType = issueRaiseApprovalService.getRelationTypeChange(ds, issueRaiseApprovalForm);
                issueRaiseApprovalForm.setRelationType(relationType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (relationType == null || relationType.equals(null)) {
                request.setAttribute("errorMsg", "Entered SADAREM ID was Dead!");
                issueRaiseApprovalForm.setSadaremId("");
            }
            request.setAttribute("relationtypechange", "relationtypechange");
        }
        return mapping.findForward("subDisabilityType");
    }

    public ActionForward getsubDisabilityList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        ArrayList typeofdisability = new ArrayList();
        DataSource ds = null;
        String catid = issueRaiseApprovalForm.getCategoryFormId();
        HttpSession session = request.getSession();
        CommonDetails commonDetails = new CommonDetails();
        boolean districtLevelAccessFlag = false;
        int sadaremid = 0;
        String districtId = null;
        ArrayList subTypeDis = new ArrayList();
        ArrayList activeSadaremId = new ArrayList();
        int succ = 1;
        String diag = null;
        ArrayList subsubdisabilityId = new ArrayList();
        int checkCategoryId = 1;
        String validMsg = "";
        ArrayList validMsgList = new ArrayList();
        int alreadyRaised = 0;
        String cat = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                districtLevelAccessFlag = commonDetails.checkDistrictFlag(issueRaiseApprovalForm.getSadaremId(), districtId);
                if (!districtLevelAccessFlag) {
                    sadaremid = 1;
                } else {
                    checkCategoryId = issueRaiseApprovalService.checkCategoryId(ds, issueRaiseApprovalForm);
                    if (checkCategoryId > 0) {
                        alreadyRaised = 1;
                    } else {
                        activeSadaremId = issueRaiseApprovalService.activeSadaremId(ds, issueRaiseApprovalForm.getSadaremId());
                        if (activeSadaremId != null && activeSadaremId.size() > 0) {
                            if (activeSadaremId.get(0).toString().equalsIgnoreCase("Active")) {
                                if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONTYPECHANGE)) {
                                    relationTypeChange(mapping, form, request, response);
                                }
                                if (catid.equals(CommonConstants.SUBTYPEDISABILITYTYPE)) {
                                    typeofdisability = issueRaiseApprovalService.getTypeofDisablity(ds, issueRaiseApprovalForm);
                                    subTypeDis = issueRaiseApprovalService.getSubDisabilityList(ds, issueRaiseApprovalForm);
                                }
                                if (catid.equals(CommonConstants.SUB_SUB_TYPEDISABILITYTYPE)) {
                                    typeofdisability = issueRaiseApprovalService.getTypeofDisablity(ds, issueRaiseApprovalForm);
                                    subsubdisabilityId = issueRaiseApprovalService.getSub_Sub_DisabilityList(ds, issueRaiseApprovalForm);
                                    if (typeofdisability != null && typeofdisability.size() > 0) {
                                        if (!typeofdisability.get(0).equals("1")) {
                                            request.setAttribute("error", "Sub Sub type disability applies only for LOCOMOTOR Disability");
                                            issueRaiseApprovalForm.reset();
                                            issueRaiseApprovalForm.setCategoryFormId(cat);
                                        }
                                    }
                                }
                                if (catid.equals(CommonConstants.DIAGNOSUBDISABILITYTYPE)) {
                                    diag = issueRaiseApprovalService.getDiagnosisofDisability(ds, issueRaiseApprovalForm);
                                }
                            } else if (activeSadaremId.get(0).toString().equalsIgnoreCase("Inactive")) {
                                String msg = null;
                                msg = "Entered SADAREM ID was " + activeSadaremId.get(1);
                                request.setAttribute("error", msg);
                            }
                        } else if (activeSadaremId.size() == 0) {
                            request.setAttribute("error", "Please Enter Valid SADAREM ID");
                            issueRaiseApprovalForm.reset();
                        }
                    }
                    if (alreadyRaised == 1) {
                        validMsgList = issueRaiseApprovalService.getValidMsg(ds, issueRaiseApprovalForm);
                        if (validMsgList.size() > 0) {
                            validMsg = "For this " + validMsgList.get(4).toString() + " for " + validMsgList.get(0).toString() + " is"
                                    + " already been raised with requestId:" + validMsgList.get(1).toString() + " on " + validMsgList.get(2).toString() + " and Status is " + validMsgList.get(3).toString() + "";
                            request.setAttribute("error", validMsg);
                            cat = issueRaiseApprovalForm.getCategoryFormId();
                            issueRaiseApprovalForm.reset();
                            issueRaiseApprovalForm.setCategoryFormId(cat);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sadaremid == 1) {
                succ = 0;
                request.setAttribute("error", "Please Enter Valid SADAREM ID");
                issueRaiseApprovalForm.reset();
            }
            this.unspecified(mapping, form, request, response);
            issueRaiseApprovalForm.setCategoryFormId(catid);
            if (catid.equals(CommonConstants.RELATIONTYPECHANGE)) {
                request.setAttribute("relationtypechange", "relationtypechange");
            }
            if (catid.equals(CommonConstants.SUBTYPEDISABILITYTYPE)) {
                if (typeofdisability != null && typeofdisability.size() > 0) {
                    issueRaiseApprovalForm.setDisabilityType((String) typeofdisability.get(1));
                }
                if (subTypeDis != null && subTypeDis.size() > 0) {
                    issueRaiseApprovalForm.setSubdisabilityList(subTypeDis);
                    request.setAttribute("subTypeDis", subTypeDis);
                }
            }
            if (catid.equals(CommonConstants.SUB_SUB_TYPEDISABILITYTYPE)) {
                if (typeofdisability != null && typeofdisability.size() > 0) {
                    issueRaiseApprovalForm.setDisabilityType((String) typeofdisability.get(1));
                }
                if (subsubdisabilityId != null && subsubdisabilityId.size() > 0) {
                    issueRaiseApprovalForm.setSubsubdisabilityList(subsubdisabilityId);
                    request.setAttribute("subsubdisabilityId", subsubdisabilityId);
                }
            }
            if (catid.equals(CommonConstants.DIAGNOSUBDISABILITYTYPE)) {
                request.setAttribute("diag", diag);
            }

        }

        return mapping.findForward("subDisabilityType");
    }

    public ActionForward getDiagnosisofList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        IssueRaiseApprovalService issueRaiseApprovalService =
                IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();

        ArrayList relationTypeList = new ArrayList();
        DataSource ds = null;
        String relationType = null;
        HttpSession session = request.getSession();
        CommonDetails commonDetails = new CommonDetails();
        boolean districtLevelAccessFlag = false;
        int sadaremid = 0;
        String districtId = null;
        int succ = 1;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }

            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                districtLevelAccessFlag = commonDetails.checkDistrictFlag(issueRaiseApprovalForm.getSadaremId(), districtId);

                if (districtLevelAccessFlag == false) {
                    sadaremid = 1;
                } else {
                    relationType = issueRaiseApprovalService.getRelationTypeChange(ds, issueRaiseApprovalForm);
                    if (relationTypeList.size() > 0) {
                        issueRaiseApprovalForm.setRelationType(relationTypeList.get(0).toString());
                        issueRaiseApprovalForm.setPopulaterelationType(relationTypeList.get(1).toString());
                    } else {
                        request.setAttribute("error", "Entered SADAREM ID was Dead!");
                        issueRaiseApprovalForm.setSadaremId("");
                    }
                }
            }
            if (sadaremid == 1) {
                succ = 0;
                String s = "Please Enter Valid SADAREM ID";
                request.setAttribute("error", s);
                issueRaiseApprovalForm.reset();
            }
            this.unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("diagonsisofDisability");
    }

    public ActionForward insertIssuesWithCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String target = "subDisabilityType";
        DataSource ds = null;
        String smessage = null;
        String systemIp = null;
        String districtId = null;
        String loginId = null;
        HttpSession session = null;
        IssueRaiseApprovalForm issueRaiseApprovalForm = (IssueRaiseApprovalForm) form;
        int result = 0;
        int validSadaremId = 0;
        String reqId = null;
        Boolean dCheck = false;
        CommonDetails commonDetails = new CommonDetails();
        StringBuffer validationMsg = null;
        IssueRaiseApprovalService service = IssueRaiseApprovalServiceFactory.getIssueRaiseApprovalServiceImpl();
        try {
            session = request.getSession();
            if (session.getAttribute("districtId") != null) {
                districtId = (String) session.getAttribute("districtId");
            }
            if (session.getAttribute("useridData") != null) {
                loginId = (String) session.getAttribute("useridData");
            }
            systemIp = request.getRemoteAddr();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("null")) {
                dCheck = commonDetails.checkDistrictFlag(issueRaiseApprovalForm.getSadaremId(), districtId);

            }
            if (issueRaiseApprovalForm.getSadaremId() != null && !issueRaiseApprovalForm.getSadaremId().equalsIgnoreCase("") && issueRaiseApprovalForm.getSadaremId().length() > 0) {
                validSadaremId = service.validSadaremId(ds, issueRaiseApprovalForm);
            }
            if (validSadaremId != 0) {
                if (dCheck) {
                    int id = Integer.parseInt(issueRaiseApprovalForm.getCategoryFormId());

                    result = service.insertIssueRasingDetails(ds, issueRaiseApprovalForm, null, systemIp, loginId, districtId, null);
                    reqId = service.getrequestMaxId(ds);
                    if (result != 0) {
                        smessage = "Issue Raised SuccessFully! generated IssueID :" + reqId;
                    }
                } else {
                    validationMsg = new StringBuffer();
                    if (!dCheck) {
                        issueRaiseApprovalForm.setSadaremId("");
                        validationMsg.append("Please Enter Valid SADAREM ID");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result == 0) {
                request.setAttribute("error", "Error in Raising Issue");
            } else {
                issueRaiseApprovalForm.reset(mapping, request);
                request.setAttribute("msg", smessage);
            }
            if (validSadaremId == 0) {
                request.setAttribute("error", "Please Enter Valid SADAREM ID");
            }
            if (!dCheck) {
                request.setAttribute("error", validationMsg);
            }

            if (issueRaiseApprovalForm.getCategoryFormId().equals(CommonConstants.RELATIONTYPECHANGE)) {
                request.setAttribute("relationtypechange", "relationtypechange");
            }
            unspecified(mapping, form, request, response);
        }
        return mapping.findForward(target);
    }
}
//    End


