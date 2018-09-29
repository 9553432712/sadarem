/*
 * AddHabitationService.java
 *
 * Created on July 18, 2008, 4:27 PM
 *
 */

package org.bf.disability.service;
import java.util.*;
import java.io.*;
import java.sql.SQLException;
import javax.sql.*;
import javax.servlet.http.*;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.AddHabitationDAO;
import org.bf.disability.dto.AddHabitationDTO;
import org.bf.disability.form.AddHabitationForm;



/**
 * This class acts as an interface between AddHabitationAction and
 * AddHabitationDAO.
 * @author Pramod Kumar G
 */
public interface AddHabitationService 
{
    
    /**
     * Invokes the getDistrict() of DAO class.
     * @param datasource DataSource Object
     * @return ArrayList object
     */
    public  ArrayList getDistricts(DataSource datasource)throws SADAREMDBException, SQLException;
   
    /**
     * Invokes the getAssemblies() of DAO class.
     * @param datasource DataSource Object
     * @param districtid District id selected by the user
     * @return ArrayList object
     */
    public  ArrayList  getAssemblies(DataSource datasource,
            String districtid)throws SADAREMDBException,SQLException; 
    
    /**
     * Invokes the getMandals() of DAO Class.
     * @param datasource DataSource object
     * @param districtid District Id  selected by the user
     * @return ArrayList object
     */
    public  ArrayList  getMandals(DataSource datasource,
            String districtid)throws SADAREMDBException,SQLException; 
    
    /**
     * Invokes the getPanchayats() of DAO class
     * @param datasource DataSource object
     * @param districtid District id selected by the user
     * @param mandalid Mandal id selected by the user
     * @return ArrayList object
     */
    public  ArrayList getPanchayats(DataSource datasource,
            String districtid,String mandalid)throws SADAREMDBException,SQLException;
    
    /**
     * Invokes the getVillages() of DAO Class
     * @param datasource DataSource object
     * @param districtid District id selected by the user
     * @param mandalid Mandal id selected by the user
     * @param panchayatid panchayat id selected by the user
     * @return ArrayList object
     */
    public  ArrayList getVillages(DataSource datasource,
            String districtid,String mandalid,String panchayatid)throws SADAREMDBException,SQLException; 
    
    /**
     * Invokes the getHabitations() of DAO class
     * @param datasource DataSource object
     * @param districtid District Id selected by the user
     * @param mandalid Mandal Id selected by the user
     * @param panchayatid Panchayat Id selected by the user
     * @param villageid Village Id selected by the user
     * @param assemblyid Assembly Id selected by the user
     * @return String literal
     */
    public  String getHabitations(DataSource datasource,
            String districtid,String mandalid,String panchayatid,
            String villageid,String assemblyid)throws SADAREMDBException,SQLException; 
   
    public  String getVillages(DataSource datasource,
            String districtid,String mandalid)throws SADAREMDBException,SQLException; 
   
    public  String getMaxPanchayat(DataSource datasource,
            String districtid,String mandalid)throws SADAREMDBException,SQLException; 
    
    public  String getMaxMandal(DataSource datasource,
            String districtid)throws SADAREMDBException,SQLException; 
    
    public  String getMaxAssembly(DataSource datasource,
            String districtid)throws SADAREMDBException,SQLException; 
    
    public  String getMaxDistrict(DataSource datasource)
    throws SADAREMDBException,SQLException;


    /**
     * this method is used to insertCampDetails of DAO class
     * @param datasource DataSource object.
     * @param AddHabitationDTO addHabitationDTO object.
     * @return int 
     */

    public  int insertCampDetails(DataSource datasource,AddHabitationDTO addHabitationDTO,String VenueName )
    throws SADAREMDBException,SQLException;

    

    public ArrayList getRoles(DataSource datasource)throws SADAREMDBException,SQLException;

    public ArrayList getCamps(DataSource datasource,String districtid)throws SADAREMDBException,SQLException;

    public  ArrayList getCampDetails(DataSource datasource,AddHabitationDTO addHabitationDTO,String districtId)
    throws SADAREMDBException,SQLException;

   

    public  int updateCamp(DataSource datasource, int camp_id, AddHabitationForm addhabitationform)
    throws SADAREMDBException,SQLException;
    public int deleteCamp(DataSource datasource, String camp_id, String district_id) throws SADAREMDBException,SQLException;
    public  int insertLoginDetails(DataSource datasource,AddHabitationDTO territoryDTO,String username,String userName)
    throws SADAREMDBException,SQLException;
    public ArrayList getUserDetails(DataSource datasource,AddHabitationDTO addHabitationDTO, String districtid,int camp_id)throws SADAREMDBException,SQLException;
    public int updateUserDetails(DataSource datasource,AddHabitationDTO territoryDTO,String role_id,String rowid) throws SADAREMDBException,SQLException;
}//end of class
