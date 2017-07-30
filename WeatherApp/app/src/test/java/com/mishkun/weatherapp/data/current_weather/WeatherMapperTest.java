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
//        weather = new Weather(new Temperature(250.0),
//                99.0,
//                1000.0,
//                WeatherConditions.RAIN,
//                50.0,
//                100000,
//                "Moscow" );
    }

    @Test
    public void test_toDomain() throws Exception {
//        when(weather.getCityName()).thenReturn("Moscow");
//        when(weather.getHumidity()).thenReturn(anyDouble());
//        when(weather.getPressureMmHg()).thenReturn(anyDouble());
//        when(weather.getTemperature()).thenReturn(new Temperature(250.0));
//        when(weather.getTime()).thenReturn(new Date(anyInt()));
//        when(weather.getWeatherConditions()).thenReturn(WeatherConditions.RAIN);
//        when(weather.getTimestamp()).thenReturn(anyLong());
//        when(weather.getWindSpeed()).thenReturn(anyDouble());
        ArrayList<Weather.WeatherConditions> arrayList = new ArrayList<>();
        arrayList.add(new com.mishkun.weatherapp.data.current_weather.Weather.WeatherConditions());

        weatherCURRENT = new com.mishkun.weatherapp.data.current_weather.Weather(new com.mishkun.weatherapp.data.current_weather.Weather.Wind(),
                arrayList, new com.mishkun.weatherapp.data.current_weather.Weather.Main());

        assertEquals(timeStamp, WeatherMapper.toDomain(weatherCURRENT, timeStamp).getTimestamp());
    }
}