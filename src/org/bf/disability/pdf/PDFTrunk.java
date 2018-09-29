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
import org.bf.disability.dto.TrunkDTO;

/**
 *
 * @author 509864
 */
public class PDFTrunk {

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
            TrunkDTO trunkdto = (TrunkDTO) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            float[] trunk_widths = {0.30f, 0.60f, 0.10f};
            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable trunk_table = new PdfPTable(trunk_widths);


            if (trunkdto.getTraumatic() > 0) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.TRAUMATIC_LESIONS, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                trunk_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.CERVICAL_SPINE_INJURIES, Font_Normal));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                trunk_table.addCell(cell_one);


                if (null != trunkdto.getCompression() && !"".equals(trunkdto.getCompression())
                        && !"0".equals(trunkdto.getCompression())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.COMPRESSION, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(2);
                    trunk_table.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    trunk_table.addCell(cell_one);
                }
                String[] componentslist = {trunkdto.getPosterior_fusion(), trunkdto.getPosterior_persistent()};
                int i = pDFCommonTemplate.getDisplayHeaderRowCount(componentslist, "0");

                if (i > 0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.POSTERIOR, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(i);
                    trunk_table.addCell(cell_one);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.WITH_FUSION, trunkdto.getPosterior_fusion(), PDFLables.YES);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.PERSISTENT, trunkdto.getPosterior_persistent(), PDFLables.YES);


                }

                String[] severelist = {trunkdto.getSevere_fire(), trunkdto.getSevere_inadequate()};
                int j = pDFCommonTemplate.getDisplayHeaderRowCount(severelist, "0");

                if (j > 0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.SEVERE_DISLOCATION, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(j);
                    trunk_table.addCell(cell_one);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.FAIR_TO_GOOD, trunkdto.getSevere_fire(), PDFLables.YES);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.INADEQUATE_REDUCTION, trunkdto.getSevere_inadequate(), PDFLables.YES);

                }

                String[] cervicallist = {trunkdto.getCervical_disc(), trunkdto.getCervical_pain()};
                int k = pDFCommonTemplate.getDisplayHeaderRowCount(cervicallist, "0");

                if (k > 0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.CERVICAL_INTERVERTEBRAL, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(k);
                    trunk_table.addCell(cell_one);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.TREATED_CASES_DISC_DISEASES_PERSISTENT, trunkdto.getCervical_disc(), PDFLables.YES);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.TREATED_CASES_INSTABILITY_PAIN, trunkdto.getCervical_pain(), PDFLables.YES);

                }

                String[] thoraciclist = {trunkdto.getThoracic_less(), trunkdto.getThoracic_more(),
                    trunkdto.getThoracic_fusion(), trunkdto.getThoracic_radiologically()};
                int l = pDFCommonTemplate.getDisplayHeaderRowCount(thoraciclist, "0");

                if (l > 0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.THORACIC_THORACOLUMBAR, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(l);
                    trunk_table.addCell(cell_one);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.COMPRESSION_OF_LESS, trunkdto.getThoracic_less(), PDFLables.YES);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.COMPRESSION_OF_MORE, trunkdto.getThoracic_more(), PDFLables.YES);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.SAME_AS_2, trunkdto.getThoracic_fusion(), PDFLables.YES);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.RADIOLOGICALLY, trunkdto.getThoracic_radiologically(), PDFLables.YES);

                }


                String[] fracturelist = {trunkdto.getFracture_less(), trunkdto.getFracture_more(),
                    trunkdto.getFracture_radiologically()};
                int m = pDFCommonTemplate.getDisplayHeaderRowCount(fracturelist, "0");

                if (m > 0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.LUMBAR_LUMBOSACRAL_SPINE, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(m);
                    trunk_table.addCell(cell_one);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.COMPRESSION_OF_25_LESS, trunkdto.getFracture_less(), PDFLables.YES);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.COMPRESSION_OF_25_DISTRUPTION, trunkdto.getFracture_more(), PDFLables.YES);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.RADIOLOGICALLY_DEMONSTRABLE, trunkdto.getFracture_radiologically(), PDFLables.YES);


                }

                String[] disclist = {trunkdto.getDisc_persistent(), trunkdto.getDisc_pain(),
                    trunkdto.getDisc_diseases(), trunkdto.getDisc_stifness()};
                int n = pDFCommonTemplate.getDisplayHeaderRowCount(disclist, "0");

                if (n > 0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.LUMBAR_LUMBOSACRAL_DISC, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(n);
                    trunk_table.addCell(cell_one);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.TREATED_CASES_PERSISTENT_PAIN, trunkdto.getDisc_persistent(), PDFLables.YES);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.TREATED_CASES_INSTABILITY_PAIN, trunkdto.getDisc_pain(), PDFLables.YES);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.TREATED_CASES_DISC_DISEASES, trunkdto.getDisc_diseases(), PDFLables.YES);

                    trunk_table = pDFCommonTemplate.creatingPdfTwoCell(trunk_table, Font_Bold, Font_Normal, cell_one,
                            PDFLables.TREATED_CASES_DISC_DISEASES_PERSISTENT, trunkdto.getDisc_stifness(), PDFLables.YES);

                }
            }

            if (trunkdto.getNontraumatic() > 0) {

                cell_one = new PdfPCell(new Paragraph(PDFLables.NON_TRAUMANTIC_LESIONS, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                trunk_table.addCell(cell_one);

                if (trunkdto.getTotalscoliosis() > 0) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.SCOLIOSIS, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(3);
                    trunk_table.addCell(cell_one);

                    if (null != trunkdto.getScoliosis_measure() && !"".equals(trunkdto.getScoliosis_measure())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MESUREMENT_OF_SPINE_SCOLIOSIS, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(3);
                        trunk_table.addCell(cell_one);

                        if ("0".equals(trunkdto.getScoliosis_measure())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_0_20, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);

                        } else if ("10".equals(trunkdto.getScoliosis_measure())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_21_50, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("20".equals(trunkdto.getScoliosis_measure())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_51_100, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("30".equals(trunkdto.getScoliosis_measure())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_101_ABOVE, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        }


                    }

                    if (null != trunkdto.getScoliosis_torso() && !"".equals(trunkdto.getScoliosis_torso())
                            && !"0".equals(trunkdto.getScoliosis_torso())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.TORSO_IMBALANCE, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(3);
                        trunk_table.addCell(cell_one);

                        if ("4".equals(trunkdto.getScoliosis_torso())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_UPTO_15, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);

                        } else if ("8".equals(trunkdto.getScoliosis_torso())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_1_6_3, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("16".equals(trunkdto.getScoliosis_torso())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_3_5, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("32".equals(trunkdto.getScoliosis_torso())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_5_ABOVE, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        }


                    }



                }



                if (trunkdto.getTotalkyphosis() > 0) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.KYPHOSIS, Font_Bold));
                    cell_one.setBorder(0);
                    cell_one.setColspan(3);
                    trunk_table.addCell(cell_one);

                    if (null != trunkdto.getKyphosis_measure() && !"".equals(trunkdto.getKyphosis_measure())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.MESUREMENT_OF_SPINE_KYPHOSIS, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(3);
                        trunk_table.addCell(cell_one);

                        if ("0".equals(trunkdto.getKyphosis_measure())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_LESSTHAN_20, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);

                        } else if ("10".equals(trunkdto.getKyphosis_measure())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_21_40, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("20".equals(trunkdto.getKyphosis_measure())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_41_60, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("30".equals(trunkdto.getKyphosis_measure())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_60_ABOVE, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        }


                    }

                    if (null != trunkdto.getKyphosis_torso() && !"".equals(trunkdto.getKyphosis_torso())
                            && !"0".equals(trunkdto.getKyphosis_torso())) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.TORSO_IMBALANCE_KYPOSIS, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(3);
                        trunk_table.addCell(cell_one);

                        if ("4".equals(trunkdto.getKyphosis_torso())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_LESSTHAN_5, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);

                        } else if ("8".equals(trunkdto.getKyphosis_torso())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_5_10, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("16".equals(trunkdto.getKyphosis_torso())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_10_15, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("32".equals(trunkdto.getKyphosis_torso())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_15_ABOVE, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        }


                    }



                }

                if (null != trunkdto.getHead() && !"".equals(trunkdto.getHead())
                        && !"0".equals(trunkdto.getHead())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.HEAD_TILT, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(3);
                    trunk_table.addCell(cell_one);

                    if ("4".equals(trunkdto.getHead())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_UPTO_15, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(3);
                        trunk_table.addCell(cell_one);

                    } else if ("10".equals(trunkdto.getHead())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_MORETHAN_15, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(3);
                        trunk_table.addCell(cell_one);
                    }


                }


                if ((null != trunkdto.getCardio_chest() && !"".equals(trunkdto.getCardio_chest())) || (null != trunkdto.getCardio_counting() && !"".equals(trunkdto.getCardio_counting()))
                        || (null != trunkdto.getCardio_associatepain() && !"".equals(trunkdto.getCardio_associatepain())) || (null != trunkdto.getCardio_associatecosmetic() && !"".equals(trunkdto.getCardio_associatecosmetic()))
                        || (null != trunkdto.getCardio_associateleg() && !"".equals(trunkdto.getCardio_associateleg()))) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.CARDIOPULMANARY_TEST, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setColspan(3);
                    trunk_table.addCell(cell_one);

                    if (null != trunkdto.getCardio_chest() && !"".equals(trunkdto.getCardio_chest())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.CHEST_EXPANSION, Font_Bold));
                        cell_one.setBorder(0);
                        cell_one.setColspan(3);
                        trunk_table.addCell(cell_one);

                        if ("0".equals(trunkdto.getCardio_chest())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_4_5_CM, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);

                        } else if ("5".equals(trunkdto.getCardio_chest())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_3_CM, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("10".equals(trunkdto.getCardio_chest())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_2_CM, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("15".equals(trunkdto.getCardio_chest())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_1_CM, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("25".equals(trunkdto.getCardio_chest())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.NO_EXPANSION, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        }


                    }
                    if (null != trunkdto.getCardio_counting() && !"".equals(trunkdto.getCardio_counting())) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.COUNTING_IN_BREATH, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(3);
                        trunk_table.addCell(cell_one);

                        if ("0".equals(trunkdto.getCardio_counting())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.BRATHOUT_MORETHAN_40, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);

                        } else if ("5".equals(trunkdto.getCardio_counting())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_0_40, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("10".equals(trunkdto.getCardio_counting())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_0_30, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("15".equals(trunkdto.getCardio_counting())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_0_20, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("20".equals(trunkdto.getCardio_counting())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_0_10, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        } else if ("25".equals(trunkdto.getCardio_counting())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.RANGE_LESSTHAN_5, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);
                        }


                    }

                    if ((null != trunkdto.getCardio_associatepain() && !"".equals(trunkdto.getCardio_associatepain()))
                            || (null != trunkdto.getCardio_associatecosmetic() && !"".equals(trunkdto.getCardio_associatecosmetic()))
                            || (null != trunkdto.getCardio_associateleg() && !"".equals(trunkdto.getCardio_associateleg()))) {

                        cell_one = new PdfPCell(new Paragraph(PDFLables.ASSOCIATED_PROBLEM, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setColspan(3);
                        trunk_table.addCell(cell_one);

                        if (null != trunkdto.getCardio_associatepain() && !"".equals(trunkdto.getCardio_associatepain())
                                && !"0".equals(trunkdto.getCardio_associatepain())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.PAIN_TRUNK, Font_Bold));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);

                            if ("4".equals(trunkdto.getCardio_associatepain())) {
                                cell_one = new PdfPCell(new Paragraph(PDFLables.MILD_INTERFERING, Font_Bold));
                                cell_one.setBorder(0);
                                cell_one.setColspan(3);
                                trunk_table.addCell(cell_one);

                            } else if ("6".equals(trunkdto.getCardio_associatepain())) {
                                cell_one = new PdfPCell(new Paragraph(PDFLables.MODERATLY_RESTRICTING, Font_Bold));
                                cell_one.setBorder(0);
                                cell_one.setColspan(3);
                                trunk_table.addCell(cell_one);
                            } else if ("10".equals(trunkdto.getCardio_associatepain())) {
                                cell_one = new PdfPCell(new Paragraph(PDFLables.SEVERELY_RESTRICTING, Font_Bold));
                                cell_one.setBorder(0);
                                cell_one.setColspan(3);
                                trunk_table.addCell(cell_one);
                            }

                        }

                        if (null != trunkdto.getCardio_associatecosmetic() && !"".equals(trunkdto.getCardio_associatecosmetic())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.COSMETIC_APPEARANCE, Font_Normal));
                            cell_one.setBorder(0);
                            cell_one.setColspan(3);
                            trunk_table.addCell(cell_one);

                            if ("0".equals(trunkdto.getCardio_associatecosmetic())) {
                                cell_one = new PdfPCell(new Paragraph(PDFLables.NO_OBVIOUS_DISFIGURATION, Font_Bold));
                                cell_one.setBorder(0);
                                cell_one.setColspan(3);
                                trunk_table.addCell(cell_one);

                            } else if ("2".equals(trunkdto.getCardio_associatecosmetic())) {
                                cell_one = new PdfPCell(new Paragraph(PDFLables.MILD_DISFIGUREMENT, Font_Bold));
                                cell_one.setBorder(0);
                                cell_one.setColspan(3);
                                trunk_table.addCell(cell_one);
                            } else if ("4".equals(trunkdto.getCardio_associatecosmetic())) {
                                cell_one = new PdfPCell(new Paragraph(PDFLables.SEVERE_DISFIGUREMENT, Font_Bold));
                                cell_one.setBorder(0);
                                cell_one.setColspan(3);
                                trunk_table.addCell(cell_one);
                            }

                        }

                        if (null != trunkdto.getCardio_associateleg() && !"".equals(trunkdto.getCardio_associateleg())) {
                            cell_one = new PdfPCell(new Paragraph(PDFLables.LEG_LENGTH, Font_Normal));
                            cell_one.setBorder(0);
                            cell_one.setColspan(2);
                            trunk_table.addCell(cell_one);

                            cell_one = new PdfPCell(new Paragraph(trunkdto.getCardio_associateleg(), Font_Bold));
                            cell_one.setBorder(0);
                            trunk_table.addCell(cell_one);


                        }



                    }



                }





            }






            cell_one = new PdfPCell(trunk_table);
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
