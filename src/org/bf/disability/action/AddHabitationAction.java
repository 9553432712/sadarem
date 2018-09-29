package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.AddHabitationDAO;
import org.bf.disability.dto.AddHabitationDTO;
import org.bf.disability.form.AddHabitationForm;
import org.bf.disability.service.AddHabitationService;
import org.bf.disability.servicefactory.AddHabitationServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 * This class deals with Adding a Habitation Code to the database based on 
 * the District Id, Mandal Id, Village Id,Panchayat Id, Assembly Id.
 * @description this class is a dispatch action class used to add,get and update the territory details
 * @author Pramod Kumar G
 * @version 1.0
 */
public class AddHabitationAction extends BaseDispatchAction {

    ArrayList districtlist = new ArrayList();
    ArrayList mandallist = new ArrayList();
    ArrayList panchayatlist = new ArrayList();
    ArrayList villagelist = new ArrayList();
    ArrayList assemblylist = new ArrayList();
    ArrayList camplist = new ArrayList();
    ArrayList rolelist = new ArrayList();
    String districtid = null;
    String mandalid = null;
    String panchayatid = null;
    String assemblyid = null;
    String villageid = null;
    String habitation_name = null;

    /**
     * add
     * This method interacts with AddHabitationDAO via AddHabitationService to 
     * populate the District Names List when the Jsp page is populated. Based on 
     * the District Selected, the assemblies and mandals under that district will be 
     * populated. Based on the District and Mandal, Panchayat and Village names
     * will be populated.
     * Using getHabitations() of AddHabitationDAO, get the maximum 
     * habitation_id from the database by passing the following information.
     * District id,Assembly id,Mandal id,Panchaya id,Village id.
     * Add 1 to the maximum value and generate Habitaion_code.
     * Invoke insertHabitationDetails() of AddHabitationDAO to insert a new 
     * habitation name along with all necessary details.
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @return ActionForward
     * @description this method will add new habitation into database
     * @throws org.bf.disability.Exception.SADAREMException 
     * @throws java.lang.Exception  
     */
    public ActionForward addHabitation(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "repeat";
        String controlstring = null;
        DataSource ds = null;


        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        AddHabitationService territoryservice =
                AddHabitationServiceFactory.getAddHabitationServiceImpl();
//     AddHabitationService territoryservice=new AddHabitationService();

        String status = request.getParameter("status");
        AddHabitationForm territoryForm = (AddHabitationForm) form;

        districtid = territoryForm.getDistrict_id();
        mandalid = territoryForm.getMandal_id();
        panchayatid = territoryForm.getPanchayat_id();
        villageid = territoryForm.getVillage_id();
        assemblyid = territoryForm.getAssembly_id();
        habitation_name = territoryForm.getHabitation_name();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (status == null || status.equalsIgnoreCase("")) {
                status = "update";
            }
            if (status.equals("update")) {
                districtlist = territoryservice.getDistricts(ds);
                if (districtid != null && districtid != "") {
                    assemblylist = territoryservice.getAssemblies(ds, districtid);
                    territoryForm.setAssemblylist(assemblylist);
                }
                if (districtid != null && districtid != "") {
                    mandallist = territoryservice.getMandals(ds, districtid);
                    territoryForm.setMandallist(mandallist);
                }
                if (mandalid != null && mandalid != "") {
                    panchayatlist = territoryservice.getPanchayats(ds, districtid, mandalid);
                    territoryForm.setPanchayatlist(panchayatlist);
                }
                if (mandalid != null && mandalid != "") {
                    villagelist = territoryservice.getVillages(ds, districtid,
                            mandalid, panchayatid);
                    territoryForm.setVillagelist(villagelist);
                }
                territoryForm.setDistrictlist(districtlist);

            } else if (status.equals("finish")) {
                AddHabitationDTO territoryDTO = null;
                for (Iterator iter = districtlist.iterator(); iter.hasNext();) {
                    territoryDTO = (AddHabitationDTO) iter.next();
                    if (districtid != null && districtid != "" && territoryDTO.getDistrict_id() != null && !territoryDTO.getDistrict_id().equalsIgnoreCase("")) {
                        if (territoryDTO.getDistrict_id().equals(districtid)) {
                            session.setAttribute("district_id", territoryDTO.getDistrict_id());
                            session.setAttribute("district_name", territoryDTO.getDistrict_name());

                        }
                    }
                }

                for (Iterator iter = assemblylist.iterator(); iter.hasNext();) {
                    territoryDTO = (AddHabitationDTO) iter.next();
                    if (assemblyid != null && assemblyid != "" && territoryDTO.getAssembly_id() != null && !territoryDTO.getAssembly_id().equalsIgnoreCase("")) {

                        if (territoryDTO.getAssembly_id().equals(assemblyid)) {
                            session.setAttribute("assembly_id", territoryDTO.getAssembly_id());
                            session.setAttribute("assembly_name", territoryDTO.getAssembly_name());

                        }
                    }
                }
                for (Iterator iter = mandallist.iterator(); iter.hasNext();) {
                    territoryDTO = (AddHabitationDTO) iter.next();
                    if (mandalid != null && mandalid != "" && territoryDTO.getMandal_id() != null && !territoryDTO.getMandal_id().equalsIgnoreCase("")) {

                        if (territoryDTO.getMandal_id().equals(mandalid)) {
                            session.setAttribute("mandal_id", territoryDTO.getMandal_id());
                            session.setAttribute("mandal_name", territoryDTO.getMandal_name());

                        }
                    }
                }
                for (Iterator iter = panchayatlist.iterator(); iter.hasNext();) {
                    territoryDTO = (AddHabitationDTO) iter.next();
                    if (territoryDTO.getPanchayat_id().equals(panchayatid)) {
                        session.setAttribute("panchayat_id", territoryDTO.getPanchayat_id());
                        session.setAttribute("panchayat_name", territoryDTO.getPanchayat_name());

                    }
                }
                for (Iterator iter = villagelist.iterator(); iter.hasNext();) {
                    territoryDTO = (AddHabitationDTO) iter.next();
                    if (territoryDTO.getVillage_id().equals(villageid)) {
                        session.setAttribute("village_id", territoryDTO.getVillage_id());
                        session.setAttribute("village_name", territoryDTO.getVillage_name());

                    }
                }

                String habi_id = territoryservice.getHabitations(ds, districtid,
                        mandalid, panchayatid, villageid, assemblyid);

                if (habi_id == null) {
                    habi_id = "00";
                }

                int len = habi_id.length();
                int value = Integer.parseInt(habi_id);

                value++;

                if (value > 9) {
                    habi_id = habi_id.valueOf(value);
                } else {
                    habi_id = "0" + habi_id.valueOf(value);
                }

                String habi_code =
                        districtid + assemblyid + mandalid + villageid + habi_id + panchayatid;

                BeanUtils.copyProperties(territoryDTO, territoryForm);
                territoryDTO.setHabitation_code(habi_code);
                territoryDTO.setHabitation_name(habitation_name);
                territoryDTO.setHabitation_id(habi_id);
                territoryDTO.setSystem_ip((String) request.getRemoteAddr());
                AddHabitationDAO.insertHabitationDetails(ds, territoryDTO);
                request.setAttribute("msg", "Habitation Added Successfully !");
                target = "finish";

            }//end of else if

        }//end of try
        catch (SADAREMDBException sADAREMException) {
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
    }//end of execute()

