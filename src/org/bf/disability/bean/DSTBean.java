/*
 * DSTBean.java
 *
 * Created on January 5, 2009, 11:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.bean;

/**
 *
 * @author kiran_h
 */
public class DSTBean {
    
    
    private int dst_birth_cry_present;
    private int dst_equal_bilateral_movements;
    private int dst_responds_to_bell;
    private int dst_vocalises_sounds;
    private int dst_smiles_spontaneously;
    private int dst_eyes_following_moving_object;
    private int dst_head_steady;
    private int dst_reaches_for_objects;
    private int dst_laughs_loud;
    private int dst_recognises_mother;
    private int dst_vocalises_for_pleasure_or_babble;
    private int dst_carries_objects_to_mouth;
    private int dst_rolls_over;
    
    private int dst_imitates_speech_sounds;
    private int dst_sits_by_self;
    private int dst_thumb_finger_grasp;
    private int dst_shows_curiousity;
    private int dst_says_three_words;
    private int dst_stands_alone_well;
    private int dst_follows_simple_instructions;
    private int dst_cooperates_for_dressing;
    private int dst_many_intelligible_words;
    private int dst_walks_runs_well;
    private int dst_indicates_wants;
    private int dst_scribbles_spontaneously;
    
    
    private int dst_says_sentences_of_twobythree_words;
    private int dst_points_out_objects_in_pictures;
    private int dst_shows_body_parts;
    private int dst_participates_in_play;
    private int dst_copies_o;
    private int dst_relates_experiences;
    private int dst_knows_names_uses_of_common_objects;
    private int dst_begins_to_ask_why;
    private int dst_takes_food_by_self;
    private int dst_toilet_control_present;
    private int dst_buttons_up;
    private int dst_comprehends_hunger_cold;
    private int dst_plays_cooperatively_with_children;
    private int dst_repeats_three_digits;
    private int dst_tells_stories;
    
    
    private int dst_defines_words;
    private int dst_makes_simple_drawing;
    private int dst_dresses_with_no_supervision;
    private int dst_describes_actions_in_pictures;
    private int dst_gives_sensible_answers_to_questions;
    private int dst_goes_about_neighbourhood;
    private int dst_can_name_primary_colours;
    private int dst_plays_games_governed_by_rules;
    private int dst_writes_simple_words;
    private int dst_gains_admission_to_school;
    private int dst_enjoys_constructive_play;
    
    
    private int dst_adapts_to_home_school;
    private int dst_tells_differences_of_objects;
    private int dst_spells_reads_writes_simple_words;
    private int dst_enjoys_group_play;
    private int dst_knows_comparative_value_of_coins;
    private int dst_combs_hair_by_self;
    private int dst_makes_small_purchases;
    private int dst_competition_in_school_or_play;
    private int dst_tells_time;
    private int dst_tells_day_month_year;
    private int dst_reads_on_own_initiative;
    private int dst_recognises_property_rights;
    private int dst_favourite_of_fairy_tales;
    private int dst_muscle_coordination_games;
    private int dst_bathes_self_unaided;
    
    
    
    private int dst_cooperates_keenly_with_companions;
    private int dst_has_various_hobbies_collections;
    private int dst_goes_about_town_freely;
    private int dst_sex_differences_in_play_become_marked;
    private int dst_can_stay_away_from_home;
    private int dst_writes_occasional_short_letters;
    private int dst_comprehends_social_situations;
    private int dst_physical_feets_liked;
    private int dst_able_to_discuss_problems;
    private int dst_enjoys_books_newspapers_magazines;
    private int dst_more_independent_in_spending;
    private int dst_capable_of_self_criticism;
    
    
    private int dst_shows_foresight_planning_judgement;
    private int dst_learns_from_experience;
    private int dst_plays_difficult_games;
    private int dst_interested_in_dressing_up;
    private int dst_understands_abstract_ideas;
    private int dst_makes_sensible_plans_for_future;
    private int dst_follows_current_events;
    private int dst_buys_own_clothing;
    private int dst_systematises_own_work;
    private int dst_purchases_for_others;
    
    private String personcode;
    private String loginid;
    private String ipaddress;

    // Variables for Printing purpose
    
