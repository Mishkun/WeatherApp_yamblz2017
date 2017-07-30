package com.mishkun.weatherapp.domain.interactors;

import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.outerworld.CurrentWeatherProvider;
import com.mishkun.weatherapp.data.google_places.repositories.CityInfoRepository;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
 * Created by DV on Space 5 
 * 30.07.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateWeatherTest {
    private UpdateWeather updateWeather;
    @Mock
    private CurrentWeatherProvider currentWeatherProvider;
    @Mock
    private CityInfoRepository cityInfoRepository;
    private TestScheduler testScheduler;

    @Before
    public void setUp() throws Exception {
        updateWeather = new UpdateWeather(testScheduler, testScheduler, currentWeatherProvider, cityInfoRepository);
        Location location = new Location(10.0, 10.0);
        //when(currentWeatherProvider.refreshData(location)).thenReturn(Completable.complete());
        when(cityInfoRepository.getCityCoordinate()).thenReturn(Single.just(location));
    }

    @Test
    public void buildUseCaseCompletable() throws Exception {
        updateWeather.buildUseCaseCompletable();
        verify(cityInfoRepository).getCityCoordinate();
    }

}