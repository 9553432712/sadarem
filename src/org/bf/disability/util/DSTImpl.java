/*
 * DSTImpl.java
 *
 * Created on January 5, 2009, 10:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.util;

import java.sql.SQLException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.DSTBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.ShowCalculationsDAO;
import org.bf.disability.dto.MRDevelopmentalScreeningTestDTO;
import org.bf.disability.service.MRDevelopmentalScreeningTestService;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author kiran_h
 */
public class DSTImpl extends ShowCalcualationsServiceImpl {

    ChronologicalAge cageobj = new ChronologicalAge();
    double years = 0, months = 0;
    double mentalage = 0;

    public void populateDSTCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO = new MRDevelopmentalScreeningTestDTO();
        DSTBean dstBean = new DSTBean();
        ShowCalculationsDAO showCalculationsDAO = new ShowCalculationsDAO();

        Date dob = null;
        Date todaysdate = null;

        try {
            mrDevelopmentalScreeningTestDTO.setPersoncode(personcode);
            MRDevelopmentalScreeningTestService mrDevelopmentalScreeningTestService = new MRDevelopmentalScreeningTestService();
            mrDevelopmentalScreeningTestDTO = mrDevelopmentalScreeningTestService.selectMRDevelopmentalScreeningTestDetails(dataSource, mrDevelopmentalScreeningTestDTO);
            cageobj = showCalculationsDAO.getChronologicalAge(dataSource, personcode);






            dstBean.setDateofbirth(cageobj.getDateofbirth());
            dstBean.setTodaysdate(cageobj.getTodaysdate());
            dstBean.setChronologicalage(cageobj.getChronologicalage());

            BeanUtils.copyProperties(dstBean, mrDevelopmentalScreeningTestDTO);
            calculationPartforMRDevleopmentalScreeningTest(dstBean);

            dstBean = convertToYears(dstBean);
            dstBean = calculateIQ(dstBean);
            request.setAttribute("dstBean", dstBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateDSTCalculations", "DSTImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DSTImpl", "populateDSTCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateDSTCalculations", "DSTImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DSTImpl", "populateDSTCalculations");
        } //end of catch block


    }

    public DSTBean dstCalculationsLogic(DSTBean dSTBean) throws SADAREMDBException, SQLException {

        return null;
    }

