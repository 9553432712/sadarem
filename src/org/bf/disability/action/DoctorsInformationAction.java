package org.bf.disability.action;
/*
 * DoctorsInformationAction.java
 *
 * Created on September 8, 2008, 6:22 PM
 */

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.DoctorsInformationDAO;
import org.bf.disability.dto.DoctorsInformationDTO;
import org.bf.disability.form.DoctorsInformationForm;
import org.bf.disability.service.DoctorsInformationService;
import org.bf.disability.servicefactory.DoctorsInformationServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import java.util.Iterator;

import org.bf.disability.dto.TerritoryDTO;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @description this action class is used to manipulate doctors information
 * @author pramodh kumar .g
 * @version 1.0
 */
public class DoctorsInformationAction extends BaseDispatchAction {

    private static String SUCCESS = "success";

    /**
     *
     * @description this method will insert the doctor information in to the data base for a particular camp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward insertDoctorInformation(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException,
            Exception {
        String target = null;
        DataSource datasource = null;
        DoctorsInformationForm doctorsinfoform = (DoctorsInformationForm) form;

        //DataSource datasource=getDataSource(request);

        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        DoctorsInformationService doctorsinfoservice =
                DoctorsInformationServiceFactory.getDoctorsInformationServiceImpl();

        String localaddr = CommonUtility.getClientIPAddress(request);
        String districtid_id = CommonUtility.checkNullObj(session.getAttribute("districtId"));
        String loginid_id = CommonUtility.checkNullObj(session.getAttribute("loginid"));
        int campid_id = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));

        DoctorsInformationDTO doctorsinfodto = new DoctorsInformationDTO();
        DoctorsInformationDAO doctorsinfodao = new DoctorsInformationDAO();
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            target = "success";
            request.setAttribute("msg", "Doctors information Added Successfully");
            if (doctorsinfodao.checkValidity(datasource,
                    doctorsinfoform.getTypeofdisability(), campid_id) == false) {
                if (!"4".equals(doctorsinfoform.getTypeofdisability()) || !"6".equals(doctorsinfoform.getTypeofdisability())) {
                    doctorsinfoform.setSpecialistprefix("Dr");
                }
                BeanUtils.copyProperties(doctorsinfodto, doctorsinfoform);
                int success = doctorsinfoservice.insertDoctorsDetails(datasource, doctorsinfodto, localaddr, districtid_id, loginid_id, campid_id);
                if (success > 0) {
                    //int i = doctorsinfoservice.addUser(datasource, doctorsinfodto, districtid_id, loginid_id, campid_id, localaddr, "1");
                    //int j = doctorsinfoservice.addUser(datasource, doctorsinfodto, districtid_id, loginid_id, campid_id, localaddr, "2");
                    //int k = doctorsinfoservice.addUser(datasource, doctorsinfodto, districtid_id, loginid_id, campid_id, localaddr, "3");
                }
            } else {
                target = "failure";
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

    /**
     *
     * @description this method will get the doctors information for a particular camp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward selectDoctorInformation(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        DataSource datasource = null;
        String personCode = null;
        ArrayList personStatusList = null;
        String personstatus = null;
        String doctorname = null;

        DoctorsInformationForm doctorsinfoform = (DoctorsInformationForm) form;
        DoctorsInformationDAO doctorsinfodao = new DoctorsInformationDAO();
        String disabilitytype = doctorsinfoform.getTypeofdisability();
        ActionMessages actionMessages = null;
        DoctorsInformationService doctorsinfoservice =
                DoctorsInformationServiceFactory.getDoctorsInformationServiceImpl();

        DoctorsInformationDTO doctorsinfodto = new DoctorsInformationDTO();
        boolean doctorsUpdate = Boolean.parseBoolean(request.getParameter("doctorsUpdate"));
        personCode = request.getParameter("personcode");
        //DataSource datasource=getDataSource(request);


        HttpSession session = request.getSession();
        int campid_id = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
        String districtid_id = CommonUtility.checkNullObj(session.getAttribute("districtId"));
        String role_id = CommonUtility.checkNullObj(session.getAttribute("roleId"));

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            if (doctorsUpdate)
            {
            	
            	CommonDAOImpl comObj1 = new CommonDAOImpl();
            	HashMap GeoDtls = comObj1.getGEODetailsbySADAREMID(personCode);
            	String distIdFromSadaremId = GeoDtls.get("districtid").toString();
             	if(districtid_id.equals(distIdFromSadaremId) || CommonConstants.ROLE_APPELLATE.equals(role_id))
            	{
            		personStatusList = new ArrayList();
                    TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
                    personStatusList = territoryservice.getPersonStatus(datasource, personCode);
                    if (null != personStatusList && personStatusList.size() > 0)
                    {
                        Iterator iterator = personStatusList.iterator();
                        iterator.hasNext();
                        TerritoryDTO territoryDTO = (TerritoryDTO) iterator.next();
                        personstatus = territoryDTO.getPersonstatus();
                        request.setAttribute("personCode", personCode);
                        request.setAttribute("doctorsUpdate", doctorsUpdate);
                        doctorsinfodto = doctorsinfoservice.selectDoctors(datasource, personCode);
                        doctorname = doctorsinfodto.getDoctorname1();
                        if (null != doctorname)
                        {
                            BeanUtils.copyProperties(doctorsinfoform, doctorsinfodto);
                            SUCCESS = "success";
                        } 
                        else
                        {
                            SUCCESS = "failure";
                        }

                    } 
                    else
                    {
                        SUCCESS = "failurepersoncode";
                        request.setAttribute("msg", "Invalid Personcode");
                    }
            	}
            	else
            	{
            		SUCCESS = "failurepersoncode";
                    request.setAttribute("msg", "SADAREM ID does not belongs your district");
            	}               
            } 
            else if (doctorsinfodao.checkValidity(datasource, doctorsinfoform.getTypeofdisability(), campid_id) == true)
            {
                session.setAttribute("typeofdisability", disabilitytype);


                doctorsinfodto =  doctorsinfoservice.selectDoctorDetails(datasource, disabilitytype, campid_id);

                try {
                    BeanUtils.copyProperties(doctorsinfoform, doctorsinfodto);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                }
                SUCCESS = "success";
            } else {
                SUCCESS = "failure";
            }
            request.setAttribute("disabilitytype", disabilitytype);
        } catch (SADAREMDBException sADAREMException) {
            SUCCESS = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            SUCCESS = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(SUCCESS);
    }

    /**
     *
     * @description  this method will update the doctors information into the database for a particular camp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * @return ActionForward
     */
    public ActionForward updateDoctorInformation(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException,
            IllegalAccessException,
            InvocationTargetException {

        DataSource datasource = null;
        String personcode = null;

        DoctorsInformationForm doctorsinfoform = (DoctorsInformationForm) form;
        boolean doctorsUpdate = Boolean.parseBoolean(request.getParameter("doctorsUpdate"));
        personcode = request.getParameter("personcode");
        //DataSource datasource=getDataSource(request);

        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        String localaddr = CommonUtility.getClientIPAddress(request);
        String districtid_id = CommonUtility.checkNullObj(session.getAttribute("districtId"));
        String loginid_id = CommonUtility.checkNullObj(session.getAttribute("loginid"));
        int campid_id = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
        String target = "success";
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            DoctorsInformationService doctorsinfoservice =
                    DoctorsInformationServiceFactory.getDoctorsInformationServiceImpl();
//        DoctorsInformationService doctorsinfoservice=new DoctorsInformationService();
            DoctorsInformationDTO doctorsinfodto = new DoctorsInformationDTO();
            doctorsinfoform.setTypeofdisability(CommonUtility.checkNullObj(session.getAttribute("typeofdisability")));
            BeanUtils.copyProperties(doctorsinfodto, doctorsinfoform);
            if (doctorsUpdate)
            {
            	CommonDAOImpl comObj1 = new CommonDAOImpl();
            	HashMap GeoDtls = comObj1.getGEODetailsbySADAREMID(personcode);
            	String distIdFromSadaremId = GeoDtls.get("districtid").toString();
            	String role_id = CommonUtility.checkNullObj(session.getAttribute("roleId"));
            	if(districtid_id.equals(distIdFromSadaremId) || CommonConstants.ROLE_APPELLATE.equals(role_id))
            	{
            		doctorsinfoservice.updateDoctors(datasource, doctorsinfodto, personcode,campid_id,role_id);
                    request.setAttribute("msg", "Doctors information Updated Successfully");
            	}
            	else
            	{
            		request.setAttribute("msg", "SADAREM ID does not belongs your district");
            	}
                
            } 
            else 
            {
                int success = doctorsinfoservice.updateDoctorDetails(datasource, doctorsinfodto, campid_id);
                if (success > 0) {
                    //int i = doctorsinfoservice.addUser(datasource, doctorsinfodto, districtid_id, loginid_id, campid_id, localaddr,"1");
                    //int j = doctorsinfoservice.addUser(datasource, doctorsinfodto, districtid_id, loginid_id, campid_id, localaddr, "2");
                    //int k = doctorsinfoservice.addUser(datasource, doctorsinfodto, districtid_id, loginid_id, campid_id, localaddr, "3");

                }
                request.setAttribute("msg", "Doctors information Updated Successfully");
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

    /**
     *
     * @description this method will be used to populate the disability type to select for adding doctors information
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws java.lang.Exception
     */
    public ActionForward selectDisabilityTypes(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DoctorsInformationService doctorsinfoservice =
                DoctorsInformationServiceFactory.getDoctorsInformationServiceImpl();
//        DoctorsInformationService doctorsinfoservice=new DoctorsInformationService();
        DataSource datasource = null;
        try {
            //DataSource datasource=getDataSource(request);
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession();
            ArrayList disabilitytype = new ArrayList();
            disabilitytype = doctorsinfoservice.selectDisabilityTypes(datasource);
            disabilitytype.remove(5);
            request.setAttribute("disabilitytype", disabilitytype);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    public ActionForward getDoctorDetailsforPartA(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        DataSource datasource = null;
        String disability_type = CommonUtility.checkNullObj(request.getParameter("disability_type"));
//        DoctorsInformationForm doctorsinfoform=(DoctorsInformationForm)form;
        DoctorsInformationDAO doctorsinfodao = new DoctorsInformationDAO();
//        String disabilitytype=doctorsinfoform.getTypeofdisability();
        ActionMessages actionMessages = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            DoctorsInformationService doctorsinfoservice =
                    DoctorsInformationServiceFactory.getDoctorsInformationServiceImpl();

            DoctorsInformationDTO doctorsinfodto = new DoctorsInformationDTO();
            //DataSource datasource=getDataSource(request);

            HttpSession session = request.getSession();
            doctorsinfodto =
                    doctorsinfodao.getDoctorDetailsforPartA(datasource, disability_type);

            PrintWriter out = response.getWriter();

            response.setHeader("Cache-Control", "private");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setContentType("text/xml");

            out.println("<root>");
            if (doctorsinfodto != null) {
                out.println("<name>" + doctorsinfodto.getHospitalname() + "</name>");
                out.println("<name>" + doctorsinfodto.getHospitaladdress() + "</name>");
                out.println("<name>" + doctorsinfodto.getDoctorname1() + "</name>");
                out.println("<name>" + doctorsinfodto.getRegisterno1() + "</name>");
                out.println("<name>" + doctorsinfodto.getDesignation1() + "</name>");
                out.println("<name>" + doctorsinfodto.getDoctorname2() + "</name>");
                out.println("<name>" + doctorsinfodto.getRegisterno2() + "</name>");
                out.println("<name>" + doctorsinfodto.getDesignation2() + "</name>");
                out.println("<name>" + doctorsinfodto.getDoctorname3() + "</name>");
                out.println("<name>" + doctorsinfodto.getRegisterno3() + "</name>");
                out.println("<name>" + doctorsinfodto.getDesignation3() + "</name>");
            } else {
                out.println("<name>" + "-no data found-" + "</name>");
                out.println("<name>" + "-no data found-" + "</name>");
                out.println("<name>" + "-no data found-" + "</name>");
                out.println("<name>" + "-no data found-" + "</name>");
                out.println("<name>" + "-no data found-" + "</name>");
                out.println("<name>" + "-no data found-" + "</name>");
                out.println("<name>" + "-no data found-" + "</name>");
                out.println("<name>" + "-no data found-" + "</name>");
                out.println("<name>" + "-no data found-" + "</name>");
                out.println("<name>" + "-no data found-" + "</name>");
                out.println("<name>" + "-no data found-" + "</name>");
            }

            out.println("</root>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
