package com.cskaoyan.weatherreportapp.dao;

import com.cskaoyan.weatherreportapp.bean.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:  张娅迪
 * Date: 2019/01/02
 * Time: 下午 4:19
 * Detail requirement:
 * Method:
 */
@Mapper
public interface CityDao {

    @Select("select *from city")
    List<City> queryAllCities();
}