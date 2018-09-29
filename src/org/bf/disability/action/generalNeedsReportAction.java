/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.FunctionalNeedReportDTO;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;

/**
 *
 * @author 748094
 */
public class generalNeedsReportAction extends org.apache.struts.actions.DispatchAction {

    String regionHeading = null;
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    DataSource ds = null;

    @Override
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        functionalFeedFORM.setDistrict_id("0");
        functionalFeedFORM.setMandal_id("0");
        functionalFeedFORM.setVillage_id("0");
        functionalFeedFORM.setTypeofdisability(1);
        functionalFeedFORM.setReportcategory(1);
        functionalFeedFORM.setFromdate("01/01/2010");
        Date date = new Date();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String todate = df.format(date);
        functionalFeedFORM.setTodate(todate);
        functionalFeedFORM.setReport(true);
        getGeneralNeedReport(mapping, functionalFeedFORM, request, response);
        setRequestParams(functionalFeedFORM, request);
       
        request.setAttribute("district_id", "0");
        request.setAttribute("mandal_id", "0");
        request.setAttribute("village_id", "0");
        regionHeading = "District";
        request.setAttribute("level", "State level Report");
        request.setAttribute("regionHeading", regionHeading);
        getAreaDescription(functionalFeedFORM, request);
        
        return mapping.findForward(SUCCESS);
    }

    public ActionForward mandalLevelReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        functionalFeedFORM.setDistrict_id(request.getParameter("district_id"));
        functionalFeedFORM.setMandal_id("0");
        functionalFeedFORM.setVillage_id("0");
        functionalFeedFORM.setTypeofdisability(Integer.parseInt(request.getParameter("typeofdisability")));

        functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        functionalFeedFORM.setTodate(request.getParameter("todate"));
        functionalFeedFORM.setReport(true);
        getGeneralNeedReport(mapping, functionalFeedFORM, request, response);
        setRequestParams(functionalFeedFORM, request);
        request.setAttribute("level", "District level Report");
        regionHeading = "Mandal";
        getAreaDescription(functionalFeedFORM, request);

        request.setAttribute("back", "<a href='./generalNeedsReport.do?mode=getOtherReports&typeofdisability=" + functionalFeedFORM.getTypeofdisability() + "&fromdate=" + functionalFeedFORM.getFromdate() + "&todate=" + functionalFeedFORM.getTodate() + "'>Back</a>");
        request.setAttribute("regionHeading", regionHeading);
        return mapping.findForward("success");
    }

    public ActionForward villageLevelReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        functionalFeedFORM.setDistrict_id(request.getParameter("district_id"));
        functionalFeedFORM.setMandal_id(request.getParameter("mandal_id"));
        functionalFeedFORM.setVillage_id("0");
        functionalFeedFORM.setTypeofdisability(Integer.parseInt(request.getParameter("typeofdisability")));

        functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        functionalFeedFORM.setTodate(request.getParameter("todate"));
        functionalFeedFORM.setReport(true);
        getGeneralNeedReport(mapping, functionalFeedFORM, request, response);

        regionHeading = "Village";
        request.setAttribute("back", "<a href='./generalNeedsReport.do?mode=mandalLevelReport&district_id=" + request.getParameter("district_id") + "&typeofdisability=" + functionalFeedFORM.getTypeofdisability() + "&fromdate=" + functionalFeedFORM.getFromdate() + "&todate=" + functionalFeedFORM.getTodate() + "'>Back</a>");
        request.setAttribute("regionHeading", regionHeading);
        request.setAttribute("level", "Mandal level Report");
        getAreaDescription(functionalFeedFORM, request);
        setRequestParams(functionalFeedFORM, request);
        return mapping.findForward("success");
    }

    public ActionForward habitationLevelReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        functionalFeedFORM.setDistrict_id(request.getParameter("district_id"));
        functionalFeedFORM.setMandal_id(request.getParameter("mandal_id"));
        functionalFeedFORM.setVillage_id(request.getParameter("village_id"));
        if(request.getParameter("typeofdisability")!=null){
        functionalFeedFORM.setTypeofdisability(Integer.parseInt(request.getParameter("typeofdisability")));
        }
        

        functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        functionalFeedFORM.setTodate(request.getParameter("todate"));
        functionalFeedFORM.setReport(true);
        getGeneralNeedReport(mapping, functionalFeedFORM, request, response);

        regionHeading = "Habitation";
        request.setAttribute("level", "Village level Report");
        getAreaDescription(functionalFeedFORM, request);
        setRequestParams(functionalFeedFORM, request);
        request.setAttribute("back", "<a href='./generalNeedsReport.do?mode=villageLevelReport&district_id=" + request.getParameter("district_id") + "&mandal_id=" + request.getParameter("mandal_id") + "&typeofdisability=" + functionalFeedFORM.getTypeofdisability() + "&fromdate=" + functionalFeedFORM.getFromdate() + "&todate=" + functionalFeedFORM.getTodate() + "'>Back</a>");
        request.setAttribute("regionHeading", regionHeading);
        return mapping.findForward("success");
    }

    public ActionForward getOtherReports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;

        functionalFeedFORM.setDistrict_id("0");
        functionalFeedFORM.setMandal_id("0");
        functionalFeedFORM.setVillage_id("0");
        if (request.getParameter("typeofdisability") != null && !request.getParameter("typeofdisability").equals("")) {
            functionalFeedFORM.setTypeofdisability(Integer.parseInt(request.getParameter("typeofdisability")));
        }

        if (request.getParameter("fromdate") != null && !request.getParameter("fromdate").equals("")) {
            functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        }
        if (request.getParameter("todate") != null && !request.getParameter("todate").equals("")) {
            functionalFeedFORM.setTodate(request.getParameter("todate"));
        }
        functionalFeedFORM.setReport(true);
        getGeneralNeedReport(mapping, functionalFeedFORM, request, response);
        functionalFeedFORM.setReportSubcategory(functionalFeedFORM.getReportSubcategory());
        regionHeading = "District";
        request.setAttribute("level", "State level Report");
        request.setAttribute("regionHeading", regionHeading);
        getAreaDescription(functionalFeedFORM, request);
        setRequestParams(functionalFeedFORM, request);
        return mapping.findForward("success");
    }

    private void getAreaDescription(ActionForm form, HttpServletRequest request) {

        FunctionalNeedReportForm ff = (FunctionalNeedReportForm) form;
        String districtid = ff.getDistrict_id();
        String mandalid = ff.getMandal_id();
        String villageid = ff.getVillage_id();
        String areaname = null;

        FunctionalNeedReportService service =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (districtid != null && districtid.equals("0")) {
                areaname = "District : All&nbsp;&nbsp;&nbsp;&nbsp;Mandal : All&nbsp;&nbsp;&nbsp;&nbsp;Village : All";
            } else if (districtid != null && !districtid.equals("0") && mandalid != null && mandalid.equals("0")) {
                areaname = service.getAreaName(ds, districtid, null, null);
            } else if (districtid != null && !districtid.equals("0") && mandalid != null && !mandalid.equals("0") && villageid != null && villageid.equals("0")) {
                areaname = service.getAreaName(ds, districtid, mandalid, null);
            } else if (districtid != null && !districtid.equals("0") && mandalid != null && !mandalid.equals("0") && villageid != null && !villageid.equals("0")) {
                areaname = service.getAreaName(ds, districtid, mandalid, villageid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("areadescription", areaname);
        }
    }

    private void setRequestParams(FunctionalNeedReportForm ff, HttpServletRequest request) {
        request.setAttribute("district_id", ff.getDistrict_id());
        request.setAttribute("mandal_id", ff.getMandal_id());
        request.setAttribute("village_id", ff.getVillage_id());
        request.setAttribute("fromdate", ff.getFromdate());
        request.setAttribute("todate", ff.getTodate());
        request.setAttribute("typeofdisability", ff.getTypeofdisability());


    }

    public ActionForward printGeneralNeeds(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        String districtid = null;
        String mandalid = null;
        String villageid = null;
        String level = null;
        if (request.getParameter("district_id") != null) {
            districtid = request.getParameter("district_id");
            functionalFeedFORM.setDistrict_id(request.getParameter("district_id"));
        }
        if (request.getParameter("mandal_id") != null) {
            mandalid = request.getParameter("mandal_id");
            functionalFeedFORM.setMandal_id(request.getParameter("mandal_id"));
        }
        if (request.getParameter("village_id") != null) {
            villageid = request.getParameter("village_id");
            functionalFeedFORM.setVillage_id(request.getParameter("village_id"));
        }
        if (request.getParameter("typeofdisability") != null && !request.getParameter("typeofdisability").equals("")) {
            functionalFeedFORM.setTypeofdisability(Integer.parseInt(request.getParameter("typeofdisability")));
        }
     
        if (request.getParameter("fromdate") != null && !request.getParameter("fromdate").equals("")) {
            functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        }
        if (request.getParameter("todate") != null && !request.getParameter("todate").equals("")) {
            functionalFeedFORM.setTodate(request.getParameter("todate"));
        }
        getGeneralNeedReport(mapping, functionalFeedFORM, request, response);
        if (districtid != null && districtid.equals("0")) {
            regionHeading = "District";
            level = "State level Report";
        } else if (districtid != null && !districtid.equals("0") && mandalid != null && mandalid.equals("0")) {
            regionHeading = "Mandal";
            level = "District level Report";
        } else if (districtid != null && !districtid.equals("0") && mandalid != null && !mandalid.equals("0") && villageid != null && villageid.equals("0")) {
            regionHeading = "Village";
            level = "Mandal level Report";
        } else if (districtid != null && !districtid.equals("0") && mandalid != null && !mandalid.equals("0") && villageid != null && !villageid.equals("0")) {
            regionHeading = "Habitation";
            level = "Village level Report";
        }

        request.setAttribute("level", level);
        request.setAttribute("regionHeading", regionHeading);
        getAreaDescription(functionalFeedFORM, request);
        return mapping.findForward("print");
    }

    public ActionForward excelGeneralNeeds(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        String districtid = null;
        String mandalid = null;
        String villageid = null;
        String level = null;
        if (request.getParameter("district_id") != null) {
            districtid = request.getParameter("district_id");
            functionalFeedFORM.setDistrict_id(request.getParameter("district_id"));
        }
        if (request.getParameter("mandal_id") != null) {
            mandalid = request.getParameter("mandal_id");
            functionalFeedFORM.setMandal_id(request.getParameter("mandal_id"));
        }
        if (request.getParameter("village_id") != null) {
            villageid = request.getParameter("village_id");
            functionalFeedFORM.setVillage_id(request.getParameter("village_id"));
        }
        if (request.getParameter("typeofdisability") != null && !request.getParameter("typeofdisability").equals("")) {
            functionalFeedFORM.setTypeofdisability(Integer.parseInt(request.getParameter("typeofdisability")));
        }
        if (request.getParameter("fromdate") != null && !request.getParameter("fromdate").equals("")) {
            functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        }
        if (request.getParameter("todate") != null && !request.getParameter("todate").equals("")) {
            functionalFeedFORM.setTodate(request.getParameter("todate"));
        }
        getGeneralNeedReport(mapping, functionalFeedFORM, request, response);
        if (districtid != null && districtid.equals("0")) {
            regionHeading = "District";
            level = "State level Report";
        } else if (districtid != null && !districtid.equals("0") && mandalid != null && mandalid.equals("0")) {
            regionHeading = "Mandal";
            level = "District level Report";
        } else if (districtid != null && !districtid.equals("0") && mandalid != null && !mandalid.equals("0") && villageid != null && villageid.equals("0")) {
            regionHeading = "Village";
            level = "Mandal level Report";
        } else if (districtid != null && !districtid.equals("0") && mandalid != null && !mandalid.equals("0") && villageid != null && !villageid.equals("0")) {
            regionHeading = "Habitation";
            level = "Village level Report";
        }

        request.setAttribute("level", level);
        request.setAttribute("regionHeading", regionHeading);
        getAreaDescription(functionalFeedFORM, request);
        return mapping.findForward("excel");
    }
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward getGeneralNeedReport(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        int typeofdisability = 0;
        String target = null;



        ActionMessages actionMessages = null;
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDTO functionalNeedDTO = new FunctionalNeedReportDTO();
        typeofdisability = functionalFeedFORM.getTypeofdisability();

        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            BeanUtils.copyProperties(functionalNeedDTO, functionalFeedFORM);
            if (typeofdisability != 0) {

                switch (typeofdisability) {
                    case 1:
                    case 2:
                    case 3:
                        ArrayList<FunctionalNeedReportDTO> commonGeneralNeedsList = null;
                        commonGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
                        commonGeneralNeedsList = functionalNeedService.getCommonGeneralNeeds(ds,
                                functionalNeedDTO);
                        if (commonGeneralNeedsList.isEmpty()) {
                            request.setAttribute("msg", "No Records Found");
                            target = "ReportException";
                        }
                        request.setAttribute("commonGeneralNeedsList", commonGeneralNeedsList);
                        target = "GeneralNeedsCommon";
                        break;

                    case 4:
                        ArrayList<FunctionalNeedReportDTO> mrGeneralNeedsList = null;
                        mrGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
                        mrGeneralNeedsList = functionalNeedService.getMRGeneralNeeds(ds,
                                functionalNeedDTO);
                        if (mrGeneralNeedsList.isEmpty()) {
                            request.setAttribute("msg", "No Records Found");
                            target = "ReportException";
                        }
                        request.setAttribute("mrGeneralNeedsList", mrGeneralNeedsList);
                        target = "GeneralNeedsMR";
                        break;

                    case 5:
                        ArrayList<FunctionalNeedReportDTO> miGeneralNeedsList = null;
                        miGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
                        miGeneralNeedsList = functionalNeedService.getMIGeneralNeeds(ds,
                                functionalNeedDTO);
                        if (miGeneralNeedsList.isEmpty()) {
                            request.setAttribute("msg", "No Records Found");
                            target = "ReportException";
                        }
                        request.setAttribute("miGeneralNeedsList", miGeneralNeedsList);
                        target = "GeneralNeedsMI";
                        break;

                    case 6:
                        ArrayList<FunctionalNeedReportDTO> multipleGeneralNeedsList = null;
                        multipleGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
                        multipleGeneralNeedsList = functionalNeedService.getMultipleGeneralNeeds(ds,
                                functionalNeedDTO);
                        if (multipleGeneralNeedsList.isEmpty()) {
                            request.setAttribute("msg", "No Records Found");
                            target = "ReportException";
                        }
                        request.setAttribute("multipleGeneralNeedsList", multipleGeneralNeedsList);
                        target = "GeneralNeedsMultiple";
                        break;

                }
            } else {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            }
        } catch (Exception sADAREMException) {
            sADAREMException.printStackTrace();
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } finally {
            return mapping.findForward(target);
        }

    }
}
