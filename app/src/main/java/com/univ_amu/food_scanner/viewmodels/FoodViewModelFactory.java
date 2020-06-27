package com.univ_amu.food_scanner.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FoodViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    private final String code;

    public FoodViewModelFactory(Application application, String code) {
        this.application = application;
        this.code = code;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FoodViewModel.class)) {
            return (T) new FoodViewModel(application, code);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}