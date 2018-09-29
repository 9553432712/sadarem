/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import com.meesevaWS.MeeSevaWebServiceSoapProxy;
import org.bf.disability.servicefactory.GrievancesRequestDetailsServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.axis.message.MessageElement;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.DataBase.JNDIDataSource;

import org.bf.disability.form.MeesvaSearchForm;
import org.bf.disability.service.MeesvaSearchService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.MeesvaSearchServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.TripleDESEncryption;
//import org.tempuri.MeeSevaWebServiceSoapProxy;
import com.meesevaWS.GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult;
import com.meesevaWS.GetPaymentTransIdResponseGetPaymentTransIdResult;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletOutputStream;
import org.bf.disability.dao.GrievancesRequestDetailsDAO;
import org.bf.disability.dto.MeesevaSearchDTO;
import org.bf.disability.service.GrievancesRequestDetailsService;

/**
 *
 * @author 693461
 */
public class MeesvaSearchAction extends DispatchAction {

    /* forward name="success" path="" */
    //String target = "success";
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
        String target = "";
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        ArrayList districtList = new ArrayList();
        MeesvaSearchForm meesvaSearchForm = (MeesvaSearchForm) form;
        DataSource ds = null;
        ArrayList mandalList = new ArrayList();
        ArrayList villageList = new ArrayList();
        ArrayList panchayatList = new ArrayList();
        ArrayList habitationList = new ArrayList();
        CommonDAO commonDAO = new CommonDAO();
        String enc = null;
        String decryptedValue = null;
        String userId = null;
        String operatorId = null;
        String checkSum = null;
        String requestId = null;
        String serviceid = null;
        String scaPassword = null;
        String applicationNo = null;
        String meesevaflag = null;
        String uniqueNo = null;
        String scaUserId = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            if (request.getParameter("enc") != null && !request.getParameter("enc").equalsIgnoreCase("")) {
                enc = request.getParameter("enc");
                meesvaSearchForm.setEncryptedString(enc);
            }

            byte[] myIV = {(byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57};
            byte[] tdesKeyData = {
                (byte) 0xA2, (byte) 0x15, (byte) 0x37, (byte) 0x07, (byte) 0xCB, (byte) 0x62,
                (byte) 0xC1, (byte) 0xD3, (byte) 0xF8, (byte) 0xF1, (byte) 0x97, (byte) 0xDF,
                (byte) 0xD0, (byte) 0x13, (byte) 0x4F, (byte) 0x79, (byte) 0x01, (byte) 0x67,
                (byte) 0x7A, (byte) 0x85, (byte) 0x94, (byte) 0x16, (byte) 0x31, (byte) 0x92};


            //Identity.instance().unAuthenticate();
            TripleDESEncryption objText = new TripleDESEncryption(tdesKeyData, myIV);
            decryptedValue = objText.decryptText(enc);
            if (decryptedValue != null) {
                String values[] = decryptedValue.split("&");
                //String values[] = enc.split(",");

                for (int i = 0; i < values.length; i++) {
                    String paramValues[] = values[i].split("=");
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("P1")) {
                        uniqueNo = paramValues[1];

                        meesvaSearchForm.setUniqueNo(uniqueNo);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p2")) {
                        scaUserId = paramValues[1];
                        meesvaSearchForm.setSCAUserId(scaUserId);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p3")) {
                        userId = paramValues[1];
                        meesvaSearchForm.setLoginId(userId);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p4")) {
                        operatorId = paramValues[1];
                        meesvaSearchForm.setChannelId(operatorId);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p5")) {
                        checkSum = paramValues[1];
                        meesvaSearchForm.setCheckSum(checkSum);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p6")) {
                        requestId = paramValues[1];
                        meesvaSearchForm.setRequestId(requestId);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p7")) {
                        serviceid = paramValues[1];
                        meesvaSearchForm.setServiceid(serviceid);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p8")) {
                        scaPassword = paramValues[1];
                        meesvaSearchForm.setScaPassword(scaPassword);

                    }

                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p9")) {
                        applicationNo = paramValues[1];
                        meesvaSearchForm.setApplicationNo(applicationNo);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p10")) {
                        meesevaflag = paramValues[1];
                        meesvaSearchForm.setMeesevaFlag(meesevaflag);

                    }

                }
            }

            if (compareCheckSum(scaUserId, scaPassword, scaUserId, userId, uniqueNo, checkSum)) {

                districtList = territoryservice.getDistricts(ds);
                if (districtList != null && districtList.size() > 0) {
                    meesvaSearchForm.setDistrictlist(districtList);
                }
                mandalList = territoryservice.getMandals(ds, meesvaSearchForm.getDistrict_id());
                meesvaSearchForm.setMandalList(mandalList);

                panchayatList = territoryservice.getPanchayats(ds, meesvaSearchForm.getDistrict_id(), meesvaSearchForm.getMandal_id());
                meesvaSearchForm.setPanchayatList(panchayatList);

                villageList = territoryservice.getVillages(ds, meesvaSearchForm.getDistrict_id(), meesvaSearchForm.getMandal_id(), meesvaSearchForm.getPanchayat_id());
                meesvaSearchForm.setVillagelist(villageList);

                habitationList = territoryservice.getHabitations(ds, meesvaSearchForm.getDistrict_id(), meesvaSearchForm.getMandal_id(), meesvaSearchForm.getPanchayat_id(), meesvaSearchForm.getVillage_id());
                meesvaSearchForm.setHabitationlist(habitationList);
                ArrayList campList = commonDAO.getCampDetails(ds, meesvaSearchForm.getDistrict_id());

                meesvaSearchForm.setCampList(campList);
                target = "success";
            } else {
                target = "exception";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    public ActionForward getCampDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        ArrayList districtList = new ArrayList();
        ArrayList mandalList = new ArrayList();
        ArrayList villageList = new ArrayList();
        ArrayList panchayatList = new ArrayList();
        ArrayList habitationList = new ArrayList();
        MeesvaSearchForm meesvaSearchForm = (MeesvaSearchForm) form;
        DataSource ds = null;
        CommonDAO commonDAO = new CommonDAO();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryservice.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                meesvaSearchForm.setDistrictlist(districtList);
            }
            mandalList = territoryservice.getMandals(ds, meesvaSearchForm.getDistrict_id());
            meesvaSearchForm.setMandalList(mandalList);

            panchayatList = territoryservice.getPanchayats(ds, meesvaSearchForm.getDistrict_id(), meesvaSearchForm.getMandal_id());
            meesvaSearchForm.setPanchayatList(panchayatList);

            villageList = territoryservice.getVillages(ds, meesvaSearchForm.getDistrict_id(), meesvaSearchForm.getMandal_id(), meesvaSearchForm.getPanchayat_id());
            meesvaSearchForm.setVillagelist(villageList);

            habitationList = territoryservice.getHabitations(ds, meesvaSearchForm.getDistrict_id(), meesvaSearchForm.getMandal_id(), meesvaSearchForm.getPanchayat_id(), meesvaSearchForm.getVillage_id());
            meesvaSearchForm.setHabitationlist(habitationList);

            ArrayList campList = commonDAO.getCampDetails(ds, meesvaSearchForm.getDistrict_id());

            meesvaSearchForm.setCampList(campList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getMeesevaSearchDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String target = "success";
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        ArrayList districtList = new ArrayList();
        ArrayList mandalList = new ArrayList();
        ArrayList villageList = new ArrayList();
        ArrayList panchayatList = new ArrayList();
        ArrayList habitationList = new ArrayList();
        MeesvaSearchForm meesvaSearchForm = (MeesvaSearchForm) form;
        DataSource ds = null;
        CommonDAO commonDAO = new CommonDAO();
        MeesvaSearchService meesvaSearchService = MeesvaSearchServiceFactory.getMeesvaSearchServiceImpl();
        String flagStatus = "";
        ArrayList statusCertificateType = new ArrayList();
        ArrayList certificateValidList = null;


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryservice.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                meesvaSearchForm.setDistrictlist(districtList);
            }
            mandalList = territoryservice.getMandals(ds, meesvaSearchForm.getDistrict_id());
            meesvaSearchForm.setMandalList(mandalList);

            panchayatList = territoryservice.getPanchayats(ds, meesvaSearchForm.getDistrict_id(), meesvaSearchForm.getMandal_id());
            meesvaSearchForm.setPanchayatList(panchayatList);

            villageList = territoryservice.getVillages(ds, meesvaSearchForm.getDistrict_id(), meesvaSearchForm.getMandal_id(), meesvaSearchForm.getPanchayat_id());
            meesvaSearchForm.setVillagelist(villageList);

            habitationList = territoryservice.getHabitations(ds, meesvaSearchForm.getDistrict_id(), meesvaSearchForm.getMandal_id(), meesvaSearchForm.getPanchayat_id(), meesvaSearchForm.getVillage_id());
            meesvaSearchForm.setHabitationlist(habitationList);
