package com.mishkun.weatherapp.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mishkun on 16.07.2017.
 */
@Module
class AppModule {

    private Context context;

    public AppModule(Context context) {
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }
}
