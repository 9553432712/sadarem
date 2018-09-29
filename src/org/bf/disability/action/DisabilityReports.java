/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import org.apache.struts.actions.DispatchAction;
import java.util.ArrayList;
import java.util.Iterator;

import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.dao.FunctionalNeedReportDAO;
import org.bf.disability.dao.FunctionalReportDAO;
import org.bf.disability.dao.ReportDAO;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

/**
 *
 * @author 509862
 */
public class DisabilityReports extends DispatchAction {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=myAction1,
     * where "method" is the value specified in <action> element :
     * ( <action parameter="method" .../> )
     */
    public ActionForward myAction1(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=myAction2,
     * where "method" is the value specified in <action> element :
     * ( <action parameter="method" .../> )
     */
    public ActionForward MRReports(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Get District list
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ur = "/MRReport.do?MRReport=MRReport";
        request.setAttribute("ur", ur);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward MIReports(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Get District list
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ur = "/MIReport.do?MIReport=MIReport";
        request.setAttribute("ur", ur);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward VIReports(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Get District list
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ur = "/VIReport.do?VIReport=VIReport";
        request.setAttribute("ur", ur);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward HIReports(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Get District list
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ur = "/HIReport.do?HIReport=HIReport";
        request.setAttribute("ur", ur);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward OHReports(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Get District list
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ur = "/LocomotorReport.do?LocomotorReport=LocomotorReport";
        request.setAttribute("ur", ur);
        return mapping.findForward(SUCCESS);
    }

//LocomotorFunctiionalneedReports
    public ActionForward LocomotorFunctiionalneedReports(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Get District list
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ur = "/LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport";
        request.setAttribute("ur", ur);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward MRFunctiionalneedReports(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Get District list
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ur = "/MRFunctiionalneedReport.do?MRFunctiionalneedReport=MRFunctiionalneedReport";
        request.setAttribute("ur", ur);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward MIFunctiionalneedReports(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Get District list
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ur = "/MIFunctiionalneedReport.do?MIFunctiionalneedReport=MIFunctiionalneedReport";
        request.setAttribute("ur", ur);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward VIFunctiionalneedReports(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Get District list
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ur = "/VIFunctiionalneedReport.do?VIFunctiionalneedReport=VIFunctiionalneedReport";
        request.setAttribute("ur", ur);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward HIFunctiionalneedReports(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Get District list
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ur = "/HIFunctiionalneedReport.do?HIFunctiionalneedReport=HIFunctiionalneedReport";
        request.setAttribute("ur", ur);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward OHFunctiionalneedReports(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Get District list
        DataSource ds = null;
        TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        ArrayList districtList = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ur = "/LocomotorFunctiionalneedReport.do?LocomotorFunctiionalneedReport=LocomotorFunctiionalneedReport";
        request.setAttribute("ur", ur);
        return mapping.findForward(SUCCESS);
    }

//Disbility Causes Percentages Reports
    public ActionForward LocomotorReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String ur = "/LocomotorReport.do?LocomotorReport=LocomotorReport";
        request.setAttribute("ur", ur);

        String target = "success";
        String subquery = null, columns = null;
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String rationcard = null, agee = null;
        DataSource ds = null;
        FunctionalReportDAO funDao = new FunctionalReportDAO();
        int cou = 1;
        String edu = null, em = null, ma = null, caste = null, religion = null, disability = null, gender = null, total = null, pmarige = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        ArrayList habitationlist = new ArrayList();
        try {
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            Iterator itr = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            ArrayList ohWiseDetails = new ArrayList();
            ArrayList empWiseSingleDetails = new ArrayList();
            ArrayList districtList = new ArrayList();
            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            ReportDAO rdao = new ReportDAO();

            // Get District list

            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }

            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }
            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }
            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {

                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                // ohWiseDetails=rdao.getVisiualDisabilityCount(ds, districtId, mandalId, villageId,fdate,tdate);
                ohWiseDetails = funDao.getDisabilityCount(ds, districtId, mandalId, villageId, fdate, tdate, "1");
                if (ohWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {

                    request.setAttribute("ohWiseDetails", ohWiseDetails);
                }

            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String vi = request.getParameter("villagesId");
                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }

                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                // ohWiseDetails=rdao.getVisiualDisabilityCount(ds, dist, mandal, village,fdate,tdate);
                ohWiseDetails = funDao.getDisabilityCount(ds, dist, mandal, village, fdate, tdate, "1");
                if (ohWiseDetails.size() == 0) {

                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("ohWiseDetails", ohWiseDetails);

                }
                target = "excel";

            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                ohWiseDetails = funDao.getDisabilityCount(ds, dist, mandal, village, fdate, tdate, "1");
                //  ohWiseDetails=rdao.getVisiualDisabilityCount(ds, dist, mandal, village,fdate,tdate);
                if (ohWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("ohWiseDetails", ohWiseDetails);
                }
                target = "print";
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                String selectmethod = request.getParameter("query");
                String co = columns, type = null;
                type = (String) request.getParameter("type");
                request.setAttribute("type", type);
                co = co.replace("'", "%27");
                co = co.replace("(", "%28");
                co = co.replace(")", "%29");
                co = co.replace("+", "%2B");
                co = co.replace(",", "%2C");
//co=co.replace("%", "%25");
                co = co.replace("%%", "%25%");
                request.setAttribute("tablee", subquery);
                request.setAttribute("colu", co);
                request.setAttribute("query", selectmethod);
                if (selectmethod != null && selectmethod.equalsIgnoreCase("query")) {
                    ohWiseDetails = dao.getPersonalDetailsAllQuery(ds, dist, mandal, village, hab, fdate, tdate, "1", subquery, columns);
                } else {
                    ohWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "1", subquery, columns);
                }
                request.setAttribute("ohWiseDetails", ohWiseDetails);
                if (ohWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data ");
                }
                target = "personal";
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                String selectmethod = request.getParameter("query");
                if (selectmethod != null && selectmethod.equalsIgnoreCase("query")) {
                    ohWiseDetails = dao.getPersonalDetailsAllQuery(ds, dist, mandal, village, hab, fdate, tdate, "1", subquery, columns);
                } else {

                    ohWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "1", subquery, columns);
                }
                request.setAttribute("ohWiseDetails", ohWiseDetails);
                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    public ActionForward MIReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String ur = "/MIReport.do?MIReport=MIReport";
        request.setAttribute("ur", ur);
        String subquery = null, columns = null;
        String target = "success";
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String rationcard = null, agee = null;
        DataSource ds = null;

        int cou = 1;
        String edu = null, em = null, ma = null, caste = null, religion = null, disability = null, gender = null, total = null, pmarige = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        FunctionalReportDAO funDao = new FunctionalReportDAO();
        ArrayList habitationlist = new ArrayList();
        try {
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            Iterator itr = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            ArrayList miWiseDetails = new ArrayList();

            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            ArrayList districtList = new ArrayList();
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }

            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }
            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }
            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                miWiseDetails = funDao.getDisabilityCount(ds, districtId, mandalId, villageId, fdate, tdate, "5");
                if (miWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("miWiseDetails", miWiseDetails);
                }
            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String vi = request.getParameter("villagesId");
                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                miWiseDetails = funDao.getDisabilityCount(ds, dist, mandal, village, fdate, tdate, "5");
                if (miWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("miWiseDetails", miWiseDetails);
                }
                target = "excel";
            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                miWiseDetails = funDao.getDisabilityCount(ds, dist, mandal, village, fdate, tdate, "5");
                if (miWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("miWiseDetails", miWiseDetails);
                }
                target = "print";
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;// String subquery=null, columns=null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }
                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                String co = columns, type = null;
                type = (String) request.getParameter("type");
                request.setAttribute("type", type);
                co = co.replace("'", "%27");//for adding specila symbles to url, special simbols must be converted into hexacode.
                co = co.replace("(", "%28");
                co = co.replace(")", "%29");
                co = co.replace("+", "%2B");
                co = co.replace(",", "%2C");
                request.setAttribute("tablee", subquery);
                request.setAttribute("colu", co);
                miWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "5", subquery, columns);
                request.setAttribute("miDetails", miWiseDetails);
                if (miWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data ");
                }
                target = "personal";
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                miWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "5", subquery, columns);
                request.setAttribute("miDetails", miWiseDetails);
                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward VIReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String ur = "/VIReport.do?VIReport=VIReport";
        request.setAttribute("ur", ur);
        String target = "success";
        String subquery = null, columns = null;
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String rationcard = null, agee = null;
        DataSource ds = null;
        FunctionalReportDAO funDao = new FunctionalReportDAO();
        int cou = 1;
        String edu = null, em = null, ma = null, caste = null, religion = null, disability = null, gender = null, total = null, pmarige = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        ArrayList habitationlist = new ArrayList();
        try {
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            Iterator itr = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            ArrayList viWiseDetails = new ArrayList();
            ArrayList empWiseSingleDetails = new ArrayList();
            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            ArrayList districtList = new ArrayList();
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }
            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }
            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {

                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                viWiseDetails = funDao.getVisiualDisabilityCount(ds, districtId, mandalId, villageId, fdate, tdate);
                if (viWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {

                    request.setAttribute("viWiseDetails", viWiseDetails);
                }

            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String vi = request.getParameter("villagesId");
                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }

                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                viWiseDetails = funDao.getVisiualDisabilityCount(ds, dist, mandal, village, fdate, tdate);
                if (viWiseDetails.size() == 0) {

                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("viWiseDetails", viWiseDetails);

                }
                target = "excel";

            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                viWiseDetails = funDao.getVisiualDisabilityCount(ds, dist, mandal, village, fdate, tdate);
                if (viWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("viWiseDetails", viWiseDetails);
                }
                target = "print";
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                String co = columns, type = null;
                type = (String) request.getParameter("type");
                request.setAttribute("type", type);
                co = co.replace("'", "%27");
                co = co.replace("(", "%28");
                co = co.replace(")", "%29");
                co = co.replace("+", "%2B");
                co = co.replace(",", "%2C");
                request.setAttribute("tablee", subquery);
                request.setAttribute("colu", co);
                viWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "2", subquery, columns);
                request.setAttribute("viDetails", viWiseDetails);
                if (viWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data ");
                }
                target = "personal";
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                viWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "2", subquery, columns);
                request.setAttribute("viDetails", viWiseDetails);
                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        //target="success";
        return mapping.findForward(target);
    }

