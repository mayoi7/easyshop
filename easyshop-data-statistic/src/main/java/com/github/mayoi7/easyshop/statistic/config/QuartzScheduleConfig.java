package com.github.mayoi7.easyshop.statistic.config;

import com.github.mayoi7.easyshop.service.StatisticService;
import com.github.mayoi7.easyshop.statistic.schedule.StatisticScheduleJob;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author LiuHaonan
 * @date 22:59 2020/5/18
 * @email acerola.orion@foxmail.com
 */
@Configuration
@EnableScheduling
public class QuartzScheduleConfig {

    @Bean(name = "statisticJob")
    JobDataMap statisticJob(@Autowired StatisticService statisticService) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("statisticService", statisticService);
        return jobDataMap;
    }

    @Bean(name = "statisticJobFactory")
    public JobDetailFactoryBean statisticJobFactory(@Qualifier("statisticJob") JobDataMap jobDataMap) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobDataMap(jobDataMap);
        jobDetailFactoryBean.setJobClass(StatisticScheduleJob.class);
        jobDetailFactoryBean.setName("statisticJobFactory");
        return jobDetailFactoryBean;
    }

    @Bean("statisticTrigger")
    public CronTriggerFactoryBean statisticTriggerBean(
            @Qualifier("statisticJobFactory") JobDetailFactoryBean jobDetailFactoryBean) {
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        triggerFactoryBean.setBeanName("statisticTrigger");
        triggerFactoryBean.setStartDelay(1000);
        // 每天23点55执行
        triggerFactoryBean.setCronExpression("0 55 23 * * ?");
        return triggerFactoryBean;
    }

    @Bean(name = "quartzScheduler")
    public SchedulerFactoryBean schedulerFactoryBean1(@Qualifier("statisticTrigger") CronTrigger trigger) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setBeanName("quartzScheduler");
        schedulerFactoryBean.setTriggers(trigger);
        return schedulerFactoryBean;
    }
}
