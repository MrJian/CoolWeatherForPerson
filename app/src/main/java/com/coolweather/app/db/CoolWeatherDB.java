package com.coolweather.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/17.
 */
public class CoolWeatherDB {

    public static final String DB_Name = "cool_weather";

    public static final int VERSION = 1;

    private static CoolWeatherDB sCoolWeatherDB;

    private SQLiteDatabase db;

    public CoolWeatherDB(Context context) {

        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_Name, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取CoolWeatherDB实例
     *
     * @param context
     * @return
     */
    public synchronized static CoolWeatherDB getInStance(Context context) {
        if (sCoolWeatherDB == null) {
            sCoolWeatherDB = new CoolWeatherDB(context);
        }
        return sCoolWeatherDB;
    }

    /**
     * 插入Province 表的数据
     *
     * @param province
     */
    public void saveProvince(Province province) {

        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvincename());
            values.put("province_code", province.getProvincecode());
            db.insert("Province", null, values);
        }
    }

    /**
     * 读取Province 表数据
     *
     * @return
     */
    private List<Province> loadProvince() {

        List<Province> list = new ArrayList<>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvincename(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvincecode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return list;
    }

    /**
     * 将City 实例储存到数据库
     */
    public void saveCity(City city) {

        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCiryName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }

    /**
     * 从数据库中读取某省的城市信息
     */
    public List<City> loadCities(int provinceId) {

        List<City> list = new ArrayList<>();
        Cursor cursor = db.query("City", null, "province_id=?", new String[]{String.valueOf(provinceId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCiryName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * 将County 实例储存到数据库
     */
    public void saveCounty(County county){

        if (county!=null){
            ContentValues values = new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountCode());
            values.put("city_id",county.getCityId());
            db.insert("County",null,values);
        }
    }

    /**
     * 从数据库读取某城市下所有的县信息
     */
    public List<County> loadCounties(int cityId){

        List<County> list = new ArrayList<>();
        Cursor cursor = db.query("County",null,"city_id = ?",new String[]{String.valueOf(cityId)},null,null,null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;

    }
}
