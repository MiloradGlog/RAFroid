package com.example.milorad.rafroid.app.fragments.dayFragments;

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
import com.example.milorad.rafroid.data.dataInterface.MyJSONParser;
import com.example.milorad.rafroid.data.dataInterface.URLConnector;
import com.example.milorad.rafroid.data.model.Classroom;
import com.example.milorad.rafroid.data.model.DAY;
import com.example.milorad.rafroid.data.model.Lecture;

import org.json.JSONArray;

import java.util.List;

public class TuesdayFragment extends Fragment
{
    private static final String TAG = "MyScheduleFragment";
    private RecyclerView lecture_RecyclerView;
    private LectureAdapter lectureAdapter;
    private List<Lecture> lectureList;
    private Manager manager = Manager.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_fragment,container,false);

        lecture_RecyclerView = view.getRootView().findViewById(R.id.day_recycler_view);
        lectureList = manager.getLecturesByDay(DAY.UTO);
        lectureAdapter = new LectureAdapter(view.getContext(), lectureList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        lecture_RecyclerView.setHasFixedSize(true);
        lecture_RecyclerView.setLayoutManager(layoutManager);
        lecture_RecyclerView.setItemAnimator(new DefaultItemAnimator());
        lecture_RecyclerView.setAdapter(lectureAdapter);

        return view;
    }

}