    private int threemonths;
    private int sixmonths;
    private int ninemonths;
    private int oneyear;
    private int oneandhalfyear;
    private int twoyears;
    private int threeyears;
    private int fouryears;
    private int fiveyears;
    private int sixyears;
    private int sevenyears;
    private int eightyears;
    private int nineyears;
    private int tenyears;
    private int elevenyears;
    private int tweleveyears;
    private int thirteenyears;
   
    private int fifteenyears;
    private StringBuffer totaldst=new StringBuffer();
    
    private boolean threetosixmonths;
    private boolean ninemonthstooneandhalfyear;
    private boolean threemonthstooneandhalfyear;
    
    private boolean twoyearstofouryears;
    private boolean fiveyearstosixyears;
    private boolean twoyearstosixyears;
   
    private boolean seventonineyears;
    private boolean tenyearstotwelveyears;
    private boolean seventotweleveyears;
    
    private StringBuffer noofyears;
    private StringBuffer noofmonths;
    
    
    private String dateofbirth;
    private String todaysdate;
    private String chronologicalage;
    private String mentalage;
    private StringBuffer iq;
   
    
    public int getDst_birth_cry_present() {
        return dst_birth_cry_present;
    }

    public void setDst_birth_cry_present(int dst_birth_cry_present) {
        this.dst_birth_cry_present = dst_birth_cry_present;
    }

    public int getDst_equal_bilateral_movements() {
        return dst_equal_bilateral_movements;
    }

    public void setDst_equal_bilateral_movements(int dst_equal_bilateral_movements) {
        this.dst_equal_bilateral_movements = dst_equal_bilateral_movements;
    }

    public int getDst_responds_to_bell() {
        return dst_responds_to_bell;
    }

    public void setDst_responds_to_bell(int dst_responds_to_bell) {
        this.dst_responds_to_bell = dst_responds_to_bell;
    }

    public int getDst_vocalises_sounds() {
        return dst_vocalises_sounds;
    }

    public void setDst_vocalises_sounds(int dst_vocalises_sounds) {
        this.dst_vocalises_sounds = dst_vocalises_sounds;
    }

    public int getDst_smiles_spontaneously() {
        return dst_smiles_spontaneously;
    }

    public void setDst_smiles_spontaneously(int dst_smiles_spontaneously) {
        this.dst_smiles_spontaneously = dst_smiles_spontaneously;
    }

    public int getDst_eyes_following_moving_object() {
        return dst_eyes_following_moving_object;
    }

    public void setDst_eyes_following_moving_object(int dst_eyes_following_moving_object) {
        this.dst_eyes_following_moving_object = dst_eyes_following_moving_object;
    }

    public int getDst_head_steady() {
        return dst_head_steady;
    }

    public void setDst_head_steady(int dst_head_steady) {
        this.dst_head_steady = dst_head_steady;
    }

    public int getDst_reaches_for_objects() {
        return dst_reaches_for_objects;
    }

    public void setDst_reaches_for_objects(int dst_reaches_for_objects) {
        this.dst_reaches_for_objects = dst_reaches_for_objects;
    }

    public int getDst_laughs_loud() {
        return dst_laughs_loud;
    }

    public void setDst_laughs_loud(int dst_laughs_loud) {
        this.dst_laughs_loud = dst_laughs_loud;
    }

    public int getDst_recognises_mother() {
        return dst_recognises_mother;
    }

    public void setDst_recognises_mother(int dst_recognises_mother) {
        this.dst_recognises_mother = dst_recognises_mother;
    }

    public int getDst_vocalises_for_pleasure_or_babble() {
        return dst_vocalises_for_pleasure_or_babble;
    }

    public void setDst_vocalises_for_pleasure_or_babble(int dst_vocalises_for_pleasure_or_babble) {
        this.dst_vocalises_for_pleasure_or_babble = dst_vocalises_for_pleasure_or_babble;
    }

    public int getDst_carries_objects_to_mouth() {
        return dst_carries_objects_to_mouth;
    }

    public void setDst_carries_objects_to_mouth(int dst_carries_objects_to_mouth) {
        this.dst_carries_objects_to_mouth = dst_carries_objects_to_mouth;
    }

    public int getDst_rolls_over() {
        return dst_rolls_over;
    }

    public void setDst_rolls_over(int dst_rolls_over) {
        this.dst_rolls_over = dst_rolls_over;
    }

    public int getDst_imitates_speech_sounds() {
        return dst_imitates_speech_sounds;
    }

