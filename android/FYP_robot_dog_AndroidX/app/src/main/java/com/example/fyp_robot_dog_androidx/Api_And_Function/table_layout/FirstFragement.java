package com.example.fyp_robot_dog_androidx.Api_And_Function.table_layout;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fyp_robot_dog_androidx.Api_And_Function.Cache;
import com.example.fyp_robot_dog_androidx.Api_And_Function.CustomAlert;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Django;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.Item_Text_Title;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.Adapter;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.MyOrder;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.Order;
import com.example.fyp_robot_dog_androidx.Api_And_Function.User;
import com.example.fyp_robot_dog_androidx.Home;
import com.example.fyp_robot_dog_androidx.MainActivity;
import com.example.fyp_robot_dog_androidx.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;

public class FirstFragement extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private List<Object> arrayList;
    private Adapter adapter;
    private LinearLayout ToCreateOrderPage;
    private SwipeRefreshLayout swipeRefreshLayout;
    Django AllOrder=new Django();
    MyOrder order=new MyOrder();
    int a = 0;
    public static FirstFragement newInstance(String param1, String param2) {
        FirstFragement fragment = new FirstFragement();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addDataToArrayListFromDatabase();
        View view = inflater.inflate(R.layout.frag1_order_layout, container, false);
        init(view);
        addDataToRecyclerView(view);
        return view;
    }

    private void init(View view){
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
    }

    private View addDataToRecyclerView(View view){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Adapter Adapter = new Adapter(arrayList);

        if(Adapter.getItemCount()==0) noAnyDataEvent(Adapter);  // if userOrder is null

        recyclerView.setAdapter(Adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout.setRefreshing(false);
        return view;
    }

    private void noAnyDataEvent(Adapter Adapter){
        arrayList.add("NULL"); // print welcome message for create first order
    }

    private void addDataToArrayListFromDatabase(){
        arrayList = new ArrayList<>();
        arrayList.clear();
//        arrayList.add(new MyOrder("OD00005", "","", "2022-09-15", "US12", "US13", "", "education materials","US001","US002","10:30" ,"arrive"));
//        arrayList.add(new MyOrder("OD00004", "","", "2022-09-15", "US12", "US13", "", "education materials","US001","US002","10:30" ,"pickup"));
//        arrayList.add(new Item_Text_Title("Pending Delivery"));
//        arrayList.add(new MyOrder("OD00001", "","", "2022-09-15", "US12", "US13", "", "education materials","US001","US002","10:30" ,"pending"));

        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    arrayList = new ArrayList<>();
                    arrayList.clear();
                    String Gurl=HttpUrl.parse("http://sshop.tplinkdns.com:8011/order/").newBuilder()
                            .addQueryParameter("userid", Cache.user.getUserid())
                            .build().toString();

                    AllOrder.sendGET(Gurl);

                    Gson gson = new Gson();

                    JSONArray OrderJsonArray = new JSONArray(AllOrder.GetResponseBody());

                    boolean printPending = false;
                    for(int i=0; i<OrderJsonArray.length(); i++) {
                        JSONObject jsonObject = OrderJsonArray.getJSONObject(i);
                        String Orderlist= jsonObject.toString();


                        JSONObject OrderJsonEach = new JSONObject(Orderlist);

                        JSONObject OrderDetail = new JSONObject(OrderJsonEach.getJSONObject(OrderJsonEach.keys().next()).toString());

                        order = gson.fromJson(OrderDetail.toString(), MyOrder.class);
                        order.setOrderId(OrderJsonEach.keys().next());
//                        Log.d("aeghaershaehehraerh",order.getStatus());
//                        //arrayList.add(new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getDate(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getSenderID(), order.getStartTime() , order.getStatus()));
////                        arrayList.add(new MyOrder(order));
//                        Log.d("ksajdhvgakjsdhg",order.getOrderId());
//                        Log.d("ksajdhvgakjsdhg",order.getStatus());


                        if(order.getStatus().equals("arrive")){
                            arrayList.add(0,new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getDate(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getSenderID(), order.getStartTime() , order.getStatus()));
                        }else if(order.getStatus().equals("pickup")){

                            arrayList.add(0,new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getDate(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getSenderID(), order.getStartTime() , order.getStatus()));
                        }


                        if(order.getStatus().equals("pending") ){

                            if(arrayList.isEmpty() || !(arrayList.get(arrayList.size()-1).equals(new Item_Text_Title("Pending Delivery")))){
                                if (printPending == false) {
                                    arrayList.add(new Item_Text_Title("Pending Delivery"));
                                    printPending = true;
                                }
                                arrayList.add(new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getDate(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getSenderID(), order.getStartTime() , order.getStatus()));
                            }else{
                                arrayList.add(new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getDate(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getSenderID(), order.getStartTime() , order.getStatus()));
                            }

                        }

                        //arrayList.add(new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getDate(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getSenderID(), order.getStartTime() , order.getStatus()));
//                        addPending(order);
//                        addPickUpArrive(order);

//                        Log.d("dsfghsdfghsdf",order.getOrderId());
//                        Log.d("dsfghsdfghsdf",order.getStartTime());
//                        Log.d("dsfghsdfghsdf",order.getStatus());

                        //addPending(order);
//                        addPickUpArrive(order);
//                        arrayList.add(new MyOrder(order.getOrderId(), "","", order.getStartTime(), order.getDeparture(), order.getDestination(), "", "education materials",order.getReceiverID(), order.getOrderId(), "10:30" ,"arrive"));


                    }



                }catch(Exception e){
                    AllOrder.SetDjangoException(e);
                    Log.e("ErrorMessage:", e.getMessage());
                    Log.e("DjangoError:",AllOrder.GetDjangoExceptionString());
                }
            }
        });
        t1.start();

        try {
            t1.join();

        } catch (InterruptedException e) {

        }

    }

