package com.example.covid_19.models.dtos;

import com.google.gson.annotations.SerializedName;

public class InformationCovid {

    private int confirmed ;
    private int recovered ;
    private int deaths ;
    private String country ;
    private int population ;

    @SerializedName("sq_km_area")
    private int sqkmArea;

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

    public int getPopulation() {
        return population;
    }

    public int getSqkmArea() {
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
