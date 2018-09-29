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
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.dto.VsmsScreeningTestDTO;

/**
 *
 * @author 509864
 */
public class PDFVinelandSocialMaturityScaleRecordSheet {

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
            VsmsScreeningTestDTO vsmsdto = (VsmsScreeningTestDTO) request.getAttribute("sectionObject");
            MentalRetardationTestsDTO mrtestdto = (MentalRetardationTestsDTO) request.getAttribute("VSMSIQDetails");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            float[] vsms_widths = {0.10f, 0.40f, 0.20f, 0.10f, 0.10f};
            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable vsms_table = new PdfPTable(vsms_widths);

            String[] List_0Yto1Y = {String.valueOf(vsmsdto.getVsms_0to1_CoolsLaughs()),String.valueOf(vsmsdto.getVsms_0to1_Balenceshead()),
                                    String.valueOf(vsmsdto.getVsms_0to1_Graphsobject()),String.valueOf(vsmsdto.getVsms_0to1_Reachesforfamiliarpersons()),
                                    String.valueOf(vsmsdto.getVsms_0to1_Rolls()),String.valueOf(vsmsdto.getVsms_0to1_Reachesforobjects()),
                                    String.valueOf(vsmsdto.getVsms_0to1_Occupies()),String.valueOf(vsmsdto.getVsms_0to1_Sits()),
                                    String.valueOf(vsmsdto.getVsms_0to1_Pulls()),String.valueOf(vsmsdto.getVsms_0to1_Talks()),
                                    String.valueOf(vsmsdto.getVsms_0to1_Drinks()),String.valueOf(vsmsdto.getVsms_0to1_Moves()),
                                    String.valueOf(vsmsdto.getVsms_0to1_Grasps()),String.valueOf(vsmsdto.getVsms_0to1_Demands()),
                                    String.valueOf(vsmsdto.getVsms_0to1_Stands()),String.valueOf(vsmsdto.getVsms_0to1_Doesnotdrool()),
                                    String.valueOf(vsmsdto.getVsms_0to1_Follows())};
            boolean  Flag_0Yto1Y = pDFCommonTemplate.getDisplayHeaderFlag(List_0Yto1Y, "0");

