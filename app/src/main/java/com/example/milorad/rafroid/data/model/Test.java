package com.example.milorad.rafroid.data.model;

public abstract class Test {

    private String testName;
    private String date;
    private String time;
    private String classroom;
    private String professor;

    public Test(String testName, String date, String time, String classroom, String professor) {
        this.testName = testName;
        this.date = date;
        this.time = time;
        this.classroom = classroom;
        this.professor = professor;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getProfessor() {
        return professor;
    }

    public String getTestName() {
        return testName;
    }
}
