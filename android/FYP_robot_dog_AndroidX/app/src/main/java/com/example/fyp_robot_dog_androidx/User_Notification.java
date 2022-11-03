package com.example.fyp_robot_dog_androidx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.Adapter;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.Item_Text_Title;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.MyOrder;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.Notification_Item;

import java.util.ArrayList;

public class User_Notification extends AppCompatActivity {
    private RecyclerView notification_recycle;
    private ArrayList<Object> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notification);
        getSupportActionBar().hide();
        init();
        addDataToArrayListFromDatabase();
        addDataToRecyclerView();
    }

    public void back(View view){
        finish();
    }

    private void init(){
        notification_recycle = findViewById(R.id.notification_recycle);
    }

    private void addDataToRecyclerView(){
        notification_recycle.setLayoutManager(new LinearLayoutManager(this));
        Adapter Adapter = new Adapter(arrayList);

        notification_recycle.setAdapter(Adapter);
        notification_recycle.setItemAnimator(new DefaultItemAnimator());
    }

    private void noAnyDataEvent(Adapter Adapter){
        arrayList.add("NULL");  // print welcome message for create first order
    }

    private void addDataToArrayListFromDatabase(){
        arrayList = new ArrayList<>();
        arrayList.clear();
        // TBC database connection --> display order record

        arrayList.add(new Notification_Item("Dog charging notofication:", "Dog charging time from 3:00 to 4:00."));
        arrayList.add(new Notification_Item("education materials", "receiver"));
    }
}