package com.cskaoyan.weatherreportapp.controller;

import com.cskaoyan.weatherreportapp.bean.City;
import com.cskaoyan.weatherreportapp.bean.Weather;
import com.cskaoyan.weatherreportapp.bean.WeatherResponse;
import com.cskaoyan.weatherreportapp.service.CityService;
import com.cskaoyan.weatherreportapp.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:  张娅迪
 * Date: 2019/01/03
 * Time: 上午 10:34
 * Detail requirement:
 * Method:
 */
@Controller
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    CityService cityService;

    @Autowired
    WeatherReportService weatherReportService;

    @RequestMapping("/cityId/{cityId}")
    public String getWeatherReportByCityId(@PathVariable("cityId") String cityId, Model model){
        HashMap<String, Object> reportModel = new HashMap<>();

        //1、城市列表的显示
        List<City> cityList = cityService.getAllCities();
        reportModel.put("cityList", cityList);

        reportModel.put("cityId", cityId);

        //2、标题显示
        reportModel.put("title","王道天气预报");

        //3、城市天气显示
        Weather weather = null;
        try {
            weather = weatherReportService.getWeatherInfoByCityId(cityId);

            reportModel.put("report", weather);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("get the city's weather fail");
        }

        model.addAttribute("reportModel", reportModel);

        return "/weather/report.html";
    }

    @RequestMapping("/cityName/{cityName}")
    public String getWeatherReportByCityName(@PathVariable("cityName") String cityName, Model model){
        HashMap<String, Object> reportModel = new HashMap<>();

        //1、城市列表的显示
        List<City> cityList = cityService.getAllCities();
        reportModel.put("cityList", cityList);

        String cityId = "";
        for (City city: cityList) {
            if (city.getCityName().equals(cityName)){
                cityId = city.getCityId();
                reportModel.put("cityId", cityId);
            }
        }

        //2、标题显示
        reportModel.put("title","王道天气预报");

        //3、城市天气显示
        Weather weather = null;
        try {
            weather = weatherReportService.getWeatherInfoByCityId(cityId);

            reportModel.put("report", weather);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("get the city's weather fail");
        }

        model.addAttribute("reportModel", reportModel);

        return "/weather/report.html";
    }

}