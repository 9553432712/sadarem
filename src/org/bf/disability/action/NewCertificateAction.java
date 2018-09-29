/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.NewCertificateDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.NewCertificateDTO;
import org.bf.disability.form.NewCertificateForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.servicefactory.NewCertificateServiceFactory;
import org.bf.disability.serviceimpl.NewCertificateServiceImpl;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 310926
 */
public class NewCertificateAction extends DispatchAction {

    /* forward name="success" path="" */
    String SUCCESS = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;

        NewCertificateForm NewCertificateForm = (NewCertificateForm) form;
        HttpSession session = request.getSession();
        String districtId = null;
        String districtName = null;
        FunctionalNeedReportService functionalNeedService = FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        ArrayList mandalList = new ArrayList();
        NewCertificateDAO partADAO = new NewCertificateDAO();


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            InetAddress ownIP = InetAddress.getLocalHost();
            String systemIp = ownIP.getHostAddress();
            districtId = (String) session.getAttribute("districtId");
            districtName = partADAO.getdistrictName(ds, districtId);


            mandalList = functionalNeedService.getMandals(ds, districtId,"");
            NewCertificateForm.setMandallist(mandalList);
            NewCertificateForm.setDistrict(districtName);
            request.setAttribute("districtId", districtId);
            request.setAttribute("districtName", districtName);
        } catch (Exception e) {
            e.printStackTrace();
        }



        return mapping.findForward(SUCCESS);
    }

    public ActionForward insertPartADetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NewCertificateForm NewCertificateForm = (NewCertificateForm) form;
        DataSource ds = null;

        int updateStatus = 0;
        String target = "success";
        HttpSession session = request.getSession();
        String personcodemax = null;
        String partACheckForDuplicatePersonCode = null;

        String loginid = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            NewCertificateDAO partADAO = new NewCertificateDAO();
            NewCertificateServiceImpl partAService = NewCertificateServiceFactory.getNewCertificateServiceImpl();
            loginid = (String) session.getAttribute("loginid");
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            NewCertificateForm.setLoginid(loginid);
            NewCertificateForm.setCampid(campId);

            TerritoryDAO territoryDAO = new TerritoryDAO();
            String habcode = NewCertificateForm.getHabitation_id();
            NewCertificateDTO newCertificateDTO = new NewCertificateDTO();


            String day = request.getParameter("day");
            String Systemip = request.getRemoteAddr();


            NewCertificateForm.setHabCode(NewCertificateForm.getHabitation_id());

            String date = NewCertificateForm.getDay() + "/" + NewCertificateForm.getMonth() + "/" + NewCertificateForm.getYear();

            //Chrononical age  calculation for Mental Retardation tests.
            Date d1 = new GregorianCalendar(Integer.parseInt(NewCertificateForm.getYear()), Integer.parseInt(NewCertificateForm.getMonth()), Integer.parseInt(NewCertificateForm.getDay())).getTime();
            Date today = new Date();
            long diff = today.getTime() - d1.getTime();
            long days = diff / (1000 * 60 * 60 * 24);

            double years = (double) days / 365;
            session.setAttribute("chronologicalage", new Double(years));
            NewCertificateForm.setDobday(date);
            NewCertificateForm.setSystemip(Systemip);
            BeanUtils.copyProperties(newCertificateDTO, NewCertificateForm);

            partACheckForDuplicatePersonCode = partAService.getPartACheckForDuplicate(ds, newCertificateDTO);

            if (partACheckForDuplicatePersonCode != null && !"null".equals(partACheckForDuplicatePersonCode)) {
                request.setAttribute("partACheckForDuplicatePersonCode", partACheckForDuplicatePersonCode);
                return mapping.findForward("duplicate");
            }

            //   PartADAO partADAO = new PartADAO();

//            boolean duplicate_slno = partADAO.isDuplicateSlno(newCertificateDTO, ds, request);


//            if (!duplicate_slno) {
            personcodemax = partAService.insertPersonalDetailsForRationCard(newCertificateDTO, ds, request);

            if (!personcodemax.equals("Not Match")) {

                if (newCertificateDTO.getPersonstatus().equals("Eligible")) {
                    target = "finish";
                    request.setAttribute("restrict", "yes");
                } else {
                    target = "rejected";
                }
                session.setAttribute("personstatus", newCertificateDTO.getPersonstatus());
                session.setAttribute("personcode", personcodemax);
                session.setAttribute("teluguname", newCertificateDTO.getTelugupersonname());
                session.setAttribute("Name", newCertificateDTO.getFirstname());
                request.setAttribute("district_id", newCertificateDTO.getDistrict_id());
                request.setAttribute("mandal_id", newCertificateDTO.getMandal_id());
                request.setAttribute("village_id", newCertificateDTO.getVillage_id());
                request.setAttribute("habitation_id", newCertificateDTO.getHabitation_id());
                request.setAttribute("panchayat_id", newCertificateDTO.getPanchayat_id());
                request.setAttribute("habcode", habcode);


            } else {
                target = "habcodeNotMatch";
//                target = "success";
//                request.setAttribute("closewindow", "closewindow");
//                request.setAttribute("msg", "Habitation code not match please select proper Territory details");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
}
