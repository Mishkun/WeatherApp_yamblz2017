package com.mishkun.weatherapp.domain;
/*
 * Created by DV on Space 5 
 * 26.07.2017
 */

import android.support.annotation.NonNull;

import io.reactivex.Completable;
import io.reactivex.Scheduler;

public abstract class CompletableParameterlessInteractor {
    private final Scheduler jobScheduler;
    private final Scheduler uiScheduler;


    protected CompletableParameterlessInteractor(@NonNull Scheduler threadExecutor, @NonNull Scheduler postExecutionThread) {
        super();
        this.jobScheduler = threadExecutor;
        this.uiScheduler = postExecutionThread;
    }


    public abstract Completable buildUseCaseCompletable();

    public Completable run() {
        return this.buildUseCaseCompletable()
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler);
    }
}
