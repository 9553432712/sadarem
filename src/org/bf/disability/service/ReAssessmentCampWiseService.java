/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author SADAREM
 */
public interface ReAssessmentCampWiseService {

    public ArrayList getDistrictDetials(DataSource datasource) throws SADAREMDBException, SQLException;

    public ArrayList getCampDetails(DataSource datasource, String district_id) throws SADAREMDBException, SQLException;

    public ArrayList getReAssessmentDetails(DataSource dataSource, String district_id, String camp_id) throws SADAREMDBException, SQLException;
}
