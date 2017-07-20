package com.mishkun.weatherapp.presentation.home;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;
import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.entities.Weather;
import com.mishkun.weatherapp.domain.interactors.GetWeatherSubscription;
import com.mishkun.weatherapp.domain.interactors.UpdateWeather;
import com.mishkun.weatherapp.presentation.RxPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;

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
    WeatherRxPresenter(GetWeatherSubscription getWeatherSubscription, UpdateWeather updateWeather) {
        this.getWeatherSubscription = getWeatherSubscription;
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
                                                     .flatMapCompletable((ignore) -> updateWeather.run(currentLocation)
                                                                                                  .doOnComplete(() -> loadingStatus.accept(false))
                                                                                                  .doOnError(
                                                                                                          (error) -> errorMessages.accept("error")));

        addSubscription(weatherRefreshSubscription.subscribe());
        addSubscription(weatherStatus.subscribe(view.getWeatherConsumer()));
        addSubscription(loadingStatus.subscribe(view.getLoadingStatusConsumer()));
        addSubscription(errorMessages.subscribe(view.getErrorConsumer()));
    }

    @Override
    protected void onDetach() {
    }
}
