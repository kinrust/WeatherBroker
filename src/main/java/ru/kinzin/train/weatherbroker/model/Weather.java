package ru.kinzin.train.weatherbroker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(WeatherPK.class)
@Table(name = "forecast")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable {

    public Weather() {
    }

    public Weather(String city, String country, String region) {
        this.city = city;
        this.country = country;
        this.region = region;
    }

    @Id
    private String city;
    @Id
    private String country;
    @Id
    private String region;
    @Id
    private String date;
    private String high;
    private String low;

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", date='" + date + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(city, weather.city) &&
                Objects.equals(country, weather.country) &&
                Objects.equals(region, weather.region) &&
                Objects.equals(date, weather.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(city, country, region, date);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}