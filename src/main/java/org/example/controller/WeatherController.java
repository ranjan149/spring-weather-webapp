package org.example.controller;

import org.example.model.WeatherResponse;
import org.example.service.WeatherService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Value("classpath:cities.json")
    Resource resource;

    @GetMapping("/data/2.5/group")
    @Cacheable("weather_information")
    public ModelAndView getWeatherInformation(@RequestParam("units") String units, @RequestParam("appid") String appid) throws Exception {

        ModelAndView mv = new ModelAndView();
        String cities = new String(resource.getInputStream().readAllBytes());
        JSONObject jo = new JSONObject(cities);
        JSONArray jsonArray = jo.getJSONArray("List");
        List<Integer> cities_codes = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            cities_codes.add(Integer.parseInt(jsonArray.getJSONObject(i).get("CityCode").toString()));
        }
        List<WeatherResponse> responses = weatherService.getWeatherInformation(cities_codes, units, appid);
        mv.addObject("responses", responses);
        mv.setViewName("weather-list");
        return mv;
    }
}
