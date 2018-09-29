/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.FileUploadDownloadServiceImpl;

/**
 *
 * @author 693461
 */
public class FileUploadDownloadServiceFactory {

    public static FileUploadDownloadServiceImpl fileUploadDownloadServiceImpl;

    public static FileUploadDownloadServiceImpl getFileUploadDownloadServiceImpl(){

       if(fileUploadDownloadServiceImpl ==null)
           fileUploadDownloadServiceImpl = new FileUploadDownloadServiceImpl();

     return fileUploadDownloadServiceImpl;
    }

}
