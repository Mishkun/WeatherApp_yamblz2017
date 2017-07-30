package com.mishkun.weatherapp.presentation.home;

import android.content.Context;
import android.content.res.Resources;

import com.mishkun.weatherapp.domain.entities.Temperature;
import com.mishkun.weatherapp.domain.entities.Weather;
import com.mishkun.weatherapp.domain.entities.WeatherConditions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/*
 * Created by DV on Space 5 
 * 29.07.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherMapperTest {
    private WeatherMapper weatherMapper;
    private Weather weather;

    @Mock
    private Context context;
    @Mock
    private Resources resources;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        weather = new Weather(new Temperature(250.0),
                99.0,
                1000.0,
                WeatherConditions.RAIN,
                50.0,
                100000,
                "Moscow" );
    }

    @Test
    public void test_toWeatherViewModel() throws Exception {
        when(context.getResources()).thenReturn(resources);
        when(resources.getString(anyInt())).thenReturn("Moscow");
        weatherMapper = new WeatherMapper(context);
        WeatherViewModel weatherViewModel =  weatherMapper.toWeatherViewModel(weather);
        assertEquals("Moscow", weatherViewModel.getCityName());
    }
}