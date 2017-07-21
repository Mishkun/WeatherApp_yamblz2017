package com.mishkun.weatherapp.jobs;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;
import com.mishkun.weatherapp.domain.providers.CurrentWeatherProvider;

import io.reactivex.annotations.Nullable;

/**
 * Created by Mishkun on 16.07.2017.
 */

public class WeatherJobCreator implements JobCreator {
    private final CurrentWeatherProvider currentWeatherProvider;

    public WeatherJobCreator(CurrentWeatherProvider currentWeatherProvider) {
        this.currentWeatherProvider = currentWeatherProvider;
    }

    @Override
    public @Nullable
    Job create(String tag) {
        switch (tag) {
            case WeatherJob.TAG:
                return new WeatherJob(currentWeatherProvider);
            default:
                return null;
        }
    }
}
