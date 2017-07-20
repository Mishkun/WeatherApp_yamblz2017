package com.mishkun.weatherapp.domain.interactors;

import android.util.Log;

import com.mishkun.weatherapp.domain.CompletableInteractor;
import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.outerworld.CurrentWeatherProvider;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;

import static com.mishkun.weatherapp.di.NamedConsts.JOB;
import static com.mishkun.weatherapp.di.NamedConsts.UI;

/**
 * Created by Mishkun on 14.07.2017.
 */

public class UpdateWeather extends CompletableInteractor<Location> {

    private static final String TAG = UpdateWeather.class.getSimpleName();
    private final CurrentWeatherProvider currentWeatherProvider;

    @Inject
    public UpdateWeather(@NonNull @Named(JOB) Scheduler threadExecutor, @NonNull @Named(UI) Scheduler postExecutionThread,
                         @NonNull CurrentWeatherProvider currentWeatherProvider) {
        super(threadExecutor, postExecutionThread);
        this.currentWeatherProvider = currentWeatherProvider;
    }

    @Override
    public Completable buildUseCaseCompletable(Location location) {
        return currentWeatherProvider.refreshData(location);
    }
}
