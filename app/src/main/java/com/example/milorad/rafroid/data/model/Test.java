package com.example.milorad.rafroid.data.model;

public abstract class Test {

    private String testName;
    private String date;
    private String startTime;
    private String endTime;
    private String classroom;
    private String professor;

    public Test(String testName, String date, String startTime, String endTime, String classroom, String professor) {
        this.testName = testName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
        this.professor = professor;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getProfessor() {
        return professor;
    }

    public String getTestName() {
        return testName;
    }
}
