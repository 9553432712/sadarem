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
import org.bf.disability.dto.MRBinetKamatDetailedTestDTO;
import org.bf.disability.dto.MentalRetardationTestsDTO;

/**
 *
 * @author 509864
 */
public class PDFBinetKamatTestofIntelligence {

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
            MRBinetKamatDetailedTestDTO MRBKDTestDTO = (MRBinetKamatDetailedTestDTO) request.getAttribute("sectionObject");
            MentalRetardationTestsDTO mrtestdto = (MentalRetardationTestsDTO) request.getAttribute("BKTIQDetails");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            float[] bkt_one_widths = {0.07f, 0.07f, 0.07f, 0.07f, 0.07f,0.07f, 0.07f, 0.07f, 0.07f, 0.07f,0.07f, 0.07f, 0.07f, 0.07f};
            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable bkt_one_table = new PdfPTable(bkt_one_widths);

            if(mrtestdto.getBictiq() > 0){

                cell_one = new PdfPCell(new Paragraph(PDFLables.ITEM_NO, Font_Bold));
                cell_one.setBorder(0);
                bkt_one_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.AGE_LEVELS, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(13);
                bkt_one_table.addCell(cell_one);

                String[] headerValues = {"","3","4","5","6","7","8","9","10","12","14","16","19","22"};

                bkt_one_table = getDisplayHeaderRow(headerValues, bkt_one_table, Font_Bold, cell_one);


                int[][] bkt_list = {{MRBKDTestDTO.getAgeLevel3_Item1(),MRBKDTestDTO.getAgeLevel4_Item1(),MRBKDTestDTO.getAgeLevel5_Item1(),MRBKDTestDTO.getAgeLevel6_Item1(),MRBKDTestDTO.getAgeLevel7_Item1(),MRBKDTestDTO.getAgeLevel8_Item1(),MRBKDTestDTO.getAgeLevel9_Item1(),MRBKDTestDTO.getAgeLevel10_Item1(),MRBKDTestDTO.getAgeLevel12_Item1(),MRBKDTestDTO.getAgeLevel14_Item1(),MRBKDTestDTO.getAgeLevel16_Item1(),MRBKDTestDTO.getAgeLevel19_Item1(),MRBKDTestDTO.getAgeLevel22_Item1()},
                                    {MRBKDTestDTO.getAgeLevel3_Item2(),MRBKDTestDTO.getAgeLevel4_Item2(),MRBKDTestDTO.getAgeLevel5_Item2(),MRBKDTestDTO.getAgeLevel6_Item2(),MRBKDTestDTO.getAgeLevel7_Item2(),MRBKDTestDTO.getAgeLevel8_Item2(),MRBKDTestDTO.getAgeLevel9_Item2(),MRBKDTestDTO.getAgeLevel10_Item2(),MRBKDTestDTO.getAgeLevel12_Item2(),MRBKDTestDTO.getAgeLevel14_Item2(),MRBKDTestDTO.getAgeLevel16_Item2(),MRBKDTestDTO.getAgeLevel19_Item2(),MRBKDTestDTO.getAgeLevel22_Item2()},
                                    {MRBKDTestDTO.getAgeLevel3_Item3(),MRBKDTestDTO.getAgeLevel4_Item3(),MRBKDTestDTO.getAgeLevel5_Item3(),MRBKDTestDTO.getAgeLevel6_Item3(),MRBKDTestDTO.getAgeLevel7_Item3(),MRBKDTestDTO.getAgeLevel8_Item3(),MRBKDTestDTO.getAgeLevel9_Item3(),MRBKDTestDTO.getAgeLevel10_Item3(),MRBKDTestDTO.getAgeLevel12_Item3(),MRBKDTestDTO.getAgeLevel14_Item3(),MRBKDTestDTO.getAgeLevel16_Item3(),MRBKDTestDTO.getAgeLevel19_Item3(),MRBKDTestDTO.getAgeLevel22_Item3()},
                                    {MRBKDTestDTO.getAgeLevel3_Item4(),MRBKDTestDTO.getAgeLevel4_Item4(),MRBKDTestDTO.getAgeLevel5_Item4(),MRBKDTestDTO.getAgeLevel6_Item4(),MRBKDTestDTO.getAgeLevel7_Item4(),MRBKDTestDTO.getAgeLevel8_Item4(),MRBKDTestDTO.getAgeLevel9_Item4(),MRBKDTestDTO.getAgeLevel10_Item4(),MRBKDTestDTO.getAgeLevel12_Item4(),MRBKDTestDTO.getAgeLevel14_Item4(),MRBKDTestDTO.getAgeLevel16_Item4(),MRBKDTestDTO.getAgeLevel19_Item4(),MRBKDTestDTO.getAgeLevel22_Item4()},
                                    {MRBKDTestDTO.getAgeLevel3_Item5(),MRBKDTestDTO.getAgeLevel4_Item5(),MRBKDTestDTO.getAgeLevel5_Item5(),MRBKDTestDTO.getAgeLevel6_Item5(),MRBKDTestDTO.getAgeLevel7_Item5(),MRBKDTestDTO.getAgeLevel8_Item5(),MRBKDTestDTO.getAgeLevel9_Item5(),MRBKDTestDTO.getAgeLevel10_Item5(),MRBKDTestDTO.getAgeLevel12_Item5(),MRBKDTestDTO.getAgeLevel14_Item5(),MRBKDTestDTO.getAgeLevel16_Item5(),MRBKDTestDTO.getAgeLevel19_Item5(),MRBKDTestDTO.getAgeLevel22_Item5()},
                                    {MRBKDTestDTO.getAgeLevel3_Item6(),MRBKDTestDTO.getAgeLevel4_Item6(),MRBKDTestDTO.getAgeLevel5_Item6(),MRBKDTestDTO.getAgeLevel6_Item6(),MRBKDTestDTO.getAgeLevel7_Item6(),MRBKDTestDTO.getAgeLevel8_Item6(),MRBKDTestDTO.getAgeLevel9_Item6(),MRBKDTestDTO.getAgeLevel10_Item6(),MRBKDTestDTO.getAgeLevel12_Item6(),MRBKDTestDTO.getAgeLevel14_Item6(),MRBKDTestDTO.getAgeLevel16_Item6(),MRBKDTestDTO.getAgeLevel19_Item6(),MRBKDTestDTO.getAgeLevel22_Item6()},
                                    {MRBKDTestDTO.getAgeLevel3_ItemAlt1(),MRBKDTestDTO.getAgeLevel4_ItemAlt1(),MRBKDTestDTO.getAgeLevel5_ItemAlt1(),MRBKDTestDTO.getAgeLevel6_ItemAlt1(),MRBKDTestDTO.getAgeLevel7_ItemAlt1(),MRBKDTestDTO.getAgeLevel8_ItemAlt1(),MRBKDTestDTO.getAgeLevel9_ItemAlt1(),MRBKDTestDTO.getAgeLevel10_ItemAlt1(),MRBKDTestDTO.getAgeLevel12_ItemAlt1(),MRBKDTestDTO.getAgeLevel14_ItemAlt1(),MRBKDTestDTO.getAgeLevel16_ItemAlt1(),MRBKDTestDTO.getAgeLevel19_ItemAlt1(),MRBKDTestDTO.getAgeLevel22_ItemAlt1()},
                                    {MRBKDTestDTO.getAgeLevel3_ItemAlt2(),MRBKDTestDTO.getAgeLevel4_ItemAlt2(),MRBKDTestDTO.getAgeLevel5_ItemAlt2(),MRBKDTestDTO.getAgeLevel6_ItemAlt2(),MRBKDTestDTO.getAgeLevel7_ItemAlt2(),MRBKDTestDTO.getAgeLevel8_ItemAlt2(),MRBKDTestDTO.getAgeLevel9_ItemAlt2(),MRBKDTestDTO.getAgeLevel10_ItemAlt2(),MRBKDTestDTO.getAgeLevel12_ItemAlt2(),MRBKDTestDTO.getAgeLevel14_ItemAlt2(),MRBKDTestDTO.getAgeLevel16_ItemAlt2(),MRBKDTestDTO.getAgeLevel19_ItemAlt2(),MRBKDTestDTO.getAgeLevel22_ItemAlt2()},
                                    {MRBKDTestDTO.getAgeLevel3_ItemAlt3(),MRBKDTestDTO.getAgeLevel4_ItemAlt3(),MRBKDTestDTO.getAgeLevel5_ItemAlt3(),MRBKDTestDTO.getAgeLevel6_ItemAlt3(),MRBKDTestDTO.getAgeLevel7_ItemAlt3(),MRBKDTestDTO.getAgeLevel8_ItemAlt3(),MRBKDTestDTO.getAgeLevel9_ItemAlt3(),MRBKDTestDTO.getAgeLevel10_ItemAlt3(),MRBKDTestDTO.getAgeLevel12_ItemAlt3(),MRBKDTestDTO.getAgeLevel14_ItemAlt3(),MRBKDTestDTO.getAgeLevel16_ItemAlt3(),MRBKDTestDTO.getAgeLevel19_ItemAlt3(),MRBKDTestDTO.getAgeLevel22_ItemAlt3()}
                                    };

                String[] lables = {"1","2","3","4","5","6","Alt.No 1","Alt.No 2","Alt.No 3"};

                 bkt_one_table =  creatingBKTPdfCells(bkt_one_table, Font_Bold, Font_Normal, cell_one, 0,
                           bkt_list, lables);




                cell_one = new PdfPCell(bkt_one_table);
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                table.addCell(cell_one);


            

            if (null != mrtestdto.getBictbasalage() && !"".equals(mrtestdto.getBictbasalage())) {
               
                cell_one = new PdfPCell(new Paragraph(PDFLables.BASAL_AGE, Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(mrtestdto.getBictbasalage(), Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

            }

                 if (null != mrtestdto.getBictterminalage() && !"".equals(mrtestdto.getBictterminalage())) {

                cell_one = new PdfPCell(new Paragraph(PDFLables.TERMINAL_AGE, Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(mrtestdto.getBictterminalage(), Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

            }

            if(null != mrtestdto && !"".equals(mrtestdto)){
                if((null != mrtestdto.getBictyear() && !"".equals(mrtestdto.getBictyear())) ||
                    null != mrtestdto.getBictmonth() && !"".equals(mrtestdto.getBictmonth())){
                cell_one = new PdfPCell(new Paragraph(PDFLables.MENTAL_AGE, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                table.addCell(cell_one);
               if(null != mrtestdto.getBictyear() && !"".equals(mrtestdto.getBictyear())){
                cell_one = new PdfPCell(new Paragraph(PDFLables.YEARS+"    "+mrtestdto.getBictyear(), Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);
               }else{
                  cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);
               }
                if(null != mrtestdto.getBictmonth() && !"".equals(mrtestdto.getBictmonth())){
                 cell_one = new PdfPCell(new Paragraph(PDFLables.MONTHS+"    "+mrtestdto.getBictmonth(), Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);
                }else{
                  cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);
               }

                }


                cell_one = new PdfPCell(new Paragraph(PDFLables.IQ, Font_Bold));
                cell_one.setBorder(0);
                table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(String.valueOf(Math.round(mrtestdto.getBictiq())), Font_Bold));
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


       public PdfPTable getDisplayHeaderRow(String[] checkingvalues,PdfPTable table,Font Font_Bold,PdfPCell cell_one) throws IOException {
        try {
            if (null != checkingvalues) {
                if (checkingvalues.length > 0) {
                    for (String checkvalue : checkingvalues) {
                        cell_one = new PdfPCell(new Paragraph(checkvalue, Font_Bold));
                        cell_one.setBorder(0);
                        table.addCell(cell_one);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }


        public PdfPTable creatingBKTPdfCells(PdfPTable tempTable, Font Font_Bold, Font Font_Normal, PdfPCell cell_one,
            int defaultValue, int[][] bktvalues, String[] lables) throws IOException {

        try {
            if (null != bktvalues) {
                if (bktvalues.length > 0) {
                    int i = 0;
                    for (int[] bktlist : bktvalues) {
                        i++;
                       boolean displayFlag = getDisplayHeaderFlag(bktlist, defaultValue);

                       if(displayFlag){
                            cell_one = new PdfPCell(new Paragraph(lables[i - 1], Font_Normal));
                            cell_one.setBorder(0);
                            tempTable.addCell(cell_one);

                            for(int bktlistValue : bktlist){

                                if (bktlistValue > 0) {
                                    cell_one = new PdfPCell(new Paragraph("Yes", Font_Normal));
                                    cell_one.setBorder(0);
                                    tempTable.addCell(cell_one);
                                }else{
                                    cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                                    cell_one.setBorder(0);
                                    tempTable.addCell(cell_one);
                                }


                            }


                       }

                           
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tempTable;
    }


        public boolean getDisplayHeaderFlag(int[] checkingvalues, int value) throws IOException {
        try {
            if (null != checkingvalues) {
                if (checkingvalues.length > 0) {
                    for (int checkvalue : checkingvalues) {
                        if (checkvalue != value) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
