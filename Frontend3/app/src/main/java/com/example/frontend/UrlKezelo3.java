package com.example.frontend;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class UrlKezelo3 {

    Context context;
    RequestQueue queue;
    String url= "https://reqbin.com/";
    Loop loop;


    public UrlKezelo3(Context context, Loop loop){
        this.context = context;
        this.loop = loop;


    }

    public void fuggveny(){
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                loop.setSzoveg2(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loop.setSzoveg2(error.toString());

            }
        });

        queue.add(request);

        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    loop.setSzoveg2("A válasz: " + jsonObject.toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loop.setSzoveg2("" + error.toString());
            }
        });

        queue.add(stringRequest);*/


    }

}
