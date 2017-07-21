package com.mishkun.weatherapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Mishkun on 17.07.2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface WeatherScreen {
}
