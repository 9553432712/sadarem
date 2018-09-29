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
import org.bf.disability.dto.MentalIllnessDTO;

/**
 *
 * @author 509864
 */
public class PDFMentalIllness {

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
            MentalIllnessDTO millnessdto = (MentalIllnessDTO) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            float[] mentalill_widths = {0.90f, 0.10f};
            PdfPTable mentalill_table = new PdfPTable(mentalill_widths);
           
            if (null != millnessdto) {

                String[] evalution_List = {String.valueOf(millnessdto.getSelfcare()),String.valueOf(millnessdto.getPersonalactivities()),
                                         String.valueOf(millnessdto.getCommunication()),String.valueOf(millnessdto.getWork())};
                    boolean evalution_Flag = pDFCommonTemplate.getDisplayHeaderFlag(evalution_List, "0");
                    if (evalution_Flag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.EVALUTION_OF_MENTAL_ILLNESS, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        mentalill_table.addCell(cell_one);

                         if (millnessdto.getSelfcare() > 0) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.SELF_CARE, Font_Normal));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(String.valueOf(millnessdto.getSelfcare()), Font_Bold));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);
                    }

                         if (millnessdto.getPersonalactivities() > 0) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.INTER_PERSONAL_ACTIVITIES, Font_Normal));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(String.valueOf(millnessdto.getPersonalactivities()), Font_Bold));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);
                    }

                         if (millnessdto.getCommunication() > 0) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.COMMUNICATION_UNDERSTANDING, Font_Normal));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(String.valueOf(millnessdto.getCommunication()), Font_Bold));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);
                    }

                         if (millnessdto.getWork() > 0) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.ANY_OTHER_LOCOMOTOR_NEEDS, Font_Normal));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(String.valueOf(millnessdto.getWork()), Font_Bold));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);
                    }
                    }
                     if (millnessdto.getDuration() > 0) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.DURATION_OF_ILLNESS, Font_Normal));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(String.valueOf(millnessdto.getDuration()), Font_Bold));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);
                    }

                     if (millnessdto.getGlobalscore() > 0) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.GLOBAL_DISABILITY_SCORE, Font_Normal));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(String.valueOf(millnessdto.getGlobalscore()), Font_Bold));
                        cell_one.setBorder(0);
                        mentalill_table.addCell(cell_one);
                    }

                cell_one = new PdfPCell(mentalill_table);
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                table.addCell(cell_one);

               String[] need_Assessment_List = {millnessdto.getBehaviormodification(),millnessdto.getSurgery(),
                                                millnessdto.getPsycotherapybehaviour(),millnessdto.getManagerproperties(),
                                                millnessdto.getPsychiatrichospital(),millnessdto.getAnyotherneed()};
                    boolean need_Assessment_Flag = pDFCommonTemplate.getDisplayHeaderFlag(need_Assessment_List, "");
                    if (need_Assessment_Flag) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NEED_ASSESSMENT, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    table.addCell(cell_one);

                    if (null != millnessdto.getBehaviormodification() && !"".equals(millnessdto.getBehaviormodification())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.EARLY_INTERVENTION_BELOW_3_YEARS, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.BEHAVIOUR_MODIFICATION, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }

                    if (null != millnessdto.getSurgery() && !"".equals(millnessdto.getSurgery())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.SURGERY, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(millnessdto.getSurgery(), Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }

                    if (null != millnessdto.getPsycotherapybehaviour() && !"".equals(millnessdto.getPsycotherapybehaviour())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.PSYCOTHERAPY_BEHAVIOUR, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }
                    if (null != millnessdto.getManagerproperties() && !"".equals(millnessdto.getManagerproperties())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MANAGER_TO_TAKECARE, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }
                    if (null != millnessdto.getPsychiatrichospital() && !"".equals(millnessdto.getPsychiatrichospital())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.ADMISSION_IN_PSYCHIATRIC, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }
                    if (null != millnessdto.getAnyotherneed() && !"".equals(millnessdto.getAnyotherneed())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.ANY_OTHER_MENTAL_ILLNESS, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(millnessdto.getAnyotherneed(), Font_Bold));
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
