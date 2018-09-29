package com.example.myproject.quartz;

import org.jboss.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 赵亚辉 on 2017/12/18.
 */
public class ScheduledJob implements Job {
    private static final Logger logger = Logger.getLogger(ScheduledJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("schedule job1 is running");
    }
}
