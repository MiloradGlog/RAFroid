package com.example.milorad.rafroid.data.model;

public abstract class Test {

    private String testName;
    private String dateAndTime;
    private String classroom;
    private String professor;

    public Test(String testName, String dateAndTime, String classroom, String professor) {
        this.testName = testName;
        this.dateAndTime = dateAndTime;
        this.classroom = classroom;
        this.professor = professor;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getProfessor() {
        return professor;
    }

    public String getTestName() {
        return testName;
    }
}