    public ActionForward HIReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String ur = "/HIReport.do?HIReport=HIReport";
        request.setAttribute("ur", ur);
        String target = "success";
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String rationcard = null, agee = null;
        DataSource ds = null;
        FunctionalReportDAO funDao = new FunctionalReportDAO();
        int cou = 1;
        String subquery = null, columns = null;
        String edu = null, em = null, ma = null, caste = null, religion = null, disability = null, gender = null, total = null, pmarige = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        ArrayList habitationlist = new ArrayList();
        try {
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            Iterator itr = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            ArrayList hiWiseDetails = new ArrayList();
            ArrayList empWiseSingleDetails = new ArrayList();
            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            ArrayList districtList = new ArrayList();
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }
            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }
            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                hiWiseDetails = funDao.getDisabilityCount(ds, districtId, mandalId, villageId, fdate, tdate, "3");
                if (hiWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("hiWiseDetails", hiWiseDetails);

                }

            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String vi = request.getParameter("villagesId");
                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                hiWiseDetails = funDao.getDisabilityCount(ds, dist, mandal, village, fdate, tdate, "3");

                if (hiWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("hiWiseDetails", hiWiseDetails);
                }
                target = "excel";

            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                hiWiseDetails = funDao.getDisabilityCount(ds, dist, mandal, village, fdate, tdate, "3");
                if (hiWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("hiWiseDetails", hiWiseDetails);

                }
                target = "print";

            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;

                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                String co = columns, type = null;
                type = (String) request.getParameter("type");
                request.setAttribute("type", type);
                co = co.replace("'", "%27");
                co = co.replace("(", "%28");
                co = co.replace(")", "%29");
                co = co.replace("+", "%2B");
                co = co.replace(",", "%2C");
                request.setAttribute("tablee", subquery);
                request.setAttribute("colu", co);
                hiWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "3", subquery, columns);
                request.setAttribute("hiDetails", hiWiseDetails);
                if (hiWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data ");
                }
                target = "personal";
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                hiWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "3", subquery, columns);
                request.setAttribute("hiDetails", hiWiseDetails);
                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);

    }

    public ActionForward MRReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String subquery = null, columns = null;
        String ur = "/MRReport.do?MRReport=MRReport";
        request.setAttribute("ur", ur);
        String target = "success";
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String rationcard = null, agee = null;
        DataSource ds = null;

        int cou = 1;
        String edu = null, em = null, ma = null, caste = null, religion = null, disability = null, gender = null, total = null, pmarige = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        FunctionalReportDAO funDao = new FunctionalReportDAO();
        ArrayList habitationlist = new ArrayList();
        try {
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            Iterator itr = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            ArrayList mrWiseDetails = new ArrayList();
            ArrayList empWiseSingleDetails = new ArrayList();
            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            ArrayList districtList = new ArrayList();
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }
            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }


            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {

                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
//getMentalRetardationDisabilityCount
                mrWiseDetails = funDao.getMentalRetardationDisabilityCount(ds, districtId, mandalId, villageId, fdate, tdate);

                if (mrWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("mrWiseDetails", mrWiseDetails);
                }

            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;

                String vi = request.getParameter("villagesId");
                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                mrWiseDetails = funDao.getMentalRetardationDisabilityCount(ds, dist, mandal, village, fdate, tdate);
                if (mrWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {

                    request.setAttribute("mrWiseDetails", mrWiseDetails);

                }
                target = "excel";

            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                mrWiseDetails = funDao.getMentalRetardationDisabilityCount(ds, dist, mandal, village, fdate, tdate);
                if (mrWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("mrWiseDetails", mrWiseDetails);
                }

                target = "print";

            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;// String subquery=null, columns=null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                String co = columns, type = null;
                type = (String) request.getParameter("type");
                request.setAttribute("type", type);
                co = co.replace("'", "%27");
                co = co.replace("(", "%28");
                co = co.replace(")", "%29");
                co = co.replace("+", "%2B");
                co = co.replace(",", "%2C");
                request.setAttribute("tablee", subquery);
                request.setAttribute("colu", co);
                mrWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "4", subquery, columns);
                request.setAttribute("mrDetails", mrWiseDetails);
                if (mrWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data ");
                }
                target = "personal";
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }
                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                mrWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "4", subquery, columns);
                request.setAttribute("mrfDetails", mrWiseDetails);
                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

