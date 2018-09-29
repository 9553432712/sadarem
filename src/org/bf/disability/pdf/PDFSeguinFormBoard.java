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

/**
 *
 * @author 509864
 */
public class PDFSeguinFormBoard {


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
            MentalRetardationTestsDTO mrtestdto = (MentalRetardationTestsDTO) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));


           if(mrtestdto.getSfbiq()>0){

               PdfPCell cell_one = new PdfPCell(new Paragraph(""));

               cell_one = new PdfPCell(new Paragraph(PDFLables.TRIALS, Font_Bold));
               cell_one.setBorder(0);
               table.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(PDFLables.TIME_TAKEN_IN_SECONDS, Font_Bold));
               cell_one.setBorder(0);
               table.addCell(cell_one);

               if (null != mrtestdto.getSfbtrialone() && !"".equals(mrtestdto.getSfbtrialone())) {
                   cell_one = new PdfPCell(new Paragraph(PDFLables.TRIAL_1, Font_Normal));
                   cell_one.setBorder(0);
                   table.addCell(cell_one);

                   cell_one = new PdfPCell(new Paragraph(mrtestdto.getSfbtrialone(), Font_Bold));
                   cell_one.setBorder(0);
                   table.addCell(cell_one);
               }

               if (null != mrtestdto.getSfbtrialtwo() && !"".equals(mrtestdto.getSfbtrialtwo())) {
                   cell_one = new PdfPCell(new Paragraph(PDFLables.TRIAL_2, Font_Normal));
                   cell_one.setBorder(0);
                   table.addCell(cell_one);

                   cell_one = new PdfPCell(new Paragraph(mrtestdto.getSfbtrialtwo(), Font_Bold));
                   cell_one.setBorder(0);
                   table.addCell(cell_one);
               }

               if (null != mrtestdto.getSfbtrialthree() && !"".equals(mrtestdto.getSfbtrialthree())) {
                   cell_one = new PdfPCell(new Paragraph(PDFLables.TRIAL_3, Font_Normal));
                   cell_one.setBorder(0);
                   table.addCell(cell_one);

                   cell_one = new PdfPCell(new Paragraph(mrtestdto.getSfbtrialthree(), Font_Bold));
                   cell_one.setBorder(0);
                   table.addCell(cell_one);
               }


               if((null != mrtestdto.getSfbyear() && !"".equals(mrtestdto.getSfbyear())) ||
                   (null != mrtestdto.getSfbmonth() && !"".equals(mrtestdto.getSfbmonth()))){

                   cell_one = new PdfPCell(new Paragraph(PDFLables.MENTAL_AGE, Font_Normal));
                   cell_one.setBorder(0);
                   cell_one.setColspan(2);
                   table.addCell(cell_one);

                   cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS+"    "+mrtestdto.getSfbyear(), Font_Bold));
                   cell_one.setBorder(0);
                   table.addCell(cell_one);

                   cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS+"    "+mrtestdto.getSfbmonth(), Font_Bold));
                   cell_one.setBorder(0);
                   table.addCell(cell_one);


               }


         
               cell_one = new PdfPCell(new Paragraph(PDFLables.IQ, Font_Normal));
               cell_one.setBorder(0);
               table.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(String.valueOf(Math.round(mrtestdto.getSfbiq())), Font_Bold));
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
