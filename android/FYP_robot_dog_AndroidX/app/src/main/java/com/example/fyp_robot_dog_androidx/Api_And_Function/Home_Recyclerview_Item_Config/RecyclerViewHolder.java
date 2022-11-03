package com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp_robot_dog_androidx.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView TvOrderID, TvStartTime, TvPlace, TvReceiver, TvSender, TvItemType, TvDistinition;
    ImageView IgSuccessFailImg;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        init(itemView);
    }

    public ImageView getIgSuccessFailImg(){
        IgSuccessFailImg = (ImageView) itemView.findViewById(R.id.IgSuccessFailImg);
        return IgSuccessFailImg;
    }

    // only for current order
    public TextView getArriveOrder(){
        TextView btnArrive =  (TextView)itemView.findViewById(R.id.arrive);
        return btnArrive;
    }

    private void init(View itemView){
        TvOrderID = (TextView) itemView.findViewById(R.id.OrderID);
        TvStartTime = (TextView) itemView.findViewById(R.id.StartTime);
        TvPlace = (TextView) itemView.findViewById(R.id.place);
        TvReceiver = (TextView) itemView.findViewById(R.id.Receiver);
        TvSender = (TextView) itemView.findViewById(R.id.Sender);
        TvItemType = (TextView) itemView.findViewById(R.id.itemType);
        TvDistinition = (TextView) itemView.findViewById(R.id.distinction);
    }

    public TextView getTvDistinition(){
        return TvDistinition;
    }

    public TextView getTvOrderID() {
        return TvOrderID;
    }

    public TextView getTvStartTime() {
        return TvStartTime;
    }

    public TextView getTvReceiver() {
        return TvReceiver;
    }

    public TextView getTvSender() {
        return TvSender;
    }

    public TextView getTvPlace() {
        return TvPlace;
    }

//    public void bind(final MyOrder item) {
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick(item);
//            }
//        });
//    }
}