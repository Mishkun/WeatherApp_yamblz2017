package com.mishkun.weatherapp.jobs;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.providers.CurrentWeatherProvider;

import java.util.concurrent.TimeUnit;

/**
 * Created by Mishkun on 16.07.2017.
 */

public class WeatherJob extends Job {
    public static final String TAG = "WeatherJob";

    private CurrentWeatherProvider currentWeatherProvider;
    private Location currentLocation = new Location(55.75222, 37.61556);

    public WeatherJob(CurrentWeatherProvider currentWeatherProvider) {
        this.currentWeatherProvider = currentWeatherProvider;
    }

    public static int scheduleJob(int minutes) {
        return new JobRequest.Builder(TAG)
                .setPersisted(true)
                .setPeriodic(TimeUnit.MINUTES.toMillis(minutes))
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .build()
                .schedule();
    }

    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        currentWeatherProvider.refreshData(currentLocation).subscribe();
        return Result.SUCCESS;
    }
}
