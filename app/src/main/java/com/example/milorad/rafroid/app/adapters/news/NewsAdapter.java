package com.example.milorad.rafroid.app.adapters.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.milorad.rafroid.R;

import java.util.List;

public class NewsAdapter extends ExpandableRecyclerAdapter<NewsViewHolder,NewsChildViewHolder>
{
    LayoutInflater inflater;

    public  NewsAdapter(Context context, List<ParentObject> parentList)
    {
        super(context, parentList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.news_parent_item,viewGroup,false);
        return new NewsViewHolder(view);
    }

    @Override
    public NewsChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.news_child_item,viewGroup,false);
        return new NewsChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(NewsViewHolder newsViewHolder, int i, Object o) {
        News news = (News)o;
        newsViewHolder.naslov.setText(news.getNaslov());
        newsViewHolder.datum.setText(news.getDatum());
    }

    @Override
    public void onBindChildViewHolder(NewsChildViewHolder newsChildViewHolder, int i, Object o) {
        NewsChild newsChild = (NewsChild)o;
        newsChildViewHolder.opis.setText(newsChild.getTekst());
    }
}
