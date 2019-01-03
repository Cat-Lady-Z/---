package com.cskaoyan.weatherreportapp.service;

import com.cskaoyan.weatherreportapp.bean.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:  方法一通过httpclient来实现
 * User:  张娅迪
 * Date: 2018/12/25
 * Time: 下午 2:26
 * Detail requirement:
 * Method:
 */
@Service
public class WeatherApiServiceImpl implements WeatherApiService{

    @Autowired
    private RestTemplate restTemplate;

    private static final String server_uri="http://wthrcdn.etouch.cn/weather_mini";

    //方法一：原始的httpclient 通过代码请求服务器
    //http://wthrcdn.etouch.cn/weather_mini?city=北京
   /* @Override
    public WeatherResponse getWeatherInfoByCityName(String cityName) {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet("http://wthrcdn.etouch.cn/weather_mini?city=" + cityName);

        //response
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

        } catch (Exception e){}

        String temp = "";
        try {

            HttpEntity entity = response.getEntity();
            temp = EntityUtils.toString(entity,"UTF-8");

        } catch (Exception e){}

        return null;
    }
*/
    //http://wthrcdn.etouch.cn/weather_mini?citykey=101280301
    @Override
    public WeatherResponse getWeatherInfoByCityId(String cityId) throws IOException {
        WeatherResponse weatherResponse = null;

        String requestUri = server_uri + "?city=" + cityId;

        weatherResponse = getWeatherResponse(requestUri);

        return weatherResponse;
    }

    //方法二：用restTemplate
    @Override
    public WeatherResponse getWeatherInfoByCityName(String cityName) throws IOException {
        WeatherResponse weatherResponse = null;

        String requestUri = server_uri + "?city=" + cityName;

        weatherResponse = getWeatherResponse(requestUri);

        return weatherResponse;
    }

    //用redis优化，加缓存
    /**
     *@Description: 如果redis里面有这个key则直接取
     *                若没有，就去请求server
     *@Param: 
     *@return: 
     *@Author: yadi.zhang
     *@date: 
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private WeatherResponse getWeatherResponse(String requestUri) throws IOException {
        String key = requestUri;
        ObjectMapper mapper = new ObjectMapper();

        WeatherResponse weatherResponse = null;
        String jsonString = "";
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();

        //判断当前redis缓存是否有该记录
        if (stringRedisTemplate.hasKey(key)){

            //redis里面有
            jsonString = stringStringValueOperations.get(key);

            System.out.println("redis");

        } else {
            //redis里面没有
            //从服务器上面取数据
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestUri, String.class);

            if (responseEntity.getStatusCodeValue() == 200){
                jsonString = responseEntity.getBody();

                //存进redis缓存里
                stringStringValueOperations.set(key,jsonString);
                System.out.println("server");
            }
        }

        //转为对应的对象
        weatherResponse = mapper.readValue(jsonString, WeatherResponse.class);

        return weatherResponse;
    }
}