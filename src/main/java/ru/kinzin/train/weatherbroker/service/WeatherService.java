package ru.kinzin.train.weatherbroker.service;

import ru.kinzin.train.weatherbroker.model.Weather;
import ru.kinzin.train.weatherbroker.model.WeatherPK;

import java.util.List;

public interface WeatherService {
    public void addWeather(String name);
    public Weather getWeatherById(WeatherPK weatherPK);
    public List<Weather> getAllWeathers();
}
