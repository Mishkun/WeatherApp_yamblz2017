package com.mishkun.weatherapp.domain;

import android.support.annotation.NonNull;

import com.mishkun.weatherapp.common.Subscriptable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Mishkun on 08.07.2017.
 */

public abstract class ParameterlessInteractor<R> extends Subscriptable {
    private final Scheduler jobScheduler;
    private final Scheduler uiScheduler;


    protected ParameterlessInteractor(@NonNull Scheduler threadExecutor, @NonNull Scheduler postExecutionThread) {
        super();
        this.jobScheduler = threadExecutor;
        this.uiScheduler = postExecutionThread;
    }


    public abstract Observable<R> buildUseCaseObservable();

    public Observable<R> get() {
        return this.buildUseCaseObservable()
                   .subscribeOn(jobScheduler)
                   .observeOn(uiScheduler);
    }
}