package org.bf.disability.service;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.dto.ContactUsDTO;
import org.bf.disability.form.ContactUsForm;

public interface ContactUsService {

    public int insertContactUsDetails(DataSource ds, ContactUsForm contactUsForm) throws Exception;

    public ContactUsDTO editDetails(DataSource ds, String rowId) throws Exception;

    public int updateDetails(DataSource ds, ContactUsForm contactUsForm) throws Exception;

    public int InActiveStatus(DataSource ds, String contactID) throws Exception;

    public ArrayList getContactUsDetails(DataSource ds, String type) throws Exception;

    public String checkExistingContact(DataSource ds, String contactUsType, String districtId, String campId);

    public ContactUsDTO existingContactDetails(DataSource ds, String contactUsType, String districtId, String campId);
}
