package com.mishkun.weatherapp.domain.interactors;

import com.mishkun.weatherapp.di.UtilsModule;
import com.mishkun.weatherapp.domain.CompletableInteractor;
import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.providers.CurrentWeatherProvider;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;

import static com.mishkun.weatherapp.di.UtilsModule.JOB;
import static com.mishkun.weatherapp.di.UtilsModule.UI;

/**
 * Created by Mishkun on 14.07.2017.
 */

public class UpdateWeather extends CompletableInteractor<Location> {

    private final CurrentWeatherProvider currentWeatherProvider;

    @Inject
    public UpdateWeather(@NonNull @Named(JOB) Scheduler threadExecutor, @NonNull @Named(UI) Scheduler postExecutionThread,
                         @NonNull CurrentWeatherProvider currentWeatherProvider) {
        super(threadExecutor, postExecutionThread);
        this.currentWeatherProvider = currentWeatherProvider;
    }

    @Override
    public Completable buildUseCaseObservable(Location location) {
        return currentWeatherProvider.refreshData(location);
    }
}
