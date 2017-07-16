package com.mishkun.weatherapp.presentation;
import com.mishkun.weatherapp.domain.entities.Weather;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by Mishkun on 15.07.2017.
 */

public interface WeatherView {
    Observable<Object> getRefreshCalls();
    Consumer<Weather> getWeatherConsumer();
    Consumer<String> getErrorConsumer();
    Consumer<Boolean> getLoadingStatusConsumer();
}
