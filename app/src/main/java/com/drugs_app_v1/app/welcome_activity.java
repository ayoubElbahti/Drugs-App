package com.drugs_app_v1.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome_activity extends AppCompatActivity {
    Button test_switch = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        test_switch = (Button)findViewById(R.id.test_switch);
        test_switch.setOnClickListener(switch_to_activity);
    }
    private View.OnClickListener switch_to_activity = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(welcome_activity.this,principal_activity.class);
            startActivity(intent);
        }
    };
}