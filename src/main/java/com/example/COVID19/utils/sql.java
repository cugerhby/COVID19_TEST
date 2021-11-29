package com.example.COVID19.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.COVID19.data.city;
import com.example.COVID19.data.province;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.COVID19.utils.JsonUtils.getJsonArrayFromHtml;

//本类为执行sql语句的类，主要用来向数据库中添加数据，取出数据，并且本类包含一个取出JSONArray中的JsonObject的方法。
public class sql {
    //test pass 数据已经取出

    //更新数据库的操作
    @Test
    public void testout(){
        JSONArray jsonArray=getJsonArrayFromHtml("https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline", "script#getAreaStat");
        for (int i=0;i<jsonArray.size();i++){
            province a=getProvinceFromJson(jsonArray,i);
            System.out.println(a);
        }
    }

    //获取province后续用来存数据和向前端传输数据
    public static province getProvinceFromJson(JSONArray jsonArray,int index){
        JSONObject jsonObject= (JSONObject) jsonArray.get(index);
        //返回的数据，里面要套嵌city数据。
        province pro=new province();
        pro.setComment(jsonObject.getString("comment"));
        pro.setConfirmedCount(jsonObject.getInteger("confirmedCount"));
        pro.setCuredCount(jsonObject.getInteger("curedCount"));
        pro.setProvinceName(jsonObject.getString("provinceName"));
        pro.setProvinceShortName("provinceShortName");
        pro.setDeadCount(jsonObject.getInteger("deadCount"));
        pro.setDetectOrgCount(jsonObject.getInteger("detectOrgCount"));
        pro.setHighDangerCount(jsonObject.getInteger("highDangerCount"));
        pro.setVaccinationOrgCount(jsonObject.getInteger("vaccinationOrgCount"));
        pro.setLocationId(jsonObject.getInteger("locationId"));
        pro.setSuspectedCount(jsonObject.getInteger("suspectedCount"));
        pro.setMidDangerCount(jsonObject.getInteger("midDangerCount"));
        pro.setCurrentConfirmedCount(jsonObject.getInteger("currentConfirmedCount"));
        //以下是cities的数据 传入的参数为获取到的arraylist数据，接收返回值设置就可以了

        List<city> cities=getCitiesFromJsonArray(jsonObject.getJSONArray("cities"),jsonObject.getString("provinceName"));
        pro.setCitys(cities);
        return pro;
    }


    //本方法为从JsonArray中获取city数据
    private static List<city> getCitiesFromJsonArray(JSONArray jsonArray,String provinceName){
        //下面会获取cities数据，然后存到province的cities中去
        ArrayList<city> res=new ArrayList<>();
        JSONObject jsonObject=null;
        city ci=null;
        for(int i=0;i<jsonArray.size();i++) {
            ci=new city();
            jsonObject= (JSONObject) jsonArray.get(i);
            ci.setProvinceName(provinceName);
            ci.setCityName(jsonObject.getString("cityName"));
            ci.setCurrentConfirmedCount(jsonObject.getInteger("currentConfirmedCount"));
            ci.setConfirmedCount(jsonObject.getInteger("confirmedCount"));
            ci.setSuspectedCount(jsonObject.getInteger("suspectedCount"));
            ci.setCuredCount(jsonObject.getInteger("curedCount"));
            ci.setDeadCount(jsonObject.getInteger("deadCount"));
            ci.setHighDangerCount(jsonObject.getInteger("highDangerCount"));
            ci.setMidDangerCount(jsonObject.getInteger("midDangerCount"));
            ci.setLocationId(jsonObject.getInteger("locationId"));
            ci.setCurrentConfirmedCountStr(jsonObject.getString("currentConfirmedCountStr"));
            res.add(ci);
        }
    return res;
    }






}
