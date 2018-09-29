/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.AcknowledgementPrintForm;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;

/**
 *
 * @author 842412
 */
public class AcknowledgementPrintAction extends DispatchAction {

    private static final String target = "success";
    DataSource ds = null;

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PartADAO partADAO = new PartADAO();
        String result = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
//            AcknowledgementPrintForm acknowledgementPrintForm = (AcknowledgementPrintForm) form;
//            if (acknowledgementPrintForm.getSadaremId() != null) {
//                result = partADAO.checkSADARAMID(ds, acknowledgementPrintForm.getSadaremId());
//            }
//            if (result != null && result != "") {
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(target);
    }

    public ActionForward PersonCodeAcknowledgement(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "Acknowledgement";
        PartADAO partADAO = new PartADAO();
        PartADTO partADTO = new PartADTO();
        String result = null;
        HttpSession session = request.getSession(true);
        ArrayList aknowledgementDetails = new ArrayList();
        ArrayList aknowledgementDetailsmultiple = new ArrayList();
        String districtid = (String) session.getAttribute("districtId");
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            AcknowledgementPrintForm acknowledgementPrintForm = (AcknowledgementPrintForm) form;
            if (acknowledgementPrintForm.getSadaremId() != null && acknowledgementPrintForm.getSadaremId() != "") {
                request.setAttribute("sadarem", acknowledgementPrintForm.getSadaremId());
                String distFromsadaremid = "";
                CommonDAOImpl comObj = new CommonDAOImpl();
                HashMap GEODtls = new HashMap();
				GEODtls=comObj.getGEODetailsbySADAREMID(acknowledgementPrintForm.getSadaremId());
				distFromsadaremid	=	GEODtls.get("districtid").toString();
                if (distFromsadaremid!=null && distFromsadaremid.equalsIgnoreCase(districtid)) {
                    result = partADAO.checkSADARAMID(ds, acknowledgementPrintForm.getSadaremId(), request);
                } else {
                    request.setAttribute("msg", "SADAREM ID not belongs to this district!");
                }
            }
            if (result != null && result != "") {
                aknowledgementDetails = partADAO.getAcknowledgementDetails(ds, acknowledgementPrintForm.getSadaremId());
                aknowledgementDetailsmultiple = partADAO.getmultipleDisabilityDetails(ds, acknowledgementPrintForm.getSadaremId(), partADTO);
                if (aknowledgementDetailsmultiple.size() > 0) {
                    request.setAttribute("aknowledgementDetailsmultiple", aknowledgementDetailsmultiple);
                }
                if (aknowledgementDetails.size() > 0) {

                    request.setAttribute("aknowledgementDetails", aknowledgementDetails);
                } else {
                    request.setAttribute("msg", "Given SADAREM ID has not registered for any Camp!!!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward PersonCodeAcknowledgementPrint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        String target = "print";
        PartADAO partADAO = new PartADAO();
        PartADTO partADTO = new PartADTO();
        String result = null;
        ArrayList aknowledgementDetails = new ArrayList();
        ArrayList aknowledgementDetailsmultiple = new ArrayList();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            AcknowledgementPrintForm acknowledgementPrintForm = (AcknowledgementPrintForm) form;
            if (request.getParameter("sadaremid") != null) {
                result = partADAO.checkSADARAMID(ds, request.getParameter("sadaremid"), request);
            }
//            if (acknowledgementPrintForm.getSadaremId() != null) {
//                result = partADAO.checkSADARAMID(ds, acknowledgementPrintForm.getSadaremId());
//            }
            if (result != null && result != "") {
                aknowledgementDetails = partADAO.getAcknowledgementDetails(ds, request.getParameter("sadaremid"));
                aknowledgementDetailsmultiple = partADAO.getmultipleDisabilityDetails(ds, request.getParameter("sadaremid"), partADTO);
            }
            if (aknowledgementDetailsmultiple.size() > 0) {
                request.setAttribute("aknowledgementDetailsmultiple", aknowledgementDetailsmultiple);
            }
            if (aknowledgementDetails.size() > 0) {
                request.setAttribute("aknowledgementDetails", aknowledgementDetails);
            } else {
                request.setAttribute("msg", "Given SADAREM ID has not registered for any Camp!!!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
}
