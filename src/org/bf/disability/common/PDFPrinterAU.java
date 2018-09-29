/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.common;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEvent;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.bf.disability.action.DataEnteredFieldsAction.CommonEnums;
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.dto.PartADTO;

/**
 *
 * @author t_bapinaidu
 */
public class PDFPrinterAU implements PdfPageEvent {

    public Map prepareBody(HttpServletRequest request,
            PdfPTable mainPdfPTable) throws Exception {

        Map sectionTableMap = null;
        Map dataEnteredSectionsMap = null;
        PdfPTable table = null;
        String sectionName = null;
        CommonEnums dataEntredSection = null;
        String personCode = null;
        //PartADTO partADTO = null;
        //CardioPulmonaryDTO  cardioPulmonarydto = null;
        int sectionOrderNum = 0;
        try {
            sectionTableMap = new LinkedHashMap();
            personCode = (String) request.getAttribute("personcode");
            dataEnteredSectionsMap = (HashMap) request.getAttribute("dataEnteredSectionsMap");
            request.setAttribute("dataEnteredSectionsMap", dataEnteredSectionsMap);
            Set dataEnteredSectionsMapSet = dataEnteredSectionsMap.keySet();
            Iterator dataEnteredSectionsMapSetite = dataEnteredSectionsMapSet.iterator();
            while (dataEnteredSectionsMapSetite.hasNext()) {
                sectionOrderNum++;
                PDFGenerator pDFGenerator = new PDFGenerator();
                sectionName = (String) dataEnteredSectionsMapSetite.next();
                request.setAttribute("TITLE_OF_THE_SECTION", sectionName);
                request.setAttribute("SECTION_NAME", sectionName);
                dataEntredSection = CommonEnums.valueOf(sectionName);
                switch (dataEntredSection) {
                    case BinetKamatTestofIntelligence:
                        request.setAttribute("BKTIQDetails", (MentalRetardationTestsDTO) request.getAttribute("BKTIQDetails"));
                        break;
                    case DevelopmentalScreeningTest:
                        request.setAttribute("DSTIQDetails", (MentalRetardationTestsDTO) request.getAttribute("DSTIQDetails"));
                        break;
                    case VinelandSocialMaturityScaleRecordSheet:
                        request.setAttribute("VSMSIQDetails", (MentalRetardationTestsDTO) request.getAttribute("VSMSIQDetails"));
                        break;
                    case DisabilityDetails:
                        request.setAttribute("pwdPhotoDrt", (String) request.getAttribute("pwdPhotoDrt"));
                        break;

//                    case DisabilityDetails:
//                        PartADTO partADTOtemp = (PartADTO) dataEnteredSectionsMap.get(sectionName);
//                        request.setAttribute("sectionObject", partADTOtemp);
//                        break;
//                    case VisualImpairment:
//                        CardioPulmonaryDTO cardioPulmonarydto = (CardioPulmonaryDTO) dataEnteredSectionsMap.get(sectionName);
//                        request.setAttribute("sectionObject", cardioPulmonarydto);
//                        break;
//                    case HearingImpairment:
//                        HearingImpairmentDto hearingdto = (HearingImpairmentDto) dataEnteredSectionsMap.get(sectionName);
//                        request.setAttribute("sectionObject", hearingdto);
//                        break;
//
                    default:
                        break;

                }
                request.setAttribute("sectionOrderNum", String.valueOf(sectionOrderNum));
                table = pDFGenerator.generatePDF(request);
                sectionTableMap.put(sectionName, table);
            }
            request.setAttribute("personcode", personCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sectionTableMap;
    }

    public PdfPTable prepareHeader(HttpServletRequest request, PartADTO partADTO) throws Exception {
        PdfPTable table1 = null;
        PdfPTable table4 = null;
        PdfPTable prtinfoTable;
        // Image headerImage;
        // Image disabilityLogo;
        float[] widths3 = {0.20f, 0.90f, 0.20f};
        float[] widths4 = {0.40f, 0.20f, 0.30f};
        float[] widths2 = {0.25f, 0.75f};
        //String strDirectoy = null;
        //String disabilitylogoDirectory = null;
        String personCode = null;
        String formdate = null;
        try {
            //strDirectoy = (String) request.getAttribute("strDirectoy");
            //disabilitylogoDirectory = (String) request.getAttribute("disabilitylogoDirectory");
            personCode = (String) request.getAttribute("personcode");
            java.util.Date date = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            formdate = sdf.format(date.getTime());
            table1 = new PdfPTable(widths3);
            table4 = new PdfPTable(widths4);
            prtinfoTable = new PdfPTable(widths2);

//            disabilityLogo = Image.getInstance(disabilitylogoDirectory);
//            disabilityLogo.scaleToFit(50, disabilityLogo.getHeight());
//            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//            table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//            table1.getDefaultCell().setBorderWidth(0);
//            table1.getDefaultCell().setColspan(1);
//            table1.addCell(new Phrase(new Chunk(disabilityLogo, 0, 0)));



            table1.setExtendLastRow(false);
            Phrase p = new Phrase();


            FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            String title1 = "";
            String title2 = "";
            // String title3 = "";

            title1 = partADTO.getHospitalname();
            title2 = partADTO.getHospitaladdress();
            //title3 = "HMRI";

            Chunk ck = new Chunk(title1 + "\n", new Font(Font.HELVETICA, 12, Font.NORMAL, new Color(139, 69, 19)));
            p.add(ck);
            ck = new Chunk(title2 + "\n", new Font(Font.HELVETICA, 12, Font.NORMAL, new Color(139, 69, 19)));
            p.add(ck);
//            ck = new Chunk(title3, new Font(Font.HELVETICA, 12, Font.NORMAL, new Color(139, 69, 19)));
//            p.add(ck);

            table1.getDefaultCell().setBorderWidth(0);
            table1.getDefaultCell().setColspan(3);
            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(p);

//            headerImage = Image.getInstance(strDirectoy);
//            headerImage.scaleToFit(50, headerImage.getHeight());
//            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//            table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//            table1.getDefaultCell().setBorderWidth(0);
//            table1.getDefaultCell().setColspan(1);
//            table1.addCell(new Phrase(new Chunk(headerImage, 0, 0)));


//            table1.getDefaultCell().setBackgroundColor(Color.white);
//            table1.getDefaultCell().setBorderWidth(0);
//            table1.getDefaultCell().setColspan(1);
//            table1.addCell(new Phrase(new Chunk("")));

            p = new Phrase();
            ck = new Chunk("PersonCode: " + personCode + "\n", new Font(Font.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19)));
            p.add(ck);

            table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.getDefaultCell().setBackgroundColor(Color.white);
            table4.getDefaultCell().setBorderWidth(0);
            table4.getDefaultCell().setColspan(2);
            table4.addCell(p);

//            table4.getDefaultCell().setBackgroundColor(Color.white);
//            table4.getDefaultCell().setBorderWidth(0);
//            table4.addCell(new Phrase(new Chunk("")));

            p = new Phrase();
            ck = new Chunk("Date: " + formdate + "\n", new Font(Font.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19)));
            p.add(ck);

            table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table4.getDefaultCell().setBorderWidth(0);
            table4.addCell(p);


            table1.getDefaultCell().setBackgroundColor(Color.white);
            table1.getDefaultCell().setBorderWidth(0);
            table1.getDefaultCell().setColspan(3);
            table1.addCell(table4);


            p = new Phrase();
            ck = new Chunk("Name of the person : " + partADTO.getSurname() + " " + partADTO.getFirstname() + "\n", new Font(Font.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19)));
            p.add(ck);

            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.getDefaultCell().setBorderWidth(0);
            table1.getDefaultCell().setColspan(3);
            table1.addCell(p);

//            table1.getDefaultCell().setBackgroundColor(Color.white);
//            table1.getDefaultCell().setBorderWidth(0);
//            table1.getDefaultCell().setColspan(3);
//            table1.addCell(new Phrase(new Chunk("")));

            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.getDefaultCell().setBorderWidth(0);
            table1.getDefaultCell().setColspan(3);
            table1.addCell(new Paragraph("------------------------------------------------------------------------------", new Font(Font.HELVETICA, 15, Font.NORMAL, Color.black)));

            String tempTitle = "";
            tempTitle = "Sheet Title:";
            p = new Phrase();
            ck = new Chunk(tempTitle, new Font(Font.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19)));
            p.add(ck);
            prtinfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            prtinfoTable.getDefaultCell().setBorderWidth(0);
            prtinfoTable.getDefaultCell().setColspan(1);
            prtinfoTable.getDefaultCell().setSpaceCharRatio(3f);
            prtinfoTable.addCell(p);
            p = new Phrase();

