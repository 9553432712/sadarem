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
 * @author 509864
 */
public class PDFRejectedDetails {

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
            String pwdPhotoDrt = (String) request.getAttribute("pwdPhotoDrt");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable table1 = new PdfPTable(doctors_widths);
            float[] widths_venu_name = {0.40f, 0.60f};
            PdfPTable venu_table = new PdfPTable(widths_venu_name);

            if (partADTO != null) {
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

                cell_one = new PdfPCell(new Paragraph(partADTO.getDoctor1name(), Font_Bold));
                cell_one.setBorder(0);
                table1.addCell(cell_one);
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

                cell_one = new PdfPCell(new Paragraph(partADTO.getDoctor2name(), Font_Bold));
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

                cell_one = new PdfPCell(new Paragraph(partADTO.getDoctor3name(), Font_Bold));
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

            if (null != partADTO.getOthertypeofdisability() && !"".equals(partADTO.getOthertypeofdisability())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.ANY_OTHER_DISABILITY, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getOthertypeofdisability(), Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }
            if (null != partADTO.getReferredto() && !"".equals(partADTO.getReferredto())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.REFERRED_TO, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getReferredto(), Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            if (null != partADTO.getSurgery() && !"".equals(partADTO.getSurgery())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.SURGERY, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getSurgery(), Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            if (null != partADTO.getPhysiotherapy() && !"".equals(partADTO.getPhysiotherapy())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.PHYSIOTHERAPY_REJ, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }


            if (null != partADTO.getLowvisionaid() && !"".equals(partADTO.getLowvisionaid())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.LOW_VISION_AID, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

             if (null != partADTO.getHearingaid() && !"".equals(partADTO.getHearingaid())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.HEARING_AID, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

             if (null != partADTO.getBehaviourmodification() && !"".equals(partADTO.getBehaviourmodification())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.BEHAVIOUR_MODIFICATION_REJ, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

             if (null != partADTO.getAdmissioninpsychiatrichospital() && !"".equals(partADTO.getAdmissioninpsychiatrichospital())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.ADMISSION_PSYCHIATRIC, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

             if (null != partADTO.getCouncellingandguidance() && !"".equals(partADTO.getCouncellingandguidance())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.COUNSELING_GUIDANCE_REJ, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

              if (null != partADTO.getAnyotherneed() && !"".equals(partADTO.getAnyotherneed())) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.ANY_OTHER_NEED, Font_Normal));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(partADTO.getAnyotherneed(), Font_Bold));
                cell_one.setBorder(0);
                can_table.addCell(cell_one);
            }

            cell_one = new PdfPCell(can_table);
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
