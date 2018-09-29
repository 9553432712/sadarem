/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.activation.DataSource;
import javax.servlet.http.HttpServletRequest;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.NewCertificateDTO;

/**
 *
 * @author 310926
 */
public interface NewCertificateService {

    public String insertPersonalDetailsForRationCard(NewCertificateDTO NewCertificateDTO, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public String getPartACheckForDuplicate(DataSource ds, NewCertificateDTO NewCertificateDTO) throws SADAREMDBException, SQLException;
}
