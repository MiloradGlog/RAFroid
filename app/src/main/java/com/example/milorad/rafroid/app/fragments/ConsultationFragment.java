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
import com.example.milorad.rafroid.app.adapters.ConsultAdapter;
import com.example.milorad.rafroid.data.Manager;

public class ConsultationFragment extends Fragment {

    RecyclerView consult_recycler;
    ConsultAdapter adapter;
    Manager manager = Manager.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.consult_fragment,container,false);

        consult_recycler = view.findViewById(R.id.consult_recycler);
        adapter = new ConsultAdapter(getContext(), manager.getConsultations());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        consult_recycler.setHasFixedSize(true);
        consult_recycler.setLayoutManager(layoutManager);
        consult_recycler.setItemAnimator(new DefaultItemAnimator());
        consult_recycler.setAdapter(adapter);

        return view;
    }



}
