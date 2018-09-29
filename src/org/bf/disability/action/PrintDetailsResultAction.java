/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;

import org.bf.disability.form.PrintDetailsResultForm;
import org.bf.disability.service.PrintDetailsService;

import org.bf.disability.servicefactory.PrintDetailsServiceFactory;


/**
 *
 * @author 747577
 */
public class PrintDetailsResultAction extends DispatchAction {

    String target = "success";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArrayList campList = new ArrayList();
        HttpSession hs = request.getSession();
        PrintDetailsResultForm printDetailsResultForm = (PrintDetailsResultForm) form;
        DataSource ds = null;
        try {
             ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            PrintDetailsService printDetailsService=PrintDetailsServiceFactory.getPrintDetailsServiceImpl();
            request.setAttribute("distId", hs.getAttribute("districtId").toString());
            campList = printDetailsService.getCampDetails(ds,hs.getAttribute("districtId").toString());
            printDetailsResultForm.setCampList(campList);
            if (campList.size() > 0) {
            } else {
                request.setAttribute("nodata", "No Data Found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getPrintDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArrayList campDetailsList = new ArrayList();
        PrintDetailsResultForm printDetailsResultForm = (PrintDetailsResultForm) form;
        DataSource ds = null;
        try {
             ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            PrintDetailsService printDetailsService=PrintDetailsServiceFactory.getPrintDetailsServiceImpl();
            printDetailsResultForm.setCamp_id(printDetailsResultForm.getCamp_id());
            printDetailsResultForm.setDistrict_id(request.getParameter("districtid"));
            campDetailsList = printDetailsService.getFullCampDetails(ds,printDetailsResultForm);
            unspecified(mapping, form, request, response);
            if (campDetailsList.size() > 0) {
                request.setAttribute("campDetailsList", campDetailsList);
            } else {
                request.setAttribute("nodata", "No Data Found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward getPrintFullDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArrayList certificateDetailsList = new ArrayList();
        PrintDetailsResultForm printDetailsResultForm = (PrintDetailsResultForm) form;
        DataSource ds = null;
        try {
             ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            PrintDetailsService printDetailsService=PrintDetailsServiceFactory.getPrintDetailsServiceImpl();
            printDetailsResultForm.setS_id(request.getParameter("sID"));
            printDetailsResultForm.setCamp_id(request.getParameter("campID"));
            printDetailsResultForm.setDistrict_id(request.getParameter("dID"));
            certificateDetailsList = printDetailsService.getPrintCertificateDetails(ds,printDetailsResultForm);
            if (certificateDetailsList.size() > 0) {
                request.setAttribute("certificateDetailsList", certificateDetailsList);
            } else {
                request.setAttribute("nodatafound", "No Data Found!");
            }
            unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("printReport");
    }

    public ActionForward getPrintCountFullDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArrayList certificateCountDetailsList = new ArrayList();
        PrintDetailsResultForm printDetailsResultForm = (PrintDetailsResultForm) form;
        DataSource ds = null;
        try {
             ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            request.setAttribute("campId", request.getParameter("selectedId"));
            request.setAttribute("distId", request.getParameter("distid"));
            PrintDetailsService printDetailsService=PrintDetailsServiceFactory.getPrintDetailsServiceImpl();
            printDetailsResultForm.setCamp_id(request.getParameter("selectedId"));
            printDetailsResultForm.setDistrict_id(request.getParameter("distid"));
            certificateCountDetailsList = printDetailsService.getCampCountDetails(ds,printDetailsResultForm);
            if (certificateCountDetailsList.size() > 0) {
                request.setAttribute("certificateCountDetailsList", certificateCountDetailsList);
            } else {
                request.setAttribute("nodata", "No Data Found!");
            }
            unspecified(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("printCountReport");
    }
}
