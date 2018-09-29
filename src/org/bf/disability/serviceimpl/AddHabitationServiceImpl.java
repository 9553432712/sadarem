/*
 * AddHabitationServiceImpl.java
 *
 * Created on September 12, 2008, 1:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.*;
import javax.sql.*;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.AddHabitationDAO;
import org.bf.disability.dto.AddHabitationDTO;
import org.bf.disability.service.AddHabitationService;
import org.bf.disability.form.AddHabitationForm;
import org.bf.disability.Exception.SADAREMException;
/**
 * This class acts as an interface between AddHabitationAction and
 * AddHabitationDAO.
 * @author Pramod Kumar G
 */
public class AddHabitationServiceImpl implements AddHabitationService {
   
    /**
     * Invokes the getDistrict() of DAO class.
     * @param datasource DataSource Object
     * @return ArrayList object
     */
    public ArrayList getDistricts(DataSource datasource)
            throws SADAREMDBException, SQLException {
        ArrayList districtlist = new ArrayList();
        AddHabitationDAO territorydao = new AddHabitationDAO();
        districtlist = territorydao.getDistricts(datasource);
        return districtlist;
    }

    /**
     * Invokes the getAssemblies() of DAO class.
     * @param datasource DataSource Object
     * @param districtid District id selected by the user
     * @return ArrayList object
     */
    public ArrayList getAssemblies(DataSource datasource,
            String districtid) throws SADAREMDBException,SQLException {
        ArrayList districtlist = new ArrayList();
        AddHabitationDAO territorydao = new AddHabitationDAO();
        districtlist = territorydao.getAssemblies(datasource, districtid);
        return districtlist;
    }

    /**
     * Invokes the getMandals() of DAO Class.
     * @param datasource DataSource object
     * @param districtid District Id  selected by the user
     * @return ArrayList object
     */
    public ArrayList getMandals(DataSource datasource,
            String districtid) throws SADAREMDBException,SQLException {
        ArrayList districtlist = new ArrayList();
        AddHabitationDAO territorydao = new AddHabitationDAO();
        districtlist = territorydao.getMandals(datasource, districtid);
        return districtlist;
    }

    /**
     * Invokes the getPanchayats() of DAO class
     * @param datasource DataSource object
     * @param districtid District id selected by the user
     * @param mandalid Mandal id selected by the user
     * @return ArrayList object
     */
    public ArrayList getPanchayats(DataSource datasource,
            String districtid, String mandalid) throws SADAREMDBException,SQLException {
        ArrayList districtlist = new ArrayList();
        AddHabitationDAO territorydao = new AddHabitationDAO();
        districtlist = territorydao.getPanchayats(datasource, districtid, mandalid);
        return districtlist;
    }

    /**
     * Invokes the getVillages() of DAO Class
     * @param datasource DataSource object
     * @param districtid District id selected by the user
     * @param mandalid Mandal id selected by the user
     * @param panchayatid panchayat id selected by the user
     * @return ArrayList object
     */
    public ArrayList getVillages(DataSource datasource,
            String districtid, String mandalid, String panchayatid) throws SADAREMDBException,SQLException {
        ArrayList districtlist = new ArrayList();
        AddHabitationDAO territorydao = new AddHabitationDAO();
        districtlist = territorydao.getVillages(datasource, districtid, mandalid, panchayatid);
        return districtlist;
    }

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
    public String getHabitations(DataSource datasource,
            String districtid, String mandalid, String panchayatid,
            String villageid, String assemblyid) throws SADAREMDBException,SQLException {
        String habitations = null;
        AddHabitationDAO territorydao = new AddHabitationDAO();
        habitations = territorydao.getHabitations(datasource, districtid, mandalid, panchayatid, villageid, assemblyid);
        return habitations;
    }

    public String getVillages(DataSource datasource,
            String districtid, String mandalid) throws SADAREMDBException,SQLException {
        String villages = null;
        AddHabitationDAO territorydao = new AddHabitationDAO();
        villages = territorydao.getVillages(datasource, districtid, mandalid);
        return villages;
    }

