/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schedule;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author 747577
 */
public class CronScheduler {
Scheduler sche =null;
    public CronScheduler() {

        
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            sche= sf.getScheduler();
            sche.start();
            
            JobDetail jDetail = new JobDetail("Newsletter", "NJob", MeesevaSchedule.class);
            CronTrigger crTrigger = new CronTrigger("cronTrigger", "NJob", "0 30 22 ? * *");
            sche.scheduleJob(jDetail, crTrigger);

            JobDetail personalDetail = new JobDetail("PersonalDetails", "Job", PersonalDetailsSchedule.class);
            CronTrigger personalTrigger = new CronTrigger("PDcronTrigger", "Job", "0 0/15 * * * ?");
            sche.scheduleJob(personalDetail, personalTrigger);

        } catch (ParseException ex) {
            Logger.getLogger(CronScheduler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SchedulerException ex) {
            Logger.getLogger(CronScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    public void stopScheduler()
    {
    try 
    {
        sche.shutdown(true);
    } 
    catch (SchedulerException ex)
    {
        Logger.getLogger(CronScheduler.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
    }
    }
}
