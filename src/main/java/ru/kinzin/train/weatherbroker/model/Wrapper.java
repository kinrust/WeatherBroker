package ru.kinzin.train.weatherbroker.model;

import java.util.List;

public class Wrapper {

    private List<Weather> weatherList;

    public Wrapper(){};

    public Wrapper(List<Weather> weatherList){
        this.weatherList = weatherList;
    }

    public List<Weather> getWeatherList() {

        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {

        this.weatherList = weatherList;
    }
}
