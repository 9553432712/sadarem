/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.CertificateGenerationForm;

/**
 *
 * @author 728056
 */
public interface CertificateGenerationService {

    public ArrayList getMandalList(DataSource ds, String districtid) throws SADAREMDBException, SQLException;

    public ArrayList getviiageNames(DataSource ds, CertificateGenerationForm cgform, String districtid) throws SADAREMDBException, SQLException;

    public ArrayList getHabitationNames(DataSource ds, CertificateGenerationForm cgform,String districtid) throws SADAREMDBException, SQLException;

    public ArrayList getPersonDetails(DataSource ds, CertificateGenerationForm cgform, String districtid) throws SADAREMDBException, SQLException;

    public boolean insertDetails(DataSource ds, CertificateGenerationForm cgform) throws SADAREMDBException, SQLException;
}
