package com.example.covid_19.models.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.HashMap;

public class InformationCovid {

    private int confirmed ;
    private int recovered ;
    private int deaths ;
    private String country ;
    private String population ;

    @SerializedName("sq_km_area")
    private String sqkmArea;

    @SerializedName("life_expectancy")
    private String lifeExpectancy;

    @SerializedName("elevation_in_meters")
    private String elevationInMeters;

    private String continent ;
    private String abbreviation ;
    private String location ;
    private int iso ;

    @SerializedName("capital_city")
    private String capitalCity;

    private String lat ;

    @SerializedName("long")
    private String longitude  ;

    private String updated;

    private Country countryObject = null;

    private HashMap<Date, String> dates;

    public String getTypeHistory() {
        return typeHistory;
    }

    public void setTypeHistory(String typeHistory) {
        this.typeHistory = typeHistory;
    }

    private String typeHistory;


    public HashMap<Date, String> getDates() {
        return dates;
    }

    public void setDates(HashMap<Date, String> dates) {
        this.dates = dates;
    }

    public String toStringLatLong(){
        String latString = lat==null  || lat.equals("")  ? "----" : lat;
        String longitudeString = longitude == null || longitude.equals("") ? "----" : longitude;
        return latString + "," + longitudeString;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public String getCountry() {
        return country;
    }

    public String getPopulation() {
        if (population== null) return "";
        return population;
    }

    public String getSqkmArea() {
        return sqkmArea;
    }

    public String getLifeExpectancy() {
        return lifeExpectancy;
    }

    public String getElevationInMeters() {
        return elevationInMeters;
    }

    public String getContinent() {
        return continent;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getLocation() {
        return location;
    }

    public int getIso() {
        return iso;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public String getLat() {
        return lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getUpdated() {
        return updated;
    }

    public Country getCountryObject() {
        return countryObject;
    }

    public void setCountryObject(Country countryObject) {
        this.countryObject = countryObject;
    }
}
