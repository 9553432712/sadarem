/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.calculateResult;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.service.CommonService;
import org.bf.disability.servicefactory.CommonServiceFactory;
import org.bf.disability.form.WebsiteCommonForm;
import org.bf.disability.dto.WebsiteCommonDTO;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.form.PartCUpdateForm;
import org.bf.disability.service.MentalRetardationService;
import org.bf.disability.servicefactory.MentalRetardationServiceFactory;

/**
 *
 * @author 509865
 */
public class CommonAction extends BaseDispatchAction {

    ActionMessages actionMessages = null;
    DataSource ds = null;

    public ActionForward unspecified(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        return mapping.findForward("success");

    }

    /**
     * this method will be used to get status of person details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward getViewOrEdit(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "success";
        DataSource datasource = null;
        


        String personCode = null;
        String status = null;

        //DataSource datasource=getDataSource(request);


        personCode = request.getParameter("personcode");
        CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
        request.setAttribute("personCode", personCode);
        try {datasource = getDataSource(request);
        if (datasource == null || "null".equals(datasource)) {
            datasource = JNDIDataSource.getConnection();
        }
            status = commonservice.selectStatus(datasource, personCode);
            if (status == null) {
                request.setAttribute("status", "Invalid Person Code Please Enter Valid Person Code");
                target = "fallure";
                return mapping.findForward(target);
            } else {
                request.setAttribute("status", status);
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
     * this method will be used to get status of person details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    //added by rekha
    public ActionForward getForwardBack(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String personcode = null;
        DataSource datasource = null;
        

        String target = "success";
        String disabilityID = null;
        int totalPercentage = 0;
        PartCUpdateForm partcform = (PartCUpdateForm) form;
        try {datasource = getDataSource(request);
        if (datasource == null || "null".equals(datasource)) {
            datasource = JNDIDataSource.getConnection();
        }
                String selectFlow="OUTERPROCESS";
        if(request.getParameter("selectFlow")!=null){
           selectFlow= request.getParameter("selectFlow");
        }else if(request.getParameter("selectedValue")!=null){
           selectFlow= request.getParameter("selectedValue");
        }
request.setAttribute("selectedValue",selectFlow);

            HttpSession session = request.getSession();
            personcode = (String) session.getAttribute("personcode");


            CommonDAO commonDAO = new CommonDAO();
            disabilityID = commonDAO.getDisabilityId(datasource, personcode);
            totalPercentage = commonDAO.getTotalPercentage(datasource, personcode);
            request.setAttribute("disabilityID", disabilityID);
            request.setAttribute("totalPercentage", totalPercentage);
            request.setAttribute("rail_eli", "rail_eli");
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

    public ActionForward updateViewOrEdit(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "success";
        DataSource datasource = null;
        

        String personCode = null;
        String status = null;

        //DataSource datasource=getDataSource(request);


        personCode = request.getParameter("personcode");
        status = request.getParameter("changeStatus");
        CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
        request.setAttribute("personCode", personCode);
        try {datasource = getDataSource(request);
        if (datasource == null || "null".equals(datasource)) {
            datasource = JNDIDataSource.getConnection();
        }
            int i = commonservice.updateStatus(datasource, personCode, status);
            request.setAttribute("status", "Status Changed Successfully");
            if (i == 0) {
                request.setAttribute("status", "Invalid Person Code Please Enter Valid Person Code");
                target = "fallure";
                return mapping.findForward(target);
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
     * this method will be used to get status of person details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward SearchPage(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = "success";
        DataSource datasource = null;
        
        String personCode = null;
        String checkPersoncode = null;

        //DataSource datasource=getDataSource(request);


        personCode = request.getParameter("personcode");
        CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
        request.setAttribute("personCode", personCode);
        try {datasource = getDataSource(request);
        if (datasource == null || "null".equals(datasource)) {
            datasource = JNDIDataSource.getConnection();
        }

            checkPersoncode = commonservice.checkPersonCodeForSearch(datasource, personCode);
            if (checkPersoncode == null) {
                request.setAttribute("searchmsg", "Invalid PersonCode");
                target = "fallure";

            } else {
                int totalDisability = commonservice.getTotalPercentage(datasource, personCode);
                request.setAttribute("totalDisability", totalDisability);

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
     * this method will be used to get feedback details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward getFeedbackDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String target = "success";
        List feedbackDetailsList = null;
        
        try {ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }

            CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
            feedbackDetailsList = commonservice.getFeedbackDetails(ds);
            target = "success";
            if (feedbackDetailsList.isEmpty() && feedbackDetailsList == null) {
                request.setAttribute("message", "No feedback details");
            }
            request.setAttribute("feedbackDetailsList", feedbackDetailsList);

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
     * this method will be used to update feedback details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward updateFeedbackDetails(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        String FeedbackIdsStatus = null;
        List feedbackDetailsList = null;
        
        int j = 0;
        try {ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            FeedbackIdsStatus = (String) request.getParameter("FeedbackIdsStatus");

            if (FeedbackIdsStatus != null && !"".equals(FeedbackIdsStatus)) {
                CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();

                String[] selectedIds = request.getParameterValues("rowIds");
                if (selectedIds != null && selectedIds.length > 0) {
                    for (int i = 0; i < selectedIds.length; i++) {
                        String selectedRowId = selectedIds[i];
                        j = commonservice.updateFeedbackDetails(ds, FeedbackIdsStatus, selectedRowId);
                    }
                }
                feedbackDetailsList = commonservice.getFeedbackDetails(ds);
                request.setAttribute("feedbackDetailsList", feedbackDetailsList);
                target = "success";
                if (j == 1) {
                    if ("ActiveFeedback".equals(FeedbackIdsStatus)) {
                        request.setAttribute("msg", "selected Feedback is Active.");
                    } else if ("Inactive".equals(FeedbackIdsStatus)) {
                        request.setAttribute("msg", "selected Feedback is Inactive.");
                    } else if ("Delete".equals(FeedbackIdsStatus)) {
                        request.setAttribute("msg", "Selected Feedback is Deleted.");
                    }
                } else if (j != 1) {
                    request.setAttribute("msg", "Feedback status not Changed");
                }
            } else {
                request.setAttribute("message", "Feedback status not Changed");
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
    public ActionForward showFeedbackDetails(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String rowId = null;
        String target = "success";
        WebsiteCommonForm feedbackform = (WebsiteCommonForm) form;
        WebsiteCommonDTO feedbackDTO = null;
        rowId = (String) request.getParameter("rowId");
        request.setAttribute("rowId", rowId);
       
        //DataSource datasource=getDataSource(request);

        try { ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
            feedbackDTO = new WebsiteCommonDTO();
            feedbackDTO = commonservice.selectFeedbackDetails(ds, rowId);
            if (null != feedbackDTO) {
                BeanUtils.copyProperties(feedbackform, feedbackDTO);
                target = "success";
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

    public ActionForward getDisabilityPercentages(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        request.setAttribute("selectedValue", request.getParameter("selectFlow"));
        String target = "success";
        
        try {
ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            HttpSession session = request.getSession(true);

            // super.execute(mapping, form, request, response);
            String personcode = null;
            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }




            int disabilityid = ((Integer) session.getAttribute("disabilityid")).intValue();

            CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
            TerritoryDTO territoryDTO = new TerritoryDTO();
            territoryDTO = commonservice.getDisabilityPercentages(ds, personcode, disabilityid);
            LinkedList allPercentages = new LinkedList();
            if (disabilityid == 1) {
                LinkedList arry = new LinkedList();
                arry.add(new Double(territoryDTO.getUpperExtremity_Total()));
                arry.add(new Double(territoryDTO.getLowerExtremity_Total()));
                arry.add(new Double(territoryDTO.getAmputation_Total()));
                arry.add(new Double(territoryDTO.getTransverse_Total()));
                arry.add(new Double(territoryDTO.getTrunk_Total()));
                arry.add(new Double(territoryDTO.getPhysical_Impairments_Total()));
                arry.add(new Double(territoryDTO.getPulmonary_Condition()));
                arry.add(new Double(territoryDTO.getDwarfism_Total()));

                LinkedList link = new LinkedList();
                link.addAll(arry);
                calculateResult c = new calculateResult();
                double ressub = Math.round(c.needCalculation(link));
                if (ressub > 100) {
                    ressub = 100;
                }
                allPercentages.add(new Double(ressub));
                request.setAttribute("totalphysical", new Double(ressub));
                territoryDTO.setTotalforphysical(String.valueOf(ressub));
//            request.setAttribute("upperextrimity", new Double(territoryDTO.getUpperExtremity_Total()));
//            request.setAttribute("lowerextremity", new Double(territoryDTO.getLowerExtremity_Total()));
//            request.setAttribute("amputation", new Double(territoryDTO.getAmputation_Total()));
//            request.setAttribute("transverse", new Double(territoryDTO.getTransverse_Total()));
//            request.setAttribute("trunk", new Double(territoryDTO.getTrunk_Total()));
//            request.setAttribute("Evaluation", new Double(territoryDTO.getPhysical_Impairments_Total()));
//            request.setAttribute("cardiopulmonary", new Double(territoryDTO.getPulmonary_Condition()));
//            request.setAttribute("dwarfism", new Double(territoryDTO.getDwarfism_Total()));
            } else if (disabilityid == 2) {
                allPercentages.add(new Double(territoryDTO.getVisual_Impairment()));
//             request.setAttribute("visualimpairment", new Double(territoryDTO.getVisual_Impairment()));
            } else if (disabilityid == 3) {
                allPercentages.add(new Double(territoryDTO.getHearing_Percentage()));
//             request.setAttribute("hearingimpairment", new Double(territoryDTO.getHearing_Percentage()));
            } else if (disabilityid == 4) {
                allPercentages.add(new Double(territoryDTO.getMental_Retardation_Total()));
//            request.setAttribute("mentalretardation", new Double(territoryDTO.getMental_Retardation_Total()));
            } else if (disabilityid == 5) {
                allPercentages.add(new Double(territoryDTO.getMental_Disability_Total()));
//            request.setAttribute("mentalillness", new Double(territoryDTO.getMental_Disability_Total()));
            } else if (disabilityid == 6) {
                LinkedList arry = new LinkedList();
                arry.add(new Double(territoryDTO.getUpperExtremity_Total()));
                arry.add(new Double(territoryDTO.getLowerExtremity_Total()));
                arry.add(new Double(territoryDTO.getAmputation_Total()));
                arry.add(new Double(territoryDTO.getTransverse_Total()));
                arry.add(new Double(territoryDTO.getTrunk_Total()));
                arry.add(new Double(territoryDTO.getPhysical_Impairments_Total()));
                arry.add(new Double(territoryDTO.getPulmonary_Condition()));
                arry.add(new Double(territoryDTO.getDwarfism_Total()));

                LinkedList link = new LinkedList();
                link.addAll(arry);
                calculateResult c = new calculateResult();
                double ressub = Math.round(c.needCalculation(link));
                if (ressub > 100) {
                    ressub = 100;
                }
                allPercentages.add(new Double(ressub));
                request.setAttribute("totalphysical", new Double(ressub));
                territoryDTO.setTotalforphysical(String.valueOf(ressub));
                allPercentages.add(new Double(territoryDTO.getVisual_Impairment()));
                allPercentages.add(new Double(territoryDTO.getHearing_Percentage()));
                allPercentages.add(new Double(territoryDTO.getMental_Retardation_Total()));
                allPercentages.add(new Double(territoryDTO.getMental_Disability_Total()));
//            request.setAttribute("mentalillness", new Double(territoryDTO.getMental_Disability_Total()));
            }


            if (allPercentages.size() > 0) {
                Double maxValue = (Double) Collections.max(allPercentages);
                request.setAttribute("maxValue", maxValue);
                LinkedList link = new LinkedList();
                link.addAll(allPercentages);
                calculateResult c = new calculateResult();
                double res = Math.round(c.needCalculation(link));
                if (res > 100) {
                    res = 100;
                }
                territoryDTO.setTotaldisability(String.valueOf(res));
                request.setAttribute("territoryDTO", territoryDTO);
            }
            MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
            MentalRetardationTestsDTO disableMentalItems = mentalRetardationService.getMentalRetardationIQValues(ds, personcode);
            request.setAttribute("disableMentalItems", disableMentalItems);

            request.setAttribute("disabilityid", disabilityid);
            target = "success";
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

    public ActionForward getDisabilityPercentagesAU(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        
        try {ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
            HttpSession session = request.getSession(true);

            // super.execute(mapping, form, request, response);
            String personcode = (String) session.getAttribute("sadaremCodeAu");
            int disabilityid = ((Integer) session.getAttribute("disabilityid")).intValue();
            CommonService commonservice = CommonServiceFactory.getCommonServiceImpl();
            TerritoryDTO territoryDTO = new TerritoryDTO();
            territoryDTO = commonservice.getDisabilityPercentagesAU(ds, personcode, disabilityid);
            LinkedList allPercentages = new LinkedList();
            if (disabilityid == 1) {
                LinkedList arry = new LinkedList();
                arry.add(new Double(territoryDTO.getUpperExtremity_Total()));
                arry.add(new Double(territoryDTO.getLowerExtremity_Total()));
                arry.add(new Double(territoryDTO.getAmputation_Total()));
                arry.add(new Double(territoryDTO.getTransverse_Total()));
                arry.add(new Double(territoryDTO.getTrunk_Total()));
                arry.add(new Double(territoryDTO.getPhysical_Impairments_Total()));
                arry.add(new Double(territoryDTO.getPulmonary_Condition()));
                arry.add(new Double(territoryDTO.getDwarfism_Total()));

                LinkedList link = new LinkedList();
                link.addAll(arry);
                calculateResult c = new calculateResult();
                double ressub = Math.round(c.needCalculation(link));
                if (ressub > 100) {
                    ressub = 100;
                }
                allPercentages.add(new Double(ressub));
                request.setAttribute("totalphysical", new Double(ressub));
                territoryDTO.setTotalforphysical(String.valueOf(ressub));
//            request.setAttribute("upperextrimity", new Double(territoryDTO.getUpperExtremity_Total()));
//            request.setAttribute("lowerextremity", new Double(territoryDTO.getLowerExtremity_Total()));
//            request.setAttribute("amputation", new Double(territoryDTO.getAmputation_Total()));
//            request.setAttribute("transverse", new Double(territoryDTO.getTransverse_Total()));
//            request.setAttribute("trunk", new Double(territoryDTO.getTrunk_Total()));
//            request.setAttribute("Evaluation", new Double(territoryDTO.getPhysical_Impairments_Total()));
//            request.setAttribute("cardiopulmonary", new Double(territoryDTO.getPulmonary_Condition()));
//            request.setAttribute("dwarfism", new Double(territoryDTO.getDwarfism_Total()));
            } else if (disabilityid == 2) {
                allPercentages.add(new Double(territoryDTO.getVisual_Impairment()));
//             request.setAttribute("visualimpairment", new Double(territoryDTO.getVisual_Impairment()));
            } else if (disabilityid == 3) {
                allPercentages.add(new Double(territoryDTO.getHearing_Percentage()));
//             request.setAttribute("hearingimpairment", new Double(territoryDTO.getHearing_Percentage()));
            } else if (disabilityid == 4) {
                allPercentages.add(new Double(territoryDTO.getMental_Retardation_Total()));
//            request.setAttribute("mentalretardation", new Double(territoryDTO.getMental_Retardation_Total()));
            } else if (disabilityid == 5) {
                allPercentages.add(new Double(territoryDTO.getMental_Disability_Total()));
//            request.setAttribute("mentalillness", new Double(territoryDTO.getMental_Disability_Total()));
            } else if (disabilityid == 6) {
                LinkedList arry = new LinkedList();
                arry.add(new Double(territoryDTO.getUpperExtremity_Total()));
                arry.add(new Double(territoryDTO.getLowerExtremity_Total()));
                arry.add(new Double(territoryDTO.getAmputation_Total()));
                arry.add(new Double(territoryDTO.getTransverse_Total()));
                arry.add(new Double(territoryDTO.getTrunk_Total()));
                arry.add(new Double(territoryDTO.getPhysical_Impairments_Total()));
                arry.add(new Double(territoryDTO.getPulmonary_Condition()));
                arry.add(new Double(territoryDTO.getDwarfism_Total()));

                LinkedList link = new LinkedList();
                link.addAll(arry);
                calculateResult c = new calculateResult();
                double ressub = Math.round(c.needCalculation(link));
                if (ressub > 100) {
                    ressub = 100;
                }
                allPercentages.add(new Double(ressub));
                request.setAttribute("totalphysical", new Double(ressub));
                territoryDTO.setTotalforphysical(String.valueOf(ressub));
                allPercentages.add(new Double(territoryDTO.getVisual_Impairment()));
                allPercentages.add(new Double(territoryDTO.getHearing_Percentage()));
                allPercentages.add(new Double(territoryDTO.getMental_Retardation_Total()));
                allPercentages.add(new Double(territoryDTO.getMental_Disability_Total()));
//            request.setAttribute("mentalillness", new Double(territoryDTO.getMental_Disability_Total()));
            }


            if (allPercentages.size() > 0) {
                Double maxValue = (Double) Collections.max(allPercentages);
                request.setAttribute("maxValue", maxValue);
                LinkedList link = new LinkedList();
                link.addAll(allPercentages);
                calculateResult c = new calculateResult();
                double res = Math.round(c.needCalculation(link));
                if (res > 100) {
                    res = 100;
                }
                territoryDTO.setTotaldisability(String.valueOf(res));
                request.setAttribute("territoryDTO", territoryDTO);
            }
            request.setAttribute("disabilityid", disabilityid);
            target = "success";
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