    public String getMaxPanchayat(DataSource datasource,
            String districtid, String mandalid) throws SADAREMDBException,SQLException {
        String panchayats = null;
        AddHabitationDAO territorydao = new AddHabitationDAO();
        panchayats = territorydao.getMaxPanchayat(datasource, districtid, mandalid);
        return panchayats;
    }

    public String getMaxMandal(DataSource datasource,
            String districtid) throws SADAREMDBException,SQLException {
        String mandals = null;
        AddHabitationDAO territorydao = new AddHabitationDAO();
        mandals = territorydao.getMaxMandal(datasource, districtid);
        return mandals;
    }

    public String getMaxAssembly(DataSource datasource,
            String districtid) throws SADAREMDBException,SQLException {
        String assembly = null;
        AddHabitationDAO territorydao = new AddHabitationDAO();
        assembly = territorydao.getMaxAssembly(datasource, districtid);
        return assembly;
    }

    public String getMaxDistrict(DataSource datasource)
            throws SADAREMDBException,SQLException {
        String district = null;
        AddHabitationDAO territorydao = new AddHabitationDAO();
        district = territorydao.getMaxDistrict(datasource);
        return district;
    }

    public int insertCampDetails(DataSource datasource, AddHabitationDTO addHabitationDTO,String VenueName) throws SADAREMDBException,SQLException {
        int i = 0;
        AddHabitationDAO territorydao = new AddHabitationDAO();
        i = territorydao.insertCampDetails(datasource, addHabitationDTO,VenueName);

        return i;
    }

    public ArrayList getCamps(DataSource datasource,
            String districtid) throws SADAREMDBException,SQLException {
        ArrayList camplist = new ArrayList();
        AddHabitationDAO territorydao = new AddHabitationDAO();
        camplist = territorydao.getCamps(datasource, districtid);
        return camplist;
    }

    public ArrayList getRoles(DataSource datasource) throws SADAREMDBException,SQLException {
        ArrayList rolelist = new ArrayList();
        AddHabitationDAO territorydao = new AddHabitationDAO();
        rolelist = territorydao.getRoles(datasource);
        return rolelist;
    }

    public ArrayList getCampDetails(DataSource datasource, AddHabitationDTO addHabitationDTO, String districtId) throws SADAREMDBException,SQLException {
        ArrayList campDetails = new ArrayList();
        AddHabitationDAO territorydao = new AddHabitationDAO();
        campDetails = territorydao.getCampDetails(datasource, addHabitationDTO, districtId);
        return campDetails;
    }

    public int updateCamp(DataSource datasource, int camp_id, AddHabitationForm addhabitationform) throws SADAREMDBException,SQLException {
        int i = 0;
        AddHabitationDAO territorydao = new AddHabitationDAO();
        i = territorydao.updateCamp(datasource, camp_id, addhabitationform);

        return i;
    }

    public int deleteCamp(DataSource datasource, String camp_id, String district_id) throws SADAREMDBException,SQLException
    {
        int i=0;
        AddHabitationDAO territorydao = new AddHabitationDAO();
        i=territorydao.deleteCamp(datasource, camp_id, district_id);
        return i;
    }
  
 public int insertLoginDetails(DataSource datasource, AddHabitationDTO territoryDTO,String username,String userName) throws SADAREMDBException,SQLException {
        int i = 0;
        AddHabitationDAO territorydao = new AddHabitationDAO();
        i = territorydao.insertLoginDetails(datasource, territoryDTO, username, userName);

        return i;
    }
 public ArrayList getUserDetails(DataSource datasource,AddHabitationDTO addHabitationDTO,String districtId,int campid_id) throws SADAREMDBException,SQLException {
        ArrayList userdetailslist = new ArrayList();
        AddHabitationDAO territorydao = new AddHabitationDAO();
        userdetailslist = territorydao.getUserDetails(datasource,addHabitationDTO,districtId, campid_id);
        return userdetailslist;
    }
 public int updateUserDetails(DataSource datasource,AddHabitationDTO territoryDTO,String role_id,String rowid) throws SADAREMDBException,SQLException {
     int i = 0;
        AddHabitationDAO territorydao = new AddHabitationDAO();
        i = territorydao.updateUserDetails(datasource,territoryDTO,role_id,rowid);

        return i;
    }

}//end of class

