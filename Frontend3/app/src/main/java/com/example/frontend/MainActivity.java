package com.example.frontend;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Frontend1 frontend;
    Button bejelentkezes_button, regisztracios_button, jelentkez_button, regisztracio_button;
    LinearLayout nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout;
    EditText bejelentkez_felhasznalonev_editText, regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText;

    TextView regisztracio_felhasznalonev_text, regisztracio_jelszo_text, regisztracio_email_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bejelentkezes_button = findViewById(R.id.bejelentkezes_button);
        regisztracios_button = findViewById(R.id.regisztracios_button);
        jelentkez_button = findViewById(R.id.bejelentkez_button);
        regisztracio_button = findViewById(R.id.regisztracio_button);

        nincs_bejelentkezve_layout = findViewById(R.id.nincs_bejelentkezve_layout);
        bejelentkezes_layout = findViewById(R.id.bejelentkezes_layout);
        regisztracio_layout = findViewById(R.id.regisztracio_layout);

        bejelentkez_felhasznalonev_editText = findViewById(R.id.bejelentkez_felhasznalonev_editText);
        regisztracio_felhasznalonev_editText = findViewById(R.id.regisztracio_felhasznalonev_editText);
        regisztracio_jelszo_editText = findViewById(R.id.regisztracio_jelszo_editText);
        regisztracio_email_editText = findViewById(R.id.regisztracio_email_editText);

        regisztracio_felhasznalonev_text = findViewById(R.id.regisztracio_felhasznalonev_text);
        regisztracio_email_text = findViewById(R.id.regisztracio_email_text);
        regisztracio_jelszo_text = findViewById(R.id.regisztracio_jelszo_text);


        frontend = new Frontend1(this, bejelentkezes_button, regisztracios_button, jelentkez_button, regisztracio_button,
                nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout,
                bejelentkez_felhasznalonev_editText, regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText,
                regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text);

        frontend.futas = true;
        frontend.start();

        /*Window window = getWindow();

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        frontend = new Frontend(this, width, height);
        setContentView(frontend);*/

        //frontend.start();

    }



}