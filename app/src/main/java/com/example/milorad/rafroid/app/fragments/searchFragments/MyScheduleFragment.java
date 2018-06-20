package com.example.milorad.rafroid.app.fragments.searchFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.adapters.LectureAdapter;
import com.example.milorad.rafroid.data.Manager;
import com.example.milorad.rafroid.data.model.DAY;
import com.example.milorad.rafroid.data.model.Lecture;
import com.example.milorad.rafroid.data.model.Group;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyScheduleFragment extends Fragment
{
    private static final String TAG = "MyScheduleFragment";
    private RecyclerView lecture_RecyclerView;
    private LectureAdapter lectureAdapter;
    private List<Lecture> lectureList;
    private Manager manager = Manager.getInstance();

    private ArrayList<AppCompatButton> buttons;

    private AppCompatButton monButton;
    private AppCompatButton tueButton;
    private AppCompatButton wedButton;
    private AppCompatButton thuButton;
    private AppCompatButton friButton;
    private AppCompatButton satButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_schedule_fragment,container,false);

        setupRecyclerView(view);

        setUpButtons(view);

        DAY danasnjiDan = getCurrentDay();

        Log.d("GOVNO", danasnjiDan.toString());

        updateButtons(getCurrentDayButton(), danasnjiDan);
        updateAdapterByDay(danasnjiDan);

        return view;
    }

    private void setupRecyclerView(View view){

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
    }

    private void setUpButtons(View view){

        buttons = new ArrayList<>();

        monButton = view.findViewById(R.id.pon_button);
        tueButton = view.findViewById(R.id.uto_button);
        wedButton = view.findViewById(R.id.sre_button);
        thuButton = view.findViewById(R.id.cet_button);
        friButton = view.findViewById(R.id.pet_button);

        buttons.add(monButton);
        buttons.add(tueButton);
        buttons.add(wedButton);
        buttons.add(thuButton);
        buttons.add(friButton);

        setButtonOnClickListener(monButton, DAY.PON);
        setButtonOnClickListener(tueButton, DAY.UTO);
        setButtonOnClickListener(wedButton, DAY.SRE);
        setButtonOnClickListener(thuButton, DAY.ČET);
        setButtonOnClickListener(friButton, DAY.PET);

    }

    private void updateButtons(AppCompatButton clickedButton, DAY day){


        for (AppCompatButton btn : buttons){
            if (btn.equals(clickedButton)){
                Toast.makeText(getContext(), "select za "+ clickedButton.getText().toString(), Toast.LENGTH_SHORT);
                btn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btn.setTextColor(getResources().getColor(R.color.white));
            }
            else {
                btn.setBackgroundColor(getResources().getColor(R.color.itemBackground));
                btn.setTextColor(getResources().getColor(R.color.black));
            }
        }
    }

    private void setButtonOnClickListener(final AppCompatButton btn,final DAY day){

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtons(btn, day);
                updateAdapterByDay(day);
            }
        });
    }


    private void updateAdapterByDay(DAY day){
        SharedPreferences preferences = getContext().getSharedPreferences(Manager.PREFERENCES_NAME, Context.MODE_PRIVATE);
        //povlacimo korisnikovu grupu iz preferences, default je 101 ako ne nadje nista
        String grupaString = preferences.getString(Manager.PREFERENCE_USER_GROUP_KEY, "101");


        Group group = manager.getGroupByString(grupaString);
        lectureList = manager.getLecturesByGroupAndDay(group, day);
        lectureAdapter.setLectureList(lectureList);
        lectureAdapter.notifyDataSetChanged();
    }

    private DAY getCurrentDay(){

        Calendar cal = Calendar.getInstance();
        switch (cal.get(Calendar.DAY_OF_WEEK)){
            case(2):{
                return DAY.PON;
            }
            case(3):{
                return DAY.UTO;
            }
            case(4):{
                return DAY.SRE;
            }
            case(5):{
                return DAY.ČET;
            }
            case(6):{
                return DAY.PET;
            }
            default:{
                return DAY.PON;
            }
        }
    }

    private AppCompatButton getCurrentDayButton(){

        Calendar cal = Calendar.getInstance();
        switch (cal.get(Calendar.DAY_OF_WEEK)){
            case(2):{
                return monButton;
            }
            case(3):{
                return tueButton;
            }
            case(4):{
                return wedButton;
            }
            case(5):{
                return thuButton;
            }
            case(6):{
                return friButton;
            }
            default:{
                return monButton;
            }
        }
    }


}
