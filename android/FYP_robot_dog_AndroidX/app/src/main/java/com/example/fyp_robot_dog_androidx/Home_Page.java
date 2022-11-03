package com.example.fyp_robot_dog_androidx;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config.Adapter;
import com.example.fyp_robot_dog_androidx.Api_And_Function.table_layout.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Page extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Adapter adapter;
    private RecyclerView recyclerView;

    private final int TITLE = 0, OrderD = 1, CurrentOrderD = 2, HistoryOrder = 3;

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentAdapter fragmentAdapter;

    public Home_Page() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Page.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Page newInstance(String param1, String param2) {
        Home_Page fragment = new Home_Page();
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
        return inflater.inflate(R.layout.fragment_home__page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        tabletPageViewSetting();
    }

    private void init(){
        tabLayout = (TabLayout)getView().findViewById(R.id.tab_layout);
        viewPager2 = (ViewPager2)getView().findViewById(R.id.view_pager);
        recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerview);
    }

    private void tabletPageViewSetting(){
        FragmentManager fm = getActivity().getSupportFragmentManager();

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

//    public void goNotice(View view){
//        CustomAlert.alertMessage(view.getContext(), "123", "!23");
//    }
}