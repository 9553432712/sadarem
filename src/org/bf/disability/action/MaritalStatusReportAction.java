/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.mail.Session;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.FunctionalNeedReportDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.FunctionalNeedReportDTO;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;

/**
 *
 * @author 484898
 */
public class MaritalStatusReportAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "success";
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String habCode = null;
        String urbanId = "";
        DataSource ds = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        ArrayList habitationlist = new ArrayList();
        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        FunctionalNeedReportDTO functionalNeedReportDTO = new FunctionalNeedReportDTO();
        ArrayList mandallist = new ArrayList();
        ArrayList maritalStatus = new ArrayList();
        ArrayList areaDetails = new ArrayList();
        ArrayList personalList = new ArrayList();
        HttpSession session = request.getSession();
        // Get District list and mandal List
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            TerritoryDAO territoryDAO = new TerritoryDAO();
            ArrayList districtsList = territoryDAO.getDistricts(ds);
            functionalNeedForm.setDistrictList(districtsList);
            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, functionalNeedForm.getDistrict_id(), functionalNeedForm.getUrban_id());
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

            if (request.getParameter("habId") == null) {
                habCode = "0";
            } else {
                habCode = request.getParameter("habId");
            }
            if (request.getParameter("mode") == null) {
                districtId = "0";
                Date date1 = new Date();
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                String currentDates = dateFormat1.format(date1);
                functionalNeedForm.setUrban_id("0");
                functionalNeedForm.setFromdate("01/01/2010");
                functionalNeedForm.setTodate(currentDates);

                request.setAttribute("fromdate", "01/01/2010");

                request.setAttribute("todate", currentDates);
                BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
                maritalStatus = functionalNeedService.getMaritalStatusDetails(ds, districtId, mandalId, villageId, habCode, functionalNeedReportDTO);
                areaDetails = functionalNeedService.getAreaDetails(ds, districtId, mandalId, villageId, "0");
                if (maritalStatus.size() > 0) {
                    request.setAttribute("maritalStatus", maritalStatus);
                    request.setAttribute("areaDetails", areaDetails);
                    target = "success";
                } else {
                    request.setAttribute("msg", "Details are Not Available!");
                    target = "failure";
                }
                if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Excel")) {
                    target = "excel";
                } else if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Print")) {
                    target = "print";
                } else {
                    target = "success";
                }
                request.setAttribute("ExcelHeader", "District");
                request.setAttribute("district", "district");
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ",AreaType:ALL,  ";
                name = message + name;
                request.setAttribute("names", name);
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {
                try {
                    BeanUtils.copyProperties(functionalNeedReportDTO, functionalNeedForm);
                    maritalStatus = functionalNeedService.getMaritalStatusDetails(ds, districtId, mandalId, villageId, habCode, functionalNeedReportDTO);
                    areaDetails = functionalNeedService.getAreaDetails(ds, districtId, mandalId, villageId, "0");
                    if (maritalStatus.size() > 0) {
                        request.setAttribute("maritalStatus", maritalStatus);
                        request.setAttribute("areaDetails", areaDetails);
                        target = "success";
                    } else {
                        request.setAttribute("msg", "Details are Not Available!");
                        target = "failure";
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
                     if (functionalNeedForm.getUrban_id().equals("0")) {
                        functionalNeedForm.setUrban_id("ALL");
                    }
                    String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                    String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ",AreaType:" + functionalNeedForm.getUrban_id() + ", ";
                    name = message + name;
                    request.setAttribute("names", name);
                     if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Excel")) {
                    target = "excel";
                } else if (request.getParameter("returnType") != null && request.getParameter("returnType").equals("Print")) {
                    target = "print";
                } else {
                    target = "success";
                }
                    request.setAttribute("fromdate", functionalNeedForm.getFromdate());
                    request.setAttribute("todate", functionalNeedForm.getTodate());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if ("getPersonalDetails".equalsIgnoreCase(request.getParameter("status"))) {

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

                if (request.getParameter("habId") == null) {
                    habCode = "0";
                } else {
                    habCode = request.getParameter("habId");
                }
                if (request.getParameter("urbanId") == null) {
                    urbanId = "0";
                } else {
                    urbanId = request.getParameter("urbanId");
                }
                String fromdate = null;
                String todate = null;

                if (request.getParameter("fromdate") != null) {
                    fromdate = request.getParameter("fromdate");

                  

                }

                if (request.getParameter("todate") != null) {
                    todate = request.getParameter("todate");


                }
                personalList = functionalNeedService.getMaritalStatusPersonalDetails(ds, districtId, mandalId, villageId, habCode, request.getParameter("mstatus").toString(), urbanId,fromdate,todate);
                areaDetails = functionalNeedService.getAreaDetails(ds, districtId, mandalId, villageId, habCode);

                request.setAttribute("areaDetails", areaDetails);
                if (personalList.size() > 0) {
                    request.setAttribute("maritalpersonalDetails", personalList);

                    target = "maritalSuccess";
                } else {
                    request.setAttribute("msg", "Details are Not Available!");
                    target = "failure";
                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "execute", "MaritalStatusReportAction", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MaritalStatusReportAction", "execute");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "execute", "MaritalStatusReportAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MaritalStatusReportAction", "execute");
        }
        return mapping.findForward(target);
    }
}
