package com.example.milorad.rafroid.data.model;

import android.support.v7.widget.util.SortedListAdapterCallback;

import java.util.ArrayList;

/**
 * Created by Milorad on 6/2/2018.
 */

public class Lecture{

    private Subject subject;
    private Lecturer lecturer;
    private ArrayList<Group> groups;
    private Classroom classroom;
    private DAY day;
    private String startTime; //SAMO ZA ISPIS, VEROVATNO CE TREBATI DA SE PREPRAVI U DATE
    private String endTime;
    private String type;


    public Lecture(Subject subject, Lecturer lecturer, ArrayList<Group> groups, Classroom classroom, DAY day, String startTime, String endTime, String type){
        this.subject = subject;
        this.lecturer = lecturer;
        this.groups = groups;
        this.classroom = classroom;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }


    public boolean hasLecturer(String l){
        if (lecturer.getName().toLowerCase().contains(l.toLowerCase())){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasDay(String d){
        if (this.day != null && this.day.toString().toLowerCase().contains(d.toLowerCase())){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasGroup(String g){
        for (Group gr : groups){
            if(gr.getName().toLowerCase().contains(g.toLowerCase())){
                return true;
            }
            else {
                continue;
            }
        }
        return false;
    }

    public boolean hasSubject(String s){
        if (subject.getName().toLowerCase().contains(s.toLowerCase())){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasClassroom(String c){
        if (classroom.getName().toLowerCase().contains(c.toLowerCase())){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasLectureByText(String searchQuery){
        if(this.subject.getName().contains(searchQuery)){
            return true;
        }
        else{
            return false;
        }
    }




    public Subject getSubject() {
        return subject;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public DAY getDay() {
        return day;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public String getGroupsString(){
        if(groups != null){
            String groupsString = "";
            for(int i = 0; i < groups.size(); i++){
                if(i == groups.size() - 1){
                    groupsString += groups.get(i).getName();
                }
                else{
                    groupsString += groups.get(i).getName() + ", ";
                }
            }
            return groupsString;
        }
        else {
            return "Nema grupa";
        }
    }

    public String getType() {
        return type;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    @Override
    public String toString() {
        String str = "Subject: "+ subject.getName() +", classroom: "+ classroom.getName() +", day: "+ day + ", groups: " ;
        for (Group g : groups){
            str += g.getName() + ", ";
        }
        str += "type: "+ type +", Start time:" + startTime + ", End time:" + endTime + ", lecturer: "+ lecturer.getName();
        return str;
    }
}
