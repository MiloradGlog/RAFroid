package com.example.milorad.rafroid.app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.data.Manager;
import com.example.milorad.rafroid.data.model.Curriculum;
import com.example.milorad.rafroid.data.model.Exam;
import com.example.milorad.rafroid.data.model.Lecture;

import java.util.List;

public class CurrAdapter extends RecyclerView.Adapter<CurrAdapter.MyViewHolder>
{
    private Context context;
    private List<Curriculum> currList;
    private Manager manager;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView currProf;
        public TextView currClass;
        public TextView currDate;
        public TextView currStartTime;
        public TextView currEndTime;
        public TextView currName;

        public MyViewHolder(View view) {
            super(view);

            currName = view.findViewById(R.id.curr_name);
            currProf = view.findViewById(R.id.curr_prof);
            currDate = view.findViewById(R.id.curr_date);
            currStartTime = view.findViewById(R.id.curr_startTime);
            currEndTime = view.findViewById(R.id.curr_endTime);
            currClass = view.findViewById(R.id.curr_classroom);
        }
    }

    public CurrAdapter(Context context, List<Curriculum> currList)
    {
        this.context = context;
        this.currList = currList;
    }

    @NonNull
    @Override
    public CurrAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View currView = LayoutInflater.from(parent.getContext()).inflate(R.layout.curr_item, parent, false);
        return new MyViewHolder(currView);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrAdapter.MyViewHolder holder, int position) {
        Curriculum curr = currList.get(position);
        holder.currName.setText(curr.getTestName());
        holder.currClass.setText(curr.getClassroom());
        holder.currDate.setText(curr.getDate());
        holder.currStartTime.setText(curr.getStartTime());
        holder.currEndTime.setText(curr.getEndTime());
        holder.currProf.setText(curr.getProfessor());

    }

    @Override
    public int getItemCount() {
        return currList.size();
    }
}