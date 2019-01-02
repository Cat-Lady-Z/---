package com.cskaoyan.weatherreportapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * Description: 方法二：底层封装httpclient，通过resttemplate实现
 * User:  张娅迪
 * Date: 2018/12/25
 * Time: 下午 10:27
 * Detail requirement: 产生一个restTEMPplate的实例
 * Method:
 */
@Configuration
public class RestConfiguration {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate(){
        return builder.build();
    }
}