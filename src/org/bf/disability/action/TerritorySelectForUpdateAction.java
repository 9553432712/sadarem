package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.service.DoctorsInformationService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.DoctorsInformationServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;

/**
 * this action class will generate the territory select screen for personal details update
 * @author deviprasad t
 * @version 1.0
 */
public class TerritorySelectForUpdateAction extends BaseAction {
    //ArrayList districtlist=new ArrayList();

    ArrayList mandallist = new ArrayList();
    ArrayList panchayatlist = new ArrayList();
    ArrayList villagelist = new ArrayList();
    ArrayList habitationlist = new ArrayList();
    ArrayList personcodelist = new ArrayList();
    ArrayList personStatusList = new ArrayList();
    String districtid = null;
    String mandalid = null;
    String panchayatid = null;
    String habitationid = null;
    String villageid = null;
    DataSource ds = null;

    /**
     * this method will generate the territory select screen for personal details update
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "repeat";
        String personcode = null;
        String districtname = null;
        String status = null;
        String personstatus = null;
        String deathStatus = null;
        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        ArrayList disabilitytypes = new ArrayList();
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        DoctorsInformationService doctorsinfoservice = DoctorsInformationServiceFactory.getDoctorsInformationServiceImpl();
        session.setAttribute("resPartB", request.getParameter("restPartB"));
        districtid = (String) session.getAttribute("districtId");
        if (request.getParameter("restrictPartBUpdateByTerr") != null) {
            session.setAttribute("restrictPartBUpdateByTerr", request.getParameter("restrictPartBUpdateByTerr"));
        }
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            super.execute(mapping, form, request, response);
            // DataSource ds= getDataSource(request);

            String flag = request.getParameter("flag");
            if (flag == null) {
                flag = "false";
            } else {
                status = "finish";
                personcode = request.getParameter("personcode");
            }

            if (flag != null) {
                if (flag.equals("false")) {
                    status = request.getParameter("status");

                    TerritoryForm territoryForm = (TerritoryForm) form;
                    //  districtid=territoryForm.getDistrict_id();




                    /*
                     *modified by ganesh
                     */
                    String districtIdFromSession = (String) session.getAttribute("district_id");
                    if (districtIdFromSession != null) {
                        if (!districtIdFromSession.equals(districtid)) {
                            territoryForm.setMandal_id("");
                        }
                    }

                    session.setAttribute("district_id", districtid);
                    mandalid = territoryForm.getMandal_id();

                    /*
                     *modified by ganesh
                     */
                    String mandalIdFromSession = (String) session.getAttribute("mandal_id");
                    if (mandalIdFromSession != null) {
                        if (!mandalIdFromSession.equals(mandalid)) {
                            territoryForm.setVillage_id("");
                        }
                    }
                    session.setAttribute("mandal_id", mandalid);

                    /*
                     *modified by ganesh
                     */

                    panchayatid = territoryForm.getPanchayat_id();
                    session.setAttribute("panchayat_id", panchayatid);
                    villageid = territoryForm.getVillage_id();
                    /*
                     *modified by ganesh
                     */
                    String villageIdFromSession = (String) session.getAttribute("village_id");
                    if (villageIdFromSession != null) {
                        if (!villageIdFromSession.equals(villageid)) {
                            territoryForm.setHabitation_id("");
                        }
                    }

                    session.setAttribute("village_id", villageid);
                    habitationid = territoryForm.getHabitation_id();
                    session.setAttribute("habitation_id", habitationid);
                    personcode = territoryForm.getPersoncode();
                    personstatus = territoryForm.getPersonstatus();
                    String enterpersoncode = request.getParameter("enterpersoncode");


