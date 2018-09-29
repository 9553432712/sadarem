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
import org.bf.disability.dto.LowerExtremityDTO;

/**
 *
 * @author 509864
 */
public class PDFLowerExtremity {

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
            LowerExtremityDTO lowerextremitydto = (LowerExtremityDTO) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));


            float[] lower_widths = {0.20f, 0.30f, 0.10f, 0.20f, 0.20f};
            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable lower_table = new PdfPTable(lower_widths);

            String[] romList = {String.valueOf(lowerextremitydto.getRomright()),String.valueOf(lowerextremitydto.getRomleft())};
            boolean  romListFlag = pDFCommonTemplate.getDisplayHeaderFlag(romList, "0");

            String[] msList = {String.valueOf(lowerextremitydto.getMsright()),String.valueOf(lowerextremitydto.getMsleft())};
            boolean  msListFlag = pDFCommonTemplate.getDisplayHeaderFlag(msList, "0.0");

            if (romListFlag || msListFlag) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.LOWEREXTREMITY_MOBILITYCOMPONENT, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(5);
                lower_table.addCell(cell_one);

                if (romListFlag) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.ACTIVE_RANGE_MOTION, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(5);
                    lower_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.JOINT, Font_Bold));
                    cell_one.setBorder(0);
                    lower_table.addCell(cell_one);

                    lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Bold, cell_one, PDFLables.COMPONENT,
                            PDFLables.NORMAL_RANGE, PDFLables.RIGHT, PDFLables.LEFT, "");



                    String[] romHipFlex = {lowerextremitydto.getRomhipflexionextensionright(),lowerextremitydto.getRomhipflexionextensionleft()};
                    String[] romHipAbd = {lowerextremitydto.getRomhipabductionadductionright(),lowerextremitydto.getRomhipabductionadductionleft()};
                     String[] romHipRota = {lowerextremitydto.getRomhiprotationright(),lowerextremitydto.getRomhiprotationleft()};
                    boolean romHipFlexFlag = pDFCommonTemplate.getDisplayHeaderFlag(romHipFlex, "140");
                    boolean romHipAbdFlag = pDFCommonTemplate.getDisplayHeaderFlag(romHipAbd, "90");
                    boolean romHipRotaFlag = pDFCommonTemplate.getDisplayHeaderFlag(romHipRota, "90");
                    if(romHipFlexFlag || romHipAbdFlag || romHipRotaFlag){

                        cell_one = new PdfPCell(new Paragraph(PDFLables.HIP_JOINT, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setRowspan(3);
                        lower_table.addCell(cell_one);


                        if (romHipFlexFlag) {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.FLEXION_EXTENSION_ARC,
                                    PDFLables.RANGE_0_140, lowerextremitydto.getRomhipflexionextensionright(), lowerextremitydto.getRomhipflexionextensionleft(), "140");
                        } else {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, "", "",
                                    "", "", "");
                        }

                        if (romHipAbdFlag) {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.ABDUCTION_ADDUCTION_ARC,
                                    PDFLables.RANGE_0_90, lowerextremitydto.getRomhipabductionadductionright(),lowerextremitydto.getRomhipabductionadductionleft(), "90");
                        } else {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, "", "",
                                    "", "", "");
                        }

                        if (romHipRotaFlag) {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.ROTATION_ARC,
                                    PDFLables.RANGE_0_90, lowerextremitydto.getRomhiprotationright(),lowerextremitydto.getRomhiprotationleft(), "90");
                        } else {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, "", "",
                                    "", "", "");
                        }

                    }

                    String[] romKnee = {lowerextremitydto.getRomkneeflexionextensionright(),lowerextremitydto.getRomkneeflexionextensionleft()};
                    boolean romKneeFlag = pDFCommonTemplate.getDisplayHeaderFlag(romKnee, "125");
                    if(romKneeFlag){
                        cell_one = new PdfPCell(new Paragraph(PDFLables.KNEE_JOINT, Font_Normal));
                        cell_one.setBorder(0);
                        lower_table.addCell(cell_one);

                       if (romKneeFlag) {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.FLEXION_EXTENSION_ARC,
                                    PDFLables.RANGE_0_125,lowerextremitydto.getRomkneeflexionextensionright(),lowerextremitydto.getRomkneeflexionextensionleft(), "125");
                        } else {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, "", "",
                                    "", "", "");
                        }
                    }

                    
                    String[] romAnkleDor = {lowerextremitydto.getRomankledorsiflexionpalmarflexionright(), lowerextremitydto.getRomankledorsiflexionpalmarflexionleft()};
                    String[] romAnkleInv = {lowerextremitydto.getRomankleinversioneversionright(), lowerextremitydto.getRomankleinversioneversionleft()};
                    boolean romAnkleDorFlag = pDFCommonTemplate.getDisplayHeaderFlag(romAnkleDor, "70");
                    boolean romAnkleFlag = pDFCommonTemplate.getDisplayHeaderFlag(romAnkleInv, "60");
                    if (romAnkleDorFlag || romAnkleFlag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.ANKLE_FOOT_JOINT, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setRowspan(2);
                        lower_table.addCell(cell_one);

                        if (romAnkleDorFlag) {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.DORSIFLEXION_PALNTAR,
                                    PDFLables.RANGE_0_70,lowerextremitydto.getRomankledorsiflexionpalmarflexionright(), lowerextremitydto.getRomankledorsiflexionpalmarflexionleft(), "70");
                        } else {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, "", "",
                                    "", "", "");
                        }

                        if (romAnkleFlag) {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.INVERSION_EVERSION_ARC,
                                    PDFLables.RANGE_0_60,lowerextremitydto.getRomankleinversioneversionright(), lowerextremitydto.getRomankleinversioneversionleft(), "60");
                        } else {
                            lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, "", "",
                                    "", "", "");
                        }
                    }

                }


               if(msListFlag){

                   cell_one = new PdfPCell(new Paragraph(PDFLables.MUSCLE_STRENGTH_LOWEREXTREMITY, Font_Bold));
                   cell_one.setBorder(0);
                   cell_one.setColspan(5);
                   lower_table.addCell(cell_one);

                   cell_one = new PdfPCell(new Paragraph(PDFLables.JOINT, Font_Bold));
                   cell_one.setBorder(0);
                   lower_table.addCell(cell_one);

                   lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Bold, cell_one, PDFLables.COMPONENT,
                           PDFLables.NORMAL_RANGE, PDFLables.RIGHT, PDFLables.LEFT, "");

                 String[][] hipJointList = {{lowerextremitydto.getMshipflexormusclesright(),lowerextremitydto.getMshipflexormusclesleft()},
                                                        {lowerextremitydto.getMshipextensormusclesright(),lowerextremitydto.getMshipextensormusclesleft()},
                                                        {lowerextremitydto.getMshiprotatormusclesright(),lowerextremitydto.getMshiprotatormusclesleft()},
                                                        {lowerextremitydto.getMshipabductormusclesright(),lowerextremitydto.getMshipabductormusclesleft()},
                                                        {lowerextremitydto.getMshipadductormusclesright(),lowerextremitydto.getMshipadductormusclesleft()}};
                int j = pDFCommonTemplate.getDisplayHeaderRowCountMultiple(hipJointList,"5");
                   if (j>0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.HIP_JOINT, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(j);
                    lower_table.addCell(cell_one);

                    String[] mshipflex = {lowerextremitydto.getMshipflexormusclesright(), lowerextremitydto.getMshipflexormusclesleft()};
                   if (pDFCommonTemplate.getDisplayHeaderFlag(mshipflex, "5")) {
                       lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.FLEXOR_MUSCLES,
                               PDFLables.RANGE_0_5, lowerextremitydto.getMshipflexormusclesright(), lowerextremitydto.getMshipflexormusclesleft(), "5");
                   }

                   String[] mshipex = {lowerextremitydto.getMshipextensormusclesright(),lowerextremitydto.getMshipextensormusclesleft()};
                   if (pDFCommonTemplate.getDisplayHeaderFlag(mshipex, "5")) {
                       lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.EXTENSOR_MUSCLES,
                               PDFLables.RANGE_0_5, lowerextremitydto.getMshipextensormusclesright(),lowerextremitydto.getMshipextensormusclesleft(), "5");
                   }

                   String[] mshiprota = {lowerextremitydto.getMshiprotatormusclesright(),lowerextremitydto.getMshiprotatormusclesleft()};
                   if (pDFCommonTemplate.getDisplayHeaderFlag(mshiprota, "5")) {
                       lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.ROTATOR_MUSCLES,
                               PDFLables.RANGE_0_5,lowerextremitydto.getMshiprotatormusclesright(),lowerextremitydto.getMshiprotatormusclesleft(), "5");
                   }

                   String[] mshipabdu = {lowerextremitydto.getMshipabductormusclesright(),lowerextremitydto.getMshipabductormusclesleft()};
                   if (pDFCommonTemplate.getDisplayHeaderFlag(mshipabdu, "5")) {
                       lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.ABDUCTOR_MUSCLES,
                               PDFLables.RANGE_0_5, lowerextremitydto.getMshipabductormusclesright(),lowerextremitydto.getMshipabductormusclesleft(), "5");
                   }

                   String[] mshipaddu = {lowerextremitydto.getMshipadductormusclesright(),lowerextremitydto.getMshipadductormusclesleft()};
                   if (pDFCommonTemplate.getDisplayHeaderFlag(mshipaddu, "5")) {
                       lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.ADDUCTOR_MUSCLES,
                               PDFLables.RANGE_0_5, lowerextremitydto.getMshipadductormusclesright(),lowerextremitydto.getMshipadductormusclesleft(), "5");
                   }
                    
                   }


                 String[][] kneeJointList = {{lowerextremitydto.getMskneeflexormusclesright(), lowerextremitydto.getMskneeflexormusclesleft()},
                                             {lowerextremitydto.getMskneeextensormusclesright(), lowerextremitydto.getMskneeextensormusclesleft()}
                                             };
                int k = pDFCommonTemplate.getDisplayHeaderRowCountMultiple(kneeJointList,"5");
                   if (k>0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.KNEE_JOINT, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(k);
                    lower_table.addCell(cell_one);

                    String[] mskneeflex = {lowerextremitydto.getMskneeflexormusclesright(), lowerextremitydto.getMskneeflexormusclesleft()};
                   if (pDFCommonTemplate.getDisplayHeaderFlag(mskneeflex, "5")) {
                       lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.FLEXOR_MUSCLES,
                               PDFLables.RANGE_0_5, lowerextremitydto.getMskneeflexormusclesright(), lowerextremitydto.getMskneeflexormusclesleft(), "5");
                   }

                    String[] mskneeext = {lowerextremitydto.getMskneeextensormusclesright(), lowerextremitydto.getMskneeextensormusclesleft()};
                   if (pDFCommonTemplate.getDisplayHeaderFlag(mskneeext, "5")) {
                       lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.EXTENSOR_MUSCLES,
                               PDFLables.RANGE_0_5,lowerextremitydto.getMskneeextensormusclesright(), lowerextremitydto.getMskneeextensormusclesleft(), "5");
                   }

                   }




                 String[][] ankleJointList = {{lowerextremitydto.getMsankleplanterflexormusclesright(),lowerextremitydto.getMsankleplanterflexormusclesleft()},
                                                        {lowerextremitydto.getMsankledorsiflexormusclesright(),lowerextremitydto.getMsankledorsiflexormusclesleft()},
                                                        {lowerextremitydto.getMsankleinvertormusclesright(),lowerextremitydto.getMsankleinvertormusclesleft()},
                                                        {lowerextremitydto.getMsankleevertormusclesright(),lowerextremitydto.getMsankleevertormusclesleft()}};
                int l = pDFCommonTemplate.getDisplayHeaderRowCountMultiple(ankleJointList,"5");
                   if (l>0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.ANKLE_FOOT_JOINT, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(l);
                    lower_table.addCell(cell_one);

                    String[] msanklepla = {lowerextremitydto.getMsankleplanterflexormusclesright(),lowerextremitydto.getMsankleplanterflexormusclesleft()};
                   if (pDFCommonTemplate.getDisplayHeaderFlag(msanklepla, "5")) {
                       lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.PLANTERFLEXOR_MUSCLES,
                               PDFLables.RANGE_0_5, lowerextremitydto.getMsankleplanterflexormusclesright(),lowerextremitydto.getMsankleplanterflexormusclesleft(), "5");
                   }

                   String[] maankledor = {lowerextremitydto.getMsankledorsiflexormusclesright(),lowerextremitydto.getMsankledorsiflexormusclesleft()};
                   if (pDFCommonTemplate.getDisplayHeaderFlag(maankledor, "5")) {
                       lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.DORSIFLEXOR_MUSCLES,
                               PDFLables.RANGE_0_5, lowerextremitydto.getMsankledorsiflexormusclesright(),lowerextremitydto.getMsankledorsiflexormusclesleft(), "5");
                   }

                   String[] maankleinv = {lowerextremitydto.getMsankleinvertormusclesright(),lowerextremitydto.getMsankleinvertormusclesleft()};
                   if (pDFCommonTemplate.getDisplayHeaderFlag(maankleinv, "5")) {
                       lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.INVERTOR_MUSCLES,
                               PDFLables.RANGE_0_5,lowerextremitydto.getMsankleinvertormusclesright(),lowerextremitydto.getMsankleinvertormusclesleft(), "5");
                   }

                   String[] maankleever = {lowerextremitydto.getMsankleevertormusclesright(),lowerextremitydto.getMsankleevertormusclesleft()};
                   if (pDFCommonTemplate.getDisplayHeaderFlag(maankleever, "5")) {
                       lower_table = pDFCommonTemplate.creatingPdfCell(lower_table, Font_Bold, Font_Normal, cell_one, PDFLables.EVERTOR_MUSCLES,
                               PDFLables.RANGE_0_5, lowerextremitydto.getMsankleevertormusclesright(),lowerextremitydto.getMsankleevertormusclesleft(), "5");
                   }

                   }
                   
               }
            }

            cell_one = new PdfPCell(lower_table);
            cell_one.setBorder(0);
            cell_one.setColspan(2);
            table.addCell(cell_one);

             if (lowerextremitydto.getStability() > 0) {

                float[] lower_stability_widths = {0.50f, 0.20f, 0.20f};
                PdfPTable lower_stability_table = new PdfPTable(lower_stability_widths);

                cell_one = new PdfPCell(new Paragraph(PDFLables.STABILITYCOMPONENT_LOWEREXTREMITY, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                lower_stability_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.COMPONENT, Font_Bold));
                cell_one.setBorder(0);
                lower_stability_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.PERFORM_WITH_DIFFICULTY, Font_Bold));
                cell_one.setBorder(0);
                lower_stability_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.CANNOT_PERFORM, Font_Bold));
                cell_one.setBorder(0);
                lower_stability_table.addCell(cell_one);


                if (null != lowerextremitydto.getWorking_on_plane() && !"".equals(lowerextremitydto.getWorking_on_plane()) && !"0".equals(lowerextremitydto.getWorking_on_plane())) {

                    lower_stability_table = pDFCommonTemplate.creatingPdfThreeCells(lower_stability_table, Font_Bold, Font_Normal, cell_one, PDFLables.WALKING_ON_PLANE_SURFACE,
                                lowerextremitydto.getWorking_on_plane(), "0",PDFLables.YES,"5","10");

                }

                 if (null != lowerextremitydto.getWorking_on_slope() && !"".equals(lowerextremitydto.getWorking_on_slope()) && !"0".equals(lowerextremitydto.getWorking_on_slope())) {

                    lower_stability_table = pDFCommonTemplate.creatingPdfThreeCells(lower_stability_table, Font_Bold, Font_Normal, cell_one, PDFLables.WALKING_ON_SLOPE,
                                lowerextremitydto.getWorking_on_slope(), "0",PDFLables.YES,"5","10");

                }

                if (null != lowerextremitydto.getWorking_on_stairs() && !"".equals(lowerextremitydto.getWorking_on_stairs()) && !"0".equals(lowerextremitydto.getWorking_on_stairs())) {

                    lower_stability_table = pDFCommonTemplate.creatingPdfThreeCells(lower_stability_table, Font_Bold, Font_Normal, cell_one, PDFLables.CLIMBING_STAIRS,
                                lowerextremitydto.getWorking_on_stairs(), "0",PDFLables.YES,"5","10");

                }

                if (null != lowerextremitydto.getStanding_on_both_legs() && !"".equals(lowerextremitydto.getStanding_on_both_legs()) && !"0".equals(lowerextremitydto.getStanding_on_both_legs())) {

                    lower_stability_table = pDFCommonTemplate.creatingPdfThreeCells(lower_stability_table, Font_Bold, Font_Normal, cell_one, PDFLables.STANDING_ON_BOTH_LEGS,
                                lowerextremitydto.getStanding_on_both_legs(), "0",PDFLables.YES,"5","10");

                }

                if (null != lowerextremitydto.getStanding_on_effected() && !"".equals(lowerextremitydto.getStanding_on_effected()) && !"0".equals(lowerextremitydto.getStanding_on_effected())) {

                    lower_stability_table = pDFCommonTemplate.creatingPdfThreeCells(lower_stability_table, Font_Bold, Font_Normal, cell_one, PDFLables.STANDING_ON_AFFECTED_LEG,
                                lowerextremitydto.getStanding_on_effected(), "0",PDFLables.YES,"5","10");
                }

                if (null != lowerextremitydto.getSquatting_on_floor() && !"".equals(lowerextremitydto.getSquatting_on_floor()) && !"0".equals(lowerextremitydto.getSquatting_on_floor())) {

                    lower_stability_table = pDFCommonTemplate.creatingPdfThreeCells(lower_stability_table, Font_Bold, Font_Normal, cell_one, PDFLables.SQUARING_ON_FLOOR,
                                lowerextremitydto.getSquatting_on_floor(), "0",PDFLables.YES,"5","10");
                }

                if (null != lowerextremitydto.getSitting_cross_leg() && !"".equals(lowerextremitydto.getSitting_cross_leg()) && !"0".equals(lowerextremitydto.getSitting_cross_leg())) {

                    lower_stability_table = pDFCommonTemplate.creatingPdfThreeCells(lower_stability_table, Font_Bold, Font_Normal, cell_one, PDFLables.SITTING_CROSS_LEG,
                                lowerextremitydto.getSitting_cross_leg(), "0",PDFLables.YES,"5","10");
                }

                if (null != lowerextremitydto.getKneeling() && !"".equals(lowerextremitydto.getKneeling()) && !"0".equals(lowerextremitydto.getKneeling())) {

                    lower_stability_table = pDFCommonTemplate.creatingPdfThreeCells(lower_stability_table, Font_Bold, Font_Normal, cell_one, PDFLables.KNEELING,
                                lowerextremitydto.getKneeling(), "0",PDFLables.YES,"5","10");
                }

                 if (null != lowerextremitydto.getTaking_turns() && !"".equals(lowerextremitydto.getTaking_turns()) && !"0".equals(lowerextremitydto.getTaking_turns())) {

                    lower_stability_table = pDFCommonTemplate.creatingPdfThreeCells(lower_stability_table, Font_Bold, Font_Normal, cell_one, PDFLables.TAKING_TURNS,
                                lowerextremitydto.getTaking_turns(), "0",PDFLables.YES,"5","10");
                }

                 cell_one = new PdfPCell(lower_stability_table);
                 cell_one.setBorder(0);
                 cell_one.setColspan(2);
                 table.addCell(cell_one);
            }

             if (lowerextremitydto.getExtra() > 0) {

                float[] lower_extra_widths = {0.30f, 0.50f, 0.20f};
                PdfPTable lower_extra_table = new PdfPTable(lower_extra_widths);

                cell_one = new PdfPCell(new Paragraph(PDFLables.PRESENCE_OF_COMPLECATIONS, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                lower_extra_table.addCell(cell_one);

                if (null != lowerextremitydto.getDeformity() && !"".equals(lowerextremitydto.getDeformity())
                                                             && !"0".equals(lowerextremitydto.getDeformity())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.DEFORMITY_LOWER, Font_Normal));
                    cell_one.setBorder(0);
                    lower_extra_table.addCell(cell_one);

                    if ("3".equals(lowerextremitydto.getDeformity())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.IN_FUNCTION_POSITION, Font_Normal)); 
                        cell_one.setBorder(0);
                        lower_extra_table.addCell(cell_one);
                    }else if("6".equals(lowerextremitydto.getDeformity())){
                        cell_one = new PdfPCell(new Paragraph(PDFLables.IN_NON_FUNCTION_POSITION, Font_Normal));
                        cell_one.setBorder(0);
                        lower_extra_table.addCell(cell_one);
                    }

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    lower_extra_table.addCell(cell_one);

                }

                if (null != lowerextremitydto.getPain() && !"".equals(lowerextremitydto.getPain())
                                                             && !"0".equals(lowerextremitydto.getPain())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.PAIN, Font_Normal));
                    cell_one.setBorder(0);
                    lower_extra_table.addCell(cell_one);

                    if ("9".equals(lowerextremitydto.getPain())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.SEVERE_LOWER, Font_Normal));
                        cell_one.setBorder(0);
                        lower_extra_table.addCell(cell_one);
                    }else if("6".equals(lowerextremitydto.getPain())){
                        cell_one = new PdfPCell(new Paragraph(PDFLables.MODERATE_LOWER, Font_Normal));
                        cell_one.setBorder(0);
                        lower_extra_table.addCell(cell_one);
                    }else if("3".equals(lowerextremitydto.getPain())){
                        cell_one = new PdfPCell(new Paragraph(PDFLables.MILD_LOWER, Font_Normal));
                        cell_one.setBorder(0);
                        lower_extra_table.addCell(cell_one);
                    }

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    lower_extra_table.addCell(cell_one);

                }

                if (null != lowerextremitydto.getLoss_of_Function() && !"".equals(lowerextremitydto.getLoss_of_Function())
                                                             && !"0".equals(lowerextremitydto.getLoss_of_Function())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.LOSS_OF_FUNCTION, Font_Normal));
                    cell_one.setBorder(0);
                    lower_extra_table.addCell(cell_one);

                    if ("9".equals(lowerextremitydto.getLoss_of_Function())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.COMPLETEE_LOSS, Font_Normal));
                        cell_one.setBorder(0);
                        lower_extra_table.addCell(cell_one);
                    }else if("6".equals(lowerextremitydto.getLoss_of_Function())){
                        cell_one = new PdfPCell(new Paragraph(PDFLables.PARTIAL_LOSS, Font_Normal));
                        cell_one.setBorder(0);
                        lower_extra_table.addCell(cell_one);
                    }

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    lower_extra_table.addCell(cell_one);

                }

                if (null != lowerextremitydto.getComplications() && !"".equals(lowerextremitydto.getComplications())
                                                             && !"0".equals(lowerextremitydto.getComplications())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.COMPLICATIONS, Font_Normal));
                    cell_one.setBorder(0);
                    lower_extra_table.addCell(cell_one);

                    if ("3".equals(lowerextremitydto.getComplications())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.SUPERFICIAL_COMPLICATIONS, Font_Normal));
                        cell_one.setBorder(0);
                        lower_extra_table.addCell(cell_one);
                    }else if("5".equals(lowerextremitydto.getComplications())){
                        cell_one = new PdfPCell(new Paragraph(PDFLables.DEEP_COMPLICATIONS, Font_Normal));
                        cell_one.setBorder(0);
                        lower_extra_table.addCell(cell_one);
                    }

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    lower_extra_table.addCell(cell_one);

                }

                 cell_one = new PdfPCell(lower_extra_table);
                 cell_one.setBorder(0);
                 cell_one.setColspan(2);
                 table.addCell(cell_one);

             }
            if (null != lowerextremitydto.getShortening() && !"".equals(lowerextremitydto.getShortening())
                                                             && !"0.0".equals(lowerextremitydto.getShortening())) {

                cell_one = new PdfPCell(new Paragraph(PDFLables.SHORTENING_OF_LIMB, Font_Normal));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(lowerextremitydto.getShortening(), Font_Bold));
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