/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.form.NewsandEventsForm;

/**
 *
 * @author 728056
 */
public interface NewsandEventsService {

    public int insertNewsDetails(NewsandEventsForm eventsForm, HttpServletRequest request, String url, DataSource ds) throws Exception;

    public ArrayList getNewsTitles(DataSource ds) throws Exception;

    public ArrayList getNews(String id, HttpServletRequest request, String url, DataSource ds) throws Exception;

    public int insertSuggestionsin(NewsandEventsForm eventsForm, DataSource ds) throws Exception;

    public ArrayList getSuggestion(DataSource ds) throws Exception;

    //Display Data for NewsAnd Events
    public ArrayList getNewsEvents(String loginId, DataSource ds) throws Exception;

    public ArrayList editNewsDetails(NewsandEventsForm eventsForm, String rowId, DataSource ds) throws Exception;

    public int updateNewsDetails(NewsandEventsForm eventsForm, String rowId, HttpServletRequest request, String url, DataSource ds) throws Exception;

    public int deleteNewsDetails(NewsandEventsForm eventsForm, String rowId, DataSource ds) throws Exception;
}
