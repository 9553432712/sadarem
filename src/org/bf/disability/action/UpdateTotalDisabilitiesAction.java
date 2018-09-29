package org.bf.disability.action;

import java.sql.SQLException;
import java.util.LinkedList;
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
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.TerritoryServiceFactory;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.dao.PartADAO;

/**
 * this action class will update the total disability percentages of all the disabilities for a particular person
 * at update mode
 * @author deviprasad t
 * @version 1..0
 */
public class UpdateTotalDisabilitiesAction extends BaseAction {

    /**
     * this method will update the total disability percentages of all the disabilities for a particular person
     * at update mode
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        HttpSession session = request.getSession(true);
        // DataSource datasource= getDataSource(request);
        String target = "success";
        ActionMessages actionMessages = null;
        DataSource datasource = null;
        double disability = 0.0;
        PartADAO partADAO = new PartADAO();
        boolean exis = false;
        try {datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            TerritoryForm territoryform = (TerritoryForm) form;
            int maxMultipleId = 0;
            super.execute(mapping, form, request, response);
            // session.removeAttribute("disabilityEditFlag");
            String personcode = null;//(String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            String loginid = (String) session.getAttribute("loginid");
            String systemip = request.getRemoteAddr();
            LinkedList totalvaluelist = new LinkedList();
            LinkedList totalvaluelist1 = new LinkedList();
            TerritoryDTO territoryDTO = (TerritoryDTO) request.getSession().getAttribute("territoryDTOtemp");
            String disabilityId = territoryform.getDisabilityId();
            request.setAttribute("disabilityId", disabilityId);
            String maxId = territoryform.getMultipleRadio();
            request.setAttribute("maxId", maxId);



            if (maxId == null) {
                // request.getSession().setAttribute("max", disabilityId);
                //maxMultipleId = 0;
                if (disabilityId != null) {
                    request.getSession().setAttribute("max", disabilityId);
                    if (disabilityId.equalsIgnoreCase("2")) {
                        exis = partADAO.checkVisual(datasource, personcode);
                        if (!exis) {
                            request.setAttribute("msg", "<font color=\"red\">Please select below link !!!</font>");
                            return mapping.findForward("back");
                        }

                    }
                }
                maxMultipleId = 0;

            } else {
                maxMultipleId = Integer.parseInt(maxId);
                request.getSession().setAttribute("max", maxId);
                if (disabilityId.equals("6")) {
                    if (maxMultipleId == 2) {
                        exis = partADAO.checkVisual(datasource, personcode);

                        if (!exis) {
                            request.setAttribute("msg", "<font color=\"red\">Please select below link !!!</font>");
                            return mapping.findForward("back");
                        }
                    }
                    PartADTO partADTO = new PartADTO();
                    //  PartADAO partADAO = new PartADAO();
                    session.removeAttribute("ohdoctor");
                    session.removeAttribute("vidoctor");
                    session.removeAttribute("hidoctor");
                    session.removeAttribute("mrdoctor");
                    session.removeAttribute("midoctor");
                    // partADTO = partADAO.getMaxMultipleDoctorsDetails(datasource,personcode,maxId);
                    partADTO = partADAO.getMaxMultipleDoctorsDetails(datasource, personcode, maxId);

                    if (partADTO != null) {

                        if (maxMultipleId == 4) {
                            String doctorName = null;
                            String Mdspecialistprefix = (String) session.getAttribute("Mdspecialistprefix");
                            if (Mdspecialistprefix.equals("Dr")) {
                                if (!partADTO.getDoctor1name().substring(0, 1).equals("Dr")) {
                                    doctorName = Mdspecialistprefix.concat(".").concat(" ").concat(partADTO.getDoctor1name());
                                }
                            } else {
                                doctorName = partADTO.getDoctor1name();
                            }
                            partADTO.setDoctor1name(doctorName);
                        } else {
                            if (!partADTO.getDoctor1name().startsWith("Dr")) {
                                String doctorName = "Dr".concat(".").concat(" ").concat(partADTO.getDoctor1name());
                                partADTO.setDoctor1name(doctorName);
                            }
                        }
                        partADAO.updateMaxMultipleDoctorsDetails(datasource, personcode, maxId, partADTO);
                    }
                }
            }
            int totalvalueinsert = 0;
            //  if(session.getAttribute("sadaremCodeAu")!=null) {

            //      totalvalueinsert = territoryservice.totalValueInsert(datasource, territoryDTO,totalvaluelist, totalvaluelist1, disability, personcode, loginid, systemip, maxMultipleId,request);

            //    }else {

            totalvalueinsert = territoryservice.totalValueUpdate(datasource, territoryDTO, totalvaluelist, totalvaluelist1, disability, personcode, loginid, systemip, maxMultipleId, request);
            //    }



            if ("5".equals(maxId)) {
                Double totalphysical = new Double(territoryDTO.getTotalforphysical());
                Double hearingimpairment = new Double(territoryDTO.getHearing_Percentage());
                Double visualimpairment = new Double(territoryDTO.getVisual_Impairment());
                Double mentalretardation = new Double(territoryDTO.getMental_Retardation_Total());
                if (totalphysical >= hearingimpairment && totalphysical >= visualimpairment && totalphysical >= mentalretardation) {
                    request.setAttribute("2ndmax", "OH");

                } else if (hearingimpairment > totalphysical && hearingimpairment > visualimpairment && hearingimpairment >= mentalretardation) {
                    request.setAttribute("2ndmax", "HI");

                } else if (visualimpairment > totalphysical && visualimpairment >= hearingimpairment && visualimpairment >= mentalretardation) {
                    request.setAttribute("2ndmax", "VI");

                } else if (mentalretardation > totalphysical && mentalretardation > hearingimpairment && mentalretardation > visualimpairment) {
                    request.setAttribute("2ndmax", "MR");

                }

            }
            PartADAO pdao = new PartADAO();
            PartADTO pdto = new PartADTO();


            //  pdao.getRailwayDoctorDetails(datasource, personcode);
            pdto = pdao.getRailwayDoctorDetails(datasource, personcode);

            if (pdto != null) {

                request.getSession().setAttribute("railway", pdto.getRail());



            }
            request.setAttribute("total", new Double(territoryDTO.getTotaldisability()));
            target = "success";
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
