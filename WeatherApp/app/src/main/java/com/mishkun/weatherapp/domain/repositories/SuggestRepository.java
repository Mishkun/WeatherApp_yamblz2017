package com.mishkun.weatherapp.domain.repositories;
/*
 * Created by DV on Space 5 
 * 25.07.2017
 */

import com.mishkun.weatherapp.domain.entities.City;
import com.mishkun.weatherapp.domain.entities.citiesSuggest.Prediction;

import java.util.List;

import io.reactivex.Single;

public interface SuggestRepository {
    Single<List<Prediction>> getCitiesSuggest(String string);
    Single<City> getCityCoordinates(String cityID);
}
