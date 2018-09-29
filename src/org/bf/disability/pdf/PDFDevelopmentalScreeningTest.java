/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.awt.Color;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.bf.disability.Constants.PDFLables;
import org.bf.disability.dto.MRDevelopmentalScreeningTestDTO;
import org.bf.disability.dto.MentalRetardationTestsDTO;

/**
 *
 * @author 509864
 */
public class PDFDevelopmentalScreeningTest {


     public PdfPTable getTable(HttpServletRequest request) throws DocumentException, IOException {
        float[] widths = {0.50f, 0.50f};
        PdfPTable table = new PdfPTable(widths);
        Font Font_Bold = null;
        Font Font_Normal = null;
        Font Font_Bold_Color = null;
        Font Font_Section_heading = null;
        try {
            table.setSplitRows(true);
            PDFCommonTemplate pDFCommonTemplate = new PDFCommonTemplate();
            MRDevelopmentalScreeningTestDTO mrDSTDTO = (MRDevelopmentalScreeningTestDTO) request.getAttribute("sectionObject");
            MentalRetardationTestsDTO mrtestdto = (MentalRetardationTestsDTO) request.getAttribute("DSTIQDetails");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));


            float[] dst_widths = {0.10f, 0.40f, 0.20f, 0.10f, 0.10f};
            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable dst_table = new PdfPTable(dst_widths);

            String[] List_3Mto1Y = {String.valueOf(mrDSTDTO.getDst_birth_cry_present()),String.valueOf(mrDSTDTO.getDst_equal_bilateral_movements()),
                                    String.valueOf(mrDSTDTO.getDst_responds_to_bell()),String.valueOf(mrDSTDTO.getDst_vocalises_sounds()),
                                    String.valueOf(mrDSTDTO.getDst_smiles_spontaneously()),String.valueOf(mrDSTDTO.getDst_eyes_following_moving_object()),
                                    String.valueOf(mrDSTDTO.getDst_head_steady()),String.valueOf(mrDSTDTO.getDst_reaches_for_objects()),
                                    String.valueOf(mrDSTDTO.getDst_laughs_loud()),String.valueOf(mrDSTDTO.getDst_recognises_mother()),
                                    String.valueOf(mrDSTDTO.getDst_vocalises_for_pleasure_or_babble()),String.valueOf(mrDSTDTO.getDst_carries_objects_to_mouth()),
                                    String.valueOf(mrDSTDTO.getDst_rolls_over()),String.valueOf(mrDSTDTO.getDst_imitates_speech_sounds()),
                                    String.valueOf(mrDSTDTO.getDst_sits_by_self()),String.valueOf(mrDSTDTO.getDst_thumb_finger_grasp()),
                                    String.valueOf(mrDSTDTO.getDst_shows_curiousity()),String.valueOf(mrDSTDTO.getDst_says_three_words()),
                                    String.valueOf(mrDSTDTO.getDst_stands_alone_well()),String.valueOf(mrDSTDTO.getDst_follows_simple_instructions()),
                                    String.valueOf(mrDSTDTO.getDst_cooperates_for_dressing()),String.valueOf(mrDSTDTO.getDst_many_intelligible_words()),
                                    String.valueOf(mrDSTDTO.getDst_walks_runs_well()),String.valueOf(mrDSTDTO.getDst_indicates_wants()),
                                    String.valueOf(mrDSTDTO.getDst_scribbles_spontaneously())};
            boolean  Flag_3Mto1Y = pDFCommonTemplate.getDisplayHeaderFlag(List_3Mto1Y, "0");

