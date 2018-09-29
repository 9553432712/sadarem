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
import org.bf.disability.dto.TransverseDTO;

/**
 *
 * @author 509864
 */
public class PDFCongentialDeficiencies {

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
            TransverseDTO transversedto = (TransverseDTO) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            

            if(transversedto.getFinalres()>0){

                float[] congintial_widths = {0.60f, 0.10f, 0.10f};
                PdfPCell cell_one = new PdfPCell(new Paragraph(""));
                PdfPTable congintial_table = new PdfPTable(congintial_widths);

                cell_one = new PdfPCell(new Paragraph(PDFLables.TRANSVERE_DEFICIENCES, Font_Bold));
                cell_one.setBorder(0);
                congintial_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.RIGHT, Font_Bold));
                cell_one.setBorder(0);
                congintial_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.LEFT, Font_Bold));
                cell_one.setBorder(0);
                congintial_table.addCell(cell_one);


                 int[][] congintialValues = {{transversedto.getShoulderdisarticulationrightside(), transversedto.getShoulderdisarticulationleftside()},
                    {transversedto.getAboveelbowamputeerightside(), transversedto.getAboveelbowamputeeleftside()},
                    {transversedto.getElbowdisarticulationrightside(), transversedto.getElbowdisarticulationleftside()},
                    {transversedto.getHipdisarticulationrightside(), transversedto.getHipdisarticulationleftside()},
                    {transversedto.getBelowelbowamputeerightside(), transversedto.getBelowelbowamputeeleftside()},
                    {transversedto.getWristdisarticulationrightside(), transversedto.getWristdisarticulationleftside()},
                    {transversedto.getCarpalbonesrightside(), transversedto.getCarpalbonesleftside()},
                    {transversedto.getKneeamputeerightside(), transversedto.getKneeamputeeleftside()}};


                String[] congintialLables = {PDFLables.DEFICIENCY_ARM_COMPLETE, PDFLables.DEFICIENCY_PROXIMAL_UPPER,
                    PDFLables.DEFICIENCY_FOREARM_COMPLETE, PDFLables.DEFICIENCY_LOWER_FOREARM,
                    PDFLables.DEFICIENCY_CARPAL_COMPLETE, PDFLables.DEFICIENCY_METACARPAL_COMPLETE,
                    PDFLables.DEFICIENCY_THIGH_COMPLETE, PDFLables.DEFICIENCY__LOWER_THIGH};


                congintial_table = pDFCommonTemplate.creatingAmputationPdfThreeCells(congintial_table, Font_Bold, Font_Normal, cell_one,
                        PDFLables.YES, congintialValues, congintialLables);

                cell_one = new PdfPCell(congintial_table);
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
