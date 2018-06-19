package com.example.milorad.rafroid.data.model;

public class Consultation {

    private String day;
    private String startTime;
    private String endTime;
    private String lecturer;
    private String className;
    private String classroom;

    public Consultation(String day, String startTime, String endTime, String lecturer, String className, String classroom){
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
