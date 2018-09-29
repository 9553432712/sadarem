/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ClusterReportDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.form.ClusterReportForm;
import org.bf.disability.service.ClusterReportService;
import org.bf.disability.servicefactory.ClusterReportServiceFactory;

/**
 *
 * @author 747577
 */
public class ClusterReportAction extends DispatchAction {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ClusterReportForm clusterReportForm = (ClusterReportForm) form;
        DataSource ds = null;
        String url = request.getRequestURI();
        int n = url.lastIndexOf('/');
        if (n >= 0) {
            url = url.substring(n + 1);
        }
        String[] s = url.split(".do");

        ArrayList stateClusterReportList = new ArrayList();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ClusterReportService clusterReportService = ClusterReportServiceFactory.getClusterReportServiceImpl();
            //SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            if (s.length > 0 && (s[0].equals("District") || s[0].equals("AreaCluster") || s[0].equals("Mandal"))) {
                request.setAttribute("URL", s[0]);
                if (request.getParameter("district") != null) {
                    clusterReportForm.setDistrict_name(request.getParameter("district"));
                    ArrayList clusters = clusterReportService.getClusters(ds, clusterReportForm.getDistrict_name());
                    clusterReportForm.setClusterList(clusters);
                    if(request.getParameter("reportId")!=null){
                        request.setAttribute("reportId", request.getParameter("reportId"));
                    }
                    return mapping.findForward("clustersView");
                }
                if (clusterReportForm.getDistrict_name() != null) {

                    ArrayList clusters = clusterReportService.getClusters(ds, clusterReportForm.getDistrict_name());
                    clusterReportForm.setClusterList(clusters);
                }
                if (clusterReportForm.getCluster_name() != null) {

                    ArrayList mandals = clusterReportService.getMandals(ds, clusterReportForm.getCluster_name());
                    clusterReportForm.setMandalList(mandals);
                }
                ArrayList districtsList = clusterReportService.getDistricts(ds);
                clusterReportForm.setDistrictList(districtsList);

            } else {
                stateClusterReportList = clusterReportService.getStateClusterReportList(ds, clusterReportForm);
                request.setAttribute("stateClusterReportList", stateClusterReportList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);

    }

    public ActionForward getDistricts(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ClusterReportForm clusterReportForm = (ClusterReportForm) form;
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            clusterReportForm.setDistrict_name(null);
            unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getCluster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ClusterReportForm clusterReportForm = (ClusterReportForm) form;
        DataSource ds = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ClusterReportService clusterReportService = ClusterReportServiceFactory.getClusterReportServiceImpl();
            ArrayList clusterList = clusterReportService.getClusters(ds, clusterReportForm.getDistrict_name());
            clusterReportForm.setClusterList(clusterList);
            request.setAttribute("typeOfselection", clusterReportForm.getTypeOfSearch());
            request.setAttribute("reportSelection", clusterReportForm.getReportSelection());
            unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getMandals(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ClusterReportForm clusterReportForm = (ClusterReportForm) form;
        DataSource ds = null;
        request.setAttribute("typeOfselection", clusterReportForm.getTypeOfSearch());
        request.setAttribute("reportSelection", clusterReportForm.getReportSelection());
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ClusterReportService clusterReportService = ClusterReportServiceFactory.getClusterReportServiceImpl();
            ArrayList mandalList = clusterReportService.getMandals(ds, clusterReportForm.getCluster_name());
            clusterReportForm.setMandalList(mandalList);
            getCluster(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward stateWiseDistrictBreakup(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ClusterReportForm clusterReportForm = (ClusterReportForm) form;
        DataSource ds = null;
        int columnsCount = 0;
        ArrayList stateClusterReportList = new ArrayList();
        ArrayList columnNames = new ArrayList();
        String reportId = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("reportId") != null) {
                reportId = (String) request.getParameter("reportId");
            }

            ClusterReportService clusterReportService = ClusterReportServiceFactory.getClusterReportServiceImpl();
            columnsCount = clusterReportService.getStateWiseDistrictColumnCount(ds, reportId);
            columnNames = clusterReportService.getStateWiseDistrictColumnNames(ds, reportId, clusterReportForm);
            stateClusterReportList = clusterReportService.getStateWiseDistrictBreakup(ds, reportId, columnsCount);
            if (reportId != null && (reportId.equalsIgnoreCase("9")
                    || reportId.equalsIgnoreCase("15")
                    || reportId.equalsIgnoreCase("16")
                    || reportId.equalsIgnoreCase("17")
                    || reportId.equalsIgnoreCase("18") || reportId.equalsIgnoreCase("19")
                    || reportId.equalsIgnoreCase("20"))) {
                request.setAttribute("msg", "Data Yet to be Received");
            }
            request.setAttribute("stateClusterReportList", stateClusterReportList);
            request.setAttribute("columnNames", columnNames);
            request.setAttribute("columnsCount", columnsCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("districtBreakup");
    }

    public ActionForward districtClusterReportList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ClusterReportForm clusterReportForm = (ClusterReportForm) form;
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            ClusterReportService clusterReportService = ClusterReportServiceFactory.getClusterReportServiceImpl();
            ArrayList stateClusterReportList = clusterReportService.districtClusterReportList(ds, clusterReportForm);
            request.setAttribute("districtClusterReportList", stateClusterReportList);
//                 TerritoryDAO territoryDAO = new TerritoryDAO();
//                 ArrayList districtsList = territoryDAO.getDistricts(ds);
            ArrayList districtsList = clusterReportService.getDistricts(ds);
            clusterReportForm.setDistrictList(districtsList);
            request.setAttribute("districtName", clusterReportForm.getDistrict_name());
            if (clusterReportForm.getCluster_name() != null) {
                request.setAttribute("clusterName", clusterReportForm.getCluster_name());
            }
            if (clusterReportForm.getMandal_name() != null) {
                request.setAttribute("mandalName", " " + clusterReportForm.getMandal_name() + " Mandal ");
            }
            //clusterReportService.getDistrictClusterReportList(ds, clusterReportForm);
            unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward districtWiseDistrictBreakup(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ClusterReportForm clusterReportForm = (ClusterReportForm) form;
        DataSource ds = null;
        int columnsCount = 0;
        ArrayList breakUpList = new ArrayList();
        ArrayList columnNames = new ArrayList();
        String reportId = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("reportId") != null) {
                reportId = (String) request.getParameter("reportId");
            }

            ClusterReportService clusterReportService = ClusterReportServiceFactory.getClusterReportServiceImpl();

            if (request.getParameter("district") != null) {
                clusterReportForm.setDistrict_name(request.getParameter("district"));
            }
            if (request.getParameter("cluster") != null && !request.getParameter("cluster").equals("")) {
                clusterReportForm.setCluster_name(request.getParameter("cluster"));
            }
            columnsCount = clusterReportService.getStateWiseDistrictColumnCount(ds, reportId);
            columnNames = clusterReportService.getStateWiseDistrictColumnNames(ds, reportId, clusterReportForm);
            breakUpList = clusterReportService.getBreakupList(ds, reportId, columnsCount, clusterReportForm);
            if (reportId != null && (reportId.equalsIgnoreCase("9")
                    || reportId.equalsIgnoreCase("15")
                    || reportId.equalsIgnoreCase("16")
                    || reportId.equalsIgnoreCase("17")
                    || reportId.equalsIgnoreCase("18") || reportId.equalsIgnoreCase("19")
                    || reportId.equalsIgnoreCase("20"))) {
                request.setAttribute("msg", "Data Yet to be Received");
            }
            request.setAttribute("districtClusterReportList", breakUpList);
            request.setAttribute("columnNames", columnNames);
            request.setAttribute("columnsCount", columnsCount);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("districtBreakup");
    }
//    public ActionForward getClusterReport(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        ClusterReportForm clusterReportForm = (ClusterReportForm) form;
//        DataSource ds = null;
//
//        ArrayList clusterReportList1 = new ArrayList();
//        ArrayList clusterReportList2 = new ArrayList();
//        ArrayList clusterReportList3 = new ArrayList();
//        ArrayList clusterReportList4 = new ArrayList();
//        ArrayList clusterReportList5 = new ArrayList();
//        ArrayList clusterReportList6 = new ArrayList();
//        ArrayList clusterReportList7 = new ArrayList();
//        ArrayList clusterReportList8 = new ArrayList();
//        ArrayList clusterReportList9 = new ArrayList();
//        ArrayList clusterReportList10 = new ArrayList();
//        ArrayList clusterReportList11 = new ArrayList();
//        ArrayList clusterReportList12 = new ArrayList();
//        ArrayList clusterReportList13 = new ArrayList();
//        ArrayList clusterReportList14 = new ArrayList();
//
//
//
//        String reportSelection = null;
//        String typeOfselection = null;
//        try {
//            ds = getDataSource(request);
//            if (ds == null || "null".equals(ds)) {
//                ds = JNDIDataSource.getConnection();
//            }
//            request.setAttribute("typeOfselection", clusterReportForm.getTypeOfSearch());
//            request.setAttribute("reportSelection", clusterReportForm.getReportSelection());
//
//            ClusterReportService clusterReportService = ClusterReportServiceFactory.getClusterReportServiceImpl();
//
//            ArrayList reportList = clusterReportService.getReportTypes(ds);
//
////            if(clusterReportForm.getReportType().equalsIgnoreCase("ALL")){
////                for (int i=0;i>reportList.size();i++){
////                    HashMap dist = new HashMap();
////                    dist = (HashMap) reportList.get(i);
////                    String reportid = dist.get("reportid").toString();
////
////                     ArrayList clusterReportList.concat(reportid) = new ArrayList();
////                }
////            }
//
//            reportSelection = clusterReportForm.getReportSelection();
//            typeOfselection = clusterReportForm.getTypeOfSearch();
//            if (clusterReportForm.getReportType() != null && reportSelection != null) {
//                if (reportSelection.equalsIgnoreCase("cluster") || reportSelection.equalsIgnoreCase("mandal")) {
//                    if ((typeOfselection != null && (reportSelection.equalsIgnoreCase("cluster") && typeOfselection.equalsIgnoreCase("mandalBreakup"))) || reportSelection.equalsIgnoreCase("mandal")) {
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("1") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList1 = clusterReportService.getClusterReport(ds, clusterReportForm, "1");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("2") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList2 = clusterReportService.getClusterReport(ds, clusterReportForm, "2");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("3") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList3 = clusterReportService.getClusterReport(ds, clusterReportForm, "3");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("4") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList4 = clusterReportService.getClusterReport(ds, clusterReportForm, "4");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("5") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList5 = clusterReportService.getClusterReport(ds, clusterReportForm, "5");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("6") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList6 = clusterReportService.getClusterReport(ds, clusterReportForm, "6");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("7") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList7 = clusterReportService.getClusterReport(ds, clusterReportForm, "7");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("8") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList8 = clusterReportService.getClusterReport(ds, clusterReportForm, "8");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("9") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList9 = clusterReportService.getClusterReport(ds, clusterReportForm, "9");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("10") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList10 = clusterReportService.getClusterReport(ds, clusterReportForm, "10");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("11") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList11 = clusterReportService.getClusterReport(ds, clusterReportForm, "11");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("12") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList12 = clusterReportService.getClusterReport(ds, clusterReportForm, "12");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("13") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList13 = clusterReportService.getClusterReport(ds, clusterReportForm, "13");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("14") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList14 = clusterReportService.getClusterReport(ds, clusterReportForm, "14");
//                        }
//                    } else if (typeOfselection != null && reportSelection.equalsIgnoreCase("cluster") && typeOfselection.equalsIgnoreCase("clusterBreakup")) {
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("1") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList1 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "1");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("2") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList2 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "2");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("3") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList3 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "3");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("4") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList4 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "4");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("5") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList5 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "5");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("6") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList6 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "6");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("7") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList7 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "7");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("8") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList8 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "8");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("9") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList9 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "9");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("10") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList10 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "10");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("11") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList11 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "11");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("12") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList12 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "12");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("13") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList13 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "13");
//                        }
//                        if (clusterReportForm.getReportType().equalsIgnoreCase("14") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                            clusterReportList14 = clusterReportService.getClusterConfirmationNoReport(ds, clusterReportForm, "14");
//                        }
//                    }
//                } else if (reportSelection.equalsIgnoreCase("district")) {
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("1") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList1 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "1");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("2") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList2 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "2");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("3") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList3 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "3");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("4") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList4 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "4");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("5") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList5 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "5");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("6") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList6 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "6");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("7") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList7 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "7");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("8") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList8 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "8");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("9") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList9 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "9");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("10") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList10 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "10");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("11") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList11 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "11");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("12") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList12 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "12");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("13") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList13 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "13");
//                    }
//                    if (clusterReportForm.getReportType().equalsIgnoreCase("14") || clusterReportForm.getReportType().equalsIgnoreCase("ALL")) {
//                        clusterReportList14 = clusterReportService.getDistrictClusterReport(ds, clusterReportForm, "14");
//                    }
//                }
//            }
//            request.setAttribute("mName", clusterReportForm.getMandal_name());
//            request.setAttribute("dName", clusterReportForm.getDistrict_name());
//            request.setAttribute("cName", clusterReportForm.getCluster_name());
//
//
//            if (clusterReportList1.size() > 0) {
//                request.setAttribute("clusterReportListOne", clusterReportList1);
//            }
//            if (clusterReportList2.size() > 0) {
//                request.setAttribute("clusterReportListTwo", clusterReportList2);
//            }
//            if (clusterReportList3.size() > 0) {
//                request.setAttribute("clusterReportListThree", clusterReportList3);
//            }
//            if (clusterReportList4.size() > 0) {
//                request.setAttribute("clusterReportListFour", clusterReportList4);
//            }
//            if (clusterReportList5.size() > 0) {
//                request.setAttribute("clusterReportListFive", clusterReportList5);
//            }
//            if (clusterReportList6.size() > 0) {
//                request.setAttribute("clusterReportListSix", clusterReportList6);
//            }
//            if (clusterReportList7.size() > 0) {
//                request.setAttribute("clusterReportListSeven", clusterReportList7);
//            }
//            if (clusterReportList8.size() > 0) {
//                request.setAttribute("clusterReportListEight", clusterReportList8);
//            }
//            if (clusterReportList9.size() > 0) {
//                request.setAttribute("clusterReportListNine", clusterReportList9);
//            }
//            if (clusterReportList10.size() > 0) {
//                request.setAttribute("clusterReportListTen", clusterReportList10);
//            }
//            if (clusterReportList11.size() > 0) {
//                request.setAttribute("clusterReportListEleven", clusterReportList11);
//            }
//            if (clusterReportList12.size() > 0) {
//                request.setAttribute("clusterReportListTwelve", clusterReportList12);
//            }
//            if (clusterReportList13.size() > 0) {
//                request.setAttribute("clusterReportListThirteen", clusterReportList13);
//            }
//            if (clusterReportList14.size() > 0) {
//                request.setAttribute("clusterReportListFourteen", clusterReportList14);
//            }
//            if (clusterReportList1.size() == 0
//                    && clusterReportList2.size() == 0
//                    && clusterReportList3.size() == 0
//                    && clusterReportList4.size() == 0
//                    && clusterReportList5.size() == 0
//                    && clusterReportList6.size() == 0
//                    && clusterReportList7.size() == 0
//                    && clusterReportList8.size() == 0
//                    && clusterReportList9.size() == 0
//                    && clusterReportList10.size() == 0
//                    && clusterReportList11.size() == 0
//                    && clusterReportList12.size() == 0
//                    && clusterReportList13.size() == 0
//                    && clusterReportList14.size() == 0) {
//                request.setAttribute("nodata", "No Data Found!");
//            } else {
//                request.setAttribute("data", "data");
//            }
//
//            getCluster(mapping, form, request, response);
//            getMandals(mapping, form, request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (typeOfselection != null && reportSelection != null) {
//            if (reportSelection.equalsIgnoreCase("district")) {
//                return mapping.findForward("districtCluster");
//            } else if (reportSelection.equalsIgnoreCase("cluster") && typeOfselection.equalsIgnoreCase("clusterBreakup")) {
//                return mapping.findForward("confirmationNo");
//            }
//        }
//        return mapping.findForward(SUCCESS);
//    }
}
