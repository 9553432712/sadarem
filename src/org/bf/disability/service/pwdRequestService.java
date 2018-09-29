/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.PwdRequestDTO;
import org.bf.disability.form.PwdRequestForm;

/**
 *
 * @author 693461
 */
public interface pwdRequestService {

    public ArrayList getPwdRequestList(DataSource ds, String requestTypeId) throws SADAREMDBException, SQLException;

    public ArrayList getPanchayatDetails(DataSource ds, String districtId, String mandalId) throws SADAREMDBException, SQLException;

    public PwdRequestDTO checkSadarermIDNo(DataSource ds, String personCode) throws SADAREMDBException, SQLException;

    public PwdRequestDTO checkSadarermIDNoPersoncode(DataSource ds, String personCode) throws SADAREMDBException, SQLException;

    //public AppealPersonalDataDTO getPersonalDetailsForAppealAuthority(DataSource datasource, String pid) throws SADAREMDBException,SQLException;
    public PwdRequestDTO tempararyDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException;

    public PwdRequestDTO duplicateCertificateDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException;

    public boolean physicalImpairment(DataSource ds, String personCode) throws SADAREMDBException, SQLException;

    public int insertRequestInformationDetails(DataSource ds, PwdRequestForm pwdRequestForm, String systemIp) throws SADAREMDBException, SQLException;

    //insertRequestInformationDetails
    public int insertRessessmentRequestInformationDetails(DataSource ds, PwdRequestForm pwdRequestForm, String systemIp) throws SADAREMDBException, SQLException;

    public int insertPwdDetails(DataSource ds, PwdRequestForm pwdRequestForm, String reqId) throws SADAREMDBException, SQLException;

    public String getrequestMaxId(DataSource ds) throws SADAREMDBException, SQLException;

    public PwdRequestDTO getReciptDetils(DataSource ds, String requestId) throws SADAREMDBException, SQLException;

    //NewCertificate Deatils Flow and Forward to PartA Form
    public int newCertificateDetails(DataSource ds, PwdRequestForm pwdRequestForm, String systemIp) throws SADAREMDBException, SQLException;

    public int partAInsertDetails(DataSource ds, PwdRequestForm pwdRequestForm, String documentPath, String addressPath, String loginId, String systemIp) throws SADAREMDBException, SQLException;

    // Mail For Updated Name,RelationName Start Email Details For To List
    public ArrayList getTORequestNameDetails(DataSource ds) throws SADAREMDBException, SQLException;
    //End.
    // Start For Updated Name,RelationName Email Details For CC List

    public ArrayList getCCRequestNameDetails(DataSource ds, String districtId) throws SADAREMDBException, SQLException;
    //End.

    //Mail Body For PWDRequestDetails
    public ArrayList getPwdBodyDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException;

    public PwdRequestDTO validRequestSADAREMID(DataSource ds, String personCode, String requesttypeId) throws SADAREMDBException, SQLException;
    //End
}
