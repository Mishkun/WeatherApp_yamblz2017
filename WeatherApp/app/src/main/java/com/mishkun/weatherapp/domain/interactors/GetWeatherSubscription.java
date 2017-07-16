package com.mishkun.weatherapp.domain.interactors;

import android.support.annotation.NonNull;

import com.mishkun.weatherapp.domain.ParameterlessInteractor;
import com.mishkun.weatherapp.domain.entities.Weather;
import com.mishkun.weatherapp.domain.providers.CurrentWeatherProvider;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

import static com.mishkun.weatherapp.di.UtilsModule.JOB;
import static com.mishkun.weatherapp.di.UtilsModule.UI;

/**
 * Created by Mishkun on 14.07.2017.
 */

public class GetWeatherSubscription extends ParameterlessInteractor<Weather> {

    private final CurrentWeatherProvider currentWeatherProvider;

    @Inject
    public GetWeatherSubscription(@NonNull @Named(JOB) Scheduler threadExecutor, @NonNull @Named(UI) Scheduler postExecutionThread,
                                  @NonNull CurrentWeatherProvider currentWeatherProvider) {
        super(threadExecutor, postExecutionThread);
        this.currentWeatherProvider = currentWeatherProvider;
    }

    @Override
    public Observable<Weather> buildUseCaseObservable() {
        return currentWeatherProvider.getCurrentWeatherSubscription();
    }
}
