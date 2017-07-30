package com.mishkun.weatherapp.domain.interactors;

import android.util.Log;

import com.mishkun.weatherapp.domain.CompletableInteractor;
import com.mishkun.weatherapp.domain.CompletableParameterlessInteractor;
import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.outerworld.CurrentWeatherProvider;
import com.mishkun.weatherapp.domain.repositories.CityInfoRepository;

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

public class UpdateWeather extends CompletableParameterlessInteractor {

    private static final String TAG = UpdateWeather.class.getSimpleName();
    private final CurrentWeatherProvider currentWeatherProvider;
    private final CityInfoRepository cityInfoRepository;

    @Inject
    public UpdateWeather(@NonNull @Named(JOB) Scheduler threadExecutor, @NonNull @Named(UI) Scheduler postExecutionThread,
                         @NonNull CurrentWeatherProvider currentWeatherProvider, CityInfoRepository cityInfoRepository) {
        super(threadExecutor, postExecutionThread);
        this.currentWeatherProvider = currentWeatherProvider;
        this.cityInfoRepository = cityInfoRepository;
    }

    @Override
    public Completable buildUseCaseCompletable() {
        return cityInfoRepository.getCityCoordinate().flatMapCompletable(currentWeatherProvider::refreshData);
    }
}
