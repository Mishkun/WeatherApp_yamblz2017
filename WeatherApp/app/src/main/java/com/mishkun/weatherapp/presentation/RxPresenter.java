package com.mishkun.weatherapp.presentation;

import com.mishkun.weatherapp.common.Subscriptable;

/**
 * Created by Mishkun on 15.07.2017.
 */

public abstract class RxPresenter<V> extends Subscriptable {

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
