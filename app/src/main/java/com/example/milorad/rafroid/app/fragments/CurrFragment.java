package com.example.milorad.rafroid.app.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.adapters.CurrAdapter;
import com.example.milorad.rafroid.app.adapters.ExamAdapter;
import com.example.milorad.rafroid.data.Manager;
import com.example.milorad.rafroid.data.model.Curriculum;
import com.example.milorad.rafroid.data.model.Exam;

import java.util.List;

public class CurrFragment extends Fragment
{
    private RecyclerView currRecycler;
    private CurrAdapter adapter;
    private List<Curriculum> currList;
    private Manager manager = Manager.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.curr_fragment,container,false);
        currRecycler = view.getRootView().findViewById(R.id.curr_recycler);

        currList = manager.getCurriculums();
        adapter = new CurrAdapter(view.getContext(), currList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        currRecycler.setHasFixedSize(true);
        currRecycler.setLayoutManager(layoutManager);
        currRecycler.setItemAnimator(new DefaultItemAnimator());
        currRecycler.setAdapter(adapter);

        return view;
    }
}

