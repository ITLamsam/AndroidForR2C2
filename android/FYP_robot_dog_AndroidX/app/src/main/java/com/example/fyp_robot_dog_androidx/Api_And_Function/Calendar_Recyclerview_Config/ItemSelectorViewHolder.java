package com.example.fyp_robot_dog_androidx.Api_And_Function.Calendar_Recyclerview_Config;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp_robot_dog_androidx.R;

public class ItemSelectorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView TimeText;
    public TextView calendarSelector;
    private final ItemSelectorAdapter.OnItemListener onItemListener;
    private RecyclerView recyclerView;

    public ItemSelectorViewHolder(@NonNull View itemView, ItemSelectorAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        TimeText = itemView.findViewById(R.id.calendarSelector);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        init(itemView);
    }

    public TextView getCalendarSelectorn(){
        return calendarSelector;
    }


    private void init(View v){
        recyclerView = v.findViewById(R.id.timeSelector);
        calendarSelector = v.findViewById(R.id.calendarSelector);
    }

    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), (String) TimeText.getText());
    }
}