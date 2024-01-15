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

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class UrlKezelo {

    Context context;
    RequestQueue queue;
   //String url= "https://10.0.2.2:5000/";
    String url= "http://192.168.0.110:52349/";

    public String vissza = "";
    Frontend1 frontend;
    //public UrlKezelo(Context context, Frontend1 frontend1){
    public UrlKezelo(Context context, Button bejelentkezes_button, Button regisztracios_button, Button bejelentkez_button, Button regisztracio_button, Button fooldal_felvetel_button, Button fooldal_kategoria_button, Button fooldal_statisztika_button, Button nyugtas_kiadas_button, Button kep_button,
                     LinearLayout nincs_bejelentkezve_layout, LinearLayout bejelentkezes_layout, LinearLayout regisztracio_layout, LinearLayout fooldal_layout, LinearLayout felvetel_valaszto_layout, LinearLayout nyugtas_kiadas_layout,
                     EditText regisztracio_felhasznalonev_editText, EditText regisztracio_jelszo_editText, EditText regisztracio_email_editText, EditText bejelentkezes_felhasznalonev_editText, EditText bejelentkezes_jelszo_editText, EditText regisztracio_egyenleg_editText,
                     TextView regisztracio_felhasznalonev_text, TextView regisztracio_email_text, TextView regisztracio_jelszo_text, TextView bejelentkezes_felhasznalonev_text, TextView bejelentkezes_jelszo_text, TextView regisztracio_egyenleg_text,
                     ImageView kep){

        this.context = context;
        frontend = new Frontend1(this, context, bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button, fooldal_felvetel_button, fooldal_kategoria_button, fooldal_statisztika_button, nyugtas_kiadas_button, kep_button,
                nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout, fooldal_layout, felvetel_valaszto_layout, nyugtas_kiadas_layout,
                regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText, regisztracio_egyenleg_editText,
                regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text, regisztracio_egyenleg_text,
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
                vissza = "1";
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
        while (!vissza.contains("0") && !vissza.contains("1")){
            continue;
        }
        String a = vissza;
        vissza = "";
        return a;
    }

    public String reg(String nev, String email, String jelszo, String egyenleg) {
        regisztral(nev, email, jelszo, egyenleg);
        while (!vissza.contains("0") && !vissza.contains("1")){
            continue;
        }
        String a = vissza;
        vissza = "";
        Log.d("a", a);
        return a;
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String result = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.d("ADFGH", result);

        return result;
    }

    public void kep_kuldes(String kep, String nev) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestBody = "{\"felhasznalonev\": "+nev+", \"kep\": "+kep+"}";
        JsonRequest request = new JsonRequest(Request.Method.POST, url + "nyugta", requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("nyugta", "HTTP request OK, JSONObject: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vissza = "1";
                Log.d("nyugta", "HTTP request failed", error);
            }
        }) {
            @Override
            public int compareTo(Object o) {
                return 0;
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

    public void uzenet(String uzenet) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //String requestBody = "{\"uzenet\": \""+uzenet+"\"}";
        JsonRequest request = new JsonRequest(Request.Method.GET, url + "proba?Query=" + uzenet , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("nyugta", "HTTP request OK, JSONObject: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vissza = "1";
                Log.d("nyugta", "HTTP request failed", error);
            }
        }) {
            @Override
            public int compareTo(Object o) {
                return 0;
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

    public String kep(String nev, Bitmap kep){
        //kep_kuldes(getStringImage(kep), nev);
        //uzenet(nev);
        uzenet(getStringImage(kep));
        while (!vissza.contains("0") && !vissza.contains("1")){
            continue;
        }
        String a = vissza;
        vissza = "";
        return a;
    }


}
