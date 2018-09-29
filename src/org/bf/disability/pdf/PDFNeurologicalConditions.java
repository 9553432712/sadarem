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
import org.bf.disability.dto.EvaluationDTO;

/**
 *
 * @author 509864
 */
public class PDFNeurologicalConditions {

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
            EvaluationDTO evaluationdto = (EvaluationDTO) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));



            if (evaluationdto.getTotalpercent() > 0) {
                float[] neurological_widths = {0.25f, 0.25f, 0.25f, 0.25f};
                PdfPCell cell_one = new PdfPCell(new Paragraph(""));
                PdfPTable neurological_table = new PdfPTable(neurological_widths);

                if (null != evaluationdto.getDominantupperextremity() && !"".equals(evaluationdto.getDominantupperextremity())
                        && !"0".equals(evaluationdto.getDominantupperextremity())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.INVOLVEMENT_OF_DOMINANT, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                }

                String[] lossOfSensationList = {evaluationdto.getLossofsensationupper(), evaluationdto.getLossofsensationlower()};
                boolean lossOfSensationFlag = pDFCommonTemplate.getDisplayHeaderFlag(lossOfSensationList, "0");
                if (lossOfSensationFlag) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.LOSS_OF_SENSATION, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(4);
                    neurological_table.addCell(cell_one);

                    if (null != evaluationdto.getLossofsensationupper() && !"".equals(evaluationdto.getLossofsensationupper())
                            && !"0".equals(evaluationdto.getLossofsensationupper())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.UPPER_EXTREMITY_NEUROLOGICAL, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if (null != evaluationdto.getLossofsensationlower() && !"".equals(evaluationdto.getLossofsensationlower())
                            && !"0".equals(evaluationdto.getLossofsensationlower())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.LOWER_EXTREMITY_NEUROLOGICAL, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }



                }

                if (null != evaluationdto.getNeurologicalstatus() && !"".equals(evaluationdto.getNeurologicalstatus())
                        && !"0".equals(evaluationdto.getNeurologicalstatus())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.ALTERED_SENSORIUM, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                }

                if (null != evaluationdto.getIntellectualimpairment() && !"".equals(evaluationdto.getIntellectualimpairment())
                        && !"0".equals(evaluationdto.getIntellectualimpairment())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.INTELLECTUAL_IMPAIRMENT, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(4);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.DEGREE_OF_MR, Font_Normal));
                    cell_one.setBorder(0);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.IQ_RANGE, Font_Normal));
                    cell_one.setBorder(0);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PLEASE_TICK_APPROPRITE_BOX, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    if ("25".equals(evaluationdto.getIntellectualimpairment())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.BOARDERLINE, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_70_79, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("50".equals(evaluationdto.getIntellectualimpairment())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MILD, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_50_69, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("75".equals(evaluationdto.getIntellectualimpairment())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MODERATE, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_35_49, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("90".equals(evaluationdto.getIntellectualimpairment())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.SEVERE, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_20_34, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("100".equals(evaluationdto.getIntellectualimpairment())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.PROFOUND, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_LESSTHAN_20, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }



                }

                if (null != evaluationdto.getSpeechdefect() && !"".equals(evaluationdto.getSpeechdefect())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.SPEECH_DEFECT, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PLEASE_TICK_APPROPRITE_BOX, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    if ("0".equals(evaluationdto.getSpeechdefect())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MILD_DYSARTHRIA, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("25".equals(evaluationdto.getSpeechdefect())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MODERATE_DYSARTHRIA, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("50".equals(evaluationdto.getSpeechdefect())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.SEVERE_DYSARTHRIA, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                }


                String[] nerveDisabilityList = {evaluationdto.getE6a(), evaluationdto.getE6b(),
                    evaluationdto.getE6c(), evaluationdto.getE6d(),
                    evaluationdto.getE6e(), evaluationdto.getE6f(),
                    evaluationdto.getE6g(), evaluationdto.getE6h()};
                boolean nerveDisabilityFlag = pDFCommonTemplate.getDisplayHeaderFlag(nerveDisabilityList, "0");

                if (nerveDisabilityFlag) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.MOTOR_CRANIAL_NERVE_DISABILITY, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PLEASE_TICK_APPROPRITE_BOX, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    if ("20".equals(evaluationdto.getE6a())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.OCULOMOTOR_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }

                    if ("20".equals(evaluationdto.getE6b())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.TROCHLEAR_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }

                    if ("20".equals(evaluationdto.getE6c())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.ABDUCENCE_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }

                    if ("20".equals(evaluationdto.getE6d())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.FACIAL_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }

                    if ("20".equals(evaluationdto.getE6e())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.ACCESSORY_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }

                    if ("20".equals(evaluationdto.getE6f())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.HYPOGLOSSAL_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }

                    if ("20".equals(evaluationdto.getE6g())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.TRIGEMINAL_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }

                    if ("20".equals(evaluationdto.getE6h())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.VAGUS_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }



                }

                String[] sensoryDisabilityList = {evaluationdto.getSca(), evaluationdto.getScb(),
                    evaluationdto.getScc(), evaluationdto.getScd()};
                boolean sensoryDisabilityFlag = pDFCommonTemplate.getDisplayHeaderFlag(sensoryDisabilityList, "0");

                if (sensoryDisabilityFlag) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.SENSORY_CRANIAL_NERVE_DISABILITY, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PLEASE_TICK_APPROPRITE_BOX, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    if ("10".equals(evaluationdto.getSca())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.OLFACTORY_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }

                    if ("10".equals(evaluationdto.getScb())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.OPTIC_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }

                    if ("10".equals(evaluationdto.getScc())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.VESTIBULOCOCHLEAR_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }

                    if ("10".equals(evaluationdto.getScd())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.GLOSSOPHARYNGEAL_NERVE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);
                    }



                }



                if (null != evaluationdto.getMotorsystem() && !"".equals(evaluationdto.getMotorsystem())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.MOTOR_SYSTEM_DISABILITY, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PLEASE_TICK_APPROPRITE_BOX, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    if ("25".equals(evaluationdto.getMotorsystem())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.A_MILD, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("50".equals(evaluationdto.getMotorsystem())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.B_MODERATE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("75".equals(evaluationdto.getMotorsystem())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.C_SEVERE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                }

                String[] sensorSystemList = {evaluationdto.getSensorysystem(), evaluationdto.getSensorysystem1(),
                    evaluationdto.getSensorysystem2()};
                boolean sensorSystemFlag = pDFCommonTemplate.getDisplayHeaderFlag(sensorSystemList, "0");

                if (sensorSystemFlag) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.SENSORY_SYSTEM_DISABILITY, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PLEASE_TICK_APPROPRITE_BOX, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.EXTENT_OF_SENSORY_DEFICIT, Font_Normal));
                    cell_one.setBorder(0);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.MILD, Font_Normal));
                    cell_one.setBorder(0);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.MODERATE, Font_Normal));
                    cell_one.setBorder(0);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.SEVERE, Font_Normal));
                    cell_one.setBorder(0);
                    neurological_table.addCell(cell_one);

                    if (!"0".equals(evaluationdto.getSensorysystem())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.ANESTHESIA, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        if ("2".equals(evaluationdto.getSensorysystem())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            cell_one.setColspan(2);
                            neurological_table.addCell(cell_one);


                        }
                        if ("5".equals(evaluationdto.getSensorysystem())) {
                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);


                        }

                        if ("10".equals(evaluationdto.getSensorysystem())) {
                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            cell_one.setColspan(2);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                        }
                    }

                    if (!"0".equals(evaluationdto.getSensorysystem1())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.HYPOESTHESIA, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        if ("2".equals(evaluationdto.getSensorysystem1())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            cell_one.setColspan(2);
                            neurological_table.addCell(cell_one);


                        }
                        if ("5".equals(evaluationdto.getSensorysystem1())) {
                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);


                        }

                        if ("10".equals(evaluationdto.getSensorysystem1())) {
                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            cell_one.setColspan(2);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                        }
                    }
                    if (!"0".equals(evaluationdto.getSensorysystem2())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.ANESTHESIA, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);

                        if ("2".equals(evaluationdto.getSensorysystem2())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            cell_one.setColspan(2);
                            neurological_table.addCell(cell_one);


                        }
                        if ("5".equals(evaluationdto.getSensorysystem2())) {
                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);


                        }

                        if ("10".equals(evaluationdto.getSensorysystem2())) {
                            cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                            cell_one.setBorder(0);
                            cell_one.setColspan(2);
                            neurological_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                            cell_one.setBorder(0);
                            neurological_table.addCell(cell_one);

                        }
                    }


                }

                String[] sensorHandList = {evaluationdto.getSensorysystemh(), evaluationdto.getSensorysystemf(),
                    evaluationdto.getSensorysystemt()};
                boolean sensorHandFlag = pDFCommonTemplate.getDisplayHeaderFlag(sensorHandList, "0");
                if (sensorHandFlag) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_6_8_1, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(4);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.EXTENT_OF_SENSORY_DEFICIT, Font_Normal));
                    cell_one.setBorder(0);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.HANDS, Font_Normal));
                    cell_one.setBorder(0);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.FEET, Font_Normal));
                    cell_one.setBorder(0);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.TRUNK, Font_Normal));
                    cell_one.setBorder(0);
                    neurological_table.addCell(cell_one);
                    
                    cell_one = new PdfPCell(new Paragraph(PDFLables.HANDS_FEET_TRUNK, Font_Normal));
                    cell_one.setBorder(0);
                    neurological_table.addCell(cell_one);



                    if ("4".equals(evaluationdto.getSensorysystemh())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);
                    } else {
                        cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);
                    }

                    if ("4".equals(evaluationdto.getSensorysystemf())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);
                    } else {
                        cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);
                    }

                    if ("4".equals(evaluationdto.getSensorysystemt())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);
                    } else {
                        cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                        cell_one.setBorder(0);
                        neurological_table.addCell(cell_one);
                    }
                }

                if (null != evaluationdto.getBladderinvolvement() && !"".equals(evaluationdto.getBladderinvolvement())
                        && !"0".equals(evaluationdto.getBladderinvolvement())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.BLADDER_DISABILITY_NEUROLOGICAL_INVOLVEMENT, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PLEASE_TICK_APPROPRITE_BOX, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    if ("25".equals(evaluationdto.getBladderinvolvement())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MILD_HESITANCY_FREQUENCY, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("50".equals(evaluationdto.getBladderinvolvement())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MODERATE_PRECIPITANCY, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("75".equals(evaluationdto.getBladderinvolvement())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.SEVERE_OCCASIONAL_RECURRENT, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("100".equals(evaluationdto.getBladderinvolvement())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.VERY_SEVERE_RETENSION_INCONTINENCE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }



                }

                if (null != evaluationdto.getInjury() && !"".equals(evaluationdto.getInjury())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.POST_HEAD_FITS_EPILEPTIC_CONVULSIONS, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(4);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.FREQUENCY_SEVERITY_CONVULSIONS, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PLEASE_TICK_APPROPRITE_BOX, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    if ("0".equals(evaluationdto.getInjury())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MILD_OCCURRENCE_ONE_CONVULSION, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("25".equals(evaluationdto.getInjury())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MODERATE_CONVULSION_MONTH_ADEQUATE_MEDICATION, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("50".equals(evaluationdto.getInjury())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.SEVERE_CONVULSION_MONTH_ADEQUATE_MEDICATION, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("75".equals(evaluationdto.getInjury())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.VERY_SEVERE_10_FITS_MONTH_ADEQUATE_MEDICATION, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }



                }

                if (null != evaluationdto.getAtaxia() && !"".equals(evaluationdto.getAtaxia())
                        && !"0".equals(evaluationdto.getAtaxia())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.ATAXIA, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(4);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.SEVERITY_ATAXIA, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PLEASE_TICK_APPROPRITE_BOX, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    neurological_table.addCell(cell_one);

                    if ("25".equals(evaluationdto.getAtaxia())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MILD_DETECTED_EXAMINATION, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("50".equals(evaluationdto.getAtaxia())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MODERATE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("75".equals(evaluationdto.getAtaxia())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.SEVERE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }

                    if ("100".equals(evaluationdto.getAtaxia())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.VERY_SEVERE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        neurological_table.addCell(cell_one);

                    }



                }



                cell_one = new PdfPCell(neurological_table);
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
