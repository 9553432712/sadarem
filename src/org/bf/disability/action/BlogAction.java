/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import org.bf.disability.form.BlogForm;
import org.bf.disability.servicefactory.BlogFactory;
import org.bf.disability.service.BlogService;
import org.bf.disability.common.DataBase.JNDIDataSource;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import java.util.*;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.upload.FormFile;
import org.bf.disability.dto.BlogDTO;


/**
 *
 * @author 484898
 */
public class BlogAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
      
        BlogService blogService = BlogFactory.getBlogImpl();
        BlogForm blogForm = (BlogForm) form;
        BlogDTO blogDTO = new BlogDTO();
        ArrayList blogSubjects = new ArrayList();
        ArrayList subjects = new ArrayList();
        ArrayList subjectDetails = new ArrayList();
        ArrayList blogsList = new ArrayList();
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        blogDTO.setUrl(getServlet().getServletContext().getRealPath("/"));
        blogSubjects = blogService.getBlogSubjects(ds);
        if (blogSubjects.size() > 0) {
            request.setAttribute("blogSubjects", blogSubjects);
        }

        // Subjects From subject Master

        subjects = blogService.getSubjects(ds);
        if (subjects.size() > 0) {
            blogForm.setSubjects(subjects);
        }
        // Subjects for subject master screen

        subjectDetails = blogService.getsubjectDetails(ds);
        if (subjectDetails.size() > 0) {
            request.setAttribute("subjects", subjectDetails);
        }

        // Blogs list for post blog service

        blogsList = blogService.getPostedBlogs(blogDTO,ds);
        if (blogsList.size() > 0) {
            request.setAttribute("blogsList", blogsList);
        }

        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for saving the blog details
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        BlogService blogService = BlogFactory.getBlogImpl();
        BlogDTO blogDTO = new BlogDTO();
        BlogForm blogForm = (BlogForm) form;
        String insertStatus = null;
        List imageList = null;
        //BeanUtils.copyProperties(blogDTO, blogForm);
        blogDTO.setName(request.getParameter("name" + request.getParameter("rowIdForInsert")));
        blogDTO.setDescription(request.getParameter("description" + request.getParameter("rowIdForInsert")));
        blogDTO.setMobile(request.getParameter("mobile" + request.getParameter("rowIdForInsert")));
        blogDTO.setEmail(request.getParameter("email" + request.getParameter("rowIdForInsert")));
        blogDTO.setSubjectIdForReply(request.getParameter("subjectIdForReply"));
        blogDTO.setPostId(request.getParameter("rowIdForInsert"));

        List listImg = blogForm.getUploadPhoto();
        if (listImg != null && listImg.size() > 0) {
            imageList = new ArrayList();
            for (int j = 0; j < listImg.size(); j++) {
                if (listImg.get(j) != null) {
                    imageList.add(listImg.get(j));
                }
            }
            blogDTO.setImageList(imageList);
            blogDTO.setFile((FormFile) imageList.get(0));
        }
        blogDTO.setSystemIp(request.getRemoteAddr());
        blogDTO.setUrl(getServlet().getServletContext().getRealPath("/"));
        insertStatus = blogService.saveBlogDetails(blogDTO, request,ds);
        if (insertStatus != null) {
            request.setAttribute("msg", insertStatus);
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This mehtod retruns arraylist of posts related to subjects
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getPosts(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        BlogService blogService = BlogFactory.getBlogImpl();
        BlogDTO blogDTO = new BlogDTO();
        ArrayList postedBlogs = new ArrayList();
        blogDTO.setSubjectMode(request.getParameter("id"));
        blogDTO.setUrl(getServlet().getServletContext().getRealPath("/"));
        // GET BLOG POST DETAILS
        postedBlogs = blogService.getPostedBlogs(blogDTO,ds);
        if (postedBlogs.size() > 0) {
            request.setAttribute("postedBlogs", postedBlogs);
        } else {
            request.setAttribute("notAvailablePostedBlogs", "Blog Posts Not Available");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for getting the blog answers through AJAX
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAnswers(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        BlogService blogService = BlogFactory.getBlogImpl();
        String answersString = null;
        PrintWriter out = null;
        try {
            response.setHeader("Cache-Control", "private");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setContentType("text/xml");
            out = response.getWriter();
            answersString = blogService.getAnswers(request.getParameter("postIdForAnswer"), request.getParameter("SubjectIdForAnswer"),ds);
            out.println("<root>");
            out.println("<table>" + answersString + "</table>");
            out.println("</root>");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is for saving the blog details post
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward savePostBlogDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        BlogService blogService = BlogFactory.getBlogImpl();
        BlogDTO blogDTO = new BlogDTO();
        BlogForm blogForm = (BlogForm) form;
        String insertStatus = null;
        BeanUtils.copyProperties(blogDTO, blogForm);
        blogDTO.setSystemIp(request.getRemoteAddr());
        blogDTO.setUrl(getServlet().getServletContext().getRealPath("/"));
        insertStatus = blogService.savePostBlogDetails(blogDTO, request,ds);
        if (insertStatus != null) {
            request.setAttribute("msg", insertStatus);
            blogForm.setName("");
            blogForm.setSubject("00");
            blogForm.setDescription("");
            blogForm.setMobile("");
            blogForm.setEmail("");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for adding the subjects into subject master
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addSubjects(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        BlogService blogService = BlogFactory.getBlogImpl();
        BlogDTO blogDTO = new BlogDTO();
        BlogForm blogForm = (BlogForm) form;
        String insertStatus = null;
        BeanUtils.copyProperties(blogDTO, blogForm);
        blogDTO.setSystemIp(request.getRemoteAddr());
        insertStatus = blogService.addSubjects(blogDTO,ds);
        if (insertStatus != null) {
            request.setAttribute("msg", insertStatus);
            blogForm.setAddSubject("");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for updating the subjects
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateSubjects(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        BlogService blogService = BlogFactory.getBlogImpl();
        BlogDTO blogDTO = new BlogDTO();
        BlogForm blogForm = (BlogForm) form;
        String insertStatus = null;
        BeanUtils.copyProperties(blogDTO, blogForm);
        blogDTO.setSystemIp(request.getRemoteAddr());
        blogDTO.setSubjectId(request.getParameter("rowForEdit"));
        insertStatus = blogService.updateSubjects(blogDTO,ds);
        if (insertStatus != null) {
            request.setAttribute("msg", insertStatus);
            blogForm.setAddSubject("");
            request.setAttribute("updateStat", "updateStat");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for Inactive the record
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward inactiveRecord(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        BlogService blogService = BlogFactory.getBlogImpl();
        BlogDTO blogDTO = new BlogDTO();
        BlogForm blogForm = (BlogForm) form;
        String inactiveStatus = null;
        BeanUtils.copyProperties(blogDTO, blogForm);
        blogDTO.setSystemIp(request.getRemoteAddr());
        blogDTO.setSubjectId(request.getParameter("inactive"));
        inactiveStatus = blogService.inactiveRecord(blogDTO,ds);
        if (inactiveStatus != null) {
            request.setAttribute("msg", inactiveStatus);
            blogForm.setAddSubject("");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for Inactive the record
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updatePostBlogDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        BlogService blogService = BlogFactory.getBlogImpl();
        BlogDTO blogDTO = new BlogDTO();
        BlogForm blogForm = (BlogForm) form;
        String updateStatus = null;
        BeanUtils.copyProperties(blogDTO, blogForm);
        blogDTO.setSystemIp(request.getRemoteAddr());
        blogDTO.setPostId(request.getParameter("rowForEdit"));
        updateStatus = blogService.updatePostBlogDetails(blogDTO, request,ds);
        if (updateStatus != null) {
            request.setAttribute("msg", updateStatus);
            blogForm.setName("");
            blogForm.setSubject("00");
            blogForm.setDescription("");
            blogForm.setMobile("");
            blogForm.setEmail("");
            request.setAttribute("updateStat", "updateStat");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }

    /**
     * This method is for Inactive the record
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward inactiveBlogPostRecord(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        BlogService blogService = BlogFactory.getBlogImpl();
        BlogDTO blogDTO = new BlogDTO();
        BlogForm blogForm = (BlogForm) form;
        String inactiveStatus = null;
        BeanUtils.copyProperties(blogDTO, blogForm);
        blogDTO.setSystemIp(request.getRemoteAddr());
        blogDTO.setPostId(request.getParameter("inactive"));
        inactiveStatus = blogService.inactiveBlogPostRecord(blogDTO,ds);
        if (inactiveStatus != null) {
            request.setAttribute("msg", inactiveStatus);
            blogForm.setAddSubject("");
        }
        unspecified(mapping, form, request, response);
        return mapping.findForward(SUCCESS);
    }
}
