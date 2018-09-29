/*
 * BKTImpl.java
 *
 * Created on January 5, 2009, 11:55 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.util;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.BKTBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.ShowCalculationsDAO;
import org.bf.disability.dto.MRBinetKamatDetailedTestDTO;
import org.bf.disability.service.MentalRetardationService;
import org.bf.disability.servicefactory.MentalRetardationServiceFactory;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author kiran_h
 */
public class BKTImpl extends ShowCalcualationsServiceImpl {

    ChronologicalAge cageobj = new ChronologicalAge();
    ShowCalculationsDAO showCalculationsDAO = new ShowCalculationsDAO();
    MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
    BKTBean bKTBean = new BKTBean();

    public void populateBKTCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {
            MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO = new MRBinetKamatDetailedTestDTO();
            mRBinetKamatDetailedTestDTO = mentalRetardationService.getMRBinetKamatDetailedTestDetails(dataSource, personcode);
            cageobj = showCalculationsDAO.getChronologicalAge(dataSource, personcode);
            BeanUtils.copyProperties(bKTBean, mRBinetKamatDetailedTestDTO);
            bKTBean.setDateofbirth(cageobj.getDateofbirth());
            bKTBean.setTodaysdate(cageobj.getTodaysdate());
            bKTBean.setChronologicalage(cageobj.getChronologicalage());
            bKTBean = bktCalculationsLogic(bKTBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateBKTCalculations", "BKTImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "BKTImpl", "populateBKTCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateBKTCalculations", "BKTImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "BKTImpl", "populateBKTCalculations");
        } //end of catch block
        request.setAttribute("bKTBean", bKTBean);

    }

