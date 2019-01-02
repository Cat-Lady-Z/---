package com.cskaoyan.weatherreportapp.service;

import com.cskaoyan.weatherreportapp.bean.WeatherResponse;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:  张娅迪
 * Date: 2018/12/25
 * Time: 上午 9:44
 * Detail requirement:
 * Method:
 */
public interface WeatherApiService {
    WeatherResponse getWeatherInfoByCityName(String cityName) throws IOException;

    WeatherResponse getWeatherInfoByCityId(String cityId) throws IOException;
}