package com.example.assignment2.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment2.R;

public class CVinformation extends AppCompatActivity {

    private TextView user_name;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private static final String USER_NAME = "USER_NAME";
    public final static  String EDUCATION = "EDUCATION";
    public final static  String EXPERIENCE = "EXPERIENCE";
    public final static  String TRAINING = "TRAINING";
    public final static  String TECBG = "TECBG";
    public final static  String SKILLS = "SKILLS";

    private EditText txtedt_edu;
    private EditText txtedt_exp;
    private EditText txtedt_train;
    private EditText txtedt_tecbg;
    private EditText txtedt_skills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_vinformation);

        Intent intent = getIntent();
        user_name = findViewById(R.id.txtusername);
        String name = intent.getStringExtra("USER");

        if(!name.equals(""))
            user_name.setText(name + " CV");

        setupViews();
        setupSharedPrefs();
        LoadPrefs();
        
    }

    private void setupViews() {

        txtedt_edu = findViewById(R.id.edt_education);
        txtedt_exp = findViewById(R.id.edt_experience);
        txtedt_train = findViewById(R.id.edt_traning);
        txtedt_tecbg = findViewById(R.id.edt_tb);
        txtedt_skills = findViewById(R.id.edt_skills);

    }

    private void setupSharedPrefs() {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

    }

    private void LoadPrefs() {

        String edu = prefs.getString(EDUCATION, "");
        String exp = prefs.getString(EDUCATION, "");
        String train = prefs.getString(TRAINING, "");
        String tecbg = prefs.getString(TECBG, "");
        String skills = prefs.getString(SKILLS, "");



        txtedt_edu.setText(edu);
        txtedt_exp.setText(exp);
        txtedt_train.setText(train);
        txtedt_tecbg.setText(tecbg);
        txtedt_skills.setText(skills);


    }

    public void btn_SaveData(View view) {

        String edu = txtedt_edu.getText().toString();
        String exp = txtedt_exp.getText().toString();
        String train = txtedt_train.getText().toString();
        String tecbg = txtedt_tecbg.getText().toString();
        String skills = txtedt_skills.getText().toString();


        editor.putString(EDUCATION, edu);
        editor.putString(EXPERIENCE, exp);
        editor.putString(TRAINING, train);
        editor.putString(TECBG, tecbg);
        editor.putString(SKILLS, skills);


        editor.commit();

    }
}