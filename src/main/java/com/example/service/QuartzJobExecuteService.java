package com.example.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.jboss.logging.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 自定义定时任务
 * 站在job角度来说这里就是业务，当然也可以在内部封装处理自己的业务,累死business下的方式，实际包名只与jobName有关
 */
public class QuartzJobExecuteService implements Job {

    private static final Logger logger = Logger.getLogger(QuartzJobExecuteService.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("执行自定义定时任务");

        final JobDetail jobDetail = jobExecutionContext.getJobDetail();
        final String jobName = jobDetail.getKey().getName();
        try {
            final String[] split = jobName.split("#");
            final Class<?> forName = Class.forName(split[0]);
            final Method method = forName.getMethod(split[1], null);

            //            todo 必要时这里可以做耗时统计
            method.invoke(forName.newInstance(), null);
        } catch (
            ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e
        ) {
            logger.error("定时任务触发异常", e);
        }
    }
}
