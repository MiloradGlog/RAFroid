package com.example.milorad.rafroid.app.fragments.searchFragments;

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
import com.example.milorad.rafroid.app.adapters.ExamAdapter;
import com.example.milorad.rafroid.data.Manager;
import com.example.milorad.rafroid.data.model.Exam;

import java.util.List;

public class ExamFragment extends Fragment
{
    private RecyclerView examRecycler;
    private ExamAdapter adapter;
    private List<Exam> examList;
    private Manager manager = Manager.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exam_fragment,container,false);
        examRecycler = view.getRootView().findViewById(R.id.exams_recycler);

        examList = manager.getExams();
        adapter = new ExamAdapter(view.getContext(), examList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        examRecycler.setHasFixedSize(true);
        examRecycler.setLayoutManager(layoutManager);
        examRecycler.setItemAnimator(new DefaultItemAnimator());
        examRecycler.setAdapter(adapter);

        return view;
    }
}