    public int calculationPartforMRDevleopmentalScreeningTest(DSTBean dstBean) {
        int threeMonths = dstBean.getDst_birth_cry_present()
                + dstBean.getDst_equal_bilateral_movements()
                + dstBean.getDst_responds_to_bell()
                + dstBean.getDst_vocalises_sounds()
                + dstBean.getDst_smiles_spontaneously()
                + dstBean.getDst_eyes_following_moving_object()
                + dstBean.getDst_head_steady();
        if (threeMonths > 0) {

            threeMonths = daysWith7Fields(threeMonths);
            dstBean.setThreemonths(threeMonths);
            dstBean.setTotaldst(dstBean.getTotaldst().append(threeMonths + "+"));

        }

        int sixMonths = dstBean.getDst_reaches_for_objects()
                + dstBean.getDst_laughs_loud()
                + dstBean.getDst_recognises_mother()
                + dstBean.getDst_vocalises_for_pleasure_or_babble()
                + dstBean.getDst_carries_objects_to_mouth()
                + dstBean.getDst_rolls_over();
        if (sixMonths > 0) {
            sixMonths = daysWith6Fields(sixMonths);
            dstBean.setSixmonths(sixMonths);
            dstBean.setTotaldst(dstBean.getTotaldst().append(sixMonths + "+"));
        }

        int nineMonths = dstBean.getDst_imitates_speech_sounds()
                + dstBean.getDst_sits_by_self()
                + dstBean.getDst_thumb_finger_grasp()
                + dstBean.getDst_shows_curiousity();
        if (nineMonths > 0) {
            nineMonths = daysWith4Fields(nineMonths);
            dstBean.setNinemonths(nineMonths);
            dstBean.setTotaldst(dstBean.getTotaldst().append(nineMonths + "+"));
        }


        int oneYear = dstBean.getDst_says_three_words()
                + dstBean.getDst_stands_alone_well()
                + dstBean.getDst_follows_simple_instructions()
                + dstBean.getDst_cooperates_for_dressing();
        if (oneYear > 0) {
            oneYear = daysWith4Fields(oneYear);
            dstBean.setOneyear(oneYear);
            dstBean.setTotaldst(dstBean.getTotaldst().append(oneYear + "+"));
        }

        int oneAndHalfYears = dstBean.getDst_many_intelligible_words()
                + dstBean.getDst_walks_runs_well()
                + dstBean.getDst_indicates_wants()
                + dstBean.getDst_scribbles_spontaneously();
        if (oneAndHalfYears > 0) {
            oneAndHalfYears = daysAndMonthsWith4Fields(oneAndHalfYears);
            dstBean.setOneandhalfyear(oneAndHalfYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(oneAndHalfYears + "+"));
        }

        int twoYears = dstBean.getDst_says_sentences_of_twobythree_words()
                + dstBean.getDst_points_out_objects_in_pictures()
                + dstBean.getDst_shows_body_parts()
                + dstBean.getDst_participates_in_play();
        if (twoYears > 0) {
            twoYears = daysAndMonthsWith4Fields(twoYears);
            dstBean.setTwoyears(twoYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(twoYears + "+"));
        }

        int threeYears = dstBean.getDst_copies_o()
                + dstBean.getDst_relates_experiences()
                + dstBean.getDst_knows_names_uses_of_common_objects()
                + dstBean.getDst_begins_to_ask_why()
                + dstBean.getDst_takes_food_by_self()
                + dstBean.getDst_toilet_control_present();
        if (threeYears > 0) {
            threeYears = monthsWith6Fields(threeYears);
            dstBean.setThreeyears(threeYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(threeYears + "+"));
        }

        int fourYears = dstBean.getDst_buttons_up()
                + dstBean.getDst_comprehends_hunger_cold()
                + dstBean.getDst_plays_cooperatively_with_children()
                + dstBean.getDst_repeats_three_digits()
                + dstBean.getDst_tells_stories();
        if (fourYears > 0) {
            fourYears = daysAndMonthsWith5Fields(fourYears);
            dstBean.setFouryears(fourYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(fourYears + "+"));
        }

        int fiveYears = dstBean.getDst_defines_words()
                + dstBean.getDst_makes_simple_drawing()
                + dstBean.getDst_dresses_with_no_supervision()
                + dstBean.getDst_describes_actions_in_pictures()
                + dstBean.getDst_gives_sensible_answers_to_questions()
                + dstBean.getDst_goes_about_neighbourhood();
        if (fiveYears > 0) {
            fiveYears = monthsWith6Fields(fiveYears);
            dstBean.setFiveyears(fiveYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(fiveYears + "+"));
        }

        int sixYears = dstBean.getDst_can_name_primary_colours()
                + dstBean.getDst_plays_games_governed_by_rules()
                + dstBean.getDst_writes_simple_words()
                + dstBean.getDst_gains_admission_to_school()
                + dstBean.getDst_enjoys_constructive_play();
        if (sixYears > 0) {
            sixYears = daysAndMonthsWith5Fields(sixYears);
            dstBean.setSixyears(sixYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(sixYears + "+"));
        }

        int sevenYears = dstBean.getDst_adapts_to_home_school()
                + dstBean.getDst_tells_differences_of_objects()
                + dstBean.getDst_spells_reads_writes_simple_words()
                + dstBean.getDst_enjoys_group_play()
                + dstBean.getDst_knows_comparative_value_of_coins();
        if (sevenYears > 0) {
            sevenYears = daysAndMonthsWith5Fields(sevenYears);
            dstBean.setSevenyears(sevenYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(sevenYears + "+"));
        }

        int eightYears = dstBean.getDst_combs_hair_by_self()
                + dstBean.getDst_makes_small_purchases()
                + dstBean.getDst_competition_in_school_or_play()
                + dstBean.getDst_tells_time();
        if (eightYears > 0) {
            eightYears = monthsWith4Fields(eightYears);
            dstBean.setEightyears(eightYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(eightYears + "+"));
        }

        int nineYears = dstBean.getDst_tells_day_month_year()
                + dstBean.getDst_reads_on_own_initiative()
                + dstBean.getDst_recognises_property_rights()
                + dstBean.getDst_favourite_of_fairy_tales()
                + dstBean.getDst_muscle_coordination_games()
                + dstBean.getDst_bathes_self_unaided();
        if (nineYears > 0) {
            nineYears = monthsWith6Fields(nineYears);
            dstBean.setNineyears(nineYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(nineYears + "+"));
        }

        int tenYears = dstBean.getDst_cooperates_keenly_with_companions()
                + dstBean.getDst_has_various_hobbies_collections()
                + dstBean.getDst_goes_about_town_freely()
                + dstBean.getDst_sex_differences_in_play_become_marked()
                + dstBean.getDst_can_stay_away_from_home();
        if (tenYears > 0) {
            tenYears = daysAndMonthsWith5Fields(tenYears);
            dstBean.setTenyears(tenYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(tenYears + "+"));
        }

        int elevenYears = dstBean.getDst_writes_occasional_short_letters()
                + dstBean.getDst_comprehends_social_situations()
                + dstBean.getDst_physical_feets_liked()
                + dstBean.getDst_able_to_discuss_problems();
        if (elevenYears > 0) {
            elevenYears = monthsWith4Fields(elevenYears);
            dstBean.setElevenyears(elevenYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(elevenYears + "+"));
        }

        int twelveYears = dstBean.getDst_enjoys_books_newspapers_magazines()
                + dstBean.getDst_more_independent_in_spending()
                + dstBean.getDst_capable_of_self_criticism();
        if (twelveYears > 0) {
            twelveYears = monthsWith3Fields(twelveYears);
            dstBean.setTweleveyears(twelveYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(twelveYears + "+"));
        }

        int thirteenYears = dstBean.getDst_shows_foresight_planning_judgement()
                + dstBean.getDst_learns_from_experience()
                + dstBean.getDst_plays_difficult_games()
                + dstBean.getDst_interested_in_dressing_up()
                + dstBean.getDst_understands_abstract_ideas();
        if (thirteenYears > 0) {
            thirteenYears = daysAndMonthsWith5Fields(thirteenYears);
            dstBean.setThirteenyears(thirteenYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(thirteenYears + "+"));
        }

        int fifteenYears = dstBean.getDst_makes_sensible_plans_for_future()
                + dstBean.getDst_follows_current_events()
                + dstBean.getDst_buys_own_clothing()
                + dstBean.getDst_systematises_own_work()
                + dstBean.getDst_purchases_for_others();
        if (fifteenYears > 0) {
            fifteenYears = monthsAndYearsWith5Fields(fifteenYears);
            dstBean.setFifteenyears(fifteenYears);
            dstBean.setTotaldst(dstBean.getTotaldst().append(fifteenYears + "+"));
        }

        int totalDays = threeMonths + sixMonths + nineMonths + oneYear + oneAndHalfYears
                + twoYears + threeYears + fourYears + fiveYears + sixYears
                + sevenYears + eightYears + nineYears + tenYears + elevenYears
                + twelveYears + thirteenYears + fifteenYears;

        if (totalDays != 0) {
            dstBean.setTotaldst(new StringBuffer(dstBean.getTotaldst().substring(0, dstBean.getTotaldst().length() - 1)));
            dstBean.setTotaldst(dstBean.getTotaldst().append("=" + totalDays));
            years = totalDays / 360;
            dstBean.setNoofyears(new StringBuffer("Total no. of years: " + totalDays + " / 360 = " + years));

            months = (double) (totalDays % 360) / 30;
            dstBean.setNoofmonths(new StringBuffer("Total no. of months: (" + totalDays + " % 360) / 30 = " + months));

        }


        return totalDays;
    }

