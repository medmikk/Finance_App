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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.medvedev.financeapp.R;
import com.medvedev.financeapp.databinding.FragmentHomeBinding;
import com.medvedev.financeapp.domain.model.Stock;
import com.medvedev.financeapp.presentation.view.adapters.StockListAdapter;
import com.medvedev.financeapp.presentation.viewModel.HomeViewModel;
import com.medvedev.financeapp.repository.network.QuoteLogic;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.rvStocksHome;
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setHasFixedSize(true);

        StockListAdapter adapter = new StockListAdapter();
        recyclerView.setAdapter(adapter);

        String[] tickers = getResources().getStringArray(R.array.popular_tickers);
        String[] desc = getResources().getStringArray(R.array.popular_descriptions);
        ArrayList<Stock> stocks = new ArrayList<>();

        QuoteLogic logic = new QuoteLogic();

        for (int i = 0; i < tickers.length; i++){
            int finalI = i;
            logic.getStockPrice(tickers[i]).observe(getViewLifecycleOwner(), v ->{
                stocks.add(new Stock(tickers[finalI],
                        desc[finalI],
                        Double.parseDouble(v.getC()),
                        Double.parseDouble(v.getDp()))
                );
                adapter.setStocks(stocks);
            });
        }
        //Toast.makeText(root.getContext(), stocks.get(0).toString(), Toast.LENGTH_SHORT).show();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}