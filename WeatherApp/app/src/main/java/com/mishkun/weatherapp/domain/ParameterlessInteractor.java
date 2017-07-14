package com.mishkun.weatherapp.domain;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

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


    protected abstract Observable<R> buildUseCaseObservable();

    public void execute(DisposableObserver<R> observer) {
        final Observable<R> observable = this.buildUseCaseObservable()
                                             .subscribeOn(jobScheduler)
                                             .observeOn(uiScheduler);
        addSubscription(observable.subscribeWith(observer));
    }
}