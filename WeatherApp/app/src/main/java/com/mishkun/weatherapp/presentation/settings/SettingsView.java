package com.mishkun.weatherapp.presentation.settings;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by Mishkun on 19.07.2017.
 */

interface SettingsView {
    Observable<Integer> getWeatherUpdateOptionCalls();
    Consumer<Integer> getWeatherUpdateOptionConsumer();
}
