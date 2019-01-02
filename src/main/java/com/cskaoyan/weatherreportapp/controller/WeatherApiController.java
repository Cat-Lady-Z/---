package com.cskaoyan.weatherreportapp.controller;

import com.cskaoyan.weatherreportapp.bean.WeatherResponse;
import com.cskaoyan.weatherreportapp.service.WeatherApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:  张娅迪
 * Date: 2018/12/25
 * Time: 下午 4:18
 * Detail requirement:
 * Method:
 */
@RestController
@RequestMapping("/weather")
public class WeatherApiController {

    @Autowired
    WeatherApiService weatherApiService;

    @RequestMapping("/cityName/{cityName}")
    public WeatherResponse getWeatherInfoByCityName(@PathVariable("cityName") String cityName){
        WeatherResponse weatherResponse = null;
        try {
            weatherResponse = weatherApiService.getWeatherInfoByCityName(cityName);
        } catch (IOException e) {
            e.printStackTrace();
            weatherResponse = new WeatherResponse();
            System.out.println("cityName error");
        }

        return weatherResponse;
    }

    @RequestMapping("/cityId/{cityId}")
    public WeatherResponse getWeatherInfoByCityId(@PathVariable("cityId") String cityId){
        WeatherResponse weatherResponse = null;
        try {
            weatherResponse = weatherApiService.getWeatherInfoByCityId(cityId);
        } catch (IOException e) {
            e.printStackTrace();
            weatherResponse = new WeatherResponse();
            System.out.println("cityId error");
        }

        return weatherResponse;
    }
}