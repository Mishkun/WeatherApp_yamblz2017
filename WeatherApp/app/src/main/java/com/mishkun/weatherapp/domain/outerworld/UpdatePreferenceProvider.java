package com.mishkun.weatherapp.domain.outerworld;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Mishkun on 19.07.2017.
 */

public interface UpdatePreferenceProvider {
    Single<Long> getUpdateFrequency();
    Completable setUpdateFrequency(long frequency);
}
