package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.dto.TerritoryDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for getting pwd status of different territory from database
 * @author Ragavendra
 * @version 1.0
 */
public class StatusForPWDReportDAO {

    /**
     * 
     * @param datasource 
     * @param territorydto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getDistricts(DataSource ds, TerritoryDTO territorydto)
            throws SADAREMDBException, SQLException {
        ArrayList pwddistrict = new ArrayList();
        ArrayList pwdreportdata = new ArrayList();
        ArrayList finalreport = new ArrayList();
        Set s = new TreeSet();
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        Statement stmt = null;
        CallableStatement cstmt = null;
        CallableStatement cstmt1 = null;
        CallableStatement cstmt2 = null;
        CallableStatement cstmt3 = null;
        CallableStatement cstmt4 = null;
        CallableStatement cstmt5 = null;
        Connection con = null;
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(territorydto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(territorydto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);


            con = DBConnection.getConnection();
            stmt = con.createStatement();
            cstmt = con.prepareCall("{Call SP_SelectDistrict_Names_for_Reports(?,?)}");
            cstmt.setString(1, fromdate);
            cstmt.setString(2, todate);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                TerritoryDTO territorydto1 = new TerritoryDTO();

                territorydto1.setDistrict_id((rs.getString("district_id")));
                territorydto1.setDistrict_name((rs.getString("district_name")));
                pwddistrict.add(territorydto1);
            }

            cstmt1 = con.prepareCall("{Call SP_Reports_for_LessthanFourtyPercentage_People_inAllDistricts(?,?)}");
            cstmt1.setString(1, fromdate);
            cstmt1.setString(2, todate);
            rs1 = cstmt1.executeQuery();
            cstmt2 = con.prepareCall("{Call SP_Reports_for_FourtytoSixtyPercentage_People_inAllDistricts(?,?)}");
            cstmt2.setString(1, fromdate);
            cstmt2.setString(2, todate);
            rs2 = cstmt2.executeQuery();
            cstmt3 = con.prepareCall("{Call SP_Reports_for_SixtyonetoEighteyPercentage_People_inAllDistricts(?,?)}");
            cstmt3.setString(1, fromdate);
            cstmt3.setString(2, todate);
            rs3 = cstmt3.executeQuery();
            cstmt4 = con.prepareCall("{Call SP_Reports_for_GreaterthanEighteyPercentage_People_inAllDistricts(?,?)}");
            cstmt4.setString(1, fromdate);
            cstmt4.setString(2, todate);
            rs4 = cstmt4.executeQuery();
            cstmt5 = con.prepareCall("{Call SP_CountofRejectedPersons_inAllDistricts(?,?)}");
            cstmt5.setString(1, fromdate);
            cstmt5.setString(2, todate);
            rs5 = cstmt5.executeQuery();
            while (rs1.next()) {
                s.add(rs1.getString(2));
            }

            while (rs2.next()) {
                s.add(rs2.getString(2));
            }

            while (rs3.next()) {
                s.add(rs3.getString(2));

            }

            while (rs4.next()) {
                s.add(rs4.getString(2));

            }

            while (rs5.next()) {

                s.add(rs5.getString(2));

            }

            Object str1[] = new String[s.size()];
            str1 = s.toArray();
            String ss[] = new String[str1.length];
            for (int i = 0; i < str1.length; i++) {
                ss[i] = ((String) str1[i]).trim();

            }

            TerritoryDTO arra[] = new TerritoryDTO[ss.length];

            for (int i = 0; i < arra.length; i++) {
                arra[i] = new TerritoryDTO();
            }

            for (int i = 0; i < arra.length; i++) {
                arra[i].setDistrict_name(ss[i]);
            }
            rs1 = cstmt1.executeQuery();
            while (rs1.next()) {

                String districtname = rs1.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs1.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setBelowfourtyforphysical(rs1.getString(1));

                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setBelowfourtyforvisual(rs1.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setBelowfourtyforhearing(rs1.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setBelowfourtyformentalretardation(rs1.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setBelowfourtyformentalillness(rs1.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setBelowfourtyformultipledisability(rs1.getString(1));
                }
            }

            rs2 = cstmt2.executeQuery();
            while (rs2.next()) {
                String districtname = rs2.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs2.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setFourtytosixtyforphysical(rs2.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setFourtytosixtyforvisual(rs2.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setFourtytosixtyforhearing(rs2.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setFourtytosixtyformentalretardation(rs2.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setFourtytosixtyformentalillness(rs2.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setFourtytosixtyformultipledisability(rs2.getString(1));
                }
            }

            rs3 = cstmt3.executeQuery();
            while (rs3.next()) {
                String districtname = rs3.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs3.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setSixtytoeightyforphysical(rs3.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setSixtyonetoeightyforvisual(rs3.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setSixtyonetoeightyforhearing(rs3.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setSixtyonetoeightyformentalretardation(rs3.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setSixtyonetoeightyformentalillness(rs3.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setSixtyonetoeightyformultipledisability(rs3.getString(1));
                }
            }
            rs4 = cstmt4.executeQuery();

            while (rs4.next()) {
                String districtname = rs4.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs4.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setAboveeightyoneforphysical(rs4.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setAboveeightyoneforvisual(rs4.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setAboveeightyoneforhearing(rs4.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setAboveeightyoneformentalretardation(rs4.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setAboveeightyoneformentalillness(rs4.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setAboveeightyoneformultipledisability(rs4.getString(1));
                }
            }

            rs5 = cstmt5.executeQuery();
            while (rs5.next()) {
                String districtname = rs5.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                arra[obj].setRejectedpersonscount(rs5.getString(1));
            }


            int LocomotorSubtotal = 0;
            int VisualSubtotal = 0;
            int HearingSubtotal = 0;
            int MentalRetardationSubtotal = 0;
            int MentalIllnessSubtotal = 0;
            int MultipleDisabilitySubtotal = 0;
            int AssessedAndRejected = 0;

            for (int i = 0; i < arra.length; i++) {


                LocomotorSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforphysical())
                        + Integer.parseInt(arra[i].getSixtytoeightyforphysical())
                        + Integer.parseInt(arra[i].getAboveeightyoneforphysical());

                VisualSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforvisual())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyforvisual())
                        + Integer.parseInt(arra[i].getAboveeightyoneforvisual());
                HearingSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforhearing())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyforhearing())
                        + Integer.parseInt(arra[i].getAboveeightyoneforhearing());
                MentalRetardationSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformentalretardation())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformentalretardation())
                        + Integer.parseInt(arra[i].getAboveeightyoneformentalretardation());
                MentalIllnessSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformentalillness())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformentalillness())
                        + Integer.parseInt(arra[i].getAboveeightyoneformentalillness());
                MultipleDisabilitySubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformultipledisability())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformultipledisability())
                        + Integer.parseInt(arra[i].getAboveeightyoneformultipledisability());
                AssessedAndRejected =
                        Integer.parseInt(arra[i].getBelowfourtyforphysical())
                        + Integer.parseInt(arra[i].getBelowfourtyforvisual())
                        + Integer.parseInt(arra[i].getBelowfourtyforhearing())
                        + Integer.parseInt(arra[i].getBelowfourtyformentalretardation())
                        + Integer.parseInt(arra[i].getBelowfourtyformentalillness())
                        + Integer.parseInt(arra[i].getBelowfourtyformultipledisability());

                int totalCount =
                        LocomotorSubtotal + VisualSubtotal + HearingSubtotal
                        + MentalRetardationSubtotal + MentalIllnessSubtotal + MultipleDisabilitySubtotal;

                arra[i].setTotalforphysical(String.valueOf(LocomotorSubtotal));
                arra[i].setTotalforvisual(String.valueOf(VisualSubtotal));
                arra[i].setTotalforhearing(String.valueOf(HearingSubtotal));
                arra[i].setTotalformentalretardation(String.valueOf(MentalRetardationSubtotal));
                arra[i].setTotalformentalillness(String.valueOf(MentalIllnessSubtotal));
                arra[i].setTotalformultipledisability(String.valueOf(MultipleDisabilitySubtotal));
                arra[i].setTotaldisability(String.valueOf(totalCount));
                arra[i].setAssessedandrejectedtotal(String.valueOf(AssessedAndRejected));
            }
            for (int kk = 0; kk < arra.length; kk++) {
                pwdreportdata.add(arra[kk]);
            }

            finalreport.add(pwddistrict);
            finalreport.add(pwdreportdata);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistricts", "StatusForPWDReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportDAO", "getDistricts");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistricts", "StatusForPWDReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportDAO", "getDistricts");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs2);
            DBConnection.closeResultSet(rs3);
            DBConnection.closeResultSet(rs4);
            DBConnection.closeResultSet(rs5);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(cstmt1);
            DBConnection.closeStatement(cstmt2);
            DBConnection.closeStatement(cstmt3);
            DBConnection.closeStatement(cstmt4);
            DBConnection.closeStatement(cstmt5);
            DBConnection.closeStatement(cstmt);


        }
        return finalreport;
    }

    /**
     * 
     * @param datasource 
     * @param territorydto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getMandals(DataSource ds, TerritoryDTO territorydto)
            throws SADAREMDBException, SQLException {
        ArrayList pwddistrict = new ArrayList();
        ArrayList mandallist = new ArrayList();
        ArrayList pwdreportformandal = new ArrayList();
        ArrayList finalmandalreport = new ArrayList();
        Connection con = null;
        Statement stmt = null;
        CallableStatement cstmt = null;
        CallableStatement cstmt1 = null;
        CallableStatement cstmt2 = null;
        CallableStatement cstmt3 = null;
        CallableStatement cstmt4 = null;
        CallableStatement cstmt5 = null;
        CallableStatement cstmtm = null;
        ResultSet rsdistrict = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(territorydto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(territorydto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            Set s = new TreeSet();

            con = DBConnection.getConnection();
            cstmtm = con.prepareCall("{Call SP_SelectMandal_Names_for_Reports(?,?,?)}");
            cstmtm.setString(1, territorydto.getDistrict_id());
            cstmtm.setString(2, fromdate);
            cstmtm.setString(3, todate);
            stmt = con.createStatement();
            rs = cstmtm.executeQuery();
            while (rs.next()) {
                TerritoryDTO territoryDTO2 = new TerritoryDTO();
                territoryDTO2.setMandal_id((rs.getString("mandal_id")));
                territoryDTO2.setMandal_name((rs.getString("mandal_name")));
                mandallist.add(territoryDTO2);
            }
            cstmt = con.prepareCall("{Call SP_SelectDistrict_Names_for_Reports(?,?)}");
            cstmt.setString(1, fromdate);
            cstmt.setString(2, todate);
            rsdistrict = cstmt.executeQuery();
            while (rsdistrict.next()) {
                TerritoryDTO territorydto1 = new TerritoryDTO();
                territorydto1.setDistrict_id((rsdistrict.getString("district_id")));
                territorydto1.setDistrict_name((rsdistrict.getString("district_name")));
                pwddistrict.add(territorydto1);
            }
            cstmt1 = con.prepareCall("{Call SP_Reports_for_LessthanFourtyPercentage_People_Basedon_District_ID(?,?,?)}");
            cstmt1.setString(1, territorydto.getDistrict_id());
            cstmt1.setString(2, fromdate);
            cstmt1.setString(3, todate);
            rs1 = cstmt1.executeQuery();
            cstmt2 = con.prepareCall("{Call SP_Reports_for_FourtytoSixtyPercentage_People_Basedon_District_ID(?,?,?)}");
            cstmt2.setString(1, territorydto.getDistrict_id());
            cstmt2.setString(2, fromdate);
            cstmt2.setString(3, todate);
            rs2 = cstmt2.executeQuery();
            cstmt3 = con.prepareCall("{Call SP_Reports_for_SixtyonetoEighteyPercentage_People_Basedon_District_ID(?,?,?)}");
            cstmt3.setString(1, territorydto.getDistrict_id());
            cstmt3.setString(2, fromdate);
            cstmt3.setString(3, todate);
            rs3 = cstmt3.executeQuery();
            cstmt4 = con.prepareCall("{Call SP_Reports_for_GreaterthanEighteyPercentage_People_Basedon_District_ID(?,?,?)}");
            cstmt4.setString(1, territorydto.getDistrict_id());
            cstmt4.setString(2, fromdate);
            cstmt4.setString(3, todate);
            rs4 = cstmt4.executeQuery();
            cstmt5 = con.prepareCall("{Call SP_CountofRejectedPersons_Givenby_District_ID(?,?,?)}");
            cstmt5.setString(1, territorydto.getDistrict_id());
            cstmt5.setString(2, fromdate);
            cstmt5.setString(3, todate);
            rs5 = cstmt5.executeQuery();

            while (rs1.next()) {

                s.add(rs1.getString(2));
            }
            while (rs2.next()) {
                s.add(rs2.getString(2));
            }
            while (rs3.next()) {

                s.add(rs3.getString(2));
            }
            while (rs4.next()) {
                s.add(rs4.getString(2));
            }
            while (rs5.next()) {

                s.add(rs5.getString(2));
            }
            Object str1[] = new String[s.size()];
            str1 = s.toArray();
            String ss[] = new String[str1.length];
            for (int i = 0; i < str1.length; i++) {
                ss[i] = ((String) str1[i]).trim();

            }

            TerritoryDTO arra[] = new TerritoryDTO[ss.length];

            for (int i = 0; i < arra.length; i++) {
                arra[i] = new TerritoryDTO();
            }

            for (int i = 0; i < arra.length; i++) {

                arra[i].setMandal_name(ss[i]);
            }
            rs1 = cstmt1.executeQuery();
            while (rs1.next()) {

                String districtname = rs1.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs1.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setBelowfourtyforphysical(rs1.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setBelowfourtyforvisual(rs1.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setBelowfourtyforhearing(rs1.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setBelowfourtyformentalretardation(rs1.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setBelowfourtyformentalillness(rs1.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setBelowfourtyformultipledisability(rs1.getString(1));
                }
            }
            rs2 = cstmt2.executeQuery();
            while (rs2.next()) {
                String districtname = rs2.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs2.getString(4);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setFourtytosixtyforphysical(rs2.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setFourtytosixtyforvisual(rs2.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setFourtytosixtyforhearing(rs2.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setFourtytosixtyformentalretardation(rs2.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setFourtytosixtyformentalillness(rs2.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setFourtytosixtyformultipledisability(rs2.getString(1));
                }
            }
            rs3 = cstmt3.executeQuery();
            while (rs3.next()) {
                String districtname = rs3.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs3.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setSixtytoeightyforphysical(rs3.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setSixtyonetoeightyforvisual(rs3.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setSixtyonetoeightyforhearing(rs3.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setSixtyonetoeightyformentalretardation(rs3.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setSixtyonetoeightyformentalillness(rs3.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setSixtyonetoeightyformultipledisability(rs3.getString(1));
                }
            }
            rs4 = cstmt4.executeQuery();
            while (rs4.next()) {
                String districtname = rs4.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs4.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setAboveeightyoneforphysical(rs4.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setAboveeightyoneforvisual(rs4.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setAboveeightyoneforhearing(rs4.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setAboveeightyoneformentalretardation(rs4.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setAboveeightyoneformentalillness(rs4.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setAboveeightyoneformultipledisability(rs4.getString(1));
                }
            }
            rs5 = cstmt5.executeQuery();
            while (rs5.next()) {
                String districtname = rs5.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                arra[obj].setRejectedpersonscount(rs5.getString(1));
            }

            int LocomotorSubtotal = 0;
            int VisualSubtotal = 0;
            int HearingSubtotal = 0;
            int MentalRetardationSubtotal = 0;
            int MentalIllnessSubtotal = 0;
            int MultipleDisabilitySubtotal = 0;
            int AssessedAndRejected = 0;

            for (int i = 0; i < arra.length; i++) {


                LocomotorSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforphysical())
                        + Integer.parseInt(arra[i].getSixtytoeightyforphysical())
                        + Integer.parseInt(arra[i].getAboveeightyoneforphysical());
                VisualSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforvisual())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyforvisual())
                        + Integer.parseInt(arra[i].getAboveeightyoneforvisual());
                HearingSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforhearing())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyforhearing())
                        + Integer.parseInt(arra[i].getAboveeightyoneforhearing());
                MentalRetardationSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformentalretardation())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformentalretardation())
                        + Integer.parseInt(arra[i].getAboveeightyoneformentalretardation());
                MentalIllnessSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformentalillness())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformentalillness())
                        + Integer.parseInt(arra[i].getAboveeightyoneformentalillness());
                MultipleDisabilitySubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformultipledisability())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformultipledisability())
                        + Integer.parseInt(arra[i].getAboveeightyoneformultipledisability());
                AssessedAndRejected =
                        Integer.parseInt(arra[i].getBelowfourtyforphysical())
                        + Integer.parseInt(arra[i].getBelowfourtyforvisual())
                        + Integer.parseInt(arra[i].getBelowfourtyforhearing())
                        + Integer.parseInt(arra[i].getBelowfourtyformentalretardation())
                        + Integer.parseInt(arra[i].getBelowfourtyformentalillness())
                        + Integer.parseInt(arra[i].getBelowfourtyformultipledisability());
                int totalCount =
                        LocomotorSubtotal + VisualSubtotal + HearingSubtotal
                        + MentalRetardationSubtotal + MentalIllnessSubtotal
                        + MultipleDisabilitySubtotal;

                arra[i].setTotalforphysical(String.valueOf(LocomotorSubtotal));
                arra[i].setTotalforvisual(String.valueOf(VisualSubtotal));
                arra[i].setTotalforhearing(String.valueOf(HearingSubtotal));
                arra[i].setTotalformentalretardation(String.valueOf(MentalRetardationSubtotal));
                arra[i].setTotalformentalillness(String.valueOf(MentalIllnessSubtotal));
                arra[i].setTotalformultipledisability(String.valueOf(MultipleDisabilitySubtotal));
                arra[i].setTotaldisability(String.valueOf(totalCount));
                arra[i].setAssessedandrejectedtotal(String.valueOf(AssessedAndRejected));



            }
            for (int kk = 0; kk < arra.length; kk++) {
                pwdreportformandal.add(arra[kk]);
            }

            finalmandalreport.add(pwddistrict);
            finalmandalreport.add(mandallist);
            finalmandalreport.add(pwdreportformandal);



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandals", "StatusForPWDReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportDAO", "getMandals");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandals", "StatusForPWDReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportDAO", "getMandals");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rsdistrict);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs2);
            DBConnection.closeResultSet(rs3);
            DBConnection.closeResultSet(rs4);
            DBConnection.closeResultSet(rs5);
            DBConnection.closeStatement(cstmtm);
            DBConnection.closeStatement(cstmt);
            DBConnection.closeStatement(cstmt1);
            DBConnection.closeStatement(cstmt2);
            DBConnection.closeStatement(cstmt3);
            DBConnection.closeStatement(cstmt4);
            DBConnection.closeStatement(cstmt5);
            DBConnection.closeStatement(stmt);


        }


        return finalmandalreport;
    }

    /**
     * 
     * @param datasource 
     * @param territorydto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getVillages(DataSource ds, TerritoryDTO territorydto)
            throws SADAREMDBException, SQLException {

        Set s = new TreeSet();
        ArrayList pwddistrict = new ArrayList();
        ArrayList mandallist = new ArrayList();
        ArrayList villagelist = new ArrayList();
        ArrayList pwdreportforvillage = new ArrayList();
        ArrayList finalvillagereport = new ArrayList();
        Connection con = null;
        Statement stmt = null;
        CallableStatement cstmtd = null;
        CallableStatement cstmtv = null;
        CallableStatement cstmt1 = null;
        CallableStatement cstmt2 = null;
        CallableStatement cstmt3 = null;
        CallableStatement cstmt4 = null;
        CallableStatement cstmt5 = null;
        CallableStatement cstmtm = null;
        ResultSet rsdistrict = null;
        ResultSet rsmandal = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(territorydto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(territorydto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            // CallableStatement cs=null;




            con = DBConnection.getConnection();
            cstmtv = con.prepareCall("{Call SP_SelectVillage_Names_for_Reports(?,?,?,?)}");

            cstmtv.setString(1, territorydto.getDistrict_id());
            cstmtv.setString(2, territorydto.getMandal_id());
            cstmtv.setString(3, fromdate);
            cstmtv.setString(4, todate);
            stmt = con.createStatement();

            rs = cstmtv.executeQuery();
            while (rs.next()) {
                TerritoryDTO territoryDTO3 = new TerritoryDTO();
                territoryDTO3.setVillage_id((rs.getString("village_id")));
                territoryDTO3.setVillage_name((rs.getString("village_name")));

                villagelist.add(territoryDTO3);
            }
            cstmtm = con.prepareCall("{Call SP_SelectMandal_Names_for_Reports(?,?,?)}");
            cstmtm.setString(1, territorydto.getDistrict_id());
            cstmtm.setString(2, fromdate);
            cstmtm.setString(3, todate);


            rsmandal = cstmtm.executeQuery();
            while (rsmandal.next()) {
                TerritoryDTO territoryDTO2 = new TerritoryDTO();
                territoryDTO2.setMandal_id((rsmandal.getString("mandal_id")));
                territoryDTO2.setMandal_name((rsmandal.getString("mandal_name")));

                mandallist.add(territoryDTO2);

            }

            cstmtd = con.prepareCall("{Call SP_SelectDistrict_Names_for_Reports(?,?)}");
            cstmtd.setString(1, fromdate);
            cstmtd.setString(2, todate);


            rsdistrict = cstmtd.executeQuery();
            while (rsdistrict.next()) {
                TerritoryDTO territorydto1 = new TerritoryDTO();
                territorydto1.setDistrict_id((rsdistrict.getString("district_id")));
                territorydto1.setDistrict_name((rsdistrict.getString("district_name")));

                pwddistrict.add(territorydto1);
            }
            cstmt1 = con.prepareCall("{Call SP_Reports_for_LessthanFourtyPercentage_People_Basedon_District_IDandMandal_ID(?,?,?,?)}");
            cstmt1.setString(1, territorydto.getDistrict_id());
            cstmt1.setString(2, territorydto.getMandal_id());
            cstmt1.setString(3, fromdate);
            cstmt1.setString(4, todate);

            rs1 = cstmt1.executeQuery();
            cstmt2 = con.prepareCall("{Call SP_Reports_for_FourtytoSixtyPercentage_People_Basedon_District_IDandMandal_ID(?,?,?,?)}");
            cstmt2.setString(1, territorydto.getDistrict_id());
            cstmt2.setString(2, territorydto.getMandal_id());
            cstmt2.setString(3, fromdate);
            cstmt2.setString(4, todate);
            rs2 = cstmt2.executeQuery();
            cstmt3 = con.prepareCall("{Call SP_Reports_for_SixtyonetoEighteyPercentage_People_Basedon_District_IDandMandal_ID (?,?,?,?)}");
            cstmt3.setString(1, territorydto.getDistrict_id());
            cstmt3.setString(2, territorydto.getMandal_id());
            cstmt3.setString(3, fromdate);
            cstmt3.setString(4, todate);
            rs3 = cstmt3.executeQuery();
            cstmt4 = con.prepareCall("{Call SP_Reports_for_GreaterthanEighteyPercentage_People_Basedon_District_IDandMandal_ID(?,?,?,?)}");
            cstmt4.setString(1, territorydto.getDistrict_id());
            cstmt4.setString(2, territorydto.getMandal_id());
            cstmt4.setString(3, fromdate);
            cstmt4.setString(4, todate);
            rs4 = cstmt4.executeQuery();
            cstmt5 = con.prepareCall("{Call SP_CountofRejectedPersons_Givenby_District_IDandMandal_ID(?,?,?,?)}");
            cstmt5.setString(1, territorydto.getDistrict_id());
            cstmt5.setString(2, territorydto.getMandal_id());
            cstmt5.setString(3, fromdate);
            cstmt5.setString(4, todate);
            rs5 = cstmt5.executeQuery();
            while (rs1.next()) {
                s.add(rs1.getString(2));
            }
            while (rs2.next()) {

                s.add(rs2.getString(2));
            }
            while (rs3.next()) {
                s.add(rs3.getString(2));
            }
            while (rs4.next()) {

                s.add(rs4.getString(2));
            }
            while (rs5.next()) {
                s.add(rs5.getString(2));
            }
            Object str1[] = new String[s.size()];
            str1 = s.toArray();
            String ss[] = new String[str1.length];

            for (int i = 0; i < str1.length; i++) {
                ss[i] = ((String) str1[i]).trim();

            }
            TerritoryDTO arra[] = new TerritoryDTO[ss.length];

            for (int i = 0; i < arra.length; i++) {
                arra[i] = new TerritoryDTO();
            }

            for (int i = 0; i < arra.length; i++) {

                arra[i].setVillage_name(ss[i]);
            }
            rs1 = cstmt1.executeQuery();
            while (rs1.next()) {

                String districtname = rs1.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs1.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setBelowfourtyforphysical(rs1.getString(1));

                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setBelowfourtyforvisual(rs1.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setBelowfourtyforhearing(rs1.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setBelowfourtyformentalretardation(rs1.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setBelowfourtyformentalillness(rs1.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setBelowfourtyformultipledisability(rs1.getString(1));
                }
            }
            rs2 = cstmt2.executeQuery();
            while (rs2.next()) {
                String districtname = rs2.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs2.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setFourtytosixtyforphysical(rs2.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setFourtytosixtyforvisual(rs2.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setFourtytosixtyforhearing(rs2.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setFourtytosixtyformentalretardation(rs2.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setFourtytosixtyformentalillness(rs2.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setFourtytosixtyformultipledisability(rs2.getString(1));
                }
            }
            rs3 = cstmt3.executeQuery();
            while (rs3.next()) {
                String districtname = rs3.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs3.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setSixtytoeightyforphysical(rs3.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setSixtyonetoeightyforvisual(rs3.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setSixtyonetoeightyforhearing(rs3.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setSixtyonetoeightyformentalretardation(rs3.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setSixtyonetoeightyformentalillness(rs3.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setSixtyonetoeightyformultipledisability(rs3.getString(1));
                }
            }
            rs4 = cstmt4.executeQuery();
            while (rs4.next()) {
                String districtname = rs4.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs4.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setAboveeightyoneforphysical(rs4.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setAboveeightyoneforvisual(rs4.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setAboveeightyoneforhearing(rs4.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setAboveeightyoneformentalretardation(rs4.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setAboveeightyoneformentalillness(rs4.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setAboveeightyoneformultipledisability(rs4.getString(1));
                }
            }
            rs5 = cstmt5.executeQuery();
            while (rs5.next()) {
                String districtname = rs5.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                arra[obj].setRejectedpersonscount(rs5.getString(1));
            }


            int LocomotorSubtotal = 0;
            int VisualSubtotal = 0;
            int HearingSubtotal = 0;
            int MentalRetardationSubtotal = 0;
            int MentalIllnessSubtotal = 0;
            int MultipleDisabilitySubtotal = 0;
            int AssessedAndRejected = 0;
            for (int i = 0; i < arra.length; i++) {


                LocomotorSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforphysical())
                        + Integer.parseInt(arra[i].getSixtytoeightyforphysical())
                        + Integer.parseInt(arra[i].getAboveeightyoneforphysical());

                VisualSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforvisual())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyforvisual())
                        + Integer.parseInt(arra[i].getAboveeightyoneforvisual());
                HearingSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforhearing())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyforhearing())
                        + Integer.parseInt(arra[i].getAboveeightyoneforhearing());
                MentalRetardationSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformentalretardation())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformentalretardation())
                        + Integer.parseInt(arra[i].getAboveeightyoneformentalretardation());
                MentalIllnessSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformentalillness())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformentalillness())
                        + Integer.parseInt(arra[i].getAboveeightyoneformentalillness());
                MultipleDisabilitySubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformultipledisability())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformultipledisability())
                        + Integer.parseInt(arra[i].getAboveeightyoneformultipledisability());
                AssessedAndRejected =
                        Integer.parseInt(arra[i].getBelowfourtyforphysical())
                        + Integer.parseInt(arra[i].getBelowfourtyforvisual())
                        + Integer.parseInt(arra[i].getBelowfourtyforhearing())
                        + Integer.parseInt(arra[i].getBelowfourtyformentalretardation())
                        + Integer.parseInt(arra[i].getBelowfourtyformentalillness())
                        + Integer.parseInt(arra[i].getBelowfourtyformultipledisability());
                int totalCount =
                        LocomotorSubtotal + VisualSubtotal + HearingSubtotal
                        + MentalRetardationSubtotal + MentalIllnessSubtotal
                        + MultipleDisabilitySubtotal;

                arra[i].setTotalforphysical(String.valueOf(LocomotorSubtotal));
                arra[i].setTotalforvisual(String.valueOf(VisualSubtotal));
                arra[i].setTotalforhearing(String.valueOf(HearingSubtotal));
                arra[i].setTotalformentalretardation(String.valueOf(MentalRetardationSubtotal));
                arra[i].setTotalformentalillness(String.valueOf(MentalIllnessSubtotal));
                arra[i].setTotalformultipledisability(String.valueOf(MultipleDisabilitySubtotal));
                arra[i].setTotaldisability(String.valueOf(totalCount));
                arra[i].setAssessedandrejectedtotal(String.valueOf(AssessedAndRejected));
            }
            for (int kk = 0; kk < arra.length; kk++) {
                pwdreportforvillage.add(arra[kk]);
            }

            finalvillagereport.add(pwddistrict);

            finalvillagereport.add(mandallist);
            finalvillagereport.add(villagelist);
            finalvillagereport.add(pwdreportforvillage);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillages", "StatusForPWDReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportDAO", "getVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillages", "StatusForPWDReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportDAO", "getVillages");
        } finally {

            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rsdistrict);
            DBConnection.closeResultSet(rsmandal);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs2);
            DBConnection.closeResultSet(rs3);
            DBConnection.closeResultSet(rs4);
            DBConnection.closeResultSet(rs5);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(cstmtv);
            DBConnection.closeStatement(cstmtm);
            DBConnection.closeStatement(cstmtd);
            DBConnection.closeStatement(cstmt1);
            DBConnection.closeStatement(cstmt2);
            DBConnection.closeStatement(cstmt3);
            DBConnection.closeStatement(cstmt4);
            DBConnection.closeStatement(cstmt5);



        }


        return finalvillagereport;
    }

    /**
     * 
     * @param datasource 
     * @param territorydto 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return list
     */
    public ArrayList getHabitations(DataSource ds, TerritoryDTO territorydto)
            throws SADAREMDBException, SQLException {


        Set s = new TreeSet();
        ArrayList habiatationlist = new ArrayList();
        ArrayList pwddistrict = new ArrayList();
        ArrayList mandallist = new ArrayList();
        ArrayList villagelist = new ArrayList();
        ArrayList pwdreportforhabitation = new ArrayList();
        ArrayList finalhabitationreport = new ArrayList();
        Connection con = null;
        Statement stmt = null;
        CallableStatement cstmtd = null;
        CallableStatement cstmtv = null;
        CallableStatement cstmt1 = null;
        CallableStatement cstmt2 = null;
        CallableStatement cstmt3 = null;
        CallableStatement cstmt4 = null;
        CallableStatement cstmt5 = null;
        CallableStatement cstmtm = null;
        CallableStatement cstmth = null;
        ResultSet rsdistrict = null;
        ResultSet rsmandal = null;
        ResultSet rsvillage = null;

        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        try {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(territorydto.getFromdate());
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(territorydto.getTodate());
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();

            cstmth = con.prepareCall("{Call SP_SelectHabitation_Names_for_Reports(?,?,?,?,?)}");
            cstmth.setString(1, territorydto.getDistrict_id());
            cstmth.setString(2, territorydto.getMandal_id());
            cstmth.setString(3, territorydto.getVillage_id());
            cstmth.setString(4, fromdate);
            cstmth.setString(5, todate);
            stmt = con.createStatement();

            rs = cstmth.executeQuery();
            while (rs.next()) {
                TerritoryDTO territoryDTO4 = new TerritoryDTO();
                territoryDTO4.setHabitation_name((rs.getString("Habitation_Name")));
                territoryDTO4.setHabitation_id((rs.getString("Habitation_Id")));
                habiatationlist.add(territoryDTO4);
            }
            cstmtv = con.prepareCall("{Call SP_SelectVillage_Names_for_Reports(?,?,?,?)}");
            cstmtv.setString(1, territorydto.getDistrict_id());
            cstmtv.setString(2, territorydto.getMandal_id());
            cstmtv.setString(3, fromdate);
            cstmtv.setString(4, todate);
            stmt = con.createStatement();

            rsvillage = cstmtv.executeQuery();
            while (rsvillage.next()) {
                TerritoryDTO territoryDTO3 = new TerritoryDTO();
                territoryDTO3.setVillage_id((rsvillage.getString("village_id")));
                territoryDTO3.setVillage_name((rsvillage.getString("village_name")));
                villagelist.add(territoryDTO3);
            }
            cstmtm = con.prepareCall("{Call SP_SelectMandal_Names_for_Reports(?,?,?)}");
            cstmtm.setString(1, territorydto.getDistrict_id());
            cstmtm.setString(2, fromdate);
            cstmtm.setString(3, todate);
            stmt = con.createStatement();

            rsmandal = cstmtm.executeQuery();
            while (rsmandal.next()) {
                TerritoryDTO territoryDTO2 = new TerritoryDTO();
                territoryDTO2.setMandal_id((rsmandal.getString("mandal_id")));
                territoryDTO2.setMandal_name((rsmandal.getString("mandal_name")));
                mandallist.add(territoryDTO2);

            }

            cstmtd = con.prepareCall("{Call SP_SelectDistrict_Names_for_Reports(?,?)}");
            cstmtd.setString(1, fromdate);
            cstmtd.setString(2, todate);
            rsdistrict = cstmtd.executeQuery();
            while (rsdistrict.next()) {
                TerritoryDTO territorydto1 = new TerritoryDTO();
                territorydto1.setDistrict_id((rsdistrict.getString("district_id")));
                territorydto1.setDistrict_name((rsdistrict.getString("district_name")));

                pwddistrict.add(territorydto1);
            }

            cstmt1 = con.prepareCall("{Call SP_Reports_for_LessthanFourtyPercentage_People_Basedon_District_IDandMandal_IDandVillage_ID(?,?,?,?,?)}");
            cstmt1.setString(1, territorydto.getDistrict_id());
            cstmt1.setString(2, territorydto.getMandal_id());
            cstmt1.setString(3, territorydto.getVillage_id());
            cstmt1.setString(4, fromdate);
            cstmt1.setString(5, todate);

            rs1 = cstmt1.executeQuery();


            cstmt2 = con.prepareCall("{Call SP_Reports_for_FourtytoSixtyPercentage_People_Basedon_District_IDandMandal_IDandVillage_ID(?,?,?,?,?)}");
            cstmt2.setString(1, territorydto.getDistrict_id());
            cstmt2.setString(2, territorydto.getMandal_id());
            cstmt2.setString(3, territorydto.getVillage_id());
            cstmt2.setString(4, fromdate);
            cstmt2.setString(5, todate);

            // stmt=con.createStatement();

            rs2 = cstmt2.executeQuery();

            cstmt3 = con.prepareCall("{Call SP_Reports_for_SixtyonetoEighteyPercentage_People_Basedon_District_IDandMandal_IDandVillage_ID(?,?,?,?,?)}");
            cstmt3.setString(1, territorydto.getDistrict_id());
            cstmt3.setString(2, territorydto.getMandal_id());
            cstmt3.setString(3, territorydto.getVillage_id());
            cstmt3.setString(4, fromdate);
            cstmt3.setString(5, todate);
            // stmt=con.createStatement();

            rs3 = cstmt3.executeQuery();

            cstmt4 = con.prepareCall("{Call SP_Reports_for_GreaterthanEighteyPercentage_People_Basedon_District_IDandMandal_IDandVillage_ID(?,?,?,?,?)}");
            cstmt4.setString(1, territorydto.getDistrict_id());
            cstmt4.setString(2, territorydto.getMandal_id());
            cstmt4.setString(3, territorydto.getVillage_id());
            cstmt4.setString(4, fromdate);
            cstmt4.setString(5, todate);
            //  stmt=con.createStatement();

            rs4 = cstmt4.executeQuery();
            cstmt5 = con.prepareCall("{Call SP_CountofRejectedPersons_Givenby_District_IDandMandal_IDandVillage_ID(?,?,?,?,?)}");
            cstmt5.setString(1, territorydto.getDistrict_id());
            cstmt5.setString(2, territorydto.getMandal_id());
            cstmt5.setString(3, territorydto.getVillage_id());
            cstmt5.setString(4, fromdate);
            cstmt5.setString(5, todate);
            rs5 = cstmt5.executeQuery();

            while (rs1.next()) {
                s.add(rs1.getString(2));
            }

            while (rs2.next()) {

                s.add(rs2.getString(2));
            }

            while (rs3.next()) {
                s.add(rs3.getString(2));
            }
            while (rs4.next()) {

                s.add(rs4.getString(2));
            }
            while (rs5.next()) {

                s.add(rs5.getString(2));
            }
            Object str1[] = new String[s.size()];
            str1 = s.toArray();
            String ss[] = new String[str1.length];

            for (int i = 0; i < str1.length; i++) {
                ss[i] = ((String) str1[i]).trim();

            }

            TerritoryDTO arra[] = new TerritoryDTO[ss.length];

            for (int i = 0; i < arra.length; i++) {
                arra[i] = new TerritoryDTO();
            }

            for (int i = 0; i < arra.length; i++) {

                arra[i].setHabitation_name(ss[i]);
            }

            rs1 = cstmt1.executeQuery();
            while (rs1.next()) {

                String districtname = rs1.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs1.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setBelowfourtyforphysical(rs1.getString(1));

                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setBelowfourtyforvisual(rs1.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setBelowfourtyforhearing(rs1.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setBelowfourtyformentalretardation(rs1.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setBelowfourtyformentalillness(rs1.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setBelowfourtyformultipledisability(rs1.getString(1));
                }
            }
            rs2 = cstmt2.executeQuery();
            while (rs2.next()) {
                String districtname = rs2.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs2.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setFourtytosixtyforphysical(rs2.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setFourtytosixtyforvisual(rs2.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setFourtytosixtyforhearing(rs2.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setFourtytosixtyformentalretardation(rs2.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setFourtytosixtyformentalillness(rs2.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setFourtytosixtyformultipledisability(rs2.getString(1));
                }
            }
            rs3 = cstmt3.executeQuery();
            while (rs3.next()) {
                String districtname = rs3.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs3.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setSixtytoeightyforphysical(rs3.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setSixtyonetoeightyforvisual(rs3.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setSixtyonetoeightyforhearing(rs3.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setSixtyonetoeightyformentalretardation(rs3.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setSixtyonetoeightyformentalillness(rs3.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setSixtyonetoeightyformultipledisability(rs3.getString(1));
                }
            }
            rs4 = cstmt4.executeQuery();
            while (rs4.next()) {
                String districtname = rs4.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                String distype = rs4.getString(3);
                if (distype.equals("A.Locomotor/OH")) {
                    arra[obj].setAboveeightyoneforphysical(rs4.getString(1));
                } else if (distype.equals("B.Visual Impairment")) {
                    arra[obj].setAboveeightyoneforvisual(rs4.getString(1));
                } else if (distype.equals("C.Hearing Impairment")) {
                    arra[obj].setAboveeightyoneforhearing(rs4.getString(1));
                } else if (distype.equals("D.Mental Retardation")) {
                    arra[obj].setAboveeightyoneformentalretardation(rs4.getString(1));
                } else if (distype.equals("E.Mental Illness")) {
                    arra[obj].setAboveeightyoneformentalillness(rs4.getString(1));
                } else if (distype.equals("F.Multiple Disability")) {
                    arra[obj].setAboveeightyoneformultipledisability(rs4.getString(1));
                }
            }
            rs5 = cstmt5.executeQuery();
            while (rs5.next()) {
                String districtname = rs5.getString(2);
                int obj = Arrays.binarySearch(ss, districtname.trim());
                arra[obj].setRejectedpersonscount(rs5.getString(1));
            }

            int LocomotorSubtotal = 0;
            int VisualSubtotal = 0;
            int HearingSubtotal = 0;
            int MentalRetardationSubtotal = 0;
            int MentalIllnessSubtotal = 0;
            int MultipleDisabilitySubtotal = 0;
            int AssessedAndRejected = 0;
            for (int i = 0; i < arra.length; i++) {


                LocomotorSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforphysical())
                        + Integer.parseInt(arra[i].getSixtytoeightyforphysical())
                        + Integer.parseInt(arra[i].getAboveeightyoneforphysical());

                VisualSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforvisual())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyforvisual())
                        + Integer.parseInt(arra[i].getAboveeightyoneforvisual());
                HearingSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyforhearing())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyforhearing())
                        + Integer.parseInt(arra[i].getAboveeightyoneforhearing());
                MentalRetardationSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformentalretardation())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformentalretardation())
                        + Integer.parseInt(arra[i].getAboveeightyoneformentalretardation());
                MentalIllnessSubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformentalillness())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformentalillness())
                        + Integer.parseInt(arra[i].getAboveeightyoneformentalillness());
                MultipleDisabilitySubtotal =
                        Integer.parseInt(arra[i].getFourtytosixtyformultipledisability())
                        + Integer.parseInt(arra[i].getSixtyonetoeightyformultipledisability())
                        + Integer.parseInt(arra[i].getAboveeightyoneformultipledisability());
                AssessedAndRejected =
                        Integer.parseInt(arra[i].getBelowfourtyforphysical())
                        + Integer.parseInt(arra[i].getBelowfourtyforvisual())
                        + Integer.parseInt(arra[i].getBelowfourtyforhearing())
                        + Integer.parseInt(arra[i].getBelowfourtyformentalretardation())
                        + Integer.parseInt(arra[i].getBelowfourtyformentalillness())
                        + Integer.parseInt(arra[i].getBelowfourtyformultipledisability());
                int totalCount =
                        LocomotorSubtotal + VisualSubtotal + HearingSubtotal
                        + MentalRetardationSubtotal + MentalIllnessSubtotal
                        + MultipleDisabilitySubtotal;

                arra[i].setTotalforphysical(String.valueOf(LocomotorSubtotal));
                arra[i].setTotalforvisual(String.valueOf(VisualSubtotal));
                arra[i].setTotalforhearing(String.valueOf(HearingSubtotal));
                arra[i].setTotalformentalretardation(String.valueOf(MentalRetardationSubtotal));
                arra[i].setTotalformentalillness(String.valueOf(MentalIllnessSubtotal));
                arra[i].setTotalformultipledisability(String.valueOf(MultipleDisabilitySubtotal));
                arra[i].setTotaldisability(String.valueOf(totalCount));
                arra[i].setAssessedandrejectedtotal(String.valueOf(AssessedAndRejected));
            }
            for (int kk = 0; kk < arra.length; kk++) {
                pwdreportforhabitation.add(arra[kk]);
            }

            finalhabitationreport.add(pwddistrict);

            finalhabitationreport.add(mandallist);
            finalhabitationreport.add(villagelist);
            finalhabitationreport.add(habiatationlist);
            finalhabitationreport.add(pwdreportforhabitation);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitations", "StatusForPWDReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportDAO", "getHabitations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getHabitations", "StatusForPWDReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportDAO", "getHabitations");
        } finally {



            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rsdistrict);
            DBConnection.closeResultSet(rsmandal);
            DBConnection.closeResultSet(rsvillage);
            DBConnection.closeResultSet(rs1);
            DBConnection.closeResultSet(rs2);
            DBConnection.closeResultSet(rs3);
            DBConnection.closeResultSet(rs4);
            DBConnection.closeResultSet(rs5);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(cstmth);
            DBConnection.closeStatement(cstmtv);
            DBConnection.closeStatement(cstmtm);
            DBConnection.closeStatement(cstmtd);
            DBConnection.closeStatement(cstmt1);
            DBConnection.closeStatement(cstmt2);
            DBConnection.closeStatement(cstmt3);
            DBConnection.closeStatement(cstmt4);
            DBConnection.closeStatement(cstmt5);

        }


        return finalhabitationreport;
    }
}
