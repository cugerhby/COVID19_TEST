package com.example.COVID19.data;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class city {

    String cityName;
    //省份名字
    String provinceName;
    int currentConfirmedCount;
    int confirmedCount;
    int suspectedCount;
    int curedCount;
    int deadCount;
    int highDangerCount;
    int midDangerCount;
    int locationId;
    String currentConfirmedCountStr;

    public city(){

    }

    public city(ResultSet res){
        try {
            this.cityName=res.getString("cityName");
            this.provinceName=res.getString("provinceName");
            this.currentConfirmedCount=res.getInt("currentConfirmedCount");
            this.confirmedCount=res.getInt("confirmedCount");
            this.suspectedCount=res.getInt("suspectedCount");
            this.curedCount=res.getInt("curedCount");
            this.deadCount=res.getInt("deadCount");
            this.highDangerCount=res.getInt("highDangerCount");
            this.midDangerCount=res.getInt("midDangerCount");
            this.locationId=res.getInt("locationId");
            this.currentConfirmedCountStr=res.getString("currentConfirmedCountStr");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    @Test
    public void sbsb(){
        city test=new city();
        test.setCityName("1");
        test.setConfirmedCount(1);
        String s=JSON.toJSONString(test);
        System.out.println(s);
    }


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCurrentConfirmedCount() {
        return currentConfirmedCount;
    }

    public void setCurrentConfirmedCount(int currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    public int getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(int confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public int getSuspectedCount() {
        return suspectedCount;
    }

    public void setSuspectedCount(int suspectedCount) {
        this.suspectedCount = suspectedCount;
    }

    public int getCuredCount() {
        return curedCount;
    }

    public void setCuredCount(int curedCount) {
        this.curedCount = curedCount;
    }

    public int getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(int deadCount) {
        this.deadCount = deadCount;
    }

    public int getHighDangerCount() {
        return highDangerCount;
    }

    public void setHighDangerCount(int highDangerCount) {
        this.highDangerCount = highDangerCount;
    }

    public int getMidDangerCount() {
        return midDangerCount;
    }

    public void setMidDangerCount(int midDangerCount) {
        this.midDangerCount = midDangerCount;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getCurrentConfirmedCountStr() {
        return currentConfirmedCountStr;
    }

    public void setCurrentConfirmedCountStr(String currentConfirmedCountStr) {
        this.currentConfirmedCountStr = currentConfirmedCountStr;
    }





}