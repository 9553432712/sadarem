package org.bf.disability.serviceimpl;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.dao.ContactUsDAO;
import org.bf.disability.dto.ContactUsDTO;
import org.bf.disability.form.ContactUsForm;
import org.bf.disability.service.ContactUsService;

public class ContactUsServiceImpl implements ContactUsService {

    public int insertContactUsDetails(DataSource ds, ContactUsForm contactUsForm) throws Exception {
        int contactDetails = 0;
        ContactUsDAO contactUsDAO = new ContactUsDAO();
        try {
            contactDetails = contactUsDAO.insertContactUsDetails(ds, contactUsForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactDetails;
    }

    public ContactUsDTO editDetails(DataSource ds, String rowId) throws Exception {

        ContactUsDTO contactUsDTO = new ContactUsDTO();
        ContactUsDAO contactUsDAO = new ContactUsDAO();
        try {
            contactUsDTO = contactUsDAO.editDetails(ds, rowId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactUsDTO;
    }

    public int updateDetails(DataSource ds, ContactUsForm contactUsForm) throws Exception {
        int contactDetails = 0;
        ContactUsDAO contactUsDAO = new ContactUsDAO();
        try {
            contactDetails = contactUsDAO.updateDetails(ds, contactUsForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactDetails;
    }

    public int InActiveStatus(DataSource ds, String contactID) throws Exception {
        int details = 0;
        ContactUsDAO contactUsDAO = new ContactUsDAO();
        try {
            details = contactUsDAO.InActiveStatus(ds, contactID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public ArrayList getContactUsDetails(DataSource ds, String type) throws Exception {
         ArrayList contactDetails = new ArrayList();
        ContactUsDAO contactUsDAO = new ContactUsDAO();
        try {
            contactDetails = contactUsDAO.getContactUsDetails(ds, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactDetails;
    }

    public String checkExistingContact(DataSource ds, String contactUsType,String districtId, String campId) {
       String status="";
        ContactUsDAO contactUsDAO = new ContactUsDAO();
        try {
            status = contactUsDAO.checkExistingContact(ds, contactUsType,districtId,campId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public ContactUsDTO existingContactDetails(DataSource ds, String contactUsType, String districtId, String campId) {
         ContactUsDTO contactUsDTO=new ContactUsDTO();
        ContactUsDAO contactUsDAO = new ContactUsDAO();
        try {
            contactUsDTO = contactUsDAO.existingContactDetails(ds, contactUsType,districtId,campId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactUsDTO;
    }
}
