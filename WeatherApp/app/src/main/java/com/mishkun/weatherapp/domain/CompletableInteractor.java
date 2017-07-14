package com.mishkun.weatherapp.domain;

import android.support.annotation.NonNull;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by Mishkun on 14.07.2017.
 */

public abstract class CompletableInteractor<P> extends Subscriptable {
    private final Scheduler jobScheduler;
    private final Scheduler uiScheduler;


    protected CompletableInteractor(@NonNull Scheduler threadExecutor, @NonNull Scheduler postExecutionThread) {
        super();
        this.jobScheduler = threadExecutor;
        this.uiScheduler = postExecutionThread;
    }


    public abstract Completable buildUseCaseObservable(P params);

    public void execute(DisposableCompletableObserver observer, P params) {
        final Completable observable = this.buildUseCaseObservable(params)
                                           .subscribeOn(jobScheduler)
                                           .observeOn(uiScheduler);
        addSubscription(observable.subscribeWith(observer));
    }

}
