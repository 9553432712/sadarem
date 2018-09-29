/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.ScheduleForAuForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 484898
 */
public class ScheduleForAUAction extends Action 
{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException 
    {

        ScheduleForAuForm scheduleForm = (ScheduleForAuForm) form;
        DataSource ds = null;
        String target = "success";
        HttpSession session = request.getSession();
        String sql = null;
        Statement st = null;

        ArrayList mandallist = new ArrayList();
        ArrayList habitationlist = new ArrayList();
        ArrayList alreadyEnteredVillages = new ArrayList();
        String checkDateConditions = null; 
        CallableStatement cstmt = null;
        String[] villageID = null;

        try
        {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) 
            {
                ds = JNDIDataSource.getConnection();
            }

            
            

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            //    FunctionalNeedReportDTO functionalNeedReportDTO = new FunctionalNeedReportDTO();

            if (session.getAttribute("districtId") != null && !session.getAttribute("districtId").equals(""))
            {
                mandallist = functionalNeedService.getMandalsScheduleMandals(ds, (String) session.getAttribute("districtId"));
                scheduleForm.setMandallist(mandallist);
            }

            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) 
            {
                habitationlist = functionalNeedService.getScheduleVillages(ds, (String) session.getAttribute("districtId"), request.getParameter("mandal_id"));
                scheduleForm.setVillagelist(habitationlist);
            }


            if ("insertDetails".equalsIgnoreCase(scheduleForm.getMode())) 
            {

                Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(scheduleForm.getFromdate());
                String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);


                if (request.getParameter("village_id").equals("0")) 
                {

                    alreadyEnteredVillages = functionalNeedService.alreadyEnteredVillagesAll(ds, habitationlist, (String) session.getAttribute("districtId"), request.getParameter("mandal_id"), fromdate);
                    checkDateConditions = functionalNeedService.checkConditions(ds, (String) session.getAttribute("districtId"), request.getParameter("mandal_id"), request.getParameter("village_id"), fromdate);

                    if (checkDateConditions != "0" && !checkDateConditions.equals("0")) 
                    {
                        // if (alreadyEnteredVillages != null) {
                        //  if (alreadyEnteredVillages.size() != 0) {
                        //      request.setAttribute("msgFailure", "Selected Villages are Already Scheduled");
                        //   } else {
                        int scheduleInsertForVillages = functionalNeedService.insertVillages(ds, habitationlist, (String) session.getAttribute("districtId"), request.getParameter("mandal_id"), fromdate, (String) session.getAttribute("loginid"), request.getRemoteAddr());

                        if (scheduleInsertForVillages != 0) {
                            request.setAttribute("msgSuccess", "Selected Villages Inserted Successfully");
                            scheduleForm.setVillage_id(villageID);
                            habitationlist = functionalNeedService.getVillageAll(ds, (String) session.getAttribute("districtId"), request.getParameter("mandal_id"));
                            scheduleForm.setVillagelist(habitationlist);
                        } else {
                            request.setAttribute("msgFailure", "Selected Village are Already Scheduled");
                            scheduleForm.setVillage_id(villageID);
                            //     }
                        }
                    }
                    else 
                    {
                        request.setAttribute("msgFailure", "Schedule date should be one day after the current date");
                    }
                } 
                else 
                {
                    alreadyEnteredVillages = functionalNeedService.alreadyEnteredVillages(ds, (String) session.getAttribute("districtId"), request.getParameter("mandal_id"), request.getParameter("village_id"), fromdate);
                    checkDateConditions = functionalNeedService.checkConditions(ds, (String) session.getAttribute("districtId"), request.getParameter("mandal_id"), request.getParameter("village_id"), fromdate);

                    if (checkDateConditions != "0" && !checkDateConditions.equals("0")) {
                        if (alreadyEnteredVillages.size() == 0) {
                            int a = functionalNeedService.insertVillage(ds, (String) session.getAttribute("districtId"), request.getParameter("mandal_id"), request.getParameter("village_id"), fromdate, (String) session.getAttribute("loginid"), request.getRemoteAddr());

                            if (a != 0) {
                                request.setAttribute("msgSuccess", "Selected Village Inserted Successfully");
                                scheduleForm.setVillage_id(villageID);
                                habitationlist = functionalNeedService.getScheduleVillages(ds, (String) session.getAttribute("districtId"), request.getParameter("mandal_id"));
                                scheduleForm.setVillagelist(habitationlist);
                            } else {
                                request.setAttribute("msgFailure", "Error in Inserting");
                                scheduleForm.setVillage_id(villageID);
                            }
                        } else {
                            request.setAttribute("msgFailure", "Selected Village are Already Scheduled");
                        }
                    } else {
                        request.setAttribute("msgFailure", "Schedule date should be one day after the current date");
                    }

                }
            }  // end of mode

            // cstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (st != null) {
                    DBConnection.closeStatement(st);
                }
                DBConnection.closeStatement(cstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(target);
    }
}
