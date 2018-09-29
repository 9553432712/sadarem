/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.PhotoGalleryDTO;
import org.bf.disability.form.PhotoGalleryFrom;
import org.bf.disability.service.PhotoGalleryService;
import org.bf.disability.servicefactory.PhotoGalleryFactory;
import org.bf.disability.util.FileUtils;

/**
 *
 * @author 484898
 */
public class PhotoGalleryAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        PhotoGalleryService photoGalleryService = PhotoGalleryFactory.getPhotoGalleryImpl();
        PhotoGalleryDTO photoGalleryDTO = new PhotoGalleryDTO();
        ArrayList galleryEvents = new ArrayList();
        photoGalleryDTO.setUrl(getServlet().getServletContext().getRealPath("/"));
        galleryEvents = photoGalleryService.getPhotoGalleryEvents(ds);

        if (galleryEvents.size() > 0) {
            request.setAttribute("galleryEvents", galleryEvents);
        }

        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for getting the gallery events
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward getEvents(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        PhotoGalleryService photoGalleryService = PhotoGalleryFactory.getPhotoGalleryImpl();
        PhotoGalleryDTO photoGalleryDTO = new PhotoGalleryDTO();
        ArrayList galleryDetails = new ArrayList();
        photoGalleryDTO.setUrl(getServlet().getServletContext().getRealPath("/"));
        photoGalleryDTO.setConditionFlag(request.getParameter("eventFlag"));
        galleryDetails = photoGalleryService.getPhotoGalleryDetails(ds, photoGalleryDTO);
        if (galleryDetails.size() > 0) {
            request.setAttribute("galleryDetails", galleryDetails);
        }
        unspecified(mapping, form, request, response);

        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for saving the uploaded photo details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        PhotoGalleryService photoGalleryService = PhotoGalleryFactory.getPhotoGalleryImpl();
        PhotoGalleryFrom photoGalleryForm = (PhotoGalleryFrom) form;
        PhotoGalleryDTO photoGalleryDTO = new PhotoGalleryDTO();
        BeanUtils.copyProperties(photoGalleryDTO, photoGalleryForm);
        photoGalleryDTO.setUrl(getServlet().getServletContext().getRealPath("/"));
        String insertStatus = null;
        List imageList = null;
        List listImg = photoGalleryForm.getUploadPhoto();
        if (listImg != null) {
            imageList = new ArrayList();
            for (int j = 0; j < listImg.size(); j++) {
                if (listImg.get(j) != null) {
                    imageList.add(listImg.get(j));
                }
            }
            photoGalleryDTO.setImageList(imageList);
        }
        insertStatus = photoGalleryService.saveDetails(ds, photoGalleryDTO, request);
        if (insertStatus != null) {
            request.setAttribute("msg", insertStatus);
            photoGalleryForm.setDescription("");
            photoGalleryForm.setEvent("");
            photoGalleryForm.setNoOfPhotos("");
        } else {
            request.setAttribute("msg", "<font color=\"red\">Error in Uploading Photo</font>");
        }
        unspecified(mapping, form, request, response);

        return mapping.findForward(SUCCESS);
    }

    /**
     * Giving the facility for uploading the photos based on count
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getPhotoCount(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        PhotoGalleryFrom photoGalleryForm = (PhotoGalleryFrom) form;
        request.setAttribute("totalPhotos", photoGalleryForm.getNoOfPhotos());
        photoGalleryForm.setEvent(photoGalleryForm.getEvent());
        photoGalleryForm.setDescription(photoGalleryForm.getDescription());
        unspecified(mapping, form, request, response);

        return mapping.findForward(SUCCESS);
    }

    /**
     * Giving the facility for Inactive the photos
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward inactiveRecord(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        PhotoGalleryService photoGalleryService = PhotoGalleryFactory.getPhotoGalleryImpl();
        PhotoGalleryDTO photoGalleryDTO = new PhotoGalleryDTO();
        String inactiveStatus = null;
        photoGalleryDTO.setRowId(request.getParameter("inactive"));
        inactiveStatus = photoGalleryService.inactiveRecord(ds, photoGalleryDTO);
        if (inactiveStatus != null && inactiveStatus.length() > 0) {
            request.setAttribute("msg", inactiveStatus);
            photoGalleryDTO.setEvent("");
            photoGalleryDTO.setDescription("");
        }
        unspecified(mapping, form, request, response);
        getEvents(mapping, form, request, response);

        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for getting teh photos for View
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getPhotos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        PhotoGalleryService photoGalleryService = PhotoGalleryFactory.getPhotoGalleryImpl();
        String photosString = null;

        photosString = photoGalleryService.getPhotoGalleryForEvents(ds,request.getParameter("event"));

        if (photosString != null) {
            if (request.getParameter("event") != null && request.getParameter("event").length() > 0 && !request.getParameter("event").contains("<html><body>")) {
                movePhotos(request.getParameter("event"), getServlet().getServletContext().getRealPath("/"));
                request.setAttribute("event", request.getParameter("event"));
                // photosString = photoGalleryService.getPhotoGalleryForEvents(request.getParameter("event"));

                if (photosString != null && photosString.length() > 0) {
                    request.setAttribute("photosString", photosString);
                }
            }
            //    } else {
            //      request.getRequestDispatcher("./500.jsp").forward(request, response);
            // }
            unspecified(mapping, form, request, response);
        } 
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is used for copy files for source folder to destination folder
     * @param fileName
     * @param url
     * @throws Exception
     */
    public boolean movePhotos(String file, String url) {
        boolean flag = true;

        File srcFolder = new File(FileUtils.filePath("PHOTOGALLERY") + file + "\\");
        File destFolder = new File(url + FileUtils.onlyPath("PHOTOGALLERYWEBPATH"));

        try {
            copyFolder(srcFolder, destFolder); // copy folder form local to webapp
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void copyFolder(File src, File dest)
            throws IOException {

        if (src.isDirectory()) {

            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile, destFile);
            }

        } else {
            //if file, then copy it
            //Use bytes stream to support all file types
            if (!dest.exists()) {
                InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dest);

                byte[] buffer = new byte[1024];

                int length;
                //copy the file content in bytes
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }

                in.close();
                out.close();
            }
        }
    }
}
