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
import org.bf.disability.dto.LocomotorneedsDTO;

/**
 *
 * @author 509864
 */
public class PDFLocomotorNeeds {



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
            LocomotorneedsDTO lneedsdto = (LocomotorneedsDTO) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            if (null != lneedsdto) {


                 String[] locoNeeds_List = {lneedsdto.getPhysiotheraphy(),lneedsdto.getOccupationaltheraphy(),lneedsdto.getSurgery(),
                                            lneedsdto.getPhysiotherapy(),lneedsdto.getOccupationaltherapy(),lneedsdto.getSelectwheelchair(),
                                            lneedsdto.getSelecttricycle(),lneedsdto.getSelectwalkingstick(),lneedsdto.getSelectcrutches(),
                                            lneedsdto.getCrutchestype(),lneedsdto.getSelectwalkingframe(),lneedsdto.getAeroplanesplint(),
                                            lneedsdto.getFigure8splint(),lneedsdto.getForearmsplint(),lneedsdto.getHandsplint(),
                                            lneedsdto.getShoulderprosthesis(),lneedsdto.getAboveelbowprosthesis(),lneedsdto.getElbowdisarticulationprosthesis(),
                                            lneedsdto.getBelowelbowprosthesis(),lneedsdto.getWristdisarticulationprosthesis(),lneedsdto.getHandprosthesis(),
                                            lneedsdto.getCosmeticfingerprosthesis(),lneedsdto.getHkafo(),lneedsdto.getKafo(),
                                            lneedsdto.getAfo(),lneedsdto.getKneeorthosis(),lneedsdto.getDbsplint(),
                                            lneedsdto.getModifiedshoe(),lneedsdto.getSerialcasting(),lneedsdto.getHipprothesis(),
                                            lneedsdto.getAbovekneeprothesis(),lneedsdto.getKneedisarticulation(),lneedsdto.getBelowkneeprothesis(),
                                            lneedsdto.getSymeprothesis(),lneedsdto.getPartialfoodprothesis(),lneedsdto.getCervicalcollar(),
                                            lneedsdto.getLsbrace(),lneedsdto.getTlsobrace(),lneedsdto.getFeeding(),
                                            lneedsdto.getBathing(),lneedsdto.getBrushing(),lneedsdto.getCombing(),
                                            lneedsdto.getDressing(),lneedsdto.getWriting(),lneedsdto.getDrivingcycling(),
                                            lneedsdto.getBedtransfer(),lneedsdto.getAnyotherinlocomotor()
                                            };

                  boolean locoNeeds_Flag = pDFCommonTemplate.getDisplayHeaderFlag(locoNeeds_List, "");

              if(locoNeeds_Flag){
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NEED_ASSESSMENT, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    table.addCell(cell_one);

                    String[] early_List = {lneedsdto.getPhysiotheraphy(),lneedsdto.getOccupationaltheraphy()};
                    boolean early_Flag = pDFCommonTemplate.getDisplayHeaderFlag(early_List, "");
                    if (early_Flag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.EARLY_INTERVENTION_SERVICES, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);

                        String[][] early_Lables = {{PDFLables.PHYSIOTHERAPY, lneedsdto.getPhysiotheraphy()}, {PDFLables.OCCUPATIONAL_THERAPHY, lneedsdto.getOccupationaltheraphy()}};

                        table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                                PDFLables.YES, early_Lables);

                    }

                    if (null != lneedsdto.getSurgery() && !"".equals(lneedsdto.getSurgery())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.SURGERY, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(lneedsdto.getSurgery(), Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }

                  String[][] early_above3_Lables = {{PDFLables.PHYSIOTHERAPY, lneedsdto.getPhysiotherapy()}, {PDFLables.OCCUPATIONAL_THERAPHY, lneedsdto.getOccupationaltherapy()}};

                  table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                          PDFLables.YES, early_above3_Lables);



                  String[] assistive_List = {lneedsdto.getSelectwheelchair(),
                                            lneedsdto.getSelecttricycle(),lneedsdto.getSelectwalkingstick(),lneedsdto.getSelectcrutches(),
                                            lneedsdto.getCrutchestype(),lneedsdto.getSelectwalkingframe(),lneedsdto.getAeroplanesplint(),
                                            lneedsdto.getFigure8splint(),lneedsdto.getForearmsplint(),lneedsdto.getHandsplint(),
                                            lneedsdto.getShoulderprosthesis(),lneedsdto.getAboveelbowprosthesis(),lneedsdto.getElbowdisarticulationprosthesis(),
                                            lneedsdto.getBelowelbowprosthesis(),lneedsdto.getWristdisarticulationprosthesis(),lneedsdto.getHandprosthesis(),
                                            lneedsdto.getCosmeticfingerprosthesis(),lneedsdto.getHkafo(),lneedsdto.getKafo(),
                                            lneedsdto.getAfo(),lneedsdto.getKneeorthosis(),lneedsdto.getDbsplint(),
                                            lneedsdto.getModifiedshoe(),lneedsdto.getSerialcasting(),lneedsdto.getHipprothesis(),
                                            lneedsdto.getAbovekneeprothesis(),lneedsdto.getKneedisarticulation(),lneedsdto.getBelowkneeprothesis(),
                                            lneedsdto.getSymeprothesis(),lneedsdto.getPartialfoodprothesis(),lneedsdto.getCervicalcollar(),
                                            lneedsdto.getLsbrace(),lneedsdto.getTlsobrace(),lneedsdto.getFeeding(),
                                            lneedsdto.getBathing(),lneedsdto.getBrushing(),lneedsdto.getCombing(),
                                            lneedsdto.getDressing(),lneedsdto.getWriting(),lneedsdto.getDrivingcycling(),
                                            lneedsdto.getBedtransfer()
                                            };
                  boolean assistive_Flag = pDFCommonTemplate.getDisplayHeaderFlag(assistive_List, "");


