package com.example.milorad.rafroid.data.model;

public class Consultation {

    private String day;
    private String time;
    private String lecturer;
    private String className;
    private String classroom;

    public Consultation(String day, String time, String lecturer, String className, String classroom){
        this.day = day;
        this.time = time;
        this.lecturer = lecturer;
        this.className = className;
        this.classroom = classroom;
    }

    public String getClassName() {
        return className;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getDay() {
        return day;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getTime() {
        return time;
    }
}
