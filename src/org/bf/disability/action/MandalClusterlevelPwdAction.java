/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

//import com.fourg.aponline.ws.SADAREMDBException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.MandalClusterlevelPwdForm;
import org.bf.disability.form.PercentageWiseReportForm;
import org.bf.disability.service.MandalClusterlevelPwdService;
import org.bf.disability.servicefactory.MandalClusterlevelPwdServiceFactory;
import com.lowagie.text.pdf.codec.Base64.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dao.CampDailyReportDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.service.CampDailyReportService;
import org.bf.disability.servicefactory.CampDailyReportServiceFactory;

/**

 * @author 842412
 */
public class MandalClusterlevelPwdAction extends DispatchAction {

    private static final String target = "success";
    DataSource ds = null;

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MandalClusterlevelPwdForm mandalClusterlevelPwdForm = (MandalClusterlevelPwdForm) form;
        CommonDAO commonDAO = new CommonDAO();
        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();
        CampDailyReportService campDailyReportService = CampDailyReportServiceFactory.getcampDailyReportServiceImpl();
        ArrayList disabilityList = null;
        ArrayList districtList = null;
        ArrayList campList = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            disabilityList = commonDAO.getDisabilitys(ds);
            if (mandalClusterlevelPwdForm.getDistrict() != null && mandalClusterlevelPwdForm.getDistrict().length() > 0 && !mandalClusterlevelPwdForm.getDistrict().equals("0")) {
                campList = campDailyReportService.getCampBasedOnLoginDetails(ds, mandalClusterlevelPwdForm.getDistrict());
                if (campList != null && campList.size() > 0) {

                    mandalClusterlevelPwdForm.setCampList(campList);
                }
                request.setAttribute("districtflag", "districtflag");
            }
            if (disabilityList != null && disabilityList.size() > 0) {
                mandalClusterlevelPwdForm.setDisabilityList(disabilityList);
                //request.setAttribute("disabilityList",disabilityList);
            }
            if (mandalClusterlevelPwdForm.getFromdate() == null || mandalClusterlevelPwdForm.getFromdate().equalsIgnoreCase("") || mandalClusterlevelPwdForm.getFromdate().equalsIgnoreCase("null")) {
                
                mandalClusterlevelPwdForm.setFromdate("06/10/2014");
            }
            TerritoryDAO territoryDAO = new TerritoryDAO();
            districtList = territoryDAO.getDistricts(ds);
            if (districtList.size() > 0) {
                mandalClusterlevelPwdForm.setDistrictlist(districtList);
            }
            String districtname=commonDAO.getDistrictNameById(ds, mandalClusterlevelPwdForm.getDistrictid());
            if(districtname!="null"&&districtname!=""){
            request.setAttribute("districtname", districtname);
            }
             if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch() != ""&&mandalClusterlevelPwdForm.getTypeOfSearch().equalsIgnoreCase("Disability Wise")) {
             String disabilityName=commonDAO.getDisabilityName(ds, mandalClusterlevelPwdForm.getDisabilityWise());
            if(disabilityName!="null"&&disabilityName!=""){
            request.setAttribute("disabilityName", disabilityName);
            }
             }

            
            

            if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch() != "") {
                mandalClusterlevelPwdForm.setDisabiltyId(mandalClusterlevelPwdForm.getDisabilityWise());
                request.setAttribute("typeOfSearchValue", mandalClusterlevelPwdForm.getTypeOfSearch());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    public ActionForward getPwdsPersonalCount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            MandalClusterlevelPwdService mandalClusterlevelPwdService = MandalClusterlevelPwdServiceFactory.getMandalClusterlevelPwdServiceImpl();
            //MandalClusterlevelPwdDAO mandalClusterlevelPwdDAO = new MandalClusterlevelPwdDAO();
            MandalClusterlevelPwdForm mandalClusterlevelPwdForm = (MandalClusterlevelPwdForm) form;
            ArrayList pwdsPersonalCount = null;
            ArrayList pwdPensionData = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("districtid") != null && request.getParameter("districtid") != ""&&!request.getParameter("districtid").equalsIgnoreCase("All")) {
                mandalClusterlevelPwdForm.setDistrict_id(request.getParameter("districtid"));
            }
