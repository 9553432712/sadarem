/*
 * MRDevelopmentalScreeningTestDAO.java
 *
 * Created on October 16, 2008, 5:04 PM
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
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dto.MRDevelopmentalScreeningTestDTO;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 * 
 * @ 
 * @author Pramod
 * @ This DAO is use for Inserting,Selecting,Updating the DevelopmentalTestDetailsData.
 */
public class MRDevelopmentalScreeningTestDAO {

    /**
     * this method is used for insert into SP_tblMR_DST_Test_Details_for_ThreeMonths_to_Eighten_Months_Age_Persons_Insert,SP_tblMR_DST_Test_Details_for_TwoYears_to_SixYears_Age_Persons_Insert,SP_tblMR_DST_Test_Details_for_SevenYears_to_TwelveYears_Age_Persons_Insert,SP_tblMR_DST_Test_Details_for_ThirteenYears_To_FifteenYears_Age_Persons_Insert.
     * @param datasource
     * @param mrDevelopmentalScreeningTestDTO
     */
    public void insertMRDevelopmentalScreeningTestDetails(DataSource datasource, MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        //Statement st=null;
        ResultSet rs = null;
        String code = mrDevelopmentalScreeningTestDTO.getPersoncode();
        //HttpSession session = request.getSession();

        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
             /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

           



            cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_ThreeMonths_to_Eighten_Months_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, code);
            cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_birth_cry_present());
            cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_equal_bilateral_movements());
            cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_responds_to_bell());
            cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_vocalises_sounds());
            cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_smiles_spontaneously());
            cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_eyes_following_moving_object());
            cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_head_steady());
            cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_reaches_for_objects());
            cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_laughs_loud());
            cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_recognises_mother());
            cs.setInt(12, mrDevelopmentalScreeningTestDTO.getDst_vocalises_for_pleasure_or_babble());
            cs.setInt(13, mrDevelopmentalScreeningTestDTO.getDst_carries_objects_to_mouth());
            cs.setInt(14, mrDevelopmentalScreeningTestDTO.getDst_rolls_over());
            cs.setInt(15, mrDevelopmentalScreeningTestDTO.getDst_imitates_speech_sounds());
            cs.setInt(16, mrDevelopmentalScreeningTestDTO.getDst_sits_by_self());
            cs.setInt(17, mrDevelopmentalScreeningTestDTO.getDst_thumb_finger_grasp());
            cs.setInt(18, mrDevelopmentalScreeningTestDTO.getDst_shows_curiousity());
            cs.setInt(19, mrDevelopmentalScreeningTestDTO.getDst_says_three_words());
            cs.setInt(20, mrDevelopmentalScreeningTestDTO.getDst_stands_alone_well());
            cs.setInt(21, mrDevelopmentalScreeningTestDTO.getDst_follows_simple_instructions());
            cs.setInt(22, mrDevelopmentalScreeningTestDTO.getDst_cooperates_for_dressing());
            cs.setInt(23, mrDevelopmentalScreeningTestDTO.getDst_many_intelligible_words());
            cs.setInt(24, mrDevelopmentalScreeningTestDTO.getDst_walks_runs_well());
            cs.setInt(25, mrDevelopmentalScreeningTestDTO.getDst_indicates_wants());
            cs.setInt(26, mrDevelopmentalScreeningTestDTO.getDst_scribbles_spontaneously());
            cs.setString(27, mrDevelopmentalScreeningTestDTO.getLoginid());
            cs.setString(28, mrDevelopmentalScreeningTestDTO.getIpaddress());

            cs.setInt(29, Integer.parseInt(details[0].toString()));
            cs.setInt(30, Integer.parseInt(details[1].toString()));

            cs.executeUpdate();

            cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_TwoYears_to_SixYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, code);
            cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_says_sentences_of_twobythree_words());
            cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_points_out_objects_in_pictures());
            cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_shows_body_parts());
            cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_participates_in_play());
            cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_copies_o());
            cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_relates_experiences());
            cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_knows_names_uses_of_common_objects());
            cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_begins_to_ask_why());
            cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_takes_food_by_self());
            cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_toilet_control_present());
            cs.setInt(12, mrDevelopmentalScreeningTestDTO.getDst_buttons_up());
            cs.setInt(13, mrDevelopmentalScreeningTestDTO.getDst_comprehends_hunger_cold());
            cs.setInt(14, mrDevelopmentalScreeningTestDTO.getDst_plays_cooperatively_with_children());
            cs.setInt(15, mrDevelopmentalScreeningTestDTO.getDst_repeats_three_digits());
            cs.setInt(16, mrDevelopmentalScreeningTestDTO.getDst_tells_stories());
            cs.setInt(17, mrDevelopmentalScreeningTestDTO.getDst_defines_words());
            cs.setInt(18, mrDevelopmentalScreeningTestDTO.getDst_makes_simple_drawing());
            cs.setInt(19, mrDevelopmentalScreeningTestDTO.getDst_dresses_with_no_supervision());
            cs.setInt(20, mrDevelopmentalScreeningTestDTO.getDst_describes_actions_in_pictures());
            cs.setInt(21, mrDevelopmentalScreeningTestDTO.getDst_gives_sensible_answers_to_questions());
            cs.setInt(22, mrDevelopmentalScreeningTestDTO.getDst_goes_about_neighbourhood());
            cs.setInt(23, mrDevelopmentalScreeningTestDTO.getDst_can_name_primary_colours());
            cs.setInt(24, mrDevelopmentalScreeningTestDTO.getDst_plays_games_governed_by_rules());
            cs.setInt(25, mrDevelopmentalScreeningTestDTO.getDst_writes_simple_words());
            cs.setInt(26, mrDevelopmentalScreeningTestDTO.getDst_gains_admission_to_school());
            cs.setInt(27, mrDevelopmentalScreeningTestDTO.getDst_enjoys_constructive_play());
            cs.setString(28, mrDevelopmentalScreeningTestDTO.getLoginid());
            cs.setString(29, mrDevelopmentalScreeningTestDTO.getIpaddress());

            cs.setInt(30, Integer.parseInt(details[0].toString()));
            cs.setInt(31, Integer.parseInt(details[1].toString()));


            cs.executeUpdate();

            cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_SevenYears_to_TwelveYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, code);
            cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_adapts_to_home_school());
            cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_tells_differences_of_objects());
            cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_spells_reads_writes_simple_words());
            cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_enjoys_group_play());
            cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_knows_comparative_value_of_coins());
            cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_combs_hair_by_self());
            cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_makes_small_purchases());
            cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_competition_in_school_or_play());
            cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_tells_time());
            cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_tells_day_month_year());
            cs.setInt(12, mrDevelopmentalScreeningTestDTO.getDst_reads_on_own_initiative());
            cs.setInt(13, mrDevelopmentalScreeningTestDTO.getDst_recognises_property_rights());
            cs.setInt(14, mrDevelopmentalScreeningTestDTO.getDst_favourite_of_fairy_tales());
            cs.setInt(15, mrDevelopmentalScreeningTestDTO.getDst_muscle_coordination_games());
            cs.setInt(16, mrDevelopmentalScreeningTestDTO.getDst_bathes_self_unaided());
            cs.setInt(17, mrDevelopmentalScreeningTestDTO.getDst_cooperates_keenly_with_companions());
            cs.setInt(18, mrDevelopmentalScreeningTestDTO.getDst_has_various_hobbies_collections());
            cs.setInt(19, mrDevelopmentalScreeningTestDTO.getDst_goes_about_town_freely());
            cs.setInt(20, mrDevelopmentalScreeningTestDTO.getDst_sex_differences_in_play_become_marked());
            cs.setInt(21, mrDevelopmentalScreeningTestDTO.getDst_can_stay_away_from_home());
            cs.setInt(22, mrDevelopmentalScreeningTestDTO.getDst_writes_occasional_short_letters());
            cs.setInt(23, mrDevelopmentalScreeningTestDTO.getDst_comprehends_social_situations());
            cs.setInt(24, mrDevelopmentalScreeningTestDTO.getDst_physical_feets_liked());
            cs.setInt(25, mrDevelopmentalScreeningTestDTO.getDst_able_to_discuss_problems());
            cs.setInt(26, mrDevelopmentalScreeningTestDTO.getDst_enjoys_books_newspapers_magazines());
            cs.setInt(27, mrDevelopmentalScreeningTestDTO.getDst_more_independent_in_spending());
            cs.setInt(28, mrDevelopmentalScreeningTestDTO.getDst_capable_of_self_criticism());
            cs.setString(29, mrDevelopmentalScreeningTestDTO.getLoginid());
            cs.setString(30, mrDevelopmentalScreeningTestDTO.getIpaddress());

            cs.setInt(31, Integer.parseInt(details[0].toString()));
            cs.setInt(32, Integer.parseInt(details[1].toString()));

            cs.executeUpdate();

            cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_ThirteenYears_To_FifteenYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, code);
            cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_shows_foresight_planning_judgement());
            cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_learns_from_experience());
            cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_plays_difficult_games());
            cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_interested_in_dressing_up());
            cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_understands_abstract_ideas());
            cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_makes_sensible_plans_for_future());
            cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_follows_current_events());
            cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_buys_own_clothing());
            cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_systematises_own_work());
            cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_purchases_for_others());
            cs.setString(12, mrDevelopmentalScreeningTestDTO.getLoginid());
            cs.setString(13, mrDevelopmentalScreeningTestDTO.getIpaddress());

            cs.setInt(14, Integer.parseInt(details[0].toString()));
            cs.setInt(15, Integer.parseInt(details[1].toString()));

            cs.executeUpdate();

            con.commit();
            con.setAutoCommit(true);

        } catch (Exception exception) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in inserting MRDevelopmental Screening Test Details", code);
            // End
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "insertMRDevelopmentalScreeningTestDetails", "MRDevelopmentalScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestDAO", "insertMRDevelopmentalScreeningTestDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);

        }
    }

    /**
     * this method is used for insert into SP_tblMR_DST_Test_Details_for_ThreeMonths_to_Eighten_Months_Age_Persons_Insert,SP_tblMR_DST_Test_Details_for_TwoYears_to_SixYears_Age_Persons_Insert,SP_tblMR_DST_Test_Details_for_SevenYears_to_TwelveYears_Age_Persons_Insert,SP_tblMR_DST_Test_Details_for_ThirteenYears_To_FifteenYears_Age_Persons_Insert.
     * @param datasource
     * @param mrDevelopmentalScreeningTestDTO
     */
    public int insertMRDevelopmentalScreeningTestDetailsAU(DataSource datasource, MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        //Statement st=null;
        ResultSet rs = null;
        String code = mrDevelopmentalScreeningTestDTO.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        PreparedStatement pstmt = null;
        String three = null;
        String two = null;
        String seven = null;
        String theerteen = null;
        int i = 0;
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            /* Getting the categeoryIdncategoryCount */
            details = dao.getCategoryDetails(datasource, code);
            con = DBConnection.getConnection();
            con.setAutoCommit(false);


            pstmt = con.prepareStatement("select * from tblMR_DST_Test_Details_for_ThreeMonths_to_Eighten_Months_Age_Persons where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            System.out.println(rs.next());
            if (rs.next()) {
                three = rs.getString(1);
            }
            pstmt = con.prepareStatement("select * from tblMR_DST_Test_Details_for_TwoYears_to_SixYears_Age_Persons where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                two = rs.getString(1);
            }
            pstmt = con.prepareStatement("select * from tblMR_DST_Test_Details_for_SevenYears_to_TwelveYears_Age_Persons where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                seven = rs.getString(1);
            }
            pstmt = con.prepareStatement("select * from tblMR_DST_Test_Details_for_ThirteenYears_To_FifteenYears_Age_Persons where Person_Code=? and status='Active'");
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                theerteen = rs.getString(1);
            }

            if (three != null || two != null || seven != null || theerteen != null) {

                cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_ThreeMonths_to_Eighten_Months_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                cs.setString(1, code);
                cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_birth_cry_present());
                cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_equal_bilateral_movements());
                cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_responds_to_bell());
                cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_vocalises_sounds());
                cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_smiles_spontaneously());
                cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_eyes_following_moving_object());
                cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_head_steady());
                cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_reaches_for_objects());
                cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_laughs_loud());
                cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_recognises_mother());
                cs.setInt(12, mrDevelopmentalScreeningTestDTO.getDst_vocalises_for_pleasure_or_babble());
                cs.setInt(13, mrDevelopmentalScreeningTestDTO.getDst_carries_objects_to_mouth());
                cs.setInt(14, mrDevelopmentalScreeningTestDTO.getDst_rolls_over());
                cs.setInt(15, mrDevelopmentalScreeningTestDTO.getDst_imitates_speech_sounds());
                cs.setInt(16, mrDevelopmentalScreeningTestDTO.getDst_sits_by_self());
                cs.setInt(17, mrDevelopmentalScreeningTestDTO.getDst_thumb_finger_grasp());
                cs.setInt(18, mrDevelopmentalScreeningTestDTO.getDst_shows_curiousity());
                cs.setInt(19, mrDevelopmentalScreeningTestDTO.getDst_says_three_words());
                cs.setInt(20, mrDevelopmentalScreeningTestDTO.getDst_stands_alone_well());
                cs.setInt(21, mrDevelopmentalScreeningTestDTO.getDst_follows_simple_instructions());
                cs.setInt(22, mrDevelopmentalScreeningTestDTO.getDst_cooperates_for_dressing());
                cs.setInt(23, mrDevelopmentalScreeningTestDTO.getDst_many_intelligible_words());
                cs.setInt(24, mrDevelopmentalScreeningTestDTO.getDst_walks_runs_well());
                cs.setInt(25, mrDevelopmentalScreeningTestDTO.getDst_indicates_wants());
                cs.setInt(26, mrDevelopmentalScreeningTestDTO.getDst_scribbles_spontaneously());
                cs.setString(27, mrDevelopmentalScreeningTestDTO.getLoginid());
                cs.setString(28, mrDevelopmentalScreeningTestDTO.getIpaddress());

                cs.setInt(29, Integer.parseInt(details[0].toString()));
                cs.setInt(30, Integer.parseInt(details[1].toString()));

                i = cs.executeUpdate();

                cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_TwoYears_to_SixYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, code);
                cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_says_sentences_of_twobythree_words());
                cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_points_out_objects_in_pictures());
                cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_shows_body_parts());
                cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_participates_in_play());
                cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_copies_o());
                cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_relates_experiences());
                cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_knows_names_uses_of_common_objects());
                cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_begins_to_ask_why());
                cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_takes_food_by_self());
                cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_toilet_control_present());
                cs.setInt(12, mrDevelopmentalScreeningTestDTO.getDst_buttons_up());
                cs.setInt(13, mrDevelopmentalScreeningTestDTO.getDst_comprehends_hunger_cold());
                cs.setInt(14, mrDevelopmentalScreeningTestDTO.getDst_plays_cooperatively_with_children());
                cs.setInt(15, mrDevelopmentalScreeningTestDTO.getDst_repeats_three_digits());
                cs.setInt(16, mrDevelopmentalScreeningTestDTO.getDst_tells_stories());
                cs.setInt(17, mrDevelopmentalScreeningTestDTO.getDst_defines_words());
                cs.setInt(18, mrDevelopmentalScreeningTestDTO.getDst_makes_simple_drawing());
                cs.setInt(19, mrDevelopmentalScreeningTestDTO.getDst_dresses_with_no_supervision());
                cs.setInt(20, mrDevelopmentalScreeningTestDTO.getDst_describes_actions_in_pictures());
                cs.setInt(21, mrDevelopmentalScreeningTestDTO.getDst_gives_sensible_answers_to_questions());
                cs.setInt(22, mrDevelopmentalScreeningTestDTO.getDst_goes_about_neighbourhood());
                cs.setInt(23, mrDevelopmentalScreeningTestDTO.getDst_can_name_primary_colours());
                cs.setInt(24, mrDevelopmentalScreeningTestDTO.getDst_plays_games_governed_by_rules());
                cs.setInt(25, mrDevelopmentalScreeningTestDTO.getDst_writes_simple_words());
                cs.setInt(26, mrDevelopmentalScreeningTestDTO.getDst_gains_admission_to_school());
                cs.setInt(27, mrDevelopmentalScreeningTestDTO.getDst_enjoys_constructive_play());
                cs.setString(28, mrDevelopmentalScreeningTestDTO.getLoginid());
                cs.setString(29, mrDevelopmentalScreeningTestDTO.getIpaddress());

                cs.setInt(30, Integer.parseInt(details[0].toString()));
                cs.setInt(31, Integer.parseInt(details[1].toString()));

                i = cs.executeUpdate();

                cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_SevenYears_to_TwelveYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, code);
                cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_adapts_to_home_school());
                cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_tells_differences_of_objects());
                cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_spells_reads_writes_simple_words());
                cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_enjoys_group_play());
                cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_knows_comparative_value_of_coins());
                cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_combs_hair_by_self());
                cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_makes_small_purchases());
                cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_competition_in_school_or_play());
                cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_tells_time());
                cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_tells_day_month_year());
                cs.setInt(12, mrDevelopmentalScreeningTestDTO.getDst_reads_on_own_initiative());
                cs.setInt(13, mrDevelopmentalScreeningTestDTO.getDst_recognises_property_rights());
                cs.setInt(14, mrDevelopmentalScreeningTestDTO.getDst_favourite_of_fairy_tales());
                cs.setInt(15, mrDevelopmentalScreeningTestDTO.getDst_muscle_coordination_games());
                cs.setInt(16, mrDevelopmentalScreeningTestDTO.getDst_bathes_self_unaided());
                cs.setInt(17, mrDevelopmentalScreeningTestDTO.getDst_cooperates_keenly_with_companions());
                cs.setInt(18, mrDevelopmentalScreeningTestDTO.getDst_has_various_hobbies_collections());
                cs.setInt(19, mrDevelopmentalScreeningTestDTO.getDst_goes_about_town_freely());
                cs.setInt(20, mrDevelopmentalScreeningTestDTO.getDst_sex_differences_in_play_become_marked());
                cs.setInt(21, mrDevelopmentalScreeningTestDTO.getDst_can_stay_away_from_home());
                cs.setInt(22, mrDevelopmentalScreeningTestDTO.getDst_writes_occasional_short_letters());
                cs.setInt(23, mrDevelopmentalScreeningTestDTO.getDst_comprehends_social_situations());
                cs.setInt(24, mrDevelopmentalScreeningTestDTO.getDst_physical_feets_liked());
                cs.setInt(25, mrDevelopmentalScreeningTestDTO.getDst_able_to_discuss_problems());
                cs.setInt(26, mrDevelopmentalScreeningTestDTO.getDst_enjoys_books_newspapers_magazines());
                cs.setInt(27, mrDevelopmentalScreeningTestDTO.getDst_more_independent_in_spending());
                cs.setInt(28, mrDevelopmentalScreeningTestDTO.getDst_capable_of_self_criticism());
                cs.setString(29, mrDevelopmentalScreeningTestDTO.getLoginid());
                cs.setString(30, mrDevelopmentalScreeningTestDTO.getIpaddress());

                cs.setInt(31, Integer.parseInt(details[0].toString()));
                cs.setInt(32, Integer.parseInt(details[1].toString()));

                i = cs.executeUpdate();

                cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_ThirteenYears_To_FifteenYears_Age_Persons_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, code);
                cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_shows_foresight_planning_judgement());
                cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_learns_from_experience());
                cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_plays_difficult_games());
                cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_interested_in_dressing_up());
                cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_understands_abstract_ideas());
                cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_makes_sensible_plans_for_future());
                cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_follows_current_events());
                cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_buys_own_clothing());
                cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_systematises_own_work());
                cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_purchases_for_others());
                cs.setString(12, mrDevelopmentalScreeningTestDTO.getLoginid());
                cs.setString(13, mrDevelopmentalScreeningTestDTO.getIpaddress());

                cs.setInt(14, Integer.parseInt(details[0].toString()));
                cs.setInt(15, Integer.parseInt(details[1].toString()));

                i = cs.executeUpdate();

                con.commit();
                con.setAutoCommit(true);


            } else {
                i = -1;
            }
        } catch (SQLException sqlException) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in inserting MRDevelopmental Screening Test Details", code);
            // End
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            int exResult = ExceptionDAO.saveException(datasource, sqlException.toString(), "insertMRDevelopmentalScreeningTestDetailsAU", "MRDevelopmentalScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestDAO", "insertMRDevelopmentalScreeningTestDetailsAU");

        } catch (Exception exception) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "insertMRDevelopmentalScreeningTestDetailsAU", "MRDevelopmentalScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestDAO", "insertMRDevelopmentalScreeningTestDetailsAU");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);

        }
        return i;
    }

    /**
     * this method is used for select the data.
     * @param datasource
     * @param mrDevelopmentalScreeningTestDTO
     */
    public MRDevelopmentalScreeningTestDTO selectMRDevelopmentalScreeningTestDetails(DataSource datasource, MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        //Statement st=null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_ThreeMonths_to_Eighten_Months_Age_Persons_Select_Basedon_Person_Code(?)}");
            cs.setString(1, mrDevelopmentalScreeningTestDTO.getPersoncode());

            rs = cs.executeQuery();


            while (rs.next()) {

                mrDevelopmentalScreeningTestDTO.setDst_birth_cry_present(rs.getInt(2));
                mrDevelopmentalScreeningTestDTO.setDst_equal_bilateral_movements(rs.getInt(3));
                mrDevelopmentalScreeningTestDTO.setDst_responds_to_bell(rs.getInt(4));
                mrDevelopmentalScreeningTestDTO.setDst_vocalises_sounds(rs.getInt(5));
                mrDevelopmentalScreeningTestDTO.setDst_smiles_spontaneously(rs.getInt(6));
                mrDevelopmentalScreeningTestDTO.setDst_eyes_following_moving_object(rs.getInt(7));
                mrDevelopmentalScreeningTestDTO.setDst_head_steady(rs.getInt(8));
                mrDevelopmentalScreeningTestDTO.setDst_reaches_for_objects(rs.getInt(9));
                mrDevelopmentalScreeningTestDTO.setDst_laughs_loud(rs.getInt(10));
                mrDevelopmentalScreeningTestDTO.setDst_recognises_mother(rs.getInt(11));
                mrDevelopmentalScreeningTestDTO.setDst_vocalises_for_pleasure_or_babble(rs.getInt(12));
                mrDevelopmentalScreeningTestDTO.setDst_carries_objects_to_mouth(rs.getInt(13));
                mrDevelopmentalScreeningTestDTO.setDst_rolls_over(rs.getInt(14));
                mrDevelopmentalScreeningTestDTO.setDst_imitates_speech_sounds(rs.getInt(15));
                mrDevelopmentalScreeningTestDTO.setDst_sits_by_self(rs.getInt(16));
                mrDevelopmentalScreeningTestDTO.setDst_thumb_finger_grasp(rs.getInt(17));
                mrDevelopmentalScreeningTestDTO.setDst_shows_curiousity(rs.getInt(18));
                mrDevelopmentalScreeningTestDTO.setDst_says_three_words(rs.getInt(19));
                mrDevelopmentalScreeningTestDTO.setDst_stands_alone_well(rs.getInt(20));
                mrDevelopmentalScreeningTestDTO.setDst_follows_simple_instructions(rs.getInt(21));
                mrDevelopmentalScreeningTestDTO.setDst_cooperates_for_dressing(rs.getInt(22));
                mrDevelopmentalScreeningTestDTO.setDst_many_intelligible_words(rs.getInt(23));
                mrDevelopmentalScreeningTestDTO.setDst_walks_runs_well(rs.getInt(24));
                mrDevelopmentalScreeningTestDTO.setDst_indicates_wants(rs.getInt(25));
                mrDevelopmentalScreeningTestDTO.setDst_scribbles_spontaneously(rs.getInt(26));
            }

            cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_TwoYears_to_SixYears_Age_Persons_Select_Basedon_Person_Code(?)}");
            cs.setString(1, mrDevelopmentalScreeningTestDTO.getPersoncode());

            rs = cs.executeQuery();

            while (rs.next()) {
                mrDevelopmentalScreeningTestDTO.setDst_says_sentences_of_twobythree_words(rs.getInt(2));
                mrDevelopmentalScreeningTestDTO.setDst_points_out_objects_in_pictures(rs.getInt(3));
                mrDevelopmentalScreeningTestDTO.setDst_shows_body_parts(rs.getInt(4));
                mrDevelopmentalScreeningTestDTO.setDst_participates_in_play(rs.getInt(5));
                mrDevelopmentalScreeningTestDTO.setDst_copies_o(rs.getInt(6));
                mrDevelopmentalScreeningTestDTO.setDst_relates_experiences(rs.getInt(7));
                mrDevelopmentalScreeningTestDTO.setDst_knows_names_uses_of_common_objects(rs.getInt(8));
                mrDevelopmentalScreeningTestDTO.setDst_begins_to_ask_why(rs.getInt(9));
                mrDevelopmentalScreeningTestDTO.setDst_takes_food_by_self(rs.getInt(10));
                mrDevelopmentalScreeningTestDTO.setDst_toilet_control_present(rs.getInt(11));
                mrDevelopmentalScreeningTestDTO.setDst_buttons_up(rs.getInt(12));
                mrDevelopmentalScreeningTestDTO.setDst_comprehends_hunger_cold(rs.getInt(13));
                mrDevelopmentalScreeningTestDTO.setDst_plays_cooperatively_with_children(rs.getInt(14));
                mrDevelopmentalScreeningTestDTO.setDst_repeats_three_digits(rs.getInt(15));
                mrDevelopmentalScreeningTestDTO.setDst_tells_stories(rs.getInt(16));
                mrDevelopmentalScreeningTestDTO.setDst_defines_words(rs.getInt(17));
                mrDevelopmentalScreeningTestDTO.setDst_makes_simple_drawing(rs.getInt(18));
                mrDevelopmentalScreeningTestDTO.setDst_dresses_with_no_supervision(rs.getInt(19));
                mrDevelopmentalScreeningTestDTO.setDst_describes_actions_in_pictures(rs.getInt(20));
                mrDevelopmentalScreeningTestDTO.setDst_gives_sensible_answers_to_questions(rs.getInt(21));
                mrDevelopmentalScreeningTestDTO.setDst_goes_about_neighbourhood(rs.getInt(22));
                mrDevelopmentalScreeningTestDTO.setDst_can_name_primary_colours(rs.getInt(23));
                mrDevelopmentalScreeningTestDTO.setDst_plays_games_governed_by_rules(rs.getInt(24));
                mrDevelopmentalScreeningTestDTO.setDst_writes_simple_words(rs.getInt(25));
                mrDevelopmentalScreeningTestDTO.setDst_gains_admission_to_school(rs.getInt(26));
                mrDevelopmentalScreeningTestDTO.setDst_enjoys_constructive_play(rs.getInt(27));
            }

            cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_SevenYears_to_TwelveYears_Age_Persons_Select_Basedon_Person_Code(?)}");
            cs.setString(1, mrDevelopmentalScreeningTestDTO.getPersoncode());

            rs = cs.executeQuery();

            while (rs.next()) {
                mrDevelopmentalScreeningTestDTO.setDst_adapts_to_home_school(rs.getInt(2));
                mrDevelopmentalScreeningTestDTO.setDst_tells_differences_of_objects(rs.getInt(3));
                mrDevelopmentalScreeningTestDTO.setDst_spells_reads_writes_simple_words(rs.getInt(4));
                mrDevelopmentalScreeningTestDTO.setDst_enjoys_group_play(rs.getInt(5));
                mrDevelopmentalScreeningTestDTO.setDst_knows_comparative_value_of_coins(rs.getInt(6));
                mrDevelopmentalScreeningTestDTO.setDst_combs_hair_by_self(rs.getInt(7));
                mrDevelopmentalScreeningTestDTO.setDst_makes_small_purchases(rs.getInt(8));
                mrDevelopmentalScreeningTestDTO.setDst_competition_in_school_or_play(rs.getInt(9));
                mrDevelopmentalScreeningTestDTO.setDst_tells_time(rs.getInt(10));
                mrDevelopmentalScreeningTestDTO.setDst_tells_day_month_year(rs.getInt(11));
                mrDevelopmentalScreeningTestDTO.setDst_reads_on_own_initiative(rs.getInt(12));
                mrDevelopmentalScreeningTestDTO.setDst_recognises_property_rights(rs.getInt(13));
                mrDevelopmentalScreeningTestDTO.setDst_favourite_of_fairy_tales(rs.getInt(14));
                mrDevelopmentalScreeningTestDTO.setDst_muscle_coordination_games(rs.getInt(15));
                mrDevelopmentalScreeningTestDTO.setDst_bathes_self_unaided(rs.getInt(16));
                mrDevelopmentalScreeningTestDTO.setDst_cooperates_keenly_with_companions(rs.getInt(17));
                mrDevelopmentalScreeningTestDTO.setDst_has_various_hobbies_collections(rs.getInt(18));
                mrDevelopmentalScreeningTestDTO.setDst_goes_about_town_freely(rs.getInt(19));
                mrDevelopmentalScreeningTestDTO.setDst_sex_differences_in_play_become_marked(rs.getInt(20));
                mrDevelopmentalScreeningTestDTO.setDst_can_stay_away_from_home(rs.getInt(21));
                mrDevelopmentalScreeningTestDTO.setDst_writes_occasional_short_letters(rs.getInt(22));
                mrDevelopmentalScreeningTestDTO.setDst_comprehends_social_situations(rs.getInt(23));
                mrDevelopmentalScreeningTestDTO.setDst_physical_feets_liked(rs.getInt(24));
                mrDevelopmentalScreeningTestDTO.setDst_able_to_discuss_problems(rs.getInt(25));
                mrDevelopmentalScreeningTestDTO.setDst_enjoys_books_newspapers_magazines(rs.getInt(26));
                mrDevelopmentalScreeningTestDTO.setDst_more_independent_in_spending(rs.getInt(27));
                mrDevelopmentalScreeningTestDTO.setDst_capable_of_self_criticism(rs.getInt(28));
            }


            cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_ThirteenYears_To_FifteenYears_Age_Persons_Select_Basedon_Person_Code(?)}");
            cs.setString(1, mrDevelopmentalScreeningTestDTO.getPersoncode());

            rs = cs.executeQuery();

            while (rs.next()) {
                mrDevelopmentalScreeningTestDTO.setDst_shows_foresight_planning_judgement(rs.getInt(2));
                mrDevelopmentalScreeningTestDTO.setDst_learns_from_experience(rs.getInt(3));
                mrDevelopmentalScreeningTestDTO.setDst_plays_difficult_games(rs.getInt(4));
                mrDevelopmentalScreeningTestDTO.setDst_interested_in_dressing_up(rs.getInt(5));
                mrDevelopmentalScreeningTestDTO.setDst_understands_abstract_ideas(rs.getInt(6));
                mrDevelopmentalScreeningTestDTO.setDst_makes_sensible_plans_for_future(rs.getInt(7));
                mrDevelopmentalScreeningTestDTO.setDst_follows_current_events(rs.getInt(8));
                mrDevelopmentalScreeningTestDTO.setDst_buys_own_clothing(rs.getInt(9));
                mrDevelopmentalScreeningTestDTO.setDst_systematises_own_work(rs.getInt(10));
                mrDevelopmentalScreeningTestDTO.setDst_purchases_for_others(rs.getInt(11));
            }

        } catch (SQLException sqlException) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            int exResult = ExceptionDAO.saveException(datasource, sqlException.toString(), "selectMRDevelopmentalScreeningTestDetails", "MRDevelopmentalScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestDAO", "selectMRDevelopmentalScreeningTestDetails");

        } catch (Exception exception) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "selectMRDevelopmentalScreeningTestDetails", "MRDevelopmentalScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestDAO", "selectMRDevelopmentalScreeningTestDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);

        }
        return mrDevelopmentalScreeningTestDTO;
    }

    /**
     * this method is used for updating the data.
     * @param datasource
     * @param mrDevelopmentalScreeningTestDTO
     */
    public void updateMRDevelopmentalScreeningTestDetails(DataSource datasource, MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cs = null;
        //Statement st=null;
        ResultSet rs = null;
        String code = mrDevelopmentalScreeningTestDTO.getPersoncode();
        String[] details = null;
        AppletAuthorityDAO dao = new AppletAuthorityDAO();
        // Created by mohan on 07/02/2012
        TransactionService transactionService = TransactionFactory.getTransaction();
        try {
            if (!checkForUser(datasource, mrDevelopmentalScreeningTestDTO.getPersoncode())) {
                //insert code
                insertMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO, request);
            } else {
                //update code

                /* Getting the categeoryIdncategoryCount */
                details = dao.getCategoryDetails(datasource, mrDevelopmentalScreeningTestDTO.getPersoncode());
                con = DBConnection.getConnection();
                con.setAutoCommit(false);


                cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_ThreeMonths_to_Eighten_Months_Age_Persons_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                cs.setString(1, mrDevelopmentalScreeningTestDTO.getPersoncode());
                cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_birth_cry_present());
                cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_equal_bilateral_movements());
                cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_responds_to_bell());
                cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_vocalises_sounds());
                cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_smiles_spontaneously());
                cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_eyes_following_moving_object());
                cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_head_steady());
                cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_reaches_for_objects());
                cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_laughs_loud());
                cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_recognises_mother());
                cs.setInt(12, mrDevelopmentalScreeningTestDTO.getDst_vocalises_for_pleasure_or_babble());
                cs.setInt(13, mrDevelopmentalScreeningTestDTO.getDst_carries_objects_to_mouth());
                cs.setInt(14, mrDevelopmentalScreeningTestDTO.getDst_rolls_over());
                cs.setInt(15, mrDevelopmentalScreeningTestDTO.getDst_imitates_speech_sounds());
                cs.setInt(16, mrDevelopmentalScreeningTestDTO.getDst_sits_by_self());
                cs.setInt(17, mrDevelopmentalScreeningTestDTO.getDst_thumb_finger_grasp());
                cs.setInt(18, mrDevelopmentalScreeningTestDTO.getDst_shows_curiousity());
                cs.setInt(19, mrDevelopmentalScreeningTestDTO.getDst_says_three_words());
                cs.setInt(20, mrDevelopmentalScreeningTestDTO.getDst_stands_alone_well());
                cs.setInt(21, mrDevelopmentalScreeningTestDTO.getDst_follows_simple_instructions());
                cs.setInt(22, mrDevelopmentalScreeningTestDTO.getDst_cooperates_for_dressing());
                cs.setInt(23, mrDevelopmentalScreeningTestDTO.getDst_many_intelligible_words());
                cs.setInt(24, mrDevelopmentalScreeningTestDTO.getDst_walks_runs_well());
                cs.setInt(25, mrDevelopmentalScreeningTestDTO.getDst_indicates_wants());
                cs.setInt(26, mrDevelopmentalScreeningTestDTO.getDst_scribbles_spontaneously());
                cs.setString(27, mrDevelopmentalScreeningTestDTO.getLoginid());
                cs.setString(28, mrDevelopmentalScreeningTestDTO.getIpaddress());

                cs.setInt(29, Integer.parseInt(details[0].toString()));
                cs.setInt(30, Integer.parseInt(details[1].toString()));


                cs.executeUpdate();

                cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_TwoYears_to_SixYears_Age_Persons_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, mrDevelopmentalScreeningTestDTO.getPersoncode());
                cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_says_sentences_of_twobythree_words());
                cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_points_out_objects_in_pictures());
                cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_shows_body_parts());
                cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_participates_in_play());
                cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_copies_o());
                cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_relates_experiences());
                cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_knows_names_uses_of_common_objects());
                cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_begins_to_ask_why());
                cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_takes_food_by_self());
                cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_toilet_control_present());
                cs.setInt(12, mrDevelopmentalScreeningTestDTO.getDst_buttons_up());
                cs.setInt(13, mrDevelopmentalScreeningTestDTO.getDst_comprehends_hunger_cold());
                cs.setInt(14, mrDevelopmentalScreeningTestDTO.getDst_plays_cooperatively_with_children());
                cs.setInt(15, mrDevelopmentalScreeningTestDTO.getDst_repeats_three_digits());
                cs.setInt(16, mrDevelopmentalScreeningTestDTO.getDst_tells_stories());
                cs.setInt(17, mrDevelopmentalScreeningTestDTO.getDst_defines_words());
                cs.setInt(18, mrDevelopmentalScreeningTestDTO.getDst_makes_simple_drawing());
                cs.setInt(19, mrDevelopmentalScreeningTestDTO.getDst_dresses_with_no_supervision());
                cs.setInt(20, mrDevelopmentalScreeningTestDTO.getDst_describes_actions_in_pictures());
                cs.setInt(21, mrDevelopmentalScreeningTestDTO.getDst_gives_sensible_answers_to_questions());
                cs.setInt(22, mrDevelopmentalScreeningTestDTO.getDst_goes_about_neighbourhood());
                cs.setInt(23, mrDevelopmentalScreeningTestDTO.getDst_can_name_primary_colours());
                cs.setInt(24, mrDevelopmentalScreeningTestDTO.getDst_plays_games_governed_by_rules());
                cs.setInt(25, mrDevelopmentalScreeningTestDTO.getDst_writes_simple_words());
                cs.setInt(26, mrDevelopmentalScreeningTestDTO.getDst_gains_admission_to_school());
                cs.setInt(27, mrDevelopmentalScreeningTestDTO.getDst_enjoys_constructive_play());
                cs.setString(28, mrDevelopmentalScreeningTestDTO.getLoginid());
                cs.setString(29, mrDevelopmentalScreeningTestDTO.getIpaddress());

                cs.setInt(30, Integer.parseInt(details[0].toString()));
                cs.setInt(31, Integer.parseInt(details[1].toString()));


                cs.executeUpdate();

                cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_SevenYears_to_TwelveYears_Age_Persons_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, mrDevelopmentalScreeningTestDTO.getPersoncode());
                cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_adapts_to_home_school());
                cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_tells_differences_of_objects());
                cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_spells_reads_writes_simple_words());
                cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_enjoys_group_play());
                cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_knows_comparative_value_of_coins());
                cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_combs_hair_by_self());
                cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_makes_small_purchases());
                cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_competition_in_school_or_play());
                cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_tells_time());
                cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_tells_day_month_year());
                cs.setInt(12, mrDevelopmentalScreeningTestDTO.getDst_reads_on_own_initiative());
                cs.setInt(13, mrDevelopmentalScreeningTestDTO.getDst_recognises_property_rights());
                cs.setInt(14, mrDevelopmentalScreeningTestDTO.getDst_favourite_of_fairy_tales());
                cs.setInt(15, mrDevelopmentalScreeningTestDTO.getDst_muscle_coordination_games());
                cs.setInt(16, mrDevelopmentalScreeningTestDTO.getDst_bathes_self_unaided());
                cs.setInt(17, mrDevelopmentalScreeningTestDTO.getDst_cooperates_keenly_with_companions());
                cs.setInt(18, mrDevelopmentalScreeningTestDTO.getDst_has_various_hobbies_collections());
                cs.setInt(19, mrDevelopmentalScreeningTestDTO.getDst_goes_about_town_freely());
                cs.setInt(20, mrDevelopmentalScreeningTestDTO.getDst_sex_differences_in_play_become_marked());
                cs.setInt(21, mrDevelopmentalScreeningTestDTO.getDst_can_stay_away_from_home());
                cs.setInt(22, mrDevelopmentalScreeningTestDTO.getDst_writes_occasional_short_letters());
                cs.setInt(23, mrDevelopmentalScreeningTestDTO.getDst_comprehends_social_situations());
                cs.setInt(24, mrDevelopmentalScreeningTestDTO.getDst_physical_feets_liked());
                cs.setInt(25, mrDevelopmentalScreeningTestDTO.getDst_able_to_discuss_problems());
                cs.setInt(26, mrDevelopmentalScreeningTestDTO.getDst_enjoys_books_newspapers_magazines());
                cs.setInt(27, mrDevelopmentalScreeningTestDTO.getDst_more_independent_in_spending());
                cs.setInt(28, mrDevelopmentalScreeningTestDTO.getDst_capable_of_self_criticism());
                cs.setString(29, mrDevelopmentalScreeningTestDTO.getLoginid());
                cs.setString(30, mrDevelopmentalScreeningTestDTO.getIpaddress());

                cs.setInt(31, Integer.parseInt(details[0].toString()));
                cs.setInt(32, Integer.parseInt(details[1].toString()));


                cs.executeUpdate();

                cs = con.prepareCall("{Call SP_tblMR_DST_Test_Details_for_ThirteenYears_To_FifteenYears_Age_Persons_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, mrDevelopmentalScreeningTestDTO.getPersoncode());
                cs.setInt(2, mrDevelopmentalScreeningTestDTO.getDst_shows_foresight_planning_judgement());
                cs.setInt(3, mrDevelopmentalScreeningTestDTO.getDst_learns_from_experience());
                cs.setInt(4, mrDevelopmentalScreeningTestDTO.getDst_plays_difficult_games());
                cs.setInt(5, mrDevelopmentalScreeningTestDTO.getDst_interested_in_dressing_up());
                cs.setInt(6, mrDevelopmentalScreeningTestDTO.getDst_understands_abstract_ideas());
                cs.setInt(7, mrDevelopmentalScreeningTestDTO.getDst_makes_sensible_plans_for_future());
                cs.setInt(8, mrDevelopmentalScreeningTestDTO.getDst_follows_current_events());
                cs.setInt(9, mrDevelopmentalScreeningTestDTO.getDst_buys_own_clothing());
                cs.setInt(10, mrDevelopmentalScreeningTestDTO.getDst_systematises_own_work());
                cs.setInt(11, mrDevelopmentalScreeningTestDTO.getDst_purchases_for_others());
                cs.setString(12, mrDevelopmentalScreeningTestDTO.getLoginid());
                cs.setString(13, mrDevelopmentalScreeningTestDTO.getIpaddress());

                cs.setInt(14, Integer.parseInt(details[0].toString()));
                cs.setInt(15, Integer.parseInt(details[1].toString()));


                cs.executeUpdate();

                con.commit();
                con.setAutoCommit(true);
            }
        } catch (SQLException sqlException) {
            // Added by mohan on 07/02/2012
            transactionService.updateTransactionDetails(datasource, "Error in updating MRDevelopmental Screening Test Details", code);
            // End
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            int exResult = ExceptionDAO.saveException(datasource, sqlException.toString(), "updateMRDevelopmentalScreeningTestDetails", "MRDevelopmentalScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestDAO", "updateMRDevelopmentalScreeningTestDetails");

        } catch (Exception exception) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "updateMRDevelopmentalScreeningTestDetails", "MRDevelopmentalScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestDAO", "updateMRDevelopmentalScreeningTestDetails");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);

        }
    }

    /**
     * 
     * @ This Method is use for Deleting the data.
     * @param datasource
     * @param mrDevelopmentalScreeningTestDTO
     */
    public void deleteMRDevelopmentalScreeningTestDetails(DataSource datasource, MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO) throws SADAREMDBException, SQLException {
        boolean code = false;
        Connection con = null;
        Statement stmt = null;


        try {
            CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, mrDevelopmentalScreeningTestDTO.getPersoncode(), "tblMR_DST_Test_Details_for_ThreeMonths_to_Eighten_Months_Age_Persons");
            CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, mrDevelopmentalScreeningTestDTO.getPersoncode(), "tblMR_DST_Test_Details_for_TwoYears_to_SixYears_Age_Persons");
            CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, mrDevelopmentalScreeningTestDTO.getPersoncode(), "tblMR_DST_Test_Details_for_SevenYears_to_TwelveYears_Age_Persons");
            CommonDAO.changeStatusToInactiveForResetInUpdate(datasource, mrDevelopmentalScreeningTestDTO.getPersoncode(), "tblMR_DST_Test_Details_for_ThirteenYears_To_FifteenYears_Age_Persons");

        } catch (Exception exception) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "deleteMRDevelopmentalScreeningTestDetails", "MRDevelopmentalScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestDAO", "deleteMRDevelopmentalScreeningTestDetails");

        } finally {
            DBConnection.closeConnection(con);

            DBConnection.closeStatement(stmt);

        }
    }

    /**
     * 
     * @ This Method is use for checking personcode.
     * @param datasource
     * @param personcode.
     */
    public boolean checkForUser(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        boolean code = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            String query = "select Person_Code from tblMR_DST_Test_Details_for_ThreeMonths_to_Eighten_Months_Age_Persons where Person_Code=? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();

            if (rs.next() == false) {
                code = false;
            } else {
                code = true;
            }
        } //end of try block
        catch (Exception exception) {
            int exResult = ExceptionDAO.saveException(datasource, exception.toString(), "checkForUser", "MRDevelopmentalScreeningTestDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MRDevelopmentalScreeningTestDAO", "checkForUser");

        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return code;
    }
}