    public void setDst_imitates_speech_sounds(int dst_imitates_speech_sounds) {
        this.dst_imitates_speech_sounds = dst_imitates_speech_sounds;
    }

    public int getDst_sits_by_self() {
        return dst_sits_by_self;
    }

    public void setDst_sits_by_self(int dst_sits_by_self) {
        this.dst_sits_by_self = dst_sits_by_self;
    }

    public int getDst_thumb_finger_grasp() {
        return dst_thumb_finger_grasp;
    }

    public void setDst_thumb_finger_grasp(int dst_thumb_finger_grasp) {
        this.dst_thumb_finger_grasp = dst_thumb_finger_grasp;
    }

    public int getDst_shows_curiousity() {
        return dst_shows_curiousity;
    }

    public void setDst_shows_curiousity(int dst_shows_curiousity) {
        this.dst_shows_curiousity = dst_shows_curiousity;
    }

    public int getDst_says_three_words() {
        return dst_says_three_words;
    }

    public void setDst_says_three_words(int dst_says_three_words) {
        this.dst_says_three_words = dst_says_three_words;
    }

    public int getDst_stands_alone_well() {
        return dst_stands_alone_well;
    }

    public void setDst_stands_alone_well(int dst_stands_alone_well) {
        this.dst_stands_alone_well = dst_stands_alone_well;
    }

    public int getDst_follows_simple_instructions() {
        return dst_follows_simple_instructions;
    }

    public void setDst_follows_simple_instructions(int dst_follows_simple_instructions) {
        this.dst_follows_simple_instructions = dst_follows_simple_instructions;
    }

    public int getDst_cooperates_for_dressing() {
        return dst_cooperates_for_dressing;
    }

    public void setDst_cooperates_for_dressing(int dst_cooperates_for_dressing) {
        this.dst_cooperates_for_dressing = dst_cooperates_for_dressing;
    }

    public int getDst_many_intelligible_words() {
        return dst_many_intelligible_words;
    }

    public void setDst_many_intelligible_words(int dst_many_intelligible_words) {
        this.dst_many_intelligible_words = dst_many_intelligible_words;
    }

    public int getDst_walks_runs_well() {
        return dst_walks_runs_well;
    }

    public void setDst_walks_runs_well(int dst_walks_runs_well) {
        this.dst_walks_runs_well = dst_walks_runs_well;
    }

    public int getDst_indicates_wants() {
        return dst_indicates_wants;
    }

    public void setDst_indicates_wants(int dst_indicates_wants) {
        this.dst_indicates_wants = dst_indicates_wants;
    }

    public int getDst_scribbles_spontaneously() {
        return dst_scribbles_spontaneously;
    }

    public void setDst_scribbles_spontaneously(int dst_scribbles_spontaneously) {
        this.dst_scribbles_spontaneously = dst_scribbles_spontaneously;
    }

    public int getDst_says_sentences_of_twobythree_words() {
        return dst_says_sentences_of_twobythree_words;
    }

    public void setDst_says_sentences_of_twobythree_words(int dst_says_sentences_of_twobythree_words) {
        this.dst_says_sentences_of_twobythree_words = dst_says_sentences_of_twobythree_words;
    }

    public int getDst_points_out_objects_in_pictures() {
        return dst_points_out_objects_in_pictures;
    }

    public void setDst_points_out_objects_in_pictures(int dst_points_out_objects_in_pictures) {
        this.dst_points_out_objects_in_pictures = dst_points_out_objects_in_pictures;
    }

    public int getDst_shows_body_parts() {
        return dst_shows_body_parts;
    }

    public void setDst_shows_body_parts(int dst_shows_body_parts) {
        this.dst_shows_body_parts = dst_shows_body_parts;
    }

    public int getDst_participates_in_play() {
        return dst_participates_in_play;
    }

    public void setDst_participates_in_play(int dst_participates_in_play) {
        this.dst_participates_in_play = dst_participates_in_play;
    }

    public int getDst_copies_o() {
        return dst_copies_o;
    }

    public void setDst_copies_o(int dst_copies_o) {
        this.dst_copies_o = dst_copies_o;
    }

    public int getDst_relates_experiences() {
        return dst_relates_experiences;
    }

    public void setDst_relates_experiences(int dst_relates_experiences) {
        this.dst_relates_experiences = dst_relates_experiences;
    }

