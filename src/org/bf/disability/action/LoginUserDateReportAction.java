/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.service.AppletAuthorityService;
import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.bf.disability.dao.TerritoryDAO;

/**
 *
 * @author 728056
 */
public class LoginUserDateReportAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getAllDataReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //int districtId = Integer.parseInt(session.getAttribute("districtId").toString());
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
        ArrayList dataList = appletAuthorityService.getAllDataReport(ds, request);
        request.setAttribute("alldataList", dataList);
        return mapping.findForward("exportDataReport");
    }

    public ActionForward exportReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }

        HttpSession session = request.getSession();
        AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
        ArrayList<HashMap> dataList = appletAuthorityService.getAllDataReport(ds, request);
        TerritoryDAO territoryDAO = new TerritoryDAO();
        String districtName = territoryDAO.getDistrictsName(ds, session.getAttribute("districtId").toString());
        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + districtName + ".xls");

            WritableWorkbook w =
                    Workbook.createWorkbook(response.getOutputStream());

            int page = 1;
            WritableSheet s = w.createSheet("Sheet" + page, page - 1);
            ArrayList headerList = new ArrayList();

            if (dataList.size() == 0) {

                s.addCell(new Label(0, 0, "No Data Found................"));
            } else {
                headerList.add("S No");
                headerList.add("SADAREM ID");
                headerList.add("Pension ID");
                headerList.add("Name");
                headerList.add("Gender");
                headerList.add("Caste");
                headerList.add("Age");
                headerList.add("Marital Status");
                headerList.add("Relation Name");
                headerList.add("Education");
                headerList.add("Rationcard");
                headerList.add("Disability");
                headerList.add("Disability %");
                headerList.add("Phase");
                headerList.add("Mandal");
                headerList.add("Village");
                headerList.add("Medical Board");
                headerList.add("Medical Board Address");
                headerList.add("Assessed Doctor");
                headerList.add("Doctor RegNo");
                headerList.add("Doctor Designation");
                headerList.add("Assessed Date");
                headerList.add("Assessement Status");

                addHeaders(s, headerList);

            }

            int x = 2;
            for (int i = 0; i < dataList.size(); i++, x++) {
                int j = 0;
                HashMap map = dataList.get(i);
                s.addCell(new Label(j++, x, i + 1 + ""));
                s.addCell(new Label(j++, x, map.get("SADAREM ID").toString()));
                s.addCell(new Label(j++, x, map.get("Pension ID").toString()));
                s.addCell(new Label(j++, x, map.get("Name").toString()));
                s.addCell(new Label(j++, x, map.get("Gender").toString()));
                s.addCell(new Label(j++, x, map.get("Caste").toString()));
                s.addCell(new Label(j++, x, map.get("Age").toString()));
                s.addCell(new Label(j++, x, map.get("Marital Status").toString()));
                s.addCell(new Label(j++, x, map.get("Relation Name").toString()));
                s.addCell(new Label(j++, x, map.get("Education").toString()));
                s.addCell(new Label(j++, x, map.get("Rationcard").toString()));
                s.addCell(new Label(j++, x, map.get("Disability").toString()));
                s.addCell(new Label(j++, x, map.get("Disability %").toString()));
                s.addCell(new Label(j++, x, map.get("Phase").toString()));
                s.addCell(new Label(j++, x, map.get("Mandal").toString()));
                s.addCell(new Label(j++, x, map.get("Village").toString()));
                s.addCell(new Label(j++, x, map.get("Medical Board").toString()));
                s.addCell(new Label(j++, x, map.get("Medical Board Address").toString()));
                s.addCell(new Label(j++, x, map.get("Assessed Doctor").toString()));
                s.addCell(new Label(j++, x, map.get("Doctor RegNo").toString()));
                s.addCell(new Label(j++, x, map.get("Doctor Designation").toString()));
                s.addCell(new Label(j++, x, map.get("Assessed Date").toString()));
                s.addCell(new Label(j++, x, map.get("PWD Status").toString()));
                s.addCell(new Label(j++, x, map.get("Assessement Status").toString()));

                if (i == (50000) * page) {
                    int sheet = ++page;
                    s = w.createSheet("Sheet" + sheet, sheet - 1);
                    addHeaders(s, headerList);
                    j = 0;
                    x = 2;

                }


            }



            w.write();

            w.close();
        } catch (Exception e) {
            //throw new ServletException("Exception in Excel Sample Servlet", e);
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return null;
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
}
