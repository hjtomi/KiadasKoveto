package com.example.frontend;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UrlKezelo {

    Context context;
    RequestQueue queue;
    //String url= "http://10.0.2.2:5000/";
    String url= "http://192.168.0.109:52349/";

    public String vissza = "";
    public JSONArray vissza_1;
    Frontend1 frontend;
    //public UrlKezelo(Context context, Frontend1 frontend1){
    public UrlKezelo(Context context, Button bejelentkezes_button, Button regisztracios_button, Button bejelentkez_button, Button regisztracio_button, Button fooldal_felvetel_button, Button fooldal_kategoria_button, Button fooldal_statisztika_button, Button nyugtas_kiadas_button, Button kep_button, Button kategoria_kuldes_button, Button home,
                     Button kategoria_1, Button kategoria_2, Button kategoria_3, Button kategoria_4, Button kategoria_5, Button kategoria_6, Button kategoria_7, Button kategoria_8,
                     Button manualis_kiadas_button, Button manualis_kuldes, Button manualis_mentes, Button manualis_tovabb, Button kategoria_hozzaadasa_button, Button bevetel_button,
                     Button fooldal_fiok_button,
                     LinearLayout nincs_bejelentkezve_layout, LinearLayout bejelentkezes_layout, LinearLayout regisztracio_layout, LinearLayout fooldal_layout, LinearLayout felvetel_valaszto_layout, LinearLayout nyugtas_kiadas_layout, LinearLayout kategoria_layout, LinearLayout manualis_layout, LinearLayout manualis_bolt_layout, LinearLayout var_layout, LinearLayout kategoria_hozzaadasa_layout, LinearLayout bevetel_hozzaadasa_layout,
                     LinearLayout fiok_hozzaadasa_layout,
                     EditText regisztracio_felhasznalonev_editText, EditText regisztracio_jelszo_editText, EditText regisztracio_email_editText, EditText bejelentkezes_felhasznalonev_editText, EditText bejelentkezes_jelszo_editText, EditText regisztracio_egyenleg_editText, EditText nyugtas_bolt_editText, EditText kategoria_egyeb_editText,
                     EditText manualis_bolt_editText, EditText manualis_ar_editText, EditText manualis_nev_editText, EditText manualis_kategoria_editText, EditText manualis_fiok_editText, EditText nyugtas_fiok_editText, EditText kategoria_hozzaadasa_editText, EditText ny_termekar_editText, EditText ny_termeknev_editText,
                     TextView regisztracio_felhasznalonev_text, TextView regisztracio_email_text, TextView regisztracio_jelszo_text, TextView bejelentkezes_felhasznalonev_text, TextView bejelentkezes_jelszo_text, TextView regisztracio_egyenleg_text, TextView nyugtas_bolt_text, TextView kategoria_text,
                     TextView manualis_ar_text, TextView manualis_bolt_text, TextView manualis_datum_text, TextView manualis_nev_text, TextView manualis_kategoria_text, TextView manualis_fiok_text, TextView var_text, TextView nyugtas_fiok_text, TextView kategoria_hozzaadasa_text,
                     ImageView kep, DatePicker manualis_datum_pick){

        this.context = context;
        frontend = new Frontend1(this, context, bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button, fooldal_felvetel_button, fooldal_kategoria_button, fooldal_statisztika_button, nyugtas_kiadas_button, kep_button, kategoria_kuldes_button, home,
                kategoria_1, kategoria_2, kategoria_3, kategoria_4, kategoria_5, kategoria_6, kategoria_7, kategoria_8,
                manualis_kiadas_button, manualis_kuldes, manualis_mentes, manualis_tovabb, kategoria_hozzaadasa_button, bevetel_button,
                fooldal_fiok_button,
                nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout, fooldal_layout, felvetel_valaszto_layout, nyugtas_kiadas_layout, kategoria_layout, manualis_layout, manualis_bolt_layout, var_layout, kategoria_hozzaadasa_layout, bevetel_hozzaadasa_layout,
                fiok_hozzaadasa_layout,
                regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText, regisztracio_egyenleg_editText, nyugtas_bolt_editText, kategoria_egyeb_editText,
                manualis_bolt_editText, manualis_ar_editText, manualis_nev_editText, manualis_kategoria_editText, manualis_fiok_editText, nyugtas_fiok_editText, kategoria_hozzaadasa_editText, ny_termekar_editText, ny_termeknev_editText,
                regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text, regisztracio_egyenleg_text, nyugtas_bolt_text, kategoria_text,
                manualis_ar_text, manualis_bolt_text, manualis_datum_text, manualis_nev_text, manualis_kategoria_text, manualis_fiok_text, var_text, nyugtas_fiok_text, kategoria_hozzaadasa_text,
                kep, manualis_datum_pick);

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
        while (!vissza.contains("0") && !vissza.contains("1") && timer1 < 50000000){
            timer1 ++;
        }
        String a = vissza;
        vissza = "";

        if (a.contains("0") || a.contains("1"))
            return a;
        else
            return "{internet:1}";
    }

    public void kategoria_hozzaadasa(String felhasznalonev, String kategoria){
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestBody = "{\"nev\": \""+felhasznalonev+"\", \"kategoria\": \""+kategoria+"\"}";
        JsonRequest request = new JsonRequest(Request.Method.POST, url + "kategoria_hozzaadasa", requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("kategoria", "HTTP request OK, JSONObject: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vissza = "HIBA";
                Log.d("kategoria", "HTTP request failed", error);
            }
        }) {
            @Override
            public int compareTo(Object o) {
                return 0;
            }

            @Override
            protected Response parseNetworkResponse(NetworkResponse response) {

                String responseText = new String(response.data, StandardCharsets.UTF_8);

                Log.d("kategoria", "HTTP request OK " + response.headers);
                Log.d("kategoria", responseText);

                vissza=responseText;

                return Response.success(null, null);
            }
        };
        queue.add(request);

    }

    public String kategoria(String nev, String kategoria){
        kategoria_hozzaadasa(nev, kategoria);
        int timer1 = 0;
        while (!vissza.contains("0") && !vissza.contains("1") && timer1 < 50000000){
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
        while (!vissza.contains("0") && !vissza.contains("1") && timer1 < 50000000){
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

    public void feldolgozott_adatok(String nev) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "adatok_"+"?nev="+nev, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("ADATOK", "HTTP request OK " + response);
                vissza_1 = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ADATOK", "HTTP request failed", error);
                String hiba = "{\"nev\" : \"dani\"}";
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("varj", "1");
                    vissza_1 = new JSONArray();
                    vissza_1.put(jsonObject);
                    Log.d("ADATOK403", "HIBA");
                } catch (JSONException e) {
                    Log.d("ADATOK404", "HIBAA");
                }

            }
        });
        queue.add(request);
    }
    public void kep_kuldes(String kep, String nev, String bolt, String fiok) {
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
                parameters.put("fiok", fiok);
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

    public JSONArray adatok(String nev) {
        feldolgozott_adatok(nev);
        Log.d("ADATOK4", "VAGYOK");
        //uzenet(nev);
        //uzenet(getStringImage(kep));
        int timer1 = 0;
        while (timer1 < 100000000){
            timer1 ++;
        }
        JSONArray a;
        a = vissza_1;
        vissza_1 = new JSONArray();
        Log.d("ADATOK65", "kll");
        return a;
    }

    public String kep(String nev, Bitmap kep, String bolt, String fiok){
        kep_kuldes(getStringImage(kep), nev, bolt, fiok);
        //uzenet(nev);
        //uzenet(getStringImage(kep));
        int timer1 = 0;
        while (!vissza.contains("0") && !vissza.contains("1") && timer1 < 100000){
            timer1 ++;
        }
        String a = vissza;
        vissza = "";
        Log.d("Timer", Integer.toString(timer1));
        if (a.contains("Sikeres kuldes"))
            return a;
        else
            return "{internet:1}";
    }

    public String JsonToString(JSONObject json){
        String str = "";

        Iterator<String> keys = json.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            try {
                if (json.get(key) instanceof JSONObject) {
                    str = str + ((JSONObject) json.get(key)).toString();
                    // do something with jsonObject here
                }
            } catch (JSONException e) {
                Log.d("HIBA", "HIBA jsonToStirong");
            }
        }

        return str;
    }

    public void manualis_kuldes(JSONObject adatok){
        RequestQueue queue = Volley.newRequestQueue(context);
        //String requestBody = "{\"nev\": \""+"dani\"}";
        String requestBody = adatok.toString();
        JsonRequest request = new JsonRequest(Request.Method.POST, url + "manualis", requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("manualis", "HTTP request OK, JSONObject: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vissza = "HIBA";
                Log.d("manualis", "HTTP request failed", error);
            }
        }) {
            @Override
            public int compareTo(Object o) {
                return 0;
            }

            @Override
            protected Response parseNetworkResponse(NetworkResponse response) {

                String responseText = new String(response.data, StandardCharsets.UTF_8);

                Log.d("manualis", "HTTP request OK " + response.headers);
                Log.d("manualis", responseText);

                vissza=responseText;

                return Response.success(null, null);
            }
        };
        queue.add(request);

    }


    public String manualis(JSONObject adatok){
        Log.d("ADATOK999", "manualis");
        manualis_kuldes(adatok);
        //uzenet(nev);
        //uzenet(getStringImage(kep));
        int timer1 = 0;
        while (!vissza.contains("0") && !vissza.contains("1") && timer1 < 50000000){
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
