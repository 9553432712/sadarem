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
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Constants.CommonConstants;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.FunctionalNeedReportDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.FunctionalNeedReportDTO;
import org.bf.disability.form.FunctionalNeedReportForm;


import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 484898
 */
public class EducationWiseReportAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "success";
        String districtName = null;
        String mandalName = null;
        String villageName = null;
        String habitationName = null;
        String qualification = null;
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        DataSource ds = null;
        ArrayList mandallist = new ArrayList();
        ArrayList habitationlist = new ArrayList();
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        HttpSession session = request.getSession();
        FunctionalNeedReportService functionalNeedService =
                FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");
            String urbanid = (String) request.getParameter("urban_id");
            //  DemographyDAO dao = new DemographyDAO();
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
            functionalNeedForm.setDistrict_id(districtId);
            functionalNeedForm.setMandal_id(mandalId);
            functionalNeedForm.setVillage_id(villageId);
            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            functionalNeedForm.setUrban_id(urbanid);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = dateFormat.format(date);
           
            if (request.getParameter("mode") == null && request.getParameter("status") == null) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                FunctionalNeedReportDTO educationwiseDTO = new FunctionalNeedReportDTO();
                functionalNeedForm.setUrban_id("0");
                functionalNeedForm.setDistrict_id("0");
                functionalNeedForm.setFromdate("01/01/2010");
                functionalNeedForm.setTodate(currentDate);
                request.setAttribute("fromdate", functionalNeedForm.getFromdate());
                request.setAttribute("todate", functionalNeedForm.getTodate());
                BeanUtils.copyProperties(educationwiseDTO, functionalNeedForm);
                ArrayList<FunctionalNeedReportDTO> educationwiseList = null;
                educationwiseList = new ArrayList<FunctionalNeedReportDTO>();
                educationwiseList = functionalNeedService.getEducationWiseReport(ds, educationwiseDTO);
                if (educationwiseList.isEmpty()) {
                    request.setAttribute("msg", "No Records Found");
                    target = "educationwise";
                } else {
                    request.setAttribute("educationwiseList", educationwiseList);
                    session.setAttribute("educationwiseList1", educationwiseList);
                    target = "educationwise";
                }
                request.setAttribute("district", "district");
                session.setAttribute("ExcelHeaders", request.getAttribute("ExcelHeader"));
                String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ",AreaType:ALL,  ";
                name = message + name;
                request.setAttribute("names", name);
                session.setAttribute("names1", name);
                session.setAttribute("ExcelHeaders", "District");
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {
                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);
                districtName = request.getParameter("districtName");
                mandalName = request.getParameter("mandalName");
                villageName = request.getParameter("villageName");
                FunctionalNeedReportDTO educationwiseDTO = new FunctionalNeedReportDTO();
                BeanUtils.copyProperties(educationwiseDTO, functionalNeedForm);
                try {
                    ArrayList<FunctionalNeedReportDTO> educationwiseList = null;
                    educationwiseList = new ArrayList<FunctionalNeedReportDTO>();
                    educationwiseList = functionalNeedService.getEducationWiseReport(ds, educationwiseDTO);
                    if (educationwiseList.isEmpty()) {
                        request.setAttribute("msg", "No Records Found");
                        target = "educationwise";
                    } else {
                        request.setAttribute("educationwiseList", educationwiseList);
                        session.setAttribute("educationwiseList1", educationwiseList);
                        target = "educationwise";
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
                    session.setAttribute("ExcelHeaders", request.getAttribute("ExcelHeader"));

                    if (functionalNeedForm.getUrban_id().equals("0")) {
                        functionalNeedForm.setUrban_id("ALL");
                    }
                    String message = "As On: " + functionalNeedForm.getFromdate() + " To: " + functionalNeedForm.getTodate() + ",AreaType:" + functionalNeedForm.getUrban_id() + ", ";
                    name = message + name;
                    request.setAttribute("names", name);
                    session.setAttribute("names1", name);
                    if (request.getAttribute("returnType") != null && request.getAttribute("returnType").equals("Excel")) {
                        target = "excel";
                    } else if (request.getAttribute("returnType") != null && request.getAttribute("returnType").equals("Print")) {
                        target = "excel";
                    } else {
                        target = "success";
                    }

                } catch (SQLException sqlEx) {
                    target = "exception";
                    int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetails", "EducationWiseReportAction", "DataBase");
                    //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EducationWiseReportAction", "getDetails");
                } catch (Exception sqlEx) {
                    target = "exception";
                    int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetails", "EducationWiseReportAction", "Code");
                    //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EducationWiseReportAction", "getDetails");
                }
            } else if ("getEducationalDetails".equalsIgnoreCase(request.getParameter("status"))) {
                try {
                    ArrayList educationwiseList = null;
                    educationwiseList = new ArrayList();
                    ArrayList areaDetails = new ArrayList();
                    educationwiseList = functionalNeedService.getPersonalEducationWiseReport(ds, request.getParameter("dID").toString(), request.getParameter("mID").toString(), request.getParameter("vID").toString(), request.getParameter("hID").toString(), request.getParameter("education").toString(), request.getParameter("urbanId").toString());
                    areaDetails = functionalNeedService.getAreaDetails(ds, request.getParameter("dID").toString(), request.getParameter("mID").toString(), request.getParameter("vID").toString(), request.getParameter("hID").toString());
                    if (educationwiseList.isEmpty()) {
                        request.setAttribute("msg", "No Records Found");
                        request.setAttribute("msg", "No Records Found");
                        target = "educationwise1";
                        // target = "ReportException";
                        functionalNeedForm.setDistrict_id("0");
                        functionalNeedForm.setMandal_id("0");
                        functionalNeedForm.setVillage_id("0");
                    } else {
                        request.setAttribute("fromdate", functionalNeedForm.getFromdate());
                        request.setAttribute("todate", functionalNeedForm.getTodate());
                        request.setAttribute("educationwiseList", educationwiseList);
                        request.setAttribute("areaDetails", areaDetails);
                        target = "educationwise1";
                    }
                } catch (SQLException sqlEx) {
                    target = "exception";
                    int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEducationalDetails", "EducationWiseReportAction", "DataBase");
                    //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EducationWiseReportAction", "getEducationalDetails");
                } catch (Exception sqlEx) {
                    target = "exception";
                    int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEducationalDetails", "EducationWiseReportAction", "Code");
                    //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EducationWiseReportAction", "getEducationalDetails");
                }
            } else if ("getEduDetails".equalsIgnoreCase(request.getParameter("status"))) {
                ActionMessages actionMessages = null;


                try {
                    ArrayList educationwiseList = null;
                    educationwiseList = new ArrayList();
                    ArrayList areaDetails = new ArrayList();

                    educationwiseList = functionalNeedService.getPersonalEducationWiseReport(ds, request.getParameter("dID").toString(), request.getParameter("mID").toString(), request.getParameter("vID").toString(), request.getParameter("hID").toString(), request.getParameter("education").toString(), request.getParameter("urbanId").toString());
                    areaDetails = functionalNeedService.getAreaDetails(ds, request.getParameter("dID").toString(), request.getParameter("mID").toString(), request.getParameter("vID").toString(), request.getParameter("hID").toString());

                    if (educationwiseList.isEmpty()) {
                        request.setAttribute("msg", "No Records Found");
                        request.setAttribute("msg", "No Records Found");
                        target = "educationwise";
                        // target = "ReportException";
                        functionalNeedForm.setDistrict_id("0");
                        functionalNeedForm.setMandal_id("0");
                        functionalNeedForm.setVillage_id("0");
                    } else {
                        request.setAttribute("educationwiseList", educationwiseList);
                        request.setAttribute("areaDetails", areaDetails);
                        request.setAttribute("date", CommonUtility.getDateTime("dd/MM/yyyy"));
                        target = "excel";
                    }

                } catch (SQLException sqlEx) {
                    target = "exception";
                    int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEduDetails", "EducationWiseReportAction", "DataBase");
                    //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EducationWiseReportAction", "getEduDetails");
                } catch (Exception sqlEx) {
                    target = "exception";
                    int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getEduDetails", "EducationWiseReportAction", "Code");
                    //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EducationWiseReportAction", "getEduDetails");
                }//end of catch block

            } else if ("getEduDetailsExcel".equalsIgnoreCase(request.getParameter("status"))) {
                request.setAttribute("educationwiseList", session.getAttribute("educationwiseList1"));
                request.setAttribute("names", session.getAttribute("names1"));
                request.setAttribute("ExcelHeader", session.getAttribute("ExcelHeaders"));
                target = "EducationWiseReportExcel";

            } else if ("getEduDetailsPrint".equalsIgnoreCase(request.getParameter("status"))) {
                request.setAttribute("educationwiseList", session.getAttribute("educationwiseList1"));
                request.setAttribute("names", session.getAttribute("names1"));
                request.setAttribute("ExcelHeader", session.getAttribute("ExcelHeaders"));
                target = "EducationWiseReportPrintTile";

            }
            request.setAttribute("fromdate", functionalNeedForm.getFromdate());
            request.setAttribute("todate", functionalNeedForm.getTodate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
}
