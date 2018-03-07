package ru.kinzin.train.weatherbroker.service;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kinzin.train.weatherbroker.dao.WeatherDAO;
import ru.kinzin.train.weatherbroker.model.Weather;
import ru.kinzin.train.weatherbroker.model.Wrapper;
import ru.kinzin.train.weatherbroker.model.WeatherPK;

import java.io.IOException;
import java.util.List;


@Service
public class WeatherServiceImpl implements WeatherService{

    private WeatherDAO weatherDAO;

    @Autowired
    public void setWeatherDAO(WeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
    }

    @Override
    public void addWeather(String name){
        List<Weather> weatherList = findWeather(name);
        for(Weather weather : weatherList){
            weatherDAO.addWeather(weather);
        }
        //model.addAttribute("weathers", weatherList);
    }

    @Override
    public List<Weather> getAllWeathers(){
        List<Weather> weatherList = weatherDAO.getAllWeathers();
        return weatherList;
    }

    @Override
    public Weather getWeatherById(WeatherPK weatherPK){
        Weather weather = weatherDAO.getWeatherById(weatherPK);
        return weather;
    }

    private List<Weather> findWeather(String name) {

        String jsonStr = new RestTemplate().getForObject("https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in " +
                "(select woeid from geo.places(1) where text =\"" + name + "\")&format=json", String.class);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomWeathersDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Wrapper.class, new CustomWeathersDeserializer());
        mapper.registerModule(module);
        Wrapper weatherList = null;
        try {
            weatherList = mapper.readValue(jsonStr, Wrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return weatherList.getWeatherList();
    }
}
