package com.mishkun.weatherapp.presentation.home;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;
import com.mishkun.weatherapp.di.WeatherScreen;
import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.entities.Weather;
import com.mishkun.weatherapp.domain.interactors.GetWeatherSubscription;
import com.mishkun.weatherapp.domain.interactors.UpdateWeather;
import com.mishkun.weatherapp.presentation.RxPresenter;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Mishkun on 15.07.2017.
 */
@WeatherScreen
public class WeatherRxPresenter extends RxPresenter<WeatherView> {
    private static final String TAG = WeatherRxPresenter.class.getSimpleName();
    private final GetWeatherSubscription getWeatherSubscription;
    private final UpdateWeather updateWeather;
    private final WeatherMapper weatherMapper;
    private BehaviorRelay<Weather> weatherStatus;
    private BehaviorRelay<Boolean> loadingStatus;
    private PublishRelay<String> errorMessages;
    // TODO - add location logic here, now it is harcoded to fit Moscow
    private Location currentLocation = new Location(55.75222, 37.61556);

    @Inject
    WeatherRxPresenter(GetWeatherSubscription getWeatherSubscription, UpdateWeather updateWeather,
                       WeatherMapper weatherMapper) {
        this.getWeatherSubscription = getWeatherSubscription;
        this.weatherMapper = weatherMapper;
        weatherStatus = BehaviorRelay.create();
        loadingStatus = BehaviorRelay.createDefault(Boolean.FALSE);
        errorMessages = PublishRelay.create();
        this.updateWeather = updateWeather;
        this.getWeatherSubscription.run().subscribe(weatherStatus);
    }


    @Override
    protected void onAttach() {
        Completable weatherRefreshSubscription = view.getRefreshCalls()
                                                     .filter((ignore) -> !loadingStatus.getValue())
                                                     .doOnNext((ignore) -> loadingStatus.accept(true))
                                                     .flatMapCompletable((ignore) -> updateWeather.run(currentLocation))
                                                     .doFinally(() -> loadingStatus.accept(false));
        addSubscription(weatherRefreshSubscription.subscribe(() -> loadingStatus.accept(false),
                                                             (error) -> errorMessages.accept(error.getLocalizedMessage())));
        addSubscription(weatherStatus.hide().map(weatherMapper::toWeatherViewModel).subscribe(view.getWeatherConsumer()));
        addSubscription(loadingStatus.subscribe(view.getLoadingStatusConsumer()));
        addSubscription(errorMessages.subscribe(view.getErrorConsumer()));
    }

    @Override
    protected void onDetach() {
    }
}