                    if (status == null) {
                        status = "update";
                    }
                    if (status.equals("update")) {
                        // districtlist = territoryservice.getDistricts(ds);
                        // session.setAttribute("districtlist",districtlist);
                        if (districtid != null && districtid != "") {
                            mandallist = territoryservice.getMandals(ds, districtid);
                            session.setAttribute("mandallist", mandallist);
                            territoryForm.setMandallist(mandallist);
                        }
                        if (mandalid != null && mandalid != "") {
                            panchayatlist = territoryservice.getPanchayats(ds, districtid, mandalid);
                            session.setAttribute("panchayatlist", panchayatlist);
                            territoryForm.setPanchayatlist(panchayatlist);
                        }
                        if (mandalid != null && mandalid != "") {
                            villagelist = territoryservice.getVillages(ds, districtid, mandalid, panchayatid);
                            session.setAttribute("villagelist", villagelist);
                            territoryForm.setVillagelist(villagelist);
                        }
                        if (mandalid != null && mandalid != "" && villageid != null && villageid != "") {
                            habitationlist = territoryservice.getHabitations(ds, districtid, mandalid, panchayatid, villageid);
                            session.setAttribute("habitationlist", habitationlist);
                            territoryForm.setHabitationlist(habitationlist);
                        }


                        if (mandalid != null && mandalid != "" && villageid != null && villageid != "" && habitationid != null && habitationid != "" && personstatus != null && personstatus != "") {
                            personcodelist = territoryservice.getPersonCode(districtid, mandalid, panchayatid, villageid, habitationid, personstatus, ds);
                            territoryForm.setPersoncodelist(personcodelist);
                        }
                        // territoryForm.setDistrictlist(districtlist);
                    }
                }


                if (status.equals("finish")) {
                	
                	CommonDAOImpl comObj = new CommonDAOImpl();
                    HashMap GEODtls = new HashMap();
        			GEODtls=comObj.getGEODetailsbySADAREMID(personcode);
                	
                    //Added code for Checking person code based on district id
        			String personcodeDistrictId = GEODtls.get("districtid").toString();
                    if (districtid.equals(personcodeDistrictId)) {
                        if (flag.equals("false")) {
                            TerritoryDTO territoryDTO = null;
                            /*   for ( Iterator iter = ((ArrayList) session.getAttribute("districtlist")).iterator(); iter.hasNext(); ) {
                            territoryDTO=(TerritoryDTO)iter.next();
                            if(territoryDTO.getDistrict_id().equals(districtid)){
                            session.setAttribute("district_id",territoryDTO.getDistrict_id());
                            session.setAttribute("district_name",territoryDTO.getDistrict_name());
                            }
                            } */

                            if (districtid != null && districtid != "") {
                                districtname = territoryservice.getDistrictsName(ds, districtid);
                                session.setAttribute("district_name", districtname);
                            }
                            for (Iterator iter = ((ArrayList) session.getAttribute("mandallist")).iterator(); iter.hasNext();) {
                                territoryDTO = (TerritoryDTO) iter.next();
                                if (territoryDTO.getMandal_id().equals(mandalid)) {
                                    session.setAttribute("mandal_id", territoryDTO.getMandal_id());
                                    session.setAttribute("mandal_name", territoryDTO.getMandal_name());
                                }
                            }
                            for (Iterator iter = ((ArrayList) session.getAttribute("panchayatlist")).iterator(); iter.hasNext();) {
                                territoryDTO = (TerritoryDTO) iter.next();
                                if (territoryDTO.getPanchayat_id().equals(panchayatid)) {
                                    session.setAttribute("panchayat_id", territoryDTO.getPanchayat_id());
                                    session.setAttribute("panchayat_name", territoryDTO.getPanchayat_name());
                                }
                            }
                            for (Iterator iter = ((ArrayList) session.getAttribute("villagelist")).iterator(); iter.hasNext();) {
                                territoryDTO = (TerritoryDTO) iter.next();
                                if (territoryDTO.getVillage_id().equals(villageid)) {
                                    session.setAttribute("village_id", territoryDTO.getVillage_id());
                                    session.setAttribute("village_name", territoryDTO.getVillage_name());
                                }
                            }
                            for (Iterator iter = ((ArrayList) session.getAttribute("habitationlist")).iterator(); iter.hasNext();) {
                                territoryDTO = (TerritoryDTO) iter.next();
                                if (territoryDTO.getHabitation_id().equals(habitationid)) {
                                    session.setAttribute("habitation_id", territoryDTO.getHabitation_id());
                                    session.setAttribute("habitation_name", territoryDTO.getHabitation_name());
                                }
                            }





                        }
                        target = "finish";
                        TerritoryDTO terri = new TerritoryDTO();
                        terri = territoryservice.getTotalValues(ds, personcode);

                        session.setAttribute("totalphysical", new Double(terri.getLocomotortotal()));
                        session.setAttribute("upperextrimity", new Double(terri.getUpperExtremity_Total()));
                        session.setAttribute("lowerextremity", new Double(terri.getLowerExtremity_Total()));
                        session.setAttribute("amputation", new Double(terri.getAmputation_Total()));
                        session.setAttribute("transverse", new Double(terri.getTransverse_Total()));
                        session.setAttribute("trunk", new Double(terri.getTrunk_Total()));
                        session.setAttribute("Evaluation", new Double(terri.getPhysical_Impairments_Total()));
                        session.setAttribute("cardiopulmonary", new Double(terri.getPulmonary_Condition()));
                        session.setAttribute("dwarfism", new Double(terri.getDwarfism_Total()));
                        session.setAttribute("hearingimpairment", new Double(terri.getHearing_Percentage()));
                        session.setAttribute("visualimpairment", new Double(terri.getVisual_Impairment()));
                        session.setAttribute("mentalretardation", new Double(terri.getMental_Retardation_Total()));
                        session.setAttribute("mentalillness", new Double(terri.getMental_Disability_Total()));
                        session.setAttribute("mutiple", terri.getMultipleRadio());

                        if (flag.equals("true")) {
                            TerritoryDTO territoryDTO = null;
                            personStatusList = territoryservice.getPersonStatus(ds, personcode);
                            if (personStatusList.size() == 0) {
                                target = "PersoncodeFailure";
                            } else {
                                Iterator iterator = personStatusList.iterator();
                                iterator.hasNext();
                                territoryDTO = (TerritoryDTO) iterator.next();
                                session.setAttribute("district_name", territoryDTO.getDistrict_name());
                                session.setAttribute("mandal_name", territoryDTO.getMandal_name());
                                session.setAttribute("village_name", territoryDTO.getVillage_name());
                                session.setAttribute("habitation_name", territoryDTO.getHabitation_name());
                                session.setAttribute("district_id", territoryDTO.getDistrict_id());
                                session.setAttribute("village_id", territoryDTO.getVillage_id());
                                session.setAttribute("mandal_id", territoryDTO.getMandal_id());
                                session.setAttribute("habitation_id", territoryDTO.getHabitation_id());
                                personstatus = territoryDTO.getPersonstatus();
                                session.setAttribute("Name", territoryDTO.getFirstname());
                                deathStatus = territoryDTO.getStatus();
                                if (deathStatus.equals("Inactive")) {
                                    request.setAttribute("massage", "PersonCode Deleted due to Ineligible ");
                                    target = "inEligible";
                                } else if (deathStatus.equals("Death")) {
                                    request.setAttribute("massage", "PersonCode Deleted due to Death/Ineligible ");
                                    target = "inEligible";
                                } else {
                                    target = "finish";
                                }
                            }
                        }
                        session.setAttribute("personstatus", personstatus);
                        session.setAttribute("personcode", personcode);
                        /*
                         *modified by ganesh
                         */

//                    session.removeAttribute("districtlist");
//                    session.removeAttribute("districtlist");
                        session.removeAttribute("mandallist");
                        session.removeAttribute("panchayatlist");
                        session.removeAttribute("habitationlist");
                        disabilitytypes = doctorsinfoservice.selectDisabilityTypes(ds);
                        session.setAttribute("disabilitytypesforupdate", disabilitytypes);
                    } else {
                        target = "PersoncodeFailure";
                    }
                }
            }
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
}
