package com.mishkun.weatherapp.presentation;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mishkun on 15.07.2017.
 */

public abstract class RxPresenter<V> {

    /*
     *  Rx logic
     * */
    private final CompositeDisposable subscriptions;

    RxPresenter() {
        this.subscriptions = new CompositeDisposable();
    }

    protected void addSubscription(@NonNull Disposable disposable) {
        subscriptions.add(disposable);
    }

    private void dispose() {
        if (!subscriptions.isDisposed()) {
            subscriptions.dispose();
        }
    }

    /*
    *   Presenter logic
    * */
    protected V view;

    public void attachView(V view) {
        this.view = view;
        onAttach();
    }

    abstract void onAttach();

    public void detachView() {
        this.view = null;
        onDetach();
        dispose();
    }

    abstract void onDetach();
}
