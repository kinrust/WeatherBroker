package ru.kinzin.train.weatherbroker.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.kinzin.train.weatherbroker.model.Weather;
import ru.kinzin.train.weatherbroker.model.Wrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomWeathersDeserializer extends StdDeserializer<Wrapper> {

    public CustomWeathersDeserializer() {
        this(null);
    }

    public CustomWeathersDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Wrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<Weather> weatherList = new ArrayList<>();

        ObjectCodec codec = jsonParser.getCodec();
        JsonNode root = codec.readTree(jsonParser);
        root = root.path("query").path("results").path("channel");

        JsonNode location = root.path("location");

        String  city    = location.get("city").asText().trim();
        String  country = location.get("country").asText().trim();
        String  region  = location.get("region").asText().trim();

        JsonNode forecast = root.path("item").path("forecast");
        if(forecast.isArray()){
            for(JsonNode node : forecast){
                Weather weather = new Weather(city, country, region);
                weather.setDate(node.get("date").asText().trim());
                weather.setHigh(node.get("high").asText().trim());
                weather.setLow(node.get("low").asText().trim());
                weatherList.add(weather);
            }
        }
        return new Wrapper(weatherList);
    }
}
