/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.AadharCardMappingDAO;
import org.bf.disability.dao.PersonCodeCheckDAO;
import org.bf.disability.dao.ReportDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.AadharCardMappingDTO;
import org.bf.disability.form.AadharCardMappingForm;
import org.bf.disability.service.AadharCardMappingService;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.AadharCardMappingServiceFactory;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;

import Aadhar.AadhaarUtility;
import Aadhar.AadharDetails;

import com.ecentric.servicemodels.AadhaarProfile;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 693461
 */
public class AadharCardMappingAction extends DispatchAction {

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
        String flag = null;
        if (request.getParameter("flag") != null && !request.getParameter("flag").equalsIgnoreCase("")
                && !request.getParameter("flag").equalsIgnoreCase("null")) {
            flag = request.getParameter("flag").toString();
        }
        if (flag != null) {
            request.setAttribute("flag", flag);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward aadharHabitation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        HttpSession session = request.getSession();
        String districtid = null;
        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        AadharCardMappingForm aadharCardMappingForm = (AadharCardMappingForm) form; 
        String roleId = CommonUtility.checkNullObj(session.getAttribute("roleId"));
        String mandalId = CommonUtility.checkNullObj(session.getAttribute("mandalId"));
        TerritoryDAO territoryDAO = new TerritoryDAO();
         CommonDAO comObj  = new CommonDAOImpl();
            
        try {
        	   Integer mandalRoleId = CommonConstants.MANDALLOGINROLEID;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            session = request.getSession(true);
            ReportDAO reportDAO = new ReportDAO();
            ArrayList shgData = new ArrayList();
            ArrayList mandallist = new ArrayList();
            if (session.getAttribute("districtId") != null) 
            {
                districtid = (String) session.getAttribute("districtId");
            }
            if(mandalRoleId.toString().equals(roleId))
            {
            	aadharCardMappingForm.setMandal_id(mandalId);
            }
                            
            
            aadharCardMappingForm.setDistrict_id(districtid);
            if (aadharCardMappingForm.getMandal_id() != null && !aadharCardMappingForm.getMandal_id().equalsIgnoreCase("0")) { // If mandalid is not select

                shgData = reportDAO.getSadaremnothavingaadhracardnos(ds,CommonUtility.checkNullObj(aadharCardMappingForm.getDistrict_id()), CommonUtility.checkNullObj(aadharCardMappingForm.getMandal_id()), CommonUtility.checkNullObj(aadharCardMappingForm.getVillage_id()), CommonUtility.checkNullObj(aadharCardMappingForm.getHabitation_id()));
                if (shgData != null && shgData.size() > 0) {
                    request.setAttribute("shgAbstract", shgData);

                } else {
                    request.setAttribute("nodata", "SADAREM ID Not Tagged To AADHARCARD No Details Not Available!");
                }
            }
            mandallist = functionalNeedService.getMandals(ds, aadharCardMappingForm.getDistrict_id(), "");
            if(mandalRoleId.toString().equals(roleId))
            {
              mandallist = new ArrayList();
              String mandalname = comObj.getMandalName(districtid,mandalId) ;
              ArrayList tempList = new ArrayList();
              tempList.add(mandalId);
              tempList.add(mandalname);
              mandallist.add(tempList);
            }
            
            aadharCardMappingForm.setMandallist(mandallist);
            request.setAttribute("mandallist", mandallist);
            request.setAttribute("mandal_id", mandalId);
            if (aadharCardMappingForm.getMandal_id() != null && !aadharCardMappingForm.getMandal_id().equalsIgnoreCase("0")) {
                aadharCardMappingForm.setVillagelist(functionalNeedService.getVillageAll(ds, aadharCardMappingForm.getDistrict_id(), aadharCardMappingForm.getMandal_id()));
            }
            if (aadharCardMappingForm.getVillage_id() != null && !aadharCardMappingForm.getVillage_id().equalsIgnoreCase("All")) {
                aadharCardMappingForm.setHabitationlist(territoryDAO.getHabitations(ds, aadharCardMappingForm.getDistrict_id(), aadharCardMappingForm.getMandal_id(), null, aadharCardMappingForm.getVillage_id()));
            }
            String flag = null;
            if (request.getParameter("flag") != null && !request.getParameter("flag").equalsIgnoreCase("")
                    && !request.getParameter("flag").equalsIgnoreCase("null")) {
                flag = request.getParameter("flag").toString();
            }
            if (flag != null) {
                request.setAttribute("flag", flag);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return mapping.findForward("successHabitation");
    }

    public ActionForward getValidSADAREMDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        AadharCardMappingForm aadharCardMappingForm = (AadharCardMappingForm) form;
        AadharCardMappingService aadharCardMappingService = AadharCardMappingServiceFactory.getAddRoleDisplayServiceImpl();
        AadharCardMappingDTO aadharCardMappingDTO = new AadharCardMappingDTO();
        AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();
        int count = 0;
        int validSadaemIdCount = 0;
        String aadharCount = "";
        String invalidMsg = null;
        ArrayList sadaremDetails = null;
        HttpSession session = null;
        String districtid = null;
        String expection = null;
        URL url1 = new URL(CommonConstants.Url1);
        String valid = "invalid";
        ArrayList list = new ArrayList();
        try 
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds))
            {
                ds = JNDIDataSource.getConnection();
            }
            
            
            aadharCardMappingForm.setSadaremId(CommonUtility.checkNull(request.getParameter("personcode")));
            CommonDetails commondetails = new CommonDetails();

