package com.mishkun.weatherapp.domain;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mishkun on 14.07.2017.
 */

class Subscriptable {
    private final CompositeDisposable subscriptions;

    protected Subscriptable() {
        this.subscriptions = new CompositeDisposable();
    }

    public void dispose() {
        if (!subscriptions.isDisposed()) {
            subscriptions.dispose();
        }
    }

    protected void addSubscription(@NonNull Disposable disposable) {
        subscriptions.add(disposable);
    }
}