            if(Flag_3Mto1Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.THREE_MONTHS_ONEHALF_YEARS, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(5);
                dst_table.addCell(cell_one);
                
                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                dst_table.addCell(cell_one);
                
                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                dst_table.addCell(cell_one);
                
                cell_one = new PdfPCell(new Paragraph(PDFLables.DAYS, Font_Bold));
                cell_one.setBorder(0);
                dst_table.addCell(cell_one);


                
                String[][] DisplayList_3Mto1Y={{"",PDFLables.BIRTH_CRY_PRESENTS,String.valueOf(mrDSTDTO.getDst_birth_cry_present()),"",PDFLables.M_D_13},
                                               {"",PDFLables.EQUAL_BILATERAL_MOVEMENTS,String.valueOf(mrDSTDTO.getDst_equal_bilateral_movements()),"",PDFLables.M_D_26},{"",PDFLables.RESPONDS_TO_BELL,String.valueOf(mrDSTDTO.getDst_responds_to_bell()),"",PDFLables.M_D_39},
                                               {"",PDFLables.VOCALISES_SOUNDS,String.valueOf(mrDSTDTO.getDst_vocalises_sounds()),"",PDFLables.M_D_52},{"",PDFLables.SMILES_SPONTANEOUSLY,String.valueOf(mrDSTDTO.getDst_smiles_spontaneously()),"",PDFLables.M_D_65},
                                               {"",PDFLables.EYES_FOLLOW_MOVING_OBJECT,String.valueOf(mrDSTDTO.getDst_eyes_following_moving_object()),"",PDFLables.M_D_78},{PDFLables.M_3,PDFLables.HEAD_STEADY,String.valueOf(mrDSTDTO.getDst_head_steady()),"",PDFLables.M_D_90},
                                               {"",PDFLables.REACH_FOR_OBJECTS,String.valueOf(mrDSTDTO.getDst_reaches_for_objects()),"",PDFLables.M_D_15},{"",PDFLables.LAUGHS_ALOUD,String.valueOf(mrDSTDTO.getDst_laughs_loud()),"",PDFLables.M_D_30},
                                               {"",PDFLables.RECOGNISES_MOTHER,String.valueOf(mrDSTDTO.getDst_recognises_mother()),"",PDFLables.M_D_45},{"",PDFLables.VOCALISES_FOR_PLEASURE_BABBLE,String.valueOf(mrDSTDTO.getDst_vocalises_for_pleasure_or_babble()),"",PDFLables.M_D_60},
                                               {"",PDFLables.CARRIES_OBJECTS_TO_MOUTH,String.valueOf(mrDSTDTO.getDst_carries_objects_to_mouth()),"",PDFLables.M_D_75},{PDFLables.M_6,PDFLables.ROLLS_OVER,String.valueOf(mrDSTDTO.getDst_rolls_over()),"",PDFLables.M_D_90},
                                               {"",PDFLables.IMITATES_SPEECH_SOUNDS,String.valueOf(mrDSTDTO.getDst_imitates_speech_sounds()),"",PDFLables.M_D_23},{"",PDFLables.SITS_BY_SELF,String.valueOf(mrDSTDTO.getDst_sits_by_self()),"",PDFLables.M_D_46},
                                               {"",PDFLables.THUMB_FINGER_GRASP,String.valueOf(mrDSTDTO.getDst_thumb_finger_grasp()),"",PDFLables.M_D_68},{PDFLables.M_9,PDFLables.SHOWS_CURIOUSITY,String.valueOf(mrDSTDTO.getDst_shows_curiousity()),"",PDFLables.M_D_90},
                                               {"",PDFLables.SAYS_3_WORDS_DADA_MAMA_ETC,String.valueOf(mrDSTDTO.getDst_says_three_words()),"",PDFLables.M_D_23},{"",PDFLables.STANDS_ALONE_WELL,String.valueOf(mrDSTDTO.getDst_stands_alone_well()),"",PDFLables.M_D_46},
                                               {"",PDFLables.FOLLOWS_SIMPLE_INSTRUCTIONS,String.valueOf(mrDSTDTO.getDst_follows_simple_instructions()),"",PDFLables.M_D_68},{PDFLables.Y_1,PDFLables.CO_OPERATES_FOR_DRESSING,String.valueOf(mrDSTDTO.getDst_cooperates_for_dressing()),"",PDFLables.M_D_90},
                                               {"",PDFLables.MANY_INTELLIGIBLE_WORDS,String.valueOf(mrDSTDTO.getDst_many_intelligible_words()),PDFLables.M_D_1,PDFLables.M_D_15},{"",PDFLables.WALKS_RUNS_WELL,String.valueOf(mrDSTDTO.getDst_walks_runs_well()),PDFLables.M_D_3,""},
                                               {"",PDFLables.INDICATES_WANTS,String.valueOf(mrDSTDTO.getDst_indicates_wants()),PDFLables.M_D_4,PDFLables.M_D_15},{PDFLables.Y1_1_2,PDFLables.SCRIBBLES_SPONTANEOUSLY,String.valueOf(mrDSTDTO.getDst_scribbles_spontaneously()),PDFLables.M_D_6,""}
                                               };


               dst_table = pDFCommonTemplate.creatingMRThreeCells(dst_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_3Mto1Y);

            }

