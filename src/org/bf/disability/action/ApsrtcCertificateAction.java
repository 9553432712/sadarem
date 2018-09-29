/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ApsrtcCertificateDAO;
import org.bf.disability.dto.ApsrtccertificateDTO;
import org.bf.disability.form.ApsrtcCertificateForm;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;

/**
 *
 * @author 747577
 */
public class ApsrtcCertificateAction extends DispatchAction {

    @Override
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String target = null;
        String personCode = null;
        DataSource ds = null;
        HttpSession session = request.getSession();
        ApsrtcCertificateForm apsrtcCertificateForm = (ApsrtcCertificateForm) form;
        ApsrtccertificateDTO apsrtccertificateDTO = new ApsrtccertificateDTO();
        ApsrtccertificateDTO apsrtccertificateDTO1 = new ApsrtccertificateDTO();
        ApsrtcCertificateDAO apsrtcCertificateDAO = new ApsrtcCertificateDAO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("personcode") != null) {
                personCode = request.getParameter("personcode");
                request.setAttribute("personCode", personCode);
                apsrtccertificateDTO.setSadaremId(personCode);
                String districtid = (String) session.getAttribute("districtId");
                
                CommonDAOImpl comObj = new CommonDAOImpl();
                HashMap GEODtls = new HashMap();
				GEODtls=comObj.getGEODetailsbySADAREMID(personCode);
				String distIdFromSadarem	=	GEODtls.get("districtid").toString();
                
				
				if (distIdFromSadarem!=null && districtid.equals(distIdFromSadarem)) {
                    apsrtccertificateDTO = apsrtcCertificateDAO.getApplicantDetails(ds, apsrtccertificateDTO);
                    BeanUtils.copyProperties(apsrtcCertificateForm, apsrtccertificateDTO);
                    apsrtccertificateDTO = apsrtcCertificateDAO.getDisabilitypercentage(ds, apsrtccertificateDTO);
                    BeanUtils.copyProperties(apsrtcCertificateForm, apsrtccertificateDTO);
                    double disabulityPercentage = apsrtccertificateDTO.getDegreeOfDisability();
                    apsrtccertificateDTO1 = apsrtcCertificateDAO.getpersonDisabilityDetails(ds, apsrtccertificateDTO);
                    if (apsrtccertificateDTO1.getTypeOfDisability() != null) {
                        String typeOfDisabulity = apsrtccertificateDTO1.getTypeOfDisability();
                        if (typeOfDisabulity.equals("Locomotor/OH")) {
                            if (disabulityPercentage >= 40) {
                                target = "apsrtcInputForm";
                            } else {
                                target = "success";
                                request.setAttribute("errorMsg", "Person Disability Only (" + disabulityPercentage + "%) So Unable To Generate Certificate");
                            }
                        } else if (typeOfDisabulity.equals("Mental Retardation")) {
                            if (disabulityPercentage >= 50) {
                                target = "apsrtcInputForm";
                            } else {
                                target = "success";
                                request.setAttribute("errorMsg", "Person Disability Only (" + disabulityPercentage + "%) So Unable To Generate Certificate");
                            }
                        } else if (typeOfDisabulity.equals("Visual Impairment")) {
                            if (disabulityPercentage >= 100) {
                                target = "apsrtcInputForm";
                            } else {
                                target = "success";
                                request.setAttribute("errorMsg", "Person Disability Only (" + disabulityPercentage + "%) So Unable To Generate Certificate");
                            }
                        } else if (typeOfDisabulity.equals("Hearing Impairment")) {
                            if (disabulityPercentage >= 100) {
                                target = "apsrtcInputForm";
                            } else {
                                target = "success";
                                request.setAttribute("errorMsg", "Person Disability Only (" + disabulityPercentage + "%) So Unable To Generate Certificate");
                            }
                        } else {
                            target = "success";
                            request.setAttribute("errorMsg", "Disability Does Not Exist");
                        }
                    } else {
                        target = "success";
                        request.setAttribute("errorMsg", "Person Disability Only (" + disabulityPercentage + "%) So Unable To Generate Certificate");
                    }

                } else {
                    target = "success";
                    request.setAttribute("errorMsg", "Please Enter Your District SADAREM ID");
                }
            } else {
                target = "success";
                // request.setAttribute("errorMsg", "Disability Does Not Exist");
            }

        } catch (NullPointerException e) {
            target = "success";
            request.setAttribute("errorMsg", "Please Enter Valid SADAREM ID");
            e.printStackTrace();
        } catch (Exception e) {
            target = "success";
            request.setAttribute("errorMsg", "Disability not Exist");
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward storeCertificateDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String target = null;
        String personCode = null;
        ApsrtcCertificateForm apsrtcCertificateForm = (ApsrtcCertificateForm) form;
        ApsrtccertificateDTO apsrtccertificateDTO = new ApsrtccertificateDTO();
        ApsrtcCertificateDAO apsrtcCertificateDAO = new ApsrtcCertificateDAO();
        DataSource ds = null;
        String districtid = null;
        String districtName = null;
        HttpSession session = request.getSession();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("districtId") != null) {
                districtid = (String) session.getAttribute("districtId");
            }
            if (request.getParameter("personcode") != null) {
                personCode = request.getParameter("personcode");
            }
            apsrtcCertificateForm.setDistrictId(districtid);
            apsrtcCertificateForm.setSadaremId(request.getParameter("personcode"));
            apsrtcCertificateForm.setLoginId(session.getAttribute("loginid").toString());
            BeanUtils.copyProperties(apsrtccertificateDTO, apsrtcCertificateForm);
            int status = apsrtcCertificateDAO.storeCertificateDetails(ds, apsrtccertificateDTO);
            if (status > 0) {
                if (request.getParameter("personcode") != null) {
                    personCode = request.getParameter("personcode");
                    request.setAttribute("personCode", personCode);
                    
                    CommonDAOImpl comObj = new CommonDAOImpl();
                    HashMap GEODtls = new HashMap();
    				GEODtls=comObj.getGEODetailsbySADAREMID(personCode);
    				String distIdFromSadarem	=	GEODtls.get("districtid").toString();
                    
    				
    				if (distIdFromSadarem!=null && districtid.equals(distIdFromSadarem)) { 
                        apsrtccertificateDTO.setSadaremId(personCode);
                        apsrtccertificateDTO = apsrtcCertificateDAO.getCertificateDetails(ds, apsrtccertificateDTO);
                        BeanUtils.copyProperties(apsrtcCertificateForm, apsrtccertificateDTO);
                        double disabulityPercentage = apsrtccertificateDTO.getDegreeOfDisability();
                        if (apsrtccertificateDTO.getTypeOfDisability() != null) {
                            String typeOfDisabulity = apsrtccertificateDTO.getTypeOfDisability();
                            if (typeOfDisabulity.equals("Locomotor/OH")) {
                                if (disabulityPercentage >= 40) {
                                    target = "apsrtcCertificatePrint";
                                } else {
                                    target = "success";
                                    request.setAttribute("errorMsg", "Person Disability Only (" + disabulityPercentage + "%) So Unable To Generate Certificate");
                                }
                            } else if (typeOfDisabulity.equals("Mental Retardation")) {
                                if (disabulityPercentage >= 50) {
                                    target = "apsrtcCertificatePrint";
                                } else {
                                    target = "success";
                                    request.setAttribute("errorMsg", "Person Disability Only (" + disabulityPercentage + "%) So Unable To Generate Certificate");
                                }
                            } else if (typeOfDisabulity.equals("Visual Impairment")) {
                                if (disabulityPercentage >= 100) {
                                    target = "apsrtcCertificatePrint";
                                } else {
                                    target = "success";
                                    request.setAttribute("errorMsg", "Person Disability Only (" + disabulityPercentage + "%) So Unable To Generate Certificate");
                                }
                            } else if (typeOfDisabulity.equals("Hearing Impairment")) {
                                if (disabulityPercentage >= 100) {
                                    target = "apsrtcCertificatePrint";
                                } else {
                                    target = "success";
                                    request.setAttribute("errorMsg", "Person Disability Only (" + disabulityPercentage + "%) So Unable To Generate Certificate");
                                }
                            } else {
                                target = "success";
                                request.setAttribute("errorMsg", "Disability Does Not Exist");
                            }
                        } else {
                            target = "success";
                            request.setAttribute("errorMsg", "Please Enter Your District SADAREM ID");
                        }
                    } else {
                        target = "success";
                        request.setAttribute("errorMsg", "Disability Does Not Exist");
                    }
                } else {
                    target = "success";
                    //  request.setAttribute("errorMsg", "Disability not Exist");
                }
            } else {
                request.setAttribute("errorMsg", "Error Occured");
            }
            if (target.equals("apsrtcCertificatePrint")) {
                districtName = apsrtcCertificateDAO.getDistrictName(ds, districtid);
                CommonDetails commonDetails = new CommonDetails();
                String url = getServlet().getServletContext().getRealPath("/");
                commonDetails.copyPhotoDtoRelativePath(personCode, districtName, request, url);
                apsrtcCertificateForm.setDistrictName(districtName);
                request.setAttribute("distName", districtName);
                request.setAttribute("personPhotoPath", "");
            }
        } catch (NullPointerException e) {
            target = "success";
            request.setAttribute("errorMsg", "Please Enter Valid SADAREM ID");
            e.printStackTrace();
        } catch (Exception e) {
            target = "success";
            request.setAttribute("errorMsg", "Disability not Exist");
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }

    public ActionForward certificatePrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String target = null;
        String personCode = null;
        ApsrtcCertificateForm apsrtcCertificateForm = (ApsrtcCertificateForm) form;
        ApsrtccertificateDTO apsrtccertificateDTO = new ApsrtccertificateDTO();
        ApsrtcCertificateDAO apsrtcCertificateDAO = new ApsrtcCertificateDAO();
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (request.getParameter("personcode") != null) {
                personCode = request.getParameter("personcode");
                request.setAttribute("personCode", personCode);
            }
            apsrtccertificateDTO.setSadaremId(personCode);
            apsrtccertificateDTO = apsrtcCertificateDAO.getCertificateDetails(ds, apsrtccertificateDTO);
            BeanUtils.copyProperties(apsrtcCertificateForm, apsrtccertificateDTO);
            target = "apsrtcCertificatePrint1";
            int updateStatus = apsrtcCertificateDAO.updateCertificateDetails(ds, apsrtccertificateDTO);
            if (updateStatus > 0) {
                target = "apsrtcCertificatePrint1";
            } else {
                target = "success";
                request.setAttribute("errorMsg", "Error In Certificate Print");
            }
            if (target.equals("apsrtcCertificatePrint1")) {
                String districtName = request.getParameter("disrictName");
                apsrtcCertificateForm.setDistrictName(districtName);
                apsrtcCertificateForm.setSadaremId(personCode);
            }
        } catch (NullPointerException e) {
            target = "success";
            request.setAttribute("errorMsg", "Please Enter Valid SADAREM ID");
            e.printStackTrace();
        } catch (Exception e) {
            target = "success";
            request.setAttribute("errorMsg", "Disability not Exist");
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
}
