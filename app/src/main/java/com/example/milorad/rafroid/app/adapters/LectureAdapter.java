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
import com.example.milorad.rafroid.data.model.Lecture;

import java.util.List;

public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.MyViewHolder> {

    private Context context;
    private List<Lecture> lectureList;
    private Manager manager;


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView lectureName;
        public TextView lectureType;
        public TextView lectureClassroom;
        public TextView lectureLecturer;
        public TextView lectureDay;
        public TextView lectureStartTime;
        public TextView lectureEndTime;


        public MyViewHolder(View view){
            super(view);

            lectureName = view.findViewById(R.id.sp_lectureName);
            lectureType = view.findViewById(R.id.sp_lectureType);
            lectureClassroom = view.findViewById(R.id.sp_lectureClassroom);
            lectureLecturer = view.findViewById(R.id.sp_lectureLecturer);
            lectureDay = view.findViewById(R.id.sp_day);
            lectureStartTime = view.findViewById(R.id.sp_startTime);
            lectureEndTime = view.findViewById(R.id.sp_endTime);
        }

    }

    public LectureAdapter(Context context, List<Lecture> lectureList){
        this.context = context;
        this.lectureList = lectureList;
    }

    @NonNull
    @Override
    public LectureAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lectureView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_item, parent, false);
        return new MyViewHolder(lectureView);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureAdapter.MyViewHolder holder, int position) {
        Lecture lecture = lectureList.get(position);
        holder.lectureName.setText(lecture.getSubject().getName());
        holder.lectureLecturer.setText(lecture.getLecturer().getName());
        holder.lectureType.setText(lecture.getType());
        holder.lectureClassroom.setText(lecture.getClassroom().getName());
        holder.lectureDay.setText(lecture.getDay().toString());
        holder.lectureStartTime.setText(lecture.getStartTime());
        holder.lectureEndTime.setText(lecture.getEndTime());
    }

    @Override
    public int getItemCount() {
        return lectureList.size();
    }
}