//functional need reports
    public ActionForward LocomotorFunctiionalneedReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String ur = "/LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport";
        request.setAttribute("ur", ur);//it is used to know action takes
        String target = "success";
        String districtId = null;
        String subquery = null, columns = null;
        String mandalId = null;
        String villageId = null;
        String rationcard = null, agee = null;
        DataSource ds = null;
        FunctionalReportDAO funDao = new FunctionalReportDAO();
        int cou = 1;
        String edu = null, em = null, ma = null, caste = null, religion = null, disability = null, gender = null, total = null, pmarige = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        ArrayList habitationlist = new ArrayList();
        try {
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            Iterator itr = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            ArrayList ohfWiseDetails = new ArrayList();
            ArrayList empWiseSingleDetails = new ArrayList();

            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();

            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            ArrayList districtList = new ArrayList();
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }


            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }


            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {

                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);


                ohfWiseDetails = funDao.getOHFunctionalneedsCount(ds, districtId, mandalId, villageId, fdate, tdate);
                if (ohfWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("ohfWiseDetails", ohfWiseDetails);
                }

            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;

                String vi = request.getParameter("villagesId");
                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }


                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                ohfWiseDetails = funDao.getOHFunctionalneedsCount(ds, dist, mandal, village, fdate, tdate);
                if (ohfWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("ohfWiseDetails", ohfWiseDetails);

                }
                target = "excel";

            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }


                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);

                ohfWiseDetails = funDao.getOHFunctionalneedsCount(ds, dist, mandal, village, fdate, tdate);
                if (ohfWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("ohfWiseDetails", ohfWiseDetails);
                }
                target = "print";

            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }


                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);


                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                String co = columns, type = null;
                type = (String) request.getParameter("type");
                request.setAttribute("type", type);
                co = co.replace("'", "%27");
                co = co.replace("(", "%28");
                co = co.replace(")", "%29");
                co = co.replace("+", "%2B");
                co = co.replace(",", "%2C");
                request.setAttribute("tablee", subquery);
                request.setAttribute("colu", co);

                ohfWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "1", subquery, columns);
                request.setAttribute("ohfDetails", ohfWiseDetails);
                if (ohfWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data ");

                }

                target = "personal";
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                ohfWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "1", subquery, columns);
                request.setAttribute("ohfDetails", ohfWiseDetails);
                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);

    }

    public ActionForward MIFunctiionalneedReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String ur = "/MIFunctiionalneedReport.do?MIFunctiionalneedReport=MIFunctiionalneedReport";
        request.setAttribute("ur", ur);
        String target = "success";
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String rationcard = null, agee = null;
        DataSource ds = null;

        int cou = 1;
        String edu = null, em = null, ma = null, caste = null, religion = null, disability = null, gender = null, total = null, pmarige = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        FunctionalReportDAO funDao = new FunctionalReportDAO();
        ArrayList habitationlist = new ArrayList();
        try {
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            Iterator itr = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                //ds = JNDIDataSource.getConnection();
            }
            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            ArrayList mifWiseDetails = new ArrayList();
            ArrayList empWiseSingleDetails = new ArrayList();

            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            ArrayList districtList = new ArrayList();
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
            String subquery = null, columns = null;
            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }


            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }


            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {

                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);

                mifWiseDetails = funDao.getMiFunctionalneedsCount(ds, districtId, mandalId, villageId, fdate, tdate);

                if (mifWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("mifWiseDetails", mifWiseDetails);

                }

            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;

                String vi = request.getParameter("villagesId");
                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                mifWiseDetails = funDao.getMiFunctionalneedsCount(ds, dist, mandal, village, fdate, tdate);

                if (mifWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("mifWiseDetails", mifWiseDetails);

                }
                target = "excel";

            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }


                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);

                mifWiseDetails = funDao.getMiFunctionalneedsCount(ds, dist, mandal, village, fdate, tdate);

                if (mifWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {

                    request.setAttribute("mifWiseDetails", mifWiseDetails);

                }
                target = "print";

            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }


                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);

                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                String co = columns, type = null;
                type = (String) request.getParameter("type");
                request.setAttribute("type", type);
                co = co.replace("'", "%27");
                co = co.replace("(", "%28");
                co = co.replace(")", "%29");
                co = co.replace("+", "%2B");
                co = co.replace(",", "%2C");
                request.setAttribute("tablee", subquery);
                request.setAttribute("colu", co);

                mifWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "5", subquery, columns);
                request.setAttribute("mifDetails", mifWiseDetails);
                if (mifWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data ");

                }
                target = "personal";
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                mifWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "5", subquery, columns);
                request.setAttribute("mifDetails", mifWiseDetails);
                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //target="success";
        return mapping.findForward(target);
    }

    public ActionForward VIFunctiionalneedReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String ur = "/VIFunctiionalneedReport.do?VIFunctiionalneedReport=VIFunctiionalneedReport";
        request.setAttribute("ur", ur);
        String target = "success";
        String districtId = null;
        String mandalId = null;
        String subquery = null, columns = null;
        String villageId = null;
        String rationcard = null, agee = null;
        DataSource ds = null;

        int cou = 1;
        String edu = null, em = null, ma = null, caste = null, religion = null, disability = null, gender = null, total = null, pmarige = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        FunctionalReportDAO funDao = new FunctionalReportDAO();
        ArrayList habitationlist = new ArrayList();
        try {
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            Iterator itr = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                //ds = JNDIDataSource.getConnection();
            }
            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            ArrayList vifWiseDetails = new ArrayList();
            ArrayList empWiseSingleDetails = new ArrayList();

            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            ArrayList districtList = new ArrayList();
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }

            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }


            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }


            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {

                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                vifWiseDetails = funDao.getViFunctionalneedsCount(ds, districtId, mandalId, villageId, fdate, tdate);
                if (vifWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {

                    request.setAttribute("vifWiseDetails", vifWiseDetails);

                }
            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String vi = request.getParameter("villagesId");
                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }

                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                vifWiseDetails = funDao.getViFunctionalneedsCount(ds, dist, mandal, village, fdate, tdate);

                if (vifWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("vifWiseDetails", vifWiseDetails);

                }
                target = "excel";

            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                vifWiseDetails = funDao.getViFunctionalneedsCount(ds, dist, mandal, village, fdate, tdate);
                if (vifWiseDetails.size() == 0) {

                    request.setAttribute("msg", "No Data Found");
                } else {

                    request.setAttribute("vifWiseDetails", vifWiseDetails);
                }
                target = "print";
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }
                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                String co = columns, type = null;
                type = (String) request.getParameter("type");
                co = co.replace("'", "%27");
                co = co.replace("(", "%28");
                co = co.replace(")", "%29");
                co = co.replace("+", "%2B");
                co = co.replace(",", "%2C");
                request.setAttribute("tablee", subquery);
                request.setAttribute("colu", co);
                request.setAttribute("type", type);
                vifWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "2", subquery, columns);
                request.setAttribute("vifDetails", vifWiseDetails);
                target = "personal";
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                vifWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "2", subquery, columns);
                request.setAttribute("vifDetails", vifWiseDetails);
                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //target="success";
        return mapping.findForward(target);
    }

    public ActionForward HIFunctiionalneedReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String ur = "/HIFunctiionalneedReport.do?HIFunctiionalneedReport=HIFunctiionalneedReport";
        request.setAttribute("ur", ur);
        String target = "success";
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String rationcard = null, agee = null;
        DataSource ds = null;

        int cou = 1;
        String edu = null, em = null, ma = null, caste = null, religion = null, disability = null, gender = null, total = null, pmarige = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        FunctionalReportDAO funDao = new FunctionalReportDAO();
        ArrayList habitationlist = new ArrayList();
        String subquery = null, columns = null;
        try {
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            Iterator itr = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                //ds = JNDIDataSource.getConnection();
            }
            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            ArrayList hifWiseDetails = new ArrayList();
            ArrayList empWiseSingleDetails = new ArrayList();

            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            ArrayList districtList = new ArrayList();
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }
            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }
            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {

                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                hifWiseDetails = funDao.getHiFunctionalneedsCount(ds, districtId, mandalId, villageId, fdate, tdate);
                if (hifWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("hifWiseDetails", hifWiseDetails);
                }
            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String vi = request.getParameter("villagesId");
                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                hifWiseDetails = funDao.getHiFunctionalneedsCount(ds, dist, mandal, village, fdate, tdate);
                if (hifWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    request.setAttribute("hifWiseDetails", hifWiseDetails);
                }
                target = "excel";
            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                hifWiseDetails = funDao.getHiFunctionalneedsCount(ds, dist, mandal, village, fdate, tdate);
                if (hifWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("hifWiseDetails", hifWiseDetails);
                }
                target = "print";
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }
                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                String co = columns, type = null;
                type = (String) request.getParameter("type");
                request.setAttribute("type", type);
                co = co.replace("'", "%27");
                co = co.replace("(", "%28");
                co = co.replace(")", "%29");
                co = co.replace("+", "%2B");
                co = co.replace(",", "%2C");
                request.setAttribute("tablee", subquery);
                request.setAttribute("colu", co);
                hifWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "3", subquery, columns);
                request.setAttribute("hifDetails", hifWiseDetails);
                if (hifWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data ");
                }
                target = "personal";
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }
                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                hifWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "3", subquery, columns);
                request.setAttribute("hifDetails", hifWiseDetails);
                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward MRFunctiionalneedReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String ur = "/MRFunctiionalneedReport.do?MRFunctiionalneedReport=MRFunctiionalneedReport";
        request.setAttribute("ur", ur);
        String target = "success";
        String subquery = null, columns = null;
        String colll = null;
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String rationcard = null, agee = null;
        DataSource ds = null;

        int cou = 1;
        String edu = null, em = null, ma = null, caste = null, religion = null, disability = null, gender = null, total = null, pmarige = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        FunctionalReportDAO funDao = new FunctionalReportDAO();
        ArrayList habitationlist = new ArrayList();
        try {
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            Iterator itr = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            ArrayList mrfWiseDetails = new ArrayList();
            ArrayList empWiseSingleDetails = new ArrayList();
            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            ArrayList districtList = new ArrayList();
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }
            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), functionalNeedForm.getUrban_id());
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }
            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }
            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                mrfWiseDetails = funDao.getMrFunctionalneedsCount(ds, districtId, mandalId, villageId, fdate, tdate);
                if (mrfWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");
                } else {
                    request.setAttribute("mrfWiseDetails", mrfWiseDetails);
                }

            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String vi = request.getParameter("villagesId");
                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                mrfWiseDetails = funDao.getMrFunctionalneedsCount(ds, dist, mandal, village, fdate, tdate);
                if (mrfWiseDetails.size() == 0) {

                    request.setAttribute("msg", "No Data Found");
                } else {

                    request.setAttribute("mrfWiseDetails", mrfWiseDetails);
                }
                target = "excel";
            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                mrfWiseDetails = funDao.getMrFunctionalneedsCount(ds, dist, mandal, village, fdate, tdate);
                if (mrfWiseDetails.size() == 0) {

                    request.setAttribute("msg", "No Data Found");

                } else {

                    request.setAttribute("mrfWiseDetails", mrfWiseDetails);
                }
                target = "print";
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }

                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                String co = columns, type = null;
                type = (String) request.getParameter("type");
                request.setAttribute("type", type);
                co = co.replace("'", "%27");
                co = co.replace("(", "%28");
                co = co.replace(")", "%29");
                co = co.replace("+", "%2B");
                co = co.replace(",", "%2C");
                request.setAttribute("tablee", subquery);
                request.setAttribute("colu", co);
                mrfWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "4", subquery, columns);
                request.setAttribute("mrfDetails", mrfWiseDetails);
                if (mrfWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data ");
                }
                target = "personal";
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }
                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);
                subquery = (String) request.getParameter("tablee");
                columns = (String) request.getParameter("colu");
                mrfWiseDetails = dao.getPersonalDetailsAll(ds, dist, mandal, village, hab, fdate, tdate, "4", subquery, columns);
                request.setAttribute("mrfDetails", mrfWiseDetails);
                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //target="success";
        return mapping.findForward(target);
    }
}
