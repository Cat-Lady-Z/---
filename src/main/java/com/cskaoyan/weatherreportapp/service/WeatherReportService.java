package com.cskaoyan.weatherreportapp.service;

import com.cskaoyan.weatherreportapp.bean.Weather;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:  张娅迪
 * Date: 2019/01/03
 * Time: 上午 11:36
 * Detail requirement:
 * Method:
 */
public interface WeatherReportService {
    Weather getWeatherInfoByCityId(String cityId) throws IOException;
}
