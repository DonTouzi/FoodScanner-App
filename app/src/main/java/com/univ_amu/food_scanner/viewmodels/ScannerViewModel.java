package com.univ_amu.food_scanner.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.univ_amu.food_scanner.R;
import com.univ_amu.food_scanner.data.Repository;

public class ScannerViewModel extends AndroidViewModel {
    private final Repository repository;
    public final MutableLiveData<String> downloadedFood;
    public final MutableLiveData<String> error;
    public ScannerViewModel(Application application) {
        super(application);
        this.downloadedFood = new MutableLiveData<>();
        this.error = new MutableLiveData<>();
        this.repository = new Repository(application);
    }
    public void handleBarcode(String barcode) {
        repository.downloadFood(barcode).observeForever(success -> {
            if (success) downloadedFood.postValue(barcode);
            else error.postValue("" + R.string.unknown_barcode);
        });
    }
}