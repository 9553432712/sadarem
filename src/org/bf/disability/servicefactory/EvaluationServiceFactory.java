/*
 * EvaluationServiceFactory.java
 *
 * Created on September 12, 2008, 12:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.EvaluationServiceImpl;

/**
 * This class is designed based on factory design pattern whose responsibility is
 * to create an object to the EvaluationServiceImpl class
 * @author Sunima Dilla
 * @version 1.0
 */
public class EvaluationServiceFactory {
    
    /** Creates a new instance of EvaluationServiceFactory
     */
    public static EvaluationServiceImpl evaluationserviceimpl;
    public static EvaluationServiceImpl getEvaluationServiceImpl(){
       if(evaluationserviceimpl==null)
               evaluationserviceimpl = new EvaluationServiceImpl();
           return evaluationserviceimpl;
   }   
}
    