    public int getDst_knows_names_uses_of_common_objects() {
        return dst_knows_names_uses_of_common_objects;
    }

    public void setDst_knows_names_uses_of_common_objects(int dst_knows_names_uses_of_common_objects) {
        this.dst_knows_names_uses_of_common_objects = dst_knows_names_uses_of_common_objects;
    }

    public int getDst_begins_to_ask_why() {
        return dst_begins_to_ask_why;
    }

    public void setDst_begins_to_ask_why(int dst_begins_to_ask_why) {
        this.dst_begins_to_ask_why = dst_begins_to_ask_why;
    }

    public int getDst_takes_food_by_self() {
        return dst_takes_food_by_self;
    }

    public void setDst_takes_food_by_self(int dst_takes_food_by_self) {
        this.dst_takes_food_by_self = dst_takes_food_by_self;
    }

    public int getDst_toilet_control_present() {
        return dst_toilet_control_present;
    }

    public void setDst_toilet_control_present(int dst_toilet_control_present) {
        this.dst_toilet_control_present = dst_toilet_control_present;
    }

    public int getDst_buttons_up() {
        return dst_buttons_up;
    }

    public void setDst_buttons_up(int dst_buttons_up) {
        this.dst_buttons_up = dst_buttons_up;
    }

    public int getDst_comprehends_hunger_cold() {
        return dst_comprehends_hunger_cold;
    }

    public void setDst_comprehends_hunger_cold(int dst_comprehends_hunger_cold) {
        this.dst_comprehends_hunger_cold = dst_comprehends_hunger_cold;
    }

    public int getDst_plays_cooperatively_with_children() {
        return dst_plays_cooperatively_with_children;
    }

    public void setDst_plays_cooperatively_with_children(int dst_plays_cooperatively_with_children) {
        this.dst_plays_cooperatively_with_children = dst_plays_cooperatively_with_children;
    }

    public int getDst_repeats_three_digits() {
        return dst_repeats_three_digits;
    }

    public void setDst_repeats_three_digits(int dst_repeats_three_digits) {
        this.dst_repeats_three_digits = dst_repeats_three_digits;
    }

    public int getDst_tells_stories() {
        return dst_tells_stories;
    }

    public void setDst_tells_stories(int dst_tells_stories) {
        this.dst_tells_stories = dst_tells_stories;
    }

    public int getDst_defines_words() {
        return dst_defines_words;
    }

    public void setDst_defines_words(int dst_defines_words) {
        this.dst_defines_words = dst_defines_words;
    }

    public int getDst_makes_simple_drawing() {
        return dst_makes_simple_drawing;
    }

    public void setDst_makes_simple_drawing(int dst_makes_simple_drawing) {
        this.dst_makes_simple_drawing = dst_makes_simple_drawing;
    }

    public int getDst_dresses_with_no_supervision() {
        return dst_dresses_with_no_supervision;
    }

    public void setDst_dresses_with_no_supervision(int dst_dresses_with_no_supervision) {
        this.dst_dresses_with_no_supervision = dst_dresses_with_no_supervision;
    }

    public int getDst_describes_actions_in_pictures() {
        return dst_describes_actions_in_pictures;
    }

    public void setDst_describes_actions_in_pictures(int dst_describes_actions_in_pictures) {
        this.dst_describes_actions_in_pictures = dst_describes_actions_in_pictures;
    }

    public int getDst_gives_sensible_answers_to_questions() {
        return dst_gives_sensible_answers_to_questions;
    }

    public void setDst_gives_sensible_answers_to_questions(int dst_gives_sensible_answers_to_questions) {
        this.dst_gives_sensible_answers_to_questions = dst_gives_sensible_answers_to_questions;
    }

    public int getDst_goes_about_neighbourhood() {
        return dst_goes_about_neighbourhood;
    }

    public void setDst_goes_about_neighbourhood(int dst_goes_about_neighbourhood) {
        this.dst_goes_about_neighbourhood = dst_goes_about_neighbourhood;
    }

    public int getDst_can_name_primary_colours() {
        return dst_can_name_primary_colours;
    }

    public void setDst_can_name_primary_colours(int dst_can_name_primary_colours) {
        this.dst_can_name_primary_colours = dst_can_name_primary_colours;
    }

    public int getDst_plays_games_governed_by_rules() {
        return dst_plays_games_governed_by_rules;
    }

