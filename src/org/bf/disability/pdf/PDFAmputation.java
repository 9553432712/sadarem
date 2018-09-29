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
import org.bf.disability.dto.AmputationDto;

/**
 *
 * @author 509864
 */
public class PDFAmputation {

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
            AmputationDto amputationDto = (AmputationDto) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            float[] amputation_widths = {0.60f, 0.20f, 0.20f};
            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable amputation_table = new PdfPTable(amputation_widths);

            if (amputationDto.getUpperamputation() > 0) {

                cell_one = new PdfPCell(new Paragraph(PDFLables.UPPEREXTREMITY_AMPUTATION, Font_Bold));
                cell_one.setBorder(0);
                amputation_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.RIGHT, Font_Bold));
                cell_one.setBorder(0);
                amputation_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.LEFT, Font_Bold));
                cell_one.setBorder(0);
                amputation_table.addCell(cell_one);


                int[][] upperAmputationValues = {{amputationDto.getUpper_fore_right(), amputationDto.getUpper_fore_left()},
                    {amputationDto.getUpper_shoulder_right(), amputationDto.getUpper_shoulder_left()},
                    {amputationDto.getUpper_aboveelbowupper_right(), amputationDto.getUpper_aboveelbowupper_left()},
                    {amputationDto.getUpper_elbowlower_right(), amputationDto.getUpper_elbowlower_left()},
                    {amputationDto.getUpper_elbowdis_right(), amputationDto.getUpper_elbowdis_left()},
                    {amputationDto.getUpper_belowelbowupper_right(), amputationDto.getUpper_belowelbowupper_left()},
                    {amputationDto.getUpper_belowelbowlower_right(), amputationDto.getUpper_belowelbowlower_left()},
                    {amputationDto.getUpper_waistdis_right(), amputationDto.getUpper_waistdis_left()},
                    {amputationDto.getUpper_handcarpel_right(), amputationDto.getUpper_handcarpel_left()},
                    {amputationDto.getUpper_thumbCM_right(), amputationDto.getUpper_thumbCM_left()},
                    {amputationDto.getUpper_thumbMCP_right(), amputationDto.getUpper_thumbMCP_left()},
                    {amputationDto.getUpper_thumbIP_right(), amputationDto.getUpper_thumbIP_left()}};


                String[] upperAmputationLables = {PDFLables.FORE_QUATER_AMPUTATION, PDFLables.SHOULDER_DISARTICULATION,
                		PDFLables.ABOVE_ELBOW_UPTO_LOWERONE,PDFLables.ABOVE_ELBOW_UPTO_UPPERONE,  
                    PDFLables.ELBOW_DISARTICULATION, PDFLables.BELOW_ELBOW_UPTO_UPPERONE,
                    PDFLables.BELOW_ELBOW_UPTO_LOWERONE, PDFLables.WRIST_DISARTICULATION,
                    PDFLables.HAND_THROUGH_CARPAL, PDFLables.THUMB_THROUGH_CM,
                    PDFLables.THUMB_DISARTICULATION_THROUGH_MCP, PDFLables.THUMB_DISARTICULATION_THROUGH_IP};


                amputation_table = pDFCommonTemplate.creatingAmputationPdfThreeCells(amputation_table, Font_Bold, Font_Normal, cell_one,
                        PDFLables.YES, upperAmputationValues, upperAmputationLables);




            }


            String[] amputationMPJointList = {String.valueOf(amputationDto.getUpper_MPIndex_right()), String.valueOf(amputationDto.getUpper_MPIndex_left()),
                String.valueOf(amputationDto.getUpper_MPMiddle_right()), String.valueOf(amputationDto.getUpper_MPMiddle_left()),
                String.valueOf(amputationDto.getUpper_MPRing_right()), String.valueOf(amputationDto.getUpper_MPRing_left()),
                String.valueOf(amputationDto.getUpper_MPLittle_right()), String.valueOf(amputationDto.getUpper_MPLittle_left())};
            boolean amputationMPJointFlag = pDFCommonTemplate.getDisplayHeaderFlag(amputationMPJointList, "0");
            if (amputationMPJointFlag) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.AMPUTATION_THROUGH_PROXIMAL, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                amputation_table.addCell(cell_one);


                int[][] amputationProximalValues = {{amputationDto.getUpper_MPIndex_right(), amputationDto.getUpper_MPIndex_left()},
                    {amputationDto.getUpper_MPMiddle_right(), amputationDto.getUpper_MPMiddle_left()},
                    {amputationDto.getUpper_MPRing_right(), amputationDto.getUpper_MPRing_left()},
                    {amputationDto.getUpper_MPLittle_right(), amputationDto.getUpper_MPLittle_left()}};
                String[] amputationProximalLables = {PDFLables.INDEX_FINGER_AMPUTATION_3_1_13, PDFLables.MIDDLE_FINGER_AMPUTAION_3_1_13,
                    PDFLables.RING_FINGER_AMPUTAION_3_1_13, PDFLables.LITTLE_FINGER_AMPUTAION_3_1_13};

                amputation_table = pDFCommonTemplate.creatingAmputationPdfThreeCells(amputation_table, Font_Bold, Font_Normal, cell_one,
                        PDFLables.YES, amputationProximalValues, amputationProximalLables);


            }

            String[] amputationPIPJointList = {String.valueOf(amputationDto.getUpper_PIPIndex_right()), String.valueOf(amputationDto.getUpper_PIPIndex_left()),
                String.valueOf(amputationDto.getUpper_PIPMiddle_right()), String.valueOf(amputationDto.getUpper_PIPMiddle_left()),
                String.valueOf(amputationDto.getUpper_PIPRing_right()), String.valueOf(amputationDto.getUpper_PIPRing_left()),
                String.valueOf(amputationDto.getUpper_PIPLittle_right()), String.valueOf(amputationDto.getUpper_PIPLittle_left())};
            boolean amputationPIPJointFlag = pDFCommonTemplate.getDisplayHeaderFlag(amputationPIPJointList, "0");
            if (amputationPIPJointFlag) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.AMPUTATION_THROUGH_DISTAL_PHALANX, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                amputation_table.addCell(cell_one);

               

                int[][] amputationProximalPIPValues = {{amputationDto.getUpper_PIPIndex_right(), amputationDto.getUpper_PIPIndex_left()},
                    {amputationDto.getUpper_PIPMiddle_right(), amputationDto.getUpper_PIPMiddle_left()},
                    {amputationDto.getUpper_PIPRing_right(), amputationDto.getUpper_PIPRing_left()},
                    {amputationDto.getUpper_PIPLittle_right(), amputationDto.getUpper_PIPLittle_left()}};
                String[] amputationProximalPIPLables = {PDFLables.INDEX_FINGER_AMPUTATION_3_1_14, PDFLables.MIDDLE_FINGER_AMPUTAION_3_1_14,
                    PDFLables.RING_FINGER_AMPUTAION_3_1_14, PDFLables.LITTLE_FINGER_AMPUTAION_3_1_14};

                amputation_table = pDFCommonTemplate.creatingAmputationPdfThreeCells(amputation_table, Font_Bold, Font_Normal, cell_one,
                        PDFLables.YES, amputationProximalPIPValues, amputationProximalPIPLables);


            }

            String[] amputationDIPJointList = {String.valueOf(amputationDto.getUpper_DIPIndex_right()), String.valueOf(amputationDto.getUpper_DIPIndex_left()),
                String.valueOf(amputationDto.getUpper_DIPMiddle_right()), String.valueOf(amputationDto.getUpper_DIPMiddle_left()),
                String.valueOf(amputationDto.getUpper_DIPRing_right()), String.valueOf(amputationDto.getUpper_DIPRing_left()),
                String.valueOf(amputationDto.getUpper_DIPLittle_right()), String.valueOf(amputationDto.getUpper_DIPLittle_left())};
            boolean amputationDIPJointFlag = pDFCommonTemplate.getDisplayHeaderFlag(amputationDIPJointList, "0");
            if (amputationDIPJointFlag) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.AMPUTATION_THROUGH_MIDDLE_PHALANX, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                amputation_table.addCell(cell_one);

               

                int[][] amputationProximalDIPValues = {{amputationDto.getUpper_DIPIndex_right(), amputationDto.getUpper_DIPIndex_left()},
                    {amputationDto.getUpper_DIPMiddle_right(), amputationDto.getUpper_DIPMiddle_left()},
                    {amputationDto.getUpper_DIPRing_right(), amputationDto.getUpper_DIPRing_left()},
                    {amputationDto.getUpper_DIPLittle_right(), amputationDto.getUpper_DIPLittle_left()}};
                String[] amputationProximalDIPLables = {PDFLables.INDEX_FINGER_AMPUTATION_3_1_15, PDFLables.MIDDLE_FINGER_AMPUTAION_3_1_15,
                    PDFLables.RING_FINGER_AMPUTAION_3_1_15, PDFLables.LITTLE_FINGER_AMPUTAION_3_1_15};

                amputation_table = pDFCommonTemplate.creatingAmputationPdfThreeCells(amputation_table, Font_Bold, Font_Normal, cell_one,
                        PDFLables.YES, amputationProximalDIPValues, amputationProximalDIPLables);


            }

            if (amputationDto.getLoweramputation() > 0) {

                cell_one = new PdfPCell(new Paragraph(PDFLables.LOWEREXTREMITY_AMPUTATION, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                amputation_table.addCell(cell_one);

               


                int[][] lowerAmputationValues = {{amputationDto.getLower_hind_right(), amputationDto.getLower_hind_left()},
                    {amputationDto.getLower_hip_right(), amputationDto.getLower_hip_left()},
                    {amputationDto.getLower_AKupper_right(), amputationDto.getLower_AKupper_left()},
                    {amputationDto.getLower_AKlower_right(), amputationDto.getLower_AKlower_left()},
                    {amputationDto.getLower_truknee_right(), amputationDto.getLower_truknee_left()},
                    {amputationDto.getLower_bk8cm_right(), amputationDto.getLower_bk8cm_left()},
                    {amputationDto.getLower_bklower_right(), amputationDto.getLower_bklower_left()},
                    {amputationDto.getLower_truankle_right(), amputationDto.getLower_truankle_left()},
                    {amputationDto.getLower_symes_right(), amputationDto.getLower_symes_left()},
                    {amputationDto.getLower_uptomid_right(), amputationDto.getLower_uptomid_left()},
                    {amputationDto.getLower_uptofore_right(), amputationDto.getLower_uptofore_left()},
                    {amputationDto.getLower_alltoe_right(), amputationDto.getLower_alltoe_left()},
                    {amputationDto.getLower_1sttoe_right(), amputationDto.getLower_1sttoe_left()},
                    {amputationDto.getLower_2ndtoe_right(), amputationDto.getLower_2ndtoe_left()},
                    {amputationDto.getLower_3rdtoe_right(), amputationDto.getLower_3rdtoe_left()},
                    {amputationDto.getLower_4thtoe_right(), amputationDto.getLower_4thtoe_left()},
                    {amputationDto.getLower_5thtoe_right(), amputationDto.getLower_5thtoe_left()}};


                String[] lowerAmputationLables = {PDFLables.HIND_QUARTER, PDFLables.HIP_DISARTICULATION,
                    PDFLables.ABOVE_KNEE_UPTO_UPPER, PDFLables.ABOVE_KNEE_UPTO_LOWER,
                    PDFLables.THROUGH_KNEE, PDFLables.BK_UPTO_8CM,
                    PDFLables.BK_UPTO_LOWER, PDFLables.THROUGH_ANKLE,
                    PDFLables.SYMENS, PDFLables.UPTO_MID_FOOT,
                    PDFLables.UPTO_FORE_FOOT, PDFLables.ALL_TOES,
                    PDFLables.LOSS_OF_FIRST_TOE, PDFLables.LOSS_OF_SECOND_TOE,
                    PDFLables.LOSS_OF_THIRD_TOE, PDFLables.LOSS_OF_FORTH_TOE,
                    PDFLables.LOSS_OF_FIFTH_TOE};


                amputation_table = pDFCommonTemplate.creatingAmputationPdfThreeCells(amputation_table, Font_Bold, Font_Normal, cell_one,
                        PDFLables.YES, lowerAmputationValues, lowerAmputationLables);




            }

            if (amputationDto.getComplicationstotal() > 0) {

                cell_one = new PdfPCell(new Paragraph(PDFLables.COMPLICATIONS_AMPUTATION, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                amputation_table.addCell(cell_one);

                if (amputationDto.getFitting_of_prosthesis() > 0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.IS_THE_STUMP, Font_Normal));
                    cell_one.setBorder(0);
                    amputation_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    amputation_table.addCell(cell_one);


                }

                if (amputationDto.getProximal_joint() > 0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.IS_THERE_STIFFNESS, Font_Normal));
                    cell_one.setBorder(0);
                    amputation_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(String.valueOf(amputationDto.getProximal_joint()), Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    amputation_table.addCell(cell_one);


                }

                if (amputationDto.getNeuroma() > 0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.IS_THERE_NEUROMA, Font_Normal));
                    cell_one.setBorder(0);
                    amputation_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(String.valueOf(amputationDto.getNeuroma()), Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    amputation_table.addCell(cell_one);


                }

                if (amputationDto.getInfection() > 0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.IS_THERE_INFECTION, Font_Normal));
                    cell_one.setBorder(0);
                    amputation_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(String.valueOf(amputationDto.getInfection()), Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    amputation_table.addCell(cell_one);


                }

                if (amputationDto.getDominant() > 0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.IS_DOMINANT_UPPER, Font_Normal));
                    cell_one.setBorder(0);
                    amputation_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    amputation_table.addCell(cell_one);


                }


            }




            cell_one = new PdfPCell(amputation_table);
            cell_one.setBorder(0);
            cell_one.setColspan(2);
            table.addCell(cell_one);

            table.setSplitRows(true);
            table.setExtendLastRow(false);
            table.setKeepTogether(true);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

