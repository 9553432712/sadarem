/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.PrintDetailsDAO;
import org.bf.disability.form.PrintDetailsResultForm;
import org.bf.disability.service.PrintDetailsService;

/**
 *
 * @author 747577
 */
public class PrintDetailsServiceImpl implements PrintDetailsService {

    PrintDetailsDAO printDetailsDAO = new PrintDetailsDAO();

    public ArrayList getCampDetails(DataSource ds,String district_id) throws SADAREMDBException, SQLException {
        ArrayList campList = new ArrayList();
        try {
            campList = printDetailsDAO.getCampDetails(ds,district_id);
        } catch (SADAREMDBException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMDBException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMDBException();
        }
        return campList;
    }

    public ArrayList getFullCampDetails(DataSource ds,PrintDetailsResultForm printDetailsResultForm) throws SADAREMDBException, SQLException {
        ArrayList campList = new ArrayList();
        try {
            campList = printDetailsDAO.getFullCampDetails(ds,printDetailsResultForm);
        } catch (SADAREMDBException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMDBException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMDBException();
        }
        return campList;
    }

    public void insertCertificatePrintDetails(DataSource ds,String sId, String cId, String cType, String lId, String campID, String distId) throws SADAREMDBException, SQLException,Exception {
        try {
            printDetailsDAO.insertCertificatePrintDetails(ds,sId, cId, cType, lId, campID, distId);
        } catch (SADAREMDBException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMDBException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMDBException();
        }
    }

    public ArrayList getPrintCertificateDetails(DataSource ds,PrintDetailsResultForm printDetailsResultForm) throws SADAREMDBException, SQLException {
        ArrayList campList = new ArrayList();
        try {
            campList = printDetailsDAO.getPrintCertificateDetails(ds,printDetailsResultForm);
        } catch (SADAREMDBException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMDBException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMDBException();
        }
        return campList;
    }

    public ArrayList getCampCountDetails(DataSource ds,PrintDetailsResultForm printDetailsResultForm) throws SADAREMDBException, SQLException{
        ArrayList campList = new ArrayList();
        try {
            campList = printDetailsDAO.getCampCountDetails(ds,printDetailsResultForm);
        } catch (SADAREMDBException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMDBException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMDBException();
        }
        return campList;
    }
}
