package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.PartCUpdateForm;
import org.bf.disability.dto.PartCUpdateDTO;
import org.bf.disability.service.PartCService;

import javax.sql.DataSource;

import org.bf.disability.common.CommonDAO;
import org.apache.struts.action.*;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.servicefactory.PartcServiceFactory;
import org.bf.disability.dao.AppletAuthorityDAO;
import org.bf.disability.dao.DoctorsInformationDAO;
import org.bf.disability.dao.PartADAO1;
import org.bf.disability.dao.PartCDAO;
import org.bf.disability.dto.DoctorsInformationDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.dto.TerritoryDTO;

import com.tcs.sadarem.util.CommonUtility;

/**
 * this action class will manipulate the person disability needs
 * @author bapinaidu
 * @version 1.0
 */
public class PartcAction extends BaseDispatchAction {

    ActionMessages actionMessages = null;

    /**
     * This method will insert person part c details into data base
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward partcInsert(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        request.setAttribute("selectedValue", request.getParameter("selectFlow"));
        //DataSource datasource=getDataSource(request);
        String personcode = null;
        String loginid = null;
        DataSource datasource = null;
        String target = "success";
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            PartCService partcservice = PartcServiceFactory.getPartcServiceImpl();

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }
            String disability = "";
            if (session.getAttribute("territoryDTOtemp") != null) 
            {
                TerritoryDTO territoryDTO = (TerritoryDTO) session.getAttribute("territoryDTOtemp");

                disability = new String(territoryDTO.getDisabilityId());
            }


            loginid = (String) session.getAttribute("loginid");
            PartCUpdateForm partcform = (PartCUpdateForm) form;
            PartCUpdateDTO partcdto = new PartCUpdateDTO();
            partcform.setSystemip((String) request.getRemoteAddr());
            try {
                BeanUtils.copyProperties(partcdto, partcform);
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            partcdto.setLoginid(loginid);
            int i2 = 0;
            int i3 = 0;
            
            if (session.getAttribute("sadaremCodeAu") != null) 
            {
                i2 = partcservice.insertPartCServiceAU(datasource, partcdto, personcode, request);
                String catid = (String) session.getAttribute("categoryIdAu");
                partcdto.setCatId(catid);
                i3 = partcservice.insertReassesment(datasource, partcdto, personcode, request);
            } 
            else 
            {
                i2 = partcservice.insertPartCService(datasource, partcdto, personcode, request);
            }


            if (i2 == 1) 
            {
                CommonDAO commonDAO = new CommonDAO();
                double eligiblePercentage = commonDAO.getDisabilityPercentage(datasource, disability);

                request.setAttribute("msg", "Inserted Successfully");
                request.setAttribute("eligiblePercentage", eligiblePercentage);
                target = "success";
            } 
            else
            {
                target = "failure";
            }
            
        } 
        catch (SADAREMDBException sADAREMException) 
        {
        	sADAREMException.printStackTrace();
        	
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } 
        catch (Exception sADAREMException) 
        {
        	sADAREMException.printStackTrace();
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     * This method will insert person part c details into data base
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward partcInsertAU(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        //DataSource datasource=getDataSource(request);
        String personcode = null;
        String loginid = null;
        DataSource datasource = null;
        String target = "success";
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            PartCService partcservice = PartcServiceFactory.getPartcServiceImpl();
            personcode = (String) session.getAttribute("sadaremCodeAu");
            loginid = (String) session.getAttribute("loginid");
            PartCUpdateForm partcform = (PartCUpdateForm) form;
            PartCUpdateDTO partcdto = new PartCUpdateDTO();
             String disability = "";
            if (session.getAttribute("territoryDTOtemp") != null) {
                TerritoryDTO territoryDTO = (TerritoryDTO) session.getAttribute("territoryDTOtemp");

                disability = new String(territoryDTO.getDisabilityId());
            }
            partcform.setSystemip((String) request.getRemoteAddr());
            try {
                BeanUtils.copyProperties(partcdto, partcform);
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            partcdto.setLoginid(loginid);
            int i2 = partcservice.insertPartCService(datasource, partcdto, personcode, request);
            if (i2 == 1) {
                CommonDAO commonDAO = new CommonDAO();
                double eligiblePercentage = commonDAO.getDisabilityPercentage(datasource, disability);

                request.setAttribute("eligiblePercentage", eligiblePercentage);
                target = "success";
            } else {
                target = "failure";
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

    /**
     * this method will get part c details from data base for a partcular person
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward selectPartc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        //DataSource datasource=getDataSource(request);
        String personcode = null;
        DataSource datasource = null;
        String target = "success";
        String disabilityID = null;
        boolean exi = false;
        String result = null;
        int maxid;
        String[] details = null;

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            PartCUpdateDTO partcdto = new PartCUpdateDTO();
            PartCService partcservice = PartcServiceFactory.getPartcServiceImpl();
            AppletAuthorityDAO partCdao = new AppletAuthorityDAO();

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            PartCUpdateForm partcform = (PartCUpdateForm) form;
            partcdto = partcservice.getLocomotorneeds(datasource, personcode);

            /* Getting the categeoryIdncategoryCount */
            details = partCdao.getCategoryDetails(datasource, personcode);