            if(Flag_0Yto1Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_0_1, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_0Yto1Y={{"1.",PDFLables.COOS_LAUGHS,String.valueOf(vsmsdto.getVsms_0to1_CoolsLaughs()),"","0.7"},
                                               {"2.",PDFLables.BALENCES_HEAD,String.valueOf(vsmsdto.getVsms_0to1_Balenceshead()),"","1.4"},{"3.",PDFLables.GRAPHS_OBJECT_WITHIN_REACH,String.valueOf(vsmsdto.getVsms_0to1_Graphsobject()),"","2.1"},
                                               {"4.",PDFLables.REACHES_FOR_FAMILIAR_PERSONS,String.valueOf(vsmsdto.getVsms_0to1_Reachesforfamiliarpersons()),"","2.8"},{"5.",PDFLables.ROLLS_OVER_UNASSISTED,String.valueOf(vsmsdto.getVsms_0to1_Rolls()),"","3.5"},
                                               {"6.",PDFLables.REACHES_FOR_NEARBY_OBJECTS,String.valueOf(vsmsdto.getVsms_0to1_Reachesforobjects()),"","4.2"},{"7.",PDFLables.OCCUPIES_SELF_UNATTENED,String.valueOf(vsmsdto.getVsms_0to1_Occupies()),"","4.9"},
                                               {"8.",PDFLables.SITS_UNSUPPORTED,String.valueOf(vsmsdto.getVsms_0to1_Sits()),"","5.6"},{"9.",PDFLables.PULLS_SELF_UPRIGHT,String.valueOf(vsmsdto.getVsms_0to1_Pulls()),"","6.3"},
                                               {"10.",PDFLables.TALKS_IMITATES_SOUNDS,String.valueOf(vsmsdto.getVsms_0to1_Talks()),"","7.0"},{"11.",PDFLables.DRINKS_FROM_CUP_OR_GLASS_ASSISTED,String.valueOf(vsmsdto.getVsms_0to1_Drinks()),"","7.7"},
                                               {"12.",PDFLables.MOVES_ABOUT_ON_FLOOR_CREEPING_CRAWLING,String.valueOf(vsmsdto.getVsms_0to1_Moves()),"","8.4"},{"13.",PDFLables.GRASPS_WITH_THUMB_AND_FINGER,String.valueOf(vsmsdto.getVsms_0to1_Grasps()),"","9.1"},
                                               {"14.",PDFLables.DEMANDS_PERSONAL_ATTENTION,String.valueOf(vsmsdto.getVsms_0to1_Demands()),"","9.8"},{"15.",PDFLables.STANDS_ALONE,String.valueOf(vsmsdto.getVsms_0to1_Stands()),"","10.6"},
                                               {"16.",PDFLables.DOES_NOT_DROOL,String.valueOf(vsmsdto.getVsms_0to1_Doesnotdrool()),"","11.3"},{"17.",PDFLables.FOLLOWS_SIMPLE_INSTRUCTIONS,String.valueOf(vsmsdto.getVsms_0to1_Follows()),"","12.0"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_0Yto1Y);

            }

            String[] List_1Yto2Y = {String.valueOf(vsmsdto.getVsms_1to2_Walks()),String.valueOf(vsmsdto.getVsms_1to2_Marks()),
                                    String.valueOf(vsmsdto.getVsms_1to2_Masticates()),String.valueOf(vsmsdto.getVsms_1to2_Pulls()),
                                    String.valueOf(vsmsdto.getVsms_1to2_Transfers()),String.valueOf(vsmsdto.getVsms_1to2_Overcomessimpleobstacles()),
                                    String.valueOf(vsmsdto.getVsms_1to2_Fetches()),String.valueOf(vsmsdto.getVsms_1to2_Drinks()),
                                    String.valueOf(vsmsdto.getVsms_1to2_Walkswithoutsupport()),String.valueOf(vsmsdto.getVsms_1to2_Plays()),
                                    String.valueOf(vsmsdto.getVsms_1to2_Eats()),String.valueOf(vsmsdto.getVsms_1to2_Goes()),
                                    String.valueOf(vsmsdto.getVsms_1to2_Discriminates()),String.valueOf(vsmsdto.getVsms_1to2_Usesnames()),
                                    String.valueOf(vsmsdto.getVsms_1to2_Walksupstairs()),String.valueOf(vsmsdto.getVsms_1to2_Unwarps()),
                                    String.valueOf(vsmsdto.getVsms_1to2_Talks())};
            boolean  Flag_1Yto2Y = pDFCommonTemplate.getDisplayHeaderFlag(List_1Yto2Y, "0");

            if(Flag_1Yto2Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_1_2, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_1Yto2Y={{"18.",PDFLables.WALKS_ABOUT_ROOM_UNATTENED,String.valueOf(vsmsdto.getVsms_1to2_Walks()),"1Y","0.7"},
                                               {"19.",PDFLables.MARKS_WITH_PENCIL_CRAYON_OR_CHALK,String.valueOf(vsmsdto.getVsms_1to2_Marks()),"1Y","1.4"},{"20.",PDFLables.MASTICATES_CHEWS_SOLID_OR_SEMI_SOLID_FOOD,String.valueOf(vsmsdto.getVsms_1to2_Masticates()),"1Y","2.1"},
                                               {"21.",PDFLables.PULLS_OFF_CLOTHES,String.valueOf(vsmsdto.getVsms_1to2_Pulls()),"1Y","2.8"},{"22.",PDFLables.TRANSFERS_OBJECTS,String.valueOf(vsmsdto.getVsms_1to2_Transfers()),"1Y","3.5"},
                                               {"23.",PDFLables.OVERCOMES_SIMPLE_OBSTACLES,String.valueOf(vsmsdto.getVsms_1to2_Overcomessimpleobstacles()),"1Y","4.2"},{"24.",PDFLables.FETCHES_OR_CARRIES_FAMILIAR_OBJECTS,String.valueOf(vsmsdto.getVsms_1to2_Fetches()),"1Y","4.9"},
                                               {"25.",PDFLables.DRINKS_FROM_CUP_OR_GLASS_UNASSISTED,String.valueOf(vsmsdto.getVsms_1to2_Drinks()),"1Y","5.6"},{"26.",PDFLables.WALKS_WITHOUT_SUPPORT,String.valueOf(vsmsdto.getVsms_1to2_Walkswithoutsupport()),"1Y","6.3"},
                                               {"27.",PDFLables.PLAYS_WITH_OTHER_CHILDREN,String.valueOf(vsmsdto.getVsms_1to2_Plays()),"1Y","7.0"},{"28.",PDFLables.EATS_WITH_OWN_HANDS,String.valueOf(vsmsdto.getVsms_1to2_Eats()),"1Y","7.7"},
                                               {"29.",PDFLables.GOES_ABOUT_HOUSE_OR_YARD,String.valueOf(vsmsdto.getVsms_1to2_Goes()),"1Y","8.4"},{"30.",PDFLables.DESCRIBES_ACTIONS_IN_PICTURES,String.valueOf(vsmsdto.getVsms_1to2_Discriminates()),"1Y","9.1"},
                                               {"31.",PDFLables.USES_NAMES_OF_FAMILIAR_OBJECTS,String.valueOf(vsmsdto.getVsms_1to2_Usesnames()),"1Y","9.8"},{"32.",PDFLables.WALKS_UPSTAIRS_UNASSISTED,String.valueOf(vsmsdto.getVsms_1to2_Walksupstairs()),"1Y","10.6"},
                                               {"33.",PDFLables.UNWRAPS_SWEETS_CHOCOLATES,String.valueOf(vsmsdto.getVsms_1to2_Unwarps()),"1Y","11.3"},{"34.",PDFLables.TALKS_IN_SHORT_SENTENCES,String.valueOf(vsmsdto.getVsms_1to2_Talks()),"1Y","12.0"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_1Yto2Y);

            }

             String[] List_2Yto3Y = {String.valueOf(vsmsdto.getVsms_2to3_Signals()),String.valueOf(vsmsdto.getVsms_2to3_Initiates()),
                                    String.valueOf(vsmsdto.getVsms_2to3_Removesshirt()),String.valueOf(vsmsdto.getVsms_2to3_Eatswithspoon()),
                                    String.valueOf(vsmsdto.getVsms_2to3_Getsdrink()),String.valueOf(vsmsdto.getVsms_2to3_Dries()),
                                    String.valueOf(vsmsdto.getVsms_2to3_Avoids()),String.valueOf(vsmsdto.getVsms_2to3_Putsonshirt()),
                                    String.valueOf(vsmsdto.getVsms_2to3_Candopaperfolding()),String.valueOf(vsmsdto.getVsms_2to3_Relates())
                                    };
            boolean  Flag_2Yto3Y = pDFCommonTemplate.getDisplayHeaderFlag(List_2Yto3Y, "0");

            if(Flag_2Yto3Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_2_3, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_2Yto3Y={{"35.",PDFLables.SINGNALS_TO_GO_TO_TOILET,String.valueOf(vsmsdto.getVsms_2to3_Signals()),"2","1.2"},
                                               {"36.",PDFLables.INITIATES_OWN_PLAY_ACTIVITIES,String.valueOf(vsmsdto.getVsms_2to3_Initiates()),"2","2.4"},{"37.",PDFLables.REMOVES_SHIRT_OR_FROCK_IF_UNBUTTONED,String.valueOf(vsmsdto.getVsms_2to3_Removesshirt()),"2","3.6"},
                                               {"38.",PDFLables.EATS_WITH_SPOON_HANDS_FOOD,String.valueOf(vsmsdto.getVsms_2to3_Eatswithspoon()),"2","4.8"},{"39.",PDFLables.GETS_DRINK_WATER_UNASSISTED,String.valueOf(vsmsdto.getVsms_2to3_Getsdrink()),"2","6.0"},
                                               {"40.",PDFLables.DRIES_OWN_HANDS,String.valueOf(vsmsdto.getVsms_2to3_Dries()),"2","7.2"},{"41.",PDFLables.AVOIDS_SIMPLE_HAZARDS,String.valueOf(vsmsdto.getVsms_2to3_Avoids()),"2","8.4"},
                                               {"42.",PDFLables.PUTS_ON_SHIRT_OR_FROCK_UNASSISTED,String.valueOf(vsmsdto.getVsms_2to3_Putsonshirt()),"2","9.6"},{"43",PDFLables.CAN_DO_PAPER_FOLDING,String.valueOf(vsmsdto.getVsms_2to3_Candopaperfolding()),"2","10.8"},
                                               {"44.",PDFLables.RELATES_EXPERIENCES,String.valueOf(vsmsdto.getVsms_2to3_Relates()),"2","12.0"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_2Yto3Y);

            }


            String[] List_3Yto4Y = {String.valueOf(vsmsdto.getVsms_3to4_Walksdownsstairs()),String.valueOf(vsmsdto.getVsms_3to4_Playscooperatively()),
                                    String.valueOf(vsmsdto.getVsms_3to4_Buttons()),String.valueOf(vsmsdto.getVsms_3to4_Helps()),
                                    String.valueOf(vsmsdto.getVsms_3to4_Performs()),String.valueOf(vsmsdto.getVsms_3to4_Washes())
                                    };
            boolean  Flag_3Yto4Y = pDFCommonTemplate.getDisplayHeaderFlag(List_3Yto4Y, "0");

            if(Flag_3Yto4Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_2_3, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_3Yto4Y={{"45.",PDFLables.WALKS_DOWNSSTAIRS_ONE_AT_A_TIME,String.valueOf(vsmsdto.getVsms_3to4_Walksdownsstairs()),"3","2"},
                                               {"46.",PDFLables.PLAYS_COOPERATIVELY_AT_KINDERGARTEN_LEVEL,String.valueOf(vsmsdto.getVsms_3to4_Playscooperatively()),"3","4"},
                                               {"47.",PDFLables.BUTTONS_SHIRT_RO_FROCK,String.valueOf(vsmsdto.getVsms_3to4_Buttons()),"3","6"},
                                               {"48.",PDFLables.HELPS_AT_LITTLE_HOUSEHOLD_TASKS,String.valueOf(vsmsdto.getVsms_3to4_Helps()),"3","8"},
                                               {"49.",PDFLables.PERFORMS_FOR_OTHERS,String.valueOf(vsmsdto.getVsms_3to4_Performs()),"3","10"},
                                               {"50.",PDFLables.WASHES_HANDS_UNAIDED,String.valueOf(vsmsdto.getVsms_3to4_Washes()),"3","12"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_3Yto4Y);

            }

             String[] List_4Yto5Y = {String.valueOf(vsmsdto.getVsms_4to5_Cares()),String.valueOf(vsmsdto.getVsms_4to5_Prints()),
                                    String.valueOf(vsmsdto.getVsms_4to5_Goesaboutneighbourhood()),String.valueOf(vsmsdto.getVsms_4to5_Dresses()),
                                    String.valueOf(vsmsdto.getVsms_4to5_Usespencil()),String.valueOf(vsmsdto.getVsms_4to5_Playscompetitive())
                                    };
            boolean  Flag_4Yto5Y = pDFCommonTemplate.getDisplayHeaderFlag(List_4Yto5Y, "0");

            if(Flag_4Yto5Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_4_5, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_4Yto5Y={{"51.",PDFLables.CARES_FOR_SELF_AT_TOILET,String.valueOf(vsmsdto.getVsms_4to5_Cares()),"4","2"},
                                               {"52.",PDFLables.WASHES_FACE_UNASSISTED,String.valueOf(vsmsdto.getVsms_4to5_Prints()),"4","4"},
                                               {"53.",PDFLables.GOES_ABOUT_NEIGHBOURHOOD_UNATTENED,String.valueOf(vsmsdto.getVsms_4to5_Goesaboutneighbourhood()),"4","6"},
                                               {"54.",PDFLables.DRESSES_SELF_EXCEPT_FOR_TYING,String.valueOf(vsmsdto.getVsms_4to5_Dresses()),"4","8"},
                                               {"55.",PDFLables.USES_PENCIL_OR_CRAYON_CHALK_DRAWING,String.valueOf(vsmsdto.getVsms_4to5_Usespencil()),"4","10"},
                                               {"56.",PDFLables.PLAYS_COMPETITIVE_GAMES,String.valueOf(vsmsdto.getVsms_4to5_Playscompetitive()),"4","12"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_4Yto5Y);

            }

            String[] List_5Yto6Y = {String.valueOf(vsmsdto.getVsms_5to6_Useshoops()),String.valueOf(vsmsdto.getVsms_5to6_Printssimplewords()),
                                    String.valueOf(vsmsdto.getVsms_5to6_Playssimplegames()),String.valueOf(vsmsdto.getVsms_5to6_trusted()),
                                    String.valueOf(vsmsdto.getVsms_5to6_Goestoschool())
                                    };
            boolean  Flag_5Yto6Y = pDFCommonTemplate.getDisplayHeaderFlag(List_5Yto6Y, "0");

            if(Flag_5Yto6Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_5_6, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_5Yto6Y={{"57.",PDFLables.USES_HOOPS_FILES_KITES_OR_USES_KNIFE,String.valueOf(vsmsdto.getVsms_5to6_Useshoops()),"5","2.4"},
                                               {"58.",PDFLables.PRINTS_WRITES_SIMPLE_WORDS,String.valueOf(vsmsdto.getVsms_5to6_Printssimplewords()),"5","4.8"},
                                               {"59.",PDFLables.PLAYS_SIMPLE_GAMES_WHICH_REQUIRE_TALKING,String.valueOf(vsmsdto.getVsms_5to6_Playssimplegames()),"5","7.2"},
                                               {"60.",PDFLables.IS_TRUSREd_WITH_MONEY,String.valueOf(vsmsdto.getVsms_5to6_trusted()),"5","9.6"},
                                               {"61.",PDFLables.GOES_TO_SCHOOL_UNATTENED,String.valueOf(vsmsdto.getVsms_5to6_Goestoschool()),"5","12.0"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_5Yto6Y);

            }

              String[] List_6Yto7Y = {String.valueOf(vsmsdto.getVsms_6to7_Mixes()),String.valueOf(vsmsdto.getVsms_6to7_Usespencilorchalk()),
                                    String.valueOf(vsmsdto.getVsms_6to7_Batches()),String.valueOf(vsmsdto.getVsms_6to7_Goestobed())
                                    };
            boolean  Flag_6Yto7Y = pDFCommonTemplate.getDisplayHeaderFlag(List_6Yto7Y, "0");

            if(Flag_6Yto7Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_6_7, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_6Yto7Y={{"62.",PDFLables.MIXES_RICE_PROPERLY_UNASSISTED,String.valueOf(vsmsdto.getVsms_6to7_Mixes()),"6","3"},
                                               {"63.",PDFLables.USES_PENCIL_OR_CHALK_FOR_WRITING,String.valueOf(vsmsdto.getVsms_6to7_Usespencilorchalk()),"6","6"},
                                               {"64.",PDFLables.BATHES_SELF_ASSISTED,String.valueOf(vsmsdto.getVsms_6to7_Batches()),"6","9"},
                                               {"65.",PDFLables.GOES_TO_BED_UNASSISTED,String.valueOf(vsmsdto.getVsms_6to7_Goestobed()),"6","12"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_6Yto7Y);

            }

            String[] List_7Yto8Y = {String.valueOf(vsmsdto.getVsms_7to8_Candifferentiatebetween()),String.valueOf(vsmsdto.getVsms_7to8_Helps()),
                                    String.valueOf(vsmsdto.getVsms_7to8_Understands()),String.valueOf(vsmsdto.getVsms_7to8_Participates()),
                                    String.valueOf(vsmsdto.getVsms_7to8_Combs())
                                    };
            boolean  Flag_7Yto8Y = pDFCommonTemplate.getDisplayHeaderFlag(List_7Yto8Y, "0");

            if(Flag_7Yto8Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_7_8, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_7Yto8Y={{"66.",PDFLables.CAN_DIFFERENTIATE_BETWEEN_AM_PM,String.valueOf(vsmsdto.getVsms_7to8_Candifferentiatebetween()),"7","2.4"},
                                               {"67.",PDFLables.HELPS_HIMSELF_DURING_MEALS,String.valueOf(vsmsdto.getVsms_7to8_Helps()),"7","4.8"},
                                               {"68.",PDFLables.UNDERSTANDS_AND_KEEPS_FAMILY_SECRETS,String.valueOf(vsmsdto.getVsms_7to8_Understands()),"8","7.2"},
                                               {"69.",PDFLables.PARTICIPATES_IN_PER_ADOLESCENT_PLAY,String.valueOf(vsmsdto.getVsms_7to8_Participates()),"7","9.6"},
                                               {"70.",PDFLables.COMBS_OR_BRUSHES_HAIR,String.valueOf(vsmsdto.getVsms_7to8_Combs()),"7","12.0"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_7Yto8Y);

            }

            String[] List_8Yto9Y = {String.valueOf(vsmsdto.getVsms_8to9_Usestools()),String.valueOf(vsmsdto.getVsms_8to9_routinehouseholdtasks()),
                                    String.valueOf(vsmsdto.getVsms_8to9_Readsonowninitiative()),String.valueOf(vsmsdto.getVsms_8to9_Batchesselfunaided())
                                    };
            boolean  Flag_8Yto9Y = pDFCommonTemplate.getDisplayHeaderFlag(List_8Yto9Y, "0");

            if(Flag_8Yto9Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_8_9, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_8Yto9Y={{"71.",PDFLables.USES_TOOLS_OR_UTENSILS,String.valueOf(vsmsdto.getVsms_8to9_Usestools()),"8","3"},
                                               {"72.",PDFLables.DOES_ROUTINE_HOUSEHOLD_TASKS,String.valueOf(vsmsdto.getVsms_8to9_routinehouseholdtasks()),"8","6"},
                                               {"73.",PDFLables.READS_ON_OWN_INITIATIVE,String.valueOf(vsmsdto.getVsms_8to9_Readsonowninitiative()),"8","9"},
                                               {"74.",PDFLables.BATHES_SELF_UNAIDED,String.valueOf(vsmsdto.getVsms_8to9_Batchesselfunaided()),"8","12"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_8Yto9Y);

            }



             String[] List_9Yto10Y = {String.valueOf(vsmsdto.getVsms_9to10_Caresforself()),String.valueOf(vsmsdto.getVsms_9to10_Makesminorpurchases()),
                                    String.valueOf(vsmsdto.getVsms_9to10_Goesabouthome())
                                    };
            boolean  Flag_9Yto10Y = pDFCommonTemplate.getDisplayHeaderFlag(List_9Yto10Y, "0");

            if(Flag_9Yto10Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_9_10, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_9Yto10Y={{"75.",PDFLables.CARES_FOR_SELF_AT_MEALS,String.valueOf(vsmsdto.getVsms_9to10_Caresforself()),"9","4"},
                                                {"76.",PDFLables.MAKES_MINOR_PURCHASES,String.valueOf(vsmsdto.getVsms_9to10_Makesminorpurchases()),"9","8"},
                                                {"77.",PDFLables.GOES_ABOUT_HOME_TOWN_FREELY,String.valueOf(vsmsdto.getVsms_9to10_Goesabouthome()),"9","12"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_9Yto10Y);

            }


             String[] List_10Yto11Y = {String.valueOf(vsmsdto.getVsms_10to11_Distinguishes()),String.valueOf(vsmsdto.getVsms_10to11_Makesindependentchoice()),
                                      String.valueOf(vsmsdto.getVsms_10to11_smallremunerativework()),String.valueOf(vsmsdto.getVsms_10to11_Follows())
                                       };
            boolean  Flag_10Yto11Y = pDFCommonTemplate.getDisplayHeaderFlag(List_10Yto11Y, "0");

            if(Flag_10Yto11Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_10_11, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_10Yto11Y={{"78.",PDFLables.DISTINGUISHES_BETWEEN_FRIENDS_PLAY_MATES,String.valueOf(vsmsdto.getVsms_10to11_Distinguishes()),"10","3"},
                                                 {"79.",PDFLables.MAKES_INDEPENDENT_CHOICE_SHOPS,String.valueOf(vsmsdto.getVsms_10to11_Makesindependentchoice()),"10","6"},
                                                 {"80.",PDFLables.DOES_SMALL_REMUNERATIVE_WORK_MAKE_ARTICLES,String.valueOf(vsmsdto.getVsms_10to11_smallremunerativework()),"10","9"},
                                                 {"81.",PDFLables.FOLLOWS_CURRENT_EVENTS,String.valueOf(vsmsdto.getVsms_10to11_Follows()),"10","12"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_10Yto11Y);

            }


             String[] List_11Yto12Y = {String.valueOf(vsmsdto.getVsms_11to12_simplecreative()),String.valueOf(vsmsdto.getVsms_11to12_lefttocare()),
                                    String.valueOf(vsmsdto.getVsms_11to12_Enjoys())
                                    };
            boolean  Flag_11Yto12Y = pDFCommonTemplate.getDisplayHeaderFlag(List_11Yto12Y, "0");

            if(Flag_11Yto12Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_11_12, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_11Yto12Y={{"82.",PDFLables.DOES_SIMPLE_CREATIVEWORK,String.valueOf(vsmsdto.getVsms_11to12_simplecreative()),"11","4"},
                                                 {"83.",PDFLables.IS_LEFT_TO_CARE_FOR_SELF_OR_OTHERS,String.valueOf(vsmsdto.getVsms_11to12_lefttocare()),"11","8"},
                                                 {"84.",PDFLables.ENJOYS_READING_BOOKS_NEWSPAPERS_MAGAZINES,String.valueOf(vsmsdto.getVsms_11to12_Enjoys()),"11","12"}
                                                };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_11Yto12Y);

            }

            String[] List_12Yto15Y = {String.valueOf(vsmsdto.getVsms_12to15_Playsdifficult()),String.valueOf(vsmsdto.getVsms_12to15_Exercisescomplete()),
                                      String.valueOf(vsmsdto.getVsms_12to15_Buys()),String.valueOf(vsmsdto.getVsms_12to15_Engages()),
                                      String.valueOf(vsmsdto.getVsms_12to15_Performs())
                                       };
            boolean  Flag_12Yto15Y = pDFCommonTemplate.getDisplayHeaderFlag(List_12Yto15Y, "0");

            if(Flag_12Yto15Y){

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS_12_15, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Bold));
                cell_one.setBorder(0);
                vsms_table.addCell(cell_one);



                String[][] DisplayList_12Yto15Y={{"85.",PDFLables.PLAYS_DIFFICULT_GAMES,String.valueOf(vsmsdto.getVsms_12to15_Playsdifficult()),"12","7.2"},
                                                 {"86.",PDFLables.EXERCISES_COMPLETE_CARE_OF_DRESS,String.valueOf(vsmsdto.getVsms_12to15_Exercisescomplete()),"12","14.4"},
                                                 {"87.",PDFLables.BUYS_OWN_CLOTHING_ACCESSORIES,String.valueOf(vsmsdto.getVsms_12to15_Buys()),"12","21.6"},
                                                 {"88.",PDFLables.ENGAGES_IN_ADOLESCENT_GROUP_ACTIVITIES,String.valueOf(vsmsdto.getVsms_12to15_Engages()),"12","28.8"},
                                                 {"89.",PDFLables.PERFORMS_RESPONSIBLE_ROUTINE_CHORES,String.valueOf(vsmsdto.getVsms_12to15_Performs()),"12","36.0"}
                                               };


               vsms_table = pDFCommonTemplate.creatingMRThreeCells(vsms_table, Font_Bold, Font_Normal, cell_one,
                       PDFLables.YES, DisplayList_12Yto15Y);

            }







            cell_one = new PdfPCell(vsms_table);
            cell_one.setBorder(0);
            cell_one.setColspan(2);
            table.addCell(cell_one);


             if(null != mrtestdto && !"".equals(mrtestdto)){

                cell_one = new PdfPCell(new Paragraph(PDFLables.SOCIAL_AGE, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS+"    "+mrtestdto.getVsmsyear(), Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                 cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS+"    "+mrtestdto.getVsmsmonth(), Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);


                 cell_one = new PdfPCell(new Paragraph(PDFLables.IQ, Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(String.valueOf(Math.round(mrtestdto.getVsmsvq())), Font_Bold));
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
