package org.bf.disability.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.PartAForm;
import org.bf.disability.service.AppletAuthorityService;
import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 * This class is used to populat the disability types for disability page
 * @author deviprasad t
 * @version 1.0
 */
public class GetDisabilityTypes extends BaseAction {

    ArrayList disabilityList = new ArrayList();
    ArrayList disabilityLocoSubList = new ArrayList();
    ArrayList disabilityLocoSubSubList = new ArrayList();
    ArrayList disabilityVisualSubList = new ArrayList();
    ArrayList disabilityHearingSubList = new ArrayList();
    ArrayList disabilityMentalRetdSubList = new ArrayList();
    ArrayList disabilityMentalIllnessSubList = new ArrayList();
    String target = "success";
    int disabilityId = 0;

    /**
     * this method is used to populate the disability types for types for disability page
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws java.lang.Exception 
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PartADTO partADTO = null;
        PartAForm partAForm = null;
        DataSource ds = null;
        String agePersonal = null;
        String personCode = null;
        try {
            //DataSource ds= getDataSource(request);
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

       request.setAttribute("selectedValue", request.getAttribute("selectedValue"));
            HttpSession session = request.getSession(true);
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            ArrayList disabilityList = new ArrayList();
            partAForm = (PartAForm) form;
            PartADAO partADAO = new PartADAO();
            partADTO = new PartADTO();
            disabilityList = partADAO.getDisabilityList(ds);
            partADTO = partADAO.getCampDetails(ds, campId);

            if (session.getAttribute("sadaremCodeAu") != null) {
                personCode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personCode = (String) session.getAttribute("personcode");
            }

            AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
            if (partADTO != null && !"".equals(partADTO)) {
                if (partADTO.getCamp_venue() != null && !"".equals(partADTO.getCamp_venue())) {
                    partAForm.setCamp_venue(partADTO.getCamp_venue());
                }
                if (partADTO.getHospitalname() != null && !"".equals(partADTO.getHospitalname())) {
                    partAForm.setHospitalname(partADTO.getHospitalname());
                }
                if (partADTO.getHospitaladdress() != null && !"".equals(partADTO.getHospitaladdress())) {
                    partAForm.setHospitaladdress(partADTO.getHospitaladdress());
                }
            }
            //BeanUtils.copyProperties(partAForm,partADTO);
            partAForm = populateForm(disabilityId, form, ds);
            partAForm.setDisabilityList(disabilityList);

            agePersonal = appletAuthorityService.getAge(ds, personCode);
            session.setAttribute("agePersonal", agePersonal);

            String personstatus = null;
            if (session.getAttribute("personStatusForAU") != null) {
                personstatus = (String) session.getAttribute("personStatusForAU");
            } else {
                personstatus = (String) session.getAttribute("personstatus");
            }


            if (personstatus.equals("Eligible")) {
                target = "success";
            } else {
                disabilityList.remove(5);
                target = "rejected";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    /**
     * This method will populate the form with disability types
     * @param disabilityId 
     * @param form 
     * @param ds 
     * @throws java.lang.Exception 
     * @return ActionForward
     */
    public PartAForm populateForm(int disabilityId, ActionForm form, DataSource ds) throws Exception {
        PartAForm partAForm = (PartAForm) form;
        PartADAO partADAO = new PartADAO();
        try {

            if (disabilityId != 0) {
                switch (disabilityId) {
                    case 1: {
                        disabilityLocoSubList = partADAO.getDisabilityLocoSubList(disabilityId, ds);
                        partAForm.setDisabilityLocoSubList(disabilityLocoSubList);
                        disabilityLocoSubSubList = partADAO.getDisabilityLocoSubSubList(disabilityId, ds);
                        partAForm.setDisabilityLocoSubSubList(disabilityLocoSubSubList);
                        break;
                    }
                    case 2: {
                        disabilityVisualSubList = partADAO.getDisabilityLocoSubList(disabilityId, ds);
                        partAForm.setDisabilityVisualSubList(disabilityVisualSubList);
                        break;
                    }
                    case 3: {
                        disabilityHearingSubList = partADAO.getDisabilityLocoSubList(disabilityId, ds);
                        partAForm.setDisabilityHearingSubList(disabilityHearingSubList);
                        break;
                    }
                    case 4: {
                        disabilityMentalRetdSubList = partADAO.getDisabilityLocoSubList(disabilityId, ds);
                        partAForm.setDisabilityMentalRetdSubList(disabilityMentalRetdSubList);
                        break;
                    }
                    case 5: {
                        disabilityMentalIllnessSubList = partADAO.getDisabilityLocoSubList(disabilityId, ds);
                        partAForm.setDisabilityMentalIllnessSubList(disabilityMentalIllnessSubList);
                        break;
                    }
                    case 6: {
                        disabilityLocoSubList = partADAO.getDisabilityLocoSubList(1, ds);
                        partAForm.setDisabilityLocoSubList(disabilityLocoSubList);
                        disabilityLocoSubSubList = partADAO.getDisabilityLocoSubSubList(1, ds);
                        partAForm.setDisabilityLocoSubSubList(disabilityLocoSubSubList);

                        disabilityVisualSubList = partADAO.getDisabilityLocoSubList(2, ds);
                        partAForm.setDisabilityVisualSubList(disabilityVisualSubList);

                        disabilityHearingSubList = partADAO.getDisabilityLocoSubList(3, ds);
                        partAForm.setDisabilityHearingSubList(disabilityHearingSubList);

                        disabilityMentalRetdSubList = partADAO.getDisabilityLocoSubList(4, ds);
                        partAForm.setDisabilityMentalRetdSubList(disabilityMentalRetdSubList);

                        disabilityMentalIllnessSubList = partADAO.getDisabilityLocoSubList(5, ds);
                        partAForm.setDisabilityMentalIllnessSubList(disabilityMentalIllnessSubList);

                        break;
                    }
                    case 7: {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partAForm;
    }
}
