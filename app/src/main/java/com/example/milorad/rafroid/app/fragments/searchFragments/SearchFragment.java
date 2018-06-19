package com.example.milorad.rafroid.app.fragments.searchFragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.adapters.LectureAdapterSearch;
import com.example.milorad.rafroid.data.Manager;
import com.example.milorad.rafroid.data.model.Lecture;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener
{
    private static final String TAG = "MyScheduleFragment";
    private RecyclerView lecture_RecyclerView;
    private LectureAdapterSearch lectureAdapter;
    private List<Lecture> lectureList;
    private Manager manager = Manager.getInstance();
    private SearchView searchView;
    private Spinner dropdownMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment,container,false);

        lecture_RecyclerView = view.getRootView().findViewById(R.id.search_recycler_view);
        lectureList = manager.getLectures();
        lectureAdapter = new LectureAdapterSearch(view.getContext(), lectureList);
        dropdownMenu = view.findViewById(R.id.dropdown);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        lecture_RecyclerView.setHasFixedSize(true);
        lecture_RecyclerView.setLayoutManager(layoutManager);
        lecture_RecyclerView.setItemAnimator(new DefaultItemAnimator());
        lecture_RecyclerView.setAdapter(lectureAdapter);

        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        searchView = view.findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE); //Ako hocemo da ide celom duzinom
        searchView.setQueryHint(getResources().getString(R.string.queryHint));
        EditText editText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        editText.setTextColor(getResources().getColor(R.color.itemBackground));
        editText.setHintTextColor(getResources().getColor(R.color.timeBackground));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processQuery(query, dropdownMenu.getSelectedItem().toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processQuery(newText, dropdownMenu.getSelectedItem().toString());
                return false;
            }
        });

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.vrstaPretrage, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownMenu.setAdapter(arrayAdapter);
        dropdownMenu.setOnItemSelectedListener(this);

        return view;
    }

    private void processQuery(String query, String searchBy) {

        List<Lecture> result = new ArrayList<>();

        switch (searchBy){
            case "Predmet":
                for (Lecture l : manager.getLectures()){
                    if(l.hasSubject(query)){
                        result.add(l);
                    }
                }
                break;
            case "Grupa":
                for (Lecture l : manager.getLectures()){
                    if(l.hasGroup(query)){
                        result.add(l);
                    }
                }
                break;
            case "Profesor":
                for (Lecture l : manager.getLectures()){
                    if(l.hasLecturer(query)){
                        result.add(l);
                    }
                }
                break;
            case "Ucionica":
                for (Lecture l : manager.getLectures()){
                    if(l.hasClassroom(query)){
                        result.add(l);
                    }
                }
                break;
            case "Dan":
                for (Lecture l : manager.getLectures()){
                    if(l.hasDay(query)){
                        result.add(l);
                    }
                }
                break;
            case "Tip nastave":
                for (Lecture l : manager.getLectures()){
                    if(l.getType().toLowerCase().contains(query.toLowerCase())){
                        result.add(l);
                    }
                }
                break;
        }

        lectureAdapter.setLectureList(result);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        processQuery(searchView.getQuery().toString(), parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
