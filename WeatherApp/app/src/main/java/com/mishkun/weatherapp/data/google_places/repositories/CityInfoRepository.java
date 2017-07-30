package com.mishkun.weatherapp.data.google_places.repositories;
/*
 * Created by DV on Space 5 
 * 26.07.2017
 */

import com.mishkun.weatherapp.domain.entities.Location;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface CityInfoRepository {

    Completable setCityInfo(Location location, String cityName);

    Single<Location> getCityCoordinate();

    Single<String> getCityName();
}