//    public PdfPTable creatingAmputationPdfThreeCells(PdfPTable tempTable, Font Font_Bold, Font Font_Normal, PdfPCell cell_one,
//            String Flag, int[][] right_left_values, String[] lables) throws IOException {
//
//        try {
//
//
//            if (null != right_left_values) {
//                if (right_left_values.length > 0) {
//                    int i = 0;
//                    for (int[] right_left_value : right_left_values) {
//                        i++;
//                        if (right_left_value[0] > 0 || right_left_value[1] > 0) {
//
//                            cell_one = new PdfPCell(new Paragraph(lables[i - 1], Font_Normal));
//                            cell_one.setBorder(0);
//                            tempTable.addCell(cell_one);
//
//                            if (right_left_value[0] > 0) {
//                                cell_one = new PdfPCell(new Paragraph(Flag, Font_Bold));
//                                cell_one.setBorder(0);
//                                tempTable.addCell(cell_one);
//                            } else {
//                                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
//                                cell_one.setBorder(0);
//                                tempTable.addCell(cell_one);
//                            }
//                            if (right_left_value[1] > 0) {
//                                cell_one = new PdfPCell(new Paragraph(Flag, Font_Bold));
//                                cell_one.setBorder(0);
//                                tempTable.addCell(cell_one);
//                            } else {
//                                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
//                                cell_one.setBorder(0);
//                                tempTable.addCell(cell_one);
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return tempTable;
//    }
}
