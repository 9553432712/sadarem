/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.MandalClusterlevelPwdForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 842412
 */
public class MandalClusterlevelPwdDAO {

    public ArrayList getPwdsPersonalCount(MandalClusterlevelPwdForm mandalClusterlevelPwdForm, DataSource ds) {
        ArrayList pwdsPersonalCount = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        String query = null;
        Statement stmt = null;
        CallableStatement cstmt = null;
        Map map = null;
        String givenFromDate = mandalClusterlevelPwdForm.getFromdate();
        String givenToDate = mandalClusterlevelPwdForm.getTodate();
        try {
            con = DBConnection.getConnection();
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenFromDate);
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenToDate);
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Disability Wise")) {
//                if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
//                    cstmt = con.prepareCall("{Call mandalcluster_level_disabilitydetailsfor_Village(?,?,?,?,?,?)}");
//                    cstmt.setString(1, mandalClusterlevelPwdForm.getDisabiltyId());
//                    cstmt.setString(2, mandalClusterlevelPwdForm.getDistrict_id());
//                    cstmt.setString(3, mandalClusterlevelPwdForm.getMandal_id());
//                    cstmt.setString(4, mandalClusterlevelPwdForm.getVillage_id());
//                    cstmt.setString(5, fromdate);
//                    cstmt.setString(6, todate);
//                } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
//                    cstmt = con.prepareCall("{Call mandalcluster_level_disabilitydetailsfor_Mandal(?,?,?,?,?)}");
//                    cstmt.setString(1, mandalClusterlevelPwdForm.getDisabiltyId());
//                    cstmt.setString(2, mandalClusterlevelPwdForm.getDistrict_id());
//                    cstmt.setString(3, mandalClusterlevelPwdForm.getMandal_id());
//                    cstmt.setString(4, fromdate);
//                    cstmt.setString(5, todate);
//                } else if (mandalClusterlevelPwdForm.getDistrict_id() != null && mandalClusterlevelPwdForm.getDistrict_id() != "") {
//                    cstmt = con.prepareCall("{Call mandalcluster_level_disabilitydetailsfor_District(?,?,?,?)}");
//                    cstmt.setString(1, mandalClusterlevelPwdForm.getDisabiltyId());
//                    cstmt.setString(2, mandalClusterlevelPwdForm.getDistrict_id());
//                    cstmt.setString(3, fromdate);
//                    cstmt.setString(4, todate);
//                } else {
                cstmt = con.prepareCall("{Call mandalcluster_level_disabilitydetailsfor_state(?,?,?,?)}");
                cstmt.setString(1, mandalClusterlevelPwdForm.getDistrictid());
                cstmt.setString(2, mandalClusterlevelPwdForm.getDisabilityWise());
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
//                }

            } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Medicalboard Wise")) {
                if (mandalClusterlevelPwdForm.getCampId() != null && mandalClusterlevelPwdForm.getCampId() != "") {
                    cstmt = con.prepareCall("{Call mandalclusterlevelfor_Medicalboard(?,?,?,?)}");
                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict());
                    cstmt.setString(2, mandalClusterlevelPwdForm.getCampId());
