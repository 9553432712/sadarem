/*
 * TransverseService.java
 *
 * Created on July 1, 2008, 10:55 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.TransverseDTO;

/**
 * This class deals with the methods to invoke the methods 
 * that are availble in TransverseDAO class.
 */
public interface TransverseService {

    /**
     * Service method that invokes the insertTransverseDetails() by passing
     * passing DataSource Object and TransverseDTO object
     */
    public int insertTransverseDetails(DataSource datasource, TransverseDTO transversedto, HttpServletRequest request)
            throws SADAREMDBException, SQLException;

    /**
     * Service method that invokes the insertTransverseDetails() by passing
     * passing DataSource Object and TransverseDTO object
     */
    public int insertTransverseDetailsAU(DataSource datasource, TransverseDTO transversedto, HttpServletRequest request)
            throws SADAREMDBException, SQLException;

    /**
     * Service method that invokes the insertTransverseDetails() by passing
     * passing DataSource Object and Person_Code
     */
    public TransverseDTO getTransverseDetails(DataSource datasource, String personcode)
            throws SADAREMDBException, SQLException;

    /**
     * Service method that invokes the insertTransverseDetails() by passing
     * passing DataSource Object and TransverseDTO object
     */
    public void updateTransverseDetails(DataSource datasource, TransverseDTO transverseupdatedto, HttpServletRequest request)
            throws SADAREMDBException, SQLException;
}
