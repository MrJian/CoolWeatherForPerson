package com.coolweather.app.model;

/**
 * Created by Administrator on 2015/5/17.
 */
public class City {

    private int id;
    private String ciryName;
    private String cityCode;
    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCiryName() {
        return ciryName;
    }

    public void setCiryName(String ciryName) {
        this.ciryName = ciryName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
