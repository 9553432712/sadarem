/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.struts.upload.FormFile;
import org.bf.disability.Constants.CertificateConstants;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;

import com.lowagie.text.pdf.codec.Base64;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author t_bapinaidu
 */
public class CommonDetails extends BaseAction
{

	String lStrQuery;
	
    // this method is used for getting DisabilityName based on DisabilityId.
    public String getDisabilityName(int disabilityId) {

        String disabilityName = null;
        try {
            switch (disabilityId) {
                case 1:
                    disabilityName = CertificateConstants.LOCOMOTOR_DISABILITYTYPE;
                    break;
                case 2:
                    disabilityName = CertificateConstants.VISUAL_DISABILITYTYPE;
                    break;
                case 3:
                    disabilityName = CertificateConstants.HEARING_DISABILITYTYPE;
                    break;
                case 4:
                    disabilityName = CertificateConstants.MENTALRETARDATION_DISABILITYTYPE;
                    break;
                case 5:
                    disabilityName = CertificateConstants.MENTALILLNESS_DISABILITYTYPE;
                    break;
                case 6:
                    disabilityName = CertificateConstants.MULTIPLE_DISABILITYTYPE;
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disabilityName;
    }

    // this method is used for getting DisabilityName based on DisabilityId.
    public String getConditionOfDisabilityName(int conditionId) {

        String conditionOfDisabilityName = null;
        try {
            switch (conditionId) {
                case 1:
                    conditionOfDisabilityName = CertificateConstants.PERMANENT_PROGRESSIVE;
                    break;
                case 2:
                    conditionOfDisabilityName = CertificateConstants.PERMANENT_NONPROGRESSIVE;
                    break;
                case 3:
                    conditionOfDisabilityName = CertificateConstants.TEMPORARY_NONPROGRESSIVE;
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conditionOfDisabilityName;
    }

// this method is used for getting Role id based on userId.
    public String getLoginDetails(DataSource ds, String userid) throws SADAREMDBException, SQLException {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String rowid = null;
        try {
            con = DBConnection.getConnection();
            
            String SQL = "select role_id from Login_Details with(nolock)  "
                    + "where UserName = ? and Status='Active';";
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                rowid = rs.getString("role_id");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

            throw new SADAREMDBException();
        } catch (Exception exception) {
            exception.printStackTrace();

            throw new SADAREMDBException();

        }
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return rowid;
    }

    public boolean campareRoleIds(String currentRoleId, String permisssionIds) {
        boolean operatorFlag = false;
        try {
            String[] roleIds = permisssionIds.split(",");
            for (int i = 0; i < roleIds.length; i++) {
                if (currentRoleId != null && !"".equals(currentRoleId)) {
                    if (currentRoleId.equals(roleIds[i])) {
                        operatorFlag = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return operatorFlag;
    }

    public String getGenderName(int genderId) throws Exception {

        String genderName = null;
        try {
            switch (genderId) {
                case 1:
                    genderName = CommonConstants.MALE;
                    break;
                case 2:
                    genderName = CommonConstants.FEMALE;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genderName;
    }

    public String getEducationName(int educationId) throws Exception {

        String educationname = null;
        try {
            switch (educationId) {
                case 1:
                    educationname = CommonConstants.EDUCATION_ILLETERATE;
                    break;
                case 2:
                    educationname = CommonConstants.EDUCATION_BELOW10TH;
                    break;
                case 3:
                    educationname = CommonConstants.EDUCATION_10THCLASS;
                    break;
                case 4:
                    educationname = CommonConstants.EDUCATION_INTERMEDIATE;
                    break;
                case 5:
                    educationname = CommonConstants.EDUCATION_DEPLOMOITI;
                    break;
                case 6:
                    educationname = CommonConstants.EDUCATION_GRADUATE;
                    break;
                case 7:
                    educationname = CommonConstants.EDUCATION_POSTGRADUATE;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return educationname;
    }

    public String getEmployementName(int employementId) throws Exception {

        String employementName = null;
        try {
            switch (employementId) {
                case 1:
                    employementName = CommonConstants.EMPLOYEEMENT_GOVT;
                    break;
                case 2:
                    employementName = CommonConstants.EMPLOYEEMENT_PRIVATE;
                    break;
                case 3:
                    employementName = CommonConstants.EMPLOYEEMENT_SELF_EMPLOYEED;
                    break;
                case 4:
                    employementName = CommonConstants.EMPLOYEEMENT_UN_EMPLOYEED;
                    break;
                case 5:
                    employementName = CommonConstants.EMPLOYEEMENT_WAGE_EMPLOYEE;
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employementName;
    }

    public String getMaritalName(int maritalId) throws Exception {

        String maritalName = null;
        try {
            switch (maritalId) {
                case 1:
                    maritalName = CommonConstants.MARITAL_MARRIED;
                    break;
                case 2:
                    maritalName = CommonConstants.MARITAL_UN_MARRIED;
                    break;
                case 3:
                    maritalName = CommonConstants.MARITAL_DIVORCEE;
                    break;
                case 4:
                    maritalName = CommonConstants.MARITAL_WIDOW;
                    break;
                case 5:
                    maritalName = CommonConstants.MARITAL_WIDOWER;
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maritalName;
    }

    public String getCasteName(int casteId) throws Exception {

        String casteName = null;
        try {
            switch (casteId) {
                case 1:
                    casteName = CommonConstants.CASTE_ST;
                    break;
                case 2:
                    casteName = CommonConstants.CASTE_SC;
                    break;
                case 3:
                    casteName = CommonConstants.CASTE_BC;
                    break;
                case 4:
                    casteName = CommonConstants.CASTE_OC;
                    break;
                case 5:
                    casteName = CommonConstants.CASTE_MINORITY;
                    break;
                case 6:
                    casteName = CommonConstants.CASTE_NA;
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return casteName;
    }

    public String getReligioinName(int religionId) throws Exception {

        String religionName = null;
        try {
            switch (religionId) {
                case 1:
                    religionName = CommonConstants.RELIGION_HINDU;
                    break;
                case 2:
                    religionName = CommonConstants.RELIGION_MUSLIM;
                    break;
                case 3:
                    religionName = CommonConstants.RELIGION_CHRISTIAN;
                    break;
                case 4:
                    religionName = CommonConstants.RELIGION_SIKH;
                    break;
                case 5:
                    religionName = CommonConstants.RELIGION_JAIN;
                    break;
                case 6:
                    religionName = CommonConstants.RELIGION_BUDHIST;
                    break;
                case 7:
                    religionName = CommonConstants.RELIGION_OTHERS;
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return religionName;
    }

    public String getRationTypeName(int rationCardId) throws Exception {

        String rationtypeName = null;
        try {
            switch (rationCardId) {
                case 1:
                    rationtypeName = CommonConstants.RATION_WHITE;
                    break;
                case 2:
                    rationtypeName = CommonConstants.RATION_PINK;
                    break;
                case 3:
                    rationtypeName = CommonConstants.RATION_ANTHYODAYA;
                    break;
                case 4:
                    rationtypeName = CommonConstants.RATION_ANNAPURNA;
                    break;
                case 5:
                    rationtypeName = CommonConstants.RATION_YELLOW;
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rationtypeName;
    }

    public String getRelationName(int relationId) throws Exception {

        String relationName = null;
        try {
            switch (relationId) {
                case 1:
                    relationName = CommonConstants.RELATION_FATHER;
                    break;
                case 2:
                    relationName = CommonConstants.RELATION_MOTHER;
                    break;
                case 3:
                    relationName = CommonConstants.RELATION_HUSBAND;
                    break;
                case 4:
                    relationName = CommonConstants.RELATION_GURDIAN;
                    break;
                case 5:
                    relationName = CommonConstants.RELATION_BROTHER;
                    break;
                case 6:
                    relationName = CommonConstants.RELATION_SISTER;
                    break;
                case 7:
                    relationName = CommonConstants.RELATION_WIFE;
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relationName;
    }

    // select subtype Name
    public String getSubTypeNames(String subTypeIds) throws Exception {
        String subTypeNametemp = null;
        List<String> subTypeNameList = null;
        String subTypeName = null;
        try {

            if (subTypeIds != null) {
                String[] subTypeIdsArray = subTypeIds.split(",");
                subTypeNameList = new ArrayList<String>();
                for (int i = 0; i < subTypeIdsArray.length; i++) {
                    int subTypeId = Integer.parseInt(subTypeIdsArray[i]);
                    switch (subTypeId) {
                        case 1:
                            subTypeNametemp = CommonConstants.A1_CEREBRAL_PALSY;
                            break;
                        case 2:
                            subTypeNametemp = CommonConstants.A2_PPRP;
                            break;
                        case 3:
                            subTypeNametemp = CommonConstants.A3_POST_HANSEN_DISEASE;
                            break;
                        case 4:
                            subTypeNametemp = CommonConstants.A4_MUSCULAR_DYSTROPHY;
                            break;
                        case 5:
                            subTypeNametemp = CommonConstants.A5_POST_TRUMANTIC_AMPUTATION;
                            break;
                        case 6:
                            subTypeNametemp = CommonConstants.A6_POST_TRUMANTIC_LIMBS;
                            break;
                        case 7:
                            subTypeNametemp = CommonConstants.A7_POST_TRUMANTIC_SPINE;
                            break;
                        case 8:
                            subTypeNametemp = CommonConstants.A8_POST_HEAD_INJURY;
                            break;
                        case 9:
                            subTypeNametemp = CommonConstants.A9_POST_BURN_INJURY;
                            break;
                        case 10:
                            subTypeNametemp = CommonConstants.A10_CEREBRO_VASCULAR_ACCIDENTS;
                            break;
                        case 11:
                            subTypeNametemp = CommonConstants.A11_DWARFISM;
                            break;
                        case 12:
                            subTypeNametemp = CommonConstants.A12_CONGENITAL_DEFOMITIES_LIMBS;
                            break;
                        case 13:
                            subTypeNametemp = CommonConstants.A13_CONGENITAL_DEFOMITIES_SPINE;
                            break;
                        case 14:
                            subTypeNametemp = CommonConstants.A14_CARDIO_PULMONARY_DISEASE;
                            break;
                        case 32:
                            subTypeNametemp = CommonConstants.A15_MUSCULAR_WEAKNESS;
                            break;
                        case 15:
                            subTypeNametemp = CommonConstants.A16_OTHERS;
                            break;
                        case 16:
                            subTypeNametemp = CommonConstants.COMPLETELOSS_VISION_BETTEREYE;
                            break;
                        case 17:
                            subTypeNametemp = CommonConstants.COMPLETELOSS_VISION_WORSEEYE;
                            break;
                        case 18:
                            subTypeNametemp = CommonConstants.LOW_VISION_BETTEREYE;
                            break;
                        case 19:
                            subTypeNametemp = CommonConstants.LOW_VISION_WORSEEYE;
                            break;
                        default:
                            break;

                    }
                    subTypeNameList.add(subTypeNametemp);
                }
                subTypeName = separatewithcamma(subTypeNameList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subTypeName;
    }

    //this method is used to select subsubtype Name's
    public String getSubSubTypeNames(String subSubTypeIds) throws Exception {
        String subSubTypeNametemp = null;
        List<String> subSubTypeNameList = null;
        String subSubTypeName = null;
        try {

            if (subSubTypeIds != null) {
                String[] subTypeIdsArray = subSubTypeIds.split(",");
                subSubTypeNameList = new ArrayList<String>();
                for (int i = 0; i < subTypeIdsArray.length; i++) {
                    int subTypeId = Integer.parseInt(subTypeIdsArray[i]);
                    switch (subTypeId) {
                        case 1:
                            subSubTypeNametemp = CommonConstants.PART_INVOLVED_RT_UL;
                            break;
                        case 2:
                            subSubTypeNametemp = CommonConstants.PART_INVOLVED_LT_UL;
                            break;
                        case 3:
                            subSubTypeNametemp = CommonConstants.PART_INVOLVED_BILL_UL;
                            break;
                        case 4:
                            subSubTypeNametemp = CommonConstants.PART_INVOLVED_RT_LL;
                            break;
                        case 5:
                            subSubTypeNametemp = CommonConstants.PART_INVOLVED_LT_LL;
                            break;
                        case 6:
                            subSubTypeNametemp = CommonConstants.PART_INVOLVED_BILL_LL;
                            break;
                        case 7:
                            subSubTypeNametemp = CommonConstants.PART_INVOLVED_SPINE;
                            break;
                        case 8:
                            subSubTypeNametemp = CommonConstants.PART_INVOLVED_TRUNK;
                            break;
                        case 9:
                            subSubTypeNametemp = CommonConstants.PART_INVOLVED_ALLLIMBS;
                            break;
                        case 10:
                            subSubTypeNametemp = CommonConstants.PART_INVOLVED_WHOLE_BODY;
                            break;
                        case 11:
                            subSubTypeNametemp = CommonConstants.PART_INVOLVED_HIPS;
                            break;
                        default:
                            break;
                    }
                    subSubTypeNameList.add(subSubTypeNametemp);
                }
                subSubTypeName = separatewithcamma(subSubTypeNameList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subSubTypeName;
    }

// this method is used for separating with camma in list values
    public String separatewithcamma(List nameoftheList) {
        StringBuffer stringBuffer = null;
        try {
            if (nameoftheList != null && !nameoftheList.isEmpty()) {
                stringBuffer = new StringBuffer();
                for (int i = 0; i < nameoftheList.size(); i++) {
                    String causename = (String) nameoftheList.get(i);
                    stringBuffer.append(causename);
                    if (i + 1 < nameoftheList.size()) {
                        if (nameoftheList.get(i + 1) != null) {
                            stringBuffer.append(",");
                        }
                    } else {
                        stringBuffer.append(".");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

    public boolean checkDistrictFlag(String personcode, String districtId) throws Exception {
        boolean districtFlag = false;
        String personcodeDistrictId = null;
        try {
            if (personcode != null && !personcode.equalsIgnoreCase("null") && districtId != null) {
            	
            	CommonDAOImpl comObj = new CommonDAOImpl();
            	
            //	ArrayList ids = comObj.getGEODetailsbySADAREMID(personcode);
            	
            	HashMap GEODtls = new HashMap();
				GEODtls=comObj.getGEODetailsbySADAREMID(personcode);
				personcodeDistrictId	=	GEODtls.get("districtid").toString();
            	
            	
               // personcodeDistrictId = personcode.substring(0, 2); 
            }
            if (personcodeDistrictId!=null && districtId.equals(personcodeDistrictId)) {
                districtFlag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return districtFlag;
    }

    public boolean checkDistrictRationcardFlag(String rationcard, String districtId) throws Exception {
        boolean districtFlag = false;
        try {
            String personcodeDistrictId = rationcard.substring(3, 5);
            if (districtId.equals(personcodeDistrictId)) {
                districtFlag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return districtFlag;
    }

    public void copyPhotoDtoRelativePath(String personcode, String DistrictName, HttpServletRequest request, String url) throws Exception {


        String strDirectoy = null;
        try {

            File dir = new File(CommonConstants.SADAREM_DOCUMENTS_PATH+personcode+"\\Profile\\Profilepic.JPG");

            if (dir.exists()) {
                BufferedImage imagePath = ImageIO.read(dir);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(imagePath, "JPG", baos);
                byte[] bytesOut = baos.toByteArray();
                String fileName = "Profilepic.JPG";
                //String url = getServlet().getServletContext().getRealPath("/");
                int urlLength = url.length();
                int portLocal = request.getLocalPort();
                if (portLocal == 8084) 
                {
                    strDirectoy = url.substring(0, urlLength - 11) + "\\web\\DisabilityUITG\\uploadedphotos\\"+personcode ;
                } 
                else
                {
                    strDirectoy = url + "DisabilityUITG\\uploadedphotos\\" + personcode;
                }
                File directory = new File(strDirectoy);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                File fileToCreate = new File(strDirectoy, fileName);
                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                fileOutStream.write(bytesOut);
                fileOutStream.flush();
                fileOutStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static boolean uploadingFile(FormFile file, String uploadPath, String filename) {
        boolean flag = false;
        try {

            //if file folder not exits create file floder
            File dest = new File(uploadPath);
            if (!dest.exists()) {
                dest.mkdirs();
            }

            InputStream stream = null;
            OutputStream bos = null;
            int bytesRead = 0;
            int fileSize = 0;
            byte[] buffer = null;
            stream = file.getInputStream();
            bos = new FileOutputStream(uploadPath + "/" + filename);
            fileSize = file.getFileSize();
            bytesRead = 0;
            buffer = new byte[fileSize];
            while ((bytesRead = stream.read(buffer, 0, fileSize)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();
            flag = true;

        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean uploadingAadharPhoto(String base64, String uploadPath, String filename) {
        boolean flag = false;
        try {

            //if file folder not exits create file floder
            File dest = new File(uploadPath);
            if (!dest.exists()) {
                dest.mkdirs();
            }
            byte[] bytearray = Base64.decode(base64);

            BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytearray));
            ImageIO.write(imag, "jpg", new File(uploadPath, filename));
//            InputStream stream = null;
//            OutputStream bos = null;
//            int bytesRead = 0;
//            int fileSize = 0;
//            byte[] buffer = null;
//            stream = file.getInputStream();
//            bos = new FileOutputStream(uploadPath + "/" + filename);
//            fileSize = file.getFileSize();
//            bytesRead = 0;
//            buffer = new byte[fileSize];
//            while ((bytesRead = stream.read(buffer, 0, fileSize)) != -1) {
//                bos.write(buffer, 0, bytesRead);
//            }
//            bos.close();
//            stream.close();
            flag = true;

        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public void copyDirectoryandFile(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            String[] children = sourceLocation.list();
            for (int i = 0; i < children.length; i++) {
                copyDirectoryandFile(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {
            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);
            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }

    public void delete(File file)
            throws IOException {

        if (file.isDirectory()) {
            //directory is empty, then delete it
            if (file.list().length == 0) {
                file.delete();
            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }
                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                }
            }
        } else {
            //if file, then delete it
            file.delete();

        }
    }

    public void download_zip_file(String filename, String files) {
        try {

            URL url = new URL(filename + "\\" + files);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("content-type", "binary/data");
            InputStream in = conn.getInputStream();

            FileOutputStream out = new FileOutputStream(filename + "\\" + files);

            byte[] b = new byte[1024];
            int count;
            while ((count = in.read(b)) > 0) {
                out.write(b, 0, count);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyDirectoryandFileAddress(File sourceLocation, File targetLocation) throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdirs();
            }
            String[] children = sourceLocation.list();
            for (int i = 0; i < children.length; i++) {
                copyDirectoryandFile(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {
            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);
            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }

    public static boolean uploadingFilePWD(FormFile file, String uploadPath, String filename) {
        boolean flag = false;
        try {
            InputStream stream = null;
            OutputStream bos = null;
            int bytesRead = 0;
            int fileSize = 0;
            byte[] buffer = null;
            stream = file.getInputStream();
            bos = new FileOutputStream(uploadPath + "/" + filename);
            fileSize = file.getFileSize();
            bytesRead = 0;
            buffer = new byte[fileSize];
            while ((bytesRead = stream.read(buffer, 0, fileSize)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean uploadingFileAddress(FormFile file, String uploadPath, String filename) {
        boolean flag = false;
        try {
            InputStream stream = null;
            OutputStream bos = null;
            int bytesRead = 0;
            int fileSize = 0;
            byte[] buffer = null;
            stream = file.getInputStream();
            bos = new FileOutputStream(uploadPath + "/" + filename);
            fileSize = file.getFileSize();
            bytesRead = 0;
            buffer = new byte[fileSize];
            while ((bytesRead = stream.read(buffer, 0, fileSize)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

//balu start
    public ArrayList splictHabitationCode(String habitationCode) throws Exception
    {
        ArrayList list = new ArrayList();
        try {
            if (habitationCode.length() == 14) {
                list.add(habitationCode.substring(0, 2));   //District      0
                list.add(habitationCode.substring(2, 5));   //Assemble      1
                list.add(habitationCode.substring(5, 7));   //Mandal        2
                list.add(habitationCode.substring(7, 10));  //Village       3
                list.add(habitationCode.substring(10, 12)); //Habitation    4
                list.add(habitationCode.substring(12, 14)); //Panchayat     5
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //balu end
    
    
    
    public ArrayList getSadaremDistDtls(String habitationCode)
    {
    	ArrayList list = new ArrayList();
    	try
    	{
    		ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
    		
			lStrQuery = "select District_ID,Assembly_ID,Mandal_id,Village_ID	,Habitation_ID,panchayat_id\n"+	
		    	       "from tblHabitation_Details with(nolock) where Habitation_Code=? ";
		    	
			tempList = new ArrayList();
			tempList.add("I");
			tempList.add(habitationCode);
			paramList.add(tempList);
			
			list = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			
			if(list.size()>0)
			{
				list = (ArrayList)list.get(0);
			}
		} 
    	catch (Exception e) 
    	{
		 
			e.printStackTrace();
		}
    	return list;
    }

}
