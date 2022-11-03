package com.example.fyp_robot_dog_androidx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_Place_Item extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_place_item);
        getSupportActionBar().hide();
    }

    public void back(View view){
        finish();
    }

    public void cancel(View view){
        finish();
    }

    public void lockAndGo(View view){
        // TBC -- sender place item
        Intent intent = new Intent(this, Home_Place_Item_success.class);
        startActivity(intent);
    }
}