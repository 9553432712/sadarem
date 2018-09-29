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
import org.bf.disability.dto.UpperExtrimityDto;

/**
 *
 * @author 509864
 */
public class PDFUpperExtremity {

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
            UpperExtrimityDto upperExtrimitydto = (UpperExtrimityDto) request.getAttribute("sectionObject");
            Font_Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font_Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font_Bold_Color = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(139, 69, 19));
            Font_Section_heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(139, 69, 19));

            float[] upper_widths = {0.20f, 0.30f, 0.10f, 0.20f, 0.20f};
            PdfPCell cell_one = new PdfPCell(new Paragraph(""));
            PdfPTable upper_table = new PdfPTable(upper_widths);

            cell_one = new PdfPCell(new Paragraph(PDFLables.ARM_COMPONENT, Font_Bold));
            cell_one.setBorder(0);
            cell_one.setColspan(5);
            upper_table.addCell(cell_one);

            if ((null != upperExtrimitydto.getRomright() && !"".equals(upperExtrimitydto.getRomright()) && !"0".equals(upperExtrimitydto.getRomright()))
                    || (null != upperExtrimitydto.getRomleft() && !"".equals(upperExtrimitydto.getRomleft()) && !"0".equals(upperExtrimitydto.getRomleft()))) {
                cell_one = new PdfPCell(new Paragraph(PDFLables.ACTIVE_RANGE_OFMOTION, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(5);
                upper_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.JOINT, Font_Bold));
                cell_one.setBorder(0);
                upper_table.addCell(cell_one);

                 upper_table = creatingPdfCell(upper_table,Font_Bold,Font_Bold, cell_one, PDFLables.COMPONENT,
                                PDFLables.NORMAL_RANGE, PDFLables.RIGHT, PDFLables.LEFT,"");




                String[] romsf = {upperExtrimitydto.getRom_sf_right(), upperExtrimitydto.getRom_sf_left()};
                boolean romsfFlag = getDisplayHeaderFlag(romsf, "220");

                String[] romsa = {upperExtrimitydto.getRom_sa_right(), upperExtrimitydto.getRom_sa_left()};
                boolean romsaFlag = getDisplayHeaderFlag(romsa, "180");

                String[] romsr = {upperExtrimitydto.getRom_sr_right(), upperExtrimitydto.getRom_sr_left()};
                boolean romsrFlag = getDisplayHeaderFlag(romsr, "180");

                if (romsaFlag || romsfFlag || romsrFlag) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.SHOULDER_JOINT, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(3);
                    upper_table.addCell(cell_one);

                    if (romsfFlag) {
                        upper_table = creatingPdfCell(upper_table,Font_Bold,Font_Normal, cell_one, PDFLables.FLEXION_EXTENSION,
                                PDFLables.RANGE_0_220, upperExtrimitydto.getRom_sf_right(), upperExtrimitydto.getRom_sf_left(),"220");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");

                    }

                    if (romsaFlag) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, PDFLables.ABDUCTION_ADDUCTION,
                                PDFLables.RANGE_0_180, upperExtrimitydto.getRom_sa_right(), upperExtrimitydto.getRom_sa_left(),"180");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");

                    }

                    if (romsrFlag) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, PDFLables.ROTATION,
                                PDFLables.RANGE_0_180, upperExtrimitydto.getRom_sr_right(), upperExtrimitydto.getRom_sr_left(),"180");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");

                    }

                }


                String[] romef = {upperExtrimitydto.getRom_ef_right(), upperExtrimitydto.getRom_ef_left()}; 
                boolean romefFlag = getDisplayHeaderFlag(romef, "150");

                String[] romes = {upperExtrimitydto.getRom_es_right(), upperExtrimitydto.getRom_es_left()};
                boolean romesFlag = getDisplayHeaderFlag(romes, "180");

                if (romefFlag || romesFlag) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.ELBOW_JOINT, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(2);
                    upper_table.addCell(cell_one);

                    if (romefFlag) {
                        upper_table = creatingPdfCell(upper_table,Font_Bold,Font_Normal, cell_one, PDFLables.FLEXION_EXTENSION,
                                PDFLables.RANGE_0_150, upperExtrimitydto.getRom_ef_right(), upperExtrimitydto.getRom_ef_left(),"150");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");

                    }
                    if (romesFlag) {
                        upper_table = creatingPdfCell(upper_table,Font_Bold,Font_Normal, cell_one, PDFLables.SUPINATION_PRONATION,
                                PDFLables.RANGE_0_180, upperExtrimitydto.getRom_es_right(), upperExtrimitydto.getRom_es_left(),"180");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");

                    }

                }

                String[] romwd = {upperExtrimitydto.getRom_wd_right(), upperExtrimitydto.getRom_wd_left()};
                boolean romwdFlag = getDisplayHeaderFlag(romwd, "160");

                String[] romwr = {upperExtrimitydto.getRom_wr_right(), upperExtrimitydto.getRom_wr_left()};
                boolean romwrFlag = getDisplayHeaderFlag(romwr, "55");

                if (romwdFlag || romwrFlag) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.WRIST_JOINT, Font_Normal));
                    cell_one.setBorder(0);
                     cell_one.setRowspan(2);
                    upper_table.addCell(cell_one);

                    if (romwdFlag) {
                        upper_table = creatingPdfCell(upper_table,Font_Bold,Font_Normal, cell_one, PDFLables.FLEXION_EXTENSION,
                                PDFLables.RANGE_0_160, upperExtrimitydto.getRom_wd_right(), upperExtrimitydto.getRom_wd_left(),"160");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");

                    }
                    if (romwrFlag) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal, cell_one, PDFLables.SUPINATION_PRONATION,
                                PDFLables.RANGE_0_55, upperExtrimitydto.getRom_wr_right(), upperExtrimitydto.getRom_wr_left(),"55");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");

                    }

                }

            }

            if ((null != upperExtrimitydto.getMsright() && !"".equals(upperExtrimitydto.getMsright()) && !"0".equals(upperExtrimitydto.getMsright()))
                    || (null != upperExtrimitydto.getMsleft() && !"".equals(upperExtrimitydto.getMsleft())) && !"0".equals(upperExtrimitydto.getMsleft())) {

                cell_one = new PdfPCell(new Paragraph(PDFLables.MUSCLE_STRENGTH, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(5);
                upper_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.JOINT, Font_Bold));
                cell_one.setBorder(0);
                upper_table.addCell(cell_one);

                 upper_table = creatingPdfCell(upper_table,Font_Bold,Font_Bold, cell_one, PDFLables.COMPONENT,
                                PDFLables.NORMAL_MUSCLE_GRADE, PDFLables.RIGHT, PDFLables.LEFT,"");


                String[] mssj = {upperExtrimitydto.getMs_sf_right(), upperExtrimitydto.getMs_sf_left(),
                                 upperExtrimitydto.getMs_se_right(),upperExtrimitydto.getMs_se_left(),
                                 upperExtrimitydto.getMs_sab_right(),upperExtrimitydto.getMs_sab_left(),
                                 upperExtrimitydto.getMs_sad_right(),upperExtrimitydto.getMs_sad_left(),
                                 upperExtrimitydto.getMs_sext_right(),upperExtrimitydto.getMs_sext_left(),
                                 upperExtrimitydto.getMs_sint_right(),upperExtrimitydto.getMs_sint_left()};
                boolean mssjFlag = getDisplayHeaderFlag(mssj, "5");
                if(mssjFlag){
                    cell_one = new PdfPCell(new Paragraph(PDFLables.SHOULDER_JOINT, Font_Normal));
                    cell_one.setBorder(0);
                     cell_one.setRowspan(6);
                    upper_table.addCell(cell_one);

                    String[] mssf = {upperExtrimitydto.getMs_sf_right(), upperExtrimitydto.getMs_sf_left()};
                    if (getDisplayHeaderFlag(mssf, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.FLEXION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_sf_right(), upperExtrimitydto.getMs_sf_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }
                     String[] msse = {upperExtrimitydto.getMs_se_right(),upperExtrimitydto.getMs_se_left()};
                    if (getDisplayHeaderFlag(msse, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.EXTENSION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_se_right(), upperExtrimitydto.getMs_se_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }

                      String[] mssab = {upperExtrimitydto.getMs_sab_right(),upperExtrimitydto.getMs_sab_left()};
                    if (getDisplayHeaderFlag(mssab, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.ABDUCTION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_sab_right(), upperExtrimitydto.getMs_sab_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }

                       String[] mssad = {upperExtrimitydto.getMs_sad_right(),upperExtrimitydto.getMs_sad_left()};
                    if (getDisplayHeaderFlag(mssad, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.ADDUCTION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_sad_right(), upperExtrimitydto.getMs_sad_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }

                        String[] mssext = {upperExtrimitydto.getMs_sext_right(),upperExtrimitydto.getMs_sext_left()};
                    if (getDisplayHeaderFlag(mssext, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.EXT_ROTATION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_sext_right(), upperExtrimitydto.getMs_sext_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }

                         String[] mssint = {upperExtrimitydto.getMs_sint_right(),upperExtrimitydto.getMs_sint_left()};
                    if (getDisplayHeaderFlag(mssint, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.INT_ROTATION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_sint_right(), upperExtrimitydto.getMs_sint_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }

                }


                String[] msej = {upperExtrimitydto.getMs_ef_right(), upperExtrimitydto.getMs_ef_left(),
                                 upperExtrimitydto.getMs_ee_right(),upperExtrimitydto.getMs_ee_left(),
                                 upperExtrimitydto.getMs_ep_right(),upperExtrimitydto.getMs_ep_left(),
                                 upperExtrimitydto.getMs_es_right(),upperExtrimitydto.getMs_es_left()};
                boolean msejFlag = getDisplayHeaderFlag(msej, "5");
                if(msejFlag){

                    cell_one = new PdfPCell(new Paragraph(PDFLables.ELBOW_JOINT, Font_Normal));
                    cell_one.setBorder(0);
                     cell_one.setRowspan(4);
                    upper_table.addCell(cell_one);

                    String[] msef = {upperExtrimitydto.getMs_ef_right(), upperExtrimitydto.getMs_ef_left()};
                    if (getDisplayHeaderFlag(msef, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.FLEXION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_ef_right(), upperExtrimitydto.getMs_ef_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }

                     String[] msee = {upperExtrimitydto.getMs_ee_right(),upperExtrimitydto.getMs_ee_left()};
                    if (getDisplayHeaderFlag(msee, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.EXTENSION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_ee_right(),upperExtrimitydto.getMs_ee_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }
                     String[] msep = {upperExtrimitydto.getMs_ep_right(),upperExtrimitydto.getMs_ep_left()};
                    if (getDisplayHeaderFlag(msep, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.PRONATION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_ep_right(),upperExtrimitydto.getMs_ep_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }
                     String[] mses = {upperExtrimitydto.getMs_es_right(),upperExtrimitydto.getMs_es_left()};
                    if (getDisplayHeaderFlag(mses, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.SUPINATION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_es_right(),upperExtrimitydto.getMs_es_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }

                }



                String[] mswj = {upperExtrimitydto.getMs_wd_right(), upperExtrimitydto.getMs_wd_left(),
                    upperExtrimitydto.getMs_wp_right(), upperExtrimitydto.getMs_wp_left(),
                    upperExtrimitydto.getMs_wr_right(), upperExtrimitydto.getMs_wr_left(),
                    upperExtrimitydto.getMs_wu_right(), upperExtrimitydto.getMs_wu_left()};
                boolean mswjFlag = getDisplayHeaderFlag(mswj, "5");
                if (mswjFlag) {

                    cell_one = new PdfPCell(new Paragraph(PDFLables.WRIST_JOINT, Font_Normal));
                    cell_one.setBorder(0);
                     cell_one.setRowspan(4);
                    upper_table.addCell(cell_one);

                    String[] mswd = {upperExtrimitydto.getMs_wd_right(), upperExtrimitydto.getMs_wd_left()};
                    if (getDisplayHeaderFlag(mswd, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.DORSIFLEXION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_wd_right(), upperExtrimitydto.getMs_wd_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }

                    String[] mswp = {upperExtrimitydto.getMs_wp_right(), upperExtrimitydto.getMs_wp_left()};
                    if (getDisplayHeaderFlag(mswp, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.PALMAR_FLEXION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_wp_right(), upperExtrimitydto.getMs_wp_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }
                    String[] mswr = {upperExtrimitydto.getMs_wr_right(), upperExtrimitydto.getMs_wr_left()};
                    if (getDisplayHeaderFlag(mswr, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.RADIAL_DEVIATION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_wr_right(), upperExtrimitydto.getMs_wr_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }
                    String[] mswu = {upperExtrimitydto.getMs_wu_right(), upperExtrimitydto.getMs_wu_left()};
                    if (getDisplayHeaderFlag(mswu, "5")) {
                        upper_table = creatingPdfCell(upper_table, Font_Bold, Font_Normal, cell_one, PDFLables.ULNAR_DEVIATION,
                                PDFLables.RANGE_0_5, upperExtrimitydto.getMs_wu_right(), upperExtrimitydto.getMs_wu_left(),"5");
                    }else{
                          upper_table = creatingPdfCell(upper_table, Font_Bold,Font_Normal,cell_one, "","",
                                "", "","");
                    }

                }



            }


            cell_one = new PdfPCell(upper_table);
            cell_one.setBorder(0);
            cell_one.setColspan(2);
            table.addCell(cell_one);
           

             float[] upper_coordinate_activites_widths = {0.15f,0.70f,0.15f};
             PdfPTable upper_coo_table = new PdfPTable(upper_coordinate_activites_widths);


             if (null != upperExtrimitydto.getCoordinate() && !"".equals(upperExtrimitydto.getCoordinate())
                     && !"0".equals(upperExtrimitydto.getCoordinate())) {

                cell_one = new PdfPCell(new Paragraph(PDFLables.COORDINATED_ACTIVITIES, Font_Bold));
                cell_one.setBorder(0);
                cell_one.setColspan(3);
                upper_coo_table.addCell(cell_one);


                cell_one = new PdfPCell(new Paragraph(PDFLables.COORDINATEACTIVITIES, Font_Bold));
                cell_one.setBorder(0);
                upper_coo_table.addCell(cell_one);


                cell_one = new PdfPCell(new Paragraph(PDFLables.COMPONENT, Font_Bold));
                cell_one.setBorder(0);
                upper_coo_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.LOSS_OF_COORDINATE_ACTIVITIES, Font_Bold));
                cell_one.setBorder(0);
                upper_coo_table.addCell(cell_one);

                String[] componentslist={upperExtrimitydto.getCoordinate_lifting(),upperExtrimitydto.getCoordinate_touching(),
                                         upperExtrimitydto.getCoordinate_eating(),upperExtrimitydto.getCoordinate_combing(),
                                           upperExtrimitydto.getCoordinate_putting(),upperExtrimitydto.getCoordinate_ablution(),
                                         upperExtrimitydto.getCoordinate_buttoning(),upperExtrimitydto.getCoordinate_tie(),
                                         upperExtrimitydto.getCoordinate_writing(),upperExtrimitydto.getCoordinate_drinking()};
                int i = getDisplayHeaderRowCount(componentslist,"0");
                if (i>0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.VALUE_90, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(2*i);
                    upper_coo_table.addCell(cell_one);

                    if (null != upperExtrimitydto.getCoordinate_lifting() && !"".equals(upperExtrimitydto.getCoordinate_lifting())
                             && !"0".equals(upperExtrimitydto.getCoordinate_lifting())) {
                        upper_coo_table = creatingPdfCell(upper_coo_table, Font_Bold, Font_Normal, cell_one, PDFLables.LIFTING_OVERHEAD,
                                PDFLables.YES,"", "","0");
                    }

                     if (null != upperExtrimitydto.getCoordinate_touching() && !"".equals(upperExtrimitydto.getCoordinate_touching())
                         && !"0".equals(upperExtrimitydto.getCoordinate_touching())) {
                         upper_coo_table = creatingPdfCell(upper_coo_table, Font_Bold, Font_Normal, cell_one, PDFLables.TOUCHING_NOSE,
                                PDFLables.YES,"", "","0");
                    }
                    if (null != upperExtrimitydto.getCoordinate_eating() && !"".equals(upperExtrimitydto.getCoordinate_eating())
                              && !"0".equals(upperExtrimitydto.getCoordinate_eating())) {
                         upper_coo_table = creatingPdfCell(upper_coo_table, Font_Bold, Font_Normal, cell_one, PDFLables.EATING_INDIAN_STYLE,
                               PDFLables.YES,"", "","0");
                    }

                     if (null != upperExtrimitydto.getCoordinate_combing() && !"".equals(upperExtrimitydto.getCoordinate_combing())
                            && !"0".equals(upperExtrimitydto.getCoordinate_combing())) {
                         upper_coo_table = creatingPdfCell(upper_coo_table, Font_Bold, Font_Normal, cell_one, PDFLables.COMBING_PLATING,
                                PDFLables.YES,"", "","0");
                    }

                     if (null != upperExtrimitydto.getCoordinate_putting() && !"".equals(upperExtrimitydto.getCoordinate_putting())
                            && !"0".equals(upperExtrimitydto.getCoordinate_putting())) {
                         upper_coo_table = creatingPdfCell(upper_coo_table, Font_Bold, Font_Normal, cell_one, PDFLables.PUTTING_SHIRT,
                                PDFLables.YES,"", "","0");
                    }

                     if (null != upperExtrimitydto.getCoordinate_ablution() && !"".equals(upperExtrimitydto.getCoordinate_ablution())
                           && !"0".equals(upperExtrimitydto.getCoordinate_ablution())) {
                         upper_coo_table = creatingPdfCell(upper_coo_table, Font_Bold, Font_Normal, cell_one, PDFLables.ABLUTION_GLASS,
                                PDFLables.YES,"", "","0");
                    }

                     if (null != upperExtrimitydto.getCoordinate_buttoning() && !"".equals(upperExtrimitydto.getCoordinate_buttoning())
                               && !"0".equals(upperExtrimitydto.getCoordinate_buttoning())) {
                         upper_coo_table = creatingPdfCell(upper_coo_table, Font_Bold, Font_Normal, cell_one, PDFLables.BUTTONING,
                                PDFLables.YES,"", "","0");
                    }

                     if (null != upperExtrimitydto.getCoordinate_tie() && !"".equals(upperExtrimitydto.getCoordinate_tie())
                                && !"0".equals(upperExtrimitydto.getCoordinate_tie())) {
                         upper_coo_table = creatingPdfCell(upper_coo_table, Font_Bold, Font_Normal, cell_one, PDFLables.TIE_NARA_DHOTI,
                                PDFLables.YES,"", "","0");
                    }

                     if (null != upperExtrimitydto.getCoordinate_writing() && !"".equals(upperExtrimitydto.getCoordinate_writing())
                                  && !"0".equals(upperExtrimitydto.getCoordinate_writing())) {
                         upper_coo_table = creatingPdfCell(upper_coo_table, Font_Bold, Font_Normal, cell_one, PDFLables.WRITING,
                                PDFLables.YES,"", "","0");
                    }

                     if (null != upperExtrimitydto.getCoordinate_drinking() && !"".equals(upperExtrimitydto.getCoordinate_drinking())
                              && !"0".equals(upperExtrimitydto.getCoordinate_drinking())) {
                         upper_coo_table = creatingPdfCell(upper_coo_table, Font_Bold, Font_Normal, cell_one, PDFLables.DRINKING_GLASS_OF_WATER,
                               PDFLables.YES,"", "","0");
                    }

                }
             }
            cell_one = new PdfPCell(upper_coo_table);
            cell_one.setBorder(0);
            cell_one.setColspan(2);
            table.addCell(cell_one);

          

            if ((null != upperExtrimitydto.getPrehention() && !"".equals(upperExtrimitydto.getPrehention()) && !"0".equals(upperExtrimitydto.getPrehention()))
                    || (null != upperExtrimitydto.getSensation() && !"".equals(upperExtrimitydto.getSensation()) && !"0".equals(upperExtrimitydto.getSensation()))
                    || (null != upperExtrimitydto.getStrength() && !"".equals(upperExtrimitydto.getStrength()) && !"0".equals(upperExtrimitydto.getStrength()))) {

            PdfPTable Prehension_table = new PdfPTable(upper_widths);

            cell_one = new PdfPCell(new Paragraph(PDFLables.UPPEREXTREMITY_HAND_COMPONENT, Font_Bold));
            cell_one.setBorder(0);
            cell_one.setColspan(5);
            Prehension_table.addCell(cell_one);

            cell_one = new PdfPCell(new Paragraph(PDFLables.PREHENSION, Font_Bold));
            cell_one.setBorder(0);
            cell_one.setColspan(5);
            Prehension_table.addCell(cell_one);
            
               cell_one = new PdfPCell(new Paragraph(PDFLables.ACTIVITIES, Font_Bold));
                cell_one.setBorder(0);
                Prehension_table.addCell(cell_one);
                
                cell_one = new PdfPCell(new Paragraph(PDFLables.MOVEMENT, Font_Bold));
                cell_one.setBorder(0);
                Prehension_table.addCell(cell_one);
                
                cell_one = new PdfPCell(new Paragraph(PDFLables.NORMAL_VALUE, Font_Bold));
                cell_one.setBorder(0);
                Prehension_table.addCell(cell_one);
                
                cell_one = new PdfPCell(new Paragraph(PDFLables.RIGHT, Font_Bold));
                cell_one.setBorder(0);
                Prehension_table.addCell(cell_one);
                
                cell_one = new PdfPCell(new Paragraph(PDFLables.LEFT, Font_Bold));
                cell_one.setBorder(0);
                Prehension_table.addCell(cell_one);

               if(null != upperExtrimitydto.getPrehention() && !"".equals(upperExtrimitydto.getPrehention())){

                String[][] componentslist_right_left = {{upperExtrimitydto.getHand_opindex_right(),upperExtrimitydto.getHand_opindex_left()},
                                                        {upperExtrimitydto.getHand_opmiddle_right(),upperExtrimitydto.getHand_opmiddle_left()},
                                                        {upperExtrimitydto.getHand_opring_right(),upperExtrimitydto.getHand_opring_left()},
                                                        {upperExtrimitydto.getHand_oplittle_right(),upperExtrimitydto.getHand_oplittle_left()}};
                int j = getDisplayHeaderRowCountMultiple(componentslist_right_left,"0");
                 if (j>0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.OPPOSITION, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(j);
                    Prehension_table.addCell(cell_one);

                    String[] opind = {upperExtrimitydto.getHand_opindex_right(), upperExtrimitydto.getHand_opindex_left()};
                    if (getDisplayHeaderFlag(opind, "0")) {
                        Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.INDEX,
                                PDFLables.RANGE_2, upperExtrimitydto.getHand_opindex_right(), upperExtrimitydto.getHand_opindex_left(),"0",PDFLables.YES);
                    }

                     String[] opmi = {upperExtrimitydto.getHand_opmiddle_right(), upperExtrimitydto.getHand_opmiddle_left()};
                    if (getDisplayHeaderFlag(opmi, "0")) {
                        Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.MIDDLE,
                                PDFLables.RANGE_2,upperExtrimitydto.getHand_opmiddle_right(), upperExtrimitydto.getHand_opmiddle_left(),"0",PDFLables.YES);
                    }

                      String[] opri = {upperExtrimitydto.getHand_opring_right(), upperExtrimitydto.getHand_opring_left()};
                    if (getDisplayHeaderFlag(opri, "0")) {
                        Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.RING,
                                PDFLables.RANGE_2, upperExtrimitydto.getHand_opring_right(), upperExtrimitydto.getHand_opring_left(),"0",PDFLables.YES);
                    }

                       String[] opli = {upperExtrimitydto.getHand_oplittle_right(), upperExtrimitydto.getHand_oplittle_left()};
                    if (getDisplayHeaderFlag(opli, "0")) {
                        Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.LITTLE,
                                PDFLables.RANGE_2, upperExtrimitydto.getHand_oplittle_right(), upperExtrimitydto.getHand_oplittle_left(),"0",PDFLables.YES);
                    }


                 }

                 String[] lakey = {upperExtrimitydto.getHand_lakey_right(), upperExtrimitydto.getHand_lakey_left()};
                    if (getDisplayHeaderFlag(lakey, "0")) {
                   cell_one = new PdfPCell(new Paragraph(PDFLables.LATERAL_PINCH, Font_Normal));
                   cell_one.setBorder(0);
                   Prehension_table.addCell(cell_one);

                   Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.KEY_HOLDING,
                           PDFLables.RANGE_5, upperExtrimitydto.getHand_lakey_right(), upperExtrimitydto.getHand_lakey_left(), "0", PDFLables.YES);
               }


                 String[][] handcomponentlist = {{upperExtrimitydto.getHand_cylarge_right(),upperExtrimitydto.getHand_cylarge_left()},
                                                 {upperExtrimitydto.getHand_cysmall_right(),upperExtrimitydto.getHand_cysmall_left()}};
                 int k = getDisplayHeaderRowCountMultiple(handcomponentlist,"0");
                     if (k>0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.CYLINDRICAL_GRASP, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(k);
                    Prehension_table.addCell(cell_one);

                    String[] cryl = {upperExtrimitydto.getHand_cylarge_right(), upperExtrimitydto.getHand_cylarge_left()};
                    if (getDisplayHeaderFlag(cryl, "0")) {
                        Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.LARGE_OBJECT,
                                PDFLables.RANGE_3, upperExtrimitydto.getHand_cylarge_right(), upperExtrimitydto.getHand_cylarge_left(),"0",PDFLables.YES);
                    }

                     String[] crysm = {upperExtrimitydto.getHand_cysmall_right(), upperExtrimitydto.getHand_cysmall_left()};
                    if (getDisplayHeaderFlag(crysm, "0")) {
                        Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.SMALL_OBJECT,
                                PDFLables.RANGE_3,upperExtrimitydto.getHand_cysmall_right(), upperExtrimitydto.getHand_cysmall_left(),"0",PDFLables.YES);
                    }
                 }

                 String[][] sphericallist={{upperExtrimitydto.getHand_splarge_right(),upperExtrimitydto.getHand_splarge_left()},
                                          {upperExtrimitydto.getHand_spsmall_right(),upperExtrimitydto.getHand_spsmall_left()}};
                int l = getDisplayHeaderRowCountMultiple(sphericallist,"0");
                 if (l>0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.SPHERICAL_GRASP, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(l);
                    Prehension_table.addCell(cell_one);

                    String[] splarge = {upperExtrimitydto.getHand_splarge_right(), upperExtrimitydto.getHand_splarge_left()};
                    if (getDisplayHeaderFlag(splarge, "0")) {
                        Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.LARGE_OBJECT,
                                PDFLables.RANGE_3, upperExtrimitydto.getHand_splarge_right(), upperExtrimitydto.getHand_splarge_left(),"0",PDFLables.YES);
                    }

                     String[] spsmall = {upperExtrimitydto.getHand_spsmall_right(), upperExtrimitydto.getHand_spsmall_left()};
                    if (getDisplayHeaderFlag(spsmall, "0")) {
                        Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.SMALL_OBJECT,
                                PDFLables.RANGE_3,upperExtrimitydto.getHand_spsmall_right(), upperExtrimitydto.getHand_spsmall_left(),"0",PDFLables.YES);
                    }
                 }

                  String[] hook = {upperExtrimitydto.getHand_hook_right(), upperExtrimitydto.getHand_hook_left()};
                    if (getDisplayHeaderFlag(hook, "0")) {
                   cell_one = new PdfPCell(new Paragraph(PDFLables.HOOK_GRASP, Font_Normal));
                   cell_one.setBorder(0);
                   Prehension_table.addCell(cell_one);

                   Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.LIFTING_BAG,
                           PDFLables.RANGE_5, upperExtrimitydto.getHand_hook_right(), upperExtrimitydto.getHand_hook_left(), "0", PDFLables.YES);
               }
               }

                cell_one = new PdfPCell(Prehension_table);
                cell_one.setBorder(0);
                cell_one.setColspan(2);
                table.addCell(cell_one);

                if(null != upperExtrimitydto.getSensation() && !"".equals(upperExtrimitydto.getSensation())){

                    String[][] sensationlist = {{upperExtrimitydto.getHand_sethumb_right(),upperExtrimitydto.getHand_sethumb_left()},
                    {upperExtrimitydto.getHand_seindex_right(),upperExtrimitydto.getHand_seindex_left()},
                    {upperExtrimitydto.getHand_semiddle_right(),upperExtrimitydto.getHand_semiddle_left()},
                    {upperExtrimitydto.getHand_sering_right(),upperExtrimitydto.getHand_sering_left()},
                    {upperExtrimitydto.getHand_selittle_right(),upperExtrimitydto.getHand_selittle_left()}};
                    int sensation = getDisplayHeaderRowCountMultiple(sensationlist,"0");
                    if (sensation > 0) {
                        cell_one = new PdfPCell(new Paragraph(PDFLables.SENSATION, Font_Normal));
                        cell_one.setBorder(0);
                        cell_one.setRowspan(sensation);
                        Prehension_table.addCell(cell_one);

                        String[] thumb = {upperExtrimitydto.getHand_sethumb_right(), upperExtrimitydto.getHand_sethumb_left()};
                        if (getDisplayHeaderFlag(thumb, "0")) {
                            Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.THUMB_RAY,
                                    PDFLables.RANGE_9, upperExtrimitydto.getHand_sethumb_right(), upperExtrimitydto.getHand_sethumb_left(), "0", PDFLables.YES);
                        }

                       

                         String[] index = {upperExtrimitydto.getHand_seindex_right(), upperExtrimitydto.getHand_seindex_left()};
                        if (getDisplayHeaderFlag(index, "0")) {
                            Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.INDEX_FINGER,
                                    PDFLables.RANGE_6, upperExtrimitydto.getHand_seindex_right(), upperExtrimitydto.getHand_seindex_left(), "0", PDFLables.YES);
                        }

                          String[] middle = {upperExtrimitydto.getHand_semiddle_right(), upperExtrimitydto.getHand_semiddle_left()};
                        if (getDisplayHeaderFlag(middle, "0")) {
                            Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.MIDDLE_FINGER,
                                    PDFLables.RANGE_5, upperExtrimitydto.getHand_semiddle_right(), upperExtrimitydto.getHand_semiddle_left(), "0", PDFLables.YES);
                        }

                           String[] sering = {upperExtrimitydto.getHand_sering_right(), upperExtrimitydto.getHand_sering_left()};
                        if (getDisplayHeaderFlag(sering, "0")) {
                            Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.RING_FINGER,
                                    PDFLables.RANGE_5, upperExtrimitydto.getHand_sering_right(), upperExtrimitydto.getHand_sering_left(), "0", PDFLables.YES);
                        }

                            String[] selittle = {upperExtrimitydto.getHand_selittle_right(), upperExtrimitydto.getHand_selittle_left()};
                        if (getDisplayHeaderFlag(selittle, "0")) {
                            Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.LITTLE_FINGER,
                                    PDFLables.RANGE_5, upperExtrimitydto.getHand_selittle_right(), upperExtrimitydto.getHand_selittle_left(), "0", PDFLables.YES);
                        }
                    }

                }

                if(null != upperExtrimitydto.getStrength() && !"".equals(upperExtrimitydto.getStrength())){
                 String[][] strengthlist={{upperExtrimitydto.getHand_stgrip_right(),upperExtrimitydto.getHand_stgrip_left()},
                                         {upperExtrimitydto.getHand_stpinch_right(),upperExtrimitydto.getHand_stpinch_left()}
                                        };
                int k = getDisplayHeaderRowCountMultiple(strengthlist,"0");
                 if (k>0) {
                    cell_one = new PdfPCell(new Paragraph(PDFLables.STRENGTH, Font_Normal));
                    cell_one.setBorder(0);
                    cell_one.setRowspan(k);
                    Prehension_table.addCell(cell_one);

                    String[] cryl = {upperExtrimitydto.getHand_stgrip_right(), upperExtrimitydto.getHand_stgrip_left()};
                    if (getDisplayHeaderFlag(cryl, "0")) {
                        Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.GRIP_STRENGTH,
                                PDFLables.RANGE_20, upperExtrimitydto.getHand_stgrip_right(), upperExtrimitydto.getHand_stgrip_left(),"0",PDFLables.YES);
                    }

                     String[] crysm = {upperExtrimitydto.getHand_stpinch_right(), upperExtrimitydto.getHand_stpinch_left()};
                    if (getDisplayHeaderFlag(crysm, "0")) {
                        Prehension_table = creatingPdfCellTwo(Prehension_table, Font_Bold, Font_Normal, cell_one, PDFLables.PINCH_STRENGTH,
                                PDFLables.RANGE_10,upperExtrimitydto.getHand_stpinch_right(), upperExtrimitydto.getHand_stpinch_left(),"0",PDFLables.YES);
                    }
                 }
                }
           }


             if (null != upperExtrimitydto.getTotalextra() && !"".equals(upperExtrimitydto.getTotalextra())
                                                        && !"0.0".equals(upperExtrimitydto.getTotalextra())) {

                PdfPTable complications_table = new PdfPTable(widths);

                cell_one = new PdfPCell(new Paragraph(PDFLables.COMPLICATIONS, Font_Bold));
                cell_one.setBorder(0);
                complications_table.addCell(cell_one);

                cell_one = new PdfPCell(new Paragraph(PDFLables.PERCENTAGEG_OF_COMPLICATIONS, Font_Bold));
                cell_one.setBorder(0);
                complications_table.addCell(cell_one);

                 if (null != upperExtrimitydto.getCom_inflection() && !"".equals(upperExtrimitydto.getCom_inflection())
                         && !"0".equals(upperExtrimitydto.getCom_inflection())) {

                     cell_one = new PdfPCell(new Paragraph(PDFLables.INFECTIONS, Font_Normal));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(upperExtrimitydto.getCom_inflection(), Font_Bold));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                 }

                if (null != upperExtrimitydto.getCom_Deformity() && !"".equals(upperExtrimitydto.getCom_Deformity())
                         && !"0".equals(upperExtrimitydto.getCom_Deformity())) {

                     cell_one = new PdfPCell(new Paragraph(PDFLables.DEFORMITY, Font_Normal));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(upperExtrimitydto.getCom_Deformity(), Font_Bold));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                 }

                if (null != upperExtrimitydto.getCom_Misalignment() && !"".equals(upperExtrimitydto.getCom_Misalignment())
                         && !"0".equals(upperExtrimitydto.getCom_Misalignment())) {

                     cell_one = new PdfPCell(new Paragraph(PDFLables.MISALIGNMENT, Font_Normal));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(upperExtrimitydto.getCom_Misalignment(), Font_Bold));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                 }

                if (null != upperExtrimitydto.getCom_Contracture() && !"".equals(upperExtrimitydto.getCom_Contracture())
                         && !"0".equals(upperExtrimitydto.getCom_Contracture())) {

                     cell_one = new PdfPCell(new Paragraph(PDFLables.CONTRACTURE, Font_Normal));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(upperExtrimitydto.getCom_Contracture(), Font_Bold));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                 }

                if (null != upperExtrimitydto.getCom_LossofCosmeticappearance() && !"".equals(upperExtrimitydto.getCom_LossofCosmeticappearance())
                         && !"0".equals(upperExtrimitydto.getCom_LossofCosmeticappearance())) {

                     cell_one = new PdfPCell(new Paragraph(PDFLables.LOSS_COSMETIC_APPEARANCE, Font_Normal));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(upperExtrimitydto.getCom_LossofCosmeticappearance(), Font_Bold));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                 }

                if (null != upperExtrimitydto.getCom_domionantupperextremity() && !"".equals(upperExtrimitydto.getCom_domionantupperextremity())
                         && !"0".equals(upperExtrimitydto.getCom_domionantupperextremity())) {

                     cell_one = new PdfPCell(new Paragraph(PDFLables.WHETHER_DOMINANT_UPPEREXTREMITY, Font_Normal));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(PDFLables.YES, Font_Bold));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                 }

                 if (null != upperExtrimitydto.getInches() && !"".equals(upperExtrimitydto.getInches())
                         && !"0.0".equals(upperExtrimitydto.getInches())) {

                     cell_one = new PdfPCell(new Paragraph(PDFLables.SHORTENING, Font_Normal));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                     cell_one = new PdfPCell(new Paragraph(upperExtrimitydto.getInches(), Font_Bold));
                     cell_one.setBorder(0);
                     complications_table.addCell(cell_one);

                 }


            cell_one = new PdfPCell(complications_table);
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

  public PdfPTable creatingPdfCellTwo(PdfPTable tempTable, Font Font_Bold,Font Font_Normal,PdfPCell cell_one, String pdflableone,
            String pdflabletwo, String valueone, String valuetwo,String normalRange,String displayValue) throws IOException {

        try {
            cell_one = new PdfPCell(new Paragraph(pdflableone, Font_Normal));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);

            cell_one = new PdfPCell(new Paragraph(pdflabletwo, Font_Normal));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);

            if(!valueone.equals(normalRange)){
            cell_one = new PdfPCell(new Paragraph(displayValue, Font_Bold));
            cell_one.setBorder(0);
            tempTable.addCell(cell_one);
            }else{
                cell_one = new PdfPCell(new Paragraph("", Font_Bold));
                cell_one.setBorder(0);
                tempTable.addCell(cell_one);
            }
            if(!valuetwo.equals(normalRange)){
            cell_one = new PdfPCell(new Paragraph(displayValue, Font_Bold));
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



}
