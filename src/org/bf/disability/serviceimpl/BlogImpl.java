/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import org.bf.disability.Exception.SADAREMDBException;
import java.sql.SQLException;
import org.bf.disability.dao.BlogDAO;
import org.bf.disability.service.BlogService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.bf.disability.dto.BlogDTO;


/**
 *
 * @author 484898
 */
public class BlogImpl implements BlogService {

    /**
     * This method is for fetching the data from blog subjects.
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getBlogSubjects(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList blogSubjects = new ArrayList();
        BlogDAO blogDAO = new BlogDAO();
        blogSubjects = blogDAO.getBlogSubjects(ds);
        return blogSubjects;
    }

    /**
     * This method is for fetching the data from blog subjects.
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getPostedBlogs(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList blogSubjects = new ArrayList();
        BlogDAO blogDAO = new BlogDAO();
        blogSubjects = blogDAO.getPostedBlogs(blogDTO,ds);
        return blogSubjects;
    }

    /**
     * This method is for saving the blog details
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String saveBlogDetails(BlogDTO blogDTO, HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException {
        String insertStatus = null;
        BlogDAO blogDAO = new BlogDAO();
        insertStatus = blogDAO.saveBlogDetails(blogDTO, request,ds);
        return insertStatus;
    }

    /**
     * This method is for Update the blog details
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String updatePostBlogDetails(BlogDTO blogDTO, HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException {
        String updateStatus = null;
        BlogDAO blogDAO = new BlogDAO();
        updateStatus = blogDAO.updatePostBlogDetails(blogDTO, request,ds);
        return updateStatus;
    }

    /**
     * This method is for Inactive the record
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String inactiveRecord(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException {
        String inactiveStatus = null;
        BlogDAO blogDAO = new BlogDAO();
        inactiveStatus = blogDAO.inactiveRecord(blogDTO,ds);
        return inactiveStatus;
    }

    /**
     * This method is for Inactive the record
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String inactiveBlogPostRecord(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException {
        String inactiveStatus = null;
        BlogDAO blogDAO = new BlogDAO();
        inactiveStatus = blogDAO.inactiveBlogPostRecord(blogDTO,ds);
        return inactiveStatus;
    }

    /**
     * This method is for saving the blog details
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String addSubjects(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException {
        String insertStatus = null;
        BlogDAO blogDAO = new BlogDAO();
        insertStatus = blogDAO.addSubjects(blogDTO,ds);
        return insertStatus;
    }

    /**
     * This method is for Update the subjects Details
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String updateSubjects(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException {
        String updateStatus = null;
        BlogDAO blogDAO = new BlogDAO();
        updateStatus = blogDAO.updateSubjects(blogDTO,ds);
        return updateStatus;
    }

    /**
     * This method is for saving the blog details Post
     * @param blogDTO
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String savePostBlogDetails(BlogDTO blogDTO, HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException {
        String insertStatus = null;
        BlogDAO blogDAO = new BlogDAO();
        insertStatus = blogDAO.savePostBlogDetails(blogDTO, request,ds);
        return insertStatus;
    }

    /**
     * This method is for getting the blog answers through AJAX
     * @param postId
     * @param subjectId
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String getAnswers(String postId, String subjectId,DataSource ds) throws SADAREMDBException, SQLException {
        String answersString = null;
        BlogDAO blogDAO = new BlogDAO();
        answersString = blogDAO.getAnswers(postId, subjectId,ds);
        return answersString;
    }

    /**
     * This method is for getting the Blog subjects
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getSubjects(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList subjects = new ArrayList();
        BlogDAO blogDAO = new BlogDAO();
        subjects = blogDAO.getSubjects(ds);
        return subjects;
    }

    /**
     * This method is for getting the subjects for add subjects screen
     * @return
     */
    public ArrayList getsubjectDetails(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList subjects = new ArrayList();
        BlogDAO blogDAO = new BlogDAO();
        subjects = blogDAO.getsubjectDetails(ds);
        return subjects;
    }
}
