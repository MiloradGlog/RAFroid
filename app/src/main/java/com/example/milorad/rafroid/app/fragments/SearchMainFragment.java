package com.example.milorad.rafroid.app.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.adapters.SectionsPageAdapter;
import com.example.milorad.rafroid.app.fragments.searchFragments.CurrFragment;
import com.example.milorad.rafroid.app.fragments.searchFragments.ExamFragment;
import com.example.milorad.rafroid.app.fragments.searchFragments.MyScheduleFragment;
import com.example.milorad.rafroid.app.fragments.searchFragments.SearchFragment;
import com.example.milorad.rafroid.data.Manager;

public class SearchMainFragment extends Fragment
{
    private Manager manager = Manager.getInstance();
    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mviewPager;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private View viewGlobal;

    //IKONICE ZA FRAGMENTE KOJI SE NALAZE DOLE U RASPOREDU
    private ImageView myScheduleIcon;
    private ImageView searchScheduleIcon;
    private ImageView examIcon;
    private ImageView currIcon;
    //----------------------------------------------------

    private static final String DEBUG_TAG = "TESTIRAM";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_main_fragment,container,false);
        viewGlobal = view;

        findViews();
        setupTabView();

        return view;
    }



    private void findViews(){

        drawerLayout = (DrawerLayout) viewGlobal.findViewById(R.id.drawer_layout);
        navigationView = viewGlobal.findViewById(R.id.nav_view);
        mviewPager = (ViewPager) viewGlobal.findViewById(R.id.container);
    }

    private void setupTabView(){

        mSectionsPageAdapter = new SectionsPageAdapter(getChildFragmentManager());
        setupViewPager(mviewPager);

        TabLayout tabLayout = (TabLayout) viewGlobal.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);

        tabLayout.getTabAt(0).setCustomView(R.layout.fragment0_item);
        tabLayout.getTabAt(1).setCustomView(R.layout.fragment1_item);
        tabLayout.getTabAt(2).setCustomView(R.layout.fragment2_item);
        tabLayout.getTabAt(3).setCustomView(R.layout.fragment3_item);

        myScheduleIcon = viewGlobal.findViewById(R.id.scheduleIcon);
        searchScheduleIcon = viewGlobal.findViewById(R.id.searchScheduleIcon);
        examIcon = viewGlobal.findViewById(R.id.examIcon);
        currIcon = viewGlobal.findViewById(R.id.currIcon);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        myScheduleIcon.setImageResource(R.drawable.ic_myscheduleselected);
                        break;
                    case 1:
                        searchScheduleIcon.setImageResource(R.drawable.ic_searchscheduleselected);
                        break;
                    case 2:
                        examIcon.setImageResource(R.drawable.ic_examselected);
                        break;
                    case 3:
                        currIcon.setImageResource(R.drawable.ic_currselected);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        myScheduleIcon.setImageResource(R.drawable.ic_myschedule);
                        break;
                    case 1:
                        searchScheduleIcon.setImageResource(R.drawable.ic_searchschedule);
                        break;
                    case 2:
                        examIcon.setImageResource(R.drawable.ic_exam);
                        break;
                    case 3:
                        currIcon.setImageResource(R.drawable.ic_curr);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void setupViewPager(ViewPager viewPager)
    {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new MyScheduleFragment(), null);
        adapter.addFragment(new SearchFragment(), null);
        adapter.addFragment(new ExamFragment(), null);
        adapter.addFragment(new CurrFragment(), null);

        viewPager.setAdapter(adapter);
    }




}
