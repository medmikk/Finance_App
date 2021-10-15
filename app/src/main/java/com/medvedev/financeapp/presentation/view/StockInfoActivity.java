package com.medvedev.financeapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.TextView;
import android.widget.Toast;

import com.medvedev.financeapp.R;
import com.medvedev.financeapp.presentation.viewModel.StockInfoViewModel;

import java.util.Calendar;

public class StockInfoActivity extends AppCompatActivity {

    private CardView addNotificationCv;
    private StockInfoViewModel model;
    private TextView tickerTv;
    private TextView companyTv;
    private TextView costTv;
    private TextView lastCostTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_info);
        getSupportActionBar().hide();

        tickerTv = findViewById(R.id.tickerTvInfo);
        companyTv = findViewById(R.id.companyNameTvInfo);
        costTv = findViewById(R.id.costTvInfo);
        lastCostTv = findViewById(R.id.lastCostTvInfo);
        addNotificationCv = findViewById(R.id.notificationCvInfo);

        Integer id = this.getIntent().getIntExtra("id", 0);
        model = new StockInfoViewModel(getApplication());
        model.getById(id).observe(this, stockDTO -> {
            tickerTv.setText(stockDTO.getTicker());
            companyTv.setText(stockDTO.getCompanyName());
            costTv.setText(new Double(stockDTO.getCost()).toString());
            lastCostTv.setText(new Double(stockDTO.getLastCost()).toString());
            addNotificationCv.setOnClickListener(v -> {
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

                    cv.put(CalendarContract.Events.TITLE, stockDTO.getTicker());
                    cv.put(CalendarContract.Events.DESCRIPTION, stockDTO.getCompanyName() +
                            "\n current cost: "+ stockDTO.getCost() + " $" +
                            "\n last cost: "+ stockDTO.getLastCost() + " $");
                    cv.put(CalendarContract.Events.DTSTART, System.currentTimeMillis());
                    cv.put(CalendarContract.Events.DTEND, System.currentTimeMillis() + 1000);
                    cv.put(CalendarContract.Events.CALENDAR_ID, 2);
                    cv.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());
                    cr.insert(CalendarContract.Events.CONTENT_URI, cv);
                    Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}