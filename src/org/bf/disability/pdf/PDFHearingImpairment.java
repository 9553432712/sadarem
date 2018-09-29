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
import org.bf.disability.common.CommonDetails;
import org.bf.disability.dto.HearingImpairmentDto;

/**
 *
 * @author t_bapinaidu
 */
public class PDFHearingImpairment {


     public PdfPTable getTable(HttpServletRequest request) throws DocumentException, IOException {
        float[] widths = {0.50f, 0.50f};
        PdfPTable table = new PdfPTable(widths);
        Font Font_Bold = null;
        Font Font_Normal = null;
        Font Font_Bold_Color = null;
        Font Font_Section_heading = null;
        try {
            table.setSplitRows(true);
            CommonDetails commonDetails = new CommonDetails();
            HearingImpairmentDto hearingdto = (HearingImpairmentDto) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            float[] hearing_widths = {0.20f, 0.10f, 0.10f, 0.10f, 0.10f, 0.10f, 0.10f, 0.10f};
            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable hearing_table = new PdfPTable(hearing_widths);

            cell_one = new PdfPCell(new Paragraph(PDFLables.FREQUENCY, Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);

            cell_one = new PdfPCell(new Paragraph(PDFLables.FREQUENCY_250, Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);

            cell_one = new PdfPCell(new Paragraph(PDFLables.FREQUENCY_500, Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);

            cell_one = new PdfPCell(new Paragraph(PDFLables.FREQUENCY_1000, Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);


            cell_one = new PdfPCell(new Paragraph(PDFLables.FREQUENCY_2000, Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);

            cell_one = new PdfPCell(new Paragraph(PDFLables.FREQUENCY_4000, Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);

            cell_one = new PdfPCell(new Paragraph(PDFLables.FREQUENCY_8000, Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);

            cell_one = new PdfPCell(new Paragraph(PDFLables.DB_Level, Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
 

            if (null != hearingdto.getRighteardblevel() && !"".equals(hearingdto.getRighteardblevel())) {

            cell_one = new PdfPCell(new Paragraph(PDFLables.RIGHT_EAR, Font_Normal));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            if(null != hearingdto.getRightear250()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getRightear250(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }

            if(null != hearingdto.getRightear500()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getRightear500(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }

            if(null != hearingdto.getRightear1000()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getRightear1000(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }

            if(null != hearingdto.getRightear2000()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getRightear2000(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }
            if(null != hearingdto.getRightear4000()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getRightear4000(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }
            if(null != hearingdto.getRightear8000()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getRightear8000(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }
            if(null != hearingdto.getRighteardblevel()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getRighteardblevel(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }


            }

            if (null != hearingdto.getLefteardblevel() && !"".equals(hearingdto.getLefteardblevel())) {

            cell_one = new PdfPCell(new Paragraph(PDFLables.LEFT_EAR, Font_Normal));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            if(null != hearingdto.getLeftear250()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getLeftear250(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }

            if(null != hearingdto.getLeftear500()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getLeftear500(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }

            if(null != hearingdto.getLeftear1000()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getLeftear1000(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }

            if(null != hearingdto.getLeftear2000()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getLeftear2000(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }
            if(null != hearingdto.getLeftear4000()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getLeftear4000(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }
            if(null != hearingdto.getLeftear8000()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getLeftear8000(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }
            if(null != hearingdto.getLefteardblevel()){
            cell_one = new PdfPCell(new Paragraph(hearingdto.getLefteardblevel(), Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }else{
            cell_one = new PdfPCell(new Paragraph("", Font_Bold));
            cell_one.setBorder(0);
            hearing_table.addCell(cell_one);
            }

            }

            cell_one = new PdfPCell(hearing_table);
            cell_one.setBorder(0);
            cell_one.setColspan(2);
            table.addCell(cell_one);

             float[] hearing_needs_widths = {0.30f,0.30f,0.40f};
             PdfPTable hearing_needs_table = new PdfPTable(hearing_needs_widths);

            if (null != hearingdto.getSpeechaudiometryrightear_pta() && !"".equals(hearingdto.getSpeechaudiometryrightear_pta())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.SPEECH_DISCRIMINATION_RIGHT_EAR, Font_Normal));
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                hearing_needs_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(hearingdto.getSpeechaudiometryrightear_pta(), Font_Bold));
                cell_one.setBorder(0);
                hearing_needs_table.addCell(cell_one);

            }

             if (null != hearingdto.getSpeechaudiometryleftear_pta() && !"".equals(hearingdto.getSpeechaudiometryleftear_pta())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.SPEECH_DISCRIMINATION_LEFT_EAR, Font_Normal));
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                hearing_needs_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(hearingdto.getSpeechaudiometryleftear_pta(), Font_Bold));
                cell_one.setBorder(0);
                hearing_needs_table.addCell(cell_one);

            }
               boolean needsFlag = getNeedsFlag(hearingdto);

               if (needsFlag) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.NEED_ASSESSMENT, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(3);
                    hearing_needs_table.addCell(cell_one);

                   if (null != hearingdto.getSpeechtheraphy() && !"".equals(hearingdto.getSpeechtheraphy())) {

                       cell_one = new PdfPCell(new Paragraph(PDFLables.EARLY_INTERVENTION_SERVICES, Font_Bold));
                       cell_one.setBorder(0);
                       cell_one.setColspan(3);
                       hearing_needs_table.addCell(cell_one);

                       cell_one = new PdfPCell(new Paragraph(PDFLables.SPEECH_THERAPY_BELOW3YEARS, Font_Normal));
                       cell_one.setBorder(0);
                       cell_one.setColspan(2);
                       hearing_needs_table.addCell(cell_one);

                       cell_one = new PdfPCell(new Paragraph(hearingdto.getSpeechtheraphy(), Font_Bold));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);

                   }

                    if (null != hearingdto.getSurgery() && !"".equals(hearingdto.getSurgery())) {

                       cell_one = new PdfPCell(new Paragraph(PDFLables.SURGERY_HEARING, Font_Normal));
                       cell_one.setBorder(0);
                       cell_one.setColspan(2);
                       hearing_needs_table.addCell(cell_one);

                       cell_one = new PdfPCell(new Paragraph(hearingdto.getSurgery(), Font_Bold));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);

                   }
                    
                    if (null != hearingdto.getSpeechtherapy() && !"".equals(hearingdto.getSpeechtherapy())) {

                       cell_one = new PdfPCell(new Paragraph(PDFLables.SPEECH_THERAPY_ABOVE3YEARS, Font_Normal));
                       cell_one.setBorder(0);
                       cell_one.setColspan(2);
                       hearing_needs_table.addCell(cell_one);

                       cell_one = new PdfPCell(new Paragraph(hearingdto.getSpeechtherapy(), Font_Bold));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);

                   }

                    if (null != hearingdto.getLanguagedevelopment() && !"".equals(hearingdto.getLanguagedevelopment())) {

                       cell_one = new PdfPCell(new Paragraph(PDFLables.LANGUAGE_DEVELOPMENT, Font_Normal));
                       cell_one.setBorder(0);
                       cell_one.setColspan(2);
                       hearing_needs_table.addCell(cell_one);

                       cell_one = new PdfPCell(new Paragraph(hearingdto.getLanguagedevelopment(), Font_Bold));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);

                   }

                     boolean assistiveNeedsFlag = getAssistiveDevicesFlag(hearingdto);
                   if (assistiveNeedsFlag) {
                    if (null != hearingdto.getHearingaidselect() ||
                            null != hearingdto.getHearingaidtype()) {

                       cell_one = new PdfPCell(new Paragraph(PDFLables.HESRING_AID, Font_Normal));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);
                       if(null != hearingdto.getHearingaidselect()){
                       cell_one = new PdfPCell(new Paragraph(hearingdto.getHearingaidselect(), Font_Bold));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);
                       }else{
                         cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);
                       }
                        if(null != hearingdto.getHearingaidtype()){
                       cell_one = new PdfPCell(new Paragraph(hearingdto.getHearingaidtype(), Font_Bold));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);
                       }else{
                         cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);
                       }

                   }

                     if (null != hearingdto.getCochlearimplantation() && !"".equals(hearingdto.getCochlearimplantation())) {

                       cell_one = new PdfPCell(new Paragraph(PDFLables.COCHLEAR_IMPLANTATION, Font_Normal));
                       cell_one.setBorder(0);
                       cell_one.setColspan(2);
                       hearing_needs_table.addCell(cell_one);

                       cell_one = new PdfPCell(new Paragraph(hearingdto.getCochlearimplantation(), Font_Bold));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);

                   }

                     if (null != hearingdto.getImplantablehearingaid() && !"".equals(hearingdto.getImplantablehearingaid())) {

                       cell_one = new PdfPCell(new Paragraph(PDFLables.IMPLANTABLE_HEARING_AID, Font_Normal));
                       cell_one.setBorder(0);
                       cell_one.setColspan(2);
                       hearing_needs_table.addCell(cell_one);

                       cell_one = new PdfPCell(new Paragraph(hearingdto.getImplantablehearingaid(), Font_Bold));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);

                   }
                    
                    if (null != hearingdto.getAnyotherhearingimpairement() && !"".equals(hearingdto.getAnyotherhearingimpairement())) {

                       cell_one = new PdfPCell(new Paragraph(PDFLables.ANY_OTHER_HEARING, Font_Normal));
                       cell_one.setBorder(0);
                       cell_one.setColspan(2);
                       hearing_needs_table.addCell(cell_one);

                       cell_one = new PdfPCell(new Paragraph(hearingdto.getAnyotherhearingimpairement(), Font_Bold));
                       cell_one.setBorder(0);
                       hearing_needs_table.addCell(cell_one);

                   }
                   }
               }

            cell_one = new PdfPCell(hearing_needs_table);
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




     public boolean getNeedsFlag(HearingImpairmentDto hearingdto) throws IOException {
        try {
            if (null != hearingdto.getSpeechtheraphy()
                    && !"".equals(hearingdto.getSpeechtheraphy())) {
                return true;
            } else if (null != hearingdto.getSurgery()
                    && !"".equals(hearingdto.getSurgery())) {
                return true;
            } else if (null != hearingdto.getSpeechtherapy()
                    && !"".equals(hearingdto.getSpeechtherapy())) {
                return true;
            } else if (null != hearingdto.getLanguagedevelopment()
                    && !"".equals(hearingdto.getLanguagedevelopment())) {
                return true;
            } else if (null != hearingdto.getHearingaidselect()
                    && !"".equals(hearingdto.getHearingaidselect())) {
                return true;
            } else if (null != hearingdto.getHearingaidtype()
                    && !"".equals(hearingdto.getHearingaidtype())) {
                return true;
            } else if (null != hearingdto.getCochlearimplantation()
                    && !"".equals(hearingdto.getCochlearimplantation())) {
                return true;
            } else if (null != hearingdto.getImplantablehearingaid()
                    && !"".equals(hearingdto.getImplantablehearingaid())) {
                return true;
            } else if (null != hearingdto.getAnyotherhearingimpairement()
                    && !"".equals(hearingdto.getAnyotherhearingimpairement())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



     public boolean getAssistiveDevicesFlag(HearingImpairmentDto hearingdto) throws IOException {

        try {
            if (null != hearingdto.getHearingaidselect()
                    && !"".equals(hearingdto.getHearingaidselect())) {
                return true;
            } else if (null != hearingdto.getHearingaidtype()
                    && !"".equals(hearingdto.getHearingaidtype())) {
                return true;
            } else if (null != hearingdto.getCochlearimplantation()
                    && !"".equals(hearingdto.getCochlearimplantation())) {
                return true;
            } else if (null != hearingdto.getImplantablehearingaid()
                    && !"".equals(hearingdto.getImplantablehearingaid())) {
                return true;
            } else if (null != hearingdto.getAnyotherhearingimpairement()
                    && !"".equals(hearingdto.getAnyotherhearingimpairement())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
