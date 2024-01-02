package com.example.frontend;

import android.content.Context;
import android.util.Log;

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

import java.nio.charset.StandardCharsets;

public class UrlKezelo {

    Context context;
    RequestQueue queue;
    String url= "http://10.0.2.2:5000/";

    Frontend1 frontend;
    public UrlKezelo(Context context, Frontend1 frontend1){
        this.context = context;
        this.frontend = frontend1;
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

    public void regisztral(String felhasznalonev, String email, String jelszo) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestBody = "{\"nev\": \""+felhasznalonev+"\", \"email\":\""+email+"\" ,\"jelszo\": \""+jelszo+"\"}";
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


                if (responseText.contains("1")){
                    frontend.regisztracio_hibas_adatok(responseText);
                }
                else{
                    frontend.sikeres_regisztracio();
                }

                return Response.success(null, null);
            }
        };
        queue.add(request);
    }

    public void bejelentkezes(String felhasznalonev, String jelszo) {
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


                if (responseText.contains("1")){
                    frontend.bejelentkezes_hibas_adatok();
                }
                else{
                    frontend.sikeres_bejelentkezes();
                }

                return Response.success(null, null);
            }
        };
        queue.add(request);

    }
}
