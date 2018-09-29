    /*
 * IssuecertificateAction.java
 *
 * Created on June 18, 2008, 4:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Iterator;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.service.TerritoryService;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.service.DoctorsInformationService;
import org.bf.disability.servicefactory.DoctorsInformationServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

//import org.bf.disability.servicefactory.DoctorsInformationServiceFactory;
/**
 * this action class will populate the territory screen for territory select to add the personal details
 * @author deviprasad t
 * @version 1.0
 */
public class TerritorySelectAction extends BaseAction {
    // ArrayList districtlist=new ArrayList();

    ArrayList mandallist = new ArrayList();
    ArrayList villagelist = new ArrayList();
    ArrayList habitationlist = new ArrayList();
    ArrayList disabilitytypes = new ArrayList();
    ActionMessages actionMessages = null;
    String target = null;
    ArrayList panchayatlist = new ArrayList();
    DataSource ds = null;

    /**
     * this method will populate the territory select screen for personal details adding
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            super.execute(mapping, form, request, response);

            TerritoryForm territoryForm = (TerritoryForm) form;
            String districtid = null;
            String districtname = null;
            String mandalid = null;
            String panchayatid = null;
            String habitationid = null;
            String villageid = null;
            target = "repeat";
            //DataSource ds= getDataSource(request);



            HttpSession session = request.getSession(true);
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            DoctorsInformationService doctorsinfoservice = DoctorsInformationServiceFactory.getDoctorsInformationServiceImpl();
            String status = request.getParameter("status");
            // districtid=territoryForm.getDistrict_id();

            districtid = (String) session.getAttribute("districtId");



            if (request.getParameter("restrictPartA") != null) {
                session.setAttribute("restrictPartA", request.getParameter("restrictPartA"));
            }

            String districtIdFromSession = (String) session.getAttribute("district_id");
            if (districtIdFromSession != null) {
                if (!districtIdFromSession.equals(districtid)) {
                    territoryForm.setMandal_id("");
                }
            }


            session.setAttribute("district_id", districtid);



            mandalid = territoryForm.getMandal_id();
            String mandalIdFromSession = (String) session.getAttribute("mandal_id");
            if (mandalIdFromSession != null) {
                if (!mandalIdFromSession.equals(mandalid)) {
                    territoryForm.setVillage_id("");
                }
            }


            session.setAttribute("mandal_id", mandalid);
            panchayatid = territoryForm.getPanchayat_id();
            session.setAttribute("panchayat_id", panchayatid);
            villageid = territoryForm.getVillage_id();

            String villageIdFromSession = (String) session.getAttribute("village_id");
            if (villageIdFromSession != null) {
                if (!villageIdFromSession.equals(villageid)) {
                    territoryForm.setHabitation_id("");
                }
            }


            session.setAttribute("village_id", villageid);
            habitationid = territoryForm.getHabitation_id();
            session.setAttribute("habitation_id", habitationid);
            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {

                // districtlist = territoryservice.getDistricts(ds);
                //  session.setAttribute("districtlist",districtlist);

                disabilitytypes = doctorsinfoservice.selectDisabilityTypes(ds);
                session.setAttribute("disabilitytypes", disabilitytypes);


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
                // territoryForm.setDistrictlist(districtlist);




            } else if (status.equals("finish")) {


                /*  for ( Iterator iter = ((ArrayList) session.getAttribute("districtlist")).iterator(); iter.hasNext(); ) {
                TerritoryDTO  territoryDTO=new TerritoryDTO();
                territoryDTO=(TerritoryDTO)iter.next();
                if(territoryDTO.getDistrict_id().equals(session.getAttribute("districtId"))){
                session.setAttribute("district_id",territoryDTO.getDistrict_id());
                session.setAttribute("district_name",territoryDTO.getDistrict_name());

                }
                } */
                if (districtid != null && districtid != "") {
                    districtname = territoryservice.getDistrictsName(ds, districtid);
                    session.setAttribute("district_name", districtname);
                }


                for (Iterator iter = ((ArrayList) session.getAttribute("mandallist")).iterator(); iter.hasNext();) {
                    TerritoryDTO territoryDTO = new TerritoryDTO();
                    territoryDTO = (TerritoryDTO) iter.next();
                    if (territoryDTO.getMandal_id().equals(session.getAttribute("mandal_id"))) {
                        session.setAttribute("mandal_id", territoryDTO.getMandal_id());
                        session.setAttribute("mandal_name", territoryDTO.getMandal_name());

                    }
                }


