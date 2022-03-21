package com.drugs_app_v1.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;
import com.cazaea.sweetalert.SweetAlertDialog;

public class principal_activity extends AppCompatActivity{
    private final String defaut = " ";
    public String cc = "Here ...";
    public String body_value = " ";
    public double doubleValue = 0;
    Button Result = null;
    Button raz = null;
    EditText Age = null;
    Spinner Genre;
    Spinner Cholesterol;
    Spinner BP;
    EditText Na = null;
    SweetAlertDialog sd;
    SweetAlertDialog sd_internet;
    EditText K = null;
    public String Age_value,Na_value,K_value;
    public  String Cholesterol_valeur="";
    public  String Bp_valeur="";
    public String Genre_selected = "";
        String[] genre_valeures = {"M","F"};
    Spinner customSpinner;
    ArrayList<CustomItem> customList;
    ArrayList<CustomItem> valeurs_qualifier;
    public Loading waiting_dialog = new Loading(principal_activity.this);

    int width = 150; //set according to your use
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Result = (Button)findViewById(R.id.button);
        raz = (Button)findViewById(R.id.button2);
        Age = (EditText)findViewById(R.id.Age_v);
        Cholesterol = (Spinner) findViewById(R.id.cholesterol);
        BP = (Spinner) findViewById(R.id.BP);
        Na = (EditText)findViewById(R.id.Na_v);
        K = (EditText)findViewById(R.id.editTextNumberDecimal2);
        //Attribute listners and assign actions
        Result.setOnClickListener(envoyerListener);
        raz.setOnClickListener(razListener);
        Age.addTextChangedListener(textWatcher);
        Na.addTextChangedListener(textWatcher);
        K.addTextChangedListener(textWatcher);
        //   test spinner

            customSpinner = findViewById(R.id.customIconSpinner);
            customList = getCustomList();
            valeurs_qualifier = getValeursList();


            //showProgress();




            OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        LinearLayout linearLayout = findViewById(R.id.customSpinnerItemLayout);
                        width = linearLayout.getWidth();
                    } catch (Exception e) {
                    }
                    try {
                        customSpinner.setDropDownWidth(width);
                        CustomItem item = (CustomItem) adapterView.getSelectedItem();
                        Genre_selected = item.getSpinnerItemName().toString();
                    }catch (Exception e){
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            };
            OnItemSelectedListener onItemSelectedListener1 = new OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        LinearLayout linearLayout = findViewById(R.id.mesure);
                        width = linearLayout.getWidth();
                    } catch (Exception e) {
                    }
                    try {
                        Cholesterol.setDropDownWidth(width);
                        CustomItem item = (CustomItem) adapterView.getSelectedItem();
                        Cholesterol_valeur = item.getSpinnerItemName().toString();
                    }catch (Exception e){
                    }

                    //Toast.makeText(this, item.getSpinnerItemName(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            };
            OnItemSelectedListener onItemSelectedListener2 = new OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        LinearLayout linearLayout = findViewById(R.id.mesure);
                        width = linearLayout.getWidth();
                    } catch (Exception e) {
                    }
                    try {
                        BP.setDropDownWidth(width);
                        CustomItem item = (CustomItem) adapterView.getSelectedItem();
                        Bp_valeur = item.getSpinnerItemName().toString();
                    }catch (Exception e){
                    }

                    //Toast.makeText(this, item.getSpinnerItemName(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            };

            CustomAdapter adapter = new CustomAdapter(this, customList);
            if (customSpinner != null) {
                customSpinner.setAdapter(adapter);
                customSpinner.setOnItemSelectedListener(onItemSelectedListener);
            }

            CustomAdapter adapter_valeurs = new CustomAdapter(this, valeurs_qualifier);
            if (Cholesterol != null) {
                Cholesterol.setAdapter(adapter_valeurs);
                Cholesterol.setOnItemSelectedListener(onItemSelectedListener1);
            }
            if (BP != null) {
                BP.setAdapter(adapter_valeurs);
                BP.setOnItemSelectedListener(onItemSelectedListener2);
            }
        }

    private ArrayList<CustomItem> getCustomList() {
        customList = new ArrayList<>();
        customList.add(new CustomItem("M", R.drawable.ic_baseline_person_24));
        customList.add(new CustomItem("F", R.drawable.women));
        return customList;
    }

    private ArrayList<CustomItem> getValeursList() {
        valeurs_qualifier = new ArrayList<>();
        valeurs_qualifier.add(new CustomItem("HIGH", R.drawable.hightbzf));
        valeurs_qualifier.add(new CustomItem("NORMAL", R.drawable.normal_24));
        valeurs_qualifier.add(new CustomItem("LOW", R.drawable.lowh));
        return valeurs_qualifier;
    }



    private View.OnClickListener razListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Age.getText().clear();
            Na.getText().clear();
            K.getText().clear();


        }
    };
    private TextWatcher textWatcher = new TextWatcher(){
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count){

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after){
        }

        @Override
        public void afterTextChanged(Editable s){
            doubleValue = 0;
            try {
                doubleValue = Double.parseDouble(s.toString().replace(',', '.'));
            }catch (NumberFormatException e) {
                doubleValue = 0;
            }
            if (s != null) {
                try {

                    if (K.getText() != null){
                        if (Double.valueOf(K.getText().toString()) >= 1.0){
                            K.setError("please entre a valid k");
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                try {
                    if (Na.getText() != null) {
                        if (Double.valueOf(Na.getText().toString()) >= 1.0) {
                            Na.setError("please entre a valid Na");
                        }
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                try {
                            if (Na.getText().toString().contains(",")){
                                Na.setText(String.valueOf(doubleValue));
                            }}
                catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                try {
                            if(K.getText().toString().contains(",")){
                                K.setText(String.valueOf(doubleValue));
                            }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            //Do something with doubleValue
        }
    };

    private View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Age_value = Age.getText().toString();
            Na_value = Na.getText().toString();
            K_value = K.getText().toString();
            if (Age_value.length() ==0 || Na_value.length() == 0 || K_value.length() == 0){
                sd = new SweetAlertDialog(principal_activity.this,SweetAlertDialog.ERROR_TYPE);
                sd.getProgressHelper().setBarColor(Color.parseColor("#a9fef3"));
                sd.setTitleText("ERROR !!");
                sd.setContentText("Vous avez oubliez un ou plusieur champs !");
                sd.setConfirmText("OK");
                sd.setCancelable(false);
                sd.show();
            }
            else{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestApi(Age_value,Genre_selected,Bp_valeur,Cholesterol_valeur,Na_value,K_value);
                    }
                }).start();

            }
            }

    };

    public void requestApi(String age,String gender,String bp,String cholesterol,String na,String k){
        try {
            RequestDrugType obj = new RequestDrugType();
            principal_activity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    sd = new SweetAlertDialog(principal_activity.this,SweetAlertDialog.PROGRESS_TYPE);
                    sd.getProgressHelper().setBarColor(Color.parseColor("#a9fef3"));
                    sd.setTitleText("Waiting ...");
                    sd.setCancelable(false);
                    sd.show();
                }
            });
            body_value = obj.sendPOST(age,gender,bp,cholesterol,na,k);
            JSONObject obj_get = new JSONObject(body_value);
            cc= obj_get.getString("drug");
            //sd.dismiss();

            principal_activity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    sd = new SweetAlertDialog(principal_activity.this,SweetAlertDialog.SUCCESS_TYPE);
                    sd.getProgressHelper().setBarColor(Color.parseColor("#a9fef3"));
                    sd.setTitleText("Done !!");
                    sd.setContentText("Your Drug is : "+cc+
                            "\n redirecting to Your Information page");
                    sd.setConfirmText("OK");
                    sd.setCancelable(false);
                    sd.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                            redirctingToPageinfo();
                        }
                    });
                    sd.show();
                }

            });
            try {
                Thread.sleep(5000);
            }catch (Exception e){

            }

        } catch (Exception e) {
            principal_activity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    sd.dismiss();
                    sd_internet = new SweetAlertDialog(principal_activity.this,SweetAlertDialog.ERROR_TYPE);
                    sd_internet.getProgressHelper().setBarColor(Color.parseColor("#a9fef3"));
                    sd_internet.setTitleText("ERROR !!");
                    sd_internet.setContentText("Vous avez un probleme d'internet !");
                    sd_internet.setConfirmText("OK");
                    sd_internet.setCancelable(false);
                    sd_internet.show();
                }
            });
            //sd_internet.show();

        }
    }
    public void redirctingToPageinfo(){
        principal_activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(principal_activity.this,Details_Drugs.class);
                intent.putExtra("Age",Age_value);
                intent.putExtra("Sex",Genre_selected);
                intent.putExtra("Bp",Bp_valeur);
                intent.putExtra("Ch",Cholesterol_valeur);
                intent.putExtra("Na",Na_value);
                intent.putExtra("K",K_value);
                intent.putExtra("drug",cc);
                startActivity(intent);
            }
        });
    }


}
