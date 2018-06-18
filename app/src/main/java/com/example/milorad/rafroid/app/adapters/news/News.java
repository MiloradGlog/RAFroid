package com.example.milorad.rafroid.app.adapters.news;

import android.content.Context;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.milorad.rafroid.data.model.Lecture;

import java.util.List;

public class News implements ParentObject{

    private Context context;
    private List<Object> childrenList;
    private String naslov;
    private String datum;

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public News(String naslov, String datum)
    {
        this.naslov = naslov;
        this.datum = datum;
    }


    @Override
    public List<Object> getChildObjectList() {
        return childrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        childrenList = list;
    }
}