//                    cstmt.setString(3, mandalClusterlevelPwdForm.getMandal_id());
//                    cstmt.setString(4, mandalClusterlevelPwdForm.getVillage_id());
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                }

            } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
                cstmt = con.prepareCall("{Call mandalclusterlevelfor_Datewise(?,?,?)}");
                cstmt.setString(1, mandalClusterlevelPwdForm.getDistrictID());
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);

            } else {
                if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
                    cstmt = con.prepareCall("{Call mandalcluster_level_personneldetailsfor_village(?,?,?,?,?)}");
                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict_id());
                    cstmt.setString(2, mandalClusterlevelPwdForm.getMandal_id());
                    cstmt.setString(3, mandalClusterlevelPwdForm.getVillage_id());
                    cstmt.setString(4, fromdate);
                    cstmt.setString(5, todate);
                } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
                    cstmt = con.prepareCall("{Call mandalcluster_level_personneldetailsfor_mandal(?,?,?,?)}");
                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict_id());
                    cstmt.setString(2, mandalClusterlevelPwdForm.getMandal_id());
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                } else if (mandalClusterlevelPwdForm.getDistrict_id() != null && mandalClusterlevelPwdForm.getDistrict_id() != "" && !mandalClusterlevelPwdForm.getDistrict_id().equalsIgnoreCase("null")) {
                    cstmt = con.prepareCall("{Call mandalcluster_level_personneldetailsfor_District(?,?,?)}");
                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict_id());
                    cstmt.setString(2, fromdate);
                    cstmt.setString(3, todate);
                } else {
                    cstmt = con.prepareCall("{Call mandalcluster_level_personneldetailsfor_state(?,?)}");
                    cstmt.setString(1, fromdate);
                    cstmt.setString(2, todate);
                }
            }


            // stmt = con.createStatement();
            rs = cstmt.executeQuery();
            int Totalcount = 0, totaldatecount = 0;
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Disability Wise")) {
//                        if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
//                            map.put("habitation_id", rs.getString(1));
//                            map.put("disabilityid", rs.getString(2));
//                            map.put("count", rs.getString(3));
//                            map.put("habitationName", rs.getString(4));
//                            map.put("disabilityName", rs.getString(5));
//                            map.put("villageid", mandalClusterlevelPwdForm.getVillage_id());
//                            map.put("districtid", mandalClusterlevelPwdForm.getDistrict_id());
//                            map.put("mandalid", mandalClusterlevelPwdForm.getMandal_id());
//                            map.put("villagename", mandalClusterlevelPwdForm.getVillageName());
//                            map.put("mandalname", mandalClusterlevelPwdForm.getMandalName());
//                            map.put("districtname", mandalClusterlevelPwdForm.getDistrictName());
//                            map.put("disabilityid", mandalClusterlevelPwdForm.getDisabiltyId());
//                            map.put("typeOfSearch", mandalClusterlevelPwdForm.getTypeOfSearch());
//                        } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
//                            map.put("village_id", rs.getString(1));
//                            map.put("disabilityid", rs.getString(2));
//                            map.put("count", rs.getString(3));
//                            map.put("villageName", rs.getString(4));
//                            map.put("disabilityName", rs.getString(5));
//                            map.put("districtid", mandalClusterlevelPwdForm.getDistrict_id());
//                            map.put("mandalid", mandalClusterlevelPwdForm.getMandal_id());
//                            map.put("mandalname", mandalClusterlevelPwdForm.getMandalName());
//                            map.put("districtname", mandalClusterlevelPwdForm.getDistrictName());
//                            map.put("disabilityid", mandalClusterlevelPwdForm.getDisabiltyId());
//                            map.put("typeOfSearch", mandalClusterlevelPwdForm.getTypeOfSearch());
//                        } else if (mandalClusterlevelPwdForm.getDistrict_id() != null && mandalClusterlevelPwdForm.getDistrict_id() != "") {
//                            map.put("mandal_id", rs.getString(1));
//                            map.put("disabilityid", rs.getString(2));
//                            map.put("count", rs.getString(3));
//                            map.put("mandalName", rs.getString(4));
//                            map.put("disabilityName", rs.getString(5));
//
//                            map.put("districtid", mandalClusterlevelPwdForm.getDistrict_id());
//                            map.put("districtname", mandalClusterlevelPwdForm.getDistrictName());
//                            map.put("disabilityid", mandalClusterlevelPwdForm.getDisabiltyId());
//                            map.put("typeOfSearch", mandalClusterlevelPwdForm.getTypeOfSearch());
//                        } else {
                        //map.put("district_id", rs.getString(1));
