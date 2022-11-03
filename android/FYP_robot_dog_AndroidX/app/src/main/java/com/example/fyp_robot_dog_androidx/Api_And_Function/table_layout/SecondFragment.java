package com.example.fyp_robot_dog_androidx.Api_And_Function.table_layout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fyp_robot_dog_androidx.Api_And_Function.Cache;
import com.example.fyp_robot_dog_androidx.Api_And_Function.CustomAlert;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Django;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.Adapter;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.Item_Text_Title;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.MyOrder;
import com.example.fyp_robot_dog_androidx.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;

public class SecondFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private List<Object> arrayList;
    Django AllOrder=new Django();
    MyOrder order=new MyOrder();

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
        View view = inflater.inflate(R.layout.frag2_history_layout, container, false);
        init(view);
        addDataToArrayListFromDatabase();
        addDataToRecyclerView(view);
        return view;
    }

    private void init(View view){
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout2);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview2);
    }

    private View addDataToRecyclerView(View view){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Adapter Adapter = new Adapter(arrayList);

        recyclerView.setAdapter(Adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout.setRefreshing(false);
        return view;
    }

    private void addDataToArrayListFromDatabase(){
        // TBC database connection --> display order history record
        arrayList = new ArrayList<>();
        arrayList.clear();

        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    arrayList = new ArrayList<>();
                    arrayList.clear();
                    String Gurl= HttpUrl.parse("http://sshop.tplinkdns.com:8011/order/").newBuilder()
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


                        if(order.getStatus().equals("done")){
                            arrayList.add(0,new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getDate(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getSenderID(), order.getStartTime() , order.getStatus()));
                        }else if(order.getStatus().equals("cancel")){

                            arrayList.add(0,new MyOrder(order.getOrderId(), order.getCancelTime(),order.getCancelDate(), order.getDate(), order.getDeparture(), order.getDestination(), order.getFinishTime(), order.getItemType(),order.getReceiverID(), order.getSenderID(), order.getStartTime() , order.getStatus()));
                        }




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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //refresh data
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