//
            meesvaSearchForm.setCampList(commonDAO.getCampDetails(ds, meesvaSearchForm.getDistrict_id()));

            flagStatus = meesvaSearchService.getSadaremIdDetails(ds, meesvaSearchForm);


            if (flagStatus != null) {
                if (flagStatus.equalsIgnoreCase("Edit")) {
                    request.setAttribute("msg", "<font color ='red'>Only PartA Entered Certificate not Generated!</font>");
                    meesvaSearchForm.setSadaremId("");
                } else if (flagStatus.equalsIgnoreCase("View")) {
                    certificateValidList = meesvaSearchService.getCertificateValidDetails(ds, meesvaSearchForm);
                    if (certificateValidList.size() > 0) {
                        request.setAttribute("certificateValidList", certificateValidList);
                    } else {
                        if ((meesvaSearchForm.getSadaremId() != null && !meesvaSearchForm.getSadaremId().equalsIgnoreCase(""))
                                || (meesvaSearchForm.getPensionId() != null && !meesvaSearchForm.getPensionId().equalsIgnoreCase(""))
                                || (meesvaSearchForm.getRationCardNo() != null && !meesvaSearchForm.getRationCardNo().equalsIgnoreCase(""))
                                || (meesvaSearchForm.getAadharCardNo() != null) && !meesvaSearchForm.getAadharCardNo().equalsIgnoreCase("")) {

                            request.setAttribute("msg", "<font color ='red'>Certificate Not Uploaded!</font>");
                        } else {
                            request.setAttribute("msg", "<font color ='red'>Assessment not done for provided Details</font>");
                        }
                    }
                }
            } else {
                request.setAttribute("msg", "<font color='red'>Assessment not done for provided Details</font>");
                meesvaSearchForm.setSadaremId("");
                meesvaSearchForm.setAadharCardNo("");
                meesvaSearchForm.setPensionId("");
                meesvaSearchForm.setCampId("");
                meesvaSearchForm.setDistrict_id("0");
                meesvaSearchForm.setMandal_id("0");
                meesvaSearchForm.setVillage_id("0");
                meesvaSearchForm.setHabitation_id("0");
                meesvaSearchForm.setDistrict_id("0");
                meesvaSearchForm.setRationCardNo("");
                meesvaSearchForm.setName("");
            }
            request.setAttribute("uniqueNo", meesvaSearchForm.getUniqueNo());
            request.setAttribute("scaUserId", meesvaSearchForm.getSCAUserId());
            request.setAttribute("userId", meesvaSearchForm.getLoginId());
            request.setAttribute("operatorId", meesvaSearchForm.getChannelId());
            request.setAttribute("checkSum", meesvaSearchForm.getCheckSum());
            request.setAttribute("requestId", meesvaSearchForm.getRequestId());
            request.setAttribute("serviceid", meesvaSearchForm.getServiceid());
            request.setAttribute("scaPassword", meesvaSearchForm.getScaPassword());
            request.setAttribute("applicationNo", meesvaSearchForm.getApplicationNo());
            request.setAttribute("meesevaflag", meesvaSearchForm.getMeesevaFlag());
            request.setAttribute("encryptedString", meesvaSearchForm.getEncryptedString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getIndivialCertificateDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "exception";
        MeesvaSearchForm meesvaSearchForm = (MeesvaSearchForm) form;
        DataSource ds = null;
        MeesvaSearchService meesvaSearchService = MeesvaSearchServiceFactory.getMeesvaSearchServiceImpl();
        String districtName = null;
        String mandalName = null;
        String personCode = null;
        String certificatetype = null;
        String checksum = null;
        String flags = null;
        int updateMeesevaId = 0;
        String systemIp = null;
        int success = 0;
        MeesevaSearchDTO meesevaSearchDTO = new MeesevaSearchDTO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            } //certificatetype
            systemIp = request.getRemoteAddr();
            if (request.getParameter("personCode") != null) {
                personCode = request.getParameter("personCode");
                request.setAttribute("personCode", personCode);
                meesvaSearchForm.setSadaremId(personCode);
            }

            if (request.getParameter("districtName") != null) {
                districtName = request.getParameter("districtName");

            }
            if (request.getParameter("mandalName") != null) {
                mandalName = request.getParameter("mandalName");

            }
            GrievancesRequestDetailsDAO grievancesRequestDetailsDAO = new GrievancesRequestDetailsDAO();
            //String fileCertificatePath = CommonConstants.SCANCERTIFICATES + "//" + districtName + "//" + mandalName + "//Certificate" + "\\";
            //
            //String filePdIdPath = CommonConstants.SCANCERTIFICATES + "//" + districtName + "//" + mandalName + "//Idcard" + "\\";

            String Systemip = request.getRemoteAddr();
            if (request.getParameter("districtId") != null) {
                meesvaSearchForm.setDistrict_id(request.getParameter("districtId"));

            }
            if (request.getParameter("name") != null) {
                meesvaSearchForm.setFirstname(request.getParameter("name"));
                request.setAttribute("name", meesvaSearchForm.getFirstname());

            }
            if (request.getParameter("mobile") != null) {
                meesvaSearchForm.setMobile(request.getParameter("mobile"));
                meesvaSearchForm.setPhoneno(request.getParameter("mobile"));

            }
            if (request.getParameter("uniqueNo") != null) {
                meesvaSearchForm.setUniqueNo(request.getParameter("uniqueNo"));

            }

            if (request.getParameter("scaUserId") != null) {
                meesvaSearchForm.setSCAUserId(request.getParameter("scaUserId"));

            }
            if (request.getParameter("userId") != null) {
                meesvaSearchForm.setLoginId(request.getParameter("userId"));

            }
            if (request.getParameter("operatorId") != null) {

                meesvaSearchForm.setChannelId(request.getParameter("operatorId"));
                request.setAttribute("channelId", meesvaSearchForm.getChannelId());

            }
            if (request.getParameter("checkSum") != null) {
                meesvaSearchForm.setCheckSum(request.getParameter("checkSum"));


            }
            if (request.getParameter("requestId") != null) {
                meesvaSearchForm.setRequestId(request.getParameter("requestId"));

            }
            if (request.getParameter("serviceid") != null) {
                meesvaSearchForm.setServiceid(request.getParameter("serviceid"));
            }
            if (request.getParameter("scaPassword") != null) {
                meesvaSearchForm.setScaPassword(request.getParameter("scaPassword"));
            }
            if (request.getParameter("applicationNo") != null) {
                meesvaSearchForm.setApplicationNo(request.getParameter("applicationNo"));

                request.setAttribute("applicationNo", meesvaSearchForm.getApplicationNo());

            }
            if (request.getParameter("meesevaflag") != null) {
                meesvaSearchForm.setMeesevaFlag(request.getParameter("meesevaflag"));
            }
            if (request.getParameter("encryptedString") != null) {
                meesvaSearchForm.setEncryptedString(request.getParameter("encryptedString"));
            }
            if (request.getParameter("villageId") != null) {
                meesvaSearchForm.setVillage_id(request.getParameter("villageId"));
            }

            if (request.getParameter("mandalId") != null) {
                meesvaSearchForm.setMandal_id(request.getParameter("mandalId"));
            }
            if (request.getParameter("districtId") != null) {
                meesvaSearchForm.setDistrict_id(request.getParameter("districtId"));
            }
            if (request.getParameter("districtName") != null) {
                request.setAttribute("districtName", request.getParameter("districtName"));
            }
            if (request.getParameter("mandalName") != null) {
                request.setAttribute("mandalName", request.getParameter("mandalName"));
            }
            if (request.getParameter("villageName") != null) {
                request.setAttribute("villageName", request.getParameter("villageName"));
            }
            if (request.getParameter("habCode") != null) {
                meesvaSearchForm.setHabCode(request.getParameter("habCode"));
            }

            if (request.getParameter("flagTableStatus") != null) {
                request.setAttribute("flagTableStatus", request.getParameter("flagTableStatus"));
            }
            if (request.getParameter("totaldisability") != null) {
                request.setAttribute("totaldisability", request.getParameter("totaldisability"));
            }

            
            GrievancesRequestDetailsService detailsService = (GrievancesRequestDetailsService) GrievancesRequestDetailsServiceFactory.getdetailsServiceImpl();
            MeeSevaWebServiceSoapProxy proxy = new MeeSevaWebServiceSoapProxy();

            String[] arrPaymentDetails = new String[9];
            arrPaymentDetails[0] = meesvaSearchForm.getUniqueNo();
            arrPaymentDetails[1] = meesvaSearchForm.getSCAUserId();
            arrPaymentDetails[2] = "CA";
            arrPaymentDetails[3] = meesvaSearchForm.getLoginId();
            arrPaymentDetails[4] = meesvaSearchForm.getChannelId();
            arrPaymentDetails[5] = meesvaSearchForm.getApplicationNo();
            arrPaymentDetails[6] = meesvaSearchForm.getRequestId();
            arrPaymentDetails[7] = meesvaSearchForm.getServiceid();
            arrPaymentDetails[8] = meesvaSearchForm.getScaPassword();

            String[] arrAmount = new String[5];
            arrAmount[0] = "25";// this.getUserCharge() + "";
            arrAmount[1] = "0";// this.getDocAmount() + "";
            arrAmount[2] = "0";
            arrAmount[3] = "0";// this.getAppAmount() + "";
            arrAmount[4] = "0";//

            String[] arrTransParams = new String[4];
            arrTransParams[0] = "Applicant Name";
            arrTransParams[1] = "District";
            arrTransParams[2] = "MobileNo";
            arrTransParams[3] = "Total Amount";

            String[] arrTransDetails = new String[4];

            arrTransDetails[0] = meesvaSearchForm.getFirstname();// this.dealerName;
            arrTransDetails[1] = meesvaSearchForm.getDistrict_id();// this.getDistrict();
            arrTransDetails[2] = meesvaSearchForm.getPhoneno();// this.getMobileNo();
            arrTransDetails[3] = "25";// this.getTotAmount() + "";

            checksum = GenerateCheckSum(meesvaSearchForm.getSCAUserId(), meesvaSearchForm.getScaPassword(),
                    meesvaSearchForm.getSCAUserId(), meesvaSearchForm.getLoginId(), meesvaSearchForm.getUniqueNo());
            GetPaymentGatewayResponseResponseGetPaymentGatewayResponseResult xmlObj;

            xmlObj = proxy.getPaymentGatewayResponse(arrPaymentDetails,
                    arrAmount, arrTransParams, arrTransDetails, "MEESEVA", "MEESEVA", checksum);

            MessageElement[] elements = xmlObj.get_any();

            Iterator iterator = elements[0].getChildElements();
            String result = "";

            while (iterator.hasNext()) {
                MessageElement ele = (MessageElement) iterator.next();
                result += ele.getValue() + "#";
            }
            String[] response1 = result.split("#");
            flags = response1[0];


            //flags = "0";
