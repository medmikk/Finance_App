package com.medvedev.financeapp.presentation.view.adapters;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.medvedev.financeapp.R;
import com.medvedev.financeapp.domain.model.Stock;
import com.medvedev.financeapp.presentation.view.StockInfoActivity;
import com.medvedev.financeapp.repository.model.StockDTO;

import java.util.ArrayList;
import java.util.List;

public class StockListAdapter extends RecyclerView.Adapter<StockListAdapter.StockListHolder> {
    private List<StockDTO> stocks = new ArrayList<>();

    @NonNull
    @Override
    public StockListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_stocks, parent, false);
        return new StockListHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StockListHolder holder, int position) {
        StockDTO currentStock = stocks.get(position);
        holder.tickerTv.setText(currentStock.getTicker());
        holder.companyNameTv.setText(currentStock.getCompanyName());
        holder.changePerCent.setText(Double.valueOf(currentStock.getChangePerCent()).toString());
        if(currentStock.getChangePerCent() > 0)
            holder.changePerCent.setTextColor(Color.GREEN);
        else if(currentStock.getChangePerCent() < 0)
            holder.changePerCent.setTextColor(Color.RED);
        holder.costTv.setText(Double.valueOf(currentStock.getCost()).toString());
    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setStocksDTO(List<StockDTO> stocks){
        this.stocks = stocks;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setStocks(List<Stock> stocks){
        List<StockDTO> list = new ArrayList<>();
        for (Stock stock : stocks)
            list.add(new StockDTO(stock));
        this.stocks = list;
        notifyDataSetChanged();
    }

    public List<StockDTO> getData() {
        return stocks;
    }

    class StockListHolder extends RecyclerView.ViewHolder{
        private final TextView tickerTv;
        private final TextView costTv;
        private final TextView companyNameTv;
        private final TextView changePerCent;
        private final CardView item;

        public StockListHolder(@NonNull View itemView) {
            super(itemView);
            tickerTv = itemView.findViewById(R.id.tickerTv);
            costTv = itemView.findViewById(R.id.costTv);
            companyNameTv = itemView.findViewById(R.id.companyNameTv);
            changePerCent = itemView.findViewById(R.id.changePerCentTv);
            item = itemView.findViewById(R.id.itemCvMain);

            item.setOnClickListener(v -> {
                Intent intent = new Intent(item.getContext(), StockInfoActivity.class);
                intent.putExtra("name", tickerTv.getText().toString());
                intent.putExtra("companyName", companyNameTv.getText().toString());
                item.getContext().startActivity(intent);
            });

        }
    }

}
