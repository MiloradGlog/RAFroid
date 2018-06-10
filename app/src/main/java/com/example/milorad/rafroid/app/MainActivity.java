package com.example.milorad.rafroid.app;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.fragments.MyScheduleFragment;
import com.example.milorad.rafroid.app.adapters.SectionsPageAdapter;
import com.example.milorad.rafroid.app.fragments.SearchFragment;
import com.example.milorad.rafroid.data.dataInterface.MyJSONParser;
import com.example.milorad.rafroid.data.dataInterface.URLConnector;
import com.example.milorad.rafroid.data.Manager;
import com.example.milorad.rafroid.data.model.Classroom;
import com.example.milorad.rafroid.data.model.DAY;
import com.example.milorad.rafroid.data.model.Group;
import com.example.milorad.rafroid.data.model.Lecture;
import com.example.milorad.rafroid.data.model.Lecturer;
import com.example.milorad.rafroid.data.model.Subject;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private Manager manager = Manager.getInstance();
    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mviewPager;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuSelector(item);
                return false;
            }
        });

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mviewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mviewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);

        test();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void test(){

        URLConnector testConn = new URLConnector();
        JSONArray arr = testConn.getResponseJSONArray();
        MyJSONParser parser = new MyJSONParser();
        parser.parseDataToManager(arr);

        for (Lecture l : manager.getLectures()){
            Log.d("TESTIRAM", "LECTURE:\n" + l.toString());
        }
        for (Lecturer l : manager.getLecturers()){
            Log.d("TESTIRAM LECTURERE", "LECTURER: " + l.getName());
        }
        for (Classroom c : manager.getClassrooms()){
            Log.d("TESTIRAM CLASSROOM", "CLASSROOM: "+ c.getName());
        }
        for (Group g : manager.getGroups()){
            Log.d("TESTIRAM GRUPE", "GROUP: " + g.getName());
        }
        for (Subject s : manager.getSubjects()){
            Log.d("TESTIRAM SUBJECTE", "SUBJECT: " + s.getName());
        }

        for (Lecture l : manager.getLecturesByDay(DAY.PON)){
            Log.d("TESTIRAM API", "LECTURES:\n"+ l.toString());
        }
    }

    private void setupViewPager(ViewPager viewPager)
    {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MyScheduleFragment(), "Moj Raspored");
        adapter.addFragment(new SearchFragment(), "Pretraga Rasporeda");
        viewPager.setAdapter(adapter);
    }

    private void menuSelector(MenuItem item){

        switch (item.getItemId()){
            case R.id.nav_raspored:
                Toast.makeText(this, "Raspored", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_vesti:
                Toast.makeText(this, "Vesti", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_konsultacije:
                Toast.makeText(this, "Konsultacije", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_opcije:
                Toast.makeText(this, "Opcije", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