            request.setAttribute("categoryIdPartC", details[0].toString());

            String s = (String) request.getSession().getAttribute("max");
            if (s != null) {
                maxid = Integer.parseInt(s);

                // depending upon % of disability(eligible or not)
                // result=partcservice.eligibiltyForRailwayCertificate(datasource,personcode);
                PartCDAO dao = new PartCDAO();
                result = dao.eligibiltyForRailwayCertificate(datasource, personcode, maxid);

                if (result != null) {
                    request.setAttribute("exists", result);
                    // territoryform.setExi(true);
                } else if (result == null) {
                    request.setAttribute("exists", "no");

                }
            }
            try {
                BeanUtils.copyProperties(partcform, partcdto);
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            saveToken(request);
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

    /**
     * this method will get part c details from data base for a partcular person
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward selectPartcPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        //DataSource datasource=getDataSource(request);
        String personcode = null;
        DataSource datasource = null;
        String target = "success";
        String disabilityID = null;
        boolean exi = false;
        String result = null;
        int maxid;

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            PartCUpdateDTO partcdto = new PartCUpdateDTO();
            PartCService partcservice = PartcServiceFactory.getPartcServiceImpl();

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            PartCUpdateForm partcform = (PartCUpdateForm) form;
            partcdto = partcservice.getLocomotorneeds(datasource, personcode);

            String s = (String) request.getSession().getAttribute("max");
            if (s != null) {
                maxid = Integer.parseInt(s);

                // depending upon % of disability(eligible or not)
                // result=partcservice.eligibiltyForRailwayCertificate(datasource,personcode);
                PartCDAO dao = new PartCDAO();
                result = dao.eligibiltyForRailwayCertificate(datasource, personcode, maxid);

                if (result != null) {
                    request.setAttribute("exists", result);
                    // territoryform.setExi(true);
                } else if (result == null) {
                    request.setAttribute("exists", "no");

                }
            }
            try {
                BeanUtils.copyProperties(partcform, partcdto);
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            saveToken(request);
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

    /**
     * This method will update the part c details for a particular person
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    /* public ActionForward updatePartc(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws SADAREMDBException,SQLException{


    HttpSession session=request.getSession(true);
    String temp=null;
    DataSource datasource = null;
    String target="success";
    int totalPercentage = 0;
    String disabilityID=null;
    String rail_eli=request.getParameter("railwaycertificate");
    if(rail_eli!=null&& rail_eli!="")
    if(rail_eli.equalsIgnoreCase("1"))
    request.setAttribute("rail_eli", rail_eli);

    try{
    boolean flag = isTokenValid(request, true);
    if (flag) {
    datasource = getDataSource(request);
    if (datasource == null || "null".equals(datasource)) {
    datasource = JNDIDataSource.getConnection();
    }

    PartCUpdateForm partcform = (PartCUpdateForm) form;
    PartCUpdateDTO partcdto=new PartCUpdateDTO();
    PartCService partcservice=PartcServiceFactory.getPartcServiceImpl();
    boolean railwaycertificate = partcform.isRailwaycertificate();
    request.setAttribute("railwaycertificate", railwaycertificate);
    temp = request.getParameter("test");
    if (temp != null) {

    return mapping.findForward("success");
    }

    String personcode=null;//(String)session.getAttribute("personcode");

    if(session.getAttribute("sadaremCodeAu")!=null) {
    personcode = (String)session.getAttribute("sadaremCodeAu");
    }else {
    personcode = (String)session.getAttribute("personcode");
    }


    partcform.setPersoncode(personcode);
    String loginid=(String) session.getAttribute("loginid");
    partcform.setLoginid(loginid);
    partcform.setSystemip(request.getRemoteAddr());
    try {
    BeanUtils.copyProperties(partcdto,partcform);
    } catch (InvocationTargetException ex) {
    ex.printStackTrace();
    } catch (IllegalAccessException ex) {
    ex.printStackTrace();
    }
    int i=0;
    if(session.getAttribute("sadaremCodeAu")!=null) {
    i= partcservice.insertPartCService(datasource,partcdto,personcode,request);
    }else {
    i=partcservice.updatePartc(datasource,partcdto,request);
    }
    CommonDAO commonDAO = new CommonDAO();
    disabilityID = commonDAO.getDisabilityId(datasource, personcode);
    totalPercentage = commonDAO.getTotalPercentage(datasource, personcode);
    request.setAttribute("disabilityID", disabilityID);
    request.setAttribute("totalPercentage", totalPercentage);
    }


    target="success";
    } catch(SADAREMException sADAREMException)
    {
    target="exception" ;
    actionMessages = new ActionMessages();
    actionMessages.add("sadaremexceptionmessage",new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
    saveErrors(request, actionMessages);
    }
    catch(Exception sADAREMException)
    {
    target="exception" ;
    actionMessages = new ActionMessages();
    actionMessages.add("sadaremexceptionmessage",new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
    saveErrors(request, actionMessages);
    }
    session.setAttribute("displaybackbuttonforAR","true");
    return mapping.findForward(target);
    }*/
    public ActionForward updatePartc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

request.setAttribute("selectedValue","PartcUpdate");
        HttpSession session = request.getSession(true);
        String temp = null;
        DataSource datasource = null;
        String target = "success";
        int totalPercentage = 0;
         double totaldisability;
        String disabilityID = null;
        String status = "Active";//DoctorsInformationDTO
        String rail_eli = request.getParameter("railwaycertificate");
        /*  if(rail_eli!=null&& rail_eli!=""){
        if(rail_eli.equalsIgnoreCase("1")){
        request.setAttribute("rail_eli", rail_eli);
        // status="Active";
        }} else if(rail_eli==null){
        rail_eli=null;
        }else{
        rail_eli="0";
        // status="Inactive";
        }*/

        if (rail_eli != null) {
            if (rail_eli.equalsIgnoreCase("1")) {
                request.setAttribute("rail_eli", rail_eli);
                // status="Active";
            } else if (rail_eli.equalsIgnoreCase("0")) {
                rail_eli = "0";
                request.setAttribute("rail_eli", rail_eli);
                // status="Inactive";
            }


        } else if (rail_eli == null) {
            rail_eli = null;
        }


//depends on disabiity type doctor details can be obtained.
        String imp = (String) request.getSession().getAttribute("rail_impair");
        int inserted = 0;


        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            boolean flag = isTokenValid(request, true);
            if (flag) {


                PartCUpdateForm partcform = (PartCUpdateForm) form;
                PartCUpdateDTO partcdto = new PartCUpdateDTO();
                PartCService partcservice = PartcServiceFactory.getPartcServiceImpl();
                boolean railwaycertificate = partcform.isRailwaycertificate();
                request.setAttribute("railwaycertificate", railwaycertificate);
                temp = request.getParameter("test");
                if (temp != null) {

                    return mapping.findForward("success");
                }

                String personcode = null;//(String)session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }

                partcform.setPersoncode(personcode);
                String loginid = (String) session.getAttribute("loginid");
                partcform.setLoginid(loginid);
                partcform.setSystemip(request.getRemoteAddr());
                try {
                    BeanUtils.copyProperties(partcdto, partcform);
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
                // added by rekha( for appellate, to enter the data into ReassessmentDetails)
                //start
                AppletAuthorityDAO dao = new AppletAuthorityDAO();
                String[] details = dao.getCategoryDetails(datasource, personcode);
                String catid = details[0];
                partcdto.setCatId(catid);
                //end
                int i = partcservice.updatePartc(datasource, partcdto, request);
                CommonDAO commonDAO = new CommonDAO();
                disabilityID = commonDAO.getDisabilityId(datasource, personcode);
                totalPercentage = commonDAO.getTotalPercentage(datasource, personcode);
                request.setAttribute("disabilityID", disabilityID);
                request.setAttribute("totalPercentage", totalPercentage);
                request.setAttribute("totaldisability", (double)totalPercentage);

                DoctorsInformationDAO ddao = new DoctorsInformationDAO();

                double eligiblePercentage = commonDAO.getDisabilityPercentage(datasource, disabilityID);
                request.setAttribute("eligiblePercentage", eligiblePercentage);

                PartADAO1 pdao = new PartADAO1();
                DoctorsInformationDTO ddto = new DoctorsInformationDTO();
                PartADTO pdto = new PartADTO();
                int d = Integer.parseInt(disabilityID);
                String de = null;
                String dn = null;
                String rn = null;


                if (imp != null && imp.equalsIgnoreCase("hi")) {
                    if (d < 5) {
                        ddto = ddao.selectDoctors1(datasource, personcode);
                        //pdao.insertRailwayDoctordetails(datasource, personcode,ddto.getDoctorname1(),ddto.getRegisterno1(),ddto.getDesignation1(), status,rail_eli);
                        dn = ddto.getDoctorname1();
                        de = ddto.getDesignation1();
                        rn = ddto.getRegisterno1();

                    } else {
                        pdto = pdao.getMaxMultipleDoctorsDetails(datasource, personcode, "3");
                        // pdao.insertRailwayDoctordetails(datasource, personcode,pdto.getDoctor1name(),pdto.getDoctor1regnumber(),pdto.getDoctor1designation(), status,rail_eli);
                        dn = pdto.getDoctor1name();
                        de = pdto.getDoctor1designation();
                        rn = pdto.getDoctor1regnumber();
                    }


                } else if (imp != null && imp.equalsIgnoreCase("vi")) {
                    if (d < 5) {

                        ddto = ddao.selectDoctors1(datasource, personcode);
                        dn = ddto.getDoctorname1();
                        de = ddto.getDesignation1();
                        rn = ddto.getRegisterno1();
                        // pdao.insertRailwayDoctordetails(datasource, personcode,ddto.getDoctorname1(),ddto.getRegisterno1(),ddto.getDesignation1(), status,rail_eli);

                    } else {
                        pdto = pdao.getMaxMultipleDoctorsDetails(datasource, personcode, "2");
                        // pdao.insertRailwayDoctordetails(datasource, personcode,pdto.getDoctor1name(),pdto.getDoctor1regnumber(),pdto.getDoctor1designation(), status,rail_eli);
                        dn = pdto.getDoctor1name();
                        de = pdto.getDoctor1designation();
                        rn = pdto.getDoctor1regnumber();
                    }

                } else if (imp != null && imp.equalsIgnoreCase("mr")) {
                    if (d < 5) {
                        ddto = ddao.selectDoctors1(datasource, personcode);
                        de = ddto.getDesignation1();
                        de = de.toLowerCase();
                        dn = ddto.getDoctorname1();

                        /*  if(!dn.startsWith("Dr.")){
                        de="Psychologist";
                        de=de.toLowerCase();
                        }else //if(de.contains("psychologist"))
                        {
                        de="Dr";

                        }*/


                        rn = ddto.getRegisterno1();
                        //pdao.insertRailwayDoctordetails(datasource, personcode,ddto.getDoctorname1(),ddto.getRegisterno1(),ddto.getDesignation1(), status,rail_eli);
                    } else {
                        pdto = pdao.getMaxMultipleDoctorsDetails(datasource, personcode, "4");
                        //pdao.insertRailwayDoctordetails(datasource, personcode,pdto.getDoctor1name(),pdto.getDoctor1regnumber(),pdto.getDoctor1designation(), status,rail_eli);
                        String v = (String) session.getAttribute("Mdspecialistprefix");

                        dn = pdto.getDoctor1name();
                        de = pdto.getDoctor1designation();
                        rn = pdto.getDoctor1regnumber();
//for getting designation(Dr or psychologist)
                        v = v.toLowerCase();
                        /*if(v!=null && !v.equalsIgnoreCase("dr"))
                        de=v;
                        else //if(de.contains("psychologist"))
                        {
                        de="Dr";
                        }*/
                    }


                    String de1 = de.toLowerCase();
                    // if(de1.equalsIgnoreCase("psychologist")||de1.contains("psychologist")){
                    // if(de.contains("psychologist"))
                    //  request.getSession().setAttribute("dr_exists", "yes");

                    //  }


                } else if (imp != null && imp.equalsIgnoreCase("tl")) {
                    if (d < 5) {
                        ddto = ddao.selectDoctors1(datasource, personcode);
                        dn = ddto.getDoctorname1();
                        de = ddto.getDesignation1();
                        rn = ddto.getRegisterno1();
                        // pdao.insertRailwayDoctordetails(datasource, personcode,ddto.getDoctorname1(),ddto.getRegisterno1(),ddto.getDesignation1(), status,rail_eli);
                    } else {
                        pdto = pdao.getMaxMultipleDoctorsDetails(datasource, personcode, "1");
                        dn = pdto.getDoctor1name();
                        de = pdto.getDoctor1designation();
                        rn = pdto.getDoctor1regnumber();
//pdao.insertRailwayDoctordetails(datasource, personcode,pdto.getDoctor1name(),pdto.getDoctor1regnumber(),pdto.getDoctor1designation(), status,rail_eli);
                    }

                }



                /*  if(ddto!=null || pdto!=null){

                pdao.insertRailwayDoctordetails(datasource, personcode, ddto, pdto,  status,rail_eli);

                }*/
                String localaddr = CommonUtility.getClientIPAddress(request);
                // String districtid_id = (String) session.getAttribute("districtId");
                // String loginid_id= (String) session.getAttribute("loginid");
                //   int campid_id = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
                // if(dn!=null && rn!=null)

                if (imp != null && (imp.equalsIgnoreCase("tl") || imp.equalsIgnoreCase("hi") || imp.equalsIgnoreCase("vi"))) {
                    if (dn != null && !dn.contains("Dr.")) {
                        dn = "Dr. " + dn;
                    }


                }
                inserted = pdao.insertRailwayDoctordetails(datasource, personcode, dn, rn, de, status, rail_eli, localaddr, loginid);
                if (inserted > 0) {
                } else {
                    target = "exception";
                }
                request.getSession().removeAttribute("rail_impair");


            }


            target = "success";
        } catch (SADAREMDBException sADAREMException) 
        {
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
        session.setAttribute("displaybackbuttonforAR", "true");
        return mapping.findForward(target);
    }
}

