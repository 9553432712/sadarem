/*
 * VSMSImpl.java
 *
 * Created on January 5, 2009, 11:53 AM
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
import org.bf.disability.bean.VSMSBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.ShowCalculationsDAO;
import org.bf.disability.dto.VsmsScreeningTestDTO;
import org.bf.disability.service.VsmsScreeningTestService;
import org.bf.disability.servicefactory.VsmsServiceFactory;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author kiran_h
 */
public class VSMSImpl extends ShowCalcualationsServiceImpl {

    VSMSBean vsmsBean = new VSMSBean();
    ChronologicalAge dobobj = new ChronologicalAge();
    double month = 0.0;
    int year = 0;
    double mentalage = 0;

    public void populateVSMSCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {

        try {
            VsmsScreeningTestService vsmsscreeningtestservice = VsmsServiceFactory.getVsmsScreeningTestServiceImpl();
            VsmsScreeningTestDTO vsmsScreeningTestDTO = new VsmsScreeningTestDTO();
            ShowCalculationsDAO showCalculationsDAO = new ShowCalculationsDAO();

            vsmsScreeningTestDTO = vsmsscreeningtestservice.getVsmsScreeningTest(dataSource, personcode);
            BeanUtils.copyProperties(vsmsBean, vsmsScreeningTestDTO);
            dobobj = showCalculationsDAO.getChronologicalAge(dataSource, personcode);
            vsmsBean = vsmsCalculationsLogic(vsmsBean);
            vsmsBean.setDateofbirth(dobobj.getDateofbirth());
            vsmsBean.setTodaysdate(dobobj.getTodaysdate());

            vsmsBean.setChronologicalage(dobobj.getChronologicalage());



            vsmsBean = convertToYears(vsmsBean);
            vsmsBean = calculateIQ(vsmsBean);


            request.setAttribute("vsmsBean", vsmsBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateVSMSCalculations", "VSMSImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VSMSImpl", "populateVSMSCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateVSMSCalculations", "VSMSImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VSMSImpl", "populateVSMSCalculations");
        }//end of catch block

    }

    /* -------------THIS IS CALCULATION LOGIC------ */
    public VSMSBean vsmsCalculationsLogic(VSMSBean vSMSBean) throws SADAREMDBException, SQLException {



        try {

            int count = 0;
            int Vsms0to1CoolsLaughs = vSMSBean.getVsms_0to1_CoolsLaughs();

            int vsms0to1Balenceshead = vSMSBean.getVsms_0to1_Balenceshead();
            int Vsms0to1Graphsobject = vSMSBean.getVsms_0to1_Graphsobject();
            int Reachesforfamiliarpersons = vSMSBean.getVsms_0to1_Reachesforfamiliarpersons();
            int Vsms0to1Rolls = vSMSBean.getVsms_0to1_Rolls();
            int Vsms0to1Reachesforobjects = vSMSBean.getVsms_0to1_Reachesforobjects();
            int Vsms0to1Occupies = vSMSBean.getVsms_0to1_Occupies();
            int Vsms0to1Sits = vSMSBean.getVsms_0to1_Sits();
            int Vsms0to1pulls = vSMSBean.getVsms_0to1_Pulls();
            int Vsms0to1Talks = vSMSBean.getVsms_0to1_Talks();
            int Vsms0to1Drinks = vSMSBean.getVsms_0to1_Drinks();
            int Vsms0to1Moves = vSMSBean.getVsms_0to1_Moves();
            int Vsms0to1Grasps = vSMSBean.getVsms_0to1_Grasps();
            int Vsms0to1Demands = vSMSBean.getVsms_0to1_Demands();
            int Vsms0to1Stands = vSMSBean.getVsms_0to1_Stands();
            int Vsms0to1Doesnotdrool = vSMSBean.getVsms_0to1_Doesnotdrool();


            int Vsms0to1Follows = vSMSBean.getVsms_0to1_Follows();

            int count0to1 = Vsms0to1CoolsLaughs + vsms0to1Balenceshead + Vsms0to1Graphsobject + Reachesforfamiliarpersons + Vsms0to1Rolls + Vsms0to1Reachesforobjects + Vsms0to1Occupies + Vsms0to1Sits + Vsms0to1pulls + Vsms0to1Talks + Vsms0to1Drinks + Vsms0to1Moves + Vsms0to1Grasps + Vsms0to1Demands + Vsms0to1Stands + Vsms0to1Doesnotdrool + Vsms0to1Follows;
            vSMSBean.setCount0to1(count0to1);
            int vsms1to2Walks = vSMSBean.getVsms_1to2_Walks();

            int Vsms1to2Marks = vSMSBean.getVsms_1to2_Marks();
            int Vsms1to2Masticates = vSMSBean.getVsms_1to2_Masticates();
            int Vsms1to2Pulls = vSMSBean.getVsms_1to2_Pulls();
            int vsms1to2Transfers = vSMSBean.getVsms_1to2_Transfers();
            int Vsms1to2Overcomessimpleobstacles = vSMSBean.getVsms_1to2_Overcomessimpleobstacles();
            int Vsms1to2Fetches = vSMSBean.getVsms_1to2_Fetches();
            int Vsms1to2Drinks = vSMSBean.getVsms_1to2_Drinks();
            int vsms1to2Walkswithoutsupport = vSMSBean.getVsms_1to2_Walkswithoutsupport();
            int Vsms1to2Plays = vSMSBean.getVsms_1to2_Plays();
            int Vsms1to2Eats = vSMSBean.getVsms_1to2_Eats();

            int Vsms1to2Goes = vSMSBean.getVsms_1to2_Goes();

            int Vsms1to2Discriminates = vSMSBean.getVsms_1to2_Discriminates();






            int vsms1to2Usesnames = vSMSBean.getVsms_1to2_Usesnames();

            int vsms1to2Walksupstairs = vSMSBean.getVsms_1to2_Walksupstairs();

            int vsms1to2Unwarps = vSMSBean.getVsms_1to2_Unwarps();
            int vsms1to2Talks = vSMSBean.getVsms_1to2_Talks();
            int count1to2 = vsms1to2Walks + Vsms1to2Marks + Vsms1to2Masticates + Vsms1to2Pulls + vsms1to2Transfers + Vsms1to2Overcomessimpleobstacles + Vsms1to2Fetches + Vsms1to2Drinks + vsms1to2Walkswithoutsupport + Vsms1to2Plays + Vsms1to2Eats + Vsms1to2Goes + Vsms1to2Discriminates + vsms1to2Usesnames + vsms1to2Walksupstairs + vsms1to2Unwarps + vsms1to2Talks;
            vSMSBean.setCount1to2(count1to2);
            int vsms2to3Signals = vSMSBean.getVsms_2to3_Signals();

            int vsms2to3Initiates = vSMSBean.getVsms_2to3_Initiates();
            int vsms2to3Removesshirt = vSMSBean.getVsms_2to3_Removesshirt();
            int vsms2to3Eatswithspoon = vSMSBean.getVsms_2to3_Eatswithspoon();
            int vsms2to3Getsdrink = vSMSBean.getVsms_2to3_Getsdrink();
            int vsms2to3Dries = vSMSBean.getVsms_2to3_Dries();
            int vsms2to3Avoids = vSMSBean.getVsms_2to3_Avoids();
            int vsms2to3Putsonshirt = vSMSBean.getVsms_2to3_Putsonshirt();
            int vsms2to3Candopaperfolding = vSMSBean.getVsms_2to3_Candopaperfolding();
            int vsms2to3Relates = vSMSBean.getVsms_2to3_Relates();

            int count2to3 = vsms2to3Signals + vsms2to3Initiates + vsms2to3Removesshirt + vsms2to3Eatswithspoon + vsms2to3Getsdrink + vsms2to3Dries + vsms2to3Avoids + vsms2to3Putsonshirt + vsms2to3Candopaperfolding + vsms2to3Relates;
            vSMSBean.setCount2to3(count2to3);
            int vsms3to4Walksdownsstairs = vSMSBean.getVsms_3to4_Walksdownsstairs();
            int vsms3to4Playscooperatively = vSMSBean.getVsms_3to4_Playscooperatively();
            int vsms3to4Buttons = vSMSBean.getVsms_3to4_Buttons();
            int vsms3to4Helps = vSMSBean.getVsms_3to4_Helps();
            int vsms3to4Performs = vSMSBean.getVsms_3to4_Performs();
            int vsms3to4Washes = vSMSBean.getVsms_3to4_Washes();
            int count3to4 = vsms3to4Walksdownsstairs + vsms3to4Playscooperatively + vsms3to4Buttons + vsms3to4Helps + vsms3to4Performs + vsms3to4Washes;
            vSMSBean.setCount3to4(count3to4);

            int vsms4to5Cares = vSMSBean.getVsms_4to5_Cares();
            int vsms4to5Prints = vSMSBean.getVsms_4to5_Prints();
            int vsms4to5Goesaboutneighbourhood = vSMSBean.getVsms_4to5_Goesaboutneighbourhood();
            int vsms4to5Dresses = vSMSBean.getVsms_4to5_Dresses();
            int vsms4to5Usespencil = vSMSBean.getVsms_4to5_Usespencil();
            int vsms4to5Playscompetitive = vSMSBean.getVsms_4to5_Playscompetitive();
            int count4to5 = vsms4to5Cares + vsms4to5Prints + vsms4to5Goesaboutneighbourhood + vsms4to5Dresses + vsms4to5Usespencil + vsms4to5Playscompetitive;
            vSMSBean.setCount4to5(count4to5);

            int vsms5to6Useshoops = vSMSBean.getVsms_5to6_Useshoops();
            int vsms5to6Printssimplewords = vSMSBean.getVsms_5to6_Printssimplewords();
            int vsms5to6Playssimplegames = vSMSBean.getVsms_5to6_Playssimplegames();
            int vsms5to6trusted = vSMSBean.getVsms_5to6_trusted();
            int vsms5to6Goestoschool = vSMSBean.getVsms_5to6_Goestoschool();

            int count5to6 = vsms5to6Useshoops + vsms5to6Printssimplewords + vsms5to6Playssimplegames + vsms5to6trusted + vsms5to6Goestoschool;
            vSMSBean.setCount5to6(count5to6);
            int vsms6to7Mixes = vSMSBean.getVsms_6to7_Mixes();
            int vsms6to7Usespencilorchalk = vSMSBean.getVsms_6to7_Usespencilorchalk();
            int vsms6to7Batches = vSMSBean.getVsms_6to7_Batches();
            int vsms6to7Goestobed = vSMSBean.getVsms_6to7_Goestobed();

            int count6to7 = vsms6to7Mixes + vsms6to7Usespencilorchalk + vsms6to7Batches + vsms6to7Goestobed;
            vSMSBean.setCount6to7(count6to7);
            int vsms7to8Candifferentiatebetween = vSMSBean.getVsms_7to8_Candifferentiatebetween();
            int vsms7to8Helps = vSMSBean.getVsms_7to8_Helps();
            int vsms7to8Understands = vSMSBean.getVsms_7to8_Understands();
            int vsms7to8participates = vSMSBean.getVsms_7to8_Participates();
            int vsms7to8Combs = vSMSBean.getVsms_7to8_Combs();
            int count7to8 = vsms7to8Candifferentiatebetween + vsms7to8Helps + vsms7to8Understands + vsms7to8participates + vsms7to8Combs;
            vSMSBean.setCount7to8(count7to8);
            int vsms8to9Usestools = vSMSBean.getVsms_8to9_Usestools();
            int vsms8to9routinehouseholdtasks = vSMSBean.getVsms_8to9_routinehouseholdtasks();
            int vsms8to9Readsonowninitiative = vSMSBean.getVsms_8to9_Readsonowninitiative();
            int vsms8to9Batchesselfunaided = vSMSBean.getVsms_8to9_Batchesselfunaided();
            int count8to9 = vsms8to9Usestools + vsms8to9routinehouseholdtasks + vsms8to9Readsonowninitiative + vsms8to9Batchesselfunaided;
            vSMSBean.setCount8to9(count8to9);
            int vsms9to10Caresforself = vSMSBean.getVsms_9to10_Caresforself();
            int vsms9to10Makesminorpurchases = vSMSBean.getVsms_9to10_Makesminorpurchases();
            int vsms9to10Goesabouthome = vSMSBean.getVsms_9to10_Goesabouthome();
            int count9to10 = vsms9to10Caresforself + vsms9to10Makesminorpurchases + vsms9to10Goesabouthome;
            vSMSBean.setCount9to10(count9to10);

            int vsms10to11Distinguishes = vSMSBean.getVsms_10to11_Distinguishes();
            int vsms10to11Makesindependentchoice = vSMSBean.getVsms_10to11_Makesindependentchoice();
            int vsms10to11smallremunerativework = vSMSBean.getVsms_10to11_smallremunerativework();
            int vsms10to11Follows = vSMSBean.getVsms_10to11_Follows();
            int count10to11 = vsms10to11Distinguishes + vsms10to11Makesindependentchoice + vsms10to11smallremunerativework + vsms10to11Follows;
            vSMSBean.setCount10to11(count10to11);
            int vsms11to12simplecreative = vSMSBean.getVsms_11to12_simplecreative();
            int vsms11to12lefttocare = vSMSBean.getVsms_11to12_lefttocare();
            int vsms11to12Enjoys = vSMSBean.getVsms_11to12_Enjoys();
            int count11to12 = vsms11to12simplecreative + vsms11to12lefttocare + vsms11to12Enjoys;
            vSMSBean.setCount11to12(count11to12);
            int vsms12to15Playsdifficult = vSMSBean.getVsms_12to15_Playsdifficult();
            int vsms12to15Exercisescomplete = vSMSBean.getVsms_12to15_Exercisescomplete();
            int vsms12to15Buys = vSMSBean.getVsms_12to15_Buys();
            int vsms12to15Engages = vSMSBean.getVsms_12to15_Engages();
            int vsms12to15Performs = vSMSBean.getVsms_12to15_Performs();
            int count12to15 = vsms12to15Playsdifficult + vsms12to15Exercisescomplete + vsms12to15Buys + vsms12to15Engages + vsms12to15Performs;
            vSMSBean.setCount12to15(count12to15);

            count = count0to1 + count1to2 + count2to3 + count3to4 + count4to5 + count5to6 + count6to7 + count7to8 + count8to9 + count9to10 + count10to11 + count11to12 + count12to15;





            switch (count) {
                case 1:
                    month = 0.7;
                    break;
                case 2:
                    month = 1.4;
                    break;
                case 3:
                    month = 2.1;
                    break;
                case 4:
                    month = 2.8;
                    break;
                case 5:
                    month = 3.5;
                    break;
                case 6:
                    month = 4.2;
                    break;
                case 7:
                    month = 4.9;
                    break;
                case 8:
                    month = 5.6;
                    break;
                case 9:
                    month = 6.3;
                    break;
                case 10:
                    month = 7.0;
                    break;
                case 11:
                    month = 7.7;
                    break;
                case 12:
                    month = 8.4;
                    break;
                case 13:
                    month = 9.1;
                    break;
                case 14:
                    month = 9.8;
                    break;
                case 15:
                    month = 10.6;
                    break;
                case 16:
                    month = 11.3;
                    break;
                case 17:
                    month = 12.0;
                    break;

                case 18:
                    year = 1;
                    month = 0.4;
                    break;

                case 19:
                    year = 1;
                    month = 1.4;
                    break;
                case 20:
                    year = 1;
                    month = 2.1;
                    break;
                case 21:
                    year = 1;
                    month = 2.8;
                    break;
                case 22:
                    year = 1;
                    month = 3.5;
                    break;
                case 23:
                    year = 1;
                    month = 4.2;
                    break;
                case 24:
                    year = 1;
                    month = 4.9;
                    break;
                case 25:
                    year = 1;
                    month = 5.6;
                    break;
                case 26:
                    year = 1;
                    month = 6.3;
                    break;
                case 27:
                    year = 1;
                    month = 7.0;
                    break;
                case 28:
                    year = 1;
                    month = 7.7;
                    break;
                case 29:
                    year = 1;
                    month = 8.4;
                    break;
                case 30:
                    year = 1;
                    month = 9.2;
                    break;
                case 31:
                    year = 1;
                    month = 9.8;
                    break;
                case 32:
                    year = 1;
                    month = 10.6;
                    break;
                case 33:
                    year = 1;
                    month = 11.3;
                    break;
                case 34:
                    year = 1;
                    month = 12.0;
                    break;
                case 35:
                    year = 2;
                    month = 1.2;
                    break;
                case 36:
                    year = 2;
                    month = 2.4;
                    break;
                case 37:
                    year = 2;
                    month = 3.6;
                    break;
                case 38:
                    year = 2;
                    month = 4.8;
                    break;
                case 39:
                    year = 2;
                    month = 6.0;
                    break;
                case 40:
                    year = 2;
                    month = 7.2;
                    break;
                case 41:
                    year = 2;
                    month = 8.4;
                    break;
                case 42:
                    year = 2;
                    month = 9.6;
                    break;
                case 43:
                    year = 2;
                    month = 10.8;
                    break;
                case 44:
                    year = 2;
                    month = 12.0;
                    break;
                case 45:
                    year = 3;
                    month = 2;
                    break;
                case 46:
                    year = 3;
                    month = 4;
                    break;
                case 47:
                    year = 3;
                    month = 6;
                    break;
                case 48:
                    year = 3;
                    month = 8;
                    break;
                case 49:
                    year = 3;
                    month = 10;
                    break;
                case 50:
                    year = 3;
                    month = 12;
                    break;
                case 51:
                    year = 4;
                    month = 2;
                    break;
                case 52:
                    year = 4;
                    month = 4;
                    break;
                case 53:
                    year = 4;
                    month = 6;
                    break;
                case 54:
                    year = 4;
                    month = 8;
                    break;
                case 55:
                    year = 4;
                    month = 10;
                    break;
                case 56:
                    year = 4;
                    month = 12;
                    break;
                case 57:
                    year = 5;
                    month = 2.4;
                    break;
                case 58:
                    year = 5;
                    month = 4.8;
                    break;
                case 59:
                    year = 5;
                    month = 7.2;
                    break;
                case 60:
                    year = 5;
                    month = 9.6;
                    break;
                case 61:
                    year = 5;
                    month = 12.0;
                    break;
                case 62:
                    year = 6;
                    month = 3;
                    break;
                case 63:
                    year = 6;
                    month = 6;
                    break;
                case 64:
                    year = 6;
                    month = 9;
                    break;
                case 65:
                    year = 6;
                    month = 12;
                    break;
                case 66:
                    year = 7;
                    month = 2.4;
                    break;
                case 67:
                    year = 7;
                    month = 4.8;
                    break;
                case 68:
                    year = 7;
                    month = 7.2;
                    break;
                case 69:
                    year = 7;
                    month = 9.6;
                    break;
                case 70:
                    year = 7;
                    month = 12.0;
                    break;
                case 71:
                    year = 8;
                    month = 3;
                    break;
                case 72:
                    year = 8;
                    month = 6;
                    break;
                case 73:
                    year = 8;
                    month = 9;
                    break;
                case 74:
                    year = 8;
                    month = 12;
                    break;
                case 75:
                    year = 9;
                    month = 4;
                    break;
                case 76:
                    year = 9;
                    month = 8;
                    break;
                case 77:
                    year = 9;
                    month = 12;
                    break;
                case 78:
                    year = 10;
                    month = 3;
                    break;
                case 79:
                    year = 10;
                    month = 6;
                    break;
                case 80:
                    year = 10;
                    month = 9;
                    break;
                case 81:
                    year = 10;
                    month = 12;
                    break;
                case 82:
                    year = 11;
                    month = 4;
                    break;
                case 83:
                    year = 11;
                    month = 8;
                    break;
                case 84:
                    year = 11;
                    month = 12;
                    break;
                case 85:
                    year = 12;
                    month = 7.2;
                    break;
                case 86:
                    year = 12;
                    month = 14.4;
                    break;
                case 87:
                    year = 12;
                    month = 21.6;
                    break;
                case 88:
                    year = 12;
                    month = 28.8;
                    break;
                case 89:
                    year = 12;
                    month = 36.0;
                    break;



            }
            vSMSBean.setYear(year);
            vSMSBean.setMonth(month);
            vSMSBean.setTotalcount(count);

        } //end of try block
        catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }//end of catch block

        return vSMSBean;
    }  /* end of calculation logic  */


    public VSMSBean convertToYears(VSMSBean vsmsBean) {
        mentalage = (double) year + month / 12;
        String str = new StringBuffer(year + " + " + month + " / 12 = " + mentalage).toString();
        vsmsBean.setMentalage(str);
        return vsmsBean;
    }

    public VSMSBean calculateIQ(VSMSBean vsmsBean) {
        double iq;
        double chronologicalage = Double.parseDouble(vsmsBean.getChronologicalage());
        if (chronologicalage < 15) {
            iq = (mentalage / chronologicalage) * 100;
            vsmsBean.setIq(new StringBuffer("( " + mentalage + " / " + vsmsBean.getChronologicalage() + " ) * 100 = " + iq));
        } else {
            chronologicalage = 15;
            iq = (mentalage / chronologicalage) * 100;
            StringBuffer temp = new StringBuffer();
            vsmsBean.setIq(new StringBuffer("As chronological age is >15,\n"));
            vsmsBean.setIq(vsmsBean.getIq().append("15 will be taken as Chronological age.\n"));
            temp = vsmsBean.getIq();
            temp.append(new StringBuffer("( " + mentalage + " / " + chronologicalage + " ) * 100 = " + iq));
        }
        return vsmsBean;
    }
}   /* end of class  */
