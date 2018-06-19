package com.example.milorad.rafroid.app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.adapters.ConsultAdapter;
import com.example.milorad.rafroid.data.Manager;

public class ConsultActivity extends AppCompatActivity {

    RecyclerView consult_recycler;
    ConsultAdapter adapter;
    Manager manager = Manager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consult_fragment);

        consult_recycler = findViewById(R.id.consult_recycler);
        adapter = new ConsultAdapter(this, manager.getConsultations());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        consult_recycler.setHasFixedSize(true);
        consult_recycler.setLayoutManager(layoutManager);
        consult_recycler.setItemAnimator(new DefaultItemAnimator());
        consult_recycler.setAdapter(adapter);
    }
}