    /**
     * 
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @return ActionForward
     * @description this method will add the new village into the data base
     * @throws org.bf.disability.Exception.SADAREMException 
     * @throws java.lang.Exception 
     */
    public ActionForward addVillage(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "repeat";
        DataSource ds = null;
        //DataSource


        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        AddHabitationService territoryservice =
                AddHabitationServiceFactory.getAddHabitationServiceImpl();
//     AddHabitationService territoryservice=new AddHabitationService();

        String status = request.getParameter("status");
        AddHabitationForm territoryForm = (AddHabitationForm) form;

        districtid = territoryForm.getDistrict_id();
        mandalid = territoryForm.getMandal_id();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {
                districtlist = territoryservice.getDistricts(ds);

                if (districtid != null && districtid != "") {
                    mandallist = territoryservice.getMandals(ds, districtid);
                    territoryForm.setMandallist(mandallist);
                }

                territoryForm.setDistrictlist(districtlist);

            } else if (status.equals("finish")) {
                AddHabitationDTO territoryDTO = null;
                for (Iterator iter = districtlist.iterator(); iter.hasNext();) {
                    territoryDTO = (AddHabitationDTO) iter.next();
                    if (territoryDTO.getDistrict_id().equals(districtid)) {
                        session.setAttribute("district_id", territoryDTO.getDistrict_id());
                        session.setAttribute("district_name", territoryDTO.getDistrict_name());
                    }
                }

                for (Iterator iter = mandallist.iterator(); iter.hasNext();) {
                    territoryDTO = (AddHabitationDTO) iter.next();
                    if (territoryDTO.getMandal_id().equals(mandalid)) {
                        session.setAttribute("mandal_id", territoryDTO.getMandal_id());
                        session.setAttribute("mandal_name", territoryDTO.getMandal_name());
                    }
                }

                String vill_id = territoryservice.getVillages(ds, districtid, mandalid);

                if (vill_id == null) {
                    vill_id = "0";
                }

                int value = Integer.parseInt(vill_id);


                value++;

                if (value < 10) {
                    vill_id = "00" + vill_id.valueOf(value);
                } else if (value >= 10 && value < 100) {
                    vill_id = "0" + vill_id.valueOf(value);
                } else {
                    vill_id = vill_id.valueOf(value);
                }


                BeanUtils.copyProperties(territoryDTO, territoryForm);
                territoryDTO.setVillage_id(vill_id);
                territoryDTO.setSystem_ip((String) request.getRemoteAddr());
                AddHabitationDAO.insertVillageDetails(ds, territoryDTO);
                request.setAttribute("msg", "Village Added Successfully !");
                target = "finish";

            }//end of else if

        }//end of try
        catch (SADAREMDBException sADAREMException) {
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
    }//end of addVillage()

