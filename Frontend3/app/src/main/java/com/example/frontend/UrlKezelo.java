package com.example.frontend;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.URL;

public class UrlKezelo {
    Context context;
    Loop loop;

    UrlKezelo(Context context, Loop loop){
        this.context = context;
        this.loop = loop;
    }

    public void URLhivas(String path) {

        String url ="https://127.0.0.1:5000/" + path;

        /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String datum = response.getString("datum");
                    loop.setSzoveg2("ASD");
                } catch (Exception e ){
                    loop.setSzoveg2(e.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loop.setSzoveg2(error.toString());

            }
        });

        loop.setSzoveg2(request.toString());*/
        // return request;

        return;


    }

    public void nyugtasKiadas() {

        URLhivas("proba/");
    }
}