    public void setDst_plays_games_governed_by_rules(int dst_plays_games_governed_by_rules) {
        this.dst_plays_games_governed_by_rules = dst_plays_games_governed_by_rules;
    }

    public int getDst_writes_simple_words() {
        return dst_writes_simple_words;
    }

    public void setDst_writes_simple_words(int dst_writes_simple_words) {
        this.dst_writes_simple_words = dst_writes_simple_words;
    }

    public int getDst_gains_admission_to_school() {
        return dst_gains_admission_to_school;
    }

    public void setDst_gains_admission_to_school(int dst_gains_admission_to_school) {
        this.dst_gains_admission_to_school = dst_gains_admission_to_school;
    }

    public int getDst_enjoys_constructive_play() {
        return dst_enjoys_constructive_play;
    }

    public void setDst_enjoys_constructive_play(int dst_enjoys_constructive_play) {
        this.dst_enjoys_constructive_play = dst_enjoys_constructive_play;
    }

    public int getDst_adapts_to_home_school() {
        return dst_adapts_to_home_school;
    }

    public void setDst_adapts_to_home_school(int dst_adapts_to_home_school) {
        this.dst_adapts_to_home_school = dst_adapts_to_home_school;
    }

    public int getDst_tells_differences_of_objects() {
        return dst_tells_differences_of_objects;
    }

    public void setDst_tells_differences_of_objects(int dst_tells_differences_of_objects) {
        this.dst_tells_differences_of_objects = dst_tells_differences_of_objects;
    }

    public int getDst_spells_reads_writes_simple_words() {
        return dst_spells_reads_writes_simple_words;
    }

    public void setDst_spells_reads_writes_simple_words(int dst_spells_reads_writes_simple_words) {
        this.dst_spells_reads_writes_simple_words = dst_spells_reads_writes_simple_words;
    }

    public int getDst_enjoys_group_play() {
        return dst_enjoys_group_play;
    }

    public void setDst_enjoys_group_play(int dst_enjoys_group_play) {
        this.dst_enjoys_group_play = dst_enjoys_group_play;
    }

    public int getDst_knows_comparative_value_of_coins() {
        return dst_knows_comparative_value_of_coins;
    }

    public void setDst_knows_comparative_value_of_coins(int dst_knows_comparative_value_of_coins) {
        this.dst_knows_comparative_value_of_coins = dst_knows_comparative_value_of_coins;
    }

    public int getDst_combs_hair_by_self() {
        return dst_combs_hair_by_self;
    }

    public void setDst_combs_hair_by_self(int dst_combs_hair_by_self) {
        this.dst_combs_hair_by_self = dst_combs_hair_by_self;
    }

    public int getDst_makes_small_purchases() {
        return dst_makes_small_purchases;
    }

    public void setDst_makes_small_purchases(int dst_makes_small_purchases) {
        this.dst_makes_small_purchases = dst_makes_small_purchases;
    }

    public int getDst_competition_in_school_or_play() {
        return dst_competition_in_school_or_play;
    }

    public void setDst_competition_in_school_or_play(int dst_competition_in_school_or_play) {
        this.dst_competition_in_school_or_play = dst_competition_in_school_or_play;
    }

    public int getDst_tells_time() {
        return dst_tells_time;
    }

    public void setDst_tells_time(int dst_tells_time) {
        this.dst_tells_time = dst_tells_time;
    }

    public int getDst_tells_day_month_year() {
        return dst_tells_day_month_year;
    }

    public void setDst_tells_day_month_year(int dst_tells_day_month_year) {
        this.dst_tells_day_month_year = dst_tells_day_month_year;
    }

    public int getDst_reads_on_own_initiative() {
        return dst_reads_on_own_initiative;
    }

    public void setDst_reads_on_own_initiative(int dst_reads_on_own_initiative) {
        this.dst_reads_on_own_initiative = dst_reads_on_own_initiative;
    }

    public int getDst_recognises_property_rights() {
        return dst_recognises_property_rights;
    }

    public void setDst_recognises_property_rights(int dst_recognises_property_rights) {
        this.dst_recognises_property_rights = dst_recognises_property_rights;
    }

    public int getDst_favourite_of_fairy_tales() {
        return dst_favourite_of_fairy_tales;
    }

    public void setDst_favourite_of_fairy_tales(int dst_favourite_of_fairy_tales) {
        this.dst_favourite_of_fairy_tales = dst_favourite_of_fairy_tales;
    }

