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
public class RailwayCertificate extends BaseAction {

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
        String selectFlow = "OUTERPROCESS";
        if (request.getParameter("selectFlow") != null) {
            selectFlow = request.getParameter("selectFlow");
        } else if (request.getParameter("selectedValue") != null) {
            selectFlow = request.getParameter("selectedValue");
        }
        request.setAttribute("selectedValue", selectFlow);
        TerritoryForm territoryForm = (TerritoryForm) form;
        request.setAttribute("separaterail", "yes");
        ActionMessages actionMessages = null;
        String target = "finish";
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
        //DataSource ds=getDataSource(request);

        ArrayList rejectedlist = new ArrayList();
        ArrayList reportlist = null;
        ArrayList Idcardlist = null;
        ArrayList personlist = null;
        ArrayList suggestionlist = null;
        String conditionlistCheckFlag = "false";
        String str = null;
        PartADAO pdao1 = new PartADAO();
        String districtName = null;
        String total_dis_value_exist = null;
        boolean id = false;
        String districtid = null;
        boolean districtLevelAccessFlag = false;
        boolean adminLevelAccessFlag = false;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            request.getSession().removeAttribute("reailwaycertificate");
            super.execute(mapping, form, request, response);
            request.getSession().removeAttribute("dr_exists");

            str = request.getParameter("displaybackbuttonforAR");
            if ("null".equals(str)) {
                request.setAttribute("displaybackbuttonforAR", "true");
            }
            flag = request.getParameter("flag");
            //   if (flag.equals("true")) {
            personcode = request.getParameter("personcode");
            //   } else {
            //   personcode = (String) session.getAttribute("personcode");
            //  }
            String print = (String) request.getParameter("print");
            territoryForm.setDifferenceFlag(print);
            if (personcode == null) {
                target = "success";
            } /*
             *getting status for particular personcode
             */ else {
                total_dis_value_exist = pdao1.getTotalDisExists(ds, personcode);
                if (personcode != null) {

                    //  reportlist = reportservice.getDetailsForCertificate(ds, territoryForm, personcode);
                    CommonDetails commondetails = new CommonDetails();

                    int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
                    adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);

                    //    boolean adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);
                    if (!adminLevelAccessFlag) {
                        districtid =CommonUtility.checkNullObj(session.getAttribute("districtId"));
                        districtLevelAccessFlag = commondetails.checkDistrictFlag(personcode, districtid);
                    }
                }


                personlist = new ArrayList();
                personlist = reportservice.getStatus(ds, personcode);

