/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.RationCardReportServiceImpl;

/**
 *
 * @author SADAREM
 */
public class RationCardReportServiceFactory {

    public static RationCardReportServiceImpl rationCardServiceImpl;

    public static RationCardReportServiceImpl getRationCardServiceImpl(){

       if(rationCardServiceImpl ==null)
           rationCardServiceImpl = new RationCardReportServiceImpl();


    return rationCardServiceImpl;
    

    }
}