    public BKTBean bktCalculationsLogic(BKTBean bKTBean) throws SADAREMDBException, SQLException {
        try {
            int agelevel3_item1 = bKTBean.getAgeLevel3_Item1();



            int[] totalOneDimArry = new int[]{bKTBean.getAgeLevel3_Item1(), bKTBean.getAgeLevel4_Item1(), bKTBean.getAgeLevel5_Item1(), bKTBean.getAgeLevel6_Item1(), bKTBean.getAgeLevel7_Item1(), bKTBean.getAgeLevel8_Item1(), bKTBean.getAgeLevel9_Item1(), bKTBean.getAgeLevel10_Item1(), bKTBean.getAgeLevel12_Item1(), bKTBean.getAgeLevel14_Item1(), bKTBean.getAgeLevel16_Item1(), bKTBean.getAgeLevel19_Item1(), bKTBean.getAgeLevel22_Item1(),
                bKTBean.getAgeLevel3_Item2(), bKTBean.getAgeLevel4_Item2(), bKTBean.getAgeLevel5_Item2(), bKTBean.getAgeLevel6_Item2(), bKTBean.getAgeLevel7_Item2(), bKTBean.getAgeLevel8_Item2(), bKTBean.getAgeLevel9_Item2(), bKTBean.getAgeLevel10_Item2(), bKTBean.getAgeLevel12_Item2(), bKTBean.getAgeLevel14_Item2(), bKTBean.getAgeLevel16_Item2(), bKTBean.getAgeLevel19_Item2(), bKTBean.getAgeLevel22_Item2(),
                bKTBean.getAgeLevel3_Item3(), bKTBean.getAgeLevel4_Item3(), bKTBean.getAgeLevel5_Item3(), bKTBean.getAgeLevel6_Item3(), bKTBean.getAgeLevel7_Item3(), bKTBean.getAgeLevel8_Item3(), bKTBean.getAgeLevel9_Item3(), bKTBean.getAgeLevel10_Item3(), bKTBean.getAgeLevel12_Item3(), bKTBean.getAgeLevel14_Item3(), bKTBean.getAgeLevel16_Item3(), bKTBean.getAgeLevel19_Item3(), bKTBean.getAgeLevel22_Item3(),
                bKTBean.getAgeLevel3_Item4(), bKTBean.getAgeLevel4_Item4(), bKTBean.getAgeLevel5_Item4(), bKTBean.getAgeLevel6_Item4(), bKTBean.getAgeLevel7_Item4(), bKTBean.getAgeLevel8_Item4(), bKTBean.getAgeLevel9_Item4(), bKTBean.getAgeLevel10_Item4(), bKTBean.getAgeLevel12_Item4(), bKTBean.getAgeLevel14_Item4(), bKTBean.getAgeLevel16_Item4(), bKTBean.getAgeLevel19_Item4(), bKTBean.getAgeLevel22_Item4(),
                bKTBean.getAgeLevel3_Item5(), bKTBean.getAgeLevel4_Item5(), bKTBean.getAgeLevel5_Item5(), bKTBean.getAgeLevel6_Item5(), bKTBean.getAgeLevel7_Item5(), bKTBean.getAgeLevel8_Item5(), bKTBean.getAgeLevel9_Item5(), bKTBean.getAgeLevel10_Item5(), bKTBean.getAgeLevel12_Item5(), bKTBean.getAgeLevel14_Item5(), bKTBean.getAgeLevel16_Item5(), bKTBean.getAgeLevel19_Item5(), bKTBean.getAgeLevel22_Item5(),
                bKTBean.getAgeLevel3_Item6(), bKTBean.getAgeLevel4_Item6(), bKTBean.getAgeLevel5_Item6(), bKTBean.getAgeLevel6_Item6(), bKTBean.getAgeLevel7_Item6(), bKTBean.getAgeLevel8_Item6(), bKTBean.getAgeLevel9_Item6(), bKTBean.getAgeLevel10_Item6(), bKTBean.getAgeLevel12_Item6(), bKTBean.getAgeLevel14_Item6(), bKTBean.getAgeLevel16_Item6(), bKTBean.getAgeLevel19_Item6(), bKTBean.getAgeLevel22_Item6(),
                bKTBean.getAgeLevel3_ItemAlt1(), bKTBean.getAgeLevel4_ItemAlt1(), bKTBean.getAgeLevel5_ItemAlt1(), bKTBean.getAgeLevel6_ItemAlt1(), bKTBean.getAgeLevel7_ItemAlt1(), bKTBean.getAgeLevel8_ItemAlt1(), bKTBean.getAgeLevel9_ItemAlt1(), bKTBean.getAgeLevel10_ItemAlt1(), bKTBean.getAgeLevel12_ItemAlt1(), bKTBean.getAgeLevel14_ItemAlt1(), bKTBean.getAgeLevel16_ItemAlt1(), bKTBean.getAgeLevel19_ItemAlt1(), bKTBean.getAgeLevel22_ItemAlt1(),
                bKTBean.getAgeLevel3_ItemAlt2(), bKTBean.getAgeLevel4_ItemAlt2(), bKTBean.getAgeLevel5_ItemAlt2(), bKTBean.getAgeLevel6_ItemAlt2(), bKTBean.getAgeLevel7_ItemAlt2(), bKTBean.getAgeLevel8_ItemAlt2(), bKTBean.getAgeLevel9_ItemAlt2(), bKTBean.getAgeLevel10_ItemAlt2(), bKTBean.getAgeLevel12_ItemAlt2(), bKTBean.getAgeLevel14_ItemAlt2(), bKTBean.getAgeLevel16_ItemAlt2(), bKTBean.getAgeLevel19_ItemAlt2(), bKTBean.getAgeLevel22_ItemAlt2(),
                bKTBean.getAgeLevel3_ItemAlt3(), bKTBean.getAgeLevel4_ItemAlt3(), bKTBean.getAgeLevel5_ItemAlt3(), bKTBean.getAgeLevel6_ItemAlt3(), bKTBean.getAgeLevel7_ItemAlt3(), bKTBean.getAgeLevel8_ItemAlt3(), bKTBean.getAgeLevel9_ItemAlt3(), bKTBean.getAgeLevel10_ItemAlt3(), bKTBean.getAgeLevel12_ItemAlt3(), bKTBean.getAgeLevel14_ItemAlt3(), bKTBean.getAgeLevel16_ItemAlt3(), bKTBean.getAgeLevel19_ItemAlt3(), bKTBean.getAgeLevel22_ItemAlt3()};

            int[][] totalTwoDimArr = new int[9][13];
            int row = 0;
            int col = 0;


            for (int i = 0; i < totalOneDimArry.length; i++) {
                if (i == 0) {
                } else if (i % 13 == 0) {
                    row++;
                    col = 0;
                } else {
                    col++;
                }
                totalTwoDimArr[row][col] = totalOneDimArry[i];
            }
//        for(int i=0;i<9;i++){
//            for(int j=0;j<13;j++)
//        }

            int mentalAgeFromChecks = 0;
            int mentalAgeTotalInMonths = 0;
            int mentalAgeYears = 0;
            int mentalAgeMonths = 0;
            double ca = 0;
            double ma = 0;
            double iq = 0;
            for (int i = 0; i < totalOneDimArry.length; i++) {
                mentalAgeFromChecks = mentalAgeFromChecks + totalOneDimArry[i];
            }

            mentalAgeTotalInMonths = 24 + mentalAgeFromChecks;
            mentalAgeYears = mentalAgeTotalInMonths / 12;
            mentalAgeMonths = mentalAgeTotalInMonths % 12;

            bKTBean.setMentalagefromchecks(mentalAgeFromChecks);
            bKTBean.setTotalmentalageinmonths(mentalAgeTotalInMonths);
            bKTBean.setMentalageinyears(mentalAgeYears);
            bKTBean.setMentalageinmonths(mentalAgeMonths);


            int indexForBasalAge = 0;
            int flag = 0;
            for (int i = 0; i <= 13; i++) {
                if (i == 13) {
                    indexForBasalAge = i;
                    break;
                } else {
                    flag = checkCloumnForAllChecked(i, totalTwoDimArr);
                }
                if (flag == 0) {
                    indexForBasalAge = i;
                    break;
                }
            }
            int basalAge = 0;
            switch (indexForBasalAge) {
                case 1:
                    basalAge = 3;
                    break;
                case 2:
                    basalAge = 4;
                    break;
                case 3:
                    basalAge = 5;
                    break;
                case 4:
                    basalAge = 6;
                    break;
                case 5:
                    basalAge = 7;
                    break;
                case 6:
                    basalAge = 8;
                    break;
                case 7:
                    basalAge = 9;
                    break;
                case 8:
                    basalAge = 10;
                    break;
                case 9:
                    basalAge = 12;
                    break;
                case 10:
                    basalAge = 14;
                    break;
                case 11:
                    basalAge = 16;
                    break;
                case 12:
                    basalAge = 19;
                    break;
                case 13:
                    basalAge = 22;
                    break;
            }


            int indexForTerminalAge = -1;
            for (int i = 0; i <= 13; i++) {
                if (i == 13) {
                    indexForTerminalAge = i;
                    break;
                } else {
                    flag = checkCloumnForAllUnChecked(i, totalTwoDimArr);
                }
                if (flag == 1 && i == 0) {
                    break;
                } else if (flag == 1) {
                    indexForTerminalAge = i;
                    break;
                }

            }
            int terminalAge = 0;
            switch (indexForTerminalAge) {
                case 0:
                    terminalAge = 3;
                    break;
                case 1:
                    terminalAge = 4;
                    break;
                case 2:
                    terminalAge = 5;
                    break;
                case 3:
                    terminalAge = 6;
                    break;
                case 4:
                    terminalAge = 7;
                    break;
                case 5:
                    terminalAge = 8;
                    break;
                case 6:
                    terminalAge = 9;
                    break;
                case 7:
                    terminalAge = 10;
                    break;
                case 8:
                    terminalAge = 12;
                    break;
                case 9:
                    terminalAge = 14;
                    break;
                case 10:
                    terminalAge = 16;
                    break;
                case 11:
                    terminalAge = 19;
                    break;
                case 12:
                    terminalAge = 22;
                    break;
            }

            bKTBean.setBasalage(basalAge);
            bKTBean.setTerminalage(terminalAge);


            ca = Double.parseDouble(bKTBean.getChronologicalage());
            ma = convertToYears(mentalAgeYears, mentalAgeMonths);
            if (ca < 16) {
                iq = calculateIQ(ma, ca);
            } else {
                bKTBean.setChronologicalageflag(true);
                ca = 16;
                iq = calculateIQ(ma, ca);
            }
        } //end of try block
        catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        } //end of catch block
        return bKTBean;
    }

    public int checkCloumnForAllChecked(int column, int[][] totalTwoDimArr) {
        int countForAllChecked = 0;
        int flag = 0;

        for (int i = 0; i < 9; i++) {
            countForAllChecked = countForAllChecked + totalTwoDimArr[i][column];
        }
        if (countForAllChecked == 12) {
            flag = 1;
        }
        if (countForAllChecked == 24) {
            flag = 1;
        }
        if (countForAllChecked == 36) {
            flag = 1;
        }
        return flag;
    }

    public int checkCloumnForAllUnChecked(int column, int[][] totalTwoDimArr) {
        int countForAllUnChecked = 0;
        int flag = 0;

        for (int i = 0; i < 9; i++) {
            countForAllUnChecked = countForAllUnChecked + totalTwoDimArr[i][column];
        }
        if (countForAllUnChecked == 0) {
            flag = 1;
        }

        return flag;
    }

    public double convertToYears(int ageinyears, int ageinmonths) {
        StringBuffer mentalagebuffer = new StringBuffer();
        double years = ageinyears + (ageinmonths) / 12;
        mentalagebuffer.append(ageinyears + "+" + "(" + ageinmonths + ")/12");
        mentalagebuffer.append("=" + years);
        bKTBean.setMentalagebuffer(mentalagebuffer);
        return years;
    }

    public double calculateIQ(double ma, double ca) {
        StringBuffer iqbuffer = new StringBuffer();
        double iq = (ma / ca) * 100;
        iqbuffer.append("(" + ma + "/" + ca + ")x100");
        iqbuffer.append("=" + iq);
        bKTBean.setIqbuffer(iqbuffer);
        return iq;
    }
}