                // if (personlist.size() == 0) {
                //// target = "PersoncodeFailure";
                // } else
                if (districtLevelAccessFlag || adminLevelAccessFlag) {
                    //total_dis_value_exist =.getTotalDisExists(ds, personcode);
                    boolean exi = territorydao.isEligibleforCertificate(ds, personcode, request);
                    if (exi) {
                        return mapping.findForward("PersoncodeFailure");

                    }


                    if (total_dis_value_exist != null && total_dis_value_exist.equalsIgnoreCase("active")) {
                        Iterator iterator = personlist.iterator();
                        iterator.hasNext();
                        territorydto = (TerritoryDTO) iterator.next();
                        personcodestatus = territorydto.getPersonstatus();
                        disabilityid = territorydto.getDisabilitytype();
                        /*
                         * checking pwd Status
                         */
                        // if (personcodestatus != null) {
                        //   if (personcodestatus.equals("Eligible")) {
                        /*
                         * getting details of particular pwd
                         */



                        reportlist = new ArrayList();
                        reportlist = reportservice.getDetailsForCertificate(ds, territoryForm, personcode);
                        if (reportlist.size() == 0) {
                            target = "failure";
                        } else {

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

                            // Idcardlist = reportservic8e.getDetaiilsForIdCard(ds, territoryForm, personcode);
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

                            // request.setAttribute("certificatewithidcard", (String) request.getParameter("certificatewithidcard"));
                            request.setAttribute("conditionlistCheckFlag", conditionlistCheckFlag);
                            request.setAttribute("suggestionlist", suggestionlist);
                            // added  for displaying Raiway Concession Certificate in Report module
                            //  request.setAttribute("flag", flag);
                            print = "railwayform";
                            if (print.equals("railwayform")) {

                                if (totaldisability < 40.0) {
                                    target = "railwayfailure";
                                } else if (disabilityName != null && !"".equals(disabilityName)) {

                                    if (CertificateConstants.LOCOMOTOR_DISABILITYTYPE.equals(disabilityName)
                                            || CertificateConstants.LOCOMOTOR_DISABILED.equals(disabilityName)) {
                                        target = "railwayconcessionOH";
                                    } else if (CertificateConstants.VISUAL_DISABILITYTYPE.equals(disabilityName)
                                            || CertificateConstants.VISUAL_DISABILED.equals(disabilityName)) {
                                        if (totaldisability == 100) {
                                            target = "railwayconcessionVI";
                                        } else {
                                            target = "railwayfailure";
                                        }
                                    } else if (CertificateConstants.HEARING_DISABILITYTYPE.equals(disabilityName)) {
                                        if (totaldisability >= 100) {
                                            target = "railwayconcessionHI";
                                        } else {
                                            target = "railwayfailure";
                                        }
                                    } else if (CertificateConstants.MENTALRETARDATION_DISABILITYTYPE.equals(disabilityName)) {

                                        if (totaldisability >= 50) {
                                            target = "railwayconcessionMR";

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
                                                target = "railwayconcessionOH";
                                            } else if (visualimpairment >= 100) {
                                                target = "railwayconcessionVI";
                                            } else if (hearingimpairment >= 100) {
                                                target = "railwayconcessionHI";
                                            } else if (mentalretardation >= 50) {
                                                target = "railwayconcessionMR";
                                            } else {
                                                target = "railwayfailure";
                                            }

                                        } else if ("2".equals(multipledisabilityid)) {


                                            if (visualimpairment >= 100) {

                                                target = "railwayconcessionVI";
                                            } else if (hearingimpairment >= 100) {
                                                target = "railwayconcessionHI";
                                            } else if (mentalretardation >= 50)//if (mentalretardation > totalphysical && mentalretardation > hearingimpairment)
                                            {
                                                if (mentalretardation >= 50) {
                                                    target = "railwayconcessionMR";
                                                } else if (totalphysical >= 40) {
                                                    target = "railwayconcessionOH";
                                                } else {
                                                    target = "railwayfailure";
                                                }
                                            } else if (totalphysical >= 40) {
                                                if (mentalretardation > totalphysical && mentalretardation >= 50) {
                                                    target = "railwayconcessionMR";
                                                } else if (totalphysical >= 40) {
                                                    target = "railwayconcessionOH";
                                                } else {
                                                    target = "railwayfailure";
                                                }
                                            } else {
                                                target = "railwayfailure";
                                            }
                                        } else if ("3".equals(multipledisabilityid)) {
                                            if (hearingimpairment >= 100) {
                                                target = "railwayconcessionHI";
                                            } else if (visualimpairment >= 100) {
                                                target = "railwayconcessionVI";
                                            } else if (mentalretardation >= 50) {
                                                target = "railwayconcessionMR";
                                            } else if (totalphysical >= 40) {
                                                target = "railwayconcessionOH";
                                            } else {
                                                target = "railwayfailure";
                                            }
                                        } else if ("4".equals(multipledisabilityid)) {
                                            if (mentalretardation >= 50) {
                                                target = "railwayconcessionMR";
                                            } else if (visualimpairment >= 100) {

                                                target = "railwayconcessionVI";
                                            } else if (hearingimpairment >= 100) {
                                                target = "railwayconcessionHI";
                                            } else if (totalphysical >= 40) {
                                                target = "railwayconcessionOH";
                                            } else {
                                                target = "railwayfailure";
                                            }
                                        } else if ("5".equals(multipledisabilityid)) {

                                            if (visualimpairment >= 100) {

                                                target = "railwayconcessionVI";
                                            } else if (hearingimpairment >= 100) {
                                                target = "railwayconcessionHI";
                                            } else if (mentalretardation >= 50) {
                                                target = "railwayconcessionMR";
                                            } else if (totalphysical >= 40) {
                                                target = "railwayconcessionOH";
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


                                DoctorsInformationDAO ddao = new DoctorsInformationDAO();

                                PartADAO pdao = new PartADAO();
                                DoctorsInformationDTO ddto = new DoctorsInformationDTO();
                                PartADTO pdto = new PartADTO();
                                String dn = null;
                                String de = null;
                                String rn = null;

                                if (!target.equalsIgnoreCase("railwayfailure")) {

                                    //**  Railway Certificate issued Doctor details comming from  the following method.
                                    pdto = pdao.getRailwayDoctorDetails(ds, personcode);
                                    if (pdto != null) {
                                        dn = pdto.getDoctor1name();
                                        rn = pdto.getDoctor1regnumber();
                                        de = pdto.getDoctor1designation();

                                        if (dn != null) {
                                            Iterator iter1 = reportlist.iterator();
                                            iter1.hasNext();
                                            territorydto = (TerritoryDTO) iter1.next();
                                            if ((pdto.getRail() == null)) {// ||( pdto!=null && pdto.getRail().equalsIgnoreCase("0"))){

                                                target = "nodata";
                                            } else if (pdto != null && pdto.getRail().equalsIgnoreCase("0")) {
                                                target = "railwayfailure";
                                            } else if (!dn.contains("Dr.")) {
                                                target = "doctorfailure";
                                                request.getSession().setAttribute("separate", "yes");

                                            } else {
                                                territorydto.setFirstdoctorname(dn);
                                                territorydto.setFirstdesgination(de);
                                                territorydto.setFirstdoctornumber(rn);
                                                territorydto.setDateofisse(pdto.getDateofissue());
                                                reportlist.remove(0);
                                                reportlist.add(territorydto);
                                                session.setAttribute("reportlist", reportlist);
                                            }


                                        } else {
                                            target = "nodata";
                                        }

                                    } else {
                                        target = "nodata";
                                    }
                                }

                            } else {
                                target = "failure";
                            }

                            if (personcode != null && districtName != null) {
                                CommonDetails commonDetails = new CommonDetails();
                                String url = getServlet().getServletContext().getRealPath("/");
                                commonDetails.copyPhotoDtoRelativePath(personcode, districtName, request, url);
                            }
                        }
                        /*     }
                        } else {
                        target = "failure";
                        }*/
                        /*
                         * throwing our own exception
                         */
                    } else if (total_dis_value_exist != null && total_dis_value_exist.equalsIgnoreCase("inactive")) {

                        target = "railwayfailure";
                    } else {
                        target = "nodata";
                        request.setAttribute("msg", "msg");
                    }
                } else {
                    target = "PersoncodeFailure";
                }
            }
            if (target.equalsIgnoreCase("railwayfailure")) {
                request.setAttribute("fail", "yes");

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
