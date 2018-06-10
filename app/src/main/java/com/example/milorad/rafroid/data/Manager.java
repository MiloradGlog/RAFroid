package com.example.milorad.rafroid.data;

import android.util.Log;

import com.example.milorad.rafroid.data.model.Classroom;
import com.example.milorad.rafroid.data.model.DAY;
import com.example.milorad.rafroid.data.model.Group;
import com.example.milorad.rafroid.data.model.Lecture;
import com.example.milorad.rafroid.data.model.Lecturer;
import com.example.milorad.rafroid.data.model.Subject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Milorad on 6/2/2018.
 */

public class Manager {

    private ArrayList<Subject> subjects;
    private ArrayList<Lecturer> lecturers;
    private ArrayList<Group> groups;
    private ArrayList<Classroom> classrooms;
    private ArrayList<Lecture> lectures;

    private static Manager instance;

    private Manager(){
        subjects = new ArrayList<Subject>();
        lecturers = new ArrayList<Lecturer>();
        groups = new ArrayList<Group>();
        classrooms = new ArrayList<Classroom>();
        lectures = new ArrayList<Lecture>();
    }

    public static Manager getInstance(){
        if (instance == null){
            instance = new Manager();
        }
        return instance;
    }


    /*
    FUNKCIJE ZA DOBAVLJANJE INFORMACIJA
     */

    /**
     *
     * @param lecturer objekat predavaca, vraca listu svih casova koji imaju tog predavaca
     * @return vraca ArrayList<Lecture> svih predavanja sa zadatim predavacem
     */
    public ArrayList<Lecture> getLecturesByLecturer(Lecturer lecturer){
        ArrayList<Lecture> list = new ArrayList<Lecture>();
        for (Lecture l : lectures){
            if (l.hasLecturer(lecturer.getName())) list.add(l);
        }
        return list;
    }

    /**
     *
     * @param group objekat grupe za koju trazimo
     * @return vraca ArrayList<Lecture> svih predavanja sa datom grupom
     */
    public ArrayList<Lecture> getLecturesByGroup(Group group){
        ArrayList<Lecture> list = new ArrayList<Lecture>();
        for (Lecture l : lectures){
            if (l.hasGroup(group.getName())) list.add(l);
        }
        return list;
    }
    /**
     *
     * @param subject objekat predmeta za koji trazimo
     * @return vraca ArrayList<Lecture> svih predavanja sa datim predmetom
     */
    public ArrayList<Lecture> getLecturesBySubject(Subject subject){
        ArrayList<Lecture> list = new ArrayList<Lecture>();
        for (Lecture l : lectures){
            if (l.hasSubject(subject.getName())) list.add(l);
        }
        return list;
    }
    /**
     *
     * @param day Enum dana za koji hocemo predavanja
     * @return vraca ArrayList<Lecture> svih predavanja sa datom grupom
     */
    public ArrayList<Lecture> getLecturesByDay(DAY day){
        ArrayList<Lecture> list = new ArrayList<Lecture>();
        for (Lecture l : lectures){
            if (l.hasDay(day.toString())) list.add(l);
        }
        return list;
    }
    /**
     *
     * @param classroom objekat ucionice(Classroom) za koji pretrazujemo
     * @return vraca ArrayList<Lecture> svih predavanja u datoj ucionici
     */
    public ArrayList<Lecture> getLecturesByClassroom(Classroom classroom){
        ArrayList<Lecture> list = new ArrayList<Lecture>();
        for (Lecture l : lectures){
            if (l.hasClassroom(classroom.getName())) list.add(l);
        }
        return list;
    }



    /*
    FUNKCIJE ZA DODAVANJE U LISTE------------------
     */

    /**
     *
     * @param name Ime objekta koji treba da se doda
     * @return Vraca postojeci objekat ako postoji neki sa tim imenom u listi, ako ne vraca novi koji je dodao u listu
     */
    public Subject addSubject(String name){
        for (Subject s : subjects){
            if (s.getName().equals(name)){
                Log.e("ADD SUBJECT", "DUPLICATE");
                return s;
            }
        }
        Subject newSubject = new Subject(name);
        subjects.add(newSubject);
        return newSubject;
    }
    /**
     *
     * @param name Ime objekta koji treba da se doda
     * @return Vraca postojeci objekat ako postoji neki sa tim imenom u listi, ako ne vraca novi koji je dodao u listu
     */
    public Lecturer addLecturer(String name){
        for (Lecturer l : lecturers){
            if (l.getName().equals(name)){
                Log.e("ADD LECTURER", "DUPLICATE");
                return l;
            }
        }
        Lecturer newLecturer = new Lecturer(name);
        lecturers.add(newLecturer);
        return newLecturer;
    }
    /**
     *
     * @param name Ime objekta koji treba da se doda
     * @return Vraca postojeci objekat ako postoji neki sa tim imenom u listi, ako ne vraca novi koji je dodao u listu
     */
    public Group addGroup(String name){
        for (Group g : groups){
            if (g.getName().equals(name)){
                Log.e("ADD GROUP", "DUPLICATE");
                return g;
            }
        }
        Group newGroup = new Group(name);
        groups.add(newGroup);
        return newGroup;
    }
    /**
     *
     * @param name Ime objekta koji treba da se doda
     * @return Vraca postojeci objekat ako postoji neki sa tim imenom u listi, ako ne vraca novi koji je dodao u listu
     */
    public Classroom addClassroom(String name){
        for (Classroom c : classrooms){
            if (c.getName().equals(name)){
                Log.e("ADD CLASSROOM", "DUPLICATE");
                return c;
            }
        }
        Classroom newClassroom = new Classroom(name);
        classrooms.add(newClassroom);
        return newClassroom;
    }


    /*
    FUNKCIJE ZA DOBAVLJANJE ODREDJENOG OBJEKTA IZ LISTI
     */

    public Group getGroupByString(String name){
        for (Group g : groups){
            if (g.getName().equals(name)) return g;
        }
        Log.e("getGroupBy ERROR", "NOT FOUND");
        return null;
    }
    public Subject getSubjectByString(String name){
        for (Subject s : subjects){
            if (s.getName().equals(name)) return s;
        }
        Log.e("getSubjectBy ERROR", "NOT FOUND");
        return null;
    }
    public Classroom getClassroomByString(String name){
        for (Classroom c : classrooms){
            if (c.getName().equals(name)) return c;
        }
        Log.e("getClassroomBy ERROR", "NOT FOUND");
        return null;
    }
    public Lecturer getLecturerByString(String name){
        for (Lecturer l : lecturers){
            if (l.getName().equals(name)) return l;
        }
        Log.e("getLecturerBy ERROR", "NOT FOUND");
        return null;
    }



    /*
    GETERI I SETERI----------------------------
    */

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }
}
