package com.cskaoyan.weatherreportapp.config;

import com.cskaoyan.weatherreportapp.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description: quartz定时更新
 * User:  张娅迪
 * Date: 2019/01/02
 * Time: 下午 3:01
 * Detail requirement: 配置定时设置
 * Method:
 */
@Configuration
public class QuartzConfiguration {

    private static final int TIME = 60;    //更新频率,5min

    //jobdetail, 指定job
    @Bean
    public JobDetail weatherDataSyncJobDetail(){
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("WeatherDataSyncJob").storeDurably().build();
    }

    //何时触发该job
    @Bean
    public Trigger weatherDataSyncTrigger(){
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().
                withIntervalInSeconds(TIME).repeatForever();

        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail()).
                withIdentity("weatherDataSyncTrigger").withSchedule(simpleScheduleBuilder).build();
    }
}