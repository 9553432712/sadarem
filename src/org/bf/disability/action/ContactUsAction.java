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
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.ContactUsDTO;
import org.bf.disability.form.ContactUsForm;
import org.bf.disability.service.ContactUsService;
import org.bf.disability.servicefactory.ContactUsServiceFactory;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;

public class ContactUsAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ContactUsForm contactsDetailsForm = (ContactUsForm) form;
        int contactDetails = 0;
        ContactUsService contactUsService = ContactUsServiceFactory.getContactUsServiceImpl();
        HttpSession session = request.getSession();
        DataSource ds = null;
        CommonDAO commonDAO = new CommonDAO();
        try 
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) 
            {
                ds = JNDIDataSource.getConnection();
            }
            
            if (session.getAttribute("districtId") != null) 
            {
                contactsDetailsForm.setDistrictId(session.getAttribute("districtId").toString());
                contactsDetailsForm.setDistrictName(commonDAO.getDistrictNameById(ds, contactsDetailsForm.getDistrictId()));
            }

            if (contactsDetailsForm.getContactUsType() != null)
            {
                if (contactsDetailsForm.getContactUsType().equalsIgnoreCase("Campincharge")) 
                {
                    contactsDetailsForm.setCampList(commonDAO.getCampDetails(ds, contactsDetailsForm.getDistrictId()));
                } 
                else 
                {
                    contactsDetailsForm.setCampList(new ArrayList());
                }
                String availableMsg = contactUsService.checkExistingContact(ds, contactsDetailsForm.getContactUsType(), contactsDetailsForm.getDistrictId(), contactsDetailsForm.getCampId());
                
                if (availableMsg.length() > 0) 
                {
                    request.setAttribute("ExistingMSG", availableMsg);
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward insertContactUsDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  throws Exception
    {
        ContactUsForm contactsDetailsForm = (ContactUsForm) form;
        int contactDetails = 0;
        ContactUsService contactUsService = ContactUsServiceFactory.getContactUsServiceImpl();
        HttpSession session = request.getSession();
        DataSource ds = null;
        try 
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds))
            {
                ds = JNDIDataSource.getConnection();
            }
            String loginid = null, deptId = null;
            if (session.getAttribute("username") != null) 
            {
                loginid = session.getAttribute("username").toString();
            }
            contactsDetailsForm.setSystemIp(request.getRemoteAddr());
            contactsDetailsForm.setLoginId(session.getAttribute("username").toString());

            contactDetails = contactUsService.insertContactUsDetails(ds, contactsDetailsForm);
            if (contactDetails != 0) 
            {
                request.setAttribute("msg", "ContactUs Details are Inserted Successfully");
                contactsDetailsForm.setEmpContact("");
                contactsDetailsForm.setEmail("");
                contactsDetailsForm.setContactCode("");
                contactsDetailsForm.setContactUsType("");
                contactsDetailsForm.setEmployeeName("");
                contactsDetailsForm.setDesignation("");
            } 
            else
            {
                request.setAttribute("Error", "Error in Insertion");
            }
            String type = null;

            ArrayList contactUsList = contactUsService.getContactUsDetails(ds, type);
            if (contactUsList.size() > 0) {
                request.setAttribute("contactUsList", contactUsList);
            } else {
                request.setAttribute("nodata", "No Data");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward editDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ArrayList contactDetails = new ArrayList();
        ContactUsForm contactsDetailsForm = (ContactUsForm) form;
        ContactUsService contactUsService = ContactUsServiceFactory.getContactUsServiceImpl();
        HttpSession session = request.getSession();
        DataSource ds = null;
        String type = null;

        CommonDAO commonDAO = new CommonDAO();
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }


            if (contactsDetailsForm.getContactUsType() != null) {

                ContactUsDTO contactUsDTO = contactUsService.existingContactDetails(ds, contactsDetailsForm.getContactUsType(), contactsDetailsForm.getDistrictId(), contactsDetailsForm.getCampId());

                if (contactUsDTO.getDistrictId() != null && contactUsDTO.getDistrictId().length() > 0) {
                    org.apache.commons.beanutils.BeanUtils.copyProperties(contactsDetailsForm, contactUsDTO);
                }


                if (contactsDetailsForm.getContactUsType() != null && !contactsDetailsForm.getContactUsType().equalsIgnoreCase("0")
                        && !contactsDetailsForm.getContactUsType().equalsIgnoreCase("District")) {
                    contactsDetailsForm.setCampList(commonDAO.getCampDetails(ds, session.getAttribute("districtId").toString()));
                }
                if (contactUsDTO.getStatus() != null && contactUsDTO.getStatus().equalsIgnoreCase("true") && contactUsDTO.getRowId() == null) {
                    request.setAttribute("ExistingMSG", "There is no contact Details available for Updation");
                }

                if (contactUsDTO.getContactUsType() == null) {
                    contactsDetailsForm.setRowId("");
                    contactsDetailsForm.setDistrictName("");
                    contactsDetailsForm.setEmployeeName("");
                    contactsDetailsForm.setDesignation("");
                    contactsDetailsForm.setEmpContact("");
                    contactsDetailsForm.setEmail("");

                }


            }


            request.setAttribute("upadate", "upadate");
            ArrayList contactUsList = contactUsService.getContactUsDetails(ds, type);
            if (contactUsList.size() > 0) {
                request.setAttribute("contactUsList", contactUsList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward updateDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ContactUsForm contactsDetailsForm = (ContactUsForm) form;
        int contactDetails = 0;
        String target = null;
        ContactUsService contactUsService = ContactUsServiceFactory.getContactUsServiceImpl();
        HttpSession session = request.getSession();
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String loginid = null, deptId = null;
            if (session.getAttribute("username") != null) {
                loginid = session.getAttribute("username").toString();
            }


            contactDetails = contactUsService.updateDetails(ds, contactsDetailsForm);


            contactsDetailsForm.setEmployeeName("");
            contactsDetailsForm.setEmpContact("");
            contactsDetailsForm.setContactCode("");
            contactsDetailsForm.setEmail("");
            contactsDetailsForm.setContactUsType("");
            contactsDetailsForm.setDesignation("");

            if (contactDetails > 0) {
                request.setAttribute("msg", "Contact Details Updated successfully");

            } else {
                request.setAttribute("Error", "Error in Contact Details Updation");
            }
            contactsDetailsForm.setRowId(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward inActiveStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int contactDetails = 0;
        String contactID = null;
        ContactUsService contactUsService = ContactUsServiceFactory.getContactUsServiceImpl();
        DataSource ds = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            contactDetails = contactUsService.InActiveStatus(ds, request.getParameter("tId").toString());

            if (contactDetails != 0) {
                request.setAttribute("msg", "Contact Deleted Successfully");
                unspecified(mapping, form, request, response);
            } else {
                request.setAttribute("Error", "Error in Inserting");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward getContactUsDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        ContactUsService contactUsService = ContactUsServiceFactory.getContactUsServiceImpl();
        ContactUsForm contactsDetailsForm = (ContactUsForm) form;
        String target = null;
        DataSource ds = null;
        CommonConstants commonConstants = new CommonConstants();

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            ArrayList softwareContactUsList = contactUsService.getContactUsDetails(ds, CommonConstants.Software);

            ArrayList districtContactUsList = contactUsService.getContactUsDetails(ds, CommonConstants.District);

            ArrayList campInchargeContactList = contactUsService.getContactUsDetails(ds, CommonConstants.CampIncharge);

            if (districtContactUsList.size() > 0) 
            {
                request.setAttribute("districtContactUsList", districtContactUsList);
            } 
            else 
            {
                request.setAttribute("nodata", "DistrictContacts Details Not Avilable ");
            }
            
            if (softwareContactUsList.size() > 0) 
            {
                request.setAttribute("softwareContactUsList", softwareContactUsList);
            } 
            else
            {
                request.setAttribute("nodata", "Software Contacts Details Not Avilable  ");
            }

            if (campInchargeContactList.size() > 0) 
            {
                request.setAttribute("campInchargeContactList", campInchargeContactList);
            } 
            else
            {
                request.setAttribute("nodata", "Camp Incharge Contacts Details Not Avilable  ");
            }

            target = "report";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
    
    public ActionForward getContactDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        String target = null;
        try 
        {
        	CommonDAOImpl comObj = new CommonDAOImpl();
        	
        	ArrayList contactList = (ArrayList)comObj.getContactDetails();
        	
        	request.setAttribute("contactList", contactList);
        	
            target = "report";

        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
}
