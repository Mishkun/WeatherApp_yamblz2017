package com.mishkun.weatherapp.data.current_weather;

import android.support.annotation.NonNull;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.entities.Temperature;
import com.mishkun.weatherapp.domain.entities.Weather;
import com.mishkun.weatherapp.domain.entities.WeatherConditions;
import com.mishkun.weatherapp.domain.providers.CurrentWeatherProvider;

import java.util.Date;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Mishkun on 14.07.2017.
 */

public class OpenWeatherMapCurrentWeatherRepository implements CurrentWeatherProvider {
    private final String API_KEY = "a94b47f30f78afba43ac68effc69a24a";
    private final OpenWeatherMapApi openWeatherMapApi;

    // to not to include room or other ORM now, I use this dirty hack
    private final BehaviorRelay<Weather> weatherBehaviorSubject;

    public OpenWeatherMapCurrentWeatherRepository(@NonNull OpenWeatherMapApi openWeatherMapApi) {
        this.openWeatherMapApi = openWeatherMapApi;
        weatherBehaviorSubject = BehaviorRelay.createDefault(getDefaultWeather());
    }

    private Weather getDefaultWeather() {
        return new Weather(new Temperature(22), 90, 769, WeatherConditions.CLEAR, 0, 1500046530);
    }

    @Override
    public Completable refreshData(@NonNull Location location) {
        return Completable.fromObservable(openWeatherMapApi.getWeather(location.getLatitude(), location.getLongitude(), API_KEY)
                                                           .map((weather) -> WeatherMapper.toDomain(weather, new Date().getTime()))
                                                           .doOnNext(weatherBehaviorSubject));
    }

    @Override
    public Observable<Weather> getCurrentWeatherSubscription() {
        return weatherBehaviorSubject;
    }
}
