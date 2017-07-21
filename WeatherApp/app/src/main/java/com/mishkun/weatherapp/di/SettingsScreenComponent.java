package com.mishkun.weatherapp.di;

import com.mishkun.weatherapp.presentation.settings.SettingsFragment;

import dagger.Subcomponent;

/**
 * Created by Mishkun on 19.07.2017.
 */
@Subcomponent
@SettingsScreen
public interface SettingsScreenComponent {
    void inject(SettingsFragment settingsFragment);
}
