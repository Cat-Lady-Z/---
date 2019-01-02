package com.cskaoyan.weatherreportapp.bean;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:  张娅迪
 * Date: 2018/12/25
 * Time: 上午 9:18
 * Detail requirement:
 * Method:
 */
public class WeatherResponse {
    private Weather data;
    private String desc;
    private int status;

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}