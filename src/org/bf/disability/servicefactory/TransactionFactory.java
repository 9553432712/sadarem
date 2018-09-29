/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.TransactionImpl;

/**
 *
 * @author 484898
 */
public class TransactionFactory {

    public static TransactionImpl transactionImpl;

    public static TransactionImpl getTransaction() {
        if (transactionImpl == null)
            
            transactionImpl = new TransactionImpl();
        
        return transactionImpl;
    }
}
