/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.service.NewCertificateService;
import org.bf.disability.serviceimpl.NewCertificateServiceImpl;

/**
 *
 * @author 310926
 */
public class NewCertificateServiceFactory {
 public static NewCertificateServiceImpl NewCertificateServiceImpl;
  public static NewCertificateServiceImpl getNewCertificateServiceImpl(){
       if(NewCertificateServiceImpl==null)
               NewCertificateServiceImpl = new NewCertificateServiceImpl();
           return NewCertificateServiceImpl;
   }


}
