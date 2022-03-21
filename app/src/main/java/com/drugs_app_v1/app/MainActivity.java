package com.drugs_app_v1.app;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private final String defaut = " ";
    private final String megaString = " Great job!";
    public String cc = "hello";
    public String body_value = " ";
    public String resultDrug = "mo";

    Button Result = null;
    Button raz = null;
    EditText Age = null;
    RadioGroup Genre = null;
    RadioGroup Cholesterol = null;
    RadioGroup BP = null;
    EditText Na = null;
    EditText K = null;
    TextView result_drugs = null;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting items
        Result = (Button)findViewById(R.id.Result);
        raz = (Button)findViewById(R.id.raz);
        Age = (EditText)findViewById(R.id.Age_text);
        Genre = (RadioGroup)findViewById(R.id.customIconSpinner);
        Cholesterol = (RadioGroup)findViewById(R.id.customIconSpinner);
        BP = (RadioGroup)findViewById(R.id.BP);
        Na = (EditText)findViewById(R.id.Na_v);
        K = (EditText)findViewById(R.id.K);
        result_drugs = (TextView)findViewById(R.id.result_drugs);

        //Attribute listners and assign actions
        Result.setOnClickListener(envoyerListener);
        raz.setOnClickListener(razListener);
        Age.addTextChangedListener(textWatcher);
        Na.addTextChangedListener(textWatcher);
        K.addTextChangedListener(textWatcher);



    }
    private OnClickListener razListener = new OnClickListener(){
        @Override
        public void onClick(View v){
            Age.getText().clear();
            Na.getText().clear();
            K.getText().clear();
            Genre.clearCheck();
            Cholesterol.clearCheck();
            BP.clearCheck();
            result_drugs.setText(defaut);
        }
    };
    private TextWatcher textWatcher = new TextWatcher(){
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count){
            result_drugs.setText(defaut);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after){
        }

        @Override
        public void afterTextChanged(Editable s){
        }
    };

    private OnClickListener envoyerListener = new OnClickListener() {
        @Override
        public void onClick(View v){
            String Age_value = Age.getText().toString();
            String Na_value = Na.getText().toString();
            String K_value = K.getText().toString();
            int Genre_id = Genre.getCheckedRadioButtonId();
            RadioButton Genre_button = (RadioButton) findViewById(Genre_id);
            String Genre_value = Genre_button.getText().toString();
            int Cholesterol_id = Cholesterol.getCheckedRadioButtonId();
            RadioButton Cholesterol_button = (RadioButton) findViewById(Cholesterol_id);
            String Cholesterol_value = Cholesterol_button.getText().toString();
            int bp_id = BP.getCheckedRadioButtonId();
            RadioButton bp_button = (RadioButton) findViewById(bp_id);
            String bp_value = bp_button.getText().toString();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    requestApi(Age_value,Genre_value,bp_value,Cholesterol_value,Na_value,K_value);
                }
            }).start();

        }
    };

    public void requestApi(String age,String gender,String bp,String cholesterol,String na,String k){
        RequestDrugType obj = new RequestDrugType();
        try {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                result_drugs.setText("waiting..");

                        }
                    });
            body_value = obj.sendPOST(age,gender,bp,cholesterol,na,k);
            JSONObject obj_get = new JSONObject(body_value);
            cc= obj_get.getString("drug");
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    result_drugs.setText(cc);
                }

            });
        } catch (JSONException | IOException e) {
            result_drugs.setText("erreur");
        }
    }
}

