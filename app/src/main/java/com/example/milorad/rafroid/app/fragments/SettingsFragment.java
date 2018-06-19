package com.example.milorad.rafroid.app.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.milorad.rafroid.R;
import com.example.milorad.rafroid.app.adapters.ConsultAdapter;
import com.example.milorad.rafroid.data.Manager;

public class SettingsFragment extends Fragment {

    private EditText groupET;
    private Button saveButton;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment,container,false);

        groupET = view.findViewById(R.id.settings_groupET);
        saveButton = view.findViewById(R.id.saveButton);

        preferences = getContext().getSharedPreferences(Manager.PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        String grupaString = preferences.getString(Manager.PREFERENCE_USER_GROUP_KEY, "101");

        groupET.setText(grupaString);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString(Manager.PREFERENCE_USER_GROUP_KEY, groupET.getText().toString());
                editor.commit();

                PackageManager packageManager = getContext().getPackageManager();
                Intent startActivity = packageManager.getLaunchIntentForPackage(getContext().getPackageName());
                startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                int pendingIntentId = 223344;
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), pendingIntentId, startActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager manager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                manager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);

                getActivity().finish();

                System.exit(0);
            }
        });

        return view;
    }



}
