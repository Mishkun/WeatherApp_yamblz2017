package com.mishkun.weatherapp.domain.interactors;

import android.support.annotation.NonNull;

import com.mishkun.weatherapp.domain.ParameterlessInteractor;
import com.mishkun.weatherapp.domain.entities.Weather;
import com.mishkun.weatherapp.domain.providers.CurrentWeatherProvider;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Mishkun on 14.07.2017.
 */

public class GetWeatherSubscription extends ParameterlessInteractor<Weather> {

    private final CurrentWeatherProvider currentWeatherProvider;

    public GetWeatherSubscription(@NonNull Scheduler threadExecutor, @NonNull Scheduler postExecutionThread,
                                  @NonNull CurrentWeatherProvider currentWeatherProvider) {
        super(threadExecutor, postExecutionThread);
        this.currentWeatherProvider = currentWeatherProvider;
    }

    @Override
    protected Observable<Weather> buildUseCaseObservable() {
        return currentWeatherProvider.getCurrentWeatherSubscription();
    }
}
