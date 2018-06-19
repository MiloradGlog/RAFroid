package com.example.milorad.rafroid.data.dataInterface;

import android.util.Log;

import com.example.milorad.rafroid.data.Manager;
import com.example.milorad.rafroid.data.model.DAY;
import com.example.milorad.rafroid.data.model.Group;
import com.example.milorad.rafroid.data.model.Lecture;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Milorad on 6/2/2018.
 */

public class MyJSONParser {

    private Manager manager;

    public MyJSONParser() {
        manager = Manager.getInstance();
    }


    /**
     * Parsira predavanja i pakuje ih u manager, treba da se pokrene samo jednom.
     * Ako manager.getLectures nije prazan, returnuje bez pitanja
     * @param classesJson String json, niz koji treba da sadrzi predavanja
     */
    public void parseClassesToManager(String classesJson){
        if (!manager.getLectures().isEmpty()){
            Log.e("ERROR PARSE DATA", "LECTURES NISU PRAZNI");
            return;
        }

        JSONArray arr = null;
        try{
            JSONObject o = new JSONObject(classesJson);
            arr = o.getJSONArray("schedule");
        } catch (Exception e){
            e.printStackTrace();
        }


        JSONObject o = null;
        String predmet = "";
        String tip = "";
        String nastavnik = "";
        String grupe = "";
        String dan = "";
        String termin = "";
        String ucionica = "";
        for (int i = 0; i < arr.length(); i++){
            try {
                o = arr.getJSONObject(i);

                predmet = o.getString("class_name");
                tip = o.getString("type");
                nastavnik = o.getString("lecturer");
                grupe = o.getString("student_groups");
                dan = o.getString("day_of_week");
                termin = o.getString("time");
                ucionica = o.getString("classroom");

                StringTokenizer stringTokenizer = new StringTokenizer(termin, "-");

                String startTime = stringTokenizer.nextToken();
                String endTime = stringTokenizer.nextToken() + ":00";

                Lecture newLecture = new Lecture(
                        manager.addSubject(predmet),
                        manager.addLecturer(nastavnik),
                        parseGroups(grupe),
                        manager.addClassroom(ucionica),
                        parseDay(dan),
                        startTime,
                        endTime,
                        tip);

                manager.getLectures().add(newLecture);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    /**
     * Pakuje grupe u listu za kreiranje Lecture(predavanja) i upisuje je u manager ako je vec nema tamo
     * @param str grupe u string formatu (npr. 301, 302, 303)
     * @return lista zasebnih grupa (npr. [(301),(302),(303)]
     */
    private ArrayList<Group> parseGroups(String str){
        ArrayList<Group> list = new ArrayList<Group>();
        StringTokenizer tokenizer = new StringTokenizer(str, ",");     //Deli grupe kod svakog zareza
        while (tokenizer.hasMoreTokens()){                                   //Vrti dok ima tokena
            String name = tokenizer.nextToken();                           //Uzima sledeci token
            name = name.replaceAll("\\s", "");         //Izbaci sve razmake iz string
            list.add(manager.addGroup(name));                               //dodaje grupu u menadzer ako je tamo nema, i upisuje u listu koju vracamo
        }
        return list;
    }

    /**
     *
     * @param dayString string za koji se ocekuje da ima vrednosti PON, UTO, SRE, ČET, PET
     * @return ENUM DAY na osnovu dana
     */
    private DAY parseDay(String dayString){
        switch (dayString){
            case ("PON"):{
                return DAY.PON;
            }case ("UTO"):{
                return DAY.UTO;
            }case ("SRE"):{
                return DAY.SRE;
            }case ("ČET"):{
                return DAY.ČET;
            }case ("PET"):{
                return DAY.PET;
            }case ("SUB"):{
                return DAY.SUB;
            }
        }
        Log.e("PARSE DAY ERROR", "FOLLOWING STRING WAS NOT PARSED CORRECTLY: "+ dayString);
        return null;
    }



    public void parseNewsToManager(String newsJson){
        if (!manager.getNews().isEmpty()){
            Log.e("ERROR PARSE DATA", "News NISU PRAZNI");
            return;
        }

        JSONArray arr = null;
        try{
            JSONObject o = new JSONObject(newsJson);
            arr = o.getJSONArray("news");
        } catch (Exception e){
            e.printStackTrace();
        }


        JSONObject o = null;
        String date = "";
        String title = "";
        String text = "";
        for (int i = 0; i < arr.length(); i++){
            try {
                o = arr.getJSONObject(i);

                date = o.getString("date");
                title = o.getString("title");
                text = o.getString("text");
                String newDate = date.substring(0,10) + "    " + date.substring(11, 19);

                manager.addNews(title, text, newDate);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void parseConsultationsToManager(String consultationsJson){
        if (!manager.getConsultations().isEmpty()){
            Log.e("ERROR PARSE DATA", "Consultations NISU PRAZNI");
            return;
        }

        JSONArray arr = null;
        try{
            JSONObject o = new JSONObject(consultationsJson);
            arr = o.getJSONArray("schedule");
        } catch (Exception e){
            e.printStackTrace();
        }


        JSONObject o = null;
        String day = "";
        String time = "";
        String lecturer = "";
        String className = "";
        String classroom = "";
        for (int i = 0; i < arr.length(); i++){
            try {
                o = arr.getJSONObject(i);

                day = o.getString("day");
                time = o.getString("time") + "h";
                lecturer = o.getString("lecturer");
                className = o.getString("class_name");
                classroom = o.getString("classroom");

                String startTime = time.substring(0, 2) + ":00";
                String endTime = time.substring(3, 5) + ":00";

                String newDay = day.substring(0,3).toUpperCase();

                manager.addConsultation(newDay, startTime, endTime, lecturer, className, classroom);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void parseExamsToManager(String examsJson){
        if (!manager.getExams().isEmpty()){
            Log.e("ERROR PARSE DATA", "Exams NISU PRAZNI");
            return;
        }

        JSONArray arr = null;
        try{
            JSONObject o = new JSONObject(examsJson);
            arr = o.getJSONArray("schedule");
        } catch (Exception e){
            e.printStackTrace();
        }


        JSONObject o = null;
        String dateAndTime = "";
        String date = "";
        String time = "";
        String test_name = "";
        String classroom = "";
        String professor = "";
        String type = "";
        for (int i = 0; i < arr.length(); i++){
            try {
                o = arr.getJSONObject(i);

                dateAndTime = o.getString("date_and_time");
                test_name = o.getString("test_name");
                classroom = o.getString("classroom");
                professor = o.getString("professor");
                type = o.getString("type");

                date = dateAndTime.substring(0, 6);
                time = dateAndTime.substring(7) + "h";

                String startTime = time.substring(0, 2) + ":00";
                String endTime = time.substring(3, 5) + ":00";

                if (type.equals("CURRICULUM")){
                    manager.addCurriculum(test_name, date, startTime, endTime, classroom, professor);
                }
                else if (type.equals("EXAM")){
                    manager.addExam(test_name, date, startTime, endTime, classroom, professor);
                }
                else {
                    Log.e("PARSERERR", "NIJE NI CURRICULUM NI EXAM");
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }




}
