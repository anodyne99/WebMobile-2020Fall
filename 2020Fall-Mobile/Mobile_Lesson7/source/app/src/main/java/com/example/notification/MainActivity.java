// Created by Vijaya Yeruva on 11/20/2020
// Reference: https://developer.android.com/guide/topics/ui/notifiers/notifications

package com.example.notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.provider.CalendarContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    TextView text = null;
    CalendarView cal;
    int selectedDay = 0;
    int selectedMonth = 0;
    int selectedYear = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;
        text = (TextView) findViewById(R.id.dateView);
        cal = (CalendarView) findViewById(R.id.calendarView);
        String todayDate = sdf.format(cal.getDate());
        text.setText("Today's Date: \n" + todayDate);
        Button butInsert = (Button) findViewById(R.id.add_button);

        // Checks and updates the selected date when the user changes the date
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                int m = month + 1;
                text.setText("Selected Date: \n" + m+"/"+dayOfMonth+"/"+year);
                selectedDay = dayOfMonth;
                selectedYear = year;
                selectedMonth = month;
            }
        });

        // Adds a listener for when the '+' button is clicked
        butInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // if the user wanted to insert an event today and doesn't change the selected date first
                if (selectedDay == 0 || selectedMonth == 0 || selectedYear == 0){
                    Calendar today = Calendar.getInstance();
                    selectedDay = today.get(Calendar.DAY_OF_MONTH);
                    selectedMonth = today.get(Calendar.MONTH);
                    selectedYear = today.get(Calendar.YEAR);
                }
                insert(selectedDay, selectedMonth, selectedYear);
            }
        });
    }

    public void insert(int day, int month, int year) {
        // Creates the new intent for inserting
        Intent intent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        // Creates a base calendar from the current date
        Calendar startTime = Calendar.getInstance();
        // Assigns the selected time to the placeholder base calendar
        startTime.set(year, month, day);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        // Opens the calendar to the date specified
        startActivity(intent);
    }
}