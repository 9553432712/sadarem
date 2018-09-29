/*
 * DoctorsInformationServiceFactory.java
 *
 * Created on September 12, 2008, 2:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.DoctorsInformationServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the DoctorsInformationServiceImpl class
 * @author Pramod Kumar G
 * @version 1.0
 */
public class DoctorsInformationServiceFactory 
{
  public static DoctorsInformationServiceImpl adddoctorsinformationserviceimpl;
  public static DoctorsInformationServiceImpl getDoctorsInformationServiceImpl()
  {
       if(adddoctorsinformationserviceimpl==null)
               adddoctorsinformationserviceimpl = new DoctorsInformationServiceImpl();
           return adddoctorsinformationserviceimpl;
   }     
    
}
