/*
 * ReportService.java
 *
 * Created on July 15, 2008, 3:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.TerritoryForm;

/**
 *
 * @author svsganesh
 */
public interface ReportService {

    /** Creates a new instance of ReportService */
    public ArrayList getDetailsForCertificate(DataSource datasource, TerritoryForm territoryForm, String personcode) throws SADAREMDBException, SQLException;

    public TerritoryDTO getTeluguName(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getDetaiilsForIdCard(DataSource datasource, TerritoryForm territoryForm, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getRejectedData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getStatus(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getLocomotorData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getVisualData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getHearingData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getMetalillnessData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getMentalRetardationData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getRejectedLocomotorData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getRejectedVisualData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getRejectedHearingData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getRejectedMetalillnessData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getRejectedMentalRetardationData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getMultipleData(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    /*
     *certificate services
     */
    public ArrayList getLocomotorSubDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getVisualSubDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getHearingSubDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getMRSubDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getMISubDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getMultipleSubDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getLocomotorConditionDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getVisualConditionDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getHearingConditionDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getMRConditionDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public ArrayList getMultipleConditionDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException; 
    public String getReasonForPersonStatus(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

	public ArrayList getCatIdOfPerson(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

	public String getStatusOfPerson(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

	public String getViewmodeOfPerson(DataSource ds, String personcode) throws SADAREMDBException, SQLException;
	public ArrayList getOnlyPartADetails(String personcode);
	public ArrayList getCampDistIds(String personcode) ;
}
