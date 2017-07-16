package com.mishkun.weatherapp.di;

import android.app.Application;
import android.content.Context;

import com.evernote.android.job.JobManager;
import com.mishkun.weatherapp.domain.providers.CurrentWeatherProvider;
import com.mishkun.weatherapp.jobs.WeatherJobCreator;

import javax.inject.Inject;

/**
 * Created by Mishkun on 16.07.2017.
 */

public class WeatherApplication extends Application {

    public static Context context;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.appComponent =
                DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
        context = this.getApplicationContext();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
