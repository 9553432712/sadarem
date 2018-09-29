package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.dto.AssessedPWDDetailsDTO;
import org.bf.disability.dto.DailyDisabilityAndPercentageDTO;
import org.bf.disability.dto.PartADTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for fetching disability and percentage list from database with respective date wise
 * @author ragavendra
 * @version 1.0
 */
public class DailyDisabilityAndPercentageDAO {

    /**
     * this method fetches disabilitywise information from database
     * @param ds 
     * @param dailydisabilityandpercentagedto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getDisabilityWise(DataSource ds,
            DailyDisabilityAndPercentageDTO dailydisabilityandpercentagedto)
            throws SADAREMDBException, SQLException {

        Connection con = null;

        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList disabilitylist = new ArrayList();
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(dailydisabilityandpercentagedto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(dailydisabilityandpercentagedto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_Reports_for_PersonCount_Disabilitywise_FromDate_ToDate(?,?)}");
            cstmt.setString(1, fromdate);
            cstmt.setString(2, todate);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                DailyDisabilityAndPercentageDTO dailydisability = new DailyDisabilityAndPercentageDTO();
                dailydisability.setDisabilitycount(rs.getString(1));
                dailydisability.setTypeofdisability(rs.getString(2));

                if (dailydisability.getTypeofdisability().equals("A.Locomotor/OH")) {
                    dailydisability.setTypeofdisability("Locomotor/OH");
                }
                if (dailydisability.getTypeofdisability().equals("B.Visual Impairment")) {
                    dailydisability.setTypeofdisability("Visual Impairment");
                }
                if (dailydisability.getTypeofdisability().equals("C.Hearing Impairment")) {
                    dailydisability.setTypeofdisability("Hearing Impairment");
                }
                if (dailydisability.getTypeofdisability().equals("D.Mental Retardation")) {
                    dailydisability.setTypeofdisability("Mental Retardation");
                }
                if (dailydisability.getTypeofdisability().equals("E.Mental Illness")) {
                    dailydisability.setTypeofdisability("Mental Illness");
                }
                if (dailydisability.getTypeofdisability().equals("F.Multiple Disability")) {
                    dailydisability.setTypeofdisability("Multiple Disability");
                }

                disabilitylist.add(dailydisability);
            }
        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "getDisabilityWise", "DailyDisabilityAndPercentageDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getDisabilityWise");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityWise", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getDisabilityWise");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityWise", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getDisabilityWise");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeConnection(con);
        }
        return disabilitylist;
    }

    /**
     * this method fetches percentagewise information from database
     * @param ds 
     * @param dailydisabilityandpercentagedto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getDisabilityPercentage(DataSource ds,
            DailyDisabilityAndPercentageDTO dailydisabilityandpercentagedto)
            throws SADAREMDBException, SQLException {

        Connection con = null;

        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList percentagelist = new ArrayList();
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(dailydisabilityandpercentagedto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(dailydisabilityandpercentagedto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_PersonCount_Percentagewise_BasedonDate(?,?)}");

            cstmt.setString(1, fromdate);
            cstmt.setString(2, todate);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                DailyDisabilityAndPercentageDTO dailypercentagedto =
                        new DailyDisabilityAndPercentageDTO();
                dailypercentagedto.setNo_of_persons(rs.getString(1));
                dailypercentagedto.setEligible_persons(rs.getString(2));
                dailypercentagedto.setRejected_persons(rs.getString(3));
                dailypercentagedto.setLessthan_fourty(rs.getString(4));
                dailypercentagedto.setFourty_to_sixty(rs.getString(5));
                dailypercentagedto.setSixtyone_to_eighty(rs.getString(6));
                dailypercentagedto.setAbove_eighty(rs.getString(7));
                percentagelist.add(dailypercentagedto);
            }
        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "getDisabilityPercentage", "DailyDisabilityAndPercentageDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getDisabilityPercentage");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityPercentage", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getDisabilityPercentage");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityPercentage", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getDisabilityPercentage");
        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeConnection(con);
        }
        return percentagelist;
    }

    public ArrayList getPersonPersonalDetailswise(DataSource ds, PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList personaldetailslist = null;
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            personaldetailslist = new ArrayList();
            cstmt = con.prepareCall("{Call SP_GETPERSONALDETAILSBASEDONDATEANDDISTRICTID(?,?,?)}");
            cstmt.setString(1, districtid);
            cstmt.setString(2, fromdate);
            cstmt.setString(3, todate);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                PartADTO partADTODetails = new PartADTO();
                partADTODetails.setPersoncode(rs.getString("Person_Code"));
                partADTODetails.setSurname(rs.getString("Surname"));
                partADTODetails.setFirstname(rs.getString("First_Name"));
                //partADTODetails.setGender(rs.getString("Gender"));
                if (rs.getString("Gender") != null) {
                    if (rs.getString("Gender").equals("1")) {
                        partADTODetails.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        partADTODetails.setGender("Female");
                    } else if (rs.getString("Gender").equals("0")) {
                        partADTODetails.setGender("-");
                    }
                } else {
                    partADTODetails.setGender("-");
                }
                String dobdate = new SimpleDateFormat("d/M/yyyy").format(new SimpleDateFormat("yyyy-MM-dd ").parse(rs.getString("Date_of_Birth")));
                partADTODetails.setDobday(dobdate);
                partADTODetails.setNoOfYears(rs.getString("Age_Years"));
                //  partADTODetails.setReligion(rs.getString("Religion"));
                if (rs.getString("Religion") != null) {
                    if (rs.getString("Religion").equals("1")) {
                        partADTODetails.setReligion("Hindu");
                    } else if (rs.getString("Religion").equals("2")) {
                        partADTODetails.setReligion("Muslim");
                    } else if (rs.getString("Religion").equals("3")) {
                        partADTODetails.setReligion("Christian");
                    } else if (rs.getString("Religion").equals("4")) {
                        partADTODetails.setReligion("Sikh");
                    } else if (rs.getString("Religion").equals("5")) {
                        partADTODetails.setReligion("Jain");
                    } else if (rs.getString("Religion").equals("6")) {
                        partADTODetails.setReligion("Budhist");
                    } else if (rs.getString("Religion").equals("7")) {
                        partADTODetails.setReligion("Others");
                    } else if (rs.getString("Religion").equals("0")) {
                        partADTODetails.setReligion("-");
                    }
                } else {
                    partADTODetails.setReligion("-");
                }

                //partADTODetails.setCaste(rs.getString("Caste"));
                if (rs.getString("Caste") != null) {
                    if (rs.getString("Caste").equals("1")) {
                        partADTODetails.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        partADTODetails.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        partADTODetails.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        partADTODetails.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        partADTODetails.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        partADTODetails.setCaste("NA");
                    } else if (rs.getString("Caste").equals("0")) {
                        partADTODetails.setCaste("-");
                    }
                } else {
                    partADTODetails.setCaste("-");
                }
                partADTODetails.setGsurname(rs.getString("Relation_Name"));
                partADTODetails.setGrelation(rs.getString("Relationship"));
                if (rs.getString("Relationship") != null) {
                    if (rs.getString("Relationship").equals("1")) {
                        partADTODetails.setGrelation("Father");
                    } else if (rs.getString("Relationship").equals("2")) {
                        partADTODetails.setGrelation("Mother");
                    } else if (rs.getString("Relationship").equals("3")) {
                        partADTODetails.setGrelation("Husband");
                    } else if (rs.getString("Relationship").equals("4")) {
                        partADTODetails.setGrelation("Guardian");
                    } else if (rs.getString("Relationship").equals("5")) {
                        partADTODetails.setGrelation("Brother");
                    } else if (rs.getString("Relationship").equals("6")) {
                        partADTODetails.setGrelation("Sister");
                    } else if (rs.getString("Relationship").equals("7")) {
                        partADTODetails.setGrelation("Wife");
                    } else if (rs.getString("Relationship").equals("0")) {
                        partADTODetails.setGrelation("-");
                    }
                } else {
                    partADTODetails.setGrelation("-");
                }

                // partADTODetails.setRtype(rs.getString("RationCard_Type"));
                if (rs.getString("RationCard_Type") != null) {

                    if (rs.getString("RationCard_Type").equals("1")) {
                        partADTODetails.setRtype("White");
                    } else if (rs.getString("RationCard_Type").equals("2")) {
                        partADTODetails.setRtype("Pink");
                    } else if (rs.getString("RationCard_Type").equals("3")) {
                        partADTODetails.setRtype("Anthyodaya");
                    } else if (rs.getString("RationCard_Type").equals("4")) {
                        partADTODetails.setRtype("Annapurna");
                    } else if (rs.getString("RationCard_Type").equals("5")) {
                        partADTODetails.setRtype("Yellow");
                    } else if (rs.getString("RationCard_Type").equals("0")) {
                        partADTODetails.setRtype("-");
                    }
                } else {
                    partADTODetails.setRtype("-");
                }

                partADTODetails.setCard(rs.getString("RationCard_Number"));
                partADTODetails.setPension_type(rs.getString("Pension_Type"));
                partADTODetails.setPensioncardno(rs.getString("PensionCard_No"));
                partADTODetails.setMole1(rs.getString("Mole_One"));
                partADTODetails.setMole2(rs.getString("Mole_Two"));
                if (rs.getString("House_Number") != null && !rs.getString("House_Number").equals("")) {
                    partADTODetails.setHouseno(rs.getString("House_Number"));
                } else {
                    partADTODetails.setHouseno("-");
                }
                partADTODetails.setDistrict(rs.getString("District_Name"));
                partADTODetails.setMandal(rs.getString("Mandal_Name"));
                partADTODetails.setVillage_name(rs.getString("Village_Name"));
                partADTODetails.setHabitation_name(rs.getString("Habitation_Name"));
                //partADTODetails.setType_disability(rs.getString("typeof_disability"));
                if (rs.getString("typeof_disability") != null) {

                    if (rs.getString("typeof_disability").equals("1")) {
                        partADTODetails.setType_disability("Locomotor");
                    } else if (rs.getString("typeof_disability").equals("2")) {
                        partADTODetails.setType_disability("Visual Impairment");
                    } else if (rs.getString("typeof_disability").equals("3")) {
                        partADTODetails.setType_disability("HearingImpairment");
                    } else if (rs.getString("typeof_disability").equals("4")) {
                        partADTODetails.setType_disability("MentalRetardation");
                    } else if (rs.getString("typeof_disability").equals("5")) {
                        partADTODetails.setType_disability("MentalIllness");
                    } else if (rs.getString("typeof_disability").equals("5")) {
                        partADTODetails.setType_disability("MultipleDisability");
                    } else if (rs.getString("typeof_disability").equals("0")) {
                        partADTODetails.setType_disability("-");
                    }
                } else {
                    partADTODetails.setType_disability("-");
                }
                personaldetailslist.add(partADTODetails);
            }
        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "getPersonPersonalDetailswise", "DailyDisabilityAndPercentageDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getPersonPersonalDetailswise");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonPersonalDetailswise", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getPersonPersonalDetailswise");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonPersonalDetailswise", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getPersonPersonalDetailswise");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeConnection(con);
        }
        return personaldetailslist;
    }

    public ArrayList getReportForLoginWiseCount(DataSource ds, PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {

        Connection con = null;
      //  Statement stmt = null;
        PreparedStatement pstmt= null ;
        ResultSet rs = null;
        ArrayList loginwisecountlist = null;
        try {

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            loginwisecountlist = new ArrayList();
           // stmt = con.createStatement();
            StringBuffer sql = new StringBuffer();
            sql.append( "SELECT a.LOGIN_ID,coalesce(A.PARTA_COUNT,0) PARTA_COUNT,coalesce(B.PARTB_COUNT,0) PARTB_COUNT \n" +
						" FROM (select partA_LOGINID LOGIN_ID,  coalesce(COUNT(PERSON_CODE),0) PARTA_COUNT \n" +
						" from dbo.tblPerson_Personal_Details  with (nolock) where status ='Active' AND \n" +
						" DISTRICTID =? and (DATEADD(DD,0, DATEDIFF(DD,0,CREATED_DATE))) BETWEEN ? and ? \n" +
						" and partA_LOGINID is not null GROUP BY partA_LOGINID) A \n" +
						" left join  \n" +
						" (select LOGIN_ID, coalesce(COUNT(PERSON_CODE),0)  PARTB_COUNT from dbo.tblPerson_Disability_Details DD \n" +
						" CROSS APPLY \n" +
						" DATA_PERSON_CODE(DD.PERSON_CODE) DPC \n" +
						" where status ='Active' AND DPC.DISTRICT_ID =? and  \n" +
						" (DATEADD(DD,0, DATEDIFF(DD,0,CREATED_DATE))) BETWEEN ? and ? and  \n" +
						" LOGIN_ID is not null GROUP BY LOGIN_ID) B on a.login_id=b.login_id \n" +
						" union  \n" +
						" SELECT b.LOGIN_ID,coalesce(A.PARTA_COUNT,0) PARTA_COUNT,coalesce(B.PARTB_COUNT,0) PARTB_COUNT \n" +
						" FROM (select partA_LOGINID LOGIN_ID,  coalesce(COUNT(PERSON_CODE),0) PARTA_COUNT \n" +
						" from dbo.tblPerson_Personal_Details  with (nolock) where status ='Active' \n" +
						" AND DISTRICTID =? and (DATEADD(DD,0, DATEDIFF(DD,0,CREATED_DATE))) BETWEEN ? and ? and \n" +
						" partA_LOGINID is not null GROUP BY partA_LOGINID) A \n" +
						" right join   \n" +
						" (select LOGIN_ID, coalesce(COUNT(PERSON_CODE),0) PARTB_COUNT \n" +
						" from dbo.tblPerson_Disability_Details DD \n" +
						" CROSS APPLY \n" +
						" DATA_PERSON_CODE(DD.PERSON_CODE) DPC \n" +
						" where status ='Active' AND \n" +
						" DPC.DISTRICT_ID =? and (DATEADD(DD,0, DATEDIFF(DD,0,CREATED_DATE))) \n" +
						" BETWEEN ? AND ?  and \n" +
						" LOGIN_ID is not null GROUP BY LOGIN_ID) B on a.login_id=b.login_id  " );

            pstmt=con.prepareStatement(sql.toString());
            
            pstmt.setString(1, districtid);
            pstmt.setString(2, fromdate);
            pstmt.setString(3, todate);

            pstmt.setString(4, districtid);
            pstmt.setString(5, fromdate);
            pstmt.setString(6, todate);
            

            pstmt.setString(7, districtid);
            pstmt.setString(8, fromdate);
            pstmt.setString(9, todate);
            

            pstmt.setString(10, districtid);
            pstmt.setString(11, fromdate);
            pstmt.setString(12, todate);
            
            
            
            rs = pstmt.executeQuery();
            
//            rs = stmt.executeQuery("select Login_ID, count(*) as total from tblperson_personal_details "
//                    + "where District_ID = '" + districtid + "' and (Dateadd (dd,0, Datediff(dd,0,Created_Date))) BETWEEN '" + fromdate + "' and '" + todate + "'"
//                    + "group by Login_ID");
            while (rs.next()) {
//               PartADTO partADTODetails = new PartADTO();
//                partADTODetails.setLoginid(convertFirstLetterToUpperCase(rs.getString("Login_ID")));
//                partADTODetails.setTotal(rs.getString("total"));
//                loginwisecountlist.add(partADTODetails);
                Map details = new HashMap();
                details.put("loginid", rs.getString(1));
                int partA = rs.getInt(2);
                int partB = rs.getInt(3);
                details.put("partA", partA);
                details.put("partB", partB);
                details.put("total", partA + partB);

                loginwisecountlist.add(details);

            }
        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "getReportForLoginWiseCount", "DailyDisabilityAndPercentageDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getReportForLoginWiseCount");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getReportForLoginWiseCount", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getReportForLoginWiseCount");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getReportForLoginWiseCount", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getReportForLoginWiseCount");
        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return loginwisecountlist;
    }

    public ArrayList getStatusCountForMandal(DataSource ds, PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {

        Connection con = null;
      //  Statement stmt = null;
        PreparedStatement pstmt =null ;
        ResultSet rs = null;
        ArrayList statuscountformandallist = null;
        try {

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            statuscountformandallist = new ArrayList();
            //stmt = con.createStatement();
            
            pstmt = con.prepareStatement("SELECT P.MANDAL_ID,M.MANDAL_NAME, COUNT(P.PERSON_CODE) as total "
                    + "FROM tblperson_personal_details P  with (nolock) ,dbo.tblMandal_Details M "
                    + "WHERE P.DISTRICT_ID = ? AND P.DISTRICT_ID = M.DISTRICT_ID AND "
                    + "P.MANDAL_ID = M.MANDAL_ID AND (Dateadd (dd,0, Datediff(dd,0,P.Created_Date))) "
                    + "BETWEEN ? and ? "
                    + "GROUP BY P.MANDAL_ID,M.MANDAL_NAME ORDER BY P.MANDAL_ID ");
            
            
            pstmt.setString(1, districtid);
            pstmt.setString(2, fromdate);
            pstmt.setString(3, todate);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PartADTO partADTODetails = new PartADTO();
                partADTODetails.setMandalid(rs.getString("Mandal_ID"));
                partADTODetails.setMandal_name(rs.getString("Mandal_Name"));
                partADTODetails.setTotal(rs.getString("total"));
                statuscountformandallist.add(partADTODetails);
            }
        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "getStatusCountForMandal", "DailyDisabilityAndPercentageDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getStatusCountForMandal");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatusCountForMandal", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getStatusCountForMandal");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatusCountForMandal", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getStatusCountForMandal");
        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return statuscountformandallist;
    }

    public ArrayList getStatusCountForVillage(DataSource ds, PartADTO partADTO, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
       // Statement stmt = null;
        
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        ArrayList statuscountforvillagelist = null;
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            statuscountforvillagelist = new ArrayList();
         //   stmt = con.createStatement();
            
            pstmt = con.prepareStatement("SELECT P.VILLAGE_ID,V.VILLAGE_NAME,COUNT(P.PERSON_CODE)AS TOTAL "
                    + "FROM TBLPERSON_PERSONAL_DETAILS P  with (nolock) , TBLVILLAGE_DETAILS V "
                    + "WHERE P.DISTRICT_ID = ? AND P.MANDAL_ID = ? AND "
                    + "P.DISTRICT_ID = V.DISTRICT_ID AND P.MANDAL_ID = V.MANDAL_ID AND "
                    + "P.VILLAGE_ID = V.VILLAGE_ID AND (DATEADD(DD,0, DATEDIFF(DD,0,P.Created_Date))) "
                    + "BETWEEN ? AND ? GROUP BY P.VILLAGE_ID,V.VILLAGE_NAME "
                    + "ORDER BY P.VILLAGE_ID ");
            
            pstmt.setString(1, districtid);
            pstmt.setString(2, mandalid);
            pstmt.setString(3, fromdate);
            pstmt.setString(4, todate);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PartADTO partADTODetails = new PartADTO();
                partADTODetails.setVillageid(rs.getString("Village_ID"));
                partADTODetails.setVillage_name(rs.getString("Village_Name"));
                partADTODetails.setTotal(rs.getString("total"));
                statuscountforvillagelist.add(partADTODetails);
            }
        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "getStatusCountForVillage", "DailyDisabilityAndPercentageDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getStatusCountForVillage");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatusCountForVillage", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getStatusCountForVillage");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatusCountForVillage", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getStatusCountForVillage");
        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return statuscountforvillagelist;
    }

    public ArrayList getStatusCountForPartA(DataSource ds, PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList statuscountforpartalist = null;
        CallableStatement cstmt = null;
        try {

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            statuscountforpartalist = new ArrayList();
            cstmt = con.prepareCall("{Call SP_PARTA_COUNTDETAILS(?,?,?)}");
            cstmt.setString(1, districtid);
            cstmt.setString(2, fromdate);
            cstmt.setString(3, todate);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                PartADTO partADTODetails = new PartADTO();
                // partADTODetails.setDistrictid(rs.getString("districtid"));
                partADTODetails.setMandalid(rs.getString("mandalid"));
                partADTODetails.setMandal_name(rs.getString("mandalname"));
                partADTODetails.setExistingpensioners(rs.getString("existingpensioners"));
                partADTODetails.setPartacount(rs.getString("parta_count"));
                partADTODetails.setOrtho(rs.getString("ortho"));
                partADTODetails.setHearing(rs.getString("hearing"));
                partADTODetails.setVisual(rs.getString("visual"));
                partADTODetails.setMentalretardation(rs.getString("mentalretardation"));
                partADTODetails.setMentalillness(rs.getString("mentalilliness"));
                partADTODetails.setMultipledisability(rs.getString("multipledisability"));
                statuscountforpartalist.add(partADTODetails);
            }
        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "getStatusCountForPartA", "DailyDisabilityAndPercentageDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getStatusCountForPartA");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatusCountForPartA", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getStatusCountForPartA");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStatusCountForPartA", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getStatusCountForPartA");
        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
        }
        return statuscountforpartalist;
    }

    public ArrayList distWisestatusreportforPartA(DataSource ds, PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList districtstatuscountforpartalist = null;
        CallableStatement cstmt = null;
        try {

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            districtstatuscountforpartalist = new ArrayList();
            cstmt = con.prepareCall("{Call SP_PARTA_COUNTDETAILSALLDISTRICTS(?,?)}");
            cstmt.setString(1, fromdate);
            cstmt.setString(2, todate);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                PartADTO partADTODetails = new PartADTO();
                partADTODetails.setDistrictid(rs.getString("districtid"));
                partADTODetails.setDistrict(rs.getString("districtname"));
                partADTODetails.setExistingpensioners(rs.getString("existingpensioners"));
                partADTODetails.setPartacount(rs.getString("parta_count"));
                partADTODetails.setOrtho(rs.getString("ortho"));
                partADTODetails.setHearing(rs.getString("hearing"));
                partADTODetails.setVisual(rs.getString("visual"));
                partADTODetails.setMentalretardation(rs.getString("mentalretardation"));
                partADTODetails.setMentalillness(rs.getString("mentalilliness"));
                partADTODetails.setMultipledisability(rs.getString("multipledisability"));
                districtstatuscountforpartalist.add(partADTODetails);
            }
        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "distWisestatusreportforPartA", "DailyDisabilityAndPercentageDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "distWisestatusreportforPartA");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "distWisestatusreportforPartA", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "distWisestatusreportforPartA");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "distWisestatusreportforPartA", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "distWisestatusreportforPartA");
        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
        }
        return districtstatuscountforpartalist;
    }

    public ArrayList distWisestatusreportforPartB(DataSource ds, PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList districtstatuscountforpartalist = null;
        CallableStatement cstmt = null;
        try {

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            String pensionPhase = partADTO.getPensionPhase();
            if (pensionPhase.equals("1")) {
                pensionPhase = "PhaseI";
            } else if (pensionPhase.equals("2")) {
                pensionPhase = "PhaseII";
            } else if (pensionPhase.equals("3")) {
                pensionPhase = "PhaseIII";
            } else if (pensionPhase.equals("4")) {
                pensionPhase = "PhaseIV";
            } else {
                pensionPhase = "ALL";
            }
            con = DBConnection.getConnection();
            districtstatuscountforpartalist = new ArrayList();
            if (pensionPhase.equals("ALL")) {
                cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_ALLPHASES(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    PartADTO partADTODetails = new PartADTO();
                    partADTODetails.setDistrictid(rs.getString("DISTRICTID"));
                    partADTODetails.setDistrict(rs.getString("DISTRICTNAME"));
                    partADTODetails.setExistingpensioners(rs.getString("EXISTINGPENSIONERS"));
                    partADTODetails.setDeathPensioners(rs.getString("DEATHPENSIONERS"));
                    partADTODetails.setPmPensioners(rs.getString("PMPENSIONERS"));
                    partADTODetails.setTotalDeleted(rs.getString("TOTALDELETED"));
                    partADTODetails.setIcfsPensioners(rs.getString("ICFSPENSIONERS"));
                    partADTODetails.setOtherPensioners(rs.getString("OTHERPENSIONERS"));
                    partADTODetails.setUnderGoSadarem(rs.getString("UNDERGOSADAREM"));
                    partADTODetails.setPartacount(rs.getString("PARTA"));
                    partADTODetails.setLivePensioners(rs.getString("LIVEPENSIONERS"));
                    partADTODetails.setDeathAndPm(rs.getString("DEATHANDPM"));
                    partADTODetails.setAssessedICFS(rs.getString("ASSESSEDICFS"));
                    partADTODetails.setAssessedOthers(rs.getString("ASSESSEDOTHERS"));
                    partADTODetails.setTotalpersonsassessed(rs.getString("TOTALASSESSED"));
                    partADTODetails.setTobeAssessed(rs.getString("TOBEASSESSED"));
                    partADTODetails.setOrtho(rs.getString("ORTHO"));
                    partADTODetails.setHearing(rs.getString("HEARING"));
                    partADTODetails.setVisual(rs.getString("VISUAL"));
                    partADTODetails.setMentalretardation(rs.getString("MENTALRETARDATION"));
                    partADTODetails.setMentalillness(rs.getString("MENTALILLINESS"));
                    partADTODetails.setMultipledisability(rs.getString("MULTIPLEDISABILITY"));
                    partADTODetails.setTotal(rs.getString("TOTAL"));
                    partADTODetails.setDirectrejected(rs.getString("DIRECTREJECTED"));
                    partADTODetails.setAssessedandrejected(rs.getString("ASSESSEDANDREJECTED"));
                    partADTODetails.setTotalrejected(rs.getString("TOTALREJECTED"));
                    districtstatuscountforpartalist.add(partADTODetails);
                }

            } else {

                cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_NEW(?,?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                cstmt.setString(3, pensionPhase);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    PartADTO partADTODetails = new PartADTO();
                    partADTODetails.setDistrictid(rs.getString("DISTRICTID"));
                    partADTODetails.setDistrict(rs.getString("DISTRICTNAME"));
                    partADTODetails.setExistingpensioners(rs.getString("EXISTINGPENSIONERS"));
                    partADTODetails.setDeathPensioners(rs.getString("DEATHPENSIONERS"));
                    partADTODetails.setPmPensioners(rs.getString("PMPENSIONERS"));
                    partADTODetails.setTotalDeleted(rs.getString("TOTALDELETED"));
                    partADTODetails.setIcfsPensioners(rs.getString("ICFSPENSIONERS"));
                    partADTODetails.setOtherPensioners(rs.getString("OTHERPENSIONERS"));
                    partADTODetails.setUnderGoSadarem(rs.getString("UNDERGOSADAREM"));
                    partADTODetails.setPartacount(rs.getString("PARTA"));
                    partADTODetails.setLivePensioners(rs.getString("LIVEPENSIONERS"));
                    partADTODetails.setDeathAndPm(rs.getString("DEATHANDPM"));
                    partADTODetails.setAssessedICFS(rs.getString("ASSESSEDICFS"));
                    partADTODetails.setAssessedOthers(rs.getString("ASSESSEDOTHERS"));
                    partADTODetails.setTotalpersonsassessed(rs.getString("TOTALASSESSED"));
                    partADTODetails.setTobeAssessed(rs.getString("TOBEASSESSED"));
                    partADTODetails.setOrtho(rs.getString("ORTHO"));
                    partADTODetails.setHearing(rs.getString("HEARING"));
                    partADTODetails.setVisual(rs.getString("VISUAL"));
                    partADTODetails.setMentalretardation(rs.getString("MENTALRETARDATION"));
                    partADTODetails.setMentalillness(rs.getString("MENTALILLINESS"));
                    partADTODetails.setMultipledisability(rs.getString("MULTIPLEDISABILITY"));
                    partADTODetails.setTotal(rs.getString("TOTAL"));
                    partADTODetails.setDirectrejected(rs.getString("DIRECTREJECTED"));
                    partADTODetails.setAssessedandrejected(rs.getString("ASSESSEDANDREJECTED"));
                    partADTODetails.setTotalrejected(rs.getString("TOTALREJECTED"));

                    districtstatuscountforpartalist.add(partADTODetails);

                }
            }
        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "distWisestatusreportforPartB", "DailyDisabilityAndPercentageDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "distWisestatusreportforPartB");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "distWisestatusreportforPartB", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "distWisestatusreportforPartB");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "distWisestatusreportforPartB", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "distWisestatusreportforPartB");
        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
        }
        return districtstatuscountforpartalist;
    }

    public ArrayList mandalWisestatusreportforPartB(DataSource ds, PartADTO partADTO, String districtid)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList districtstatuscountforpartblist = null;
        CallableStatement cstmt = null;

        try {
            String sum;
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            districtstatuscountforpartblist = new ArrayList();
            cstmt = con.prepareCall("{Call SP_PARTA_PARTB_MANDALWISECOUNTDETAILS(?,?,?)}");
            cstmt.setString(1, districtid);
            cstmt.setString(2, fromdate);
            cstmt.setString(3, todate);
            rs = cstmt.executeQuery();
            PartADTO partADTODetailscount = new PartADTO();
            while (rs.next()) {

                PartADTO partADTODetails = new PartADTO();
                partADTODetails.setMandalid(rs.getString("mandalid"));
                partADTODetails.setMandal_name(rs.getString("mandalname").replace(" ", ""));
                partADTODetails.setExistingpensioners(rs.getString("existingpensioners"));
                partADTODetails.setPartacount(rs.getString("parta_count"));
                partADTODetails.setTotalpersonsassessed(rs.getString("totalpersonsassessed"));
                partADTODetails.setOrtho(rs.getString("ortho"));
                partADTODetails.setHearing(rs.getString("hearing"));
                partADTODetails.setVisual(rs.getString("visual"));
                partADTODetails.setMentalretardation(rs.getString("mentalretardation"));
                partADTODetails.setMentalillness(rs.getString("mentalilliness"));
                partADTODetails.setMultipledisability(rs.getString("multipledisability"));
                partADTODetails.setDirectrejected(rs.getString("directrejected"));
                partADTODetails.setTotal(rs.getString("total"));
                partADTODetails.setAssessedandrejected(rs.getString("assessedandrejected"));
                partADTODetails.setTotalrejected(rs.getString("totalrejected"));
                districtstatuscountforpartblist.add(partADTODetails);

            }

        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "mandalWisestatusreportforPartB", "DailyDisabilityAndPercentageDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "mandalWisestatusreportforPartB");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "mandalWisestatusreportforPartB", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "mandalWisestatusreportforPartB");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "mandalWisestatusreportforPartB", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "mandalWisestatusreportforPartB");
        } finally {

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);
        }
        return districtstatuscountforpartblist;
    }

    public ArrayList villageWiseStatusReportForPartB(DataSource ds, PartADTO partADTO,
            String districtid, String mandalid) throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList villagewisereportforpartB = null;
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(partADTO.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            villagewisereportforpartB = new ArrayList();
            cstmt = con.prepareCall("{Call SP_PARTA_PARTB_VILLAGEWISECOUNTDETAILS(?,?,?,?)}");
            cstmt.setString(1, districtid);
            cstmt.setString(2, mandalid);
            cstmt.setString(3, fromdate);
            cstmt.setString(4, todate);
            rs = cstmt.executeQuery();

            while (rs.next()) {

                PartADTO partADTODetails = new PartADTO();
                // partADTODetails.setMandalid(rs.getString("mandalid"));
                //  partADTODetails.setMandal_name(rs.getString("mandalname"));
                partADTODetails.setVillage_name(rs.getString("villagename"));
                partADTODetails.setExistingpensioners(rs.getString("existingpensioners"));
                partADTODetails.setPartacount(rs.getString("parta_count"));
                partADTODetails.setTotalpersonsassessed(rs.getString("totalpersonsassessed"));
                partADTODetails.setOrtho(rs.getString("ortho"));
                partADTODetails.setHearing(rs.getString("hearing"));
                partADTODetails.setVisual(rs.getString("visual"));
                partADTODetails.setMentalretardation(rs.getString("mentalretardation"));
                partADTODetails.setMentalillness(rs.getString("mentalilliness"));
                partADTODetails.setMultipledisability(rs.getString("multipledisability"));
                partADTODetails.setDirectrejected(rs.getString("directrejected"));
                partADTODetails.setTotal(rs.getString("total"));
                partADTODetails.setAssessedandrejected(rs.getString("assessedandrejected"));
                partADTODetails.setTotalrejected(rs.getString("totalrejected"));
                villagewisereportforpartB.add(partADTODetails);

            }
        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "villageWiseStatusReportForPartB", "DailyDisabilityAndPercentageDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "villageWiseStatusReportForPartB");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "villageWiseStatusReportForPartB", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "villageWiseStatusReportForPartB");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "villageWiseStatusReportForPartB", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "villageWiseStatusReportForPartB");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeConnection(con);
        }
        return villagewisereportforpartB;
    }

    /**
     * this method fetches part B information from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList statusreportforPartB(DataSource ds,
            PartADTO partBDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String fromdate = null;
        String todate = null;

        ArrayList<PartADTO> partBList = null;
        district_id = partBDTO.getDistrict_id();
        mandal_id = partBDTO.getMandal_id();


        partBList = new ArrayList<PartADTO>();
        String pensionPhase = partBDTO.getPensionPhase();
        if (pensionPhase.equals("1")) {
            pensionPhase = "PhaseI";
        } else if (pensionPhase.equals("2")) {
            pensionPhase = "PhaseII";
        } else if (pensionPhase.equals("3")) {
            pensionPhase = "PhaseIII";
        } else if (pensionPhase.equals("4")) {
            pensionPhase = "PhaseIV";
        } else if (pensionPhase.equals("5")) {
            pensionPhase = "RachaBandaI";
        } else if (pensionPhase.equals("7")) {
            pensionPhase = "RachaBandaII";
        } else {
            pensionPhase = "ALL";
        }
        try {
            if (partBDTO.getFromdate() != null && !partBDTO.getFromdate().equalsIgnoreCase("")) {
                Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partBDTO.getFromdate());
                fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            }
            if (partBDTO.getFromdate() != null && !partBDTO.getTodate().equalsIgnoreCase("")) {
                Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(partBDTO.getTodate());
                todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            }

            con = DBConnection.getConnection();

            if (fromdate != null && todate != null) {
                if (district_id.equals("0")) {
                    if (pensionPhase.equals("RachaBandaI") || pensionPhase.equals("RachaBandaII")) {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_Rachabanda(?,?,?,?,?)}");
                        cstmt.setString(1, "All");
                        cstmt.setString(2, "All");
                        cstmt.setString(3, fromdate);
                        cstmt.setString(4, todate);
                        if (pensionPhase.equals("RachaBandaI")) {
                            pensionPhase = "05/07/2012";
                        } else if (pensionPhase.equals("RachaBandaII")) {
                            pensionPhase = "07/31/2012";
                        }
                        cstmt.setString(5, pensionPhase);
                    } else if (pensionPhase.equals("ALL")) {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_ALLPHASES(?,?)}");
                        cstmt.setString(1, fromdate);
                        cstmt.setString(2, todate);
                    } else if (pensionPhase.equals("PhaseIII")) {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_NEW_PHASEIII(?,?,?)}");
                        //SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE__districtWISE_NEWV1_PHASEIII
                        cstmt.setString(1, fromdate);
                        cstmt.setString(2, todate);
                        cstmt.setString(3, pensionPhase);
                    } else {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_NEW(?,?,?)}");
                        cstmt.setString(1, fromdate);
                        cstmt.setString(2, todate);
                        cstmt.setString(3, pensionPhase);
                    }

                    rs = cstmt.executeQuery();
                    while (rs.next()) {
                        PartADTO partADTODetails = new PartADTO();
                        partADTODetails.setDistrictid(rs.getString("DISTRICTID"));
                        partADTODetails.setDistrict(rs.getString("DISTRICTNAME"));
                        partADTODetails.setExistingpensioners(rs.getString("EXISTINGPENSIONERS"));
                        partADTODetails.setDeathPensioners(rs.getString("DEATHPENSIONERS"));
                        partADTODetails.setPmPensioners(rs.getString("PMPENSIONERS"));
                        if (pensionPhase.equals("PhaseIII")) {
                            partADTODetails.setSusTmPensioners("0");
                        } else {
                            partADTODetails.setSusTmPensioners(rs.getString("suspendpensioners"));
                        }
                        partADTODetails.setTotalDeleted(rs.getString("TOTALDELETED"));
                        //partADTODetails.setIcfsPensioners(rs.getString("ICFSPENSIONERS"));
                        partADTODetails.setOtherPensioners(rs.getString("OTHERPENSIONERS"));
                        partADTODetails.setUnderGoSadarem(rs.getString("UNDERGOSADAREM"));
                        partADTODetails.setPartacount(rs.getString("PARTA"));
                        partADTODetails.setLivePensioners(rs.getString("LIVEPENSIONERS"));
                        partADTODetails.setDeathAndPm(rs.getString("DEATHANDPM"));
                        partADTODetails.setAssessedICFS(rs.getString("ASSESSEDICFS"));
                        partADTODetails.setAssessedOthers(rs.getString("ASSESSEDOTHERS"));
                        partADTODetails.setTotalpersonsassessed(rs.getString("TOTALASSESSED"));
                        partADTODetails.setTobeAssessed(rs.getString("TOBEASSESSED"));
                        partADTODetails.setOrtho(rs.getString("ORTHO"));
                        partADTODetails.setHearing(rs.getString("HEARING"));
                        partADTODetails.setVisual(rs.getString("VISUAL"));
                        partADTODetails.setMentalretardation(rs.getString("MENTALRETARDATION"));
                        partADTODetails.setMentalillness(rs.getString("MENTALILLINESS"));
                        partADTODetails.setMultipledisability(rs.getString("MULTIPLEDISABILITY"));
                        partADTODetails.setTotal(rs.getString("TOTAL"));
                        partADTODetails.setDirectrejected(rs.getString("DIRECTREJECTED"));
                        partADTODetails.setAssessedandrejected(rs.getString("ASSESSEDANDREJECTED"));
                        partADTODetails.setTotalrejected(rs.getString("TOTALREJECTED"));
                        partBList.add(partADTODetails);
                    }

                } else if (mandal_id.equals("0")) {
                    if (pensionPhase.equals("RachaBandaI") || pensionPhase.equals("RachaBandaII")) {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_Rachabanda(?,?,?,?,?)}");
                        cstmt.setString(1, district_id);
                        cstmt.setString(2, "All");
                        cstmt.setString(3, fromdate);
                        cstmt.setString(4, todate);
                        if (pensionPhase.equals("RachaBandaI")) {
                            pensionPhase = "05/07/2012";
                        } else if (pensionPhase.equals("RachaBandaII")) {
                            pensionPhase = "07/31/2012";
                        }
                        cstmt.setString(5, pensionPhase);
                    } else if (pensionPhase.equals("ALL")) {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_ALLPHASES_MANDAL(?,?,?)}");
                        cstmt.setString(1, district_id);
                        cstmt.setString(2, fromdate);
                        cstmt.setString(3, todate);
                    } else {
                        if (pensionPhase.equals("PhaseIII")) {
                            cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE__MANDALWISE_NEW_PHASEIII(?,?,?,?)}");
                            //SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE__MANDALWISE_NEWV1_PHASEIII
                        } else {
                            cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE__MANDALWISE_NEW(?,?,?,?)}");
                        }
                        cstmt.setString(1, district_id);
                        cstmt.setString(2, fromdate);
                        cstmt.setString(3, todate);
                        cstmt.setString(4, pensionPhase);

                    }
                    rs = cstmt.executeQuery();
                    while (rs.next()) {
                        PartADTO partADTODetails = new PartADTO();
                        partADTODetails.setDistrict_id(district_id);
                        partADTODetails.setMandal_id(rs.getString("MANDALID"));
                        partADTODetails.setMandal(rs.getString("MANDALNAME"));
                        partADTODetails.setExistingpensioners(rs.getString("EXISTINGPENSIONERS"));
                        partADTODetails.setDeathPensioners(rs.getString("DEATHPENSIONERS"));
                        partADTODetails.setPmPensioners(rs.getString("PMPENSIONERS"));
                        if (pensionPhase.equals("PhaseIII")) {
                            partADTODetails.setSusTmPensioners("0");
                        } else {
                            partADTODetails.setSusTmPensioners(rs.getString("suspendpensioners"));
                        }
                        partADTODetails.setTotalDeleted(rs.getString("TOTALDELETED"));
                        //partADTODetails.setIcfsPensioners(rs.getString("ICFSPENSIONERS"));
                        partADTODetails.setOtherPensioners(rs.getString("OTHERPENSIONERS"));
                        partADTODetails.setUnderGoSadarem(rs.getString("UNDERGOSADAREM"));
                        partADTODetails.setPartacount(rs.getString("PARTA"));
                        partADTODetails.setLivePensioners(rs.getString("LIVEPENSIONERS"));
                        partADTODetails.setDeathAndPm(rs.getString("DEATHANDPM"));
                        partADTODetails.setAssessedICFS(rs.getString("ASSESSEDICFS"));
                        partADTODetails.setAssessedOthers(rs.getString("ASSESSEDOTHERS"));
                        partADTODetails.setTotalpersonsassessed(rs.getString("TOTALASSESSED"));
                        partADTODetails.setTobeAssessed(rs.getString("TOBEASSESSED"));
                        partADTODetails.setOrtho(rs.getString("ORTHO"));
                        partADTODetails.setHearing(rs.getString("HEARING"));
                        partADTODetails.setVisual(rs.getString("VISUAL"));
                        partADTODetails.setMentalretardation(rs.getString("MENTALRETARDATION"));
                        partADTODetails.setMentalillness(rs.getString("MENTALILLINESS"));
                        partADTODetails.setMultipledisability(rs.getString("MULTIPLEDISABILITY"));
                        partADTODetails.setTotal(rs.getString("TOTAL"));
                        partADTODetails.setDirectrejected(rs.getString("DIRECTREJECTED"));
                        partADTODetails.setAssessedandrejected(rs.getString("ASSESSEDANDREJECTED"));
                        partADTODetails.setTotalrejected(rs.getString("TOTALREJECTED"));
                        partBList.add(partADTODetails);
                    }

                } else if (!mandal_id.equals("0")) {
                    if (pensionPhase.equals("RachaBandaI") || pensionPhase.equals("RachaBandaII")) {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_Rachabanda(?,?,?,?,?)}");
                        cstmt.setString(1, district_id);
                        cstmt.setString(2, mandal_id);
                        cstmt.setString(3, fromdate);
                        cstmt.setString(4, todate);
                        if (pensionPhase.equals("RachaBandaI")) {
                            pensionPhase = "05/07/2012";
                        } else if (pensionPhase.equals("RachaBandaII")) {
                            pensionPhase = "07/31/2012";
                        }
                        cstmt.setString(5, pensionPhase);
                    } else if (pensionPhase.equals("ALL")) {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_ALLPHASES_VILLAGE(?,?,?,?)}");
                        cstmt.setString(1, district_id);
                        cstmt.setString(2, mandal_id);
                        cstmt.setString(3, fromdate);
                        cstmt.setString(4, todate);
                    } else {
                        if (pensionPhase.equals("PhaseIII")) {
                            cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE__VILLAGEWISE_NEW_PHASEIII(?,?,?,?,?)}");
                        } else {
                            cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE__VILLAGEWISE_NEW(?,?,?,?,?)}");
                        }
                        cstmt.setString(1, district_id);
                        cstmt.setString(2, mandal_id);
                        cstmt.setString(3, fromdate);
                        cstmt.setString(4, todate);
                        cstmt.setString(5, pensionPhase);
                    }
                    rs = cstmt.executeQuery();
                    while (rs.next()) {
                        PartADTO partADTODetails = new PartADTO();
                        partADTODetails.setDistrict_id(district_id);
                        partADTODetails.setMandal_id(mandal_id);
                        partADTODetails.setVillage_id(rs.getString("VILLAGEID"));
                        partADTODetails.setTownVillage(rs.getString("VILLAGENAME"));
                        partADTODetails.setExistingpensioners(rs.getString("EXISTINGPENSIONERS"));
                        partADTODetails.setDeathPensioners(rs.getString("DEATHPENSIONERS"));
                        partADTODetails.setPmPensioners(rs.getString("PMPENSIONERS"));
                        if (pensionPhase.equals("PhaseIII")) {
                            partADTODetails.setSusTmPensioners("0");
                        } else {
                            partADTODetails.setSusTmPensioners(rs.getString("suspendpensioners"));
                        }
                        partADTODetails.setTotalDeleted(rs.getString("TOTALDELETED"));
                        //partADTODetails.setIcfsPensioners(rs.getString("ICFSPENSIONERS"));
                        partADTODetails.setOtherPensioners(rs.getString("OTHERPENSIONERS"));
                        partADTODetails.setUnderGoSadarem(rs.getString("UNDERGOSADAREM"));
                        partADTODetails.setPartacount(rs.getString("PARTA"));
                        partADTODetails.setLivePensioners(rs.getString("LIVEPENSIONERS"));
                        partADTODetails.setDeathAndPm(rs.getString("DEATHANDPM"));
                        partADTODetails.setAssessedICFS(rs.getString("ASSESSEDICFS"));
                        partADTODetails.setAssessedOthers(rs.getString("ASSESSEDOTHERS"));
                        partADTODetails.setTotalpersonsassessed(rs.getString("TOTALASSESSED"));
                        partADTODetails.setTobeAssessed(rs.getString("TOBEASSESSED"));
                        partADTODetails.setOrtho(rs.getString("ORTHO"));
                        partADTODetails.setHearing(rs.getString("HEARING"));
                        partADTODetails.setVisual(rs.getString("VISUAL"));
                        partADTODetails.setMentalretardation(rs.getString("MENTALRETARDATION"));
                        partADTODetails.setMentalillness(rs.getString("MENTALILLINESS"));
                        partADTODetails.setMultipledisability(rs.getString("MULTIPLEDISABILITY"));
                        partADTODetails.setTotal(rs.getString("TOTAL"));
                        partADTODetails.setDirectrejected(rs.getString("DIRECTREJECTED"));
                        partADTODetails.setAssessedandrejected(rs.getString("ASSESSEDANDREJECTED"));
                        partADTODetails.setTotalrejected(rs.getString("TOTALREJECTED"));
                        partBList.add(partADTODetails);
                    }


                }
            }
        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "statusreportforPartB", "DailyDisabilityAndPercentageDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "statusreportforPartB");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "statusreportforPartB", "DailyDisabilityAndPercentageDAO", "DataBase");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "statusreportforPartB");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "statusreportforPartB", "DailyDisabilityAndPercentageDAO", "Code");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "statusreportforPartB");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeConnection(con);
        }
        return partBList;
    }

    /*   public ArrayList statusreportforPartB(DataSource ds,
    PartADTO partBDTO)
    throws SADAREMDBException,SQLException {

    Connection con = null;
    Statement cstmt = null;
    ResultSet rs = null;
    String district_id = null;
    String mandal_id = null;
    String sql = null;

    ArrayList partBList = null;
    district_id = partBDTO.getDistrict_id();
    mandal_id = partBDTO.getMandal_id();


    partBList = new ArrayList();
    String pensionPhase = partBDTO.getPensionPhase();

    try {
    Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(partBDTO.getFromdate());
    String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

    Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(partBDTO.getTodate());
    String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
    con = DBConnection.getConnection();
    cstmt = con.createStatement();

    if (pensionPhase.equals("1")) {
    pensionPhase = "PhaseI";
    } else if (pensionPhase.equals("2")) {
    pensionPhase = "PhaseII";
    } else if (pensionPhase.equals("3")) {
    pensionPhase = "PhaseIII";
    } else if (pensionPhase.equals("4")) {
    pensionPhase = "PhaseIV";
    } else if (pensionPhase.equals("5")) {
    pensionPhase = "PhaseI','PhaseII','PhaseIII','PhaseIV";
    }
    int i = 0;

    if (district_id.equals("0")) {

    sql = "select b.district_id,b.district_name,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode) as totalPensioners,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and reasonforpersonstatus='Dead') as dead,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and reasonforpersonstatus='Permanent Migration') as pm,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and reasonforpersonstatus='ICFS Deletion') as icfs,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and (reasonforpersonstatus='Not Eligible' or reasonforpersonstatus='Abhayahastham')) as abh,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=district_id ) as partAentered,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=district_id and reasonforpersonstatus='Live' and view_edit_mode='View' and (DATEADD(DD,0,DATEDIFF(DD,0,UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') as partALive,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=district_id and view_edit_mode='View' and reasonforpersonstatus in('Dead','Permanent Migration') and (DATEADD(DD,0,DATEDIFF(DD,0,UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "' and pensioncard_no in(select pensionid from DISABLED_PENSION where distcode=district_id and distcode=b.district_id and pensionphase in('" + pensionPhase + "'))) as partAdeathandPM,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=district_id and view_edit_mode='View' and reasonforpersonstatus='ICFS Deletion' and (DATEADD(DD,0,DATEDIFF(DD,0,UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') as partAicfs,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=district_id and view_edit_mode='View' and (reasonforpersonstatus='Not Eligible' or reasonforpersonstatus='Abhayahastham') and (DATEADD(DD,0,DATEDIFF(DD,0,UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') as partAbhandnot,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id and d.Disability_ID='1' and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') ortho,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id and d.Disability_ID='2' and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') visual,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id and d.Disability_ID='3' and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') hearing,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id and d.Disability_ID='4' and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') mentalre,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id and d.Disability_ID='5' and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') mentalill,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id and d.Disability_ID='6' and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') multiple,"
    + "(select count(*) from tblPerson_Personal_Details c join tblRejectPerson_Details d on(c.person_code=d.person_code) and c.district_id=b.district_id and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and c.view_edit_mode='View' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') beforeAss,"
    + "(select count(*) from TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c join TBLPERSON_PERSONAL_DETAILS d on (c.person_code=d.person_code) and  d.district_id=b.district_id and d.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and c.totaldisability < 40 and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') afterAss "
    + "from Disabled_Pension a right join dbo.tblDistrict_Details b on(a.distcode=b.district_id) group by b.district_id,b.district_name order by b.district_id";

    rs = cstmt.executeQuery(sql);
    while (rs.next()) {

    HashMap data = new HashMap();
    data.put("district_id", rs.getString(1));
    data.put("district_name", rs.getString(2));
    data.put("totalPensioners", rs.getString(3));
    data.put("dead", rs.getString(4));
    data.put("pm", rs.getString(5));
    data.put("icfs", rs.getString(6));
    data.put("abh", rs.getString(7));
    data.put("partAentered", rs.getString(8));
    data.put("partALive", rs.getString(9));
    data.put("partAdeathandPM", rs.getString(10));
    data.put("partAicfs", rs.getString(11));
    data.put("partAbhandnot", rs.getString(12));
    data.put("ortho", rs.getString(13));
    data.put("visual", rs.getString(14));
    data.put("hearing", rs.getString(15));
    data.put("mentalre", rs.getString(16));
    data.put("mentalill", rs.getString(17));
    data.put("multiple", rs.getString(18));
    data.put("beforeAss", rs.getString(19));
    data.put("afterAss", rs.getString(20));
    data.put("id", i++);
    partBList.add(data);

    }



    } else if (!district_id.equals("0") && mandal_id.equals("0")) {

    sql = "select b.district_id,b.mandal_id,b.mandal_name,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and b.mandal_id=mndcode) as totalPensioners,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and b.mandal_id=mndcode and reasonforpersonstatus='Dead') as dead,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and b.mandal_id=mndcode and reasonforpersonstatus='Permanent Migration') as pm,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and b.mandal_id=mndcode and reasonforpersonstatus='ICFS Deletion') as icfs,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and b.mandal_id=mndcode and (reasonforpersonstatus='Not Eligible' or reasonforpersonstatus='Abhayahastham')) as abh,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=substring(HABCODE,1,2) and b.mandal_id=substring(HABCODE,6,2) and (DATEADD(DD,0, DATEDIFF(DD,0,updated_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') as partAentered,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=substring(HABCODE,1,2) and b.mandal_id=substring(HABCODE,6,2) and reasonforpersonstatus='Live' and view_edit_mode='View' and (DATEADD(DD,0, DATEDIFF(DD,0,updated_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') as partALive,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=substring(HABCODE,1,2) and b.mandal_id=substring(HABCODE,6,2) and reasonforpersonstatus in('Dead','Permanent Migration') and view_edit_mode='View' and (DATEADD(DD,0, DATEDIFF(DD,0,updated_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') as partAdeathandPM,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=substring(HABCODE,1,2) and b.mandal_id=substring(HABCODE,6,2) and reasonforpersonstatus='ICFS Deletion' and view_edit_mode='View' and (DATEADD(DD,0, DATEDIFF(DD,0,updated_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') as partAicfs,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=substring(HABCODE,1,2) and b.mandal_id=substring(HABCODE,6,2) and (reasonforpersonstatus='Not Eligible' or reasonforpersonstatus='Abhayahastham') and view_edit_mode='View' and (DATEADD(DD,0, DATEDIFF(DD,0,updated_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') as partAbhandnot,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where substring(C.HABCODE,1,2)=b.district_id and substring(c.HABCODE,6,2)=b.mandal_id and d.Disability_ID='1' and c.pensionphase in('" + pensionPhase + "') and d.status='Active' and c.status='Active' and (DATEADD(DD,0, DATEDIFF(DD,0,d.created_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') ortho,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where substring(C.HABCODE,1,2)=b.district_id and substring(c.HABCODE,6,2)=b.mandal_id and d.Disability_ID='2' and c.pensionphase in('" + pensionPhase + "') and d.status='Active' and c.status='Active' and (DATEADD(DD,0, DATEDIFF(DD,0,d.created_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') visual,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where substring(C.HABCODE,1,2)=b.district_id  and substring(c.HABCODE,6,2)=b.mandal_id and d.Disability_ID='3' and c.pensionphase in('" + pensionPhase + "') and d.status='Active' and c.status='Active' and (DATEADD(DD,0, DATEDIFF(DD,0,d.created_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') hearing,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where substring(C.HABCODE,1,2)=b.district_id and substring(c.HABCODE,6,2)=b.mandal_id and d.Disability_ID='4' and c.pensionphase in('" + pensionPhase + "') and d.status='Active' and c.status='Active' and (DATEADD(DD,0, DATEDIFF(DD,0,d.created_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') mentalre,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where substring(C.HABCODE,1,2)=b.district_id  and substring(c.HABCODE,6,2)=b.mandal_id and d.Disability_ID='5' and c.pensionphase in('" + pensionPhase + "') and d.status='Active' and c.status='Active' and (DATEADD(DD,0, DATEDIFF(DD,0,d.created_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') mentalill,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where substring(C.HABCODE,1,2)=b.district_id and substring(c.HABCODE,6,2)=b.mandal_id and d.Disability_ID='6' and c.pensionphase in('" + pensionPhase + "') and d.status='Active' and c.status='Active' and (DATEADD(DD,0, DATEDIFF(DD,0,d.created_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') multiple, "
    + "(select count(*) from tblPerson_Personal_Details c join tblRejectPerson_Details d on(c.person_code=d.person_code) and substring(c.habcode,1,2)=b.district_id and substring(c.habcode,6,2)=b.mandal_id and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and c.view_edit_mode='View' and (DATEADD(DD,0, DATEDIFF(DD,0,c.created_date))) BETWEEN '" + fromdate + "' AND '" + todate + "')  as beforeAss,"
    + "(select count(*) from TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c join TBLPERSON_PERSONAL_DETAILS d on (c.person_code=d.person_code) where  substring(d.habcode,1,2)=b.district_id and substring(d.habcode,6,2)= b.mandal_id and d.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and c.totaldisability < 40 and (DATEADD(DD,0, DATEDIFF(DD,0,d.created_date))) BETWEEN '" + fromdate + "' AND '" + todate + "') as afterAss"
    + " from Disabled_Pension a right join dbo.tblMandal_Details b on(a.distcode=b.district_id and a.mndcode=b.mandal_id) where b.district_id='" + district_id + "' group by b.district_id,b.mandal_id,b.mandal_name order by b.district_id";
    rs = cstmt.executeQuery(sql);
    while (rs.next()) {

    HashMap data = new HashMap();
    data.put("district_id", rs.getString(1));
    data.put("mandal_id", rs.getString(2));
    data.put("mandal_name", rs.getString(3));
    data.put("totalPensioners", rs.getString(4));
    data.put("dead", rs.getString(5));
    data.put("pm", rs.getString(6));
    data.put("icfs", rs.getString(7));
    data.put("abh", rs.getString(8));
    data.put("partAentered", rs.getString(9));
    data.put("partALive", rs.getString(10));
    data.put("partAdeathandPM", rs.getString(11));
    data.put("partAicfs", rs.getString(12));
    data.put("partAbhandnot", rs.getString(13));
    data.put("ortho", rs.getString(14));
    data.put("visual", rs.getString(15));
    data.put("hearing", rs.getString(16));
    data.put("mentalre", rs.getString(17));
    data.put("mentalill", rs.getString(18));
    data.put("multiple", rs.getString(19));
    data.put("beforeAss", rs.getString(20));
    data.put("afterAss", rs.getString(21));
    data.put("id", i++);
    partBList.add(data);
    }
    } else if (!district_id.equals("0") && !mandal_id.equals("0")) {

    sql = "select b.district_id,b.mandal_id,b.village_id,b.village_name,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and b.mandal_id=mndcode and b.village_id=SUBSTRING(habcode,8,3)) as totalPensioners,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and b.mandal_id=mndcode and b.village_id=SUBSTRING(habcode,8,3) and reasonforpersonstatus='Dead') as dead,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and b.mandal_id=mndcode and b.village_id=SUBSTRING(habcode,8,3) and reasonforpersonstatus='Permanent Migration') as pm,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and b.mandal_id=mndcode and b.village_id=SUBSTRING(habcode,8,3) and reasonforpersonstatus='ICFS Deletion') as icfs,"
    + "(select count(*) from Disabled_Pension where pensionphase in('" + pensionPhase + "') and b.district_id=distcode and b.mandal_id=mndcode and b.village_id=SUBSTRING(habcode,8,3) and reasonforpersonstatus in('Not Eligible','Abhayahastham')) as abh,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=district_id and b.mandal_id=mandal_id and b.village_id=SUBSTRING(habcode,8,3) and view_edit_mode='View' and (DATEADD(DD,0,DATEDIFF(DD,0,UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') as partAentered,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=district_id and b.mandal_id=mandal_id and b.village_id=SUBSTRING(habcode,8,3) and reasonforpersonstatus='Live' and view_edit_mode='View' and (DATEADD(DD,0,DATEDIFF(DD,0,UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') as partALive,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=district_id and b.mandal_id=mandal_id and b.village_id=SUBSTRING(habcode,8,3) and (reasonforpersonstatus='Dead' or reasonforpersonstatus='Permanent Migration') and view_edit_mode='View' and (DATEADD(DD,0,DATEDIFF(DD,0,UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') as partAdeathandPM,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=district_id and b.mandal_id=mandal_id and b.village_id=SUBSTRING(habcode,8,3) and reasonforpersonstatus='ICFS Deletion' and view_edit_mode='View' and (DATEADD(DD,0,DATEDIFF(DD,0,UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') as partAicfs,"
    + "(select count(*) from tblPerson_Personal_Details where pensionphase in('" + pensionPhase + "') and b.district_id=district_id and b.mandal_id=mandal_id and b.village_id=SUBSTRING(habcode,8,3) and (reasonforpersonstatus='Not Eligible' or reasonforpersonstatus='Abhayahastham') and view_edit_mode='View' and (DATEADD(DD,0,DATEDIFF(DD,0,UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') as partAbhandnot,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id and c.mandal_id=b.mandal_id and substring(c.habcode,8,3)=b.village_id and d.Disability_ID='1' and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active'  and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') ortho,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id and c.mandal_id=b.mandal_id and substring(c.habcode,8,3)=b.village_id and d.Disability_ID='2' and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') visual,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id  and c.mandal_id=b.mandal_id and substring(c.habcode,8,3)=b.village_id and d.Disability_ID='3' and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') hearing,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id and c.mandal_id=b.mandal_id and substring(c.habcode,8,3)=b.village_id and d.Disability_ID='4' and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') mentalre,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id  and c.mandal_id=b.mandal_id and substring(c.habcode,8,3)=b.village_id and d.Disability_ID='5' and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') mentalill,"
    + "(select count(*) from tblPerson_Personal_Details c join  dbo.tblPerson_Disability_Details d on(c.person_code=d.person_code) where c.district_id=b.district_id and c.mandal_id=b.mandal_id and substring(c.habcode,8,3)=b.village_id and d.Disability_ID='6' and c.pensionphase in('" + pensionPhase + "')  and c.status='Active' and d.status='Active' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') multiple,"
    + "(select count(*) from tblPerson_Personal_Details c join tblRejectPerson_Details d on(c.person_code=d.person_code) and c.district_id=b.district_id and substring(c.habcode,6,2)=b.mandal_id and SUBSTRING(c.habcode,8,3)=b.village_id and c.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and c.view_edit_mode='View' and (DATEADD(DD,0,DATEDIFF(DD,0,c.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') beforeAss,"
    + "(select count(*) from TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c join TBLPERSON_PERSONAL_DETAILS d on (c.person_code=d.person_code) where  substring(d.habcode,1,2)=b.district_id and substring(d.habcode,6,2)=b.mandal_id and SUBSTRING(d.habcode,8,3)=b.village_id and d.pensionphase in('" + pensionPhase + "') and c.status='Active' and d.status='Active' and c.totaldisability < 40 and (DATEADD(DD,0,DATEDIFF(DD,0,d.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "') afterAss "
    + "from Disabled_Pension a right join dbo.tblVillage_Details b on(a.distcode=b.district_id and a.mndcode=b.mandal_id and b.village_id=SUBSTRING(habcode,8,3)) where b.district_id='" + district_id + "' and b.mandal_id='" + mandal_id + "' group by b.district_id,b.mandal_id,b.village_id,b.village_name order by b.district_id";

    rs = cstmt.executeQuery(sql);
    while (rs.next()) {

    HashMap data = new HashMap();
    data.put("district_id", rs.getString(1));
    data.put("mandal_id", rs.getString(2));
    data.put("village_id", rs.getString(3));
    data.put("village_name", rs.getString(4));
    data.put("totalPensioners", rs.getString(5));
    data.put("dead", rs.getString(6));
    data.put("pm", rs.getString(7));
    data.put("icfs", rs.getString(8));
    data.put("abh", rs.getString(9));
    data.put("partAentered", rs.getString(10));
    data.put("partALive", rs.getString(11));
    data.put("partAdeathandPM", rs.getString(12));
    data.put("partAicfs", rs.getString(13));
    data.put("partAbhandnot", rs.getString(14));
    data.put("ortho", rs.getString(15));
    data.put("visual", rs.getString(16));
    data.put("hearing", rs.getString(17));
    data.put("mentalre", rs.getString(18));
    data.put("mentalill", rs.getString(19));
    data.put("multiple", rs.getString(20));
    data.put("beforeAss", rs.getString(21));
    data.put("afterAss", rs.getString(22));
    data.put("id", i++);
    partBList.add(data);
    }
    }



    } catch (SQLException sqlException) {
    sqlException.printStackTrace();
   con.rollback();
    throw new SADAREMException();
    } catch (Exception e) {
    e.printStackTrace();
   con.rollback();
    throw new SADAREMException();
    } finally {
    DBConnection.closeResultSet(rs);
    DBConnection.closeStatement(cstmt);
    DBConnection.closeConnection(con);
    }
    return partBList;
    }
     */
    //Added by mohan on 05/07/2011
    /**
     * this method fetches part B information from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList statusReportforPartBAssessed(DataSource ds, String district_id, String mandal_id, String village_id, String phase, String header)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        //Statement stmt = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        ArrayList data = new ArrayList();
        String sql = null;
        String phaseName = null;
        try {
            con = DBConnection.getConnection();
           // stmt = con.createStatement();

            if (phase.equalsIgnoreCase("ALL")) {
                phaseName = "PhaseI','PhaseII','PhaseIII','PhaseIV";
            } else {
                phaseName = phase;
            }


            if (!district_id.equals("0") && mandal_id.equals("") && village_id.equals("")) {

                if (header.equals("3") || header.equals("4") || header.equals("5") || header.equals("6") || header.equals("7") || header.equals("8") || header.equals("9")) {

                    sql = "select distinct coalesce(b.person_code,'-') sadaremcode, a.firstname + SPACE(2) + a.mid_name + SPACE(2) + coalesce(a.lastname,'') as name,"
                            + "a.fname,a.age,CASE WHEN a.sex = 1 THEN 'M' WHEN a.sex = 2 THEN 'F' ELSE 'Not Entered' END as gender,a.phc,a.pensionid,'-' as per,"
                            + "a.pensionphase,coalesce(c.district_name,'-') as district,coalesce(d.mandal_name,'-') as mandal,coalesce(v.village_name,'-') as village,coalesce(b.reasonforpersonstatus,'-') "
                            + "from dbo.Disabled_Pension a "
                            + "left join tblPerson_personal_details b  with (nolock) on(a.distcode=b.district_id and a.mndcode=b.mandal_id and a.pensionid=b.pensioncard_no) "
                            + //"left join tblPerson_Disability_TotalValue_Details e on(a.distcode=substring(e.person_code,1,2) and a.mndcode=substring(e.person_code,6,2) and b.person_code=e.person_code) "+
                            "left join tblDistrict_details c on(a.distcode=c.district_id and b.district_id=c.district_id) "
                            + "left join tblMandal_details d on(a.distcode=d.district_id and a.mndcode=b.mandal_id and b.mandal_id=d.mandal_id and b.district_id = d.district_id and c.district_id=d.district_id) "
                            + "left join tblVillage_details v on(SUBSTRING(a.habcode,8,3)=v.village_id and a.distcode=v.district_id and a.mndcode=v.mandal_id and b.district_id=v.district_id and b.mandal_id=v.mandal_id "
                            + "and b.village_id=v.village_id and c.district_id=v.district_id and d.mandal_id=v.mandal_id and d.district_id=v.district_id) where ";

                    if (header.equals("3")) {
                        sql += "a.distcode=? and a.pensionphase in('" + phaseName + "') order by a.pensionid";
                    } else if (header.equals("4")) {
                        sql += "a.distcode=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='Dead' order by a.pensionid";
                    } else if (header.equals("5")) {
                        sql += "a.distcode=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='Permanent Migration' order by a.pensionid";
                    } else if (header.equals("6")) {
                        sql += "a.distcode=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Permanent Migration' or a.reasonforpersonstatus='Dead') order by a.pensionid";
                    } else if (header.equals("7")) {
                        sql += "a.distcode=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='ICFS Deletion' order by a.pensionid";
                    } else if (header.equals("8")) {
                        sql += "a.distcode=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Not Eligible' or a.reasonforpersonstatus='Abhayahastham') order by a.pensionid";
                    } else if (header.equals("9")) {
                        sql += "a.distcode=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus!='Dead' and a.reasonforpersonstatus!='Permanent Migration') order by a.pensionid";
                    }
                } else if (header.equals("10") || header.equals("11") || header.equals("12") || header.equals("13") || header.equals("14") || header.equals("15")) {

                    sql = "select a.person_code,a.surname + SPACE(2) + a.first_name name,a.relation_name,a.age_years,"
                            + "case when a.gender='1' then 'M' when a.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when a.typeof_disability='1' then 'Locomotor/OH' when a.typeof_disability='2' then 'Visual Impairment' "
                            + "when a.typeof_disability='3' then 'Hearing Impairment' when a.typeof_disability='4' then 'Mental Retardation' "
                            + "when a.typeof_disability='5' then 'Mental Illness'  when a.typeof_disability='6' then 'Multiple Disability' else 'NA' end as phc,"
                            + "a.pensioncard_no as person_id,coalesce(e.totaldisability,'0') as rationcard_number,a.pensionphase,b.district_name,c.mandal_name,"
                            + "d.village_name,coalesce(a.reasonforpersonstatus,'-') from tblPerson_Personal_Details a  with (nolock) "
                            + "left join tblPerson_Disability_TotalValue_Details e on(a.district_id=substring(e.person_code,1,2) and a.mandal_id=substring(e.person_code,6,2) and a.person_code=e.person_code)"
                            + "left join tblDistrict_details b on(a.district_id=b.district_id) "
                            + "left join tblmandal_details c on(c.district_id=b.district_id and c.mandal_id=a.mandal_id and a.district_id=c.district_id) "
                            + "left join tblVillage_details d on(d.district_id=a.district_id and d.mandal_id=a.mandal_id and d.village_id=a.village_id and b.district_id=d.district_id and c.mandal_id=d.mandal_id)";

                    if (header.equals("10")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "')";
                    } else if (header.equals("11")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='Live' and view_edit_mode='View'";
                    } else if (header.equals("12")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Dead' or a.reasonforpersonstatus='Permanent Migration') and a.view_edit_mode='View'";
                    } else if (header.equals("13")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='ICFS Deletion' and a.view_edit_mode='View'";
                    } else if (header.equals("14")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Not Eligible' or a.reasonforpersonstatus='Abhayahastham') and a.view_edit_mode='View'";
                    } else if (header.equals("15")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and  view_edit_mode='View' and (a.reasonforpersonstatus='ICFS Deletion' or a.reasonforpersonstatus='Dead' or a.reasonforpersonstatus='Permanent Migration' or a.reasonforpersonstatus='Live' or a.reasonforpersonstatus='Not Eligible' or a.reasonforpersonstatus='Abhayahastham')";
                    }

                } else if (header.equals("17") || header.equals("18") || header.equals("19") || header.equals("20") || header.equals("21") || header.equals("22") || header.equals("23")) {

                    sql = "select distinct a.person_code,a.surname + SPACE(2) + a.first_name name,a.relation_name,a.age_years,"
                            + "case when a.gender='1' then 'M' when a.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when b.Disability_ID='1' then 'Locomotor/OH' when b.Disability_ID='2' then 'Visual Impairment' when b.Disability_ID='3' "
                            + "then 'Hearing Impairment' when b.Disability_ID='4' then 'Mental Retardation' when b.Disability_ID='5' then 'Mental Illness'  "
                            + "when b.Disability_ID='6' then 'Multiple Disability' else 'NA' end as phc,a.pensioncard_no as person_id,"
                            + "coalesce(c.totaldisability,'0') as rationcard_number,a.pensionphase,d.district_name,e.mandal_name,f.village_name,coalesce(a.reasonforpersonstatus,'-') "
                            + "from tblPerson_Personal_Details a  with (nolock) join  dbo.tblPerson_Disability_Details b on(a.person_code=b.person_code) "
                            + "left join TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c on(c.person_code=a.person_code and c.person_code=b.person_code) "
                            + "left join tblDistrict_details d on(a.district_id=d.district_id) left join tblmandal_details e "
                            + "on(a.district_id=e.district_id and a.mandal_id=e.mandal_id) left join tblVillage_details f "
                            + "on(f.district_id=a.district_id and f.mandal_id=a.mandal_id and f.village_id=a.village_id and f.district_id=d.district_id and f.mandal_id=e.mandal_id) ";

                    if (header.equals("17")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='1' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("18")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='2' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("19")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='3' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("20")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='4' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("21")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='5' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("22")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='6'";
                    } else if (header.equals("23")) {
                        sql += " where a.district_id=? and a.pensionphase in('" + phaseName + "') and (b.Disability_ID='1' or  b.Disability_ID='2' or typeof_disability='3' or b.Disability_ID='4' or b.Disability_ID='5' or b.Disability_ID='6') and a.status='Active' and b.status='Active'";
                    }

                } else if (header.equals("24")) {

                    sql = "select c.person_code,c.surname + SPACE(2) + c.first_name name,c.relation_name,c.age_years,"
                            + "case when c.gender='1' then 'M' when c.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when d.Disability_ID='1' then 'Locomotor/OH' when d.Disability_ID='2' then 'Visual Impairment' "
                            + "when d.Disability_ID='3' then 'Hearing Impairment' when d.Disability_ID='4' then 'Mental Retardation' "
                            + "when d.Disability_ID='5' then 'Mental Illness'  when d.Disability_ID='6' then 'Multiple Disability' else 'NA' end as phc,"
                            + "c.pensioncard_no as person_id,coalesce(f.totaldisability,'0') as rationcard_number,c.pensionphase,a.district_name,"
                            + "b.mandal_name,e.village_name,coalesce(c.reasonforpersonstatus,'-') from tblPerson_Personal_Details c  with (nolock) join tblRejectPerson_Details d on(c.person_code=d.person_code) "
                            + "left join tblPerson_Disability_TotalValue_Details f on(c.district_id=substring(f.person_code,1,2) and c.mandal_id=substring(f.person_code,6,2) and c.person_code=f.person_code) "
                            + "join tblDistrict_details a on(a.district_id=c.district_id) "
                            + "join tblMandal_details b on(b.district_id=c.district_id and b.mandal_id=c.mandal_id and b.district_id=a.district_id) "
                            + "join tblVillage_details e on(e.district_id=c.district_id and e.mandal_id=c.mandal_id and e.village_id=c.village_id and e.district_id=a.district_id and e.mandal_id=b.mandal_id)"
                            + " and c.district_id =? and c.pensionphase in('" + phaseName + "') and c.status='Active' "
                            + "and d.status='Active' and c.view_edit_mode='View'";

                } else if (header.equals("25")) {
                    sql = "select d.person_code,d.surname + SPACE(2) + d.first_name name,d.relation_name,d.age_years,"
                            + "case when d.gender='1' then 'M' when d.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when e.Disability_ID='1' then 'Locomotor/OH' when e.Disability_ID='2' then 'Visual Impairment' when e.Disability_ID='3' "
                            + "then 'Hearing Impairment' when e.Disability_ID='4' then 'Mental Retardation' when e.Disability_ID='5' then 'Mental Illness'  "
                            + "when e.Disability_ID='6' then 'Multiple Disability'  else 'NA' end as phc,d.pensioncard_no as person_id,"
                            + "coalesce(c.totaldisability,'0') as rationcard_number,d.pensionphase,f.district_name,m.mandal_name,v.village_name,coalesce(d.reasonforpersonstatus,'-') from "
                            + "TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c join TBLPERSON_PERSONAL_DETAILS d  with (nolock) on (c.person_code=d.person_code) "
                            + "left join tblRejectPerson_Details e on(e.person_code=d.person_code and e.person_code=c.person_code) "
                            + "join tblDistrict_details f on(d.district_id=f.district_id) "
                            + "join tblMandal_details m on(d.district_id=m.district_id and d.mandal_id=m.mandal_id and m.district_id=f.district_id) "
                            + "join tblVillage_details v on(d.district_id=v.district_id and d.mandal_id=v.mandal_id and d.village_id=v.village_id and v.district_id=m.district_id and v.mandal_id=m.mandal_id) "
                            + "where d.district_id=? and d.pensionphase in('" + phaseName + "') and c.status='Active' and d.status='Active' and c.totaldisability < 40";
                } else if (header.equals("26")) {
                    sql = "select d.person_code,d.surname + SPACE(2) + d.first_name name,d.relation_name,d.age_years,"
                            + "case when d.gender='1' then 'M' when d.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when e.Disability_ID='1' then 'Locomotor/OH' when e.Disability_ID='2' then 'Visual Impairment' when e.Disability_ID='3' "
                            + "then 'Hearing Impairment' when e.Disability_ID='4' then 'Mental Retardation' when e.Disability_ID='5' then 'Mental Illness'  "
                            + "when e.Disability_ID='6' then 'Multiple Disability'  else 'NA' end as phc,d.pensioncard_no as person_id,"
                            + "coalesce(c.totaldisability,'0') as rationcard_number,d.pensionphase,f.district_name,m.mandal_name,v.village_name,coalesce(d.reasonforpersonstatus,'-') from "
                            + "TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c join TBLPERSON_PERSONAL_DETAILS d  with (nolock) on (c.person_code=d.person_code) "
                            + "left join tblRejectPerson_Details e on(e.person_code=d.person_code and e.person_code=c.person_code) "
                            + "join tblDistrict_details f on(d.district_id=f.district_id) "
                            + "join tblMandal_details m on(d.district_id=m.district_id and d.mandal_id=m.mandal_id and m.district_id=f.district_id) "
                            + "join tblVillage_details v on(d.district_id=v.district_id and d.mandal_id=v.mandal_id and d.village_id=v.village_id and v.district_id=m.district_id and v.mandal_id=m.mandal_id) "
                            + "where d.district_id=? and d.pensionphase in('" + phaseName + "') and c.status='Active' and d.status='Active' and c.totaldisability < 40"
                            + "union all "
                            + "select c.person_code,c.surname + SPACE(2) + c.first_name name,c.relation_name,c.age_years,"
                            + "case when c.gender='1' then 'M' when c.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when d.Disability_ID='1' then 'Locomotor/OH' when d.Disability_ID='2' then 'Visual Impairment' "
                            + "when d.Disability_ID='3' then 'Hearing Impairment' when d.Disability_ID='4' then 'Mental Retardation' "
                            + "when d.Disability_ID='5' then 'Mental Illness'  when d.Disability_ID='6' then 'Multiple Disability' else 'NA' end as phc,"
                            + "c.pensioncard_no as person_id,coalesce(f.totaldisability,'0') as rationcard_number,c.pensionphase,a.district_name,"
                            + "b.mandal_name,e.village_name,coalesce(c.reasonforpersonstatus,'-') from tblPerson_Personal_Details c  with (nolock) join tblRejectPerson_Details d on(c.person_code=d.person_code) "
                            + "left join tblPerson_Disability_TotalValue_Details f on(c.district_id=substring(f.person_code,1,2) and c.mandal_id=substring(f.person_code,6,2) and c.person_code=f.person_code) "
                            + "join tblDistrict_details a on(a.district_id=c.district_id) "
                            + "join tblMandal_details b on(b.district_id=c.district_id and b.mandal_id=c.mandal_id and b.district_id=a.district_id) "
                            + "join tblVillage_details e on(e.district_id=c.district_id and e.mandal_id=c.mandal_id and e.village_id=c.village_id and e.district_id=a.district_id and e.mandal_id=b.mandal_id)"
                            + " and c.district_id =? and c.pensionphase in('" + phaseName + "') and c.status='Active' "
                            + "and d.status='Active' and c.view_edit_mode='View'";
                }


            }

            if (!district_id.equals("0") && !mandal_id.equals("") && village_id.equals("")) {

                if (header.equals("3") || header.equals("4") || header.equals("5") || header.equals("6") || header.equals("7") || header.equals("8") || header.equals("9")) {

                    sql = "select distinct coalesce(b.person_code,'-') sadaremcode, a.firstname + SPACE(2) + a.mid_name + SPACE(2) + coalesce(a.lastname,'') as name,"
                            + "a.fname,a.age,CASE WHEN a.sex = 1 THEN 'M' WHEN a.sex = 2 THEN 'F' ELSE 'Not Entered' END as gender,a.phc,a.pensionid,'-' as per,"
                            + "a.pensionphase,coalesce(c.district_name,'-') as district,coalesce(d.mandal_name,'-') as mandal,coalesce(v.village_name,'-') as village,coalesce(b.reasonforpersonstatus,'-') "
                            + "from dbo.Disabled_Pension a "
                            + "left join tblPerson_personal_details b  with (nolock)  on(a.distcode=b.district_id and a.mndcode=b.mandal_id and a.pensionid=b.pensioncard_no) "
                            + //"left join tblPerson_Disability_TotalValue_Details e on(a.distcode=substring(e.person_code,1,2) and a.mndcode=substring(e.person_code,6,2) and b.person_code=e.person_code) "+
                            "left join tblDistrict_details c on(a.distcode=c.district_id and b.district_id=c.district_id) "
                            + "left join tblMandal_details d on(a.distcode=d.district_id and a.mndcode=b.mandal_id and b.mandal_id=d.mandal_id and b.district_id = d.district_id and c.district_id=d.district_id) "
                            + "left join tblVillage_details v on(SUBSTRING(a.habcode,8,3)=v.village_id and a.distcode=v.district_id and a.mndcode=v.mandal_id and b.district_id=v.district_id and b.mandal_id=v.mandal_id "
                            + "and b.village_id=v.village_id and c.district_id=v.district_id and d.mandal_id=v.mandal_id and d.district_id=v.district_id) where ";

                    if (header.equals("3")) {
                        sql += "a.distcode=? and a.mndcode=? and a.pensionphase in('" + phaseName + "') order by a.pensionid";
                    } else if (header.equals("4")) {
                        sql += "a.distcode=? and a.mndcode=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='Dead' order by a.pensionid";
                    } else if (header.equals("5")) {
                        sql += "a.distcode=? and a.mndcode=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='Permanent Migration' order by a.pensionid";
                    } else if (header.equals("6")) {
                        sql += "a.distcode=? and a.mndcode=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Permanent Migration' or a.reasonforpersonstatus='Dead') order by a.pensionid";
                    } else if (header.equals("7")) {
                        sql += "a.distcode=? and a.mndcode=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='ICFS Deletion' order by a.pensionid";
                    } else if (header.equals("8")) {
                        sql += "a.distcode=? and a.mndcode=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Not Eligible' or a.reasonforpersonstatus='Abhayahastham') order by a.pensionid";
                    } else if (header.equals("9")) {
                        sql += "a.distcode=? and a.mndcode=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus!='Dead' and a.reasonforpersonstatus!='Permanent Migration') order by a.pensionid";
                    }
                } else if (header.equals("10") || header.equals("11") || header.equals("12") || header.equals("13") || header.equals("14") || header.equals("15")) {

                    sql = "select a.person_code,a.surname + SPACE(2) + a.first_name name,a.relation_name,a.age_years,"
                            + "case when a.gender='1' then 'M' when a.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when a.typeof_disability='1' then 'Locomotor/OH' when a.typeof_disability='2' then 'Visual Impairment' "
                            + "when a.typeof_disability='3' then 'Hearing Impairment' when a.typeof_disability='4' then 'Mental Retardation' "
                            + "when a.typeof_disability='5' then 'Mental Illness'  when a.typeof_disability='6' then 'Multiple Disability' else 'NA' end as phc,"
                            + "a.pensioncard_no as person_id,coalesce(e.totaldisability,'0') as rationcard_number,a.pensionphase,b.district_name,c.mandal_name,"
                            + "d.village_name,coalesce(a.reasonforpersonstatus,'-') from tblPerson_Personal_Details a  with (nolock)  "
                            + "left join tblPerson_Disability_TotalValue_Details e on(a.district_id=substring(e.person_code,1,2) and a.mandal_id=substring(e.person_code,6,2) and a.person_code=e.person_code)"
                            + "left join tblDistrict_details b on(a.district_id=b.district_id) "
                            + "left join tblmandal_details c on(c.district_id=b.district_id and c.mandal_id=a.mandal_id and a.district_id=c.district_id) "
                            + "left join tblVillage_details d on(d.district_id=a.district_id and d.mandal_id=a.mandal_id and d.village_id=a.village_id and b.district_id=d.district_id and c.mandal_id=d.mandal_id)";

                    if (header.equals("10")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "')";
                    } else if (header.equals("11")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='Live' and view_edit_mode='View'";
                    } else if (header.equals("12")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Dead' or a.reasonforpersonstatus='Permanent Migration') and a.view_edit_mode='View'";
                    } else if (header.equals("13")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='ICFS Deletion' and a.view_edit_mode='View'";
                    } else if (header.equals("14")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Not Eligible' or a.reasonforpersonstatus='Abhayahastham') and a.view_edit_mode='View'";
                    } else if (header.equals("15")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and  view_edit_mode='View' and (a.reasonforpersonstatus='ICFS Deletion' or a.reasonforpersonstatus='Dead' or a.reasonforpersonstatus='Permanent Migration' or a.reasonforpersonstatus='Live' or a.reasonforpersonstatus='Not Eligible' or a.reasonforpersonstatus='Abhayahastham')";
                    }

                } else if (header.equals("17") || header.equals("18") || header.equals("19") || header.equals("20") || header.equals("21") || header.equals("22") || header.equals("23")) {

                    sql = "select distinct a.person_code,a.surname + SPACE(2) + a.first_name name,a.relation_name,a.age_years,"
                            + "case when a.gender='1' then 'M' when a.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when b.Disability_ID='1' then 'Locomotor/OH' when b.Disability_ID='2' then 'Visual Impairment' when b.Disability_ID='3' "
                            + "then 'Hearing Impairment' when b.Disability_ID='4' then 'Mental Retardation' when b.Disability_ID='5' then 'Mental Illness'  "
                            + "when b.Disability_ID='6' then 'Multiple Disability' else 'NA' end as phc,a.pensioncard_no as person_id,"
                            + "coalesce(c.totaldisability,'0') as rationcard_number,a.pensionphase,d.district_name,e.mandal_name,f.village_name,coalesce(a.reasonforpersonstatus,'-') "
                            + "from tblPerson_Personal_Details a  with (nolock) join  dbo.tblPerson_Disability_Details b on(a.person_code=b.person_code) "
                            + "left join TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c on(c.person_code=a.person_code and c.person_code=b.person_code) "
                            + "left join tblDistrict_details d on(a.district_id=d.district_id) left join tblmandal_details e "
                            + "on(a.district_id=e.district_id and a.mandal_id=e.mandal_id) left join tblVillage_details f "
                            + "on(f.district_id=a.district_id and f.mandal_id=a.mandal_id and f.village_id=a.village_id and f.district_id=d.district_id and f.mandal_id=e.mandal_id) ";

                    if (header.equals("17")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='1' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("18")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='2' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("19")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='3' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("20")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='4' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("21")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='5' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("22")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='6' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("23")) {
                        sql += " where a.district_id=? and a.mandal_id=? and a.pensionphase in('" + phaseName + "') and (b.Disability_ID='1' or  b.Disability_ID='2' or typeof_disability='3' or b.Disability_ID='4' or b.Disability_ID='5' or b.Disability_ID='6') and a.status='Active' and b.status='Active'";
                    }

                } else if (header.equals("24")) {
                    sql = "select c.person_code,c.surname + SPACE(2) + c.first_name name,c.relation_name,c.age_years,"
                            + "case when c.gender='1' then 'M' when c.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when d.Disability_ID='1' then 'Locomotor/OH' when d.Disability_ID='2' then 'Visual Impairment' "
                            + "when d.Disability_ID='3' then 'Hearing Impairment' when d.Disability_ID='4' then 'Mental Retardation' "
                            + "when d.Disability_ID='5' then 'Mental Illness'  when d.Disability_ID='6' then 'Multiple Disability' else 'NA' end as phc,"
                            + "c.pensioncard_no as person_id,coalesce(f.totaldisability,'0') as rationcard_number,c.pensionphase,a.district_name,"
                            + "b.mandal_name,e.village_name,coalesce(c.reasonforpersonstatus,'-') from tblPerson_Personal_Details c  with (nolock)  join tblRejectPerson_Details d on(c.person_code=d.person_code) "
                            + "left join tblPerson_Disability_TotalValue_Details f on(c.district_id=substring(f.person_code,1,2) and c.mandal_id=substring(f.person_code,6,2) and c.person_code=f.person_code) "
                            + "join tblDistrict_details a on(a.district_id=c.district_id) "
                            + "join tblMandal_details b on(b.district_id=c.district_id and b.mandal_id=c.mandal_id and b.district_id=a.district_id) "
                            + "join tblVillage_details e on(e.district_id=c.district_id and e.mandal_id=c.mandal_id and e.village_id=c.village_id and e.district_id=a.district_id and e.mandal_id=b.mandal_id)"
                            + " and c.district_id =? and c.mandal_id=? and c.pensionphase in('" + phaseName + "') and c.status='Active' "
                            + "and d.status='Active' and c.view_edit_mode='View'";
                } else if (header.equals("25")) {
                    sql = "select d.person_code,d.surname + SPACE(2) + d.first_name name,d.relation_name,d.age_years,"
                            + "case when d.gender='1' then 'M' when d.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when e.Disability_ID='1' then 'Locomotor/OH' when e.Disability_ID='2' then 'Visual Impairment' when e.Disability_ID='3' "
                            + "then 'Hearing Impairment' when e.Disability_ID='4' then 'Mental Retardation' when e.Disability_ID='5' then 'Mental Illness'  "
                            + "when e.Disability_ID='6' then 'Multiple Disability'  else 'NA' end as phc,d.pensioncard_no as person_id,"
                            + "coalesce(c.totaldisability,'0') as rationcard_number,d.pensionphase,f.district_name,m.mandal_name,v.village_name,coalesce(d.reasonforpersonstatus,'-') from "
                            + "TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c join TBLPERSON_PERSONAL_DETAILS d  with (nolock) on (c.person_code=d.person_code) "
                            + "left join tblRejectPerson_Details e on(e.person_code=d.person_code and e.person_code=c.person_code) "
                            + "join tblDistrict_details f on(d.district_id=f.district_id) "
                            + "join tblMandal_details m on(d.district_id=m.district_id and d.mandal_id=m.mandal_id and m.district_id=f.district_id) "
                            + "join tblVillage_details v on(d.district_id=v.district_id and d.mandal_id=v.mandal_id and d.village_id=v.village_id and v.district_id=m.district_id and v.mandal_id=m.mandal_id) "
                            + "where d.district_id=? and d.mandal_id=? and d.pensionphase in('" + phaseName + "') and c.status='Active' and d.status='Active' and c.totaldisability < 40";

                } else if (header.equals("26")) {
                    sql = "select d.person_code,d.surname + SPACE(2) + d.first_name name,d.relation_name,d.age_years,"
                            + "case when d.gender='1' then 'M' when d.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when e.Disability_ID='1' then 'Locomotor/OH' when e.Disability_ID='2' then 'Visual Impairment' when e.Disability_ID='3' "
                            + "then 'Hearing Impairment' when e.Disability_ID='4' then 'Mental Retardation' when e.Disability_ID='5' then 'Mental Illness'  "
                            + "when e.Disability_ID='6' then 'Multiple Disability'  else 'NA' end as phc,d.pensioncard_no as person_id,"
                            + "coalesce(c.totaldisability,'0') as rationcard_number,d.pensionphase,f.district_name,m.mandal_name,v.village_name,coalesce(d.reasonforpersonstatus,'-') from "
                            + "TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c join TBLPERSON_PERSONAL_DETAILS d  with (nolock) on (c.person_code=d.person_code) "
                            + "left join tblRejectPerson_Details e on(e.person_code=d.person_code and e.person_code=c.person_code) "
                            + "join tblDistrict_details f on(d.district_id=f.district_id) "
                            + "join tblMandal_details m on(d.district_id=m.district_id and d.mandal_id=m.mandal_id and m.district_id=f.district_id) "
                            + "join tblVillage_details v on(d.district_id=v.district_id and d.mandal_id=v.mandal_id and d.village_id=v.village_id and v.district_id=m.district_id and v.mandal_id=m.mandal_id) "
                            + "where d.district_id=? and d.mandal_id=? and d.pensionphase in('" + phaseName + "') and c.status='Active' and d.status='Active' and c.totaldisability < 40"
                            + "union all "
                            + "select c.person_code,c.surname + SPACE(2) + c.first_name name,c.relation_name,c.age_years,"
                            + "case when c.gender='1' then 'M' when c.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when d.Disability_ID='1' then 'Locomotor/OH' when d.Disability_ID='2' then 'Visual Impairment' "
                            + "when d.Disability_ID='3' then 'Hearing Impairment' when d.Disability_ID='4' then 'Mental Retardation' "
                            + "when d.Disability_ID='5' then 'Mental Illness'  when d.Disability_ID='6' then 'Multiple Disability' else 'NA' end as phc,"
                            + "c.pensioncard_no as person_id,coalesce(f.totaldisability,'0') as rationcard_number,c.pensionphase,a.district_name,"
                            + "b.mandal_name,e.village_name,coalesce(c.reasonforpersonstatus,'-') from tblPerson_Personal_Details c  with (nolock) join tblRejectPerson_Details d on(c.person_code=d.person_code) "
                            + "left join tblPerson_Disability_TotalValue_Details f on(c.district_id=substring(f.person_code,1,2) and c.mandal_id=substring(f.person_code,6,2) and c.person_code=f.person_code) "
                            + "join tblDistrict_details a on(a.district_id=c.district_id) "
                            + "join tblMandal_details b on(b.district_id=c.district_id and b.mandal_id=c.mandal_id and b.district_id=a.district_id) "
                            + "join tblVillage_details e on(e.district_id=c.district_id and e.mandal_id=c.mandal_id and e.village_id=c.village_id and e.district_id=a.district_id and e.mandal_id=b.mandal_id)"
                            + " and c.district_id =? and c.mandal_id=? and c.pensionphase in('" + phaseName + "') and c.status='Active' "
                            + "and d.status='Active' and c.view_edit_mode='View'";
                }
            }


            if (!district_id.equals("0") && !mandal_id.equals("") && !village_id.equals("")) {

                if (header.equals("3") || header.equals("4") || header.equals("5") || header.equals("6") || header.equals("7") || header.equals("8") || header.equals("9")) {

                    sql = "select distinct coalesce(b.person_code,'-') sadaremcode, a.firstname + SPACE(2) + a.mid_name + SPACE(2) + coalesce(a.lastname,'') as name,"
                            + "a.fname,a.age,CASE WHEN a.sex = 1 THEN 'M' WHEN a.sex = 2 THEN 'F' ELSE 'Not Entered' END as gender,a.phc,a.pensionid,'-' as per,"
                            + "a.pensionphase,coalesce(c.district_name,'-') as district,coalesce(d.mandal_name,'-') as mandal,coalesce(v.village_name,'-') as village,coalesce(b.reasonforpersonstatus,'-') "
                            + "from dbo.Disabled_Pension a "
                            + "left join tblPerson_personal_details b  with (nolock) on(a.distcode=b.district_id and a.mndcode=b.mandal_id and a.pensionid=b.pensioncard_no) "
                            + "left join tblDistrict_details c on(a.distcode=c.district_id and b.district_id=c.district_id) "
                            + "left join tblMandal_details d on(a.distcode=d.district_id and a.mndcode=b.mandal_id and b.mandal_id=d.mandal_id and b.district_id = d.district_id and c.district_id=d.district_id) "
                            + "left join tblVillage_details v on(SUBSTRING(a.habcode,8,3)=v.village_id and a.distcode=v.district_id and a.mndcode=v.mandal_id and b.district_id=v.district_id and b.mandal_id=v.mandal_id "
                            + "and b.village_id=v.village_id and c.district_id=v.district_id and d.mandal_id=v.mandal_id and d.district_id=v.district_id) where ";

                    if (header.equals("3")) {
                        sql += "a.distcode=? and a.mndcode=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') order by a.pensionid";
                    } else if (header.equals("4")) {
                        sql += "a.distcode=? and a.mndcode=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='Dead' order by a.pensionid";
                    } else if (header.equals("5")) {
                        sql += "a.distcode=? and a.mndcode=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='Permanent Migration' order by a.pensionid";
                    } else if (header.equals("6")) {
                        sql += "a.distcode=? and a.mndcode=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Permanent Migration' or a.reasonforpersonstatus='Dead') order by a.pensionid";
                    } else if (header.equals("7")) {
                        sql += "a.distcode=? and a.mndcode=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='ICFS Deletion' order by a.pensionid";
                    } else if (header.equals("8")) {
                        sql += "a.distcode=? and a.mndcode=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Not Eligible' or a.reasonforpersonstatus='Abhayahastham') order by a.pensionid";
                    } else if (header.equals("9")) {
                        sql += "a.distcode=? and a.mndcode=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus!='Dead' and a.reasonforpersonstatus!='Permanent Migration') order by a.pensionid";
                    }
                } else if (header.equals("10") || header.equals("11") || header.equals("12") || header.equals("13") || header.equals("14") || header.equals("15")) {

                    sql = "select a.person_code,a.surname + SPACE(2) + a.first_name name,a.relation_name,a.age_years,"
                            + "case when a.gender='1' then 'M' when a.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when a.typeof_disability='1' then 'Locomotor/OH' when a.typeof_disability='2' then 'Visual Impairment' "
                            + "when a.typeof_disability='3' then 'Hearing Impairment' when a.typeof_disability='4' then 'Mental Retardation' "
                            + "when a.typeof_disability='5' then 'Mental Illness'  when a.typeof_disability='6' then 'Multiple Disability' else 'NA' end as phc,"
                            + "a.pensioncard_no as person_id,coalesce(f.totaldisability,'0') as rationcard_number,a.pensionphase,b.district_name,c.mandal_name,"
                            + "d.village_name,coalesce(a.reasonforpersonstatus,'-') from tblPerson_Personal_Details a  with (nolock) "
                            + "left join tblPerson_Disability_TotalValue_Details f on(a.district_id=substring(f.person_code,1,2) and a.mandal_id=substring(f.person_code,6,2) and a.person_code=f.person_code) "
                            + "left join tblDistrict_details b on(a.district_id=b.district_id) "
                            + "left join tblmandal_details c on(c.district_id=b.district_id and c.mandal_id=a.mandal_id and a.district_id=c.district_id) "
                            + "left join tblVillage_details d on(d.district_id=a.district_id and d.mandal_id=a.mandal_id and d.village_id=a.village_id and b.district_id=d.district_id and c.mandal_id=d.mandal_id)";

                    if (header.equals("10")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "')";
                    } else if (header.equals("11")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='Live' and view_edit_mode='View'";
                    } else if (header.equals("12")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Dead' or a.reasonforpersonstatus='Permanent Migration') and a.view_edit_mode='View'";
                    } else if (header.equals("13")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and a.reasonforpersonstatus='ICFS Deletion' and a.view_edit_mode='View'";
                    } else if (header.equals("14")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and (a.reasonforpersonstatus='Not Eligible' or a.reasonforpersonstatus='Abhayahastham') and a.view_edit_mode='View'";
                    } else if (header.equals("15")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and  view_edit_mode='View' and (a.reasonforpersonstatus='ICFS Deletion' or a.reasonforpersonstatus='Dead' or a.reasonforpersonstatus='Permanent Migration' or a.reasonforpersonstatus='Live' or a.reasonforpersonstatus='Not Eligible' or a.reasonforpersonstatus='Abhayahastham')";
                    }

                } else if (header.equals("17") || header.equals("18") || header.equals("19") || header.equals("20") || header.equals("21") || header.equals("22") || header.equals("23")) {

                    sql = "select distinct a.person_code,a.surname + SPACE(2) + a.first_name name,a.relation_name,a.age_years,"
                            + "case when a.gender='1' then 'M' when a.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when b.Disability_ID='1' then 'Locomotor/OH' when b.Disability_ID='2' then 'Visual Impairment' when b.Disability_ID='3' "
                            + "then 'Hearing Impairment' when b.Disability_ID='4' then 'Mental Retardation' when b.Disability_ID='5' then 'Mental Illness'  "
                            + "when b.Disability_ID='6' then 'Multiple Disability' else 'NA' end as phc,a.pensioncard_no as person_id,"
                            + "coalesce(c.totaldisability,'0') as rationcard_number,a.pensionphase,d.district_name,e.mandal_name,f.village_name,coalesce(a.reasonforpersonstatus,'-') "
                            + "from tblPerson_Personal_Details a  with (nolock) join  dbo.tblPerson_Disability_Details b on(a.person_code=b.person_code) "
                            + "left join TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c on(c.person_code=a.person_code and c.person_code=b.person_code) "
                            + "left join tblDistrict_details d on(a.district_id=d.district_id) left join tblmandal_details e "
                            + "on(a.district_id=e.district_id and a.mandal_id=e.mandal_id) left join tblVillage_details f "
                            + "on(f.district_id=a.district_id and f.mandal_id=a.mandal_id and f.village_id=a.village_id and f.district_id=d.district_id and f.mandal_id=e.mandal_id) ";

                    if (header.equals("17")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='1' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("18")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='2' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("19")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='3' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("20")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='4' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("21")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='5' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("22")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and b.Disability_ID='6' and a.status='Active' and b.status='Active'";
                    } else if (header.equals("23")) {
                        sql += " where a.district_id=? and a.mandal_id=? and SUBSTRING(a.habcode,8,3)=? and a.pensionphase in('" + phaseName + "') and (b.Disability_ID='1' or  b.Disability_ID='2' or typeof_disability='3' or b.Disability_ID='4' or b.Disability_ID='5' or b.Disability_ID='6') and a.status='Active' and b.status='Active'";
                    }

                } else if (header.equals("24")) {
                    sql = "select c.person_code,c.surname + SPACE(2) + c.first_name name,c.relation_name,c.age_years,"
                            + "case when c.gender='1' then 'M' when c.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when d.Disability_ID='1' then 'Locomotor/OH' when d.Disability_ID='2' then 'Visual Impairment' "
                            + "when d.Disability_ID='3' then 'Hearing Impairment' when d.Disability_ID='4' then 'Mental Retardation' "
                            + "when d.Disability_ID='5' then 'Mental Illness'  when d.Disability_ID='6' then 'Multiple Disability' else 'NA' end as phc,"
                            + "c.pensioncard_no as person_id,coalesce(f.totaldisability,'0') as rationcard_number,c.pensionphase,a.district_name,"
                            + "b.mandal_name,e.village_name,coalesce(c.reasonforpersonstatus,'-') from tblPerson_Personal_Details c  with (nolock) join tblRejectPerson_Details d on(c.person_code=d.person_code) "
                            + "left join tblPerson_Disability_TotalValue_Details f on(c.district_id=substring(f.person_code,1,2) and c.mandal_id=substring(f.person_code,6,2) and c.person_code=f.person_code) "
                            + "join tblDistrict_details a on(a.district_id=c.district_id) "
                            + "join tblMandal_details b on(b.district_id=c.district_id and b.mandal_id=c.mandal_id and b.district_id=a.district_id) "
                            + "join tblVillage_details e on(e.district_id=c.district_id and e.mandal_id=c.mandal_id and e.village_id=c.village_id and e.district_id=a.district_id and e.mandal_id=b.mandal_id)"
                            + " and c.district_id =? and c.mandal_id=? and c.village_id=? and c.pensionphase in('" + phaseName + "') and c.status='Active' "
                            + "and d.status='Active' and c.view_edit_mode='View'";
                } else if (header.equals("25")) {
                    sql = "select d.person_code,d.surname + SPACE(2) + d.first_name name,d.relation_name,d.age_years,"
                            + "case when d.gender='1' then 'M' when d.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when e.Disability_ID='1' then 'Locomotor/OH' when e.Disability_ID='2' then 'Visual Impairment' when e.Disability_ID='3' "
                            + "then 'Hearing Impairment' when e.Disability_ID='4' then 'Mental Retardation' when e.Disability_ID='5' then 'Mental Illness'  "
                            + "when e.Disability_ID='6' then 'Multiple Disability'  else 'NA' end as phc,d.pensioncard_no as person_id,"
                            + "coalesce(c.totaldisability,'0') as rationcard_number,d.pensionphase,f.district_name,m.mandal_name,v.village_name,coalesce(d.reasonforpersonstatus,'-') from "
                            + "TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c join TBLPERSON_PERSONAL_DETAILS d  with (nolock) on (c.person_code=d.person_code) "
                            + "left join tblRejectPerson_Details e on(e.person_code=d.person_code and e.person_code=c.person_code) "
                            + "join tblDistrict_details f on(d.district_id=f.district_id) "
                            + "join tblMandal_details m on(d.district_id=m.district_id and d.mandal_id=m.mandal_id and m.district_id=f.district_id) "
                            + "join tblVillage_details v on(d.district_id=v.district_id and d.mandal_id=v.mandal_id and d.village_id=v.village_id and v.district_id=m.district_id and v.mandal_id=m.mandal_id) "
                            + "where d.district_id=? and d.mandal_id=? and d.village_id=? and d.pensionphase in('" + phaseName + "') and c.status='Active' and d.status='Active' and c.totaldisability < 40";

                } else if (header.equals("26")) {
                    sql = "select d.person_code,d.surname + SPACE(2) + d.first_name name,d.relation_name,d.age_years,"
                            + "case when d.gender='1' then 'M' when d.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when e.Disability_ID='1' then 'Locomotor/OH' when e.Disability_ID='2' then 'Visual Impairment' when e.Disability_ID='3' "
                            + "then 'Hearing Impairment' when e.Disability_ID='4' then 'Mental Retardation' when e.Disability_ID='5' then 'Mental Illness'  "
                            + "when e.Disability_ID='6' then 'Multiple Disability'  else 'NA' end as phc,d.pensioncard_no as person_id,"
                            + "coalesce(c.totaldisability,'0') as rationcard_number,d.pensionphase,f.district_name,m.mandal_name,v.village_name,coalesce(d.reasonforpersonstatus,'-') from "
                            + "TBLPERSON_DISABILITY_TOTALVALUE_DETAILS c join TBLPERSON_PERSONAL_DETAILS d  with (nolock) on (c.person_code=d.person_code) "
                            + "left join tblRejectPerson_Details e on(e.person_code=d.person_code and e.person_code=c.person_code) "
                            + "join tblDistrict_details f on(d.district_id=f.district_id) "
                            + "join tblMandal_details m on(d.district_id=m.district_id and d.mandal_id=m.mandal_id and m.district_id=f.district_id) "
                            + "join tblVillage_details v on(d.district_id=v.district_id and d.mandal_id=v.mandal_id and d.village_id=v.village_id and v.district_id=m.district_id and v.mandal_id=m.mandal_id) "
                            + "where d.district_id=? and d.mandal_id=? and d.village_id=? and d.pensionphase in('" + phaseName + "') and c.status='Active' and d.status='Active' and c.totaldisability < 40"
                            + "union all "
                            + "select c.person_code,c.surname + SPACE(2) + c.first_name name,c.relation_name,c.age_years,"
                            + "case when c.gender='1' then 'M' when c.gender='2' then 'F' else 'Not Entered' end as gender,"
                            + "case when d.Disability_ID='1' then 'Locomotor/OH' when d.Disability_ID='2' then 'Visual Impairment' "
                            + "when d.Disability_ID='3' then 'Hearing Impairment' when d.Disability_ID='4' then 'Mental Retardation' "
                            + "when d.Disability_ID='5' then 'Mental Illness'  when d.Disability_ID='6' then 'Multiple Disability' else 'NA' end as phc,"
                            + "c.pensioncard_no as person_id,coalesce(f.totaldisability,'0') as rationcard_number,c.pensionphase,a.district_name,"
                            + "b.mandal_name,e.village_name,coalesce(c.reasonforpersonstatus,'-') from tblPerson_Personal_Details c  with (nolock) join tblRejectPerson_Details d on(c.person_code=d.person_code) "
                            + "left join tblPerson_Disability_TotalValue_Details f on(c.district_id=substring(f.person_code,1,2) and c.mandal_id=substring(f.person_code,6,2) and c.person_code=f.person_code) "
                            + "join tblDistrict_details a on(a.district_id=c.district_id) "
                            + "join tblMandal_details b on(b.district_id=c.district_id and b.mandal_id=c.mandal_id and b.district_id=a.district_id) "
                            + "join tblVillage_details e on(e.district_id=c.district_id and e.mandal_id=c.mandal_id and e.village_id=c.village_id and e.district_id=a.district_id and e.mandal_id=b.mandal_id)"
                            + " and c.district_id =? and c.mandal_id=? and c.village_id=? and c.pensionphase in('" + phaseName + "') and c.status='Active' "
                            + "and d.status='Active' and c.view_edit_mode='View'";
                }


            }

            
            pstmt = con.prepareStatement(sql);
            
            if (!district_id.equals("0") && mandal_id.equals("") && village_id.equals("")) {
            	
            	if (header.equals("26")){
            		pstmt.setString(1, district_id);
            		pstmt.setString(2, district_id);
            	}
            	else
            	{
            		pstmt.setString(1, district_id);
            	}
            
            }
            if (!district_id.equals("0") && !mandal_id.equals("") && village_id.equals("")) {
            	
            	if (header.equals("26")){
            		pstmt.setString(1, district_id);
                	pstmt.setString(2, mandal_id);	
                	pstmt.setString(3, district_id);
                	pstmt.setString(4, mandal_id);	
            	}
            	else
            	{
            		pstmt.setString(1, district_id);
                	pstmt.setString(2, mandal_id);		
            	}
            	
            }
            
            if (!district_id.equals("0") && !mandal_id.equals("") && !village_id.equals("")) {
            	
            	if (header.equals("26"))
            	{
            		pstmt.setString(1, district_id);
                	pstmt.setString(2, mandal_id);
                	pstmt.setString(3, village_id);
                	
                	pstmt.setString(4, district_id);
                	pstmt.setString(5, mandal_id);
                	pstmt.setString(6, village_id);
            	}
            	else
            	{
            		pstmt.setString(1, district_id);
                	pstmt.setString(2, mandal_id);
                	pstmt.setString(3, village_id);	
            	}
            	
            	
            	
            }

            rs = pstmt.executeQuery();
            while (rs.next()) {
                HashMap partBData = new HashMap();
                partBData.put("pensionId", rs.getString(1));
                partBData.put("name", rs.getString(2));
                partBData.put("fname", rs.getString(3));
                partBData.put("age", rs.getString(4));
                partBData.put("gender", rs.getString(5));
                partBData.put("phc", rs.getString(6));
                partBData.put("dis_per", rs.getString(7));
                partBData.put("rationcardno", rs.getString(8));
                partBData.put("pensionphase", rs.getString(9));
                partBData.put("districtName", rs.getString(10));
                partBData.put("mandalName", rs.getString(11));
                partBData.put("villageName", rs.getString(12));
                partBData.put("reasonforStatus", rs.getString(13));
                data.add(partBData);

            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "statusReportforPartBAssessed", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "statusReportforPartBAssessed");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "statusReportforPartBAssessed", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "statusReportforPartBAssessed");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }



        return data;
    }

    //for First Letter CapitalPurpose
    public String convertFirstLetterToUpperCase(String inputString) {
        if (inputString.equals("")) {
            return inputString;
        } else {
            StringBuffer inputStrBuffer = new StringBuffer(inputString);
            String firstLetterUpperCase = inputStrBuffer.substring(0, 1).toUpperCase();
            inputStrBuffer.replace(0, 1, firstLetterUpperCase);
            return inputStrBuffer.toString();
        }

    }
    //kavya

    public ArrayList statusreportforPartBModified(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String panchayat_id = null;
        String village_id = null;
        ArrayList partBList = null;

        district_id = assessedPWDDetailsDTO.getDistrict_id();
        mandal_id = assessedPWDDetailsDTO.getMandal_id();
        panchayat_id = assessedPWDDetailsDTO.getPanchayat_id();
        village_id = assessedPWDDetailsDTO.getVillage_id();

        String givenFromDate = assessedPWDDetailsDTO.getFromdate();
        String givenToDate = assessedPWDDetailsDTO.getTodate();

        if (!(assessedPWDDetailsDTO.getFinancialYearWise().equals("0"))) {

            String[] financialYear = assessedPWDDetailsDTO.getFinancialYearWise().split("-");
            int givenYear = Integer.parseInt(financialYear[0]);
            givenFromDate = "01/04/" + givenYear;
            givenToDate = getAssignedDate(givenYear + 1, ds, givenFromDate);

// Date today = new Date();
//            if ((givenYear + 1) == (today.getYear() + 1900)) {

//
//                // givenToDate = new SimpleDateFormat("dd/mm/yyyy").format(today);
//                givenToDate = today.getDate() + "/" + today.getMonth() + "/" + (today.getYear() + 1900);
//            } else {

//                givenFromDate = "01/04/" + givenYear;
//                givenToDate = "31/03/" + (givenYear + 1);
//
//            }
        }


        partBList = new ArrayList();
        String rachabandaDate = "";
        String pensionPhase = assessedPWDDetailsDTO.getPensionPhase();

        if (pensionPhase.equals("1")) {
            pensionPhase = "PhaseI";
        } else if (pensionPhase.equals("2")) {
            pensionPhase = "PhaseII";
        } else if (pensionPhase.equals("3")) {
            pensionPhase = "PhaseIII";
        } else if (pensionPhase.equals("4")) {
            pensionPhase = "PhaseIV";
        } else if (pensionPhase.equals("5")) {
            pensionPhase = "RachaBanda1";
            rachabandaDate = "05/07/2012";
        } else if (pensionPhase.equals("6")) {
            pensionPhase = "RachaBandaII";
            rachabandaDate = "07/31/2012";
        } else if (pensionPhase.equals("7")) {
            pensionPhase = "After RachaBandaI";
            rachabandaDate = "05/28/2013";
        } else if (pensionPhase.equals("8")) {
            pensionPhase = "After RachaBandaII";
            rachabandaDate = "08/12/2013";
        } else {
            pensionPhase = "ALL";
        }
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenFromDate);
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenToDate);
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();

            if (district_id.equals("0")) {

                if (pensionPhase.equals("RachaBanda1") || pensionPhase.equals("RachaBandaII") || pensionPhase.equals("After RachaBandaI") || pensionPhase.equals("After RachaBandaII")) {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_Rachabanda_Login(?,?,?,?,?,?,?)}");
                    cstmt.setString(1, "All");
                    cstmt.setString(2, "All");
                    cstmt.setString(3, "All");
                    cstmt.setString(4, "All");
                    cstmt.setString(5, rachabandaDate);
                    cstmt.setString(6, fromdate);
                    cstmt.setString(7, todate);
                } else if (pensionPhase.equals("ALL")) {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_ALLPHASES_Login(?,?)}");
                    cstmt.setString(1, fromdate);
                    cstmt.setString(2, todate);
                } else if (pensionPhase.equals("PhaseIII")) {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_PHASEIII_Login(?,?,?)}");
                    cstmt.setString(1, fromdate);
                    cstmt.setString(2, todate);
                    cstmt.setString(3, pensionPhase);
                } else {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_LOGIN(?,?,?)}");
                    cstmt.setString(1, fromdate);
                    cstmt.setString(2, todate);
                    cstmt.setString(3, pensionPhase);
                }

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    int undergone = rs.getInt("UNDERGOSADAREM");
                    int total = rs.getInt("TOTAL");
                    int directedRejacted = rs.getInt("DIRECTREJECTED");
                    int assessedandrejected = rs.getInt("ASSESSEDANDREJECTED");
                    int eligible = total + directedRejacted;

                    int totalRejected = directedRejacted + assessedandrejected;
                    int toBeAssesed = undergone - eligible;
                    int beforeAssest = eligible - totalRejected;
                    Map map = new HashMap();


                    map.put("districtid", rs.getString("DISTRICTID"));
                    map.put("district", rs.getString("DISTRICTNAME"));
                    if (pensionPhase.equals("PhaseIII")) {
                        map.put("ExistingPensioners", "0");
                    } else {
                        map.put("ExistingPensioners", rs.getString("EXISTINGPENSIONERS"));
                    }
                    map.put("underGoSadarem", undergone);
                    map.put("total", eligible);
                    map.put("beforeAssest", beforeAssest);
                    map.put("directrejected", directedRejacted);
                    map.put("assessedandrejected", assessedandrejected);
                    map.put("totalRejected", totalRejected);
                    map.put("toBeAssesed", toBeAssesed);
                    if (pensionPhase.equals("PhaseIII")) {
                        map.put("toBeAssesed", rs.getInt("Tobeassesed"));
                    }
                    partBList.add(map);

                }

            } else if (mandal_id.equals("0")) {

                if (pensionPhase.equals("RachaBanda1") || pensionPhase.equals("RachaBandaII") || pensionPhase.equals("After RachaBandaI") || pensionPhase.equals("After RachaBandaII")) {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_Rachabanda_Login(?,?,?,?,?,?,?)}");
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, "All");
                    cstmt.setString(3, "All");
                    cstmt.setString(4, "All");
                    cstmt.setString(5, rachabandaDate);
                    cstmt.setString(6, fromdate);
                    cstmt.setString(7, todate);
                } else if (pensionPhase.equals("ALL")) {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_ALLPHASES_MANDAL_Login(?,?,?)}");
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, fromdate);
                    cstmt.setString(3, todate);
                } else {
                    if (pensionPhase.equals("PhaseIII")) {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE__MANDALWISE_PHASEIII_Login(?,?,?,?)}");
                        //SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE__MANDALWISE_NEWV1_PHASEIII
                    } else {

                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_MANDALWISE_Login(?,?,?,?)}");
                    }
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, fromdate);
                    cstmt.setString(3, todate);
                    cstmt.setString(4, pensionPhase);

                }
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    Map map = new HashMap();

                    int undergone = rs.getInt("UNDERGOSADAREM");
                    int total = rs.getInt("TOTAL");
                    int directedRejacted = rs.getInt("DIRECTREJECTED");
                    int assessedandrejected = rs.getInt("ASSESSEDANDREJECTED");
                    int eligible = total + directedRejacted;

                    int totalRejected = directedRejacted + assessedandrejected;
                    int toBeAssesed = undergone - eligible;
                    int beforeAssest = eligible - totalRejected;
                    map.put("district_id", district_id);
                    map.put("mandal_id", rs.getString("MANDALID"));
                    map.put("mandal", rs.getString("MANDALNAME"));
                    if (pensionPhase.equals("PhaseIII")) {
                        map.put("ExistingPensioners", "0");
                    } else {
                        map.put("ExistingPensioners", rs.getString("EXISTINGPENSIONERS"));
                    }
                    map.put("underGoSadarem", undergone);
                    map.put("total", eligible);
                    map.put("beforeAssest", beforeAssest);
                    map.put("directrejected", directedRejacted);
                    map.put("assessedandrejected", assessedandrejected);
                    map.put("totalRejected", totalRejected);
                    map.put("toBeAssesed", toBeAssesed);
                    if (pensionPhase.equals("PhaseIII")) {
                        map.put("toBeAssesed", rs.getInt("Tobeassesed"));
                    }
                    partBList.add(map);
                }

            } else if (panchayat_id.equals("0")) {

                if (pensionPhase.equals("RachaBanda1") || pensionPhase.equals("RachaBandaII") || pensionPhase.equals("After RachaBandaI") || pensionPhase.equals("After RachaBandaII")) {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_Rachabanda_Login(?,?,?,?,?,?,?)}");
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, mandal_id);
                    cstmt.setString(3, "All");
                    cstmt.setString(4, "All");
                    cstmt.setString(5, rachabandaDate);
                    cstmt.setString(6, fromdate);
                    cstmt.setString(7, todate);
                } else if (pensionPhase.equals("ALL")) {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_ALLPHASES_panchayat_Login(?,?,?,?)}");
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, mandal_id);
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                } else {
                    if (pensionPhase.equals("PhaseIII")) {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_PANCHAYATWISE_PHASEIII_Login(?,?,?,?,?)}");
                    } else {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_PANCHAYATWISE_Login(?,?,?,?,?)}");
                    }
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, mandal_id);
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                    cstmt.setString(5, pensionPhase);
                }
                rs = cstmt.executeQuery();
                while (rs.next()) {

                    Map map = new HashMap();
                    int undergone = rs.getInt("UNDERGOSADAREM");
                    int total = rs.getInt("TOTAL");
                    int directedRejacted = rs.getInt("DIRECTREJECTED");
                    int assessedandrejected = rs.getInt("ASSESSEDANDREJECTED");
                    int eligible = total + directedRejacted;

                    int totalRejected = directedRejacted + assessedandrejected;
                    int toBeAssesed = undergone - eligible;
                    int beforeAssest = eligible - totalRejected;

                    map.put("district_id", district_id);
                    map.put("mandal_id", mandal_id);
                    map.put("panchayat_id", rs.getString("PANCHAYAT_ID"));
                    map.put("panchayat_name", rs.getString("PANCHAYAT_NAME"));
                    if (pensionPhase.equals("PhaseIII")) {
                        map.put("ExistingPensioners", "0");
                    } else {
                        map.put("ExistingPensioners", rs.getString("EXISTINGPENSIONERS"));
                    }
                    map.put("underGoSadarem", undergone);
                    map.put("total", eligible);
                    map.put("beforeAssest", beforeAssest);
                    map.put("directrejected", directedRejacted);
                    map.put("assessedandrejected", assessedandrejected);
                    map.put("totalRejected", totalRejected);
                    map.put("toBeAssesed", toBeAssesed);
                    if (pensionPhase.equals("PhaseIII")) {
                        map.put("toBeAssesed", rs.getInt("Tobeassesed"));
                    }
                    partBList.add(map);
                }
            } else if (village_id.equals("0")) {


                if (pensionPhase.equals("RachaBanda1") || pensionPhase.equals("RachaBandaII") || pensionPhase.equals("After RachaBandaI") || pensionPhase.equals("After RachaBandaII")) {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_Rachabanda_Login(?,?,?,?,?,?,?)}");
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, mandal_id);
                    cstmt.setString(3, panchayat_id);
                    cstmt.setString(4, "All");
                    cstmt.setString(5, rachabandaDate);
                    cstmt.setString(6, fromdate);
                    cstmt.setString(7, todate);
                } else if (pensionPhase.equals("ALL")) {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_ALLPHASES_VILLAGE_Login(?,?,?,?,?)}");
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, mandal_id);
                    cstmt.setString(3, panchayat_id);
                    cstmt.setString(4, fromdate);
                    cstmt.setString(5, todate);
                } else {
                    if (pensionPhase.equals("PhaseIII")) {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_VILLAGEWISE_PHASEIII_Login(?,?,?,?,?,?)}");
                    } else {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_VILLAGEWISE_Login(?,?,?,?,?,?)}");
                    }
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, mandal_id);
                    cstmt.setString(3, panchayat_id);
                    cstmt.setString(4, fromdate);
                    cstmt.setString(5, todate);
                    cstmt.setString(6, pensionPhase);
                }
                rs = cstmt.executeQuery();
                while (rs.next()) {

                    Map map = new HashMap();

                    int undergone = rs.getInt("UNDERGOSADAREM");
                    int total = rs.getInt("TOTAL");
                    int directedRejacted = rs.getInt("DIRECTREJECTED");
                    int assessedandrejected = rs.getInt("ASSESSEDANDREJECTED");
                    int eligible = total + directedRejacted;

                    int totalRejected = directedRejacted + assessedandrejected;
                    int toBeAssesed = undergone - eligible;
                    int beforeAssest = eligible - totalRejected;

                    map.put("district_id", district_id);
                    map.put("mandal_id", mandal_id);
                    map.put("panchayat_id", panchayat_id);
                    map.put("village_id", rs.getString("VILLAGEID"));
                    map.put("townVillage", rs.getString("VILLAGENAME"));
                    if (pensionPhase.equals("PhaseIII")) {
                        map.put("ExistingPensioners", "0");
                    } else {
                        map.put("ExistingPensioners", rs.getString("EXISTINGPENSIONERS"));
                    }
                    map.put("underGoSadarem", undergone);
                    map.put("total", eligible);
                    map.put("beforeAssest", beforeAssest);
                    map.put("directrejected", directedRejacted);
                    map.put("assessedandrejected", assessedandrejected);
                    map.put("totalRejected", totalRejected);
                    map.put("toBeAssesed", toBeAssesed);
                    if (pensionPhase.equals("PhaseIII")) {
                        map.put("toBeAssesed", rs.getInt("Tobeassesed"));
                    }
                    partBList.add(map);
                }
            } else if (!village_id.equals("0")) {


                if (pensionPhase.equals("RachaBanda1") || pensionPhase.equals("RachaBandaII") || pensionPhase.equals("After RachaBandaI") || pensionPhase.equals("After RachaBandaII")) {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_Rachabanda_Login(?,?,?,?,?,?,?)}");
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, mandal_id);
                    cstmt.setString(3, panchayat_id);
                    cstmt.setString(4, village_id);
                    cstmt.setString(5, rachabandaDate);
                    cstmt.setString(6, fromdate);
                    cstmt.setString(7, todate);
                } else if (pensionPhase.equals("ALL")) {
                    cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_ALLPHASE_HABITAIONWISE_Login(?,?,?,?,?,?)}");
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, mandal_id);
                    cstmt.setString(3, panchayat_id);
                    cstmt.setString(4, village_id);
                    cstmt.setString(5, fromdate);
                    cstmt.setString(6, todate);
                } else {
                    if (pensionPhase.equals("PhaseIII")) {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_HABITATIONWISE_PHASEIII_Login(?,?,?,?,?,?,?)}");
                    } else {
                        cstmt = con.prepareCall("{Call SP_PARTA_PARTB_COUNTDETAILSALLDISTRICTS_SERP_PHASEWISE_HABITATIONWISE_Login(?,?,?,?,?,?,?)}");
                    }
                    cstmt.setString(1, district_id);
                    cstmt.setString(2, mandal_id);
                    cstmt.setString(3, panchayat_id);
                    cstmt.setString(4, village_id);
                    cstmt.setString(5, fromdate);
                    cstmt.setString(6, todate);
                    cstmt.setString(7, pensionPhase);
                }
                rs = cstmt.executeQuery();
                while (rs.next()) {

                    Map map = new HashMap();

                    int undergone = rs.getInt("UNDERGOSADAREM");
                    int total = rs.getInt("TOTAL");
                    int directedRejacted = rs.getInt("DIRECTREJECTED");
                    int assessedandrejected = rs.getInt("ASSESSEDANDREJECTED");
                    int eligible = total + directedRejacted;
                    int totalRejected = directedRejacted + assessedandrejected;
                    int toBeAssesed = undergone - eligible;
                    int beforeAssest = eligible - totalRejected;

                    map.put("district_id", district_id);
                    map.put("mandal_id", mandal_id);
                    map.put("panchayat_id", panchayat_id);
                    map.put("village_id", village_id);
                    map.put("habitation_id", rs.getString("habtationid"));
                    map.put("habitation_name", rs.getString("habitationname"));
                    if (pensionPhase.equals("PhaseIII")) {
                        map.put("ExistingPensioners", "0");
                    } else {
                        map.put("ExistingPensioners", rs.getString("EXISTINGPENSIONERS"));
                    }
                    map.put("underGoSadarem", undergone);
                    map.put("total", eligible);
                    map.put("beforeAssest", beforeAssest);
                    map.put("directrejected", directedRejacted);
                    map.put("assessedandrejected", assessedandrejected);
                    map.put("totalRejected", totalRejected);
                    map.put("toBeAssesed", toBeAssesed);
                    if (pensionPhase.equals("PhaseIII")) {
                        map.put("toBeAssesed", rs.getInt("Tobeassesed"));
                    }
                    partBList.add(map);
                }
            }


        } catch (ParseException parseEx) {
            int exResult = ExceptionDAO.saveException(ds, parseEx.toString(), "statusreportforPartBModified", "DailyDisabilityAndPercentageDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, parseEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "statusreportforPartBModified");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "statusreportforPartBModified", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "statusreportforPartBModified");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "statusreportforPartBModified", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "statusreportforPartBModified");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeConnection(con);
        }

        return partBList;
    }

    public String getAssignedDate(int year, DataSource ds, String date) throws SADAREMDBException, SQLException {
        Connection con = null;
       // Statement stmt = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        date = "31/03/" + year; 
        try {
            con = DBConnection.getConnection();
          //  stmt = con.createStatement();
            String sql = "select "
                    + "datepart(day, GETDATE()) as day, "
                    + "datepart(month, GETDATE()) as month, "
                    + "datepart(year, GETDATE()) as year "
                    + "where datepart(year, GETDATE()) =?";
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, year+"");
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                date = rs.getString("day") + "/" + rs.getString("month") + "/" + rs.getString("year");
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssignedDate", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getAssignedDate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssignedDate", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getAssignedDate");
        } finally {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);

        }

        return date;
    }

    public ArrayList setDistrictList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList distList = new ArrayList();
        Map m = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            String query = "select District_ID ,District_Name from tblDistrict_Details order by District_Name";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                m = new HashMap();
                m.put("district_id", rs.getString("District_ID"));
                m.put("district_name", rs.getString("District_Name"));

                distList.add(m);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "setDistrictList", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "setDistrictList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "setDistrictList", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "setDistrictList");
        } finally {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeConnection(con);

        }
        return distList;
    }

    public ArrayList setMandalList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO) throws SADAREMDBException, SQLException, SADAREMException {
        Connection con = null;
       // Statement stmt = null;
        
        PreparedStatement pstmt =null ;
        ResultSet rs = null;
        ArrayList mandalList = new ArrayList();
        Map m = null;
        try {
            con = DBConnection.getConnection();
         //   stmt = con.createStatement();
            String query = "select Mandal_ID ,Mandal_Name from tblMandal_Details where District_ID=? order by Mandal_Name";
            
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, assessedPWDDetailsDTO.getDistrict_id());
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                m = new HashMap();
                m.put("mandal_id", rs.getString("Mandal_ID"));
                m.put("mandal_name", rs.getString("Mandal_Name"));

                mandalList.add(m);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "setMandalList", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "setMandalList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "setMandalList", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "setMandalList");
        } finally {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);

        }
        return mandalList;
    }

    public ArrayList setPanchayatList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO) throws SADAREMDBException, SQLException {
        Connection con = null;
       // Statement stmt = null;
        PreparedStatement pstmt = null;
        
        ResultSet rs = null;
        ArrayList pancList = new ArrayList();
        Map m = null;
        try {
            con = DBConnection.getConnection();
         //   stmt = con.createStatement();
            String query = "select Panchayat_ID ,Panchayat_Name from tblPanchayat_Details where District_ID=? and Mandal_ID=? order by Panchayat_Name";
            
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, assessedPWDDetailsDTO.getDistrict_id());
            pstmt.setString(2,assessedPWDDetailsDTO.getMandal_id());
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                m = new HashMap();
                m.put("panchayat_id", rs.getString("Panchayat_ID"));
                m.put("panchayat_name", rs.getString("Panchayat_Name"));

                pancList.add(m);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "setPanchayatList", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "setPanchayatList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "setPanchayatList", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "setPanchayatList");
        } finally {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);

        }
        return pancList;
    }

    public ArrayList setVillageList(DataSource ds, AssessedPWDDetailsDTO assessedPWDDetailsDTO) throws SADAREMDBException, SQLException {
        Connection con = null;
      //  Statement stmt = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        ArrayList villageList = new ArrayList();
        Map m = null;
        try {
            con = DBConnection.getConnection();
          //  stmt = con.createStatement();
            String query = "select distinct b.village_id,b.village_name from dbo.tblHabitation_Details a,dbo.tblVillage_Details b where "
                    + "a.district_id=b.district_id and "
                    + "a.mandal_id=b.mandal_id and "
                    + "a.village_id=b.village_id "
                    + "and a.district_id=? and a.mandal_id=? and a.panchayat_id=?";
            
            pstmt=con.prepareStatement(query);
            pstmt.setString(1, assessedPWDDetailsDTO.getDistrict_id());
            pstmt.setString(2, assessedPWDDetailsDTO.getMandal_id());
            pstmt.setString(3,  assessedPWDDetailsDTO.getPanchayat_id());
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                m = new HashMap();
                m.put("village_id", rs.getString("village_id"));
                m.put("village_name", rs.getString("village_name"));

                villageList.add(m);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "setVillageList", "DailyDisabilityAndPercentageDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "setVillageList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "setVillageList", "DailyDisabilityAndPercentageDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "setVillageList");
        } finally {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);

        }
        return villageList;
    }
    // end of kavya

    public ArrayList getAllDataByMandal(DataSource ds, String districtId, String mandalId) throws SADAREMDBException, SQLException {
        Connection con = null;
       // Statement stmt = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        ArrayList allData = new ArrayList();
        Map m = null;
        try {
            con = DBConnection.getConnection();
            //stmt = con.createStatement();
            String query = "";
            query = "select coalesce(nullif(PENSIONID,''),'-') ,coalesce(nullif(SADAREMCODE,''),'-'),coalesce(nullif(PERSONNAME,''),'-'),coalesce(nullif(GENDER,''),'-'),coalesce(nullif(EDUCATION,''),'-'),coalesce(nullif(RELATIONNAME,''),'-') "
                    + ",coalesce(nullif(RATIONCARD_NUMBER,''),'-'),coalesce(nullif(TYPEOFDISABILITY,''),'-'),coalesce(nullif(TOTALDISABILITY,''),'-')    "
                    + ",coalesce(nullif(REASONFORPERSONSTATUS,''),'-'),coalesce(nullif(ASSESSEMENTSTATUS,''),'-'),coalesce(nullif(district_name,''),'-'),coalesce(nullif(mandal_name,''),'-'),coalesce(nullif(village_name,''),'-') FROM dbo.percentage2039_totaldata a where substring(a.sadaremcode,1,2)=?";
            if (mandalId != null && !mandalId.equals("0")) {

                query = query.concat(" and substring(a.sadaremcode,6,2)=?");
            }

             query = query.concat(" order by mandal_name");   
             
             
             pstmt = con.prepareStatement(query.toString());
           
             pstmt.setString(1, districtId);
             if (mandalId != null && !mandalId.equals("0")) {
            	  pstmt.setString(2, mandalId);
             }
             
             
             
             
            rs = pstmt.executeQuery();
            while (rs.next()) {
                m = new HashMap();
                m.put("PENSIONID", rs.getString(1));
                m.put("SADAREMCODE", rs.getString(2));
                m.put("PERSONNAME", rs.getString(3));
                m.put("GENDER", rs.getString(4));
                m.put("EDUCATION", rs.getString(5));
                m.put("RELATIONNAME", rs.getString(6));
                m.put("RATIONCARD_NUMBER", rs.getString(7));
                m.put("TYPEOFDISABILITY", rs.getString(8));
                m.put("TOTALDISABILITY", rs.getString(9));
                m.put("REASONFORPERSONSTATUS", rs.getString(10));
                m.put("ASSESSEMENTSTATUS", rs.getString(11));
                m.put("district_name", rs.getString(12));
                m.put("mandal_name", rs.getString(13));
                m.put("village_name", rs.getString(14));

                allData.add(m);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAllDataByMandal", "DailyDisabilityAndPercentageDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getAllDataByMandal");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAllDataByMandal", "DailyDisabilityAndPercentageDAO", "Code");

            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DailyDisabilityAndPercentageDAO", "getAllDataByMandal");
        } finally {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);

        }
        return allData;
    }
}
