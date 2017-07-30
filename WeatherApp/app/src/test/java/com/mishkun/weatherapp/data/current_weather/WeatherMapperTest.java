package com.mishkun.weatherapp.data.current_weather;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/*
 * Created by DV on Space 5 
 * 29.07.2017
 */
public class WeatherMapperTest {

    private com.mishkun.weatherapp.data.current_weather.Weather weatherCURRENT;
    private long timeStamp = 100000;

    @Before
    public void setUp(){
    }

    @Test
    public void test_toDomain() throws Exception {
        ArrayList<Weather.WeatherConditions> arrayList = new ArrayList<>();
        arrayList.add(new com.mishkun.weatherapp.data.current_weather.Weather.WeatherConditions());

        weatherCURRENT = new com.mishkun.weatherapp.data.current_weather.Weather(new com.mishkun.weatherapp.data.current_weather.Weather.Wind(),
                arrayList, new com.mishkun.weatherapp.data.current_weather.Weather.Main());

        assertEquals(timeStamp, WeatherMapper.toDomain(weatherCURRENT, timeStamp).getTimestamp());
    }
}