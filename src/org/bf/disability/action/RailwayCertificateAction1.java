package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Constants.CertificateConstants;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.DoctorsInformationDAO;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.DoctorsInformationDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.service.ReportService;
import org.bf.disability.servicefactory.ReportServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @description this action class is used to pulate the data related to generate the certificate and id card of the disability person based on the person code
 * @author bapinaidu.t
 * @version 1.0
 */
public class RailwayCertificateAction1 extends BaseAction {

    /**
     *
     *@description this method is used to pulate the data related to generate the certificate and id card of the disability person based on the person code
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        /*
         * Creating instance for  services
         */
        ReportService reportservice = ReportServiceFactory.getReportServiceImpl();
        TerritoryDTO territorydto = new TerritoryDTO();
        TerritoryDAO territorydao = new TerritoryDAO();
        /*
         * Creating instance for form(TerritoryForm)
         */
        request.getSession().removeAttribute("dr_exists");
        TerritoryForm territoryForm = (TerritoryForm) form;
        ActionMessages actionMessages = null;
        boolean id = false;
        String target = "success";
        String flag;
        String disabilityid = null;
        String personcode = null;
        String personcodestatus = null;
        double totaldisability;
        String disabilityName = null;
        DataSource ds = null;
        String multipledisabilityid = null;
        boolean railwaycertificate;
        HttpSession session = request.getSession();
        //   DoctorsInformationDTO dto=new DoctorsInformationDTO();
        //DataSource ds=getDataSource(request);

        session.removeAttribute("Mdspecialistprefix");
        session.removeAttribute("des");
        ArrayList rejectedlist = new ArrayList();
        ArrayList reportlist = null;
        ArrayList Idcardlist = null;
        ArrayList personlist = null;
        ArrayList suggestionlist = null;
        String conditionlistCheckFlag = "false";
        String str = null;
        String districtName = null;
        String districtid = null;
        String total_dis_value_exist = null;
        DoctorsInformationDAO ddao = new DoctorsInformationDAO();
        boolean districtLevelAccessFlag = false;
        PartADTO pdto = new PartADTO();
        PartADAO pdao = new PartADAO();
        DoctorsInformationDTO ddto = new DoctorsInformationDTO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            super.execute(mapping, form, request, response);
            request.getSession().setAttribute("reailwaycertificate", "exits");
            request.getSession().removeAttribute("failed");
            request.removeAttribute("cer");

            flag = request.getParameter("flag");

            personcode = request.getParameter("personcode");

            request.removeAttribute("type");
            request.removeAttribute("doctorname");
            boolean adminLevelAccessFlag = false;
            request.removeAttribute("designation");

            String regno = (String) request.getAttribute("regno");
            if (personcode != null) {
                boolean exi = territorydao.isEligibleforCertificate(ds, personcode, request);
                if (exi) {
                    return mapping.findForward("PersoncodeFailure");

                }
            }
            String print = (String) request.getParameter("print");
            if (personcode == null) {
                target = "success";
            } else if (personcode != null) {

                reportlist = new ArrayList();

                total_dis_value_exist = pdao.getTotalDisExists(ds, personcode);



                if (personcode != null) {


                    CommonDetails commondetails = new CommonDetails();

                    int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
                    adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);

                    //    boolean adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);
                    if (!adminLevelAccessFlag) {
                        districtid = (String) session.getAttribute("districtId");
                        districtLevelAccessFlag = commondetails.checkDistrictFlag(personcode, districtid);
                    }
                }

