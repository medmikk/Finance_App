package com.medvedev.financeapp.presentation.view.homeActivity.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medvedev.financeapp.R;
import com.medvedev.financeapp.databinding.FragmentFavouriteBinding;
import com.medvedev.financeapp.databinding.FragmentHomeBinding;
import com.medvedev.financeapp.presentation.view.adapters.StockListAdapter;
import com.medvedev.financeapp.presentation.viewModel.FavouriteViewModel;
import com.medvedev.financeapp.presentation.viewModel.HomeViewModel;

import org.jetbrains.annotations.NotNull;

public class FavouriteFragment extends Fragment {

    private FavouriteViewModel viewModel;
    private RecyclerView recyclerView;
    private FragmentFavouriteBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel =
                new ViewModelProvider(this).get(FavouriteViewModel.class);

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.rvStocks;
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setHasFixedSize(true);

        StockListAdapter adapter = new StockListAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(FavouriteViewModel.class);
        viewModel.getAllStocks().observe(getViewLifecycleOwner(), stocks -> adapter.setStocks(stocks));

        viewModel.addStock("111", 123.2, "comp", 124.5);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView,
                                  @NonNull @NotNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                viewModel.delete(((StockListAdapter) recyclerView.getAdapter()).getData().get(position));
            }
        }).attachToRecyclerView(recyclerView);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}