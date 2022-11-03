package com.example.fyp_robot_dog_androidx.Api_And_Function.table_layout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull  Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new FirstFragement();
            case 1:
                return new SecondFragment();
        }

        return new FirstFragement();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

