package com.mishkun.weatherapp.di;

import android.content.Context;

import com.mishkun.weatherapp.data.current_weather.OpenWeatherMapApi;
import com.mishkun.weatherapp.data.current_weather.OpenWeatherMapCurrentWeatherRepository;
import com.mishkun.weatherapp.domain.providers.CurrentWeatherProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mishkun on 16.07.2017.
 */
@Module
public class CurrentWeatherModule {


    @Provides
    @Singleton
    public OpenWeatherMapApi provideOpenWeatherMapApi() {
        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/").addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherMapApi.class);
    }

    @Provides
    @Singleton
    public CurrentWeatherProvider provideCurrentWeather(OpenWeatherMapApi openWeatherMapApi) {
        return new OpenWeatherMapCurrentWeatherRepository(openWeatherMapApi, WeatherApplication.context);
    }
}
