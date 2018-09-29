/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.PrintWriter;
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
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.service.AppletAuthorityService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 509864
 */
public class TerritorySelectForPersonDetailsAction extends BaseDispatchAction {

    public ActionForward getPersonDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        DataSource ds = null;
        String target = null;
        String districtid = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        String panchayat_id = null;

        String district_name = null;
        String mandal_name = null;
        String village_name = null;
        String panchayat_name = null;
        String habitation_name = null;

        ActionMessages actionMessages = null;
        ArrayList personStatusList = null;
        String personcode = null;
        String status = null;
        String personstatus = null;
        boolean districtLevelAccessFlag = false;
        String deathStatus = null;
        String powerCutIds = null;
        try 
        {


            HttpSession session = request.getSession();
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
        	
        	
        	
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds))
            {
                ds = JNDIDataSource.getConnection();
            }

            String flag = request.getParameter("flag");
            
            System.out.println("flag : "+flag);
            
            
            if (flag == null) 
            {
                flag = "false";
                TerritoryForm territoryForm = (TerritoryForm) form;
                personcode = territoryForm.getPersoncode();
                status = request.getParameter("status");
            }
            else 
            {
                status = "finish";
                personcode = request.getParameter("personcode"); 
            }

            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            TerritoryDAO territoryDAO = new TerritoryDAO();
            CommonDetails commondetails = new CommonDetails();
            boolean adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);

            //System.out.println("adminLevelAccessFlag : "+adminLevelAccessFlag);
            /* Dont delete data for login role id is 5 */
            if (!adminLevelAccessFlag) 
            {
                /** To Release the PowerCut Ids */
                AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
                /** Releasing the powercut problems */
                powerCutIds = appletAuthorityService.getPowerCutIds(ds, "Completed", personcode, request);

                if (powerCutIds != null && powerCutIds.length() > 0) 
                { 
                    appletAuthorityService.powerCutIds(ds, powerCutIds, request); 
                }
                
                	String partBAlreadyRaised ="";
                	partBAlreadyRaised=appletAuthorityService.CheckDisabilityDetails(ds, personcode, request);
                	if(partBAlreadyRaised==null || !partBAlreadyRaised.equals("1"))
                	{

                        personStatusList = new ArrayList();
                        personStatusList = territoryservice.getPersonStatus(ds, personcode);
                        int i = territoryDAO.getPersoncodeDetails(ds, personcode);
                        if (personStatusList.size() == 0 && i > 0) 
                        {
                             request.setAttribute("massage", "Kindly do the Part B entry for SADAREM ID "+personcode+" after 15 mins");
                        }
                        else
                        {
                        	request.setAttribute("massage", "You are Not Eligible <br>OR<br>Details have been already filled in normal flow. <br>OR<br> Please continue with Re-Assessment or Temparary flow");
                        }                		
                        target = "inEligible";
                        return mapping.findForward(target);
                    }
                	
                }
            
            /** end */
            session.removeAttribute("sadaremCodeAu");

            //boolean  adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);
            if (!adminLevelAccessFlag) 
            {
                districtid = (String) session.getAttribute("districtId");
                districtLevelAccessFlag = commondetails.checkDistrictFlag(personcode, districtid);
            }
            
            if (districtLevelAccessFlag || adminLevelAccessFlag) 
            {
                //super.execute(mapping, form, request, response);
                districtid = request.getParameter("district_id");
                mandal_id = request.getParameter("mandal_id");
                village_id = request.getParameter("village_id");
                habitation_id = request.getParameter("habitation_id");
                panchayat_id = request.getParameter("panchayat_id");

                district_name = request.getParameter("district_name");

                mandal_name = request.getParameter("mandal_name");
                village_name = request.getParameter("village_name");
                panchayat_name = request.getParameter("panchayat_name");
                habitation_name = request.getParameter("habitation_name");
                

                request.setAttribute("resPartB", request.getParameter("restPartB"));
                
                if (request.getParameter("restrictPartBUpdateByTerr") != null) 
                {
                    request.setAttribute("restrictPartBUpdateByTerr", request.getParameter("restrictPartBUpdateByTerr"));
                }
                
                if (request.getParameter("restrictPartA") != null) 
                {
                    request.setAttribute("restrictPartA", request.getParameter("restrictPartA"));
                }
                
                if (flag != null) 
                {
                    TerritoryForm territoryForm = (TerritoryForm) form;
                    personcode = territoryForm.getPersoncode();
                    personstatus = territoryForm.getPersonstatus();
                    if (status.equals("finish"))
                    {
                        target = "finish";
                        if (flag.equals("true")) 
                        {
                            
                            int i = territoryDAO.getPersoncodeDetails(ds, personcode);

                            TerritoryDTO territoryDTO = null;
                            personStatusList = new ArrayList();
                            personStatusList = territoryservice.getPersonStatus(ds, personcode);
                            if (personStatusList.size() == 0 && i == 0) 
                            {
                                target = "PersoncodeFailure";
                            }
                            else if (personStatusList.size() == 0 && i > 0) 
                            {
                                 request.setAttribute("massage", "Kindly do the Part B entry for SADAREM ID "+personcode+" after 15 mins");
                                    target = "inEligible";
                                

                            } 
                            else 
                            {
                                Iterator iterator = personStatusList.iterator();
                                iterator.hasNext();
                                territoryDTO = (TerritoryDTO) iterator.next();
                                districtid = territoryDTO.getDistrict_id();
                                mandal_id = territoryDTO.getMandal_id();
                                village_id = territoryDTO.getVillage_id();
                                habitation_id = territoryDTO.getHabitation_id();
                                panchayat_id = territoryDTO.getPanchayat_id();

                                district_name = territoryDTO.getDistrict_name();
                                mandal_name = territoryDTO.getMandal_name();
                                village_name = territoryDTO.getVillage_name();
                                habitation_name = territoryDTO.getHabitation_name();
                                panchayat_name = territoryDTO.getPanchayat_name();
                                personstatus = territoryDTO.getPersonstatus();
                                deathStatus = territoryDTO.getStatus();
                                String proofId = territoryDTO.getProof_id();
                                String proofdoctype = territoryDTO.getProofdoc_type();

                                if (proofdoctype != null && (proofdoctype.equalsIgnoreCase("Adhaar Card") || proofdoctype.equalsIgnoreCase("Aadhaar Card") || proofdoctype.equalsIgnoreCase("Aadhaar ID") || proofdoctype.equalsIgnoreCase("Adhaar Card"))
                                        && proofId != null && !proofId.equalsIgnoreCase("-") && !proofId.equalsIgnoreCase("NA")) 
                                {
                                    request.setAttribute("aadhar", "aadhar");

                                }
                                PartADAO partADAO = new PartADAO();

                                String pensioncardno = partADAO.checkPensioncardno(ds, personcode);


                                if (pensioncardno != null && !pensioncardno.isEmpty()) 
                                {
                                    request.setAttribute("pensioncardno", pensioncardno);


                                }
                                if (deathStatus.equals("Inactive"))
                                {
                                    request.setAttribute("massage", "PersonCode Deleted due to " + territoryDTO.getReasonforstatus());
                                    target = "inEligible";
                                } 
                                else if (deathStatus.equals("Death")) 
                                {
                                    request.setAttribute("massage", "PersonCode Deleted due to Death/Ineligible" + territoryDTO.getStatus());
                                    target = "inEligible";
                                }
//                                session.setAttribute("Name", territoryDTO.getFirstname());
                            }

                        }
                        
                        
                        
                        request.setAttribute("district_id", districtid);
                        request.setAttribute("mandal_id", mandal_id);
                        request.setAttribute("village_id", village_id);
                        request.setAttribute("habitation_id", habitation_id);
                        request.setAttribute("panchayat_id", panchayat_id);

                        request.setAttribute("district_name", district_name);
                        request.setAttribute("mandal_name", mandal_name);
                        request.setAttribute("village_name", village_name);
                        request.setAttribute("panchayat_name", panchayat_name);
                        request.setAttribute("habitation_name", habitation_name);

                        
                        
                        
                        session.setAttribute("personstatus", personstatus);
                        session.setAttribute("personcode", personcode);
                    }
                }
            } 
            else 
            {
                target = "PersoncodeFailure";
            }
        } 
        catch (SADAREMDBException sADAREMException) 
        {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
            
            sADAREMException.printStackTrace();
        } 
        catch (Exception sADAREMException) 
        {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
            sADAREMException.printStackTrace();
        }
        
      //  System.out.println("target : "+target);
        
        return mapping.findForward(target);
    }

    public ActionForward accessPersonStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String districtid = null;
        boolean districtLevelAccessFlag = false;
        String personcode = null;
        String districtName = null;
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            CommonDetails commondetails = new CommonDetails();
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));

            boolean adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);
            if (!adminLevelAccessFlag) {
                districtid = (String) session.getAttribute("districtId");
                //districtLevelAccessFlag = commondetails.checkDistrictFlag(personcode, districtid);
                TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
                districtName = territoryservice.getDistrictsName(ds, districtid);
            }
            PrintWriter out = response.getWriter();
            response.setHeader("Cache-Control", "private");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setContentType("text/xml");

            out.println("<root>");
            if (!"null".equals(adminLevelAccessFlag)) {
                out.println("<adminLevelAccessFlag>" + adminLevelAccessFlag + "</adminLevelAccessFlag>");
                out.println("<districtid>" + districtid + "</districtid>");
                out.println("<districtname>" + districtName + "</districtname>");
            } else {
                out.println("<adminLevelAccessFlag>" + 0 + "</adminLevelAccessFlag>");
                out.println("<districtid>" + 0 + "</districtid>");
                out.println("<districtname>" + 0 + "</districtname>");
            }
            out.println("</root>");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward selectPersonCodesList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String districtid = null;
        DataSource ds = null;
        ds = getDataSource(request);

        String mandalid = null;
        String panchayatid = null;
        String habitationid = null;
        String villageid = null;
        String personstatus = null;
        ArrayList personcodelist = new ArrayList();
        TerritoryDTO territoryDTO = null;
        try {
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtid = request.getParameter("districtid");
            mandalid = request.getParameter("mandalid");
            villageid = request.getParameter("villageid");
            panchayatid = request.getParameter("panchayatid");
            habitationid = request.getParameter("habitationid");
            personstatus = request.getParameter("personstatus");
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();

            PrintWriter out = response.getWriter();
            response.setHeader("Cache-Control", "private");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setContentType("text/xml");
            if (mandalid != null && !"".equals(mandalid)
                    && villageid != null && !"".equals(villageid)
                    && habitationid != null && !"".equals(habitationid)
                    && personstatus != null && !"".equals(personstatus)) {
                personcodelist = territoryservice.getPersonCode(districtid, mandalid, panchayatid, villageid, habitationid, personstatus, ds);
                int id_size = personcodelist.size();
                int count = 0;
                out.println("<root>");
                if (id_size > 0) {
                    out.println("<personcode>" + id_size + "</personcode>");
                    for (count = 0; count < personcodelist.size(); count++) {
                        territoryDTO = (TerritoryDTO) personcodelist.get(count);
                        out.println("<personcode>" + territoryDTO.getPersoncode() + "</personcode>");
                    }
                } else {
                    out.println("<personcode>" + 0 + "</personcode>");
                }

            }
            out.println("</root>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
