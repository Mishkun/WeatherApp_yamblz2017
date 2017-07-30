package com.mishkun.weatherapp.data.google_places.repositories;
/*
 * Created by DV on Space 5 
 * 26.07.2017
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.mishkun.weatherapp.domain.entities.Location;

import io.reactivex.Completable;
import io.reactivex.Single;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefsCityRepository implements CityInfoRepository{

    private Context context;
    private String LOCATION = "LOCATION";
    private String CITY_NAME = "LOCATION";
    private String LOCATION_LATITUDE = "LOCATION_LAT";
    private String LOCATION_LONGITUDE = "LOCATION_LON";

    public SharedPrefsCityRepository(Context context){
        this.context = context;
    }

    public Completable setCityInfo(Location location, String cityName){
        return Completable.fromAction(() -> context.getSharedPreferences(LOCATION, MODE_PRIVATE).edit()
        .putFloat(LOCATION_LATITUDE, (float) location.getLatitude())
        .putFloat(LOCATION_LONGITUDE, (float) location.getLongitude())
        .putString(CITY_NAME, cityName)
        .apply());

        // Is it good to save double to float?
    }

    public Single<Location> getCityCoordinate(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOCATION, MODE_PRIVATE);
        double lat = sharedPreferences.getFloat(LOCATION_LATITUDE, 0);
        double lon = sharedPreferences.getFloat(LOCATION_LONGITUDE, 0);
        Single<Location> locationSingle = Single.just(new Location(lat, lon));

        return locationSingle;
    }

    public Single<String> getCityName(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOCATION, MODE_PRIVATE);
        String cityName = sharedPreferences.getString(CITY_NAME, "empty");
        Single<String> nameSingle = Single.just(cityName);

        return nameSingle;
    }
}
