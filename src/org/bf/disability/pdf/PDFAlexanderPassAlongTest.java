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
public class PDFAlexanderPassAlongTest {


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

            float[] alex_one_widths = {0.10f, 0.20f,0.40f,0.15f,0.15f};
            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable alex_one_table = new PdfPTable(alex_one_widths);


            if (mrtestdto.getPat_IQ() > 0) {

                String[] alexTestList = {mrtestdto.getPat_Second_One(), mrtestdto.getPat_Second_Two(),
                    mrtestdto.getPat_Second_Three(), mrtestdto.getPat_Second_Four(),
                    mrtestdto.getMisicarithmeticrawscore(), mrtestdto.getPat_Second_Five(),
                    mrtestdto.getPat_Second_Six(), mrtestdto.getPat_Second_Seven(),
                    mrtestdto.getPat_Second_Eight(), mrtestdto.getPat_Second_Nine(),
                    mrtestdto.getPat_SA_One(), mrtestdto.getPat_SA_Two(),mrtestdto.getPat_SA_Three(), mrtestdto.getPat_SA_Four(),
                    mrtestdto.getPat_SA_Five(), mrtestdto.getPat_SA_Six(),
                    mrtestdto.getPat_SA_Seven(), mrtestdto.getPat_SA_Eight(),
                    mrtestdto.getPat_SA_Nine(), mrtestdto.getPat_Remarks_One(),
                    mrtestdto.getPat_Remarks_Two(), mrtestdto.getPat_Remarks_Three(),
                    mrtestdto.getPat_Remarks_Four(), mrtestdto.getPat_Remarks_Five(), mrtestdto.getPat_Remarks_Six(),
                    mrtestdto.getPat_Remarks_Seven(), mrtestdto.getPat_Remarks_Eight(),
                    mrtestdto.getPat_Remarks_Nine()};
                boolean alexTest_Flag = pDFCommonTemplate.getDisplayHeaderFlag(alexTestList, "0");

                if (alexTest_Flag) {

                     cell_one = new PdfPCell(new Paragraph(PDFLables.DESIGN_NO, Font_Bold));
                cell_one.setBorder(0);
                alex_one_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.ALLOTTED_TIME, Font_Bold));
                cell_one.setBorder(0);
                alex_one_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.ACTUAL_TIME_TAKEN_BY_SOLVE_PROBLEM, Font_Bold));
                cell_one.setBorder(0);
                alex_one_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.SCORE, Font_Bold));
                cell_one.setBorder(0);
                alex_one_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.REMARKS, Font_Bold));
                cell_one.setBorder(0);
                alex_one_table.addCell(cell_one);


                String[][] alexvalues = {{"1",PDFLables.TWO_MINITUS,mrtestdto.getPat_Second_One(),mrtestdto.getPat_SA_One(),mrtestdto.getPat_Remarks_One()},
                                          {"2",PDFLables.TWO_MINITUS,mrtestdto.getPat_Second_Two(),mrtestdto.getPat_SA_Two(),mrtestdto.getPat_Remarks_Two()},
                                          {"3",PDFLables.THREE_MINITUS,mrtestdto.getPat_Second_Three(),mrtestdto.getPat_SA_Three(),mrtestdto.getPat_Remarks_Three()},
                                          {"4",PDFLables.THREE_MINITUS,mrtestdto.getPat_Second_Four(),mrtestdto.getPat_SA_Four(),mrtestdto.getPat_Remarks_Four()},
                                          {"5",PDFLables.THREE_MINITUS,mrtestdto.getPat_Second_Five(),mrtestdto.getPat_SA_Five(),mrtestdto.getPat_Remarks_Five()},
                                          {"6",PDFLables.THREE_MINITUS,mrtestdto.getPat_Second_Six(),mrtestdto.getPat_SA_Six(),mrtestdto.getPat_Remarks_Six()},
                                          {"7",PDFLables.THREE_MINITUS,mrtestdto.getPat_Second_Seven(),mrtestdto.getPat_SA_Seven(),mrtestdto.getPat_Remarks_Seven()},
                                          {"8",PDFLables.FOUR_MINITUS,mrtestdto.getPat_Second_Eight(),mrtestdto.getPat_SA_Eight(),mrtestdto.getPat_Remarks_Eight()},
                                          {"9",PDFLables.FIVE_MINITUS,mrtestdto.getPat_Second_Nine(),mrtestdto.getPat_SA_Nine(),mrtestdto.getPat_Remarks_Nine()}};

                 alex_one_table = pDFCommonTemplate.creatingMRALEXCCells(alex_one_table, Font_Bold, Font_Normal, cell_one,
                                                        "0", alexvalues);
                }




                cell_one = new PdfPCell(new Paragraph(PDFLables.MENTAL_AGE, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                alex_one_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS+"    "+mrtestdto.getPat_Year(), Font_Bold));
                cell_one.setBorder(0);
                alex_one_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS+"    "+mrtestdto.getPat_Month(), Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                alex_one_table.addCell(cell_one);

                

                cell_one = new PdfPCell(new Paragraph(PDFLables.IQ, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                alex_one_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(String.valueOf(Math.round(mrtestdto.getPat_IQ())), Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                alex_one_table.addCell(cell_one);



            }




            cell_one = new PdfPCell(alex_one_table);
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

}