//    public List<Object> addOrderintoOrderList(Django orderjson){
//        try{
//            Gson gson = new Gson();
//
//            JSONArray OrderJsonArray = new JSONArray(orderjson.GetResponseBody());
//            for(int i=0; i<OrderJsonArray.length(); i++) {
//                JSONObject jsonObject = OrderJsonArray.getJSONObject(3);
//                String Orderlist= jsonObject.toString();
//
//
//                JSONObject OrderJsonEach = new JSONObject(Orderlist);
//
//                JSONObject OrderDetail = new JSONObject(OrderJsonEach.getJSONObject(OrderJsonEach.keys().next()).toString());
//
//                order = gson.fromJson(OrderDetail.toString(), MyOrder.class);
//                order.setOrderId(OrderJsonEach.keys().next());
//                arrayList.add(new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getStartTime(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getOrderId(), order.getStartTime() , order.getStatus()));
//                addPending(order);
//                addPickUpArrive(order);
//                if(order.getStatus().equals("arrive") ||order.getStatus().equals("pickup")){
////            Log.d("dfhsdfhsdfhdf",order.getStatus());
////            Log.d("xgjhdhxdf",order.getOrderId());
//                    arrayList.add(new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getStartTime(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getOrderId(), order.getStartTime() , order.getStatus()));
//
//                }
//
//                if(order.getStatus().equals("pending") ){
//
//                    if(arrayList.get(arrayList.size() - 1)!=new Item_Text_Title("Pending Delivery")){
////                Log.d("dfhsdfhsdfhdf",order.getStatus());
////                Log.d("xgjhdhxdf",order.getOrderId());
//                        arrayList.add(new Item_Text_Title("Pending Delivery"));
//                        arrayList.add(new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getStartTime(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getOrderId(), order.getStartTime() , order.getStatus()));
//
//                    }else{
////                Log.d("dfhsdfhsdfhdf",order.getStatus());
////                Log.d("xgjhdhxdf",order.getOrderId());
//                        arrayList.add(new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getStartTime(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getOrderId(), order.getStartTime() , order.getStatus()));
//
//                    }
////                }
//
//
//                Log.d("dsfghsdfghsdf",OrderJsonEach.keys().next());
//            }
//        }catch (Exception e){
//            Log.d("ErrorMsg:",e.getMessage());
//        }
//
//        return arrayList;
//    }

    public List<Object> addOrderintoOrderList(Django orderjson){
        try{
            Gson gson = new Gson();

            JSONArray OrderJsonArray = new JSONArray(orderjson.GetResponseBody());
            for(int i=0; i<OrderJsonArray.length(); i++) {
                JSONObject jsonObject = OrderJsonArray.getJSONObject(3);
                String Orderlist= jsonObject.toString();


                JSONObject OrderJsonEach = new JSONObject(Orderlist);

                JSONObject OrderDetail = new JSONObject(OrderJsonEach.getJSONObject(OrderJsonEach.keys().next()).toString());

                order = gson.fromJson(OrderDetail.toString(), MyOrder.class);
                order.setOrderId(OrderJsonEach.keys().next());

                addPending(order);
                //addPickUpArrive(order);
                arrayList.add(new MyOrder(order.getOrderId(), "","", order.getStartTime(), order.getDeparture(), order.getDestination(), "", "education materials",order.getReceiverID(), order.getOrderId(), "10:30" ,"arrive"));

                Log.d("dsfghsdfghsdf",String.valueOf(a));
            }
        }catch (Exception e){
            Log.d("ErrorMsg:",e.getMessage());
        }

        return arrayList;
    }



    public void addPickUpArrive(MyOrder order){
        if(order.getStatus().equals("arrive") ||order.getStatus().equals("pickup")){
//            Log.d("dfhsdfhsdfhdf",order.getStatus());
//            Log.d("xgjhdhxdf",order.getOrderId());
            arrayList.add(new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getDate(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getSenderID(), order.getStartTime() , order.getStatus()));

        }
    }



    public void addPending(MyOrder order){
        if(order.getStatus().equals("pending") ){


            if(!(arrayList.get(arrayList.size() - 1).equals(new Item_Text_Title("Pending Delivery"))) ){
//                Log.d("dfhsdfhsdfhdf",order.getStatus());
//                Log.d("xgjhdhxdf",order.getOrderId());

                if(arrayList.get(arrayList.size() - 1)!=new Item_Text_Title("Pending Delivery")){
                    Log.d("dfhsdfhsdfhdf",order.getStatus());
                    Log.d("xgjhdhxdf",order.getOrderId());

//                    arrayList.add(new Item_Text_Title("Pending Delivery"));
                    arrayList.add(new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getDate(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getSenderID(), order.getStartTime() , order.getStatus()));

                }else{
    //                Log.d("dfhsdfhsdfhdf",order.getStatus());
    //                Log.d("xgjhdhxdf",order.getOrderId());
                    arrayList.add(new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getDate(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getSenderID(), order.getStartTime() , order.getStatus()));

                }
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CustomAlert.alertMessage(getContext(), "Testing","Refresh data");
                addDataToArrayListFromDatabase();
                addDataToRecyclerView(view);
            }
        });
    }
}