//
            if (flags != null && flags.length() > 0 && flags.equals("0")) {
                meesvaSearchForm.setSystemIp(systemIp);
                success = meesvaSearchService.insertSearchMeesevaSearchDetails(ds, meesvaSearchForm);
                if (success > 0) {

                    try {
                        // TODO initialize WS operation arguments here

                        String checksum1 = null;
                        String transId = null;
                        checksum1 = GenerateCheckSum(meesvaSearchForm.getSCAUserId(), meesvaSearchForm.getScaPassword(),
                                meesvaSearchForm.getSCAUserId(), meesvaSearchForm.getLoginId(), meesvaSearchForm.getUniqueNo());

                        String[] arrPaymentDetails1 = new String[12];

                        arrPaymentDetails1[0] = meesvaSearchForm.getUniqueNo();
                        arrPaymentDetails1[1] = meesvaSearchForm.getSCAUserId();
                        arrPaymentDetails1[2] = "CA";
                        arrPaymentDetails1[3] = meesvaSearchForm.getLoginId();
                        arrPaymentDetails1[4] = meesvaSearchForm.getChannelId();
                        arrPaymentDetails1[5] = meesvaSearchForm.getApplicationNo();
                        arrPaymentDetails1[6] = meesvaSearchForm.getRequestId();
                        arrPaymentDetails1[7] = meesvaSearchForm.getServiceid();
//                        arrPaymentDetails1[8] = grievancesRequestDetailsDAO.SADAREMTransctionId(ds, meesvaSearchForm.getApplicationNo());
                        arrPaymentDetails1[8] = meesvaSearchForm.getSadaremId();
                        arrPaymentDetails1[9] = "00";
                        arrPaymentDetails1[10] = meesvaSearchForm.getScaPassword();
                        arrPaymentDetails1[11] = meesvaSearchForm.getMeesevaFlag();
                        String[] arrAmount1 = new String[5];
                        arrAmount1[0] = "25";
                        arrAmount1[1] = "0";
                        arrAmount1[2] = "0";
                        arrAmount1[3] = "0";
                        arrAmount1[4] = "0";

                        String[] arrTransParams1 = new String[9];
                        arrTransParams1[0] = "Applicant Name";
                        arrTransParams1[1] = "DistrictId";
                        arrTransParams1[2] = "MandalId";
                        arrTransParams1[3] = "VillageId";
                        arrTransParams1[4] = "SLA";
                        arrTransParams1[5] = "DeliveryType";
                        arrTransParams1[6] = "Total Amount";
                        arrTransParams1[7] = "Status";
                        arrTransParams1[8] = "SLAEndDate";

                        String[] arrTransDetails1 = new String[9];
                        arrTransDetails1[0] = meesvaSearchForm.getFirstname();
                        arrTransDetails1[1] = meesvaSearchForm.getDistrict_id();
                        arrTransDetails1[2] = meesvaSearchForm.getMandal_id();
                        arrTransDetails1[3] = meesvaSearchForm.getVillage_id();
                        arrTransDetails1[4] = "0";
                        arrTransDetails1[5] = "Manual";
                        arrTransDetails1[6] = "25";
                        arrTransDetails1[7] = "04";
                        arrTransDetails1[8] = "";
////
                       GetPaymentTransIdResponseGetPaymentTransIdResult transResp = proxy.getPaymentTransId(
                               arrPaymentDetails1, arrAmount1, arrTransParams1,
                               arrTransDetails1, "MEESEVA", "MEESEVA", checksum1);

                        MessageElement[] transElements = transResp.get_any();
                        Iterator transIt = transElements[0].getChildElements();
                        String transResult = "";

                        while (transIt.hasNext()) {
                            MessageElement tranEle = (MessageElement) transIt.next();
                            ;
                            transResult += tranEle.getValue() + "#";

                       }

                        String[] transRespDtls = transResult.split("#");
                       if (transRespDtls[1].toString().equals("0")) {
                            transId = transRespDtls[2];
                        }
                        //transId = "12345";
                        if (transId != null && !transId.equalsIgnoreCase("")) {
                            updateMeesevaId = meesvaSearchService.updateSearchMEESEVATransctionId(ds, meesvaSearchForm.getApplicationNo(), transId);
                        } else {
                            //balue start
                            meesvaSearchForm.setSystemIp(Systemip);
                            int failtrans = 0;

                            failtrans = detailsService.submitMeesevaFialTransctionDetails(
                                    ds,
                                    meesvaSearchForm.getApplicationNo(),
                                    meesvaSearchForm.getUniqueNo(),
                                    meesvaSearchForm.getSCAUserId(),
                                    meesvaSearchForm.getLoginId(),
                                    meesvaSearchForm.getChannelId(),
                                    meesvaSearchForm.getRequestId(),
                                    meesvaSearchForm.getServiceid(),
                                    //grievancesRequestDetailsDAO.SADAREMTransctionId(ds, meesvaSearchForm.getApplicationNo()),
                                    meesvaSearchForm.getSadaremId(),
                                    meesvaSearchForm.getScaPassword(),
                                    meesvaSearchForm.getMeesevaFlag(),
                                    meesvaSearchForm.getFirstname(),
                                    meesvaSearchForm.getDistrict_id(),
                                    meesvaSearchForm.getMandal_id(),
                                    meesvaSearchForm.getVillage_id(),
                                    meesvaSearchForm.getSystemIp());

                            //

                            //balu end
                        }


                        //meesevaSearchDTO = meesvaSearchService.getRequestDetails(ds, meesvaSearchForm.getApplicationNo());

                        meesevaSearchDTO = meesvaSearchService.getSerachAcknowledgementDetails(ds, meesvaSearchForm);
                        if (meesevaSearchDTO != null) {
                            request.setAttribute("applicationNo", meesvaSearchForm.getApplicationNo());
                            request.setAttribute("surName", meesevaSearchDTO.getSurname());
                            request.setAttribute("name", meesevaSearchDTO.getFirstname());
                            request.setAttribute("age", meesevaSearchDTO.getAge());
                            request.setAttribute("houseNo", meesevaSearchDTO.getHouseno());
                            request.setAttribute("districtName", meesevaSearchDTO.getDistrictName());
                            request.setAttribute("mandalName", meesevaSearchDTO.getMandalName());
                            request.setAttribute("villageName", meesevaSearchDTO.getVillageName());
                            request.setAttribute("habitationName", meesevaSearchDTO.getHabitationName());
                            request.setAttribute("panchayatName", meesevaSearchDTO.getPanchayatName());
                            request.setAttribute("created_date", meesevaSearchDTO.getCreated_date());
                            request.setAttribute("habCode", meesevaSearchDTO.getHabCode());
                            if (meesevaSearchDTO.getMeesevaId() != null && !meesevaSearchDTO.getMeesevaId().equalsIgnoreCase("")) {
                                request.setAttribute("meesevaId", meesevaSearchDTO.getMeesevaId());
                            } else {
                                request.setAttribute("meesevaId", "-");
                            }
                            request.setAttribute("channelId", meesvaSearchForm.getChannelId());

                        }
                        request.setAttribute("uniqueNo", meesvaSearchForm.getUniqueNo());
                        request.setAttribute("scaUserId", meesvaSearchForm.getSCAUserId());
                        request.setAttribute("userId", meesvaSearchForm.getLoginId());
                        request.setAttribute("operatorId", meesvaSearchForm.getChannelId());
                        request.setAttribute("checkSum", meesvaSearchForm.getCheckSum());
                        request.setAttribute("requestId", meesvaSearchForm.getRequestId());
                        request.setAttribute("serviceid", meesvaSearchForm.getServiceid());
                        request.setAttribute("scaPassword", meesvaSearchForm.getScaPassword());
                        request.setAttribute("applicationNo", meesvaSearchForm.getApplicationNo());
                        request.setAttribute("meesevaflag", meesvaSearchForm.getMeesevaFlag());

                        request.setAttribute("firstName", meesvaSearchForm.getFirstname());
                        request.setAttribute("districtId", meesvaSearchForm.getDistrict_id());
                        request.setAttribute("mandalId", meesvaSearchForm.getMandal_id());
                        request.setAttribute("villageId", meesvaSearchForm.getVillage_id());
                        request.setAttribute("phoneNo", meesvaSearchForm.getPhoneno());
                        //request.setAttribute("phoneNo", meesvaSearchForm.getPhoneno());


                        target = "acknowledgment";

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    target = "exception";

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);

    }

    ///View Certificate Downlaod
    public ActionForward indViewCertificate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "acknowledgment";
        String districtName = null;
        String mandalName = null;
        String flag = null;
        String fileCertificatePath = "";
        String personCode = "";
        MeesvaSearchService meesvaSearchService = MeesvaSearchServiceFactory.getMeesvaSearchServiceImpl();
        MeesvaSearchForm meesvaSearchForm = (MeesvaSearchForm) form;
        DataSource ds = null;
        String certificateType = null;

        try {


            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("districtName") != null) {
                districtName = request.getParameter("districtName");
            }

            if (request.getParameter("mandalName") != null) {
                mandalName = request.getParameter("mandalName");
            }
            if (request.getParameter("personCode") != null) {
                personCode = request.getParameter("personCode");
                meesvaSearchForm.setSadaremId(personCode);
            }

            if (request.getParameter("uniqueNo") != null) {
                meesvaSearchForm.setUniqueNo(request.getParameter("uniqueNo"));

            }

            if (request.getParameter("scaUserId") != null) {
                meesvaSearchForm.setSCAUserId(request.getParameter("scaUserId"));

            }
            if (request.getParameter("userId") != null) {
                meesvaSearchForm.setLoginId(request.getParameter("userId"));

            }
            if (request.getParameter("operatorId") != null) {

                meesvaSearchForm.setChannelId(request.getParameter("operatorId"));
                //request.setAttribute("channelId", meesvaSearchForm.getChannelId());

            }
            if (request.getParameter("checkSum") != null) {
                meesvaSearchForm.setCheckSum(request.getParameter("checkSum"));
            }
            if (request.getParameter("requestId") != null) {
                meesvaSearchForm.setRequestId(request.getParameter("requestId"));

            }
            if (request.getParameter("serviceid") != null) {
                meesvaSearchForm.setServiceid(request.getParameter("serviceid"));
            }
            if (request.getParameter("scaPassword") != null) {
                meesvaSearchForm.setScaPassword(request.getParameter("scaPassword"));
            }
            if (request.getParameter("applicationNo") != null) {
                meesvaSearchForm.setApplicationNo(request.getParameter("applicationNo"));
                request.setAttribute("applicationNo", meesvaSearchForm.getApplicationNo());

            }
            if (request.getParameter("meesevaflag") != null) {
                meesvaSearchForm.setMeesevaFlag(request.getParameter("meesevaflag"));
            }
            if (request.getParameter("encryptedString") != null) {
                meesvaSearchForm.setEncryptedString(request.getParameter("encryptedString"));
            }
            if (request.getParameter("villageId") != null) {
                meesvaSearchForm.setVillage_id(request.getParameter("villageId"));
            }

            if (request.getParameter("mandalId") != null) {
                meesvaSearchForm.setMandal_id(request.getParameter("mandalId"));
            }

            if (request.getParameter("districtDataId") != null) {
                meesvaSearchForm.setDistrict_id(request.getParameter("districtDataId"));
            }

            if (request.getParameter("firstName") != null) {
                meesvaSearchForm.setFirstname(request.getParameter("firstName"));
            }

            if (request.getParameter("mandalId") != null) {
                meesvaSearchForm.setMandal_id(request.getParameter("mandalId"));
            }
            if (request.getParameter("villageId") != null) {
                meesvaSearchForm.setVillage_id(request.getParameter("villageId"));
            }
            if (request.getParameter("habCode") != null) {
                meesvaSearchForm.setHabCode(request.getParameter("habCode"));
            }
            if (request.getParameter("phoneNo") != null) {
                meesvaSearchForm.setMobile(request.getParameter("phoneNo"));
            }





            if (request.getParameter("flag") != null) {
                flag = request.getParameter("flag");
            }
            if (flag.equalsIgnoreCase("1")) {
                fileCertificatePath = CommonConstants.SCANCERTIFICATES + "//SadaremDoc//" + personCode + "//"  + "//Certificate" + "\\";
                certificateType = "Certificate";
            } else {
                fileCertificatePath = CommonConstants.SCANCERTIFICATES + "//SadaremDoc//" + personCode + "//"  + "//Idcard" + "\\";
                certificateType = "Idcard";
            }



            meesvaSearchForm.setCertificateType(certificateType);
            meesvaSearchForm.setSystemIp(request.getRemoteAddr());
            meesvaSearchForm.setMeesevaFlag("");
            this.viewCertificate(fileCertificatePath, certificateType, request, response);
            meesvaSearchService.getPrintMeesevaSearchDetails(ds, meesvaSearchForm);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return mapping.findForward(target);

    }

    private boolean compareCheckSum(String scaUserId, String scaPassword, String centerId, String operatorId, String uniqueNo, String Checksum) {
        //string checkSum = "";
        try {
            String compareChecksum = Checksum.substring(1, Checksum.length() - 1);
            String str1 = centerId + uniqueNo;
            String str2 = operatorId;
            String l3 = (str1 + scaUserId) + (str2 + scaPassword);
            int n1 = str1.length();
            int n2 = str2.length();
            String l1 = str1.substring(0, n1 - 3);
            String l2 = str2.substring(0, n2 - 2);

            if (compareChecksum.equalsIgnoreCase(l3 + l2 + uniqueNo + l1)) {
                return true;
            } else {
                return false;
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    private String GenerateCheckSum(String scaUserId, String scaPassword, String centerId, String operatorId, String uniqueNo) {
        String checkSum = "";
        try {

            String str1 = centerId + uniqueNo;
            String str2 = operatorId;
            String l3 = (str1 + scaUserId) + (str2 + scaPassword);
            int n1 = str1.length();
            int n2 = str2.length();
            String l1 = str1.substring(0, n1 - 3);
            String l2 = str2.substring(0, n2 - 2);
            Random random = new Random();
            int intl4 = random.nextInt(9);
            String l4 = Integer.toString(intl4);
            checkSum = l4 + l3 + l2 + uniqueNo + l1 + l4;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return checkSum;
    }

    public static void viewCertificate(String fileName, String fileType, HttpServletRequest request, HttpServletResponse response) {

        try {
            fileName = fileName + fileType;
            File folder = new File(fileName);

            File[] listOfFiles = folder.listFiles();

            int size = 0;
            if (listOfFiles != null && !listOfFiles.equals("")) {
                size = listOfFiles.length + 1;
            } else {
                size = 0 + 1;
            }
            CommonDetails commonDetails = new CommonDetails();
            String contentType = null;
            contentType = "txt";

            BufferedInputStream in = null;
            File fileDetailsData = new File(folder + ".pdf");


            FileInputStream fin = new FileInputStream(fileDetailsData);

            in = new BufferedInputStream(fin);

            ServletOutputStream out = response.getOutputStream();
            byte[] buffer = new byte[4 * 1024];

            int data = 0;
            while ((data = in.read(buffer)) != -1) {
                out.write(buffer, 0, data);
            }
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ActionForward getPrintCertificate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "printCertificate";
        MeesvaSearchService meesvaSearchService = MeesvaSearchServiceFactory.getMeesvaSearchServiceImpl();
        MeesvaSearchForm meesvaSearchForm = (MeesvaSearchForm) form;
        DataSource ds = null;
        String enc = null;
        String decryptedValue = null;
        String userId = null;
        String operatorId = null;
        String checkSum = null;
        String requestId = null;
        String serviceid = null;
        String scaPassword = null;
        String applicationNo = null;
        String meesevaflag = null;
        String uniqueNo = null;
        String scaUserId = null;
        String sadaremId = null;
        ArrayList totalPercentage = null;
        String totalDisability = null;
        String districtName = null;
        String mandalName = null;
        String personCode = null;
        String habCode = null;
        String mandalId = null;
        String villageId = null;
        String districtId = null;
        String name = null;
        String phoneNo = null;




        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
//            if (request.getParameter("sadaremId") != null) {
            //sadaremId = request.getParameter("sadaremId");
            // sadaremId = "16209930050000155";

            // }

            if (request.getParameter("enc") != null && !request.getParameter("enc").equalsIgnoreCase("")) {
                enc = request.getParameter("enc");
                meesvaSearchForm.setEncryptedString(enc);
            }

            byte[] myIV = {(byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57};
            byte[] tdesKeyData = {
                (byte) 0xA2, (byte) 0x15, (byte) 0x37, (byte) 0x07, (byte) 0xCB, (byte) 0x62,
                (byte) 0xC1, (byte) 0xD3, (byte) 0xF8, (byte) 0xF1, (byte) 0x97, (byte) 0xDF,
                (byte) 0xD0, (byte) 0x13, (byte) 0x4F, (byte) 0x79, (byte) 0x01, (byte) 0x67,
                (byte) 0x7A, (byte) 0x85, (byte) 0x94, (byte) 0x16, (byte) 0x31, (byte) 0x92};


            //Identity.instance().unAuthenticate();
            TripleDESEncryption objText = new TripleDESEncryption(tdesKeyData, myIV);
            decryptedValue = objText.decryptText(enc);
            if (decryptedValue != null) {
                String values[] = decryptedValue.split("&");
                //String values[] = enc.split(",");

                for (int i = 0; i < values.length; i++) {
                    String paramValues[] = values[i].split("=");
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("P1")) {
                        uniqueNo = paramValues[1];
                        meesvaSearchForm.setUniqueNo(uniqueNo);
                        request.setAttribute("uniqueNo", uniqueNo);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p2")) {
                        scaUserId = paramValues[1];
                        meesvaSearchForm.setSCAUserId(scaUserId); //
                        request.setAttribute("scaUserId", scaUserId);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p3")) {
                        userId = paramValues[1];
                        meesvaSearchForm.setLoginId(userId);
                        request.setAttribute("userId", userId);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p4")) {
                        operatorId = paramValues[1];
                        meesvaSearchForm.setChannelId(operatorId);
                        request.setAttribute("operatorId", operatorId);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p5")) {
                        checkSum = paramValues[1];
                        meesvaSearchForm.setCheckSum(checkSum);
                        request.setAttribute("checkSum", checkSum);

                    }

                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p6")) {
                        serviceid = paramValues[1];
                        meesvaSearchForm.setServiceid(serviceid);
                        request.setAttribute("serviceid", serviceid);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p7")) {
                        scaPassword = paramValues[1];
                        meesvaSearchForm.setScaPassword(scaPassword);
                        request.setAttribute("scaPassword", scaPassword);

                    }

                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p8")) {
                        sadaremId = paramValues[1];

                        meesvaSearchForm.setSadaremId(sadaremId);
                        request.setAttribute("sadaremId", sadaremId);

                    }
                    if (paramValues != null && paramValues.length == 2 && paramValues[0].equalsIgnoreCase("p9")) {
                        meesevaflag = paramValues[1];
                        meesvaSearchForm.setMeesevaFlag(meesevaflag);
                        request.setAttribute("meesevaflag", meesevaflag);

                    }

                }
            }
            
            if (compareCheckSum(scaUserId, scaPassword, scaUserId, userId, uniqueNo, checkSum)) {

                totalPercentage = meesvaSearchService.getSADAREMIDPercentageDetails(ds, meesvaSearchForm, sadaremId);
                Map map = null;
                if (totalPercentage.size() > 0) {
                    Iterator it = totalPercentage.iterator();
                    while (it.hasNext()) {
                        map = (Map) it.next();
                        totalDisability = map.get("totalDisability").toString();
                        districtName = map.get("districtName").toString();
                        mandalName = map.get("mandalName").toString();
                        personCode = map.get("personCode").toString();
                        habCode = map.get("habCode").toString();
                        districtId = map.get("districtId").toString();
                        mandalId = map.get("mandalId").toString();
                        villageId = map.get("villageId").toString();
                        name = map.get("name").toString();
                        phoneNo = map.get("phoneNo").toString();

                    }
                }
                request.setAttribute("totalDisability", totalDisability);
                request.setAttribute("districtName", districtName);
                request.setAttribute("mandalName", mandalName);
                request.setAttribute("personCode", personCode);
                request.setAttribute("habCode", habCode);
                request.setAttribute("districtId", districtId);
                request.setAttribute("mandalId", mandalId);
                request.setAttribute("villageId", villageId);
                request.setAttribute("name", name);
                request.setAttribute("phoneNo", phoneNo);
                target = "printCertificate";
            } else {
                target = "exception";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return mapping.findForward(target);

    }

    public ActionForward getPrintViewCertificate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "printCertificate";
        String districtName = null;
        String mandalName = null;
        String flag = null;
        String fileCertificatePath = "";
        String personCode = "";
        String habCode = null;
        String certificateType = "";
        MeesvaSearchService meesvaSearchService = MeesvaSearchServiceFactory.getMeesvaSearchServiceImpl();
        MeesvaSearchForm meesvaSearchForm = (MeesvaSearchForm) form;

        int success = 0;
        DataSource ds = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            if (request.getParameter("districtName") != null) {
                districtName = request.getParameter("districtName");
            }

            if (request.getParameter("mandalName") != null) {
                mandalName = request.getParameter("mandalName");
            }
            if (request.getParameter("personCode") != null) {
                personCode = request.getParameter("personCode");
                meesvaSearchForm.setSadaremId(personCode);
            }
            if (request.getParameter("habCode") != null) {
                habCode = request.getParameter("habCode");
                meesvaSearchForm.setHabCode(habCode);
            }

            if (request.getParameter("districtId") != null) {
                meesvaSearchForm.setDistrict_id(request.getParameter("districtId"));
            }
            if (request.getParameter("mandalId") != null) {
                meesvaSearchForm.setMandal_id(request.getParameter("mandalId"));
            }

            if (request.getParameter("villageId") != null) {
                meesvaSearchForm.setVillage_id(request.getParameter("villageId"));
            }

            if (request.getParameter("uniqueNo") != null) {
                meesvaSearchForm.setUniqueNo(request.getParameter("uniqueNo"));
            }
            if (request.getParameter("scaUserId") != null) {
                meesvaSearchForm.setSCAUserId(request.getParameter("scaUserId"));
            }
            if (request.getParameter("userId") != null) {
                meesvaSearchForm.setLoginId(request.getParameter("userId"));
            }
            if (request.getParameter("operatorId") != null) {
                meesvaSearchForm.setChannelId(request.getParameter("operatorId"));
            }
            if (request.getParameter("checkSum") != null) {
                meesvaSearchForm.setCheckSum(request.getParameter("checkSum"));
            }
            if (request.getParameter("serviceid") != null) {
                meesvaSearchForm.setServiceid(request.getParameter("serviceid"));
            }
            if (request.getParameter("scaPassword") != null) {
                meesvaSearchForm.setScaPassword(request.getParameter("scaPassword"));
            }
            if (request.getParameter("sadaremId") != null) {
                meesvaSearchForm.setSadaremId(request.getParameter("sadaremId"));
            }

            if (request.getParameter("meesevaflag") != null) {
                meesvaSearchForm.setMeesevaFlag(request.getParameter("meesevaflag"));
            }

            if (request.getParameter("districtId") != null) {
                meesvaSearchForm.setDistrict_id(request.getParameter("districtId"));
            }

            if (request.getParameter("mandalId") != null) {
                meesvaSearchForm.setMandal_id(request.getParameter("mandalId"));
            }

            if (request.getParameter("villageId") != null) {
                meesvaSearchForm.setVillage_id(request.getParameter("villageId"));
            }


            if (request.getParameter("name") != null) {
                meesvaSearchForm.setFirstname(request.getParameter("name"));
            }

            if (request.getParameter("phoneNo") != null) {
                meesvaSearchForm.setMobile(request.getParameter("phoneNo"));
            }

            if (request.getParameter("flag") != null) {
                flag = request.getParameter("flag");
            }
            if (flag.equalsIgnoreCase("1")) {
                fileCertificatePath = CommonConstants.SCANCERTIFICATES + "//" + personCode  + "//Certificate" + "\\";
                certificateType = "Certificate";
            } else {
                fileCertificatePath = CommonConstants.SCANCERTIFICATES + "//" + personCode  + "//Idcard" + "\\";
                certificateType = "Idcard";

            }
            meesvaSearchForm.setCertificateType(certificateType);
            this.viewCertificate(fileCertificatePath, certificateType, request, response);
            meesvaSearchForm.setSystemIp(request.getRemoteAddr());
            success = meesvaSearchService.getPrintMeesevaSearchDetails(ds, meesvaSearchForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);

    }
}
