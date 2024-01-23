package com.example.frontend;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class UrlKezelo {

    Context context;
    RequestQueue queue;
    //String url= "http://10.0.2.2:5000/";
    String url= "http://192.168.0.110:52349/";

    public String vissza = "";
    public JSONArray vissza_1;
    Frontend1 frontend;
    //public UrlKezelo(Context context, Frontend1 frontend1){
    public UrlKezelo(Context context, Button bejelentkezes_button, Button regisztracios_button, Button bejelentkez_button, Button regisztracio_button, Button fooldal_felvetel_button, Button fooldal_kategoria_button, Button fooldal_statisztika_button, Button nyugtas_kiadas_button, Button kep_button, Button kategoria_kuldes_button, Button home,
                     Button kategoria_1, Button kategoria_2, Button kategoria_3, Button kategoria_4, Button kategoria_5, Button kategoria_6, Button kategoria_7, Button kategoria_8,
                     LinearLayout nincs_bejelentkezve_layout, LinearLayout bejelentkezes_layout, LinearLayout regisztracio_layout, LinearLayout fooldal_layout, LinearLayout felvetel_valaszto_layout, LinearLayout nyugtas_kiadas_layout, LinearLayout kategoria_layout,
                     EditText regisztracio_felhasznalonev_editText, EditText regisztracio_jelszo_editText, EditText regisztracio_email_editText, EditText bejelentkezes_felhasznalonev_editText, EditText bejelentkezes_jelszo_editText, EditText regisztracio_egyenleg_editText, EditText nyugtas_bolt_editText, EditText kategoria_egyeb_editText,
                     TextView regisztracio_felhasznalonev_text, TextView regisztracio_email_text, TextView regisztracio_jelszo_text, TextView bejelentkezes_felhasznalonev_text, TextView bejelentkezes_jelszo_text, TextView regisztracio_egyenleg_text, TextView nyugtas_bolt_text, TextView kategoria_text,
                     ImageView kep){

        this.context = context;
        frontend = new Frontend1(this, context, bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button, fooldal_felvetel_button, fooldal_kategoria_button, fooldal_statisztika_button, nyugtas_kiadas_button, kep_button, kategoria_kuldes_button, home,
                kategoria_1, kategoria_2, kategoria_3, kategoria_4, kategoria_5, kategoria_6, kategoria_7, kategoria_8,
                nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout, fooldal_layout, felvetel_valaszto_layout, nyugtas_kiadas_layout, kategoria_layout,
                regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText, regisztracio_egyenleg_editText, nyugtas_bolt_editText, kategoria_egyeb_editText,
                regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text, regisztracio_egyenleg_text, nyugtas_bolt_text, kategoria_text,
                kep);

        frontend.futas = true;
        frontend.start();


    }
    public void fuggveny1(){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "regisztralt-felhasznalok", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("fuggveny", "HTTP request OK " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fuggveny", "HTTP request failed", error);
            }
        });
        queue.add(request);
    }

    public void fuggveny2() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestBody = "{\"nev\": \"Jane Doe\", \"jelszo\": \"Xjelszo1234\"}";

        JsonRequest request = new JsonRequest(Request.Method.POST, url + "regisztralas", requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("fuggveny2", "HTTP request OK, JSONObject: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fuggveny2", "HTTP request failed", error);
            }
        }) {
            @Override
            public int compareTo(Object o) {
                return 0;
            }

            @Override
            protected Response parseNetworkResponse(NetworkResponse response) {
                String responseText = new String(response.data, StandardCharsets.UTF_8);
                Log.d("fuggveny2", "HTTP request OK " + response.headers);
                Log.d("fuggveny2", responseText);
                return Response.success(null, null);
            }
        };
        queue.add(request);
    }

    public void regisztral(String felhasznalonev, String email, String jelszo, String egyenleg) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestBody = "{\"nev\": \""+felhasznalonev+"\", \"email\": \""+email+"\", \"jelszo\": \""+jelszo+"\", \"egyenleg\": \""+egyenleg+"\"}";
        JsonRequest request = new JsonRequest(Request.Method.POST, url + "regisztral", requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("regisztáció", "HTTP request OK, JSONObject: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("regisztráció", "HTTP request failed", error);
            }
        }) {
            @Override
            public int compareTo(Object o) {
                return 0;
            }

            @Override
            protected Response parseNetworkResponse(NetworkResponse response) {
                String responseText = new String(response.data, StandardCharsets.UTF_8);
                Log.d("registráció", "HTTP request OK " + response.headers);
                Log.d("registráció", responseText);


                vissza = responseText;
                /*if (responseText.contains("1")){
                    frontend.regisztracio_hibas_adatok(responseText);
                }
                else{
                    frontend.sikeres_regisztracio();
                }*/

                return Response.success(null, null);
            }
        };
        queue.add(request);
    }

    public void bejelentkezes(String felhasznalonev, String jelszo){
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestBody = "{\"nev\": \""+felhasznalonev+"\", \"jelszo\": \""+jelszo+"\"}";
        JsonRequest request = new JsonRequest(Request.Method.POST, url + "bejelenzkez", requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("bejelentkezés", "HTTP request OK, JSONObject: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vissza = "HIBA";
                Log.d("bejelentkezés", "HTTP request failed", error);
            }
        }) {
            @Override
            public int compareTo(Object o) {
                return 0;
            }

            @Override
            protected Response parseNetworkResponse(NetworkResponse response) {

                String responseText = new String(response.data, StandardCharsets.UTF_8);

                Log.d("bejelentkezés", "HTTP request OK " + response.headers);
                Log.d("bejelentkezés", responseText);

                vissza=responseText;

                return Response.success(null, null);
            }
        };
        queue.add(request);

    }

    public String be(String nev, String jelszo){
        bejelentkezes(nev, jelszo);
        int timer1 = 0;
        while (!vissza.contains("0") && !vissza.contains("1") && timer1 < 5000000){
            timer1 ++;
        }
        String a = vissza;
        vissza = "";

        if (a.contains("0") || a.contains("1"))
            return a;
        else
            return "{internet:1}";
    }

    public String reg(String nev, String email, String jelszo, String egyenleg) {
        regisztral(nev, email, jelszo, egyenleg);
        int timer1 = 0;
        while (!vissza.contains("0") && !vissza.contains("1") && timer1 < 5000000){
            timer1 ++;
        }
        String a = vissza;
        vissza = "";
        if (a.contains("0") || a.contains("1"))
            return a;
        else
            return "{internet:1}";
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String result = Base64.encodeToString(byteArray, Base64.DEFAULT);

        return result;
    }

    public void kep_kuldes(String kep, String nev, String bolt) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url + "nyugta", new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.d("nyugta", "HTTP request OK, response: " + response);
                vissza = response;
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                vissza = "HIBA";
                Log.d("nyugta", "HTTP request failed", error);
            }
        }) {
            //adding parameters to send
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("kep", kep);
                parameters.put("bolt", bolt);
                parameters.put("nev", nev);
                return parameters;
            }
            @Override
            protected Response parseNetworkResponse(NetworkResponse response) {

                String responseText = new String(response.data, StandardCharsets.UTF_8);
                Log.d("nyugta", "HTTP request OK " + response.headers);
                Log.d("nyugta", responseText);

                vissza=responseText;

                return Response.success(null, null);
            }
        };
        queue.add(request);
    }

    public String kep(String nev, Bitmap kep, String bolt){
        kep_kuldes(getStringImage(kep), nev, bolt);
        //uzenet(nev);
        //uzenet(getStringImage(kep));
        int timer1 = 0;
        while (!vissza.contains("0") && !vissza.contains("1") && timer1 < 10000000){
            timer1 ++;
        }
        String a = vissza;
        vissza = "";
        if (a.contains("0") || a.contains("1"))
            return a;
        else
            return "{internet:1}";
    }

}