    /**
     * 
     * @description this method is used to add new panchayat to a partcular mandal and district
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @throws java.lang.Exception 
     * @return ActionForward
     */
    public ActionForward addPanchayat(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "repeat";
        DataSource ds = null;


        //DataSource 


        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        AddHabitationService territoryservice =
                AddHabitationServiceFactory.getAddHabitationServiceImpl();
//     AddHabitationService territoryservice=new AddHabitationService();

        String status = request.getParameter("status");
        AddHabitationForm territoryForm = (AddHabitationForm) form;

        districtid = territoryForm.getDistrict_id();
        mandalid = territoryForm.getMandal_id();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {
                districtlist = territoryservice.getDistricts(ds);

                if (districtid != null && districtid != "") {
                    mandallist = territoryservice.getMandals(ds, districtid);
                    territoryForm.setMandallist(mandallist);
                }

                territoryForm.setDistrictlist(districtlist);

            } else if (status.equals("finish")) {
                AddHabitationDTO territoryDTO = null;
                for (Iterator iter = districtlist.iterator(); iter.hasNext();) {
                    territoryDTO = (AddHabitationDTO) iter.next();
                    if (territoryDTO.getDistrict_id().equals(districtid)) {
                        session.setAttribute("district_id", territoryDTO.getDistrict_id());
                        session.setAttribute("district_name", territoryDTO.getDistrict_name());
                    }
                }

                for (Iterator iter = mandallist.iterator(); iter.hasNext();) {
                    territoryDTO = (AddHabitationDTO) iter.next();
                    if (territoryDTO.getMandal_id().equals(mandalid)) {
                        session.setAttribute("mandal_id", territoryDTO.getMandal_id());
                        session.setAttribute("mandal_name", territoryDTO.getMandal_name());
                    }
                }

                String pan_id = territoryservice.getMaxPanchayat(ds, districtid, mandalid);

                if (pan_id == null) {
                    pan_id = "0";
                }

                int value = Integer.parseInt(pan_id);


                value++;

                if (value > 9) {
                    pan_id = pan_id.valueOf(value);
                } else {
                    pan_id = "0" + pan_id.valueOf(value);
                }



                BeanUtils.copyProperties(territoryDTO, territoryForm);

                territoryDTO.setPanchayat_id(pan_id);
                territoryDTO.setSystem_ip((String) request.getRemoteAddr());

                AddHabitationDAO.insertPanchayatDetails(ds, territoryDTO);
                request.setAttribute("msg", "Panchayat Added Successfully !");
                target = "finish";

            }//end of else if

        }//end of try
        catch (SADAREMDBException sADAREMException) {
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
    }//end of getMaxPanchayat()

