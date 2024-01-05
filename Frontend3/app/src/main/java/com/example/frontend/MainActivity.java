package com.example.frontend;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private UrlKezelo urlKezelo;
    Button bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button, fooldal_felvetel_button, fooldal_kategoria_button, fooldal_statisztika_button;
    LinearLayout nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout, fooldal_layout;
    EditText regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText, regisztracio_egyenleg_editText;
    TextView regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text, regisztracio_egyenleg_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bejelentkezes_button = findViewById(R.id.bejelentkezes_button);
        regisztracios_button = findViewById(R.id.regisztracios_button);
        bejelentkez_button = findViewById(R.id.bejelentkez_button);
        regisztracio_button = findViewById(R.id.regisztracio_button);
        fooldal_felvetel_button = findViewById(R.id.fooldal_felvetel_button);
        fooldal_kategoria_button = findViewById(R.id.fooldal_kategoria_button);
        fooldal_statisztika_button = findViewById(R.id.fooldal_statisztika_button);

        nincs_bejelentkezve_layout = findViewById(R.id.nincs_bejelentkezve_layout);
        bejelentkezes_layout = findViewById(R.id.bejelentkezes_layout);
        regisztracio_layout = findViewById(R.id.regisztracio_layout);
        fooldal_layout = findViewById(R.id.fooldal_layout);

        regisztracio_felhasznalonev_editText = findViewById(R.id.regisztracio_felhasznalonev_editText);
        regisztracio_jelszo_editText = findViewById(R.id.regisztracio_jelszo_editText);
        regisztracio_email_editText = findViewById(R.id.regisztracio_email_editText);
        regisztracio_egyenleg_editText = findViewById(R.id.regisztracio_egyenleg_editText);
        bejelentkezes_felhasznalonev_editText = findViewById(R.id.bejelentkez_felhasznalonev_editText);
        bejelentkezes_jelszo_editText = findViewById(R.id.bejelentkez_jelszo_editText);

        regisztracio_felhasznalonev_text = findViewById(R.id.regisztracio_felhasznalonev_text);
        regisztracio_email_text = findViewById(R.id.regisztracio_email_text);
        regisztracio_jelszo_text = findViewById(R.id.regisztracio_jelszo_text);
        regisztracio_egyenleg_text = findViewById(R.id.regisztracio_egyenleg_text);
        bejelentkezes_felhasznalonev_text = findViewById(R.id.bejelentkezes_felhasznalonev_text);
        bejelentkezes_jelszo_text = findViewById(R.id.bejelentkezes_jelszo_text);

        urlKezelo = new UrlKezelo(this, bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button, fooldal_felvetel_button, fooldal_kategoria_button, fooldal_statisztika_button,
                nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout, fooldal_layout,
                regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText, regisztracio_egyenleg_editText,
                regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text, regisztracio_egyenleg_text);

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