                for (Iterator iter = ((ArrayList) session.getAttribute("panchayatlist")).iterator(); iter.hasNext();) {
                    TerritoryDTO territoryDTO = new TerritoryDTO();
                    territoryDTO = (TerritoryDTO) iter.next();
                    if (territoryDTO.getPanchayat_id().equals(session.getAttribute("panchayat_id"))) {
                        session.setAttribute("panchayat_id", territoryDTO.getPanchayat_id());
                        session.setAttribute("panchayat_name", territoryDTO.getPanchayat_name());

                    }

                }

                for (Iterator iter = ((ArrayList) session.getAttribute("villagelist")).iterator(); iter.hasNext();) {
                    TerritoryDTO territoryDTO = new TerritoryDTO();
                    territoryDTO = (TerritoryDTO) iter.next();
                    if (territoryDTO.getVillage_id().equals(session.getAttribute("village_id"))) {
                        session.setAttribute("village_id", territoryDTO.getVillage_id());
                        session.setAttribute("village_name", territoryDTO.getVillage_name());

                    }

                }

                for (Iterator iter = ((ArrayList) session.getAttribute("habitationlist")).iterator(); iter.hasNext();) {
                    TerritoryDTO territoryDTO = new TerritoryDTO();
                    territoryDTO = (TerritoryDTO) iter.next();
                    if (territoryDTO.getHabitation_id().equals(session.getAttribute("habitation_id"))) {
                        session.setAttribute("habitation_id", territoryDTO.getHabitation_id());
                        session.setAttribute("habitation_name", territoryDTO.getHabitation_name());
                    }

                }

                session.setAttribute("totalphysical", new Double(0));
                session.setAttribute("upperextrimity", new Double(0));
                session.setAttribute("lowerextremity", new Double(0));
                session.setAttribute("amputation", new Double(0));
                session.setAttribute("transverse", new Double(0));
                session.setAttribute("trunk", new Double(0));
                session.setAttribute("Evaluation", new Double(0));
                session.setAttribute("cardiopulmonary", new Double(0));
                session.setAttribute("dwarfism", new Double(0));
                session.setAttribute("hearingimpairment", new Double(0));
                session.setAttribute("visualimpairment", new Double(0));
                session.setAttribute("mentalretardation", new Double(0));
                session.setAttribute("mentalillness", new Double(0));
                session.setAttribute("developmentalresult", new Double(0));
                session.setAttribute("vinelandresult", new Double(0));
                session.setAttribute("binetkamalresult", new Double(0));
                session.setAttribute("sfbiq", new Double(0));
                session.setAttribute("misiciq", new Double(0));
                session.setAttribute("pat", new Double(0));
                session.setAttribute("bhatia", new Double(0));

                String disabilityType = territoryForm.getTypeofdisability();
                TerritoryDTO territoryDTO = new TerritoryDTO();
                session.setAttribute("Name", territoryDTO.getFirstname());
                /*DoctorsInformationDTO doctorsInformationDTO=doctorsinfoservice.selectDoctorDetails(ds,disabilityType);
                request.setAttribute("HospitalName",doctorsInformationDTO.getHospitalname());
                request.setAttribute("Hospital_Address",doctorsInformationDTO.getHospitaladdress());
                request.setAttribute("Doctor_Name1",doctorsInformationDTO.getDoctorname1());
                request.setAttribute("Designation1",doctorsInformationDTO.getDesignation1());
                request.setAttribute("Registration1",doctorsInformationDTO.getRegisterno1());
                request.setAttribute("Doctor_Name2",doctorsInformationDTO.getDoctorname2());
                request.setAttribute("Designation2",doctorsInformationDTO.getDesignation2());
                request.setAttribute("Registration2",doctorsInformationDTO.getRegisterno2());
                request.setAttribute("Doctor_Name3",doctorsInformationDTO.getDoctorname3());
                request.setAttribute("Designation3",doctorsInformationDTO.getDesignation3());
                request.setAttribute("Registration3",doctorsInformationDTO.getRegisterno3());*/


//                session.removeAttribute("districtlist");
//                session.removeAttribute("districtlist");
                session.removeAttribute("mandallist");
                session.removeAttribute("panchayatlist");
                session.removeAttribute("habitationlist");


                target = "finish";
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
