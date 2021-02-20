package com.example.covid_19.models.dtos;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Country implements Serializable {
    private String name;
    private String region;
    private String flag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
