package com.example.assignment2.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignment2.Classes.UserInfo;
import com.example.assignment2.R;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static String DATA = "DATA";
    String gender;
    EditText edt_name;
    EditText edt_email;
    RadioButton male,female;
    EditText edt_phone;
    Spinner year_Spinner, dayspinner, monthspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        yearsSpinner();
    }


    private void yearsSpinner() {


        ArrayList<String> years = new ArrayList<>();
        years.add("1995");
        years.add("1996");
        years.add("1997");
        years.add("1998");
        years.add("1999");
        years.add("2000");
        years.add("2001");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, years);


        year_Spinner.setAdapter(adapter);


    }

    private void setupViews() {
        edt_name = findViewById(R.id.txt_name);
        edt_email = findViewById(R.id.txt_Email);
        edt_phone = findViewById(R.id.txtphone);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        year_Spinner = findViewById(R.id.years);
        dayspinner = findViewById(R.id.daysSpn);
        monthspinner = findViewById(R.id.monthsSpn);
    }

    public void btn_SaveData(View view) {

        String name = edt_name.getText().toString();
        String email = edt_email.getText().toString();
        String phone = edt_phone.getText().toString();
        String day = dayspinner.getSelectedItem().toString();
        String month = monthspinner.getSelectedItem().toString();
        String year = year_Spinner.getSelectedItem().toString();

        String dob = day + " " + month + " " + year ;

        if(male.isChecked())
            gender = "Male";
        else if (female.isChecked())
            gender = "Female";

        UserInfo userInfo = new UserInfo(name, email, gender, phone, dob);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String userString = gson.toJson(userInfo);
        editor.putString(DATA, userString);
        editor.commit();

        Toast.makeText(this, "Data Saved: " + userString, Toast.LENGTH_SHORT).show();




    }

    public void btn_LoadData(View view) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String str = prefs.getString(DATA, "");
        if(!str.equals("")){

            UserInfo userInfos = gson.fromJson(str, UserInfo.class);
            edt_name.setText(userInfos.getName());
            edt_phone.setText(userInfos.getPhone());
            edt_email.setText(userInfos.getEmail());
            String [] strdob = userInfos.getDob().split(" ");

            dayspinner.setSelection(getIndex(dayspinner, strdob[0]));
            monthspinner.setSelection(getIndex(monthspinner,strdob[1]));
            year_Spinner.setSelection(getIndex(year_Spinner,strdob[2]));

            if(userInfos.getGender().equals("Female"))
                female.setChecked(true);
            else if(userInfos.getGender().equals("Male"))
                male.setChecked(true);

        }

        else
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();

    }

    private int getIndex(Spinner spinner, String s) {

        for(int i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(s)){
                return i;
            }
        }
        return 0;

    }

    public void btn_nextPage(View view) {
        Intent intent = new Intent(MainActivity.this, CVinformation.class);

        String user = edt_name.getText().toString();

        intent.putExtra("USER", user);

        startActivity(intent);

    }
}