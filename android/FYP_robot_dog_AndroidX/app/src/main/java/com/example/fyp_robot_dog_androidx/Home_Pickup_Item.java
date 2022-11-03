package com.example.fyp_robot_dog_androidx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home_Pickup_Item extends AppCompatActivity{
    private TextView food, education, clean, confid, chenical, elect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pickup_item);
        getSupportActionBar().hide();
    }

    public void back(View view){
        finish();
    }

    public void cancel(View view){
        finish();
    }

    public void openBox(View view){

    }
}