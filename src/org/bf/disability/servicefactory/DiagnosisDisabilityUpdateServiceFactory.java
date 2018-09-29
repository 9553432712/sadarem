/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.DiagnosisDisabilityUpdateServiceImpl;

/**
 *
 * @author 693461
 */
public class DiagnosisDisabilityUpdateServiceFactory {

    public static DiagnosisDisabilityUpdateServiceImpl diagnosisDisabilityUpdateServiceImpl;

    public static DiagnosisDisabilityUpdateServiceImpl getDiagnosisDisabilityUpdateServiceImpl(){

           if(diagnosisDisabilityUpdateServiceImpl==null){
             diagnosisDisabilityUpdateServiceImpl = new DiagnosisDisabilityUpdateServiceImpl();
           }

        return diagnosisDisabilityUpdateServiceImpl;
    }

}