//            if (mandalClusterlevelPwdForm.getDisabilityWise() != null && mandalClusterlevelPwdForm.getDisabilityWise() != "") {
//                request.setAttribute("disabilityWiseID", mandalClusterlevelPwdForm.getDisabilityWise());
//
//            }
            if (request.getParameter("mandalid") != null && request.getParameter("mandalid") != "") {
                mandalClusterlevelPwdForm.setMandal_id(request.getParameter("mandalid"));
            }
            if (request.getParameter("villageid") != null && request.getParameter("villageid") != "") {
                mandalClusterlevelPwdForm.setVillage_id(request.getParameter("villageid"));
            }
            if (request.getParameter("districtName") != null && request.getParameter("districtName") != "") {
                mandalClusterlevelPwdForm.setDistrictName(request.getParameter("districtName"));
            }
            if (request.getParameter("mandalName") != null && request.getParameter("mandalName") != "") {
                mandalClusterlevelPwdForm.setMandalName(request.getParameter("mandalName"));
            }
            if (request.getParameter("villageName") != null && request.getParameter("villageName") != "") {
                mandalClusterlevelPwdForm.setVillageName(request.getParameter("villageName"));
            }
//            if (request.getParameter("districtName") != null && request.getParameter("districtName") != "") {
//                mandalClusterlevelPwdForm.setDistrictName(request.getParameter("districtName"));
//            }
            if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
                request.setAttribute("habitation", "habitation");
            } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
                request.setAttribute("village", "village");
            } else if (mandalClusterlevelPwdForm.getDistrict_id() != null && mandalClusterlevelPwdForm.getDistrict_id() != "") {
                request.setAttribute("mandal", "mandal");
            } else {
                request.setAttribute("district", "district");
            }
            if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch() != "") {
                mandalClusterlevelPwdForm.setDisabiltyId(mandalClusterlevelPwdForm.getDisabilityWise());
                request.setAttribute("typeOfSearchValue", mandalClusterlevelPwdForm.getTypeOfSearch());
            }
            if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("District Wise")) {
                request.setAttribute("districtWise", "districtWise");
            } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Disability Wise")) {
                if (mandalClusterlevelPwdForm.getDistrictid().equalsIgnoreCase("All") && mandalClusterlevelPwdForm.getDisabilityWise().equalsIgnoreCase("All")) {
                    request.setAttribute("ALL", "ALL");
                }else if (mandalClusterlevelPwdForm.getDistrictid().equalsIgnoreCase("All") && !mandalClusterlevelPwdForm.getDisabilityWise().equalsIgnoreCase("All")) {
                request.setAttribute("Districtall", "Districtall");
                }else if (!mandalClusterlevelPwdForm.getDistrictid().equalsIgnoreCase("All") && mandalClusterlevelPwdForm.getDisabilityWise().equalsIgnoreCase("All")) {
                request.setAttribute("Disabilityall", "Disabilityall");
                }else{
                    request.setAttribute("Notall", "Notall");
                }
                if (request.getParameter("district_ID") != null && request.getParameter("district_ID") != "") {
                    
                    mandalClusterlevelPwdForm.setDistrictid(request.getParameter("district_ID"));
                }
                request.setAttribute("disabilityWise", "disabilityWise");
            } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Medicalboard Wise")) {
                request.setAttribute("medicalboardWise", "medicalboardWise");
            } else {
                request.setAttribute("dateWise", "dateWise");
            }
            if (request.getParameter("typeOfSearch") != null && request.getParameter("typeOfSearch") != "") {
                mandalClusterlevelPwdForm.setTypeOfSearch(request.getParameter("typeOfSearch"));
            }
           if (request.getParameter("disabilityid") != null && request.getParameter("disabilityid") != "") {
                mandalClusterlevelPwdForm.setDisabilityWise(request.getParameter("disabilityid"));
            }
            pwdsPersonalCount = mandalClusterlevelPwdService.getPwdsPersonalCount(mandalClusterlevelPwdForm, ds);
            if (pwdsPersonalCount != null && pwdsPersonalCount.size() > 0) {
                request.setAttribute("pwdsPersonalCount", pwdsPersonalCount);
            } else {
                request.setAttribute("msg", "PwD's  Details are not available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(target);
    }

    public ActionForward getPwdsIndividualCount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String target = "individualDetails";
        MandalClusterlevelPwdService mandalClusterlevelPwdService = MandalClusterlevelPwdServiceFactory.getMandalClusterlevelPwdServiceImpl();
        //MandalClusterlevelPwdDAO mandalClusterlevelPwdDAO = new MandalClusterlevelPwdDAO();
        MandalClusterlevelPwdForm mandalClusterlevelPwdForm = (MandalClusterlevelPwdForm) form;
        ArrayList pwdsIndividualCount = null;
        ArrayList pwdPensionData = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("districtid") != null && request.getParameter("districtid") != "") {
                mandalClusterlevelPwdForm.setDistrict_id(request.getParameter("districtid"));
            }
            if (request.getParameter("disabilityname") != null && request.getParameter("disabilityname") != "") {
                mandalClusterlevelPwdForm.setDisabilityName(request.getParameter("disabilityname"));
            }
            if (request.getParameter("mandalid") != null && request.getParameter("mandalid") != "") {
                mandalClusterlevelPwdForm.setMandal_id(request.getParameter("mandalid"));
            }
            if (request.getParameter("villageid") != null && request.getParameter("villageid") != "") {
                mandalClusterlevelPwdForm.setVillage_id(request.getParameter("villageid"));
            }
            if (request.getParameter("habitationid") != null && request.getParameter("habitationid") != "") {
                mandalClusterlevelPwdForm.setHabitation_id(request.getParameter("habitationid"));
            }
            if (request.getParameter("districtName") != null && request.getParameter("districtName") != "") {
                mandalClusterlevelPwdForm.setDistrictName(request.getParameter("districtName"));
            }
            if (request.getParameter("mandalName") != null && request.getParameter("mandalName") != "") {
                mandalClusterlevelPwdForm.setMandalName(request.getParameter("mandalName"));
            }
            if (request.getParameter("villageName") != null && request.getParameter("villageName") != "") {
                mandalClusterlevelPwdForm.setVillageName(request.getParameter("villageName"));
            }
            if (request.getParameter("habitationName") != null && request.getParameter("habitationName") != "") {
                mandalClusterlevelPwdForm.setHabitationName(request.getParameter("habitationName"));
            }
