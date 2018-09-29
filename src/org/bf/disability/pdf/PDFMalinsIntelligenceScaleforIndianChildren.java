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
public class PDFMalinsIntelligenceScaleforIndianChildren {

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


            float[] misic_one_widths = {0.40f, 0.30f, 0.30f};
            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable misic_one_table = new PdfPTable(misic_one_widths);

            String[] verbalTestList = {mrtestdto.getMisicinformationraw(),mrtestdto.getMisicinformationtq(),
                                       mrtestdto.getMisiccomprehensionrawscore(),mrtestdto.getMisiccomprehensiontq(),
                                       mrtestdto.getMisicarithmeticrawscore(),mrtestdto.getMisicarithmetictq(),
                                       mrtestdto.getMisicsimilaritiesrawscore(),mrtestdto.getMisicsimilaritiestq(),
                                       mrtestdto.getMisicvocabularyrawscore(),mrtestdto.getMisicvocabularytq(),
                                       mrtestdto.getMisicdigitspanrawscore(),mrtestdto.getMisicdigitspantq()};
            boolean  verbalTest_Flag = pDFCommonTemplate.getDisplayHeaderFlag(verbalTestList, "0");

            if(verbalTest_Flag){
                cell_one = new PdfPCell(new Paragraph(PDFLables.VERBAL_TEST, Font_Bold));
                cell_one.setBorder(0);
                misic_one_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.RAW_SCORE, Font_Bold));
                cell_one.setBorder(0);
                misic_one_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.IQ, Font_Bold));
                cell_one.setBorder(0);
                misic_one_table.addCell(cell_one);

                String[][] vtestvalues = {{PDFLables.INFORMATION,mrtestdto.getMisicinformationraw(),mrtestdto.getMisicinformationtq()},
                                          {PDFLables.COMPREHENSION,mrtestdto.getMisiccomprehensionrawscore(),mrtestdto.getMisiccomprehensiontq()},
                                          {PDFLables.ARITHMETIC,mrtestdto.getMisicarithmeticrawscore(),mrtestdto.getMisicarithmetictq()},
                                          {PDFLables.SIMILARITIES,mrtestdto.getMisicsimilaritiesrawscore(),mrtestdto.getMisicsimilaritiestq()},
                                          {PDFLables.VOCABULARY,mrtestdto.getMisicvocabularyrawscore(),mrtestdto.getMisicvocabularytq()},
                                          {PDFLables.DIGIT_SPAN,mrtestdto.getMisicdigitspanrawscore(),mrtestdto.getMisicdigitspantq()}};

                 misic_one_table = pDFCommonTemplate.creatingMRMISICCells(misic_one_table, Font_Bold, Font_Normal, cell_one,
                                                        "0", vtestvalues);


            }

            cell_one = new PdfPCell(misic_one_table);
            cell_one.setBorder(0);
            table.addCell(cell_one);

             PdfPTable misic_two_table = new PdfPTable(misic_one_widths);

            String[] perfomanceTestList = {mrtestdto.getMisicpcrawscore(),mrtestdto.getMisicpctq(),
                                       mrtestdto.getMisicbdrawscore(),mrtestdto.getMisicbdtq(),
                                       mrtestdto.getMisicoarawscore(),mrtestdto.getMisicoatq(),
                                       mrtestdto.getMisiccodingrawscore(),mrtestdto.getMisiccodingtq(),
                                       mrtestdto.getMisicmazesrawscore(),mrtestdto.getMisicmazestq()};
            boolean  perfomanceTest_Flag = pDFCommonTemplate.getDisplayHeaderFlag(perfomanceTestList, "0");

            if(perfomanceTest_Flag){
                cell_one = new PdfPCell(new Paragraph(PDFLables.PERFORMANCE_TEST, Font_Bold));
                cell_one.setBorder(0);
                misic_two_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.RAW_SCORE, Font_Bold));
                cell_one.setBorder(0);
                misic_two_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.IQ, Font_Bold));
                cell_one.setBorder(0);
                misic_two_table.addCell(cell_one);

                String[][] ptestvalues = {{PDFLables.PICTURE_COMPLETION,mrtestdto.getMisicpcrawscore(),mrtestdto.getMisicpctq()},
                                          {PDFLables.BLOCK_DESIGN,mrtestdto.getMisicbdrawscore(),mrtestdto.getMisicbdtq()},
                                          {PDFLables.OBJECT_ASSEMBLY,mrtestdto.getMisicoarawscore(),mrtestdto.getMisicoatq()},
                                          {PDFLables.CODING,mrtestdto.getMisiccodingrawscore(),mrtestdto.getMisiccodingtq()},
                                          {PDFLables.MAZES,mrtestdto.getMisicmazesrawscore(),mrtestdto.getMisicmazestq()}};

                 misic_two_table = pDFCommonTemplate.creatingMRMISICCells(misic_two_table, Font_Bold, Font_Normal, cell_one,
                                                        "0", ptestvalues);


            }

            cell_one = new PdfPCell(misic_two_table);
            cell_one.setBorder(0);
            table.addCell(cell_one);

            // final IQ value Display

            if(verbalTest_Flag || perfomanceTest_Flag){

                cell_one = new PdfPCell(new Paragraph(PDFLables.MISIC_IQ, Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(String.valueOf(Math.round(mrtestdto.getMisiciq())), Font_Bold));
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