            String[] List_2Yto6Y = {String.valueOf(mrDSTDTO.getDst_says_sentences_of_twobythree_words()),String.valueOf(mrDSTDTO.getDst_points_out_objects_in_pictures()),
                                    String.valueOf(mrDSTDTO.getDst_shows_body_parts()),String.valueOf(mrDSTDTO.getDst_participates_in_play()),
                                    String.valueOf(mrDSTDTO.getDst_copies_o()),String.valueOf(mrDSTDTO.getDst_relates_experiences()),
                                    String.valueOf(mrDSTDTO.getDst_knows_names_uses_of_common_objects()),String.valueOf(mrDSTDTO.getDst_begins_to_ask_why()),
                                    String.valueOf(mrDSTDTO.getDst_takes_food_by_self()),String.valueOf(mrDSTDTO.getDst_toilet_control_present()),
                                    String.valueOf(mrDSTDTO.getDst_buttons_up()),String.valueOf(mrDSTDTO.getDst_comprehends_hunger_cold()),
                                    String.valueOf(mrDSTDTO.getDst_plays_cooperatively_with_children()),String.valueOf(mrDSTDTO.getDst_repeats_three_digits()),
                                    String.valueOf(mrDSTDTO.getDst_tells_stories()),String.valueOf(mrDSTDTO.getDst_defines_words()),
                                    String.valueOf(mrDSTDTO.getDst_makes_simple_drawing()),String.valueOf(mrDSTDTO.getDst_dresses_with_no_supervision()),
                                    String.valueOf(mrDSTDTO.getDst_describes_actions_in_pictures()),String.valueOf(mrDSTDTO.getDst_gives_sensible_answers_to_questions()),
                                    String.valueOf(mrDSTDTO.getDst_goes_about_neighbourhood()),String.valueOf(mrDSTDTO.getDst_can_name_primary_colours()),
                                    String.valueOf(mrDSTDTO.getDst_plays_games_governed_by_rules()),String.valueOf(mrDSTDTO.getDst_writes_simple_words()),
                                    String.valueOf(mrDSTDTO.getDst_gains_admission_to_school()),String.valueOf(mrDSTDTO.getDst_enjoys_constructive_play())};
            boolean  Flag_2Yto6Y = pDFCommonTemplate.getDisplayHeaderFlag(List_2Yto6Y, "0");

