/*
 * DataBackupService.java
 *
 * Created on September 22, 2008, 2:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.service;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 * This interface has an abstract method, whose implementation contains the logic
 * for taking database backup.
 * @author Pramod Kumar G
 * @version 1.0
 */
public interface DataBackupService 
{
 public void takeDataBackup(DataSource datasource) throws SADAREMDBException,SQLException;   
   
}
