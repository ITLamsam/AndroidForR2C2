package com.example.fyp_robot_dog_androidx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.fyp_robot_dog_androidx.Api_And_Function.CustomAlert;
import com.example.fyp_robot_dog_androidx.databinding.LayoutHomeBottomMenuBinding;
import com.example.fyp_robot_dog_androidx.Api_And_Function.table_layout.FragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;


public class Home extends AppCompatActivity{
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private FragmentAdapter fragmentAdapter;
    private LayoutHomeBottomMenuBinding binding;
    private Button homeButton;
    private ConstraintLayout conLayput;
    private BottomNavigationView BottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        binding = LayoutHomeBottomMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Home_Page());
        init();
        buttonMenuInit();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAlert.alertMessage(getSupportActionBar().getThemedContext(), "Testing", "Click Home Botton");
            }
        });

        BottomMenu.getMenu().setGroupCheckable(0, false, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void buttonMenuInit(){
        binding.bottonNegavionView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.newOrder:
                    Intent intent = new Intent(this, New_Order_Booking_Calendar.class);
                    startActivity(intent);
                    break;
                case R.id.dogGuide:
                    break;
                case R.id.iconHome:
                    break;
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    private void init(){
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        homeButton = findViewById(R.id.HomeButton);
        BottomMenu = findViewById(R.id.bottonNegavionView);
        conLayput = findViewById(R.id.conLayput);
    }

    private void tabloutPageViewSetting(){
        FragmentManager fm = getSupportFragmentManager();

        fragmentAdapter = new FragmentAdapter(fm,getLifecycle());

        viewPager2.setAdapter(fragmentAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Order"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cellDayText:
//                openDialog();
                break;
            case R.id.AM:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goNotice(View view){
        Intent intent = new Intent(Home.this, User_Notification.class);
        startActivity(intent);
    }

    public void goSetting(View view){
        Intent intent = new Intent(Home.this, User_Setting.class);
        startActivity(intent);
    }
}
