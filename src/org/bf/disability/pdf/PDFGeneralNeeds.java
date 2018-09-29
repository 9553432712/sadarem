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
import org.bf.disability.dto.PartCUpdateDTO;

/**
 *
 * @author 509864
 */
public class PDFGeneralNeeds {


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
            PartCUpdateDTO partcdto = (PartCUpdateDTO) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            if (null != partcdto) {

                  String[] general_Needs_List = {partcdto.getEarlyeducationservices(),partcdto.getEducationservies(),
                                                 partcdto.getVocationaltraining(),partcdto.getIndividual(),
                                                 partcdto.getFamily(),partcdto.getAnyotherservices()
                                                  };
                  boolean general_Needs_Flag = pDFCommonTemplate.getDisplayHeaderFlag(general_Needs_List, "");
                  if(general_Needs_Flag){

                        cell_one = new PdfPCell(new Paragraph(PDFLables.NEED_ASSESSMENT_COMMON_FOR_ALL, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);


                      if (null != partcdto.getEarlyeducationservices() && !"".equals(partcdto.getEarlyeducationservices())) {

                          cell_one = new PdfPCell(new Paragraph(PDFLables.EARLY_EDUCATION_CHILDREN_BELOW_5_YEARS, Font_Normal));
                          cell_one.setBorder(0);
                          table.addCell(cell_one);

                          cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                          cell_one.setBorder(0);
                          table.addCell(cell_one);
                      }

                        if (null != partcdto.getEducationservies() && !"".equals(partcdto.getEducationservies())) {

                          cell_one = new PdfPCell(new Paragraph(PDFLables.EDUCATION_SERVICES_SELECT, Font_Normal));
                          cell_one.setBorder(0);
                          table.addCell(cell_one);

                          cell_one = new PdfPCell(new Paragraph(partcdto.getEducationservies(), Font_Bold));
                          cell_one.setBorder(0);
                          table.addCell(cell_one);
                      }

                        if (null != partcdto.getVocationaltraining() && !"".equals(partcdto.getVocationaltraining())) {

                          cell_one = new PdfPCell(new Paragraph(PDFLables.VOCATIONAL_TRAINING_SELECT, Font_Normal));
                          cell_one.setBorder(0);
                          table.addCell(cell_one);

                          cell_one = new PdfPCell(new Paragraph(partcdto.getVocationaltraining(), Font_Bold));
                          cell_one.setBorder(0);
                          table.addCell(cell_one);
                      }

                    String[] counseling_List = {partcdto.getIndividual(),
                                                 partcdto.getFamily()};
                    boolean counseling_Flag = pDFCommonTemplate.getDisplayHeaderFlag(counseling_List, "");
                    if (counseling_Flag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.COUNSELING_GUIDANCE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        table.addCell(cell_one);

                        String[][] counseling_Lables = {{PDFLables.INDIVIDUAL, partcdto.getIndividual()}, {PDFLables.FAMILY, partcdto.getFamily()}
                                                         };

                        table = pDFCommonTemplate.creatingNeedsCells(table, Font_Bold, Font_Normal, cell_one,
                                PDFLables.YES, counseling_Lables);

                    }


                     if (null != partcdto.getAnyotherservices() && !"".equals(partcdto.getAnyotherservices())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.ANY_OTHER_GENERAL_NEEDS, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(partcdto.getAnyotherservices(), Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }

                  }
                  if (partcdto.isRailwaycertificate()) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.RAILWAY_CONCESSION, Font_Normal));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
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
