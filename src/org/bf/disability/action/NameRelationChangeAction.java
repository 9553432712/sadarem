/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.mail.internet.InternetAddress;
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
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.NameRelationChangeDTO;
import org.bf.disability.form.NameRelationChangeForm;
import org.bf.disability.service.NameRelationChangeService;
import org.bf.disability.service.pwdRequestService;
import org.bf.disability.servicefactory.NameRelationChangeServiceFactory;
import org.bf.disability.servicefactory.PwdRequestServiceFactory;
import org.bf.disability.util.EmailUtility;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;

/**
 *
 * @author 693461
 */
public class NameRelationChangeAction extends DispatchAction {

    /* forward name="success" path="" */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "success";
        DataSource ds = null;

        ArrayList campList = new ArrayList();
        HttpSession session = request.getSession();
        CommonDetails commondetails = new CommonDetails();
        boolean adminLevelAccessFlag = false;
        boolean urbanLevlAccessFlag = false;
        int campId = 0;

        NameRelationChangeService nameRelationChangeService = NameRelationChangeServiceFactory.getNameRelationChangeServiceImpl();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            //  adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_RURALDRDA);

            ///  urbanLevlAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_URBANMEPMA);



            if(session.getAttribute("campId").toString()!=null){
            campId = Integer.parseInt(session.getAttribute("campId").toString());
            }
            

            String districtId = (String) session.getAttribute("districtId");
            campList = nameRelationChangeService.getnameRelationChangeDetails(ds, campId, districtId);

            if (campList.isEmpty()) {
                request.setAttribute("msg", "No Data Found");
            }