//            if (request.getParameter("districtName") != null && request.getParameter("districtName") != "") {
//                mandalClusterlevelPwdForm.setDistrictName(request.getParameter("districtName"));
//            }
            if (mandalClusterlevelPwdForm.getHabitation_id() != null && mandalClusterlevelPwdForm.getHabitation_id() != "") {
                request.setAttribute("habitation", "habitation");
            } else if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
                request.setAttribute("village", "village");
            } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
                request.setAttribute("mandal", "mandal");
            } else if (mandalClusterlevelPwdForm.getDistrict_id() != null && mandalClusterlevelPwdForm.getDistrict_id() != "") {
                request.setAttribute("district", "district");
            }
            if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Medicalboard Wise")) {
                request.setAttribute("medicalboardWise", "medicalboardWise");
                if (request.getParameter("districtID") != null && request.getParameter("districtID") != "") {
                    mandalClusterlevelPwdForm.setDistrict(request.getParameter("districtID"));
                }
                if (request.getParameter("campid") != null && request.getParameter("campid") != "") {
                    mandalClusterlevelPwdForm.setCampId(request.getParameter("campid"));
                }
            } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("District Wise")) {
                request.setAttribute("districtWise", "districtWise");
            } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Disability Wise")) {
                request.setAttribute("disabilityWise", "disabilityWise");
                if (request.getParameter("district_ID") != null && request.getParameter("district_ID") != "") {
                    mandalClusterlevelPwdForm.setDistrictid(request.getParameter("district_ID"));
                }
            } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
                request.setAttribute("dateWise", "dateWise");
                if (request.getParameter("district_ID") != null && request.getParameter("district_ID") != "") {
                    mandalClusterlevelPwdForm.setDistrictID(request.getParameter("district_ID"));
                }
                if (request.getParameter("campDate") != null && request.getParameter("campDate") != "") {
                    mandalClusterlevelPwdForm.setCampDate(request.getParameter("campDate"));
                }

            }
            //if (request.getParameter("individual") != null && request.getParameter("districtName") != "") {
