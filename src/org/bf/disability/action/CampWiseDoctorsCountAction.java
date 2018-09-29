/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.CampWiseDoctorsCountDTO;
import org.bf.disability.form.CampWiseDoctorsCountForm;
import org.bf.disability.service.CampWiseDoctorsCountService;
import org.bf.disability.servicefactory.CampWiseDoctorsCountServiceFactory;
import org.bf.disability.util.EmailUtility;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 693461
 */
public class CampWiseDoctorsCountAction extends DispatchAction {

    /* forward name="success" path="" */
    private String target = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {
        DataSource ds = null;
        CampWiseDoctorsCountForm formBean = (CampWiseDoctorsCountForm) form;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            CampWiseDoctorsCountService campWiseDoctorsCountService =
                    CampWiseDoctorsCountServiceFactory.getCampWiseDoctorsCountServiceImpl();
            formBean.setDisabilityList(campWiseDoctorsCountService.getTypeofDisability(ds));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    public ActionForward getDoctorsCountDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {

        CampWiseDoctorsCountForm campWiseDoctorsCountForm = (CampWiseDoctorsCountForm) form;
        CampWiseDoctorsCountDTO campWiseDoctorsCountDTO = new CampWiseDoctorsCountDTO();
        HttpSession session = request.getSession();

        CampWiseDoctorsCountService campWiseDoctorsCountService =
                CampWiseDoctorsCountServiceFactory.getCampWiseDoctorsCountServiceImpl();

        DataSource ds = null;


        String date = null;
        ArrayList campBasedOnDisabilityDetails = new ArrayList();
        int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
        String districtId = (String) session.getAttribute("districtId");

        ArrayList countData = new ArrayList();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (campWiseDoctorsCountForm.getCampDate() != null) {
                Date campdate = new SimpleDateFormat("dd/mm/yyyy").parse(campWiseDoctorsCountForm.getCampDate());
                date = new SimpleDateFormat("mm/dd/yyyy").format(campdate);
            }
            campBasedOnDisabilityDetails = campWiseDoctorsCountService.getCampBasedDisabilityDetails(ds, campId, date, districtId);


            for (int i = 0; i < campWiseDoctorsCountForm.getCountOfPwd().length; i++) {
                if (campWiseDoctorsCountForm.getCountOfPwd()[i] != null && !campWiseDoctorsCountForm.getCountOfPwd()[i].equals("")) {
                    HashMap list = null;
                    list = new HashMap();
                    list.put("PWDCount", campWiseDoctorsCountForm.getCountOfPwd()[i]);
                    list.put("disabilityId", campWiseDoctorsCountForm.getDisabilityId()[i]);
                    list.put("doctorRegNo", campWiseDoctorsCountForm.getDocRegNo()[i]);
                    countData.add(list);
                }
            }

            if (campBasedOnDisabilityDetails.size() > 0) {
                request.setAttribute("date", campWiseDoctorsCountForm.getDate());
                request.setAttribute("campBasedOnDisabilityDetails", campBasedOnDisabilityDetails);
                request.setAttribute("countData", countData);
            } else {
                request.setAttribute("msg", "No Data Found");
            }

            //campWiseDoctorsCountForm.reset(mapping, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //unspecified(mapping, form, request, response);
        return mapping.findForward("countDetails");
    }

    public ActionForward insertAssessedPwdDoctorsCount(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {
        HttpSession session = request.getSession();
        boolean msg = false;
        CampWiseDoctorsCountForm campWiseDoctorsCountForm = (CampWiseDoctorsCountForm) form;
        CampWiseDoctorsCountService campWiseDoctorsCountService =
                CampWiseDoctorsCountServiceFactory.getCampWiseDoctorsCountServiceImpl();
        DataSource ds = null;

        int pwdDoctorCountDetails = 0;


        int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
        String districtId = (String) session.getAttribute("districtId");
        String Systemip = request.getRemoteAddr();
        String loginId = (String) session.getAttribute("loginid");

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            ArrayList countData = (ArrayList) request.getSession(true).getAttribute("countData");

            Date campdate = new SimpleDateFormat("dd/mm/yyyy").parse(campWiseDoctorsCountForm.getCampDate());
            String date = new SimpleDateFormat("mm/dd/yyyy").format(campdate);


            if (countData.size() > 0) {
                for (int i = 0; i < countData.size(); i++) {
                    HashMap list1 = new HashMap();
                    list1 = (HashMap) countData.get(i);
                    pwdDoctorCountDetails = campWiseDoctorsCountService.insertPwdDoctorCountDetails(ds, date, campId, districtId, list1.get("disabilityId").toString(), list1.get("PWDCount").toString(), list1.get("doctorRegNo").toString(), loginId, Systemip);
                }
            }
            if (pwdDoctorCountDetails > 0) {

                request.setAttribute("msgData", "Inserted Successfully");
                msg = this.sendMail(request, date, countData);
            } else {
                request.setAttribute("msgData", "Error occured while Insertion");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("countDetails");
    }

    public boolean sendMail(HttpServletRequest request, String date, ArrayList countData) throws SADAREMException, SQLException {


        int dataEnteredbyCamp = 0;
        ArrayList campBasedOnDisabilityDetailsMail = new ArrayList();
        boolean msg = false;
        String msgBody = "";
        CampWiseDoctorsCountService campWiseDoctorsCountService =
                CampWiseDoctorsCountServiceFactory.getCampWiseDoctorsCountServiceImpl();
        HttpSession session = request.getSession();
        DataSource ds = null;

        int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
        String districtId = (String) session.getAttribute("districtId");
        TerritoryDAO territoryDAO = new TerritoryDAO();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String campName = campWiseDoctorsCountService.getCampName(ds, campId, districtId);
            String districtNameData = territoryDAO.getDistrictsName(ds, districtId);
            String certificateCount = campWiseDoctorsCountService.getCertificateCountDetails(ds, campId, districtId, date);
            String partAEnteredCount = campWiseDoctorsCountService.getPartAEnteredCountDetails(ds, campId, districtId, date);
            campBasedOnDisabilityDetailsMail = campWiseDoctorsCountService.getCampBasedDisabilityDetailsForMail(ds, campId, districtId, date);

            if (campBasedOnDisabilityDetailsMail.size() > 0) {
                String subject = "PWD's Assessed the Doctor's Information";

                ArrayList<InternetAddress> RecMail = new ArrayList<InternetAddress>();
                InternetAddress emailId = null;


                if (countData.size() > 0) {

                    for (int i = 0; i < countData.size(); i++) {

                        HashMap list1 = new HashMap();
                        list1 = (HashMap) countData.get(i);
                        dataEnteredbyCamp += Integer.parseInt(list1.get("PWDCount").toString());
                    }
                }


                emailId = new InternetAddress(CommonConstants.DoctorCampWise_EmailID);
                RecMail.add(emailId);
                EmailUtility emailUtility = new EmailUtility();

                msgBody = emailUtility.IntimationForVerificationMailFormat(districtNameData, campName, dataEnteredbyCamp, certificateCount, partAEnteredCount, campBasedOnDisabilityDetailsMail);
                msg = EmailUtility.SendEmail(RecMail, RecMail, RecMail, subject, msgBody, CommonConstants.Email_ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    /*Balu code*/
    public ActionForward submitDate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {
        HttpSession session = request.getSession();
        boolean msg = false;
        CampWiseDoctorsCountForm formBean = (CampWiseDoctorsCountForm) form;
        if (session.getAttribute("sessionList") != null) {
            session.removeAttribute("sessionList");
        }
        if (request.getAttribute("nextList") != null) {
            request.removeAttribute("nextList");
        }
        DataSource ds = null;
        ArrayList disabilityList = new ArrayList();
        //String districtId = (String) session.getAttribute("districtId");
        //String Systemip = request.getRemoteAddr();
        //String loginId = (String) session.getAttribute("loginid");
        try {
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            CampWiseDoctorsCountService serviceObj =
                    CampWiseDoctorsCountServiceFactory.getCampWiseDoctorsCountServiceImpl();

            int count = serviceObj.checkDoctorCount(ds, campId, formBean.getCampDate(), null);

            if (count > 0) {
                request.setAttribute("msg", "Doctor details already Registered");
            } else {
                disabilityList = serviceObj.getTypeofDisability(ds);

                request.setAttribute("disabilityList", disabilityList);
            }
            formBean.setDocreg("");

            unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("success");
    }

    public ActionForward getDoctorNameAndDesg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {

        HttpSession session = request.getSession();
        boolean msg = false;
        CampWiseDoctorsCountForm formBean = (CampWiseDoctorsCountForm) form;
        DataSource ds = null;
        ArrayList disabilityList = new ArrayList();

        //String districtId = (String) session.getAttribute("districtId");
        //String Systemip = request.getRemoteAddr();
        //String loginId = (String) session.getAttribute("loginid");
        try {
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            CampWiseDoctorsCountService serviceObj =
                    CampWiseDoctorsCountServiceFactory.getCampWiseDoctorsCountServiceImpl();

            int count = serviceObj.checkDoctorCount(ds, campId, formBean.getCampDate(), null);

            if (count > 0) {
                request.setAttribute("msg", "Doctor details already Registered");
            } else {

                if (session.getAttribute("sessionList") != null) {

                    ArrayList nextList = (ArrayList) session.getAttribute("sessionList");
                    session.removeAttribute("sessionList");
                    request.setAttribute("nextList", nextList);

                }
                disabilityList = serviceObj.getTypeofDisability(ds);

                ArrayList list = serviceObj.getDocDetails(ds, campId, formBean.getDisabilityid(), formBean.getDocreg());
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        Map tempList = new HashMap();
                        tempList = (Map) list.get(i);
                        request.setAttribute("doctorName", tempList.get("doctorName"));
                        request.setAttribute("docDesig", tempList.get("docDesig"));
                    }

                    request.setAttribute("list", list);
                } else {
                    //request.setAttribute("nodata", "No Data Found");
                    request.setAttribute("msg", "No Doctor is found for the given Registration Number and Disability!!!");
                }
                request.setAttribute("disabilityList", disabilityList);
            }


            unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("success");
    }

    public ActionForward addDoctor(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {
        DataSource ds = null;
        //HttpSession session = request.getSession();
        CampWiseDoctorsCountForm formBean = (CampWiseDoctorsCountForm) form;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            CampWiseDoctorsCountService campWiseDoctorsCountService =
                    CampWiseDoctorsCountServiceFactory.getCampWiseDoctorsCountServiceImpl();
            formBean.setDisabilityList(campWiseDoctorsCountService.getTypeofDisability(ds));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("addDoctor");
    }

    public ActionForward insertDoctor(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {
        HttpSession session = request.getSession();
        CampWiseDoctorsCountForm formBean = (CampWiseDoctorsCountForm) form;
        DataSource ds = null;

        try {
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            //String districtId = (String) session.getAttribute("districtId");
            //String Systemip = request.getRemoteAddr();
            String loginId = (String) session.getAttribute("loginid");
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            CampWiseDoctorsCountService serviceObj =
                    CampWiseDoctorsCountServiceFactory.getCampWiseDoctorsCountServiceImpl();

            int status =serviceObj.checkDoctorWithRegistrationNo(ds,campId,formBean.getDocreg(),formBean.getDisabilityid());
            if(status==0){
            int success = serviceObj.insertDoctor(ds, loginId, campId, null, formBean);
            if (success > 0) {
                request.setAttribute("msg", "Doctor details Registered successfully");
                request.setAttribute("closewindow", "closewindow");
            }
            }else{
                request.setAttribute("msg", "Doctor details already Registered with this Registration number and Disability");
                formBean.setDisabilityList(serviceObj.getTypeofDisability(ds));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("addDoctor");
    }

    public ActionForward insertDocotorCount(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {
        HttpSession session = request.getSession();
        CampWiseDoctorsCountForm formBean = (CampWiseDoctorsCountForm) form;
        String target = "success";
        DataSource ds = null;

        boolean msg = false;
        try {
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            String districtId = (String) session.getAttribute("districtId");
            //String Systemip = request.getRemoteAddr();
            String loginId = (String) session.getAttribute("loginid");
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            CampWiseDoctorsCountService serviceObj =
                    CampWiseDoctorsCountServiceFactory.getCampWiseDoctorsCountServiceImpl();
            int dataEnteredbyCamp = 0;
            Date campdate = new SimpleDateFormat("dd/mm/yyyy").parse(formBean.getCampDate());
            String date = new SimpleDateFormat("mm/dd/yyyy").format(campdate);

            ArrayList nextList = new ArrayList();
            ArrayList sessionList = new ArrayList();

            if (session.getAttribute("sessionList") != null) {

                sessionList = (ArrayList) session.getAttribute("sessionList");
                session.removeAttribute("sessionList");

            }
            nextList = insertList(sessionList, formBean);

            request.setAttribute("nextList", nextList);
            //formBean.setSessionList(nextList);
            ArrayList disabilityList = serviceObj.getTypeofDisability(ds);
            request.setAttribute("disabilityList", disabilityList);

            formBean.setDisabilityid("");
            formBean.setCount("");
            formBean.setDocreg("");
            unspecified(mapping, form, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward allDoctorsInsert(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMException, SQLException {
        HttpSession session = request.getSession();
        CampWiseDoctorsCountForm formBean = (CampWiseDoctorsCountForm) form;
        String target = "success";
        DataSource ds = null;

        boolean msg = false;
        try {
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            String districtId = (String) session.getAttribute("districtId");
            //String Systemip = request.getRemoteAddr();
            String loginId = (String) session.getAttribute("loginid");
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            CampWiseDoctorsCountService serviceObj =
                    CampWiseDoctorsCountServiceFactory.getCampWiseDoctorsCountServiceImpl();
            int dataEnteredbyCamp = 0;
            Date campdate = new SimpleDateFormat("dd/mm/yyyy").parse(formBean.getCampDate());
            String date = new SimpleDateFormat("mm/dd/yyyy").format(campdate);

            if (session.getAttribute("sessionList") != null) {
                ArrayList nextList = (ArrayList) session.getAttribute("sessionList");
                session.removeAttribute("sessionList");
                String[] totalCount = new String[nextList.size()];
                String[] doctorReg = new String[nextList.size()];
                String[] disabilityIds = new String[nextList.size()];

                for (int x = 0; x < nextList.size(); x++) {

                    Map m = (Map) nextList.get(x);
                    doctorReg[x] = (String) m.get("doctorRegNumber");
                    totalCount[x] = (String) m.get("count");
                    disabilityIds[x] = (String) m.get("disabilityId");
                }
                formBean.setTotalCount(totalCount);
                formBean.setDoctorReg(doctorReg);
                formBean.setDisabilityId(disabilityIds);
            }
            int success = serviceObj.insertDocotorCount(ds, loginId, campId, districtId, formBean);
            if (success > 0) {
                request.setAttribute("msg", "Doctor Count Submitted Successfully!");
//                ArrayList list = serviceObj.getCampWiseDoctorsAssCountDetails(ds, campId, formBean.getCampDate(), null);
//                request.setAttribute("list", list);
//                request.setAttribute("date", formBean.getCampDate());
                //msg = this.sendMailDoctorCount(request, date, dataEnteredbyCamp);

               // target = "campWiseDoctorsAssCountDetails";
                 target="success";
            } else {
                request.setAttribute("msg", "Error while instering");
            }
            formBean.setDisabilityid("");
            formBean.setCount("");
            formBean.setDocreg("");
            unspecified(mapping, form, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public boolean sendMailDoctorCount(HttpServletRequest request, String date, int countData) throws SADAREMException, SQLException {
        ArrayList campBasedOnDisabilityDetailsMail = new ArrayList();
        boolean msg = false;
        String msgBody = "";
        CampWiseDoctorsCountService campWiseDoctorsCountService =
                CampWiseDoctorsCountServiceFactory.getCampWiseDoctorsCountServiceImpl();
        HttpSession session = request.getSession();
        DataSource ds = null;


        TerritoryDAO territoryDAO = new TerritoryDAO();
        try {
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            String districtId = (String) session.getAttribute("districtId");
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String campName = campWiseDoctorsCountService.getCampName(ds, campId, districtId);
            String districtNameData = territoryDAO.getDistrictsName(ds, districtId);
            String certificateCount = campWiseDoctorsCountService.getCertificateCountDetails(ds, campId, districtId, date);
            String partAEnteredCount = campWiseDoctorsCountService.getPartAEnteredCountDetails(ds, campId, districtId, date);
            campBasedOnDisabilityDetailsMail = campWiseDoctorsCountService.getCampBasedDisabilityDetailsForMail(ds, campId, districtId, date);
            if (campBasedOnDisabilityDetailsMail.size() > 0) {
                String subject = "PWD's Assessed the Doctor's Information";
                ArrayList<InternetAddress> RecMail = new ArrayList<InternetAddress>();
                InternetAddress emailId = null;
                emailId = new InternetAddress(CommonConstants.DoctorCampWise_EmailID);
                RecMail.add(emailId);
                EmailUtility emailUtility = new EmailUtility();
                msgBody = emailUtility.IntimationForVerificationMailFormat(districtNameData, campName, countData, certificateCount, partAEnteredCount, campBasedOnDisabilityDetailsMail);
                msg = EmailUtility.SendEmail(RecMail, RecMail, RecMail, subject, msgBody, CommonConstants.Email_ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    private ArrayList insertList(ArrayList nextList, CampWiseDoctorsCountForm formBean) {
     
        Map tempList = new HashMap();
        tempList.put("sno", nextList.size() + 1);
        tempList.put("doctorName", formBean.getDocname());
        tempList.put("doctorRegNumber", formBean.getDocreg());
        tempList.put("desig", formBean.getDocdesig());
        tempList.put("count", formBean.getCount());
        tempList.put("disability", formBean.getDisName());
        tempList.put("disabilityId", formBean.getDisabilityid());
        nextList.add(tempList);
        return nextList;
    }
}

    