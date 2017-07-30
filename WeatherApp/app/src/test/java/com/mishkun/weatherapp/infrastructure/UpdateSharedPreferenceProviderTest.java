package com.mishkun.weatherapp.infrastructure;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mishkun.weatherapp.domain.outerworld.UpdatePreferenceProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.schedulers.TestScheduler;

/*
 * Created by DV on Space 5 
 * 30.07.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateSharedPreferenceProviderTest {
    private UpdatePreferenceProvider updatePreferenceProvider;
    @Mock
    private Context context;
    @Mock
    private SharedPreferences prefs;
    @Mock
    private PreferenceManager preferenceManager;
    private TestScheduler testScheduler;

    @Before
    public void setUp() throws Exception {
//        when(PreferenceManager.getDefaultSharedPreferences(any(Context.class))).thenReturn(prefs);
//        updatePreferenceProvider = new UpdateSharedPreferenceProvider(context);
    }

    @Test
    public void getUpdateFrequency() throws Exception {
//        updatePreferenceProvider.getUpdateFrequency();
//        verify(prefs).getLong(anyString(), anyLong());
    }

    @Test
    public void setUpdateFrequency() throws Exception {
    }

}