//            if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("District Wise")) {
//            pwdsIndividualCount = mandalClusterlevelPwdService.getPwdsIndividualCount(mandalClusterlevelPwdForm, ds);
//            }
            if (request.getParameter("typeOfSearch") != null && request.getParameter("typeOfSearch") != "") {
                mandalClusterlevelPwdForm.setTypeOfSearch(request.getParameter("typeOfSearch"));
            }
            if (request.getParameter("disabilityid") != null && request.getParameter("disabilityid") != "") {
                mandalClusterlevelPwdForm.setDisabiltyId(request.getParameter("disabilityid"));
            }
            pwdsIndividualCount = mandalClusterlevelPwdService.getPwdsIndividualCount(mandalClusterlevelPwdForm, ds);
            if (pwdsIndividualCount != null && pwdsIndividualCount.size() > 0) {
                request.setAttribute("pwdsIndividualCount", pwdsIndividualCount);
            } else {
                request.setAttribute("msg", "Mandal,Cluster level Pwd Individual Details are not available");
            }
            //individualDetails(pwdsIndividualCount, form, request, response);
            // }

//            pwdPensionData = mandalClusterlevelPwdService.getPwdsPensionDetails(ds);
//            if(pwdPensionData!=null && pwdPensionData.size()>0){
//                request.setAttribute("pwdPensionData", pwdPensionData);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward excelWriting(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws Exception, SQLException, Exception {
        ArrayList pensionData = null;
        ArrayList pwdsPersonalCount = null;
        ArrayList pwdsIndividualCount = null;
        MandalClusterlevelPwdService mandalClusterlevelPwdService = MandalClusterlevelPwdServiceFactory.getMandalClusterlevelPwdServiceImpl();
        MandalClusterlevelPwdForm mandalClusterlevelPwdForm = (MandalClusterlevelPwdForm) form;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