                if (districtLevelAccessFlag || adminLevelAccessFlag) {

                    if (total_dis_value_exist != null && total_dis_value_exist.equalsIgnoreCase("active")) {
                        reportlist = reportservice.getDetailsForCertificate(ds, territoryForm, personcode);

                        if (reportlist.size() == 0) {
                            target = "railwayfailure";
                        } else {
                            request.setAttribute("personcode", personcode);
                            target = "details";
                            personlist = new ArrayList();
                            personlist = reportservice.getStatus(ds, personcode);

                            Iterator iterator = personlist.iterator();
                            iterator.hasNext();
                            territorydto = (TerritoryDTO) iterator.next();
                            personcodestatus = territorydto.getPersonstatus();
                            disabilityid = territorydto.getDisabilitytype();

                            String causeOfDisabilityFlag = "false";

                            Iterator iter = reportlist.iterator();
                            iter.hasNext();
                            territorydto = (TerritoryDTO) iter.next();
                            totaldisability = territorydto.getDisabilityvalue();
                            disabilityName = territorydto.getDisabilitytype();
                            multipledisabilityid = territorydto.getMultipleRadio();

                            railwaycertificate = territorydto.isRailwaycertificate();
                            districtName = territorydto.getDistrict_name();
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
                             * getting telugu names for particular pwd
                             */
                            //territorydto = reportservice.getTeluguName(ds, personcode);
                            String telugupersonname = territorydto.getTelugupersonname();
                            String telugufathername = territorydto.getTelugufathername();
                            String disabilitytypeInTelugu = territorydto.getDisabilityTypeInTelugu();
                            String genderInTelugu = territorydto.getGenderInTelugu();
                            String disabilityduration = territorydto.getDisabilityduration();
                            String teluguDisablityName = territorydto.getTeluguDisabilityName();
                            String apflag = territorydto.getFlag();
                            request.setAttribute("apflag", apflag);
                            /*
                             *for telugu name we can not directly set Form to jsp
                             * so we have to put every telugu field in session
                             */
                            request.setAttribute("causeOfDisabilityFlag", causeOfDisabilityFlag);
                            request.setAttribute("disabilityduration", disabilityduration);
                            request.setAttribute("disabilitytypeInTelugu", disabilitytypeInTelugu);
                            session.setAttribute("personcode", personcode);
                            request.setAttribute("gender", genderInTelugu);
                            request.setAttribute("telugupersonname", telugupersonname);
                            request.setAttribute("telugufathername", telugufathername);
                            session.setAttribute("teluguDisablityName", teluguDisablityName);
                            /*
                             * getting details of particular pwd for Id Card
                             */
                            //Idcardlist = new ArrayList();
                            // Idcardlist = reportservice.getDetaiilsForIdCard(ds, territoryForm, personcode);
                            session.setAttribute("reportlist", reportlist);
                            //  request.setAttribute("Idcardlist", Idcardlist);
                            suggestionlist = new ArrayList();
                            if (disabilityid.equals("1") && totaldisability < 40.0) {
                                suggestionlist = reportservice.getLocomotorData(ds, personcode);
                            } else if (disabilityid.equals("2") && totaldisability < 40.0) {
                                suggestionlist = reportservice.getVisualData(ds, personcode);
                            } else if (disabilityid.equals("3") && totaldisability < 40.0) {
                                suggestionlist = reportservice.getHearingData(ds, personcode);
                            } else if (disabilityid.equals("4") && totaldisability < 40.0) {
                                suggestionlist = reportservice.getMentalRetardationData(ds, personcode);
                            } else if (disabilityid.equals("5") && totaldisability < 40.0) {
                                suggestionlist = reportservice.getMetalillnessData(ds, personcode);
                            } else if (disabilityid.equals("6") && totaldisability < 40.0) {
                                suggestionlist = reportservice.getMultipleData(ds, personcode);
                            }
                            //  request.setAttribute("certificatewithidcard", (String) request.getParameter("certificatewithidcard"));
                            request.setAttribute("conditionlistCheckFlag", conditionlistCheckFlag);
                            request.setAttribute("suggestionlist", suggestionlist);
                            // added  for displaying Raiway Concession Certificate in Report module
                            request.setAttribute("flag", flag);
                            print = "railwayform";
                            if (print.equals("railwayform")) {
                                if (totaldisability < 40.0) {
                                    target = "railwayfailure";
                                } else if (disabilityName != null && !"".equals(disabilityName)) {

                                    if (CertificateConstants.LOCOMOTOR_DISABILITYTYPE.equals(disabilityName)
                                            || CertificateConstants.LOCOMOTOR_DISABILED.equals(disabilityName)) {

                                        request.setAttribute("percentage", totaldisability + "");
                                        request.setAttribute("type", disabilityName);
                                        request.setAttribute("exists", "tl");
                                        // target = "railwayconcessionOH";

                                    } else if (CertificateConstants.VISUAL_DISABILITYTYPE.equals(disabilityName)
                                            || CertificateConstants.VISUAL_DISABILED.equals(disabilityName)) {
                                        if (totaldisability == 100) {
                                            request.setAttribute("percentage", totaldisability + "");
                                            request.setAttribute("type", disabilityName);
                                            request.setAttribute("exists", "vi");
                                            //  target = "railwayconcessionVI";

                                        } else {
                                            target = "railwayfailure";
                                        }
                                    } else if (CertificateConstants.HEARING_DISABILITYTYPE.equals(disabilityName)) {
                                        if (totaldisability >= 100) {
                                            request.setAttribute("percentage", totaldisability + "");
                                            request.setAttribute("type", disabilityName);
                                            request.setAttribute("exists", "hi");
                                            //  target = "railwayconcessionHI";
                                        } else {
                                            target = "railwayfailure";
                                        }
                                    } else if (CertificateConstants.MENTALRETARDATION_DISABILITYTYPE.equals(disabilityName)) {
                                        if (totaldisability >= 50) {
                                            request.setAttribute("percentage", totaldisability + "");
                                            request.setAttribute("type", disabilityName);
                                            request.setAttribute("exists", "mr");
                                            //   target = "railwayconcessionMR";
                                        } else {
                                            target = "railwayfailure";
                                        }
                                    } else if (CertificateConstants.MULTIPLE_DISABILITYTYPE.equals(disabilityName)) {

                                        id = true;

                                        territorydto = territorydao.getTotalValues(ds, personcode);
                                        double totalphysical = territorydto.getLocomotortotal();
                                        double visualimpairment = Double.parseDouble(territorydto.getVisual_Impairment());
                                        double hearingimpairment = Double.parseDouble(territorydto.getHearing_Percentage());
                                        double mentalretardation = Double.parseDouble(territorydto.getMental_Retardation_Total());

                                        if ("1".equals(multipledisabilityid)) {
                                            if (totalphysical >= 40) {
                                                request.setAttribute("percentage", totalphysical + "");
                                                request.setAttribute("type", "Orthopaedically Handicapped");
                                                request.setAttribute("exists", "tl");

                                                //     target = "railwayconcessionOH";

                                            } else if (visualimpairment >= 100) {


                                                //  target = "railwayconcessionVI";
                                                request.setAttribute("percentage", visualimpairment + "");
                                                request.setAttribute("type", "Comlete Blind");
                                                request.setAttribute("exists", "vi");

                                            } else if (hearingimpairment >= 100) {

                                                request.setAttribute("percentage", hearingimpairment + "");
                                                request.setAttribute("type", "Totally Deaf & Dumb");
                                                request.setAttribute("exists", "hi");



                                                //  target = "railwayconcessionOH";
                                            } else if (mentalretardation >= 50) {
                                                request.setAttribute("percentage", mentalretardation + "");
                                                request.setAttribute("type", "Mentally Retarded");
                                                request.setAttribute("exists", "mr");

                                            } else {
                                                target = "railwayfailure";
                                            }



                                            //     target = "railwayconcessionOH";
                                        } else if ("2".equals(multipledisabilityid)) {




                                            if (visualimpairment >= 100) {


                                                //  target = "railwayconcessionVI";
                                                request.setAttribute("percentage", visualimpairment + "");
                                                request.setAttribute("type", "Comlete Blind");
                                                request.setAttribute("exists", "vi");

                                            } else if (hearingimpairment >= 100) {

                                                request.setAttribute("percentage", hearingimpairment + "");
                                                request.setAttribute("type", "Totally Deaf & Dumb");
                                                request.setAttribute("exists", "hi");



                                                //  target = "railwayconcessionOH";
                                            } else if (mentalretardation >= 50) {
                                                if (mentalretardation >= 50) {

                                                    request.setAttribute("percentage", mentalretardation + "");
                                                    request.setAttribute("type", "Mentally Retarded");
                                                    request.setAttribute("exists", "mr");


                                                    //  target = "railwayconcessionMR";
                                                } else if (totalphysical >= 40) {

                                                    request.setAttribute("percentage", totalphysical + "");
                                                    request.setAttribute("type", "Orthopaedically Handicapped");
                                                    request.setAttribute("exists", "tl");

                                                    //     target = "railwayconcessionOH";
                                                } else {
                                                    target = "railwayfailure";
                                                }
                                            } else if (totalphysical >= 40) {
                                                if (mentalretardation > totalphysical && mentalretardation >= 50) {
                                                    request.setAttribute("percentage", mentalretardation + "");
                                                    request.setAttribute("type", "Mentally Retarded");
                                                    request.setAttribute("exists", "mr");

                                                } else if (totalphysical >= 40) {
                                                    request.setAttribute("percentage", totalphysical + "");
                                                    request.setAttribute("type", "Orthopaedically Handicapped");
                                                    request.setAttribute("exists", "tl");

                                                } else {
                                                    target = "railwayfailure";
                                                }
                                            } else {
                                                target = "railwayfailure";
                                            }
                                        } else if ("3".equals(multipledisabilityid)) {
                                            if (hearingimpairment >= 100) {

                                                request.setAttribute("percentage", hearingimpairment + "");
                                                request.setAttribute("type", "Totally Deaf & Dumb");
                                                request.setAttribute("exists", "hi");

                                                //   target = "railwayconcessionHI";

                                            }
                                            if (visualimpairment >= 100) {


                                                //  target = "railwayconcessionVI";
                                                request.setAttribute("percentage", visualimpairment + "");
                                                request.setAttribute("type", "Comlete Blind");
                                                request.setAttribute("exists", "vi");

                                            } else if (mentalretardation >= 50) {

                                                //   target = "railwayconcessionMR";
                                                request.setAttribute("percentage", mentalretardation + "");
                                                request.setAttribute("type", "Mentally Retarded");
                                                request.setAttribute("exists", "mr");
                                            } else if (totalphysical >= 40) {
                                                request.setAttribute("percentage", totalphysical + "");
                                                request.setAttribute("type", "Orthopaedically Handicapped");
                                                request.setAttribute("exists", "tl");

                                                //  target = "railwayconcessionOH";
                                            } else {
                                                target = "railwayfailure";
                                            }
                                        } else if ("4".equals(multipledisabilityid)) {
                                            if (mentalretardation >= 50) {

                                                request.setAttribute("percentage", mentalretardation + "");
                                                request.setAttribute("type", "Mentally Retarded");
                                                request.setAttribute("exists", "mr");
                                                // request.setAttribute("cer", pdto.getRail());
                                                //   target = "railwayconcessionMR";
                                            } else if (visualimpairment >= 100) {


                                                //  target = "railwayconcessionVI";
                                                request.setAttribute("percentage", visualimpairment + "");
                                                request.setAttribute("type", "Comlete Blind");
                                                request.setAttribute("exists", "vi");

                                            } else if (hearingimpairment >= 100) {

                                                request.setAttribute("percentage", hearingimpairment + "");
                                                request.setAttribute("type", "Totally Deaf & Dumb");
                                                request.setAttribute("exists", "hi");



                                                //  target = "railwayconcessionOH";
                                            } else if (totalphysical >= 40) {
                                                request.setAttribute("percentage", totalphysical + "");
                                                request.setAttribute("type", "Orthopaedically Handicapped");
                                                request.setAttribute("exists", "tl");

                                                //    target = "railwayconcessionOH";
                                            } else {
                                                target = "railwayfailure";
                                            }
                                        } else if ("5".equals(multipledisabilityid)) {
                                            if (visualimpairment >= 100) {

                                                request.setAttribute("type", "Comlete Blind");
                                                request.setAttribute("percentage", visualimpairment + "");
                                                // request.setAttribute("cer", pdto.getRail());
                                                request.setAttribute("exists", "vi");
                                                //    target = "railwayconcessionVI";
                                            } else if (hearingimpairment >= 100) {
                                                request.setAttribute("percentage", hearingimpairment + "");
                                                request.setAttribute("type", "Totally Deaf & Dumb");
                                                request.setAttribute("exists", "hi");
                                                // request.setAttribute("cer", pdto.getRail());
                                                //   target = "railwayconcessionHI";
                                            } else if (mentalretardation >= 50) {

                                                //   target = "railwayconcessionMR";
                                                request.setAttribute("percentage", mentalretardation + "");
                                                request.setAttribute("type", "Mentally Retarded");
                                                request.setAttribute("exists", "mr");
                                            } else if (totalphysical >= 40) {

                                                //    target = "railwayconcessionOH";
                                                request.setAttribute("percentage", totalphysical + "");
                                                request.setAttribute("type", "Orthopaedically Handicapped");
                                                request.setAttribute("exists", "tl");
                                            } else {
                                                target = "railwayfailure";
                                            }
                                        } else {
                                            target = "railwayfailure";
                                        }
                                    } else {
                                        target = "railwayfailure";
                                    }
                                } else {
                                    target = "railwayfailure";
                                }
                            } else {
                                target = "failure";
                            }
                            if (target != null && !target.equalsIgnoreCase("railwayfailure")) {
                                pdto = null;
                                pdto = pdao.getRailwayDoctorDetails(ds, personcode);

                                if (pdto != null) {
                                    target = "details";
                                    request.setAttribute("doctorname", pdto.getDoctor1name());
                                    request.setAttribute("regno", pdto.getDoctor1regnumber());
                                    request.setAttribute("designation", pdto.getDoctor1designation());
                                    //  request.setAttribute("position", pdto.getPosition());
                                    request.setAttribute("cer", pdto.getRail());
                                }
                            }

                            if (personcode != null && districtName != null) {
                                CommonDetails commonDetails = new CommonDetails();
                                String url = getServlet().getServletContext().getRealPath("/");
                                commonDetails.copyPhotoDtoRelativePath(personcode, districtName, request, url);
                            }

                        }

                    } else if (total_dis_value_exist != null && total_dis_value_exist.equalsIgnoreCase("inactive")) {

                        target = "railwayfailure";
                    } else {
                        target = "nodata";
                        request.setAttribute("msg", "msg1");
                    }

                } else {
                    target = "PersoncodeFailure";
                }

            }
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
