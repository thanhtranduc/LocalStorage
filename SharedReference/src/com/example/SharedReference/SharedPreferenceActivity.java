package com.example.SharedReference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SharedPreferenceActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText editText = (EditText)findViewById(R.id.main_et);
        Button button = (Button) findViewById(R.id.main_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
                String str = editText.getText().toString();
                prefsEditor.putString("My Object", str);
                Log.d("Oncreate","save");
                prefsEditor.commit();
//                Toast.makeText(this, "Editext stored in sharedReferences", Toast.LENGTH_LONG);
            }
        });

    }
    public void onDestroy() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String imgSett = prefs.getString("My Object", "");

        super.onDestroy();
        Log.d("OnDestroy","destroy"+ imgSett);
        android.os.Process.killProcess(android.os.Process.myPid());

    }
}