//                        if (mandalClusterlevelPwdForm.getDistrictid().equalsIgnoreCase("All") && mandalClusterlevelPwdForm.getDisabilityWise().equalsIgnoreCase("All")) {
//                            map.put("disabilityid", rs.getString(1));
//                            if (rs.getString(2) != null && rs.getString(2) !="") {
//                                map.put("districtName", rs.getString(2));
//                            }else {
//                                map.put("districtName", "-");
//                            }
//                            //map.put("districtName", rs.getString(2));
//                            //map.put("disabilityName", rs.getString(3));
//                            if (rs.getString(3) != null && rs.getString(3) !="") {
//                                map.put("disabilityName", rs.getString(3));
//                            }else {
//                                map.put("disabilityName", "-");
//                            }
//                            map.put("count", rs.getString(4));
//
//                            // map.put("districtID", mandalClusterlevelPwdForm.getDistrictID());
//                        }else{
                        map.put("district_id", rs.getString(1));
                        map.put("districtName", rs.getString(2));
                        map.put("count", rs.getString(3));
                        map.put("disabilityid", rs.getString(4));

                        if (rs.getString(5) != null && rs.getString(5) != "") {
                            map.put("disabilityName", rs.getString(5));
                        } else {
                            map.put("disabilityName", "-");
                        }
                        //map.put("disabilityName", rs.getString(4));

                        // }
                        map.put("typeOfSearch", mandalClusterlevelPwdForm.getTypeOfSearch());
                        map.put("disabilityID", mandalClusterlevelPwdForm.getDisabilityWise());
                        map.put("district", mandalClusterlevelPwdForm.getDistrictid());
//                        }
                    } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Medicalboard Wise")) {
                        if (mandalClusterlevelPwdForm.getCampId() != null && mandalClusterlevelPwdForm.getCampId() != "") {
                            map.put("campid", rs.getString(1));
                            map.put("campname", rs.getString(2));
                            map.put("count", rs.getString(3));
                            map.put("districtName", rs.getString(4));
                            map.put("districtid", mandalClusterlevelPwdForm.getDistrict());
                            map.put("campid1", mandalClusterlevelPwdForm.getCampId());
                            map.put("typeOfSearch", mandalClusterlevelPwdForm.getTypeOfSearch());
                        }

                    } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
                        map.put("campdate", rs.getString(1));
                        map.put("districtname", rs.getString(2));
                        map.put("count", rs.getString(3));
                        map.put("typeOfSearch", mandalClusterlevelPwdForm.getTypeOfSearch());
                        map.put("districtID", mandalClusterlevelPwdForm.getDistrictID());

                    } else {
                        if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
                            map.put("habitation_id", rs.getString(1));
                            map.put("habitationName", rs.getString(2));
                            map.put("count", rs.getString(3));
                            map.put("villageid", mandalClusterlevelPwdForm.getVillage_id());
                            map.put("districtid", mandalClusterlevelPwdForm.getDistrict_id());
                            map.put("mandalid", mandalClusterlevelPwdForm.getMandal_id());
                            map.put("villagename", mandalClusterlevelPwdForm.getVillageName());
                            map.put("mandalname", mandalClusterlevelPwdForm.getMandalName());
                            map.put("districtname", mandalClusterlevelPwdForm.getDistrictName());
                        } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
                            map.put("village_id", rs.getString(1));
                            map.put("villageName", rs.getString(2));
                            map.put("count", rs.getString(3));
                            map.put("districtid", mandalClusterlevelPwdForm.getDistrict_id());
                            map.put("mandalid", mandalClusterlevelPwdForm.getMandal_id());
                            map.put("mandalname", mandalClusterlevelPwdForm.getMandalName());
                            map.put("districtname", mandalClusterlevelPwdForm.getDistrictName());
                        } else if (mandalClusterlevelPwdForm.getDistrict_id() != null && mandalClusterlevelPwdForm.getDistrict_id() != "") {
                            map.put("mandal_id", rs.getString(1));
                            map.put("mandalName", rs.getString(2));
                            map.put("count", rs.getString(3));
                            map.put("districtid", mandalClusterlevelPwdForm.getDistrict_id());
                            map.put("districtname", mandalClusterlevelPwdForm.getDistrictName());
                        } else {
                            map.put("district_id", rs.getString(1));
                            map.put("districtName", rs.getString(2));
                            map.put("count", rs.getString(3));
                        }
                    }

//                    map.put("district_id", rs.getString(1));
//                    map.put("districtName", rs.getString(2));
//                    map.put("count", rs.getString(3));
                    map.put("fromDate", mandalClusterlevelPwdForm.getFromdate());
                    map.put("toDate", mandalClusterlevelPwdForm.getTodate());
                    map.put("typeOfSearch", mandalClusterlevelPwdForm.getTypeOfSearch());
                    // mandalClusterlevelPwdForm.setDistrict_id(rs.getString(1));
                    //if (!mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
                    Totalcount = Totalcount + rs.getInt(3);
                    // }
