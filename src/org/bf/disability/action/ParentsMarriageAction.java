/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.FunctionalNeedReportDAO;
import org.bf.disability.dto.FunctionalNeedReportDTO;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

/**
 *
 * @author 484898
 */
public class ParentsMarriageAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        String target = "success";
        DataSource ds = null;


        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String habCode = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            TerritoryService territoryService = TerritoryServiceFactory.getTerritoryServiceImpl();
            FunctionalNeedReportDTO functionalNeedReportDTO = new FunctionalNeedReportDTO();
            FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
            ArrayList districtList = new ArrayList();
            ArrayList mandallist = new ArrayList();
            ArrayList parentsMarriage = new ArrayList();
            ArrayList habitationlist = new ArrayList();
            ArrayList areaDetails = new ArrayList();
            ArrayList indivdualDetails = new ArrayList();

            // Get Districts
            districtList = territoryService.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                functionalNeedForm.setDistrictList(districtList);
            }

            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"), "");
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

            if (request.getParameter("habCode") == null) {
                habCode = "0";
            } else {
                habCode = request.getParameter("habCode");
            }
            Date date = new Date();
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = dateFormat1.format(date);
            if (request.getParameter("mode") == null && request.getParameter("status") == null) {
                functionalNeedForm.setFromdate("01/01/2010");
                functionalNeedForm.setTodate(currentDate);
                BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
                parentsMarriage = functionalNeedService.getParentsMaritalStatusDetails(ds, districtId, mandalId, villageId, habCode, functionalNeedReportDTO);
                areaDetails = functionalNeedService.getAreaDetails(ds, districtId, mandalId, villageId, "0");
                if (parentsMarriage.size() > 0) {
                    request.setAttribute("parentsMarriage", parentsMarriage);
                    request.setAttribute("areaDetails", areaDetails);
                } else {
                    request.setAttribute("msg", "Details are Not Available!");
                }
                request.setAttribute("ExcelHeader", "District");
                request.setAttribute("district", "district");
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ", ";
                name = message + name;
                request.setAttribute("names", name);
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {
                functionalNeedReportDTO.setFromdate(functionalNeedForm.getFromdate());
                functionalNeedReportDTO.setTodate(functionalNeedForm.getTodate());
                BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
                parentsMarriage = functionalNeedService.getParentsMaritalStatusDetails(ds, districtId, mandalId, villageId, habCode, functionalNeedReportDTO);
                areaDetails = functionalNeedService.getAreaDetails(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("areaDetails", areaDetails);
                if (parentsMarriage.size() > 0) {
                    request.setAttribute("parentsMarriage", parentsMarriage);

                } else {


                    request.setAttribute("msg", "Details are Not Available!");
                }
                if (districtId.equals("0") && mandalId.equals("0") && villageId.equals("0")) {
                    request.setAttribute("district", "district");
                    request.setAttribute("ExcelHeader", "District");
                } else if (!districtId.equals("0") && mandalId.equals("0") && villageId.equals("0")) {
                    request.setAttribute("mandal", "mandal");
                    request.setAttribute("ExcelHeader", "Mandal");
                } else if (!districtId.equals("0") && !mandalId.equals("0") && villageId.equals("0")) {
                    request.setAttribute("village", "village");
                    request.setAttribute("ExcelHeader", "Village");
                } else if (!districtId.equals("0") && !mandalId.equals("0") && !villageId.equals("0")) {
                    request.setAttribute("habitation", "habitation");
                    request.setAttribute("ExcelHeader", "Habitation");
                }
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ", ";
                name = message + name;
                request.setAttribute("names", name);
                if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Excel")) {
                    target = "excel";
                } else if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Print")) {
                    target = "print";
                } else {
                    target = "success";
                }

            } else if ("getPersonalDetails".equalsIgnoreCase(request.getParameter("status"))) {

                if (request.getParameter("dID") == null) {
                    districtId = "0";
                } else {
                    districtId = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null) {
                    mandalId = "0";
                } else {
                    mandalId = request.getParameter("mID");
                }
                if (request.getParameter("vID") == null) {
                    villageId = "0";
                } else {
                    villageId = request.getParameter("vID");
                }

                if (request.getParameter("habCode") == null) {
                    habCode = "0";
                } else {
                    habCode = request.getParameter("habCode");
                }

                String fromdate = null;
                String todate = null;

                if (request.getParameter("fDate") != null) {
                    fromdate = request.getParameter("fDate");
                }

                if (request.getParameter("toDate") != null) {
                    todate = request.getParameter("toDate");

                }

                String pId = null;
                if (request.getParameter("pId") != null) {
                    pId = request.getParameter("pId");

                    indivdualDetails = functionalNeedService.getParentsMaritalIndivdualDetails(ds, districtId, mandalId, villageId, habCode, pId, fromdate, todate);
                    areaDetails = functionalNeedService.getAreaDetails(ds, districtId, mandalId, villageId, habCode);
                    request.setAttribute("areaDetails", areaDetails);
                    request.setAttribute("parentsIndivdualDetails", indivdualDetails);
                    if (indivdualDetails.isEmpty()) {
                        request.setAttribute("parentsIndivdualDetails", indivdualDetails);
                        target = "success";
                    } else {
                        request.setAttribute("parentsIndivdualDetails", indivdualDetails);
                        target = "failure";
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        request.setAttribute("fromdate", functionalNeedForm.getFromdate());
        request.setAttribute("todate", functionalNeedForm.getTodate());
        return mapping.findForward(target);
    }
}
