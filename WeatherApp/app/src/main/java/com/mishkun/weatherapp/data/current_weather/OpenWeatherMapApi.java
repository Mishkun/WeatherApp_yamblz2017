package com.mishkun.weatherapp.data.current_weather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mishkun on 14.07.2017.
 */

public interface OpenWeatherMapApi {

    @GET("weather")
    Observable<Weather> getWeather(@Query("lat") double latitude, @Query("lon") double longitude, @Query("appid") String apiKey);
}