    public int getDst_muscle_coordination_games() {
        return dst_muscle_coordination_games;
    }

    public void setDst_muscle_coordination_games(int dst_muscle_coordination_games) {
        this.dst_muscle_coordination_games = dst_muscle_coordination_games;
    }

    public int getDst_bathes_self_unaided() {
        return dst_bathes_self_unaided;
    }

    public void setDst_bathes_self_unaided(int dst_bathes_self_unaided) {
        this.dst_bathes_self_unaided = dst_bathes_self_unaided;
    }

    public int getDst_cooperates_keenly_with_companions() {
        return dst_cooperates_keenly_with_companions;
    }

    public void setDst_cooperates_keenly_with_companions(int dst_cooperates_keenly_with_companions) {
        this.dst_cooperates_keenly_with_companions = dst_cooperates_keenly_with_companions;
    }

    public int getDst_has_various_hobbies_collections() {
        return dst_has_various_hobbies_collections;
    }

    public void setDst_has_various_hobbies_collections(int dst_has_various_hobbies_collections) {
        this.dst_has_various_hobbies_collections = dst_has_various_hobbies_collections;
    }

    public int getDst_goes_about_town_freely() {
        return dst_goes_about_town_freely;
    }

    public void setDst_goes_about_town_freely(int dst_goes_about_town_freely) {
        this.dst_goes_about_town_freely = dst_goes_about_town_freely;
    }

    public int getDst_sex_differences_in_play_become_marked() {
        return dst_sex_differences_in_play_become_marked;
    }

    public void setDst_sex_differences_in_play_become_marked(int dst_sex_differences_in_play_become_marked) {
        this.dst_sex_differences_in_play_become_marked = dst_sex_differences_in_play_become_marked;
    }

    public int getDst_can_stay_away_from_home() {
        return dst_can_stay_away_from_home;
    }

    public void setDst_can_stay_away_from_home(int dst_can_stay_away_from_home) {
        this.dst_can_stay_away_from_home = dst_can_stay_away_from_home;
    }

    public int getDst_writes_occasional_short_letters() {
        return dst_writes_occasional_short_letters;
    }

    public void setDst_writes_occasional_short_letters(int dst_writes_occasional_short_letters) {
        this.dst_writes_occasional_short_letters = dst_writes_occasional_short_letters;
    }

    public int getDst_comprehends_social_situations() {
        return dst_comprehends_social_situations;
    }

    public void setDst_comprehends_social_situations(int dst_comprehends_social_situations) {
        this.dst_comprehends_social_situations = dst_comprehends_social_situations;
    }

    public int getDst_physical_feets_liked() {
        return dst_physical_feets_liked;
    }

    public void setDst_physical_feets_liked(int dst_physical_feets_liked) {
        this.dst_physical_feets_liked = dst_physical_feets_liked;
    }

    public int getDst_able_to_discuss_problems() {
        return dst_able_to_discuss_problems;
    }

    public void setDst_able_to_discuss_problems(int dst_able_to_discuss_problems) {
        this.dst_able_to_discuss_problems = dst_able_to_discuss_problems;
    }

    public int getDst_enjoys_books_newspapers_magazines() {
        return dst_enjoys_books_newspapers_magazines;
    }

    public void setDst_enjoys_books_newspapers_magazines(int dst_enjoys_books_newspapers_magazines) {
        this.dst_enjoys_books_newspapers_magazines = dst_enjoys_books_newspapers_magazines;
    }

    public int getDst_more_independent_in_spending() {
        return dst_more_independent_in_spending;
    }

    public void setDst_more_independent_in_spending(int dst_more_independent_in_spending) {
        this.dst_more_independent_in_spending = dst_more_independent_in_spending;
    }

    public int getDst_capable_of_self_criticism() {
        return dst_capable_of_self_criticism;
    }

    public void setDst_capable_of_self_criticism(int dst_capable_of_self_criticism) {
        this.dst_capable_of_self_criticism = dst_capable_of_self_criticism;
    }

    public int getDst_shows_foresight_planning_judgement() {
        return dst_shows_foresight_planning_judgement;
    }

    public void setDst_shows_foresight_planning_judgement(int dst_shows_foresight_planning_judgement) {
        this.dst_shows_foresight_planning_judgement = dst_shows_foresight_planning_judgement;
    }

