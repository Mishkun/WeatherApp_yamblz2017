package com.mishkun.weatherapp.domain.interactors;

import com.mishkun.weatherapp.domain.CompletableInteractor;
import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.providers.CurrentWeatherProvider;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;

/**
 * Created by Mishkun on 14.07.2017.
 */

public class UpdateWeather extends CompletableInteractor<Location> {

    private final CurrentWeatherProvider currentWeatherProvider;

    public UpdateWeather(@NonNull Scheduler threadExecutor, @NonNull Scheduler postExecutionThread,
                         @NonNull CurrentWeatherProvider currentWeatherProvider) {
        super(threadExecutor, postExecutionThread);
        this.currentWeatherProvider = currentWeatherProvider;
    }

    @Override
    public Completable buildUseCaseObservable(Location location) {
        return currentWeatherProvider.refreshData(location);
    }
}
