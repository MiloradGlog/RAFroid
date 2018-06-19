package com.example.milorad.rafroid.app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.data.Manager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashActivity extends AppCompatActivity {

    public static final String CLASS_TAG = "CLASSES_JSON";
    public static final String NEWS_TAG = "NEWS_JSON";
    public static final String CONSULT_TAG = "CONSULT_JSON";
    public static final String EXAMS_TAG = "EXAMS_JSON";

    public static final String API_KEY = "26diE3vAjShIjpT1ccUD";
    public static final String CLASS_URL = "https://api.raf.ninja/v1/classes";
    public static final String NEWS_URL = "https://api.raf.ninja/v1/news";
    public static final String CONSULT_URL = "https://api.raf.ninja/v1/consultations";
    public static final String EXAMS_URL = "https://api.raf.ninja/v1/exams";

    private Intent intent = null;
    OkHttpClient client = new OkHttpClient();
    private String classesJSON;
    private String newsJSON;
    private String consultationsJSON;
    private String examsJSON;
    private String grupa = "";

    private AlertDialog.Builder mBuilder;
    private AlertDialog dialog;
    private View mView;
    private EditText groupText;
    private Button dialogButton;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        preferences = getSharedPreferences(Manager.PREFERENCES_NAME,MODE_PRIVATE);
        editor = preferences.edit();

        mBuilder = new AlertDialog.Builder(SplashActivity.this);
        mView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        groupText = mView.findViewById(R.id.dialogEditText);
        dialogButton = mView.findViewById(R.id.dialogButton);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grupa = groupText.getText().toString();

                editor.putBoolean(Manager.PREFERENCE_FIRST_TIME_KEY, false);
                editor.putString(Manager.PREFERENCE_USER_GROUP_KEY, grupa);
                editor.commit();

                dialog.cancel();

                readClassesFromURL();
            }
        });

        mBuilder.setView(mView);
        dialog = mBuilder.create();

        intent = new Intent(this, MainActivity.class);

        startLoadingData();

    }

    private void startLoadingData(){

        checkFirstTimeRun();
    }

    private void checkFirstTimeRun(){

        if (preferences.getBoolean(Manager.PREFERENCE_FIRST_TIME_KEY, true)){
            dialog.show();
        }
        else {
            readClassesFromURL();
        }

    }

    private void readClassesFromURL() {
        String apikey = API_KEY;
        String url = CLASS_URL;
        Log.d("TESTURL:", "usao u testRead");


        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey", apikey)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TESTURL:", "Usao u onFailiure classes");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();
                classesJSON = res;

                readNewsFromURL();

                Log.d("TESTURL:", "Usao u onSuccess classes");
            }
        });

    }

    private void readNewsFromURL() {
        String apikey = API_KEY;
        String url = NEWS_URL;
        Log.d("TESTURL:", "usao u testRead");


        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey", apikey)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TESTURL:", "Usao u onFailiure news");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();
                newsJSON = res;

                readConsultationsFromURL();

                Log.d("TESTURL:", "Usao u onSuccess news");
            }
        });

    }

    private void readConsultationsFromURL() {
        String apikey = API_KEY;
        String url = CONSULT_URL;
        Log.d("TESTURL:", "usao u testRead");


        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey", apikey)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TESTURL:", "Usao u onFailiure consult");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();
                consultationsJSON = res;

                readExamsJSON();

                Log.d("TESTURL:", "Usao u onSuccess consult");
            }
        });

    }




    private void readExamsJSON() {
        String apikey = API_KEY;
        String url = EXAMS_URL;
        Log.d("TESTURL:", "usao u testRead");


        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey", apikey)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TESTURL:", "Usao u onFailiure exam");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();
                examsJSON = res;
                Log.d("TESTURL:", "Usao u onSuccess exam");


                sendIntent();
            }
        });

    }

    private void sendIntent(){
        intent.putExtra(CLASS_TAG, classesJSON);
        intent.putExtra(NEWS_TAG, newsJSON);
        intent.putExtra(CONSULT_TAG, consultationsJSON);
        intent.putExtra(EXAMS_TAG, examsJSON);
        finish();

        startActivity(intent);
    }
}