/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.FilterCandidateServiceImpl;

/**
 *
 * @author vijay
 */
public class FilterCandidateServiceFactory {

     public static FilterCandidateServiceImpl filterCandidateServiceImpl;
  public static FilterCandidateServiceImpl getFilterCandidateServiceImpl(){
       if(filterCandidateServiceImpl==null)
               filterCandidateServiceImpl = new FilterCandidateServiceImpl();
           return filterCandidateServiceImpl;
   }

}