            request.setAttribute("campList", campList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getnameRelationPersonalDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String target = "changeParticularDetails";
        DataSource ds = null;
        //requestIdData
        ArrayList oldParticularList = new ArrayList();
        ArrayList nameList = new ArrayList();
        ArrayList relationList = new ArrayList();
        ArrayList molesList = new ArrayList();
        ArrayList dateOfBirthList = new ArrayList();
         ArrayList surNameList = new ArrayList();
//        String Type1 = null;
//        String Type2 = null;
//        String Type5 = null;
        String dOBDetails = null;

        HttpSession session = request.getSession();
        NameRelationChangeForm nameRelationChangeForm = (NameRelationChangeForm) form;

        NameRelationChangeDTO nameRelationChangeDTO = null;
        NameRelationChangeService nameRelationChangeService = NameRelationChangeServiceFactory.getNameRelationChangeServiceImpl();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String requestId = (String) request.getParameter("requestId");
            String personCode = (String) request.getParameter("personCode");

            request.setAttribute("requestFormId", requestId);
            request.setAttribute("personCodeFormId", personCode);

            oldParticularList = nameRelationChangeService.individualNameRelationNameChangeDetails(ds, requestId);

            dOBDetails = nameRelationChangeService.dOBDetails(ds, personCode);


            for (int i = 0; i < oldParticularList.size(); i++) {
                if (oldParticularList.get(i).equals(CommonConstants.NAMECHANGECATEGORYID)) {
                    nameList = nameRelationChangeService.NameChangeDetails(ds, requestId, oldParticularList.get(i).toString());
                    Iterator it = nameList.iterator();
                    while (it.hasNext()) {
                        nameRelationChangeDTO = new NameRelationChangeDTO();
                        nameRelationChangeDTO = (NameRelationChangeDTO) it.next();
                        request.setAttribute("teluguOldName", nameRelationChangeDTO.getOldTeluguSurName());

                    }
                }
                if (oldParticularList.get(i).equals(CommonConstants.RELATIONNAMECHANGECATEGORYID)) {
                    relationList = nameRelationChangeService.RelationChangeDetails(ds, requestId, oldParticularList.get(i).toString());
                }
                if (oldParticularList.get(i).equals(CommonConstants.IDENTIFICATIONMOLESCATEGORYID)) {
                    molesList = nameRelationChangeService.MoleChangeDetails(ds, requestId, oldParticularList.get(i).toString());
                }
                if (oldParticularList.get(i).equals(CommonConstants.DATEOFBIRTHCATEGORYID)) {
                    dateOfBirthList = nameRelationChangeService.DOBChangeDetails(ds, requestId, oldParticularList.get(i).toString());
                }

                if (oldParticularList.get(i).equals(CommonConstants.SURNAMECATEGORYID)) {
                    surNameList = nameRelationChangeService.surNameChangeDetails(ds, requestId, oldParticularList.get(i).toString());
request.setAttribute("Note", "Note");


 Iterator it = surNameList.iterator();
                    while (it.hasNext()) {
                        nameRelationChangeDTO = new NameRelationChangeDTO();
                        nameRelationChangeDTO = (NameRelationChangeDTO) it.next();
                        request.setAttribute("teluguname", nameRelationChangeDTO.getOldTeluguName());

                    }
                }

            }
            request.setAttribute("oldParticularList", oldParticularList);
            request.setAttribute("oldParticularId", oldParticularList.size());
            request.setAttribute("personCode", personCode);
            request.setAttribute("requestId", requestId);
            request.setAttribute("dOBDetails", dOBDetails);

            request.setAttribute("nameList", nameList);
            request.setAttribute("relationList", relationList);
            request.setAttribute("molesList", molesList);
            request.setAttribute("dateOfBirthList", dateOfBirthList);
            request.setAttribute("surNameList", surNameList);

            if (oldParticularList.isEmpty()) {
                request.setAttribute("msg", "No Data Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "namechange";
        DataSource ds = null;

        ArrayList nameChangeList = new ArrayList();
        NameRelationChangeForm nameRelationChangeForm = (NameRelationChangeForm) form;
        NameRelationChangeService nameRelationChangeService = NameRelationChangeServiceFactory.getNameRelationChangeServiceImpl();
        TerritoryDAO territoryDAO = new TerritoryDAO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ArrayList districtsList = territoryDAO.getDistricts(ds);
            nameRelationChangeForm.setDistrictList(districtsList);
            nameChangeList = nameRelationChangeService.getData(ds, nameRelationChangeForm.getDistrict_id(), nameRelationChangeForm.getStatus());
            if (nameChangeList != null && nameChangeList.size() > 0) {
                request.setAttribute("nameChangeList", nameChangeList);
            } else {
                request.setAttribute("msg", "No Data Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

//    public ActionForward getParticularOldDetails(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//
//
//        String target = "changeParticularDetails";
//        DataSource ds = null;         ds = getDataSource(request);         if (ds == null || "null".equals(ds)) {             ds = JNDIDataSource.getConnection();         } //requestIdData
//        ArrayList oldParticularList = new ArrayList();
//        HttpSession session = request.getSession();
//        NameRelationChangeForm nameRelationChangeForm = (NameRelationChangeForm) form;
//        NameRelationChangeService nameRelationChangeService = NameRelationChangeServiceFactory.getNameRelationChangeServiceImpl();
//
//        try {
//            ds = getDataSource(request);
//            ds = getDataSource(request);
//            if (ds == null || "null".equals(ds)) {
//                ds = JNDIDataSource.getConnection();
//            }
//            String personCodeId = (String) request.getParameter("personCodeId");
//            String requesttypeId = (String) request.getParameter("requesttypeId");
//            String requestIdData = (String) request.getParameter("requestIdData");
//
//            request.setAttribute("personCodeId", personCodeId);
//            request.setAttribute("requesttypeId", requesttypeId);
//            request.setAttribute("requestIdData", requestIdData);
//
//            if (requesttypeId.equals("1")) {
//                request.setAttribute("type", "1");
//
//            } else if (requesttypeId.equals("2")) {
//                request.setAttribute("type", "2");
//            } else if (requesttypeId.equals("3")) {
//                request.setAttribute("type", "3");
//
//            } else if (requesttypeId.equals("3")) {
//                request.setAttribute("type", "4");
//            }
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mapping.findForward(target);
//    }
    public ActionForward updateDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {



        String target = "changeParticularDetails";
        DataSource ds = null;
        //requestIdData
        int updateRequestDetails = 0;
        String districtId = null;

        boolean name = false;
        boolean relationName = false;
        InternetAddress emailId = null;
        ArrayList toList = null;
        ArrayList ccList = null;
        String toEmailId = null;
        String ccEmailId = null;
        boolean emailStatus = false;
        String subject = "";
        String emailPersonName = null;
        String emailBody = null;
        String loginid = null;
        String emailDistrictId = null;
        HttpSession session = request.getSession();
        ArrayList<InternetAddress> ToMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> CCMail = new ArrayList<InternetAddress>();
        ArrayList<InternetAddress> BCCMail = new ArrayList<InternetAddress>();

        ArrayList oldParticularList = new ArrayList();
        ArrayList emailList = new ArrayList();
        ArrayList oldemailNameRelationName = null;
        String requestIdData = null;

        String personCodeList = null;

        loginid = (String) session.getAttribute("loginid");
        emailDistrictId = (String) session.getAttribute("districtId");

        NameRelationChangeDTO nameRelationChangeDTO = new NameRelationChangeDTO();
        EmailUtility emailUtilty = new EmailUtility();
        NameRelationChangeForm nameRelationChangeForm = (NameRelationChangeForm) form;
        NameRelationChangeService nameRelationChangeService = NameRelationChangeServiceFactory.getNameRelationChangeServiceImpl();

        pwdRequestService pwdRequestService = PwdRequestServiceFactory.getPwdRequestServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            requestIdData = nameRelationChangeForm.getEmailRequestId();

            oldParticularList = nameRelationChangeService.individualNameRelationNameChangeDetails(ds, requestIdData);

            personCodeList = nameRelationChangeService.getPersonCodeDetails(ds, requestIdData);
            
            CommonDAOImpl comObj = new CommonDAOImpl();
            HashMap GEODtls = new HashMap();
			GEODtls=comObj.getGEODetailsbySADAREMID(personCodeList);
            
            //districtId = personCodeList.substring(0, 2);
			districtId = GEODtls.get("districtid").toString();
			
            nameRelationChangeForm.setLoginId(loginid);


            if (nameRelationChangeForm.getBiometricId() != null) {

                for (int k = 0; k < nameRelationChangeForm.getBiometricId().length; k++) {

                    updateRequestDetails = nameRelationChangeService.updateParticularDetails(ds, nameRelationChangeForm, nameRelationChangeForm.getBiometricId()[k]);
                }


                request.setAttribute("oldParticularList", oldParticularList);
                nameRelationChangeDTO.setOldParticularList(oldParticularList);
                toList = pwdRequestService.getTORequestNameDetails(ds);
                ccList = pwdRequestService.getCCRequestNameDetails(ds, districtId);
                for (int i = 0; i < toList.size(); i++) {
                    toEmailId = toList.get(i).toString();
                    emailId = new InternetAddress(toEmailId, "");
                    ToMail.add(emailId);
                }

                for (int i = 0; i < ccList.size(); i++) {
                    ccEmailId = ccList.get(i).toString();
                    emailId = new InternetAddress(ccEmailId, "");
                    CCMail.add(emailId);
                }

                if (updateRequestDetails > 0) {

                    if (name == true || relationName == true) {
                        emailList = nameRelationChangeService.getEmailBodyDetails(ds, nameRelationChangeForm.getEmailPersonCode(), nameRelationChangeForm.getEmailRequestId());

                        oldemailNameRelationName = nameRelationChangeService.getEmailOldNameRelationDetails(ds, nameRelationChangeForm.getEmailPersonCode());
                        emailPersonName = nameRelationChangeService.emailpersonDetails(ds, loginid, emailDistrictId);
                        emailBody = emailUtilty.emailNameRelationName(emailList, emailPersonName, oldemailNameRelationName);
                        emailStatus = EmailUtility.SendEmail(ToMail, CCMail, BCCMail, "Subject.", emailBody, CommonConstants.Email_ID);
                    }

                    request.setAttribute("msg", "Updated SuccessFully");
                    nameRelationChangeForm.setFirstnametelugu("");

                } else {
                    request.setAttribute("msg", "Error in Updating");
                    nameRelationChangeForm.setFirstnametelugu("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //this.getParticularOldDetails(mapping, form, request, response);
        return mapping.findForward(target);
    }

    public ActionForward notUpdateDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = "changeParticularDetails";
        DataSource ds = null;

        ArrayList campList = new ArrayList();
        HttpSession session = request.getSession();
        CommonDetails commondetails = new CommonDetails();

        ArrayList oldParticularList = new ArrayList();
        ArrayList nameList = new ArrayList();
        ArrayList relationList = new ArrayList();
        ArrayList molesList = new ArrayList();
        ArrayList dateOfBirthList = new ArrayList();
//        String Type1 = null;
//        String Type2 = null;
//        String Type5 = null;
        String dOBDetails = null;
        NameRelationChangeForm nameRelationChangeForm = (NameRelationChangeForm) form;

        NameRelationChangeService nameRelationChangeService = NameRelationChangeServiceFactory.getNameRelationChangeServiceImpl();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String requestId = nameRelationChangeForm.getRequestFormId();
            String personCode = nameRelationChangeForm.getPersonCodeFormId();

            request.setAttribute("requestFormId", requestId);
            request.setAttribute("personCodeFormId", personCode);

            oldParticularList = nameRelationChangeService.individualNameRelationNameChangeDetails(ds, requestId);


            dOBDetails = nameRelationChangeService.dOBDetails(ds, personCode);

            for (int i = 0; i < oldParticularList.size(); i++) {
                if (oldParticularList.get(i).equals("1")) {
                    nameList = nameRelationChangeService.NameChangeDetails(ds, requestId, oldParticularList.get(i).toString());
                }
                if (oldParticularList.get(i).equals("2")) {
                    relationList = nameRelationChangeService.RelationChangeDetails(ds, requestId, oldParticularList.get(i).toString());
                }
                if (oldParticularList.get(i).equals("3")) {
                    molesList = nameRelationChangeService.MoleChangeDetails(ds, requestId, oldParticularList.get(i).toString());
                }
                if (oldParticularList.get(i).equals("4")) {
                    dateOfBirthList = nameRelationChangeService.DOBChangeDetails(ds, requestId, oldParticularList.get(i).toString());
                }

            }
            request.setAttribute("oldParticularList", oldParticularList);
            request.setAttribute("oldParticularId", oldParticularList.size());
            request.setAttribute("personCode", personCode);
            request.setAttribute("requestId", requestId);
            request.setAttribute("dOBDetails", dOBDetails);

            request.setAttribute("nameList", nameList);
            request.setAttribute("relationList", relationList);
            request.setAttribute("molesList", molesList);
            request.setAttribute("dateOfBirthList", dateOfBirthList);

            if (oldParticularList.isEmpty()) {
                request.setAttribute("msg", "No Data Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
}
