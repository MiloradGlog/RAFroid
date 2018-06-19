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
    }


    private void setupViewPager(ViewPager viewPager)
    {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new MyScheduleFragment(), "Moj Raspored");
        adapter.addFragment(new SearchFragment(), "Pretraga Rasporeda");
        adapter.addFragment(new ExamFragment(), "Ispiti");
        adapter.addFragment(new CurrFragment(), "Kolokvijumi");
        viewPager.setAdapter(adapter);
    }




}
