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
     * @param arr JSONArray, niz koji treba da sadrzi predavanja
     */
    public void parseDataToManager(JSONArray arr){
        if (!manager.getLectures().isEmpty()){
            Log.e("ERROR PARSE DATA", "LECTURES NISU PRAZNI");
            return;
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

                predmet = o.getString("predmet");
                tip = o.getString("tip");
                nastavnik = o.getString("nastavnik");
                grupe = o.getString("grupe");
                dan = o.getString("dan");
                termin = o.getString("termin");
                ucionica = o.getString("ucionica");

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
            }case ("?ET"):{
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

}