                  if(assistive_Flag){

                      if (null != lneedsdto.getSelectwheelchair() && !"".equals(lneedsdto.getSelectwheelchair())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.WHEEL_CHAIR_SELECT, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(lneedsdto.getSelectwheelchair(), Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }

                      if (null != lneedsdto.getSelecttricycle() && !"".equals(lneedsdto.getSelecttricycle())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.TRICYCLE_SELECT, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(lneedsdto.getSelecttricycle(), Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }

                      if (null != lneedsdto.getSelectwalkingstick() && !"".equals(lneedsdto.getSelectwalkingstick())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.WALKING_STICK_SELECT, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(lneedsdto.getSelectwalkingstick(), Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }

                      if (null != lneedsdto.getSelectcrutches() && !"".equals(lneedsdto.getSelectcrutches())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.CRUTCHES_SELECT, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(lneedsdto.getSelectcrutches()+"   "+lneedsdto.getCrutchestype(), Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }

                      if (null != lneedsdto.getSelectwalkingframe() && !"".equals(lneedsdto.getSelectwalkingframe())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.WALKING_FRAME_SELECT, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(lneedsdto.getSelectwalkingframe(), Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }



                    String[] orthosis_List = {lneedsdto.getAeroplanesplint(),
                                            lneedsdto.getFigure8splint(),lneedsdto.getForearmsplint(),lneedsdto.getHandsplint()};
                    boolean orthosis_Flag = pDFCommonTemplate.getDisplayHeaderFlag(orthosis_List, "");
                    if (orthosis_Flag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.ORTHOSIS_UPPER, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);

                        String[][] orthosis_Lables = {{PDFLables.AEROPLANE_SPLINT, lneedsdto.getAeroplanesplint()}, {PDFLables.FIGURE_8_SPLINT, lneedsdto.getFigure8splint()},
                                                   {PDFLables.FORE_ARM_SPLINT, lneedsdto.getForearmsplint()}, {PDFLables.HAND_SPLINT, lneedsdto.getHandsplint()}};

                        table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                                PDFLables.YES, orthosis_Lables);

                    }


                    String[] prosthesis_List = {lneedsdto.getShoulderprosthesis(),lneedsdto.getAboveelbowprosthesis(),lneedsdto.getElbowdisarticulationprosthesis(),
                                            lneedsdto.getBelowelbowprosthesis(),lneedsdto.getWristdisarticulationprosthesis(),lneedsdto.getHandprosthesis(),
                                            lneedsdto.getCosmeticfingerprosthesis()};
                    boolean prosthesis_Flag = pDFCommonTemplate.getDisplayHeaderFlag(prosthesis_List, "");
                    if (prosthesis_Flag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.UPPER_PROSTHESIS, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);

                        String[][] prosthesis_Lables = {{PDFLables.SHOULDER_PROSTHESIS, lneedsdto.getShoulderprosthesis()}, {PDFLables.ABOVE_ELBOW_PROSTHESIS, lneedsdto.getAboveelbowprosthesis()},
                                                        {PDFLables.ELBOW_DISARTICULATION_PROSTHESIS, lneedsdto.getElbowdisarticulationprosthesis()}, {PDFLables.BELOW_ELBOW_PROSTHESIS, lneedsdto.getBelowelbowprosthesis()},
                                                        {PDFLables.WRIST_DISARTICULATION_PROSTHESIS, lneedsdto.getWristdisarticulationprosthesis()}, {PDFLables.HAND_PROSTHESIS, lneedsdto.getHandprosthesis()},
                                                        {PDFLables.COSMETIC_FINGER_PROSTHESIS, lneedsdto.getCosmeticfingerprosthesis()}
                                                         };

                        table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                                PDFLables.YES, prosthesis_Lables);

                    }

                    String[] orthosis_Lower_List = {lneedsdto.getHkafo(),lneedsdto.getKafo(),
                                            lneedsdto.getAfo(),lneedsdto.getKneeorthosis(),lneedsdto.getDbsplint(),
                                            lneedsdto.getModifiedshoe(),lneedsdto.getSerialcasting()};
                    boolean orthosis_Lower_Flag = pDFCommonTemplate.getDisplayHeaderFlag(orthosis_Lower_List, "");
                    if (orthosis_Lower_Flag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.ORTHOSIS_LOWER, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);

                        String[][] orthosis_Lower_Lables = {{PDFLables.HKAFO, lneedsdto.getHkafo()}, {PDFLables.KAFO, lneedsdto.getKafo()},
                                                        {PDFLables.AFO, lneedsdto.getAfo()}, {PDFLables.KNEE_ORTHOSIS, lneedsdto.getKneeorthosis()},
                                                        {PDFLables.DB_SPLINT, lneedsdto.getDbsplint()}, {PDFLables.MODIFIED_SHOE, lneedsdto.getModifiedshoe()},
                                                        {PDFLables.SERIAL_CASTING_CTEV, lneedsdto.getSerialcasting()}
                                                         };

                        table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                                PDFLables.YES, orthosis_Lower_Lables);

                    }


