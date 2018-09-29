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

/**
 *
 * @author 509864
 */
public class PDFCardiopulmonaryDiseases {

     public PdfPTable getTable(HttpServletRequest request) throws DocumentException, IOException {
        float[] widths = {0.50f, 0.50f};
        PdfPTable table = new PdfPTable(widths);
        Font Font_Bold = null;
        Font Font_Normal = null;
        Font Font_Bold_Color = null;
        Font Font_Section_heading = null;
        try {
            table.setSplitRows(true);
            String cardiopulmonary =(String)request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            if(!"".equals(cardiopulmonary) && !"0".equals(cardiopulmonary)){
                 float[] cardiopulmonary_widths = {0.80f, 0.20f};
                PdfPCell cell_one = new PdfPCell(new Paragraph(""));
                PdfPTable cardiopulmonary_table = new PdfPTable(cardiopulmonary_widths);

                if("13.0".equals(cardiopulmonary)){
                     cell_one = new PdfPCell(new Paragraph(PDFLables.GROUP_0, Font_Normal));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                }

                 if("25.0".equals(cardiopulmonary)){
                     cell_one = new PdfPCell(new Paragraph(PDFLables.GROUP_1, Font_Normal));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                }

                 if("38.0".equals(cardiopulmonary)){
                     cell_one = new PdfPCell(new Paragraph(PDFLables.GROUP_2, Font_Normal));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                }

                 if("63.0".equals(cardiopulmonary)){
                     cell_one = new PdfPCell(new Paragraph(PDFLables.GROUP_3, Font_Normal));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                }

                 if("88.0".equals(cardiopulmonary)){
                     cell_one = new PdfPCell(new Paragraph(PDFLables.GROUP_4, Font_Normal));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                }

                 if("100.0".equals(cardiopulmonary)){
                     cell_one = new PdfPCell(new Paragraph(PDFLables.GROUP_5, Font_Normal));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    cardiopulmonary_table.addCell(cell_one);

                }

                

                 cell_one = new PdfPCell(cardiopulmonary_table);
                cell_one.setBorder(0);
                cell_one.setColspan(2);
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
