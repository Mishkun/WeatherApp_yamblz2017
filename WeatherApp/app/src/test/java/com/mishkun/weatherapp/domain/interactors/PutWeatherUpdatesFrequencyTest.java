package com.mishkun.weatherapp.domain.interactors;

import com.mishkun.weatherapp.domain.outerworld.UpdatePreferenceProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

/*
 * Created by DV on Space 5 
 * 30.07.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class PutWeatherUpdatesFrequencyTest{
    private PutWeatherUpdatesFrequency putWeatherUpdatesFrequency;
    @Mock
    private UpdatePreferenceProvider updatePreferenceProvider;
    private TestScheduler testScheduler;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        putWeatherUpdatesFrequency = new PutWeatherUpdatesFrequency(testScheduler, testScheduler, updatePreferenceProvider);
        //Is this needed?
        //when(updatePreferenceProvider.setUpdateFrequency(anyLong())).thenReturn(Completable.complete());
    }

    @Test
    public void buildUseCaseCompletable() throws Exception {
        putWeatherUpdatesFrequency.buildUseCaseCompletable(anyLong());
        verify(updatePreferenceProvider).setUpdateFrequency(anyLong());
    }
}