            if(Flag_2Yto6Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.Y2_Y6, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(5);
                dst_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                dst_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                dst_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.DAYS, Font_Bold));
                cell_one.setBorder(0);
                dst_table.addCell(cell_one);



                String[][] DisplayList_2Yto6Y={{"",PDFLables.SAYS_SENTENCES_OF_2_3_WORDS,String.valueOf(mrDSTDTO.getDst_says_sentences_of_twobythree_words()),PDFLables.M_D_1,PDFLables.M_D_15},
                                               {"",PDFLables.POINTS_OUT_OBJECTS_IN_PICTURES,String.valueOf(mrDSTDTO.getDst_points_out_objects_in_pictures()),PDFLables.M_D_3,""},{"",PDFLables.SHOWS_BODY_PARTS,String.valueOf(mrDSTDTO.getDst_shows_body_parts()),PDFLables.M_D_4,PDFLables.M_D_15},
                                               {"",PDFLables.PARTICIPATES_IN_PALY,String.valueOf(mrDSTDTO.getDst_participates_in_play()),"",PDFLables.M_D_6},{"",PDFLables.COPIES_0,String.valueOf(mrDSTDTO.getDst_copies_o()),PDFLables.M_D_2,""},
                                               {"",PDFLables.RELATES_EXPERIENCES,String.valueOf(mrDSTDTO.getDst_relates_experiences()),PDFLables.M_D_4,""},{"",PDFLables.KNOWS_NAMES_USES_OF_COMMON_OBJECTS,String.valueOf(mrDSTDTO.getDst_knows_names_uses_of_common_objects()),PDFLables.M_D_6,""},
                                               {"",PDFLables.BEGINS_TO_ASK_WHY,String.valueOf(mrDSTDTO.getDst_begins_to_ask_why()),PDFLables.M_D_8,""},{"",PDFLables.TAKES_FOOD_BY_SELF,String.valueOf(mrDSTDTO.getDst_takes_food_by_self()),PDFLables.M_D_10,""},
                                               {PDFLables.Y_3,PDFLables.TOILET_CONTROL_PRESENT,String.valueOf(mrDSTDTO.getDst_toilet_control_present()),PDFLables.M_D_12,""},{"",PDFLables.BUTTONS_UP,String.valueOf(mrDSTDTO.getDst_buttons_up()),"",PDFLables.M_D_60},
                                               {"",PDFLables.COMPREHENDS_HUNGER_COLD,String.valueOf(mrDSTDTO.getDst_comprehends_hunger_cold()),PDFLables.M_D_4,PDFLables.M_D_24},{"",PDFLables.PLAYS_COOPERATIVELY_WITH_CHILDREN,String.valueOf(mrDSTDTO.getDst_plays_cooperatively_with_children()),PDFLables.M_D_7,PDFLables.M_D_6},
                                               {"",PDFLables.REPEATS_3_DIGITS,String.valueOf(mrDSTDTO.getDst_repeats_three_digits()),PDFLables.M_D_9,PDFLables.M_D_18},{PDFLables.Y_4,PDFLables.TELLS_STORIES,String.valueOf(mrDSTDTO.getDst_tells_stories()),PDFLables.M_D_12,""},
                                               {"",PDFLables.DEFINES_WORDS,String.valueOf(mrDSTDTO.getDst_defines_words()),PDFLables.M_D_2,""},{"",PDFLables.MAKES_SIMPLE_DRAWING,String.valueOf(mrDSTDTO.getDst_makes_simple_drawing()),PDFLables.M_D_4,""},
                                               {"",PDFLables.DRESSES_WITH_NO_SUPERVISION,String.valueOf(mrDSTDTO.getDst_dresses_with_no_supervision()),PDFLables.M_D_6,""},{"",PDFLables.DESCRIBES_ACTIONS_IN_PICTURES,String.valueOf(mrDSTDTO.getDst_describes_actions_in_pictures()),PDFLables.M_D_8,""},
                                               {"",PDFLables.GIVES_SENSIBLE_ANSWERS_TO_QUESTIONS,String.valueOf(mrDSTDTO.getDst_gives_sensible_answers_to_questions()),PDFLables.M_D_10,""},{PDFLables.Y_5,PDFLables.GOES_ABOUT_NEIGHBOURHOOD,String.valueOf(mrDSTDTO.getDst_goes_about_neighbourhood()),PDFLables.M_D_12,""},
                                               {"",PDFLables.CAN_NAME_PRIMARY_COLOURS,String.valueOf(mrDSTDTO.getDst_can_name_primary_colours()),PDFLables.M_D_2,PDFLables.M_D_12},{"",PDFLables.PLAYS_GAMES_GOVERNED_BY_RULES,String.valueOf(mrDSTDTO.getDst_plays_games_governed_by_rules()),PDFLables.M_D_4,PDFLables.M_D_24},
                                               {"",PDFLables.WRITES_SIMPLE_WORDS,String.valueOf(mrDSTDTO.getDst_writes_simple_words()),PDFLables.M_D_7,PDFLables.M_D_6},{"",PDFLables.GAINS_ADMISSION_TO_SCHOOL,String.valueOf(mrDSTDTO.getDst_gains_admission_to_school()),PDFLables.M_D_9,PDFLables.M_D_18},
                                               {PDFLables.Y_6,PDFLables.ENJOYS_CONSTRUCTIVE_PLAY,String.valueOf(mrDSTDTO.getDst_enjoys_constructive_play()),PDFLables.M_D_12,""}
                                               };


               dst_table = pDFCommonTemplate.creatingMRThreeCells(dst_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_2Yto6Y);

            }


            String[] List_7Yto12Y = {String.valueOf(mrDSTDTO.getDst_adapts_to_home_school()),String.valueOf(mrDSTDTO.getDst_tells_differences_of_objects()),
                                    String.valueOf(mrDSTDTO.getDst_spells_reads_writes_simple_words()),String.valueOf(mrDSTDTO.getDst_enjoys_group_play()),
                                    String.valueOf(mrDSTDTO.getDst_knows_comparative_value_of_coins()),String.valueOf(mrDSTDTO.getDst_combs_hair_by_self()),
                                    String.valueOf(mrDSTDTO.getDst_makes_small_purchases()),String.valueOf(mrDSTDTO.getDst_competition_in_school_or_play()),
                                    String.valueOf(mrDSTDTO.getDst_tells_time()),String.valueOf(mrDSTDTO.getDst_tells_day_month_year()),
                                    String.valueOf(mrDSTDTO.getDst_reads_on_own_initiative()),String.valueOf(mrDSTDTO.getDst_recognises_property_rights()),
                                    String.valueOf(mrDSTDTO.getDst_favourite_of_fairy_tales()),String.valueOf(mrDSTDTO.getDst_muscle_coordination_games()),
                                    String.valueOf(mrDSTDTO.getDst_bathes_self_unaided()),String.valueOf(mrDSTDTO.getDst_cooperates_keenly_with_companions()),
                                    String.valueOf(mrDSTDTO.getDst_has_various_hobbies_collections()),String.valueOf(mrDSTDTO.getDst_goes_about_town_freely()),
                                    String.valueOf(mrDSTDTO.getDst_sex_differences_in_play_become_marked()),String.valueOf(mrDSTDTO.getDst_can_stay_away_from_home()),
                                    String.valueOf(mrDSTDTO.getDst_writes_occasional_short_letters()),String.valueOf(mrDSTDTO.getDst_comprehends_social_situations()),
                                    String.valueOf(mrDSTDTO.getDst_physical_feets_liked()),String.valueOf(mrDSTDTO.getDst_able_to_discuss_problems()),
                                    String.valueOf(mrDSTDTO.getDst_enjoys_books_newspapers_magazines()),String.valueOf(mrDSTDTO.getDst_more_independent_in_spending()),
                                    String.valueOf(mrDSTDTO.getDst_capable_of_self_criticism())};
            boolean  Flag_7Yto12Y = pDFCommonTemplate.getDisplayHeaderFlag(List_7Yto12Y, "0");

            if(Flag_7Yto12Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.Y7_Y12, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(5);
                dst_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                dst_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                dst_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.DAYS, Font_Bold));
                cell_one.setBorder(0);
                dst_table.addCell(cell_one);



                String[][] DisplayList_7Yto12Y={{"",PDFLables.ADAPTS_TO_HOME_SCHOOL,String.valueOf(mrDSTDTO.getDst_adapts_to_home_school()),PDFLables.M_D_2,PDFLables.M_D_12},
                                               {"",PDFLables.TELLS_DIFFERENCES_OF_OBJECTS,String.valueOf(mrDSTDTO.getDst_tells_differences_of_objects()),PDFLables.M_D_4,PDFLables.M_D_24},{"",PDFLables.SPELLS_READS_WRITES_SIMPLE_WORDS,String.valueOf(mrDSTDTO.getDst_spells_reads_writes_simple_words()),PDFLables.M_D_7,PDFLables.M_D_6},
                                               {"",PDFLables.ENJOYS_GROUP_PLAY,String.valueOf(mrDSTDTO.getDst_enjoys_group_play()),PDFLables.M_D_9,PDFLables.M_D_18},{PDFLables.Y_7,PDFLables.KNOWS_COMPARATIVE_VALUE_OF_COINS,String.valueOf(mrDSTDTO.getDst_knows_comparative_value_of_coins()),PDFLables.M_D_12,""},
                                               {"",PDFLables.COMBS_HAIR_BY_SELF,String.valueOf(mrDSTDTO.getDst_combs_hair_by_self()),PDFLables.M_D_3,""},{"",PDFLables.MAKES_SMALL_PURCHASES,String.valueOf(mrDSTDTO.getDst_makes_small_purchases()),PDFLables.M_D_6,""},
                                               {"",PDFLables.COMPETITION_IN_SCHOOL_PLAY,String.valueOf(mrDSTDTO.getDst_competition_in_school_or_play()),PDFLables.M_D_9,""},{PDFLables.Y_8,PDFLables.TELLS_TIME,String.valueOf(mrDSTDTO.getDst_tells_time()),PDFLables.M_D_12,""},
                                               {"",PDFLables.TELLS_DAY_MONTH_YEAR,String.valueOf(mrDSTDTO.getDst_tells_day_month_year()),PDFLables.M_D_2,""},{"",PDFLables.READS_ON_OWN_INITIATIVE,String.valueOf(mrDSTDTO.getDst_reads_on_own_initiative()),PDFLables.M_D_4,""},
                                               {"",PDFLables.RECOGNISES_PROPERTY_RIGHTS,String.valueOf(mrDSTDTO.getDst_recognises_property_rights()),PDFLables.M_D_6,""},{"",PDFLables.FAVOURITE_OF_FAIRY_TALES,String.valueOf(mrDSTDTO.getDst_favourite_of_fairy_tales()),PDFLables.M_D_8,""},
                                               {"",PDFLables.MUSCLE_COORDINATION_GAMES,String.valueOf(mrDSTDTO.getDst_muscle_coordination_games()),PDFLables.M_D_10,""},{PDFLables.Y_9,PDFLables.BATHES_SELF_UNAIDED,String.valueOf(mrDSTDTO.getDst_bathes_self_unaided()),PDFLables.M_D_12,""},
                                               {"",PDFLables.COOPERATES_KEENLY_WITH_COMPANIONS,String.valueOf(mrDSTDTO.getDst_cooperates_keenly_with_companions()),PDFLables.M_D_2,PDFLables.M_D_12},{"",PDFLables.HAS_VARIOUS_HOBBIES_COLLECTIONS,String.valueOf(mrDSTDTO.getDst_has_various_hobbies_collections()),PDFLables.M_D_4,PDFLables.M_D_24},
                                               {"",PDFLables.GOES_ABOUT_TOWN_FREELY,String.valueOf(mrDSTDTO.getDst_goes_about_town_freely()),PDFLables.M_D_7,PDFLables.M_D_6},{"",PDFLables.SEX_DIFFERENCES_IN_PLAY_BECOME_MARKED,String.valueOf(mrDSTDTO.getDst_sex_differences_in_play_become_marked()),PDFLables.M_D_9,PDFLables.M_D_18},
                                               {PDFLables.Y_10,PDFLables.CAN_STAY_AWAY_FROM_HOME_CAMPS,String.valueOf(mrDSTDTO.getDst_can_stay_away_from_home()),PDFLables.M_D_12,""},{"",PDFLables.WRITES_OCCASIONAL_SHORT_LETTERS,String.valueOf(mrDSTDTO.getDst_writes_occasional_short_letters()),PDFLables.M_D_3,""},
                                               {"",PDFLables.COMPREHENDS_SOCIAL_SITUATIONS,String.valueOf(mrDSTDTO.getDst_comprehends_social_situations()),PDFLables.M_D_6,""},{"",PDFLables.PHYSICAL_FEATS_LIKED,String.valueOf(mrDSTDTO.getDst_physical_feets_liked()),PDFLables.M_D_9,""},
                                               {PDFLables.Y_11,PDFLables.ABLE_TO_DISCUSES_PROBLEMS,String.valueOf(mrDSTDTO.getDst_able_to_discuss_problems()),PDFLables.M_D_12,""},{"",PDFLables.ENJOYS_BOOKS_NEWSPAPERS_MAGAZINES,String.valueOf(mrDSTDTO.getDst_enjoys_books_newspapers_magazines()),PDFLables.M_D_4,""},
                                               {"",PDFLables.MORE_INDEPENDENT_IN_SPENDING,String.valueOf(mrDSTDTO.getDst_more_independent_in_spending()),PDFLables.M_D_8,""},{PDFLables.Y_12,PDFLables.CAPABLE_OF_SELF_CRITICISM,String.valueOf(mrDSTDTO.getDst_capable_of_self_criticism()),PDFLables.M_D_12,""}
                                               };


               dst_table = pDFCommonTemplate.creatingMRThreeCells(dst_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_7Yto12Y);

            }

             String[] List_13Yto15Y = {String.valueOf(mrDSTDTO.getDst_shows_foresight_planning_judgement()),String.valueOf(mrDSTDTO.getDst_learns_from_experience()),
                                    String.valueOf(mrDSTDTO.getDst_plays_difficult_games()),String.valueOf(mrDSTDTO.getDst_interested_in_dressing_up()),
                                    String.valueOf(mrDSTDTO.getDst_understands_abstract_ideas()),String.valueOf(mrDSTDTO.getDst_makes_sensible_plans_for_future()),
                                    String.valueOf(mrDSTDTO.getDst_follows_current_events()),String.valueOf(mrDSTDTO.getDst_buys_own_clothing()),
                                    String.valueOf(mrDSTDTO.getDst_systematises_own_work()),String.valueOf(mrDSTDTO.getDst_purchases_for_others())};
            boolean  Flag_13Yto15Y = pDFCommonTemplate.getDisplayHeaderFlag(List_13Yto15Y, "0");

            if(Flag_13Yto15Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.Y13_Y15, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(5);
                dst_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                dst_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                dst_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.DAYS, Font_Bold));
                cell_one.setBorder(0);
                dst_table.addCell(cell_one);



                String[][] DisplayList_13Yto15Y={{"",PDFLables.SHOWS_FORESIGHT_PLANNING_JUDGEMENT,String.valueOf(mrDSTDTO.getDst_shows_foresight_planning_judgement()),PDFLables.M_D_2,PDFLables.M_D_12},
                                               {"",PDFLables.LEARNS_FROM_EXPERIENCE,String.valueOf(mrDSTDTO.getDst_learns_from_experience()),PDFLables.M_D_4,PDFLables.M_D_24},{"",PDFLables.PLAYS_DIFFICULT_GAMES,String.valueOf(mrDSTDTO.getDst_plays_difficult_games()),PDFLables.M_D_7,PDFLables.M_D_6},
                                               {"",PDFLables.INTERESTED_IN_DRESSING_UP,String.valueOf(mrDSTDTO.getDst_interested_in_dressing_up()),PDFLables.M_D_9,PDFLables.M_D_18},{PDFLables.Y_13,PDFLables.UNDERSTANDS_ABSTRACT_IDEAS_JUSTICE,String.valueOf(mrDSTDTO.getDst_understands_abstract_ideas()),PDFLables.M_D_12,""},
                                               {"",PDFLables.MAKES_SENSIBLE_PLANS_FOR_FUTURE_JOB,String.valueOf(mrDSTDTO.getDst_makes_sensible_plans_for_future()),PDFLables.M_D_4,PDFLables.M_D_24},{"",PDFLables.FOLLOWS_CURRENT_EVENTS,String.valueOf(mrDSTDTO.getDst_follows_current_events()),PDFLables.M_D_9,PDFLables.M_D_18},
                                               {"",PDFLables.BUYS_OWN_CLOTHING,String.valueOf(mrDSTDTO.getDst_buys_own_clothing()),PDFLables.M_D_1Y_2M,PDFLables.M_D_12},{"",PDFLables.SYSTEMATISES_OWN_WORK,String.valueOf(mrDSTDTO.getDst_systematises_own_work()),PDFLables.M_D_1Y_7M,PDFLables.M_D_6},
                                               {PDFLables.Y_15,PDFLables.PURCHASES_FOR_OTHERS,String.valueOf(mrDSTDTO.getDst_purchases_for_others()),PDFLables.M_D_2Y,""}
                                               };


               dst_table = pDFCommonTemplate.creatingMRThreeCells(dst_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_13Yto15Y);

            }
                 cell_one = new PdfPCell(dst_table);
                 cell_one.setBorder(0);
                 cell_one.setColspan(2);
                 table.addCell(cell_one);

            if(null != mrtestdto && !"".equals(mrtestdto)){

                cell_one = new PdfPCell(new Paragraph(PDFLables.DEVELOPMENTAL_AGE, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS+"    "+mrtestdto.getDstyear(), Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                 cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS+"    "+mrtestdto.getDstmonth(), Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);


                 cell_one = new PdfPCell(new Paragraph(PDFLables.IQ, Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(String.valueOf(Math.round(mrtestdto.getDstdq())), Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);


            }

               


            table.setSplitRows(true);
            table.setExtendLastRow(false);
            table.setKeepTogether(true);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }


}
