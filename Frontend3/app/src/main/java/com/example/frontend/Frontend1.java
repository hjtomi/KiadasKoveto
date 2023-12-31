package com.example.frontend;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Frontend1 extends Thread {

    Context context;

    UrlKezelo urlKezelo;

    Button bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button;
    LinearLayout nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout;
    EditText bejelentkez_felhasznalonev_editText, regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText;

    TextView regisztracio_felhasznalonev_text, regisztracio_jelszo_text, regisztracio_email_text;
    public boolean futas = false;

    public Frontend1(Context context, Button button_bejelentkezes_button, Button button_regisztracios_button, Button button_bejelentkez_button, Button button_regisztracio_button,
                     LinearLayout layout_nincs_bejelentkezve_layout, LinearLayout layout_bejelentkezes_layout, LinearLayout regisztracio_layout,
                     EditText bejelentkez_felhasznalonev_editText, EditText regisztracio_felhasznalonev_editText, EditText regisztracio_jelszo_editText, EditText regisztracio_email_editText,
                     TextView regisztracio_felhasznalonev_text, TextView regisztracio_jelszo_text, TextView regisztracio_email_text){
        this.context = context;
        urlKezelo = new UrlKezelo(this.context);
        this.bejelentkezes_button = button_bejelentkezes_button;
        this.regisztracios_button = button_regisztracios_button;
        this.bejelentkez_button = button_bejelentkez_button;
        this.regisztracio_button = button_regisztracio_button;

        this.nincs_bejelentkezve_layout = layout_nincs_bejelentkezve_layout;
        this.bejelentkezes_layout = layout_bejelentkezes_layout;
        this.regisztracio_layout = regisztracio_layout;

        this.bejelentkez_felhasznalonev_editText = bejelentkez_felhasznalonev_editText;
        this.regisztracio_felhasznalonev_editText = regisztracio_felhasznalonev_editText;
        this.regisztracio_jelszo_editText = regisztracio_jelszo_editText;
        this.regisztracio_email_editText = regisztracio_email_editText;

        this.regisztracio_felhasznalonev_text = regisztracio_felhasznalonev_text;
        this.regisztracio_email_text = regisztracio_email_text;
        this.regisztracio_jelszo_text = regisztracio_jelszo_text;

        layout_bejelentkezes_layout.setVisibility(View.INVISIBLE);
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

    private void regisztracio() {

        regisztracio_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nincs_bejelentkezve_layout.setVisibility(View.VISIBLE);
                regisztracio_layout.setVisibility(View.INVISIBLE);

                urlKezelo.regisztral(regisztracio_felhasznalonev_editText.getText().toString(),
                        regisztracio_email_editText.getText().toString(),
                        regisztracio_jelszo_editText.getText().toString());
            }
        });

    }

    private void bejelentkezes() {
        /*button_bejelentkez_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                layout_nincs_bejelentkezve_layout.setVisibility(View.INVISIBLE);
                regisztracio_layout.setVisibility(View.VISIBLE);
            }
        });*/

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