    public int getDst_learns_from_experience() {
        return dst_learns_from_experience;
    }

    public void setDst_learns_from_experience(int dst_learns_from_experience) {
        this.dst_learns_from_experience = dst_learns_from_experience;
    }

    public int getDst_plays_difficult_games() {
        return dst_plays_difficult_games;
    }

    public void setDst_plays_difficult_games(int dst_plays_difficult_games) {
        this.dst_plays_difficult_games = dst_plays_difficult_games;
    }

    public int getDst_interested_in_dressing_up() {
        return dst_interested_in_dressing_up;
    }

    public void setDst_interested_in_dressing_up(int dst_interested_in_dressing_up) {
        this.dst_interested_in_dressing_up = dst_interested_in_dressing_up;
    }

    public int getDst_understands_abstract_ideas() {
        return dst_understands_abstract_ideas;
    }

    public void setDst_understands_abstract_ideas(int dst_understands_abstract_ideas) {
        this.dst_understands_abstract_ideas = dst_understands_abstract_ideas;
    }

    public int getDst_makes_sensible_plans_for_future() {
        return dst_makes_sensible_plans_for_future;
    }

    public void setDst_makes_sensible_plans_for_future(int dst_makes_sensible_plans_for_future) {
        this.dst_makes_sensible_plans_for_future = dst_makes_sensible_plans_for_future;
    }

    public int getDst_follows_current_events() {
        return dst_follows_current_events;
    }

    public void setDst_follows_current_events(int dst_follows_current_events) {
        this.dst_follows_current_events = dst_follows_current_events;
    }

    public int getDst_buys_own_clothing() {
        return dst_buys_own_clothing;
    }

    public void setDst_buys_own_clothing(int dst_buys_own_clothing) {
        this.dst_buys_own_clothing = dst_buys_own_clothing;
    }

    public int getDst_systematises_own_work() {
        return dst_systematises_own_work;
    }

    public void setDst_systematises_own_work(int dst_systematises_own_work) {
        this.dst_systematises_own_work = dst_systematises_own_work;
    }

    public int getDst_purchases_for_others() {
        return dst_purchases_for_others;
    }

