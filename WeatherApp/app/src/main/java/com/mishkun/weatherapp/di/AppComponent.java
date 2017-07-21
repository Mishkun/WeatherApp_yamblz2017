package com.mishkun.weatherapp.di;

import android.content.Context;

import com.mishkun.weatherapp.HomeActivity;
import com.mishkun.weatherapp.view.HomeFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mishkun on 16.07.2017.
 */
@Singleton
@Component(modules = {AppModule.class, UtilsModule.class, CurrentWeatherModule.class})
public interface AppComponent {
    void inject(WeatherApplication application);

    void inject(HomeFragment homeFragment);

    void inject(HomeActivity homeActivity);
}
