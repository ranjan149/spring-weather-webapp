package org.example.model;

public class WeatherResponse {

    private int id;
    private String name;
    private String description;
    private double temperature;


    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setTemperature(double temperature){
        this.temperature = temperature;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public double getTemperature(){
        return this.temperature;
    }
}
