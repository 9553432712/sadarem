/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author 484898
 */
public interface TransactionService {

    public int insertTransactionalDetails(DataSource ds, String transactionStatus, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int updateTransactionDetails(DataSource ds, String transactionStatus, String personCode) throws SADAREMDBException, SQLException;

    public int updateTransactionStatus(DataSource ds, String transactionStatus, String personCode) throws SADAREMDBException, SQLException;
    //   public ArrayList getPartAEnteredDetails(DataSource ds,String district_id) throws SADAREMDBException,SQLException;
    //  public int updatePartAEnteredDetails(DataSource ds,String personCode) throws SADAREMDBException,SQLException;
}
