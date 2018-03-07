package ru.kinzin.train.weatherbroker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinzin.train.weatherbroker.model.Weather;
import ru.kinzin.train.weatherbroker.service.WeatherServiceImpl;

import java.util.List;

@Controller
public class WeatherController {

    private WeatherServiceImpl weatherService;

    @Autowired
    public void setWeatherService(WeatherServiceImpl weatherService) {

        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {

        List<Weather> weatherList = weatherService.getAllWeathers();
        model.addAttribute("weathers", weatherList);
        return "index";
    }

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public String addWeather(@RequestParam(value = "name") String name, Model model) {
        weatherService.addWeather(name);

        List<Weather> weatherList = weatherService.getAllWeathers();
        model.addAttribute("weathers", weatherList);
        return "index";
    }
}