            session = request.getSession();
            if (session.getAttribute("districtId") != null) 
            {
                districtid = (String) session.getAttribute("districtId");
            }
            request.setAttribute("personcode", aadharCardMappingForm.getSadaremId());
            boolean districtLevelAccessFlag = commondetails.checkDistrictFlag(aadharCardMappingForm.getSadaremId(), districtid);
            int roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            if (districtLevelAccessFlag || roleId==CommonConstants.SPMLOGIN_ROLEID) 
            {
                validSadaemIdCount = aadharCardMappingService.getValidSADAREMIDDetails(ds, aadharCardMappingForm);
                if (validSadaemIdCount >= 1)
                {
                    aadharCardMappingDTO = aadharCardMappingService.getAadharCardCount(ds, aadharCardMappingForm);
                    if (aadharCardMappingDTO.getValidMsg() == null) 
                    {
                    	/* String rationCardNumber = aadharCardMappingDAO.getRationCardNumber(ds, aadharCardMappingForm);
                        if (rationCardNumber != null && rationCardNumber.length() > 0)
                        {
                            // AponlineWSClient wsClient = new AponlineWSClient();
                            // ArrayList wsRationCardDetailsList = wsClient.getDetails(url1, rationCardNumber.toUpperCase(), request, "");
                            ArrayList wsRationCardDetailsList = aadharCardMappingDAO.getCivilSuppliesData(ds, rationCardNumber, districtid);
                            if (wsRationCardDetailsList.size() == 0) 
                            {
                                PersonCodeCheckDAO dao = new PersonCodeCheckDAO();
                                List<String> dists = dao.getIssueRaisingRationCard(ds, rationCardNumber);
                                String oldDist = "";
                                if (dists.size() > 0 && !dists.get(0).equals("")) 
                                {
                                    oldDist = dists.get(0);
                                    wsRationCardDetailsList = dao.getDataFromCivilDatabaseWithOld(ds, rationCardNumber, oldDist);
                                    // Collections.sort(getRationData, myComparator);
                                }
                            }

                            request.setAttribute("wsRationCardDetailsList", wsRationCardDetailsList);
                            sadaremDetails = aadharCardMappingService.getSADAREMIDValidDetails(ds, aadharCardMappingForm);

                            request.setAttribute("sadaremCommonDetails", sadaremDetails);
                            request.setAttribute("updateButton", "updateButton");

                            if (wsRationCardDetailsList != null && wsRationCardDetailsList.size() > 0) {


                                // expection = "ServerDown1";

                                sadaremDetails = aadharCardMappingService.getSADAREMIDValidDetails(ds, aadharCardMappingForm);
                                request.setAttribute("sadaremCommonDetails", sadaremDetails);

                                for (int j = 0; j < wsRationCardDetailsList.size(); j++) {
                                    valid = "invalid";
                                    Map templist = new HashMap();
                                    Map rationCardData = new HashMap<String, String>();
                                    templist = (HashMap) wsRationCardDetailsList.get(j);
                                    rationCardData.put("memberName", templist.get("memberName").toString());
                                    rationCardData.put("slNo", templist.get("slNo").toString());
                                    rationCardData.put("age", templist.get("age").toString());
                                    rationCardData.put("dob", templist.get("dob").toString());
                                    rationCardData.put("relationName", templist.get("relationName").toString());
                                    rationCardData.put("gender", templist.get("gender").toString());
                                    rationCardData.put("district", templist.get("district").toString());
                                    if (templist.get("aadharcardNo") != null && !templist.get("aadharcardNo").toString().equalsIgnoreCase("")
                                            && !templist.get("aadharcardNo").toString().equalsIgnoreCase("null")) {
                                        valid = getAadharCardValid(templist.get("aadharcardNo").toString());
                                    }
                                    if (valid.equalsIgnoreCase("success")) {
                                        rationCardData.put("aadharcardNo", templist.get("aadharcardNo").toString());
                                    } else {
                                        rationCardData.put("aadharcardNo", "");
                                    }
                                    list.add(rationCardData);
                                }
                                wsRationCardDetailsList = list;
                                request.setAttribute("wsRationCardDetailsList", wsRationCardDetailsList);
                                request.setAttribute("updateButton", "updateButton");

                            } else {
                                sadaremDetails = aadharCardMappingService.getSADAREMIDValidDetails(ds, aadharCardMappingForm);
                                request.setAttribute("sadaremDetails", sadaremDetails);
                                request.setAttribute("updateButton", "updateButton");
                            }
                        } else {*/
                            sadaremDetails = aadharCardMappingService.getSADAREMIDValidDetails(ds, aadharCardMappingForm);
                            request.setAttribute("sadaremDetails", sadaremDetails);
                            request.setAttribute("updateButton", "updateButton");
                            
                            if(sadaremDetails==null || sadaremDetails.size()==0)
                            {
                            	request.setAttribute("errmsg", "This SADAREM ID: " + aadharCardMappingForm.getSadaremId() + " Please Enter Valid Sadarem ID");
                            }

                       /* }*/
                    } else {
                        request.setAttribute("errmsg", "This SADAREM ID: " + aadharCardMappingForm.getSadaremId() + " was already tagged to " + aadharCardMappingDTO.getPrrofDocType() + ":" + aadharCardMappingDTO.getValidMsg());
                        aadharCardMappingForm.setSadaremId("");
                    }

                } else {
                    invalidMsg = aadharCardMappingService.invalidSADAREMIDMsg(ds, aadharCardMappingForm);
                    request.setAttribute("errmsg", "Entered SADAREM ID  was " + invalidMsg);
                    aadharCardMappingForm.setSadaremId("");
                }

            } else {
                request.setAttribute("errmsg", "This SADAREM ID: " + aadharCardMappingForm.getSadaremId() + "  does not belongs to your district");
                aadharCardMappingForm.setSadaremId("");
            }
            request.setAttribute("flag", "sadarem");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // aadharCardMappingForm.setRadioValue(null);
        return mapping.findForward(SUCCESS);


    }

    public ActionForward getAadharCardNoDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        AadharCardMappingForm aadharCardMappingForm = (AadharCardMappingForm) form;
        AadharCardMappingService aadharCardMappingService = AadharCardMappingServiceFactory.getAddRoleDisplayServiceImpl();
        AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();
        ArrayList sadaremDetails = null;
        HttpSession session = null;
        String districtid = null;
        String expection = null;
        String personcode = null;
        String rationCardNo = null;
        URL url1 = new URL(CommonConstants.Url1);
        String valid = "invalid";
        ArrayList list = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            CommonDetails commondetails = new CommonDetails();

            session = request.getSession();
            if (session.getAttribute("districtId") != null) {
                districtid = (String) session.getAttribute("districtId");
            }
            if (request.getParameter("personcode") != null && !request.getParameter("personcode").equalsIgnoreCase("")
                    && !request.getParameter("personcode").equalsIgnoreCase("null")) {
                personcode = request.getParameter("personcode");
            }
            aadharCardMappingForm.setSadaremId(personcode);
            if (request.getParameter("rationCardNo") != null && !request.getParameter("rationCardNo").equalsIgnoreCase("")
                    && !request.getParameter("rationCardNo").equalsIgnoreCase("null") && !request.getParameter("rationCardNo").equalsIgnoreCase("-")) {
                rationCardNo = request.getParameter("rationCardNo");
            }
            if (rationCardNo == null) {
                rationCardNo = aadharCardMappingForm.getRationcardNo();
            }
            if (rationCardNo != null && rationCardNo.length() > 0) {
                //AponlineWSClient wsClient = new AponlineWSClient();
                //ArrayList wsRationCardDetailsList = wsClient.getDetails(url1, rationCardNo.toUpperCase(), request, "");
                ArrayList wsRationCardDetailsList = aadharCardMappingDAO.getCivilSuppliesData(ds, rationCardNo, districtid);
                //ArrayList wsRationCardDetailsList = aadharCardMappingDAO.getCivilSuppliesData(ds, rationCardNumber, districtid);
                if (wsRationCardDetailsList.size() == 0) {
                    PersonCodeCheckDAO dao = new PersonCodeCheckDAO();
                    List<String> dists = dao.getIssueRaisingRationCard(ds, rationCardNo);
                    String oldDist = "";
                    if (dists.size() > 0 && !dists.get(0).equals("")) {
                        oldDist = dists.get(0);
                        wsRationCardDetailsList = dao.getDataFromCivilDatabaseWithOld(ds, rationCardNo, oldDist);
                        // Collections.sort(getRationData, myComparator);
                    }
                }

                request.setAttribute("wsRationCardDetailsList", wsRationCardDetailsList);
                sadaremDetails = aadharCardMappingService.getSADAREMIDValidDetails(ds, aadharCardMappingForm);
                request.setAttribute("sadaremCommonDetails", sadaremDetails);
                request.setAttribute("updateButton", "updateButton");
                //wsRationCardDetailsList=null;
                if (wsRationCardDetailsList != null && wsRationCardDetailsList.size() > 0) {
                    sadaremDetails = aadharCardMappingService.getSADAREMIDValidDetails(ds, aadharCardMappingForm);
                    request.setAttribute("sadaremCommonDetails", sadaremDetails);

                    for (int j = 0; j < wsRationCardDetailsList.size(); j++) {
                        valid = "invalid";
                        Map templist = new HashMap();
                        Map rationCardData = new HashMap<String, String>();
                        templist = (HashMap) wsRationCardDetailsList.get(j);
                        rationCardData.put("memberName", templist.get("memberName").toString());
                        rationCardData.put("slNo", templist.get("slNo").toString());
                        rationCardData.put("age", templist.get("age").toString());
                        rationCardData.put("dob", templist.get("dob").toString());
                        rationCardData.put("relationName", templist.get("relationName").toString());
                        if (templist.get("gender") != null) {
                            rationCardData.put("gender", templist.get("gender").toString());
                        }
                        rationCardData.put("district", templist.get("district").toString());
                        if (templist.get("aadharcardNo") != null && !templist.get("aadharcardNo").toString().equalsIgnoreCase("")
                                && !templist.get("aadharcardNo").toString().equalsIgnoreCase("null")) {
                            valid = getAadharCardValid(templist.get("aadharcardNo").toString());
                        }
                        if (valid.equalsIgnoreCase("success")) {
                            rationCardData.put("aadharcardNo", templist.get("aadharcardNo").toString());
                        } else {
                            rationCardData.put("aadharcardNo", "");
                        }
                        list.add(rationCardData);
                    }
                    wsRationCardDetailsList = list;
                    request.setAttribute("wsRationCardDetailsList", wsRationCardDetailsList);
                    request.setAttribute("updateButton", "updateButton");

                } else {
                    sadaremDetails = aadharCardMappingService.getSADAREMIDValidDetails(ds, aadharCardMappingForm);

                    request.setAttribute("sadaremDetails", sadaremDetails);
                    request.setAttribute("updateButton", "updateButton");
                }
            } else {
                sadaremDetails = aadharCardMappingService.getSADAREMIDValidDetails(ds, aadharCardMappingForm);

                request.setAttribute("sadaremDetails", sadaremDetails);
                request.setAttribute("updateButton", "updateButton");

            }
            request.setAttribute("personcode", personcode);
            request.setAttribute("rationCardNo", rationCardNo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // aadharCardMappingForm.setRadioValue(null);
        return mapping.findForward("aadharCardDetails");


    }

    public String getAadharCardDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        AadharCardMappingForm aadharCardMappingForm = (AadharCardMappingForm) form;
        AadharCardMappingService aadharCardMappingService = AadharCardMappingServiceFactory.getAddRoleDisplayServiceImpl();
        AadharCardMappingDTO aadharCardMappingDTO = new AadharCardMappingDTO();
        int count = 0;
        String target = "success";
        String msg = null;
        String personcode = null;
        String aadharvalid = null;
        AadhaarProfile aadharProfile = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();

            }
            String flag = null;
            if (request.getParameter("flag") != null && !request.getParameter("flag").equalsIgnoreCase("")
                    && !request.getParameter("flag").equalsIgnoreCase("null")) {
                flag = request.getParameter("flag").toString();
            }
            if (aadharCardMappingForm.getAadharCardId() != null) {

                AadharDetails aadharDetails = new AadharDetails();
                msg = aadharDetails.validateAadhar(aadharCardMappingForm.getAadharCardId());

                if (msg.equalsIgnoreCase("100") || msg.equalsIgnoreCase("101")) {
                    aadharCardMappingDTO = aadharCardMappingService.particularAaadhartagedSADAREMIDDetails(ds, aadharCardMappingForm);
                    if (aadharCardMappingDTO != null) {
                        request.setAttribute("errmsg", "Given AadharCard No" + aadharCardMappingForm.getAadharCardId() + " already tagged to SADAREM ID" + aadharCardMappingDTO.getValidSADAREMIDMsg());
                        aadharvalid = "Given AadharCard No" + aadharCardMappingForm.getAadharCardId() + " already tagged to SADAREM ID" + aadharCardMappingDTO.getValidSADAREMIDMsg();
                        aadharCardMappingForm.setAadharCardId("");
                    } else {
                        try {
                            //aadharProfile = AadharDetails.getAadharDetails(aadharCardMappingForm.getAadharCardId());
                        	aadharProfile = AadhaarUtility.getAadhaarProfileByUID(aadharCardMappingForm.getAadharCardId());
                            
                        } catch (Exception e) {
                            request.setAttribute("errmsg", "Aadhar Webservice is down. Please try after some time");

                        }
                        String name = aadharProfile.getName();
                        request.setAttribute("aadharCardIdvalue", aadharCardMappingForm.getAadharCardId());
                        aadharCardMappingForm.setAadharCardPersonName(name);
                        request.setAttribute("validMsgFire", "validMsgFire");
                        request.setAttribute("aadharCardNo", aadharCardMappingForm.getAadharCardId());
                        aadharvalid = "success";

                    }
                } else if (msg.equalsIgnoreCase("ap")) {
                    request.setAttribute("errmsg", "This AadharCard No " + aadharCardMappingForm.getAadharCardId() + " is belongs to Andhra Pradesh State so please check in telangana SADAREM website.");
                    aadharCardMappingForm.setAadharCardId("");
                    aadharvalid = "This AadharCard No " + aadharCardMappingForm.getAadharCardId() + " is belongs to Andhra Pradesh State so please check in telangana SADAREM website.";

                } else if (msg.equalsIgnoreCase("Aadhar Webservice is down. Please try after some time")) {
                    request.setAttribute("errmsg", "Aadhar Webservice is down. Please try after some time.");
                    aadharCardMappingForm.setAadharCardId("");
                    aadharvalid = "Aadhar Webservice is down. Please try after some time.";

                } else {
                    request.setAttribute("errmsg", "Aadhaar Card No " + aadharCardMappingForm.getAadharCardId() + " is invalid .");
                    aadharvalid = "Invalid AadharCard No " + aadharCardMappingForm.getAadharCardId();
                    aadharCardMappingForm.setAadharCardId("");
                }
            } else {
                request.setAttribute("errmsg", "Aadhaar Card No " + aadharCardMappingForm.getAadharCardId() + " is invalid .");
                aadharvalid = "Invalid AadharCard No " + aadharCardMappingForm.getAadharCardId();
                aadharCardMappingForm.setAadharCardId("");
            }
            if (flag != null && flag.equalsIgnoreCase("habitation")) {
                target = "aadharCardDetails";
            }
            request.setAttribute("aadharCardNo", aadharCardMappingForm.getAadharCardId());
            if (flag != null) {
                request.setAttribute("flag", flag);
            }
            if (flag != null && !flag.equalsIgnoreCase("habitation")) {
                aadharCardMappingForm.setSadaremId(personcode);
                this.getValidSADAREMDetails(mapping, form, request, response);
            } else {
                aadharCardMappingForm.setSadaremId(personcode);
                this.getAadharCardNoDetails(mapping, form, request, response);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return aadharvalid;
        //return mapping.findForward(target);


    }

    public ActionForward updatePersonalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        AadharCardMappingForm aadharCardMappingForm = (AadharCardMappingForm) form;
        AadharCardMappingService aadharCardMappingService = AadharCardMappingServiceFactory.getAddRoleDisplayServiceImpl();
        String target = "success";
        HttpSession session = request.getSession();
        String loginId = null;
        int count = 0;
        String aadharExist = null;
        String personcode = null;
        String aadharvalid = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("loginid") != null) {
                loginId = (String) session.getAttribute("loginid");
                aadharCardMappingForm.setLoginId(loginId);
            }
            if (request.getParameter("radioValue") != null && request.getParameter("radioValue").length() > 10) {
                aadharCardMappingForm.setAadharCardId(request.getParameter("radioValue"));

            } else if (request.getParameter("aadharCardId") != null && request.getParameter("aadharCardId").length() > 0) {

                aadharCardMappingForm.setAadharCardId(request.getParameter("aadharCardId"));
            }
            if (aadharCardMappingForm.getAadharCardId() == null && aadharCardMappingForm.getAadharCardId().equalsIgnoreCase("")
                    && request.getParameter("aadharCardId") != null && !request.getParameter("aadharCardId").equalsIgnoreCase("")
                    && !request.getParameter("aadharCardId").equalsIgnoreCase("null")) {
                aadharCardMappingForm.setAadharCardId(request.getParameter("aadharCardId").toString());
            }
            if (request.getParameter("personcode") != null && !request.getParameter("personcode").equalsIgnoreCase("")
                    && !request.getParameter("personcode").equalsIgnoreCase("null")) {
                personcode = request.getParameter("personcode");
            }
            if (request.getParameter("rationCardNo") != null && !request.getParameter("rationCardNo").equalsIgnoreCase("")
                    && !request.getParameter("rationCardNo").equalsIgnoreCase("null")) {
                aadharCardMappingForm.setRationcardNo(request.getParameter("rationCardNo"));
            }
            aadharCardMappingForm.setAadharPersonCode(request.getParameter("personC"));
            aadharExist = aadharCardMappingService.AadharCardExist(ds, aadharCardMappingForm);
            request.setAttribute("errmsg", null);
            if (aadharExist == null) {
                aadharvalid = "success";//this.getAadharCardDetails(mapping, form, request, response);//SPM Request for Map for Any AdharID 
                if (aadharvalid != null && aadharvalid.equalsIgnoreCase("success")) {
                    request.setAttribute("errmsg", null);
                    count = aadharCardMappingService.updateAadharCardCount(ds, aadharCardMappingForm);
                } else {
                    request.setAttribute("errmsg", aadharvalid);
                }
                if (count > 0) {
                    request.setAttribute("succmsg", "SADAREM ID was tagged to AadharCard No Successfully");
                }
            } else {
                request.setAttribute("nodata", "Provided AadharCard No " + aadharCardMappingForm.getAadharCardId() + " already tagged to " + aadharExist);
            }
            aadharCardMappingForm.setSadaremId("");
            String flag = null;
            if (request.getParameter("flag") != null && !request.getParameter("flag").equalsIgnoreCase("")
                    && !request.getParameter("flag").equalsIgnoreCase("null")) {
                flag = request.getParameter("flag").toString();
            }
            if (flag != null) {
                request.setAttribute("flag", flag);
            }

            if (flag != null && flag.equalsIgnoreCase("habitation") && aadharvalid != null && aadharvalid.equalsIgnoreCase("success")) {
                request.setAttribute("closewindow", "closewindow");
                target = "successHabitation";
            } else if (flag != null && flag.equalsIgnoreCase("habitation")) {
                aadharCardMappingForm.setSadaremId(personcode);
                if (count == 0) {
                    this.getAadharCardNoDetails(mapping, form, request, response);
                }
                target = "aadharCardDetails";
            } else {

                if (count == 0) {
                    aadharCardMappingForm.setSadaremId(personcode);
                    this.getValidSADAREMDetails(mapping, form, request, response);
                } else {
                    aadharCardMappingForm.setSadaremId("");
                }
                target = "success";
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return mapping.findForward(target);

    }

    public String getAadharCardValid(String aadharCardNo)
            throws Exception {
        String msg = "invalid";
        try {
            if (aadharCardNo != null) {
                AadharDetails aadharDetails = new AadharDetails();
                msg = aadharDetails.validateAadhar(aadharCardNo);

                if (msg.equalsIgnoreCase("100") || msg.equalsIgnoreCase("101")) {
                    msg = "success";
                } else {
                    msg = "invalid";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
        //return mapping.findForward(target);


    }
}
