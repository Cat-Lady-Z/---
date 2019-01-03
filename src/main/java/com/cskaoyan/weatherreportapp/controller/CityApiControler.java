package com.cskaoyan.weatherreportapp.controller;

import com.cskaoyan.weatherreportapp.bean.City;
import com.cskaoyan.weatherreportapp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:  张娅迪
 * Date: 2019/01/02
 * Time: 下午 4:13
 * Detail requirement:
 * Method:
 */
@RestController
public class CityApiControler {
    
    @Autowired
    CityService cityService;

    @GetMapping("/listCities")
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }
}