package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.ContactUsServiceImpl;

public class ContactUsServiceFactory {

    public static ContactUsServiceImpl contactUsServiceImpl;

    public static ContactUsServiceImpl getContactUsServiceImpl() {
        if (contactUsServiceImpl == null) {
            contactUsServiceImpl = new ContactUsServiceImpl();
        }
        return contactUsServiceImpl;
    }
}
