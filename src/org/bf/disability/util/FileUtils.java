/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.util;

import java.util.ResourceBundle;

/**
 *
 * @author 747577
 */
public class FileUtils {


    public static String filePath(String path) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.myapp.struts.ApplicationResource");
        String filePath = resourceBundle.getString("drive") + resourceBundle.getString(path);
        return filePath;
    }

    public static String onlyPath(String path) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.myapp.struts.ApplicationResource");
        String filePath = resourceBundle.getString(path);
        return filePath;
    }
}
