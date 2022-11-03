package com.example.fyp_robot_dog_androidx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fyp_robot_dog_androidx.Api_And_Function.Cache;
import com.example.fyp_robot_dog_androidx.Api_And_Function.CustomAlert;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Django;
import com.example.fyp_robot_dog_androidx.Api_And_Function.User;
import com.google.gson.Gson;

import org.json.JSONObject;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.HttpUrl;

public class New_Order_Select_Location_Item_type extends AppCompatActivity implements View.OnClickListener{
    private String SelectDate, SelectTime, SelectReceiver,SelectTimeforInfoday;
    private TextView food, education, clean, confid, chenical, elect;
    private TextView previousTextView;
    private Spinner startPoint, endPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_select_location_item_type);
        getSupportActionBar().hide();
        init();
        spinnerInit();

    }

    private void init(){
        SelectDate = getIntent().getStringExtra("SelectDate");
        SelectTime = getIntent().getStringExtra("SelectTime");
        SelectReceiver = getIntent().getStringExtra("Receiver");

        // Just for Infoday
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        SelectTimeforInfoday = dtf.format(now);

        // Just for Infoday


        food = findViewById(R.id.food);
        education = findViewById(R.id.education);
        clean = findViewById(R.id.clean);
        confid = findViewById(R.id.confid);
        chenical = findViewById(R.id.chenical);
        elect = findViewById(R.id.elect);
        startPoint = findViewById(R.id.startPoint);
        endPoint = findViewById(R.id.endPoint);

        food.setOnClickListener(this);
        education.setOnClickListener(this);
        clean.setOnClickListener(this);
        confid.setOnClickListener(this);
        chenical.setOnClickListener(this);
        elect.setOnClickListener(this);
    }

    @SuppressLint("ResourceAsColor")
    private void spinnerInit(){
        ArrayList<String> startPointArray = new ArrayList<>();

        startPointArray.add("Location: Start point");
        startPointArray.add("UG36");
        startPointArray.add("UG37");
        startPointArray.add("403");
        startPointArray.add("231");
        spainnerConfig(startPoint, startPointArray);

        ArrayList<String> endPointArray = new ArrayList<>();
        endPointArray.add("Location: End point");
        endPointArray.add("UG36");
        endPointArray.add("UG37");
        endPointArray.add("403");
        endPointArray.add("231");
        spainnerConfig(endPoint, endPointArray);
    }

    private void spainnerConfig(Spinner spinner, ArrayList<String> arrayList){
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parent.getChildAt(0)).setTextSize(15);
                ((TextView) parent.getChildAt(0)).setTypeface(null, Typeface.BOLD);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void Back(View view) {
        finish();
    }

    public void submit(View view){
        if(startPoint.toString()== endPoint.toString()){
            CustomAlert.alertMessage(this,"Error Message","Start point and End point can not be same.");
        }else{

            Intent intent = new Intent(New_Order_Select_Location_Item_type.this, New_Order_Confirm_Item.class);
            intent.putExtra("SelectDate", SelectDate);

            //intent.putExtra("SelectTime", SelectTime);
            //For Infoday only
            intent.putExtra("SelectTime", SelectTimeforInfoday);
            //For Infoday only

            intent.putExtra("Receiver", SelectReceiver);
            intent.putExtra("SelectDeparture", startPoint.getSelectedItem().toString());
            intent.putExtra("SelectDestination", endPoint.getSelectedItem().toString());
            intent.putExtra("SelectItemType", previousTextView.getText().toString());
            startActivity(intent);
        }










        // TBC (insert data to database) , use following data
//        SelectDay, SelectTime, Receiver
//        endPoint.getText();
//        startPoint.getText();
//        previousTextView.getText();
    }

    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    @Override
    public void onClick(View v) {
        if(previousTextView != null) itemTypeToDefault();
        switch (v.getId()){
            case R.id.food:
                food.setBackground(getDrawable(R.drawable.item_view_item_type_selector_style_one_selected));
                food.setTextColor(Color.parseColor("#000000"));
                previousTextView = food;
                break;
            case R.id.education:
                education.setBackgroundColor(Color.parseColor("#FFFFFF"));
                education.setTextColor(Color.parseColor("#000000"));
                previousTextView = education;
                break;
            case R.id.clean:
                clean.setBackgroundColor(Color.parseColor("#FFFFFF"));
                clean.setTextColor(Color.parseColor("#000000"));
                previousTextView = clean;
                break;
            case R.id.confid:
                confid.setBackgroundColor(Color.parseColor("#FFFFFF"));
                confid.setTextColor(Color.parseColor("#000000"));
                previousTextView = confid;
                break;
            case R.id.chenical:
                chenical.setBackgroundColor(Color.parseColor("#FFFFFF"));
                chenical.setTextColor(Color.parseColor("#000000"));
                previousTextView = chenical;
                break;
            case R.id.elect:
                elect.setBackground(getDrawable(R.drawable.item_view_item_type_selector_style_three_selected));
                elect.setTextColor(Color.parseColor("#000000"));
                previousTextView = elect;
                break;
        }
    }

    private void itemTypeToDefault(){
        if(previousTextView == food)
            previousTextView.setBackgroundResource(R.drawable.item_view_item_type_selector_style_one);
        else if(previousTextView == elect)
            previousTextView.setBackgroundResource(R.drawable.item_view_item_type_selector_style_three);
        else
            previousTextView.setBackgroundResource(R.drawable.item_view_item_type_selector_style_two);

        previousTextView.setTextColor(Color.parseColor("#FFFFFF"));
    }
}