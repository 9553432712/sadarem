/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.service;

import org.bf.disability.Exception.SADAREMDBException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.SQLException;
import org.bf.disability.dto.BlogDTO;

/**
 *
 * @author 484898
 */
public interface BlogService {

    public ArrayList getBlogSubjects(DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getPostedBlogs(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException;

    public String saveBlogDetails(BlogDTO blogDTO,HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException;

    public String savePostBlogDetails(BlogDTO blogDTO,HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException;

    public String addSubjects(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException;

    public String inactiveBlogPostRecord(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException;

    public String updateSubjects(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException;

    public String inactiveRecord(BlogDTO blogDTO,DataSource ds) throws SADAREMDBException, SQLException;

    public String updatePostBlogDetails(BlogDTO blogDTO,HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getSubjects(DataSource ds) throws SADAREMDBException, SQLException;

    public String getAnswers(String postId,String subjectId,DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getsubjectDetails(DataSource ds) throws SADAREMDBException, SQLException;

}
