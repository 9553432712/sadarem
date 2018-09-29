/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schedule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author 747577
 */
public class TimerServlet extends HttpServlet {

    CronScheduler cron = null;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        try {
            cron = new CronScheduler();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
        cron.stopScheduler();
        super.destroy();
    }
}
