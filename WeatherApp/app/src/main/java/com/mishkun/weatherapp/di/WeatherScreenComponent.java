package com.mishkun.weatherapp.di;

import com.mishkun.weatherapp.HomeActivity;
import com.mishkun.weatherapp.presentation.home.HomeFragment;
import com.mishkun.weatherapp.presentation.settings.SettingsFragment;

import dagger.Subcomponent;

/**
 * Created by Mishkun on 17.07.2017.
 */
@Subcomponent()
@WeatherScreen
public interface WeatherScreenComponent {
    void inject(HomeActivity homeActivity);
    void inject(HomeFragment homeFragment);
}
