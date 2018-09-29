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
public class PDFBhatiasBatteryofIntelligenceTests {


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

             PdfPCell cell_one = new PdfPCell(new Paragraph(""));

            if(null != mrtestdto.getBbpti_IQ() && !"".equals(mrtestdto.getBbpti_IQ()) &&
                    !"0".equals(mrtestdto.getBbpti_IQ())){

                cell_one = new PdfPCell(new Paragraph(PDFLables.TESTS, Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.SCORE, Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                if (null != mrtestdto.getBbpti_KOHS_Block_Design_Test() && !"".equals(mrtestdto.getBbpti_KOHS_Block_Design_Test())
                        && !"0".equals(mrtestdto.getBbpti_KOHS_Block_Design_Test())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.KOHS_BLOCK_DESIGN_TEST, Font_Normal));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(mrtestdto.getBbpti_KOHS_Block_Design_Test(), Font_Bold));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);

                }

                if (null != mrtestdto.getBbpti_Passalong_Test() && !"".equals(mrtestdto.getBbpti_Passalong_Test())
                        && !"0".equals(mrtestdto.getBbpti_Passalong_Test())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PASS_ALONG_TEST, Font_Normal));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(mrtestdto.getBbpti_Passalong_Test(), Font_Bold));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);

                }

                if (null != mrtestdto.getBbpti_PatternDrawing_Test() && !"".equals(mrtestdto.getBbpti_PatternDrawing_Test())
                        && !"0".equals(mrtestdto.getBbpti_PatternDrawing_Test())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PATTERN_DRAWING_TEST, Font_Normal));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(mrtestdto.getBbpti_PatternDrawing_Test(), Font_Bold));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);

                }

                if (null != mrtestdto.getBbpti_ImmediateMemory_Test() && !"".equals(mrtestdto.getBbpti_ImmediateMemory_Test())
                        && !"0".equals(mrtestdto.getBbpti_ImmediateMemory_Test())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.IMMEDIATE_MEMORY_TEST, Font_Normal));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(mrtestdto.getBbpti_ImmediateMemory_Test(), Font_Bold));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);

                }

                if (null != mrtestdto.getBbpti_PictureConstruction_Test() && !"".equals(mrtestdto.getBbpti_PictureConstruction_Test())
                        && !"0".equals(mrtestdto.getBbpti_PictureConstruction_Test())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PICTURE_CONSTRUCTION_TEST, Font_Normal));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(mrtestdto.getBbpti_PictureConstruction_Test(), Font_Bold));
                    cell_one.setBorder(0);
                    table.addCell(cell_one);

                }


                cell_one = new PdfPCell(new Paragraph(PDFLables.IQ, Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(mrtestdto.getBbpti_IQ(), Font_Bold));
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
