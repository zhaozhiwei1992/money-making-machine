package com.example.config;

import com.example.service.QuartzJobExecuteService;
import java.io.IOException;
import java.util.Properties;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时配置（可以配置静态定时任务）
 */
@Configuration
public class QuartzSchedulerConfiguration {

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        JobFactoryAutowireConfiguration jobFactory = new JobFactoryAutowireConfiguration();

        jobFactory.setApplicationContext(applicationContext);

        return jobFactory;
    }

    //SchedulerFactoryBean
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, Trigger simpleJobTrigger) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        factory.setTriggers(simpleJobTrigger);

        return factory;
    }

    /**
     * 静态方式配置定时任务
     *
     * @param jobDetail
     * @return
     */
    @Bean
    public CronTriggerFactoryBean simpleJobTrigger(@Qualifier("simpleJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();

        factoryBean.setJobDetail(jobDetail);
        factoryBean.setStartDelay(1000L);
        factoryBean.setName("trigger1");
        factoryBean.setGroup("group1");
        factoryBean.setCronExpression("0 0 */12 ? * 2-6");

        return factoryBean;
    }

    @Bean
    public JobDetailFactoryBean simpleJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();

        factoryBean.setJobClass(QuartzJobExecuteService.class);
        factoryBean.setGroup("group1");
        factoryBean.setName("job1");

        return factoryBean;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();

        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));

        propertiesFactoryBean.afterPropertiesSet();

        return propertiesFactoryBean.getObject();
    }
}
