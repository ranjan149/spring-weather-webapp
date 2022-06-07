package org.example.service.impl;

import org.example.model.WeatherResponse;
import org.example.service.WeatherService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${openweather.web.link}")
    private String url;

    protected String getUri(List<Integer> ids, String metrics, String appid){
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(url)
                .path("data/2.5/group")
                .queryParam("id",  ids.stream().map(Object::toString).collect(Collectors.joining(",")))
                .queryParam("units", metrics)
                .queryParam("appid", appid).toUriString();
    }

    @Override
    public List<WeatherResponse> getWeatherInformation(List<Integer> ids, String metrics, String appid){

        String weatherResponse1 =  restTemplate.getForObject(getUri(ids, metrics, appid), String.class);
        JSONObject jo = new JSONObject(weatherResponse1);
        JSONArray jsonArray = jo.getJSONArray("list");
        List<WeatherResponse> weatherResponses = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            WeatherResponse weatherResponse = new WeatherResponse();

            String weatherInfo = jsonArray.get(i).toString();
            JSONObject innerJsonObject = new JSONObject(weatherInfo);
            JSONArray innerJsonArray = innerJsonObject.getJSONArray("weather");

            weatherResponse.setId( Integer.parseInt(innerJsonObject.get("id").toString()));
            weatherResponse.setDescription(innerJsonArray.getJSONObject(0).get("description").toString());
            weatherResponse.setName(innerJsonObject.get("name").toString());
            weatherResponse.setTemperature(Double.parseDouble(innerJsonObject.getJSONObject("main").get("temp").toString()));

            weatherResponses.add(weatherResponse);
        }
        return weatherResponses;

    }

}
