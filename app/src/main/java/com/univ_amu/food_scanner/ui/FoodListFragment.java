package com.univ_amu.food_scanner.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.univ_amu.food_scanner.databinding.FragmentFoodListBinding;
import com.univ_amu.food_scanner.viewmodels.FoodListViewModel;

public class FoodListFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentFoodListBinding binding = FragmentFoodListBinding.inflate(inflater, container, false);
        FoodListViewModel model = ViewModelProviders.of(this).get(FoodListViewModel.class);
        binding.setModel(model);
        FoodListAdapter foodListAdapter = new FoodListAdapter();
        model.foods().observe(this, foodListAdapter::submitList);
        binding.foodList.setAdapter(foodListAdapter);
        binding.foodList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        return binding.getRoot();
    }
}