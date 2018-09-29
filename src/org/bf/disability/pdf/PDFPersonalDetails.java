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
import org.bf.disability.dto.PartADTO;

/**
 *
 * @author t_bapinaidu
 */
public class PDFPersonalDetails{


   public PdfPTable getTable(HttpServletRequest request) throws DocumentException, IOException {

       float[] widths = {0.50f, 0.50f};
       PdfPTable table = new PdfPTable(widths);
       Font Font_Bold = null;
       Font Font_Normal = null;
       Font Font_Bold_Color = null;
       Font Font_Section_heading = null;

       float[] widths1 = {0.25f, 0.25f,0.25f, 0.25f};

       try {

           table.setSplitRows(true);
           CommonDetails commonDetails = new CommonDetails();
           PartADTO partADTO  = (PartADTO)request.getAttribute("sectionObject");
           Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
           Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
           Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
           Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

           PdfPCell cell_one = new PdfPCell(new Paragraph(""));
           PdfPTable table1 = new PdfPTable(widths1);
          if(null != partADTO.getFormno() && !"".equals(partADTO.getFormno())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.FORN_NO, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getFormno(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }
         if(null != partADTO.getFromdate() && !"".equals(partADTO.getFromdate())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.DATE_OF_ASSESSMENT, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getFromdate(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
              
           }

           PdfPCell cell_two = new PdfPCell(new Paragraph(PDFLables.INDIVIDUAL_DETAILS, Font_Bold));
           cell_two.setBorder(0);
           cell_two.setColspan(4);
           table1.addCell(cell_two);

           cell_two = new PdfPCell(new Paragraph(PDFLables.NAME_OF_THE_PERSON, Font_Bold));
           cell_two.setBorder(0);
           cell_two.setColspan(4);
           table1.addCell(cell_two);

           if(null != partADTO.getSurname() && !"".equals(partADTO.getSurname())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.SURNAME, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getSurname(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }
         if(null != partADTO.getFirstname() && !"".equals(partADTO.getFirstname())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.NAME, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getFirstname(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

           }


            if(null != partADTO.getNoOfYears() && !"".equals(partADTO.getNoOfYears())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.AGE, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getNoOfYears(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }
         if(null != partADTO.getDobday() && !"".equals(partADTO.getDobday())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.DATE_OF_BIRTH, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getDobday(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

           }
            if(null != partADTO.getGender() && !"".equals(partADTO.getGender())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.GENDER, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(commonDetails.getGenderName(Integer.parseInt(partADTO.getGender())), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }

             if(null != partADTO.getEducation() && !"".equals(partADTO.getEducation()) && !"0".equals(partADTO.getEducation())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.EDUCATION, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(commonDetails.getEducationName(Integer.parseInt(partADTO.getEducation())), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }

              if(null != partADTO.getEmployment() && !"".equals(partADTO.getEmployment()) && !"0".equals(partADTO.getEmployment())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.EMPLOYMENT, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(commonDetails.getEmployementName(Integer.parseInt(partADTO.getEmployment())), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }

             if(null != partADTO.getMarital() && !"".equals(partADTO.getMarital())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.MARITAL_STATUS, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(commonDetails.getMaritalName(Integer.parseInt(partADTO.getMarital())), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }
            if(null != partADTO.getCaste() && !"".equals(partADTO.getCaste())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.CASTE, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(commonDetails.getCasteName(Integer.parseInt(partADTO.getCaste())), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }
            if(null != partADTO.getReligion() && !"".equals(partADTO.getReligion()) && !"0".equals(partADTO.getReligion())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.RELIGION, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(commonDetails.getReligioinName(Integer.parseInt(partADTO.getReligion())), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }

           if(null != partADTO.getCard() && !"".equals(partADTO.getCard())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.RATION_CARD_NO, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getCard(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }
         if(null != partADTO.getRtype() && !"".equals(partADTO.getRtype())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.RATION_TYPE, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(commonDetails.getRationTypeName(Integer.parseInt(partADTO.getRtype())), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

           }

            if(null != partADTO.getEpiccardno() && !"".equals(partADTO.getEpiccardno())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.EPIC_CARD, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getEpiccardno(), Font_Bold));
               cell_one.setBorder(0);
               cell_one.setColspan(3);
               table1.addCell(cell_one);
          }

          if(null != partADTO.getPension_type() && !"".equals(partADTO.getPension_type())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.PENSION_CARD_TYPE, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getPension_type(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }
         if(null != partADTO.getPensioncardno() && !"".equals(partADTO.getPensioncardno())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.PENSION_CARD_NUMBER, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getPensioncardno(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

           }

           if(null != partADTO.getMole1() && !"".equals(partADTO.getMole1())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.IDENTIFICATION_MARKS, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getMole1(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }
         if(null != partADTO.getMole2() && !"".equals(partADTO.getMole2())){
               cell_one = new PdfPCell(new Paragraph(partADTO.getMole2(), Font_Bold));
               cell_one.setBorder(0);
               cell_one.setColspan(2);
               table1.addCell(cell_one);

           }

            if (null != partADTO.getParents_marriage() && !"".equals(partADTO.getParents_marriage())) {
               cell_one = new PdfPCell(new Paragraph(PDFLables.CONSANGUINEOUS_MARRIAGE, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
               if ("1".equals(partADTO.getParents_marriage())) {
                   cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                   cell_one.setBorder(0);
                   cell_one.setColspan(3);
                   table1.addCell(cell_one);
               } else if ("0".equals(partADTO.getParents_marriage())) {
                   cell_one = new PdfPCell(new Paragraph(PDFLables.NO, Font_Bold));
                   cell_one.setBorder(0);
                   cell_one.setColspan(3);
                   table1.addCell(cell_one);

               }
           }

           cell_two = new PdfPCell(new Paragraph(PDFLables.FAMILY_DETAILS, Font_Bold));
           cell_two.setBorder(0);
           cell_two.setColspan(4);
           table1.addCell(cell_two);

          if(null != partADTO.getGsurname() && !"".equals(partADTO.getGsurname())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.FATHER_NAME, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getGsurname(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }
         if(null != partADTO.getGrelation() && !"".equals(partADTO.getGrelation())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.RELATION, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(commonDetails.getRelationName(Integer.parseInt(partADTO.getGrelation())), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

           }


           cell_two = new PdfPCell(new Paragraph(PDFLables.ADDRESS, Font_Bold));
           cell_two.setBorder(0);
           cell_two.setColspan(4);
           table1.addCell(cell_two);

         if(null != partADTO.getHouseno() && !"".equals(partADTO.getHouseno())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.HOUSE_NO, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getHouseno(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }

          if(null != partADTO.getHabitation_name() && !"".equals(partADTO.getHabitation_name())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.HABITATION_WARDNO, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getHabitation_name(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }

           if(null != partADTO.getVillage_name() && !"".equals(partADTO.getVillage_name())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.TOWN_VILLAGE, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getVillage_name(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }

           if(null != partADTO.getMandal_name() && !"".equals(partADTO.getMandal_name())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.MANDAL, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getMandal_name(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }

           if(null != partADTO.getDistrict() && !"".equals(partADTO.getDistrict())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.DISTRICT, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getDistrict(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }

            if(null != partADTO.getPhoneno() && !"".equals(partADTO.getPhoneno())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.PHONE_NO, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getPhoneno(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }
         if(null != partADTO.getEmail() && !"".equals(partADTO.getEmail())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.EMAIL, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getEmail(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

           }

            if(null != partADTO.getPin() && !"".equals(partADTO.getPin())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.PIN, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getPin(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }


             if(null != partADTO.getType_disability() && !"".equals(partADTO.getType_disability())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.TYPE_OF_DISABILITY_PARTA, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(commonDetails.getDisabilityName(Integer.parseInt(partADTO.getType_disability())), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);
          }

        if(null != partADTO.getExistingpercentage() && !"".equals(partADTO.getExistingpercentage())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.EXISTING_PERCENTAGE, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getExistingpercentage(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

           }

           if(null != partADTO.getPersonstatus() && !"".equals(partADTO.getPersonstatus())){
               cell_one = new PdfPCell(new Paragraph(PDFLables.PERSON_STATUS, Font_Normal));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

               cell_one = new PdfPCell(new Paragraph(partADTO.getPersonstatus(), Font_Bold));
               cell_one.setBorder(0);
               table1.addCell(cell_one);

           }


           
           cell_one = new PdfPCell(table1);
           cell_one.setBorder(0);
           cell_one.setColspan(2);
           table.addCell(cell_one);
           
           table.setSplitRows(true);
           table.setExtendLastRow(false);
           table.setKeepTogether(true);
           
       }catch(Exception e){
           e.printStackTrace();
       }
    return table;
    }

}
