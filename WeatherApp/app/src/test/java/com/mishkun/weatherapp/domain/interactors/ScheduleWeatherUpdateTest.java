package com.mishkun.weatherapp.domain.interactors;

import com.mishkun.weatherapp.domain.outerworld.WeatherUpdatesScheduler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

/*
 * Created by DV on Space 5 
 * 30.07.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ScheduleWeatherUpdateTest {
    private ScheduleWeatherUpdate scheduleWeatherUpdate;
    @Mock
    private WeatherUpdatesScheduler weatherUpdatesScheduler;
    private TestScheduler testScheduler;
    @Before
    public void setUp() throws Exception {
        scheduleWeatherUpdate = new ScheduleWeatherUpdate(testScheduler, testScheduler, weatherUpdatesScheduler);
    }

    @Test
    public void buildUseCaseCompletable() throws Exception {
        scheduleWeatherUpdate.buildUseCaseCompletable(anyLong());
        verify(weatherUpdatesScheduler).scheduleWeatherUpdates(anyLong());
    }

}