/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

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
import org.bf.disability.common.CommonService;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.CertificatesUploadDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.GrievancesRequestDetailsDAO;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.PartAForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.service.PartAService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.servicefactory.PartAServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import Aadhar.AadhaarUtility;

import com.ecentric.servicemodels.AadhaarProfile;
import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 310926
 */
public class AadharCardInsertAction extends BaseDispatchAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
			        String target = "invalid";
			        ActionMessages actionMessages = null;
			        try {
			
			
			            HttpSession session = request.getSession(true);
			            if (request.getParameter("PartArestrict") != null) {
			                session.setAttribute("PartArestrict", request.getParameter("PartArestrict"));
			            }
			
			            target = "aadharDetails";
			
			        }
			        catch (Exception sADAREMException) 
			        {
			            target = "invalid";
			            actionMessages = new ActionMessages();
			            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));

			        }
        return mapping.findForward(target);
    }

    public ActionForward getExistingData(ActionMapping mapping, ActionForm form,  HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        ActionMessages actionMessages = null;
        String districtId = null;
        int aadharDistrictId = 0;
        //String aadharCardNo = null;
        String target = "invalid";
        //PartADTO partADTO = null;
        DataSource ds = null;
        ArrayList existPensionNo = new ArrayList();
        ArrayList existSADAREMID = new ArrayList();
        String districtName = null;
        String mandalName = null;
        //ArrayList districtList = new ArrayList();
        ArrayList mandalList = new ArrayList();
        ArrayList panchayatList = new ArrayList();
        //ArrayList villageList = new ArrayList();
        //ArrayList habitationList = new ArrayList();
        String xmlString = null;
        String validxmlString = null;
        // System.out.println("aadhar");
        XMLDecoder validxmlDecoder = null;
        XMLDecoder xmlDecoder = null;
        AadhaarProfile aadharProfile = null;
        PartAForm partAForm = (PartAForm) form;
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds))
            {
                ds = JNDIDataSource.getConnection();
            }


            PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
            GrievancesRequestDetailsDAO grievancesRequestDetailsDAO = new GrievancesRequestDetailsDAO();
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            HttpSession session = request.getSession(true);
            if (request.getParameter("PartArestrict") != null) {
                session.setAttribute("PartArestrict", request.getParameter("PartArestrict"));
            }
            
            if (partAForm.getAadharCardNo() != null) 
            {
                districtId = (String) session.getAttribute("districtId");
                existSADAREMID = partAService.checkSADAREMAssessmentData(ds, partAForm.getAadharCardNo());

                if (existSADAREMID != null && existSADAREMID.size() > 0) 
                {
                    HashMap tempList = new HashMap();
                    tempList = (HashMap) existSADAREMID.get(0);
                    request.setAttribute("errorMsg", "Assessment Already done for the given AadharCard Number with SADAREM ID " + tempList.get("sadaremid").toString() + " in " + tempList.get("districtname").toString() + " district");
                } 
                else 
                {
//                    existPensionNo = partAService.checkPensionData(ds, partAForm.getAadharCardNo());
//
//                    if (existPensionNo != null && existPensionNo.size() > 0) {
//                        HashMap tempList = new HashMap();
//                        tempList = (HashMap) existPensionNo.get(0);
//                        request.setAttribute("errorMsg", "For given AadharCard No already Pension No " + tempList.get("pensionid").toString() + " in " + tempList.get("districtname").toString() + " district exists. Do assessment through Pensioner");
//                    } else {

                    try {
                        //aadharProfile = AadharDetails.getAadharDetails(partAForm.getAadharCardNo().toString());
                    	
                    	 aadharProfile = AadhaarUtility.getAadhaarProfileByUID(partAForm.getAadharCardNo().toString());
                    	
                    	
                    	 /*aadharProfile = new AadhaarProfile() ;
                    	aadharProfile.setBase64file(CommonUtility.checkNullObj(("BASE64FILE")));
    					aadharProfile.setBuildingName(CommonUtility.checkNullObj(("BUILDINGNAME")));
    					aadharProfile.setCareof(CommonUtility.checkNullObj(("CAREOF")));
    					aadharProfile.setDistrict(CommonUtility.checkNullObj(("14")));
    					aadharProfile.setDistrict_name(CommonUtility.checkNullObj(("DISTRICT_NAME")));
    					aadharProfile.setDob(CommonUtility.checkNullObj(("DOB")));
    					aadharProfile.setEid(CommonUtility.checkNullObj(("EID")));
    					aadharProfile.setGender(CommonUtility.checkNullObj(("GENDER")));
    					aadharProfile.setMandal(CommonUtility.checkNullObj(("01")));
    					aadharProfile.setMandal_name(CommonUtility.checkNullObj(("MANDAL_NAME")));
    					aadharProfile.setName(CommonUtility.checkNullObj(("NAME")));
    					aadharProfile.setPhoneNo(CommonUtility.checkNullObj(("PHONENO")));
    					aadharProfile.setPincode(CommonUtility.checkNullObj(("PINCODE")));
    					aadharProfile.setStatecode(CommonUtility.checkNullObj(("STATECODE")));
    					aadharProfile.setStatus(CommonUtility.checkNullObj(("100")));
    					aadharProfile.setStreet(CommonUtility.checkNullObj(("STREET")));
    					aadharProfile.setUid(CommonUtility.checkNullObj(("444526264054")));
    					aadharProfile.setVillage(CommonUtility.checkNullObj(("001")));
    					aadharProfile.setVillage_name(CommonUtility.checkNullObj(("VILLAGE_NAME")));
    					aadharProfile.setSrdhwstxn(CommonUtility.checkNullObj(("SRDHWSTXN")));*/
                        
                        
                    } catch (Exception e)
                    {
                        request.setAttribute("errorMsg", "Aadhar Webservice is down. Please try after some time");
                        target = "invalid";
                    }
                    
                    if (aadharProfile != null) 
                    {
                        //validation Aadhar Number End

                        //System.out.println("Status: " + aadharProfile.getStatus());
                        String invalidFlag = aadharProfile.getStatus();

                        if (invalidFlag != null && (invalidFlag.equalsIgnoreCase("100") || invalidFlag.equalsIgnoreCase("101")))
                        {
                            if (aadharProfile.getDistrict() != null)
                            {
                              
                                if (aadharProfile.getStatecode() != null && (aadharProfile.getStatecode().equals("2") || aadharProfile.getStatecode().equals("101")))
                                {
                                	  aadharDistrictId = Integer.parseInt(aadharProfile.getDistrict());
                                    String district = aadharProfile.getDistrict();
                                    if (district != null && district.length() == 1) 
                                    {
                                        district = "0" + district;
                                    }
                                    partAForm.setDistrict_id(district);
                                    if ((aadharDistrictId > 13 && aadharDistrictId <= 23)) 
                                    {
//                                            if ((districtId.equalsIgnoreCase("15") || districtId.equalsIgnoreCase("16")) && (district.equalsIgnoreCase("15") || district.equalsIgnoreCase("16"))) {
                                        district = districtId;
//                    String district = "14";
//                                            }
                                        partAForm.setDistrict_id(district);
//                                            if (districtId.equalsIgnoreCase(district)) {
                                        //if (true) {
                                        districtName = grievancesRequestDetailsDAO.getDistrictName(ds, district);
                                        partAForm.setDistrict(districtName);
                                        String name = aadharProfile.getName();
                                        String a[] = name.split(" ", 2);
                                        if (a.length >= 2) 
                                        {
                                            for (int j = 0; j < a.length; j++) 
                                            {
                                                partAForm.setSurname(a[0]);
                                                partAForm.setFirstname(a[1]);
                                                break;
                                            }
                                        } 
                                        else 
                                        {
                                            partAForm.setFirstname(name);
                                            // partAForm.setFirstname("sravani");
                                        }

                                        request.setAttribute("surname", partAForm.getSurname());
                                        
                                        if (!aadharProfile.getCareof().equalsIgnoreCase("101"))
                                        {
                                            partAForm.setGsurname(aadharProfile.getCareof());
                                        }
                                        
                                        if (aadharProfile.getBase64file() != null  && !aadharProfile.getBase64file().equalsIgnoreCase("101")) 
                                        {
                                            partAForm.setBase64Photo(aadharProfile.getBase64file());
//                    partAForm.setBase64Photo("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");
//
                                        } else if (aadharProfile.getBase64file() != null && aadharProfile.getBase64file().equalsIgnoreCase("101")) 
                                        {
                                            partAForm.setBase64Photo("-");
                                        }
                                        partAForm.setMandal_id(aadharProfile.getMandal());
                                        partAForm.setVillage_id(aadharProfile.getVillage());
                                        mandalList = territoryservice.getMandals(ds, partAForm.getDistrict_id());
                                        partAForm.setMandallist(mandalList);
                                        panchayatList = territoryservice.getPanchayats(ds, partAForm.getDistrict_id(), partAForm.getMandal_id());
                                        partAForm.setPanchayatlist(panchayatList);
                                        String gender = aadharProfile.getGender();

                                        if (gender != null && gender.equalsIgnoreCase("M")) 
                                        {
                                            partAForm.setGender("1");
                                        } 
                                        else
                                        {
                                            partAForm.setGender("2");
                                        }
                                        partAForm.setDistrict_id(partAForm.getDistrict_id());
                                        partAForm.setMandal_id(partAForm.getMandal_id());
                                        target = "aadharDetails";
//                                            } else {
//                                                request.setAttribute("errorMsg", "This Aadhaar Card No " + partAForm.getAadharCardNo() + " not belongs to this district.");
//                                                partAForm.setAadharCardNo("");
//                                            }
                                    } 
                                    else if (aadharDistrictId <= 13)
                                    {
                                        request.setAttribute("errorMsg", "This Aadhar Number No " + partAForm.getAadharCardNo() + " is belongs to Andhra Pradesh State.");
                                    } 
                                    else if (aadharDistrictId == 101) 
                                    {
                                        partAForm.setBase64Photo("-");
                                        partAForm.setDistrict(grievancesRequestDetailsDAO.getDistrictName(ds, districtId));
                                        partAForm.setDistrict_id(districtId);
                                        mandalList = territoryservice.getMandals(ds, partAForm.getDistrict_id());
                                        partAForm.setMandallist(mandalList);
                                        panchayatList = territoryservice.getPanchayats(ds, partAForm.getDistrict_id(), partAForm.getMandal_id());
                                        partAForm.setPanchayatlist(panchayatList);
                                        request.setAttribute("aadharDetailsFlag", "aadharDetailsFlag");
                                        target = "aadharDetails";
                                    }
                                } 
                                else if (aadharProfile.getStatecode() != null && aadharProfile.getStatecode().equals("1")) 
                                {
                                    request.setAttribute("errorMsg", "This Aadhar Number No " + partAForm.getAadharCardNo() + " is belongs to Andhra Pradesh State.");
                                } 
                                else
                                {
                                    partAForm.setBase64Photo("-");
                                    partAForm.setDistrict(grievancesRequestDetailsDAO.getDistrictName(ds, districtId));
                                    partAForm.setDistrict_id(districtId);
                                    mandalList = territoryservice.getMandals(ds, partAForm.getDistrict_id());
                                    partAForm.setMandallist(mandalList);
                                    panchayatList = territoryservice.getPanchayats(ds, partAForm.getDistrict_id(), partAForm.getMandal_id());
                                    partAForm.setPanchayatlist(panchayatList);
                                    request.setAttribute("aadharDetailsFlag", "aadharDetailsFlag");
                                    target = "aadharDetails";
                                }

                            }
                        } 
                        else if (invalidFlag != null && invalidFlag.substring(0, 3).equalsIgnoreCase("121")) 
                        {
                            request.setAttribute("errorMsg", "Given Aadhar Number is Cancelled/Rejected UID -  " + partAForm.getAadharCardNo() + " due to reason '" + invalidFlag.substring(3, invalidFlag.length()) + "'");
                            partAForm.setAadharCardNo("");
                        } 
                        else if (invalidFlag != null && invalidFlag.equalsIgnoreCase("111"))
                        {
                            request.setAttribute("errorMsg", "Unknown error oocured from AADHAR Web Service.");
                            partAForm.setAadharCardNo("");
                            target = "invalid";
                        }
                    } 
                    else
                    {
                        request.setAttribute("errorMsg", "Aadhar Webservice is down. Please try after some time");
                    }
                }
//                }
            }
            else
            {
                request.setAttribute("errorMsg", "Please Enter Aadhar Number...");

            }


        } catch (SADAREMDBException sADAREMException) 
        {
            request.setAttribute("errorMsg", "No Data available for the given UID - " + partAForm.getAadharCardNo() + ".");
            target = "invalid";
            int exResult = ExceptionDAO.saveException(ds, sADAREMException.toString(), "getExistingData", "AadharCardInsertAction", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sADAREMException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCampBasedOnLoginDetails");

//            actionMessages = new ActionMessages();
//            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
//            saveErrors(request, actionMessages);
        } 
        catch (Exception sADAREMException) 
        {
            sADAREMException.printStackTrace();
            request.setAttribute("errorMsg", "No Data available for the given UID - " + partAForm.getAadharCardNo() + ".");
            target = "invalid";
            if (validxmlDecoder != null) {
                validxmlDecoder.close();
            }
            if (xmlDecoder != null) {
                xmlDecoder.close();
            }
            int exResult = ExceptionDAO.saveException(ds, sADAREMException.toString(), "getExistingData", "AadharCardInsertAction", "code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sADAREMException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "IssueRaiseApprovalDAO", "getCampBasedOnLoginDetails");

//            sADAREMException.printStackTrace();
//            actionMessages = new ActionMessages();
//            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
//            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public ActionForward getSADAREMAssessedList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException 
    {

        ActionMessages actionMessages = null;
        //String districtId = null;
        //String aadharCardNo = null;
        String target = "invalid";
        PartADTO partADTO = new PartADTO();
        DataSource ds = null;
        ArrayList sadaremAssessedList = new ArrayList();
        ArrayList mandalList = new ArrayList();
        ArrayList panchayatList = new ArrayList();
        ArrayList villageList = new ArrayList();
        ArrayList habitationList = new ArrayList();
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            PartAForm partAForm = (PartAForm) form;
            PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
            //GrievancesRequestDetailsDAO grievancesRequestDetailsDAO = new GrievancesRequestDetailsDAO();
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            //HttpSession session = request.getSession(true);
            sadaremAssessedList = partAService.checkSADAREMAssessmentData(ds, partAForm.getAadharCardNo());
            mandalList = territoryservice.getMandals(ds, partAForm.getDistrict_id());
            partAForm.setMandallist(mandalList);
            panchayatList = territoryservice.getPanchayats(ds, partAForm.getDistrict_id(), partAForm.getMandal_id());
            partAForm.setPanchayatlist(panchayatList);
            villageList = territoryservice.getVillages(ds, partAForm.getDistrict_id(), partAForm.getMandal_id(), partAForm.getPanchayat_id());
            partAForm.setVillagelist(villageList);
            habitationList = territoryservice.getHabitations(ds, partAForm.getDistrict_id(), partAForm.getMandal_id(), partAForm.getPanchayat_id(), partAForm.getVillage_id());
            partAForm.setHabitationlist(habitationList);
            BeanUtils.copyProperties(partADTO, partAForm);
            sadaremAssessedList = partAService.getSADAREMAssessedData(ds, partADTO);
            partAForm.setIsExist(null);
            if (sadaremAssessedList.size() > 0) {
                request.setAttribute("sadaremAssessedList", sadaremAssessedList);
            } else {
                request.setAttribute("nodata", "nodata");
                partAForm.setIsExist("No");
            }

            request.setAttribute("aadharCardNo", partAForm.getAadharCardNo());
            target = "aadharDetails";



        } catch (SADAREMDBException sADAREMException) {
            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public ActionForward taggedAadharCardNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException 
    {

        ActionMessages actionMessages = null;
        String target = "invalid";
        DataSource ds = null;
        int success = 0;

        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            PartAForm partAForm = (PartAForm) form;
            PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
            success = partAService.taggedAadharCardNo(ds, partAForm);
            if (success > 0) {
                request.setAttribute("errorMsg", "Successfully tagged SADAREM ID to AadharCard No.");
            } else {
                request.setAttribute("errorMsg", "Error Occured while tagging.");
            }

            target = "sadaremIdTagged";



        } catch (SADAREMDBException sADAREMException) {

            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {

            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public ActionForward partAForm(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException
    {

        ActionMessages actionMessages = null;
        String target = "invalid";
        DataSource ds = null;

        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        // Here Getting the personal details data from web service i.e slno and rationcard number


        ArrayList mandalList = new ArrayList();
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            PartAForm partAForm = (PartAForm) form;
            TerritoryDAO dAO = new TerritoryDAO();
            //PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
            PartADAO partADAO = new PartADAO();
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
            mandalList = functionalNeedService.getMandals(ds, partAForm.getDistrict_id(), "");
            partAForm.setMandallist(mandalList);
            ArrayList panchayatList = new ArrayList();
            panchayatList = dAO.getPanchayats(ds, partAForm.getDistrict_id(), partAForm.getMandal_id());
            partAForm.setPanchayatlist(panchayatList);
            ArrayList villageList = new ArrayList();
            villageList = dAO.getVillages(ds, partAForm.getDistrict_id(), partAForm.getMandal_id(), partAForm.getPanchayat_id());
            partAForm.setVillagelist(villageList);
            ArrayList habitationList = new ArrayList();
            habitationList = dAO.getHabitations(ds, partAForm.getDistrict_id(), partAForm.getMandal_id(), partAForm.getPanchayat_id(), partAForm.getVillage_id());
            partAForm.setHabitationlist(habitationList);
            request.setAttribute("districtId", partAForm.getDistrict_id());
            if (request.getParameter("noofrows") != null) {
                request.setAttribute("noOfRows", request.getParameter("noofrows").toString());
            }
            request.setAttribute("surname", partAForm.getSurname());
            request.setAttribute("relationname", partAForm.getGsurname());
            request.setAttribute("base64Photo", partAForm.getBase64Photo());
            target = "partAForm";



        } catch (SADAREMDBException sADAREMException) {

            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {

            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public ActionForward insertPersonalDetails(ActionMapping mapping, ActionForm form,  HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException 
    {

        ActionMessages actionMessages = null;
        String target = "success";
        DataSource ds = null;
        String personcodemax = null;
        int success = 0;

        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            PartAForm partAForm = (PartAForm) form;
            HttpSession session = request.getSession(true);

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
            //TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            //TerritoryDAO territoryDAO = new TerritoryDAO();
            //personcodemax = territoryDAO.getHabitationCode(district_id, mandal_id, panchayat_id, assembly_id, village_id, habitation_id, ds);
            //String reasonforstatus = (String) request.getParameter("reasonforstatus");
            String partACheckForDuplicatePersonCode = null;
            CertificatesUploadDAO certificatesUploadDAO = new CertificatesUploadDAO();
            String relation = null;
            //String status = null;
            //String personcode = null;
            String loginid = null;
            // String target = null;
            PartADTO partADTO = new PartADTO();
            partACheckForDuplicatePersonCode = partADAO.getPartACheckForDuplicate(ds, district_id, mandal_id, village_id, habitation_id, partAForm.getSurname(), partAForm.getFirstname(), partAForm.getLastname(), partAForm.getNoOfYears(), partAForm.getGender(), partAForm.getGsurname(), partAForm.getHouseno());
            if (partACheckForDuplicatePersonCode != null && !"null".equals(partACheckForDuplicatePersonCode)) {
                request.setAttribute("partACheckForDuplicatePersonCode", partACheckForDuplicatePersonCode);
                return mapping.findForward("duplicate");
            }

            //added by kavya

            if (partAForm.getRationCardNo() != null)
            {
                //AponlineWSClient wsClient = new AponlineWSClient();
                String message = null;
                // String rationcardtype = "";

                //rationcardtype = partAForm.getRationCardNo().substring(0, 3);
                message = partADAO.getRAPTAPRationCardSerialNumberStatus(ds, partAForm.getRationCardNo().toString(), partAForm.getRationCardSlno().toString(), district_id);
                // message = "SUCCESS";
                if (message != null && message.equalsIgnoreCase(CommonConstants.SUCCESS)) 
                {
                    message = partADAO.getAssesmentStatusRationcardAndSlnoStatus(ds, partAForm.getRationCardNo(), partAForm.getRationCardSlno(), partACheckForDuplicatePersonCode);

                }
                if (message != null && message.equalsIgnoreCase(CommonConstants.ERROR)) 
                {
                    request.setAttribute("MSG", "You Have Entered Invalid Rationcard serial number ");

                    return mapping.findForward("invalid");
                }
                else if (message != null && !message.equalsIgnoreCase(CommonConstants.SUCCESS))
                {
                    request.setAttribute("MSG", message);
                    return mapping.findForward("invalid");
                }
            }
            //ended by kavya
            //String day = request.getParameter("day");
            String Systemip = request.getRemoteAddr();
            relation = convertToString(partAForm.getRelation());
            partAForm.setRelationStr(relation);
            //personcode = (String) session.getAttribute("personcode");
            loginid = (String) session.getAttribute("loginid");
            int campId = 0;
            if (session.getAttribute("campId") != null) 
            {
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
            if (panchayat_id != null && !"".equals(panchayat_id))
            {
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
            String personCode = null;
            personCode = partADAO.checkaadharcardNumber(ds, partAForm.getAadharCardNo(), district_id);
            if (personCode == null || personCode.equalsIgnoreCase("")) 
            {
                personcodemax = partAService.insertPersonalDetailsForAadharNumber(partADTO, ds, request);
                session.setAttribute("personcode", personcodemax);
                if (personcodemax != null && !personcodemax.equals("Not Match"))
                {
                    success = partADAO.checkPersonalDetailsForAadharNumber(partADTO, ds, request, personcodemax);
                    request.setAttribute("base64Photo", partAForm.getBase64Photo());
                    request.setAttribute("district_name", (String) session.getAttribute("district_name"));

                    if (request.getAttribute("dvalues") != null) 
                    {
                        request.setAttribute("dvaluess", "<font color='red' size='2'>Select Disability Exceeded in Seleted Camp</font>");
                        partADAO.removeSadaremids(ds, personcodemax);
                        partAForm.setType_disability("");
                        request.setAttribute("dvaluess", "<font color='red' size='2'>Select Disability Exceeded in Seleted Camp</font>");
                        FunctionalNeedReportService functionalNeedService =
                                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
                        TerritoryDAO dAO = new TerritoryDAO();
                        ArrayList mandalList = new ArrayList();
                        String districtId = null;

                        if (partAForm.getDistrict_id() == null) 
                        {
                            districtId = request.getParameter("district");
                            request.setAttribute("districtId", districtId);
                        } 
                        else
                        {
                            districtId = partAForm.getDistrict_id();
                            request.setAttribute("districtId", partAForm.getDistrict_id());
                            ArrayList campList = new ArrayList();
                            campList = partADAO.getMultipleCampDetails(ds, districtId);
                            partAForm.setCampsList(campList);
                            request.setAttribute("campsList", campList);
                        }
                        mandalList = functionalNeedService.getMandals(ds, partAForm.getDistrict_id(), "");
                        partAForm.setMandallist(mandalList);

                        ArrayList panchayatList = new ArrayList();
                        panchayatList = dAO.getPanchayats(ds, partAForm.getDistrict_id(), partAForm.getMandal_id());
                        partAForm.setPanchayatlist(panchayatList);

                        ArrayList villageList = new ArrayList();
                        villageList = dAO.getVillages(ds, partAForm.getDistrict_id(), partAForm.getMandal_id(), partAForm.getPanchayat_id());
                        partAForm.setVillagelist(villageList);

                        ArrayList habitationList = new ArrayList();
                        habitationList = dAO.getHabitations(ds, partAForm.getDistrict_id(), partAForm.getMandal_id(), partAForm.getPanchayat_id(), partAForm.getVillage_id());
                        partAForm.setHabitationlist(habitationList);
                        request.setAttribute("districtId", partAForm.getDistrict_id());
                        return mapping.findForward("nocamp");
                    } 
                    else 
                    {
                        if (partAForm.getBase64Photo() != null && !partAForm.getBase64Photo().equalsIgnoreCase("-")) 
                        {
                            int i = uploadAadharPhoto(ds, personcodemax , request, partAForm.getBase64Photo());
                            if (i == 0) 
                            {
                                partAForm.setBase64Photo("-");
                            }
                        }
                        request.setAttribute("base64Photo", partAForm.getBase64Photo());
                        if (partADTO.getPersonstatus().equals("Eligible")) 
                        {
                            target = "finish";
                        } 
                        else 
                        {
                            target = "rejected";
                        }
                        String messages = null;
                        if (partADTO.getPhoneno() != null && partADTO.getPhoneno().length() > 0)
                        {
                            ArrayList dpmList = partADAO.getDpmuDetails(ds, district_id, partADTO.getPartaCampId(), personcodemax);
                            ArrayList campDetails = partADAO.getsmsCampDetials(ds, personcodemax, partADTO);
                            if (dpmList.size() > 0 && campDetails.size() > 0) 
                            {
                                messages = "Your Personal Details entered successfully. SADAREM ID is " + personcodemax + ".Attend Camp on " + campDetails.get(1) + ", At " + campDetails.get(0) + ".Contact To Camp Incharge (" + dpmList.get(1) + ").";

                            }
                            else if (campDetails.size() > 0) 
                            {
                                messages = "Your Personal Details entered successfully. SADAREM ID is " + personcodemax + ".Attend Camp on " + campDetails.get(1) + ", At " + campDetails.get(0) + ".";

                            } 
                            else 
                            {
                                messages = messages = "Your Personal Details entered successfully. SADAREM ID is " + personcodemax + ".";

                            }
//                            String result = null;
//                            try {
//                                result = com.tpSMS.TpSendSMS.sendSMS(partADTO.getPhoneno(), messages);
//                                if (result.equals("OK")) {
//                                    certificatesUploadDAO.insertSmsLogDetails(ds, messages, partADTO.getPhoneno(), "Success", CommonConstants.PARTA, partAForm.getLoginid(),request.getRemoteAddr());
//                                } else {
//                                    certificatesUploadDAO.insertSmsLogDetails(ds, messages, partADTO.getPhoneno(), "Fail", CommonConstants.PARTA, partAForm.getLoginid(),request.getRemoteAddr());
//                                }
//                            } catch (Exception e) {
//                                certificatesUploadDAO.insertSmsLogDetails(ds, messages, partADTO.getPhoneno(), "Exception while sending sms", CommonConstants.PARTA, partAForm.getLoginid(),request.getRemoteAddr());
//                            }
                        }

                    }
                    session.setAttribute("personstatus", partADTO.getPersonstatus());
                    session.setAttribute("personcode", personcodemax);
                    session.setAttribute("teluguname", partADTO.getTelugupersonname());
                    session.setAttribute("Name", partADTO.getFirstname());
                    request.setAttribute("Surname", partADTO.getSurname());
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
                    partAForm.setCard("");
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
                    if (((String) session.getAttribute("PartArestrict") != null) && "true".equals((String) session.getAttribute("PartArestrict")))
                    {
                        request.setAttribute("PartArestrict", (String) session.getAttribute("PartArestrict"));
                        target = "restrictPartA";
                        request.setAttribute("restrict", "yes");
                    }
                    session.removeAttribute("PartArestrict");
                } 
                else 
                {
                    target = "habcodeNotMatch";
                }
            } else {
                request.setAttribute("aadharCard", partAForm.getAadharCardNo());
                request.setAttribute("personcode", personCode);
                target = "alreadyEnteredDataDetails";

            }
        } catch (SADAREMDBException sADAREMException) {

            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    public String convertToString(int[] intArray) 
    {
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

    public ActionForward campdatesdropdownList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException 
    {
        String target = "success";
        DataSource ds = null;
        ActionMessages actionMessages = null;
        //PartADAO partADAO = new PartADAO();
        //PartAForm partAForm = new PartAForm();
        PrintWriter out = null;
        //String jobcardDetails = null;
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
            HttpSession session = request.getSession(true);

            ArrayList territory_info = new ArrayList();
            ArrayList territory_id_list = new ArrayList();
            //territory_info = partADAO.getCampDatesBasedOnLoginDetailsForPartA(ds, districtId, request.getParameter("disabilityid"));
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


            target = "partAForm";

        } catch (SADAREMDBException sADAREMException) {

            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {

            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }

        return mapping.findForward(target);
    }

    public ActionForward campdropdownList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException 
    {
        String target = "success";
        DataSource ds = null;
        ActionMessages actionMessages = null;
        PartADAO partADAO = new PartADAO();
        //PartAForm partAForm = new PartAForm();
        PrintWriter out = null;
        //String jobcardDetails = null;
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
            HttpSession session = request.getSession(true);
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


            target = null;

        } catch (SADAREMDBException sADAREMException) {

            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {

            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
    
    
    
    public ActionForward newcampdropdownList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException
            {
        String target = "success";
        DataSource ds = null;
        ActionMessages actionMessages = null;
        PartADAO partADAO = new PartADAO();
        //PartAForm partAForm = new PartAForm();
        PrintWriter out = null;
        //String jobcardDetails = null;
        String districtId = null,campId="" ,roleId="";
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
            HttpSession session = request.getSession(true);
            if (session.getAttribute("districtId") != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            campId = CommonUtility.checkNullObj(session.getAttribute("campId"));
            roleId = CommonUtility.checkNullObj(session.getAttribute("roleId"));
            ArrayList territory_info = new ArrayList();
            ArrayList territory_id_list = new ArrayList();
            ArrayList territory_name_list = new ArrayList();
            
            if(roleId.equals(CommonConstants.ROLE_DEO))
            {
            	 territory_info = partADAO.getNewCampBasedOnLoginCampDetailsForPartA(ds, districtId, request.getParameter("disabilityid"),campId);
            }
            else
            {
            	 territory_info = partADAO.getCampBasedOnLoginDetailsForPartA(ds, districtId, request.getParameter("disabilityid"));
            }
             
            
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


            target = null;

        } catch (SADAREMDBException sADAREMException) {

            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {

            target = "invalid";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
    
    
    
    public int uploadAadharPhoto(DataSource ds, String personcode, HttpServletRequest request, String base64)
            throws Exception {
        ActionMessages actionMessages = null;

        String districtName = null;

        String districtid = null;

        String loginid = null;
        CommonDetails commonDetails = null;
        boolean districtLevelAccessFlag = false;
        boolean success1 = false;
        boolean success2 = false;
        int i = 0;
        try {
//            ds = getDataSource(request);
//            if (ds == null || "null".equals(ds)) {
//                ds = JNDIDataSource.getConnection();
//            }

//            UploadForm myForm = (UploadForm) form;
            HttpSession session = request.getSession(true);
            CommonService commonservice = new CommonService();

            // added for upload photos based on district folder
            districtName = (String) session.getAttribute("district_name");
            personcode = (String) session.getAttribute("personcode");

            CommonDetails commondetails = new CommonDetails();
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            boolean adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);
            if (!adminLevelAccessFlag) {
                districtid = (String) session.getAttribute("districtId");
                districtLevelAccessFlag = commondetails.checkDistrictFlag(personcode, districtid);
            }
            if (null == districtName) {
                districtid = (String) session.getAttribute("districtId");
                if (adminLevelAccessFlag || districtLevelAccessFlag) {
                    TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
                    districtName = territoryservice.getDistrictsName(ds, districtid);
                }
            }

            boolean uploadPhotoFlag = commonservice.checkUploadPhoto(ds, personcode);

            if (uploadPhotoFlag && personcode != null && districtName != null) {
                if (personcode != null && districtName != null) {
                    commonDetails = new CommonDetails();
                    String url = getServlet().getServletContext().getRealPath("/");
                    commonDetails.copyPhotoDtoRelativePath(personcode, districtName, request, url);
                }

            } else 
            {
                if (personcode != null && districtName != null) {
                    loginid = (String) session.getAttribute("loginid");
                    commonservice.insertUploadPhoto(ds, personcode, loginid);


                    String fileName = "Profilepic.JPG";

                    String url = getServlet().getServletContext().getRealPath("/");
                    String strDirectoytemp = CommonConstants.SADAREM_DOCUMENTS_PATH+personcode+"\\Profile\\";


                    File dir = null;
                    File subdir = null;
                    File subdir1 = null;
                    File distdir = null;


                    subdir = new File(getServlet().getServletContext().getRealPath("/") + "DisabilityUITG");
                    if (!subdir.exists())
                    {
                        subdir.mkdir();
                    }
                    subdir1 = new File(subdir + "/uploadedphotos");
                    if (!subdir1.exists()) {
                        subdir1.mkdir();
                    }

                    distdir = new File(subdir1 + "/" + personcode);
                    if (!distdir.exists()) {
                        distdir.mkdir();
                    }

                    success1 = CommonDetails.uploadingAadharPhoto(base64, "" + distdir, fileName);

                    File tempdir = new File(strDirectoytemp);
                    if (!tempdir.exists()) {
                        tempdir.mkdirs();
                    }
                    /*File tempdistdir = new File(tempdir + "/" + districtName);
                    if (!tempdistdir.exists()) {
                        tempdistdir.mkdir();
                    }*/
                    success2 = CommonDetails.uploadingAadharPhoto(base64, "" + tempdir, fileName);

                    if (success1 && success2) {
                        i = 1;
                    }

                } else {
                    i = 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return i;
    }
}
