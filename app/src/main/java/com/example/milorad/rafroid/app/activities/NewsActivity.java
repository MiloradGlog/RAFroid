package com.example.milorad.rafroid.app.activities;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.adapters.news.News;
import com.example.milorad.rafroid.app.adapters.news.NewsAdapter;
import com.example.milorad.rafroid.app.adapters.news.NewsChild;
import com.example.milorad.rafroid.data.Manager;
import com.example.milorad.rafroid.data.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    RecyclerView newsRecycler;
    ArrayList<News> newsList;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        ((NewsAdapter)newsRecycler.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        newsRecycler = (RecyclerView) findViewById(R.id.newsRecycler);
        newsRecycler.setLayoutManager(new LinearLayoutManager(this));
        NewsAdapter adapter = new NewsAdapter(this, initData());
        adapter.setParentAndIconExpandOnClick(true);
        adapter.setParentClickableViewAnimationDefaultDuration();
        newsRecycler.setAdapter(adapter);
    }


    private List<ParentObject> initData()
    {
        List<ParentObject> news = new ArrayList<>();
        List<NewsModel> vesti = Manager.getInstance().getNews();
        int i = 0;
        for(NewsModel vest:vesti)
        {
            List<Object> childList = new ArrayList<>();
            childList.add(new NewsChild(vest.getText()));
            news.add(new News(vest.getTitle(),vest.getDate()));
            news.get(i).setChildObjectList(childList);
            i++;
        }

        return news;
    }
}
