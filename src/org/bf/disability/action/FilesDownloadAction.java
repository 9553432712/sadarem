/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;

/**
 *
 * @author 707797
 */
public class FilesDownloadAction extends DispatchAction {

    /* forward name="success" path="" */
    String SUCCESS = "success";

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
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        FileInputStream fdest = null;
        String file = request.getRequestURI();

        if (request.getQueryString() != null) {
            file += '?' + request.getQueryString();

        }
        URL reconstructedURL = null;
        try {
            reconstructedURL = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), file);

        } catch (MalformedURLException ex) {
            Logger.getLogger(FilesDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = reconstructedURL.toString();

        String[] sURL = url.split("\\?");



        String fileName = sURL[1];

        File srcFolder = new File(CommonConstants.DOWNLOADS + fileName);
        File destFolder = new File(getServlet().getServletContext().getRealPath("/") + "Downloads");

        if (!srcFolder.exists()) {
            srcFolder.mkdir();
        }
        if (!destFolder.exists()) {
            destFolder.mkdir();

        }
//        destFolder.mkdir();

        File fileDetailsData = new File(destFolder + "\\" + fileName);
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(fileDetailsData);
        } catch (FileNotFoundException ex) {
            //file not found then executes
            try {
                copyFolder(srcFolder, destFolder, fileName);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
        fdest = new FileInputStream(new File(destFolder + "\\" + fileName));
        BufferedInputStream in = new BufferedInputStream(fdest);

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(FilesDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setHeader("Cache-Control", "public");
        response.setHeader("Pragma", "public");
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        byte[] buffer = new byte[4 * 1024];

        int data = 0;

        try {
            while ((data = in.read(buffer)) != -1) {
                out.write(buffer, 0, data);
            }
            out.flush();
        } catch (Exception e) {
            //System.exit(1);
        }

        return null;
    }

    public static void copyFolder(File src, File dest, String Name) throws FileNotFoundException {
        if (src.isDirectory()) {

            //list all the directory contents
            String files[] = src.list();
            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile, destFile, Name);
            }

        } else {
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
           OutputStream out = new FileOutputStream(dest + "\\" + Name);

            byte[] buffer = new byte[1024];
            int length = 0;
            try {
                //copy the file content in bytes
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
