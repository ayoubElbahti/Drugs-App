package com.drugs_app_v1.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class Details_Drugs extends AppCompatActivity {
    String age;
    String gender;
    String bp;
    String cholesterol;
    String na;
    String k_v;
    String dr_v;
    TextView Age,Na,Gender,Bp,Cholesterol,K,drugs;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_drugs);
        Age = (TextView) findViewById(R.id.textView17);
        Gender = (TextView)findViewById(R.id.textView18);
        Bp = (TextView)findViewById(R.id.textView19);
        Cholesterol = (TextView)findViewById(R.id.textView24);
        Na = (TextView)findViewById(R.id.textView20);
        K = (TextView)findViewById(R.id.textView21);
        drugs = (TextView)findViewById(R.id.textView25);
        btn = (Button)findViewById(R.id.button3);

        age = getIntent().getStringExtra("Age");
        gender = getIntent().getStringExtra("Age");
        gender = getIntent().getStringExtra("Sex");
        bp = getIntent().getStringExtra("Bp");
        cholesterol = getIntent().getStringExtra("Ch");
        na = getIntent().getStringExtra("Na");
        k_v = getIntent().getStringExtra("K");
        dr_v = getIntent().getStringExtra("drug");

        Age.setText(age);
        Gender.setText(gender);
        Bp.setText(bp);
        Cholesterol.setText(cholesterol);
        Na.setText(na);
        K.setText(k_v);
        drugs.setText(dr_v);
        btn.setOnClickListener(envoyerListener);

    }
    private View.OnClickListener envoyerListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(Details_Drugs.this,principal_activity.class);
            startActivity(intent);
        }
    };
}