package com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp_robot_dog_androidx.New_Order_Booking_Calendar;
import com.example.fyp_robot_dog_androidx.Home_Place_Item;
import com.example.fyp_robot_dog_androidx.Home_Pickup_Item;
import com.example.fyp_robot_dog_androidx.R;

import java.util.List;
import java.util.Objects;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Object> dataList;
    private final int layout = R.layout.list_item_two;
    private RecyclerviewOnClickListener listener;
    private final int TITLE = 0, OrderD = 1, CurrentOrderD = 2, HistoryOrder = 3, welcomeMessageForCreateOrder = 4;
    private final int notificaton_Item = 5;

    public Adapter(List<Object> dataList) {
        this.dataList = dataList;
    }

    public void adjustMyadapterOnclickEvent(RecyclerviewOnClickListener listener){
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case OrderD:   // message for current order
                View v1 = inflater.inflate(R.layout.list_item_two, viewGroup, false);
                viewHolder = new RecyclerViewHolder(v1);
                break;
            case TITLE: // a text
                View v2 = inflater.inflate(R.layout.list_item_three, viewGroup, false);
                viewHolder = new RecyclerViewHolder2(v2);
                break;
            case CurrentOrderD:
                View v3 = inflater.inflate(R.layout.list_item_four, viewGroup, false);
                viewHolder = new RecyclerViewHolder(v3);
                break;
            case HistoryOrder:
                View v4 = inflater.inflate(R.layout.list_item_five, viewGroup, false);
                viewHolder = new RecyclerViewHolder(v4);
                break;
            case welcomeMessageForCreateOrder:
                viewHolder = new RecyclerViewHolder2(inflater.inflate(R.layout.list_item_one, viewGroup, false));
                break;
            case notificaton_Item:
                viewHolder = new RecyclerViewHolder2(inflater.inflate(R.layout.list_item_notification, viewGroup, false));
                break;
            default:    //message for create order
                View v = inflater.inflate(R.layout.list_item_one, viewGroup, false);
                viewHolder = new RecyclerViewHolder2(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {

        if (dataList.get(position) instanceof MyOrder) {

            if(((MyOrder)dataList.get(position)).getStatus().equals("pending"))
                return OrderD;
            else if(((MyOrder)dataList.get(position)).getStatus().equals("arrive")  || ((MyOrder)dataList.get(position)).getStatus().equals( "pickup")) {

                return CurrentOrderD;
            }else if(((MyOrder)dataList.get(position)).getStatus().equals("done") ||((MyOrder)dataList.get(position)).getStatus().equals("cancel") )
                return HistoryOrder;
        } else if (dataList.get(position) instanceof Item_Text_Title)
            return TITLE;
        else if(dataList.get(position) instanceof String)
            return welcomeMessageForCreateOrder;
        else if(dataList.get(position) instanceof Notification_Item)
            return notificaton_Item;
        return -1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TITLE:
            case welcomeMessageForCreateOrder:
            case notificaton_Item:
                RecyclerViewHolder2 vh1 = (RecyclerViewHolder2) holder;
                configureViewHolder2(vh1, position, holder.getItemViewType());
                break;
            case OrderD:
            case CurrentOrderD:
            case HistoryOrder:
                RecyclerViewHolder vh3 = (RecyclerViewHolder) holder;
                configureViewHolder(vh3, position, holder.getItemViewType());
                break;
        }
    }

    private void configureViewHolder(RecyclerViewHolder vh1, int position, int getItemViewType) {
        MyOrder myOrder = (MyOrder) dataList.get(position);
        if (myOrder != null) {
            vh1.getTvOrderID().setText(myOrder.getOrderId());
            vh1.getTvStartTime().setText(myOrder.getStartTime()+myOrder.getDate());
            vh1.getTvPlace().setText(myOrder.getDeparture());
            vh1.getTvDistinition().setText(myOrder.getDeparture());
            vh1.getTvSender().setText(myOrder.getSenderID());
            vh1.getTvReceiver().setText(myOrder.getReceiverID());

            if(getItemViewType == HistoryOrder){
                if(myOrder.getStatus() == "cancel")  vh1.getIgSuccessFailImg().setImageResource(R.drawable.fail);
                else vh1.getIgSuccessFailImg().setImageResource(R.drawable.success);
            }

            if(getItemViewType == CurrentOrderD){
                if(Objects.equals(myOrder.getStatus(), "arrive"))
                    vh1.getArriveOrder().setText("Arrive");
                else
                    vh1.getArriveOrder().setText("PickUp");

                vh1.getArriveOrder().setOnClickListener(v -> {
                    Intent intent = new Intent();
                    if(Objects.equals(myOrder.getStatus(), "arrive")){
                        intent = new Intent(v.getContext(), Home_Place_Item.class);
                    }else {
                        // if(Objects.equals(myOrder.getOrderRole(), "receiver")
                        intent = new Intent(v.getContext(), Home_Pickup_Item.class);
                    }
                    v.getContext().startActivity(intent);
                });
            }
        }
    }

    private void configureViewHolder2(RecyclerViewHolder2 vh2, int position, int getItemViewType) {
        switch (getItemViewType){
            case TITLE:
                Item_Text_Title item_Text_title = (Item_Text_Title) dataList.get(position);
                if (item_Text_title != null)
                    vh2.getItemTitle().setText(item_Text_title.getTitle());
                break;
            case welcomeMessageForCreateOrder:
                String temp = (String) dataList.get(position);
                if(temp != null){
                    vh2.getBtnWelcomeMessage().setOnClickListener(v -> {
                        Intent intent = new Intent(v.getContext(), New_Order_Booking_Calendar.class);
                        v.getContext().startActivity(intent);
                    });
                }
                break;
            case notificaton_Item:
                Notification_Item notification_item = (Notification_Item) dataList.get(position);
                vh2.getItemTitle().setText(notification_item.getTitle());
                vh2.getItemContent().setText(notification_item.getContent());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

}