    /**
     * 
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @return ActionForward
     * @description this method will add new mandal into database for a particular district
     * @throws org.bf.disability.Exception.SADAREMException 
     * @throws java.lang.IllegalAccessException 
     * @throws java.lang.reflect.InvocationTargetException 
     * @throws java.lang.Exception 
     */
    public ActionForward addMandal(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException,
            IllegalAccessException,
            InvocationTargetException, Exception {
        String target = "repeat";
        DataSource ds = null;


        //DataSource 


        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        AddHabitationService territoryservice =
                AddHabitationServiceFactory.getAddHabitationServiceImpl();
//     AddHabitationService territoryservice=new AddHabitationService();

        String status = request.getParameter("status");
        AddHabitationForm territoryForm = (AddHabitationForm) form;

        districtid = territoryForm.getDistrict_id();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {
                districtlist = territoryservice.getDistricts(ds);
                territoryForm.setDistrictlist(districtlist);
            } else if (status.equals("finish")) {
                AddHabitationDTO territoryDTO = null;
                for (Iterator iter = districtlist.iterator(); iter.hasNext();) {
                    territoryDTO = (AddHabitationDTO) iter.next();
                    if (territoryDTO.getDistrict_id().equals(districtid)) {
                        session.setAttribute("district_id", territoryDTO.getDistrict_id());
                        session.setAttribute("district_name", territoryDTO.getDistrict_name());
                    }
                }

                String man_id = territoryservice.getMaxMandal(ds, districtid);

                if (man_id == null) {
                    man_id = "0";
                }

                int value = Integer.parseInt(man_id);


                value++;

                if (value > 9) {
                    man_id = man_id.valueOf(value);
                } else {
                    man_id = "0" + man_id.valueOf(value);
                }



                BeanUtils.copyProperties(territoryDTO, territoryForm);

                territoryDTO.setMandal_id(man_id);
                territoryDTO.setSystem_ip((String) request.getRemoteAddr());
                AddHabitationDAO.insertMandalDetails(ds, territoryDTO);
                request.setAttribute("msg", "Mandal Added Successfully !");
                target = "finish";

            }//end of else if

        }//end of try
        catch (SADAREMDBException sADAREMException) {
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
    }//end of getMaxMandal()

    /**
     * 
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @return ActionForward
     * @description this method will add new assembly into database for a prticular district
     * @throws org.bf.disability.Exception.SADAREMException 
     * @throws java.lang.Exception 
     */
    public ActionForward addAssembly(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "repeat";
        DataSource ds = null;



        //DataSource 


        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        AddHabitationService territoryservice =
                AddHabitationServiceFactory.getAddHabitationServiceImpl();
//     AddHabitationService territoryservice=new AddHabitationService();

        String status = request.getParameter("status");
        AddHabitationForm territoryForm = (AddHabitationForm) form;

        districtid = territoryForm.getDistrict_id();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {
                districtlist = territoryservice.getDistricts(ds);
                territoryForm.setDistrictlist(districtlist);
            } else if (status.equals("finish")) {
                AddHabitationDTO territoryDTO = null;
                for (Iterator iter = districtlist.iterator(); iter.hasNext();) {
                    territoryDTO = (AddHabitationDTO) iter.next();
                    if (territoryDTO.getDistrict_id().equals(districtid)) {
                        session.setAttribute("district_id", territoryDTO.getDistrict_id());
                        session.setAttribute("district_name", territoryDTO.getDistrict_name());
                    }
                }

                String asmly_id = territoryservice.getMaxAssembly(ds, districtid);

                if (asmly_id == null) {
                    asmly_id = "0";
                }

                int value = Integer.parseInt(asmly_id);


                value++;
                if (value < 10) {
                    asmly_id = "00" + asmly_id.valueOf(value);
                } else if (value >= 10 && value < 100) {
                    asmly_id = "0" + asmly_id.valueOf(value);
                } else {
                    asmly_id = asmly_id.valueOf(value);
                }




                BeanUtils.copyProperties(territoryDTO, territoryForm);

                territoryDTO.setAssembly_id(asmly_id);
                territoryDTO.setSystem_ip((String) request.getRemoteAddr());
                AddHabitationDAO.insertAssemblyDetails(ds, territoryDTO);
                request.setAttribute("msg", "Assembly Added Successfully !");
                target = "finish";

            }//end of else if

        }//end of try
        catch (SADAREMDBException sADAREMException) {
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
    }//end of getMaxAssemly()

    /**
     * 
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @return ActionForward
     * @description this method will add new district into data base
     * @throws org.bf.disability.Exception.SADAREMException 
     * @throws java.lang.IllegalAccessException 
     * @throws java.lang.reflect.InvocationTargetException 
     * @throws java.lang.Exception 
     */
    public ActionForward addDistrict(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException,
            IllegalAccessException,
            InvocationTargetException, Exception {
        String target = "finish";
        DataSource ds = null;


        //DataSource 


        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        AddHabitationService territoryservice =
                AddHabitationServiceFactory.getAddHabitationServiceImpl();
//     AddHabitationService territoryservice=new AddHabitationService();

        AddHabitationForm territoryForm = (AddHabitationForm) form;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            AddHabitationDTO territoryDTO = new AddHabitationDTO();
            String dist_id = territoryservice.getMaxDistrict(ds);

            if (dist_id == null) {
                dist_id = "0";
            }

            int value = Integer.parseInt(dist_id);

            value++;
            if (value < 9) {
                dist_id = "0" + dist_id.valueOf(value);
            } else {
                dist_id = dist_id.valueOf(value);
            }
            BeanUtils.copyProperties(territoryDTO, territoryForm);
            territoryDTO.setDistrict_id(dist_id);
            territoryDTO.setSystem_ip((String) request.getRemoteAddr());
            AddHabitationDAO.insertDistrictDetails(ds, territoryDTO);
            request.setAttribute("msg", "District Added Successfully !");
        }//end of try
        catch (SADAREMDBException sADAREMException) {
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
    }//end of getMaxDistrict()

    // added For inserting camp Details
    public ActionForward addCamp(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "repeat";
        String loginid = null;
        DataSource ds = null;



        //DataSource 


        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;

        String VenueName = (String) request.getParameter("camp_venue");
        AddHabitationService territoryservice = AddHabitationServiceFactory.getAddHabitationServiceImpl();

        String status = request.getParameter("status");
        AddHabitationForm territoryForm = (AddHabitationForm) form;

        districtid = territoryForm.getDistrict_id();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {
                districtlist = territoryservice.getDistricts(ds);
                territoryForm.setDistrictlist(districtlist);
            } else if (status.equals("finish")) {
                AddHabitationDTO territoryDTO = new AddHabitationDTO();
                loginid = (String) session.getAttribute("loginid");
                territoryForm.setLogin_id(loginid);
                territoryForm.setSystem_ip((String) request.getRemoteAddr());
                BeanUtils.copyProperties(territoryDTO, territoryForm);

                int i = AddHabitationDAO.insertCampDetails(ds, territoryDTO, VenueName);

                if (i == 1) {

                    request.setAttribute("msg", "Camp Added Successfully !");
                    target = "finish";
                } else if (i == 0) {

                    request.setAttribute("msg", "Camp AlreadyExist !");
                    target = "failure";
                }
            }//end of else if

        }//end of try
        catch (SADAREMDBException sADAREMException) {
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

    // added For Updateing camp Details
    public ActionForward selectCamp(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {


        String target = "repeat";
        ActionMessages actionMessages = null;
        String districtid = null;
        AddHabitationDTO addHabitationDTO = null;
        ArrayList campDetailsList = null;
        DataSource ds = null;

        try {
            //DataSource 

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            AddHabitationService territoryservice = AddHabitationServiceFactory.getAddHabitationServiceImpl();
            String status = request.getParameter("status");

            AddHabitationForm territoryForm = (AddHabitationForm) form;
            districtid = territoryForm.getDistrict_id();
            HttpSession session = request.getSession();


            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {
                districtlist = territoryservice.getDistricts(ds);
                territoryForm.setDistrictlist(districtlist);
                if (districtid != null && !"".equals(districtid)) {
                    addHabitationDTO = new AddHabitationDTO();
                    campDetailsList = new ArrayList();

                    campDetailsList = territoryservice.getCampDetails(ds, addHabitationDTO, districtid);

                    request.setAttribute("campDetailsList", campDetailsList);
                }
            }
        }//end of try
        catch (SADAREMDBException sADAREMException) {
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

    public ActionForward updateCamp(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        ActionMessages actionMessages = null;


        String target = null;
        int camp_id = 0;
        String Address = null;
        String Name = null;
        String VenueName = null;
        String districtid = null;
        String loginid = null;
        HttpSession session = request.getSession();
        DataSource ds = null;



        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            AddHabitationDTO territoryDTO = new AddHabitationDTO();
            AddHabitationForm addhabitationform = (AddHabitationForm) form;
            Address = addhabitationform.getCamp_address();

            //camp_id = addhabitationform.getCamp_id();
            String campid = (String) request.getParameter("camp_id");
            if (campid != null) {
                camp_id = Integer.parseInt(campid.trim());
            }
            Name = addhabitationform.getCamp_name();
            VenueName = addhabitationform.getCamp_venue();

            //DataSource 


            AddHabitationService addhabitationservice = AddHabitationServiceFactory.getAddHabitationServiceImpl();
            loginid = (String) session.getAttribute("loginid");
            addhabitationform.setLogin_id(loginid);
            addhabitationform.setSystem_ip((String) request.getRemoteAddr());
            BeanUtils.copyProperties(territoryDTO, addhabitationform);
            int i = addhabitationservice.updateCamp(ds, camp_id, addhabitationform);
            if (i == 0) {
                target = "success";
                request.setAttribute("msg", "Camp Update Successfully !");
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

    public ActionForward updateCampDiplay(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {


        ActionMessages actionMessages = null;
        String target = "null";
        String camp_id = "null";
        String camp_name = "null";
        String camp_address = "null";
        String camp_venue = "null";
        camp_id = request.getParameter("campid");

        camp_name = request.getParameter("camp_name");
        camp_address = request.getParameter("camp_address");
        camp_venue = request.getParameter("camp_venue");
        request.setAttribute("id", camp_id);
        request.setAttribute("name", camp_name);
        request.setAttribute("address", camp_address);
        request.setAttribute("venue", camp_venue);
        AddHabitationService addhabitationservice = AddHabitationServiceFactory.getAddHabitationServiceImpl();
        AddHabitationForm territoryForm = (AddHabitationForm) form;
        districtid = territoryForm.getDistrict_name();

        request.setAttribute("districtname", "districtid");

        if (camp_id != null) {
            target = "success";
        } else {
            target = "failure";
        }


        return mapping.findForward(target);
    }

    public ActionForward deleteCamp(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {


        ActionMessages actionMessages = null;
        DataSource ds = null;

        String target = "null";
        String camp_id = "null";
        String district_id = "null";
        camp_id = request.getParameter("campid");
        district_id = request.getParameter("district_id");
        request.setAttribute("id", camp_id);
        try {

            //DataSource 
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            AddHabitationService addhabitationservice = AddHabitationServiceFactory.getAddHabitationServiceImpl();

            int i = addhabitationservice.deleteCamp(ds, camp_id, district_id);
            if (i == 1) {
                target = "success";
                request.setAttribute("msg", "Camp Delete Successfully !");
            } else if (i == 0) {
                target = "failure";
                request.setAttribute("msg", "Camp Name Already Exist !");
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

    // added this method for login based on districtid
    public ActionForward addLogin(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "repeat";
        ActionMessages actionMessages = null;
        String userName = null;
        DataSource ds = null;


        try {
            userName = request.getParameter("user_name");//create user name value

            //DataSource 
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            AddHabitationService territoryservice = AddHabitationServiceFactory.getAddHabitationServiceImpl();
            String status = request.getParameter("status");
            AddHabitationForm territoryForm = (AddHabitationForm) form;
            districtid = territoryForm.getDistrict_id();
            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {
                rolelist = territoryservice.getRoles(ds);
                territoryForm.setRolelist(rolelist);
                districtlist = territoryservice.getDistricts(ds);
                if (districtid != null && districtid != "") {
                    camplist = territoryservice.getCamps(ds, districtid);
                    territoryForm.setCamplist(camplist);
                }

                territoryForm.setDistrictlist(districtlist);

            } else if (status != null && status.equals("finish")) {
                AddHabitationDTO territoryDTO = new AddHabitationDTO();
                BeanUtils.copyProperties(territoryDTO, territoryForm);
                territoryDTO.setSystem_ip((String) request.getRemoteAddr());
                int insertPersonLoginDetails = AddHabitationDAO.insertLoginDetails(ds, territoryDTO, username, userName);
                if (insertPersonLoginDetails == 1) {
                    request.setAttribute("msg", "Login Added Successfully !");
                    target = "finish";
                } else if (insertPersonLoginDetails == 0) {
                    request.setAttribute("msg", "UserName " + userName + " Already Exists !");
                    target = "reject";
                }
                territoryForm.setPerson_name(null);
                territoryForm.setUser_name(null);
                territoryForm.setPassword(null);
                territoryForm.setRole_id(0);
                territoryForm.setDistrict_id(null);
                territoryForm.setCamp_id(0);
                rolelist = territoryservice.getRoles(ds);
                territoryForm.setRolelist(rolelist);
                districtlist = territoryservice.getDistricts(ds);
                territoryForm.setDistrictlist(districtlist);
            }//end of else if
            request.setAttribute("userName", territoryForm.getUser_name());
            request.setAttribute("password", territoryForm.getPassword());

        }//end of try
        catch (SADAREMDBException sADAREMException) {
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

    public ActionForward getUserDetails(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        AddHabitationDTO addHabitationDTO = null;
        String target = "repeat";
        ActionMessages actionMessages = null;
        HttpSession session = request.getSession();
        String districtid = (String) session.getAttribute("districtId");
        int campid_id = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
        ArrayList userdetailslist = null;
        DataSource ds = null;

        AddHabitationForm territoryForm = (AddHabitationForm) form;
        try {
            //DataSource
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String status = request.getParameter("status");


            if (status == null) {
                status = "update";
            }
            if (status.equals("update")) {
                AddHabitationService territoryservice = AddHabitationServiceFactory.getAddHabitationServiceImpl();
                addHabitationDTO = new AddHabitationDTO();
                userdetailslist = new ArrayList();
                userdetailslist = territoryservice.getUserDetails(ds, addHabitationDTO, districtid, campid_id);
                request.setAttribute("userdetailslist", userdetailslist);
            } else if (status.equals("finish")) {
                AddHabitationDTO territoryDTO = new AddHabitationDTO();
                BeanUtils.copyProperties(territoryDTO, territoryForm);
                territoryDTO.setSystem_ip((String) request.getRemoteAddr());
                String user_name = request.getParameter("user_name");
                String role_id = request.getParameter("role_id");
                String rowid = (String) request.getParameter("rowid");
                int updateUserDetails = AddHabitationDAO.updateUserDetails(ds, territoryDTO, role_id, rowid);
                if (updateUserDetails == 1) {
                    request.setAttribute("msg", "User Role Updated Successfully !");
                    target = "finish";
                } else if (updateUserDetails == 0) {
                    request.setAttribute("msg", " Update Failure !");
                    //request.setAttribute("userName", userName);
                    target = "reject";
                }//end of else if

            }
        }//end of try
        catch (SADAREMDBException sADAREMException) {
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
}//end of class

