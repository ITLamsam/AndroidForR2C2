package com.example.fyp_robot_dog_androidx;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


import com.example.fyp_robot_dog_androidx.Api_And_Function.Calendar_Recyclerview_Config.CalendarAdapter;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Calendar_Recyclerview_Config.ItemSelectorAdapter;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Calendar_Recyclerview_Config.Item_Text;
import com.example.fyp_robot_dog_androidx.Api_And_Function.CustomAlert;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class New_Order_Booking_Calendar extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText, TextAM, TextPM;
    private RecyclerView calendarRecyclerView, timeSelector;
    private LocalDate selectedDate;
    private ArrayList<Object> AvabiliableTimeList;
    private String SelectDay, SelectTime;
    private CheckBox SelectInstanceBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_calendar);
        getSupportActionBar().hide();
        init();
        setMonthView();
        setTimeSelector();
        SelectInstanceBooking.setChecked(true);
//        setDefaultCalendarDayToCurrentD();
    }

    private void init()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        timeSelector = findViewById(R.id.timeSelector);
        monthYearText = findViewById(R.id.monthYearTV);
        TextAM = findViewById(R.id.AM);
        TextPM = findViewById(R.id.PM);
        SelectInstanceBooking= findViewById(R.id.InstantBookingCheckBox);
        selectedDate = LocalDate.now();
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    private String monthYearFromCurrnetDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-");
        return date.format(formatter);
    }

//    the method for testing only
    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onItemClick(int position, String Text)
    {
        if (!Text.equals("") && Text.length() <= 2) {
            for (int x = 0; x < calendarRecyclerView.getChildCount(); x++) {
                TextView temp = (TextView) (((ConstraintLayout) (calendarRecyclerView.getChildAt(x))).getChildAt(0));
                if (temp.getText().length() != 0) {
                    temp.setBackgroundResource(R.color.backgroundColor);
                    temp.setTextColor(Color.parseColor("#FFFFFF"));
                }
            }

            TextView element = (TextView) (((ConstraintLayout) (calendarRecyclerView.getChildAt(position))).getChildAt(0));
            if (element.getText().length() != 0) {
                element.setBackgroundResource(R.drawable.calendar_element);
                element.setTextColor(R.color.backgroundColor);
            }

            SelectDay = monthYearFromCurrnetDate(selectedDate) + Text;
//            String message = "Select: " + Text + " " + monthYearFromDate(selectedDate);
//            Toast.makeText(this, SelectTime , Toast.LENGTH_LONG).show();
        } else if (Text.length() >= 7) {
            CardView cardView = ((CardView) (timeSelector.getChildAt(position)));
            if (((TextView) cardView.getChildAt(0)).getTextColors().getDefaultColor() != -65536) {
                for (int x = 0; x < timeSelector.getChildCount(); x++) {
                    CardView card = ((CardView) (timeSelector.getChildAt(x)));
                    TextView temp = (TextView) (card.getChildAt(0));

                    if (temp.getText().length() != 0 && temp.getTextColors().getDefaultColor() != -65536) {
                        card.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#304156")));
                        temp.setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }

                SelectTime = Text;
                cardView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                ((TextView) cardView.getChildAt(0)).setTextColor(R.color.backgroundColor);
            }
        }
    }

    public void TimePeriods(View view){
        if(view.getId() == TextAM.getId()) {
            TextAM.setTextColor(Color.parseColor("#FF0000"));
            TextPM.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else if(view.getId() == TextPM.getId()) {
            TextAM.setTextColor(Color.parseColor("#FFFFFF"));
            TextPM.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    public void Back(View view) {
        finish();
    }

    public void next(View view){
//        if(SelectDay == null || SelectTime == null) {
        if (SelectDay == null  || !SelectInstanceBooking.isChecked()) {
            CustomAlert.alertMessage(this, "Error Message", "Please select a date!");
        } else {
            Intent intent = new Intent(this, New_Order_Select_Receiver.class);
            intent.putExtra("SelectDate", SelectDay);
            intent.putExtra("SelectTime", SelectTime);
            startActivity(intent);
        }
    }

    private void setTimeSelector(){
        addAvailableTime();
        timeSelector.setLayoutManager(new GridLayoutManager(this, 3));
        ItemSelectorAdapter Adapter = new ItemSelectorAdapter(AvabiliableTimeList, this::onItemClick);
        timeSelector.setAdapter(Adapter);
//        timeSelector.setItemAnimator(new DefaultItemAnimator());
    }

    private void addAvailableTime(){
       AvabiliableTimeList = new ArrayList<>();
//        AvabiliableTimeList.add(new Item_Text("7:00-7:30", "red"));
//        AvabiliableTimeList.add(new Item_Text("7:30-8:00", "normal"));
//        AvabiliableTimeList.add(new Item_Text("8:00-8:30","red"));
//        AvabiliableTimeList.add(new Item_Text("8:30-9:00","normal"));
//        AvabiliableTimeList.add(new Item_Text("9:30-10:00","normal"));
//        AvabiliableTimeList.add(new Item_Text("10:00-10:30","normal"));
//        AvabiliableTimeList.add(new Item_Text("10:30-11:00","normal"));
//        AvabiliableTimeList.add(new Item_Text("11:00-11:30","normal"));
//        AvabiliableTimeList.add(new Item_Text("11:30-12:00","normal"));


    }
    public void SelectInstantBooking(){

    }
}