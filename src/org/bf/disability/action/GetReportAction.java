package org.bf.disability.action;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.dto.CertificateDTO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.service.ReportService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.ReportServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;
import org.bf.disability.form.PartAForm;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PrintDetailsDAO;

/**
 * This GetReportAction is used for generation of certificate and identity
 * card based on the territories and personcode selected.
 * @author svsganesh
 * @version 1.0
 */
public class GetReportAction extends BaseAction {

    /**
     * This method is used for report generation
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    @Override
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        ActionMessages actionMessages = null;
        String target = "repeat";
        ArrayList districtlist = new ArrayList();
        ArrayList mandallist = new ArrayList();
        ArrayList panchayatlist = new ArrayList();
        ArrayList villagelist = new ArrayList();
        ArrayList habitationlist = new ArrayList();
        ArrayList personcodelist = new ArrayList();
        ArrayList personlist = new ArrayList();
        ArrayList reportlist = new ArrayList();
        String certificateType = "", category = "0";
        //ArrayList Idcardlist = new ArrayList();
        ArrayList rejectedlist = null;
        ArrayList suggestionlist = new ArrayList();
        ArrayList rejectlist = new ArrayList();
        //ArrayList conditionlist = new ArrayList();
        String districtid = null;
        String mandalid = null;
        String panchayatid = null;
        String habitationid = null;
        String villageid = null;
        String personcode = null;
        String disabilityName = null;
        String districtName = null;
        DataSource ds = null;
        double totaldisability;
        /*
         * Creating instance for Services
         */
        TerritoryService territoryservice =
                TerritoryServiceFactory.getTerritoryServiceImpl();
        ReportService reportservice =
                ReportServiceFactory.getReportServiceImpl();
        /*
         * Creating instance for form(TerritoryForm)
         */
        String conditionlistCheckFlag = "false";
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String selectFlow = "OUTERPROCESS";
            if (request.getParameter("selectFlow") != null) {
                selectFlow = request.getParameter("selectFlow");
            } else if (request.getParameter("selectedValue") != null) {
                selectFlow = request.getParameter("selectedValue");
            }
            request.setAttribute("selectedValue", selectFlow);

            super.execute(mapping, form, request, response);
            PartAForm partA = new PartAForm();
            rejectedlist = new ArrayList();
            TerritoryForm territoryForm = (TerritoryForm) form;
            TerritoryDTO territorydto = new TerritoryDTO();
            CertificateDTO certificatedto = new CertificateDTO();
            BeanUtils.copyProperties(territorydto, territoryForm);
            //DataSource ds= getDataSource(request);


            HttpSession session = request.getSession();
            String disabilityid = null;
            String status = request.getParameter("status");
            districtid = territoryForm.getDistrict_id();
            mandalid = territoryForm.getMandal_id();
            panchayatid = territoryForm.getPanchayat_id();
            villageid = territoryForm.getVillage_id();
            habitationid = territoryForm.getHabitation_id();

