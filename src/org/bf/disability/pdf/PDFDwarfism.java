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
import org.bf.disability.dto.DwarfismDTO;

/**
 *
 * @author 509864
 */
public class PDFDwarfism {

     public PdfPTable getTable(HttpServletRequest request) throws DocumentException, IOException {
        float[] widths = {0.50f, 0.50f};
        PdfPTable table = new PdfPTable(widths);
        Font Font_Bold = null;
        Font Font_Normal = null;
        Font Font_Bold_Color = null;
        Font Font_Section_heading = null;
        try {
            table.setSplitRows(true);
            DwarfismDTO dwarfismdto = (DwarfismDTO) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));


            if (dwarfismdto.getAgeyears() >= 0 && dwarfismdto.getAgemonths() >= 0
                    && dwarfismdto.getHeight() >= 0) {
                float[] dwarfism_widths = {0.20f, 0.20f, 0.20f, 0.20f, 0.20f};
                PdfPCell cell_one = new PdfPCell(new Paragraph(""));
                PdfPTable dwarfism_table = new PdfPTable(dwarfism_widths);


                cell_one = new PdfPCell(new Paragraph(PDFLables.AGE_OF_PERSON, Font_Normal));
                cell_one.setBorder(0);
                cell_one.setColspan(4);
                dwarfism_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.TOTAL_HEIGHT, Font_Bold));
                cell_one.setBorder(0);
                dwarfism_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS, Font_Normal));
                cell_one.setBorder(0);
                dwarfism_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(String.valueOf(dwarfismdto.getAgeyears()), Font_Bold));
                cell_one.setBorder(0);
                dwarfism_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS, Font_Normal));
                cell_one.setBorder(0);
                dwarfism_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(String.valueOf(dwarfismdto.getAgemonths()), Font_Bold));
                cell_one.setBorder(0);
                dwarfism_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(String.valueOf(dwarfismdto.getHeight()), Font_Bold));
                cell_one.setBorder(0);
                dwarfism_table.addCell(cell_one);

                


                cell_one = new PdfPCell(dwarfism_table);
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
