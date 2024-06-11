package com.example.frontend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Frontend1 extends Thread {


    public Context context;



    UrlKezelo urlKezelo;

    int xyz = 1;
    Button bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button, fooldal_felvetel_button, fooldal_kategoria_button, fooldal_statisztika_button, nyugtas_kiadas_button, kep_button, kategoria_kuldes_button, home_;
    Button kategoria_1, kategoria_2, kategoria_3, kategoria_4, kategoria_5, kategoria_6, kategoria_7, kategoria_8;
    Button manualis_kiadas_button, manualis_tovabb, manualis_mentes, manualis_kuldes, kategoria_hozzaadasa_button, bevetel_button, bevetel_hozzaadasa_button;
    Button fooldal_fiok_button, fiok_hozzaadasa_button;
    LinearLayout nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout, fooldal_layout, felvetel_valaszto_layout, nyugtas_kiadas_layout, kategoria_layout, manualis_bolt_layout, manualis_layout, var_layout, kategoria_hozzaadasa_layout, bevetel_hozzaadasa_layout;
    LinearLayout fiok_hozzaadasa_layout;
    EditText regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText, regisztracio_egyenleg_editText, nyugtas_bolt_editText, kategoria_egyeb_editText, manualis_bolt_editText;
    EditText manualis_nev_editText, manualis_ar_editText, manualis_kategoria_editText, manualis_fiok_editText, nyugtas_fiok_editText, kategoria_hozzaadasa_editText, ny_termekar_editText, ny_termeknev_editText, bevetel_fiok_editText, bevetel_ar_editText, fiok_hozzaadasa_editText, fiok_osszeg_editText;
    TextView regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text, regisztracio_egyenleg_text, nyugtas_bolt_text, kategoria_text, manualis_bolt_text, bevetel_fiok_text;
    TextView manualis_nev_text, manualis_ar_text, manualis_datum_text, manualis_kategoria_text, manualis_fiok_text, var_text, nyugtas_fiok_text, kategoria_hozzaadasa_text;
    DatePicker manualis_datum_pick;
    ImageView kep;

    public int tart = 0;
    public int hossz = 0;
    public HashMap<String, String> kategoriak_vissza = new HashMap<>();
    public JSONArray adatok;

    public Bitmap kep_;

    public boolean var = false;

    public boolean futas = false;
    public JSONObject manualis_kuldeni;
    public JSONObject nyugtas_kuldeni;


    public Frontend1(UrlKezelo urlKezelo, Context context, Button bejelentkezes_button, Button regisztracios_button, Button bejelentkez_button, Button regisztracio_button, Button fooldal_felvetel_button, Button fooldal_kategoria_button, Button fooldal_statisztika_button, Button nyugtas_kiadas_button, Button kep_button, Button kategoria_kuldes_button, Button home_,
                     Button kategoria_1, Button kategoria_2, Button kategoria_3, Button kategoria_4, Button kategoria_5, Button kategoria_6, Button kategoria_7, Button kategoria_8,
                     Button manualis_kiadas_button, Button manualis_kuldes, Button manualis_mentes, Button manualis_tovabb, Button kategoria_hozzaadasa_button, Button bevetel_button,
                     Button fooldal_fiok_button, Button bevetel_hozzaadasa_button, Button fiok_hozzaadasa_button,
                     LinearLayout nincs_bejelentkezve_layout, LinearLayout bejelentkezes_layout, LinearLayout regisztracio_layout, LinearLayout fooldal_layout, LinearLayout felvetel_valaszto_layout, LinearLayout nyugtas_kiadas_layout, LinearLayout kategoria_layout, LinearLayout manualis_layout, LinearLayout manualis_bolt_layout, LinearLayout var_layout, LinearLayout kategoria_hozzaadasa_layout, LinearLayout bevetel_hozzaadasa_layout,
                     LinearLayout fiok_hozzaadasa_layout,
                     EditText regisztracio_felhasznalonev_editText, EditText regisztracio_jelszo_editText, EditText regisztracio_email_editText, EditText bejelentkezes_felhasznalonev_editText, EditText bejelentkezes_jelszo_editText, EditText regisztracio_egyenleg_editText, EditText nyugtas_bolt_editText, EditText kategoria_egyeb_editText,
                     EditText manualis_bolt_editText, EditText manualis_ar_editText, EditText manualis_nev_editText, EditText manualis_kategoria_editText, EditText manualis_fiok_editText, EditText nyugtas_fiok_editText, EditText kategoria_hozzaadasa_editText, EditText ny_termekar_editText, EditText ny_termeknev_editText,
                     EditText bevetel_fiok_editText, EditText bevetel_ar_editText, EditText fiok_hozzaadasa_editText, EditText fiok_osszeg_editText,
                     TextView regisztracio_felhasznalonev_text, TextView regisztracio_email_text, TextView regisztracio_jelszo_text, TextView bejelentkezes_felhasznalonev_text, TextView bejelentkezes_jelszo_text, TextView regisztracio_egyenleg_text, TextView nyugtas_bolt_text, TextView kategoria_text,
                     TextView manualis_ar_text, TextView manualis_bolt_text, TextView manualis_datum_text, TextView manualis_nev_text, TextView manualis_kategoria_text, TextView manualis_fiok_text, TextView var_text, TextView nyugtas_fiok_text, TextView kategoria_hozzaadasa_text,
                     TextView bevetel_fiok_text,
                     ImageView kep, DatePicker manualis_datum_pick){


        this.context = context;
        this.urlKezelo = urlKezelo;
        this.bejelentkezes_button = bejelentkezes_button;
        this.regisztracios_button = regisztracios_button;
        this.bejelentkez_button = bejelentkez_button;
        this.regisztracio_button = regisztracio_button;
        this.fooldal_felvetel_button = fooldal_felvetel_button;
        this.fooldal_kategoria_button = fooldal_kategoria_button;
        this.fooldal_statisztika_button = fooldal_statisztika_button;
        this.nyugtas_kiadas_button = nyugtas_kiadas_button;
        this.kep_button = kep_button;
        this.kategoria_kuldes_button = kategoria_kuldes_button;
        this.home_ = home_;
        this.bevetel_hozzaadasa_button = bevetel_hozzaadasa_button;

        this.kategoria_1 = kategoria_1;
        this.kategoria_2 = kategoria_2;
        this.kategoria_3 = kategoria_3;
        this.kategoria_4 = kategoria_4;
        this.kategoria_5 = kategoria_5;
        this.kategoria_6 = kategoria_6;
        this.kategoria_7 = kategoria_7;
        this.kategoria_8 = kategoria_8;

        this.manualis_kiadas_button = manualis_kiadas_button;
        this.manualis_tovabb = manualis_tovabb;
        this.manualis_mentes = manualis_mentes;
        this.manualis_kuldes = manualis_kuldes;
        this.bevetel_button = bevetel_button;
        this.fooldal_fiok_button = fooldal_fiok_button;
        this.kategoria_hozzaadasa_button = kategoria_hozzaadasa_button;
        this.fiok_hozzaadasa_button = fiok_hozzaadasa_button;

        this.nincs_bejelentkezve_layout = nincs_bejelentkezve_layout;
        this.bejelentkezes_layout = bejelentkezes_layout;
        this.regisztracio_layout = regisztracio_layout;
        this.fooldal_layout = fooldal_layout;
        this.felvetel_valaszto_layout = felvetel_valaszto_layout;
        this.nyugtas_kiadas_layout = nyugtas_kiadas_layout;
        this.kategoria_layout = kategoria_layout;
        this.manualis_bolt_layout = manualis_bolt_layout;
        this.manualis_layout = manualis_layout;
        this.var_layout = var_layout;
        this.kategoria_hozzaadasa_layout = kategoria_hozzaadasa_layout;
        this.bevetel_hozzaadasa_layout = bevetel_hozzaadasa_layout;
        this.fiok_hozzaadasa_layout = fiok_hozzaadasa_layout;

        this.regisztracio_felhasznalonev_editText = regisztracio_felhasznalonev_editText;
        this.regisztracio_jelszo_editText = regisztracio_jelszo_editText;
        this.regisztracio_email_editText = regisztracio_email_editText;
        this.regisztracio_egyenleg_editText = regisztracio_egyenleg_editText;
        this.bejelentkezes_felhasznalonev_editText = bejelentkezes_felhasznalonev_editText;
        this.bejelentkezes_jelszo_editText = bejelentkezes_jelszo_editText;
        this.nyugtas_bolt_editText = nyugtas_bolt_editText;
        this.kategoria_egyeb_editText = kategoria_egyeb_editText;
        this.manualis_bolt_editText = manualis_bolt_editText;
        this.manualis_ar_editText = manualis_ar_editText;
        this.manualis_nev_editText = manualis_nev_editText;
        this.manualis_kategoria_editText = manualis_kategoria_editText;
        this.manualis_fiok_editText = manualis_fiok_editText;
        this.nyugtas_fiok_editText = nyugtas_fiok_editText;
        this.kategoria_hozzaadasa_editText = kategoria_hozzaadasa_editText;
        this.ny_termekar_editText = ny_termekar_editText;
        this.ny_termeknev_editText = ny_termeknev_editText;
        this.bevetel_fiok_editText = bevetel_fiok_editText;
        this.bevetel_ar_editText = bevetel_ar_editText;
        this.fiok_hozzaadasa_editText = fiok_hozzaadasa_editText;
        this.fiok_osszeg_editText = fiok_osszeg_editText;

        this.regisztracio_felhasznalonev_text = regisztracio_felhasznalonev_text;
        this.regisztracio_email_text = regisztracio_email_text;
        this.regisztracio_jelszo_text = regisztracio_jelszo_text;
        this.regisztracio_egyenleg_text = regisztracio_egyenleg_text;
        this.bejelentkezes_felhasznalonev_text = bejelentkezes_felhasznalonev_text;
        this.bejelentkezes_jelszo_text = bejelentkezes_jelszo_text;
        this.nyugtas_bolt_text = nyugtas_bolt_text;
        this.kategoria_text = kategoria_text;
        this.manualis_nev_text = manualis_nev_text;
        this.manualis_ar_text = manualis_ar_text;
        this.manualis_bolt_text = manualis_bolt_text;
        this.manualis_datum_text = manualis_datum_text;
        this.manualis_kategoria_text = manualis_kategoria_text;
        this.manualis_fiok_text = manualis_fiok_text;
        this.var_text = var_text;
        this.nyugtas_fiok_text = nyugtas_fiok_text;
        this.kategoria_hozzaadasa_text = kategoria_hozzaadasa_text;
        this.bevetel_fiok_text = bevetel_fiok_text;

        this.kep = kep;
        this.manualis_datum_pick = manualis_datum_pick;

        home();
        //bevetel_hozzaadasa_layout.setVisibility(View.VISIBLE);
        nincs_bejelentkezve_layout.setVisibility(View.VISIBLE);
        //kategoria_layout.setVisibility(View.VISIBLE);
        //nyugtas_kiadas_layout.setVisibility(View.VISIBLE);

        manualis_kuldeni = new JSONObject();
        nyugtas_kuldeni = new JSONObject();


    }


    public void home(){
        nincs_bejelentkezve_layout.setVisibility(View.INVISIBLE);
        fooldal_layout.setVisibility(View.INVISIBLE);
        bejelentkezes_layout.setVisibility(View.INVISIBLE);
        regisztracio_layout.setVisibility(View.INVISIBLE);
        nyugtas_kiadas_layout.setVisibility(View.INVISIBLE);
        felvetel_valaszto_layout.setVisibility(View.INVISIBLE);
        kategoria_layout.setVisibility(View.INVISIBLE);
        manualis_layout.setVisibility(View.INVISIBLE);
        manualis_bolt_layout.setVisibility(View.INVISIBLE);
        var_layout.setVisibility(View.INVISIBLE);
        kategoria_hozzaadasa_layout.setVisibility(View.INVISIBLE);
        bevetel_hozzaadasa_layout.setVisibility(View.INVISIBLE);
        fiok_hozzaadasa_layout.setVisibility(View.INVISIBLE);

    }

    public void loop(){
        if (var)
            {
            if (xyz%10000000 == 0) {
                Log.d("ADATOK", "ADAT");
                adatok = urlKezelo.adatok(bejelentkezes_felhasznalonev_editText.getText().toString());
                //Log.d("ADATOK889", adatok.toString());
                try {
                    Log.d("ADATOK456", adatok.get(0).toString());

                    if (!adatok.get(0).toString().contains("1")){
                        var = false;
                        Log.d("ADATOK556", "SIKERES Képküldés");
                        tart = 0;
                        CharSequence charSequence = new StringBuffer("Válasszon kategóriát");
                        kategoria_text.setText(charSequence);
                        kategoria_text.setTextColor(Color.BLACK);
                        //nyugtas_kuldeni.put("tart", 0);
                        kategoria_megjelenites();


                    }

                    else{
                        Random rand = new Random();
                        int r = rand.nextInt(150);
                        int g = rand.nextInt(150);
                        int b = rand.nextInt(150);
                        kategoria_text.setTextColor(Color.rgb(50+r,50+g,50+b));}

                } catch (JSONException e) {

                    //hibas_kepkuldes();
                    Log.d("ADATOK226", adatok.toString());

                }

            }
            xyz++;
            if (xyz == 300000000) {var = false; Log.d("ADATOK156", "internet");}
            }

        if (home_.getVisibility() == View.VISIBLE){
            home_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!(nincs_bejelentkezve_layout.getVisibility() == View.VISIBLE || bejelentkezes_layout.getVisibility() == View.VISIBLE || regisztracio_layout.getVisibility() == View.VISIBLE)) {
                        home();
                        fooldal_layout.setVisibility(View.VISIBLE);
                    }
                    else if (regisztracio_layout.getVisibility() == View.VISIBLE){
                        nincs_bejelentkezve_layout.setVisibility(View.VISIBLE);
                        regisztracio_layout.setVisibility(View.INVISIBLE);
                    }
                    else if (bejelentkezes_layout.getVisibility() == View.VISIBLE){
                        nincs_bejelentkezve_layout.setVisibility(View.VISIBLE);
                        bejelentkezes_layout.setVisibility(View.INVISIBLE);
                    }

                }
            });
        }

        if (nincs_bejelentkezve_layout.getVisibility() == View.VISIBLE)
            nincs_bejelentkezve();

        if (bejelentkezes_layout.getVisibility() == View.VISIBLE)
            bejelentkezes();

        if (regisztracio_layout.getVisibility() == View.VISIBLE)
            regisztracio();

        if (fooldal_layout.getVisibility() == View.VISIBLE)
            fooldal();

        if (nyugtas_kiadas_layout.getVisibility() == View.VISIBLE)
            nyugtas_kiadas();

        if (felvetel_valaszto_layout.getVisibility() == View.VISIBLE)
            felvetel_valaszto();

        if (manualis_layout.getVisibility() == View.VISIBLE)
            manualis_termek_hozzaadas();

        if (kategoria_hozzaadasa_layout.getVisibility() == View.VISIBLE)
            kategoria_hozzaadasa();


    }


    private void kategoria_hozzaadasa() {
        Log.d("Kategoria", "itt");
        kategoria_hozzaadasa_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adat = urlKezelo.kategoria(bejelentkezes_felhasznalonev_editText.getText().toString(),
                        kategoria_hozzaadasa_editText.getText().toString());

                    if (adat.equals("{internet:1}")){
                        Toast.makeText(context, "Nem sikerült kapcsolódni a szerverhez", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(context, "A kategória hozzáadva", Toast.LENGTH_LONG).show();

                    }
            }
        });
    }


    private void kategoria_megjelenites() {
        try {

            JSONObject ho = new JSONObject(adatok.get(1).toString());
            hossz = ho.getInt("hossz");
            JSONObject json = new JSONObject(adatok.get(tart+2).toString());
            CharSequence nev = new StringBuffer(json.getString(tart+"nev"));
            ny_termeknev_editText.setText(nev);

            CharSequence ar = new StringBuffer(json.getString(tart+"ar"));
            ny_termekar_editText.setText(ar);

            CharSequence kat = new StringBuffer(json.getString(tart+"kat"));
            Log.d("ADATOK99+86", kat.toString());

                        //Log.d("ADATOK559", adatok.get(3).toString());
            Log.d("ADATOK88", adatok.get(1).toString());
            //JSONObject jsonObject = new JSONObject(adatok.get(3).toString());
            //text1 = new StringBuffer(jsonObject.get("bolti_termek_nevek").toString());


            HashMap<Integer, String > kat_ = new HashMap<>(); // kat.toString().split(",");
            for (int i=0; i<8; i++){
                String ka = kat.toString().split(",")[i];
                Log.d("ADATOK", ka);

                String k;
                while (ka.charAt(0) == '[' || ka.charAt(0) == '"'){
                    k = "";
                    for (int x=1; x<ka.length(); x++){
                        k += ka.charAt(x);
                    }
                    ka = k;

                }
                while (ka.charAt(ka.length()-1) == '"' || ka.charAt(ka.length()-1) == ']'){
                    k = "";
                    for (int x=0; x<ka.length()-1; x++){
                        k += ka.charAt(x);
                    }
                    ka = k;
                    Log.d("ADATOK", ka);
                }
                kat_.put(i, ka);
                //kat_.put(i, "ASD");
            }
            kategoria_1.setText(kat_.get(0));
            kategoria_2.setText(kat_.get(1));
            kategoria_3.setText(kat_.get(2));
            kategoria_4.setText(kat_.get(3));
            kategoria_5.setText(kat_.get(4));
            kategoria_6.setText(kat_.get(5));
            kategoria_7.setText(kat_.get(6));
            kategoria_8.setText(kat_.get(7));


            kategoria_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tart ++;

                        try {
                            JSONObject termek = new JSONObject();
                            termek.put("nev", nev);
                            termek.put("ar", ar);
                            termek.put("kat", kategoria_1.getText().toString());
                            nyugtas_kuldeni.put("tart", tart);
                            nyugtas_kuldeni.put(Integer.toString(tart), termek);
                        } catch (JSONException e) {
                            Log.d("ADATOK223", "HIBA");

                    }

                    if (tart == hossz){
                        Log.d("ADATOK556", nyugtas_kuldeni.toString());
                        JSONObject datum = new JSONObject();
                        try {
                            datum.put("ev", 2024);
                            datum.put("honap", 2);
                            datum.put("nap", 24);
                            nyugtas_kuldeni.put("datum", datum);
                            nyugtas_kuldeni.put("bolt", nyugtas_bolt_editText.getText().toString());
                            nyugtas_kuldeni.put("fiok", nyugtas_fiok_editText.getText().toString());
                            nyugtas_kuldeni.put("felhasznalonev", bejelentkezes_felhasznalonev_editText.getText().toString());
                            Log.d("ADATOK557", nyugtas_kuldeni.toString());
                            String vissza = urlKezelo.manualis(nyugtas_kuldeni);
                            Log.d("ADATOK5567", vissza);
                            nyugtas_kuldeni = new JSONObject();
                            nyugtas_kuldeni.put("tart", 0);

                            home();
                            fooldal_layout.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            Log.d("ADATOK335", "HIba");
                        }
                    }




                    kategoria_megjelenites();
                    //if (tart == hossz){Log.d("ADATOK559", "VÉGE");}
                }
            });
            kategoria_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tart ++;

                    try {
                        JSONObject termek = new JSONObject();
                        termek.put("nev", nev);
                        termek.put("ar", ar);
                        termek.put("kat", kategoria_2.getText().toString());
                        nyugtas_kuldeni.put("tart", tart);
                        nyugtas_kuldeni.put(Integer.toString(tart), termek);
                    } catch (JSONException e) {
                        Log.d("ADATOK223", "HIBA");

                    }

                    if (tart == hossz){
                        Log.d("ADATOK556", nyugtas_kuldeni.toString());
                        JSONObject datum = new JSONObject();
                        try {
                            datum.put("ev", 2024);
                            datum.put("honap", 2);
                            datum.put("nap", 24);
                            nyugtas_kuldeni.put("datum", datum);
                            nyugtas_kuldeni.put("bolt", nyugtas_bolt_editText.getText().toString());
                            nyugtas_kuldeni.put("fiok", nyugtas_fiok_editText.getText().toString());
                            nyugtas_kuldeni.put("felhasznalonev", bejelentkezes_felhasznalonev_editText.getText().toString());
                            Log.d("ADATOK557", nyugtas_kuldeni.toString());
                            String vissza = urlKezelo.manualis(nyugtas_kuldeni);
                            Log.d("ADATOK5567", vissza);
                            nyugtas_kuldeni = new JSONObject();
                            nyugtas_kuldeni.put("tart", 0);

                            home();
                            fooldal_layout.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            Log.d("ADATOK335", "HIba");
                        }
                    }




                    kategoria_megjelenites();
                }
            });
            kategoria_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tart ++;

                    try {
                        JSONObject termek = new JSONObject();
                        termek.put("nev", nev);
                        termek.put("ar", ar);
                        termek.put("kat", kategoria_3.getText().toString());
                        nyugtas_kuldeni.put("tart", tart);
                        nyugtas_kuldeni.put(Integer.toString(tart), termek);
                    } catch (JSONException e) {
                        Log.d("ADATOK223", "HIBA");

                    }

                    if (tart == hossz){
                        Log.d("ADATOK556", nyugtas_kuldeni.toString());
                        JSONObject datum = new JSONObject();
                        try {
                            datum.put("ev", 2024);
                            datum.put("honap", 2);
                            datum.put("nap", 24);
                            nyugtas_kuldeni.put("datum", datum);
                            nyugtas_kuldeni.put("bolt", nyugtas_bolt_editText.getText().toString());
                            nyugtas_kuldeni.put("fiok", nyugtas_fiok_editText.getText().toString());
                            nyugtas_kuldeni.put("felhasznalonev", bejelentkezes_felhasznalonev_editText.getText().toString());
                            Log.d("ADATOK557", nyugtas_kuldeni.toString());
                            String vissza = urlKezelo.manualis(nyugtas_kuldeni);
                            Log.d("ADATOK5567", vissza);
                            nyugtas_kuldeni = new JSONObject();
                            nyugtas_kuldeni.put("tart", 0);

                            home();
                            fooldal_layout.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            Log.d("ADATOK335", "HIba");
                        }
                    }




                    kategoria_megjelenites();
                }
            });
            kategoria_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tart ++;

                    try {
                        JSONObject termek = new JSONObject();
                        termek.put("nev", nev);
                        termek.put("ar", ar);
                        termek.put("kat", kategoria_4.getText().toString());
                        nyugtas_kuldeni.put("tart", tart);
                        nyugtas_kuldeni.put(Integer.toString(tart), termek);
                    } catch (JSONException e) {
                        Log.d("ADATOK223", "HIBA");

                    }

                    if (tart == hossz){
                        Log.d("ADATOK556", nyugtas_kuldeni.toString());
                        JSONObject datum = new JSONObject();
                        try {
                            datum.put("ev", 2024);
                            datum.put("honap", 2);
                            datum.put("nap", 24);
                            nyugtas_kuldeni.put("datum", datum);
                            nyugtas_kuldeni.put("bolt", nyugtas_bolt_editText.getText().toString());
                            nyugtas_kuldeni.put("fiok", nyugtas_fiok_editText.getText().toString());
                            nyugtas_kuldeni.put("felhasznalonev", bejelentkezes_felhasznalonev_editText.getText().toString());
                            Log.d("ADATOK557", nyugtas_kuldeni.toString());
                            String vissza = urlKezelo.manualis(nyugtas_kuldeni);
                            Log.d("ADATOK5567", vissza);
                            nyugtas_kuldeni = new JSONObject();
                            nyugtas_kuldeni.put("tart", 0);

                            home();
                            fooldal_layout.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            Log.d("ADATOK335", "HIba");
                        }
                    }




                    kategoria_megjelenites();
                }
            });
            kategoria_5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tart ++;

                    try {
                        JSONObject termek = new JSONObject();
                        termek.put("nev", nev);
                        termek.put("ar", ar);
                        termek.put("kat", kategoria_5.getText().toString());
                        nyugtas_kuldeni.put("tart", tart);
                        nyugtas_kuldeni.put(Integer.toString(tart), termek);
                    } catch (JSONException e) {
                        Log.d("ADATOK223", "HIBA");

                    }

                    if (tart == hossz){
                        Log.d("ADATOK556", nyugtas_kuldeni.toString());
                        JSONObject datum = new JSONObject();
                        try {
                            datum.put("ev", 2024);
                            datum.put("honap", 2);
                            datum.put("nap", 24);
                            nyugtas_kuldeni.put("datum", datum);
                            nyugtas_kuldeni.put("bolt", nyugtas_bolt_editText.getText().toString());
                            nyugtas_kuldeni.put("fiok", nyugtas_fiok_editText.getText().toString());
                            nyugtas_kuldeni.put("felhasznalonev", bejelentkezes_felhasznalonev_editText.getText().toString());
                            Log.d("ADATOK557", nyugtas_kuldeni.toString());
                            String vissza = urlKezelo.manualis(nyugtas_kuldeni);
                            Log.d("ADATOK5567", vissza);
                            nyugtas_kuldeni = new JSONObject();
                            nyugtas_kuldeni.put("tart", 0);

                            home();
                            fooldal_layout.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            Log.d("ADATOK335", "HIba");
                        }
                    }




                    kategoria_megjelenites();
                }
            });
            kategoria_6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tart ++;

                    try {
                        JSONObject termek = new JSONObject();
                        termek.put("nev", nev);
                        termek.put("ar", ar);
                        termek.put("kat", kategoria_6.getText().toString());
                        nyugtas_kuldeni.put("tart", tart);
                        nyugtas_kuldeni.put(Integer.toString(tart), termek);
                    } catch (JSONException e) {
                        Log.d("ADATOK223", "HIBA");

                    }

                    if (tart == hossz){
                        Log.d("ADATOK556", nyugtas_kuldeni.toString());
                        JSONObject datum = new JSONObject();
                        try {
                            datum.put("ev", 2024);
                            datum.put("honap", 2);
                            datum.put("nap", 24);
                            nyugtas_kuldeni.put("datum", datum);
                            nyugtas_kuldeni.put("bolt", nyugtas_bolt_editText.getText().toString());
                            nyugtas_kuldeni.put("fiok", nyugtas_fiok_editText.getText().toString());
                            nyugtas_kuldeni.put("felhasznalonev", bejelentkezes_felhasznalonev_editText.getText().toString());
                            Log.d("ADATOK557", nyugtas_kuldeni.toString());
                            String vissza = urlKezelo.manualis(nyugtas_kuldeni);
                            Log.d("ADATOK5567", vissza);
                            nyugtas_kuldeni = new JSONObject();
                            nyugtas_kuldeni.put("tart", 0);

                            home();
                            fooldal_layout.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            Log.d("ADATOK335", "HIba");
                        }
                    }




                    kategoria_megjelenites();
                }
            });
            kategoria_7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tart ++;

                    try {
                        JSONObject termek = new JSONObject();
                        termek.put("nev", nev);
                        termek.put("ar", ar);
                        termek.put("kat", kategoria_7.getText().toString());
                        nyugtas_kuldeni.put("tart", tart);
                        nyugtas_kuldeni.put(Integer.toString(tart), termek);
                    } catch (JSONException e) {
                        Log.d("ADATOK223", "HIBA");

                    }

                    if (tart == hossz){
                        Log.d("ADATOK556", nyugtas_kuldeni.toString());
                        JSONObject datum = new JSONObject();
                        try {
                            datum.put("ev", 2024);
                            datum.put("honap", 2);
                            datum.put("nap", 24);
                            nyugtas_kuldeni.put("datum", datum);
                            nyugtas_kuldeni.put("bolt", nyugtas_bolt_editText.getText().toString());
                            nyugtas_kuldeni.put("fiok", nyugtas_fiok_editText.getText().toString());
                            nyugtas_kuldeni.put("felhasznalonev", bejelentkezes_felhasznalonev_editText.getText().toString());
                            Log.d("ADATOK557", nyugtas_kuldeni.toString());
                            String vissza = urlKezelo.manualis(nyugtas_kuldeni);
                            Log.d("ADATOK5567", vissza);
                            nyugtas_kuldeni = new JSONObject();
                            nyugtas_kuldeni.put("tart", 0);

                            home();
                            fooldal_layout.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            Log.d("ADATOK335", "HIba");
                        }
                    }




                    kategoria_megjelenites();
                }
            });
            kategoria_8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tart ++;

                    try {
                        JSONObject termek = new JSONObject();
                        termek.put("nev", nev);
                        termek.put("ar", ar);
                        termek.put("kat", kategoria_8.getText().toString());
                        nyugtas_kuldeni.put("tart", tart);
                        nyugtas_kuldeni.put(Integer.toString(tart), termek);
                    } catch (JSONException e) {
                        Log.d("ADATOK223", "HIBA");

                    }

                    if (tart == hossz){
                        Log.d("ADATOK556", nyugtas_kuldeni.toString());
                        JSONObject datum = new JSONObject();
                        try {
                            datum.put("ev", 2024);
                            datum.put("honap", 2);
                            datum.put("nap", 24);
                            nyugtas_kuldeni.put("datum", datum);
                            nyugtas_kuldeni.put("bolt", nyugtas_bolt_editText.getText().toString());
                            nyugtas_kuldeni.put("fiok", nyugtas_fiok_editText.getText().toString());
                            nyugtas_kuldeni.put("felhasznalonev", bejelentkezes_felhasznalonev_editText.getText().toString());
                            Log.d("ADATOK557", nyugtas_kuldeni.toString());
                            String vissza = urlKezelo.manualis(nyugtas_kuldeni);
                            Log.d("ADATOK5567", vissza);
                            nyugtas_kuldeni = new JSONObject();
                            nyugtas_kuldeni.put("tart", 0);

                            home();
                            fooldal_layout.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            Log.d("ADATOK335", "HIba");
                        }
                    }




                    kategoria_megjelenites();
                }
            });

            kategoria_kuldes_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tart ++;

                    try {
                        JSONObject termek = new JSONObject();
                        termek.put("nev", nev);
                        termek.put("ar", ar);
                        termek.put("kat", kategoria_egyeb_editText.getText().toString());
                        nyugtas_kuldeni.put("tart", tart);
                        nyugtas_kuldeni.put(Integer.toString(tart), termek);
                    } catch (JSONException e) {
                        Log.d("ADATOK223", "HIBA");

                    }

                    if (tart == hossz){
                        Log.d("ADATOK556", nyugtas_kuldeni.toString());
                        JSONObject datum = new JSONObject();
                        try {
                            datum.put("ev", 2024);
                            datum.put("honap", 2);
                            datum.put("nap", 24);
                            nyugtas_kuldeni.put("datum", datum);
                            nyugtas_kuldeni.put("bolt", nyugtas_bolt_editText.getText().toString());
                            nyugtas_kuldeni.put("fiok", nyugtas_fiok_editText.getText().toString());
                            nyugtas_kuldeni.put("felhasznalonev", bejelentkezes_felhasznalonev_editText.getText().toString());
                            Log.d("ADATOK557", nyugtas_kuldeni.toString());
                            String vissza = urlKezelo.manualis(nyugtas_kuldeni);
                            Log.d("ADATOK5567", vissza);
                            nyugtas_kuldeni = new JSONObject();
                            nyugtas_kuldeni.put("tart", 0);

                            home();
                            fooldal_layout.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            Log.d("ADATOK335", "HIba");
                        }
                    }




                    kategoria_megjelenites();

                }
            });

        } catch (JSONException e) {
            Log.d("Adatok335", "HIBa");
        }
    }


    private void manualis_termek_hozzaadas() {
        manualis_mentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("datum", "ASD");

                try {

                    String nev = manualis_nev_editText.getText().toString();
                    String ar = manualis_ar_editText.getText().toString();
                    String kat = manualis_kategoria_editText.getText().toString();

                    if (!(nev.equals("") || ar.equals("") || kat.equals(""))){
                    JSONObject termek = new JSONObject();
                    termek.put("nev", nev);
                    termek.put("ar", ar);
                    termek.put("kat", kat);

                    manualis_kuldeni.put("tart", manualis_kuldeni.getInt("tart") + 1);
                    manualis_kuldeni.put(manualis_kuldeni.get("tart").toString(), termek);

                    manualis_nev_editText.setText("");
                    manualis_ar_editText.setText("");
                    manualis_kategoria_editText.setText("");
                    manualis_nev_text.setTextColor(Color.BLACK);
                    manualis_ar_text.setTextColor(Color.BLACK);
                    manualis_kategoria_text.setTextColor(Color.BLACK);
                    }
                    else {
                        if (nev.equals(""))
                            manualis_nev_text.setTextColor(Color.RED);
                        else
                            manualis_nev_text.setTextColor(Color.BLACK);
                        if (ar.equals(""))
                            manualis_ar_text.setTextColor(Color.RED);
                        else
                            manualis_ar_text.setTextColor(Color.BLACK);
                        if (kat.equals(""))
                            manualis_kategoria_text.setTextColor(Color.RED);
                        else
                            manualis_kategoria_text.setTextColor(Color.BLACK);
                    }


                } catch (JSONException e) {
                    Log.d("HIBA", "manualis_termek_hozzaadas");
                }


            }
        });
        manualis_kuldes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("datum", "Kezdődik:");
                try {
                    /*for (int i=1; i<=manualis_kuldeni.getInt("tart"); i++) {
                        Log.d("datum", manualis_kuldeni.getJSONObject(Integer.toString(i)).get("nev").toString());
                        Log.d("datum", manualis_kuldeni.getJSONObject(Integer.toString(i)).get("ar").toString());
                        Log.d("datum", manualis_kuldeni.getJSONObject(Integer.toString(i)).get("kat").toString());
                    }*/
                    if (!manualis_kuldeni.get("tart").toString().equals("0")) {
                        String vissza = urlKezelo.manualis(manualis_kuldeni);
                        Log.d("ASD", vissza);
                        if (vissza.equals("internet:1")) {
                            Toast.makeText(context, "Hiba a szerverkapcsolattal", Toast.LENGTH_LONG).show();
                        } else {
                            if (vissza.equals("hiba:1"))
                                Toast.makeText(context, "Hiba a felvétel során", Toast.LENGTH_LONG).show();
                            else {
                                Toast.makeText(context, "Sikeres felvétel", Toast.LENGTH_LONG).show();
                                manualis_layout.setVisibility(View.INVISIBLE);
                                fooldal_layout.setVisibility(View.VISIBLE);
                            }


                        }

                        manualis_kuldeni = new JSONObject();
                        manualis_kuldeni.put("tart", 0);
                    }

                    else {
                        Toast.makeText(context, "Adjon meg treméket!", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    Log.d("HIBA", "manualis_kuldes");
                }

            }

        });
    }

    void manualis(){
        felvetel_valaszto_layout.setVisibility(View.INVISIBLE);
        manualis_bolt_layout.setVisibility(View.VISIBLE);

        manualis_tovabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("datum", "ASD");
                manualis_bolt_layout.setVisibility(View.INVISIBLE);
                manualis_layout.setVisibility(View.VISIBLE);


                try {
                    int day = manualis_datum_pick.getDayOfMonth();
                    int month = manualis_datum_pick.getMonth() + 1;
                    int year = manualis_datum_pick.getYear();
                    JSONObject datum = new JSONObject();
                    datum.put("nap", day);
                    datum.put("honap", month);
                    datum.put("ev", year);
                    manualis_kuldeni.put("datum", datum);
                    manualis_kuldeni.put("bolt", manualis_bolt_editText.getText().toString());
                    manualis_kuldeni.put("fiok", manualis_fiok_editText.getText().toString());
                    manualis_kuldeni.put("tart", 0);
                    manualis_kuldeni.put("felhasznalonev", bejelentkezes_felhasznalonev_editText.getText().toString());

                    Log.d("datum", manualis_kuldeni.get("bolt").toString());

                    Log.d("datum", Integer.toString(year));
                    Log.d("datum", Integer.toString(month));
                    Log.d("datum", Integer.toString(day));



                } catch (JSONException e) {
                    Log.d("Hiba", "manualis");
                }


            }
        });
    }

    private void felvetel_valaszto() {
        manualis_kiadas_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manualis();

            }
        });

        bevetel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bevetel();
            }
        });

    }

    private void bevetel() {

        home();
        bevetel_hozzaadasa_layout.setVisibility(View.VISIBLE);

        bevetel_hozzaadasa_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adatok = urlKezelo.bevetel(bejelentkezes_felhasznalonev_editText.getText().toString(),
                                                  bevetel_fiok_editText.getText().toString(),
                                                  bevetel_ar_editText.getText().toString());

                if (adatok.equals("internet:1"))
                    Toast.makeText(context, "Nem sikerült kapcsolódni a szerverhez", Toast.LENGTH_LONG).show();
                else{
                    if (adatok.contains("1"))
                        {
                            Toast.makeText(context, "Nem létező fiók", Toast.LENGTH_LONG).show();
                            bevetel_fiok_text.setTextColor(Color.RED);
                        }
                    else
                    {
                        Toast.makeText(context, "Bevétel sikeresen hozzáadva", Toast.LENGTH_LONG).show();
                        bevetel_fiok_text.setTextColor(Color.BLACK);
                    }
                }
            }
        });


    }

    public void kep_bitmap(Bitmap kep){
        kep_ = kep;
    }
    public void hibas_kepkuldes(){
        nyugtas_kiadas_layout.setVisibility(View.INVISIBLE);
        felvetel_valaszto_layout.setVisibility(View.VISIBLE);
        nyugtas_bolt_text.setTextColor(Color.RED);
        Toast.makeText(context, "Hiba a képküldés során", Toast.LENGTH_SHORT).show();
    }
    public void sikeres_kepkuldes(){
        //home();
        //Toast.makeText(context, "Sikeres képküldés", Toast.LENGTH_SHORT).show();
        Log.d("ADATOK559", "Itt is");
        /*try{
            if (kategoriak_.getJSONObject(0).get("Hiba") == "1"){
                Log.d("kateg", "HIBA");
                //HIBA
            }
            else{
                kategoria();
                //OK
            }
        }
        catch (Exception e){
            int i=0;
        }*/
    }

    public void nyugtas_kiadas() {

        kep_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!nyugtas_bolt_editText.getText().toString().equals("") && !nyugtas_fiok_editText.getText().toString().equals("")){

                Toast.makeText(context, "Kép küldése", Toast.LENGTH_LONG).show();

                String kep_kuldes = urlKezelo.kep(bejelentkezes_felhasznalonev_editText.getText().toString(), kep_, nyugtas_bolt_editText.getText().toString(), nyugtas_fiok_editText.getText().toString());
                    //kategoria_layout.setVisibility(View.VISIBLE);
                    var = true;
                    home();
                    kategoria_layout.setVisibility(View.VISIBLE);


                    /*try {
                        kategoriak_ = new JSONArray(kep_kuldes);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    if (kep_kuldes.contains("hiba:0")) {
                        sikeres_kepkuldes();
                    } else {
                        hibas_kepkuldes();
                    }
                }*/
                }


            }
        });

    }

    public void regisztracio_hibas_adatok(String hiba) {
        int nulla = 0;
        int egy = 0;

        if (hiba.equals("{internet:1}")) {
            Toast.makeText(context, "Nem sikerült kapcsolódni a szerverhez", Toast.LENGTH_LONG).show();
        } else {

            Log.d("Vissza", hiba);

            for (int i = 0; i < hiba.length(); i++) {
                if (hiba.charAt(i) == '0') {
                    nulla++;
                    if (nulla + egy == 1)
                        regisztracio_felhasznalonev_text.setTextColor(Color.BLACK);
                    if (nulla + egy == 2)
                        regisztracio_email_text.setTextColor(Color.BLACK);
                    if (nulla + egy == 3)
                        regisztracio_jelszo_text.setTextColor(Color.BLACK);
                }
                if (hiba.charAt(i) == '1') {

                    egy++;
                    if (nulla + egy == 1) {
                        regisztracio_felhasznalonev_text.setTextColor(Color.RED);
                    }
                    if (nulla + egy == 2) {
                        regisztracio_email_text.setTextColor(Color.RED);
                    }
                    if (nulla + egy == 3) {
                        regisztracio_jelszo_text.setTextColor(Color.RED);
                    }

                }

            }

            //Toast.makeText(context, "Sikertelen regisztráció", Toast.LENGTH_SHORT).show();
        }
    }

    public void sikeres_regisztracio() {
        nincs_bejelentkezve_layout.setVisibility(View.VISIBLE);
        regisztracio_layout.setVisibility(View.INVISIBLE);
        Toast.makeText(context, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
    }

    public static String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            Log.d("HIBA", "HASH");
        }
        return base;
    }

    private void regisztracio() {

        regisztracio_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String jelszo = regisztracio_jelszo_editText.getText().toString();
                boolean szam = (jelszo.contains("0") || jelszo.contains("1") || jelszo.contains("2") || jelszo.contains("3") || jelszo.contains("4") || jelszo.contains("5") || jelszo.contains("6") || jelszo.contains("7") || jelszo.contains("8") || jelszo.contains("9"));
                if (!jelszo.toUpperCase().equals(jelszo) && !jelszo.toLowerCase().equals(jelszo) && szam && jelszo.length() >= 6){
                    Log.d("jelszo", "helyes");
                    String kod = sha256(jelszo);

                    String adat = urlKezelo.reg(regisztracio_felhasznalonev_editText.getText().toString(),
                            regisztracio_email_editText.getText().toString(),
                            kod,
                            regisztracio_egyenleg_editText.getText().toString());
                    Log.d("ADAT", adat);
                    if (adat.contains("1"))
                        regisztracio_hibas_adatok(adat);
                    else
                        sikeres_regisztracio();
                }
                else{
                    Log.d("jelszo", "helytelen");
                    Toast.makeText(context, "Jelszónak tartalmaznia kell:\nNagy betű\nKis betű\nSzám\nLegalább 6 karakter", Toast.LENGTH_LONG).show();
                    regisztracio_hibas_adatok("{nev:0, email:0, jelszo:1}");
                }

            }
        });

    }

    public void sikeres_bejelentkezes(){
        bejelentkezes_layout.setVisibility(View.INVISIBLE);
        fooldal_layout.setVisibility(View.VISIBLE);


        /*try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("felhasznalo.txt"));
            writer.write(bejelentkezes_felhasznalonev_editText.getText().toString());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Log.d("bejelentkez", "siker");

        Toast.makeText(context, "Sikeres bejelentkezés", Toast.LENGTH_SHORT).show();
    }

    public void bejelentkezes_hibas_adatok(){
        bejelentkezes_felhasznalonev_text.setTextColor(Color.RED);
        bejelentkezes_jelszo_text.setTextColor(Color.RED);


        Toast.makeText(context, "Sikertelen bejelentkezés", Toast.LENGTH_SHORT).show();
    }

    private void bejelentkezes() {
        bejelentkez_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String jelszo = bejelentkezes_jelszo_editText.getText().toString();
                String kod = sha256(jelszo);
                String adat = urlKezelo.be(bejelentkezes_felhasznalonev_editText.getText().toString(),
                                            kod);
                if (adat.contains("1"))
                    if (adat.equals("{internet:1}")){
                        Toast.makeText(context, "Nem sikerült kapcsolódni a szerverhez", Toast.LENGTH_LONG).show();
                    }
                    else {
                        bejelentkezes_hibas_adatok();
                    }
                else
                    sikeres_bejelentkezes();
            }
        });

    }

    private void fooldal(){
        fooldal_felvetel_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                felvetel_valaszto_layout.setVisibility(View.VISIBLE);
                fooldal_layout.setVisibility(View.INVISIBLE);

            }
        });

        fooldal_kategoria_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                home();
                kategoria_hozzaadasa_layout.setVisibility(View.VISIBLE);
            }
        });

        fooldal_statisztika_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        fooldal_fiok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiok_hozzaadasa();
            }
        });

    }

    private void fiok_hozzaadasa() {
        home();
        fiok_hozzaadasa_layout.setVisibility(View.VISIBLE);

        fiok_hozzaadasa_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adat = urlKezelo.fiok(bejelentkezes_felhasznalonev_editText.getText().toString(),
                        fiok_hozzaadasa_editText.getText().toString(),
                        fiok_osszeg_editText.getText().toString());

                if (adat.equals("{internet:1}")){
                    Toast.makeText(context, "Nem sikerült kapcsolódni a szerverhez", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(context, "A pénzfiók hozzáadva", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void nincs_bejelentkezve() {
        bejelentkezes_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                bejelentkezes_layout.setVisibility(View.VISIBLE);
                nincs_bejelentkezve_layout.setVisibility(View.INVISIBLE);

            }
        });

        regisztracios_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                regisztracio_layout.setVisibility(View.VISIBLE);
                nincs_bejelentkezve_layout.setVisibility(View.INVISIBLE);
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

