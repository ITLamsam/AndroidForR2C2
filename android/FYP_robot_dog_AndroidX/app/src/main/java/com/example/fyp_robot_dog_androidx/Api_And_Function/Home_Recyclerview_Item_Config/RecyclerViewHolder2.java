package com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp_robot_dog_androidx.R;

import org.w3c.dom.Text;

public class RecyclerViewHolder2 extends RecyclerView.ViewHolder {

    public RecyclerViewHolder2(@NonNull View itemView) {
        super(itemView);
    }

    public TextView getItemTitle(){
        return itemView.findViewById(R.id.itemTitle);
    }

    public TextView getBtnWelcomeMessage(){
        TextView textView = itemView.findViewById(R.id.btnWelcome);
        return textView;
    }

    public TextView getItemContent(){
        return itemView.findViewById(R.id.itemContent);
    }
}