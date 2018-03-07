package ru.kinzin.train.weatherbroker.dao;

import ru.kinzin.train.weatherbroker.model.Weather;
import ru.kinzin.train.weatherbroker.model.WeatherPK;

import java.util.List;

public interface WeatherDAO {
    public void addWeather(Weather weather);
    public Weather getWeatherById(WeatherPK weatherPK);
    public List<Weather> getAllWeathers();
}
