/*
 * TerritoryService.java
 *
 * Created on June 18, 2008, 4:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.*;
import javax.sql.*;
import javax.servlet.http.*;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.TerritoryDTO;

/**
 * This interface has abstract methods, whose implementation deals with the logic of
 * retriving district details, mandal details, village details, habitation details,
 * and person code generation, generating total values as well as inseting them into 
 * a dedicated table
 * @author Deviprasad T
 * @version 1.0
 */
public interface TerritoryService {

    public ArrayList getDistricts(DataSource datasource) throws SADAREMDBException, SQLException;

    public ArrayList getMandals(DataSource datasource, String districtid) throws SADAREMDBException, SQLException;

    public ArrayList getPanchayats(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException, SQLException;

    public ArrayList getVillages(DataSource datasource, String districtid, String mandalid, String panchayatid) throws SADAREMDBException, SQLException;

    public ArrayList getHabitations(DataSource datasource, String districtid, String mandalid, String panchayatid, String villageid) throws SADAREMDBException, SQLException;

    public String getHabitationCode(String districtid, String mandalid, String panchayatid, String assemblyid, String villageid, String habitationid, DataSource datasource) throws SADAREMDBException, SQLException;

    public ArrayList getPersonCode(String districtid, String mandalid, String panchayatid, String villageid, String habitationid, String personstatus, DataSource datasource) throws SADAREMDBException, SQLException;

    public TerritoryDTO getTotalValues(DataSource datasource, String personcode) throws SADAREMDBException, SQLException;

    public int totalValueInsert(DataSource datasource, TerritoryDTO territoryDTO, LinkedList list, LinkedList list1, double totaldisability, String personcode, String loginid, String systemip, int maxMultipleId, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int totalValueUpdate(DataSource datasource, TerritoryDTO territoryDTO, LinkedList list, LinkedList list1, double totaldisability, String personcode, String loginid, String systemip, int maxMultipleId, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public ArrayList getPersonStatus(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public String getDistrictsName(DataSource datasource, String districtid) throws SADAREMDBException, SQLException;

    public ArrayList getVillageAll(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException, SQLException;

    public String getHabitationCodeRationCard(String districtid, String mandalid, String panchayatid, String assemblyid, String villageid, String habitationid, DataSource datasource) throws SADAREMDBException, SQLException;
}
