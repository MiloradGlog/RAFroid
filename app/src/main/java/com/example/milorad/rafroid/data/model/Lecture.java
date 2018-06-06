package com.example.milorad.rafroid.data.model;

import java.util.ArrayList;

/**
 * Created by Milorad on 6/2/2018.
 */

public class Lecture {

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


    public boolean hasLecturer(Lecturer l){
        if (lecturer.equals(l)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasDay(DAY d){
        if (this.day == d){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasGroup(Group g){
        for (Group gr : groups){
            if(gr.getName().equals(g.getName())){
                return true;
            }
            else {
                continue;
            }
        }
        return false;
    }

    public boolean hasSubject(Subject s){
        if (subject.equals(s)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasClassroom(Classroom c){
        if (classroom.getName().equals(c.getName())){
            return true;
        }
        else {
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
