package com.example.milorad.rafroid.app.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.fragments.MyScheduleFragment;
import com.example.milorad.rafroid.app.adapters.SectionsPageAdapter;
import com.example.milorad.rafroid.app.fragments.SearchFragment;
import com.example.milorad.rafroid.data.dataInterface.MyJSONParser;
import com.example.milorad.rafroid.data.Manager;

public class SearchActivity extends AppCompatActivity {

    private Manager manager = Manager.getInstance();
    private static final String TAG = "SearchActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mviewPager;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;

    private static final String DEBUG_TAG = "TESTIRAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        loadData();

        findViews();

        setupSupportActionBar();

        setupNavigationView();

        setupTabView();

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
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        mviewPager = (ViewPager) findViewById(R.id.container);
    }

    private void setupSupportActionBar(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        actionBarDrawerToggle = new ActionBarDrawerToggle(SearchActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupTabView(){

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        setupViewPager(mviewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);
    }

    private void setupNavigationView(){
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuSelector(item);
                return false;
            }
        });
    }

    private void loadData(){

        Intent intent = getIntent();
        String classesJson = intent.getStringExtra(SplashActivity.CLASS_TAG);
        String newsJson = intent.getStringExtra(SplashActivity.NEWS_TAG);
        String consultationsJson = intent.getStringExtra(SplashActivity.CONSULT_TAG);
        String examsJson = intent.getStringExtra(SplashActivity.EXAMS_TAG);

        MyJSONParser parser = new MyJSONParser();
        parser.parseClassesToManager(classesJson);
        parser.parseNewsToManager(newsJson);
        parser.parseConsultationsToManager(consultationsJson);
        parser.parseExamsToManager(examsJson);
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
                Intent newsIntent = new Intent(getApplicationContext(), NewsActivity.class);
                startActivity(newsIntent);
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
