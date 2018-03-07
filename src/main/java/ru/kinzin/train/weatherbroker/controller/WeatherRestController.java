package ru.kinzin.train.weatherbroker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.kinzin.train.weatherbroker.model.Weather;
import ru.kinzin.train.weatherbroker.model.WeatherPK;
import ru.kinzin.train.weatherbroker.service.WeatherServiceImpl;

import java.util.List;

@RestController
public class WeatherRestController {

    private WeatherServiceImpl weatherService;

    @Autowired
    public void setWeatherService(WeatherServiceImpl weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(
            value = "/weatherslist",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public List<Weather> getAllWeathers() {

        List<Weather> weathersList = weatherService.getAllWeathers();
        return weathersList;
    }


    @RequestMapping(
            value = "/weather/{city}/{country}/{region}/{date}",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public Weather getWeatherById(
            @PathVariable String city,
            @PathVariable String country,
            @PathVariable String region,
            @PathVariable String date) {

        WeatherPK weatherPK = new WeatherPK(city, country, region, date);
        Weather weather = weatherService.getWeatherById(weatherPK);

        return weather;
    }
}