//                    if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
//                        totaldatecount =totaldatecount+ rs.getInt(2);
//                    }

                    //map.put("total",Totalcount);
                    pwdsPersonalCount.add(map);
                }
            }
            if (pwdsPersonalCount.size() > 0) {
                map.put("Totalcount", Totalcount);
                map.put("toptaldatecount", totaldatecount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pwdsPersonalCount;
    }

    public ArrayList getPwdsPensionDetails(DataSource ds) {
        ArrayList pwdPensionData = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        String query = null;
        Statement stmt = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            query = "select district_id,district_name,census,Totalassessed,coverageaganistcensus,Eligible,pensionscoverage,pensioncoveredaganisteligibility from dbo.pensionpopulation";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            int totalCensus = 0, totalAssessed = 0, totalCoverageaganistcensus = 0, totalEligible = 0, totalPensionscoverage = 0, totalPensioncoveredaganisteligibility = 0, angCoverageaganistcensus = 0, avgPensioncoveredaganisteligibility = 0;
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("district_id", rs.getString(1));
                    map.put("district_name", rs.getString(2));
                    map.put("census", rs.getString(3));
                    map.put("Totalassessed", rs.getString(4));
                    map.put("coverageaganistcensus", Math.round(rs.getLong(5)));
                    map.put("Eligible", rs.getString(6));
                    map.put("pensionscoverage", rs.getString(7));
                    map.put("pensioncoveredaganisteligibility", rs.getString(8));
                    totalCensus = totalCensus + rs.getInt(3);
                    totalAssessed = totalAssessed + rs.getInt(4);
                    totalCoverageaganistcensus = totalCoverageaganistcensus + rs.getInt(5);
                    totalEligible = totalEligible + rs.getInt(6);
                    totalPensionscoverage = totalPensionscoverage + rs.getInt(7);
                    totalPensioncoveredaganisteligibility = totalPensioncoveredaganisteligibility + rs.getInt(8);
                    pwdPensionData.add(map);
                }
            }
            if (pwdPensionData.size() > 0) {
                angCoverageaganistcensus = (totalCoverageaganistcensus / (pwdPensionData.size() + 1)) * 100;
                avgPensioncoveredaganisteligibility = (totalPensioncoveredaganisteligibility / (pwdPensionData.size() + 1)) * 100;
                map.put("totalCensus", totalCensus);
                map.put("totalAssessed", totalAssessed);
                map.put("angCoverageaganistcensus", angCoverageaganistcensus);
                map.put("totalEligible", totalEligible);
                map.put("totalPensionscoverage", totalPensionscoverage);
                map.put("avgPensioncoveredaganisteligibility", avgPensioncoveredaganisteligibility);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pwdPensionData;
    }

    public ArrayList getPwdsDisabilityPensionDAO(MandalClusterlevelPwdForm mandalClusterlevelPwdForm, DataSource ds) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public ArrayList getPwdsIndividualCount(MandalClusterlevelPwdForm mandalClusterlevelPwdForm, DataSource ds)
            throws SADAREMDBException, SQLException {
        ArrayList pwdsIndividualCount = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        String query = null;
        Statement stmt = null;
        CallableStatement cstmt = null;
        String campDate = mandalClusterlevelPwdForm.getCampDate();
        Map map = null;
        String givenFromDate = mandalClusterlevelPwdForm.getFromdate();
        String givenToDate = mandalClusterlevelPwdForm.getTodate();
        try {
            con = DBConnection.getConnection();
//            if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
//                Date date = new SimpleDateFormat("dd/mm/yyyy").parse(campDate);
//                String Cdate = new SimpleDateFormat("mm/dd/yyyy").format(date);
//            }
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenFromDate);

            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenToDate);
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Disability Wise")) {
//                if (mandalClusterlevelPwdForm.getHabitation_id() != null && mandalClusterlevelPwdForm.getHabitation_id() != "") {
//                    cstmt = con.prepareCall("{Call mandalclusterlevel_Individualdetailsfordisability_Village(?,?,?,?,?,?,?)}");
//
//                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict_id());
//                    cstmt.setString(2, mandalClusterlevelPwdForm.getMandal_id());
//                    cstmt.setString(3, mandalClusterlevelPwdForm.getVillage_id());
//                    cstmt.setString(4, mandalClusterlevelPwdForm.getHabitation_id());
//                    cstmt.setString(5, mandalClusterlevelPwdForm.getDisabiltyId());
//                    cstmt.setString(6, fromdate);
//                    cstmt.setString(7, todate);
//                } else if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
//                    cstmt = con.prepareCall("{Call mandalclusterlevel_Individualdetailsfordisability_Mandal(?,?,?,?,?,?)}");
//                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict_id());
//                    cstmt.setString(2, mandalClusterlevelPwdForm.getMandal_id());
//                    cstmt.setString(3, mandalClusterlevelPwdForm.getVillage_id());
//                    cstmt.setString(4, mandalClusterlevelPwdForm.getDisabiltyId());
//                    cstmt.setString(5, fromdate);
//                    cstmt.setString(6, todate);
//                } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
//                    cstmt = con.prepareCall("{Call mandalclusterlevel_Individualdetailsfordisability_District(?,?,?,?,?)}");
//                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict_id());
//                    cstmt.setString(2, mandalClusterlevelPwdForm.getMandal_id());
//                    cstmt.setString(3, mandalClusterlevelPwdForm.getDisabiltyId());
//                    cstmt.setString(4, fromdate);
//                    cstmt.setString(5, todate);
//                } else {
                cstmt = con.prepareCall("{Call mandalclusterlevel_Individualdetailsfordisability_state(?,?,?,?)}");
                cstmt.setString(1, mandalClusterlevelPwdForm.getDistrictid());
                cstmt.setString(2, mandalClusterlevelPwdForm.getDisabiltyId());
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
//                }
            } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Medicalboard Wise")) {
                if (mandalClusterlevelPwdForm.getCampId() != null && mandalClusterlevelPwdForm.getCampId() != "") {
                    cstmt = con.prepareCall("{Call mandalclusterlevel_Individualdetailsfor_Medicalboard(?,?,?,?)}");
                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict());
                    cstmt.setString(2, mandalClusterlevelPwdForm.getCampId());
