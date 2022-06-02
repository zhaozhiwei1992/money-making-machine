package com.example.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: QuartJobListener
 * @Package com/example/listener/QuartJobListener.java
 * @Description:
 * 可以在这里做监控
 * @author zhaozhiwei
 * @date 2022/6/2 下午5:39
 * @version V1.0
 */
public class QuartJobListener implements JobListener {

    private static final Logger log = LoggerFactory.getLogger(QuartJobListener.class);

    public static final String LISTENER_NAME = "QuartSchedulerListener";

    @Override
    public String getName() {
        //must return a name
        return LISTENER_NAME;
    }

    //任务被调度前
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().toString();
        log.info("jobToBeExecuted");
        log.info("Job : {} is going to start...", jobName);
    }

    //任务调度被拒了
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.info("jobExecutionVetoed");
        //可以做一些日志记录原因

    }

    //任务被调度后
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        log.info("jobWasExecuted");

        String jobName = context.getJobDetail().getKey().toString();
        log.info("Job : {} is finished...", jobName);

        if (jobException != null && !jobException.getMessage().equals("")) {
            log.error("Exception thrown by: " + jobName + " Exception: ", jobException);
        }
    }
}
