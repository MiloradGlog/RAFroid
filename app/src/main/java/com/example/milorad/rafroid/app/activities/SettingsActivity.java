package com.example.milorad.rafroid.app.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.data.Manager;

public class SettingsActivity extends AppCompatActivity {

    private EditText groupET;
    private Button saveButton;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_fragment);

        groupET = findViewById(R.id.settings_groupET);
        saveButton = findViewById(R.id.saveButton);

        preferences = this.getSharedPreferences(Manager.PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        String grupaString = preferences.getString(Manager.PREFERENCE_USER_GROUP_KEY, "101");

        groupET.setText(grupaString);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString(Manager.PREFERENCE_USER_GROUP_KEY, groupET.getText().toString());
                editor.commit();

                PackageManager packageManager = getApplicationContext().getPackageManager();
                Intent startActivity = packageManager.getLaunchIntentForPackage(getApplicationContext().getPackageName());
                startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                int pendingIntentId = 223344;
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), pendingIntentId, startActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager manager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                manager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);

                finish();

                System.exit(0);
            }
        });
    }
}
