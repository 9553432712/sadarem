/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.TransactionDAO;
import org.bf.disability.service.TransactionService;

/**
 *
 * @author 484898
 */
public class TransactionImpl implements TransactionService {

    public int insertTransactionalDetails(DataSource ds, String transactionStatus, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        TransactionDAO transactionDAO = new TransactionDAO();
        insertStatus = transactionDAO.insertTransactionalDetails(ds, transactionStatus, person_code, request);
        return insertStatus;
    }

    public int updateTransactionDetails(DataSource ds, String transactionStatus, String personCode) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        TransactionDAO transactionDAO = new TransactionDAO();
        insertStatus = transactionDAO.updateTransactionDetails(ds, transactionStatus, personCode);
        return insertStatus;
    }

    public int updateTransactionStatus(DataSource ds, String transactionStatus, String personCode) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        TransactionDAO transactionDAO = new TransactionDAO();
        insertStatus = transactionDAO.updateTransactionStatus(ds, transactionStatus, personCode);
        return insertStatus;
    }

    /*  public ArrayList getPartAEnteredDetails(DataSource ds, String district_id) throws SADAREMDBException,SQLException {
    ArrayList partAIds = new ArrayList();
    TransactionDAO transactionDAO = new TransactionDAO();
    partAIds = transactionDAO.getPartAEnteredDetails(ds, district_id);
    return partAIds;
    }

    public int updatePartAEnteredDetails(DataSource ds, String personCode) throws SADAREMDBException,SQLException {
    int insertStatus = 0;
    TransactionDAO transactionDAO = new TransactionDAO();
    insertStatus = transactionDAO.updatePartAEnteredDetails(ds, personCode);
    return insertStatus;
    }*/
}
