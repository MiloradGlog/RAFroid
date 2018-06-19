package com.example.milorad.rafroid.app.fragments;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.adapters.news.News;
import com.example.milorad.rafroid.app.adapters.news.NewsAdapter;
import com.example.milorad.rafroid.app.adapters.news.NewsChild;
import com.example.milorad.rafroid.data.Manager;
import com.example.milorad.rafroid.data.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    RecyclerView newsRecycler;
    ArrayList<News> newsList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment,container,false);

        newsRecycler = (RecyclerView) view.findViewById(R.id.newsRecycler);
        newsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsAdapter adapter = new NewsAdapter(getContext(), initData());
        adapter.setParentAndIconExpandOnClick(true);
        adapter.setParentClickableViewAnimationDefaultDuration();
        newsRecycler.setAdapter(adapter);

        return view;
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
