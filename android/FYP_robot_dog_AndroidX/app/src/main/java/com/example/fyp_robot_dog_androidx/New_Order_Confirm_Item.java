package com.example.fyp_robot_dog_androidx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.fyp_robot_dog_androidx.Api_And_Function.Cache;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Django;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import okhttp3.FormBody;

public class New_Order_Confirm_Item extends AppCompatActivity {

    private TextView date, time,receiver,startpoint,endpoint,itemtype;
    String SelectDate,SelectTime,SelectReceiver,SelectDeparture,SelectDestination,SelectItemType;
    Django OrderCreator=new Django();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_confirm_item);
        getSupportActionBar().hide();
        init();
    }


    private void init(){
        date = findViewById(R.id.OrderConfirmDate);
        time = findViewById(R.id.OrderConfirmTime);
        receiver = findViewById(R.id.OrderConfirmReceiver);
        startpoint=findViewById(R.id.OrderConfirmDeparture);
        endpoint=findViewById(R.id.OrderConfirmDistinition);
        itemtype=findViewById(R.id.OrderConfirmItemType);



        SelectDate = getIntent().getStringExtra("SelectDate");
        SelectTime = getIntent().getStringExtra("SelectTime");
        SelectReceiver = getIntent().getStringExtra("Receiver");
        SelectDeparture=getIntent().getStringExtra("SelectDeparture");
        SelectDestination=getIntent().getStringExtra("SelectDestination");
        SelectItemType=getIntent().getStringExtra("SelectItemType");



        Log.d("1",SelectDate);
        Log.d("1",SelectTime);
        Log.d("1",SelectReceiver);
        Log.d("abc",SelectDeparture);
        Log.d("abc",SelectDestination);
        Log.d("abc",SelectItemType);



        date.setText(SelectDate);
        time.setText(SelectTime);
        receiver.setText(SelectReceiver);
        startpoint.setText(SelectDeparture);
        endpoint.setText(SelectDestination);
        itemtype.setText(SelectItemType);

    }

    public void OrderConfirmSubmit(View v){
        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    FormBody body = new FormBody.Builder()
                            .add("cancelDate", "")
                            .add("cancelTime", "")
                            .add("date",date.getText().toString() )
                            .add("departure", startpoint.getText().toString() )
                            .add("destination",endpoint.getText().toString() )
                            .add("finishTime","" )
                            .add("itemType",itemtype.getText().toString())
                            .add("receiverID",receiver.getText().toString() )
                            .add("senderID", Cache.user.getUserid())
                            .add("startTime", time.getText().toString())
                            .add("status", "pending")
                            .build();

                    OrderCreator.sendPOST(body,"http://sshop.tplinkdns.com:8011/order/create/");

                }catch(Exception e){
                    OrderCreator.SetDjangoException(e);
                    Log.d("DjangoError:",OrderCreator.GetDjangoExceptionString());
//
                }


            }


        }).start();



    }

    public void back(View view){
        finish();
    }
}