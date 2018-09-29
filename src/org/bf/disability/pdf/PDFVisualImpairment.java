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
import org.bf.disability.dto.CardioPulmonaryDTO;

/**
 *
 * @author t_bapinaidu
 */
public class PDFVisualImpairment {

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
            CardioPulmonaryDTO cardioPulmonarydto = (CardioPulmonaryDTO) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            float[] visual_widths = {0.25f, 0.25f, 0.25f, 0.15f, 0.10f};
            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable table1 = new PdfPTable(visual_widths);

            if (null != cardioPulmonarydto.getVisualimpairment() && !"".equals(cardioPulmonarydto.getVisualimpairment())) {

                cell_one = new PdfPCell(new Paragraph(PDFLables.CATEGORY, Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.BETTER_EYE, Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.WORSE_EYE, Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.PERCENTAGE_IMPAIRMENT, Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                if ("20.0".equals(cardioPulmonarydto.getVisualimpairment())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.CATEGORY_0, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.BETTER_EYE_6_9_6_18, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.WORSE_EYE_6_24_6_36, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PERCENTAGE_20, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                } else if ("40.0".equals(cardioPulmonarydto.getVisualimpairment())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.CATEGORY_1, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.BETTER_EYE_6_18_6_36, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.WORSE_EYE_6_60_NILL, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PERCENTAGE_40, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                } else if ("75.0".equals(cardioPulmonarydto.getVisualimpairment())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.CATEGORY_2, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.BETTER_EYE_6_40_6_60, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.WORSE_EYE_3_60_NILL, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PERCENTAGE_75, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                } else if ("101.0".equals(cardioPulmonarydto.getVisualimpairment())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.CATEGORY_3, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.BETTER_EYE_3_60_1_60, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.WORSE_EYE_FC_NILL, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PERCENTAGE_100, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                } else if ("100.0".equals(cardioPulmonarydto.getVisualimpairment())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.CATEGORY_4, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.BETTER_EYE_FC, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.WORSE_EYE_FC_NILL, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PERCENTAGE_100, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                } else if ("30.0".equals(cardioPulmonarydto.getVisualimpairment())) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.ONE_EYED_PERSONS, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.BETTER_EYE_6_6, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.BETTER_EYE_FC, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.PERCENTAGE_30, Font_Normal));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);

                }
                cell_one = new PdfPCell(table1);
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                table.addCell(cell_one);

                boolean needsFlag = getNeedsFlag(cardioPulmonarydto);
                if (needsFlag) {
                    float[] needs_widths = {0.50f, 0.50f};
                    PdfPTable needs_table = new PdfPTable(needs_widths);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.NEED_ASSESSMENT, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    needs_table.addCell(cell_one);

                    if (null != cardioPulmonarydto.getSurgery() && !"".equals(cardioPulmonarydto.getSurgery())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.VISUAL_SURGERY, Font_Normal));
                        cell_one.setBorder(0);
                        needs_table.addCell(cell_one);

                        cell_one = new PdfPCell(new Paragraph(cardioPulmonarydto.getSurgery(), Font_Bold));
                        cell_one.setBorder(0);
                        needs_table.addCell(cell_one);

                    }

                    boolean assistiveNeedsFlag = getAssistiveDevicesFlag(cardioPulmonarydto);
                    if (assistiveNeedsFlag) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.VISUAL_ASSISTIVE_AUGMENTATIVE, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(2);
                        needs_table.addCell(cell_one);

                        if (null != cardioPulmonarydto.getWhitecane() && !"".equals(cardioPulmonarydto.getWhitecane())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.VISUAL_WHITE_CANE, Font_Normal));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);
                        }

                        if (null != cardioPulmonarydto.getBrailleequipments() && !"".equals(cardioPulmonarydto.getBrailleequipments())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.VISUAL_BRAILLEEQUIPMENTS, Font_Normal));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);
                        }

                        if (null != cardioPulmonarydto.getArithmeticframes() && !"".equals(cardioPulmonarydto.getArithmeticframes())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.VISUAL_ARITHMETIC_FRAMES, Font_Normal));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);
                        }

                        if (null != cardioPulmonarydto.getLowvisionaids() && !"".equals(cardioPulmonarydto.getLowvisionaids())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.VISUAL_LOW_VISIONAIDS, Font_Normal));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);
                        }

                        if (null != cardioPulmonarydto.getSpeechsynthesizer() && !"".equals(cardioPulmonarydto.getSpeechsynthesizer())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.VISUAL_SYNTHESIZER, Font_Normal));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);
                        }

                        if (null != cardioPulmonarydto.getBrailleshorthandmachines() && !"".equals(cardioPulmonarydto.getBrailleshorthandmachines())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.VISUAL_BRAILLE_SHORTHAND, Font_Normal));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);
                        }

                        if (null != cardioPulmonarydto.getTalkingwatchcalculator() && !"".equals(cardioPulmonarydto.getTalkingwatchcalculator())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.VISUAL_TALKING_WATCH, Font_Normal));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);
                        }

                        if (null != cardioPulmonarydto.getAnyadlequipmentes() && !"".equals(cardioPulmonarydto.getAnyadlequipmentes())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.VISUAL_ANYADLEQUIPMENT, Font_Normal));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);
                        }

                        if (null != cardioPulmonarydto.getAnyotherspecify() && !"".equals(cardioPulmonarydto.getAnyotherspecify())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.VISUAL_ANYOTHER_VISUALIMPAIRMENT, Font_Normal));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(cardioPulmonarydto.getAnyotherspecify(), Font_Bold));
                            cell_one.setBorder(0);
                            needs_table.addCell(cell_one);
                        }


                    }

                    cell_one = new PdfPCell(needs_table);
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
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

    public boolean getNeedsFlag(CardioPulmonaryDTO cardioPulmonarydto) throws IOException {
        try {
            if (null != cardioPulmonarydto.getSurgery()
                    && !"".equals(cardioPulmonarydto.getSurgery())) {
                return true;
            } else if (null != cardioPulmonarydto.getWhitecane()
                    && !"".equals(cardioPulmonarydto.getWhitecane())) {
                return true;
            } else if (null != cardioPulmonarydto.getBrailleequipments()
                    && !"".equals(cardioPulmonarydto.getBrailleequipments())) {
                return true;
            } else if (null != cardioPulmonarydto.getArithmeticframes()
                    && !"".equals(cardioPulmonarydto.getArithmeticframes())) {
                return true;
            } else if (null != cardioPulmonarydto.getLowvisionaids()
                    && !"".equals(cardioPulmonarydto.getLowvisionaids())) {
                return true;
            } else if (null != cardioPulmonarydto.getSpeechsynthesizer()
                    && !"".equals(cardioPulmonarydto.getSpeechsynthesizer())) {
                return true;
            } else if (null != cardioPulmonarydto.getBrailleshorthandmachines()
                    && !"".equals(cardioPulmonarydto.getBrailleshorthandmachines())) {
                return true;
            } else if (null != cardioPulmonarydto.getTalkingwatchcalculator()
                    && !"".equals(cardioPulmonarydto.getTalkingwatchcalculator())) {
                return true;
            } else if (null != cardioPulmonarydto.getAnyadlequipmentes()
                    && !"".equals(cardioPulmonarydto.getAnyadlequipmentes())) {
                return true;
            } else if (null != cardioPulmonarydto.getAnyotherspecify()
                    && !"".equals(cardioPulmonarydto.getAnyotherspecify())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getAssistiveDevicesFlag(CardioPulmonaryDTO cardioPulmonarydto) throws IOException {

        try {
            if (null != cardioPulmonarydto.getWhitecane()
                    && !"".equals(cardioPulmonarydto.getWhitecane())) {
                return true;
            } else if (null != cardioPulmonarydto.getBrailleequipments()
                    && !"".equals(cardioPulmonarydto.getBrailleequipments())) {
                return true;
            } else if (null != cardioPulmonarydto.getArithmeticframes()
                    && !"".equals(cardioPulmonarydto.getArithmeticframes())) {
                return true;
            } else if (null != cardioPulmonarydto.getLowvisionaids()
                    && !"".equals(cardioPulmonarydto.getLowvisionaids())) {
                return true;
            } else if (null != cardioPulmonarydto.getSpeechsynthesizer()
                    && !"".equals(cardioPulmonarydto.getSpeechsynthesizer())) {
                return true;
            } else if (null != cardioPulmonarydto.getBrailleshorthandmachines()
                    && !"".equals(cardioPulmonarydto.getBrailleshorthandmachines())) {
                return true;
            } else if (null != cardioPulmonarydto.getTalkingwatchcalculator()
                    && !"".equals(cardioPulmonarydto.getTalkingwatchcalculator())) {
                return true;
            } else if (null != cardioPulmonarydto.getAnyadlequipmentes()
                    && !"".equals(cardioPulmonarydto.getAnyadlequipmentes())) {
                return true;
            } else if (null != cardioPulmonarydto.getAnyotherspecify()
                    && !"".equals(cardioPulmonarydto.getAnyotherspecify())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
