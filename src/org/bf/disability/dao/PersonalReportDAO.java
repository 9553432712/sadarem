
/*
 * ReportDAO.java
 *
 * Created on July 15, 2008, 3:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.CommonReportDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for getting personal details for reports from database
 * @author sunima
 * @version 1.0
 */
public class PersonalReportDAO {

    /**
     * 
     * @param datasource 
     * @param surgeryreportdto 
     * @param Surgerytype 
     * @throws java.lang.Exception 
     * @return list
     */
    public ArrayList getPersonalReportDetails(DataSource ds, CommonReportDTO surgeryreportdto, String Surgerytype) throws SADAREMDBException, SQLException {
        ArrayList ar = new ArrayList();



        Connection con = null;
       // Statement stmt = null;
        PreparedStatement pstmt =null;
        CallableStatement cstmt = null;
        //Statement stmt2=null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String annual = null;
        String subdisability = null;
        String subsubdisability = null;

        int a[] = new int[20];
        int b[] = new int[20];
        if (Surgerytype.equals("Physiotherapy") || Surgerytype.equals("SpeechTherapy") || Surgerytype.equals("CouncellingGuidance")) {


            try {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Districtwise(?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getTablename());
                cstmt.setString(3, surgeryreportdto.getFirstcolumn());

                cstmt.setString(4, surgeryreportdto.getFieldvalue());
                cstmt.setString(5, surgeryreportdto.getSecondcolumn());
                cstmt.setString(6, surgeryreportdto.getSecondfieldvalue());
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));


                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }

                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));
                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));
                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));
                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));
                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));



                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));

                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]); 
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*   if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */


                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);
                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } finally {
                DBConnection.closeConnection(con);

                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);


            }



        } else if (Surgerytype.equals("orthosis")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Orthosis_Reports_Districtwise(?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));


                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }



                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));
                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));
                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));
                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));
                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));

                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        //stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt =con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }
                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                       // stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*   if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);


            }
        } else if (Surgerytype.equals("prosthesis")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Prosthesis_Reports_Districtwise(?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());



                rs = cstmt.executeQuery();
                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));

                    surgeryreportdto1.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }





                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));
                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));
                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));
                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));
                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=? ");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }
                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else if (Surgerytype.equals("HearingAidType")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_HearingAidType_Reports_Districtwise(?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }


                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));
                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));
                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));
                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));
                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));

                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }
                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                       // stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }
                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*   if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */





                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);
                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else if (Surgerytype.equals("WalkingFrame")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_WalkingFrame_Reports_Districtwise(?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());


                rs = cstmt.executeQuery();
                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));

                    surgeryreportdto1.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }

                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));

                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else {

            try {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Districtwise(?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());

                cstmt.setString(2, surgeryreportdto.getTablename());
                cstmt.setString(3, Surgerytype);
                cstmt.setString(4, surgeryreportdto.getFieldvalue());
              //  stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }


                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                   //     stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }
                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*   if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */


                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }


                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        }

        return ar;
    }

    /**
     * 
     * @param inputString 
     * @return int array
     */
    public int[] convertToIntArray(String inputString) {

        if (inputString != null) {
            String[] stringArray = inputString.split(",");
            int[] intArray = new int[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                intArray[i] = Integer.valueOf(stringArray[i]).intValue();

            }
            return intArray;
        } else {
            return null;
        }

    }

    /**
     * 
     * @param datasource 
     * @param surgeryreportdto 
     * @param Surgerytype 
     * @throws java.lang.Exception 
     * @return list
     */
    public ArrayList getPersonalReportformandalDetails(DataSource ds, CommonReportDTO surgeryreportdto, String Surgerytype) throws SADAREMDBException, SQLException {
        ArrayList ar1 = new ArrayList();



        Connection con = null;
        //Statement stmt = null;
        PreparedStatement pstmt = null ;
        CallableStatement cstmt = null;
        //Statement stmt2=null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String annual = null;
        String subdisability = null;
        String subsubdisability = null;

        int a[] = new int[20];
        int b[] = new int[20];


        if (Surgerytype.equals("Physiotherapy") || Surgerytype.equals("SpeechTherapy") || Surgerytype.equals("CouncellingGuidance")) {


            try {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Mandalwise(?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getTablename());
                cstmt.setString(4, surgeryreportdto.getFirstcolumn());

                cstmt.setString(5, surgeryreportdto.getFieldvalue());
                cstmt.setString(6, surgeryreportdto.getSecondcolumn());
                cstmt.setString(7, surgeryreportdto.getSecondfieldvalue());
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }


                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                    //    stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) { 
                        	pstmt = con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }
                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }


                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar1.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportformandalDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportformandalDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportformandalDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportformandalDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }



        } else if (Surgerytype.equals("orthosis")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Orthosis_Reports_Mandalwise(?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());

                cstmt.setString(2, surgeryreportdto.getMandalid());


                rs = cstmt.executeQuery();
                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));



                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }


                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*   if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */
                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar1.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportformandalDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportformandalDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportformandalDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportformandalDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else if (Surgerytype.equals("prosthesis")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Prosthesis_Reports_Mandalwise(?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());

                cstmt.setString(2, surgeryreportdto.getMandalid());
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));


                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }

                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));


                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));

                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        //stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }


                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar1.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportformandalDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportformandalDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportformandalDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportformandalDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else if (Surgerytype.equals("HearingAidType")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_HearingAidType_Reports_Mandalwise(?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());


                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));



                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }



                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));


                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));

                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                       // stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*   if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar1.add(surgeryreportdto1);

                }
            } catch (SQLException sqle) {
            } catch (Exception e) {
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else if (Surgerytype.equals("WalkingFrame")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_WalkingFrame_Reports_Mandalwise(?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());


                rs = cstmt.executeQuery();
                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));
                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }


                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));

                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar1.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportformandalDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportformandalDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportformandalDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportformandalDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else {

            try {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Mandalwise(?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getTablename());
                cstmt.setString(4, Surgerytype);
                cstmt.setString(5, surgeryreportdto.getFieldvalue());
              //  stmt = con.createStatement();
                rs = cstmt.executeQuery();


                while (rs.next()) {
                    CommonReportDTO surgeryreportdto2 = new CommonReportDTO();
                    surgeryreportdto2.setDistrictid(surgeryreportdto.getDistrictid());
                    surgeryreportdto2.setName(rs.getString("PersonName"));
                    surgeryreportdto2.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto2.setGender("Male");
                    } else {
                        surgeryreportdto2.setGender("Female");
                    }


                    surgeryreportdto2.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto2.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto2.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto2.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto2.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto2.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < a.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto2.setSubtype(subdisability);
                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto2.setSubsubtype(subsubdisability);


                    surgeryreportdto2.setTotalpercent(rs.getString("TotalDisability"));

                    /*   if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto2.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto2.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto2.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto2.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto2.setCaste("ST");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto2.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto2.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto2.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto2.setOccupation("Un-Employed");
                    }

                    ar1.add(surgeryreportdto2);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportformandalDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportformandalDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportformandalDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportformandalDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        }

        return ar1;
    }

    /**
     * 
     * @param datasource 
     * @param surgeryreportdto 
     * @param Surgerytype 
     * @throws java.lang.Exception 
     * @return list
     */
    public ArrayList getPersonalReportforvillageDetails(DataSource ds, CommonReportDTO surgeryreportdto, String Surgerytype) throws SADAREMDBException, SQLException {
        ArrayList ar = new ArrayList();


        Connection con = null;
       // Statement stmt = null;
        PreparedStatement pstmt= null;
        CallableStatement cstmt = null;
        // Statement stmt2=null;
        ResultSet rs = null;
        String annual = null;
        String subdisability = null;
        String subsubdisability = null;
        ResultSet rs2 = null;
        int a[] = new int[20];
        int b[] = new int[20];


        if (Surgerytype.equals("Physiotherapy") || Surgerytype.equals("SpeechTherapy") || Surgerytype.equals("CouncellingGuidance")) {


            try {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Villagewise(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());

                cstmt.setString(2, surgeryreportdto.getMandalid());

                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getTablename());
                cstmt.setString(5, surgeryreportdto.getFirstcolumn());

                cstmt.setString(6, surgeryreportdto.getFieldvalue());
                cstmt.setString(7, surgeryreportdto.getSecondcolumn());
                cstmt.setString(8, surgeryreportdto.getSecondfieldvalue());
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setDistrictid(surgeryreportdto.getDistrictid());
                    surgeryreportdto1.setMandalid(surgeryreportdto.getMandalid());
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }

                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));

                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }


                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforvillageDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforvillageDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforvillageDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforvillageDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }



        } else if (Surgerytype.equals("orthosis")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Orthosis_Reports_Villagewise(?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));

                    surgeryreportdto1.setAge(rs.getString("Age_Years"));
                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }


                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforvillageDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforvillageDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforvillageDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforvillageDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else if (Surgerytype.equals("prosthesis")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Prosthesis_Reports_Villagewise(?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }




                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                       // stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */


                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforvillageDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforvillageDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforvillageDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforvillageDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else if (Surgerytype.equals("HearingAidType")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_HearingAidType_Reports_Villagewise(?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                rs = cstmt.executeQuery();


                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }



                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));


                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                       // stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforvillageDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforvillageDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforvillageDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforvillageDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else if (Surgerytype.equals("WalkingFrame")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_WalkingFrame_Reports_Villagewise(?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());


                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));

                    surgeryreportdto1.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }


                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                       // stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                       // stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*   if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }


                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqle) {
            } catch (Exception e) {
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else {


            try {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Villagewise(?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getTablename());
                cstmt.setString(5, Surgerytype);
                cstmt.setString(6, surgeryreportdto.getFieldvalue());
            
                
            //    stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto3 = new CommonReportDTO();
                    surgeryreportdto3.setDistrictid(surgeryreportdto.getDistrictid());

                    surgeryreportdto3.setMandalid(surgeryreportdto.getMandalid());
                    surgeryreportdto3.setName(rs.getString("PersonName"));
                    surgeryreportdto3.setAge(rs.getString("Age_Years"));


                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto3.setGender("Male");
                    } else {
                        surgeryreportdto3.setGender("Female");
                    }


                    surgeryreportdto3.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto3.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto3.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto3.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto3.setHabitationname(rs.getString("Habitation_Name"));

                    surgeryreportdto3.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto3.setSubtype(subdisability);
                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < b.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto3.setSubsubtype(subsubdisability);


                    surgeryreportdto3.setTotalpercent(rs.getString("TotalDisability"));

                    /*   if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto3.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto3.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto3.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto3.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto3.setCaste("ST");
                    }


                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto3.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto3.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto3.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto3.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto3);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforvillageDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforvillageDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforvillageDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforvillageDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        }
        return ar;
    }

    /**
     * 
     * @param datasource 
     * @param surgeryreportdto 
     * @param Surgerytype 
     * @throws java.lang.Exception 
     * @return list
     */
    public ArrayList getPersonalReportforhabitationDetails(DataSource ds, CommonReportDTO surgeryreportdto, String Surgerytype) throws SADAREMDBException, SQLException {
        ArrayList ar = new ArrayList();


        Connection con = null;
       // Statement stmt = null;
        PreparedStatement pstmt= null;
        CallableStatement cstmt = null;
        //Statement stmt2=null;
        ResultSet rs = null;
        String annual = null;
        String subdisability = null;
        String subsubdisability = null;
        ResultSet rs2 = null;
        int a[] = new int[20];
        int b[] = new int[20];


        if (Surgerytype.equals("Physiotherapy") || Surgerytype.equals("SpeechTherapy") || Surgerytype.equals("CouncellingGuidance")) {


            try {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Habitationwise(?,?,?,?,?,?,?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());

                cstmt.setString(2, surgeryreportdto.getMandalid());

                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                cstmt.setString(5, surgeryreportdto.getTablename());
                cstmt.setString(6, surgeryreportdto.getFirstcolumn());

                cstmt.setString(7, surgeryreportdto.getFieldvalue());
                cstmt.setString(8, surgeryreportdto.getSecondcolumn());
                cstmt.setString(9, surgeryreportdto.getSecondfieldvalue());
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto2 = new CommonReportDTO();
                    surgeryreportdto2.setDistrictid(surgeryreportdto.getDistrictid());
                    surgeryreportdto2.setMandalid(surgeryreportdto.getMandalid());
                    surgeryreportdto2.setVillageid(surgeryreportdto.getVillageid());
                    surgeryreportdto2.setName(rs.getString("PersonName"));

                    surgeryreportdto2.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto2.setGender("Male");
                    } else {
                        surgeryreportdto2.setGender("Female");
                    }



                    surgeryreportdto2.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto2.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto2.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto2.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto2.setHabitationname(rs.getString("Habitation_Name"));


                    surgeryreportdto2.setTypeofdisability(rs.getString("Disability_Name"));

                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                       // stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto2.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto2.setSubsubtype(subsubdisability);


                    surgeryreportdto2.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto2.setAnnualincome(annual); */





                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto2.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto2.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto2.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto2.setCaste("ST");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto2.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto2.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto2.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto2.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto2);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }



        } else if (Surgerytype.equals("orthosis")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Orthosis_Reports_Habitationwise(?,?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));


                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }


                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));


                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */


                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else if (Surgerytype.equals("prosthesis")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Prosthesis_Reports_Habitationwise(?,?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                rs = cstmt.executeQuery();

                while (rs.next()) {

                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }




                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));



                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        // stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else if (Surgerytype.equals("HearingAidType")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_HearingAidType_Reports_Habitationwise(?,?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                rs = cstmt.executeQuery();


                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));



                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }



                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));


                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                      //  stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*    if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else if (Surgerytype.equals("WalkingFrame")) {
            try {
                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_WalkingFrame_Reports_Habitationwise(?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setName(rs.getString("PersonName"));
                    surgeryreportdto1.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto1.setGender("Male");
                    } else {
                        surgeryreportdto1.setGender("Female");
                    }



                    surgeryreportdto1.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto1.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto1.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto1.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto1.setHabitationname(rs.getString("Habitation_Name"));


                    surgeryreportdto1.setTypeofdisability(rs.getString("Disability_Name"));

                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                       // stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubtype(subdisability);


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < b.length - 1; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto1.setSubsubtype(subsubdisability);


                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    /*   if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto1.setAnnualincome(annual); */

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("ST");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        } else {


            try {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Habitationwise(?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());

                cstmt.setString(2, surgeryreportdto.getMandalid());

                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                cstmt.setString(5, surgeryreportdto.getTablename());
                cstmt.setString(6, Surgerytype);
                cstmt.setString(7, surgeryreportdto.getFieldvalue());

            //    stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto3 = new CommonReportDTO();
                    surgeryreportdto3.setDistrictid(surgeryreportdto.getDistrictid());
                    surgeryreportdto3.setMandalid(surgeryreportdto.getMandalid());
                    surgeryreportdto3.setMandalid(surgeryreportdto.getVillageid());
                    surgeryreportdto3.setName(rs.getString("PersonName"));
                    surgeryreportdto3.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        surgeryreportdto3.setGender("Male");
                    } else {
                        surgeryreportdto3.setGender("Female");
                    }


                    surgeryreportdto3.setHouseno(rs.getString("House_Number"));

                    surgeryreportdto3.setDistrictname(rs.getString("District_Name"));

                    surgeryreportdto3.setMandalname(rs.getString("Mandal_Name"));

                    surgeryreportdto3.setVillagename(rs.getString("Village_Name"));

                    surgeryreportdto3.setHabitationname(rs.getString("Habitation_Name"));



                    surgeryreportdto3.setTypeofdisability(rs.getString("Disability_Name"));


                    if (rs.getString("Sub_Disability_ID") != null) {

                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                     //   stmt = con.createStatement();
                        for (int i = 0; i < a.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto3.setSubtype(subdisability);
                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                       // stmt = con.createStatement();
                        for (int i = 0; i < b.length; i++) {
                        	pstmt=con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i]);
                        	
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                    }
                    surgeryreportdto3.setSubsubtype(subsubdisability);


                    surgeryreportdto3.setTotalpercent(rs.getString("TotalDisability"));

                    /*  if(rs.getString("Annual_Income").equalsIgnoreCase("0"))
                    annual="NA";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("1"))
                    annual="0 -24";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("2"))
                    annual="24-50";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("3"))
                    annual="51-75";
                    if(rs.getString("Annual_Income").equalsIgnoreCase("4"))
                    annual="Above 75";
                    
                    
                    surgeryreportdto3.setAnnualincome(annual); */


                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto3.setCaste("General");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto3.setCaste("BC or OBC");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto3.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto3.setCaste("ST");
                    }


                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto3.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto3.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto3.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto3.setOccupation("Un-Employed");
                    }

                    ar.add(surgeryreportdto3);

                }
            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } catch (Exception sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalReportforhabitationDetails", "PersonalReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PersonalReportDAO", "getPersonalReportforhabitationDetails");
            } finally {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeResultSet(rs2);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeStatement(cstmt);

            }
        }
        return ar;
    }
}




