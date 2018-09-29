package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.DoctorsInformationDTO;
import org.bf.disability.dto.TerritoryDTO;
/*
 * DoctorsInformationDAO.java
 *
 * Created on September 9, 2008, 11:59 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for manipulation doctor information to database
 * @author Pranod Kumar . G
 * @version 1.0
 */
public class DoctorsInformationDAO {

    /**
     *
     * @param datasource
     * @param doctorsinfodto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int insertDoctorDetails(DataSource datasource, DoctorsInformationDTO doctorsinfodto, String localaddr, String districtid_id, String loginid_id, int campid_id) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement cs = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{Call SP_tblDoctorsInformation_Details_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//            cs=con.prepareStatement("insert into tblDoctorsInformation_Details values(?,?,?,?,?,?,?,?,?,?,?,?)");
            cs.setString(1, doctorsinfodto.getTypeofdisability());
            cs.setString(2, doctorsinfodto.getSpecialistprefix());
            cs.setString(3, doctorsinfodto.getDoctorname1());
            cs.setString(4, doctorsinfodto.getRegisterno1());
            cs.setString(5, doctorsinfodto.getDesignation1());
            cs.setString(6, doctorsinfodto.getDoctorname2());
            cs.setString(7, doctorsinfodto.getRegisterno2());
            cs.setString(8, doctorsinfodto.getDesignation2());
            cs.setString(9, doctorsinfodto.getDoctorname3());
            cs.setString(10, doctorsinfodto.getRegisterno3());
            cs.setString(11, doctorsinfodto.getDesignation3());
            cs.setString(12, districtid_id);
            cs.setInt(13, campid_id);
            cs.setString(14, loginid_id);
            cs.setString(15, localaddr);
            // cs.setString(11,doctorsinfodto.getHospitalname());
            //cs.setString(12,doctorsinfodto.getHospitaladdress());
            i = cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertDoctorDetails", "DoctorsInformationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "insertDoctorDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertDoctorDetails", "DoctorsInformationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "insertDoctorDetails");
        } finally {
           DBConnection.closeStatement(cs);
           DBConnection.closeConnection(con);
        }
        return i;
    }//end of insertTransverseDetails()

    /**
     *
     * @param ds
     * @param adduser
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int addUser(DataSource ds, DoctorsInformationDTO doctorsinfodto, String districtid_id, String loginid_id, int campid_id, String localaddr, String flag) throws SADAREMDBException, SQLException {

        Connection con = null;
       // Statement stmt = null;
        PreparedStatement pstmt =null ;
        ResultSet rs = null;
        CallableStatement cs = null;
        String query = null;
        int i = 0;

        try {
            con = DBConnection.getConnection();
            //stmt = con.createStatement();
            if (flag != null) {
                query = "select UserName from Login_Details where UserName=? "; 
                
                pstmt = con.prepareStatement(query);                
                pstmt.setString(1, flag);           
                
                rs = pstmt.executeQuery(); 
                
                if (rs.next()) {
                    return i;
                } else {

                    //  con =DBConnection.getConnection();

                    con.setAutoCommit(false);
                    cs = con.prepareCall("{call SP_Login_Details_INSERT(?,?,?,?,?,?,?,?,?)}");
                    if (flag.equalsIgnoreCase("1")) {
                        cs.setString(1, doctorsinfodto.getDoctorname1());
                        cs.setString(2, doctorsinfodto.getRegisterno1());
                    }else if (flag.equalsIgnoreCase("2")) {
                        cs.setString(1, doctorsinfodto.getDoctorname2());
                        cs.setString(2, doctorsinfodto.getRegisterno2());
                    }else if (flag.equalsIgnoreCase("3")) {
                        cs.setString(1, doctorsinfodto.getDoctorname3());
                        cs.setString(2, doctorsinfodto.getRegisterno3());
                    }
                    cs.setString(3, CommonConstants.password);
                    cs.setString(4, "29");
                    cs.setString(5, districtid_id);
                    cs.setInt(6, campid_id);
                    cs.setString(7, loginid_id);
                    cs.setString(8, localaddr);
                    cs.setString(9, CommonConstants.encryptedPwd);

                    i = cs.executeUpdate();

                    con.commit();
                    con.setAutoCommit(true);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "addUser", "DoctorsInformationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "addUser");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "addUser", "DoctorsInformationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "addUser");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);
          //  DBConnection.closeStatement(stmt); 
            DBConnection.closeStatement(pstmt);

        }
        return i;
    }

    /**
     *
     * @param datasource
     * @param disabilitytype
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public DoctorsInformationDTO selectDoctorDetails(DataSource datasource, String disabilitytype, int campid_id) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement cs = null;
        ResultSet rs = null;
        DoctorsInformationDTO doctorsinfodto = new DoctorsInformationDTO();
        ;
        try {
            con = DBConnection.getConnection();
            cs = con.prepareCall("{Call SP_tblDoctorsInformation_Details_Select(?,?)}");
//       cs=con.prepareStatement("select * from tblDoctorsInformation_Details where Type_of_Disability='"+ disabilitytype +"'");
            cs.setString(1, disabilitytype);
            cs.setInt(2, campid_id);
            rs = cs.executeQuery();
            while (rs.next()) {
                //doctorsinfodto.setTypeofdisability(rs.getString(12));
                doctorsinfodto.setSpecialistprefix(rs.getString(1));
                doctorsinfodto.setDoctorname1(rs.getString(2));
                doctorsinfodto.setRegisterno1(rs.getString(3));
                doctorsinfodto.setDesignation1(rs.getString(4));
                doctorsinfodto.setDoctorname2(rs.getString(5));
                doctorsinfodto.setRegisterno2(rs.getString(6));
                doctorsinfodto.setDesignation2(rs.getString(7));
                doctorsinfodto.setDoctorname3(rs.getString(8));
                doctorsinfodto.setRegisterno3(rs.getString(9));
                doctorsinfodto.setDesignation3(rs.getString(10));
                // doctorsinfodto.setHospitalname(rs.getString(1));
                //doctorsinfodto.setHospitaladdress(rs.getString(2));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDoctorDetails", "DoctorsInformationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "selectDoctorDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDoctorDetails", "DoctorsInformationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "selectDoctorDetails");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);
           DBConnection.closeConnection(con);
        }
        return doctorsinfodto;
    }

    /**
     *
     * @param datasource
     * @param disabilitytype
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     * Update Doctors details based on person code
     */
    public DoctorsInformationDTO selectDoctors(DataSource datasource, String personCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement cs = null;
        ResultSet rs = null;
        ArrayList personStatusList = null;
        String personstatus = null;
        DoctorsInformationDTO doctorsinfodto = new DoctorsInformationDTO();
        try {
            con = DBConnection.getConnection();
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            personStatusList = territoryservice.getPersonStatus(datasource, personCode);
            Iterator iterator = personStatusList.iterator();
            iterator.hasNext();
            TerritoryDTO territoryDTO = (TerritoryDTO) iterator.next();
            personstatus = territoryDTO.getPersonstatus();
            if ("Eligible".equals(personstatus)) {
                cs = con.prepareStatement("select First_Doctor_Name,First_Doctor_RegNumber,First_Doctor_Designation,Second_Doctor_Name,Second_Doctor_RegNumber,Second_Doctor_Designation,Third_Doctor_Name,Third_Doctor_RegNumber,Third_Doctor_Designation from tblPerson_Disability_Details where Person_Code=? and Status='Active'");
                cs.setString(1,personCode );
                rs = cs.executeQuery();
                while (rs.next()) {
                    doctorsinfodto.setDoctorname1(rs.getString(1));
                    doctorsinfodto.setRegisterno1(rs.getString(2));
                    doctorsinfodto.setDesignation1(rs.getString(3));
                    doctorsinfodto.setDoctorname2(rs.getString(4));
                    doctorsinfodto.setRegisterno2(rs.getString(5));
                    doctorsinfodto.setDesignation2(rs.getString(6));
                    doctorsinfodto.setDoctorname3(rs.getString(7));
                    doctorsinfodto.setRegisterno3(rs.getString(8));
                    doctorsinfodto.setDesignation3(rs.getString(9));
                }
            } else {
                cs = con.prepareStatement("select First_Doctor_Name,First_Doctor_RegNumber,First_Doctor_Designation,Second_Doctor_Name,Second_Doctor_RegNumber,Second_Doctor_Designation,Third_Doctor_Name,Third_Doctor_RegNumber,Third_Doctor_Designation from tblRejectPerson_Details where Person_Code=? and Status='Active'");
                cs.setString(1,personCode );
                rs = cs.executeQuery();
                while (rs.next()) {
                    doctorsinfodto.setDoctorname1(rs.getString(1));
                    doctorsinfodto.setRegisterno1(rs.getString(2));
                    doctorsinfodto.setDesignation1(rs.getString(3));
                    doctorsinfodto.setDoctorname2(rs.getString(4));
                    doctorsinfodto.setRegisterno2(rs.getString(5));
                    doctorsinfodto.setDesignation2(rs.getString(6));
                    doctorsinfodto.setDoctorname3(rs.getString(7));
                    doctorsinfodto.setRegisterno3(rs.getString(8));
                    doctorsinfodto.setDesignation3(rs.getString(9));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDoctors", "DoctorsInformationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "selectDoctors");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDoctors", "DoctorsInformationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "selectDoctors");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);
           DBConnection.closeConnection(con);
        }
        return doctorsinfodto;
    }

    public DoctorsInformationDTO selectDoctors1(DataSource datasource, String personCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement cs = null;
        ResultSet rs = null;
        ArrayList personStatusList = null;
        String personstatus = null;
        DoctorsInformationDTO doctorsinfodto = new DoctorsInformationDTO();
        try {
            con = DBConnection.getConnection();
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            personStatusList = territoryservice.getPersonStatus(datasource, personCode);
            Iterator iterator = personStatusList.iterator();
            iterator.hasNext();
            TerritoryDTO territoryDTO = (TerritoryDTO) iterator.next();
            personstatus = territoryDTO.getPersonstatus();
            if ("Eligible".equals(personstatus)) {
                cs = con.prepareStatement("select First_Doctor_Name,First_Doctor_RegNumber,First_Doctor_Designation,Second_Doctor_Name,Second_Doctor_RegNumber,Second_Doctor_Designation,Third_Doctor_Name,Third_Doctor_RegNumber,Third_Doctor_Designation from tblPerson_Disability_Details where Person_Code=? and Status='Active'");                
                cs.setString(1,personCode );
                rs = cs.executeQuery();
                
                while (rs.next()) {
                    doctorsinfodto.setDoctorname1(rs.getString(1));
                    doctorsinfodto.setRegisterno1(rs.getString(2));
                    doctorsinfodto.setDesignation1(rs.getString(3));
                    doctorsinfodto.setDoctorname2(rs.getString(4));
                    doctorsinfodto.setRegisterno2(rs.getString(5));
                    doctorsinfodto.setDesignation2(rs.getString(6));
                    doctorsinfodto.setDoctorname3(rs.getString(7));
                    doctorsinfodto.setRegisterno3(rs.getString(8));
                    doctorsinfodto.setDesignation3(rs.getString(9));
                }
            } else {
                cs = con.prepareStatement("select First_Doctor_Name,First_Doctor_RegNumber,First_Doctor_Designation,Second_Doctor_Name,Second_Doctor_RegNumber,Second_Doctor_Designation,Third_Doctor_Name,Third_Doctor_RegNumber,Third_Doctor_Designation from tblRejectPerson_Details where Person_Code=? and Status='Active'");
                cs.setString(1,personCode );
                
                rs = cs.executeQuery();
                while (rs.next()) {
                    doctorsinfodto.setDoctorname1(rs.getString(1));
                    doctorsinfodto.setRegisterno1(rs.getString(2));
                    doctorsinfodto.setDesignation1(rs.getString(3));
                    doctorsinfodto.setDoctorname2(rs.getString(4));
                    doctorsinfodto.setRegisterno2(rs.getString(5));
                    doctorsinfodto.setDesignation2(rs.getString(6));
                    doctorsinfodto.setDoctorname3(rs.getString(7));
                    doctorsinfodto.setRegisterno3(rs.getString(8));
                    doctorsinfodto.setDesignation3(rs.getString(9));
                }
            }
            /*  cs = con.prepareStatement("select Railway_Certificate from tblAllDisabilityPerson_Common_Needs where person_code='"+personCode+"'");
            rs=cs.executeQuery();
            while(rs.next()){
            doctorsinfodto.setRail(rs.getString(1));
            }*/
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDoctors1", "DoctorsInformationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "selectDoctors1");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDoctors1", "DoctorsInformationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "selectDoctors1");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);
           DBConnection.closeConnection(con);
        }
        return doctorsinfodto;
    }

    /**
     * Update Doctors details based on person code
     * @param datasource
     * @param doctorsinfodto
     * @param role_id 
     * @param campid_id, 
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int updateDoctors(DataSource datasource, DoctorsInformationDTO doctorsinfodto, String personcode, int campid_id, String role_id) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement cs = null;
        ArrayList personStatusList = null;
        String personstatus = null;
        String query=""; 
        try {
            con = DBConnection.getConnection();
            TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
            personStatusList = territoryservice.getPersonStatus(datasource, personcode);
            Iterator iterator = personStatusList.iterator();
            iterator.hasNext();
            TerritoryDTO territoryDTO = (TerritoryDTO) iterator.next();
            personstatus = territoryDTO.getPersonstatus();
            if ("Eligible".equals(personstatus))
            {
            	        
                
                        if(!role_id.equals(CommonConstants.ROLE_APPELLATE))
                        {
                        	query="update tblPerson_Disability_Details set First_Doctor_Name=? , "
                                    + "First_Doctor_RegNumber=?,First_Doctor_Designation=?,"
                                    + "Second_Doctor_Name=?,Second_Doctor_RegNumber=?,"
                                    + "Second_Doctor_Designation=?,Third_Doctor_Name=?,"
                                    + "Third_Doctor_RegNumber=?,Third_Doctor_Designation=?"
                        			+ ",Hospital_Name =t.Name,Hospital_Address=t.Address,VenueName=t.VenueName "
                                    + "from tblCamp_Details t "
                                    + "where Person_Code=? and tblPerson_Disability_Details.Status='Active' and t.camp_id=?";
                        }
                        else
                        {
                        	query="update tblPerson_Disability_Details set First_Doctor_Name=? , "
                                    + "First_Doctor_RegNumber=?,First_Doctor_Designation=?,"
                                    + "Second_Doctor_Name=?,Second_Doctor_RegNumber=?,"
                                    + "Second_Doctor_Designation=?,Third_Doctor_Name=?,"
                                    + "Third_Doctor_RegNumber=?,Third_Doctor_Designation=?"
                                    + "where Person_Code=? and Status='Active' ";
                        }
                      cs = con.prepareStatement(query);  
                
                cs.setString(1,doctorsinfodto.getDoctorname1() );
                cs.setString(2,doctorsinfodto.getRegisterno1() );
                cs.setString(3,doctorsinfodto.getDesignation1() );
                cs.setString(4,doctorsinfodto.getDoctorname2() );
                cs.setString(5,doctorsinfodto.getRegisterno2() ); 
                
                cs.setString(6,doctorsinfodto.getDesignation2() );
                cs.setString(7,doctorsinfodto.getDoctorname3() );
                cs.setString(8,doctorsinfodto.getRegisterno3() );
                cs.setString(9,doctorsinfodto.getDesignation3() );
                cs.setString(10,personcode );
                if(!role_id.equals(CommonConstants.ROLE_APPELLATE))
                {
                	cs.setInt(11,campid_id );
                }              
                
                cs.executeUpdate();
            } 
            else
            {
            	
            	
            	if(!role_id.equals(CommonConstants.ROLE_APPELLATE))
                {
            		query = "update tblRejectPerson_Details set First_Doctor_Name=? , "
                            + "First_Doctor_RegNumber=?,First_Doctor_Designation=?,"
                            + "Second_Doctor_Name=?,Second_Doctor_RegNumber=?,"
                            + "Second_Doctor_Designation=?,Third_Doctor_Name=?,"
                            + "Third_Doctor_RegNumber=?,Third_Doctor_Designation=?, "
                            + "Hospital_Name =t.Name,Hospital_Address=t.Address,VenueName=t.VenueName from tblCamp_Details t "
                            + "where Person_Code=?  and tblRejectPerson_Details.Status='Active' and t.Camp_ID=?";
                }
                else
                {
                	query = "update tblRejectPerson_Details set First_Doctor_Name=? , "
                            + "First_Doctor_RegNumber=?,First_Doctor_Designation=?,"
                            + "Second_Doctor_Name=?,Second_Doctor_RegNumber=?,"
                            + "Second_Doctor_Designation=?,Third_Doctor_Name=?,"
                            + "Third_Doctor_RegNumber=?,Third_Doctor_Designation=? where Person_Code=? and Status='Active'";
                }
            	
            	
            	
                cs = con.prepareStatement(query);
                
                cs.setString(1,doctorsinfodto.getDoctorname1() );
                cs.setString(2,doctorsinfodto.getRegisterno1() );
                cs.setString(3,doctorsinfodto.getDesignation1() );
                cs.setString(4,doctorsinfodto.getDoctorname2() );
                cs.setString(5,doctorsinfodto.getRegisterno2() ); 
                
                cs.setString(6,doctorsinfodto.getDesignation2() );
                cs.setString(7,doctorsinfodto.getDoctorname3() );
                cs.setString(8,doctorsinfodto.getRegisterno3() );
                cs.setString(9,doctorsinfodto.getDesignation3() );
                cs.setString(10,personcode );
                if(!role_id.equals(CommonConstants.ROLE_APPELLATE))
                {
                	cs.setInt(11,campid_id );
                }          
                
                cs.executeUpdate();
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDoctors", "DoctorsInformationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "updateDoctors");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDoctors", "DoctorsInformationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "updateDoctors");
        } finally {
           DBConnection.closeStatement(cs);
           DBConnection.closeConnection(con);
        }
        return 0;
    }

    /**
     *
     * @param datasource
     * @param doctorsinfodto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public int updateDoctorDetails(DataSource datasource, DoctorsInformationDTO doctorsinfodto, int campid_id) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement cs = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            cs = con.prepareCall("{Call SP_tblDoctorsInformation_Details_Update(?,?,?,?,?,?,?,?,?,?,?,?)}");
//            cs=con.prepareStatement("update tblDoctorsInformation_Details set Hosptial_Name='"+ king +"'where Type_of_Disability='Mental Illness'");
            cs.setString(1, doctorsinfodto.getTypeofdisability());
            cs.setString(2, doctorsinfodto.getSpecialistprefix());
            cs.setString(3, doctorsinfodto.getDoctorname1());
            cs.setString(4, doctorsinfodto.getRegisterno1());
            cs.setString(5, doctorsinfodto.getDesignation1());
            cs.setString(6, doctorsinfodto.getDoctorname2());
            cs.setString(7, doctorsinfodto.getRegisterno2());
            cs.setString(8, doctorsinfodto.getDesignation2());
            cs.setString(9, doctorsinfodto.getDoctorname3());
            cs.setString(10, doctorsinfodto.getRegisterno3());
            cs.setString(11, doctorsinfodto.getDesignation3());
            cs.setInt(12, campid_id);
            //  cs.setString(11,doctorsinfodto.getHospitalname());
            //  cs.setString(12,doctorsinfodto.getHospitaladdress());
            i = cs.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDoctorDetails", "DoctorsInformationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "updateDoctorDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDoctorDetails", "DoctorsInformationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "updateDoctorDetails");
        } finally {
           DBConnection.closeStatement(cs);
           DBConnection.closeConnection(con);
        }
        return i;
    }

    /**
     *
     * @param datasource
     * @throws java.lang.Exception
     * @return list
     */
    public ArrayList selectDisabilityTypes(DataSource datasource) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement cs = null;
        ResultSet rs = null;
        ArrayList disabilitytype = new ArrayList();
        try {
            con = DBConnection.getConnection();
            cs = con.prepareStatement("select Disability_Name from tblDisability_Details;");
            rs = cs.executeQuery();
            while (rs.next()) {
                disabilitytype.add(rs.getString(1));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDisabilityTypes", "DoctorsInformationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "selectDisabilityTypes");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "selectDisabilityTypes", "DoctorsInformationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "selectDisabilityTypes");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);
           DBConnection.closeConnection(con);
        }
        return disabilitytype;
    }

    /**
     *
     * @param datasource
     * @param disabilitytype
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public boolean checkValidity(DataSource datasource, String disabilitytype, int campid_id) throws SADAREMDBException, SQLException {
        boolean code = false;
        Connection con = null;
        PreparedStatement cs = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            cs = con.prepareCall("{Call SP_tblDoctorsInformation_Details_Select(?,?)}");
//       cs=con.prepareStatement("select * from tblDoctorsInformation_Details where Type_of_Disability='"+ disabilitytype +"'");
            cs.setString(1, disabilitytype);
            cs.setInt(2, campid_id);
            rs = cs.executeQuery();
            if (rs.next() == false) {
                code = false;
            } else {
                code = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "checkValidity", "DoctorsInformationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "checkValidity");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "checkValidity", "DoctorsInformationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "checkValidity");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);
           DBConnection.closeConnection(con);
        }
        return code;
    }

    public DoctorsInformationDTO getDoctorDetailsforPartA(DataSource datasource, String disabilitytype) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement cs = null;
        ResultSet rs = null;
        DoctorsInformationDTO doctorsinfodto = null;
        try {
            con = DBConnection.getConnection();
            cs = con.prepareCall("{Call SP_tblDoctorsInformation_Details_Select(?)}");
//       cs=con.prepareStatement("select * from tblDoctorsInformation_Details where Type_of_Disability='"+ disabilitytype +"'");
            cs.setString(1, disabilitytype);
            rs = cs.executeQuery();
            while (rs.next()) {
                doctorsinfodto = new DoctorsInformationDTO();
                doctorsinfodto.setTypeofdisability(rs.getString(12));
                doctorsinfodto.setDoctorname1(rs.getString(3));
                doctorsinfodto.setRegisterno1(rs.getString(4));
                doctorsinfodto.setDesignation1(rs.getString(5));
                doctorsinfodto.setDoctorname2(rs.getString(6));
                doctorsinfodto.setRegisterno2(rs.getString(7));
                doctorsinfodto.setDesignation2(rs.getString(8));
                doctorsinfodto.setDoctorname3(rs.getString(9));
                doctorsinfodto.setRegisterno3(rs.getString(10));
                doctorsinfodto.setDesignation3(rs.getString(11));
                doctorsinfodto.setHospitalname(rs.getString(1));
                doctorsinfodto.setHospitaladdress(rs.getString(2));
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDoctorDetailsforPartA", "DoctorsInformationDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "getDoctorDetailsforPartA");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDoctorDetailsforPartA", "DoctorsInformationDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DoctorsInformationDAO", "getDoctorDetailsforPartA");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);
           DBConnection.closeConnection(con);
        }
        return doctorsinfodto;
    }
}
