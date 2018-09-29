/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.BlogImpl;



/**
 *
 * @author 484898
 */
public class BlogFactory {

    public static BlogImpl blogImpl;

    public static BlogImpl getBlogImpl() {
        if (blogImpl == null) {
            blogImpl = new BlogImpl();
        }
        return blogImpl;
    }
}
