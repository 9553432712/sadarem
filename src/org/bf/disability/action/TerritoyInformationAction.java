package org.bf.disability.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.dao.TerritoryInformationDAO;
import org.bf.disability.dto.TerritoryInformationDTO;
import org.bf.disability.form.TerritoryInformationForm;
import org.bf.disability.util.RequestParameterHolder;
import org.bf.disability.common.DataBase.JNDIDataSource;

public class TerritoyInformationAction extends DispatchAction {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward updateTerritoryInformation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TerritoryInformationForm form_obj = (TerritoryInformationForm) form;
        TerritoryInformationDAO dao_obj = new TerritoryInformationDAO();
        DataSource datasource = null;
        //DataSource datasource= getDataSource(request);
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }


            response.setHeader("Cache-Control", "private");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");



            TerritoryInformationDTO dto_obj = new TerritoryInformationDTO();
            BeanUtils.copyProperties(dto_obj, form_obj);
            dao_obj.updateTerritoryInformation(datasource, dto_obj);
            request.setAttribute("msg", "Territory Updated Successfully !");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getTerritoryList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        HttpSession session = request.getSession();
        //DataSource ds= getDataSource(request);

        PrintWriter out = response.getWriter();

        response.setHeader("Cache-Control", "private");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setContentType("text/xml");

        ArrayList territory_info = new ArrayList();
        ArrayList territory_id_list = new ArrayList();
        ArrayList territory_name_list = new ArrayList();
        String district_id="";
        if (session.getAttribute("districtId") != null) {
            district_id = (String) session.getAttribute("districtId");
        }else{
        district_id = request.getParameter("districtid");
        }
        String mandal_id = request.getParameter("mandalid");
        String village_id = request.getParameter("villageid");
        String panchayatid = request.getParameter("panchayatid");
        String territory = request.getParameter("territory");


        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            RequestParameterHolder holder = new RequestParameterHolder();
            holder.setDistrictid(district_id);
            holder.setMandalid(mandal_id);
            holder.setVillageid(village_id);
            holder.setTerritory(territory);
            holder.setPanchayatid(panchayatid);
            TerritoryInformationForm formobj = (TerritoryInformationForm) form;
            TerritoryInformationDAO dao_obj = new TerritoryInformationDAO();

            territory_info = dao_obj.getTerritoryDetails(ds, holder);
            territory_name_list = (ArrayList) territory_info.get(0);
            territory_id_list = (ArrayList) territory_info.get(1);
            request.setAttribute("districtList", territory_info);

            int id_size = territory_name_list.size();

            int count = 0;
//        String name=(String)district_name_list.get(1);
//        int id=((Integer)district_id_list.get(1)).intValue();
            out.println("<root>");


            if (id_size != 0) {
                out.println("<name>" + id_size + "</name>");
                out.println("<id>" + id_size + "</id>");
                for (count = 0; count < territory_id_list.size(); count++) {

                    out.println("<name>" + (String) territory_name_list.get(count) + "</name>");
                    out.println("<id>" + territory_id_list.get(count) + "</id>");
                }
            } else {
                out.println("<name>" + 0 + "</name>");
                out.println("<id>" + 0 + "</id>");

            }

            out.println("</root>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public ActionForward getNewTerritoryList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        HttpSession session = request.getSession();
        //DataSource ds= getDataSource(request);

        PrintWriter out = response.getWriter();

        response.setHeader("Cache-Control", "private");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setContentType("text/xml");

        ArrayList territory_info = new ArrayList();
        ArrayList territory_id_list = new ArrayList();
        ArrayList territory_name_list = new ArrayList();
        String district_id="";
        
        district_id = request.getParameter("districtid");
        
        String mandal_id = request.getParameter("mandalid");
        String village_id = request.getParameter("villageid");
        String panchayatid = request.getParameter("panchayatid");
        String territory = request.getParameter("territory");
       // System.out.println("territory"+territory);
       // System.out.println("district_id"+district_id);

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            RequestParameterHolder holder = new RequestParameterHolder();
            holder.setDistrictid(district_id);
            holder.setMandalid(mandal_id);
            holder.setVillageid(village_id);
            holder.setTerritory(territory);
            holder.setPanchayatid(panchayatid);
            TerritoryInformationForm formobj = (TerritoryInformationForm) form;
            TerritoryInformationDAO dao_obj = new TerritoryInformationDAO();

            territory_info = dao_obj.getTerritoryDetails(ds, holder);
            territory_name_list = (ArrayList) territory_info.get(0);
            territory_id_list = (ArrayList) territory_info.get(1);
            request.setAttribute("districtList", territory_info);
        // System.out.println("districtList"+territory_info);
            int id_size = territory_name_list.size();

            int count = 0;
//        String name=(String)district_name_list.get(1);
//        int id=((Integer)district_id_list.get(1)).intValue();
            out.println("<root>");


            if (id_size != 0) {
                out.println("<name>" + id_size + "</name>");
                out.println("<id>" + id_size + "</id>");
                for (count = 0; count < territory_id_list.size(); count++) {

                    out.println("<name>" + (String) territory_name_list.get(count) + "</name>");
                    out.println("<id>" + territory_id_list.get(count) + "</id>");
                }
            } else {
                out.println("<name>" + 0 + "</name>");
                out.println("<id>" + 0 + "</id>");

            }

            out.println("</root>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public ActionForward insertTerritoryInformation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TerritoryInformationForm form_obj = (TerritoryInformationForm) form;
        TerritoryInformationDAO dao_obj = new TerritoryInformationDAO();
        TerritoryInformationDTO dto_obj = new TerritoryInformationDTO();

        DataSource datasource = null;
        //DataSource datasource= getDataSource(request);

        response.setHeader("Cache-Control", "private");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            BeanUtils.copyProperties(dto_obj, form_obj);
            dto_obj.setIpaddress((String) request.getRemoteAddr());
            String maxvalue = dao_obj.getMaxValueFromTerritory(datasource, dto_obj);

            dao_obj.insertTerritoryInformation(datasource, dto_obj, maxvalue);
//        TerritoryInformationDTO dto_obj=new TerritoryInformationDTO();
//        BeanUtils.copyProperties(dto_obj,form_obj);
//        dao_obj.updateTerritoryInformation(datasource,dto_obj);

            request.setAttribute("msg", "Territory Added Successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);

    }

    //GET DISTRICI LIST
    public List getTerritoryList(HttpServletRequest request)
            throws Exception {
        DataSource ds = null;


        ArrayList territory_info = new ArrayList();
        String territory = "1";
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            RequestParameterHolder holder = new RequestParameterHolder();
            holder.setTerritory(territory);
            TerritoryInformationDAO dao_obj = new TerritoryInformationDAO();
            territory_info = dao_obj.getTerritoryDetails(ds, holder);
            request.setAttribute("districtList", territory_info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return territory_info;

    }
}
