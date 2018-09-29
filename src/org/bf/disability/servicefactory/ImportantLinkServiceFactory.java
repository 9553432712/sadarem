/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.ImportantLinkServiceImpl;



/**
 *
 * @author 693461
 */
public class ImportantLinkServiceFactory {
     public static ImportantLinkServiceImpl importantLinkServiceImpl;

    public static ImportantLinkServiceImpl getImportantLinkServiceImpll() {
        if (importantLinkServiceImpl == null) {
            importantLinkServiceImpl = new ImportantLinkServiceImpl();
        }
        return importantLinkServiceImpl;
    }

}
