package com.mishkun.weatherapp.domain.outerworld;

import io.reactivex.Completable;

/**
 * Created by Mishkun on 19.07.2017.
 */

public interface WeatherUpdatesScheduler {
    Completable scheduleWeatherUpdates(long millis);
}
