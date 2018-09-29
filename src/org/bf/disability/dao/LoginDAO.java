package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.dto.LoginDTO;
import org.bf.disability.form.ShgloginForm;

import com.tcs.sadarem.util.PasswordEncriptDecrypt;
import com.tcs.sgv.common.util.DBConnection;

/**
 * this is class is for manipulating on login privilage from database
 * @author Devi Prasad .T
 * @version 1.0
 */
public class LoginDAO {

    /** Creates a new instance of SHGDAO */
    public LoginDAO() {
    }
    //Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    

    /**
     * 
     * @param ds 
     * @param userid 
     * @param password 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public LoginDTO getValidUser(DataSource ds, String userid, String password) throws SADAREMException, SQLException {


        ResultSet rs = null;
        //Statement stmt = null;
        LoginDTO logindto = null;  
        PreparedStatement pstmt = null;
        Connection conn = null;
        String DecPassword=null;
        try {
            DecPassword = password.substring(5, password.length() - 5); 
            conn = DBConnection.getConnection(); 
            //stmt = conn.createStatement(); 
            
             
            String SQL = "select UserName,role_id,District_ID,Camp_ID, \n"
            		+ " UserStatus,mandal_id,Status,rtrim(password_flag) password_flag,PersonName,contactno,email,is_active \n"
            		+ " from Login_Details "
                    + "where (UserName = ? OR user_id =? ) and is_active='Y'  ";
//            if (pwd_flag != null && pwd_flag.equalsIgnoreCase("EY") && role!=CommonConstants.ENCRPT_PASSWORD_NOTREQUIRED_IDS) {
                SQL = SQL + " and Encrypted_password=? ";
//            } else {
//                SQL = SQL + " and Password='" + password + "'";
//            }
                
                pstmt =conn.prepareStatement(SQL);
                pstmt.setString(1, userid); ;
                pstmt.setString(2, userid); 
               pstmt.setString(3, DecPassword); 
                
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                logindto = new LoginDTO();
                logindto.setUserStatus(rs.getBoolean("UserStatus"));
                if (!rs.getBoolean("UserStatus")) 
                {
                    logindto.setUserName(rs.getString("UserName"));
                    logindto.setRoleId(rs.getInt("role_id"));
                    logindto.setDistrictId(rs.getString("District_ID"));
                    logindto.setCampId(rs.getInt("Camp_ID"));
                    logindto.setMandal_Id(rs.getString("mandal_id"));
                    logindto.setStatus(rs.getString("Status"));
                    logindto.setPwdFlag(rs.getString("password_flag"));
                    logindto.setPersonName(rs.getString("PersonName"));
                    logindto.setContact_no(rs.getString("contactno"));
                    logindto.setEmail_id(rs.getString("email"));
                }
                //results1=r.getString(1);
                //result=true;
            }
            if (logindto != null) 
            {
                CommonDetails commondetails = new CommonDetails();
                boolean RoleIdFlag = commondetails.campareRoleIds(String.valueOf(logindto.getRoleId()), CommonConstants.PERMISSIONS_FOR_OTHERRPERSONLOGIN);
                if (!logindto.isUserStatus() &&  RoleIdFlag==true) {
                    String SQLQuery = "update Login_Details set UserStatus='True' where ( UserName = ? OR user_id = ?) and Status='Active' ";
//                    if (pwd_flag != null && pwd_flag.equalsIgnoreCase("EY")) {
                        SQLQuery = SQLQuery + " and Encrypted_password=?";
//                    } else {
//                        SQLQuery = SQLQuery + " and Password='" + password + "'";
//                    }
                        
                        pstmt =conn.prepareStatement(SQLQuery);
                        pstmt.setString(1, userid); 
                        pstmt.setString(2, userid.trim()); 
                        pstmt.setString(3, DecPassword); 
                        pstmt.executeUpdate();
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidUser", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getValidUser");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidUser", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getValidUser");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(pstmt);
        }
        return logindto;
    }

    /**
     * 
     * @param ds 
     * @param userid 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public LoginDTO getUserDetails(DataSource ds, String userid) throws SADAREMException, SQLException {


        ResultSet rs = null;
       // Statement stmt = null;
        PreparedStatement pstmt =null ;
        LoginDTO logindto = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
         
            String SQL = "select UserName,role_id,District_ID,Camp_ID,UserStatus,mandal_id,Status,rtrim(password_flag) password_flag from Login_Details "
                    + "where UserName = ?";
            
            pstmt = conn.prepareStatement(SQL);
            
            pstmt.setString(1,userid);
            
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                logindto = new LoginDTO();
                logindto.setUserStatus(rs.getBoolean("UserStatus"));
                if (!rs.getBoolean("UserStatus")) {
                    logindto.setUserName(rs.getString("UserName"));
                    logindto.setRoleId(rs.getInt("role_id"));
                    logindto.setDistrictId(rs.getString("District_ID"));
                    logindto.setCampId(rs.getInt("Camp_ID"));
                    logindto.setMandal_Id(rs.getString("mandal_id"));
                    logindto.setStatus(rs.getString("Status"));
                    logindto.setPwdFlag(rs.getString("password_flag"));
                }
                //results1=r.getString(1);
                //result=true;
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUserDetails", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getUserDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUserDetails", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getUserDetails");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt); 
            DBConnection.closeStatement(pstmt);

        }
        return logindto;
    }

    /**
     *
     * @param ds
     * @param userid
     * @param password
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public LoginDTO getRDCallCenterValidUser(DataSource ds, String userid) throws SADAREMException, SQLException {


        ResultSet rs = null;
        Statement stmt = null;
        PreparedStatement pstmt =null ;
        LoginDTO logindto = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            //stmt = conn.createStatement();
            String SQL = "select UserName,role_id,District_ID,Camp_ID,UserStatus,mandal_id,status from Login_Details "
                    + "where UserName = ?  and Status='Active' and role_id in ('23','24','25');"; 
            
             pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1, userid);
             
            rs = pstmt.executeQuery();
            while (rs.next()) {
                logindto = new LoginDTO();
                logindto.setUserStatus(rs.getBoolean("UserStatus"));
                if (!rs.getBoolean("UserStatus")) {
                    logindto.setUserName(rs.getString("UserName"));
                    logindto.setRoleId(rs.getInt("role_id"));
                    logindto.setDistrictId(rs.getString("District_ID"));
                    logindto.setCampId(rs.getInt("Camp_ID"));
                    logindto.setMandal_Id(rs.getString("mandal_id"));
                    logindto.setStatus(rs.getString("Status"));

                }
                //results1=r.getString(1);
                //result=true;
            }

            if (logindto != null) {

                CommonDetails commondetails = new CommonDetails();
                boolean RoleIdFlag = commondetails.campareRoleIds(String.valueOf(logindto.getRoleId()), CommonConstants.PERMISSIONS_FOR_OTHERRPERSONLOGIN);

                if (!logindto.isUserStatus() && RoleIdFlag==true) {
                    String SQLQuery = "update Login_Details set UserStatus='True' where UserName = ?  and Status='Active';";
                    
                    pstmt=conn.prepareStatement(SQLQuery);
                    pstmt.setString(1, userid);
                     
                     pstmt.executeUpdate();
                    
                    
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRDCallCenterValidUser", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getRDCallCenterValidUser");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRDCallCenterValidUser", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getRDCallCenterValidUser");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(pstmt);
        }
        return logindto;
    }

    /**
     * 
     * @param ds 
     * @param userid 
     * @throws java.lang.Exception 
     * @return list
     */
    public String getRoleId(DataSource ds, String userid) throws SADAREMException, SQLException {

        String roleid = null;
       
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        PreparedStatement pstmt =null;
        try {
            conn = DBConnection.getConnection();
            //stmt = conn.createStatement();

            String SQL = "select role_id from roles where role_id in (select role_id from user_roles where userid =?);";
            //rs=stmt.executeQuery(" select role_id from roles where role_id in (select role_id from user_roles where userid ='"+userid+"');");
            
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userid);
             
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                roleid = rs.getString(1);

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRoleId", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getRoleId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRoleId", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getRoleId");
        } finally {
            DBConnection.closeConnection(conn);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(pstmt);

        }
        return roleid;
    }

    //Services  Method
    /**
     * 
     * @param ds 
     * @param roleid 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public Vector getServicesSQL(DataSource ds, String roleid) throws SADAREMException, SQLException {

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt =null;
        Vector resultList = new Vector();
        // String SQL = "select service_id,parent_id,target_url,service_name,priority from services  where service_id in (select service_id from roles_services where role_id in (select role_id from roles where role_id = "+roleid+") )order by parent_id,priority";
        String SQL =  
        
        " select DISTINCT  service_id,parent_id,target_url,service_name,priority \n"+
		" from services WITH(NOLOCK) \n"+
		" where service_id in (select service_id from roles_services s  WITH(NOLOCK),roles r  WITH(NOLOCK) \n "
		+ " WhERe ( s.role_id = ? OR s.role_id=0) AND \n"
		+ " r.role_id=s.role_id  AND \n"
		+ " s.is_active='Y' AND \n"
		+ " r.is_active='Y' \n"+
		"  )  AND is_active='Y'\n"+
		" order by parent_id,priority";

        try {
            conn = DBConnection.getConnection();
            
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, roleid+"");
             
            rs = pstmt.executeQuery();
            
            
          //  stmt = conn.createStatement();
            //LogFactory.getLog("com.mobilefish.DemoAction").info("EWS:Log4J:GeneralSQL.java :getArrayListTwo() : The Query is : ");
            //LogFactory.getLog("com.mobilefish.DemoAction").info(" "+ SQL);
           // rs = stmt.executeQuery(SQL);
            if (rs != null) {


                while (rs.next()) {
                    String servicedesc[] = new String[5];
                    servicedesc[0] = rs.getString(1);
                    servicedesc[1] = rs.getString(2);
                    servicedesc[2] = rs.getString(3);
                    servicedesc[3] = rs.getString(4);

                    resultList.addElement(servicedesc);
                }

                //LogFactory.getLog("com.mobilefish.DemoAction").info("EWS:Log4J:GeneralSQL.java :getArrayListTwo() : Query Completed----------------->");
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getServicesSQL", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getServicesSQL");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getServicesSQL", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getServicesSQL");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(pstmt);

        }
        return resultList;
    }

    public int getunAuthorisedAccessStatus(DataSource ds, String userid) throws SADAREMException, SQLException {
        int i = 0;
        Connection conn = null;
       // Statement stmt = null;
        PreparedStatement pstmt =null;
        try {
            conn = DBConnection.getConnection();
        //    stmt = conn.createStatement();
            String SQLQuery = "update Login_Details set UserStatus='False' where UserName = ?  and Status='Active';";
            
            pstmt=conn.prepareStatement(SQLQuery); 
            
            pstmt.setString(1, userid); 
            
            
            
            pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getunAuthorisedAccessStatus", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getunAuthorisedAccessStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getunAuthorisedAccessStatus", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getunAuthorisedAccessStatus");
        } finally {
            DBConnection.closeConnection(conn);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(pstmt);


        }
        return i;
    }

    public int changePassword(DataSource ds, ShgloginForm ShgloginForm, String username) throws SADAREMException, SQLException {

        Connection con = null;
      //  Statement stmt = null;
        PreparedStatement pstmt =null ;
        int j = 0;
        try {

            con = DBConnection.getConnection();
         //   stmt = con.createStatement();
            String password = PasswordEncriptDecrypt.decrypt(ShgloginForm.getPassword());
            //String userid=logform.getUserid();
            String newpassword = PasswordEncriptDecrypt.decrypt(ShgloginForm.getNewpassword());
            String confirmpassword = PasswordEncriptDecrypt.decrypt(ShgloginForm.getConfirmpassword());
            String encrptPassword = ShgloginForm.getEncrptPwd(); 
            
           // System.out.println("password : "+password);
            String query = "update Login_Details set updated_date=getDate(),password=?,Encrypted_password=?,password_flag='N',updated_by=? where password=? and username=?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newpassword);
            pstmt.setString(2, encrptPassword);
            pstmt.setString(3, username);  
            pstmt.setString(4, password);
            pstmt.setString(5, username);            
            j = pstmt.executeUpdate();
            
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "changePassword", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "changePassword");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "changePassword", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "changePassword");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(pstmt);
        }
        return j;
    }

    public ArrayList getCountOfApprovalPendingofRole(DataSource ds, String districtId, String roleId, String mandalId) throws SADAREMException, SQLException {
        ArrayList list = new ArrayList();
        Connection con = null;

        Map map = null;

        CallableStatement cstmt = null;
        try {

            con = DBConnection.getConnection();
            stmt = con.createStatement();
            cstmt = con.prepareCall("{Call SP_SADAREMGRIEVANCES_DASHBOARD(?,?,?)}");
            cstmt.setString(1, districtId);
            cstmt.setString(2, mandalId);
            cstmt.setInt(3, Integer.parseInt(roleId));
            rs = cstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();

                    map.put("requestType", rs.getString(1));
                    map.put("pending", rs.getInt(2));
                    map.put("closed", rs.getInt(3));
                    list.add(map);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCountOfApprovalPendingofRole", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getCountOfApprovalPendingofRole");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCountOfApprovalPendingofRole", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getCountOfApprovalPendingofRole");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(stmt);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
        }
        return list;
    }

    public String getCampNames(DataSource ds, String districtId) throws SADAREMException, SQLException {
        Connection con = null;
       // Statement st = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        HashMap map = null;

        String campNames = "";

        try {

            con = DBConnection.getConnection();
        //    st = con.createStatement();


            sql = "select c.venueName+','+SPACE(3)+address camp from tblcamp_details c join dbo.tblDistrict_Details d on (c.District_ID=d.District_ID)"
                    + " join dbo.tblPerson_Disability_Details a on (a.Hospital_Name=c.Name and a.Hospital_Address=c.Address and a.VenueName=c.VenueName)"
                    + " where c. district_id=?  and convert(varchar,a.created_date,103)=convert(varchar,getDate(),103)";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, districtId);

            
            rs = pstmt.executeQuery();


            if (rs != null) {
                if (rs.next()) {
                    campNames = rs.getString(1);
                } else if (campNames.equals("")) {
                    campNames = "No Camps Running Today";
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampNames", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getCampNames");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampNames", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "getCampNames");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(stmt);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }

        return campNames;
    }

    public int updatePasswordFlag(DataSource ds, String username, String password, String encrptPassword, String districtId, String roleId) throws SADAREMException, SQLException {
        Connection con = null;
     //   Statement st = null;
        
        PreparedStatement pstmt =null;
        
        String sql = null;
        ResultSet rs = null;
        int flag = 0;
        try {

            con = DBConnection.getConnection();
           // st = con.createStatement();

            sql = "update Login_Details set password_flag='N',Encrypted_password=?  ,updated_date=getDate(),password=?  where  UserName = ? and district_id=? and role_id=? and Status='Active'";
            
            pstmt =con.prepareStatement(sql);
            pstmt.setString(1,encrptPassword);
            pstmt.setString(2,password);
            pstmt.setString(3,username);
            pstmt.setString(4,districtId);
            pstmt.setString(5,roleId);
              
            flag = pstmt.executeUpdate();
            


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePasswordFlag", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "updatePasswordFlag");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePasswordFlag", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "updatePasswordFlag");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(stmt);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }

        return flag;
    }

    /**
     *
     * @param ds
     * @param userid
     * @param password
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public int insertLoginStatus(DataSource ds, String userid, String systemIp, String status) throws SADAREMException, SQLException {


        ResultSet rs = null;
        PreparedStatement stmt = null;
        int i = 0;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();

            String SQL = "INSERT INTO dbo.LoginStatus_Details (UserName,System_IP_Address,LoginTime,Status)"
                    + " VALUES(?,?,getDate(),?)";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, userid);
            stmt.setString(2, systemIp);
            stmt.setString(3, status);
            i = stmt.executeUpdate();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertLoginStatus", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "insertLoginStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertLoginStatus", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "insertLoginStatus");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
           // DBConnection.closeStatement(stmt);

        }
        return i;
    }

    /**
     *
     * @param ds
     * @param userid
     * @param password
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public int loginStatusCnt(DataSource ds, String userid) throws SADAREMException, SQLException {


        ResultSet rs = null;
        //Statement st = null;
        PreparedStatement pstmt =null;
        Statement stmt = null;
        int i = 0;
        Connection con = null;
        String loginStatus = "Login Success";
        try {
            con = DBConnection.getConnection();
           // st = con.createStatement();

            String query = "select top 1 status from  LoginStatus_Details  where UserName=? order by logintime desc ";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, userid);
            
            rs = pstmt.executeQuery();
            
            if (rs != null) {
                if (rs.next()) {
                    loginStatus = rs.getString(1);
                }
            }
            if (loginStatus.equalsIgnoreCase("Login Failed")) {
                String SQL = "select count(*) from LoginStatus_Details where UserName=? and logintime between DATEADD(minute,-10,GetDate()) and getDate() and status='Login Failed' ";


                pstmt = con.prepareStatement(SQL);
                pstmt.setString(1, userid);
                
                rs = pstmt.executeQuery();
           
                if (rs != null) {
                    if (rs.next()) {
                        i = rs.getInt(1);
                    }
                }
                if (i >= 3) {
                    stmt = con.createStatement();
                    String query1 = "update Login_Details set status='Inactive' where  username=?"; 
                    pstmt = con.prepareStatement(query1);
                    pstmt.setString(1, userid);
                    
            
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "loginStatusCnt", "LoginDAO", "DataBase");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "loginStatusCnt");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "loginStatusCnt", "LoginDAO", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginDAO", "loginStatusCnt");
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
            DBConnection.closeStatement(pstmt);

        }
        return i;
    }
}
