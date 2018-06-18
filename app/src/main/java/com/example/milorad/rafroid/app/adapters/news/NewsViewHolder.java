package com.example.milorad.rafroid.app.adapters.news;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.milorad.rafroid.R;

import org.w3c.dom.Text;

public class NewsViewHolder extends ParentViewHolder
{
    public TextView naslov;
    public TextView datum;


    public NewsViewHolder(View itemView)
    {
        super(itemView);
        naslov = (TextView) itemView.findViewById(R.id.naslov);
        datum = (TextView) itemView.findViewById(R.id.datum);
    }
}
