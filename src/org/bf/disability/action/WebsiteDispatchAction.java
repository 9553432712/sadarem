/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.CommonReportDTO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.dto.WebsiteCommonDTO;
import org.bf.disability.form.CommonReportForm;
import org.bf.disability.form.PartAForm;
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.form.WebsiteCommonForm;
import org.bf.disability.service.CommonPersonalReportService;
import org.bf.disability.service.CommonReportService;
import org.bf.disability.service.CommonService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.CommonPersonalReportServiceFactory;
import org.bf.disability.servicefactory.CommonReportServiceFactory;
import org.bf.disability.servicefactory.CommonServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 509865
 */
public class WebsiteDispatchAction extends DispatchAction {

    ActionMessages actionMessages = null;
    DataSource ds = null;
    String success = "repeat";
    String failure = "failure";

    /**
     * this method will be used to search person details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return ActionForward
     */
    public ActionForward SearchPensionPage(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "success";
        String personCode = null;
        ArrayList districtList = new ArrayList();

        personCode = request.getParameter("personcode");
        request.setAttribute("personCode", personCode);
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        PartAForm partAForm = (PartAForm) form;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            districtList = territoryservice.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                partAForm.setDistrictlist(districtList);
            }
        } catch (Exception sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }

        return mapping.findForward(target);
    }

    public ActionForward SearchPage(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "success";
        String personCode = null;
        String checkPersoncode = null;
        PartAForm partAForm = (PartAForm) form;
        personCode = request.getParameter("personcode");
        CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
        request.setAttribute("personCode", personCode);
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            checkPersoncode = commonservice.checkPersonCodeForSearch(ds, personCode);
            if (checkPersoncode == null) {
                request.setAttribute("searchmsg", "Assessment not Done For the SADAREM ID");
                  request.setAttribute("type", "1");
                target = "fallure";

            } else {
                String disabilityId = commonservice.getDisabilityId(ds, personCode);
                int totalDisability = commonservice.getTotalPercentage(ds, personCode);
                request.setAttribute("totalDisability", (double)totalDisability);
                CommonDAO commonDAO = new CommonDAO();
                double eligiblePercentage = commonDAO.getDisabilityPercentage(ds, disabilityId);
                request.setAttribute("eligiblePercentage", eligiblePercentage);
            }

        } catch (Exception sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }

        return mapping.findForward(target);
    }

    /**
     * this method will be used to search person details of certificate and Id card
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward searchCertificate(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {
        /*
         * Creating instance for  services
         */