            ck = new Chunk("Person With Disability Details", new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK));
            p.add(ck);
            prtinfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            prtinfoTable.getDefaultCell().setBorderWidth(0);
            prtinfoTable.getDefaultCell().setColspan(1);
            prtinfoTable.addCell(p);



            table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            table1.getDefaultCell().setBorderWidth(0);
            table1.addCell(prtinfoTable);




        } catch (Exception e) {
            e.printStackTrace();
        }
        return table1;
    }

    public Document prepareDocument(ByteArrayOutputStream baos,
            PdfPTable headerPdfPTable) throws Exception {

        Document document = null;

        try {

            //document = new Document(PageSize.A4, 50, 50, 210, 72);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, baos);


            PdfPageEventHelper pghelp = new PdfPageEventHelper();
            pghelp.onCloseDocument(writer, document);
            pghelp.onEndPage(writer, document);
            pghelp.onOpenDocument(writer, document);
            writer.setPageEvent(new PDFPrinter());

            //Prepare Header
            Paragraph p = new Paragraph();
            p.add(headerPdfPTable);
            HeaderFooter header = new HeaderFooter(new Phrase(p), true);
            header.setAlignment(Paragraph.ALIGN_CENTER);
            header.setBorder(Rectangle.NO_BORDER);


            //set the header
            document.setHeader(header);

            //Prepare Footer
            HeaderFooter footer = new HeaderFooter(new Phrase(""), false);
            footer.setBorder(Rectangle.NO_BORDER);
            footer.setAlignment(Paragraph.ALIGN_CENTER);

            //set the footer
            document.setFooter(footer);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return document;
    }

    public Document setBodyToDocument(Document document,
            Map sectionTableMap,
            PdfPTable minHeaderPdfPTable)
            throws Exception {
        String sectionName = null;
        try {
            float width = document.getPageSize().getWidth();
            float height = document.getPageSize().getHeight();
            PdfPTable pdfPTable = null;


            for (Iterator ite = sectionTableMap.keySet().iterator(); ite.hasNext();) {

                sectionName = ite.next().toString();
                pdfPTable = (PdfPTable) sectionTableMap.get(sectionName);
                if (null != pdfPTable) {
                    pdfPTable.setExtendLastRow(false);
                    pdfPTable.getDefaultCell().setBorder(0);
                    pdfPTable.setHorizontalAlignment(0);
                    pdfPTable.setTotalWidth(width - 72);
                    pdfPTable.setLockedWidth(true);
                    pdfPTable.setSplitRows(true);
                    if (!document.isOpen()) {
                        document.open();

                        document = resetHeaderToDocumentIfRequired(document, minHeaderPdfPTable);
                    }
                    document.add(pdfPTable);
                }
            }


            document.close();



        } catch (Exception e) {
            e.printStackTrace();
        }


        return document;
    }

    private Document resetHeaderToDocumentIfRequired(Document document, PdfPTable minHeaderPdfPTable) {

        try {
            document.resetHeader();
            Paragraph p = new Paragraph("");
            p.add(minHeaderPdfPTable);
            HeaderFooter header = new HeaderFooter(new Phrase(p), true);
            header.setAlignment(Paragraph.ALIGN_CENTER);
            header.setBorder(Rectangle.NO_BORDER);
            document.setHeader(header);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    public void print(HttpServletRequest request, ByteArrayOutputStream baos)
            throws Exception {

        try {
            PdfReader pdReader = new PdfReader(new ByteArrayInputStream(baos.toByteArray()));
            request.setAttribute("CONTENT_TYPE", "application/pdf");
            request.setAttribute("FILE_TYPE", ".pdf");
            request.setAttribute("CONTENT_BYTES", baos.toByteArray());

            //request.setAttribute(EpConstants.Ep_PTL_NUMBER, formRequestObj.getPtlNumber());

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void onOpenDocument(PdfWriter writer, Document dcmnt) {
        // TODO Auto-generated method stub
    }

    public void onStartPage(PdfWriter writer, Document dcmnt) {
        // TODO Auto-generated method stub
    }

    public void onEndPage(PdfWriter writer, Document dcmnt) {
        // TODO Auto-generated method stub
    }

    public void onCloseDocument(PdfWriter writer, Document dcmnt) {
        // TODO Auto-generated method stub
    }

    public void onParagraph(PdfWriter writer, Document dcmnt, float f) {
        // TODO Auto-generated method stub
    }

    public void onParagraphEnd(PdfWriter writer, Document dcmnt, float f) {
        // TODO Auto-generated method stub
    }

    public void onChapter(PdfWriter writer, Document dcmnt, float f, Paragraph prgrph) {
        // TODO Auto-generated method stub
    }

    public void onChapterEnd(PdfWriter writer, Document dcmnt, float f) {
        // TODO Auto-generated method stub
    }

    public void onSection(PdfWriter writer, Document dcmnt, float f, int i, Paragraph prgrph) {
        // TODO Auto-generated method stub
    }

    public void onSectionEnd(PdfWriter writer, Document dcmnt, float f) {
        // TODO Auto-generated method stub
    }

    public void onGenericTag(PdfWriter writer, Document dcmnt, Rectangle rctngl, String string) {
        // TODO Auto-generated method stub
    }

    public Document setHeaderFooterToDocument(HttpServletRequest request,
            Document document, ByteArrayOutputStream baos, PartADTO partADTO) throws Exception {

        BaseFont helv;
        String waterMarkTxt = "SADAREM";
        String strDirectoy = null;

        try {
            PdfReader pdReader = new PdfReader(new ByteArrayInputStream(baos.toByteArray()));
            int n = pdReader.getNumberOfPages();
            baos.reset();
            // we create a stamper that will copy the document to a new file
            PdfStamper stamp = new PdfStamper(pdReader, baos);
            // adding content to each page
            int i = 0;
            PdfContentByte under;
            PdfContentByte over;
            // strDirectoy = (String) request.getAttribute("strDirectoy");
            //Image img = Image.getInstance(strDirectoy);
            // BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
            //img.setAbsolutePosition(200, 400);
            helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
            PdfTemplate tpl;
            PdfPTable table = null;

            if (null == waterMarkTxt || "null".equals(waterMarkTxt) || "".equals(waterMarkTxt)) {
                waterMarkTxt = "SADAREM";
            }
            while (i < n) {
                i++;
                // watermark under the existing page
                under = stamp.getUnderContent(i);
                under.saveState();

                //initialization of the template
                tpl = under.createTemplate(100, 100);
                tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));

                // compose the footer
                String text = "Page " + i + " of " + n;
                float textSize = helv.getWidthPoint(text, 10);
                float textBase = document.bottom() - 5;
                under.beginText();
                under.setFontAndSize(helv, 10);

                float adjust = helv.getWidthPoint("0", 10);
                under.setTextMatrix(document.right() - textSize - adjust, textBase);
                under.showText(text);
                under.endText();
                under.addTemplate(tpl, document.right() - adjust, textBase);

//            String textmessage = "* Default Range Values are Not Displayed";
//            float textmessageSize = helv.getWidthPoint(textmessage, 20);
//            float textmessageBase = document.bottom() - 5;
//            under.beginText();
//            under.setFontAndSize(helv, 10);
//
//            float textmessageadjust = helv.getWidthPoint("0", 10);
//            under.setTextMatrix(document.left() - textmessageSize - textmessageadjust, textmessageBase);
//            under.showText(textmessage);
//            under.endText();
//            under.addTemplate(tpl, document.left() - textmessageadjust, textmessageBase);

                // compose the footer


                if (null != partADTO) {

                    String space = "";
                    float spaceSize = helv.getWidthPoint(space, 10);
                    float spaceBase = document.bottom() + 60;
                    under.beginText();
                    under.setFontAndSize(helv, 10);

                    float adjustspace = helv.getWidthPoint("0", 10);
                    under.setTextMatrix(document.right() - spaceSize - adjustspace, spaceBase);
                    under.setColorFill(Color.BLUE);
                    under.showText(space);
                    under.endText();
                    under.addTemplate(tpl, document.right() - adjustspace, spaceBase);

                    if (null != partADTO.getDoctor1name() && null != partADTO.getDoctor1regnumber()
                            && null != partADTO.getDoctor1designation()) {
                        String signature = "(Signature of the Doctor)";
                        float signatureSize = helv.getWidthPoint(signature, 10);
                        float signatureBase = document.bottom() + 40;
                        under.beginText();
                        under.setFontAndSize(helv, 10);

                        float adjustsignature = helv.getWidthPoint("0", 10);
                        under.setTextMatrix(document.right() - signatureSize - adjustsignature, signatureBase);
                        under.setColorFill(Color.BLACK);
                        under.showText(signature);
                        under.endText();
                        under.addTemplate(tpl, document.right() - adjustsignature, signatureBase);
                    }
                    if (null != partADTO.getDoctor1name()) {
                        String doctorname = "Doctor Name : " + partADTO.getDoctor1name();
                        float doctornameSize = helv.getWidthPoint(doctorname, 10);
                        float doctornameBase = document.bottom() + 30;
                        under.beginText();
                        under.setFontAndSize(helv, 10);

                        float adjustdoctorname = helv.getWidthPoint("0", 10);
                        under.setTextMatrix(document.right() - doctornameSize - adjustdoctorname, doctornameBase);
                        under.setColorFill(Color.BLUE);
                        under.showText(doctorname);
                        under.endText();
                        under.addTemplate(tpl, document.right() - adjustdoctorname, doctornameBase);
                    }
                    if (null != partADTO.getDoctor1regnumber()) {
                        String doctorRegno = "Reg. No. : " + partADTO.getDoctor1regnumber();
                        float doctorRegnoSize = helv.getWidthPoint(doctorRegno, 10);
                        float doctorRegnoBase = document.bottom() + 20;
                        under.beginText();
                        under.setFontAndSize(helv, 10);

                        float adjustdoctorRegno = helv.getWidthPoint("0", 10);
                        under.setTextMatrix(document.right() - doctorRegnoSize - adjustdoctorRegno, doctorRegnoBase);
                        under.setColorFill(Color.BLUE);
                        under.showText(doctorRegno);
                        under.endText();
                        under.addTemplate(tpl, document.right() - adjustdoctorRegno, doctorRegnoBase);
                    }
                    if (null != partADTO.getDoctor1designation()) {
                        String doctorDisig = "Designation : " + partADTO.getDoctor1designation();
                        float doctorDisigSize = helv.getWidthPoint(doctorDisig, 10);
                        float doctorDisigBase = document.bottom() + 10;
                        under.beginText();
                        under.setFontAndSize(helv, 10);

                        float adjustdoctorDisig = helv.getWidthPoint("0", 10);
                        under.setTextMatrix(document.right() - doctorDisigSize - adjustdoctorDisig, doctorDisigBase);
                        under.setColorStroke(Color.BLUE);
                        under.showText(doctorDisig);
                        under.endText();
                        under.addTemplate(tpl, document.right() - adjustdoctorDisig, doctorDisigBase);
                    }
                }



                // draw a Rectangle around the page
                under.setColorStroke(new Color(139, 69, 19));
                under.setLineWidth(2);
                if (90 != stamp.getReader().getPageRotation(i)) {
                    if (842 == document.getPageSize().getHeight()) {
                        under.rectangle(20, 20, document.getPageSize().getWidth() - 40, document.getPageSize().getHeight() - 40);
                    } else {
                        under.rectangle(20, 20, document.getPageSize().getWidth() - 290, document.getPageSize().getHeight() + 205);
                    }
                } else {
                    if (842 == document.getPageSize().getHeight()) {
                        under.rectangle(20, 20, document.getPageSize().getWidth() + 205, document.getPageSize().getHeight() - 290);
                    } else {
                        under.rectangle(20, 20, document.getPageSize().getWidth() - 40, document.getPageSize().getHeight() - 40);
                    }
                }
                under.stroke();

                if (i > 0) {
                    under.setColorFill(new Color(255, 228, 225));
                    //under.setColorFill(new Color(139, 69, 19));
                    under.beginText();
                    if (90 != stamp.getReader().getPageRotation(i)) {
                        //if(842 == document.getPageSize().height()){
                        under.setFontAndSize(helv, 60);
                        //under.setFontAndSize(helv, 40);
                        under.showTextAligned(Element.ALIGN_CENTER, waterMarkTxt, (document.getPageSize().getWidth() - 40) / 2, (document.getPageSize().getHeight() - 40) / 2, 45);
                        // }else{
                        //under.setFontAndSize(helv, 32);
                        //under.showTextAligned(Element.ALIGN_CENTER,waterMarkTxt, (document.getPageSize().height() - 290) / 2, (document.getPageSize().width() + 205) / 2, 45);
                        // }

                    } else {
                        //if(842 == document.getPageSize().height()){
                        under.setFontAndSize(helv, 60);
                        //under.setFontAndSize(helv, 32);
                        under.showTextAligned(Element.ALIGN_CENTER, waterMarkTxt, (document.getPageSize().getWidth() + 205) / 2, (document.getPageSize().getHeight() - 290) / 2, 45);
                        // }else{
                        //under.setFontAndSize(helv, 32);
                        //under.showTextAligned(Element.ALIGN_CENTER,waterMarkTxt, (document.getPageSize().height() - 40) / 2, (document.getPageSize().width() - 40) / 2, 45);
                        // }
                    }
                    under.endText();
                    //under.restoreState();
                }
                under.restoreState();
            }
            //}
            // closing PdfStamper will generate the new PDF file
            stamp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }
}
