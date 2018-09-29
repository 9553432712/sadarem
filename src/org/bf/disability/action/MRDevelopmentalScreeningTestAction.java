/*
 * MRDevelopmentalScreeningTestAction.java
 *
 * Created on October 14, 2008, 4:49 PM
 */
package org.bf.disability.action;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.MRDevelopmentalScreeningTestDAO;
import org.bf.disability.dto.MRDevelopmentalScreeningTestDTO;
import org.bf.disability.form.MRDevelopmentalScreeningTestForm;
import org.bf.disability.service.MRDevelopmentalScreeningTestService;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;

/**
 * 
 * @author Pramod
 * @ This class is use for Inserting,Selecting,Updating the DevelopmentalTestDetails.
 */
public class MRDevelopmentalScreeningTestAction extends BaseDispatchAction {

    /**
     * 
     *@ This Method is use for inserting the DevelopmentalTestDetails.
     * @param mapping ActionMapping
     * @param form DevelopmentalScreeningTestForm
     * @throws java.lang.SADAREMException 
     */
    public ActionForward insertMRDevelopmentalScreeningTest(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        MRDevelopmentalScreeningTestForm mrDevelopmentalScreeningTestForm = (MRDevelopmentalScreeningTestForm) form;
        MRDevelopmentalScreeningTestService mrDevelopmentalScreeningTestService = new MRDevelopmentalScreeningTestService();
        MRDevelopmentalScreeningTestDAO mrDevelopmentalScreeningTestDAO = new MRDevelopmentalScreeningTestDAO();
        MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO = new MRDevelopmentalScreeningTestDTO();

        DataSource datasource = null;
        //DataSource datasource=getDataSource(request);



        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        int years, noOfDays;
        double months;
        String target = "SUCCESS";


        String personcode = null;

        if (session.getAttribute("sadaremCodeAu") != null) {
            personcode = (String) session.getAttribute("sadaremCodeAu");
        } else {
            personcode = (String) session.getAttribute("personcode");
        }

        String loginid = (String) session.getAttribute("loginid");

//        String loginid="pramod";
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }


            if (!mrDevelopmentalScreeningTestService.checkForUser(datasource, personcode)) {
                noOfDays = calculationPartforMRDevleopmentalScreeningTest(mrDevelopmentalScreeningTestForm);

                years = noOfDays / 360;
                months = (double) (noOfDays % 360) / 30;

                request.setAttribute("dstinsertyears", new Integer(years));
                request.setAttribute("dstinsertmonths", new Double(months));

                if (noOfDays > 0) {

                    BeanUtils.copyProperties(mrDevelopmentalScreeningTestDTO, mrDevelopmentalScreeningTestForm);
                    mrDevelopmentalScreeningTestDTO.setIpaddress((String) request.getRemoteAddr());
                    mrDevelopmentalScreeningTestDTO.setLoginid(loginid);
                    mrDevelopmentalScreeningTestDTO.setPersoncode(personcode);


                    mrDevelopmentalScreeningTestService.insertMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO, request);


                    //           mrDevelopmentalScreeningTestService.insertMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO,request);
                } else {
                    target = "SUCCESS";
                }
            } else if (session.getAttribute("sadaremCodeAu") != null) {
                noOfDays = calculationPartforMRDevleopmentalScreeningTest(mrDevelopmentalScreeningTestForm);

                years = noOfDays / 360;
                months = (double) (noOfDays % 360) / 30;

                request.setAttribute("dstinsertyears", new Integer(years));
                request.setAttribute("dstinsertmonths", new Double(months));

                if (noOfDays > 0) {

                    BeanUtils.copyProperties(mrDevelopmentalScreeningTestDTO, mrDevelopmentalScreeningTestForm);
                    mrDevelopmentalScreeningTestDTO.setIpaddress((String) request.getRemoteAddr());
                    mrDevelopmentalScreeningTestDTO.setLoginid(loginid);
                    mrDevelopmentalScreeningTestDTO.setPersoncode(personcode);


                    int i = mrDevelopmentalScreeningTestService.insertMRDevelopmentalScreeningTestDetailsAU(datasource, mrDevelopmentalScreeningTestDTO, request);
                    if (i == -1) {
                        target = "FAILURE";
                    } else {
                        target = "SUCCESS";
                    }

                    //           mrDevelopmentalScreeningTestService.insertMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO,request);
                } else {
                    target = "SUCCESS";
                }
            } else {
                target = "FAILURE";
            }
        } catch (Exception sADAREMException) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(datasource, sADAREMException.toString(), "insertMRDevelopmentalScreeningTest", "MRDevelopmentalScreeningTestAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sADAREMException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestAction", "insertMRDevelopmentalScreeningTest");
        }
        return mapping.findForward(target);
    }

    /**
     *
     *@ This Method is use for inserting the DevelopmentalTestDetails.
     * @param mapping ActionMapping
     * @param form DevelopmentalScreeningTestForm
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertMRDevelopmentalScreeningTestAU(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        MRDevelopmentalScreeningTestForm mrDevelopmentalScreeningTestForm = (MRDevelopmentalScreeningTestForm) form;
        MRDevelopmentalScreeningTestService mrDevelopmentalScreeningTestService = new MRDevelopmentalScreeningTestService();
        MRDevelopmentalScreeningTestDAO mrDevelopmentalScreeningTestDAO = new MRDevelopmentalScreeningTestDAO();
        MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO = new MRDevelopmentalScreeningTestDTO();

        DataSource datasource = null;
        //DataSource datasource=getDataSource(request);



        HttpSession session = request.getSession();
        ActionMessages actionMessages = null;
        int years, noOfDays;
        double months;
        String target = "SUCCESS";


        String personcode = (String) session.getAttribute("sadaremCodeAu");
        String loginid = (String) session.getAttribute("loginid");

//        String loginid="pramod";
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }



            if (!mrDevelopmentalScreeningTestService.checkForUser(datasource, personcode)) {
                noOfDays = calculationPartforMRDevleopmentalScreeningTest(mrDevelopmentalScreeningTestForm);

                years = noOfDays / 360;
                months = (double) (noOfDays % 360) / 30;

                request.setAttribute("dstinsertyears", new Integer(years));
                request.setAttribute("dstinsertmonths", new Double(months));

                if (noOfDays > 0) {

                    BeanUtils.copyProperties(mrDevelopmentalScreeningTestDTO, mrDevelopmentalScreeningTestForm);
                    mrDevelopmentalScreeningTestDTO.setIpaddress((String) request.getRemoteAddr());
                    mrDevelopmentalScreeningTestDTO.setLoginid(loginid);
                    mrDevelopmentalScreeningTestDTO.setPersoncode(personcode);
                    mrDevelopmentalScreeningTestService.insertMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO, request);
                } else {
                    target = "SUCCESS";
                }
            } else {
                target = "FAILURE";
            }
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "insertMRDevelopmentalScreeningTestAU", "MRDevelopmentalScreeningTestAction", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestAction", "insertMRDevelopmentalScreeningTestAU");
        }
        return mapping.findForward(target);
    }

    /**
     * 
     *@ This Method is use for getting  the DevelopmentalTestDetails.
     * @param mapping ActionMapping
     * @param form DevelopmentalScreeningTestForm
     * @throws java.lang.SADAREMException 
     */
    public ActionForward selectMRDevelopmentalScreeningTest(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = null;
        ActionMessages actionMessages = null;
        DataSource datasource = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            MRDevelopmentalScreeningTestForm mrDevelopmentalScreeningTestForm = (MRDevelopmentalScreeningTestForm) form;
            MRDevelopmentalScreeningTestService mrDevelopmentalScreeningTestService = new MRDevelopmentalScreeningTestService();
            MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO = new MRDevelopmentalScreeningTestDTO();

            HttpSession session = request.getSession();
            String personcode = null;//(String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            String loginid = (String) session.getAttribute("loginid");
            mrDevelopmentalScreeningTestDTO.setIpaddress((String) request.getRemoteAddr());
            mrDevelopmentalScreeningTestDTO.setLoginid(loginid);
            mrDevelopmentalScreeningTestDTO.setPersoncode(personcode);
            mrDevelopmentalScreeningTestDTO = mrDevelopmentalScreeningTestService.selectMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO);
            BeanUtils.copyProperties(mrDevelopmentalScreeningTestForm, mrDevelopmentalScreeningTestDTO);
            saveToken(request);
            target = "success";
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "selectMRDevelopmentalScreeningTest", "MRDevelopmentalScreeningTestAction", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestAction", "selectMRDevelopmentalScreeningTest");
        }
        return mapping.findForward(target);
    }

    /**
     * 
     *@ This Method is use for Updating the DevelopmentalTestDetails.
     * @param mapping ActionMapping
     * @param form DevelopmentalScreeningTestForm
     * @throws java.lang.SADAREMException 
     */
    public ActionForward updateMRDevelopmentalScreeningTest(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String target = null;
        ActionMessages actionMessages = null;
        DataSource datasource = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            boolean flag = isTokenValid(request, true);
            if (flag) {
                MRDevelopmentalScreeningTestForm mrDevelopmentalScreeningTestForm = (MRDevelopmentalScreeningTestForm) form;
                MRDevelopmentalScreeningTestService mrDevelopmentalScreeningTestService = new MRDevelopmentalScreeningTestService();
                MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO = new MRDevelopmentalScreeningTestDTO();

                HttpSession session = request.getSession();
                String personcode = null;//(String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }


                String loginid = (String) session.getAttribute("loginid");
                int noOfDays = 0, years;
                double months;
                noOfDays = calculationPartforMRDevleopmentalScreeningTest(mrDevelopmentalScreeningTestForm);
                years = noOfDays / 360;
                months = (double) (noOfDays % 360) / 30;
                request.setAttribute("dstupdateyears", new Integer(years));
                request.setAttribute("dstupdatemonths", new Double(months));
                BeanUtils.copyProperties(mrDevelopmentalScreeningTestDTO, mrDevelopmentalScreeningTestForm);
                mrDevelopmentalScreeningTestDTO.setIpaddress((String) request.getRemoteAddr());
                mrDevelopmentalScreeningTestDTO.setLoginid(loginid);
                mrDevelopmentalScreeningTestDTO.setPersoncode(personcode);


                if (noOfDays > 0) {
                    if (session.getAttribute("sadaremCodeAu") != null) {
                        mrDevelopmentalScreeningTestService.insertMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO, request);
                    } else {
                        mrDevelopmentalScreeningTestService.updateMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO, request);
                    }
                } else {
                    mrDevelopmentalScreeningTestService.deleteMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO);
                }



            }
            target = "success";
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "updateMRDevelopmentalScreeningTest", "MRDevelopmentalScreeningTestAction", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestAction", "updateMRDevelopmentalScreeningTest");
        }
        return mapping.findForward(target);
    }

    /**
     * 
     *@ This Method is use for calculation the DevelopmentalTestDetails.
     * @param form DevelopmentalScreeningTestForm
     */
    public int calculationPartforMRDevleopmentalScreeningTest(MRDevelopmentalScreeningTestForm formObj) {
        int threeMonths = formObj.getDst_birth_cry_present()
                + formObj.getDst_equal_bilateral_movements()
                + formObj.getDst_responds_to_bell()
                + formObj.getDst_vocalises_sounds()
                + formObj.getDst_smiles_spontaneously()
                + formObj.getDst_eyes_following_moving_object()
                + formObj.getDst_head_steady();
        if (threeMonths > 0) {
            threeMonths = daysWith7Fields(threeMonths);
        }

        int sixMonths = formObj.getDst_reaches_for_objects()
                + formObj.getDst_laughs_loud()
                + formObj.getDst_recognises_mother()
                + formObj.getDst_vocalises_for_pleasure_or_babble()
                + formObj.getDst_carries_objects_to_mouth()
                + formObj.getDst_rolls_over();
        if (sixMonths > 0) {
            sixMonths = daysWith6Fields(sixMonths);
        }

        int nineMonths = formObj.getDst_imitates_speech_sounds()
                + formObj.getDst_sits_by_self()
                + formObj.getDst_thumb_finger_grasp()
                + formObj.getDst_shows_curiousity();
        if (nineMonths > 0) {
            nineMonths = daysWith4Fields(nineMonths);
        }


        int oneYear = formObj.getDst_says_three_words()
                + formObj.getDst_stands_alone_well()
                + formObj.getDst_follows_simple_instructions()
                + formObj.getDst_cooperates_for_dressing();
        if (oneYear > 0) {
            oneYear = daysWith4Fields(oneYear);
        }

        int oneAndHalfYears = formObj.getDst_many_intelligible_words()
                + formObj.getDst_walks_runs_well()
                + formObj.getDst_indicates_wants()
                + formObj.getDst_scribbles_spontaneously();
        if (oneAndHalfYears > 0) {
            oneAndHalfYears = daysAndMonthsWith4Fields(oneAndHalfYears);
        }

        int twoYears = formObj.getDst_says_sentences_of_twobythree_words()
                + formObj.getDst_points_out_objects_in_pictures()
                + formObj.getDst_shows_body_parts()
                + formObj.getDst_participates_in_play();
        if (twoYears > 0) {
            twoYears = daysAndMonthsWith4Fields(twoYears);
        }

        int threeYears = formObj.getDst_copies_o()
                + formObj.getDst_relates_experiences()
                + formObj.getDst_knows_names_uses_of_common_objects()
                + formObj.getDst_begins_to_ask_why()
                + formObj.getDst_takes_food_by_self()
                + formObj.getDst_toilet_control_present();
        if (threeYears > 0) {
            threeYears = monthsWith6Fields(threeYears);
        }

        int fourYears = formObj.getDst_buttons_up()
                + formObj.getDst_comprehends_hunger_cold()
                + formObj.getDst_plays_cooperatively_with_children()
                + formObj.getDst_repeats_three_digits()
                + formObj.getDst_tells_stories();
        if (fourYears > 0) {
            fourYears = daysAndMonthsWith5Fields(fourYears);
        }

        int fiveYears = formObj.getDst_defines_words()
                + formObj.getDst_makes_simple_drawing()
                + formObj.getDst_dresses_with_no_supervision()
                + formObj.getDst_describes_actions_in_pictures()
                + formObj.getDst_gives_sensible_answers_to_questions()
                + formObj.getDst_goes_about_neighbourhood();
        if (fiveYears > 0) {
            fiveYears = monthsWith6Fields(fiveYears);
        }

        int sixYears = formObj.getDst_can_name_primary_colours()
                + formObj.getDst_plays_games_governed_by_rules()
                + formObj.getDst_writes_simple_words()
                + formObj.getDst_gains_admission_to_school()
                + formObj.getDst_enjoys_constructive_play();
        if (sixYears > 0) {
            sixYears = daysAndMonthsWith5Fields(sixYears);
        }

        int sevenYears = formObj.getDst_adapts_to_home_school()
                + formObj.getDst_tells_differences_of_objects()
                + formObj.getDst_spells_reads_writes_simple_words()
                + formObj.getDst_enjoys_group_play()
                + formObj.getDst_knows_comparative_value_of_coins();
        if (sevenYears > 0) {
            sevenYears = daysAndMonthsWith5Fields(sevenYears);
        }

        int eightYears = formObj.getDst_combs_hair_by_self()
                + formObj.getDst_makes_small_purchases()
                + formObj.getDst_competition_in_school_or_play()
                + formObj.getDst_tells_time();
        if (eightYears > 0) {
            eightYears = monthsWith4Fields(eightYears);
        }

        int nineYears = formObj.getDst_tells_day_month_year()
                + formObj.getDst_reads_on_own_initiative()
                + formObj.getDst_recognises_property_rights()
                + formObj.getDst_favourite_of_fairy_tales()
                + formObj.getDst_muscle_coordination_games()
                + formObj.getDst_bathes_self_unaided();
        if (nineYears > 0) {
            nineYears = monthsWith6Fields(nineYears);
        }

        int tenYears = formObj.getDst_cooperates_keenly_with_companions()
                + formObj.getDst_has_various_hobbies_collections()
                + formObj.getDst_goes_about_town_freely()
                + formObj.getDst_sex_differences_in_play_become_marked()
                + formObj.getDst_can_stay_away_from_home();
        if (tenYears > 0) {
            tenYears = daysAndMonthsWith5Fields(tenYears);
        }

        int elevenYears = formObj.getDst_writes_occasional_short_letters()
                + formObj.getDst_comprehends_social_situations()
                + formObj.getDst_physical_feets_liked()
                + formObj.getDst_able_to_discuss_problems();
        if (elevenYears > 0) {
            elevenYears = monthsWith4Fields(elevenYears);
        }

        int twelveYears = formObj.getDst_enjoys_books_newspapers_magazines()
                + formObj.getDst_more_independent_in_spending()
                + formObj.getDst_capable_of_self_criticism();
        if (twelveYears > 0) {
            twelveYears = monthsWith3Fields(twelveYears);
        }

        int thirteenYears = formObj.getDst_shows_foresight_planning_judgement()
                + formObj.getDst_learns_from_experience()
                + formObj.getDst_plays_difficult_games()
                + formObj.getDst_interested_in_dressing_up()
                + formObj.getDst_understands_abstract_ideas();
        if (thirteenYears > 0) {
            thirteenYears = daysAndMonthsWith5Fields(thirteenYears);
        }

        int fifteenYears = formObj.getDst_makes_sensible_plans_for_future()
                + formObj.getDst_follows_current_events()
                + formObj.getDst_buys_own_clothing()
                + formObj.getDst_systematises_own_work()
                + formObj.getDst_purchases_for_others();
        if (fifteenYears > 0) {
            fifteenYears = monthsAndYearsWith5Fields(fifteenYears);
        }

        int totalDays = threeMonths + sixMonths + nineMonths + oneYear + oneAndHalfYears
                + twoYears + threeYears + fourYears + fiveYears + sixYears
                + sevenYears + eightYears + nineYears + tenYears + elevenYears
                + twelveYears + thirteenYears + fifteenYears;

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
}