//        if(request.getParameter("individualDetails").equals("individualDetails")){
//        }else{
        if (request.getParameter("districtid") != null && request.getParameter("districtid") != "") {
            mandalClusterlevelPwdForm.setDistrict_id(request.getParameter("districtid"));
        }
        if (request.getParameter("campDate") != null && request.getParameter("campDate") != "") {
            mandalClusterlevelPwdForm.setCampDate(request.getParameter("campDate"));
        }
        if (request.getParameter("mandalid") != null && request.getParameter("mandalid") != "") {
            mandalClusterlevelPwdForm.setMandal_id(request.getParameter("mandalid"));
        }
        if (request.getParameter("villageid") != null && request.getParameter("villageid") != "") {
            mandalClusterlevelPwdForm.setVillage_id(request.getParameter("villageid"));
        }
        if (request.getParameter("habitationid") != null && request.getParameter("habitationid") != "") {
            mandalClusterlevelPwdForm.setHabitation_id(request.getParameter("habitationid"));
        }
        if (request.getParameter("districtName") != null && request.getParameter("districtName") != "") {
            mandalClusterlevelPwdForm.setDistrictName(request.getParameter("districtName"));
        }
        if (request.getParameter("districtName") != null && request.getParameter("districtName") != "") {
            mandalClusterlevelPwdForm.setDistrictName(request.getParameter("districtName"));
        }
        if (request.getParameter("mandalName") != null && request.getParameter("mandalName") != "") {
            mandalClusterlevelPwdForm.setMandalName(request.getParameter("mandalName"));
        }
        if (request.getParameter("villageName") != null && request.getParameter("villageName") != "") {
            mandalClusterlevelPwdForm.setVillageName(request.getParameter("villageName"));
        }
        if (request.getParameter("typeOfSearch") != null && request.getParameter("typeOfSearch") != "") {
            mandalClusterlevelPwdForm.setTypeOfSearch(request.getParameter("typeOfSearch"));
        }
        if (request.getParameter("disabilityid") != null && request.getParameter("disabilityid") != "") {
            mandalClusterlevelPwdForm.setDisabilityWise(request.getParameter("disabilityid"));
        }
        if (request.getParameter("habitationName") != null && request.getParameter("habitationName") != "") {
            mandalClusterlevelPwdForm.setHabitationName(request.getParameter("habitationName"));
        }
        if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
            request.setAttribute("habitation", "habitation");
        } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
            request.setAttribute("village", "village");
        } else if (mandalClusterlevelPwdForm.getDistrict_id() != null && mandalClusterlevelPwdForm.getDistrict_id() != "") {
            request.setAttribute("mandal", "mandal");
        } else {
            request.setAttribute("district", "district");
        }

        if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Disability Wise")) {

            if (request.getParameter("district_ID") != null && request.getParameter("district_ID") != "") {
                    mandalClusterlevelPwdForm.setDistrictid(request.getParameter("district_ID"));
                }
            mandalClusterlevelPwdForm.setDisabiltyId(mandalClusterlevelPwdForm.getDisabilityWise());
        }
        if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Medicalboard Wise")) {
            request.setAttribute("medicalboardWise", "medicalboardWise");
            if (request.getParameter("districtID") != null && request.getParameter("districtID") != "") {
                mandalClusterlevelPwdForm.setDistrict(request.getParameter("districtID"));
            }
            if (request.getParameter("campid") != null && request.getParameter("campid") != "") {
                mandalClusterlevelPwdForm.setCampId(request.getParameter("campid"));
            }
        } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
            if (request.getParameter("district_ID") != null && request.getParameter("district_ID") != "") {
                mandalClusterlevelPwdForm.setDistrictID(request.getParameter("district_ID"));
            }
        }

        if (request.getParameter("typeOfSearch") != null && request.getParameter("typeOfSearch") != "") {
            mandalClusterlevelPwdForm.setTypeOfSearch(request.getParameter("typeOfSearch"));
        }
        if (request.getParameter("disabilityid") != null && request.getParameter("disabilityid") != "") {
            mandalClusterlevelPwdForm.setDisabiltyId(request.getParameter("disabilityid"));
        }
        if (request.getParameter("individual") != null && request.getParameter("individual") != "") {
            if (request.getParameter("districtName") != null && request.getParameter("districtName") != "") {
                mandalClusterlevelPwdForm.setDistrictName(request.getParameter("districtName"));
            }
            pwdsIndividualCount = mandalClusterlevelPwdService.getPwdsIndividualCount(mandalClusterlevelPwdForm, ds);
            individualDetails(pwdsIndividualCount, form, request, response);
        } else {
            pwdsPersonalCount = mandalClusterlevelPwdService.getPwdsPersonalCount(mandalClusterlevelPwdForm, ds);
            exportExcel(pwdsPersonalCount, form, request, response);
        }

        //}
        return null;
    }

    public static void exportExcel(ArrayList dataList1, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        //PercentageWiseReportForm percentageWiseReportForm = (PercentageWiseReportForm) form;
        MandalClusterlevelPwdForm mandalClusterlevelPwdForm = (MandalClusterlevelPwdForm) form;
        String fromdate = (String) request.getParameter("fromdate");
        String todate = (String) request.getParameter("todate");

        String districtName = (String) request.getParameter("districtName");
        String mandalName = (String) request.getParameter("mandalName");
        String villageName = (String) request.getParameter("villageName");
        String habitationName = (String) request.getParameter("habitationName");
        String FinancialYearWise = (String) request.getParameter("FinancialYearWise");
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=20-39 Mandal,Cluster level Pwd Personal Details Abstract Report.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(bold);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat.setAlignment(Alignment.CENTRE);

            WritableCellFormat cell = new WritableCellFormat();
            WritableCellFormat cellleft = new WritableCellFormat();
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setAlignment(Alignment.CENTRE);
            cellleft.setAlignment(Alignment.LEFT);
            cell.setVerticalAlignment(VerticalAlignment.CENTRE);

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            if (dataList1.size() == 0) {
                s.addCell(new Label(0, 0, "Mandal,Cluster level Pwd Personal Details are not Available................"));
            } else {
                // s.mergeCells(3, 1, 7, 1);
                s.mergeCells(0, 0, 2, 0);
                s.addCell(new Label(0, 0, "Mandal,Cluster level Pwd Personal Details Abstract Report", cellFormat));
                s.mergeCells(0, 1, 2, 1);
                if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Medicalboard Wise")) {
                    s.addCell(new Label(0, 1, "District::" + districtName + "  " + "Camp::" + request.getParameter("campName") + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Disability Wise")) {
                    if (mandalClusterlevelPwdForm.getDistrictid().equalsIgnoreCase("All") && mandalClusterlevelPwdForm.getDisabilityWise().equalsIgnoreCase("All")) {
                    s.addCell(new Label(0, 1, "District::ALL"  +"Disability::ALL"  + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                }else if (mandalClusterlevelPwdForm.getDistrictid().equalsIgnoreCase("All") && !mandalClusterlevelPwdForm.getDisabilityWise().equalsIgnoreCase("All")) {
                    s.addCell(new Label(0, 1, "District::ALL"  +"Disability::"+ request.getParameter("disabilityname")  + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                }else if (!mandalClusterlevelPwdForm.getDistrictid().equalsIgnoreCase("All") && mandalClusterlevelPwdForm.getDisabilityWise().equalsIgnoreCase("All")) {
                    s.addCell(new Label(0, 1, "District::"+ request.getParameter("districtName")+"  "  +"Disability::ALL" + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                }else{
                    s.addCell(new Label(0, 1, "District::"+ request.getParameter("districtName")+"  "  +"Disability::"+ request.getParameter("disabilityname")  + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                }
                    //s.addCell(new Label(0, 1, "Disability::" + request.getParameter("disabilityname") + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
                    s.addCell(new Label(0, 1, "District::" + request.getParameter("districtName") + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                } else {
                    if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
                        s.addCell(new Label(0, 1, "District:" + districtName + "  " + "Mandal:" + mandalName + "  " + "Village:" + villageName + "  " + "Habitation:" + habitationName + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                    } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
                        s.addCell(new Label(0, 1, "District:" + districtName + "  " + "Mandal:" + mandalName + "  " + "Village:ALL" + "  " + "Habitation: ALL" + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                    } else if (mandalClusterlevelPwdForm.getDistrict_id() != null && mandalClusterlevelPwdForm.getDistrict_id() != "") {
                        s.addCell(new Label(0, 1, "District:" + districtName + "  " + "Mandal:ALL" + "  " + "Village:ALL" + "  " + "Habitation: ALL" + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                    } else {
                        s.addCell(new Label(0, 1, "District:ALL" + "  " + "  Mandal:ALL" + "  " + "  Village:ALL" + "Habitation: ALL" + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                    }
                }

                //s.addCell(new Label(0, 1, "rrrrrrrr", cellFormat));
                if (dataList1.size() > 0) {
//                    s.mergeCells(0, 0, 2, 0);
//                    s.addCell(new Label(0, 2, "kkkkkkkkkkk", cellFormat));
//                    s.mergeCells(0, 1, 2, 1);
//                    s.addCell(new Label(0, 2, "rrrrr", cellFormat));
                    headerList.add("S.No");
                    if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("District Wise")) {
                        if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
                            headerList.add("Habitation");
                        } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
                            headerList.add("Village");
                        } else if (mandalClusterlevelPwdForm.getDistrict_id() != null && mandalClusterlevelPwdForm.getDistrict_id() != "") {
                            headerList.add("Mandal");
                        } else {
                            headerList.add("District");
                        }
                    } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Medicalboard Wise")) {
                        headerList.add("Medical Board");
                    } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Disability Wise")) {
                        headerList.add("Disability");
                    } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
                        headerList.add("Camp Date");
                    }

                    headerList.add("Only Part-A(Doctor Not Assessed)");
                    addHeaders(s, headerList, cell, "dataList1");
                }


            }
            int x = 3;
            if (dataList1.size() > 0) {

                int k = 0;
                for (int i = 0; i < dataList1.size(); i++, x++) {
                    int j = 0;
                    k++;
                    Map m = (Map) dataList1.get(i);
                    s.addCell(new Label(j++, x, i + 1 + "", cell));
                    if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("District Wise")) {
                        if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
                            s.addCell(new Label(j++, x, m.get("habitationName").toString(), cellleft));
                        } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
                            s.addCell(new Label(j++, x, m.get("villageName").toString(), cellleft));
                        } else if (mandalClusterlevelPwdForm.getDistrict_id() != null && mandalClusterlevelPwdForm.getDistrict_id() != "") {
                            s.addCell(new Label(j++, x, m.get("mandalName").toString(), cellleft));
                        } else {
                            s.addCell(new Label(j++, x, m.get("districtName").toString(), cellleft));
                        }
                    } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Medicalboard Wise")) {
                        s.addCell(new Label(j++, x, m.get("campname").toString(), cellleft));
                    } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Disability Wise")) {
                        s.addCell(new Label(j++, x, m.get("disabilityName").toString(), cellleft));
                    } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
                        s.addCell(new Label(j++, x, m.get("campdate").toString(), cellleft));
                    }
//                    if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
//                        s.addCell(new Label(j++, x, m.get("datecount").toString(), cell));
//                    }
//                    if (!mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
                        s.addCell(new Label(j++, x, m.get("count").toString(), cell));
//                    }


                    if (i == (50000) * page) {
                        int sheet = ++page;
                        s = w.createSheet("Sheet" + sheet, sheet - 1);
                        j = 0;
                        x = 2;
                        addHeaders(s, headerList, cell, "dataList1");
                    }
                }
                Map m = (Map) dataList1.get(k - 1);
                s.mergeCells(0, x, 1, x);
                s.addCell(new Label(0, x, "Total", cellFormat));
//                if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
//                    s.addCell(new Label(2, x, m.get("toptaldatecount").toString(), cellFormat));
//                }
//                if (!mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
                    s.addCell(new Label(2, x, m.get("Totalcount").toString(), cellFormat));
//                }
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

    public static void individualDetails(ArrayList dataList1, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        //PercentageWiseReportForm percentageWiseReportForm = (PercentageWiseReportForm) form;
        MandalClusterlevelPwdForm mandalClusterlevelPwdForm = (MandalClusterlevelPwdForm) form;
        String fromdate = (String) request.getParameter("fromdate");
        String todate = (String) request.getParameter("todate");

        String districtName = (String) request.getParameter("districtName");
        String mandalName = (String) request.getParameter("mandalName");
        String villageName = (String) request.getParameter("villageName");
        String habitationName = (String) request.getParameter("habitationName");
        String FinancialYearWise = (String) request.getParameter("FinancialYearWise");
        try {
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=20-39 Mandal,Cluster level Pwd Personal Details Abstract Report.xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());
            ArrayList headerList = new ArrayList();
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(bold);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat.setAlignment(Alignment.CENTRE);

            WritableCellFormat cell = new WritableCellFormat();
            WritableCellFormat cellleft = new WritableCellFormat();
            cellleft.setAlignment(Alignment.LEFT);
            cellleft.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setBorder(Border.ALL, BorderLineStyle.THIN);
            cell.setAlignment(Alignment.CENTRE);

            cell.setVerticalAlignment(VerticalAlignment.CENTRE);

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            if (dataList1.size() == 0) {
                s.addCell(new Label(0, 0, "Mandal,Cluster level Pwd Personal Details are not Available................"));
            } else {
                // s.mergeCells(3, 1, 7, 1);
                s.mergeCells(0, 0, 19, 0);
                s.addCell(new Label(0, 0, "Mandal,Cluster level Pwd Personal Details Abstract Report", cellFormat));
                s.mergeCells(0, 1, 19, 1);
                if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Medicalboard Wise")) {
                    s.addCell(new Label(0, 1, "District:" + districtName + "  " + "Camp:" + request.getParameter("campname") + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));

                }else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Disability Wise")) {
                    s.addCell(new Label(0, 1, "Disability::" + request.getParameter("disabilityname") + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                }else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
                    s.addCell(new Label(0, 1, "District::" + request.getParameter("districtName") + "  " + "Camp Date::" + request.getParameter("campDate") +"  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                }else {
                    if (mandalClusterlevelPwdForm.getHabitation_id() != null && mandalClusterlevelPwdForm.getHabitation_id() != "") {
                        s.addCell(new Label(0, 1, "District:" + districtName + "  " + "Mandal:" + mandalName + "  " + "Village:" + villageName + "  " + "Habitation:" + habitationName + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                    } else if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
                        s.addCell(new Label(0, 1, "District:" + districtName + "  " + "Mandal:" + mandalName + "  " + "Village:" + villageName + "  " + "Habitation:ALL" + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                    } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
                        s.addCell(new Label(0, 1, "District:" + districtName + "  " + "Mandal:" + mandalName + "  " + "Village:ALL" + "  " + "Habitation: ALL" + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                    } else if (mandalClusterlevelPwdForm.getDistrict_id() != null && mandalClusterlevelPwdForm.getDistrict_id() != "") {
                        s.addCell(new Label(0, 1, "District:" + districtName + "  " + "Mandal:ALL" + "  " + "Village:ALL" + "  " + "Habitation: ALL" + "  " + "From Date::" + request.getParameter("fromdate") + "  " + "To Date::" + request.getParameter("todate"), cellFormat));
                    } else {
                        s.addCell(new Label(0, 1, "District:ALL" + "  " + "  Mandal:ALL" + "  " + "  Village:ALL" + "  " + "Habitation: ALL", cellFormat));
                    }
                }

                //s.addCell(new Label(0, 1, "rrrrrrrr", cellFormat));
                if (dataList1.size() > 0) {
//                    s.mergeCells(0, 0, 2, 0);
//                    s.addCell(new Label(0, 2, "kkkkkkkkkkk", cellFormat));
//                    s.mergeCells(0, 1, 2, 1);
//                    s.addCell(new Label(0, 2, "rrrrr", cellFormat));
                    headerList.add("S.No");
                    headerList.add("Pensioncard ID");
                    headerList.add("SADAREM ID");
                    headerList.add("Name");
                    headerList.add("Relation Name");
                    headerList.add("Gender");
                    headerList.add("Age");
                    headerList.add("Ration Card Number");
//                    headerList.add("District");
                    headerList.add("Mandal ");
                    headerList.add("Village ");
                    headerList.add("Habitation ");
                    headerList.add("Disability");
                    headerList.add("Pension Phase");
                    headerList.add("House No");
//                    headerList.add("Category");
                    headerList.add("Camp Nmae");
                    headerList.add("Camp Date");
                    headerList.add("Phone");
                    // s.mergeCells(0, 0, 7, 0);
//                    s.mergeCells(0, 0, 2, 0);
//                    s.addCell(new Label(0, 2, "kkkkkkkkkkk", cellFormat));
                    addHeaders(s, headerList, cell, "dataList1");
                }

                int x = 4;

                for (int i = 0; i < dataList1.size(); i++, x++) {
                    int j = 0;
                    Map m = (Map) dataList1.get(i);

                    s.addCell(new Label(j++, x, i + 1 + "", cellFormat));

                    s.addCell(new Label(j++, x, m.get("pensionid").toString(), cell));
                    s.addCell(new Label(j++, x, m.get("sadaremid").toString(), cell));
                    s.addCell(new Label(j++, x, m.get("name").toString(), cellleft));
                    s.addCell(new Label(j++, x, m.get("relationname").toString(), cellleft));
                    s.addCell(new Label(j++, x, m.get("gender").toString(), cellleft));
                    s.addCell(new Label(j++, x, m.get("age").toString(), cell));
                    s.addCell(new Label(j++, x, m.get("rationno").toString(), cell));
//                    s.addCell(new Label(j++, x, m.get("districtname").toString(), cellleft));
                    s.addCell(new Label(j++, x, m.get("mandal").toString(), cellleft));
                    s.addCell(new Label(j++, x, m.get("village").toString(), cellleft));
                    s.addCell(new Label(j++, x, m.get("habitation").toString(), cellleft));
                    s.addCell(new Label(j++, x, m.get("disabilityType").toString(), cellleft));
                    s.addCell(new Label(j++, x, m.get("pensionphase").toString(), cellleft));
                    s.addCell(new Label(j++, x, m.get("houseno").toString(), cell));
//                    s.addCell(new Label(j++, x, m.get("category").toString(), cell));
                    s.addCell(new Label(j++, x, m.get("campname").toString(), cellleft));
                    s.addCell(new Label(j++, x, m.get("campdate").toString(), cell));
                    s.addCell(new Label(j++, x, m.get("phone").toString(), cell));

                    if (i == (50000) * page) {
                        int sheet = ++page;
                        s = w.createSheet("Sheet" + sheet, sheet - 1);
                        j = 0;
                        x = 2;
                        //addHeaders(s, headerList, cell, "camp");
                    }
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
        for (int i = 0; i < col; i++) {
            s.setColumnView(i, 30);
        }

    }

    public static void addHeaders(WritableSheet s, ArrayList list, WritableCellFormat cell, String flag) throws WriteException {
        WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableCellFormat cellFormat = new WritableCellFormat(bold);
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setAlignment(Alignment.CENTRE);
        for (int x = 0; x < list.size(); x++) {
            try {

                if (flag.equals("dataList1")) {
                    s.addCell(new Label(x, 2, list.get(x).toString(), cellFormat));
                    setWidth(s, 19, 30);
                }
                //  if (flag.equals("dataList2")) {
//                 s.addCell(new Label(x, 2, list.get(x).toString(), cellFormat));
//                setWidth(s, 7, 30);
//                 }

            } catch (WriteException ex) {
                ex.printStackTrace();
            }
        }
    }
}
