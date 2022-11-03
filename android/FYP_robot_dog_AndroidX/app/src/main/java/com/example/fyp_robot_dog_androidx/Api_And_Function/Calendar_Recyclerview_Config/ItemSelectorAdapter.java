package com.example.fyp_robot_dog_androidx.Api_And_Function.Calendar_Recyclerview_Config;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp_robot_dog_androidx.R;

import java.util.List;

public class ItemSelectorAdapter extends RecyclerView.Adapter<ItemSelectorViewHolder>
{
    private final List<Object> TimeTextList;
    private final OnItemListener onItemListener;
    private final int normal = 0, red = 1;

    public ItemSelectorAdapter(List<Object> TimeTextList, OnItemListener onItemListener)
    {
        this.TimeTextList = TimeTextList;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ItemSelectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_calendar_time, parent, false);
        return new ItemSelectorViewHolder(view, onItemListener);
    }

    public int getItemViewType(int position) {
        if(((Item_Text)TimeTextList.get(position)).getTextColor().equals("red"))
            return red;
        else if(((Item_Text)TimeTextList.get(position)).getTextColor().equals("normal"))
            return normal;
        return -1;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ItemSelectorViewHolder holder, int position)
    {
        Item_Text temp = (Item_Text) TimeTextList.get(position);
        holder.getCalendarSelectorn().setText(temp.getText());
        switch (holder.getItemViewType()) {
            case normal:
                holder.TimeText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case red:
                holder.getCalendarSelectorn().setTextColor(Color.parseColor("#FF0000"));
                break;
        }
    }

    @Override
    public int getItemCount()
    {
        return TimeTextList.size();
    }

    public interface OnItemListener
    {
        void onItemClick(int position, String dayText);
    }
}