//                    cstmt.setString(3, mandalClusterlevelPwdForm.getMandal_id());
//                    cstmt.setString(4, mandalClusterlevelPwdForm.getVillage_id());
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                }

            } else if (mandalClusterlevelPwdForm.getTypeOfSearch() != null && mandalClusterlevelPwdForm.getTypeOfSearch().equals("Date Wise")) {
                Date date = new SimpleDateFormat("dd/mm/yyyy").parse(campDate);
                String Cdate = new SimpleDateFormat("mm/dd/yyyy").format(date);
                cstmt = con.prepareCall("{Call mandalclusterlevel_Individualdetailsfor_Datewise(?,?,?,?)}");
                cstmt.setString(1, mandalClusterlevelPwdForm.getDistrictID());
                cstmt.setString(2, Cdate);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);

            } else {
                if (mandalClusterlevelPwdForm.getHabitation_id() != null && mandalClusterlevelPwdForm.getHabitation_id() != "") {

                    cstmt = con.prepareCall("{Call mandalcluster_level_Individualdetailsfor_village(?,?,?,?,?,?)}");
                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict_id());
                    cstmt.setString(2, mandalClusterlevelPwdForm.getMandal_id());
                    cstmt.setString(3, mandalClusterlevelPwdForm.getVillage_id());
                    cstmt.setString(4, mandalClusterlevelPwdForm.getHabitation_id());
                    cstmt.setString(5, fromdate);
                    cstmt.setString(6, todate);
                } else if (mandalClusterlevelPwdForm.getVillage_id() != null && mandalClusterlevelPwdForm.getVillage_id() != "") {
                    cstmt = con.prepareCall("{Call mandalcluster_level_Individualdetailsfor_Mandal(?,?,?,?,?)}");
                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict_id());
                    cstmt.setString(2, mandalClusterlevelPwdForm.getMandal_id());
                    cstmt.setString(3, mandalClusterlevelPwdForm.getVillage_id());
                    cstmt.setString(4, fromdate);
                    cstmt.setString(5, todate);
                } else if (mandalClusterlevelPwdForm.getMandal_id() != null && mandalClusterlevelPwdForm.getMandal_id() != "") {
                    cstmt = con.prepareCall("{Call mandalcluster_level_Individualdetailsfor_District(?,?,?,?)}");
                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict_id());
                    cstmt.setString(2, mandalClusterlevelPwdForm.getMandal_id());
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                } else {
                    cstmt = con.prepareCall("{Call mandalcluster_level_Individualdetailsfor_state(?,?,?)}");
                    cstmt.setString(1, mandalClusterlevelPwdForm.getDistrict_id());
                    cstmt.setString(2, fromdate);
                    cstmt.setString(3, todate);
                }
            }
            rs = cstmt.executeQuery();
            //}
            if (rs != null) {

                while (rs.next()) {
                    map = new HashMap();
                    map.put("pensionid", rs.getString(1));
                    map.put("sadaremid", rs.getString(2));
                    map.put("name", rs.getString(3));
                    map.put("relationname", rs.getString(4));
                    map.put("gender", rs.getString(5));
                    map.put("age", rs.getString(6));
                    if (rs.getString(7) != null && rs.getString(7) != "") {
                        map.put("rationno", rs.getString(7));
                    } else {
                        map.put("rationno", "-");
                    }
                    //map.put("rationno", rs.getString(7));
                    map.put("mandal", rs.getString(8));
                    map.put("village", rs.getString(9));
                    map.put("habitation", rs.getString(10));
                    map.put("disabilityType", rs.getString(11));
                    map.put("pensionphase", rs.getString(12));
                    if (rs.getString(13) != null && rs.getString(13) != "") {
                        map.put("houseno", rs.getString(13));
                    } else {
                        map.put("houseno", "-");
                    }
                    map.put("category", rs.getString(14));
                    map.put("campname", rs.getString(15));
                    map.put("campdate", rs.getString(16));
                    if (rs.getString(17) != null && rs.getString(17) != "") {
                        map.put("phone", rs.getString(17));
                    } else {
                        map.put("phone", "-");
                    }
                    //map.put("phone", rs.getString(17));
                    map.put("villageid", mandalClusterlevelPwdForm.getVillage_id());
                    map.put("districtid", mandalClusterlevelPwdForm.getDistrict_id());
                    map.put("mandalid", mandalClusterlevelPwdForm.getMandal_id());
                    map.put("habitationid", mandalClusterlevelPwdForm.getHabitation_id());
                    map.put("villagename", mandalClusterlevelPwdForm.getVillageName());
                    map.put("mandalname", mandalClusterlevelPwdForm.getMandalName());
                    map.put("districtname", mandalClusterlevelPwdForm.getDistrictName());
                    map.put("habitationname", mandalClusterlevelPwdForm.getHabitationName());
                    map.put("fromdate", mandalClusterlevelPwdForm.getFromdate());
                    map.put("todate", mandalClusterlevelPwdForm.getTodate());
                    map.put("disabilityid", mandalClusterlevelPwdForm.getDisabiltyId());
                    map.put("typeOfSearch", mandalClusterlevelPwdForm.getTypeOfSearch());
                    map.put("district_id", mandalClusterlevelPwdForm.getDistrict());
                    map.put("campid", mandalClusterlevelPwdForm.getCampId());
                    map.put("disability", mandalClusterlevelPwdForm.getDisabilityName());
                    map.put("districtID", mandalClusterlevelPwdForm.getDistrictID());
                    map.put("district", mandalClusterlevelPwdForm.getDistrictid());
                    pwdsIndividualCount.add(map);

                    //}

                }

            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdsIndividualCount", "MandalClusterlevelPwdDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getCMReport");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdsIndividualCount", "MandalClusterlevelPwdDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportDAO", "getCMReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return pwdsIndividualCount;
    }

    
}
