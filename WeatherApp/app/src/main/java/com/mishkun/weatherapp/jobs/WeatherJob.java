package com.mishkun.weatherapp.jobs;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.interactors.UpdateWeather;

/**
 * Created by Mishkun on 16.07.2017.
 */

public class WeatherJob extends Job {
    static final String TAG = "WeatherJob";

    private Location currentLocation = new Location(55.75222, 37.61556);
    private final UpdateWeather updateWeather;


    public WeatherJob(UpdateWeather updateWeather) {

        this.updateWeather = updateWeather;
    }

    public static int scheduleJob(long millis) {
        return new JobRequest.Builder(TAG)
                .setPersisted(true)
                .setPeriodic(millis)
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .build()
                .schedule();
    }

    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        updateWeather.run(currentLocation).subscribe();
        return Result.SUCCESS;
    }
}
