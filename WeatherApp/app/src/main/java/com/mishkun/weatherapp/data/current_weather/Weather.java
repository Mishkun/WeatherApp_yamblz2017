package com.mishkun.weatherapp.data.current_weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mishkun on 14.07.2017.
 */

/*
 * JSON mapper object. See https://openweathermap.org/current#format for details
 */
class Weather {

    @SerializedName("wind")
    Wind wind;

    @SerializedName("weather")
    List<WeatherConditions> weather = null;

    @SerializedName("main")
    Main main;

    static class WeatherConditions {

        @SerializedName("id")
        int weather_id;
    }

    static public class Main {

        @SerializedName("temp")
        double temperature;

        @SerializedName("pressure")
        double pressureHPa;

        @SerializedName("humidity")
        double humidity;
    }

    static public class Wind {
        @SerializedName("speed")
        double windSpeed;
    }
}
