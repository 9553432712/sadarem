/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.pdf;

import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.io.IOException;

/**
 *
 * @author 509864
 */
public class PDFCommonTemplate {



     public PdfPTable creatingPdfCell(PdfPTable tempTable, Font Font_Bold,Font Font_Normal,PdfPCell cell_one, String pdflableone,
            String pdflabletwo, String valueone, String valuetwo,String normalRange) throws IOException {

        try {
            cell_one = new PdfPCell(new Paragraph(pdflableone, Font_Normal));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);

            cell_one = new PdfPCell(new Paragraph(pdflabletwo, Font_Normal));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);

            if(!valueone.equals(normalRange)){
            cell_one = new PdfPCell(new Paragraph(valueone, Font_Bold));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);
            }else{
                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                cell_one.setBorder(0);
                tempTable.addCell(cell_one);
            }
            if(!valuetwo.equals(normalRange)){
            cell_one = new PdfPCell(new Paragraph(valuetwo, Font_Bold));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);
            }else{
                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                cell_one.setBorder(0);
                tempTable.addCell(cell_one);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tempTable;
    }

      public PdfPTable creatingPdfThreeCells(PdfPTable tempTable, Font Font_Bold,Font Font_Normal,PdfPCell cell_one, String pdflableone,
                                           String valueone, String normalRange,String Flag,String defaultOne,String defaultTwo) throws IOException {

        try {


            cell_one = new PdfPCell(new Paragraph(pdflableone, Font_Normal));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);


            if(null != valueone && !"".equals(valueone)
                    && defaultOne.equals(valueone)){
            cell_one = new PdfPCell(new Paragraph(Flag, Font_Bold));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);
            }else{
                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                cell_one.setBorder(0);
                tempTable.addCell(cell_one);
            }
            if(null != valueone && !"".equals(valueone)
                    && defaultTwo.equals(valueone)){
            cell_one = new PdfPCell(new Paragraph(Flag, Font_Bold));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);
            }else{
                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                cell_one.setBorder(0);
                tempTable.addCell(cell_one);
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tempTable;
    }

       public PdfPTable creatingAmputationPdfThreeCells(PdfPTable tempTable, Font Font_Bold, Font Font_Normal, PdfPCell cell_one,
            String Flag, int[][] right_left_values, String[] lables) throws IOException {

        try {


            if (null != right_left_values) {
                if (right_left_values.length > 0) {
                    int i = 0;
                    for (int[] right_left_value : right_left_values) {
                        i++;
                        if (right_left_value[0] > 0 || right_left_value[1] > 0) {

                            cell_one = new PdfPCell(new Paragraph(lables[i - 1], Font_Normal));
                            cell_one.setBorder(0);
                            tempTable.addCell(cell_one);

                            if (right_left_value[0] > 0) {
                                cell_one = new PdfPCell(new Paragraph(Flag, Font_Bold));
                                cell_one.setBorder(0);
                                tempTable.addCell(cell_one);
                            } else {
                                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                                cell_one.setBorder(0);
                                tempTable.addCell(cell_one);
                            }
                            if (right_left_value[1] > 0) {
                                cell_one = new PdfPCell(new Paragraph(Flag, Font_Bold));
                                cell_one.setBorder(0);
                                tempTable.addCell(cell_one);
                            } else {
                                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                                cell_one.setBorder(0);
                                tempTable.addCell(cell_one);
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



      public boolean getDisplayHeaderFlag(String[] checkingvalues, String value) throws IOException {
        try {
            if (null != checkingvalues) {
                if (checkingvalues.length > 0) {
                    for (String checkvalue : checkingvalues) {
                        if (null != checkvalue && !"".equals(checkvalue) && !checkvalue.equals(value)) {
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


       public int getDisplayHeaderRowCountMultiple(String[][] checkingvalues, String defaultValue) throws IOException {
        int i = 0;
        try {
            if (null != checkingvalues) {
                if (checkingvalues.length > 0) {
                    for (String[] checkvalue : checkingvalues) {
                        for (String checkvalueone : checkvalue) {
                            if (null != checkvalueone && !"".equals(checkvalueone) && !defaultValue.equals(checkvalueone)) {
                                i++;
                                break;
                            }

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

       public int getDisplayHeaderRowCount(String[] checkingvalues,String defaultValue) throws IOException {
           int i = 0;
        try {
            if (null != checkingvalues) {
                if (checkingvalues.length > 0) {
                    for (String checkvalue : checkingvalues) {
                        if (null != checkvalue && !"".equals(checkvalue) && !defaultValue.equals(checkvalue)) {
                            i++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

 public PdfPTable creatingPdfTwoCell(PdfPTable tempTable, Font Font_Bold,Font Font_Normal,PdfPCell cell_one, String pdflableone,
             String valueone, String flag) throws IOException {

        try {
            if(null != valueone && !"".equals(valueone) && !"0".equals(valueone)){
            cell_one = new PdfPCell(new Paragraph(pdflableone, Font_Normal));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);

            cell_one = new PdfPCell(new Paragraph(flag, Font_Bold));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tempTable;
    }

 public PdfPTable creatingMRThreeCells(PdfPTable tempTable, Font Font_Bold, Font Font_Normal, PdfPCell cell_one,
            String Flag, String[][] display_values) throws IOException {

        try {
             if (null != display_values) {
                if (display_values.length > 0) {
                    for (String[] display_value : display_values) {
                        for (String checkvalueone : display_value) {
                            if (null != display_value[2] && !"".equals(display_value[2])
                                    && "1".equals(display_value[2])) {
                                if ("1".equals(checkvalueone)) {
                                    cell_one = new PdfPCell(new Paragraph(Flag, Font_Bold));
                                    cell_one.setBorder(0);
                                    tempTable.addCell(cell_one);

                                } else {
                                    cell_one = new PdfPCell(new Paragraph(checkvalueone, Font_Normal));
                                    cell_one.setBorder(0);
                                    tempTable.addCell(cell_one);
                                }
                            } else {
                                break;
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
 
 
 
 public PdfPTable creatingMRMISICCells(PdfPTable tempTable, Font Font_Bold, Font Font_Normal, PdfPCell cell_one,
            String defaultValue, String[][] display_values) throws IOException {
        try {
             if (null != display_values) {
                if (display_values.length > 0) {
                    for (String[] display_value : display_values) {
                       String[] list = {display_value[1],display_value[2]};
                       boolean displayFlag = getDisplayHeaderFlag(list, defaultValue);
                       if(displayFlag){

                           cell_one = new PdfPCell(new Paragraph(display_value[0], Font_Normal));
                           cell_one.setBorder(0);
                           tempTable.addCell(cell_one);
                           if(null != display_value[1] && !"".equals(display_value[1])
                                    && !defaultValue.equals(display_value[1])){
                           cell_one = new PdfPCell(new Paragraph(display_value[1], Font_Bold));
                           cell_one.setBorder(0);
                           tempTable.addCell(cell_one);
                           }else{
                               cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                           cell_one.setBorder(0);
                           tempTable.addCell(cell_one);
                           }

                          if(null != display_value[2] && !"".equals(display_value[2])
                                    && !defaultValue.equals(display_value[2])){
                           cell_one = new PdfPCell(new Paragraph(display_value[2], Font_Bold));
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



       } catch (Exception e) {
            e.printStackTrace();
        }

        return tempTable;
    }



 public PdfPTable creatingMRALEXCCells(PdfPTable tempTable, Font Font_Bold, Font Font_Normal, PdfPCell cell_one,
            String defaultValue, String[][] display_values) throws IOException {
        try {
             if (null != display_values) {
                if (display_values.length > 0) {
                    for (String[] display_value : display_values) {
                       String[] list = {display_value[2],display_value[3],display_value[4]};
                       boolean displayFlag = getDisplayHeaderFlag(list, defaultValue);
                       if(displayFlag){

                           cell_one = new PdfPCell(new Paragraph(display_value[0], Font_Normal));
                           cell_one.setBorder(0);
                           tempTable.addCell(cell_one);

                           cell_one = new PdfPCell(new Paragraph(display_value[1], Font_Normal));
                           cell_one.setBorder(0);
                           tempTable.addCell(cell_one);


                           if(null != display_value[2] && !"".equals(display_value[2])
                                    && !defaultValue.equals(display_value[2])){
                           cell_one = new PdfPCell(new Paragraph(display_value[2], Font_Bold));
                           cell_one.setBorder(0);
                           tempTable.addCell(cell_one);
                           }else{
                               cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                           cell_one.setBorder(0);
                           tempTable.addCell(cell_one);
                           }

                          if(null != display_value[3] && !"".equals(display_value[3])
                                    && !defaultValue.equals(display_value[3])){
                           cell_one = new PdfPCell(new Paragraph(display_value[3], Font_Bold));
                           cell_one.setBorder(0);
                           tempTable.addCell(cell_one);
                           }else{
                               cell_one = new PdfPCell(new Paragraph("", Font_Normal));
                           cell_one.setBorder(0);
                           tempTable.addCell(cell_one);
                           }

                            if(null != display_value[4] && !"".equals(display_value[4])
                                    && !defaultValue.equals(display_value[4])){
                           cell_one = new PdfPCell(new Paragraph(display_value[4], Font_Bold));
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



       } catch (Exception e) {
            e.printStackTrace();
        }

        return tempTable;
    }


  public PdfPTable creatingNeedsCells(PdfPTable tempTable, Font Font_Bold, Font Font_Normal, PdfPCell cell_one,
            String Flag, String[][] display_values) throws IOException {

        try {
             if (null != display_values) {
                if (display_values.length > 0) {
                    for (String[] display_value : display_values) {
                            if (null != display_value[1] && !"".equals(display_value[1])) {

                                    cell_one = new PdfPCell(new Paragraph(display_value[0], Font_Normal));
                                    cell_one.setBorder(0);
                                    tempTable.addCell(cell_one);

                                    cell_one = new PdfPCell(new Paragraph(Flag, Font_Bold));
                                    cell_one.setBorder(0);
                                    tempTable.addCell(cell_one);

                            }
                    }
                }
            }



       } catch (Exception e) {
            e.printStackTrace();
        }

        return tempTable;
    }

   

}
