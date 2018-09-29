/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.dao.NewsandEventsDAO;
import org.bf.disability.form.NewsandEventsForm;
import org.bf.disability.service.NewsandEventsService;

/**
 *
 * @author 728056
 */
public class NewsandEventsServiceImpl implements NewsandEventsService {

    public int insertNewsDetails(NewsandEventsForm eventsForm, HttpServletRequest request, String url, DataSource ds) throws Exception {
        int newsDetails = 0;
        NewsandEventsDAO eventsDAO = new NewsandEventsDAO();
        try {
            newsDetails = eventsDAO.insertNewsDetails(eventsForm, request, url,ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsDetails;
    }

    public ArrayList getNewsTitles(DataSource ds) throws Exception {
        ArrayList newsTitles = new ArrayList();
        NewsandEventsDAO eventsDAO = new NewsandEventsDAO();
        try {
            newsTitles = eventsDAO.getNewsTitles(ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsTitles;
    }

    public ArrayList getNews(String id, HttpServletRequest request, String url, DataSource ds) throws Exception {
        ArrayList newsDescription = new ArrayList();
        NewsandEventsDAO eventsDAO = new NewsandEventsDAO();
        try {
            newsDescription = eventsDAO.getNews(id,request, url,ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsDescription;
    }

    public int insertSuggestionsin(NewsandEventsForm eventsForm, DataSource ds) throws Exception {
        int newsDetails = 0;
        NewsandEventsDAO eventsDAO = new NewsandEventsDAO();
        try {
            newsDetails = eventsDAO.insertSuggestionsin(eventsForm,ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsDetails;
    }

    public ArrayList getSuggestion(DataSource ds) throws Exception {
        ArrayList SuggestionsDetails = new ArrayList();
        NewsandEventsDAO eventsDAO = new NewsandEventsDAO();
        try {
            SuggestionsDetails = eventsDAO.getSuggestion(ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SuggestionsDetails;
    }

    public ArrayList getNewsEvents(String loginId, DataSource ds) throws Exception {
        ArrayList newEvents = new ArrayList();
        NewsandEventsDAO eventsDAO = new NewsandEventsDAO();
        try {
            newEvents = eventsDAO.getNewsEvents(loginId,ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newEvents;

    }

    public ArrayList editNewsDetails(NewsandEventsForm eventsForm, String rowId, DataSource ds) throws Exception {

        ArrayList editList = new ArrayList();
        NewsandEventsDAO eventsDAO = new NewsandEventsDAO();

        try {
            editList = eventsDAO.editNewsDetails(eventsForm, rowId,ds);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return editList;
    }

   public int updateNewsDetails(NewsandEventsForm eventsForm, String rowId,HttpServletRequest request, String url, DataSource ds) throws Exception {

        int updateDetails = 0;
        NewsandEventsDAO eventsDAO = new NewsandEventsDAO();
        try {
            updateDetails = eventsDAO.updateNewsDetails(eventsForm, rowId, request, url,ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateDetails;

    }

    public int deleteNewsDetails(NewsandEventsForm eventsForm, String rowId, DataSource ds) throws Exception {

        int deleteDetails = 0;
        NewsandEventsDAO eventsDAO = new NewsandEventsDAO();
        try {
            deleteDetails = eventsDAO.deleteNewsDetails(eventsForm, rowId,ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteDetails;

    }

  
}
