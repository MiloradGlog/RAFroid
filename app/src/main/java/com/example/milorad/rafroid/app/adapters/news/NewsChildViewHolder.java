package com.example.milorad.rafroid.app.adapters.news;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.milorad.rafroid.R;


public class NewsChildViewHolder extends ChildViewHolder
{
    public TextView opis;


    public NewsChildViewHolder(View itemView)
    {
        super(itemView);
        opis = (TextView) itemView.findViewById(R.id.opis);
    }
}
