package com.mishkun.weatherapp.domain.interactors;

import com.mishkun.weatherapp.domain.outerworld.UpdatePreferenceProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;

/*
 * Created by DV on Space 5 
 * 30.07.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class GetWeatherUpdatesFrequencyTest {
    private GetWeatherUpdatesFrequency getWeatherUpdatesFrequency;
    @Mock
    private UpdatePreferenceProvider updatePreferenceProvider;
    private TestScheduler testScheduler;

    @Before
    public void setUp() {
        getWeatherUpdatesFrequency = new GetWeatherUpdatesFrequency(testScheduler, testScheduler, updatePreferenceProvider);
    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        getWeatherUpdatesFrequency.buildUseCaseObservable();
        verify(updatePreferenceProvider).getUpdateFrequency();
    }

}