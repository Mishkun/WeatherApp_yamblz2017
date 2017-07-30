package com.mishkun.weatherapp.presentation.suggest;
/*
 * Created by DV on Space 5 
 * 24.07.2017
 */


import com.mishkun.weatherapp.domain.entities.citiesSuggest.Prediction;

import java.util.List;

interface SuggestView {

    void setSuggestAdapter(List<Prediction> list);
    void showError(String string);
    void terminateFragment();
}
