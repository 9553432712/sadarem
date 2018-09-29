/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 509865
 */
package org.bf.disability.action;

import java.util.ArrayList;

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
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.dto.FunctionalNeedReportDTO;
import org.bf.disability.common.CommonDAO;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.OutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @description this dispatch action class is used in common for generating the functional report
 * @author sivakumar
 * @version 1.0
 */
public class FunctionalNeedsReportAction extends DispatchAction {

    String regionHeading = null;
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

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String todate = df.format(date);
        functionalFeedFORM.setTodate(todate);
        functionalFeedFORM.setReport(true);
        getFunctionalNeedReport(mapping, functionalFeedFORM, request, response);
        setRequestParams(functionalFeedFORM, request);
        

        regionHeading = "District";
        request.setAttribute("level", "State level Report");
        request.setAttribute("regionHeading", regionHeading);
        getAreaDescription(functionalFeedFORM, request);

        return mapping.findForward("success");
    }

    public ActionForward mandalLevelReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        functionalFeedFORM.setDistrict_id(request.getParameter("district_id"));
        functionalFeedFORM.setMandal_id("0");
        functionalFeedFORM.setVillage_id("0");
        functionalFeedFORM.setTypeofdisability(Integer.parseInt(request.getParameter("typeofdisability")));
        functionalFeedFORM.setReportcategory(Integer.parseInt(request.getParameter("reportcategory")));
        if (request.getParameter("reportSubcategory") != null && !request.getParameter("reportSubcategory").equals("")) {
            functionalFeedFORM.setReportSubcategory(Integer.parseInt(request.getParameter("reportSubcategory")));
        }
        functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        functionalFeedFORM.setTodate(request.getParameter("todate"));
        functionalFeedFORM.setReport(true);
        getFunctionalNeedReport(mapping, functionalFeedFORM, request, response);
        setRequestParams(functionalFeedFORM, request);
        request.setAttribute("level", "District level Report");
        regionHeading = "Mandal";
        getAreaDescription(functionalFeedFORM, request);

        request.setAttribute("back", "<a href='./analysisReport.do?mode=getOtherReports&typeofdisability=" + functionalFeedFORM.getTypeofdisability() + "&reportcategory=" + functionalFeedFORM.getReportcategory() + "&fromdate=" + functionalFeedFORM.getFromdate() + "&todate=" + functionalFeedFORM.getTodate() + "&reportSubcategory=" + functionalFeedFORM.getReportSubcategory() + "'>Back</a>");
        request.setAttribute("regionHeading", regionHeading);
        return mapping.findForward("success");
    }

    public ActionForward villageLevelReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        functionalFeedFORM.setDistrict_id(request.getParameter("district_id"));
        functionalFeedFORM.setMandal_id(request.getParameter("mandal_id"));
        functionalFeedFORM.setVillage_id("0");
        functionalFeedFORM.setTypeofdisability(Integer.parseInt(request.getParameter("typeofdisability")));
        functionalFeedFORM.setReportcategory(Integer.parseInt(request.getParameter("reportcategory")));
        if (request.getParameter("reportSubcategory") != null && !request.getParameter("reportSubcategory").equals("")) {
            functionalFeedFORM.setReportSubcategory(Integer.parseInt(request.getParameter("reportSubcategory")));
        }
        functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        functionalFeedFORM.setTodate(request.getParameter("todate"));
        functionalFeedFORM.setReport(true);
        getFunctionalNeedReport(mapping, functionalFeedFORM, request, response);

        regionHeading = "Village";
        request.setAttribute("back", "<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + request.getParameter("district_id") + "&typeofdisability=" + functionalFeedFORM.getTypeofdisability() + "&reportcategory=" + functionalFeedFORM.getReportcategory() + "&fromdate=" + functionalFeedFORM.getFromdate() + "&todate=" + functionalFeedFORM.getTodate() + "&reportSubcategory=" + functionalFeedFORM.getReportcategory() + "'>Back</a>");
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
        functionalFeedFORM.setTypeofdisability(Integer.parseInt(request.getParameter("typeofdisability")));
        functionalFeedFORM.setReportcategory(Integer.parseInt(request.getParameter("reportcategory")));
        if (request.getParameter("reportSubcategory") != null && !request.getParameter("reportSubcategory").equals("")) {
            functionalFeedFORM.setReportSubcategory(Integer.parseInt(request.getParameter("reportSubcategory")));
        }
        functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        functionalFeedFORM.setTodate(request.getParameter("todate"));
        functionalFeedFORM.setReport(true);
        getFunctionalNeedReport(mapping, functionalFeedFORM, request, response);

        regionHeading = "Habitation";
        request.setAttribute("level", "Village level Report");
        getAreaDescription(functionalFeedFORM, request);
        setRequestParams(functionalFeedFORM, request);
        request.setAttribute("back", "<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + request.getParameter("district_id") + "&mandal_id=" + request.getParameter("mandal_id") + "&typeofdisability=" + functionalFeedFORM.getTypeofdisability() + "&reportcategory=" + functionalFeedFORM.getReportcategory() + "&fromdate=" + functionalFeedFORM.getFromdate() + "&todate=" + functionalFeedFORM.getTodate() + "&reportSubcategory=" + functionalFeedFORM.getReportSubcategory() + "'>Back</a>");
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
        if (request.getParameter("reportcategory") != null && !request.getParameter("reportcategory").equals("")) {
            functionalFeedFORM.setReportcategory(Integer.parseInt(request.getParameter("reportcategory")));
        }
        if (request.getParameter("reportSubcategory") != null && !request.getParameter("reportSubcategory").equals("")) {
            functionalFeedFORM.setReportSubcategory(Integer.parseInt(request.getParameter("reportSubcategory")));
        }
        if (request.getParameter("fromdate") != null && !request.getParameter("fromdate").equals("")) {
            functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        }
        if (request.getParameter("todate") != null && !request.getParameter("todate").equals("")) {
            functionalFeedFORM.setTodate(request.getParameter("todate"));
        }
        functionalFeedFORM.setReport(true);
        getFunctionalNeedReport(mapping, functionalFeedFORM, request, response);
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

    public ActionForward printWebsiteAnalysis(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        if (request.getParameter("reportcategory") != null && !request.getParameter("reportcategory").equals("")) {
            functionalFeedFORM.setReportcategory(Integer.parseInt(request.getParameter("reportcategory")));
        }
        if (request.getParameter("reportSubcategory") != null && !request.getParameter("reportSubcategory").equals("")) {
            functionalFeedFORM.setReportSubcategory(Integer.parseInt(request.getParameter("reportSubcategory")));
        }
        if (request.getParameter("fromdate") != null && !request.getParameter("fromdate").equals("")) {
            functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        }
        if (request.getParameter("todate") != null && !request.getParameter("todate").equals("")) {
            functionalFeedFORM.setTodate(request.getParameter("todate"));
        }
        getFunctionalNeedReport(mapping, functionalFeedFORM, request, response);
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

    public ActionForward excelWebsiteAnalysis(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        if (request.getParameter("reportcategory") != null && !request.getParameter("reportcategory").equals("")) {
            functionalFeedFORM.setReportcategory(Integer.parseInt(request.getParameter("reportcategory")));
        }
        if (request.getParameter("reportSubcategory") != null && !request.getParameter("reportSubcategory").equals("")) {
            functionalFeedFORM.setReportSubcategory(Integer.parseInt(request.getParameter("reportSubcategory")));
        }
        if (request.getParameter("fromdate") != null && !request.getParameter("fromdate").equals("")) {
            functionalFeedFORM.setFromdate(request.getParameter("fromdate"));
        }
        if (request.getParameter("todate") != null && !request.getParameter("todate").equals("")) {
            functionalFeedFORM.setTodate(request.getParameter("todate"));
        }
        getFunctionalNeedReport(mapping, functionalFeedFORM, request, response);
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

    private void setRequestParams(FunctionalNeedReportForm ff, HttpServletRequest request) {
        request.setAttribute("district_id", ff.getDistrict_id());
        request.setAttribute("mandal_id", ff.getMandal_id());
        request.setAttribute("village_id", ff.getVillage_id());
        request.setAttribute("fromdate", ff.getFromdate());
        request.setAttribute("todate", ff.getTodate());
        request.setAttribute("typeofdisability", ff.getTypeofdisability());
        request.setAttribute("reportcategory", ff.getReportcategory());
        request.setAttribute("reportSubcategory", ff.getReportSubcategory());

    }

    /**
     *
     * @description this method is used to generate the functional Spscific needs report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getFunctionalNeedReport(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        int typeofdisability = 0;
        int reportcategory = 0;
        int reportSubcategory = 0;
        String target = null;


        ActionMessages actionMessages = null;
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDTO functionalNeedDTO = new FunctionalNeedReportDTO();
        typeofdisability = functionalFeedFORM.getTypeofdisability();
        reportcategory = functionalFeedFORM.getReportcategory();
        reportSubcategory = functionalFeedFORM.getReportSubcategory();

        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        try {
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }

            BeanUtils.copyProperties(functionalNeedDTO, functionalFeedFORM);
             target = "GeneralNeedsReportTile";
            if (typeofdisability != 0) {
                switch (typeofdisability) {
                    case 1: {
                        ArrayList<FunctionalNeedReportDTO> medicalInterventionList = null;
                        ArrayList<FunctionalNeedReportDTO> prothosisList = null;
                        ArrayList<FunctionalNeedReportDTO> orthosisList = null;
                        ArrayList<FunctionalNeedReportDTO> walkingAidsList = null;
                        ArrayList<FunctionalNeedReportDTO> mobilityList = null;
                        ArrayList<FunctionalNeedReportDTO> adlEquipmentsList = null;
                        ArrayList<FunctionalNeedReportDTO> otherNeedsList = null;
                        ArrayList<FunctionalNeedReportDTO> allFunctionalNeedList = null;
                        switch (reportcategory) {
                            case 1: {
                                medicalInterventionList = new ArrayList<FunctionalNeedReportDTO>();
                                medicalInterventionList = functionalNeedService.getMedicalIntervention(ds,
                                        functionalNeedDTO);
                                if (medicalInterventionList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("medicalInterventionList", medicalInterventionList);
                                target = "MedicalInterventionOH";
                                break;
                            }
                            case 2: {
                                switch (reportSubcategory) {
                                    case 1: {
                                        prothosisList = new ArrayList<FunctionalNeedReportDTO>();
                                        prothosisList = functionalNeedService.getAssistiveDevicesProthosis(ds,
                                                functionalNeedDTO);
                                        if (prothosisList.isEmpty()) {
                                            request.setAttribute("msg", "No Records Found");
                                            target = "ReportException";
                                        }
                                        request.setAttribute("prothosisList", prothosisList);
                                        target = "AssistiveDevicesProthosis";
                                        break;
                                    }
                                    case 2: {
                                        orthosisList = new ArrayList<FunctionalNeedReportDTO>();
                                        orthosisList = functionalNeedService.getAssistiveDevicesOrthosis(ds,
                                                functionalNeedDTO);
                                        if (orthosisList.isEmpty()) {
                                            request.setAttribute("msg", "No Records Found");
                                            target = "ReportException";
                                        }
                                        request.setAttribute("orthosisList", orthosisList);
                                        target = "AssistiveDevicesOrthosis";
                                        break;
                                    }
                                    case 3: {
                                        mobilityList = new ArrayList<FunctionalNeedReportDTO>();
                                        mobilityList = functionalNeedService.getAssistiveDevicesMobility(ds,
                                                functionalNeedDTO);
                                        if (mobilityList.isEmpty()) {
                                            request.setAttribute("msg", "No Records Found");
                                            target = "ReportException";
                                        }
                                        request.setAttribute("mobilityList", mobilityList);
                                        target = "AssistiveDevicesMobility";
                                        break;
                                    }
                                    case 4: {
                                        walkingAidsList = new ArrayList<FunctionalNeedReportDTO>();
                                        walkingAidsList = functionalNeedService.getAssistiveDevicesWalkingAids(ds,
                                                functionalNeedDTO);
                                        if (walkingAidsList.isEmpty()) {
                                            request.setAttribute("msg", "No Records Found");
                                            target = "ReportException";
                                        }
                                        request.setAttribute("walkingAidsList", walkingAidsList);
                                        target = "AssistiveDevicesWalkingAids";
                                        break;
                                    }

                                    case 5: {
                                        adlEquipmentsList = new ArrayList<FunctionalNeedReportDTO>();
                                        adlEquipmentsList = functionalNeedService.getAssistiveDevicesADLEquipments(ds,
                                                functionalNeedDTO);
                                        if (adlEquipmentsList.isEmpty()) {
                                            request.setAttribute("msg", "No Records Found");
                                            target = "ReportException";
                                        }
                                        request.setAttribute("adlEquipmentsList", adlEquipmentsList);
                                        target = "AssistiveDevicesADLEquipments";
                                        break;
                                    }
                                }
                                break;
                            }
                            case 3: {
                                otherNeedsList = new ArrayList<FunctionalNeedReportDTO>();
                                otherNeedsList = functionalNeedService.getOtherNeeds(ds, functionalNeedDTO);
                                if (otherNeedsList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("otherNeedsList", otherNeedsList);
                                target = "OtherNeedsOH";
                                break;
                            }
                        }
                        break;
                    }
                    case 2: {
                        ArrayList<FunctionalNeedReportDTO> interventionVIList = null;
                        ArrayList<FunctionalNeedReportDTO> assistiveAugmentativeDevicesVIList = null;
                        ArrayList<FunctionalNeedReportDTO> otherNeedsVIList = null;
                        switch (reportcategory) {
                            case 1: {
                                interventionVIList = new ArrayList<FunctionalNeedReportDTO>();
                                interventionVIList = functionalNeedService.getMedicalInterventionVI(ds, functionalNeedDTO);
                                if (interventionVIList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("medicalInterventionVIList", interventionVIList);
                                target = "MedicalInterventionVI";
                                break;
                            }
                            case 2: {
                                assistiveAugmentativeDevicesVIList = new ArrayList<FunctionalNeedReportDTO>();
                                assistiveAugmentativeDevicesVIList = functionalNeedService.getAssistiveAugmentativeDevicesVI(
                                        ds, functionalNeedDTO);
                                if (assistiveAugmentativeDevicesVIList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("assistiveDevicesVIList", assistiveAugmentativeDevicesVIList);
                                target = "AssistiveAugmentativeDevicesVI";
                                break;
                            }
                            case 3: {
                                otherNeedsVIList = new ArrayList<FunctionalNeedReportDTO>();
                                otherNeedsVIList = functionalNeedService.getOtherNeedsVI(ds, functionalNeedDTO);
                                if (otherNeedsVIList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("otherNeedsVIList", otherNeedsVIList);
                                target = "OtherNeedsVI";
                                break;
                            }
                        }
                        break;
                    }
                    case 3: {
                        ArrayList<FunctionalNeedReportDTO> medicalInterventionHIList = null;
                        ArrayList<FunctionalNeedReportDTO> assistiveAugmentativeDevicesHIList = null;
                        ArrayList<FunctionalNeedReportDTO> otherNeedsHIList = null;
                        switch (reportcategory) {
                            case 1: {
                                medicalInterventionHIList = new ArrayList<FunctionalNeedReportDTO>();
                                medicalInterventionHIList = functionalNeedService.getMedicalInterventionHI(ds,
                                        functionalNeedDTO);
                                if (medicalInterventionHIList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("medicalInterventionHIList", medicalInterventionHIList);
                                target = "MedicalInterventionHI";
                                break;
                            }
                            case 2: {
                                assistiveAugmentativeDevicesHIList = new ArrayList<FunctionalNeedReportDTO>();
                                assistiveAugmentativeDevicesHIList = functionalNeedService.getAssistiveAugmentativeDevicesHI(
                                        ds, functionalNeedDTO);
                                if (assistiveAugmentativeDevicesHIList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("assistiveDevicesHIList", assistiveAugmentativeDevicesHIList);
                                target = "AssistiveAugmentativeDevicesHI";
                                break;
                            }
                            case 3: {
                                otherNeedsHIList = new ArrayList<FunctionalNeedReportDTO>();
                                otherNeedsHIList = functionalNeedService.getOtherNeedsHI(ds, functionalNeedDTO);
                                if (otherNeedsHIList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("otherNeedsHIList", otherNeedsHIList);
                                target = "OtherNeedsHI";
                                break;
                            }
                        }
                        break;
                    }
                    case 4: {
                        ArrayList<FunctionalNeedReportDTO> medicalInterventionMRList = null;
                        ArrayList<FunctionalNeedReportDTO> assistiveAugmentativeDevicesMRList = null;
                        ArrayList<FunctionalNeedReportDTO> otherNeedsMRList = null;
                        switch (reportcategory) {
                            case 1: {
                                medicalInterventionMRList = new ArrayList<FunctionalNeedReportDTO>();
                                medicalInterventionMRList = functionalNeedService.getMedicalInterventionMR(ds,
                                        functionalNeedDTO);
                                if (medicalInterventionMRList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("medicalInterventionMRList", medicalInterventionMRList);
                                target = "MedicalInterventionMR";
                                break;
                            }
                            case 2: {
                                assistiveAugmentativeDevicesMRList = new ArrayList<FunctionalNeedReportDTO>();
                                assistiveAugmentativeDevicesMRList = functionalNeedService.getAssistiveAugmentativeDevicesMR(
                                        ds, functionalNeedDTO);
                                if (assistiveAugmentativeDevicesMRList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("assistiveDevicesMRList", assistiveAugmentativeDevicesMRList);
                                target = "AssistiveAugmentativeDevicesMR";
                                break;
                            }
                            case 3: {
                                otherNeedsMRList = new ArrayList<FunctionalNeedReportDTO>();
                                otherNeedsMRList = functionalNeedService.getOtherNeedsMR(ds, functionalNeedDTO);
                                if (otherNeedsMRList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("otherNeedsMRList", otherNeedsMRList);
                                target = "OtherNeedsMR";
                                break;
                            }
                        }
                        break;
                    }
                    case 5: {
                        ArrayList<FunctionalNeedReportDTO> medicalInterventionMIList = null;
                        ArrayList<FunctionalNeedReportDTO> otherNeedsMIList = null;
                        switch (reportcategory) {
                            case 1: {
                                medicalInterventionMIList = new ArrayList<FunctionalNeedReportDTO>();
                                medicalInterventionMIList = functionalNeedService.getMedicalInterventionMI(ds,
                                        functionalNeedDTO);
                                if (medicalInterventionMIList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("medicalInterventionMIList", medicalInterventionMIList);
                                target = "MedicalInterventionMI";
                                break;
                            }
                            case 2: {
                                //request.setAttribute("msg", "Not Applicable for the following selection");
                            }
                            case 3: {
                                otherNeedsMIList = new ArrayList<FunctionalNeedReportDTO>();
                                otherNeedsMIList = functionalNeedService.getOtherNeedsMI(ds, functionalNeedDTO);
                                if (otherNeedsMIList.isEmpty()) {
                                    request.setAttribute("msg", "No Records Found");
                                    target = "ReportException";
                                }
                                request.setAttribute("otherNeedsMIList", otherNeedsMIList);
                                target = "OtherNeedsMI";
                                break;
                            }
                        }
                        break;
                    }
                }
            } else {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            }
        } catch (Exception sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to generate the functional General needs report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
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
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to generate the functional  needs Personal report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getPersonalDetailsAnalysis(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = null;
        ActionMessages actionMessages = null;
        String typeOfDisability = null;
        String tableName = null;
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDTO functionalNeedDTO = new FunctionalNeedReportDTO();
        ArrayList<FunctionalNeedReportDTO> reportlist = null;
        reportlist = new ArrayList<FunctionalNeedReportDTO>();
        //DataSource datasource= getDataSource(request);
       


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession();
            String District_Id = request.getParameter("dID");
            String Mandal_Id = request.getParameter("mID");
            String Village_Id = request.getParameter("vID");
            String Habitation_Id = request.getParameter("hID");
            typeOfDisability = request.getParameter("D");
            String columnName = request.getParameter("cName");
            String columnValue = request.getParameter("cValue");
            String fromDate = request.getParameter("fDate");
            String toDate = request.getParameter("toDate");
            request.setAttribute("cName", columnName);
            request.setAttribute("cValue", columnValue);
            request.setAttribute("typeOfDisability", typeOfDisability);

            if (typeOfDisability.equals("1")) {
                tableName = "tblPerson_LocomotorNeeds_Details";
            } else if (typeOfDisability.equals("2")) {
                tableName = "tblVisualImpairment_Details";
            } else if (typeOfDisability.equals("3")) {
                tableName = "tblHearing_Impairment_Details";
            } else if (typeOfDisability.equals("4")) {
                tableName = "tblMental_Retardation_Details";
            } else if (typeOfDisability.equals("5")) {
                tableName = "tblMental_Disability_Illness_Details";
            }

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            functionalFeedFORM.setDistrict_id(District_Id);
            functionalFeedFORM.setMandal_id(Mandal_Id);
            functionalFeedFORM.setVillage_id(Village_Id);
            functionalFeedFORM.setHabitation_id(Habitation_Id);
            functionalFeedFORM.setFromdate(fromDate);
            functionalFeedFORM.setTodate(toDate);
            BeanUtils.copyProperties(functionalNeedDTO, functionalFeedFORM);
            int totalresults = Integer.parseInt(request.getParameter("count"));
            String totresstr = String.valueOf(totalresults);
            if (request.getParameter("start") == null) {
                reportlist = functionalNeedService.getPersonalDetailsAnalysis(ds, functionalNeedDTO, 1, totalresults,
                        columnName, columnValue, tableName);
                session.setAttribute("arraylist", reportlist);
            }

            String actionnamewithdo = request.getRequestURI();
            String actionnamewithdoarr[] = actionnamewithdo.split("/");
            String url = actionnamewithdoarr[2] + "?getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID="
                    + District_Id + "&mID=" + Mandal_Id + "&vID=" + Village_Id + "&hID=" + Habitation_Id
                    + "&fDate=" + fromDate + "&toDate=" + toDate + "&count=" + totresstr + "&D=" + typeOfDisability;
            request.setAttribute("url", url);
            CommonDAO commonDAO = new CommonDAO();
            ArrayList startAndEndList = new ArrayList();
            startAndEndList = commonDAO.populateStartEnd(request, totalresults);
            int start = 0;
            int endrange = 0;
            start = ((Integer) startAndEndList.get(0)).intValue();
            endrange = ((Integer) startAndEndList.get(1)).intValue();
            reportlist = functionalNeedService.getPersonalDetailsAnalysis(ds, functionalNeedDTO, start, endrange,
                    columnName, columnValue, tableName);

            request.setAttribute("reportlist", reportlist);
            session.setAttribute("arraylist1", reportlist);

            if (reportlist.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            } else {
                target = "repeat";
            }

        } catch (SADAREMDBException sADAREMException) {
            sADAREMException.printStackTrace();
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
             sADAREMException.printStackTrace();
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to generate the functional  needs Personal report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getPersonalDetailsTwoColumns(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = null;
        ActionMessages actionMessages = null;
        String typeOfDisability = null;
        String tableName = null;
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDTO functionalNeedDTO = new FunctionalNeedReportDTO();
        ArrayList<FunctionalNeedReportDTO> reportlist = null;
        reportlist = new ArrayList<FunctionalNeedReportDTO>();
       

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession();
            String District_Id = request.getParameter("dID");
            String Mandal_Id = request.getParameter("mID");
            String Village_Id = request.getParameter("vID");
            String Habitation_Id = request.getParameter("hID");
            typeOfDisability = request.getParameter("D");
            String columnNameOne = request.getParameter("cName");
            String columnValueOne = request.getParameter("cValue");
            String columnNameTwo = request.getParameter("cn2");
            String columnValueTwo = request.getParameter("cv2");
            String fromDate = request.getParameter("fDate");
            String toDate = request.getParameter("toDate");
            request.setAttribute("cName", columnNameOne);
            request.setAttribute("cValue", columnValueOne);
            request.setAttribute("cNameTwo", columnNameTwo);
            request.setAttribute("cValueTwo", columnValueTwo);
            request.setAttribute("typeOfDisability", typeOfDisability);

            if (typeOfDisability.equals("1")) {
                tableName = "tblPerson_LocomotorNeeds_Details";
            } else if (typeOfDisability.equals("2")) {
                tableName = "tblVisualImpairment_Details";
            } else if (typeOfDisability.equals("3")) {
                tableName = "tblHearing_Impairment_Details";
            } else if (typeOfDisability.equals("4")) {
                tableName = "tblMental_Retardation_Details";
            } else if (typeOfDisability.equals("5")) {
                tableName = "tblMental_Disability_Illness_Details";
            }

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            functionalFeedFORM.setDistrict_id(District_Id);
            functionalFeedFORM.setMandal_id(Mandal_Id);
            functionalFeedFORM.setVillage_id(Village_Id);
            functionalFeedFORM.setHabitation_id(Habitation_Id);
            functionalFeedFORM.setFromdate(fromDate);
            functionalFeedFORM.setTodate(toDate);
            BeanUtils.copyProperties(functionalNeedDTO, functionalFeedFORM);
            int totalresults = Integer.parseInt(request.getParameter("count"));
            String totresstr = String.valueOf(totalresults);
            reportlist = functionalNeedService.getPersonalDetailsTwoColumns(ds, functionalNeedDTO, 1, totalresults,
                    columnNameOne, columnValueOne, columnNameTwo, columnValueTwo, tableName);
            session.setAttribute("arraylist", reportlist);
            String actionnamewithdo = request.getRequestURI();
            String actionnamewithdoarr[] = actionnamewithdo.split("/");
            String url = actionnamewithdoarr[2] + "?getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID="
                    + District_Id + "&mID=" + Mandal_Id + "&vID=" + Village_Id + "&hID=" + Habitation_Id
                    + "&fDate=" + fromDate + "&toDate=" + toDate + "&count=" + totresstr + "&D=" + typeOfDisability
                    + "&cn2=" + columnNameTwo + "&cv2=" + columnValueTwo;
            request.setAttribute("url", url);
            CommonDAO commonDAO = new CommonDAO();
            ArrayList startAndEndList = new ArrayList();
            startAndEndList = commonDAO.populateStartEnd(request, totalresults);
            int start = 0;
            int endrange = 0;
            start = ((Integer) startAndEndList.get(0)).intValue();
            endrange = ((Integer) startAndEndList.get(1)).intValue();
            reportlist = functionalNeedService.getPersonalDetailsTwoColumns(ds, functionalNeedDTO, start, endrange,
                    columnNameOne, columnValueOne, columnNameTwo, columnValueTwo, tableName);

            request.setAttribute("reportlist", reportlist);
            session.setAttribute("arraylist1", reportlist);

            if (reportlist.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            } else {
                target = "repeat";
            }

        } catch (SADAREMDBException sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to generate the Not Come to SADAREM Camp report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getNotComeToCamp(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws SADAREMDBException, SQLException 
    {
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        String reportCategory = null;
        String target = null;
        String districtName = null;
        String phseName = null;
        String districtId = null;

        ActionMessages actionMessages = null;
        HttpSession session = request.getSession();
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDTO functionalNeedDTO = new FunctionalNeedReportDTO();
        
        reportCategory = CommonUtility.checkNullObj(request.getParameter("reportCategory"));
        districtName = CommonUtility.checkNullObj(request.getParameter("districtName"));
        phseName = CommonUtility.checkNullObj(request.getParameter("pahseName"));
        phseName=functionalFeedFORM.getPhase();
        districtId = CommonUtility.checkNullObj(functionalFeedFORM.getDistrict_id());


        try 
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds))
            {
                ds = JNDIDataSource.getConnection();
            }
            
            FunctionalNeedReportService functionalNeedService = FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            BeanUtils.copyProperties(functionalNeedDTO, functionalFeedFORM);
            
            if (reportCategory != null) 
            {
                if (reportCategory.equals("1"))
                {

                    ArrayList<FunctionalNeedReportDTO> notComeToCampList = null;
                    notComeToCampList = new ArrayList<FunctionalNeedReportDTO>();
                    
                    notComeToCampList = functionalNeedService.getNotComeToSadaremCamp(ds,districtId, phseName);
                    
                    if (notComeToCampList.isEmpty()) 
                    {
                        request.setAttribute("msg", "No Records Found");
                        target = "ReportException";
                    }
                    request.setAttribute("notComeToCampList", notComeToCampList);
                    session.setAttribute("notComeToCampList1", notComeToCampList);
                    target = "NotComeToSADAREMCamp";
                }
            }
            else 
            {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            }
            functionalFeedFORM.setDistrictlist(territoryService.getDistricts(ds));
        } 
        catch (Exception sADAREMException) 
        {
            target = "ReportException";
            log.error(sADAREMException);
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to generate the Not Come to SADAREM Camp Personal report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getNotComeToCampPersonalDetails(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {


        String target = null;
        String districtName = null;
        String phaseName = null;
        String districtId = null;
        String mandalId = null;
        String reportType = null;

        ActionMessages actionMessages = null;
        districtId = request.getParameter("dID");
        mandalId = request.getParameter("mID");
        districtName = request.getParameter("districtName");
        phaseName = request.getParameter("phase");
        reportType = request.getParameter("reportType");
        response.setContentType("application/MyExcel.ms-excel");

        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            ArrayList<FunctionalNeedReportDTO> notComeToCampList = null;
            notComeToCampList = new ArrayList<FunctionalNeedReportDTO>();
            notComeToCampList = functionalNeedService.getNotComeToCampPersonalDetails(ds,
                    districtId, mandalId, phaseName, reportType);
            if (notComeToCampList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            }
            request.setAttribute("notComeToCampList", notComeToCampList);
            if (reportType.equals("parta")) {
                target = "onlyPartAPersonal";
                //kavya
                exportExcel(notComeToCampList, reportType, response);

            } else if (reportType.equals("notcome")) {
                target = "NotComeToSADAREMCamp";
                //kavya
                exportExcel(notComeToCampList, reportType, response);
            }

        } catch (Exception sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to generate the functional  needs  Surgery wise and Any other Needs Personal report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getPersonalDetailsSurgery(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = null;
        ActionMessages actionMessages = null;
        String typeOfDisability = null;
        String tableName = null;
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDTO functionalNeedDTO = new FunctionalNeedReportDTO();
        ArrayList<FunctionalNeedReportDTO> reportlist = null;
        reportlist = new ArrayList<FunctionalNeedReportDTO>();
       


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String District_Id = request.getParameter("dID");
            String Mandal_Id = request.getParameter("mID");
            String Village_Id = request.getParameter("vID");
            String Habitation_Id = request.getParameter("hID");
            typeOfDisability = request.getParameter("D");
            String columnName = request.getParameter("cName");
            String fromDate = request.getParameter("fDate");
            String toDate = request.getParameter("toDate");
            request.setAttribute("cName", columnName);
            request.setAttribute("typeOfDisability", typeOfDisability);

            if (typeOfDisability.equals("1")) {
                tableName = "tblPerson_LocomotorNeeds_Details S";
            } else if (typeOfDisability.equals("2")) {
                tableName = "tblVisualImpairment_Details S";
            } else if (typeOfDisability.equals("3")) {
                tableName = "tblHearing_Impairment_Details S";
            } else if (typeOfDisability.equals("4")) {
                tableName = "tblMental_Retardation_Details S";
            } else if (typeOfDisability.equals("5")) {
                tableName = "tblMental_Disability_Illness_Details S";
            }

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            functionalFeedFORM.setDistrict_id(District_Id);
            functionalFeedFORM.setMandal_id(Mandal_Id);
            functionalFeedFORM.setVillage_id(Village_Id);
            functionalFeedFORM.setHabitation_id(Habitation_Id);
            functionalFeedFORM.setFromdate(fromDate);
            functionalFeedFORM.setTodate(toDate);
            functionalNeedDTO.setDisabilityType(typeOfDisability);
            BeanUtils.copyProperties(functionalNeedDTO, functionalFeedFORM);
            reportlist = functionalNeedService.getPersonalDetailsSurgery(ds, functionalNeedDTO,
                    columnName, tableName);

            request.setAttribute("reportlist", reportlist);

            if (reportlist.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            } else {
                target = "repeat";
            }

        } catch (SADAREMDBException sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to generate the functional General Needs Personal report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getpersonalGeneralNeeds(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = null;
        ActionMessages actionMessages = null;
        String typeOfDisability = null;
        String tableName = null;
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDTO functionalNeedDTO = new FunctionalNeedReportDTO();
        ArrayList<FunctionalNeedReportDTO> genaralPersonalList = null;
        genaralPersonalList = new ArrayList<FunctionalNeedReportDTO>();
       


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession();
            String District_Id = request.getParameter("dID");
            String Mandal_Id = request.getParameter("mID");
            String Village_Id = request.getParameter("vID");
            String Habitation_Id = request.getParameter("hID");
            typeOfDisability = request.getParameter("D");
            String columnName = request.getParameter("cName");
            String columnValue = request.getParameter("cValue");
            String fromDate = request.getParameter("fDate");
            String toDate = request.getParameter("toDate");
            tableName = request.getParameter("tN");
            request.setAttribute("cName", columnName);
            request.setAttribute("cValue", columnValue);
            request.setAttribute("typeOfDisability", typeOfDisability);

            if (tableName.equals("1")) {
                tableName = "tblAllDisabilityPerson_Common_Needs s";
            } else if (tableName.equals("2")) {
                tableName = "tblMental_Retardation_Details s";
            } else if (tableName.equals("3")) {
                tableName = "tblMental_Disability_Illness_Details s";
            }

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            functionalFeedFORM.setDistrict_id(District_Id);
            functionalFeedFORM.setMandal_id(Mandal_Id);
            functionalFeedFORM.setVillage_id(Village_Id);
            functionalFeedFORM.setHabitation_id(Habitation_Id);
            functionalFeedFORM.setFromdate(fromDate);
            functionalFeedFORM.setTodate(toDate);
            functionalNeedDTO.setDisabilityType(typeOfDisability);
            BeanUtils.copyProperties(functionalNeedDTO, functionalFeedFORM);
            int totalresults = Integer.parseInt(request.getParameter("count"));
            String totresstr = String.valueOf(totalresults);

            String actionnamewithdo = request.getRequestURI();
            String actionnamewithdoarr[] = actionnamewithdo.split("/");
            String url = actionnamewithdoarr[2] + "?getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID="
                    + District_Id + "&mID=" + Mandal_Id + "&vID=" + Village_Id + "&hID=" + Habitation_Id
                    + "&fDate=" + fromDate + "&toDate=" + toDate + "&count=" + totresstr + "&D=" + typeOfDisability;
            request.setAttribute("url", url);
            CommonDAO commonDAO = new CommonDAO();
            ArrayList startAndEndList = new ArrayList();
            startAndEndList = commonDAO.populateStartEnd(request, totalresults);
            int start = 0;
            int endrange = 0;
            start = ((Integer) startAndEndList.get(0)).intValue();
            endrange = ((Integer) startAndEndList.get(1)).intValue();
            genaralPersonalList = functionalNeedService.getpersonalGeneralNeeds(ds, functionalNeedDTO, start, endrange,
                    columnName, columnValue, tableName);

            request.setAttribute("genaralPersonalList", genaralPersonalList);
            session.setAttribute("genaralPersonalList1", genaralPersonalList);

            if (genaralPersonalList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            } else {
                target = "repeat";
            }

        } catch (SADAREMDBException sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to generate the functional  needs Personal report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getpersonalGeneralNeedsTotal(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = null;
        ActionMessages actionMessages = null;
        String typeOfDisability = null;
        String tableName = null;
        FunctionalNeedReportForm functionalFeedFORM = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDTO functionalNeedDTO = new FunctionalNeedReportDTO();
        ArrayList<FunctionalNeedReportDTO> genaralPersonalTotalList = null;
        genaralPersonalTotalList = new ArrayList<FunctionalNeedReportDTO>();
        


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String District_Id = request.getParameter("dI");
            String Mandal_Id = request.getParameter("mI");
            String Village_Id = request.getParameter("vI");
            String Habitation_Id = request.getParameter("hI");
            typeOfDisability = request.getParameter("D");
            String columnName = request.getParameter("cName");
            String columnValue = request.getParameter("R");
            String fromDate = request.getParameter("fD");
            String toDate = request.getParameter("tD");
            tableName = request.getParameter("tN");
            request.setAttribute("cName", columnName);
            request.setAttribute("cValue", columnValue);
            request.setAttribute("typeOfDisability", typeOfDisability);

            if (tableName.equals("1")) {
                tableName = "tblAllDisabilityPerson_Common_Needs S";
            } else if (tableName.equals("2")) {
                tableName = "tblMental_Retardation_Details S";
            } else if (tableName.equals("3")) {
                tableName = "tblMental_Disability_Illness_Details S";
            }

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            functionalFeedFORM.setDistrict_id(District_Id);
            functionalFeedFORM.setMandal_id(Mandal_Id);
            functionalFeedFORM.setVillage_id(Village_Id);
            functionalFeedFORM.setHabitation_id(Habitation_Id);
            functionalFeedFORM.setFromdate(fromDate);
            functionalFeedFORM.setTodate(toDate);
            functionalNeedDTO.setDisabilityType(typeOfDisability);
            BeanUtils.copyProperties(functionalNeedDTO, functionalFeedFORM);
            int totalresults = Integer.parseInt(request.getParameter("count"));
            String totresstr = String.valueOf(totalresults);

            genaralPersonalTotalList = functionalNeedService.getpersonalGeneralNeedsTotal(ds, functionalNeedDTO, 1,
                    totalresults, columnName, columnValue, tableName);
            request.setAttribute("genaralPersonalTotalList", genaralPersonalTotalList);




            if (genaralPersonalTotalList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            } else {
                target = "repeat";
            }

        } catch (SADAREMDBException sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     *
     * @description this method is used to generate the Education wise report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward getEducationWiseReport(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String target = null;

        ActionMessages actionMessages = null;
        String districtName = request.getParameter("districtName");
        String mandalName = request.getParameter("mandalName");
        String villageName = request.getParameter("villageName");
        String districtId = request.getParameter("districtId");
        String mandalId = request.getParameter("mandalId");
        String villageId = request.getParameter("villageId");
        String urbanId = request.getParameter("urbanId");
        String fromDate = request.getParameter("fdate");
        String toDate = request.getParameter("todate");

        FunctionalNeedReportDTO educationwiseDTO = new FunctionalNeedReportDTO();
        educationwiseDTO.setDistrict_id(districtId);
        educationwiseDTO.setMandal_id(mandalId);
        educationwiseDTO.setVillage_id(villageId);
        educationwiseDTO.setUrban_id(urbanId);
        educationwiseDTO.setFromdate(fromDate);
        educationwiseDTO.setTodate(toDate);

        ArrayList areaDetails = new ArrayList();
        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ArrayList<FunctionalNeedReportDTO> educationwiseList = null;
            educationwiseList = new ArrayList<FunctionalNeedReportDTO>();
            educationwiseList = functionalNeedService.getEducationWiseReport(ds,
                    educationwiseDTO);

            areaDetails = functionalNeedService.getAreaDetails(ds, districtId, mandalId, villageId, "0");

            if (educationwiseList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            }
            request.setAttribute("educationwiseList", educationwiseList);
            request.setAttribute("areaDetails", areaDetails);
            target = "educationwise";
        } catch (Exception sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
    //kavya

    public static void exportExcel(ArrayList dataList, String reportType, HttpServletResponse response) {
        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=notComrToSadaremCampPersonal.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);

            if (dataList.size() == 0) {
                s.addCell(new Label(0, 0, "No Data Found................"));
            } else {

                if (reportType.equals("parta")) {

                    headerList.add("S No");
                    headerList.add("Pension ID");
                    headerList.add("SADAREM ID");
                    headerList.add("Name");
                    headerList.add("Relation Name");
                    headerList.add("Gender");
                    headerList.add("Age");
                    headerList.add("Ration Card Number");
                    headerList.add("Type of Disability");
                    headerList.add("House No");
                    headerList.add("Mandal");
                    headerList.add("Village");
                    headerList.add("Habitation");
                    headerList.add("Pension Phase");
                    // headerList.add("Status");
                    setWidth(s, 7, 20);

                } else {

                    headerList.add("S No");
                    headerList.add("Pension ID");
                    headerList.add("First Name");
                    headerList.add("Middle Name");
                    headerList.add("Last Name");
                    headerList.add("Retalation Name");
                    headerList.add("Gender");
                    headerList.add("Age");
                    headerList.add("Disability");
                    headerList.add("Ration Card Number");
                    headerList.add("House No");
                    headerList.add("Disability Type");
                    headerList.add("Mandal");
                    headerList.add("Village");
                    headerList.add("Habitation");
                    headerList.add("Pension Phase");
                    headerList.add("Data Received Date");

                    setWidth(s, 9, 20);
                }
                addHeaders(s, headerList);
            }

            int x = 2;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                FunctionalNeedReportDTO functionalNeedReportDTO = (FunctionalNeedReportDTO) dataList.get(i);

                s.addCell(new Label(j++, x, i + 1 + ""));
                if (reportType.equals("parta")) {
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getPensionid()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getPersoncode()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getRalationName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getGender()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getAge()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getRationcard()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getDisabilityType()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getHouseno()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getMandalName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getVillageName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getHabitationName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getPhase()));
                    //s.addCell(new Label(j++, x, functionalNeedReportDTO.getCategoryID()));
                } else {
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getPensionid()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getFirstName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getMiddleName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getLastName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getRalationName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getGender()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getAge()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getSubtypeofDisability()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getRationcard()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getHouseno()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getDisabilityType()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getMandalName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getVillageName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getHabitationName()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getPhase()));
                    s.addCell(new Label(j++, x, functionalNeedReportDTO.getDataReceivedDate()));

                }

                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    j = 0;
                    x = 2;
                    if (reportType.equals("parta")) {
                        setWidth(s, 7, 20);
                    } else {
                        setWidth(s, 9, 20);
                    }
                    addHeaders(s, headerList);
                }

            }

            w.write();

            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void setWidth(WritableSheet s, int col, int widthInChars) {

        s.setColumnView(col, widthInChars);
    }

    public static void addHeaders(WritableSheet s, ArrayList list) {

        for (int x = 0; x < list.size(); x++) {
            try {
                s.addCell(new Label(x, 0, list.get(x).toString()));
            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ActionForward getNotComeToCampPersonalDetailsTotal(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        String target = null;
        String districtName = null;
        String phaseName = null;
        String districtId = null;
        String mandalId = null;
        String reportType = null;

        ActionMessages actionMessages = null;
        districtId = request.getParameter("dID");
        mandalId = request.getParameter("mID");
        districtName = request.getParameter("districtName");
        phaseName = request.getParameter("phase");
        reportType = request.getParameter("reportType");
        response.setContentType("application/MyExcel.ms-excel");

        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            ArrayList<FunctionalNeedReportDTO> notComeToCampList = null;
            notComeToCampList = new ArrayList<FunctionalNeedReportDTO>();
            notComeToCampList = functionalNeedService.getNotComeToCampPersonalDetailsTotal(ds,
                    districtId, phaseName, reportType);
            if (notComeToCampList.isEmpty()) {
                request.setAttribute("msg", "No Records Found");
                target = "ReportException";
            }
            request.setAttribute("notComeToCampList", notComeToCampList);
            if (reportType.equals("parta")) {
                target = "onlyPartAPersonal";
                //kavya
                exportExcel(notComeToCampList, reportType, response);

            } else if (reportType.equals("notcome")) {
                target = "NotComeToSADAREMCamp";
                //kavya
                exportExcel(notComeToCampList, reportType, response);
            }

        } catch (Exception sADAREMException) {
            target = "ReportException";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage",
                    new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }
}
