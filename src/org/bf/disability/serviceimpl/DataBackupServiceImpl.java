/*
 * DataBackupServiceImpl.java
 *
 * Created on September 22, 2008, 2:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.DataBackupDAO;
import org.bf.disability.service.DataBackupService;

/**
 * This class has the implementation functionality for DataBackupService interface.
 * @author Pramod Kumar G
 * @version 1.0
 */
public class DataBackupServiceImpl implements DataBackupService {

    DataBackupDAO databackupdao = new DataBackupDAO();

    public void takeDataBackup(DataSource datasource) throws SADAREMDBException, SQLException {
        databackupdao.takeDataBackup(datasource);
    }
}