            personcode = territoryForm.getPersoncode();
            if (personcode == null || "".equals(personcode)) {
                personcode = (String) session.getAttribute("personcode");
            }
            //personcode = "19242030440223001";
            String personstatus = territoryForm.getPersonstatus();
            String enterpersoncode = request.getParameter("enterpersoncode");
            String print = (String) request.getParameter("print");
            territoryForm.setDifferenceFlag(print);
            String checkTerritoryFlag = request.getParameter("checkTerritoryFlag");
            String card = (String) request.getParameter("card");
//            if (territoryForm.getDifferenceFlag() == null) {
//                territoryForm.setDifferenceFlag("idcard");
//            }
            String certificate = (String) request.getParameter("certificate");
            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {
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
                if (mandalid != null && mandalid != "" && villageid != null && villageid != "" && habitationid != null && habitationid != "" && personstatus != null && !personstatus.equals("0")) {
                    personcodelist = territoryservice.getPersonCode(districtid, mandalid, panchayatid, villageid, habitationid, personstatus, ds);
                    territoryForm.setPersoncodelist(personcodelist);
                }
                territoryForm.setDistrictlist(districtlist);
            } else if (status.equals("finish")) {
                TerritoryDTO territoryDTO = null;
                for (Iterator iter = districtlist.iterator(); iter.hasNext();) {
                    territoryDTO = (TerritoryDTO) iter.next();
                    if (territoryDTO.getDistrict_id().equals(districtid)) {
                        session.setAttribute("district_id", territoryDTO.getDistrict_id());
                        session.setAttribute("district_name", territoryDTO.getDistrict_name());
                    }
                }
                for (Iterator iter = mandallist.iterator(); iter.hasNext();) {
                    territoryDTO = (TerritoryDTO) iter.next();
                    if (territoryDTO.getMandal_id().equals(mandalid)) {
                        session.setAttribute("mandal_id", territoryDTO.getMandal_id());
                        session.setAttribute("mandal_name", territoryDTO.getMandal_name());
                    }
                }
                for (Iterator iter = panchayatlist.iterator(); iter.hasNext();) {
                    territoryDTO = (TerritoryDTO) iter.next();
                    if (territoryDTO.getPanchayat_id().equals(panchayatid)) {
                        session.setAttribute("panchayat_id", territoryDTO.getPanchayat_id());
                        session.setAttribute("panchayat_name", territoryDTO.getPanchayat_name());
                    }
                }
                for (Iterator iter = villagelist.iterator(); iter.hasNext();) {
                    territoryDTO = (TerritoryDTO) iter.next();
                    if (territoryDTO.getVillage_id().equals(villageid)) {
                        session.setAttribute("village_id", territoryDTO.getVillage_id());
                        session.setAttribute("village_name", territoryDTO.getVillage_name());
                    }
                }
                for (Iterator iter = habitationlist.iterator(); iter.hasNext();) {
                    territoryDTO = (TerritoryDTO) iter.next();
                    if (territoryDTO.getHabitation_id().equals(habitationid)) {
                        session.setAttribute("habitation_id", territoryDTO.getHabitation_id());
                        session.setAttribute("habitation_name", territoryDTO.getHabitation_name());
                    }
                }
                session.setAttribute("personcode", personcode);
                /*
                 * checking pwd Status
                 */
                if (personstatus != null) {
                    if (personstatus.equals("Eligible")) {
                        reportlist = reportservice.getDetailsForCertificate(ds, territoryForm, personcode);
                        if (reportlist.size() == 0) {
                            target = "failure";
                        } else {
                            String causeOfDisabilityFlag = "false";
                            Iterator iter = reportlist.iterator();
                            iter.hasNext();
                            territorydto = (TerritoryDTO) iter.next();
                            disabilityid = territorydto.getTypeofdisability();
                            totaldisability = territorydto.getDisabilityvalue();
                            disabilityName = territorydto.getDisabilitytype();
                            request.setAttribute("categoryId", territorydto.getCategory());
                            /*
                             * checking for displaying names
                             *
                             */
                            if ((null != territorydto.getTotalcauseofdisabilities() && !"".equals(territorydto.getTotalcauseofdisabilities()))) {
                                causeOfDisabilityFlag = "true";
                            }
                            if ((null != territorydto.getPhysicalImpairmentPhysicalRequirementList() && !"".equals(territorydto.getPhysicalImpairmentPhysicalRequirementList()))
                                    || (null != territorydto.getVisualImpairmentPhysicalRequirementList() && !"".equals(territorydto.getVisualImpairmentPhysicalRequirementList()))
                                    || (null != territorydto.getHearingImpairmentPhysicalRequirementList() && !"".equals(territorydto.getHearingImpairmentPhysicalRequirementList()))
                                    || (null != territorydto.getMentalRetardationPhysicalRequirementList() && !"".equals(territorydto.getMentalRetardationPhysicalRequirementList()))) {
                                conditionlistCheckFlag = "true";
                            }

                            /*
                             * checking  percentage of the pwd
                             */
                            if (territorydto.getDisabilityvalue() == 0.0) {
                                target = "failure";
                            } else {
                                //totaldisability=territorydto.getDisabilityvalue();
                                /*
                                 * getting telugu names for particular pwd
                                 */
                                //territoryDTO = reportservice.getTeluguName(ds, personcode);
                                String telugupersonname = territorydto.getTelugupersonname();
                                String telugufathername = territorydto.getTelugufathername();
                                String disabilitytype = territorydto.getDisabilitytype();
                                String genderInTelugu = territorydto.getGenderInTelugu();
                                String disabilityduration = territorydto.getDisabilityduration();
                                String disabilitytypeInTelugu = territorydto.getDisabilityTypeInTelugu();
                                String apflag = territorydto.getFlag();
                                /*
                                 *for telugu name we can not directly set Form to jsp
                                 * so we have to put every telugu field in session
                                 */
                                request.setAttribute("causeOfDisabilityFlag", causeOfDisabilityFlag);
                                // session.setAttribute("disabilityreason", disabilityreason);
                                request.setAttribute("disabilityduration", disabilityduration);
                                request.setAttribute("disabilitytype", disabilitytype);
                                request.setAttribute("gender", genderInTelugu);
                                request.setAttribute("telugupersonname", telugupersonname);
                                request.setAttribute("telugufathername", telugufathername);
                                request.setAttribute("disabilitytypeInTelugu", disabilitytypeInTelugu);
                                request.setAttribute("apflag", apflag);
                                /*
                                 * getting details of particular pwd for Id Card
                                 */
                                // Idcardlist = reportservice.getDetaiilsForIdCard(ds, territoryForm, personcode);
                                CommonDAO commonDAO = new CommonDAO();
                                double eligiblePercentage = commonDAO.getDisabilityPercentage(ds, disabilityid);
                                // request.setAttribute("Idcardlist", Idcardlist);
                                if (disabilityid.equals("1") && totaldisability < eligiblePercentage) {
                                    suggestionlist = reportservice.getLocomotorData(ds, personcode);
                                } else if (disabilityid.equals("2") && totaldisability < eligiblePercentage) {
                                    suggestionlist = reportservice.getVisualData(ds, personcode);
                                } else if (disabilityid.equals("3") && totaldisability < eligiblePercentage) {
                                    suggestionlist = reportservice.getHearingData(ds, personcode);
                                } else if (disabilityid.equals("4") && totaldisability < eligiblePercentage) {
                                    suggestionlist = reportservice.getMentalRetardationData(ds, personcode);
                                } else if (disabilityid.equals("5") && totaldisability < eligiblePercentage) {
                                    suggestionlist = reportservice.getMetalillnessData(ds, personcode);
                                } else if (disabilityid.equals("6") && totaldisability < eligiblePercentage) {
                                    suggestionlist = reportservice.getMultipleData(ds, personcode);
                                }

                                session.setAttribute("reportlist", reportlist);
                                request.setAttribute("suggestionlist", suggestionlist);
                                request.setAttribute("conditionlistCheckFlag", conditionlistCheckFlag);
                                request.setAttribute("certificatewithidcard", (String) request.getParameter("certificatewithidcard"));
                                request.setAttribute("checkTerritoryFlag", checkTerritoryFlag);
                                // target="finish";
                            }
                        }
                        if (certificate != null) {
                            CommonDAO commonDAO = new CommonDAO();
                            double eligiblePercentage = commonDAO.getDisabilityPercentage(ds, disabilityid);

                            if (territorydto.getDisabilityvalue() < eligiblePercentage) {
                                target = "rejectedcertificate";
                            } else {
                                if (disabilityName != null && !"".equals(disabilityName)) {
                                    if ("Physical(Locomotor/Orthopaedic) Disability".equals(disabilityName) || "Physically Disabled".equals(disabilityName)) {
                                        target = "physicallimpairmentcertificate";
                                    } else if ("Visual Impairment".equals(disabilityName) || "Visual Disabled".equals(disabilityName)) {
                                        target = "visualimpairmentcertificate";
                                    } else if ("Hearing Impairment".equals(disabilityName)) {
                                        target = "hearingimpairmentcertificate";
                                    } else if ("Mental Retardation".equals(disabilityName)) {
                                        target = "mentalretardationcertificate";
                                    } else if ("Mental Illness".equals(disabilityName)) {
                                        target = "mentalillnesscertificate";
                                    } else if ("Multiple disabilities".equals(disabilityName)) {
                                        target = "multipledisabilitycertificate";
                                    } else {
                                        target = "finish";
                                    }
                                }

                                //target="finish";
                            }
                        }
                        if (card != null) {
                            CommonDAO commonDAO = new CommonDAO();
                            double eligiblePercentage = commonDAO.getDisabilityPercentage(ds, disabilityid);

                            if (territorydto.getDisabilityvalue() >= eligiblePercentage) {
                                target = "finish";
                            } else {
                                target = "failure";
                            }
                        }
                        if (print != null) {
                            if (print.equals("certificateprint")) {
                                if (disabilityName != null && !"".equals(disabilityName)) {
                                    if ("Physical(Locomotor/Orthopaedic) Disability".equals(disabilityName) || "Physically Disabled".equals(disabilityName)) {
                                        target = "physicalcertificateprint";
                                    } else if ("Visual Impairment".equals(disabilityName) || "Visual Disabled".equals(disabilityName)) {
                                        target = "visualcertificateprint";
                                    } else if ("Hearing Impairment".equals(disabilityName)) {
                                        target = "hearingcertificateprint";
                                    } else if ("Mental Retardation".equals(disabilityName)) {
                                        target = "mentalretardationcertificateprint";
                                    } else if ("Mental Illness".equals(disabilityName)) {
                                        target = "mentalillnesscertificateprint";
                                    } else if ("Multiple disabilities".equals(disabilityName)) {
                                        target = "multipledisabilitycertificateprint";
                                    } else {
                                        target = "certificate";
                                    }
                                }
                                //target="certificateprint";
                            }
                            if (print.equals("rejectedcertificateprint")) {
                                target = "rejectedcertificateprint";
                            } else if (print.equals("idcardprint")) {
                                target = "idcardprint";
                            }
                        }
                    } else {
                        /*
                         * getting rejected pwd details
                         */
                        rejectedlist = reportservice.getRejectedData(ds, personcode);
                        if (rejectedlist != null && !rejectedlist.isEmpty() && rejectedlist.size() > 0) {
                            Iterator iterator = rejectedlist.iterator();
                            iterator.hasNext();
                            certificatedto = (CertificateDTO) iterator.next();
                            disabilityid = certificatedto.getDisabilityidforrejecte();
                            districtName = certificatedto.getDistrictname();
                            if (disabilityid.equals("1")) {
                                rejectlist = reportservice.getRejectedLocomotorData(ds, personcode);
                            } else if (disabilityid.equals("2")) {
                                rejectlist = reportservice.getRejectedVisualData(ds, personcode);
                            } else if (disabilityid.equals("3")) {
                                rejectlist = reportservice.getRejectedHearingData(ds, personcode);
                            } else if (disabilityid.equals("4")) {
                                rejectlist = reportservice.getRejectedMentalRetardationData(ds, personcode);
                            } else if (disabilityid.equals("5")) {
                                rejectlist = reportservice.getRejectedMetalillnessData(ds, personcode);
                            }

                            String apflag = certificatedto.getFlag();
                            request.setAttribute("apflag", apflag);



                            session.setAttribute("rejectedlist", rejectedlist);
                            session.setAttribute("rejectlist", rejectlist);

                            target = "rejectecertificateforpersoncode";
                            if (print != null)
                            {
                                if (print.equals("rejectedcertificatepersoncodeprint"))
                                {
                                	DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                                    Date date = new Date();
                                    request.setAttribute("Todaydate", dateFormat.format(date));
                                    target = "rejectedcertificateforpersoncodeprint";
                                }
                                if (print.equals("idcard"))
                                {
                                    target = "failure";
                                }
                            }
                        } else {
                            target = "failure";
                        }
                    }
                    if (personcode != null && districtName != null) {
                        CommonDetails commonDetails = new CommonDetails();
                        String url = getServlet().getServletContext().getRealPath("/");
                        commonDetails.copyPhotoDtoRelativePath(personcode, districtName, request, url);
                    }
                }
            }
            String printType = request.getParameter("print");
            if (printType != null && printType.equals("idcardprint")) {
                certificateType = "ID";
            } else if (printType != null && printType.equals("certificateprint")) {
                certificateType = "CT";
            }


            if (!certificateType.equals("")) {
                String loginId = (String) session.getAttribute("loginid");
                String campIdValue = session.getAttribute("campId").toString();
                String distId = session.getAttribute("districtId").toString();
                category = "1";
                if (request.getParameter("selectedValue") != null) {
                    category = request.getParameter("selectedValue").toString();
                    if (category.equals("appellate")) {
                        category = "2";
                    } else if (category.equals("temporay")) {
                        category = "3";
                    } else if (category.equals("OUTERPROCESS")) {
                        category = "0";
                    } else {
                        category = "1";
                    }
                } else {
                    category = "0";
                }

                PrintDetailsDAO printDetailsDAO = new PrintDetailsDAO();
                //PrintDetailsService printDetailsService = PrintDetailsServiceFactory.getPrintDetailsServiceImpl();
                printDetailsDAO.insertCertificatePrintDetails1(ds, personcode, category, certificateType, loginId, campIdValue, distId);
                category = "";
                session.removeAttribute("selectedValue");
                session.removeAttribute("categoryIds");
            }
            /*
             * throwing our own exception in catch block
             */
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
}

