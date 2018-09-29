package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

// import org.apache.bcel.generic.BREAKPOINT;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.AddHabitationDTO;
import org.bf.disability.form.AddHabitationForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 * 
 * This DAO deals with Retrieving the information like:
 * District Id List, District Name, District Id;
 * Assembly Id List, Assembly Name, Assembly Id;Mandal Id List, Mandal Name, Mandal Id;
 * * Panchayat Id List, Panchayat  Name, Panchayat  Id;
 * Village Id List, Village Name, Village Id;
 * It has a method to retrieve the maximum habitaton_id in the given
 * combination.
 * It has a method to insert the details about new habitation.
 * @author Pramod Kumar.G
 * @version 1.0
 */
public class AddHabitationDAO {

    /**
     * This method returns the list of availble districts from the database.
     * 
     * @return ArrayList Object
     * @param datasource DataSource Object
     * @throws java.lang.Exception 
     */
    public static ArrayList getDistricts(DataSource datasource) throws SADAREMDBException, SQLException {

        Connection con = null;
        // PreparedStatement pstmt=null;
        Statement stmt = null;
        ArrayList districtlist = new ArrayList();
        ResultSet rs = null;

        try {
            //con =DBConnection.getConnection();
            con = DBConnection.getConnection();
            // this query gets district id and district name



            String query = "select District_ID ,District_Name from tblDistrict_Details order by District_Name";
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AddHabitationDTO territoryDTO = new AddHabitationDTO();
                territoryDTO.setDistrict_id((rs.getString("District_ID")));
                territoryDTO.setDistrict_name((rs.getString("District_Name")));
                districtlist.add(territoryDTO);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDistricts", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getDistricts");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDistricts", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getDistricts");
        }//end of catch block
        finally {

            try {
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(stmt);

        }//end of finally block
        return districtlist;
    }//end of getDistricts()

    /**
     * This method returns the list of availble assemblies from the database, based
     * on the district selected by the user.
     * @param datasource DataSource object
     * @param districtid Id of the District selected by the user.
     * @return ArrayList object.
     */
    public static ArrayList getAssemblies(DataSource datasource,
            String districtid) throws SADAREMDBException, SQLException {

        Connection con = null;
          PreparedStatement pstmt=null;
        Statement stmt = null;
        ArrayList assemblylist = new ArrayList();
        ResultSet rs = null;
        AddHabitationDTO territory;
        try {
            // con =DBConnection.getConnection();
            con = DBConnection.getConnection();

            // this query gets cluster id and cluster name
            String query = "select Assembly_ID,Assembly_Name from tblAssembly_Details where District_ID=? order by Assembly_Name";
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, districtid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                territory = new AddHabitationDTO();
                territory.setAssembly_id(rs.getString("Assembly_ID"));
                territory.setAssembly_name(rs.getString("Assembly_Name")); 
              
                assemblylist.add(territory);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getAssemblies", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getAssemblies");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getAssemblies", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getAssemblies");
        } //end of catch block
        finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstmt != null) {
                	pstmt.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(stmt);

        } 
        
        return assemblylist;
    }//end of getAssemblies()

    /**
     * This method returns the list of availble mandals from the database, based
     * on the district selected by the user.
     * @param datasource DataSource Object
     * @param districtid Id of the District selected by the user.
     * @return ArrayList Object
     */
    public static ArrayList getMandals(DataSource datasource,
            String districtid) throws SADAREMDBException, SQLException {
        Connection con = null;
      PreparedStatement pstmt=null;
        Statement stmt = null;
        ArrayList mandallist = new ArrayList();
        ResultSet rs = null;
        AddHabitationDTO territory;
        try {
            //  con =DBConnection.getConnection();
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select Mandal_ID,Mandal_Name from tblMandal_Details where District_ID=? order by Mandal_Name";
 
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, districtid);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                territory = new AddHabitationDTO();
                territory.setMandal_id(rs.getString("Mandal_ID"));
                territory.setMandal_name(rs.getString("Mandal_Name")); 
                
                mandallist.add(territory);
            }
        }//end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandals", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getMandals");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandals", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getMandals");
        } //end of catch block
        finally {

            try {
                if (con != null) {
                    con.close();
                }
                if (pstmt != null) {
                	pstmt.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(stmt);

        }
        return mandallist;
    }//end of getMandals()

    /**
     * This method returns the list of availble Panchayats from the database, based
     * on the district and mandal values selected by the user.
     * @param datasource DataSource Object
     * @param districtid Id of the District selected by the user.
     * @param mandalid Id of the Mandal selected by the user.
     * @return ArrayList Object
     */
    public static ArrayList getPanchayats(DataSource datasource,
            String districtid, String mandalid) throws SADAREMDBException, SQLException {
        Connection con = null;
         PreparedStatement pstmt=null;
        Statement stmt = null;
        ArrayList panchayatlist = new ArrayList();
        ResultSet rs = null;
        AddHabitationDTO territory;
        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select Panchayat_ID,Panchayat_Name from  tblPanchayat_Details where District_ID=? and  Mandal_ID=? order by Panchayat_Name";
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                territory = new AddHabitationDTO();
                territory.setPanchayat_id(rs.getString("Panchayat_ID"));
                territory.setPanchayat_name(rs.getString("Panchayat_Name"));
             
                panchayatlist.add(territory);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPanchayats", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getPanchayats");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPanchayats", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getPanchayats");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(stmt);

        }
        return panchayatlist;
    }//end of getPanchayats()

    /**
     * This method returns the list of availble villages from the database, based
     * on the district and mandal values selected by the user.
     * @param datasource DataSource Object
     * @param districtid Id of the District selected by the user.
     * @param mandalid Id of the Mandal selected by the user.
     * @param panchayatid Id of the Panchayat selected by the user.
     * @return ArrayList Object
     */
    public static ArrayList getVillages(DataSource datasource,
            String districtid, String mandalid, String panchayatid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
         PreparedStatement pstmt=null;
        //Statement stmt = null;
        ArrayList villagelist = new ArrayList();
        ResultSet rs = null;
        AddHabitationDTO territory;
        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select Village_ID,Village_Name from tblVillage_Details where District_ID=? and  Mandal_ID=? order by Village_Name";
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                territory = new AddHabitationDTO();
                territory.setVillage_id(rs.getString("Village_ID"));
                territory.setVillage_name(rs.getString("Village_Name")); 
            
                villagelist.add(territory);
            }
        }//end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillages", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillages", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getVillages");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           DBConnection.closeStatement(pstmt);
           DBConnection.closeResultSet(rs);

        }

        return villagelist;
    }//end of getVillages()

    /**
     * Gets the maximum habitation_id from the database, based on the 
     * given values.
     * @param datasource DataSource Object
     * @param districtid Id of the District selected by the user.
     * @param mandalid Id of the Mandal selected by the user.
     * @param panchayatid Id of the Panchayat selected by the user.
     * @param villageid Id of the Village selected by the user.
     * @param assemblyid Id of the Assembly selected by the user.
     * @return String literal
     */
    public static String getHabitations(DataSource datasource,
            String districtid, String mandalid, String panchayatid,
            String villageid, String assemblyid) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt=null;
        Statement stmt = null;
        // ArrayList habitationlist= new ArrayList();
        String habitation_id = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select max(Habitation_ID) from tblHabitation_Details where District_ID=? and Assembly_ID=? and Mandal_ID=? and Village_ID=? and Panchayat_ID=?";

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, districtid);
            pstmt.setString(2, assemblyid);
            pstmt.setString(3, mandalid);
            pstmt.setString(4, villageid);
            pstmt.setString(5, panchayatid);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                habitation_id = rs.getString(1);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitations", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getHabitations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitations", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getHabitations");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);

        }

        return habitation_id;
    }//end of getHabitations()

    /**
     * 
     * @param datasource 
     * @param districtid 
     * @param mandalid 
     * @throws java.lang.Exception 
     * @return maxid
     */
    public static String getVillages(DataSource datasource,
            String districtid, String mandalid) throws SADAREMDBException, SQLException {
        Connection con = null;
       PreparedStatement pstmt=null;
        Statement stmt = null;
        // ArrayList habitationlist= new ArrayList();
        String village_id = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            // this query gets cluster id and cluster name
            String query = "select max(Village_ID) from tblVillage_Details where District_ID=? and Mandal_ID=?";

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                village_id = rs.getString(1);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillages", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillages", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getVillages");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);

        }

        return village_id;
    }//end of getVillages()

    /**
     * 
     * @param datasource 
     * @param districtid 
     * @param mandalid 
     * @throws java.lang.Exception 
     * @return max id
     */
    public static String getMaxPanchayat(DataSource datasource,
            String districtid, String mandalid) throws SADAREMDBException, SQLException {
        Connection con = null;
         PreparedStatement pstmt=null;
        Statement stmt = null;
        // ArrayList habitationlist= new ArrayList();
        String panchayat_id = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            // this query gets cluster id and cluster name
            String query = "select max(Panchayat_ID) from tblPanchayat_Details where District_ID=? and Mandal_ID=?";

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                panchayat_id = rs.getString(1);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMaxPanchayat", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getMaxPanchayat");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMaxPanchayat", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getMaxPanchayat");
        } finally {

            try {
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);

        }

        return panchayat_id;
    }//end of getMaxPanchayat()

    /**
     * 
     * @param datasource 
     * @param districtid 
     * @throws java.lang.Exception 
     * @return max id
     */
    public static String getMaxMandal(DataSource datasource,
            String districtid) throws SADAREMDBException, SQLException {
        Connection con = null;
      PreparedStatement pstmt=null;
        Statement stmt = null;
        // ArrayList habitationlist= new ArrayList();
        String mandal_id = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select max(Mandal_ID) from tblMandal_Details where District_ID=?";

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, districtid);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                mandal_id = rs.getString(1);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMaxMandal", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getMaxMandal");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMaxMandal", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getMaxMandal");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);

        }

        return mandal_id;
    }//end of getMaxMandal()

    /**
     * 
     * @param datasource 
     * @param districtid 
     * @throws java.lang.Exception 
     * @return max id
     */
    public static String getMaxAssembly(DataSource datasource,
            String districtid) throws SADAREMDBException, SQLException {
        Connection con = null;
         PreparedStatement pstmt=null;
        Statement stmt = null;
        // ArrayList habitationlist= new ArrayList();
        String assembly_id = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select max(Assembly_ID) from tblAssembly_Details where District_ID=?";

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, districtid);


            rs = pstmt.executeQuery();

            while (rs.next()) {
                assembly_id = rs.getString(1);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMaxAssembly", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getMaxAssembly");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMaxAssembly", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getMaxAssembly");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);

        }

        return assembly_id;
    }//end of getMaxAssembly()

    /**
     * This method inserts the new habitation details into the database.
     * @param datasource DataSource name
     * @param addHabitationDTO AddHabitationDTO object
     */
    public static int insertHabitationDetails(DataSource datasource,
            AddHabitationDTO addHabitationDTO) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        //  Statement st=null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{Call SP_tblHabitation_Details_Insert(?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, addHabitationDTO.getDistrict_id());
            cs.setString(2, addHabitationDTO.getAssembly_id());
            cs.setString(3, addHabitationDTO.getMandal_id());
            cs.setString(4, addHabitationDTO.getVillage_id());
            cs.setString(5, addHabitationDTO.getHabitation_id());
            cs.setString(6, addHabitationDTO.getPanchayat_id());
            cs.setString(7, addHabitationDTO.getHabitation_code());
            cs.setString(8, addHabitationDTO.getHabitation_name());
            cs.setString(9, addHabitationDTO.getSystem_ip());

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertHabitationDetails", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertHabitationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertHabitationDetails", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertHabitationDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);

        }
        return 0;
    }//end of insertHabitationDetails()

    /**
     * 
     * @param datasource 
     * @param addHabitationDTO 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @throws java.sql.SQLException 
     * @return 
     */
    public static int insertVillageDetails(DataSource datasource,
            AddHabitationDTO addHabitationDTO) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        // Statement st=null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{Call SP_tblVillage_Details_Insert(?,?,?,?,?)}");

            cs.setString(1, addHabitationDTO.getDistrict_id());
            cs.setString(2, addHabitationDTO.getMandal_id());
            cs.setString(3, addHabitationDTO.getVillage_id());
            cs.setString(4, addHabitationDTO.getVillage_name());
            cs.setString(5, addHabitationDTO.getSystem_ip());

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            con.rollback();
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertVillageDetails", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertVillageDetails");
        } catch (Exception sqlEx) {
            con.rollback();
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertVillageDetails", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertVillageDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);

        }
        return 0;
    }//end of insertVillageDetails()

    /**
     * 
     * @param datasource 
     * @param addHabitationDTO 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public static int insertPanchayatDetails(DataSource datasource,
            AddHabitationDTO addHabitationDTO) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        // Statement st=null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{Call SP_tblPanchayat_Details_Insert(?,?,?,?,?)}");

            cs.setString(1, addHabitationDTO.getDistrict_id());
            cs.setString(2, addHabitationDTO.getMandal_id());
            cs.setString(3, addHabitationDTO.getPanchayat_id());
            cs.setString(4, addHabitationDTO.getPanchayat_name());
            cs.setString(5, addHabitationDTO.getSystem_ip());

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertPanchayatDetails", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertPanchayatDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertPanchayatDetails", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertPanchayatDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);

        }
        return 0;
    }//end of insertPanchayatDetails()

    /**
     * 
     * @param datasource 
     * @param addHabitationDTO 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public static int insertMandalDetails(DataSource datasource,
            AddHabitationDTO addHabitationDTO) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        // Statement st=null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{Call SP_tblMandal_Details_Insert(?,?,?,?)}");

            cs.setString(1, addHabitationDTO.getDistrict_id());
            cs.setString(2, addHabitationDTO.getMandal_id());
            cs.setString(3, addHabitationDTO.getMandal_name());
            cs.setString(4, addHabitationDTO.getSystem_ip());

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertMandalDetails", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertMandalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertMandalDetails", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertMandalDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);

        }
        return 0;
    }//end of insertMandalDetails()

    /**
     * 
     * @param datasource 
     * @param addHabitationDTO 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public static int insertAssemblyDetails(DataSource datasource,
            AddHabitationDTO addHabitationDTO) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        //  Statement st=null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{Call SP_tblAssembly_Details_Insert(?,?,?,?)}");

            cs.setString(1, addHabitationDTO.getDistrict_id());
            cs.setString(2, addHabitationDTO.getAssembly_id());
            cs.setString(3, addHabitationDTO.getAssembly_name());
            cs.setString(4, addHabitationDTO.getSystem_ip());

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertAssemblyDetails", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertAssemblyDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertAssemblyDetails", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertAssemblyDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);

        }
        return 0;
    }//end of insertAssemblyDetails()

    /**
     * 
     * @param datasource 
     * @param addHabitationDTO 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public static int insertDistrictDetails(DataSource datasource,
            AddHabitationDTO addHabitationDTO) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        // Statement st=null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{Call SP_tblDistrict_Details_Inserts(?,?,?)}");

            cs.setString(1, addHabitationDTO.getDistrict_id());
            cs.setString(2, addHabitationDTO.getDistrict_name());
            cs.setString(3, addHabitationDTO.getSystem_ip());

            cs.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertDistrictDetails", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertDistrictDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertDistrictDetails", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertDistrictDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);

        }
        return 0;
    }//end of insertDistrictDetails()

    /**
     * 
     * @param datasource 
     * @throws java.lang.Exception 
     * @return max id
     */
    public static String getMaxDistrict(DataSource datasource) throws SADAREMDBException, SQLException {
        Connection con = null;
        // PreparedStatement pstmt=null;
        Statement stmt = null;
        // ArrayList habitationlist= new ArrayList();
        String district_id = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select max(District_ID) from tblDistrict_Details";

            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                district_id = rs.getString(1);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMaxDistrict", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getMaxDistrict");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMaxDistrict", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getMaxDistrict");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(stmt);

        }

        return district_id;
    }//end of getMaxDistrict()

    /**
     *
     * @param datasource
     * @param addHabitationDTO
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public static int insertCampDetails(DataSource datasource,
            AddHabitationDTO addHabitationDTO, String VenueName) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String query = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
           
            query = "select VenueName from dbo.tblCamp_Details where VenueName=? ";
            prst = con.prepareStatement(query);
            prst.setString(1, VenueName);
            
            rs = prst.executeQuery();
            if (rs.next()) {
                return i;
            } else {
                con = DBConnection.getConnection();
                con.setAutoCommit(false);
                cs = con.prepareCall("{Call SP_tblCamp_Details_INSERT(?,?,?,?,?,?)}");

                cs.setString(1, addHabitationDTO.getDistrict_id());
                cs.setString(2, addHabitationDTO.getCamp_name());
                cs.setString(3, addHabitationDTO.getCamp_address());
                cs.setString(4, addHabitationDTO.getCamp_venue());
                cs.setString(5, addHabitationDTO.getLogin_id());
                cs.setString(6, addHabitationDTO.getSystem_ip());


                i = cs.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertCampDetails", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertCampDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertCampDetails", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertCampDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);
           DBConnection.closeStatement(prst);

        }
        return i;
    }

    public static ArrayList getCamps(DataSource datasource,
            String districtid) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt=null;
        Statement stmt = null;
        ArrayList camplist = new ArrayList();
        ResultSet rs = null;
        AddHabitationDTO territory;
        try {
            con = DBConnection.getConnection();

            // this query gets cluster id and cluster name
            String query = "select Camp_ID,VenueName from tblCamp_Details where District_ID=? order by Name"; 
            
            
            pstmt = con.prepareStatement(query);
            	pstmt.setString(1, districtid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                territory = new AddHabitationDTO();
                territory.setCamp_id(rs.getInt("Camp_ID"));
                territory.setCamp_name(rs.getString("VenueName"));
                camplist.add(territory);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getCamps", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getCamps");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getCamps", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getCamps");
        } //end of catch block
        finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);

        }
        return camplist;
    }//end of getCamps()

    public static ArrayList getRoles(DataSource datasource) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList rolelist = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets district id and district name
            String query = "select role_id, role_name from roles order by role_name";
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AddHabitationDTO territoryDTO = new AddHabitationDTO();
                territoryDTO.setRole_id((rs.getInt("role_id")));
                territoryDTO.setRole_name((rs.getString("role_name")));
                rolelist.add(territoryDTO);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getRoles", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getRoles");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getRoles", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getRoles");
        }//end of catch block
        finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(stmt);

        }//end of finally block
        return rolelist;
    }//end of getRoles()

    public static int insertLoginDetails(DataSource datasource,
            AddHabitationDTO addHabitationDTO, String username, String userName) throws SADAREMDBException, SQLException {
        int i = 0;

        Connection con = null;
        CallableStatement cs = null;
        PreparedStatement pstmt = null ;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
           
            query = "select UserName from Login_Details where UserName=? ";
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, userName);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return i;
            } else {
                con.setAutoCommit(false);
                cs = con.prepareCall("{Call SP_Login_Details_INSERT(?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, addHabitationDTO.getPerson_name());
                cs.setString(2, addHabitationDTO.getUser_name());
                cs.setString(3, addHabitationDTO.getPassword());
                cs.setInt(4, addHabitationDTO.getRole_id());
                cs.setString(5, addHabitationDTO.getDistrict_id());
                cs.setInt(6, addHabitationDTO.getCamp_id());
                cs.setString(7, username);
                cs.setString(8, addHabitationDTO.getSystem_ip());
                cs.setString(9, addHabitationDTO.getEncrptPwd());
                i = cs.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertLoginDetails", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertLoginDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertLoginDetails", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "insertLoginDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);

        }
        return i;
    }

    public ArrayList getCampDetails(DataSource datasource, AddHabitationDTO addHabitationDTO, String districtId) throws SADAREMDBException, SQLException {
        Connection con = null;

        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null ;
        ArrayList campDetailsList = null;
        try {
            campDetailsList = new ArrayList();
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
           
            String query = "select Camp_ID,Name,Address,VenueName from tblCamp_Details where District_ID=?";
            
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, districtId);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {

                addHabitationDTO = new AddHabitationDTO();
                addHabitationDTO.setCamp_id(rs.getInt("Camp_ID"));
                String x = rs.getString("Camp_ID");
                addHabitationDTO.setCamp_name(rs.getString("Name"));
                addHabitationDTO.setCamp_address(rs.getString("Address"));
                addHabitationDTO.setCamp_venue(rs.getString("VenueName"));

                campDetailsList.add(addHabitationDTO);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getCampDetails", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getCampDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getCampDetails", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getCampDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(st);

        }
        return campDetailsList;
    }

    public static int updateCamp(DataSource datasource, int camp_id, AddHabitationForm addhabitationform) throws SADAREMDBException, SQLException {
        Connection con = null;

        Statement st = null;
        PreparedStatement pstmt = null ;


        try {

            String Name = addhabitationform.getCamp_name();
            String Address = addhabitationform.getCamp_address();
            String VenueName = addhabitationform.getCamp_venue();
            String Loginid = addhabitationform.getLogin_id();
            String Systemip = addhabitationform.getSystem_ip();
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            String query = "UPDATE tblCamp_Details SET Name=?,Address=?, VenueName=?, Login_ID=? , System_IP_Address=?  WHERE Camp_ID=?";
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, Name);
            pstmt.setString(2, Address);
            pstmt.setString(3, VenueName);
            pstmt.setString(4, Loginid);
            pstmt.setString(5, Systemip);
            pstmt.setString(6, camp_id+"");
            
            pstmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateCamp", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "updateCamp");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateCamp", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "updateCamp");
        } finally {
           DBConnection.closeConnection(con);

           DBConnection.closeStatement(pstmt);

        }
        return 0;
    }

    public static int deleteCamp(DataSource datasource, String camp_id, String district_id) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null ;

        int i = 0;
        try {
            con = DBConnection.getConnection();
            
            String query = "delete from dbo.tblCamp_Details where Camp_ID=? ";
            
            pstmt = con.prepareStatement(query);            
            pstmt.setString(1, camp_id);
            
            i = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "deleteCamp", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "deleteCamp");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "deleteCamp", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "deleteCamp");
        } finally {

           DBConnection.closeConnection(con);
           DBConnection.closeStatement(st);

        }
        return i;
    }

    public static ArrayList getUserDetails(DataSource datasource, AddHabitationDTO addHabitationDTO, String districtId, int campid_id) throws SADAREMDBException, SQLException {
        Connection con = null;
        //  PreparedStatement pstmt=null;
        Statement stmt = null;
        PreparedStatement pstmt = null ;
        ArrayList userdetailslist = new ArrayList();
        ResultSet rs = null;
        AddHabitationDTO territory;
        try {
            con = DBConnection.getConnection();

            // this query gets cluster id and cluster name
            String query = "select UserName,RowID from Login_Details where camp_id=? and district_ID=?";
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, campid_id+"");
            pstmt.setString(2, districtId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                territory = new AddHabitationDTO();
                territory.setUser_name(rs.getString("UserName"));
                territory.setRowid(rs.getString("RowID"));
                userdetailslist.add(territory);
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getUserDetails", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getUserDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getUserDetails", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "getUserDetails");
        } //end of catch block
        finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);

        }
        return userdetailslist;
    }//end of getCamps()

    public static int updateUserDetails(DataSource datasource, AddHabitationDTO territoryDTO, String role_id, String rowid) throws SADAREMDBException, SQLException {
        Connection con = null;

        Statement st = null;
        PreparedStatement pstmt = null ;
        int i = 0;

        try {
            String user_name = territoryDTO.getUser_name();
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
           // st = con.createStatement();
            String query = "UPDATE Login_Details SET role_id=?  WHERE RowID=?  ";
            
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, role_id);
            pstmt.setString(2, rowid);            
            i = pstmt.executeUpdate();
            
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateUserDetails", "AddHabitationDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "updateUserDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateUserDetails", "AddHabitationDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddHabitationDAO", "updateUserDetails");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeStatement(pstmt);

        }

        return i;
    }
}//end of class

