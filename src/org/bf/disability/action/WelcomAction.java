/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

/**
 *
 * @author 310926
 */
import java.io.File;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.NewsandEventsDAO;

import Festival.FestivalDAO;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sgv.common.util.DBConnection;

public class WelcomAction extends DispatchAction 
{

    ActionMessages actionMessages = null;

    public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SADAREMDBException, SQLException 
    {
        String randomInt = null;
        Random randomGenerator = new Random();
        String code = "";
        String codeGen = "";
        DataSource ds = null;
        String target = "welcome";
        String lastUpdatedDate = null;
        String visitorCount = null;
        int p = 0;
        String path = null;
        String action = null;

        path = ((HttpServletRequest) request).getRequestURI();
        p = path.lastIndexOf("/");
        action = path.substring(p + 1, path.length() - 3);
        try 
        {
            HttpSession session = request.getSession();
            NewsandEventsDAO dao = new NewsandEventsDAO();
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) 
            {
                ds = JNDIDataSource.getConnection();
            }
         
            request.setAttribute("news", dao.getNewsTitles(ds));
          

            //GET WEBSITE LAST UPDATED DATE

            lastUpdatedDate = CommonDAOImpl.getLastUpdatedDate();
            if (lastUpdatedDate != null && lastUpdatedDate.length() > 0)
            {
                session.setAttribute("lastUpdatedDate", lastUpdatedDate);
            }

            //END

            // festival start
            FestivalDAO festivalDAO = new FestivalDAO();
            String sourcepath = null;
            sourcepath = festivalDAO.getFestivalImage(ds);

            if (sourcepath != null && sourcepath.length() > 0) {
                String imagepath = sourcepath.split("#")[0];
                String file = sourcepath.split("#")[1];
                request.setAttribute("file", file);

                File targetDir = null;
                File sourceDir = null;
                String uploadPath = getServlet().getServletContext().getRealPath("/") + "Festival";
                targetDir = new File(uploadPath);
                if (targetDir.exists()) {
                    String[] entries = targetDir.list();
                    for (String s : entries) {
                        File currentFile = new File(targetDir.getPath(), s);
                        currentFile.delete();
                    }
                    targetDir.delete();
                }
                if (!targetDir.exists()) {
                    targetDir.mkdir();
                }
                sourceDir = new File(imagepath);
                festivalDAO.copyDirectory(sourceDir, targetDir);
                request.setAttribute("popup", "popup");
            }

            //festival end
            // Visitor Count

            visitorCount = dao.visitorCount(ds);
           
            if (visitorCount != null && visitorCount.length() > 0) {
                session.setAttribute("visitorCount", visitorCount);

            }
        } 
        catch (SADAREMDBException sADAREMException) 
        {

            target = "exception";
            sADAREMException.printStackTrace();
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremservicemessage"));
            saveErrors(request, actionMessages);

        } 
        catch (Exception sADAREMException)
        {

            target = "exception";
            sADAREMException.printStackTrace();
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);

        }
        
        return mapping.findForward("welcome");
    }
}
