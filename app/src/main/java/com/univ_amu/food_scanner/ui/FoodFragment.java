package com.univ_amu.food_scanner.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.univ_amu.food_scanner.databinding.FragmentFoodBinding;
import com.univ_amu.food_scanner.viewmodels.FoodViewModel;
import com.univ_amu.food_scanner.viewmodels.FoodViewModelFactory;

public class FoodFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentFoodBinding binding = FragmentFoodBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        String code = FoodFragmentArgs.fromBundle(getArguments()).getCode();
        FoodViewModel model = ViewModelProviders.of(this,
                new FoodViewModelFactory(getActivity().getApplication(), code)).get(FoodViewModel.class);
        binding.setModel(model);
        QuantityListAdapter adapter = new QuantityListAdapter();
        model.quantities().observe(this, adapter::submitList);
        binding.quantityList.setAdapter(adapter);
        return binding.getRoot();
    }
}