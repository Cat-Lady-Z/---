package com.cskaoyan.weatherreportapp.service;

import com.cskaoyan.weatherreportapp.bean.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:  张娅迪
 * Date: 2019/01/03
 * Time: 上午 11:38
 * Detail requirement:
 * Method:
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    WeatherApiService weatherApiService;

    @Override
    public Weather getWeatherInfoByCityId(String cityId) throws IOException {
        return weatherApiService.getWeatherInfoByCityId(cityId).getData();
    }
}