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
import com.example.milorad.rafroid.data.model.Consultation;
import com.example.milorad.rafroid.data.model.Curriculum;

import java.util.List;

public class ConsultAdapter extends RecyclerView.Adapter<ConsultAdapter.MyViewHolder>
{

    private Context context;
    private List<Consultation> consultList;
    private Manager manager;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView consultProf;
        public TextView consultDay;
        public TextView consultStartTime;
        public TextView consultEndTime;
        public TextView consultName;
        public TextView consultClass;

        public MyViewHolder(View view) {
            super(view);

            consultName = view.findViewById(R.id.consult_name);
            consultProf = view.findViewById(R.id.consult_prof);
            consultDay = view.findViewById(R.id.consult_day);
            consultStartTime = view.findViewById(R.id.consult_startTime);
            consultEndTime = view.findViewById(R.id.consult_endTime);
            consultClass = view.findViewById(R.id.consult_class);
        }
    }

    public ConsultAdapter(Context context, List<Consultation> consultList)
    {
        this.context = context;
        this.consultList = consultList;
    }

    @NonNull
    @Override
    public ConsultAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View consultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.consult_item, parent, false);
        return new MyViewHolder(consultView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultAdapter.MyViewHolder holder, int position) {
        Consultation consult = consultList.get(position);
        holder.consultClass.setText(consult.getClassroom());
        holder.consultStartTime.setText(consult.getStartTime());
        holder.consultEndTime.setText(consult.getEndTime());
        holder.consultDay.setText(consult.getDay());
        holder.consultProf.setText(consult.getLecturer());
        holder.consultName.setText(consult.getClassName());
    }

    @Override
    public int getItemCount() {
        return consultList.size();
    }
}
