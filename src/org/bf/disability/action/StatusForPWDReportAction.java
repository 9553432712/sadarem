package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.service.StatusForPWDReportService;
import org.bf.disability.servicefactory.StatusForPWDReportServiceFactory;

/**
 * this dispatch action class will generate the report relating to a person with disability as it gives the 
 * status of a PWD at camp level
 * @author raghavendra 
 * @version 1.0
 */
public class StatusForPWDReportAction extends BaseDispatchAction {

    DataSource ds = null;

    /**
     * this method will generate the status of PWD at district level for a particular camp
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward getDistrictReports(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {

        ArrayList pwdreport = new ArrayList();
        ArrayList districtlist = new ArrayList();
        ArrayList districtreport = new ArrayList();
        String target = "success";
        ActionMessages actionMessages = null;
        //DataSource ds= getDataSource(request);


        TerritoryForm territoryform = (TerritoryForm) form;
        TerritoryDTO territorydto = new TerritoryDTO();
        TerritoryDTO territorydto1;
        TerritoryDTO territorydto2;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String fromdate = (String) territoryform.getFromdate();
            String todate = (String) territoryform.getTodate();
            BeanUtils.copyProperties(territorydto, territoryform);
            request.setAttribute("gotofromdatejsp", fromdate);
            request.setAttribute("gototodatejsp", todate);
            ArrayList districtlistforreport = new ArrayList();
            StatusForPWDReportService statusforpwdreportservice =
                    StatusForPWDReportServiceFactory.getStatusForPWDReportServiceImpl();
            districtlistforreport = statusforpwdreportservice.getDistricts(ds, territorydto);
            ArrayList districtname = new ArrayList();
            ArrayList districtid = new ArrayList();
            districtlist = (ArrayList) districtlistforreport.get(0);
            districtreport = (ArrayList) districtlistforreport.get(1);
            if (districtreport.isEmpty()) {

                target = "ReportException";
            }

            Iterator ite1 = districtlist.iterator();
            while (ite1.hasNext()) {
                territorydto1 = (TerritoryDTO) ite1.next();
                districtname.add(territorydto1.getDistrict_name());
                districtid.add(territorydto1.getDistrict_id());
            }


            String gotojspfromdate = (String) request.getAttribute("gotofromdatejsp");
            String gotojsptodate = (String) request.getAttribute("gototodatejsp");
            territorydto.setFromdate(gotojspfromdate);
            territorydto.setTodate(gotojsptodate);
            BeanUtils.copyProperties(territoryform, territorydto);
            request.setAttribute("districtlist", districtname);
            request.setAttribute("districtid", districtid);
            request.setAttribute("FromDateToPWDTerritory",
                    territoryform.getFromdate());
            request.setAttribute("ToDateToPWDTerritory", territoryform.getTodate());
            request.setAttribute("pwdreports", districtreport);
            request.setAttribute("pwdreportsheading", "Districts");
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

    /**
     * this method will generate the status of PWD at mandal level for a particular camp
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward getMandalReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        // DataSource ds= getDataSource(request);


        TerritoryForm territoryform = (TerritoryForm) form;
        TerritoryDTO territorydto = new TerritoryDTO();
        TerritoryDTO territorydto1 = new TerritoryDTO();
        ArrayList districts = new ArrayList();
        ArrayList mandals = new ArrayList();
        ActionMessages actionMessages = null;
        ArrayList reportformandals = new ArrayList();
        ArrayList districtlistforreport = new ArrayList();
        ArrayList mandallistforreport = new ArrayList();
        ArrayList district_id = new ArrayList();
        ArrayList district_name = new ArrayList();
        ArrayList mandal_id = new ArrayList();
        ArrayList mandal_name = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String dis = request.getParameter("districtid");
            String fromdate = request.getParameter("from_date_to_action");
            String todate = request.getParameter("to_date_to_action");
            territoryform.setFromdate(fromdate);
            territoryform.setTodate(todate);
            request.setAttribute("go_to_mandal_fromdate", fromdate);
            request.setAttribute("go_to_mandal_todate", todate);
            String districtid = request.getParameter("district_namefromjsp");
            request.setAttribute("comparedistrictname", districtid);
            territoryform.setDistrict_id(dis);
            BeanUtils.copyProperties(territorydto, territoryform);
            StatusForPWDReportService statusforpwdreportservice =
                    StatusForPWDReportServiceFactory.getStatusForPWDReportServiceImpl();
            mandallistforreport = statusforpwdreportservice.getMandals(ds, territorydto);
            districts = (ArrayList) mandallistforreport.get(0);
            mandals = (ArrayList) mandallistforreport.get(1);
            reportformandals = (ArrayList) mandallistforreport.get(2);
            if (reportformandals.isEmpty()) {

                target = "ReportException";
            }
            Iterator ite = districts.iterator();
            while (ite.hasNext()) {
                territorydto = (TerritoryDTO) ite.next();

                district_name.add(territorydto.getDistrict_name());
                district_id.add(territorydto.getDistrict_id());
            }

            Iterator iterator = mandals.iterator();
            while (iterator.hasNext()) {
                territorydto = (TerritoryDTO) iterator.next();

                mandal_name.add(territorydto.getMandal_name());
                mandal_id.add(territorydto.getMandal_id());

            }
            String gotofromdateform = (String) request.getAttribute("go_to_mandal_fromdate");
            String gototodateform = (String) request.getAttribute("go_to_mandal_todate");

            territorydto.setFromdate(gotofromdateform);
            territorydto.setTodate(gototodateform);
            request.setAttribute("districtlist", district_name);
            request.setAttribute("districtid", district_id);
            request.setAttribute("mandallist", mandal_name);
            request.setAttribute("mandalid", mandal_id);
            request.setAttribute("FromDateToPWDTerritory",
                    territoryform.getFromdate());
            request.setAttribute("ToDateToPWDTerritory",
                    territoryform.getTodate());
            request.setAttribute("pwdreports", reportformandals);
            request.setAttribute("pwdreportsheading", "Mandals");
            BeanUtils.copyProperties(territoryform, territorydto);

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

    /**
     * this method will generate the status of PWD at village level for a particular camp
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward getVillageReport(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        TerritoryDTO territorydto2 = null;
        //DataSource ds= getDataSource(request);



        TerritoryForm territoryform = (TerritoryForm) form;
        TerritoryDTO territorydto = new TerritoryDTO();
        ArrayList districts = new ArrayList();
        ArrayList mandals = new ArrayList();
        ArrayList villages = new ArrayList();
        ArrayList villagereport = new ArrayList();
        ArrayList settojsppage = new ArrayList();
        ArrayList districtlistforreport = new ArrayList();
        ArrayList mandallistforreport = new ArrayList();
        ArrayList villagelistforreport = new ArrayList();
        ArrayList district_id = new ArrayList();
        ArrayList district_name = new ArrayList();
        ArrayList mandal_id = new ArrayList();
        ArrayList mandal_name = new ArrayList();
        ArrayList village_id = new ArrayList();
        ArrayList village_name = new ArrayList();
        ActionMessages actionMessages = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String disid = request.getParameter("districtid");
            String manid = request.getParameter("mandalid");
            String districtnamefromjsp = request.getParameter("districtname");
            String mandalnamefromjsp = request.getParameter("mandal_namefromjsp");
            String fromdate = request.getParameter("from_date_to_action");
            String todate = request.getParameter("to_date_to_action");
            territoryform.setFromdate(fromdate);
            territoryform.setTodate(todate);
            request.setAttribute("go_to_village_fromdate", fromdate);
            request.setAttribute("go_to_village_todate", todate);
            request.setAttribute("comparedistrictname", districtnamefromjsp);
            request.setAttribute("comparemandalname", mandalnamefromjsp);
            territoryform.setDistrict_id(disid);
            territoryform.setMandal_id(manid);
            BeanUtils.copyProperties(territorydto, territoryform);
            StatusForPWDReportService statusforpwdreportservice =
                    StatusForPWDReportServiceFactory.getStatusForPWDReportServiceImpl();
            villagelistforreport = statusforpwdreportservice.getVillages(ds, territorydto);
            districts = (ArrayList) villagelistforreport.get(0);
            mandals = (ArrayList) villagelistforreport.get(1);
            villages = (ArrayList) villagelistforreport.get(2);
            villagereport = (ArrayList) villagelistforreport.get(3);
            if (villagereport.isEmpty()) {

                target = "ReportException";
            }
            Iterator ite = districts.iterator();
            while (ite.hasNext()) {
                territorydto2 = (TerritoryDTO) ite.next();

                district_name.add(territorydto2.getDistrict_name());
                district_id.add(territorydto2.getDistrict_id());
            }
            Iterator iterator = mandals.iterator();
            while (iterator.hasNext()) {
                territorydto = (TerritoryDTO) iterator.next();
                mandal_name.add(territorydto.getMandal_name());
                mandal_id.add(territorydto.getMandal_id());
            }
            Iterator iteratorforvillage = villages.iterator();
            while (iteratorforvillage.hasNext()) {
                territorydto = (TerritoryDTO) iteratorforvillage.next();

                village_name.add(territorydto.getVillage_name());
                village_id.add(territorydto.getVillage_id());
            }
            String gotofromdateform = (String) request.getAttribute("go_to_village_fromdate");
            String gototodateform = (String) request.getAttribute("go_to_village_todate");
            territorydto.setFromdate(gotofromdateform);
            territorydto.setTodate(gototodateform);
            request.setAttribute("districtlist", district_name);
            request.setAttribute("districtid", district_id);
            request.setAttribute("mandallist", mandal_name);
            request.setAttribute("mandalid", mandal_id);
            request.setAttribute("villageid", village_id);
            request.setAttribute("villagelist", village_name);
            request.setAttribute("FromDateToPWDTerritory",
                    territoryform.getFromdate());
            request.setAttribute("ToDateToPWDTerritory",
                    territoryform.getTodate());
            request.setAttribute("pwdreports", villagereport);
            request.setAttribute("pwdreportsheading", "Villages");
            BeanUtils.copyProperties(territoryform, territorydto);

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

    /**
     * this method will generate the status of PWD at habitation level for a particular camp
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward getHabitationReport(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "success";
        ActionMessages actionMessages = null;
        //DataSource ds= getDataSource(request);


        TerritoryForm territoryform = (TerritoryForm) form;
        TerritoryDTO territorydto = new TerritoryDTO();
        ArrayList settojsppage = new ArrayList();
        ArrayList districts = new ArrayList();
        ArrayList mandals = new ArrayList();
        ArrayList villages = new ArrayList();
        ArrayList habitations = new ArrayList();
        ArrayList habitationreport = new ArrayList();
        ArrayList habitationlistforreport = new ArrayList();
        ArrayList district_id = new ArrayList();
        ArrayList district_name = new ArrayList();
        ArrayList mandal_id = new ArrayList();
        ArrayList mandal_name = new ArrayList();
        ArrayList village_id = new ArrayList();
        ArrayList village_name = new ArrayList();
        ArrayList habitation_id = new ArrayList();
        ArrayList habitation_name = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String disid = request.getParameter("districtid");
            String manid = request.getParameter("mandalid");
            String villid = request.getParameter("villageid");
            String districtnamefromjsp = request.getParameter("districtname");
            String mandalnamefromjsp = request.getParameter("mandal_namefromjsp");
            String villagenamefromjsp = request.getParameter("village_namefromjsp");
            request.setAttribute("comparedistrictname", districtnamefromjsp);
            request.setAttribute("comparemandalname", mandalnamefromjsp);
            request.setAttribute("comparevillagename", villagenamefromjsp);
            territoryform.setDistrict_id(disid);
            territoryform.setMandal_id(manid);
            territoryform.setVillage_id(villid);
            String fromdate = request.getParameter("from_date_to_action");
            String todate = request.getParameter("to_date_to_action");
            territoryform.setFromdate(fromdate);
            territoryform.setTodate(todate);
            request.setAttribute("go_to_habitation_fromdate", fromdate);
            request.setAttribute("go_to_habitation_todate", todate);
            BeanUtils.copyProperties(territorydto, territoryform);
            StatusForPWDReportService statusforpwdreportservice =
                    StatusForPWDReportServiceFactory.getStatusForPWDReportServiceImpl();
            habitationlistforreport = statusforpwdreportservice.getHabitations(ds, territorydto);
            districts = (ArrayList) habitationlistforreport.get(0);
            mandals = (ArrayList) habitationlistforreport.get(1);
            villages = (ArrayList) habitationlistforreport.get(2);
            habitations = (ArrayList) habitationlistforreport.get(3);
            habitationreport = (ArrayList) habitationlistforreport.get(4);
            if (habitationreport.isEmpty()) {

                target = "ReportException";
            }
            Iterator ite = districts.iterator();
            while (ite.hasNext()) {
                territorydto = (TerritoryDTO) ite.next();
                district_name.add(territorydto.getDistrict_name());
                district_id.add(territorydto.getDistrict_id());
            }
            Iterator iterator = mandals.iterator();
            while (iterator.hasNext()) {
                territorydto = (TerritoryDTO) iterator.next();

                mandal_name.add(territorydto.getMandal_name());
                mandal_id.add(territorydto.getMandal_id());
            }
            Iterator iteratorforvillage = villages.iterator();
            while (iteratorforvillage.hasNext()) {
                territorydto = (TerritoryDTO) iteratorforvillage.next();
                village_name.add(territorydto.getVillage_name());
                village_id.add(territorydto.getVillage_id());
            }
            Iterator iteratorforhabitation = habitations.iterator();
            while (iteratorforhabitation.hasNext()) {
                territorydto = (TerritoryDTO) iteratorforhabitation.next();
                habitation_name.add(territorydto.getHabitation_name());
                habitation_id.add(territorydto.getHabitation_id());
            }
            String compare = (String) request.getAttribute("comparevillagename");
            String gotofromdateform = (String) request.getAttribute("go_to_habitation_fromdate");
            String gototodateform = (String) request.getAttribute("go_to_habitation_todate");
            territorydto.setFromdate(gotofromdateform);
            territorydto.setTodate(gototodateform);
            request.setAttribute("districtlist", district_name);
            request.setAttribute("districtid", district_id);
            request.setAttribute("mandallist", mandal_name);
            request.setAttribute("mandalid", mandal_id);
            request.setAttribute("villageid", village_id);
            request.setAttribute("villagelist", village_name);
            request.setAttribute("habitationlist", habitation_name);
            request.setAttribute("habitationid", habitation_id);
            request.setAttribute("comparevillagename", compare);
            request.setAttribute("FromDateToPWDTerritory",
                    territoryform.getFromdate());
            request.setAttribute("ToDateToPWDTerritory",
                    territoryform.getTodate());
            request.setAttribute("pwdreports", habitationreport);
            request.setAttribute("pwdreportsheading", "Habitations");
            BeanUtils.copyProperties(territoryform, territorydto);

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


