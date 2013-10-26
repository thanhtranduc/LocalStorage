package com.example.StartActivitiResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: thanhtd
 * Date: 10/25/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScreenTwoActivity extends Activity {
    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.screen_two);
        TextView txtName = (TextView) findViewById(R.id.txtname);
        TextView txtEmail = (TextView) findViewById(R.id.txtemail);
        Button btnClose = (Button) findViewById(R.id.btnclose);

        Intent i = getIntent();
        // Receiving the Data
        String name = i.getStringExtra("name");
        String email = i.getStringExtra("email");
        Log.e("Second Screen", name + "." + email);

        // Displaying Received data
        txtName.setText(name);
        txtEmail.setText(email);

        // Binding Click event to Button
        btnClose.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
            }
        });

    }
}
