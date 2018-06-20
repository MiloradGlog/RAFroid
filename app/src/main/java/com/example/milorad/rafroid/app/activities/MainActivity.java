package com.example.milorad.rafroid.app.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.adapters.SectionsPageAdapter;
import com.example.milorad.rafroid.app.fragments.ConsultationFragment;
import com.example.milorad.rafroid.app.fragments.NewsFragment;
import com.example.milorad.rafroid.app.fragments.SearchMainFragment;
import com.example.milorad.rafroid.app.fragments.SettingsFragment;
import com.example.milorad.rafroid.data.dataInterface.MyJSONParser;
import com.example.milorad.rafroid.data.Manager;
import com.example.milorad.rafroid.data.model.Exam;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class MainActivity extends AppCompatActivity {

    private Manager manager = Manager.getInstance();
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;

    private SearchMainFragment searchMainFragment;
    private NewsFragment newsFragment;
    private ConsultationFragment consultFragment;
    private SettingsFragment settingsFragment;

    private static final String DEBUG_TAG = "TESTIRAM";

    private static final String CLASSES_FILENAME = "classes.json";
    private static final String NEWS_FILENAME = "news.json";
    private static final String CONSULT_FILENAME = "consult.json";
    private static final String EXAMS_FILENAME = "exams.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchMainFragment = new SearchMainFragment();
        newsFragment = new NewsFragment();
        consultFragment = new ConsultationFragment();
        settingsFragment = new SettingsFragment();

        loadData();

        findViews();

        setupSupportActionBar();

        setupNavigationView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void findViews(){

        toolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    private void setupSupportActionBar(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void setupNavigationView(){
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuSelector(item);
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    private void loadData(){


        Intent intent = getIntent();

        boolean success = intent.getBooleanExtra(SplashActivity.SUCCESS_TAG, false);
        if (success){
            String classesJson = intent.getStringExtra(SplashActivity.CLASS_TAG);
            String newsJson = intent.getStringExtra(SplashActivity.NEWS_TAG);
            String consultationsJson = intent.getStringExtra(SplashActivity.CONSULT_TAG);
            String examsJson = intent.getStringExtra(SplashActivity.EXAMS_TAG);

            MyJSONParser parser = new MyJSONParser();
            parser.parseClassesToManager(classesJson);
            parser.parseNewsToManager(newsJson);
            parser.parseConsultationsToManager(consultationsJson);
            parser.parseExamsToManager(examsJson);

            saveAllJSONS(classesJson, newsJson, consultationsJson, examsJson);
        }
        else {
            Toast.makeText(this, "Konekcija neuspesna", Toast.LENGTH_SHORT).show();
            loadDataFromLocalStorage();
        }

//
//        for (NewsModel n : manager.getNews()){
//            Log.d(DEBUG_TAG + "NEWS", n.getTitle());
//        }
//        for (Consultation c : manager.getConsultations()){
//            Log.d(DEBUG_TAG + "CONS", c.getClassName());
//        }
//        for (Exam e : manager.getExams()){
//            Log.d(DEBUG_TAG + "EXAM", e.getTestName());
//        }
//        for (Curriculum c : manager.getCurriculums()){
//            Log.d(DEBUG_TAG, c.getTestName());
//        }
    }


    private void menuSelector(MenuItem item){

        switch (item.getItemId()){
            case R.id.nav_raspored:
                beginFragmentTransaction(searchMainFragment);
                break;
            case R.id.nav_vesti:
                beginFragmentTransaction(newsFragment);
                break;
            case R.id.nav_konsultacije:
                beginFragmentTransaction(consultFragment);
                break;

            case R.id.nav_opcije:
                beginFragmentTransaction(settingsFragment);
                break;
        }

    }

    private void beginFragmentTransaction(Fragment f){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (!f.isAdded()){
            fragmentTransaction.add(R.id.fragment_container, f);
            fragmentTransaction.commit();
        }
        else {
            fragmentTransaction.replace(R.id.fragment_container, f);
            fragmentTransaction.commit();
        }
    }

    private void saveAllJSONS(String classJSON, String newsJSON, String consultJSON, String examJSON){
        try {
            FileOutputStream fos = openFileOutput(CLASSES_FILENAME, MODE_PRIVATE);
            fos.write(classJSON.getBytes());
            fos.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            FileOutputStream fos = openFileOutput(NEWS_FILENAME, MODE_PRIVATE);
            fos.write(newsJSON.getBytes());
            fos.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            FileOutputStream fos = openFileOutput(CONSULT_FILENAME, MODE_PRIVATE);
            fos.write(consultJSON.getBytes());
            fos.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            FileOutputStream fos = openFileOutput(EXAMS_FILENAME, MODE_PRIVATE);
            fos.write(examJSON.getBytes());
            fos.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void loadDataFromLocalStorage(){
        String classString = readFile(CLASSES_FILENAME);
        String newsString = readFile(NEWS_FILENAME);
        String consultString = readFile(CONSULT_FILENAME);
        String examString = readFile(EXAMS_FILENAME);


        MyJSONParser parser = new MyJSONParser();
        parser.parseClassesToManager(classString);
        parser.parseNewsToManager(newsString);
        parser.parseConsultationsToManager(consultString);
        parser.parseExamsToManager(examString);

    }


    private String readFile(String fileName){
        String newLine = System.getProperty("line.separator");
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
            String line;
            boolean flag = false;
            while ((line = reader.readLine()) != null) {
                result.append(flag? newLine: "").append(line);
                flag = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return result.toString();
    }
}
