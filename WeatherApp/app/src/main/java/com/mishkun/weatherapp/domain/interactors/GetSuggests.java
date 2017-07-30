package com.mishkun.weatherapp.domain.interactors;


import com.mishkun.weatherapp.domain.SingleInteractor;
import com.mishkun.weatherapp.domain.entities.citiesSuggest.Prediction;
import com.mishkun.weatherapp.domain.repositories.SuggestRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

import static com.mishkun.weatherapp.di.NamedConsts.JOB;
import static com.mishkun.weatherapp.di.NamedConsts.UI;

/*
 * Created by DV on Space 5 
 * 26.07.2017
 */

public class GetSuggests extends SingleInteractor<String, List<Prediction>> {

    private final SuggestRepository suggestRepository;

    @Inject
    public GetSuggests(@NonNull @Named(JOB) Scheduler threadExecutor, @NonNull @Named(UI) Scheduler postExecutionThread,
                       SuggestRepository suggestRepository) {
        super(threadExecutor, postExecutionThread);
        this.suggestRepository = suggestRepository;
    }

    @Override
    public Single<List<Prediction>> buildUseCaseObservable(String partOfWord) {
         return suggestRepository.getCitiesSuggest(partOfWord);
    }
}
