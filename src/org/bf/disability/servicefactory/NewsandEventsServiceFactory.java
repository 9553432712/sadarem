/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.NewsandEventsServiceImpl;


/**
 *
 * @author 728056
 */
public class NewsandEventsServiceFactory {

    public static NewsandEventsServiceImpl newsandEventsServiceImpl;

    public static NewsandEventsServiceImpl getnewsandEventsServiceImpl() {
        if (newsandEventsServiceImpl == null) {
            newsandEventsServiceImpl = new NewsandEventsServiceImpl();
        }
        return newsandEventsServiceImpl;
    }
}
