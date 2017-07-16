package com.mishkun.weatherapp.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;
import com.mishkun.weatherapp.R;
import com.mishkun.weatherapp.di.WeatherApplication;
import com.mishkun.weatherapp.domain.entities.Weather;
import com.mishkun.weatherapp.presentation.WeatherRxPresenter;
import com.mishkun.weatherapp.presentation.WeatherView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements WeatherView {
    public static final String TAG = HomeFragment.class.getSimpleName();


    @BindView(R.id.degrees_text_view)
    public TextView degreesView;
    @BindView(R.id.humidity_text_view)
    public TextView humidityView;
    @BindView(R.id.wind_text_view)
    public TextView windView;
    @BindView(R.id.pressure_text_view)
    public TextView pressureView;
    @Inject
    public WeatherRxPresenter weatherRxPresenter;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WeatherApplication) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(R.string.home_title);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        weatherRxPresenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        weatherRxPresenter.detachView();
    }

    @Override
    public Observable<Object> getRefreshCalls() {
        return RxSwipeRefreshLayout.refreshes(swipeRefreshLayout);
    }


    @Override
    public Consumer<Weather> getWeatherConsumer() {
        return weather -> {
            humidityView.setText(String.format(getString(R.string.humidity_fmt_string), weather.getHumidity()));
            degreesView.setText(String.format(getString(R.string.temperature_fmt_string), weather.getTemperature().getCelsiusDegrees()));
            pressureView.setText(String.format(getString(R.string.pressure_fmt_string), weather.getPressureMmHg()));
            windView.setText(String.format(getString(R.string.wind_fmt_string), weather.getWindSpeed()));
        };
    }

    @Override
    public Consumer<String> getErrorConsumer() {
        return s -> Toast.makeText(getActivity(), s,
                                   Toast.LENGTH_LONG).show();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Consumer<Boolean> getLoadingStatusConsumer() {
        return (Consumer<Boolean>) RxSwipeRefreshLayout.refreshing(swipeRefreshLayout);
    }
}
