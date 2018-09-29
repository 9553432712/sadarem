
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.CommonReportDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for fetching information for personalreprots form database
 * @author sunima
 * @version 1.0
 */
public class CommonPersonalReportDAO {

    /**
     * this method will fetch districtwise personalreport detail from database
     * @param datasource
     * @param surgeryreportdto
     * @param Surgerytype
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getPersonalReportDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String Surgerytype, int start, int endrange) throws SADAREMDBException, SQLException {

        ArrayList ar = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;
        String subdisability = null;
        ResultSet rs = null;
        ResultSet rs2 = null;


        String subsubdisability = null;
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            int a[] = new int[20];
            int b[] = new int[20];
            if (Surgerytype.equals("Physiotherapy") || Surgerytype.equals("SpeechTherapy") || Surgerytype.equals("CouncellingGuidance")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Districtwise(?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getTablename());
                cstmt.setString(3, surgeryreportdto.getFirstcolumn());
                cstmt.setString(4, surgeryreportdto.getFieldvalue());
                cstmt.setString(5, surgeryreportdto.getSecondcolumn());
                cstmt.setString(6, surgeryreportdto.getSecondfieldvalue());
                cstmt.setString(7, fromdate);
                cstmt.setString(8, todate);
                cstmt.setInt(9, start);
                cstmt.setInt(10, endrange);


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
                        

                        subdisability = null;

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
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {
                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }
                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }
                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);
                }

            } else if (Surgerytype.equals("orthosis")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Orthosis_Reports_Districtwise(?,?,?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                cstmt.setInt(4, start);
                cstmt.setInt(5, endrange);
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
                        
                        subdisability = null;
                        for (int i = 0; i < a.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                            pstmt.setInt(1,  a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }
                            }
                        }
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1,  b[i] );
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } else if (Surgerytype.equals("prosthesis")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Prosthesis_Reports_Districtwise(?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                cstmt.setInt(4, start);
                cstmt.setInt(5, endrange);

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
                        

                        subdisability = null;


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
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {
                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } else if (Surgerytype.equals("HearingAidType")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_HearingAidType_Reports_Districtwise(?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                cstmt.setInt(4, start);
                cstmt.setInt(5, endrange);
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
                        


                        subdisability = null;


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
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {
                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);
                }
            } else if (Surgerytype.equals("WalkingFrame")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_WalkingFrame_Reports_Districtwise(?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                cstmt.setInt(4, start);
                cstmt.setInt(5, endrange);


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
                        

                        subdisability = null;


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
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {
                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                        	pstmt= con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } else {

                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Districtwise(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getTablename());
                cstmt.setString(3, Surgerytype);
                cstmt.setString(4, surgeryreportdto.getFieldvalue());
                cstmt.setString(5, fromdate);
                cstmt.setString(6, todate);
                cstmt.setInt(7, start);
                cstmt.setInt(8, endrange);

                
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
                        

                        subdisability = null;

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
                        surgeryreportdto1.setSubtype(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));


                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            }
        } catch (ParseException parseException) {
            
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, parseException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportDetails");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportDetails", "CommonPersonalReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportDetails", "CommonPersonalReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportDetails");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeResultSet(rs2);
           DBConnection.closeStatement(pstmt);
           DBConnection.closeStatement(cstmt);

           DBConnection.closeConnection(con);
        }
        return ar;
    }

    /**
     *
     * @param inputString
     * @return int
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
     * this mehtod is for fetching mandalwise personalreport list from database
     * @param datasource
     * @param surgeryreportdto
     * @param Surgerytype
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getPersonalReportformandalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String Surgerytype, int start, int endrange) throws SADAREMDBException, SQLException {
        ArrayList ar1 = new ArrayList();

        Connection con = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String subdisability = null;
        String subsubdisability = null;
        ResultSet rs2 = null;
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);


            int a[] = new int[20];
            int b[] = new int[20];


            if (Surgerytype.equals("Physiotherapy") || Surgerytype.equals("SpeechTherapy") || Surgerytype.equals("CouncellingGuidance")) {



                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Mandalwise(?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getTablename());
                cstmt.setString(4, surgeryreportdto.getFirstcolumn());

                cstmt.setString(5, surgeryreportdto.getFieldvalue());
                cstmt.setString(6, surgeryreportdto.getSecondcolumn());
                cstmt.setString(7, surgeryreportdto.getSecondfieldvalue());
                cstmt.setString(8, fromdate);
                cstmt.setString(9, todate);
                cstmt.setInt(10, start);
                cstmt.setInt(11, endrange);

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
                        

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i] );
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }
                            }
                        }
                        surgeryreportdto1.setSubtype(subdisability);
                    }


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }



                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));


                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar1.add(surgeryreportdto1);
                }

            } else if (Surgerytype.equals("orthosis")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Orthosis_Reports_Mandalwise(?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                cstmt.setInt(5, start);
                cstmt.setInt(6, endrange);

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
                        

                        subdisability = null;

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
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));


                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar1.add(surgeryreportdto1);
                }

            } else if (Surgerytype.equals("prosthesis")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Prosthesis_Reports_Mandalwise(?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                cstmt.setInt(5, start);
                cstmt.setInt(6, endrange);


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
                        
                        subdisability = null;

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
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {
                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar1.add(surgeryreportdto1);
                }

            } else if (Surgerytype.equals("HearingAidType")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_HearingAidType_Reports_Mandalwise(?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                cstmt.setInt(5, start);
                cstmt.setInt(6, endrange);

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
                        
                        subdisability = null;
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
                        surgeryreportdto1.setSubtype(subdisability);
                    }
                    if (rs.getString("Sub_Sub_Disability_ID") != null) {
                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1,b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }
                            }
                        }
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar1.add(surgeryreportdto1);
                }
            } else if (Surgerytype.equals("WalkingFrame")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_WalkingFrame_Reports_Mandalwise(?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                cstmt.setInt(5, start);
                cstmt.setInt(6, endrange);

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
                        
                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1,  a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }
                            }
                        }
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }
                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }
                    ar1.add(surgeryreportdto1);

                }
            } else {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Mandalwise(?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getTablename());
                cstmt.setString(4, Surgerytype);
                cstmt.setString(5, surgeryreportdto.getFieldvalue());
                cstmt.setString(6, fromdate);
                cstmt.setString(7, todate);
                cstmt.setInt(8, start);
                cstmt.setInt(9, endrange);
                
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
                        
                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1, a[i] );
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }
                            }
                        }
                        surgeryreportdto2.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto2.setSubsubtype(subsubdisability);
                    }
                    surgeryreportdto2.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto2.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto2.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto2.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto2.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto2.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto2.setCaste("NA");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto2.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto2.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto2.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto2.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto2.setOccupation("Wage-Employed");
                    }

                    ar1.add(surgeryreportdto2);
                }
            }
        } catch (ParseException parseException) {
         
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, parseException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportformandalDetails");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportformandalDetails", "CommonPersonalReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportformandalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportformandalDetails", "CommonPersonalReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportformandalDetails");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeResultSet(rs2);
           DBConnection.closeStatement(pstmt);
           DBConnection.closeStatement(cstmt);
           DBConnection.closeConnection(con);
        }

        return ar1;
    }

    /**
     * this mehtod is for fetching villagewise personalreport list from database
     * @param datasource
     * @param surgeryreportdto
     * @param Surgerytype
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getPersonalReportforvillageDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String Surgerytype, int start, int endrange) throws SADAREMDBException, SQLException {

        ArrayList ar = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;
        ResultSet rs2 = null;
        ResultSet rs = null;
        String annual = null;
        String subdisability = null;
        String subsubdisability = null;
        try {

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

            int a[] = new int[20];
            int b[] = new int[20];

            if (Surgerytype.equals("Physiotherapy") || Surgerytype.equals("SpeechTherapy") || Surgerytype.equals("CouncellingGuidance")) {

                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Villagewise(?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getTablename());
                cstmt.setString(5, surgeryreportdto.getFirstcolumn());
                cstmt.setString(6, surgeryreportdto.getFieldvalue());
                cstmt.setString(7, surgeryreportdto.getSecondcolumn());
                cstmt.setString(8, surgeryreportdto.getSecondfieldvalue());
                cstmt.setString(9, fromdate);
                cstmt.setString(10, todate);
                cstmt.setInt(11, start);
                cstmt.setInt(12, endrange);

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
                        

                        subdisability = null;

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
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }
                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);
                }
            } else if (Surgerytype.equals("orthosis")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Orthosis_Reports_Villagewise(?,?,?,?,?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                cstmt.setInt(6, start);
                cstmt.setInt(7, endrange);

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
                        
                        subdisability = null;

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
                        surgeryreportdto1.setSubtype(subdisability);
                    }
                    if (rs.getString("Sub_Sub_Disability_ID") != null) {
                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1,  b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }
                            }
                        }
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }
                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);
                }

            } else if (Surgerytype.equals("prosthesis")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Prosthesis_Reports_Villagewise(?,?,?,?,?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                cstmt.setInt(6, start);
                cstmt.setInt(7, endrange);

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
                        
                        subdisability = null;

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
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {
                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }
                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);
                }

            } else if (Surgerytype.equals("HearingAidType")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_HearingAidType_Reports_Villagewise(?,?,?,?,?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                cstmt.setInt(6, start);
                cstmt.setInt(7, endrange);
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
                        
                        subdisability = null;

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
                        surgeryreportdto1.setSubtype(subdisability);
                    }


                    if (rs.getString("Sub_Sub_Disability_ID") != null) {
                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1, b[i] );
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }
                            }
                        }
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);
                }
            } else if (Surgerytype.equals("WalkingFrame")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_WalkingFrame_Reports_Villagewise(?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                cstmt.setInt(6, start);
                cstmt.setInt(7, endrange);

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
                        

                        subdisability = null;

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
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {
                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }

                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);
                }

            } else {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Villagewise(?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getTablename());
                cstmt.setString(5, Surgerytype);
                cstmt.setString(6, surgeryreportdto.getFieldvalue());
                cstmt.setString(7, fromdate);
                cstmt.setString(8, todate);
                cstmt.setInt(9, start);
                cstmt.setInt(10, endrange);

                
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
                        

                        subdisability = null;


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
                        surgeryreportdto3.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto3.setSubsubtype(subsubdisability);
                    }



                    surgeryreportdto3.setTotalpercent(rs.getString("TotalDisability"));



                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto3.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto3.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto3.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto3.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto3.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto3.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto3.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto3.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto3.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto3.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto3.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto3);

                }
            }
        } catch (ParseException parseException) {
            
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, parseException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportforvillageDetails");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportforvillageDetails", "CommonPersonalReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportforvillageDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportforvillageDetails", "CommonPersonalReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportforvillageDetails");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeResultSet(rs2);
           DBConnection.closeStatement(pstmt);
           DBConnection.closeStatement(cstmt);

           DBConnection.closeConnection(con);
        }


        return ar;
    }

    /**
     * this mehtod is for fetching habitationwise personalreport list from database
     * @param datasource
     * @param surgeryreportdto
     * @param Surgerytype
     * @throws org.bf.disability.Exception.SADAREMException
     * @return lilst
     */
    public ArrayList getPersonalReportforhabitationDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String Surgerytype, int start, int endrange) throws SADAREMDBException, SQLException {


        ArrayList ar = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;

        ResultSet rs = null;
        String subdisability = null;
        String subsubdisability = null;
        ResultSet rs2 = null;
        int a[] = new int[20];
        int b[] = new int[20];
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(surgeryreportdto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

            if (Surgerytype.equals("Physiotherapy") || Surgerytype.equals("SpeechTherapy") || Surgerytype.equals("CouncellingGuidance")) {



                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Habitationwise(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());

                cstmt.setString(2, surgeryreportdto.getMandalid());

                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                cstmt.setString(5, surgeryreportdto.getTablename());
                cstmt.setString(6, surgeryreportdto.getFirstcolumn());

                cstmt.setString(7, surgeryreportdto.getFieldvalue());
                cstmt.setString(8, surgeryreportdto.getSecondcolumn());
                cstmt.setString(9, surgeryreportdto.getSecondfieldvalue());
                cstmt.setString(10, fromdate);
                cstmt.setString(11, todate);
                cstmt.setInt(12, start);
                cstmt.setInt(13, endrange);


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
                        

                        subdisability = null;


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
                        surgeryreportdto2.setSubtype(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto2.setSubsubtype(subsubdisability);
                    }



                    surgeryreportdto2.setTotalpercent(rs.getString("TotalDisability"));
                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto2.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto2.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto2.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto2.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto2.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto2.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto2.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto2.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto2.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto2.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto2.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto2);

                }



            } else if (Surgerytype.equals("orthosis")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Orthosis_Reports_Habitationwise(?,?,?,?,?,?,?,?)}");

                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                cstmt.setString(5, fromdate);
                cstmt.setString(6, todate);
                cstmt.setInt(7, start);
                cstmt.setInt(8, endrange);

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
                        

                        subdisability = null;


                        for (int i = 0; i < a.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Disability_Name from tblSubDisability_Details where Sub_Disability_ID=?");
                        	pstmt.setInt(1,  a[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }
                        }
                        surgeryreportdto1.setSubtype(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);
                }

            } else if (Surgerytype.equals("prosthesis")) {

                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Prosthesis_Reports_Habitationwise(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                cstmt.setString(5, fromdate);
                cstmt.setString(6, todate);
                cstmt.setInt(7, start);
                cstmt.setInt(8, endrange);

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
                        

                        subdisability = null;



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
                        surgeryreportdto1.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {
                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }

                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);
                }

            } else if (Surgerytype.equals("HearingAidType")) {

                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_HearingAidType_Reports_Habitationwise(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                cstmt.setString(5, fromdate);
                cstmt.setString(6, todate);
                cstmt.setInt(7, start);
                cstmt.setInt(8, endrange);

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
                        

                        subdisability = null;


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
                        surgeryreportdto1.setSubtype(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1,  b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }



                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));


                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }
            } else if (Surgerytype.equals("WalkingFrame")) {

                con = DBConnection.getConnection();

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_WalkingFrame_Reports_Habitationwise(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                cstmt.setString(5, fromdate);
                cstmt.setString(6, todate);
                cstmt.setInt(7, start);
                cstmt.setInt(8, endrange);

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
                        

                        subdisability = null;



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
                        surgeryreportdto1.setSubtype(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                        	pstmt = con.prepareStatement("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities where Sub_Sub_Disability_ID=?");
                        	pstmt.setInt(1,b[i]);
                            rs2 = pstmt.executeQuery();
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name");
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name");
                                }

                            }
                        }
                        surgeryreportdto1.setSubsubtype(subsubdisability);
                    }



                    surgeryreportdto1.setTotalpercent(rs.getString("TotalDisability"));


                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto1.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto1.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto1.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto1.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto1.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto1.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto1.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto1.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto1.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto1.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto1.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto1);

                }

            } else {



                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Habitationwise(?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());

                cstmt.setString(2, surgeryreportdto.getMandalid());

                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                cstmt.setString(5, surgeryreportdto.getTablename());
                cstmt.setString(6, Surgerytype);
                cstmt.setString(7, surgeryreportdto.getFieldvalue());
                cstmt.setString(8, fromdate);
                cstmt.setString(9, todate);
                cstmt.setInt(10, start);
                cstmt.setInt(11, endrange);


                
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
                        

                        subdisability = null;


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
                        surgeryreportdto3.setSubtype(subdisability);
                    }

                    if (rs.getString("Sub_Sub_Disability_ID") != null) {


                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
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
                        surgeryreportdto3.setSubsubtype(subsubdisability);
                    }



                    surgeryreportdto3.setTotalpercent(rs.getString("TotalDisability"));

                    if (rs.getString("Caste").equals("1")) {
                        surgeryreportdto3.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        surgeryreportdto3.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        surgeryreportdto3.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        surgeryreportdto3.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        surgeryreportdto3.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        surgeryreportdto3.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        surgeryreportdto3.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        surgeryreportdto3.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        surgeryreportdto3.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        surgeryreportdto3.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        surgeryreportdto3.setOccupation("Wage-Employed");
                    }

                    ar.add(surgeryreportdto3);

                }
            }
        } catch (ParseException parseException) {
         
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, parseException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportforhabitationDetails");
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportforhabitationDetails", "CommonPersonalReportDAO", "DataBase");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportforhabitationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportforhabitationDetails", "CommonPersonalReportDAO", "Code");
          con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportDAO", "getPersonalReportforhabitationDetails");
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeResultSet(rs2);
           DBConnection.closeStatement(pstmt);
           DBConnection.closeStatement(cstmt);

           DBConnection.closeConnection(con);
        }
        return ar;
    }

    // Get Disrictwise Niramaya Personal Report
    public ArrayList getNiramayaDistrictPersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException,SQLException {

        ArrayList ar = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String dateOfBirth = null;

        try {
            if (disabilityName.equalsIgnoreCase("MR")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_MR_PERSONALDETAILSDISTRICTWISEFORNIRAMAYA(?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setInt(2, start);
                cstmt.setInt(3, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }
            } else if (disabilityName.equalsIgnoreCase("OH")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call dbo.SP_CP_PERSONALDETAILSDISTRICTWISEFORNIRAMAYA(?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setInt(2, start);
                cstmt.setInt(3, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }

            } else if (disabilityName.equalsIgnoreCase("MD")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_MD_PERSONALDETAILSDISTRICTWISEFORNIRAMAYA(?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setInt(2, start);
                cstmt.setInt(3, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }

            }


        } catch (SQLException sqle) {
        } catch (Exception e) {
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(pstmt);
           DBConnection.closeStatement(cstmt);
           DBConnection.closeConnection(con);

        }

        return ar;
    }

    // Get Mandalwise Niramaya Personal Report
    public ArrayList getNiramayaMandalPersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException,SQLException {

        ArrayList ar = new ArrayList();
        Connection con = null;
        //Statement stmt = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String dateOfBirth = null;
        try {
            if (disabilityName.equalsIgnoreCase("MR")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_MR_PERSONALDETAILSMANDALWISEFORNIRAMAYA(?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setInt(3, start);
                cstmt.setInt(4, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }
            } else if (disabilityName.equalsIgnoreCase("OH")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_CP_PERSONALDETAILSMANDALWISEFORNIRAMAYA(?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setInt(3, start);
                cstmt.setInt(4, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }

            } else if (disabilityName.equalsIgnoreCase("MD")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_MD_PERSONALDETAILSMANDALWISEFORNIRAMAYA(?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setInt(3, start);
                cstmt.setInt(4, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }

            }


        } catch (SQLException sqle) {
        } catch (Exception e) {
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);
           DBConnection.closeConnection(con);

        }

        return ar;
    }

    // Get Villagewise Niramaya Personal Report
    public ArrayList getNiramayaVillagePersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException,SQLException {

        ArrayList ar = new ArrayList();
        Connection con = null;
        //Statement stmt = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String dateOfBirth = null;
        try {
            if (disabilityName.equalsIgnoreCase("MR")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_MR_PERSONALDETAILSVILLAGEWISEFORNIRAMAYA(?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setInt(4, start);
                cstmt.setInt(5, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }
            } else if (disabilityName.equalsIgnoreCase("OH")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_CP_PERSONALDETAILSVILLAGEWISEFORNIRAMAYA(?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setInt(4, start);
                cstmt.setInt(5, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }

            } else if (disabilityName.equalsIgnoreCase("MD")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_MD_PERSONALDETAILSVILLAGEWISEFORNIRAMAYA(?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setInt(4, start);
                cstmt.setInt(5, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }

            }


        } catch (SQLException sqle) {
        } catch (Exception e) {
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);
           DBConnection.closeConnection(con);

        }

        return ar;
    }

    // Get Villagewise Niramaya Personal Report
    public ArrayList getNiramayaHabitationPersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException,SQLException {

        ArrayList ar = new ArrayList();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String dateOfBirth = null;


        try {
            if (disabilityName.equalsIgnoreCase("MR")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_MR_PERSONALDETAILSHABITATIONWISEFORNIRAMAYA(?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                cstmt.setInt(5, start);
                cstmt.setInt(6, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }
            } else if (disabilityName.equalsIgnoreCase("OH")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_CP_PERSONALDETAILSHABITATIONWISEFORNIRAMAYA(?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                cstmt.setInt(5, start);
                cstmt.setInt(6, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }

            } else if (disabilityName.equalsIgnoreCase("MD")) {
                con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call SP_MD_PERSONALDETAILSHABITATIONWISEFORNIRAMAYA(?,?,?,?,?,?)}");
                cstmt.setString(1, surgeryreportdto.getDistrictid());
                cstmt.setString(2, surgeryreportdto.getMandalid());
                cstmt.setString(3, surgeryreportdto.getVillageid());
                cstmt.setString(4, surgeryreportdto.getHabitationid());
                cstmt.setInt(5, start);
                cstmt.setInt(6, endrange);

                rs = cstmt.executeQuery();

                while (rs.next()) {
                    CommonReportDTO surgeryreportdto1 = new CommonReportDTO();
                    surgeryreportdto1.setPersoncode(rs.getString("SADAREMID"));
                    surgeryreportdto1.setName(rs.getString("PERSONNAME"));
                    surgeryreportdto1.setFathername(rs.getString("RELATION_NAME"));
                    Date formateDate = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("DATE_OF_BIRTH"));
                    dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").format(formateDate);
                    surgeryreportdto1.setDateOfBirth(dateOfBirth);
                    surgeryreportdto1.setAge(rs.getString("AGE_YEARS"));
                    surgeryreportdto1.setGender(rs.getString("GENDER"));
                    surgeryreportdto1.setMaritalStatus(rs.getString("MARITALSTATUS"));
                    surgeryreportdto1.setEducation(rs.getString("EDUCATION"));
                    surgeryreportdto1.setTypeofdisability(rs.getString("DISABILITY TYPE"));
                    surgeryreportdto1.setTotalpercent(rs.getString("TOTALDISABILITY"));
                    surgeryreportdto1.setHouseno(rs.getString("HOUSE_NUMBER"));
                    surgeryreportdto1.setDistrictname(rs.getString("DISTRICT_NAME"));
                    surgeryreportdto1.setMandalname(rs.getString("MANDAL_NAME"));
                    surgeryreportdto1.setVillagename(rs.getString("VILLAGE_NAME"));
                    surgeryreportdto1.setHabitationname(rs.getString("HABITATION_NAME"));

                    ar.add(surgeryreportdto1);
                }

            }


        } catch (SQLException sqle) {
        } catch (Exception e) {
        } finally {
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);
           DBConnection.closeConnection(con);

        }

        return ar;
    }
}




