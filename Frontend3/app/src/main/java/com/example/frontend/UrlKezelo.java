package com.example.frontend;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class UrlKezelo {
    Context context;

    UrlKezelo(Context context){
        this.context = context;
    }

    public JsonObjectRequest URLhivas(String path) {

        String url ="http://127.0.0.1:5000/" + path;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String datetime = response.getString("datetime");
                } catch (Exception e ){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



        return request;


    }

    public JsonObjectRequest nyugtasKiadas() {

        return URLhivas("nyugtas_kiadas_felvetel/");
    }
}
