/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import org.bf.disability.common.DataBase.JNDIDataSource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.form.NewsandEventsForm;
import org.bf.disability.servicefactory.NewsandEventsServiceFactory;
import org.bf.disability.service.NewsandEventsService;

/**
 *
 * @author 728056
 */
public class NewsandEventsAction extends DispatchAction {

    /* forward name="success" path="" */
    String SUCCESS = "success";

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
        NewsandEventsForm eventsForm = (NewsandEventsForm) form;
        NewsandEventsService eventsService = NewsandEventsServiceFactory.getnewsandEventsServiceImpl();
        ArrayList newList = new ArrayList();
        try {
            DataSource ds = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            String loginId = null;
            HttpSession session = request.getSession();
            if (session.getAttribute("userName") != null) {
                loginId = session.getAttribute("userName").toString();
            }
            newList = eventsService.getNewsEvents(loginId, ds);

            if (newList.size() > 0) {
                request.setAttribute("newList", newList);
            } else {
                request.setAttribute("msg", "<font color ='red'>News And Events are not Available!</font>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward insertNewsNevents(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        NewsandEventsForm eventsForm = (NewsandEventsForm) form;
        NewsandEventsService eventsService = NewsandEventsServiceFactory.getnewsandEventsServiceImpl();
        int newsDetails = 0;
        ArrayList newList = new ArrayList();
        String url = getServlet().getServletContext().getRealPath("/");
        HttpSession session = request.getSession();
        String sesionTokenCode = null;
        String requestTokenCode = null;
        String fileName = null;
        String deptId = null;
        try {
            DataSource ds = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
                String loginId = null;
                if (session.getAttribute("userName") != null) {
                    loginId = session.getAttribute("userName").toString();
                }
                if (session.getAttribute("department") != null) {
                    deptId = session.getAttribute("department").toString();
                }

                if (eventsForm.getNewsUpload() != null && eventsForm.getNewsUpload().getFileName().length() > 0) {
                    StringTokenizer stringTokenizer = new StringTokenizer(eventsForm.getNewsUpload().getFileName(), ".");
                    fileName = stringTokenizer.nextElement().toString();
                    eventsForm.setFileName(fileName);

                }
                if (eventsForm.getNewsUpload() != null && eventsForm.getNewsUpload().getFileName().length() > 0) {
                    int fileSize = 0;
                    if (eventsForm.getNewsUpload() != null && eventsForm.getNewsUpload().getFileSize() > 0) {
                        if (eventsForm.getNewsUpload().getFileSize() >= 1024) {
                            fileSize = eventsForm.getNewsUpload().getFileSize() / 1024;
                        } else {
                            fileSize = eventsForm.getNewsUpload().getFileSize();
                        }
                    }
                    int fileExtensionCount = StringUtils.countMatches(eventsForm.getNewsUpload().getFileName(), ".");
                    if (fileExtensionCount == 1) {
                        if (fileSize > 0 && fileSize <= 10240) {
                            newsDetails = eventsService.insertNewsDetails(eventsForm, request, url, ds);
                            newList = eventsService.getNewsEvents(loginId, ds);
                            request.setAttribute("newList", newList);
                            if (newsDetails > 0) {
                                request.setAttribute("Suuces", "<font color ='green'>News And Events Inserted SuucessFully!</font>");
                                eventsForm.setNewsTitle("");
                                eventsForm.setNewsDescription("");
                                eventsForm.setFromDate("");
                                eventsForm.setToDate("");
                                eventsForm.setFlag("");
                            } else {
                                request.setAttribute("fail", "<font color ='red'>News And Events  Error in Inserting!</font>");
                                eventsForm.setNewsTitle("");
                                eventsForm.setNewsDescription("");
                                eventsForm.setFromDate("");
                                eventsForm.setToDate("");
                                eventsForm.setFlag("");
                            }
                        } else {
                            request.setAttribute("fail", "File Size Must Be Bellow 10MB");
                        }
                    } else {
                        request.setAttribute("fail", "File Extension should be single extension(Ex: .pdf or .xls or .txt) only");
                    }
                } else {
                    newsDetails = eventsService.insertNewsDetails(eventsForm, request, url, ds);
                    newList = eventsService.getNewsEvents(loginId, ds);
                    request.setAttribute("newList", newList);
                    if (newsDetails > 0) {
                        request.setAttribute("Suuces", "<font color ='green'>News And Events Inserted SuucessFully!</font>");
                        eventsForm.setNewsTitle("");
                        eventsForm.setNewsDescription("");
                        eventsForm.setFromDate("");
                        eventsForm.setToDate("");
                        eventsForm.setFlag("");
                    } else {
                        request.setAttribute("fail", "<font color ='red'>News And Events  Error in Inserting!</font>");
                        eventsForm.setNewsTitle("");
                        eventsForm.setNewsDescription("");
                        eventsForm.setFromDate("");
                        eventsForm.setToDate("");
                        eventsForm.setFlag("");
                    }
                }

           

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    //NewEvents
    public ActionForward editNewsDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArrayList editList = new ArrayList();

        NewsandEventsForm eventsForm = (NewsandEventsForm) form;
        NewsandEventsService eventsService = NewsandEventsServiceFactory.getnewsandEventsServiceImpl();

        String newsId = request.getParameter("newsId");
        request.setAttribute("newsId", newsId);
        String type = request.getParameter("type");
        try {
            DataSource ds = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            editList = eventsService.editNewsDetails(eventsForm, newsId, ds);

            eventsForm.setNewsTitle(editList.get(0).toString());
            eventsForm.setNewsDescription(editList.get(1).toString());
            if (editList.get(2) != null) {
                eventsForm.setFromDate(editList.get(2).toString());
            }
            if (editList.get(3) != null) {
                eventsForm.setToDate(editList.get(3).toString());
            }
            if (editList.get(4) != null) {
                eventsForm.setFlag(editList.get(4).toString());
            }
            request.setAttribute("editData", "editData");
            request.setAttribute("upadate", "upadate");
            this.unspecified(mapping, form, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }



        return mapping.findForward(SUCCESS);
    }

    public ActionForward updateNewsDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int updateDate = 0;

        NewsandEventsForm eventsForm = (NewsandEventsForm) form;
        NewsandEventsService eventsService = NewsandEventsServiceFactory.getnewsandEventsServiceImpl();
        String newDataId = eventsForm.getNewDataId();
        String url = getServlet().getServletContext().getRealPath("/");
        HttpSession session = request.getSession();
        String sesionTokenCode = null;
        String requestTokenCode = null;
        try {
            DataSource ds = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("tokenCode") != null) {
                sesionTokenCode = session.getAttribute("tokenCode").toString();
                requestTokenCode = request.getParameter("tokenCodeChecking");
            }
//            if (sesionTokenCode != null && requestTokenCode != null && sesionTokenCode.equals(requestTokenCode)) {

                String loginid = null, deptId = null;
                if (session.getAttribute("userName") != null) {
                    loginid = session.getAttribute("userName").toString();
                }
                if (session.getAttribute("department") != null) {
                    deptId = session.getAttribute("department").toString();
                }

                updateDate = eventsService.updateNewsDetails(eventsForm, newDataId, request, url, ds);

                if (updateDate > 0) {
                    request.setAttribute("msg", "<font color ='green'>News And Events are Updated SuccessFully!</font>");
                    eventsForm.setNewsTitle("");
                    eventsForm.setNewsDescription("");
                    eventsForm.setFromDate("");
                    eventsForm.setToDate("");
                    eventsForm.setFlag("");
                } else {
                    request.setAttribute("msg", "<font color ='red'>Error in News And Events Updating!</font>");
                    eventsForm.setNewsTitle("");
                    eventsForm.setNewsDescription("");
                    eventsForm.setFromDate("");
                    eventsForm.setToDate("");
                    eventsForm.setFlag("");
                }
                eventsForm.setNewsId("");

//            } else {
//                request.getRequestDispatcher("./403.jsp").forward(request, response);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.unspecified(mapping, form, request, response);


        return mapping.findForward(SUCCESS);
    }

    public ActionForward deleteNewsDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int delete = 0;

        NewsandEventsForm eventsForm = (NewsandEventsForm) form;
        NewsandEventsService eventsService = NewsandEventsServiceFactory.getnewsandEventsServiceImpl();
        String newDataId = request.getParameter("newsId");
        HttpSession session = request.getSession();
        String sesionTokenCode = null;
        String requestTokenCode = null;
        try {
            DataSource ds = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("tokenCode") != null) {
                sesionTokenCode = session.getAttribute("tokenCode").toString();
                requestTokenCode = request.getParameter("tokenCodeChecking");
            }

            String loginid = null, deptId = null;
            if (session.getAttribute("userName") != null) {
                loginid = session.getAttribute("userName").toString();
            }
            if (session.getAttribute("department") != null) {
                deptId = session.getAttribute("department").toString();
            }

//            if (sesionTokenCode != null && requestTokenCode != null && sesionTokenCode.equals(requestTokenCode)) {
                delete = eventsService.deleteNewsDetails(eventsForm, newDataId, ds);

                if (delete != 0) {
                    request.setAttribute("msg", "<font color='green'>News And Events Record Deleted SuccFully!</font>");
                } else {
                    request.setAttribute("msg", "<font color='red'>Error in News And Eventys Record Deleting!</font>");
                }

//            } else {
//                request.getRequestDispatcher("./403.jsp").forward(request, response);
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        this.unspecified(mapping, form, request, response);


        return mapping.findForward(SUCCESS);
    }
    //end

    public ActionForward downLoadDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String extension = null;
        try {

            String filetitle = request.getParameter("fileName");

            File folder = new File(filePath("newsEvents"));
            File[] listOfFiles = folder.listFiles();
            int size = 0;
            if (listOfFiles != null && !listOfFiles.equals("")) {
                size = listOfFiles.length + 1;
            } else {
                size = 0 + 1;
            }
            BufferedInputStream in = null;
            File fileDetailsData = new File(folder + "\\" + "\\" + request.getParameter("uploadpath"));



            FileInputStream fin = new FileInputStream(fileDetailsData);
            in = new BufferedInputStream(fin);
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filetitle);
            byte[] buffer = new byte[4 * 1024];
            int data = 0;
            while ((data = in.read(buffer)) != -1) {
                out.write(buffer, 0, data);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward newsDownLoadDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String extension = null;
        ServletOutputStream out = null;
        try {
            String fileName = request.getParameter("ext");
            String filetitle = request.getParameter("fileName");
            String id = request.getParameter("id");
            File folder = new File(filePath("newsEvents"));
            File[] listOfFiles = folder.listFiles();
            int size = 0;
            if (listOfFiles != null && !listOfFiles.equals("")) {
                size = listOfFiles.length + 1;
            } else {
                size = 0 + 1;
            }
            BufferedInputStream in = null;
            if (request.getParameter("uploadpath") != null && request.getParameter("uploadpath").length() > 0) {
                File fileDetailsData = new File(folder + "\\" + "\\" + request.getParameter("uploadpath"));
                FileInputStream fin = new FileInputStream(fileDetailsData);
                in = new BufferedInputStream(fin);
                out = response.getOutputStream();
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment; filename=\"" + id + "_" + filetitle);
                byte[] buffer = new byte[4 * 1024];
                int data = 0;
                while ((data = in.read(buffer)) != -1) {
                    out.write(buffer, 0, data);
                }
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            request.setAttribute("fail", "Selected File Not Available");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unspecified(mapping, form, request, response);
        }
        return mapping.findForward(SUCCESS);
    }

    public static String filePath(String path) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.myapp.struts.ApplicationResource");
        String filePath = resourceBundle.getString("drive") + resourceBundle.getString(path);
        return filePath;
    }
}
