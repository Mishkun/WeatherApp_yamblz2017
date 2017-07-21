package com.mishkun.weatherapp.common;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mishkun on 14.07.2017.
 */

abstract public class Subscriptable {
    private final CompositeDisposable subscriptions;

    public Subscriptable() {
        this.subscriptions = new CompositeDisposable();
    }

    protected void dispose() {
        if (!subscriptions.isDisposed()) {
            subscriptions.dispose();
        }
    }

    protected void addSubscription(@NonNull Disposable disposable) {
        subscriptions.add(disposable);
    }
}
