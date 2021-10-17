package com.medvedev.financeapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.TextView;
import android.widget.Toast;

import com.medvedev.financeapp.R;
import com.medvedev.financeapp.presentation.viewModel.StockInfoViewModel;

import java.util.Calendar;
import java.util.Objects;

public class StockInfoActivity extends AppCompatActivity {

    private CardView addNotificationCv;
    private TextView tickerTv;
    private TextView companyTv;
    private TextView costTv;
    private TextView changePerCentTv;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_info);
        Objects.requireNonNull(getSupportActionBar()).hide();

        tickerTv = findViewById(R.id.tickerTvInfo);
        companyTv = findViewById(R.id.companyNameTvInfo);
        costTv = findViewById(R.id.costTvInfo);
        changePerCentTv = findViewById(R.id.lastCostTvInfo);
        addNotificationCv = findViewById(R.id.notificationCvInfo);

        String ticker = this.getIntent().getStringExtra("name");
        String companyName = this.getIntent().getStringExtra("companyName");
        StockInfoViewModel model = new StockInfoViewModel(getApplication());
        model.getStockPrice(ticker).observe(this, stockPriceData -> {
            tickerTv.setText(ticker);
            companyTv.setText(companyName);
            costTv.setText(Double.valueOf(stockPriceData.getC()).toString());
            changePerCentTv.setText(Double.valueOf(stockPriceData.getDp()).toString());
            if(Double.parseDouble(stockPriceData.getDp()) > 0)
                changePerCentTv.setTextColor(Color.GREEN);
            else if(Double.parseDouble(stockPriceData.getDp()) < 0)
                changePerCentTv.setTextColor(Color.RED);
            addNotificationCv.setOnClickListener(v -> {
                if(costTv.getText().equals("loading"))
                    return;
                if (ActivityCompat.checkSelfPermission(
                        getApplicationContext(),
                        Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            this,new String[]{Manifest.permission.WRITE_CALENDAR},
                            100);
                }
                if(ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED){

                    ContentResolver cr = getContentResolver();
                    ContentValues cv = new ContentValues();

                    cv.put(CalendarContract.Events.TITLE, "Start watch " + ticker);
                    cv.put(CalendarContract.Events.DESCRIPTION, companyName +
                            "\n current cost: "+ stockPriceData.getC() + " $" +
                            "\n last cost: "+ stockPriceData.getDp() + " $");
                    cv.put(CalendarContract.Events.DTSTART, System.currentTimeMillis());
                    cv.put(CalendarContract.Events.DTEND, System.currentTimeMillis() + 1000);
                    cv.put(CalendarContract.Events.CALENDAR_ID, 2);
                    cv.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());
                    cr.insert(CalendarContract.Events.CONTENT_URI, cv);
                    //TODO open calendar
                    Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}