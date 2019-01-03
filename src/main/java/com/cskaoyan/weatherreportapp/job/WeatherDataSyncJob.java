package com.cskaoyan.weatherreportapp.job;

import com.cskaoyan.weatherreportapp.bean.City;
import com.cskaoyan.weatherreportapp.service.CityService;
import com.cskaoyan.weatherreportapp.service.WeatherApiService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * Description: 实现任务类
 * User:  张娅迪
 * Date: 2019/01/02
 * Time: 下午 3:30
 * Detail requirement:
 * Method:
 */
@Component
public class WeatherDataSyncJob extends QuartzJobBean {

    //private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    /*@Autowired
    private CityDataService cityDataService;*/
    private static final String server_uri="http://wthrcdn.etouch.cn/weather_mini";

    @Autowired
    private WeatherApiService weatherApiService;

    @Autowired
    private CityService cityService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<City> cities = cityService.getAllCities();
        String requestUri = "";
        String key = "";
        String key2 = "";

        String jsonString = "";
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();

        for (City city:cities) {
            requestUri = server_uri + "?city=" + city.getCityName();
            key = requestUri;
            key2 = server_uri + "?city=" + city.getCityId();

            ResponseEntity<String>  responseEntity = restTemplate.getForEntity(requestUri, String.class);

            if (responseEntity.getStatusCodeValue() == 200){
                jsonString = responseEntity.getBody();

                //存进redis缓存里
                stringStringValueOperations.set(key,jsonString);

                //牺牲空间再存一个key
                stringStringValueOperations.set(key2,jsonString);
            }
        }

        System.out.println("getdatafromserver , end");

    }
}