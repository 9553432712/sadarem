/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.AadharCardMappingDAO;
import org.bf.disability.dto.AadharCardMappingDTO;
import org.bf.disability.form.AadharCardMappingForm;
import org.bf.disability.service.AadharCardMappingService;

/**
 *
 * @author 693461
 */
public class AadharCardMappingServiceImpl implements AadharCardMappingService {

    public int getValidSADAREMIDDetails(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException {

        AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();
        int validSadaremId = 0;

        try {
            validSadaremId = aadharCardMappingDAO.getValidSADAREMIDDetails(ds, aadharCardMappingForm);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return validSadaremId;

    }

    public String invalidSADAREMIDMsg(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException {
        String validMsg = null;
        AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();

        try {
            validMsg = aadharCardMappingDAO.invalidSADAREMIDMsg(ds, aadharCardMappingForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validMsg;
    }

    public ArrayList getSADAREMIDValidDetails(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException {

        ArrayList SADAREMIDValidDetails = new ArrayList();
        AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();
        try {
            SADAREMIDValidDetails = aadharCardMappingDAO.getSADAREMIDValidDetails(ds, aadharCardMappingForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SADAREMIDValidDetails;
    }

    public AadharCardMappingDTO getAadharCardCount(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException {
        AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();
        AadharCardMappingDTO aadharCardCount = new AadharCardMappingDTO();

        try {
            aadharCardCount = aadharCardMappingDAO.getAadharCardCount(ds, aadharCardMappingForm);

        } catch (Exception e) {
            e.printStackTrace();


        }
        return aadharCardCount;
    }

    public int updateAadharCardCount(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException {
        AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();
        int updateAadharCardCount = 0;
        try {
            updateAadharCardCount = aadharCardMappingDAO.updateAadharCardCount(ds, aadharCardMappingForm);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return updateAadharCardCount;

    }

    public AadharCardMappingDTO particularAaadhartagedSADAREMIDDetails(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException {

        int particularAaadhartagedSADAREMIDDetails = 0;
        AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();
        AadharCardMappingDTO aadharCardMappingDTO = new AadharCardMappingDTO();
        try {
            aadharCardMappingDTO = aadharCardMappingDAO.particularAaadhartagedSADAREMIDDetails(ds, aadharCardMappingForm);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return aadharCardMappingDTO;

    }

    public String AadharCardExist(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException {
        String SADAREMIDDetails = null;
        AadharCardMappingDAO aadharCardMappingDAO = new AadharCardMappingDAO();

        try {
            SADAREMIDDetails = aadharCardMappingDAO.AadharCardExist(ds, aadharCardMappingForm);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return SADAREMIDDetails;

    }
}
