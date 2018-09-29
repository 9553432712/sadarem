/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.PhotoGalleryDTO;
import org.bf.disability.util.FileUtils;

import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 484898
 */
public class PhotoGalleryDAO {

    /**
     * This method is for saving the details of photos
     * @param photoGalleryDTO
     * @param request
     * @return int
     * @throws SADAREMDBException
     */
    public String saveDetails(DataSource ds,PhotoGalleryDTO photoGalleryDTO, HttpServletRequest request) throws SADAREMDBException {
        String insertStatus = null;
        int status = 0;
        boolean flag = false;
        String fileName = null;
        String extension = null;
        try {

            for (int k = 0; k < photoGalleryDTO.getFormFiles().size(); k++) {

                fileName = ((FormFile) photoGalleryDTO.getFormFiles().get(k)).getFileName();
                int dotPos = fileName.lastIndexOf(".");
                extension = fileName.substring(dotPos);
                // fileName = cCLAKeyContactsDTO.getUploadPhoto();
                long filesize = ((FormFile) photoGalleryDTO.getFormFiles().get(k)).getFileSize();
                long filesizeInKB = filesize / 1024;
                int count = StringUtils.countMatches(fileName, ".");
                if (((FormFile) photoGalleryDTO.getFormFiles().get(k)).getContentType().equalsIgnoreCase("image/pjpeg") || ((FormFile) photoGalleryDTO.getFormFiles().get(k)).getContentType().equalsIgnoreCase("image/jpeg")) {
                    if (count == 1) {
//                        if (filesizeInKB > 1 && filesizeInKB < 100) {
                            if (extension.equalsIgnoreCase(".jpg")) {
                                flag = uploadPhotos((FormFile) photoGalleryDTO.getFormFiles().get(k), photoGalleryDTO.getEvent(), request, photoGalleryDTO.getUrl());
                                if (flag) {
                                    status = DataAccess.updateStatementExecute("INSERT INTO PhotoGallery(Event,Description,UploadPhoto,CreatedDate,UpdatedDate) "
                                            + "VALUES('" + photoGalleryDTO.getEvent() + "','" + photoGalleryDTO.getDescription() + "','" + photoGalleryDTO.getFormFiles().get(k)
                                            + "',getDate(),getDate())");
                                    if (status != 0) {
                                        insertStatus = "<font color=\"green\">Photo Uploaded Successfully</font>";
                                    } else {
                                        insertStatus = "<font color=\"red\">Error :: Error in Upload Photo</font>";
                                    }
                                } else {
                                    insertStatus = "<font color=\"red\">Error :: Failed in Upload Image</font>";
                                    break;
                                }
                            } else {
                                insertStatus = "<font color=\"red\">Error :: File Extension should be .jpg only </font>";
                                break;
                            }
//                        } else {
//                            insertStatus = "<font color=\"red\">Error :: File Size Should be more then 1 KB and less then 100 KB </font>";
//                            break;
//                        }
                    } else {
                        insertStatus = "<font color=\"red\">Error :: For Security Reasons Please Remove .(dots) in file Name</font>";
                        break;
                    }
                } else {
                    insertStatus = "<font color=\"red\">Error :: Content Type Should be JPG Only</font>";
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertStatus;
    }

    /**
     * This method is for updating the record in a table when uploaded file is empty
     * @param uploadGosDTO
     * @return int
     * @throws SADAREMDBException
     */
    public int updateRecordWhenUploadFails(DataSource ds,PhotoGalleryDTO photoGalleryDTO) throws SADAREMDBException {
        int updateStatus = 0;

        try {

            updateStatus = DataAccess.updateStatementExecute("update PhotoGallery set status='Inactive' where Event='" + photoGalleryDTO.getEvent() + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateStatus;
    }

    /**
     * Method for getting the photoGalleryDetails
     * @return ArrayList
     * @throws SADAREMDBException
     */
    public ArrayList getPhotoGalleryDetails(DataSource ds,PhotoGalleryDTO photoGalleryDto) throws SADAREMDBException {
        ArrayList galleryDetails = new ArrayList();
        HashMap details = null;
        Connection con = null;
        StringBuffer sql = new StringBuffer();
        ResultSet rs = null;
        Statement st = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql.append("select RowId,event,description,uploadphoto from dbo.PhotoGallery where event = '" + photoGalleryDto.getConditionFlag() + "' and status='Active' order by rowid");

            rs = st.executeQuery(sql.toString());
            if (rs != null) {
                while (rs.next()) {
                    details = new HashMap();
                    details.put("RowId", rs.getString(1));
                    details.put("event", rs.getString(2));
                    details.put("description", rs.getString(3));
                    copyPhotoRelativePath(rs.getString(2), rs.getString(4), photoGalleryDto.getUrl());
                    details.put("uploadphoto", rs.getString(4));
                    galleryDetails.add(details);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                }
                if (st != null) {
                    st.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return galleryDetails;
    }

    /**
     * Method for getting the photoGallery events
     * @return ArrayList
     * @throws SADAREMDBException
     */
    public ArrayList getPhotoGalleryEvents(DataSource ds)
    {
        ArrayList galleryDetails = new ArrayList();
        try
        {
            galleryDetails = DataAccess.pickDataByPrepareStmtArrayListHashMap("select distinct event from dbo.PhotoGallery where status='Active'", new ArrayList());        	
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return galleryDetails;
    }

    /**
     * Method for getting the photos for particular event
     * @return String
     * @throws SADAREMDBException
     */
    public String getPhotoGalleryForEvents(DataSource ds,String event) throws SADAREMDBException {
        String photoString = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement("select uploadPhoto from dbo.PhotoGallery where event=? and status='Active' order by rowid");
            pstmt.setString(1, event);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    if (photoString != null) {

                        photoString = photoString + ",'./PhotoGallery/" + rs.getString(1) + "'";

                    } else {

                        photoString = "'./PhotoGallery/" + rs.getString(1) + "'";

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    DBConnection.closeConnection(con);
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return photoString;
    }

    /**
     * This method is for inactive the photos
     * @param photoGalleryDTO
     * @return String
     * @throws SADAREMDBException
     */
    public String inactiveRecord(DataSource ds,PhotoGalleryDTO photoGalleryDTO) throws SADAREMDBException, SQLException {
        String inactiveStatus = null;
        int status = 0;
        status = DataAccess.updateStatementExecute("update PhotoGallery set Status='Inactive' where rowid='" + photoGalleryDTO.getRowId() + "' and status='Active'");
        if (status != 0) {
            inactiveStatus = "<font color=\"green\">Selected Photo Deleted Successfully</font>";
        } else {
            inactiveStatus = "<font color=\"red\">Error :: Error While Photo Deletion</font>";
        }
        return inactiveStatus;
    }

    /**
     * This method is for upload the ccla gos
     * @param uploadFileName
     * @param goNumber
     * @param request
     * @param url
     * @return
     */
    public boolean uploadPhotos(FormFile uploadFileName, String name, HttpServletRequest request, String url) {
        String extension = null;
        String strDirectoytemp = null;
        boolean flag = false;
        try {

            String fileName = uploadFileName.getFileName();
            int dotPos = fileName.lastIndexOf(".");
            extension = fileName.substring(dotPos);
            //   fileName = fileName;
            strDirectoytemp = FileUtils.filePath("PHOTOGALLERY") + name + "\\";

            if (strDirectoytemp != null && !"".equals(strDirectoytemp) && strDirectoytemp.length() > 0) { // If directory is not exists it will create
                File directorytemp = new File(strDirectoytemp);
                if (!directorytemp.exists()) {
                    directorytemp.mkdirs();
                }

                File fileToCreatetemp = new File(strDirectoytemp, fileName); // Copy the file into directory
                FileOutputStream fileOutStreamtemp = new FileOutputStream(fileToCreatetemp); // Write the file content into buffer

                if (uploadFileName.getFileSize() > 0) {
                    fileOutStreamtemp.write(uploadFileName.getFileData());
                    fileOutStreamtemp.flush();
                    fileOutStreamtemp.close();
                    flag = true;
                } else {
                    flag = false;
                }
            } else {
                flag = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void copyPhotoRelativePath(String event, String fileName, String url) throws Exception {
        String strDirectoy = null;
        try {

            File dir = new File(FileUtils.filePath("PHOTOGALLERY") + event + "\\" + fileName);

            if (dir.exists()) {
                BufferedImage imagePath = ImageIO.read(dir);
                if (imagePath != null && !imagePath.equals("")) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(imagePath, "jpg", baos);
                    byte[] bytesOut = baos.toByteArray();
                    String file = fileName;
                    strDirectoy = url + FileUtils.onlyPath("PHOTOGALLERYWEBPATH");
                    File directory = new File(strDirectoy);

                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    File fileToCreate = new File(strDirectoy, file);
                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                    fileOutStream.write(bytesOut);
                    fileOutStream.flush();
                    fileOutStream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
