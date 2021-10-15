package com.medvedev.financeapp.presentation.view.homeActivity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.medvedev.financeapp.databinding.FragmentHomeBinding;
import com.medvedev.financeapp.presentation.viewModel.HomeViewModel;
import com.medvedev.financeapp.repository.network.QuoteLogic;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {


        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TextView tv = binding.textHome;


        QuoteLogic logic = new QuoteLogic();
        logic.getStockPrice("AAPL").observe(getViewLifecycleOwner(),v ->
                Toast.makeText(root.getContext(), v.getC(), Toast.LENGTH_LONG).show());


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}