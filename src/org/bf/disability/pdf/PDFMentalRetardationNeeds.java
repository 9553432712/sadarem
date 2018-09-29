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
import org.bf.disability.dto.MentalRetardationDTO;

/**
 *
 * @author 509864
 */
public class PDFMentalRetardationNeeds {

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
            MentalRetardationDTO mrdto = (MentalRetardationDTO) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));


            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            if (null != mrdto) {
                if (null != mrdto.getMentalage() && !"".equals(mrdto.getMentalage()) && !"0".equals(mrdto.getMentalage())) {

                    cell_one = new PdfPCell(new Paragraph(mrdto.getChronologicalage(), Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.INTELLIGENT_QUOTIENT, Font_Normal));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(mrdto.getMentalage(), Font_Normal));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);
                }


                String[] mrNeeds_List = {mrdto.getSpeechtheraphy(), mrdto.getBehaviormodification(),
                    mrdto.getSensoryintegrationcognitive(), mrdto.getSpeechtherapy(),
                    mrdto.getPhysiotherapy(), mrdto.getOccupationaltherapy(),
                    mrdto.getPsycotherapybehaviour(), mrdto.getCognitivebehaviourtherapy(),
                    mrdto.getParientfamilyintervention(), mrdto.getLegalguardian(),
                    mrdto.getLearningmaterials(), mrdto.getSpecialsoftware(),
                    mrdto.getToys(), mrdto.getAnyothermentalretardation()};

                boolean mrNeeds_Flag = pDFCommonTemplate.getDisplayHeaderFlag(mrNeeds_List, "");

                if (mrNeeds_Flag) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.NEED_ASSESSMENT, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    table.addCell(cell_one);

                    String[] early_List = {mrdto.getSpeechtheraphy(), mrdto.getBehaviormodification()};
                    boolean early_Flag = pDFCommonTemplate.getDisplayHeaderFlag(early_List, "");
                    if (early_Flag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.EARLY_INTERVENTION_SERVICES, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);

                        String[][] early_Lables = {{PDFLables.SPEECH_THERAPY_BELOW3YEARS, mrdto.getSpeechtheraphy()}, {PDFLables.BEHAVIOUR_MODIFICATION, mrdto.getBehaviormodification()}};

                        table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                                PDFLables.YES, early_Lables);

                    }

                    String[][] needs_Lables = {{PDFLables.SENSORY_INTEGRATION, mrdto.getSensoryintegrationcognitive()}, {PDFLables.SPEECH_DEVELOPMENT, mrdto.getSpeechtherapy()},
                        {PDFLables.PHYSIOTHERAPAY, mrdto.getPhysiotherapy()}, {PDFLables.OCCUPATIONAL_THERAPHY, mrdto.getOccupationaltherapy()},
                        {PDFLables.PSYCOTHERAPHY, mrdto.getPsycotherapybehaviour()}, {PDFLables.COGNITIVE, mrdto.getCognitivebehaviourtherapy()},
                        {PDFLables.PARENT_FAMILY, mrdto.getParientfamilyintervention()}, {PDFLables.LEGAL_GUARDIAN, mrdto.getLegalguardian()}};

                    table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.YES, needs_Lables);

                    String[] Assistive_list = {mrdto.getLearningmaterials(), mrdto.getSpecialsoftware(), mrdto.getToys()};

                    boolean Assistive_Flag = pDFCommonTemplate.getDisplayHeaderFlag(Assistive_list, "");

                    if (Assistive_Flag) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.ASSISTIVE_ARGUMENTIVE_DEVICES, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);

                        String[][] assistive_Lables = {{PDFLables.LEARNING_MATERIAL, mrdto.getLearningmaterials()},
                            {PDFLables.SPECIAL_SOFTWARE, mrdto.getSpecialsoftware()},
                            {PDFLables.TOYS, mrdto.getToys()}};

                        table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                                PDFLables.YES, assistive_Lables);
                    }
                    if (null != mrdto.getAnyothermentalretardation() && !"".equals(mrdto.getAnyothermentalretardation())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.ANYOTHER_MR_NEEDS, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(mrdto.getAnyothermentalretardation(), Font_Bold));
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
