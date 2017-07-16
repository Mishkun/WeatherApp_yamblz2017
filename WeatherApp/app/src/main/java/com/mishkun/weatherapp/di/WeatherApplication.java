package com.mishkun.weatherapp.di;

import android.app.Application;

/**
 * Created by Mishkun on 16.07.2017.
 */

public class WeatherApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.appComponent =
                DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
