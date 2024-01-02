package com.example.frontend;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Frontend1 extends Thread {

    Context context;

    UrlKezelo urlKezelo;

    Button bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button;
    LinearLayout nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout;
    EditText regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText;
    TextView regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text;
    public boolean futas = false;

    public Frontend1(Context context, Button bejelentkezes_button, Button regisztracios_button, Button bejelentkez_button, Button regisztracio_button,
                     LinearLayout nincs_bejelentkezve_layout, LinearLayout bejelentkezes_layout, LinearLayout regisztracio_layout,
                     EditText regisztracio_felhasznalonev_editText, EditText regisztracio_jelszo_editText, EditText regisztracio_email_editText, EditText bejelentkezes_felhasznalonev_editText, EditText bejelentkezes_jelszo_editText,
                     TextView regisztracio_felhasznalonev_text, TextView regisztracio_email_text, TextView regisztracio_jelszo_text, TextView bejelentkezes_felhasznalonev_text, TextView bejelentkezes_jelszo_text){
        this.context = context;
        urlKezelo = new UrlKezelo(this.context, this);
        this.bejelentkezes_button = bejelentkezes_button;
        this.regisztracios_button = regisztracios_button;
        this.bejelentkez_button = bejelentkez_button;
        this.regisztracio_button = regisztracio_button;

        this.nincs_bejelentkezve_layout = nincs_bejelentkezve_layout;
        this.bejelentkezes_layout = bejelentkezes_layout;
        this.regisztracio_layout = regisztracio_layout;

        this.regisztracio_felhasznalonev_editText = regisztracio_felhasznalonev_editText;
        this.regisztracio_jelszo_editText = regisztracio_jelszo_editText;
        this.regisztracio_email_editText = regisztracio_email_editText;
        this.bejelentkezes_felhasznalonev_editText = bejelentkezes_felhasznalonev_editText;
        this.bejelentkezes_jelszo_editText = bejelentkezes_jelszo_editText;

        this.regisztracio_felhasznalonev_text = regisztracio_felhasznalonev_text;
        this.regisztracio_email_text = regisztracio_email_text;
        this.regisztracio_jelszo_text = regisztracio_jelszo_text;
        this.bejelentkezes_felhasznalonev_text = bejelentkezes_felhasznalonev_text;
        this.bejelentkezes_jelszo_text = bejelentkezes_jelszo_text;

        this.bejelentkezes_layout.setVisibility(View.INVISIBLE);
        regisztracio_layout.setVisibility(View.INVISIBLE);



    }

    public void loop(){

        if (nincs_bejelentkezve_layout.getVisibility() == View.VISIBLE) {
            nincs_bejelentkezve();
        }
        if (bejelentkezes_layout.getVisibility() == View.VISIBLE) {
            bejelentkezes();
        }
        if (regisztracio_layout.getVisibility() == View.VISIBLE) {
            regisztracio();
        }


    }

    public void regisztracio_hibas_adatok(String hiba){
        int nulla = 0;
        int egy = 0;

        Log.d("Vissza", hiba);
        for (int i=0; i<hiba.length();i++){

            if (hiba.charAt(i) == '0') {nulla++;
                if (nulla + egy == 1)
                    regisztracio_felhasznalonev_text.setTextColor(Color.BLACK);
                if (nulla + egy == 2)
                    regisztracio_email_text.setTextColor(Color.BLACK);
                if (nulla + egy == 3)
                    regisztracio_jelszo_text.setTextColor(Color.BLACK);}
            if (hiba.charAt(i) == '1'){
                egy++;
                if (nulla + egy == 1)
                    regisztracio_felhasznalonev_text.setTextColor(Color.RED);
                if (nulla + egy == 2)
                    regisztracio_email_text.setTextColor(Color.RED);
                if (nulla + egy == 3)
                    regisztracio_jelszo_text.setTextColor(Color.RED);

                }
            }
        Toast.makeText(context, "Sikertelen regisztráció", Toast.LENGTH_SHORT);
        }

    public void sikeres_regisztracio() {
        nincs_bejelentkezve_layout.setVisibility(View.VISIBLE);
        regisztracio_layout.setVisibility(View.INVISIBLE);
        Toast.makeText(context, "Sikeres regisztráció", Toast.LENGTH_SHORT);
    }

    private void regisztracio() {

        regisztracio_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                urlKezelo.regisztral(regisztracio_felhasznalonev_editText.getText().toString(),
                        regisztracio_email_editText.getText().toString(),
                        regisztracio_jelszo_editText.getText().toString());
            }
        });

    }

    public void sikeres_bejelentkezes(){
        nincs_bejelentkezve_layout.setVisibility(View.VISIBLE);
        bejelentkezes_layout.setVisibility(View.INVISIBLE);
        //Toast.makeText(context, "Sikeres bejelentkezés", Toast.LENGTH_SHORT);
    }

    public void bejelentkezes_hibas_adatok(){
        bejelentkezes_felhasznalonev_text.setTextColor(Color.RED);
        bejelentkezes_jelszo_text.setTextColor(Color.RED);

        Toast.makeText(context, "Sikertelen bejelentkezés", Toast.LENGTH_SHORT);
    }


    private void bejelentkezes() {
        bejelentkez_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                urlKezelo.bejelentkezes(bejelentkezes_felhasznalonev_editText.getText().toString(),
                                        bejelentkezes_jelszo_editText.getText().toString());

            }
        });

    }

    public void nincs_bejelentkezve(){

        bejelentkezes_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nincs_bejelentkezve_layout.setVisibility(View.INVISIBLE);
                bejelentkezes_layout.setVisibility(View.VISIBLE);

            }
        });

        regisztracios_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nincs_bejelentkezve_layout.setVisibility(View.INVISIBLE);
                regisztracio_layout.setVisibility(View.VISIBLE);
        }
        });

    }


    @Override
    public void run() {
        super.run();
        while (futas) {
            loop();
        }
    }


}

