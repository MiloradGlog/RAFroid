package com.example.milorad.rafroid.app;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.fragments.MyScheduleFragment;
import com.example.milorad.rafroid.app.adapters.SectionsPageAdapter;
import com.example.milorad.rafroid.app.fragments.SearchFragment;
import com.example.milorad.rafroid.app.fragments.dayFragments.FridayFragment;
import com.example.milorad.rafroid.app.fragments.dayFragments.MondayFragment;
import com.example.milorad.rafroid.app.fragments.dayFragments.ThursdayFragment;
import com.example.milorad.rafroid.app.fragments.dayFragments.TuesdayFragment;
import com.example.milorad.rafroid.app.fragments.dayFragments.WednesdayFragment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mviewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mviewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);

        test();

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

        for (Lecture l : manager.getLecturesByDay(DAY.MON)){
            Log.d("TESTIRAM API", "LECTURES:\n"+ l.toString());
        }
    }

    private void setupViewPager(ViewPager viewPager)
    {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MyScheduleFragment(), "Moj Raspored");
        adapter.addFragment(new SearchFragment(), "Pretraga Rasporeda");

        adapter.addFragment(new MondayFragment(), "Ponedeljak");
        adapter.addFragment(new TuesdayFragment(), "Utorak");
        adapter.addFragment(new WednesdayFragment(), "Sreda");
        adapter.addFragment(new ThursdayFragment(), "Cetvrtak");
        adapter.addFragment(new FridayFragment(), "Petak");
        viewPager.setAdapter(adapter);
    }


}
