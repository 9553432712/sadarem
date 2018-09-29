
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.ExceptionServiceImpl;

public class ExceptionServiceFactory {

    /** Creates a new instance of EvaluationServiceFactory
     */
    public static ExceptionServiceImpl exceptionServiceImpl;
    public static ExceptionServiceImpl getExceptionServiceImpl(){
       if(exceptionServiceImpl==null)
               exceptionServiceImpl = new ExceptionServiceImpl();
           return exceptionServiceImpl;
   }
}