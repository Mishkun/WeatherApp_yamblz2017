package com.mishkun.weatherapp.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Mishkun on 14.07.2017.
 */

public interface OpenWeatherMapApi {

    @GET("api.openweathermap.org/data/2.5/weather")
    Observable<Weather> getWeather(@Path("lat") double latitude, @Path("lon") double longitude, @Path("appid") String apiKey);
}
