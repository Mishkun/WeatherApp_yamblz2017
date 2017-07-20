package com.mishkun.weatherapp.jobs;

import android.content.Context;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.mishkun.weatherapp.domain.interactors.UpdateWeather;
import com.mishkun.weatherapp.domain.outerworld.WeatherUpdatesScheduler;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;

/**
 * Created by Mishkun on 19.07.2017.
 */

public class WeatherUpdatesAndroidJob implements WeatherUpdatesScheduler {

    private final UpdateWeather updateWeather;
    private static final String TAG = WeatherUpdatesAndroidJob.class.getSimpleName();

    public WeatherUpdatesAndroidJob(UpdateWeather updateWeather, Context context) {
        this.updateWeather = updateWeather;
        JobManager.create(context);
        JobManager.instance().addJobCreator(new WeatherJobCreator(updateWeather));
    }

    @Override
    public Completable scheduleWeatherUpdates(long millis) {
        Set<Job> weatherJobs = JobManager.instance().getAllJobsForTag(WeatherJob.TAG);
        if (weatherJobs.size() != 0) {
            for (Job weatherJob : weatherJobs) {
                weatherJob.cancel();
            }
        }
        WeatherJob.scheduleJob(millis);
        Log.d(TAG, "scheduleWeatherUpdates (millis): " + millis);
        Log.d(TAG, "scheduleWeatherUpdates (minutes): " + TimeUnit.MILLISECONDS.toMinutes(millis));
        return Completable.complete();
    }
}
