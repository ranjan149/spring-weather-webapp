package org.example.service;

import org.example.model.WeatherResponse;

import java.util.List;

public interface WeatherService {

     List<WeatherResponse> getWeatherInformation(List<Integer> ids, String metrics, String appid) throws Exception;

}