    public void setDst_purchases_for_others(int dst_purchases_for_others) {
        this.dst_purchases_for_others = dst_purchases_for_others;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    

    public StringBuffer getTotaldst() {
        return totaldst;
    }

    public void setTotaldst(StringBuffer totaldst) {
        this.totaldst = totaldst;
    }

    public int getThreemonths() {
        return threemonths;
    }

    public void setThreemonths(int threemonths) {
        this.threemonths = threemonths;
    }

    public int getSixmonths() {
        return sixmonths;
    }

    public void setSixmonths(int sixmonths) {
        this.sixmonths = sixmonths;
    }

    public int getNinemonths() {
        return ninemonths;
    }

    public void setNinemonths(int ninemonths) {
        this.ninemonths = ninemonths;
    }

    public int getOneyear() {
        return oneyear;
    }

    public void setOneyear(int oneyear) {
        this.oneyear = oneyear;
    }

    public int getOneandhalfyear() {
        return oneandhalfyear;
    }

    public void setOneandhalfyear(int oneandhalfyear) {
        this.oneandhalfyear = oneandhalfyear;
    }

    public int getTwoyears() {
        return twoyears;
    }

    public void setTwoyears(int twoyears) {
        this.twoyears = twoyears;
    }

    public int getThreeyears() {
        return threeyears;
    }

    public void setThreeyears(int threeyears) {
        this.threeyears = threeyears;
    }

    public int getFouryears() {
        return fouryears;
    }

    public void setFouryears(int fouryears) {
        this.fouryears = fouryears;
    }

    public int getFiveyears() {
        return fiveyears;
    }

    public void setFiveyears(int fiveyears) {
        this.fiveyears = fiveyears;
    }

    public int getSixyears() {
        return sixyears;
    }

    public void setSixyears(int sixyears) {
        this.sixyears = sixyears;
    }

    public int getSevenyears() {
        return sevenyears;
    }

    public void setSevenyears(int sevenyears) {
        this.sevenyears = sevenyears;
    }

    public int getEightyears() {
        return eightyears;
    }

    public void setEightyears(int eightyears) {
        this.eightyears = eightyears;
    }

    public int getNineyears() {
        return nineyears;
    }

    public void setNineyears(int nineyears) {
        this.nineyears = nineyears;
    }

    public int getTenyears() {
        return tenyears;
    }

    public void setTenyears(int tenyears) {
        this.tenyears = tenyears;
    }

    public void setElevenyears(int elevenyears) {
        this.elevenyears = elevenyears;
    }

    public int getTweleveyears() {
        return tweleveyears;
    }

    public void setTweleveyears(int tweleveyears) {
        this.tweleveyears = tweleveyears;
    }

    public int getThirteenyears() {
        return thirteenyears;
    }

    public void setThirteenyears(int thirteenyears) {
        this.thirteenyears = thirteenyears;
    }

    public int getFifteenyears() {
        return fifteenyears;
    }

    public void setFifteenyears(int fifteenyears) {
        this.fifteenyears = fifteenyears;
    }

    public int getElevenyears() {
        return elevenyears;
    }

    public boolean isThreetosixmonths() {
        return (this.threemonths!=0||this.sixmonths!=0);
    }

    public void setThreetosixmonths(boolean threetosixmonths) {
        this.threetosixmonths = threetosixmonths;
    }

    public boolean isNinemonthstooneandhalfyear() {
        return (this.ninemonths!=0||this.oneyear!=0||this.oneandhalfyear!=0);
    }

    public void setNinemonthstooneandhalfyear(boolean ninemonthstooneandhalfyear) {
        this.ninemonthstooneandhalfyear = ninemonthstooneandhalfyear;
    }

    public boolean isThreemonthstooneandhalfyear() {
        return threemonthstooneandhalfyear;
    }

    public void setThreemonthstooneandhalfyear(boolean threemonthstooneandhalfyear) {
        this.threemonthstooneandhalfyear = threemonthstooneandhalfyear;
    }

    public boolean isTwoyearstofouryears() {
        return (this.twoyears!=0||this.threeyears!=0||this.fouryears!=0);
    }

    public void setTwoyearstofouryears(boolean twoyearstofouryears) {
        this.twoyearstofouryears = twoyearstofouryears;
    }

    public boolean isFiveyearstosixyears() {
        return (this.fiveyears!=0||this.sixyears!=0);
    }

    public void setFiveyearstosixyears(boolean fiveyearstosixyears) {
        this.fiveyearstosixyears = fiveyearstosixyears;
    }

    public boolean isTwoyearstosixyears() {
        return twoyearstosixyears;
    }

    public void setTwoyearstosixyears(boolean twoyearstosixyears) {
        this.twoyearstosixyears = twoyearstosixyears;
    }

    public boolean isSeventonineyears() {
        return (this.sevenyears!=0||this.eightyears!=0||this.nineyears!=0);
    }

    public void setSeventonineyears(boolean seventonineyears) {
        this.seventonineyears = seventonineyears;
    }

    public boolean isTenyearstotwelveyears() {
        return (this.tenyears!=0||this.elevenyears!=0||this.tweleveyears!=0);
    }

    public void setTenyearstotwelveyears(boolean tenyearstotwelveyears) {
        this.tenyearstotwelveyears = tenyearstotwelveyears;
    }

    public boolean isSeventotweleveyears() {
        return seventotweleveyears;
    }

    public void setSeventotweleveyears(boolean seventotweleveyears) {
        this.seventotweleveyears = seventotweleveyears;
    }

    public StringBuffer getNoofyears() {
        return noofyears;
    }

    public void setNoofyears(StringBuffer noofyears) {
        this.noofyears = noofyears;
    }

    public StringBuffer getNoofmonths() {
        return noofmonths;
    }

    public void setNoofmonths(StringBuffer noofmonths) {
        this.noofmonths = noofmonths;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getTodaysdate() {
        return todaysdate;
    }

    public void setTodaysdate(String todaysdate) {
        this.todaysdate = todaysdate;
    }

    public String getChronologicalage() {
        return chronologicalage;
    }

    public void setChronologicalage(String chronologicalage) {
        this.chronologicalage = chronologicalage;
    }

    public String getMentalage() {
        return mentalage;
    }

    public void setMentalage(String mentalage) {
        this.mentalage = mentalage;
    }

    public StringBuffer getIq() {
        return iq;
    }

    public void setIq(StringBuffer iq) {
        this.iq = iq;
    }

    
    
}
