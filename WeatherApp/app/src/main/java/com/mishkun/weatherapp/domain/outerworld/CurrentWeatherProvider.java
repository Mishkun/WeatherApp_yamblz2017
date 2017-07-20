package com.mishkun.weatherapp.domain.outerworld;

import android.support.annotation.NonNull;

import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.entities.Weather;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Mishkun on 13.07.2017.
 */

public interface CurrentWeatherProvider {
    Completable refreshData(@NonNull Location location);
    Observable<Weather> getCurrentWeatherSubscription();
}
