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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
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
public class NewsDetailsAction extends DispatchAction {

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
        ArrayList newsTitle = new ArrayList();
        NewsandEventsForm eventsForm = (NewsandEventsForm) form;
        NewsandEventsService eventsService = NewsandEventsServiceFactory.getnewsandEventsServiceImpl();
        
        try {
            DataSource ds = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            newsTitle = eventsService.getNewsTitles(ds);
            request.setAttribute("titles", newsTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward getNewsDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ArrayList newsDetails = new ArrayList();
        NewsandEventsService eventsService = NewsandEventsServiceFactory.getnewsandEventsServiceImpl();
        String titleId = request.getParameter("id");
        String target = "";
        File folder = null;
        String url = getServlet().getServletContext().getRealPath("/");
        try {
            if(titleId==null){
               titleId= request.getParameter("news_Id");
            }
            DataSource ds = null;
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            newsDetails = eventsService.getNews(titleId, request, url,ds);
            request.setAttribute("news", newsDetails);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("news");
    }

    public ActionForward downLoadDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException,Exception {
        String extension = null;
        String fileName = null;
        String filetitle = null;
        String target = null;
        try {
            if (!request.getParameter("ext").equals("") && !request.getParameter("fileName").equals("") && request.getParameter("fileName").length() > 0) {
                fileName = request.getParameter("ext");
                filetitle = request.getParameter("fileName");
                if (fileName != null && fileName.length() > 0) {
                    int dotPos = fileName.lastIndexOf(".");
                    extension = fileName.substring(dotPos);
                }
                File folder = new File(filePath("newsEvents"));
                File[] listOfFiles = folder.listFiles();
                int size = 0;
                if (listOfFiles != null && !listOfFiles.equals("")) {
                    size = listOfFiles.length + 1;
                } else {
                    size = 0 + 1;
                }
                BufferedInputStream in = null;

                File fileDetailsData = new File(folder + "\\" + "\\" + request.getParameter("news_Id") + extension);
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
            }else{
                 request.setAttribute("fail", "Selected File Not Available");
            }

            target = "news";

        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("fail", "Selected File Not Available");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            target = "news";
        }
        
        getNewsDetails(mapping, form, request, response);
        return mapping.findForward(target);
    }
    public static String filePath(String path) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.myapp.struts.ApplicationResource");
        String filePath = resourceBundle.getString("drive") + resourceBundle.getString(path);


        return filePath;



    }
}
