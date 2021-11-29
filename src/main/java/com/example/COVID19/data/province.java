package com.example.COVID19.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.COVID19.utils.MysqlUtils.cut;

public class province {

    String provinceName;
    String provinceShortName;
    int currentConfirmedCount;
    int confirmedCount;
    int suspectedCount;
    int curedCount;
    int deadCount;
    String comment;
    int locationId;
    int highDangerCount;
    int midDangerCount;
    int detectOrgCount;
    int vaccinationOrgCount;
    List<city> citys=null;//记录array中的citys的数据

    public province(){

    }


    public province(ResultSet res,List<city> list){
        try {
            this.provinceName=cut(res.getString("provinceName"));
            this.provinceShortName=res.getString("provinceShortName");
            this.currentConfirmedCount=res.getInt("currentConfirmedCount");
            this.confirmedCount=res.getInt("confirmedCount");
            this.suspectedCount=res.getInt("suspectedCount");
            this.curedCount=res.getInt("curedCount");
            this.deadCount=res.getInt("deadCount");
            this.comment=res.getString("comment");
            this.locationId=res.getInt("locationId");
            this.highDangerCount=res.getInt("highDangerCount");
            this.midDangerCount=res.getInt("midDangerCount");
            this.detectOrgCount=res.getInt("detectOrgCount");
            this.vaccinationOrgCount=res.getInt("vaccinationOrgCount");
            this.citys=list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "province{" +
                "provinceName='" + provinceName + '\'' +
                ", provinceShortName='" + provinceShortName + '\'' +
                ", currentConfirmedCount=" + currentConfirmedCount +
                ", confirmedCount=" + confirmedCount +
                ", suspectedCount=" + suspectedCount +
                ", curedCount=" + curedCount +
                ", deadCount=" + deadCount +
                ", comment='" + comment + '\'' +
                ", locationId=" + locationId +
                ", highDangerCount=" + highDangerCount +
                ", midDangerCount=" + midDangerCount +
                ", detectOrgCount=" + detectOrgCount +
                ", vaccinationOrgCount=" + vaccinationOrgCount +
                ", citys=" + citys +
                '}';
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceShortName() {
        return provinceShortName;
    }

    public void setProvinceShortName(String provinceShortName) {
        this.provinceShortName = provinceShortName;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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

    public int getDetectOrgCount() {
        return detectOrgCount;
    }

    public void setDetectOrgCount(int detectOrgCount) {
        this.detectOrgCount = detectOrgCount;
    }

    public int getVaccinationOrgCount() {
        return vaccinationOrgCount;
    }

    public void setVaccinationOrgCount(int vaccinationOrgCount) {
        this.vaccinationOrgCount = vaccinationOrgCount;
    }

    public void setCitys(List<city> citys) {
        this.citys = citys;
    }

    public List<city> getCitys() {
        return citys;
    }
}
