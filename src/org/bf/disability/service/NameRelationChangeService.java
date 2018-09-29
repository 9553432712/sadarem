/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.NameRelationChangeForm;

/**
 *
 * @author 693461
 */
public interface NameRelationChangeService {

    public ArrayList getnameRelationChangeDetails(DataSource ds, int campId, String districtId) throws SADAREMDBException, SQLException;

    public ArrayList individualNameRelationNameChangeDetails(DataSource ds, String requestId) throws SADAREMDBException, SQLException;

    public ArrayList oldParticularDertails(DataSource ds, String personCode, String requestId, String requestTypeId) throws SADAREMDBException, SQLException;

// public int updateParticularDetails (DataSource ds,NameRelationChangeForm nameRelationChangeForm,ArrayList oldParticularList)throws SADAREMDBException,SQLException;
    public int updateParticularDetails(DataSource ds, NameRelationChangeForm nameRelationChangeForm, String requestId) throws SADAREMDBException, SQLException;

    public ArrayList RelationChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException;

    public ArrayList NameChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException;

    public ArrayList MoleChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException;

    public ArrayList DOBChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException;

    public String getPersonCodeDetails(DataSource ds, String requestId) throws SADAREMDBException, SQLException;

    public ArrayList getEmailBodyDetails(DataSource ds, String personCode, String requestId) throws SADAREMDBException, SQLException;

    public String emailpersonDetails(DataSource ds, String username, String districtId) throws SADAREMDBException, SQLException;

    public ArrayList getEmailOldNameRelationDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException;

    public String dOBDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException;

    public ArrayList getData(DataSource ds, String district_id, String status) throws SADAREMDBException, SQLException;

public ArrayList surNameChangeDetails(DataSource ds, String requestId, String requestType) throws SADAREMDBException, SQLException;
}
