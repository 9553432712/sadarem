/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;


import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.AadharCardMappingDTO;
import org.bf.disability.form.AadharCardMappingForm;

/**
 *
 * @author 693461
 */
public interface AadharCardMappingService {

    public int getValidSADAREMIDDetails(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException;

    public String invalidSADAREMIDMsg(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException;

    public ArrayList getSADAREMIDValidDetails(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException;

    public AadharCardMappingDTO getAadharCardCount(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException;

    public int updateAadharCardCount(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException;

    public AadharCardMappingDTO particularAaadhartagedSADAREMIDDetails(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException;

    public String AadharCardExist(DataSource ds, AadharCardMappingForm aadharCardMappingForm) throws SADAREMDBException;
}