                     String[] prosthesis_Lower_List = {lneedsdto.getHipprothesis(),
                                            lneedsdto.getAbovekneeprothesis(),lneedsdto.getKneedisarticulation(),lneedsdto.getBelowkneeprothesis(),
                                            lneedsdto.getSymeprothesis(),lneedsdto.getPartialfoodprothesis()};
                    boolean prosthesis_Lower_Flag = pDFCommonTemplate.getDisplayHeaderFlag(prosthesis_Lower_List, "");
                    if (prosthesis_Lower_Flag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.PROSTHESIS_LOWER, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);

                        String[][] prosthesis_Lower_Lables = {{PDFLables.HIP_DISARTICULATION_PROSTHESIS, lneedsdto.getHipprothesis()}, {PDFLables.ABOVE_KNEE_PROSTHESIS, lneedsdto.getAbovekneeprothesis()},
                                                        {PDFLables.KNEE_DISARTICULATION_PROSTHESIS, lneedsdto.getKneedisarticulation()}, {PDFLables.BELOW_KNEE_PROSTHESIS, lneedsdto.getBelowkneeprothesis()},
                                                        {PDFLables.SYMES_PROSTHESIS, lneedsdto.getSymeprothesis()}, {PDFLables.PARTIAL_FOOT_PROSTHESIS, lneedsdto.getPartialfoodprothesis()}
                                                         };

                        table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                                PDFLables.YES, prosthesis_Lower_Lables);

                    }


                   String[] spinal_List = {lneedsdto.getCervicalcollar(),
                                            lneedsdto.getLsbrace(),lneedsdto.getTlsobrace()};
                    boolean spinal_Flag = pDFCommonTemplate.getDisplayHeaderFlag(spinal_List, "");
                    if (spinal_Flag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.SPINAL_ORTHOSIS, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);

                        String[][] spinal_Lables = {{PDFLables.CERVICAL_COLLAR, lneedsdto.getCervicalcollar()}, {PDFLables.LS_BRACE, lneedsdto.getLsbrace()},
                                                        {PDFLables.TLSO_SCOLIOSIS_KYPHOSIS, lneedsdto.getTlsobrace()}
                                                         };

                        table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                                PDFLables.YES, spinal_Lables);

                    }

                   String[] any_Adl_List = {lneedsdto.getFeeding(),
                                            lneedsdto.getBathing(),lneedsdto.getBrushing(),lneedsdto.getCombing(),
                                            lneedsdto.getDressing(),lneedsdto.getWriting(),lneedsdto.getDrivingcycling(),
                                            lneedsdto.getBedtransfer()};
                    boolean any_Adl_Flag = pDFCommonTemplate.getDisplayHeaderFlag(any_Adl_List, "");
                    if (any_Adl_Flag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.PROSTHESIS_LOWER, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);

                        String[][] any_Adl_Lables = {{PDFLables.FEEDING, lneedsdto.getFeeding()}, {PDFLables.TOILETING_BATHING, lneedsdto.getBathing()},
                                                     {PDFLables.BRUSHING, lneedsdto.getBrushing()}, {PDFLables.COMBING, lneedsdto.getCombing()},
                                                     {PDFLables.DRESSING, lneedsdto.getDressing()}, {PDFLables.WRITING, lneedsdto.getWriting()},
                                                     {PDFLables.DRIVING_CYCLING, lneedsdto.getDrivingcycling()}, {PDFLables.BED_TRANSFER, lneedsdto.getBedtransfer()}
                                                     };

                        table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                                PDFLables.YES, any_Adl_Lables);

                    }



                  }

                  if (null != lneedsdto.getAnyotherinlocomotor() && !"".equals(lneedsdto.getAnyotherinlocomotor())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.ANY_OTHER_LOCOMOTOR_NEEDS, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(lneedsdto.getAnyotherinlocomotor(), Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }



              }
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
