package com.cskaoyan.weatherreportapp.service;

import com.cskaoyan.weatherreportapp.bean.City;
import com.cskaoyan.weatherreportapp.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:  张娅迪
 * Date: 2019/01/02
 * Time: 下午 4:18
 * Detail requirement:
 * Method:
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityDao cityDao;

    @Override
    public List<City> getAllCities() {
        return cityDao.queryAllCities();
    }
}