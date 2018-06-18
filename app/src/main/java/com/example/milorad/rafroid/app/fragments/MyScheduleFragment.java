package com.example.milorad.rafroid.app.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.adapters.LectureAdapter;
import com.example.milorad.rafroid.data.Manager;
import com.example.milorad.rafroid.data.model.Lecture;
import com.example.milorad.rafroid.data.model.Group;

import java.util.List;

public class MyScheduleFragment extends Fragment
{
    private static final String TAG = "MyScheduleFragment";
    private RecyclerView lecture_RecyclerView;
    private LectureAdapter lectureAdapter;
    private List<Lecture> lectureList;
    private Manager manager = Manager.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_schedule_fragment,container,false);


        lecture_RecyclerView = view.getRootView().findViewById(R.id.my_schedule_fragment_recycler_view);


        SharedPreferences preferences = getContext().getSharedPreferences(Manager.PREFERENCES_NAME, Context.MODE_PRIVATE);
        //povlacimo korisnikovu grupu iz preferences, default je 101 ako ne nadje nista
        String grupaString = preferences.getString(Manager.PREFERENCE_USER_GROUP_KEY, "101");


        Group group = manager.getGroupByString(grupaString);
        lectureList = manager.getLecturesByGroup(group);
        lectureAdapter = new LectureAdapter(view.getContext(), lectureList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        lecture_RecyclerView.setHasFixedSize(true);
        lecture_RecyclerView.setLayoutManager(layoutManager);
        lecture_RecyclerView.setItemAnimator(new DefaultItemAnimator());
        lecture_RecyclerView.setAdapter(lectureAdapter);

        return view;
    }
}
