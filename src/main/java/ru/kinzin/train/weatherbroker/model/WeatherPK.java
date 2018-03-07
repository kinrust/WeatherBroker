package ru.kinzin.train.weatherbroker.model;

import java.io.Serializable;
import java.util.Objects;

public class WeatherPK implements Serializable{

    protected String city;
    protected String country;
    protected String region;
    protected String date;

    public WeatherPK(){}

    public WeatherPK(String city, String country, String region, String date){
        this.city = city;
        this.country = country;
        this.region = region;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherPK weatherPK = (WeatherPK) o;
        return Objects.equals(city, weatherPK.city) &&
                Objects.equals(country, weatherPK.country) &&
                Objects.equals(region, weatherPK.region) &&
                Objects.equals(date, weatherPK.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(city, country, region, date);
    }
}
