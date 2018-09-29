package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.PopulateCalculationsLinksBean;
import org.bf.disability.util.ChronologicalAge;

import com.tcs.sgv.common.util.DBConnection;

public class ShowCalculationsDAO {

    //ChronologicalAge dobobj = new ChronologicalAge();

    public boolean checkPersoncode(String personcode,
            DataSource ds)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        boolean personcodecheckflag = false;
        try {
            con = DBConnection.getConnection();
            //query for finding personcode
            String query = "select P.Person_Code from tblPerson_Personal_Details P   with (nolock) ,dbo.tblPerson_Disability_TotalValue_Details PT where P.Person_Code=PT.Person_Code and P.Person_Code=? and PT.status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs.next() == false) {
                personcodecheckflag = false;
            } else {
                personcodecheckflag = true;
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersoncode", "ShowCalculationsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "checkPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersoncode", "ShowCalculationsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "checkPersoncode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);



        }
        return personcodecheckflag;
    }/* end of checkPersoncode */


    public String getPersonName(String personcode,
            DataSource ds)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        String name = null;
        try {
            con = DBConnection.getConnection();
            String query = "select First_Name from tblPerson_Personal_Details  with (nolock) where Person_Code=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                name = rs.getString("First_Name");
            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonName", "ShowCalculationsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "getPersonName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonName", "ShowCalculationsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "getPersonName");
        }//end of catch block
        finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }//end of finally block
        return name;
    }

    public PopulateCalculationsLinksBean decideTypeofDisability(DataSource ds, String personCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        PopulateCalculationsLinksBean calBean = new PopulateCalculationsLinksBean();

        ArrayList totaldisabilityArrList = new ArrayList();


        try {
            con = DBConnection.getConnection();
            String query = "select HearingImpairment,VisualImpairment,MentalRetardation,MentalIllness,TotalLocomotor from tblPerson_Disability_TotalValue_Details where Person_code=? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                if (rs.getDouble(1) > 0.0) {
                    calBean.setHi("true");
                }
                if (rs.getDouble(2) > 0.0) {
                    calBean.setVi("true");
                }
                if (rs.getDouble(3) > 0.0) {
                    calBean.setMr("true");
                    calBean = decideMRTypeofDisability(ds, personCode, calBean);
                    //populate mr into  bean
                }
                if (rs.getDouble(4) > 0.0) {
                    calBean.setMi("true");
                }
                if (rs.getDouble(5) > 0.0) {
                    calBean.setOh("true");
                    calBean = decideOHTypeofDisability(ds, personCode, calBean);
                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "decideTypeofDisability", "ShowCalculationsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "decideTypeofDisability");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "decideTypeofDisability", "ShowCalculationsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "decideTypeofDisability");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);



        }


        return calBean;
    }   /* end of decideTypeofDisability */


    public PopulateCalculationsLinksBean decideOHTypeofDisability(DataSource ds, String personCode, PopulateCalculationsLinksBean calBean) throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        ArrayList totalOHDisabilityArrList = new ArrayList();
        try {
            con = DBConnection.getConnection();
            String query = "select UpperExtrimity,LowerExtrimity,Amputation,Transverse,Trunk,Evaluation,Cardiopulmonary,Dwarfism from tblPerson_Disability_TotalValue_Details where Person_code=? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getDouble(1) > 0.0) {
                    calBean.setOhUe("true");
                }
                if (rs.getDouble(2) > 0.0) {
                    calBean.setOhLe("true");
                }
                if (rs.getDouble(3) > 0.0) {
                    calBean.setOhAmp("true");
                }
                if (rs.getDouble(4) > 0.0) {
                    calBean.setOhTrans("true");
                }
                if ((rs.getDouble(5)) > 0.0) {
                    calBean.setOhTrunk("true");
                }
                if (rs.getDouble(6) > 0.0) {
                    calBean.setOhPhysical("true");
                }
                if (rs.getDouble(7) > 0.0) {
                    calBean.setOhCardio("true");
                }
                if (rs.getDouble(8) > 0.0) {
                    calBean.setOhDwarf("true");
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "decideOHTypeofDisability", "ShowCalculationsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "decideOHTypeofDisability");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "decideOHTypeofDisability", "ShowCalculationsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "decideOHTypeofDisability");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);



        }

        return calBean;
    }  /* end of decideOHTypeofDisability */


    public PopulateCalculationsLinksBean decideMRTypeofDisability(DataSource ds, String personCode, PopulateCalculationsLinksBean calBean) throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        ArrayList mr_test_list = new ArrayList();
        try {
            con = DBConnection.getConnection();
            String query = null;
            int counter = -1;

            ArrayList mr_test_names = new ArrayList();
            mr_test_names.add("tblMR_DST_Test_Details_for_SevenYears_to_TwelveYears_Age_Persons");
            mr_test_names.add("tblVSMS_Test_Details_for_FourYears_to_SixYears_Age_Persons");
            mr_test_names.add("tblPerson_MR_BKT_Detailed_Test_Details");
            mr_test_names.add("tblPerson_MR_MISIC_Test_Details");
            mr_test_names.add("tblPerson_MR_SFB_Test_Details");
            mr_test_names.add("tblPerson_MR_PAT_Test_Details");
            mr_test_names.add("tblPerson_MR_BBPTI_Test_Details");

            Iterator it = mr_test_names.iterator();
            while (it.hasNext()) {
                query = "select Person_Code from " + it.next() + " where Person_Code=? and status='Active'";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, personCode);
                rs = pstmt.executeQuery();
                counter++;
                while (rs.next()) {
                    mr_test_list.add(new Integer(counter));
                }


                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(pstmt);
            }
            it = mr_test_list.iterator();
            for (int i = 0; i < mr_test_list.size(); i++) {
                switch (((Integer) mr_test_list.get(i)).intValue()) {
                    case 0:
                        calBean.setMrDst("true");
                        break;
                    case 1:
                        calBean.setMrVsms("true");
                        break;
                    case 2:
                        calBean.setMrBkt("true");
                        break;
                    case 3:
                        calBean.setMrMisic("true");
                        break;
                    case 4:
                        calBean.setMrSfb("true");
                        break;
                    case 5:
                        calBean.setMrPat("true");
                        break;
                    case 6:
                        calBean.setMrBbpti("true");
                        break;

                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "decideMRTypeofDisability", "ShowCalculationsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "decideMRTypeofDisability");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "decideMRTypeofDisability", "ShowCalculationsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "decideMRTypeofDisability");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);



        }

        return calBean;
    }   /* end of decideMRTypeofDisability */


    public ChronologicalAge getChronologicalAge(DataSource ds, String personCode) throws SADAREMDBException, SQLException {
        String formatteddob = new String();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Date dob = null;
        Date today = new Date();
        double years = -1;
        ChronologicalAge dobobj = new ChronologicalAge();
        try {
            con = DBConnection.getConnection();
            String query = null;
            int counter = 0;
            String dateOfBirth[] = null;
            ArrayList twodobs = new ArrayList();
            query = "select Date_of_Birth from tblPerson_Personal_Details  with (nolock) where Person_Code=?";
            pstmt.setString(1, personCode);
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                dob = new Date();
                dob = rs.getDate(1);


            }

            if (dob != null) {
                dateOfBirth = dob.toString().split("-");

                GregorianCalendar g = new GregorianCalendar(Integer.parseInt(dateOfBirth[0]), Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[2]));
                g.add(g.MONTH, -1);
                dob = g.getTime();
                formatteddob = dateOfBirth[2] + "-" + dateOfBirth[1] + "-" + dateOfBirth[0];
                dobobj.setDateofbirth(formatteddob);
                dobobj.setDob(dob);
                dobobj = getTodaysDate(dobobj);
                double chronologicalage = dobobj.computeChronologicalAge(dobobj.getToday(), dobobj.getDob());
                dobobj.setChronologicalage(new Double(chronologicalage).toString());

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getChronologicalAge", "ShowCalculationsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "getChronologicalAge");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getChronologicalAge", "ShowCalculationsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "getChronologicalAge");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return dobobj;
    }   /* end of getChronologicalAge */


    public ChronologicalAge getTodaysDate(ChronologicalAge dobobj) {

        Date d = new Date();
        SimpleDateFormat ddmmyyyy = new SimpleDateFormat("dd-MM-yyyy");
        StringBuilder nowddmmyyyy = new StringBuilder(ddmmyyyy.format(d));
        String todaysdate = nowddmmyyyy.toString();
        dobobj.setTodaysdate(todaysdate);
        dobobj.setToday(d);
        return dobobj;
    }

    public double getDateOfBirth(DataSource ds, String personCode) throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Date dob = null;
        double years = -1;
        try {
            con = DBConnection.getConnection();
            String query = null;
            int counter = 0;

            query = "select Date_of_Birth from tblPerson_Personal_Details  with (nolock) where Person_Code=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personCode);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                dob = new Date();
                dob = rs.getDate(1);
            }

            if (dob != null) {
                String dateOfBirth[] = dob.toString().split("-");

                dob = new GregorianCalendar(new Integer(dateOfBirth[0]).intValue(), new Integer(dateOfBirth[1]).intValue(), new Integer(dateOfBirth[2]).intValue()).getTime();
                Date today = new Date();
                long diff = today.getTime() - dob.getTime();
                long days = diff / (1000 * 60 * 60 * 24);

                years = (double) days / 365;

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDateOfBirth", "ShowCalculationsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "getDateOfBirth");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDateOfBirth", "ShowCalculationsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ShowCalculationsDAO", "getDateOfBirth");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }

        return years;
    }
}