//        ReportService reportservice = ReportServiceFactory.getReportServiceImpl();
//        TerritoryDTO territorydto = new TerritoryDTO();
//        CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
//        /*
//         * Creating instance for form(TerritoryForm)
//         */
//        TerritoryForm territoryForm = (TerritoryForm) form;
//        String target = "finish";
//        String disabilityid = null;
//        String personcode = null;
//        String personcodestatus = null;
//        double totaldisability;
//        String disabilityName = null;
//        CommonDetails commonDetails = null;
//        //DataSource ds=getDataSource(request);
//
//        ArrayList rejectedlist = new ArrayList();
//        ArrayList reportlist = null;
//        ArrayList suggestionlist = null;
//        String conditionlistCheckFlag = "false";
//        String districtName = null;
//        TerritoryDAO territorydao = new TerritoryDAO();
//        try {
//            ds = getDataSource(request);
//            if (ds == null || "null".equals(ds)) {
//                ds = JNDIDataSource.getConnection();
//            }
//
//            personcode = request.getParameter("personcode");
//            String display = request.getParameter("display");
//            String print = (String) request.getParameter("print");
//            territoryForm.setDifferenceFlag(print);
//            request.setAttribute("display", display);
//            /*
//             *getting status for particular personcode
//             */
//            personcodestatus = commonservice.getPersonStatus(ds, personcode);
//            boolean exi = false;
//            if (personcode != null && personcodestatus != null) {
//                exi = territorydao.isEligibleforCertificate(ds, personcode, request);
//                if (exi) {
//
//                    //  return mapping.findForward("PersoncodeFailure");
//                    return mapping.findForward("PersoncodeFailure");
//                }
//
//            }
//
//            if (personcodestatus != null) {
//                if (personcodestatus.equals("Eligible")) {
//                    /*
//                     * getting details of particular pwd
//                     */
//                    disabilityid = commonservice.getDisabilityId(ds, personcode);
//                    reportlist = new ArrayList();
//                    reportlist = reportservice.getDetailsForCertificate(ds, territoryForm, personcode);
//                    if (reportlist.size() == 0) {
//                        target = "failure";
//                    } else {
//                        String causeOfDisabilityFlag = "false";
//                        Iterator iter = reportlist.iterator();
//                        iter.hasNext();
//                        territorydto = (TerritoryDTO) iter.next();
//                        totaldisability = territorydto.getDisabilityvalue();
//                        disabilityName = territorydto.getDisabilitytype();
//                        districtName = territorydto.getDistrict_name();
//                        /*
//                         * checking for displaying names
//                         *
//                         */
//                        if ((null != territorydto.getTotalcauseofdisabilities() && !"".equals(territorydto.getTotalcauseofdisabilities()))) {
//                            causeOfDisabilityFlag = "true";
//                        }
//                        if ((null != territorydto.getPhysicalImpairmentPhysicalRequirementList() && !"".equals(territorydto.getPhysicalImpairmentPhysicalRequirementList()))
//                                || (null != territorydto.getVisualImpairmentPhysicalRequirementList() && !"".equals(territorydto.getVisualImpairmentPhysicalRequirementList()))
//                                || (null != territorydto.getHearingImpairmentPhysicalRequirementList() && !"".equals(territorydto.getHearingImpairmentPhysicalRequirementList()))
//                                || (null != territorydto.getMentalRetardationPhysicalRequirementList() && !"".equals(territorydto.getMentalRetardationPhysicalRequirementList()))) {
//                            conditionlistCheckFlag = "true";
//                        }
//
//                        /*
//                         * getting telugu names for particular pwd
//                         */
//
//                        //territorydto = reportservice.getTeluguName(ds, personcode);
//                        String telugupersonname = territorydto.getTelugupersonname();
//                        String telugufathername = territorydto.getTelugufathername();
//                        String disabilitytypeInTelugu = territorydto.getDisabilityTypeInTelugu();
//                        String genderInTelugu = territorydto.getGenderInTelugu();
//                        String disabilityduration = territorydto.getDisabilityduration();
//                        String teluguDisablityName = territorydto.getTeluguDisabilityName();
//                        String apflag = territorydto.getFlag();
//
//                        /*
//                         *for telugu name we can not directly set Form to jsp
//                         * so we have to put every telugu field in session
//                         */
//
//                        request.setAttribute("causeOfDisabilityFlag", causeOfDisabilityFlag);
//                        request.setAttribute("disabilityduration", disabilityduration);
//                        request.setAttribute("disabilitytypeInTelugu", disabilitytypeInTelugu);
//                        request.setAttribute("personcode", personcode);
//                        request.setAttribute("gender", genderInTelugu);
//                        request.setAttribute("telugupersonname", telugupersonname);
//                        request.setAttribute("telugufathername", telugufathername);
//                        request.setAttribute("teluguDisablityName", teluguDisablityName);
//                        request.setAttribute("apflag", apflag);
//                        /*
//                         * getting details of particular pwd for Id Card
//                         */
//                        //Idcardlist = new ArrayList();
//
//                        // Idcardlist = reportservice.getDetaiilsForIdCard(ds, territoryForm, personcode);
//                        request.setAttribute("reportlist", reportlist);
//                        //  request.setAttribute("Idcardlist", Idcardlist);
//                        CommonDAO commonDAO = new CommonDAO();
//                        double eligiblePercentage = commonDAO.getDisabilityPercentage(ds, disabilityid);
//                        suggestionlist = new ArrayList();
//                        if (disabilityid.equals("1") && totaldisability < 40.0) {
//                            suggestionlist = reportservice.getLocomotorData(ds, personcode);
//                        } else if (disabilityid.equals("2") && totaldisability < 40.0) {
//                            suggestionlist = reportservice.getVisualData(ds, personcode);
//                        } else if (disabilityid.equals("3") && totaldisability < 40.0) {
//                            suggestionlist = reportservice.getHearingData(ds, personcode);
//                        } else if (disabilityid.equals("4") && totaldisability < 40.0) {
//                            suggestionlist = reportservice.getMentalRetardationData(ds, personcode);
//                        } else if (disabilityid.equals("5") && totaldisability < 40.0) {
//                            suggestionlist = reportservice.getMetalillnessData(ds, personcode);
//                        } else if (disabilityid.equals("6") && totaldisability < 40.0) {
//                            suggestionlist = reportservice.getMultipleData(ds, personcode);
//                        }
//                        request.setAttribute("certificatewithidcard", (String) request.getParameter("certificatewithidcard"));
//                        request.setAttribute("conditionlistCheckFlag", conditionlistCheckFlag);
//                        request.setAttribute("suggestionlist", suggestionlist);
//
//
//                        if (print.equals("certificate")) {
//                            if (totaldisability < eligiblePercentage) {
//                                target = "rejectedcertificate";
//                            } else {
//                                if (disabilityName != null && !"".equals(disabilityName)) {
//                                    if (CertificateConstants.LOCOMOTOR_DISABILITYTYPE.equals(disabilityName)
//                                            || CertificateConstants.LOCOMOTOR_DISABILED.equals(disabilityName)) {
//                                        target = "physicallimpairmentcertificate";
//                                    } else if (CertificateConstants.VISUAL_DISABILITYTYPE.equals(disabilityName)
//                                            || CertificateConstants.VISUAL_DISABILED.equals(disabilityName)) {
//                                        target = "visualimpairmentcertificate";
//                                    } else if (CertificateConstants.HEARING_DISABILITYTYPE.equals(disabilityName)) {
//                                        target = "hearingimpairmentcertificate";
//                                    } else if (CertificateConstants.MENTALRETARDATION_DISABILITYTYPE.equals(disabilityName)) {
//                                        target = "mentalretardationcertificate";
//                                    } else if (CertificateConstants.MENTALILLNESS_DISABILITYTYPE.equals(disabilityName)) {
//                                        target = "mentalillnesscertificate";
//                                    } else if (CertificateConstants.MULTIPLE_DISABILITYTYPE.equals(disabilityName)) {
//                                        target = "multipledisabilitycertificate";
//                                    } else {
//                                        target = "certificate";
//                                    }
//                                }
//                            }
//                        } else if (print.equals("idcard")) {
//                            if (totaldisability >= eligiblePercentage) {
//                                target = "idcard";
//                            } else {
//                                target = "failure";
//                            }
//                        } else {
//                            target = "failure";
//                        }
//                    }
//                } else {
//                    /*
//                     * getting Directed rejected pwd details
//                     */
//
//                    CertificateDTO certificatedto = new CertificateDTO();
//                    ArrayList rejectlist = new ArrayList();
//
//                    rejectedlist = reportservice.getRejectedData(ds, personcode);
//                    Iterator iterator = rejectedlist.iterator();
//                    iterator.hasNext();
//                    certificatedto = (CertificateDTO) iterator.next();
//                    disabilityid = certificatedto.getDisabilityidforrejecte();
//                    int disabilityId = Integer.parseInt(disabilityid);
//                    districtName = certificatedto.getDistrictname();
//
//                    switch (disabilityId) {
//                        case 1:
//                            rejectlist = reportservice.getRejectedLocomotorData(ds, personcode);
//                            break;
//                        case 2:
//                            rejectlist = reportservice.getRejectedVisualData(ds, personcode);
//                            break;
//                        case 3:
//                            rejectlist = reportservice.getRejectedHearingData(ds, personcode);
//                            break;
//                        case 4:
//                            rejectlist = reportservice.getRejectedMentalRetardationData(ds, personcode);
//                            break;
//                        case 5:
//                            rejectlist = reportservice.getRejectedMetalillnessData(ds, personcode);
//                            break;
//                        default:
//                            break;
//                    }
//                    String apflag = certificatedto.getFlag();
//                    request.setAttribute("apflag", apflag);
//
//                    request.setAttribute("rejectedlist", rejectedlist);
//                    request.setAttribute("rejectlist", rejectlist);
//
//                    target = "directrejected";
//
//                }
//                if (personcode != null && districtName != null) {
//                    commonDetails = new CommonDetails();
//                    String url = getServlet().getServletContext().getRealPath("/");
//                    commonDetails.copyPhotoDtoRelativePath(personcode, districtName, request, url);
//                }
//            }
//        } catch (SQLException sqlEx) {
//            target = "exception";
//            actionMessages = new ActionMessages();
//            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
//            saveErrors(request, actionMessages);
//
//        } catch (Exception sqlEx) {
//            target = "exception";
//            actionMessages = new ActionMessages();
//            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
//            saveErrors(request, actionMessages);
//
//        }
    	System.out.println("action");
         request.setAttribute("msg", "Access Denied");
        return mapping.findForward("PersoncodeFailure");
    }

    /**
     *
     * @description this method will insert the Feedback information in to the data base for a website
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward insertFeedback(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException,
            Exception {
        String target = null;

        WebsiteCommonForm feedbackform = (WebsiteCommonForm) form;

        //DataSource datasource=getDataSource(request);

        CommonService feedbackervice = CommonServiceFactory.getCommonServiceImpl();
        String localaddr = CommonUtility.getClientIPAddress(request);
        WebsiteCommonDTO feedbackdto = new WebsiteCommonDTO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            target = "success";
            BeanUtils.copyProperties(feedbackdto, feedbackform);
            int status = feedbackervice.insertFeedbackDetails(ds, feedbackdto, localaddr);
            if (status > 0) {
                request.setAttribute("SUCCESS", "Thank You For Your FeedBack");
                feedbackform.setDistrict("");
                feedbackform.setName("");
                feedbackform.setState("");
                feedbackform.setMandal("");
                feedbackform.setLocation("");
                feedbackform.setPhone("");
                feedbackform.setEmail("");
                feedbackform.setFeedback("");
            }

        } catch (SQLException sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }

        return getFeedback(mapping, form, request, response);

    }

    /**
     *
     * @description this method will dispaly the Feedback information in to the  website feedback page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getFeedback(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException,
            Exception {
        String target = "success";
        List feedbackDetailsList = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
            feedbackDetailsList = commonservice.getFeedback(ds);
            target = "success";
            if (feedbackDetailsList.isEmpty() && feedbackDetailsList == null) {
                request.setAttribute("message", "No feedback details");
            }
            request.setAttribute("feedbackDetailsList", feedbackDetailsList);

        } catch (SQLException sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }
        return mapping.findForward(target);

    }

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @ throws SADAREMDBException, SQLException
     */
    public ActionForward getDashBoardDetails(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = null;
        ArrayList<WebsiteCommonDTO> dashBoardList = null;
        ArrayList tempList = null;
        //  Connection con = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            // ds = getDataSource(request);
            //if (ds == null || "null".equals(ds)) {
            //  ds = JNDIDataSource.getConnection();
            //}
            //  con = DBConnection.getConnection(datasource);
            CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
            CommonDAO commonDAO = new CommonDAO();
            PrintWriter out = response.getWriter();
            response.setHeader("Cache-Control", "private");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setContentType("text/xml");
            dashBoardList = commonDAO.getDashBoardDetails(ds);
            int id_size = dashBoardList.size();
            int count = 0;
            out.println("<root>");
            if (id_size != 0) {
                out.println("<pensionphase>" + id_size + "</pensionphase>");
                out.println("<noofpersons>" + id_size + "</noofpersons>");
                out.println("<noofassessed>" + id_size + "</noofassessed>");
                out.println("<completedpercent>" + id_size + "</completedpercent>");
                for (count = 0; count < dashBoardList.size(); count++) {
                    WebsiteCommonDTO websiteDTO = (WebsiteCommonDTO) dashBoardList.get(count);
                    out.println("<pensionphase>" + websiteDTO.getPhaseName() + "</pensionphase>");
                    out.println("<noofpersons>" + websiteDTO.getNoofpersons() + "</noofpersons>");
                    out.println("<noofassessed>" + websiteDTO.getNoofassessed() + "</noofassessed>");
                    out.println("<completedpercent>" + websiteDTO.getCompletedpercent() + "</completedpercent>");
                }

            } else {
                out.println("<pensionphase>" + 0 + "</pensionphase>");
                out.println("<noofpersons>" + 0 + "</noofpersons>");
                out.println("<noofassessed>" + 0 + "</noofassessed>");
                out.println("<completedpercent>" + 0 + "</completedpercent>");
            }
            out.println("</root>");

        } catch (SQLException sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }



        return null;
    }

    /**
     *
     * @description this method is used to generate the district wise report for niramaya
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getDistrictReportNiramaya(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String success = "repeat";

        //DataSource ds=getDataSource(request);

        ArrayList niramayareportlist = new ArrayList();
        CommonReportForm niramayareportform = (CommonReportForm) form;
        CommonReportDTO niramayareportdto = new CommonReportDTO();
        CommonReportService surgeryreportservice = CommonReportServiceFactory.getCommonReportServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            BeanUtils.copyProperties(niramayareportdto, niramayareportform);
            niramayareportlist = surgeryreportservice.getNiramayaDistrictReport(ds);
            if (niramayareportlist.isEmpty()) {
                success = "ReportException";
            }
            int mrTotal = 0, cpTotal = 0, multipleTotal = 0;
            Iterator iter = niramayareportlist.iterator();
            while (iter.hasNext()) {
                CommonReportDTO commonreportdto = (CommonReportDTO) iter.next();
                mrTotal = mrTotal + Integer.parseInt(commonreportdto.getDistrictMRCount());
                cpTotal = cpTotal + Integer.parseInt(commonreportdto.getDistrictCEREBRALPALSYCount());
                multipleTotal = multipleTotal + Integer.parseInt(commonreportdto.getDistrictMULTIPLECount());
            }
            BeanUtils.copyProperties(niramayareportform, niramayareportdto);
            request.setAttribute("mrTotal", mrTotal);
            request.setAttribute("cpTotal", cpTotal);
            request.setAttribute("multipleTotal", multipleTotal);
            request.setAttribute("niramayareportlist", niramayareportlist);
            request.setAttribute("niramayareportform", niramayareportform);

            success = "repeat";
        } catch (Exception sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(success);
    }

    /**
     *
     * @description this method is used to generate the Mandal wise report for niramaya
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getMandalReportNiramaya(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String success = "repeat";

        //DataSource ds=getDataSource(request);

        ArrayList niramayareportlist = new ArrayList();
        CommonReportForm niramayareportform = (CommonReportForm) form;
        CommonReportDTO niramayareportdto = new CommonReportDTO();
        CommonReportService surgeryreportservice = CommonReportServiceFactory.getCommonReportServiceImpl();
        String District_Id = request.getParameter("districtid");
        String district = request.getParameter("district");
        request.setAttribute("districtid", District_Id);
        request.setAttribute("district", district);
        niramayareportform.setDistrictid(District_Id);

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            BeanUtils.copyProperties(niramayareportdto, niramayareportform);
            niramayareportlist = surgeryreportservice.getNiramayaMandalReport(ds, District_Id);
            BeanUtils.copyProperties(niramayareportform, niramayareportdto);
            request.setAttribute("niramayareportlist", niramayareportlist);
            if (niramayareportlist.isEmpty()) {
                success = "ReportException";
            }
            int mrTotal = 0, cpTotal = 0, multipleTotal = 0;
            Iterator iter = niramayareportlist.iterator();
            while (iter.hasNext()) {
                CommonReportDTO commonreportdto = (CommonReportDTO) iter.next();
                mrTotal = mrTotal + Integer.parseInt(commonreportdto.getMandalMRCount());
                cpTotal = cpTotal + Integer.parseInt(commonreportdto.getMandalCEREBRALPALSYCount());
                multipleTotal = multipleTotal + Integer.parseInt(commonreportdto.getMandalMULTIPLECount());
            }
            BeanUtils.copyProperties(niramayareportform, niramayareportdto);
            request.setAttribute("mrTotal", mrTotal);
            request.setAttribute("cpTotal", cpTotal);
            request.setAttribute("multipleTotal", multipleTotal);

            success = "repeat";
        } catch (Exception sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(success);
    }

    /**
     *
     * @description this method is used to generate the Mandal wise report for niramaya
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getVillageReportNiramaya(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String success = "repeat";

        //DataSource ds=getDataSource(request);

        ArrayList niramayareportlist = new ArrayList();
        CommonReportForm niramayareportform = (CommonReportForm) form;
        CommonReportDTO niramayareportdto = new CommonReportDTO();
        CommonReportService surgeryreportservice = CommonReportServiceFactory.getCommonReportServiceImpl();
        String District_Id = request.getParameter("districtid");
        String district = request.getParameter("district");
        String mandalId = request.getParameter("mandalid");
        String mandal = request.getParameter("mandal");
        request.setAttribute("districtid", District_Id);
        request.setAttribute("district", district);
        request.setAttribute("mandalId", mandalId);
        request.setAttribute("mandal", mandal);
        niramayareportform.setDistrictid(District_Id);
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            BeanUtils.copyProperties(niramayareportdto, niramayareportform);
            niramayareportlist = surgeryreportservice.getNiramayaVillageReport(ds, District_Id, mandalId);
            BeanUtils.copyProperties(niramayareportform, niramayareportdto);
            request.setAttribute("niramayareportlist", niramayareportlist);
            if (niramayareportlist.isEmpty()) {
                success = "ReportException";
            }
            int mrTotal = 0, cpTotal = 0, multipleTotal = 0;
            Iterator iter = niramayareportlist.iterator();
            while (iter.hasNext()) {
                CommonReportDTO commonreportdto = (CommonReportDTO) iter.next();
                mrTotal = mrTotal + Integer.parseInt(commonreportdto.getVillageMRCount());
                cpTotal = cpTotal + Integer.parseInt(commonreportdto.getVillageCEREBRALPALSYCount());
                multipleTotal = multipleTotal + Integer.parseInt(commonreportdto.getVillageMULTIPLECount());
            }
            BeanUtils.copyProperties(niramayareportform, niramayareportdto);
            request.setAttribute("mrTotal", mrTotal);
            request.setAttribute("cpTotal", cpTotal);
            request.setAttribute("multipleTotal", multipleTotal);

            success = "repeat";
        } catch (Exception sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(success);
    }

    /**
     *
     * @description this method is used to generate the Mandal wise report for niramaya
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getHabitationReportNiramaya(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String success = "repeat";

        //DataSource ds=getDataSource(request);

        ArrayList niramayareportlist = new ArrayList();
        CommonReportForm niramayareportform = (CommonReportForm) form;
        CommonReportDTO niramayareportdto = new CommonReportDTO();
        CommonReportService surgeryreportservice = CommonReportServiceFactory.getCommonReportServiceImpl();
        String District_Id = request.getParameter("districtid");
        String district = request.getParameter("district");
        String mandalId = request.getParameter("mandalid");
        String mandal = request.getParameter("mandal");
        String villageId = request.getParameter("villageid");
        String village = request.getParameter("village");
        request.setAttribute("districtid", District_Id);
        request.setAttribute("district", district);
        request.setAttribute("mandal", mandal);
        request.setAttribute("village", village);
        request.setAttribute("mandalId", mandalId);
        request.setAttribute("villageid", villageId);
        niramayareportform.setDistrictid(District_Id);
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            BeanUtils.copyProperties(niramayareportdto, niramayareportform);
            niramayareportlist = surgeryreportservice.getNiramayaHabitationReport(ds, District_Id, mandalId, villageId);
            BeanUtils.copyProperties(niramayareportform, niramayareportdto);
            request.setAttribute("niramayareportlist", niramayareportlist);
            if (niramayareportlist.isEmpty()) {
                success = "ReportException";
            }
            int mrTotal = 0, cpTotal = 0, multipleTotal = 0;
            Iterator iter = niramayareportlist.iterator();
            while (iter.hasNext()) {
                CommonReportDTO commonreportdto = (CommonReportDTO) iter.next();
                mrTotal = mrTotal + Integer.parseInt(commonreportdto.getHabitationMRCount());
                cpTotal = cpTotal + Integer.parseInt(commonreportdto.getHabitationCEREBRALPALSYCount());
                multipleTotal = multipleTotal + Integer.parseInt(commonreportdto.getHabitationMULTIPLECount());
            }
            BeanUtils.copyProperties(niramayareportform, niramayareportdto);
            request.setAttribute("mrTotal", mrTotal);
            request.setAttribute("cpTotal", cpTotal);
            request.setAttribute("multipleTotal", multipleTotal);

            success = "repeat";
        } catch (Exception sADAREMException) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(success);
    }

    // DistrictWise Personal Report for Niramaya
    public ActionForward getdistrictNiramayaReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        CommonReportForm surgeryform = (CommonReportForm) form;
        CommonReportDTO surgeryreportdto = new CommonReportDTO();
        ArrayList reportlist = new ArrayList();
        //DataSource datasource= getDataSource(request);



        ActionMessages actionMessages = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            String District_Id = request.getParameter("districtid");
            String disabilityName = request.getParameter("disability");
            request.setAttribute("disabilityName", disabilityName);
            CommonPersonalReportService reportservice = CommonPersonalReportServiceFactory.getCommonPersonalReportServiceImpl();
            surgeryform.setDistrictid(District_Id);
            BeanUtils.copyProperties(surgeryreportdto, surgeryform);
            int totalresults = Integer.parseInt(request.getParameter("distcount"));
            String totresstr = String.valueOf(totalresults);
            reportlist = reportservice.getNiramayaDistrictPersonalDetails(ds, surgeryreportdto, 1, totalresults, disabilityName);
            session.setAttribute("arraylist", reportlist);
            String actionnamewithdo = request.getRequestURI();
            String actionnamewithdoarr[] = actionnamewithdo.split("/");
            String url = actionnamewithdoarr[2] + "?getdistrictNiramayaReport=getdistrictNiramayaReport&districtid=" + District_Id + "&distcount=" + totresstr;
            request.setAttribute("url", url);
            CommonDAO commonDAO = new CommonDAO();
            ArrayList startAndEndList = new ArrayList();
            startAndEndList = commonDAO.populateStartEnd(request, totalresults);
            int start = 0;
            int endrange = 0;
            start = ((Integer) startAndEndList.get(0)).intValue();
            endrange = ((Integer) startAndEndList.get(1)).intValue();
            reportlist = reportservice.getNiramayaDistrictPersonalDetails(ds, surgeryreportdto, start, endrange, disabilityName);


            request.setAttribute("districtid", District_Id);
            request.setAttribute("reportlist", reportlist);
            session.setAttribute("arraylist1", reportlist);

            if (!reportlist.isEmpty()) {
                success = "repeat";
            } else {
                success = "failure";
            }

        } catch (SQLException sqlEx) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sqlEx) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }
        return mapping.findForward(success);
    }

    // MandalWise Personal Report for Niramaya
    public ActionForward getMandalNiramaya(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        CommonReportForm surgeryform = (CommonReportForm) form;
        CommonReportDTO surgeryreportdto = new CommonReportDTO();
        ArrayList reportlist = new ArrayList();
        //DataSource datasource= getDataSource(request);


        ActionMessages actionMessages = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            String District_Id = request.getParameter("dID");
            String mID = request.getParameter("mID");
            String disabilityName = request.getParameter("disability");
            request.setAttribute("disabilityName", disabilityName);
            CommonPersonalReportService reportservice = CommonPersonalReportServiceFactory.getCommonPersonalReportServiceImpl();
            surgeryform.setDistrictid(District_Id);
            surgeryform.setMandalid(mID);
            BeanUtils.copyProperties(surgeryreportdto, surgeryform);
            int totalresults = Integer.parseInt(request.getParameter("mcount"));
            String totresstr = String.valueOf(totalresults);
            reportlist = reportservice.getNiramayaMandalPersonalDetails(ds, surgeryreportdto, 1, totalresults, disabilityName);
            session.setAttribute("arraylist", reportlist);
            String actionnamewithdo = request.getRequestURI();
            String actionnamewithdoarr[] = actionnamewithdo.split("/");
            String url = actionnamewithdoarr[2] + "?getMandalNiramaya=getMandalNiramaya&dID=" + District_Id + "&mID=" + mID + "&mcount=" + totresstr;
            request.setAttribute("url", url);
            CommonDAO commonDAO = new CommonDAO();
            ArrayList startAndEndList = new ArrayList();
            startAndEndList = commonDAO.populateStartEnd(request, totalresults);
            int start = 0;
            int endrange = 0;
            start = ((Integer) startAndEndList.get(0)).intValue();
            endrange = ((Integer) startAndEndList.get(1)).intValue();
            reportlist = reportservice.getNiramayaMandalPersonalDetails(ds, surgeryreportdto, start, endrange, disabilityName);


            request.setAttribute("districtid", District_Id);
            request.setAttribute("mID", mID);
            request.setAttribute("reportlist", reportlist);
            session.setAttribute("arraylist1", reportlist);

            if (!reportlist.isEmpty()) {
                success = "repeat";
            } else {
                success = "failure";
            }

        } catch (SQLException sqlEx) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sqlEx) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }
        return mapping.findForward(success);
    }

    // VillageWise Personal Report for Niramaya
    public ActionForward getVillageNiramaya(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        CommonReportForm surgeryform = (CommonReportForm) form;
        CommonReportDTO surgeryreportdto = new CommonReportDTO();
        ArrayList reportlist = new ArrayList();
        //DataSource datasource= getDataSource(request);


        ActionMessages actionMessages = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            String District_Id = request.getParameter("dID");
            String mID = request.getParameter("mID");
            String vID = request.getParameter("vID");
            String disabilityName = request.getParameter("disability");
            request.setAttribute("disabilityName", disabilityName);
            CommonPersonalReportService reportservice = CommonPersonalReportServiceFactory.getCommonPersonalReportServiceImpl();
            surgeryform.setDistrictid(District_Id);
            surgeryform.setMandalid(mID);
            surgeryform.setVillageid(vID);
            BeanUtils.copyProperties(surgeryreportdto, surgeryform);
            int totalresults = Integer.parseInt(request.getParameter("vcount"));
            String totresstr = String.valueOf(totalresults);
            reportlist = reportservice.getNiramayaVillagePersonalDetails(ds, surgeryreportdto, 1, totalresults, disabilityName);
            session.setAttribute("arraylist", reportlist);
            String actionnamewithdo = request.getRequestURI();
            String actionnamewithdoarr[] = actionnamewithdo.split("/");
            String url = actionnamewithdoarr[2] + "?getVillageNiramaya=getVillageNiramaya&dID=" + District_Id + "&mID=" + mID + "&vID=" + vID + "&vcount=" + totresstr;
            request.setAttribute("url", url);
            CommonDAO commonDAO = new CommonDAO();
            ArrayList startAndEndList = new ArrayList();
            startAndEndList = commonDAO.populateStartEnd(request, totalresults);
            int start = 0;
            int endrange = 0;
            start = ((Integer) startAndEndList.get(0)).intValue();
            endrange = ((Integer) startAndEndList.get(1)).intValue();
            reportlist = reportservice.getNiramayaVillagePersonalDetails(ds, surgeryreportdto, start, endrange, disabilityName);


            request.setAttribute("districtid", District_Id);
            request.setAttribute("mID", mID);
            request.setAttribute("vID", vID);
            request.setAttribute("reportlist", reportlist);
            session.setAttribute("arraylist1", reportlist);

            if (!reportlist.isEmpty()) {
                success = "repeat";
            } else {
                success = "failure";
            }

        } catch (SQLException sqlEx) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sqlEx) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }
        return mapping.findForward(success);
    }

    // HabitationWise Personal Report for Niramaya
    public ActionForward getHabitationNiramaya(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        CommonReportForm surgeryform = (CommonReportForm) form;
        CommonReportDTO surgeryreportdto = new CommonReportDTO();
        ArrayList reportlist = new ArrayList();
        //DataSource datasource= getDataSource(request);



        ActionMessages actionMessages = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            String District_Id = request.getParameter("dID");
            String mID = request.getParameter("mID");
            String vID = request.getParameter("vID");
            String hID = request.getParameter("hID");
            String disabilityName = request.getParameter("disability");
            request.setAttribute("disabilityName", disabilityName);
            CommonPersonalReportService reportservice = CommonPersonalReportServiceFactory.getCommonPersonalReportServiceImpl();
            surgeryform.setDistrictid(District_Id);
            surgeryform.setMandalid(mID);
            surgeryform.setVillageid(vID);
            surgeryform.setHabitationid(hID);
            BeanUtils.copyProperties(surgeryreportdto, surgeryform);
            int totalresults = Integer.parseInt(request.getParameter("hcount"));
            String totresstr = String.valueOf(totalresults);
            reportlist = reportservice.getNiramayaHabitationPersonalDetails(ds, surgeryreportdto, 1, totalresults, disabilityName);
            session.setAttribute("arraylist", reportlist);
            String actionnamewithdo = request.getRequestURI();
            String actionnamewithdoarr[] = actionnamewithdo.split("/");
            String url = actionnamewithdoarr[2] + "?getHabitationNiramaya=getHabitationNiramaya&dID=" + District_Id + "&mID=" + mID + "&vID=" + vID + "&hID=" + hID + "&hcount=" + totresstr;
            request.setAttribute("url", url);
            CommonDAO commonDAO = new CommonDAO();
            ArrayList startAndEndList = new ArrayList();
            startAndEndList = commonDAO.populateStartEnd(request, totalresults);
            int start = 0;
            int endrange = 0;
            start = ((Integer) startAndEndList.get(0)).intValue();
            endrange = ((Integer) startAndEndList.get(1)).intValue();
            reportlist = reportservice.getNiramayaHabitationPersonalDetails(ds, surgeryreportdto, start, endrange, disabilityName);


            request.setAttribute("districtid", District_Id);
            request.setAttribute("mID", mID);
            request.setAttribute("vID", vID);
            request.setAttribute("hID", hID);
            request.setAttribute("reportlist", reportlist);
            session.setAttribute("arraylist1", reportlist);

            if (!reportlist.isEmpty()) {
                success = "repeat";
            } else {
                success = "failure";
            }

        } catch (SQLException sqlEx) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sqlEx) {
            success = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }
        return mapping.findForward(success);
    }

    /**
     * this method will be used to get status of person details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward viewPhoto(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "success";

        String personCode = null;
        String name = null;
        String districtName = null;



        personCode = request.getParameter("personcode");
        name = request.getParameter("name");
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            
            CommonDAOImpl comObj = new CommonDAOImpl();
            HashMap GEODtls = new HashMap();
			GEODtls=comObj.getGEODetailsbySADAREMID(personCode);
			String personcodeDistrictId = GEODtls.get("districtid").toString();
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            districtName = territoryservice.getDistrictsName(ds, personcodeDistrictId);
            if (personCode != null && districtName != null) {
                CommonDetails commonDetails = new CommonDetails();
                CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
                String url = getServlet().getServletContext().getRealPath("/");
                commonDetails.copyPhotoDtoRelativePath(personCode, districtName, request, url);
                boolean uploadPhotoFlag = commonservice.checkUploadPhoto(ds, personCode);
                request.setAttribute("name", name);
                request.setAttribute("personcode", personCode);
                if (uploadPhotoFlag) {
                    request.setAttribute("name", name);
                    request.setAttribute("personcode", personCode);
                    request.setAttribute("districtName", districtName);
                } else {
                    request.setAttribute("msg", "Photo is Not Uploded");

                }
            }

        } catch (SQLException sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }

        return mapping.findForward(target);
    }

    /**
     * this method will be used to get status of person details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward selectListDeatils(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "repeat";
        ArrayList districtlist = new ArrayList();
        ArrayList mandallist = new ArrayList();
        ArrayList panchayatlist = new ArrayList();
        ArrayList villagelist = new ArrayList();
        ArrayList habitationlist = new ArrayList();

        String districtid = null;
        String mandalid = null;
        String panchayatid = null;
        String habitationid = null;
        String villageid = null;

        /*
         * Creating instance for Services
         */
        TerritoryService territoryservice =
                TerritoryServiceFactory.getTerritoryServiceImpl();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            TerritoryForm territoryForm = (TerritoryForm) form;
            TerritoryDTO territorydto = new TerritoryDTO();

            BeanUtils.copyProperties(territorydto, territoryForm);
            //DataSource ds= getDataSource(request);

            districtid = territoryForm.getDistrict_id();
            mandalid = territoryForm.getMandal_id();
            panchayatid = territoryForm.getPanchayat_id();
            villageid = territoryForm.getVillage_id();
            habitationid = territoryForm.getHabitation_id();

            districtlist = territoryservice.getDistricts(ds);
            if (districtid != null && districtid != "") {
                mandallist = territoryservice.getMandals(ds, districtid);
                territoryForm.setMandallist(mandallist);
            }
            if (mandalid != null && mandalid != "") {
                panchayatlist = territoryservice.getPanchayats(ds, districtid, mandalid);
                territoryForm.setPanchayatlist(panchayatlist);
            }
            if (mandalid != null && mandalid != "") {
                villagelist = territoryservice.getVillages(ds, districtid, mandalid, panchayatid);
                territoryForm.setVillagelist(villagelist);
            }
            if (mandalid != null && mandalid != "" && villageid != null && villageid != "") {
                habitationlist =
                        territoryservice.getHabitations(ds, districtid, mandalid, panchayatid, villageid);
                territoryForm.setHabitationlist(habitationlist);
            }

            territoryForm.setDistrictlist(districtlist);

            /*
             * throwing our own exception in catch block
             */
        } catch (SQLException sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method will dispaly the State Wise Cumulative Report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getcumulativeReport(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException,
            Exception {
        String target = "success";
        ArrayList cumulativeReportList = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            cumulativeReportList = new ArrayList();
            CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
            cumulativeReportList = commonservice.getcumulativeReport(ds);
            target = "success";
            if (cumulativeReportList.isEmpty() && cumulativeReportList == null) {
                request.setAttribute("message", "No cumulativeReport details");
            }
            request.setAttribute("cumulativeReportList", cumulativeReportList);

        } catch (SQLException sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }
        return mapping.findForward(target);

    }

    public ActionForward SearchPension(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "success";
        DataSource datasource = null;
        String[] personCode = new String[2];
        String checkPersoncode = null;
        String success = "repeat";
        String failure = "failure";
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }

        //DataSource datasource=getDataSource(request);

        String pensioncardno = request.getParameter("personcode");
        String disId = request.getParameter("districtid");

        CommonDAO cdao = new CommonDAO();
        personCode = cdao.getPersionCodes(ds, pensioncardno, disId);
        //get Person Code .
        if (personCode[0] == null) {
            request.setAttribute("searchmsg", "Not Entered");
            target = "fallure";
            return mapping.findForward(target);
        } else if (personCode[1].toString().trim().equalsIgnoreCase("Edit")) {
            request.setAttribute("searchmsg", "Part A entered");
            target = "fallure";
            return mapping.findForward(target);

        }

        /// personCode = request.getParameter("personcode");
        CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
        request.setAttribute("personCode", personCode[0]);
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(ds)) {
                datasource = JNDIDataSource.getConnection();
            }
            checkPersoncode = commonservice.checkPersonCodeForSearch(datasource, personCode[0]);
            if (checkPersoncode == null) {
                request.setAttribute("searchmsg", "Not Entered");
                target = "fallure";

            } else {
                int totalDisability = commonservice.getTotalPercentage(datasource, personCode[0]);
                request.setAttribute("totalDisability", totalDisability);
            }

        } catch (SQLException sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        } catch (Exception sqlEx) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }

        return mapping.findForward(target);
    }
    public ActionForward searchRationCardDetaails(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "PersoncodeFailure";
        request.setAttribute("msg","Access denied");

//        String failure = "searchfailure";
//
//  
//
//
//        String rationcard = null;
//        String checkPersoncode = null;
//
//        rationcard = request.getParameter("rationcard");
//        CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
//        request.setAttribute("rationcard", rationcard);
//
//        PartAForm partAForm = (PartAForm) form;
//
//        String id = request.getParameter("rationcard");
//        String type = request.getParameter("type");
//      
//
// 
//        String personcode = request.getParameter("personcode");
//
//        String rationcardnumber = request.getParameter("rationcard");
//
//
//        String aadhracard = request.getParameter("aadhracard");
//
//
//        String pensionid = request.getParameter("pensionid");
//        String districtid = request.getParameter("");
//
//
//        try {
//            ds = getDataSource(request);
//            if (ds == null || "null".equals(ds)) {
//                ds = JNDIDataSource.getConnection();
//            }
// 
//            ArrayList rationcardDetails = new ArrayList();
//            CommonDAO CommonDAO = new CommonDAO();
//            if (type.equals("2")) {
//
//                rationcardDetails = CommonDAO.getrationCardDetails(ds, "", type, rationcardnumber, "", "", "", partAForm, request);
//
//
//                request.setAttribute("rationcardnumber", "rationcardnumber");
//                if (rationcardDetails.size() > 0) {
//
//                    request.setAttribute("rationcardDetails", rationcardDetails);
//
//                } else {
//                    request.setAttribute("searchRationcard", " Assessment not Done For the  RationCard Number");
//
//                    target = "fallure";
//
//                 
//                }
//            } else if (type.equals("4")) {
//
//                rationcardDetails = CommonDAO.getrationCardDetails(ds, "", type, "", aadhracard, "", "", partAForm, request);
//                request.setAttribute("aadhracardnumber", "aadhracardnumber");
//                if (rationcardDetails.size() > 0) {
//
//                    request.setAttribute("rationcardDetails", rationcardDetails);
//                } else {
//                    request.setAttribute("searchaadhracard", "Assessment not Done For the Aadhar Card Number");
//
//                    target = "fallure";
//                  
//
//
//                }
//            }
//
//
//            request.setAttribute("type", type);
//
//      
//
//        } catch (Exception sqlEx) {
//            sqlEx.printStackTrace();
//
//
//        }

        return mapping.findForward(target);
    }

    public ActionForward pensioncardDetails(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "searchPensionCardDetails";

        CommonDAO CommonDAO = new CommonDAO();
        String pensionid = null;
        String checkPersoncode = null;

        pensionid = request.getParameter("pensionid");
        CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
        request.setAttribute("pensionid", pensionid);

        PartAForm partAForm = (PartAForm) form;

      
        ArrayList districtList = new ArrayList();
        ArrayList rationcardDetails = new ArrayList();
        
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            districtList = CommonDAO.getDistrictList(ds);
            if (districtList != null && districtList.size() > 0) {
                partAForm.setDistrictList(districtList);


            }
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();


        }

        return mapping.findForward(target);
    }

    public ActionForward searchPensionCardDetaails(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "searchPensionCardDetails";

        CommonDAO CommonDAO = new CommonDAO();
        String pensionid = null;
        String checkPersoncode = null;

        pensionid = request.getParameter("pensionid");
        CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
        request.setAttribute("pensionid", pensionid);

        PartAForm partAForm = (PartAForm) form;
        ArrayList districtList = new ArrayList();
        districtList = CommonDAO.getDistrictList(ds);


  

        if (districtList != null && districtList.size() > 0) {
            partAForm.setDistrictList(districtList);


        }
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            ArrayList rationcardDetails = new ArrayList();
            rationcardDetails = CommonDAO.getrationCardDetails(ds, "", "", "", "", partAForm.getPensionid(), partAForm.getDistrict_id(), partAForm, request);

            if (rationcardDetails.size() > 0) {
                request.setAttribute("rationcardDetails", rationcardDetails);
            } else {
                request.setAttribute("noData", "Assessment not Done For the Pension ID");
            }



        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();


        }

        return mapping.findForward(target);
    }
}
