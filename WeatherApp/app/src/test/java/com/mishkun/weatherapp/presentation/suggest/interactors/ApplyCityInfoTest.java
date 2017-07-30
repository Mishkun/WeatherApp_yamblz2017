package com.mishkun.weatherapp.presentation.suggest.interactors;

import com.mishkun.weatherapp.domain.entities.City;
import com.mishkun.weatherapp.domain.entities.Location;
import com.mishkun.weatherapp.domain.interactors.ApplyCityInfo;
import com.mishkun.weatherapp.data.google_places.repositories.CityInfoRepository;
import com.mishkun.weatherapp.data.google_places.repositories.SuggestRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
 * Created by DV on Space 5 
 * 30.07.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplyCityInfoTest {
    private ApplyCityInfo applyCityInfo;
    @Mock
    private SuggestRepository suggestRepository;
    @Mock
    private CityInfoRepository cityInfoRepository;
    private TestScheduler testScheduler;
    @Before
    public void setUp() throws Exception {
        applyCityInfo = new ApplyCityInfo(testScheduler, testScheduler, suggestRepository, cityInfoRepository);
        Location location = new Location(10.0, 10.0);
        City city = new City("Moscow", location);
        when(suggestRepository.getCityCoordinates(anyString())).thenReturn(Single.just(city));
    }
    @Test
    public void buildUseCaseCompletable() throws Exception {
        applyCityInfo.buildUseCaseCompletable("test");
        verify(suggestRepository).getCityCoordinates("test");
    }
}