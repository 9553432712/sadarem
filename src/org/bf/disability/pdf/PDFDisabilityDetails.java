/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.pdf;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.bf.disability.Constants.PDFLables;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.dto.PartADTO;

/**
 *
 * @author t_bapinaidu
 */
public class PDFDisabilityDetails {

    public PdfPTable getTable(HttpServletRequest request) throws DocumentException, IOException {
        float[] widths = {0.50f, 0.50f};
        PdfPTable table = new PdfPTable(widths);
        Font Font_Bold = null;
        Font Font_Normal = null;
        Font Font_Bold_Color = null;
        Font Font_Section_heading = null;
        Image disabilityLogo;
        float[] widths1 = {0.25f, 0.25f, 0.25f, 0.25f};
        float[] doctors_widths = {0.14f, 0.20f, 0.16f, 0.10f, 0.16f, 0.14f};
        try {
            table.setSplitRows(true);
            CommonDetails commonDetails = new CommonDetails();
            PartADTO partADTO = (PartADTO) request.getAttribute("sectionObject");
            String pwdPhotoDrt = (String)request.getAttribute("pwdPhotoDrt");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable table1 = new PdfPTable(doctors_widths);
            float[] widths_venu_name = {0.40f, 0.60f};
            PdfPTable venu_table = new PdfPTable(widths_venu_name);

            if(partADTO != null){
                if (null != pwdPhotoDrt && !"".equals(pwdPhotoDrt)) {
                    cell_one = new PdfPCell(new Paragraph("PWD Photo :", Font_Bold));
                    cell_one.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell_one.setBorder(0);
                    venu_table.addCell(cell_one);

                    File file = new File(pwdPhotoDrt);
                    boolean isFile = file.isFile();
                    if (isFile) {
                        disabilityLogo = Image.getInstance(pwdPhotoDrt);
                        disabilityLogo.scaleToFit(80, disabilityLogo.getHeight());
                        venu_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                        venu_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
                        venu_table.getDefaultCell().setBorderWidth(0);
                        venu_table.addCell(new Phrase(new Chunk(disabilityLogo, 0, 0)));
                    } else {
                        cell_one = new PdfPCell(new Paragraph("Not Uploaded", Font_Bold));
                        cell_one.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell_one.setBorder(0);
                        venu_table.addCell(cell_one);
                    }
                }
            

            if (null != partADTO.getCamp_venue() && !"".equals(partADTO.getCamp_venue())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.VENU_NAME, Font_Normal));
                cell_one.setBorder(0);
                venu_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getCamp_venue(), Font_Bold));
                cell_one.setBorder(0);
                venu_table.addCell(cell_one);
            }
            if (null != partADTO.getHospitalname() && !"".equals(partADTO.getHospitalname())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.HOSPITAL_NAME, Font_Normal));
                cell_one.setBorder(0);
                venu_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getHospitalname(), Font_Bold));
                cell_one.setBorder(0);
                venu_table.addCell(cell_one);
            }
            if (null != partADTO.getHospitaladdress() && !"".equals(partADTO.getHospitaladdress())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.HOSPITAL_ADDRESS, Font_Normal));
                cell_one.setBorder(0);
                venu_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getHospitaladdress(), Font_Bold));
                cell_one.setBorder(0);
                venu_table.addCell(cell_one);
            }

            if (0 != partADTO.getDisabilityId()) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.TYPE_OF_DISABILITY, Font_Normal));
                cell_one.setBorder(0);
                venu_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(commonDetails.getDisabilityName(partADTO.getDisabilityId()), Font_Bold));
                cell_one.setBorder(0);
                venu_table.addCell(cell_one);
            }

            cell_one = new PdfPCell(venu_table);
            cell_one.setBorder(0);
            cell_one.setColspan(2);
            table.addCell(cell_one);


            if (null != partADTO.getDoctor1name() && !"".equals(partADTO.getDoctor1name())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.FIRST_DOCTOR_NAME, Font_Normal));
                cell_one.setBorder(0);
                table1.addCell(cell_one);
               // String name =partADTO.getDoctor1name().substring(0, 3);
              //  if (partADTO.getDoctor1name().startsWith("Dr")) {
              //      cell_one = new PdfPCell(new Paragraph(partADTO.getDoctor1name().substring(3, partADTO.getDoctor1name().length()), Font_Bold));
               //     cell_one.setBorder(0);
               //     table1.addCell(cell_one);
              //  } else {     
                    cell_one = new PdfPCell(new Paragraph(partADTO.getDoctor1name(), Font_Bold));
                    cell_one.setBorder(0);
                    table1.addCell(cell_one);
            ///    }
            }

            if (null != partADTO.getDoctor1regnumber() && !"".equals(partADTO.getDoctor1regnumber())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.FIRST_DOCTOR_REGNO, Font_Normal));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getDoctor1regnumber(), Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);
            }

            if (null != partADTO.getDoctor1designation() && !"".equals(partADTO.getDoctor1designation())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.FIRST_DOCTOR_DESIGNATION, Font_Normal));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getDoctor1designation(), Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);
            }

            if (null != partADTO.getDoctor2name() && !"".equals(partADTO.getDoctor2name())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.SECOND_DOCTOR_NAME, Font_Normal));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph("Dr. "+partADTO.getDoctor2name(), Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);
            }

            if (null != partADTO.getDoctor2regnumber() && !"".equals(partADTO.getDoctor2regnumber())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.FIRST_DOCTOR_REGNO, Font_Normal));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getDoctor2regnumber(), Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);
            }

            if (null != partADTO.getDoctor2designation() && !"".equals(partADTO.getDoctor2designation())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.FIRST_DOCTOR_DESIGNATION, Font_Normal));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getDoctor2designation(), Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);
            }

            if (null != partADTO.getDoctor3name() && !"".equals(partADTO.getDoctor3name())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.THIRD_DOCTOR_NAME, Font_Normal));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph("Dr. "+partADTO.getDoctor3name(), Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);
            }

            if (null != partADTO.getDoctor3regnumber() && !"".equals(partADTO.getDoctor3regnumber())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.FIRST_DOCTOR_REGNO, Font_Normal));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getDoctor3regnumber(), Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);
            }

            if (null != partADTO.getDoctor3designation() && !"".equals(partADTO.getDoctor3designation())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.FIRST_DOCTOR_DESIGNATION, Font_Normal));
                cell_one.setBorder(0);
                table1.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getDoctor3designation(), Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);
            }

            cell_one = new PdfPCell(table1);
            cell_one.setBorder(0);
            cell_one.setColspan(2);
            table.addCell(cell_one);

            float[] widths_Can = {0.40f, 0.60f};
            PdfPTable can_table = new PdfPTable(widths_Can);

            if (null != partADTO.getDisabilitySubIds() && !"".equals(partADTO.getDisabilitySubIds())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.SUB_TYPES, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(commonDetails.getSubTypeNames(partADTO.getDisabilitySubIds()), Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }
            if (null != partADTO.getDisabilitySubSubIds() && !"".equals(partADTO.getDisabilitySubSubIds())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.PART_INVOLVED, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(commonDetails.getSubSubTypeNames(partADTO.getDisabilitySubSubIds()), Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }
            if (null != partADTO.getF_can() && !"".equals(partADTO.getF_can())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.F_CAN, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
                if ("1".equals(partADTO.getF_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                }
                if ("0".equals(partADTO.getF_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NO, Font_Bold));
                }
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            if (null != partADTO.getPp_can() && !"".equals(partADTO.getPp_can())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.PP_CAN, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                if ("1".equals(partADTO.getPp_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                }
                if ("0".equals(partADTO.getPp_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NO, Font_Bold));
                }
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            if (null != partADTO.getL_can() && !"".equals(partADTO.getL_can())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.L_CAN, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                if ("1".equals(partADTO.getL_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                }
                if ("0".equals(partADTO.getL_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NO, Font_Bold));
                }
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            if (null != partADTO.getKc_can() && !"".equals(partADTO.getKc_can())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.KC_CAN, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                if ("1".equals(partADTO.getKc_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                }
                if ("0".equals(partADTO.getKc_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NO, Font_Bold));
                }
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            if (null != partADTO.getB_can() && !"".equals(partADTO.getB_can())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.B_CAN, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                if ("1".equals(partADTO.getB_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                }
                if ("0".equals(partADTO.getB_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NO, Font_Bold));
                }
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            if (null != partADTO.getS_can() && !"".equals(partADTO.getS_can())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.S_CAN, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                if ("1".equals(partADTO.getS_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                }
                if ("0".equals(partADTO.getS_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NO, Font_Bold));
                }
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            if (null != partADTO.getSt_can() && !"".equals(partADTO.getSt_can())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.ST_CAN, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                if ("1".equals(partADTO.getSt_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                }
                if ("0".equals(partADTO.getSt_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NO, Font_Bold));
                }
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            if (null != partADTO.getW_can() && !"".equals(partADTO.getW_can())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.W_CAN, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                if ("1".equals(partADTO.getW_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                }
                if ("0".equals(partADTO.getW_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NO, Font_Bold));
                }
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            if (null != partADTO.getRw_can() && !"".equals(partADTO.getRw_can())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.RW_CAN, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                if ("1".equals(partADTO.getRw_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                }
                if ("0".equals(partADTO.getRw_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NO, Font_Bold));
                }
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            if (null != partADTO.getSe_can() && !"".equals(partADTO.getSe_can())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.SE_CAN, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                if ("1".equals(partADTO.getSe_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                }
                if ("0".equals(partADTO.getSe_can())) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.NO, Font_Bold));
                }
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            cell_one = new PdfPCell(can_table);
            cell_one.setBorder(0);
            cell_one.setColspan(2);
            table.addCell(cell_one);

            PdfPTable Disability_table = new PdfPTable(widths_venu_name);

            if (null != partADTO.getDiagnosisofdisability() && !"".equals(partADTO.getDiagnosisofdisability())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.DISABILITY_IMPAIRMENT_DUETO, Font_Normal));
                cell_one.setBorder(0);
                Disability_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getDiagnosisofdisability(), Font_Bold));
                cell_one.setBorder(0);
                Disability_table.addCell(cell_one);
            }

            if (null != partADTO.getConditiondisabilityIds() && !"".equals(partADTO.getConditiondisabilityIds())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.CONDITION_OF_DISABILITY, Font_Normal));
                cell_one.setBorder(0);
                Disability_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(commonDetails.getConditionOfDisabilityName(Integer.parseInt(partADTO.getConditiondisabilityIds())), Font_Bold));
                cell_one.setBorder(0);
                Disability_table.addCell(cell_one);
            }

            if (null != partADTO.getYearsfortemporary() && !"".equals(partADTO.getYearsfortemporary())
                    && !"0".equals(partADTO.getYearsfortemporary())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.PERIOD_OF_REASSESSMENT, Font_Normal));
                cell_one.setBorder(0);
                Disability_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getYearsfortemporary(), Font_Bold));
                cell_one.setBorder(0);
                Disability_table.addCell(cell_one);
            }

            cell_one = new PdfPCell(new Paragraph(PDFLables.CAUSE_OF_DISABILITY, Font_Bold));
            cell_one.setBorder(0);
            cell_one.setColspan(2);
            Disability_table.addCell(cell_one);

            cell_one = new PdfPCell(Disability_table);
            cell_one.setBorder(0);
            cell_one.setColspan(2);
            table.addCell(cell_one);

            

            if(partADTO.getDisabilityId() == 2)
            {
                float[] cod_visual = {0.40f, 0.60f};
                PdfPTable table2 = new PdfPTable(cod_visual);
                if (partADTO.isCongenitalbettereye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.CONGENITAL+"-"+PDFLables.BETTER_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                if (partADTO.isCongenitalworseeye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.CONGENITAL+"-"+PDFLables.WORSE_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                if (partADTO.isHereditarybettereye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.HEREDITARY+"-"+PDFLables.BETTER_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                if (partADTO.isHereditaryworseeye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.HEREDITARY+"-"+PDFLables.WORSE_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                if (partADTO.isBirthinjurybettereye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.BIRTH_INJURY+"-"+PDFLables.BETTER_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                if (partADTO.isBirthinjuryworseeye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.BIRTH_INJURY+"-"+PDFLables.WORSE_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                if (partADTO.isDiseaseInfectionbettereye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.DISEASE_AND_INFECTION+"-"+PDFLables.BETTER_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                if (partADTO.isDiseaseInfectionworseeye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.DISEASE_AND_INFECTION+"-"+PDFLables.WORSE_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                 if (partADTO.isMalnutritionbettereye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.MALNUTRITION+"-"+PDFLables.BETTER_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                if (partADTO.isMalnutritionworseeye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.MALNUTRITION+"-"+PDFLables.WORSE_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                if (partADTO.isAccidentbettereye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.ACCIDENT+"-"+PDFLables.BETTER_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                if (partADTO.isAccidentworseeye()) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.ACCIDENT+"-"+PDFLables.WORSE_EYE, Font_Normal));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);

                    cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                    cell_one.setBorder(0);
                    table2.addCell(cell_one);
                }
                cell_one = new PdfPCell(table2);
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                table.addCell(cell_one);
            }
            if(2 != partADTO.getDisabilityId()){
                float[] Cod_widths = {0.40f, 0.60f};
                PdfPTable Cod_table = new PdfPTable(Cod_widths);
                
                if (partADTO.isCongenital()) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.CONGENITAL, Font_Normal));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);
            }

            if (partADTO.isHereditary()) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.HEREDITARY, Font_Normal));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);
            }

            if (partADTO.isBirthinjury()) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.BIRTH_INJURY, Font_Normal));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);
            }

            if (partADTO.isBirthasphyxia()) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.BIRTH_ASPHYXIA, Font_Normal));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);
            }

            if (partADTO.isHighfever()) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.MENINGITIS, Font_Normal));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);
            }

            if (partADTO.isEpilepsy()) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.EPILEPSY, Font_Normal));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);
            }

            if (partADTO.isDiseaseInfection()) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.DISEASE_AND_INFECTION, Font_Normal));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);
            }

            if (partADTO.isMalnutrition()) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.MALNUTRITION, Font_Normal));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);
            }

            if (partADTO.isAccident()) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.ACCIDENT, Font_Normal));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                Cod_table.addCell(cell_one);
            }
                cell_one = new PdfPCell(Cod_table);
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                table.addCell(cell_one);

            }

            PdfPTable OtherCause_table = new PdfPTable(widths_venu_name);
            if (null != partADTO.getOtherconditionofdisability() && !"".equals(partADTO.getOtherconditionofdisability())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.OTHER_CAUSE_OF_DISABILITY, Font_Normal));
                cell_one.setBorder(0);
                OtherCause_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getOtherconditionofdisability(), Font_Bold));
                cell_one.setBorder(0);
                OtherCause_table.addCell(cell_one);
            }
            cell_one = new PdfPCell(OtherCause_table);
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
