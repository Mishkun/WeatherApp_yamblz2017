package com.mishkun.weatherapp.di;

import com.mishkun.weatherapp.infrastructure.WeatherApplication;
import com.mishkun.weatherapp.presentation.suggest.SuggestFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mishkun on 16.07.2017.
 */
@Singleton
@Component(modules = {AppModule.class, DomainModule.class, DataModule.class, WeatherSchedulerModule.class})
public interface AppComponent {
    WeatherScreenComponent weatherScreenComponent();
    SettingsScreenComponent settingsScreenComponent();
    void inject(SuggestFragment suggestFragment);
    void inject(WeatherApplication weatherApplication);
}
