package com.mishkun.weatherapp.di;

import com.mishkun.weatherapp.infrastructure.WeatherApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mishkun on 16.07.2017.
 */
@Singleton
@Component(modules = {AppModule.class, DomainModule.class, CurrentWeatherDataModule.class, WeatherSchedulerModule.class})
public interface AppComponent {
    WeatherScreenComponent weatherScreenComponent();
    SettingsScreenComponent settingsScreenComponent();

    void inject(WeatherApplication weatherApplication);
}