    int daysWith4Fields(int count) {
        switch (count) {
            case 1:
                return 23;
            case 2:
                return 46;
            case 3:
                return 68;
            case 4:
                return 90;
        }
        return 0;
    }

    int daysWith6Fields(int count) {
        switch (count) {
            case 1:
                return 15;
            case 2:
                return 30;
            case 3:
                return 45;
            case 4:
                return 60;
            case 5:
                return 75;
            case 6:
                return 90;
        }
        return 0;
    }

    int daysWith7Fields(int count) {
        switch (count) {
            case 1:
                return 13;
            case 2:
                return 26;
            case 3:
                return 39;
            case 4:
                return 52;
            case 5:
                return 65;
            case 6:
                return 78;
            case 7:
                return 90;
        }
        return 0;
    }

    int daysAndMonthsWith4Fields(int count) {
        switch (count) {
            case 1:
                return 45;
            case 2:
                return 90;
            case 3:
                return 135;
            case 4:
                return 180;
        }
        return 0;
    }

    int daysAndMonthsWith5Fields(int count) {
        switch (count) {
            case 1:
                return 72;
            case 2:
                return 144;
            case 3:
                return 216;
            case 4:
                return 288;
            case 5:
                return 360;
        }
        return 0;
    }

    int monthsWith3Fields(int count) {
        switch (count) {
            case 1:
                return 120;
            case 2:
                return 240;
            case 3:
                return 360;
        }
        return 0;
    }

    int monthsWith4Fields(int count) {
        switch (count) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            case 4:
                return 360;
        }
        return 0;
    }

    int monthsWith6Fields(int count) {
        switch (count) {
            case 1:
                return 60;
            case 2:
                return 120;
            case 3:
                return 180;
            case 4:
                return 240;
            case 5:
                return 300;
            case 6:
                return 360;
        }
        return 0;
    }

    int monthsAndYearsWith5Fields(int count) {
        switch (count) {
            case 1:
                return 144;
            case 2:
                return 288;
            case 3:
                return 426;
            case 4:
                return 576;
            case 5:
                return 720;
        }
        return 0;
    }

    public DSTBean convertToYears(DSTBean dstBean) {
        mentalage = years + months / 12;
        String str = new StringBuffer(years + " + " + months + " / 12 = " + mentalage).toString();
        dstBean.setMentalage(str);
        return dstBean;
    }

    public DSTBean calculateIQ(DSTBean dstBean) {
        double iq;
        double chronologicalage = Double.parseDouble(dstBean.getChronologicalage());
        if (chronologicalage < 15) {
            iq = (mentalage / chronologicalage) * 100;
            dstBean.setIq(new StringBuffer("( " + mentalage + " / " + dstBean.getChronologicalage() + " ) * 100 = " + iq));
        } else {
            StringBuffer temp = new StringBuffer();
            dstBean.setIq(new StringBuffer("As chronological age is >15,\n"));
            dstBean.setIq(dstBean.getIq().append("15 will be taken as Chronological age.\n"));
            chronologicalage = 15;
            iq = (mentalage / chronologicalage) * 100;
            temp = dstBean.getIq();
            temp.append(new StringBuffer("( " + mentalage + " / " + chronologicalage + " ) * 100 = " + iq));
        }


        return dstBean;
    }
}
