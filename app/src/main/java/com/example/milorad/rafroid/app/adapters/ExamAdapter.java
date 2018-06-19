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
import com.example.milorad.rafroid.data.model.Exam;
import com.example.milorad.rafroid.data.model.Lecture;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.MyViewHolder>
{
    private Context context;
    private List<Exam> examList;
    private Manager manager;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView examProf;
        public TextView examClass;
        public TextView examDate;
        public TextView examTime;
        public TextView examName;

        public MyViewHolder(View view) {
            super(view);

            examName = view.findViewById(R.id.exam_name);
            examProf = view.findViewById(R.id.exam_prof);
            examDate = view.findViewById(R.id.exam_date);
            examTime = view.findViewById(R.id.exam_time);
            examClass = view.findViewById(R.id.exam_classroom);
        }
    }

    public ExamAdapter(Context context, List<Exam> examList)
    {
        this.context = context;
        this.examList = examList;
    }

    @NonNull
    @Override
    public ExamAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View examView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_item, parent, false);
        return new MyViewHolder(examView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamAdapter.MyViewHolder holder, int position) {
        Exam exam = examList.get(position);
        holder.examName.setText(exam.getTestName());
        holder.examClass.setText(exam.getClassroom());
        holder.examDate.setText(exam.getDate());
        holder.examTime.setText(exam.getTime());
        holder.examProf.setText(exam.getProfessor());

    }

    @Override
    public int getItemCount() {
        return examList.size();
    }
}
