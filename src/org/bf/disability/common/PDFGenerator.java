/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.common;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.awt.Color;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.bf.disability.action.DataEnteredFieldsAction.CommonEnums;
import org.bf.disability.dto.AmputationDto;
import org.bf.disability.dto.CardioPulmonaryDTO;
import org.bf.disability.dto.DwarfismDTO;
import org.bf.disability.dto.EvaluationDTO;
import org.bf.disability.dto.HearingImpairmentDto;
import org.bf.disability.dto.LocomotorneedsDTO;
import org.bf.disability.dto.LowerExtremityDTO;
import org.bf.disability.dto.MRBinetKamatDetailedTestDTO;
import org.bf.disability.dto.MRDevelopmentalScreeningTestDTO;
import org.bf.disability.dto.MentalIllnessDTO;
import org.bf.disability.dto.MentalRetardationDTO;
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.dto.PartCUpdateDTO;
import org.bf.disability.dto.TransverseDTO;
import org.bf.disability.dto.TrunkDTO;
import org.bf.disability.dto.UpperExtrimityDto;
import org.bf.disability.dto.VsmsScreeningTestDTO;

/**
 *
 * @author t_bapinaidu
 */
public class PDFGenerator {


    public PdfPTable generatePDF(HttpServletRequest request) {
        
        PdfPTable table_main = null;
        Font Font_Bold = null;
        Font Font_Normal = null;
        Font Font_Bold_Color = null;
        Font Font_Section_heading = null;
        String pdfObjectType = null;
        Class classObject = null;
        PdfPTable table = null;
        PdfPCell cell = null;
        Map dataEnteredSectionsMap = null;
        CommonEnums dataEntredSection = null;
        PartADTO partADTO = null;
        CardioPulmonaryDTO  cardioPulmonarydto = null;
        HearingImpairmentDto hearingdto = null;
        UpperExtrimityDto upperExtrimitydto = null;
        LowerExtremityDTO lowerextremitydto = null;
        AmputationDto amputationdto = null;
        TransverseDTO transversedto = null;
        TrunkDTO trunkdto = null;
        EvaluationDTO evaluationdto = null;
        DwarfismDTO dwarfismdto = null;
        MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO = null;
        VsmsScreeningTestDTO vsmsscreeningtestdto = null;
        MentalRetardationTestsDTO mentalretardationtestdto = null;
        MRBinetKamatDetailedTestDTO MRBKDTestDTO = null;
        MentalRetardationDTO mentalRetardationDTO = null;
        LocomotorneedsDTO locomotorneedsdto = null;
        PartCUpdateDTO partcdto = null;
        MentalIllnessDTO millnessdto = null;
        String sectionOrderNum = "";
        
        try{
             dataEnteredSectionsMap = (HashMap)request.getAttribute("dataEnteredSectionsMap");
             if(request.getAttribute("sectionOrderNum")!=null)
			sectionOrderNum=(String)request.getAttribute("sectionOrderNum");
             String title = (String) request.getAttribute("TITLE_OF_THE_SECTION");
             String sectionName = (String) request.getAttribute("SECTION_NAME");
             dataEntredSection = CommonEnums.valueOf(sectionName);
                 switch(dataEntredSection){
                    case PersonalDetails:
                         partADTO = (PartADTO)dataEnteredSectionsMap.get(sectionName);
                         request.setAttribute("sectionObject", partADTO);
                         break;
                   case DisabilityDetails:
                        partADTO = (PartADTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("pwdPhotoDrt",(String)request.getAttribute("pwdPhotoDrt"));
                        request.setAttribute("sectionObject", partADTO);
                        break;
                   case RejectedDetails:
                        partADTO = (PartADTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("pwdPhotoDrt",(String)request.getAttribute("pwdPhotoDrt"));
                        request.setAttribute("sectionObject", partADTO);
                        break;
                   case VisualImpairment:
                        cardioPulmonarydto = (CardioPulmonaryDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", cardioPulmonarydto);
                        break;
                   case HearingImpairment:
                        hearingdto = (HearingImpairmentDto) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", hearingdto);
                        break;
                   case UpperExtremity:
                        upperExtrimitydto = (UpperExtrimityDto) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", upperExtrimitydto);
                        break;
                   case LowerExtremity:
                        lowerextremitydto = (LowerExtremityDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", lowerextremitydto);
                        break;
                   case Amputation:
                        amputationdto = (AmputationDto) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", amputationdto);
                        break;
                   case CongentialDeficiencies:
                        transversedto = (TransverseDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", transversedto);
                        break;
                   case Trunk:
                        trunkdto = (TrunkDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", trunkdto);
                        break;
                   case NeurologicalConditions:
                        evaluationdto = (EvaluationDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", evaluationdto);
                        break;
                   case CardiopulmonaryDiseases:
                        String cardiopulmonary = (String)dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", cardiopulmonary);
                        break;
                   case Dwarfism:
                        dwarfismdto = (DwarfismDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", dwarfismdto);
                        break;
                   case LocomotorNeeds:
                        locomotorneedsdto = (LocomotorneedsDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", locomotorneedsdto);
                        break;
                   case DevelopmentalScreeningTest:
                        mrDevelopmentalScreeningTestDTO = (MRDevelopmentalScreeningTestDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", mrDevelopmentalScreeningTestDTO);
                        break;
                   case VinelandSocialMaturityScaleRecordSheet:
                        vsmsscreeningtestdto = (VsmsScreeningTestDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("VSMSIQDetails",(MentalRetardationTestsDTO)request.getAttribute("VSMSIQDetails"));
                        request.setAttribute("sectionObject", vsmsscreeningtestdto);
                        break;
                   case BinetKamatTestofIntelligence:
                        MRBKDTestDTO = (MRBinetKamatDetailedTestDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("BKTIQDetails",(MentalRetardationTestsDTO)request.getAttribute("BKTIQDetails"));
                        request.setAttribute("sectionObject", MRBKDTestDTO);
                        break;
                   case MalinsIntelligenceScaleforIndianChildren:
                        mentalretardationtestdto = (MentalRetardationTestsDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", mentalretardationtestdto);
                        break;
                   case SeguinFormBoard:
                        mentalretardationtestdto = (MentalRetardationTestsDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", mentalretardationtestdto);
                        break;
                   case AlexanderPassAlongTest:
                        mentalretardationtestdto = (MentalRetardationTestsDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", mentalretardationtestdto);
                        break;
                   case BhatiasBatteryofIntelligenceTests:
                        mentalretardationtestdto = (MentalRetardationTestsDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", mentalretardationtestdto);
                        break;
                   case MentalRetardationNeeds:
                        mentalRetardationDTO = (MentalRetardationDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", mentalRetardationDTO);
                        break;
                   case GeneralNeeds:
                        partcdto = (PartCUpdateDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", partcdto);
                        break;
                   case MentalIllness:
                        millnessdto = (MentalIllnessDTO) dataEnteredSectionsMap.get(sectionName);
                        request.setAttribute("sectionObject", millnessdto);
                        break;




                    default:
                         break;

                 }

            if(sectionName != null){
            pdfObjectType = "org.bf.disability.pdf.PDF"+sectionName;

            Font_Bold =  FontFactory.getFont(FontFactory.HELVETICA,10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA,10, Font.NORMAL,Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA,10, Font.BOLD, new Color(139,69,19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD,new Color(139,69,19));

            float[] widths1 = {0.05f, 0.948f, 0.02f};
            table_main = new PdfPTable(widths1);
            table_main.setExtendLastRow(false);
            table_main.setWidthPercentage(100);
            table_main.setSplitLate(false);
            
            PdfPCell cell1 = null;
            
            if(!"1".equals(sectionOrderNum)){
            cell1 = new PdfPCell((new Paragraph("----------------------------------------------------------------------------------------------", new Font(Font.HELVETICA, 15, Font.NORMAL, Color.black))));
            cell1.setBorder(0);
            cell1.setColspan(3);
            //cell1.setPaddingRight(30f);
            cell1.setPaddingLeft(10f);
            cell1.setLeading(2f, 2f);
            //cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table_main.addCell(cell1);
            }

            cell1 = new PdfPCell((new Paragraph("~ ~ ~ ~ ~ ~" + title.toString() + "~ ~ ~ ~ ~ ~", Font_Section_heading)));
            cell1.setBorder(0);
            cell1.setColspan(3);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setPaddingRight(30f);
            //cell1.setPaddingLeft(10f);
            cell1.setLeading(2f, 2f);
            table_main.addCell(cell1);
            table_main.setExtendLastRow(false);

            classObject = Class.forName(pdfObjectType);
            Object ob = classObject.newInstance();
            Method method = classObject.getMethod("getTable",  new Class[] { HttpServletRequest.class });
            table = (PdfPTable)method.invoke(ob, new Object[] {request});
            if (null != table && table.getRows().size() > 0) {
                table.setSplitLate(false);
                table.setExtendLastRow(false);
                cell = new PdfPCell(table);
                cell.setBorder(0);
                cell.setColspan(2);
                //table_main.setSpacingBefore(15f);
                //table_main.setSpacingAfter(15f);
                //cell.setFixedHeight(table.getTotalHeight());
                table_main.addCell(cell);
                cell = new PdfPCell(new Paragraph(""));
                cell.setBorder(0);
                table_main.addCell(cell);
            }
            
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
      return table_main;
    }


}
