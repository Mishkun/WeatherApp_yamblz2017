package com.mishkun.weatherapp.presentation;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;
import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.entities.Weather;
import com.mishkun.weatherapp.domain.interactors.GetWeatherSubscription;
import com.mishkun.weatherapp.domain.interactors.UpdateWeather;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Mishkun on 15.07.2017.
 */
@Singleton
public class WeatherRxPresenter extends RxPresenter<WeatherView> {
    private static final String TAG = WeatherRxPresenter.class.getSimpleName();
    private final GetWeatherSubscription getWeatherSubscription;
    private final UpdateWeather updateWeather;

    private BehaviorRelay<Weather> weatherStatus;
    private BehaviorRelay<Boolean> loadingStatus;
    private PublishRelay<String> errorMessages;

    // TODO - add location logic here, now it is harcoded to fit Moscow
    private Location currentLocation = new Location(55.75222, 37.61556);

    @Inject
    public WeatherRxPresenter(GetWeatherSubscription getWeatherSubscription, UpdateWeather updateWeather) {
        super();
        this.getWeatherSubscription = getWeatherSubscription;
        weatherStatus = BehaviorRelay.create();
        loadingStatus = BehaviorRelay.createDefault(Boolean.FALSE);
        errorMessages = PublishRelay.create();
        this.updateWeather = updateWeather;
        this.getWeatherSubscription.get().subscribe(weatherStatus);
    }


    @Override
    void onAttach() {
        Observable<Object> weatherRefhreshSubscription = view.getRefreshCalls().filter((ignore) -> !loadingStatus.getValue())
                                                             .doOnNext((ignore) -> loadingStatus.accept(true))
                                                             .flatMap(
                                                                     (ignore) -> updateWeather.get(currentLocation)
                                                                                              .doOnComplete(() -> loadingStatus.accept(false))
                                                                                              .toObservable())

                                                             .doOnError((ignore) -> errorMessages.accept("error"));
        addSubscription(weatherRefhreshSubscription.subscribe());
        addSubscription(weatherStatus.subscribe(view.getWeatherConsumer()));
    }

    @Override
    void onDetach() {
    }
}
